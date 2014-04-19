package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据类型包处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IBatchType
{
   /**
    * <T>备份类型包</T>
    * 
    * @param config xml节点配置
    */
   void backupType(FXmlNode config,
                   FStrings allLog,
                   FStrings failLog);

   /**
    * <T>创建数据类型包操作</T>
    * 
    * @param config xml节点配置
    */
   void createType(FXmlNode config,
                   FStrings allLog,
                   FStrings failLog);

   /**
    * <T>删除数据类型包操作</T>
    * 
    * @param config xml节点配置
    */
   void dropType(FXmlNode config,
                 FStrings allLog,
                 FStrings failLog);

   /**
    * <T>更新数据类型包操作</T>
    * 
    * @param config xml节点配置
    */
   void updateType(FXmlNode config,
                   FStrings allLog,
                   FStrings failLog);
}
