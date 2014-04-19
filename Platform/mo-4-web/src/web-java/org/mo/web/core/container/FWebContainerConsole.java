package org.mo.web.core.container;

import org.mo.com.console.FConsole;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.global.IGlobalConsole;
import org.mo.web.core.container.common.FWebContainerCollection;
import org.mo.web.core.container.common.FWebContainerItem;
import org.mo.web.core.session.IWebSession;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>数据表单控制台。</T>
// <p>获得和缓冲数据容器</p>
//============================================================
public class FWebContainerConsole
      extends FConsole
      implements
         IWebContainerConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FWebContainerConsole.class);

   // 全局控制台接口
   @ALink
   protected IGlobalConsole _globalConsole;

   // 绑定控制台接口
   @ALink
   protected IBindConsole _bindConsole;

   // 内存超期时间
   @AProperty
   protected long _memoryTimeout;

   // 最大刷新数目
   @AProperty
   protected int _refreshCount;

   // 工作路径
   @AProperty
   protected String _directory;

   @AProperty
   protected String _workFile;

   // 数据容器监视器
   protected FWebContainerMonitor _monitor;

   //============================================================
   // <T>构造数据表单控制台。</T>
   //============================================================
   public FWebContainerConsole(){
   }

   //============================================================
   // <T>查找一个数据容器。</T>
   //
   // @param context 网页环境
   // @param type 表单类型
   // @param clazz 类对象
   // @return 表单
   //============================================================
   @Override
   public FWebContainerItem findContainer(IWebContext context,
                                       AContainer type,
                                       Class<?> clazz){
      // 检查参数
      if(clazz == null){
         throw new NullPointerException("clazz");
      }
      //............................................................
      // 查找表单
      FWebContainerItem form = null;
      String containerName = type.name();
      EContainerScope scopeCd = type.scope();
      if(EContainerScope.Global == scopeCd){
         // 类型为全局对象时
         FWebContainerCollection containers = _globalConsole.find("web.containers", FWebContainerCollection.class);
         form = containers.findContainer(type.name(), clazz);
      }else if(EContainerScope.Session == scopeCd){
         // 类型为线程对象时
         IWebSession session = context.session();
         FWebContainerCollection containers = session.containers();
         form = containers.findContainer(type.name(), clazz);
      }else if(EContainerScope.Page == scopeCd){
         // 类型为页面对象时
         FWebContainerCollection containers = context.containers();
         form = containers.findContainer(containerName, clazz);
      }else{
         // 类型为本地对象时
         form = new FWebContainerItem();
         form.setContainer(RClass.newInstance(clazz));
      }
      //............................................................
      // 检查表单中是否含有数据容器对象
      if(!form.hasContainer()){
         throw new FFatalError("Form container is null. (name={1}, scope={2}, instance={3})", containerName, scopeCd, form);
      }
      // 是否要清除表单中的数据内容
      if(type.clear()){
         form.clear();
      }
      // 填充表单中的数据
      if(type.fill()){
         form.fill(context.parameters());
      }
      //............................................................
      if(_logger.debugAble()){
         _logger.debug(this, "form", "Find form. (name={1}, scope={2}, instance={3}, fill={4})", containerName, scopeCd, form, type.fill());
      }
      return form;
   }

   //   /**
   //    * <p>初始化前操作</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public void initializeConfig(){
   //      _workFile = configNode().nodeText("WorkFile");
   //      _directory = configNode().nodeText("Directory");
   //      _refreshCount = FIntegerUtil.toInteger(configNode().nodeText("RefreshCount"), 0);
   //      _memoryTimeOut = FLongUtil.toLong(configNode().nodeText("MemoryTimeOut"), 30) * 1000;
   //      FFileUtil.makedir(_directory);
   //   }
   //
   //   /**
   //    * <p>初始化后操作</p>
   //    * <p>建立数据容器监视器</p>
   //    * <p>将数据容器监视器配置到监视器控制台中</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public void initializeMonitor(){
   //      // 布置监视器
   //      _monitor = new FFormMonitor(this);
   //      _monitor.setIntervalCount(FIntegerUtil.toInteger(configNode().nodeText("IntervalCount"), 1));
   //      FMonitorManager.console().push(_monitor);
   //   }
   //
   //   /**
   //    * <p>初始化线程操作</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @exception FException 应用程序例外
   //    */
   //   public void initializeResume(){
   //      if(FFileUtil.exists(_workFile)){
   //         _sessionMap = FFileUtil.readObject(_workFile);
   //      }
   //   }
   //
   //   private String makeFormFileName(String sFormName,
   //                                   String sClassName){
   //      if(sFormName == null){
   //         sFormName = "frm";
   //      }else{
   //         sFormName = FStringUtil.replace(sFormName, '.', '_');
   //         sFormName = FStringUtil.replace(sFormName, '|', '.');
   //      }
   //      sClassName = FStringUtil.replace(sClassName, '.', '_');
   //      return FFileUtil.formatFileName(sFormName + "-" + sClassName);
   //   }
   //
   //   private String makeFormPathName(String sSessionId,
   //                                   String sFormFileName){
   //      String sPath = FFileUtil.makeFileName(_directory, sSessionId.toLowerCase());
   //      FFileUtil.makedir(sPath);
   //      String sFileName = FFileUtil.makeFileName(sPath, sFormFileName);
   //      return FFileUtil.formatFileName(sFileName);
   //   }
   //
   //   @SuppressWarnings("unused")
   //   private String makeFormPathName(String sSessionId,
   //                                   String sFormName,
   //                                   String sClassName){
   //      String sPath = FFileUtil.makeFileName(_directory, sSessionId.toLowerCase());
   //      FFileUtil.makedir(sPath);
   //      String sFileName = FFileUtil.makeFileName(sPath, makeFormFileName(sFormName, sClassName));
   //      return FFileUtil.formatFileName(sFileName);
   //   }
   //
   //   /**
   //    * <p>纷发的处理</p>
   //    * <p>create date:2005/11/09</p>
   //    *
   //    * @param sAction 处理命令
   //    * @param oParam 
   //    */
   //   public void process(String sAction,
   //                       Object oParam){
   //      if(sAction.equals(FSessionReleaseEvent.EVENT_ID)){
   //         processSessionRelease((FSessionId) oParam);
   //      }
   //   }
   //
   //   /**
   //    * <p>暂停当前的名称服务，缓冲所有的服务信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    */
   //   public void processSessionRelease(FSessionId oSession){
   //      synchronized(lockObject()){
   //         String sSessionId = oSession.alias();
   //         m_oSessionFormMap.remove(sSessionId);
   //         FFileUtil.rmdirs(FFileUtil.makeDirectoryName(m_sDirectory, sSessionId.toLowerCase()));
   //         if(FLogger.canDeepDebug()){
   //            FString sMsg = new FString();
   //            sMsg.append("Remove session forms [");
   //            sMsg.append(sSessionId);
   //            sMsg.append("]");
   //            FLogger.debug(this, "processSessionRelease", sMsg);
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>从指定的文件中读取已经序列化的数据容器</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sClassName 类名称
   //    * @return 数据容器
   //    * @exception FException 应用程序例外
   //    */
   //   public FForm readForm(String sSessionId,
   //                         String sFormFileName){
   //      String[] arNames = FStringUtil.split(sFormFileName, '-');
   //      @SuppressWarnings("unused")
   //      String sFormName = FStringUtil.replace(arNames[0], '_', '.');
   //      @SuppressWarnings("unused")
   //      String sClassName = FStringUtil.replace(arNames[1], '_', '.');
   //      String sFileName = makeFormPathName(sSessionId, sFormFileName);
   //      FForm oForm = null;
   //      try{
   //         oForm = (FForm) FFileUtil.readObject(sFileName);
   //      }catch(Exception oException){
   //         FFileUtil.delete(sFileName);
   //         FLogger.error(this, "readForm", oException);
   //      }
   //      if(FLogger.canDeepDebug()){
   //         FString sMsg = new FString();
   //         sMsg.append("Read form [");
   //         sMsg.append(oForm);
   //         sMsg.append("] from ");
   //         sMsg.append(sFileName);
   //         FLogger.debug(this, "readForm", sMsg);
   //      }
   //      return oForm;
   //   }
   //
   //   /**
   //    * <p>刷新缓冲中的容器到文件中去</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public void refresh(){
   //      writeAllForm(false);
   //   }
   //
   //   /**
   //    * <p>释放线程操作</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @exception FException 应用程序例外
   //    */
   //   public void releaseInterrupt(){
   //      writeAllForm(true);
   //      FFileUtil.writeObject(_workFile, _sessionMap);
   //   }
   //
   //   protected void writeAllForm(boolean bAll){
   //      synchronized(lockObject()){
   //         int nCount = 0;
   //         FForm oForm = null;
   //         FWebFormMap oFormMap = null;
   //         for(String sSessionId : m_oSessionFormMap.nameArray()){
   //            oFormMap = m_oSessionFormMap.value(sSessionId);
   //            for(String sFormName : oFormMap.nameArray()){
   //               oForm = oFormMap.value(sFormName);
   //               if(oForm != null){
   //                  if(bAll){
   //                     oForm.resetCommitFlag();
   //                     writeForm(sSessionId, sFormName, oForm);
   //                     oFormMap.put(sFormName, null);
   //                  }else{
   //                     if(oForm.testTimeout(m_lMemoryTimeOut)){
   //                        writeForm(sSessionId, sFormName, oForm);
   //                        oFormMap.put(sFormName, null);
   //                        // 每次最大存储个数
   //                        nCount++;
   //                        if((m_nRefreshCount > 0) && (nCount > m_nRefreshCount)){
   //                           return;
   //                        }
   //                     }
   //                  }
   //               }
   //            }
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>序列化指定数据容器到指定的文件中</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oForm 指定数据容器
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public void writeForm(String sSessionId,
   //                         String sFormName,
   //                         FForm oForm){
   //      String sFullFileName = makeFormPathName(sSessionId, sFormName);
   //      if(FLogger.canDeepDebug()){
   //         FString sMsg = new FString();
   //         sMsg.append("Write Form [");
   //         sMsg.append(oForm);
   //         sMsg.append("] to ");
   //         sMsg.append(sFullFileName);
   //         FLogger.debug(this, "writeForm", sMsg);
   //      }
   //      FFileUtil.writeObject(sFullFileName, oForm);
   //   }
}
