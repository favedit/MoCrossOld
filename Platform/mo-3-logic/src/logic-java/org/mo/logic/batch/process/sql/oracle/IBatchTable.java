package org.mo.logic.batch.process.sql.oracle;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.xml.FXmlNode;

/**
 * <T>数据表处理控制台。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public interface IBatchTable
{
   /**
    * <T>数据表增加字段操作</T>
    * 
    * @param config xml节点配置
    */
   void addField(FXmlNode config,
                 ISqlConnection synConnection);

   /**
    * <T>数据表增加字段操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void addFieldDataset(FXmlNode config,
                        ISqlConnection synConnection);

   /**
    * <T>创建数据表操作</T>
    * 
    * @param config xml节点配置
    */
   void createTable(FXmlNode config,
                    ISqlConnection synConnection);

   /**
    * <T>创建数据表操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void createTableDataset(FXmlNode config,
                           ISqlConnection synConnection);

   /**
    * <T>恢复数据。</T>
    * 
    * @history 091223 YJHUA 创建
    */
   void dataRestore(FXmlNode config,
                    FStrings allLogs,
                    FStrings failLogs);

   /**
    * <T>存储数据</T>
    * 
    * @param config xml节点配置
    */
   void dataStore(FXmlNode config,
                  FStrings allLogs,
                  FStrings failLogs);

   /**
    * <T>数据表删除字段操作</T>
    * 
    * @param config xml节点配置
    */
   FString dropField(String tableName,
                     String fieldName);

   /**
    * <T>数据表删除字段操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void dropFieldDataset(FXmlNode config,
                         ISqlConnection synConnection);

   /**
    * <T>删除数据表操作</T>
    * 
    * @param config xml节点配置
    */
   void dropTable(FXmlNode config,
                  ISqlConnection synConnection);

   /**
    * <T>删除数据表操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void dropTableDataset(FXmlNode config,
                         ISqlConnection synConnection);

   /**
    * <T>数据导出操作</T>
    * 
    * @param config xml节点配置
    */
   void exportData(FXmlNode config,
                   ISqlConnection synConnection);

   /**
    * <T>数据导出操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void exportDataDataset(FXmlNode config,
                          ISqlConnection synConnection);

   /**
    * <T>数据导入操作</T>
    * 
    * @param config xml节点配置
    */
   void importData(FXmlNode config,
                   ISqlConnection synConnection);

   /**
    * <T>数据导入操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void importDataDataset(FXmlNode config,
                          ISqlConnection synConnection);

   /**
    * <T>数据表更新操作</T>
    * 
    * @param config xml节点配置
    */
   void updateField(FXmlNode config,
                    ISqlConnection synConnection);

   /**
    * <T>数据表更新操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void updateFieldDataset(FXmlNode config,
                           ISqlConnection synConnection);

   /**
    * <T>更新数据表操作</T>
    * 
    * @param config xml节点配置
    */
   void updateTable(FXmlNode config,
                    FStrings allLogs,
                    FStrings failLogs);

   /**
    * <T>更新数据表操作通过数据集</T>
    * 
    * @param config xml节点配置
    */
   void updateTableDataset(FXmlNode config,
                           ISqlConnection synConnection);
}
