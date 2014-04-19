package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据视图处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IBatchView
{
   /**
    * <T>创建数据视图操作</T>
    * 
    * @param config xml节点配置
    */
   void createView(FXmlNode config,
                   FStrings allLog,
                   FStrings failLog);

   /**
    * <T>创建数据视图操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void createViewDataset(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog);

   /**
    * <T>删除数据视图操作</T>
    * 
    * @param config xml节点配置
    */
   void dropView(FXmlNode config,
                 FStrings allLog,
                 FStrings failLog);

   /**
    * <T>删除数据视图操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void dropViewDataset(FXmlNode config,
                        FStrings allLog,
                        FStrings failLog);

   /**
    * <T>更新数据视图操作</T>
    * 
    * @param config xml节点配置
    */
   void updateView(FXmlNode config,
                   FStrings allLog,
                   FStrings failLog);

   /**
    * <T>更新普通数据视图操作通过数据集除属性视图外的视图</T>
    * 
    * @param config xml节点配置
    */
   void updateViewDataset(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog);

   /**
    * <T>更新属性视图操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void updateViewPropertyDataset(FXmlNode config,
                                  FStrings allLog,
                                  FStrings failLog);
}
