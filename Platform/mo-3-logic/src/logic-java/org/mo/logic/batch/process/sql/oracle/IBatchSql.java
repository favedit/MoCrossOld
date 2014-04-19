package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据类型包处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IBatchSql
{
   /**
    * <T>执行sql文编译无效对象。</T>
    *
    */
   void compileInvalid(FXmlNode config,
                       FStrings allLog,
                       FStrings failLog);

   /**
    * <T>执行sql文</T>
    * 
    * @param config xml节点配置
    * @param synConnection 要同步的数据库连接
    */
   void execute(FXmlNode config,
                FStrings allLog,
                FStrings failLog);

   /**
    * <T>比较同步情况。</T>
    *
    * @param script 脚本
    */
   void executeCompare(FXmlNode config,
                       FStrings allLog,
                       FStrings failLog);

   /**
    * <T>执行sql文</T>
    * 
    * @param config xml节点配置
    * @param synConnection 要同步的数据库连接
    */
   void executeDisplay(FXmlNode config,
                       FStrings allLog,
                       FStrings failLog);

   /**
    * <T>执行一个脚本。</T>
    *
    * @param script 脚本
    */
   void executeScript(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);
}
