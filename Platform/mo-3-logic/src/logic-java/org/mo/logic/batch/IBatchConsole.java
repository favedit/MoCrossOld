package org.mo.logic.batch;

import java.lang.reflect.Method;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.IAttributes;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.common.FBatchCommand;

public interface IBatchConsole
{
   /**
    * <T>根据配置文件创建方法的参数。</T>
    * 
    * @param faceName 接口名称
    * @return 接口组件
    */
   Object[] createParameters(Method method,
                             FXmlNode faceNode,
                             ISqlConnection synConnection,
                             IAttributes attributes);

   /**
    * <T>执行一个处理。</T>
    * 参数：
    * FXmlNode config;
    * FStrings allLogs
    * FStrings fileLogs
    * 
    * @param faceNode 接口名称
    * @return 是否成功
    */
   boolean execute(String faceName,
                   String methodName,
                   FXmlNode config,
                   IAttributes attributes);

   /**
    * <T>根据接口名称，查找接口。</T>
    * 
    * @param faceName 接口名称
    * @return 接口组件
    */
   Object findInstance(String faceName);

   /**
    * <T>根据实例和方法名称，查找该实例的方法。</T>
    * 
    * @param faceName 接口名称
    * @return 接口组件
    */
   Method findMethod(Object instance,
                     String methodName);

   /**
    * <T>根据执行命令名称获得命令</T>
    * 
    */
   FBatchCommand getCommand(String name);

   /**
    * <T>根据流程名称获得流程的xml配置节点</T>
    * 
    */
   FXmlNode getProcessConfig(String processName);

   /**
    * <T>导入批处理配置文件</T>
    * 
    */
   void loadConfig(String filePath,
                   String fileName);

   /**
    * <T>执行批处理</T>
    * 
    */
   void process(String dataset);

   /**
    * <T>替换变量</T>
    * 
    */
   void replace(FXmlNode config);

   /**
    * <T>设置要同步的数据库名称</T>
    * 
    */
   void setSynDatabaseName(String synDatabaseName);
}
