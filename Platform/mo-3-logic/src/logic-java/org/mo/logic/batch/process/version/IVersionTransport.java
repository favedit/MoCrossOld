package org.mo.logic.batch.process.version;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据类型包处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IVersionTransport
{
   /**
    * <T>执行命令客户端</T>
    * 
    * @param config xml节点配置
    */
   void commandClient(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);

   /**
    * <T>传输文件客户端</T>
    * 
    * @param config xml节点配置
    */
   void transportClient(FXmlNode config,
                        FStrings allLog,
                        FStrings failLog);

   /**
    * <T>传输客户端</T>
    * 
    * @param config xml节点配置
    */
   void versionServer(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);
}
