package org.mo.logic.batch;

import java.lang.reflect.Method;
import org.mo.com.data.ISqlConnection;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.core.aop.container.FAopComponent;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.logic.batch.common.FBatchCommand;
import org.mo.logic.batch.common.FBatchCommands;
import org.mo.logic.batch.common.FBatchDefine;
import org.mo.logic.batch.common.FBatchDefines;
import org.mo.logic.batch.common.FBatchInclude;
import org.mo.logic.batch.common.FBatchProcess;
import org.mo.logic.batch.common.FBatchProcesses;

public class FBatchConsole
      implements
         IBatchConsole
{
   private final FStrings _configPaths = new FStrings();

   // 构造处理过程列表
   private final FBatchProcesses _processes = new FBatchProcesses(this);

   // 构造执行命令列表
   private final FBatchCommands _commands = new FBatchCommands(this);

   // 要同步的数据库名称
   @SuppressWarnings("unused")
   private String _synDatabaseName = "";

   // 构造批处理变量列表
   private final FBatchDefines _defines = new FBatchDefines(this);

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(FBatchConsole.class);

   private final FXmlNode _config = new FXmlNode("Batch");

   private String _configPath;

   private String _logPath = "D:/euis-batch-log";

   private final FStrings _allLog = new FStrings();

   private final FStrings _failLog = new FStrings();

   // 获得数据库连接控制台
   @ALink
   protected IDatabaseConsole _databaseConsole;

   @Override
   public Object[] createParameters(Method method,
                                    FXmlNode faceNode,
                                    ISqlConnection synConnection,
                                    IAttributes attributes){
      //      Type[] paramType = method.getGenericParameterTypes();
      //      int length = paramType.length;
      Object[] parameters = new Object[3];
      //      int count = 0;
      try{
         //         while(count < length){
         //            //创建FXmlNode类型参数
         //            if(paramType[count] == FXmlNode.class){
         //               parameters[count] = faceNode;
         //            }
         //            //创建ISqlConnection类型参数
         //            if(paramType[count] == ISqlConnection.class){
         //               parameters[count] = synConnection;
         //            }
         //            count++;
         //}
         parameters[0] = faceNode;
         parameters[1] = _allLog;
         parameters[2] = _failLog;
         _logger.debug(this, "createParameters", "The create method's parameters Success ({0}->{1}[parameters count={2}])", faceNode.get("face"), faceNode.get("process"), 3);
      }catch(Exception e){
         throw new FFatalError(e, "Create method's parameters failure. (class={0}, method={1})", faceNode.get("face"), faceNode.get("process"));
      }
      return parameters;
   }

   @Override
   public boolean execute(String faceName,
                          String methodName,
                          FXmlNode config,
                          IAttributes attributes){
      long start = System.currentTimeMillis();
      //检查是接口是否否为空
      if(RString.isEmpty(faceName)){
         _logger.debug(this, "execute", "This interface is empty.[{0}].", faceName);
         return false;
      }
      if(RString.isEmpty(methodName)){
         _logger.debug(this, "execute", "This interface's method is empty.[interface = {0}, method = {1}].", faceName, methodName);
         return false;
      }
      // 根据接口名称查找接口对应的实例
      Object instance = findInstance(faceName);
      if(null == instance){
         _logger.debug(this, "execute", "Can't find interface's instance [interface = {0}].", faceName);
         return false;
      }
      // 根据接口和方法名称查找该接口的方法
      Method method = findMethod(instance, methodName);
      if(null == method){
         _logger.debug(this, "execute", "Can't find interface's method [interface = {0}；method = {1}].", faceName, methodName);
         return false;
      }
      ISqlConnection synConnection = null;
      try{
         // 获得要同步的数据库连接
         Object[] parameters = createParameters(method, config, synConnection, attributes);
         for(Object parameter : parameters){
            if(null == parameter){
               _logger.debug(this, "execute", "Can't execute interface's method [interface = {0}；method = {1}].", faceName, methodName);
               return false;
            }
         }
         method.invoke(instance, parameters);
      }catch(Exception e){
         _logger.debug(this, "execute", "The batch execute fail[Interface={0},Method={1}].", faceName, methodName);
      }finally{
         if(null != synConnection){
            _databaseConsole.free(synConnection);
         }
      }
      _logger.debug(this, "execute", System.currentTimeMillis() - start, "Process command [{0}]->{1} end", faceName, methodName);
      // 创建方法的参数
      return true;
   }

   @Override
   public Object findInstance(String faceName){
      // 创建接口
      FAopComponent cmp = null;
      // 获得接口实例
      Object instance = null;
      // 判断接口是否存在
      if(RClass.exists(faceName)){
         // 查找接口
         cmp = RAop.findComponent(faceName);
         // 查找接口的实例对象
         instance = RAop.tryFind(cmp.face());
         _logger.debug(this, "findFace", "The find instance Success [{0}]->{1}", faceName, "Success");
      }else{
         _logger.debug(this, "findFace", "The find instance is not exist.[interface name = {0}]->{1}", faceName, "Failure");
      }
      return instance;
   }

   @Override
   public Method findMethod(Object instance,
                            String methodName){
      Method method = null;
      try{
         methodName = (methodName != null) ? methodName.toLowerCase() : "construct";
         method = RClass.findMethod(instance, methodName);
         _logger.debug(this, "findMethod", "The find method Success [{0}]->{1}->{2}", instance.getClass().getName(), methodName, "Success");
      }catch(Exception e){
         throw new FFatalError(e, "Find method failure. (class={0}, method={1})", instance.getClass().getName(), methodName);
      }
      return method;
   }

   @Override
   public FBatchCommand getCommand(String name){
      FBatchCommand command = _commands.getCommand(name);
      if(null == command){
         throw new FFatalError("Command define is null. (name={0})", name);
      }
      return command;
   }

   @Override
   public FXmlNode getProcessConfig(String processName){
      //循环已加载的配置文件
      for(FXmlNode config : _config.nodes()){
         if(config.isName(FBatchProcess.NAME) && processName.equals(config.get("name"))){
            return config;
         }
      }
      return null;
   }

   /**
    * <T>获得处理配置文件中的定义和展开xml节点</T>
    * 
    */
   private boolean isExistConfigPath(String configPaths){
      if(_configPaths.contains(configPaths)){
         return true;
      }
      _configPaths.push(configPaths);
      return false;
   }

   public void loadConfig(){
      // 处理文件内所有节点
      for(FXmlNode node : _config.nodes()){
         // 加载处理命令
         if(node.isName(FBatchCommand.NAME)){
            FBatchCommand command = new FBatchCommand(this);
            command.loadConfig(node);
            _commands.push(command);
         }
         // 加载处理流程
         if(node.isName(FBatchProcess.NAME)){
            FBatchProcess process = new FBatchProcess(this);
            process.loadConfig(node);
            _processes.push(process);
         }
      }
   }

   /**
    * <T>获得处理配置文件中的定义和展开xml节点</T>
    * 
    */
   public void loadConfig(String fileName){
      // 加载文件
      String fullName = RFile.makeFilename(_configPath, fileName + ".xml");
      _logger.debug(this, "loadConfig", "Load config file (file={0})", fullName);
      // 判断是否存在该配置文件
      if(!isExistConfigPath(fullName)){
         FXmlDocument xdoc = new FXmlDocument(fullName);
         // 处理文件内所有节点
         for(FXmlNode node : xdoc.root()){
            // 加载包含文件
            if(node.isName("BatchName")){
               IAttributes attributes = node.attributes();
               int count = attributes.count();
               for(int n = 0; n < count; n++){
                  _config.set(attributes.name(n), attributes.value(n));
               }
            }else{
               if(node.isName(FBatchInclude.NAME)){
                  FBatchInclude include = new FBatchInclude(this);
                  include.loadConfig(node);
                  // 判断导入的配置文件是否已经导入过并且不是
                  loadConfig(include.path());
               }else{
                  _defines.replaceAll(node);
                  // 加载处理命令
                  if(node.isName(FBatchDefine.NAME)){
                     FBatchDefine define = new FBatchDefine(this);
                     define.loadConfig(node);
                     _defines.push(define);
                  }else{
                     _config.push(node);
                  }
               }
            }
         }
      }else{
         _logger.debug(this, "loadConfig", "This config file is exist. [config path {0}]", fullName);
      }
   }

   @Override
   public void loadConfig(String filePath,
                          String fileName){
      _configPath = filePath;
      // 展开节点和替换变量
      loadConfig(fileName);
      // 根据配置文件创建处理
      loadConfig();
   }

   @Override
   public void process(String dataset){
      // 获得数据集控制台
      // 批处理处理调用
      _processes.process(dataset);
      // 处理之后操作，主要是打log文件
      String pathHome = _config.get("log_path");
      if(RString.isNotEmpty(pathHome)){
         _logPath = pathHome;
      }
      String logPath = _logPath + "/" + _config.get("name") + "-{YYMMDD-HH24MISS}";
      String subDate = RString.mid(logPath, "{", "}");
      logPath = RString.replace(logPath, "{" + subDate + "}", RDateTime.format(subDate));
      // 保存全部操作日志
      // 保存处理失败日志
      int allCount = _allLog.count();
      if(0 < allCount){
         FString allLogs = new FString();
         allLogs.appendLine("批处理名称：" + _config.get("label"));
         allLogs.appendLine("共处理" + allCount + "条 具体内容如下：");
         for(int n = 0; n < allCount; n++){
            allLogs.appendLine(_allLog.get(n));
            allLogs.appendLine("===================================");
         }
         RFile.saveToFile(logPath + "/allLog.log", allLogs, REncoding.UTF8);
      }
      // 保存处理失败日志
      int failCount = _failLog.count();
      if(0 < failCount){
         FString failLogs = new FString();
         failLogs.appendLine("批处理名称：" + _config.get("label"));
         failLogs.appendLine("处理失败" + failCount + "条 具体内容如下：");
         for(int n = 0; n < failCount; n++){
            failLogs.appendLine(_failLog.get(n));
            failLogs.appendLine("===================================");
         }
         RFile.saveToFile(logPath + "/failLog.log", failLogs, REncoding.UTF8);
      }
   }

   @Override
   public void replace(FXmlNode config){
      _defines.replaceAll(config);
   }

   @Override
   public void setSynDatabaseName(String synDatabaseName){
      _synDatabaseName = synDatabaseName;
   }
}
