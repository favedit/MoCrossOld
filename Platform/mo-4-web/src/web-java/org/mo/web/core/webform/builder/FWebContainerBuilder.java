package org.mo.web.core.webform.builder;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页容器建立器。</T>
//============================================================
public class FWebContainerBuilder
      extends FWebBuilderObject
{
   //============================================================
   // <T>构造网页容器建立器。</T>
   //
   // @param context 建立环境
   //============================================================
   public FWebContainerBuilder(FWebBuilderContext context){
      super(context);
      _context = context;
   }

   //============================================================
   // <T>建立数据行。</T>
   //
   // @param xconfig 设置信息
   // @return 数据行
   //============================================================
   public FRow fetchRow(FXmlNode xconfig){
      // 获得属性
      String datasetName = xconfig.get("dataset");
      String datasetSearch = xconfig.get("dataset_search");
      //............................................................
      // 建立命令字符串
      FSql sql = new FSql();
      sql.append("SELECT ");
      int fieldIndex = 0;
      for(FXmlNode xnode : xconfig.nodes()){
         String typeName = xnode.name();
         String dataName = null;
         if("Number".equals(typeName)){
            dataName = xnode.get("data_name");
         }else if("Edit".equals(typeName)){
            dataName = xnode.get("data_name");
         }
         if(null != dataName){
            if(fieldIndex > 0){
               sql.append(",");
            }
            sql.append(dataName);
            fieldIndex++;
         }
      }
      sql.append(" FROM ");
      sql.append(datasetName);
      sql.append(" WHERE ");
      sql.append(datasetSearch);
      //............................................................
      // 替换内容
      String source = sql.toString();
      for(IStringPair pair : _context.parameters()){
         String name = "${" + pair.name() + "}";
         String value = pair.value();
         source = RString.replace(source, name, value);
      }
      //............................................................
      // 查询内容
      FRow row = null;
      ISqlConnection connection = null;
      try{
         connection = _context.connectionConsole().alloc();
         row = connection.find(source);
      }finally{
         _context.connectionConsole().free(connection);
      }
      return row;
   }

   //============================================================
   // <T>建立数据集合。</T>
   //
   // @param xconfig 设置信息
   // @return 数据集合
   //============================================================
   public FDataset fetchDataset(FXmlNode xconfig,
                                int pageSize,
                                int page){
      // 获得属性
      String datasetName = xconfig.get("dataset");
      //............................................................
      // 建立命令字符串
      FSql sql = new FSql();
      sql.append("SELECT ");
      int fieldIndex = 0;
      for(FXmlNode xnode : xconfig.nodes()){
         String typeName = xnode.name();
         String dataName = null;
         if("ColumnNumber".equals(typeName)){
            dataName = xnode.get("data_name");
         }else if("ColumnEdit".equals(typeName)){
            dataName = xnode.get("data_name");
         }
         if(null != dataName){
            if(fieldIndex > 0){
               sql.append(",");
            }
            sql.append(dataName);
            fieldIndex++;
         }
      }
      sql.append(" FROM ");
      sql.append(datasetName);
      //............................................................
      // 查询内容
      FDataset dataset = null;
      ISqlConnection connection = null;
      try{
         connection = _context.connectionConsole().alloc();
         dataset = connection.fetchDataset(sql.toString(), pageSize, page);
      }finally{
         _context.connectionConsole().free(connection);
      }
      return dataset;
   }
}
