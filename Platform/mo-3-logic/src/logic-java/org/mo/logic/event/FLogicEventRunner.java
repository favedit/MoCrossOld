/*
 * @(#)FLogicEventRunner.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FThrowables;
import org.mo.com.lang.RObject;
import org.mo.com.lang.RString;
import org.mo.com.lang.RThrowable;
import org.mo.com.lang.reflect.FMethodDescriptor;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FRunnable;
import org.mo.com.system.RThread;
import org.mo.core.aop.RAop;
import org.mo.core.aop.RAopDescriptor;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlContext;
import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.data.ILgEventDi;
import org.mo.logic.data.impl.FLgEventImpl;

/**
 * <T>事件运行执行者</T>
 * <P>设置事件线程的调起函数</P>
 * <P>设置事件的状态</P>
 * <P>判断事件的执行类型</P>
 * <P>将事件执行数据结果打包</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEventRunner
      extends FRunnable
{
   // 全局日志对象
   private final static ILogger _logger = RLogger.find(FLogicEventRunner.class);

   /**
    * <T>调用其他plsql参数</T>
    */
   public final static String PARAMS = "params_";

   /**
    * <T>事件标识</T>
    */
   public final static String PRM_EVENT_ID = "event_id_";

   /**
    * <T>业务逻辑</T>
    */
   public final static String PRM_LOGIC = "logic_";

   /**
    * <T>session包名</T>
    */
   public final static String PTY_SESSION_PACKAGE = "CP_SESSION";

   /**
    * <T>获取session信息的方法名</T>
    */
   public final static String PTY_METHOD = "EVENT_LINK";

   /**
    * <T>结果code号</T>
    */
   public final static String PTY_CODE = "code";

   /**
    * <T>结果描述</T>
    */
   public final static String PTY_DESCRIPTION = "description";

   /**
    * <T>结果参数</T>
    */
   public final static String PTY_PARAMETERS = "parameters";

   /**
    * <T>结果类型</T>
    */
   public final static String PTY_TYPE_ID = "type_cd";

   // 数据库服务组件对象
   protected IDatabaseConsole _databaseConsole = RAop.find(IDatabaseConsole.class);

   // 事件列表容器
   protected FLogicEvents _events = new FLogicEvents();

   // 线程状态标识
   protected boolean _isExecute = false;

   // 线程池对象
   protected FLogicEventThreadPool _pool = new FLogicEventThreadPool();

   /**
    * <T>调用plsql里Do_Start()函数进行处理</T>
    * <P>调用事件包中的<R link = 'ps:LG_EVENT_DI|Do_Start'>Do_Start()</R>，<B/>
    *    修改事件的状态为Running(R)</P>
    * 
    * @param event 修改状态的事件
    */
   public void doEventStart(ILogicEvent event){
      ISqlConnection sqlCnn = null;
      try{
         /// 收集数据库连接
         sqlCnn = _databaseConsole.alloc();
         ILgEventDi lgEvent = new FLgEventImpl(sqlCnn);
         lgEvent.doStart(null, event.id().get());
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   /**
    * <T>调用plsql里Do_Stop()函数进行处理</T>
    * <P title='函数调用'>调用事件包中的<R link = 'ps:LG_EVENT_DI|Do_Stop'>Do_Start()>Do_Stop</R>，<B/>
    *    修改事件的状态为Stop(S)</P>
    * <P>在事件处理完后调用该函数进行处理，<B/>
    *    通过该函数设置事件的状态等并将该事件的执行那个结果状态等打包传入后台
    * </P>
    * 
    * @param event 修改状态的事件
    * @param exception 产生的例外
    */
   public void doEventStop(ILogicEvent event,
                           Throwable exception){
      ISqlConnection sqlCnn = null;
      try{
         /// 如果执行过程中产生错误
         if(null != exception){
            FThrowables throwables = RThrowable.buildStack(exception);
            /// 创建错误对象，设置其内容
            FLogicEventError error = new FLogicEventError();
            error.setCode("logic.event.error");
            error.parameters().set("message", throwables.last().getMessage());
            error.setDescription(RString.left(RThrowable.buildDescription(exception).toString(), 2000));
            /// 将错误加入到结果列表中
            event.setError(error);
         }
         /// 停止当前事件
         sqlCnn = _databaseConsole.alloc();
         ILgEventDi lgEvent = new FLgEventImpl(sqlCnn);
         String resultPack = null;
         if(event.hasResult()){
            resultPack = packResult(event);
         }
         lgEvent.doStop(null, event.id().get(), resultPack);
      }catch(Throwable e){
         _logger.error(this, "doEventStop", e, "Stop event failure. (event_id={0})", event.id().get());
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.com.system.FRunnable#execute()
    */
   @Override
   public boolean execute(){
      ILogicEvent event = null;
      while(!RThread.checkStop()){
         /// 对_events加锁，此时不允许插入
         synchronized(_events){
            event = _events.get(0);
            if(null != event){
               /// 删除第一个事件
               _events.erase(0);
               /// 对事件按优先级和创建时间排序
               //RObject.sort(_events.memory(), 0, _events.count(), true);
            }
         }
         if(null == event){
            break;
         }
         processEvent(event);
      }
      _isExecute = false;
      return true;
   }

   /**
    * <T>类型为调用可执行文件时的处理</T>
    * <P>启动一个进程执行事件对象</P>
    * 
    * @param event 事件对象
    */
   public void invokeCommand(ILogicEvent event){
      FLogicEventProcess process = new FLogicEventProcess();
      process.setCommand(event.processInvoke());
      process.setEvent(event);
      process.setRunner(this);
      process.start();
   }

   /**
    * <T>类型为调用java端时的处理</T>
    * <P>根据processInvoke字段的值，调用java端函数</P>
    * 
    * @param event 事件队列对象
    */
   public void invokeJava(ILogicEvent event){
      /// 解析process_invoke字段，获得接口对象名和方法名
      String processInvoke = event.processInvoke();
      if(RString.isBlank(processInvoke)){
         throw new FFatalError("No invoke. (event_id={0})", event.id());
      }
      String[] splitResult = RString.splitTwo(processInvoke, '@', true);
      if(null == splitResult){
         throw new FFatalError("Invoke's format is invalid. (event_id={0},invoke={1})", event.id(), processInvoke);
      }
      String methodName = splitResult[0];
      String faceName = splitResult[1];
      /// 用RAop找到接口对象
      Object instance = RAop.tryFind(faceName);
      if(null == instance){
         throw new FFatalError("Interface's instance is not found. (event_id={0},interface={1})", event.id(), faceName);
      }
      /// 找到方法
      FMethodDescriptor method = RAopDescriptor.findMethod(faceName, methodName);
      if(null == method){
         throw new FFatalError("Interface's method is not found. (event_id={0},method={1})", event.id(), methodName);
      }
      Throwable exception = null;
      /// 创建参数数组
      Object[] params = method.createParameters();
      try{
         /// 构造传入参数数组
         int count = method.parameterCount();
         for(int n = 0; n < count; n++){
            /// 获得参数类型
            Class<?> type = method.parameterType(n);
            Object value = null;
            /// 获得参数值
            if(type == ISqlContext.class){
               value = new FSqlContext(_databaseConsole);
            }else if(type == ILogicEvent.class){
               value = event;
            }else if(type.isInterface()){
               value = RAop.find(type);
            }else{
               throw new FFatalError("Build param error. {0}", type);
            }
            params[n] = value;
         }
         /// 根据构造参数调用函数
         method.invoke(instance, params);
         //method.invoke(null, params);
      }catch(Throwable e){
         exception = e;
         _logger.error(this, "invoke", e, "Invoke event error. (event_id={0})", event.id().get());
      }finally{
         /// 释放参数
         Throwable e = RObject.tryRelease(params);
         if(null != e){
            exception = e;
            _logger.error(this, "invoke", e, "Invoke event release error. (event_id={0})", event.id().get());
         }
      }
      /// 停止当前事件
      doEventStop(event, exception);
   }

   /**
    * <T>类型为调用plsql端时的处理</T>
    * <P>根据processInvoke字段的值，调用package包的函数</P>
    * 
    * @param event 事件
    */
   public void invokePackage(ILogicEvent event){
      ISqlConnection cnn = null;
      Throwable exception = null;
      String processInvoke = event.processInvoke();
      try{
         /// 解析处理调用字段，获得包名和方法名
         if(RString.isBlank(processInvoke)){
            throw new FFatalError("No invoke. (event_id={0})", event.id());
         }
         String[] splitResult = RString.splitTwo(processInvoke, '.', true);
         if(null == splitResult){
            throw new FFatalError("Invoke's format is invalid. (event_id={0},invoke={1})", event.id(), processInvoke);
         }
         // 收集数据库链接
         cnn = _databaseConsole.alloc();
         // 获取session信息
         FSqlProcedure session = new FSqlProcedure(PTY_METHOD);
         session.setLogicName(PTY_SESSION_PACKAGE);
         /// 创建所需参数
         session.createParameter(PRM_LOGIC, null, ESqlDataType.String, ESqlDataDirection.InOut);
         cnn.execute(session);
         /// 创建存储过程
         String packageName = splitResult[0];
         String methodName = splitResult[1];
         FSqlProcedure procedure = new FSqlProcedure(methodName);
         procedure.setLogicName(packageName);
         /// 创建所需参数
         procedure.createParameter(PRM_LOGIC, null, ESqlDataType.String, ESqlDataDirection.InOut);
         procedure.createParameter(PARAMS, event.parameters(), ESqlDataType.String, ESqlDataDirection.InOut);
         cnn.execute(procedure);
      }catch(Throwable e){
         exception = e;
         _logger.error(this, "invokePackage", e, "Invoke event error. (event_id={0},packageInvoke={1})", event.id().get(), processInvoke);
      }finally{
         if(null != cnn){
            _databaseConsole.free(cnn);
         }
      }
      /// 停止当前事件
      doEventStop(event, exception);
   }

   /**
    * <T>判断该线程是否处于执行状态</T>
    * <P>设置线程返回值，用来判断是否处于执行状态</P>
    * 
    * @return 是否处于执行状态
    * <OL>
    *    <L value='true'>处于执行状态<L>
    *    <L value='false'>处于非执行状态<L>
    * </OL>
    */
   public boolean isExecute(){
      return _isExecute;
   }

   /**
    * <T>将结果列表打包</T>
    * <P>打包字符串顺序如下：<B/>
    * <OL>
    *    <L value='type_cd'>结果类型</L>
    *    <L value='code'>结果编号</L>
    *    <L value='parameters'>结果参数</L>
    * </OL>
    * </P>
    * 
    * @param event 产生结果的事件
    * @return 结果列表的所有内容的打包字符串
    */
   public String packResult(ILogicEvent event){
      /// 创建外层结构
      FAttributes packs = new FAttributes();
      for(ILogicEventResult item : event.results()){
         /// 将一条事件结果进行打包
         FAttributes pack = new FAttributes();
         pack.set(PTY_TYPE_ID, item.resultType().toString());
         pack.set(PTY_CODE, item.code());
         pack.set(PTY_DESCRIPTION, item.description());
         if(item.hasParameter()){
            pack.set(PTY_PARAMETERS, item.parameters().toString());
         }
         /// 将事件打包字符串放入结果列表中
         packs.set(Integer.toString(packs.count()), pack.pack().toString());
      }
      return packs.pack().toString();
   }

   /**
    * <T>根据处理类型，判断调用相应函数</T>
    * <P>有以下几种类型调用：<B/>
    * <UL>
    *    <L value='Java'>调用invokeJava进行处理</L>
    *    <L value='PlSql'>调用invokePackage进行处理</L>
    *    <L value='Execute'>调用invokeCommand进行处理</L>
    * </UL>
    * </P>
    * 
    * @param event 事件对象
    */
   protected void processEvent(ILogicEvent event){
      /// 判断事件处理类型,根据处理类型调用相应处理
      ELogicEventProcess processType = event.processType();
      switch(processType){
         case Java:
            /// 修改事件执行标志为开始
            doEventStart(event);
            /// 处理JAVA调用
            invokeJava(event);
            break;
         case PlSql:
            /// 修改事件执行标志为开始
            doEventStart(event);
            /// 处理PLSQL调用
            invokePackage(event);
            break;
         case Execute:
            /// 处理COMMAND调用
            invokeCommand(event);
            break;
         default:
            throw new FFatalError("this type is not existed");
      }
   }

   /**
    * <T>将事件对象推入事件容器</T>
    * <P>在推入数据的时候设置成同步，保证在推入数据的同时没有数据丢失</P>
    * 
    * @param event 事件对象
    */
   public void push(ILogicEvent event){
      synchronized(_events){
         _events.push(event);
      }
   }

   /**
    * <T>设置线程执行状态</T>
    * <P>设置当前工作线程的状态为执行中</P>
    */
   public void setExecuteStart(){
      _isExecute = true;
   }
}
