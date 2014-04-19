package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据序列处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IBatchSequence
{
   /**
    * <T>创建数据序列操作</T>
    * 
    * @param config xml节点配置
    */
   void createSequence(FXmlNode config,
                       FStrings allLog,
                       FStrings failLog);

   /**
    * <T>创建数据序列操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void createSequenceDataset(FXmlNode config,
                              FStrings allLog,
                              FStrings failLog);

   /**
    * <T>删除数据序列操作</T>
    * 
    * @param config xml节点配置
    */
   void dropSequence(FXmlNode config,
                     FStrings allLog,
                     FStrings failLog);

   /**
    * <T>删除数据序列操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void dropSequenceDataset(FXmlNode config,
                            FStrings allLog,
                            FStrings failLog);

   /**
    * <T>更新数据序列操作</T>
    * 
    * @param config xml节点配置
    */
   void updateSequence(FXmlNode config,
                       FStrings allLog,
                       FStrings failLog);

   /**
    * <T>更新数据序列操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void updateSequenceDataset(FXmlNode config,
                              FStrings allLog,
                              FStrings failLog);
}
