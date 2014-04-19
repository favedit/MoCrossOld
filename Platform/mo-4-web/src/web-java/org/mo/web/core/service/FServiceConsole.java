package org.mo.web.core.service;

import java.sql.SQLException;
import org.mo.com.console.FConsole;
import org.mo.com.data.MSqlConnect;
import org.mo.com.data.RSql;
import org.mo.com.lang.FError;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FMap;
import org.mo.com.lang.IRelease;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.FMethod;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.message.FErrorMessage;
import org.mo.com.message.FFatalMessage;
import org.mo.com.message.FInfoMessage;
import org.mo.com.message.FMessages;
import org.mo.com.message.FValidMessage;
import org.mo.com.message.FWarnMessage;
import org.mo.com.message.IMessage;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlContext;
import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.session.FSqlSessionContext;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.IWebContainerConsole;
import org.mo.web.core.container.common.FWebContainerItem;
import org.mo.web.core.message.IWebMessageConsole;
import org.mo.web.core.service.common.FServiceDescriptor;
import org.mo.web.core.service.common.FServiceMethodDescriptor;
import org.mo.web.core.service.common.FWebService;
import org.mo.web.core.service.common.FWebServiceMap;
import org.mo.web.core.service.common.XAopService;
import org.mo.web.core.session.IWebSession;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>服务命令处理控制台。</T>
// <P>根据访问的地址，对页面服务执行分发处理。</P>
//============================================================
public class FServiceConsole
      extends FConsole
      implements
         IServiceConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FServiceConsole.class);

   // 数据检查
   public static String PTY_CHECKED = "checked";

   // 传送数据时使用编码方式
   @AProperty
   protected String _encoding;

   // 绑定控制台接口
   @ALink
   protected IBindConsole _bindConsole;

   // 数据库管理接口
   @ALink
   protected IDatabaseConsole _databaseConsole;

   // 数据表单控制台接口
   @ALink
   protected IWebContainerConsole _formConsole;

   // 结果消息控制台
   @ALink
   protected IWebMessageConsole _messageConsole;

   // 服务类描述器哈希集合
   @SuppressWarnings("rawtypes")
   protected FMap<Class, FServiceDescriptor> _descriptors = new FMap<Class, FServiceDescriptor>(Class.class, FServiceDescriptor.class);

   // 页面服务哈希集合
   protected FWebServiceMap _services = new FWebServiceMap();

   //============================================================
   // <T>构造服务命令处理控制台。</T>
   //============================================================
   public FServiceConsole(){
   }

   //============================================================
   // <T>获得字符编码的类型。</T>
   //
   // @return 字符编码
   //============================================================
   @Override
   public String encoding(){
      return _encoding;
   }

   //============================================================
   // <T>查找函数。</T>
   //
   // @param clazz 类对象
   // @param name 名称
   // @return 函数描述器
   //============================================================
   public FServiceMethodDescriptor findMethod(FClass<?> clazz,
                                              String name){
      FServiceDescriptor descriptor = _descriptors.find(clazz.nativeObject());
      if(descriptor == null){
         descriptor = new FServiceDescriptor(clazz.nativeObject());
         _descriptors.set(clazz.nativeObject(), descriptor);
      }
      // Find Method Descriptor 
      name = (name != null) ? name.toLowerCase() : "process";
      FServiceMethodDescriptor methodDescriptor = descriptor.find(name);
      if(methodDescriptor == null){
         for(FMethod method : clazz.methods(false)){
            if(name.equalsIgnoreCase(method.name())){
               methodDescriptor = new FServiceMethodDescriptor(method.nativeObject());
               break;
            }
         }
         if(methodDescriptor != null){
            descriptor.push(name, methodDescriptor);
         }
      }
      return methodDescriptor;
   }

   //============================================================
   // <T>查找服务对象。</T>
   //
   // @param name 名称
   // @return 服务对象
   //============================================================
   public FWebService findService(String name){
      FWebService service = null;
      name = RString.nvl(name).toLowerCase();
      service = _services.find(name);
      if(service == null){
         XAopService xservice = RAop.configConsole().findNode(XAopService.NAME, name);
         if(xservice != null){
            service = new FWebService();
            if(service.construct(xservice)){
               _services.set(name, service);
            }
         }
      }
      return service;
   }

   // 根据输出信息建立消息输出节点信息
   protected void buildMessages(IWebContext context,
                                IWebOutput output){
      FMessages messages = context.messages();
      if(!messages.isEmpty()){
         output.config().nodes().clear();
         FXmlNode msgNode = output.config().createNode("Messages");
         if(messages.hasMessage(FFatalMessage.class)){
            // 如果存在严重错误的情况
            FFatalMessage msg = messages.message(FFatalMessage.class);
            msgNode.nodes().push(msg.convertToNode());
         }else if(messages.hasMessage(FErrorMessage.class)){
            // 如果存在逻辑错误的情况
            FErrorMessage msg = messages.message(FErrorMessage.class);
            msgNode.nodes().push(msg.convertToNode());
         }else{
            // 如果存在校验错误的情况
            if(messages.hasMessage(FValidMessage.class)){
               FMessages msgs = messages.messages(FValidMessage.class);
               for(IMessage msg : msgs){
                  msgNode.nodes().push(msg.convertToNode());
               }
            }
            // 如果存在警告信息的情况
            if(messages.hasMessage(FWarnMessage.class)){
               FMessages msgs = messages.messages(FWarnMessage.class);
               for(IMessage msg : msgs){
                  msgNode.nodes().push(msg.convertToNode());
               }
            }
            // 如果存在提示信息的情况
            if(messages.hasMessage(FInfoMessage.class)){
               FMessages msgs = messages.messages(FInfoMessage.class);
               for(IMessage msg : msgs){
                  msgNode.nodes().push(msg.convertToNode());
               }
            }
         }
      }
   }

   //============================================================
   // <T>根据名称查找实例。</T>
   //
   // @param name 名称
   // @return 实例
   //============================================================
   @Override
   public Object findInstance(String name){
      Object instance = null;
      FWebService service = findService(name);
      if(service != null){
         String face = service.face();
         if(!RString.isBlank(face)){
            instance = RAop.find(face);
            if(instance == null){
               _logger.debug(this, "execute", "Can't find service. (face={1})", face);
            }
         }
      }
      if(_logger.debugAble()){
         _logger.debug(this, "execute", "Find service. (name={1}, instance={2})", name, instance);
      }
      return instance;
   }

   //============================================================
   // <T>根据服务名称执行一个服务处理过程。</T>
   //
   // @param name 服务名称
   // @param context 页面环境
   // @param input 输入信息
   // @param output 输出信息
   //============================================================
   @Override
   public void execute(String name,
                       IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // 找到服务对象的定义
      FWebService service = findService(name);
      if(null == service){
         _logger.warn(this, "execute", "not find service. (name={1}, service={1})", name, service);
         return;
      }
      // 找到服务对象的实例
      Object instance = findInstance(name);
      if(null == instance){
         _logger.warn(this, "execute", "not find service method (name={1}, instance={2})", name, instance);
         return;
      }
      // 找到服务对象实例对应的默认处理函数
      String action = input.get("action");
      action = RString.nvl(action, "process");
      FServiceMethodDescriptor methodDsp = findMethod(service.faceClass(), action);
      if(null == methodDsp){
         _logger.warn(this, "execute", "Can't find method in service. (instance={1}, action={2})", instance, action);
         return;
      }
      _logger.debug(this, "execute", "Process service. (name={1}, instance={2}, action={3})", name, instance, action);
      // 检查当前函数是否需要登录认证
      if(methodDsp.testRequireLogin()){
         IWebSession session = context.session();
         if(!session.user().isLogin()){
            // 返回用户未登录画面
            FXmlNode msgsNode = output.config().createNode("Messages");
            FXmlNode fatalNode = msgsNode.createNode("Fatal");
            fatalNode.set("type", "session.timeout");
            fatalNode.set("redirect", _messageConsole.loginWithout());
            return;
         }
      }
      // 调用函数对象
      Class<?>[] types = methodDsp.types();
      AContainer[] aforms = methodDsp.forms();
      FWebContainerItem[] forms = new FWebContainerItem[types.length];
      Object[] params = new Object[types.length];
      Throwable exception = null;
      ISqlContext sqlContext = null;
      ISqlSessionContext sqlSessionContext = null;
      try{
         for(int n = 0; n < types.length; n++){
            Class<?> type = types[n];
            Object value = null;
            if(type == IWebContext.class){
               // 当参数对象为网络环境对象时
               value = context;
            }else if(type == ISqlContext.class){
               // 当参数对象为数据库环境对象时
               if(null != sqlContext){
                  throw new FFatalError("");
               }
               value = new FSqlContext(_databaseConsole);
            }else if(type == ISqlSessionContext.class){
               // 当参数对象为线程数据对象时
               if(null != sqlSessionContext){
                  // 只允许存在一个线程数据对象
                  throw new FFatalError("");
               }
               sqlSessionContext = new FSqlSessionContext(_databaseConsole);
               value = sqlSessionContext;
            }else if(type == IWebInput.class){
               value = input;
            }else if(type == IWebOutput.class){
               value = output;
            }else if(type.isInterface()){
               String className = RClass.shortName(type);
               if(className.startsWith("I") && className.endsWith("Di")){
                  // 如果为数据库对象时
                  if(null == sqlSessionContext){
                     sqlSessionContext = new FSqlSessionContext(_databaseConsole);
                  }
                  className = className.substring(1, className.length() - 2);
                  className = RClass.packageName(type) + ".impl.F" + className + "Impl";
                  MSqlConnect impl = RClass.newInstance(className);
                  impl.linkConnect(sqlSessionContext);
                  value = impl;
               }else{
                  // 如果为配置的接口对象时
                  value = RAop.find(type);
               }
            }else if(aforms[n] != null){
               forms[n] = _formConsole.findContainer(context, aforms[n], type);
               value = forms[n].container();
               context.define(aforms[n].name(), value);
            }else{
               throw new FFatalError("Build param error. (type={1})", type);
            }
            params[n] = value;
         }
         if(null != sqlSessionContext){
            // 连接线程
            sqlSessionContext.link(context.session().connectId());
         }
         // 动态调用方法
         methodDsp.invoke(instance, params);
      }catch(Throwable throwable){
         exception = throwable;
         // 产生例外时，处理例外内容
         boolean isSqlException = false;
         if(throwable instanceof FError){
            Throwable root = ((FError)throwable).rootThrowable();
            if(root instanceof SQLException){
               isSqlException = true;
               RSql.parseSqlException(context.messages(), root);
            }
         }
         if(!isSqlException){
            context.messages().push(new FFatalMessage(throwable));
         }
      }finally{
         // 释放所有调用参数
         for(Object param : params){
            if((param != sqlContext) && (param != sqlSessionContext) && (param instanceof IRelease)){
               try{
                  ((IRelease)param).release();
               }catch(Exception e){
                  exception = e;
               }
            }
         }
         // 处理含有线程数据库访问的情况
         if(null != sqlSessionContext){
            if(null == exception){
               // 断开线程连接
               sqlSessionContext.unlink();
               // 分析是否提交数据库数据
               String checked = input.config().get(PTY_CHECKED);
               if(RBoolean.parse(checked)){
                  // 用户确认的情况下，只要没有错误，就提交数据。
                  sqlSessionContext.release();
               }else{
                  // 将消息加入消息列表
                  context.messages().append(sqlSessionContext.messages());
                  // 判断消息内容
                  if(context.messages().hasMessage(FWarnMessage.class)){
                     // 当存在警告时，回滚数据。
                     sqlSessionContext.rollback();
                  }else{
                     // 当不存在警告时，提交数据。
                     sqlSessionContext.release();
                  }
               }
            }else{
               // 当存在例外时，回滚所有数据
               sqlSessionContext.rollback();
            }
         }
         // 处理不含有线程数据库访问的情况
         if(null != sqlContext){
            if(null == exception){
               sqlContext.release();
            }else{
               // 当存在例外时，回滚所有数据
               sqlContext.rollback();
            }
         }
         // 建立所有例外消息
         buildMessages(context, output);
      }
   }

   //============================================================
   // <T>初始化配置信息。</T>
   //============================================================
   public void initializeConfig(){
      //      FCommandMap map = _configConsole.commandMap(FServiceCommand.Name);
      //      int count = map.count();
      //      for(int n = 0; n < count; n++){
      //         FServiceCommand cmd = (FServiceCommand)map.value(n);
      //         FService service = new FService();
      //         if(service.construct(cmd)){
      //            _services.set(service.name(), service);
      //         }
      //      }
   }

   //============================================================
   // <T>初始化监视器。</T>
   //============================================================
   public void initializeMonitor(){
      //m_oMonitor = new FServiceMonitor(this, m_sDirectory);
      //FMonitorManager.console().push(m_oMonitor);
   }

   //============================================================
   // <T>加载设置文件。</T>
   //
   // @param fileName 设置文件
   //============================================================
   public void loadConfig(String fileName){
      //      FXmlDocument oDocument = new FXmlDocument(fileName);
      //      for(FXmlNode oNode : oDocument.rootNode().nodes()){
      //         if(oNode.isName("Service")){
      //            m_oServicesNodes.remove("Service", "name", oNode.attribute("name"));
      //            m_oServicesNodes.push(oNode);
      //         }
      //      }
      //      if(FLogger.canDebug()){
      //         FString sMsg = new FString();
      //         sMsg.append("Load config ");
      //         sMsg.append(fileName);
      //         FLogger.debug(this, "loadConfig", sMsg);
      //      }
   }
}
