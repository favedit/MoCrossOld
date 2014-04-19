package org.mo.logic.batch.process.sql.oracle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnection;
import org.mo.com.data.ISqlDatasetReader;
import org.mo.com.data.RSql;
import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.system.ESystemMode;
import org.mo.com.system.RSystem;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.data.datainfo.common.XField;
import org.mo.data.dataset.FDatasetConsole;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.data.dataset.common.RDatasetSqlBuilder;
import org.mo.data.dataset.common.XDataWork;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mo.logic.batch.process.FBatchSqlCommand;
import org.mo.logic.event.FLogicEventRunner;
import org.mo.mime.csv.FCsvColumn;
import org.mo.mime.csv.FCsvColumns;
import org.mo.mime.csv.FCsvCommandProperty;
import org.mo.mime.csv.FCsvLine;
import org.mo.mime.csv.FCsvReader;
import org.mo.mime.csv.FCsvWriter;
import org.mo.mime.csv.RCsvCommand;

public class FBatchTable
      extends FBatchSqlCommand
      implements
         IBatchTable
{
   public static void readZipFile(File file){
      try{
         ZipFile zipfile = new ZipFile(file);
         //创建ZipFile对象        
         System.out.println(zipfile.getName());
         //打印Zip文件路径        
         System.out.println("ZIP条目数：" + zipfile.size());
         //打印Zip文件条目数                
         Enumeration<? extends ZipEntry> en = zipfile.entries();
         ZipEntry entry;
         while(en.hasMoreElements()){
            entry = en.nextElement();
            System.out.println(entry.getName());
         }
         zipfile.close();
      }catch(ZipException e){
         System.out.println(file.getName() + "压缩文件错误");
      }catch(IOException e){
         System.out.println(file.getName() + "读取文件时出错");
      }
   }

   // 系统模式
   private String _sysMode = "";

   // 数据备份模式
   private String _dateStoreMode = "";

   // 数据恢复模式
   private String _dateRestoreMode = "";

   // 同步失败log
   FStrings _failLogs = new FStrings();

   // 获得数据集控制台
   @ALink
   protected IDatasetConsole _datasetConsole;

   @ALink
   private ITemplateConsole _templateConsole;

   // 全局日志对象
   private final static ILogger _logger = RLogger.find(FLogicEventRunner.class);

   private static IResource _resource = RResource.find(FDatasetConsole.class);

   protected final String TYPE_ORACLE = "database/logicunit/sqlTable";

   protected final String TYPE_ORACLE_HISTORY = "database/logicunit/history/sqlTable";

   public final static String TYPE_ORACLE_CACHE = "database/logicunit/cache/sqlTable";

   public final static String TYPE_ORACLE_WORK = "database/logicunit/work/sqlTable";

   public final static String TYPE_ORACLE_MEMORY = "database/logicunit/memory/sqlTable";

   protected String _installDataTable = "d:/data-store/data-{YYMMDD-HH24MISS}";

   private final String _csv = ".csv";

   private final String _encode = "GB18030";

   @SuppressWarnings("unused")
   private boolean _addedField = false;

   /**
    * <T>制作表</T>
    */
   public FString addDataStore(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      parser.define("indexs", dataset.node("indexs").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   /**
    * <T>制作表</T>
    */
   public FString addDataTable(FXmlNode dataset,
                               String talbeType){
      // 导入模板
      ITemplateParser parser = null;
      if("dataStore".equals(talbeType)){
         parser = _templateConsole.findParser(TYPE_ORACLE);
      }else if("historyStore".equals(talbeType)){
         parser = _templateConsole.findParser(TYPE_ORACLE_HISTORY);
      }
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      parser.define("indexs", dataset.node("indexs").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   @Override
   public void addField(FXmlNode config,
                        ISqlConnection synConnection){
      /* /// 增加字段处理
       /// 得到表名
       String tableName = config.get("data_name")
       FSql sql = new FSql();
       String dataName = config.get("data_name").toUpperCase();
       sql.append("ALTER TABLE " + tableName + " ADD(" + dataName + " " + config.get("data_type"));
       String dataDefault = config.get("data_default");
       if(dataDefault != null){
          sql.append(" default " + dataDefault);
       }
       sql.append(");");
       return sql;*/
   }

   public FString addField(String tableName,
                           FXmlNode node){
      /// 增加字段处理
      /// alter table 表名 add(字段名称 type（size） default 默认值)
      FSql sql = new FSql();
      String fieldName = node.get("data_name").toUpperCase();
      sql.append("ALTER TABLE " + tableName + " ADD(" + fieldName + " " + node.get("data_type"));
      String dataDefault = node.get("data_default");
      if(dataDefault != null){
         sql.append(" DEFAULT " + dataDefault);
      }
      String isNull = node.get(XField.PTY_IS_NULL);
      if(!RBoolean.parse(isNull)){
         sql.append(" NOT NULL ");
      }
      sql.append(");");
      return sql;
   }

   @Override
   public void addFieldDataset(FXmlNode config,
                               ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   /**
    * <T>制作表</T>
    */
   public FString addHistoryStore(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_HISTORY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      parser.define("indexs", dataset.node("indexs").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   /**
    * <T>检查索引</T>
    */
   public String checkAlertIndex(String indexName,
                                 FDataset indexs,
                                 FXmlNode indexNode){
      if(null != indexNode && indexNode.hasNode() && RString.isNotEmpty(indexName)){
         FStrings params = getIndexParams(indexName, indexs);
         FXmlNodes paramNodes = indexNode.nodes("index");
         if(paramNodes.count() != params.count()){
            return "c";
         }else{
            for(FXmlNode paramNode : paramNodes){
               String paramsName = paramNode.get("index_params");
               boolean isSame = false;
               for(String param : params){
                  if(paramsName.equalsIgnoreCase(param)){
                     isSame = true;
                     break;
                  }
               }
               if(!isSame){
                  return "dc";
               }
            }
         }
      }
      return null;
   }

   /**
    * <T>检查表里是否有数据</T>
    * 
    */
   public boolean checkDataExist(String tableName,
                                 ISqlConnection sqlCnn){
      // 执行sql，检索出type进行比较
      FSql sql = new FSql();
      sql.append("SELECT 1 FROM " + tableName);
      // 执行sql
      FSqlQuery sqlSource = new FSqlQuery(sqlCnn, sql);
      FDataset dataset = sqlSource.fetchDataset();
      if(dataset.count() > 0){
         return true;
      }
      return false;
   }

   /**
    * <T>检查表字段</T>
    * <P>需要变更返回true，否则返回false</P>
    */
   public boolean checkField(String tableName,
                             FXmlNode node,
                             FDataset dataset){
      // 执行sql，检索出type进行比较
      String fieldName = node.get("data_name");
      if(fieldName != null){
         // 如果返回结果为空，表示该字段在数据表不存在，该字段需要添加
         if(dataset.find("column_name", fieldName) == null){
            return true;
         }
      }
      return false;
   }

   /**
    * <T>检查字段类型</T>
    */
   public FString checkFieldDataType(String tableName,
                                     FXmlNode fieldNode,
                                     FDataset dataset){
      String fieldName = fieldNode.get("data_name");
      String initialType = fieldNode.get("data_type").trim();
      String dataTypeInXml = initialType == null ? null : initialType;
      if(dataTypeInXml != null){
         if(dataTypeInXml.contains("(")){
            dataTypeInXml = dataTypeInXml.substring(0, dataTypeInXml.indexOf("("));
         }
         dataTypeInXml = typeCasting(dataTypeInXml.toUpperCase());
         dataTypeInXml = dataTypeInXml.toUpperCase();
      }
      FRow row = dataset.find("column_name", fieldName);
      if(row != null){
         String dataType = row.get("data_type").trim().toUpperCase();
         if(!dataTypeInXml.equalsIgnoreCase(dataType)){
            // 如果不同，则把该字段进行更新操作
            FSql process_sql = new FSql();
            // 2010-03-24修改
            /*修改成
             * 1、如果原先的是Char类型，修改成Varchar，则只修改属性；
             * 2、如果原先的是Integer类型，修改成Float，则只修改属性；
             * 3、其余的则新建字段，修改原先的字段名称fieldName_n，然后添加新字段
             */
            // 如果旧类型时Integer
            if("Number".equalsIgnoreCase(dataType)){
               // 如果修改成Float类型则只修改属性
               if("Float".equalsIgnoreCase(dataTypeInXml)){
                  process_sql.append("alter table " + tableName + " modify " + fieldName + " " + initialType + ";");
                  return process_sql;
               }
            }
            if("Char".equalsIgnoreCase(dataType)){
               // 如果修改成Float类型则只修改属性
               if("Varchar2".equalsIgnoreCase(dataTypeInXml)){
                  process_sql.append("alter table " + tableName + " modify " + fieldName + " " + initialType + ";");
                  return process_sql;
               }
            }
            String faillog = "Table(" + tableName + "):字段[" + fieldName + "]由" + dataType + "类型修改成" + dataTypeInXml + "类型，系统更新失败！";
            System.out.println("=======================================");
            System.out.println(faillog);
            System.out.println("=======================================");
            _failLogs.push(faillog);
            //            if(!_addedField){
            //               //process_sql.append("alter table " + tableName + " modify " + fieldName + " " + initialType + ";");
            //               // 修改字段名称
            //               FString addFieldSql = addField(tableName, fieldNode);
            //               process_sql.append(addFieldSql.toString());
            //               _addedField = true;
            //               return process_sql;
            //            }
         }
      }
      return null;
   }

   /**
    * <T>检查字段缺省值</T>
    */
   public FString checkFieldDefault(String tableName,
                                    FXmlNode node,
                                    FDataset dataset){
      String dataName = node.get("data_name").toUpperCase();
      String dataDefault = node.get("data_default");
      if(RString.isNotEmpty(dataDefault)){
         dataDefault = dataDefault.toUpperCase();
         if(dataDefault.contains("CP_SESSION")){
            return null;
         }
         if(dataDefault.contains("EI")){
            return null;
         }
         // 取得当前记录，有且仅有一行
         FRow row = dataset.find("column_name", dataName);
         if(row != null){
            String defaultValue = row.get("data_default");
            if(RString.isNotEmpty(dataDefault)){
               if("CP_BOOLEAN_EI.ETRUE".equals(dataDefault)){
                  dataDefault = "'Y'";
               }
               if("CP_BOOLEAN_EI.EFALSE".equals(dataDefault)){
                  dataDefault = "'N'";
               }
               /*if(0 == dataDefault.compareToIgnoreCase(defaultValue)){
                  return modifyFieldDefault(tableName, dataName, dataDefault);
               }*/
            }/*else if(RString.isNotEmpty(defaultValue)){CP_SESSION.Get_User_Id()
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        return modifyFieldDefault(tableName, dataName, dataDefault);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     }*/
            if((RString.isEmpty(dataDefault) && RString.isNotEmpty(defaultValue)) || (RString.isNotEmpty(dataDefault) && RString.isEmpty(defaultValue))){
               return modifyFieldDefault(tableName, dataName, dataDefault);
            }else if(RString.isNotEmpty(dataDefault) && RString.isNotEmpty(defaultValue)){
               if(0 != dataDefault.compareToIgnoreCase(defaultValue)){
                  return modifyFieldDefault(tableName, dataName, dataDefault);
               }
            }
         }
      }
      return null;
   }

   /**
    * <T>检查字段可为空</T>
    */
   public FString checkFieldEmpty(String tableName,
                                  FXmlNode fieldNode,
                                  FDataset dataset){
      String fieldName = fieldNode.get("data_name").toUpperCase();
      if(!fieldName.equals("OUID")){
         String newIsEmpty = fieldNode.get(XField.PTY_IS_NULL);
         // 取得当前记录，有且仅有一行
         FRow row = dataset.find("column_name", fieldName);
         if(row != null){
            String isEmpty = row.get("nullable");
            if(RString.isEmpty(newIsEmpty)){
               newIsEmpty = "N";
            }
            //            if((RString.isEmpty(dataEmpty) && RString.isNotEmpty(emptyValue)) || (RString.isNotEmpty(dataEmpty) && RString.isEmpty(emptyValue))){
            //               return modifyFieldEmpty(tableName, dataName, dataEmpty);
            //            }else if(RString.isNotEmpty(dataEmpty) && RString.isNotEmpty(emptyValue)){
            //               if(0 != dataEmpty.compareToIgnoreCase(emptyValue)){
            //                  return modifyFieldEmpty(tableName, dataName, dataEmpty);
            //               }
            //            }
            /*处理过程
             * 1、从可为空改为不空，则修改旧字段名称为fieldName_n，然后新建字段
             * 2、从不为空改为可为空，则直接修改属性
             */
            if(!RBoolean.parse(isEmpty)){
               if(RBoolean.parse(newIsEmpty)){
                  return modifyFieldEmpty(tableName, fieldName, newIsEmpty);
               }
            }else if(RBoolean.parse(isEmpty)){
               /*2010-03-29注掉
                * 
                */
               //               if(!RBoolean.parse(newIsEmpty)){
               //                  if(!_addedField){
               //                     // 查找最新的历史名词
               //                     String hisFieldName = getHisFieldName(fieldName, dataset);
               //                     FSql sql = new FSql();
               //                     // 修改旧字段名称为fieldName_n
               //                     sql.append("ALTER TABLE " + tableName + " RENAME COLUMN " + fieldName + " TO " + hisFieldName + ";");
               //                     FString addFieldSql = addField(tableName, fieldNode);
               //                     sql.append(addFieldSql.toString());
               //                     _addedField = true;
               //                     return sql;
               //                  }
               //               }
               if(!RBoolean.parse(newIsEmpty)){
                  String faillog = "Table(" + tableName + "):字段[" + fieldName + "]原为可为空修改成不可为空操作，系统更新失败！";
                  System.out.println("=======================================");
                  System.out.println(faillog);
                  System.out.println("=======================================");
                  _failLogs.push(faillog);
               }
            }
         }
      }
      return null;
   }

   /**
    * <T>检查字段大小</T>
    */
   public FString checkFieldSize(String tableName,
                                 FXmlNode fieldNode,
                                 FDataset dataset){
      /// select data_length from (select * from user_tab_columns where table_name = Upper('表名')) where column_name = Upper('字段名称')
      String fieldName = fieldNode.get("data_name").toUpperCase();
      String dataSize = getDataSize(fieldNode);
      if(dataSize != null){
         // 取得当前记录，有且仅有一行
         FRow row = dataset.find("column_name", fieldName);
         if(row != null){
            // 获得数据库上该字段的大小
            String dataLength = row.getInteger("data_length").toString();
            // 减少字段大小
            int idataLength = Integer.valueOf(dataLength);
            int idataSize = Integer.valueOf(dataSize);
            /*处理过程
             * 1、字段大小减少，则报错
             * 2、字段大小增加，则直接修改属性
             */
            if(idataLength > idataSize){
               // 查找最新的历史名词
               //               if(!_addedField){
               //                  String hisFieldName = getHisFieldName(fieldName, dataset);
               //                  FSql sql = new FSql();
               //                  // 修改旧字段名称为fieldName_n
               //                  sql.append("ALTER TABLE " + tableName + " RENAME COLUMN " + fieldName + " TO " + hisFieldName + ";");
               //                  FString addFieldSql = addField(tableName, fieldNode);
               //                  sql.append(addFieldSql.toString());
               //                  _addedField = true;
               //                  return sql;
               //               }
               String faillog = "Table(" + tableName + "):字段[" + fieldName + "]大小由" + dataLength + "修改为" + dataSize + "操作，系统更新失败！";
               System.out.println("=======================================");
               System.out.println(faillog);
               System.out.println("=======================================");
               _failLogs.push(faillog);
            }else if(idataLength < idataSize){
               return modifyFieldSize(tableName, fieldName, fieldNode.get("data_type"));
            }
         }
      }
      return null;
   }

   /**
    * <T>检查唯一键</T>
    */
   public FString checkFieldUnique(String tableName,
                                   String tableAlias,
                                   FXmlNode node,
                                   FDataset allUnique){
      String uniquename = node.get("data_name").toUpperCase();
      String isUnique = node.get("is_unique");
      FRow row = allUnique.find("column_name", uniquename);
      /// 唯一名称集
      String dataUniques = node.get("data_uniques");
      /// 是否为空
      String nullAble = node.get("IS_NULL");
      if(RString.isNotEmpty(nullAble)){
         return null;
      }
      if(RString.isEmpty(dataUniques)){
         if(row != null){
            /// 删除原来唯一值
            if(isUnique == null){
               return dropUnique(tableName.toUpperCase(), tableAlias, uniquename);
            }
         }else if("Y".equals(isUnique)){
            /// 字段别称
            String fieldAlias = node.get("data_alias");
            /// 增加唯一值
            return modifyUniqueKey(tableName.toUpperCase(), tableAlias, uniquename, fieldAlias);
         }
      }
      return null;
   }

   /**
    * <T>检查索引</T>
    */
   public FString checkIndex(String tableName,
                             FXmlNode indexNodes,
                             ISqlConnection sqlCnn){
      // 获得所有表的索引
      FDataset indexs = getTableIndexs(tableName, sqlCnn);
      if(null != indexNodes && indexNodes.hasNode()){
         boolean isExist = false;
         if(null != indexs && !(indexs.isEmpty())){
            isExist = true;
         }
         FString indexSql = new FString();
         for(FXmlNode indexNode : indexNodes.nodes("index_name")){
            String indexName = indexNode.get("index_name");
            indexName = getAllIndexName(tableName, indexName);
            if(isExist){
               String alertMark = checkAlertIndex(indexName, indexs, indexNode);
               if("dc".equalsIgnoreCase(alertMark)){
                  // 删除索引
                  indexSql.append(dropIndex(indexName));
               }
               if("dc".equalsIgnoreCase(alertMark) || "c".equalsIgnoreCase(alertMark)){
                  // 创建索引
                  indexSql.append(createIndex(tableName, indexName, indexNode));
               }
            }else{
               FString addIndexSql = createIndex(tableName, indexName, indexNode);
               if(null != addIndexSql && !(addIndexSql.isEmpty())){
                  indexSql.append(addIndexSql);
               }
            }
         }
         return indexSql;
      }
      return null;
   }

   /**
    * <T>检查主键</T>
    */
   public FString checkPrimaryKey(String tableName,
                                  FXmlNode node,
                                  ISqlConnection sqlCnn){
      // 检查类型是否是主键
      if("Primary".equals(node.get("key_type"))){
         String keyNameXml = node.get("key");
         if(RString.isNotEmpty(keyNameXml)){
            FSql sql = new FSql();
            sql.append("SELECT 1 FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME AND AU.CONSTRAINT_TYPE = 'P' AND AU.TABLE_NAME = Upper('" + tableName + "') AND CU.COLUMN_NAME = Upper('" + keyNameXml + "')");
            // 执行sql
            FSqlQuery sqlSource = new FSqlQuery(sqlCnn, sql);
            FDataset dataset = sqlSource.fetchDataset();
            // 取得当前记录，有且仅有一行
            FRow row = dataset.get(0);
            if(row == null){
               // 新增主键
               FSql processSql = new FSql();
               processSql.append(createPrimaryKey(tableName, node));
               /*
                           String keyName = row.get("COLUMN_NAME").trim().toUpperCase();
                           if(RString.isNotEmpty(keyName)){
                              if(!keyNameXml.equals(keyName)){
                                 FSql processSql = new FSql();
                                 // 删除原有的主键
                                 processSql.append(dropPrimaryKey(tableName, keyName));
                                 // 新增主键
                                 processSql.append(createPrimaryKey(tableName, node));
                                 return processSql;
                              }
                           }
                        }
                     }*/
            }
         }
      }
      return null;
   }

   /**
    * <T>检查数据表</T>
    */
   public FDataset checkTable(String tableName,
                              ISqlConnection sqlCnn){
      // 执行sql，检索出type进行比较
      FSql sql = new FSql();
      sql.append("SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = Upper('" + tableName + "')");
      // 执行sql
      FSqlQuery sqlSource = new FSqlQuery(sqlCnn, sql);
      FDataset dataset = sqlSource.fetchDataset();
      return dataset;
      /*
      // 新建数据表
      if(dataset.isEmpty()){
         // 生成新表
         return true;
      }
      return false;*/
   }

   /**
    * <T>创建索引</T>
    */
   public FString createIndex(String tableName,
                              String indexName,
                              FXmlNode indexNode){
      if(RString.isNotEmpty(indexName)){
         String indexParams = "";
         for(FXmlNode indexParamNodes : indexNode.nodes("index")){
            String params = indexParamNodes.get("index_params");
            if(RString.isNotEmpty(params)){
               if(RString.isNotEmpty(indexParams)){
                  indexParams = indexParams + "," + params;
               }else{
                  indexParams = params;
               }
            }
         }
         // 创建创建索引的sql文
         if(RString.isNotEmpty(indexParams)){
            FSql sql = new FSql();
            sql.append("CREATE INDEX " + indexName + " ON " + tableName + "(" + indexParams + ") TABLESPACE EUISDX STORAGE(INITIAL 64k MINEXTENTS 1 MAXEXTENTS UNLIMITED);");
            return sql;
         }
      }
      return null;
   }

   /**
    * <T>检查唯一键</T>
    */
   /*
   public FString checkUniqueKey(String tableName,
                                 FXmlNode node,
                                 ISqlConnection sqlCnn){
      // 执行sql，检索出type进行比较
      FSql sql = new FSql();
      String isUnique = node.get("is_unique");
      String dataName = node.get("data_name");
      String uniqueName = (tableName + "_" + dataName).toUpperCase();
      sql.append("select * from user_constraints where table_name = Upper('" + tableName
            + "') and constraint_type = 'U' and constraint_name=" + uniqueName);
      // 执行sql
      FSqlQuery sqlSource = new FSqlQuery(sqlCnn, sql);
      FDataset dataset = sqlSource.fetchDataset();
      // 取得当前记录，有且仅有一行
      if(dataset.isEmpty()){
         // 修改唯一键
         modifyUniqueKey(tableName, uniqueName);
      }
      return null;
   }
   */
   /**
    * <T>创建主键</T>
    */
   public FString createPrimaryKey(String tableName,
                                   FXmlNode node){
      // 字段名称
      String fieldName = node.get("data_name").trim();
      // 执行sql，检索出type进行比较
      FSql sql = new FSql();
      sql.append("ALTER TABLE " + tableName + " ADD (CONSTRAINT 主键名称 PRIMARY KEY(" + fieldName + ")");
      return sql;
   }

   @Override
   public void createTable(FXmlNode config,
                           ISqlConnection synConnection){
      // 解析xml节点获得处理记录
      //FStrings items = fetchRangeItem(config);
   }

   @Override
   public void createTableDataset(FXmlNode config,
                                  ISqlConnection synConnection){
      System.out.println("tableCreateDataset");
   }

   public void dataDelete(IXmlObject xdataset,
                          boolean disableKey){
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         String dataName = xdataset.innerGet("data_name");
         if(disableKey){
            tableForeignKeyDisable(sqlCnn, dataName);
         }
         // 执行删除表中所有记录的SQL语句
         FSql sql = new FSql("DELETE FROM " + dataName + "_DS");
         sqlCnn.executeSql(sql.toString());
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   @Override
   public void dataRestore(FXmlNode config,
                           FStrings allLogs,
                           FStrings failLogs){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      /// 得到数据库连接
      String dataName = config.get("syn_name");
      ISqlConnection synConnection = _databaseConsole.alloc(dataName).activeConnection();
      /// 得到zip包路径名称
      String zipPath = config.get("zip_path");
      /// 得到zip名称
      String zipName = config.get("zip_name");
      /// 如果为空，取得当前最新的zip包名称
      /*if (RString.isEmpty(zipName)) {
         zipName = 
      }*/
      /// 处理需要读取的文件
      String restoreFile = config.get("restore_file");
      /// 如果为空，取得当前最新的数据名称
      /*if (RString.isEmpty(restoreFile)) {
         restoreFile = 
      }*/
      /// 处理后路径
      String dataStorePath = null;
      if(RString.isNotEmpty(zipPath)){
         dataStorePath = zipPath + "/" + zipName;
      }else{
         dataStorePath = zipPath + "/" + zipName;
      }
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xodataset : _datasetConsole.list()){
                  /// 恢复数据
                  dataRestore(xodataset, synConnection, dataStorePath, restoreFile, true);
               }
            }else{
               IXmlObject xodataset = _datasetConsole.find(dataset);
               /// 恢复数据
               dataRestore(xodataset, synConnection, dataStorePath, restoreFile, true);
            }
         }
      }
   }

   @SuppressWarnings("unused")
   protected void dataRestore(IXmlObject xdataset,
                              ISqlConnection synConnection,
                              FCsvReader reader,
                              boolean disableKey){
      FXmlNode config = _datasetConsole.buildConfig(xdataset);
      String data_name = config.get("data_name");
      try{
         synConnection = _databaseConsole.alloc();
         if(disableKey){
            tableForeignKeyDisable(synConnection, data_name);
            dataDelete(xdataset, true);
         }
         String datasetName = xdataset.innerGet("name");
         FXmlNode node = config.findNode("Fields");
         FCsvColumns columns = reader.columns();
         FString fieldsStr = new FString();
         FXmlNodes fields = new FXmlNodes();
         for(FXmlNode sub : node){
            if(columns.contains(sub.get("name"))){
               fields.push(sub);
               if(!fieldsStr.isEmpty()){
                  fieldsStr.append(",");
               }
               fieldsStr.append(sub.get("data_name"));
            }
         }
         //
         FString sql = new FString();
         while(reader.hasNextData()){
            FCsvLine csvline = reader.readLine();
            sql.clear();
            sql.append("INSERT INTO ", data_name, "_DS", "(", fieldsStr, ") VALUES(");
            boolean isFirst = true;
            for(FXmlNode field : fields){
               String dataType = field.get("type");
               String value = csvline.get(field.get("name"));
               value = RString.replace(value, "\\n", "\n");
               if(value.startsWith(":")){
                  value = value.substring(1, value.length());
               }
               value = RSql.formatValue(value);
               String dataName = field.get("data_name");
               if(isFirst){
                  isFirst = false;
               }else{
                  sql.append(",");
               }
               if("OGID".equals(dataName)){
                  if(RString.isEmpty(value)){
                     sql.append("SYS_GUID()");
                  }else{
                     sql.append('\'', value, '\'');
                  }
                  continue;
               }
               if(RString.isEmpty(value)){
                  sql.append(RSql.NULL);
               }else if(dataType.equals("Date")){
                  sql.append("TO_DATE(", '\'', value, '\'', ",'YYYYMMDDHH24MISS')");
               }else if(dataType.equals("Integer")){
                  sql.append(value);
               }else{
                  sql.append('\'', value, '\'');
               }
            }
            sql.append(")");
            synConnection.executeSql(sql.toString());
         }
         maxSequence(synConnection, data_name);
         reader.close();
      }catch(Exception e){
         _logger.error(this, "dataRestore", e, "Execute failure. (dataset={0})", data_name);
      }finally{
         if(disableKey){
            try{
               tableForeignKeyEnable(synConnection, data_name);
            }catch(Exception e){
               _logger.error(this, "dataRestore", e, "Enable forkey failure.");
            }
         }
         if(null != synConnection){
            _databaseConsole.free(synConnection);
         }
      }
   }

   public void dataRestore(IXmlObject xdataset,
                           ISqlConnection synConnection,
                           String zipPath,
                           String restoreFile,
                           boolean disableKey){
      // Execute
      String dataName = "";
      FCsvReader reader = null;
      try{
         String datasetName = xdataset.innerGet("name");
         dataName = xdataset.innerGet("data_name");
         ZipFile zipFile = getZipObject(zipPath);
         String fileName = restoreFile + "/" + datasetName + _csv;
         /// String fileName = "data-100304-161638" + "/" + "logic.event" + ".csv";
         /// FLinesFile file = new FLinesFile(fileName, _encode);
         /*reader = new FCsvReader();
         reader.openFile(file.toString(), _encode);
         reader.openSegment();*/
         reader = readFile(zipFile, fileName);
         // 恢复数据
         dataRestore(xdataset, synConnection, reader, disableKey);
      }catch(Exception e){
         _logger.error(this, "dataRestore", e, "Execute failure. (dataset={0})", dataName);
      }finally{
      }
   }

   @Override
   public void dataStore(FXmlNode config,
                         FStrings allLogs,
                         FStrings failLogs){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      /// 得到数据库连接
      String dataName = config.get("data_name");
      ISqlConnection synConnection = _databaseConsole.alloc(dataName).activeConnection();
      /// 处理前路劲
      String storePath = config.get("data_path");
      setStorePath(storePath);
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xodataset : _datasetConsole.list()){
                  /// 存储数据
                  dataStore(xodataset, synConnection);
               }
            }else{
               IXmlObject xodataset = _datasetConsole.find(dataset);
               /// 存储数据
               dataStore(xodataset, synConnection);
            }
         }
      }
   }

   @SuppressWarnings("unused")
   public void dataStore(IXmlObject xdataset,
                         ISqlConnection sqlCnn){
      String dataStorePath = _installDataTable;
      if("DataStore".equalsIgnoreCase(xdataset.name())){
         FXmlNode config = _datasetConsole.buildConfig(xdataset);
         ISqlDatasetReader reader = null;
         FCsvWriter csvWriter = new FCsvWriter();
         String datasetName = xdataset.innerGet("name").toLowerCase();
         String data_name = xdataset.innerGet("data_name");
         try{
            if(!checkTable(data_name, sqlCnn).isEmpty()){
               String sqlCountSource = "SELECT COUNT(*) COUNT FROM " + data_name;
               FSqlQuery countSource = new FSqlQuery(sqlCnn, sqlCountSource);
               FDataset countDataset = countSource.fetchDataset();
               int recordCount = 0;
               for(FRow row : countDataset){
                  recordCount = Integer.parseInt(row.get("COUNT"));
               }
               String tableName = data_name + "_DS";
               String installDataPath = RFile.format(dataStorePath + "/" + datasetName + _csv);
               if(0 < recordCount){
                  // 保存CSV
                  FXmlNode node = config.findNode("Fields");
                  int count = node.nodes().count();
                  String sqlSource = _resource.findString("data.table.column");
                  sqlSource = RString.parse(sqlSource, "table_name", tableName);
                  FSqlQuery columnSource = new FSqlQuery(sqlCnn, sqlSource);
                  FDataset columnDataset = columnSource.fetchDataset();
                  FAttributes existsColumns = new FAttributes();
                  FAttributes columns = new FAttributes();
                  for(FRow row : columnDataset){
                     columns.set(row.get("column_name"), null);
                  }
                  for(int n = 0; n < count; n++){
                     FXmlNode fieldNode = node.node(n);
                     String fieldName = fieldNode.get("data_name");
                     boolean exists = columns.contains(fieldName);
                     if(exists){
                        FCsvColumn column = new FCsvColumn();
                        String name = fieldNode.get("name");
                        column.setName(name);
                        String label = fieldNode.get("label");
                        column.setLabel(label);
                        csvWriter.columns().set(name, column);
                        existsColumns.set(fieldName, name);
                     }
                     fieldNode.set("is_exists", RBoolean.toString(exists));
                  }
                  String[] names = existsColumns.values();
                  File f = new File(_installDataTable);
                  // 路径不存在则创建路径
                  if(!f.exists()){
                     f.mkdirs();
                  }
                  csvWriter.openFile(installDataPath, _encode);
                  csvWriter.doCommand(RCsvCommand.Head);
                  csvWriter.doCommand(RCsvCommand.Label);
                  // 版本
                  FCsvCommandProperty versionPropert = new FCsvCommandProperty();
                  versionPropert.setName("version");
                  versionPropert.setValue("1.0.0");
                  csvWriter.doCommand(versionPropert);
                  // 作者
                  FCsvCommandProperty authorProperty = new FCsvCommandProperty();
                  authorProperty.setName("author");
                  authorProperty.setValue("eUIS-system");
                  csvWriter.doCommand(authorProperty);
                  // 生成时间
                  //FCsvCommandProperty dateProperty = new FCsvCommandProperty();
                  //dateProperty.setName("create_date");
                  //dateProperty.setValue(RDate.format());
                  //csvWriter.doCommand(dateProperty);
                  // 数据集
                  FCsvCommandProperty property = new FCsvCommandProperty();
                  property.setName("dataset_name");
                  property.setValue(datasetName);
                  csvWriter.doCommand(property);
                  csvWriter.doCommand(RCsvCommand.Data);
                  FSql sql = RDatasetSqlBuilder.makeFetchSql(config);
                  // Execute
                  //      try{
                  //                  reader = sqlCnn.activeConnection().fetchReader(sql);
                  //                  ICsvLine line = csvWriter.createLine();
                  //                  while(reader.hasNext()){
                  //                     line.clear();
                  //                     FRow row = reader.read();
                  //                     for(String name : names){
                  //                        String rowValue = row.get(name);
                  //                        if(null != rowValue && !rowValue.isEmpty()){
                  //                           if(rowValue.contains("\n")){
                  //                              rowValue = RString.replace(rowValue, "\n", "\\n");
                  //                           }
                  //                           if(rowValue.contains("\t")){
                  //                              rowValue = RString.replace(rowValue, "\t", "\\t");
                  //                           }
                  //                           rowValue = ":" + rowValue;
                  //                        }
                  //                        line.set(name, rowValue);
                  //                     }
                  //                     csvWriter.writeLine(line);
                  //                  }
                  //                  csvWriter.doCommand(RCsvCommand.End);
                  //                  csvWriter.doCommand(RCsvCommand.ValidLines);
               }else{
               }
            }
         }catch(Exception e){
            _logger.error(this, "updateDataStore", e, "Execute failure. (dataset={0})", datasetName);
         }finally{
            if(null != reader){
               reader.close();
            }
            csvWriter.Close();
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   @Override
   public FString dropField(String tableName,
                            String fieldName){
      FSql sql = new FSql();
      sql.append("ALTER TABLE " + tableName + "_DS DROP COLUMN " + fieldName);
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   @Override
   public void dropFieldDataset(FXmlNode config,
                                ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   /**
    * <T>删除索引</T>
    */
   public FString dropIndex(String index){
      // 删除索引操作
      FSql sql = new FSql();
      sql.append("DROP INDEX " + index + ";");
      return sql;
   }

   /**
    * <T>删除主键</T>
    */
   public FString dropPrimaryKey(String tableName,
                                 String key){
      // 删除主键操作
      FSql sql = new FSql();
      sql.append("alter table " + tableName + " drop constraint " + key);
      return sql;
   }

   @Override
   public void dropTable(FXmlNode config,
                         ISqlConnection synConnection){
      // 解析xml节点获得处理记录
      //FStrings items = fetchRangeItem(config);
   }

   /**
    * <T>删除表</T>
    */
   public FString dropTable(String tableName){
      FSql sql = new FSql();
      sql.append("DROP TABLE " + tableName + "_HS");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   @Override
   public void dropTableDataset(FXmlNode config,
                                ISqlConnection synConnection){
      System.out.println("tableDropDataset");
   }

   /**
    * <T>删除唯一键</T>
    */
   public FString dropUnique(String tableName,
                             String tableAlias,
                             String uniqueName){
      // 删除唯一键操作
      String uniqueTableName = tableName;
      if(tableAlias != null){
         uniqueTableName = tableAlias.toUpperCase();
      }
      FSql sql = new FSql();
      sql.append("alter table " + tableName + " drop constraint " + uniqueTableName + "_UK_" + uniqueName + " cascade;");
      return sql;
   }

   @Override
   public void exportData(FXmlNode config,
                          ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   @Override
   public void exportDataDataset(FXmlNode config,
                                 ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   /**
    * <T>检查索引</T>
    */
   public String getAllIndexName(String tableName,
                                 String indexName){
      if(RString.isNotEmpty(tableName)){
         tableName = RString.toUpper(tableName);
         tableName = RString.replace(tableName, "_DS", "_DX_");
         String allIndexName = tableName + indexName;
         return allIndexName;
      }
      return null;
   }

   /**
    * <T>得到所有外键</T>
    * 
    */
   public FDataset getAllUnique(String tableName,
                                ISqlConnection sqlCnn){
      // 执行sql，检索出type进行比较
      FSql sql = new FSql();
      sql.append("SELECT CU.COLUMN_NAME FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME AND AU.CONSTRAINT_TYPE = 'U' AND AU.TABLE_NAME = Upper('" + tableName + "')");
      // 执行sql
      FSqlQuery sqlSource = new FSqlQuery(sqlCnn, sql);
      FDataset dataset = sqlSource.fetchDataset();
      return dataset;
   }

   /**
    * <T>得到xml中字段长度</T>
    */
   public String getDataSize(FXmlNode node){
      String dataType = node.get("type").toUpperCase();
      if("STRING".equals(dataType) || "CHAR".equals(dataType)){
         return node.get("data_size");
      }else{
         return null;
      }
   }

   /**
    * <T>检查字段缺省值</T>
    */
   protected String getHisFieldName(String fieldName,
                                    FDataset dataset){
      String hisFieldName = fieldName;
      // 取得当前记录，有且仅有一行
      int count = 0;
      FRow row = null;
      while(count < 20){
         if(0 != count){
            hisFieldName = fieldName + "_" + String.valueOf(count);
         }
         row = dataset.find("column_name", hisFieldName);
         if(row == null){
            break;
         }
         count++;
      }
      return hisFieldName;
   }

   /**
    * <T>获得表的所有索引列表</T>
    */
   public FStrings getIndexParams(String indexName,
                                  FDataset indexs){
      FStrings params = null;
      if(null != indexs && !(indexs.isEmpty()) && RString.isNotEmpty(indexName)){
         params = new FStrings();
         for(FRow row : indexs){
            if(indexName.equalsIgnoreCase(row.get("index_name"))){
               params.push(row.get("column_name"));
            }
         }
      }
      return params;
   }

   /**
    * <T>获得表的所有索引列表</T>
    */
   public FDataset getTableIndexs(String tableName,
                                  ISqlConnection synConnection){
      FDataset indexs = null;
      if(RString.isNotEmpty(tableName)){
         String sql = "SELECT DISTINCT (A.INDEX_NAME),B.COLUMN_NAME,B.TABLE_NAME FROM USER_INDEXES A, USER_IND_COLUMNS B WHERE A.INDEX_NAME = B.INDEX_NAME AND A.TABLE_NAME = Upper('" + tableName + "')";
         indexs = synConnection.fetchDataset(sql);
      }
      return indexs;
   }

   /**
    * <T>获得指定目录下的zip文件目录</T>
    * <P>如果没有指定zip文件，则查找最后版本的zip文件目录</p>
    * 
    */
   protected ZipFile getZipObject(String zipPath){
      if(RString.isEmpty(zipPath)){
         zipPath = "D:/eUIS-Store";
      }
      try{
         File file = new File(zipPath);
         // 判断指定的目录是否为zip文件包
         if(file.isFile()){
            return new ZipFile(zipPath);
         }
         // 查找最后一个导入文件
         String newPath = "";
         for(File zipFile : file.listFiles()){
            String path = zipFile.getAbsolutePath();
            if(path.compareTo(zipPath) > 0){
               newPath = path;
            }
         }
         return new ZipFile(newPath);
      }catch(Exception e){
         _logger.error(this, "getZipObject", e, "getZipObject failure.");
      }
      return null;
   }

   @Override
   public void importData(FXmlNode config,
                          ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   @Override
   public void importDataDataset(FXmlNode config,
                                 ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   public FStringFile makeSqlTableCache(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_CACHE);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeSqlTableDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP TABLE " + dataset.get("data_name") + "_DS");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makeSqlTableMemory(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_MEMORY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      parser.define("indexs", dataset.node("indexs").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeSqlTableWork(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_WORK);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public void maxSequence(ISqlConnection sqlCnn,
                           String dataName){
      // 建立数据库连接
      String tableName = dataName + "_DS";
      String sequenceName = dataName + "_SQ";
      FSqlProcedure procedure = new FSqlProcedure("Max_Sequence");
      procedure.setLogicName("CP_SEQUENCE");
      procedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("sequence_name_", sequenceName, ESqlDataType.String, ESqlDataDirection.In);
      sqlCnn.activeConnection().execute(procedure);
   }

   /**
    * <T>更新字段缺省值</T>
    */
   public FString modifyFieldDefault(String tableName,
                                     String dataName,
                                     String dataDefault){
      /// alter table 表名称 modify 字段 数据类型(长度)
      FSql sql = new FSql();
      sql.append("alter table " + tableName + " modify " + dataName + " default " + dataDefault + ";");
      return sql;
   }

   /**
    * <T>更新字段空处理</T>
    */
   public FString modifyFieldEmpty(String tableName,
                                   String dataName,
                                   String dataEmpty){
      /// alter table 表名称 modify 字段 数据类型(长度)
      dataEmpty = dataEmpty.equals("Y") ? "NULL" : "NOT NULL";
      FSql sql = new FSql();
      sql.append("alter table " + tableName + " modify " + dataName + " " + dataEmpty + ";");
      return sql;
   }

   /**
    * <T>修改字段名称之前操作</T>
    */
   public FString modifyFieldName(String tableName,
                                  String fieldName,
                                  FRow row,
                                  FDataset dataset){
      if(null != row && !row.isEmpty()){
         FString sql = new FString();
         String isNull = row.get("nullable");
         if(!RBoolean.parse(isNull)){
            sql.append(modifyFieldEmpty(tableName, fieldName, "Y") + ";");
         }
         // 查找最新的历史名词
         String hisFieldName = getHisFieldName(fieldName, dataset);
         // 修改旧字段名称为fieldName_n
         sql.append("ALTER TABLE " + tableName + " RENAME COLUMN " + fieldName + " TO " + hisFieldName + ";");
         return sql;
      }
      return null;
   }

   /**
    * <T>更新字段大小</T>
    */
   public FString modifyFieldSize(String tableName,
                                  String dataName,
                                  String dataSize){
      /// alter table 表名称 modify 字段 数据类型(长度)
      if(RString.isNotEmpty(dataSize)){
         dataSize = dataSize.trim();
         FSql sql = new FSql();
         sql.append("alter table " + tableName + " modify " + dataName + " " + dataSize + ";");
         return sql;
      }
      return null;
   }

   /**
    * <T>修改唯一键</T>
    */
   public FString modifyUniqueKey(String tableName,
                                  String tableAlias,
                                  String uniqueName,
                                  String uniqueAlias){
      /// alter table 表名称 modify 字段 数据类型(长度)
      String uniqueTableName = tableName;
      if(tableAlias != null){
         uniqueTableName = tableAlias.toUpperCase();
      }
      if(uniqueAlias != null){
         uniqueAlias = uniqueAlias.toUpperCase();
      }
      if(RString.isEmpty(uniqueAlias)){
         uniqueAlias = uniqueName;
      }
      FSql sql = new FSql();
      sql.append("alter table " + tableName + " add constraint " + uniqueTableName + "_UK_" + uniqueAlias + " unique (" + uniqueName + ");");
      return sql;
   }

   /**
    * <T>从指定文件中读取文件</T>
    * 
    */
   protected FCsvReader readFile(String filePath){
      if(RString.isNotEmpty(filePath)){
         try{
            filePath = _installDataTable + "/" + filePath;
            filePath = RString.replace(filePath, "\\", "/");
            File file = new File(filePath);
            if(file.canRead()){
               FCsvReader reader = new FCsvReader();
               InputStream in = new FileInputStream(file);
               InputStreamReader stream = new InputStreamReader(in, _encode);
               reader.openStream(stream, _encode);
               reader.openSegment();
               return reader;
            }
         }catch(Exception e){
            _logger.error(this, "readFile", e, "Execute readFile failure)");
         }
      }
      return null;
   }

   /**
    * <T>从指定文件中读取文件</T>
    * 
    */
   protected FCsvReader readFile(ZipFile zipfile,
                                 String filePath){
      if(null != zipfile){
         try{
            filePath = RString.replace(filePath, "\\", "/");
            ZipEntry entry = zipfile.getEntry(filePath);
            if(null != entry){
               FCsvReader reader = new FCsvReader();
               InputStreamReader stream = new InputStreamReader(zipfile.getInputStream(entry), _encode);
               reader.openStream(stream, _encode);
               reader.openSegment();
               return reader;
            }
         }catch(Exception e){
            _logger.error(this, "readFile", e, "Execute readFile failure)");
         }
      }
      return null;
   }

   /**
    * <T>设置备份数据路径</T>
    */
   public void setStorePath(String path){
      /// 处理后路劲
      if(RString.isNotEmpty(path)){
         _installDataTable = path;
      }
      String subDate = RString.mid(_installDataTable, "{", "}");
      _installDataTable = RString.replace(_installDataTable, "{" + subDate + "}", RDateTime.format(subDate));
      _installDataTable = _installDataTable + "/data-store";
   }

   public void tableForeignKeyDisable(ISqlConnection sqlCnn,
                                      String tableName){
      // 设置禁止外键使用
      FSqlProcedure disableProcedure = new FSqlProcedure("Table_Foreign_Key_Disable");
      disableProcedure.setLogicName("CP_SQL");
      disableProcedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      sqlCnn.activeConnection().execute(disableProcedure);
   }

   public void tableForeignKeyDisableAll(){
      ISqlConnection sqlCnn = null;
      try{
         sqlCnn = _databaseConsole.alloc();
         // 设置禁止所有外键使用
         FSqlProcedure disableProcedure = new FSqlProcedure("Table_Foreign_Key_Disable_All");
         disableProcedure.setLogicName("CP_SQL");
         sqlCnn.activeConnection().execute(disableProcedure);
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   public void tableForeignKeyEnable(ISqlConnection sqlCnn,
                                     String tableName){
      // 设置恢复外键使用
      FSqlProcedure enableProcedure = new FSqlProcedure("Table_Foreign_Key_Enable");
      enableProcedure.setLogicName("CP_SQL");
      enableProcedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      sqlCnn.activeConnection().execute(enableProcedure);
   }

   public void tableForeignKeyEnableAll(){
      ISqlConnection sqlCnn = null;
      // 设置恢复所有外键使用
      try{
         sqlCnn = _databaseConsole.alloc();
         FSqlProcedure enableProcedure = new FSqlProcedure("Table_Foreign_Key_Enable_All");
         enableProcedure.setLogicName("CP_SQL");
         sqlCnn.activeConnection().execute(enableProcedure);
      }finally{
         if(null != sqlCnn){
            _databaseConsole.free(sqlCnn);
         }
      }
   }

   /**
    * <T>数据转换</T>
    */
   public String typeCasting(String dataType){
      String resultType = dataType;
      if("INTEGER".equals(dataType)){
         resultType = "NUMBER";
      }
      return resultType;
   }

   public void updateDataStore(IXmlObject xodataset,
                               ISqlConnection connection,
                               FStrings allLogs,
                               FStrings failLogs){
      FString sqlString = new FString();
      /// 得到表名（未判断表的类型）
      String dataName = xodataset.innerGet("data_name");
      // 数据集名称
      String datasetName = xodataset.innerGet("name");
      FXmlNode xdataset = _datasetConsole.buildConfig(xodataset);
      if("Y".equals(xdataset.get("IS_VALID"))){
         // 设置系统模式（由批处理里设定）
         if(ESystemMode.Test.equals(_sysMode)){
            xdataset.set("config", "Test");
         }else if(ESystemMode.Logic.equals(_sysMode)){
            xdataset.set("config", "Logic");
         }else if(ESystemMode.Develop.equals(_sysMode)){
            xdataset.set("config", "Develop");
         }
         try{
            if(dataName != null){
               /// 数据表名称
               String dataStoreTableName = dataName + "_DS";
               /// 数据表别称
               String dataStoreAliasName = xodataset.innerGet("data_alias");
               if(RString.isEmpty(dataStoreAliasName)){
                  dataStoreAliasName = dataName;
               }
               /// 表操作SQL
               FString addTableSql = null;
               /// 数据表字段属性
               FDataset dataset = checkTable(dataStoreTableName, connection);
               /// 数据表唯一键集合
               FDataset allUnique = getAllUnique(dataStoreTableName, connection);
               // 如果表不存在，则新建表
               if(dataset.isEmpty()){
                  /// 表类型
                  String datasetType = xodataset.name();
                  /// 如果表不存在，创建表
                  if("DataWork".equalsIgnoreCase(datasetType)){
                     // 获得数据类型
                     String dataType = xdataset.get(XDataWork.PTY_DATA_TYPE);
                     // 判断数据类型为工作数据
                     if("Work".equalsIgnoreCase(dataType)){
                        addTableSql = makeSqlTableWork(xdataset);
                     }else if("Memory".equalsIgnoreCase(dataType)){
                        // 判断数据类型为内存数据
                        addTableSql = makeSqlTableMemory(xdataset);
                     }
                  }else if("DataStore".equalsIgnoreCase(datasetType)){
                     addTableSql = addDataTable(xdataset, "dataStore");
                  }
                  // 执行sql
                  if(addTableSql != null){
                     connection.activeConnection().executeSqls(addTableSql.toString());
                  }
               }else{
                  /// 保存数据标识
                  boolean dataStoreAble = false;
                  /// 检查字段属性
                  FXmlNode fieldsNodes = xdataset.findNode("Fields");
                  if(fieldsNodes != null){
                     //int count = fieldsNodes.count();
                     /// 添加字段操作sql
                     FString addFieldSql = null;
                     /// 字段类型变更sql
                     //                     FString modifyTypeSql = null;
                     //                     /// 字段大小
                     //                     FString modifySizeSql = null;
                     //                     /// 检查缺省字段
                     //                     FString modifyDefaultSql = null;
                     for(FXmlNode fieldNode : fieldsNodes.nodes()){
                        _failLogs.clear();
                        /// 得到行记录下载
                        if("Y".equals(fieldNode.get("IS_VALID"))){
                           if(fieldNode != null){
                              /// 检查字段是否存在
                              /// 如果字段不存在，则新增;true 表示不存在
                              if(checkField(dataStoreTableName, fieldNode, dataset)){
                                 addFieldSql = addField(dataStoreTableName, fieldNode);
                                 if(addFieldSql != null){
                                    dataStoreAble = true;
                                    sqlString.append(addFieldSql);
                                 }
                              }else{
                                 _addedField = false;
                                 /// 检查字段类型
                                 FString modifyTypeSql = checkFieldDataType(dataStoreTableName, fieldNode, dataset);
                                 if(modifyTypeSql != null){
                                    dataStoreAble = true;
                                    sqlString.append(modifyTypeSql);
                                 }
                                 /// 检查字段大小
                                 if(modifyTypeSql == null){
                                    FString modifySizeSql = checkFieldSize(dataStoreTableName, fieldNode, dataset);
                                    if(modifySizeSql != null){
                                       dataStoreAble = true;
                                       sqlString.append(modifySizeSql);
                                    }
                                 }
                                 /// 检查字段缺省值
                                 //                                 FString modifyDefaultSql = checkFieldDefault(dataStoreTableName, fieldNode, dataset);
                                 //                                 if(modifyDefaultSql != null){
                                 //                                    dataStoreAble = true;
                                 //                                    sqlString.append(modifyDefaultSql);
                                 //                                 }
                                 /// 检查字段非空处理
                                 FString modifyEmptySql = checkFieldEmpty(dataStoreTableName, fieldNode, dataset);
                                 if(modifyEmptySql != null){
                                    dataStoreAble = true;
                                    sqlString.append(modifyEmptySql);
                                 }
                                 /// 唯一值检查
                                 FString modifyUniqueSql = checkFieldUnique(dataStoreTableName, dataStoreAliasName, fieldNode, allUnique);
                                 if(modifyUniqueSql != null){
                                    dataStoreAble = true;
                                    sqlString.append(modifyUniqueSql);
                                 }
                              }
                           }
                           if(!_failLogs.isEmpty()){
                              for(String failLog : _failLogs){
                                 failLogs.push(failLog);
                              }
                           }
                        }
                     }
                  }
                  /// 检查外键
                  /// 检查主键（因为对应的SQL文处没有对主键进行处理，无法得到相应的属性）
                  /*FXmlNode keysNodes = xdataset.findNode("Keys");
                  if(keysNodes != null){
                     for(int k = 0; k < keysNodes.count(); k++){
                        FXmlNode keysNode = keysNodes.node(k);
                        checkPrimaryKey(dataStoreTableName, keysNode, connection);
                     }
                  }*/
                  /// 检查索引
                  FXmlNode indexNodes = xdataset.findNode("Indexs");
                  if(null != indexNodes && indexNodes.hasNode()){
                     FString indexSqls = checkIndex(dataStoreTableName, indexNodes, connection);
                     sqlString.append(indexSqls);
                  }
                  /*FString modifyUniqueSql = checkUniqueKey(dataStoreTableName, fieldNode, connection);
                  if(modifyUniqueSql != null){
                     dataStoreAble = true;
                     sqlString.append(modifyUniqueSql);
                  }*/
                  /// checkIndex(tableName, indexsNodes, connection);
                  // 执行sql
                  // 数据备份模式是All则全部备份
                  if("All".equalsIgnoreCase(_dateStoreMode)){
                     dataStoreAble = true;
                  }
                  if(dataStoreAble){
                     if("Alert".equalsIgnoreCase(_dateStoreMode)){
                        boolean dataExist = checkDataExist(dataStoreTableName, connection);
                        if(dataExist){
                           /// 保存数据
                           dataStore(xodataset, connection);
                        }
                     }
                  }
                  /// 执行操作
                  if(!sqlString.isEmpty()){
                     System.out.println("==================================");
                     System.out.println(sqlString);
                     System.out.println("==================================");
                     connection.activeConnection().executeSqls(sqlString.toString());
                  }
                  if("All".equalsIgnoreCase(_dateRestoreMode)){
                     dataStoreAble = true;
                  }
                  if(dataStoreAble){
                     if("Alert".equalsIgnoreCase(_dateRestoreMode)){
                        String fileName = datasetName + _csv;
                        FCsvReader read = readFile(fileName);
                        if(null != read){
                           /// 恢复数据
                           dataRestore(xodataset, connection, read, true);
                        }
                     }
                  }
                  /// 写出日志
                  allLogs.push("TABLE OPERATION(" + dataName + ":   " + sqlString.toString() + ")");
                  /*if(dataExist){
                     /// 恢复数据
                     _datasetConsole.dataRestore(xodataset);
                  }*/
               }
            }
         }catch(Exception e){
            _logger.error(this, "updateDataStore", e, "Execute failure. (sql={0})", sqlString);
            /// 追加log消息
            failLogs.push("TABLE OPERATION FAILED(" + dataName + ":   " + sqlString.toString() + ")");
         }finally{
            if(null != connection){
               _databaseConsole.free(connection);
            }
         }
      }
   }

   @Override
   public void updateField(FXmlNode config,
                           ISqlConnection synConnection){
      // TODO Auto-generated method stub
   }

   /**
    * <T>检查表字段</T>
    */
   /*public FString updateField(String tableName,
                              FXmlNode node,
                              ISqlConnection sqlCnn){
      // 检查字段是否需要添加
      if(checkField(tableName, node, sqlCnn).isEmpty()){
         FSql processSql = new FSql();
         processSql.append(addField(tableName, node));
         return processSql;
      }
      return null;
   }*/
   @Override
   public void updateFieldDataset(FXmlNode config,
                                  ISqlConnection synConnection){
      /*// 检查字段是否需要添加
      if(checkFieldModify(tableName, node, sqlCnn) == true){
         FSql processSql = new FSql();
         processSql.append(addField(tableName, node));
         return processSql;
      }
      return null;*/
   }

   @SuppressWarnings("unused")
   public void updateHistoryStore(IXmlObject xodataset,
                                  ISqlConnection connection,
                                  FStrings allLogs,
                                  FStrings failLogs){
      FString sqlString = new FString();
      /// 得到表名（未判断表的类型）
      String datasetName = xodataset.innerGet("data_name");
      FXmlNode xdataset = _datasetConsole.buildConfig(xodataset);
      if("Y".equals(xdataset.get("IS_VALID"))){
         // 设置系统模式（由批处理里设定）
         if(ESystemMode.Test.equals(RSystem.modeCd())){
            xdataset.set("config", "Test");
         }else if(ESystemMode.Logic.equals(RSystem.modeCd())){
            xdataset.set("config", "Logic");
         }else if(ESystemMode.Develop.equals(RSystem.modeCd())){
            xdataset.set("config", "Develop");
         }
         try{
            if(datasetName != null){
               /// 数据表名称
               String dataStoreTableName = datasetName + "_HS";
               /// 数据表别称
               String dataStoreAliasName = xodataset.innerGet("data_alias");
               /// 表操作SQL
               FString addTableSql = null;
               /// 返回值接收
               FDataset dataset = checkTable(dataStoreTableName, connection);
               /// 数据表唯一键集合
               FDataset allUnique = getAllUnique(dataStoreTableName, connection);
               /// 如果表不存在，创建表
               if(dataset.isEmpty()){
                  addTableSql = addDataTable(xdataset, "historyStore");
                  // 执行sql
                  if(addTableSql != null){
                     connection.activeConnection().executeSqls(addTableSql.toString());
                  }
               }else{
                  /// 保存数据标识
                  boolean dataStoreAble = false;
                  /// 检查字段属性
                  FXmlNode fieldsNodes = xdataset.findNode("Fields");
                  if(fieldsNodes != null){
                     int count = fieldsNodes.nodeCount();
                     //                     /// 添加字段操作sql
                     //                     FString addFieldSql = null;
                     //                     /// 字段类型变更sql
                     //                     FString modifyTypeSql = null;
                     //                     /// 字段大小
                     //                     FString modifySizeSql = null;
                     //                     /// 检查缺省字段
                     //                     FString modifyDefaultSql = null;
                     for(int n = 0; n < count; n++){
                        /// 得到行记录下载
                        FXmlNode fieldNode = fieldsNodes.node(n);
                        if("Y".equals(fieldNode.get("IS_VALID"))){
                           _failLogs.clear();
                           if(fieldNode != null){
                              /// 检查字段是否存在
                              if(checkField(dataStoreTableName, fieldNode, dataset) == true){
                                 FString addFieldSql = addField(dataStoreTableName, fieldNode);
                                 if(addFieldSql != null){
                                    dataStoreAble = true;
                                    sqlString.append(addFieldSql);
                                 }
                              }else{
                                 /// 检查字段类型
                                 FString modifyTypeSql = checkFieldDataType(dataStoreTableName, fieldNode, dataset);
                                 if(modifyTypeSql != null){
                                    dataStoreAble = true;
                                    sqlString.append(modifyTypeSql);
                                 }
                                 /// 检查字段大小
                                 if(modifyTypeSql == null){
                                    FString modifySizeSql = checkFieldSize(dataStoreTableName, fieldNode, dataset);
                                    if(modifySizeSql != null){
                                       dataStoreAble = true;
                                       sqlString.append(modifySizeSql);
                                    }
                                 }
                                 /*/// 检查字段缺省值
                                 modifyDefaultSql = checkFieldDefault(dataStoreTableName, fieldNode, dataset);
                                 if(modifyDefaultSql != null){
                                    dataStoreAble = true;
                                    sqlString.append(modifyDefaultSql);
                                 }*/
                                 /// 检查字段非空处理
                                 FString modifyEmptySql = checkFieldEmpty(dataStoreTableName, fieldNode, dataset);
                                 if(modifyEmptySql != null){
                                    dataStoreAble = true;
                                    sqlString.append(modifyEmptySql);
                                 }
                                 /// 唯一值检查
                                 //                                 FString modifyUniqueSql = checkFieldUnique(dataStoreTableName, dataStoreAliasName,
                                 //                                       fieldNode, allUnique);
                                 //                                 if(modifyUniqueSql != null){
                                 //                                    dataStoreAble = true;
                                 //                                    sqlString.append(modifyUniqueSql);
                                 //                                 }
                              }
                           }
                           if(!_failLogs.isEmpty()){
                              for(String failLog : _failLogs){
                                 failLogs.push(failLog);
                              }
                           }
                        }
                     }
                  }
                  /// 检查外键
                  /// 检查主键
                  /*FXmlNode keysNodes = xdataset.findNode("Keys");
                  if(keysNodes != null){
                     for(int k = 0; k < keysNodes.count(); k++){
                        FXmlNode keysNode = keysNodes.node(k);
                        checkPrimaryKey(dataStoreTableName, keysNode, connection);
                     }
                  }*/
                  /// 检查索引
                  ///FXmlNode indexsNodes = xdataset.findNode("Indexs");
                  /*FString modifyUniqueSql = checkUniqueKey(dataStoreTableName, fieldNode, connection);
                  if(modifyUniqueSql != null){
                     dataStoreAble = true;
                     sqlString.append(modifyUniqueSql);
                  }*/
                  /// checkIndex(tableName, indexsNodes, connection);
                  // 执行sql
                  //                  if(dataStoreAble){
                  //                     boolean dataExist = checkDataExist(dataStoreTableName, connection);
                  //                     if(dataExist){
                  //                        /// 保存数据
                  //                        _datasetConsole.dataStore(xodataset);
                  //                     }
                  //                     /// 执行操作
                  //                     connection.activeConnection().executeSqls(sqlString);
                  //                     /// 写出日志
                  //                     allLogs.push("TABLE OPERATION FAILED(" + datasetName + ":   " + sqlString.toString());
                  //                     /// 日志记录
                  //
                  //                     if(dataExist){
                  //                        /// 恢复数据
                  //                        _datasetConsole.dataRestore(xodataset);
                  //                     }
                  //
                  //                  }
                  if("All".equalsIgnoreCase(_dateStoreMode)){
                     dataStoreAble = true;
                  }
                  if(dataStoreAble){
                     boolean dataExist = checkDataExist(dataStoreTableName, connection);
                     if(dataExist){
                        /// 保存数据
                        dataStore(xodataset, connection);
                     }
                  }
                  /// 执行操作
                  if(!sqlString.isEmpty()){
                     System.out.println("==================================");
                     System.out.println(sqlString.toString());
                     System.out.println("==================================");
                     connection.activeConnection().executeSqls(sqlString.toString());
                  }
                  if("All".equalsIgnoreCase(_dateRestoreMode)){
                     dataStoreAble = true;
                  }
                  if(dataStoreAble){
                     if("Alert".equalsIgnoreCase(_dateRestoreMode)){
                        String fileName = datasetName + _csv;
                        FCsvReader read = readFile(fileName);
                        if(null != read){
                           /// 恢复数据
                           dataRestore(xodataset, connection, read, true);
                        }
                     }
                  }
                  /// 写出日志
                  allLogs.push("TABLE OPERATION FAILED(" + dataStoreTableName + ":   " + sqlString.toString());
                  /*if(dataExist){
                     /// 恢复数据
                     _datasetConsole.dataRestore(xodataset);
                  }*/
               }
            }
         }catch(Exception e){
            _logger.error(this, "updateDataStore", e, "Execute failure. (sql={0})", sqlString);
            /// 追加log消息
            failLogs.push("TABLE OPERATION FAILED(" + datasetName + "_HS:   " + sqlString.toString());
         }finally{
            if(null != connection){
               _databaseConsole.free(connection);
            }
         }
      }
   }

   @Override
   public void updateTable(FXmlNode config,
                           FStrings allLogs,
                           FStrings failLogs){
      // 解析xml获得要同步的数据集名称
      FStrings items = fetchRangeItem(config);
      if(items.isEmpty()){
         return;
      }
      /// 得到数据库连接
      String dataName = config.get("syn_name");
      ISqlConnection synConnection = _databaseConsole.alloc(dataName).activeConnection();
      if(null == synConnection){
         failLogs.push("Update Table is fail, cause connection database is fail.");
         return;
      }
      // 设置系统模式
      _sysMode = config.get("system_mode");
      _dateStoreMode = config.get("data_store");
      _dateRestoreMode = config.get("data_restore");
      String dataPath = config.get("data_path");
      setStorePath(dataPath);
      for(String item : items){
         String dataset = item;
         if(RString.isNotEmpty(dataset)){
            if(TYPE_ALL.equalsIgnoreCase(dataset)){
               for(IXmlObject xodataset : _datasetConsole.list()){
                  if("DataStore".equalsIgnoreCase(xodataset.name())){
                     updateDataStore(xodataset, synConnection, allLogs, failLogs);
                     updateHistoryStore(xodataset, synConnection, allLogs, failLogs);
                  }
                  if("DataWork".equalsIgnoreCase(xodataset.name())){
                     // 判断数据类型为内存数据
                     updateDataStore(xodataset, synConnection, allLogs, failLogs);
                  }
               }
            }else{
               IXmlObject xodataset = _datasetConsole.find(dataset);
               if("DataStore".equalsIgnoreCase(xodataset.name())){
                  updateDataStore(xodataset, synConnection, allLogs, failLogs);
                  updateHistoryStore(xodataset, synConnection, allLogs, failLogs);
               }
               if("DataWork".equalsIgnoreCase(xodataset.name())){
                  // 判断数据类型为内存数据
                  updateDataStore(xodataset, synConnection, allLogs, failLogs);
               }
            }
         }
      }
   }

   @Override
   public void updateTableDataset(FXmlNode config,
                                  ISqlConnection synConnection){
      System.out.println("tableUpdateDataset");
   }
   //   public void truncate(FXmlNode config){
   //      FStrings items = fetchRange(config);
   //      if(!items.isEmpty()){
   //         ISqlConnection connection = _databaseConsole.alloc();
   //         try{
   //            FSqlQuery queryForeign = new FSqlQuery(connection, _resource.findString("query.foreign"));
   //            for(String item : items){
   //               // 禁止外键
   //               queryForeign.bindString("table_name", item);
   //               FDataset dsForeign = queryForeign.fetchDataset();
   //               for(FRow row : dsForeign){
   //                  connection.executeSql("ALTER TABLE " + row.get("TABLE_NAME") + " DROP CONSTRAINT " + row.get("CONSTRAINT_NAME"));
   //               }
   //               // 删除表格
   //               connection.executeSql("DROP TABLE " + item);
   //            }
   //         }finally{
   //            _databaseConsole.free(connection);
   //         }
   //      }
   //   }
}
