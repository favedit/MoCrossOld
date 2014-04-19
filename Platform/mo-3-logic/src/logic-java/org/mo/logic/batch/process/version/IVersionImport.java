package org.mo.logic.batch.process.version;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据类型包处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IVersionImport
{
   /**
    * <T>备份类型包</T>
    * 
    * @param config xml节点配置
    */
   void importVersion(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);
}
