package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据包处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IBatchPackage
{
   /**
    * <T>备份定义列表包</T>
    * 
    * @param config xml节点配置
    */
   void backupListPackage(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog);

   /**
    * <T>备份数据包</T>
    * 
    * @param config xml节点配置
    */
   void backupPackage(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);

   /**
    * <T>备份数据包通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void backupPackageDataset(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog);

   /**
    * <T>备份属性视图包通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void backupPropertyPackageDataset(FXmlNode config,
                                     FStrings allLog,
                                     FStrings failLog);

   /**
    * <T>创建数据包操作</T>
    * 
    * @param config xml节点配置
    */
   void createPackage(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);

   /**
    * <T>创建数据包通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void createPackageDataset(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog);

   /**
    * <T>删除数据包操作</T>
    * 
    * @param config xml节点配置
    */
   void dropPackage(FXmlNode config,
                    FStrings allLog,
                    FStrings failLog);

   /**
    * <T>删除数据包操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void dropPackageDataset(FXmlNode config,
                           FStrings allLog,
                           FStrings failLog);

   /**
    * <T>更新数据包操作</T>
    * 
    * @param config xml节点配置
    */
   void updatePackage(FXmlNode config,
                      FStrings allLog,
                      FStrings failLog);

   /**
    * <T>更新数据包操作通过数据集除属性视图</T>
    * 
    * @param config xml节点配置
    */
   void updatePackageDataset(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog);

   /**
   * <T>更新枚举包操作通过定义列表</T>
   * 
   * @param config xml节点配置
   */
   void updatePackageList(FXmlNode config,
                          FStrings allLog,
                          FStrings failLog);

   /**
    * <T>更新属性视图的数据包操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void updatePackagePropertyDataset(FXmlNode config,
                                     FStrings allLog,
                                     FStrings failLog);
}
