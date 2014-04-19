package org.mo.data.dataset.common;

import org.mo.com.data.FSql;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;

public class RDatasetSqlBuilder
{
   public static FSql makeClearSql(IXmlObject xdataset){
      FSql sql = new FSql();
      sql.append("DELETE FROM ", xdataset.innerGet("data_name") + "_DS");
      return sql;
   }

   public static FSql makeDropSql(IXmlObject xdataset){
      FSql sql = new FSql();
      sql.append("DROP TABLE ", xdataset.innerGet("data_name"));
      return sql;
   }

   public static FSql makeFetchSql(FXmlNode xdataset){
      FSql sql = new FSql();
      sql.append("SELECT ");
      FXmlNodes xfields = xdataset.findNode("Fields").nodes();
      int count = xfields.count();
      boolean isFirst = true;
      for(int n = 0; n < count; n++){
         FXmlNode xfield = xfields.get(n);
         if(RBoolean.parse(xfield.get("is_exists"))){
            if(isFirst){
               isFirst = false;
            }else{
               sql.append(',');
            }
            String name = xfield.get("name");
            String dataName = xfield.get("data_name");
            sql.append(RString.toUpper(dataName));
            if(!dataName.equalsIgnoreCase(name)){
               sql.append(' ');
               sql.append(RString.toUpper(name));
            }
         }
      }
      sql.append(" FROM ");
      sql.append(xdataset.get("data_name") + "_DS");
      sql.append(" ORDER BY OUID");
      return sql;
   }

   /**
    * <T>根据数据集描述和行数据，生成一条插入用的SQL文。</T>
    * 
    * @param xdataset 数据集
    * @param row 行数据
    * @return 插入用的SQL文
    */
   public static FSql makeInsertSql(FXmlNode xdataset,
                                    IAttributes row){
      FSql sql = new FSql();
      FString fields = new FString();
      FString values = new FString();
      FXmlNodes xfields = xdataset.findNode("Fields").nodes();
      int count = xfields.count();
      boolean first = true;
      for(int n = 0; n < count; n++){
         FXmlNode xchild = xfields.get(n);
         String type = xchild.name();
         String name = xchild.get("name");
         String dataName = xchild.get("data_name");
         String dataValue = row.get(name);
         // 检查数据内容是否存在
         if(RString.isEmpty(dataValue)){
            continue;
         }
         // 追加一个新的数据字段和内容
         if(first){
            first = false;
         }else{
            fields.append(',');
            values.append(',');
         }
         fields.append(dataName);
         if("FieldInteger".equals(type)){
            values.append(dataValue);
         }else if("FieldDate".equals(type)){
            values.append("TO_DATE('", dataValue, "','YYYYMMDDHH24MISS')");
         }else{
            values.append("'", dataValue, "'");
         }
      }
      sql.append("INSERT INTO ");
      sql.append(xdataset.get("data_name") + "_DS");
      sql.append('(', fields, ')');
      sql.append(" VALUES(", values, ")");
      return sql;
   }

   public static FSql makeUpdateSql(IXmlObject xdataset,
                                    IAttributes row){
      FSql sql = new FSql();
      sql.append("UPDATE ");
      sql.append(xdataset.innerGet("data_name"));
      sql.append(" SET ");
      IXmlObjects children = xdataset.children();
      int count = children.count();
      for(int n = 0; n < count; n++){
         IXmlObject xchild = children.get(n);
         if(n > 0){
            sql.append(',');
         }
         String dataName = xchild.innerGet("data_name");
         String dataValue = row.get(dataName);
         sql.append(dataName, "=");
         if(RString.isEmpty(dataValue)){
            sql.append("NULL");
         }else{
            sql.append("'", dataValue, "'");
         }
      }
      sql.append(" WHERE OUID=");
      sql.append(row.get("ouid"));
      return sql;
   }
}
