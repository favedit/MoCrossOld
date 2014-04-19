package org.mo.mime.csv;

import org.mo.com.lang.RBoolean;
import org.mo.com.xml.FXmlNode;

public class FCsvFile
      implements
         ICsvContainer
{
   protected String _executeAction;

   protected String _executePlsql;

   protected String _executePlsqlAfter;

   protected String _executePlsqlBefore;

   protected String _fileName;

   protected int _headLines;

   private final FCsvLines _lines = new FCsvLines();

   protected FCsvReader _reader = new FCsvReader();

   public FCsvFile(){
   }

   public FCsvFile(String fileName){
      loadFile(fileName);
   }

   @Override
   public FCsvColumns columns(){
      return _reader._columns;
   }

   public ICsvLine createLine(){
      FCsvLine line = new FCsvLine();
      line._container = this;
      return line;
   }

   public String executeAction(){
      return _executeAction;
   }

   public String executePlsql(){
      return _executePlsql;
   }

   public String executePlsqlAfter(){
      return _executePlsqlAfter;
   }

   public String executePlsqlBefore(){
      return _executePlsqlBefore;
   }

   public int headLines(){
      return _headLines;
   }

   public FCsvLines lines(){
      return _lines;
   }

   public void loadConfig(FXmlNode config){
      if(config.hasNode()){
         _headLines = config.getInt("head_lines");
         _executePlsql = config.get("execute_plsql");
         _executePlsqlBefore = config.get("execute_plsql_before");
         _executePlsqlAfter = config.get("execute_plsql_after");
         _executeAction = config.get("execute_action");
         FXmlNode columns = config.findNode("Columns");
         if(columns.hasNode()){
            for(FXmlNode column : columns.nodes()){
               FCsvColumn csvColumn = new FCsvColumn();
               String columnName = column.get("name");
               String label = column.get("label");
               String dataName = column.get("data_name");
               boolean isRequire = RBoolean.parse(column.get("is_require"));
               if(null != dataName){
                  csvColumn.setLabel(label);
                  csvColumn.setName(columnName);
                  csvColumn.setDataName(dataName);
                  csvColumn.setIsRequire(isRequire);
                  columns().set(dataName, csvColumn);
               }
            }
         }
      }
   }

   public void loadConfigByLabel(FXmlNode config,
                                 String[] columnLabels){
      if(config.hasNode()){
         _headLines = config.getInt("head_lines");
         _executePlsql = config.get("execute_plsql");
         _executePlsqlBefore = config.get("execute_plsql_before");
         _executePlsqlAfter = config.get("execute_plsql_after");
         _executeAction = config.get("execute_action");
         FXmlNode columns = config.findNode("Columns");
         for(int i = 0; i < columnLabels.length; i++){
            String colLabel = columnLabels[i];
            if(columns.hasNode()){
               for(FXmlNode column : columns.nodes()){
                  FCsvColumn csvColumn = new FCsvColumn();
                  String columnName = column.get("name");
                  String label = column.get("label");
                  String dataName = column.get("data_name");
                  boolean isRequire = RBoolean.parse(column.get("is_require"));
                  if(colLabel.equals(label)){
                     if(null != dataName){
                        csvColumn.setLabel(label);
                        csvColumn.setName(columnName);
                        csvColumn.setDataName(dataName);
                        csvColumn.setIsRequire(isRequire);
                        columns().set(dataName, csvColumn);
                        break;
                     }
                  }
               }
            }
         }
      }
   }

   public void loadConfigByName(FXmlNode config,
                                String[] columnNames){
      if(config.hasNode()){
         _headLines = config.getInt("head_lines");
         _executePlsql = config.get("execute_plsql");
         _executePlsqlBefore = config.get("execute_plsql_before");
         _executePlsqlAfter = config.get("execute_plsql_after");
         _executeAction = config.get("execute_action");
         FXmlNode columns = config.findNode("Columns");
         for(int i = 0; i < columnNames.length; i++){
            String colName = columnNames[i];
            if(columns.hasNode()){
               for(FXmlNode column : columns.nodes()){
                  FCsvColumn csvColumn = new FCsvColumn();
                  String columnName = column.get("name");
                  String label = column.get("label");
                  String dataName = column.get("data_name");
                  boolean isRequire = RBoolean.parse(column.get("is_require"));
                  if(colName.equals(columnName)){
                     if(null != dataName){
                        csvColumn.setLabel(label);
                        csvColumn.setName(columnName);
                        csvColumn.setDataName(dataName);
                        csvColumn.setIsRequire(isRequire);
                        columns().set(dataName, csvColumn);
                     }
                  }
               }
            }
         }
      }
   }

   /**
    * <T>加载目标文件所有内容。</T>
    * 
    * @param fileName 目标文件名
    */
   public void loadFile(String fileName){
      try{
         _reader.openFile(fileName);
         while(_reader.hasNext()){
            FCsvLine line = _reader.readLine();
            if(line != null){
               _lines.push(line);
            }
         }
      }finally{
         if(null != _reader){
            _reader.close();
         }
      }
   }

   public void loadFileWithoutHead(String fileName){
      try{
         _reader.openFile(fileName);
         for(int n = 0; n < _headLines; n++){
            _reader.readHead();
         }
         while(_reader.hasNext()){
            FCsvLine line = _reader.readLine();
            if(line != null){
               _lines.push(line);
            }
         }
      }finally{
         if(null != _reader){
            _reader.close();
         }
      }
   }

   public void loadFileWithoutHead(String fileName,
                                   String code){
      try{
         _reader.openFile(fileName, code);
         for(int n = 0; n < _headLines; n++){
            _reader.readHead();
         }
         while(_reader.hasNext()){
            FCsvLine line = _reader.readLine();
            if(line != null){
               _lines.push(line);
            }
         }
      }finally{
         if(null != _reader){
            _reader.close();
         }
      }
   }

   /**
    * @param fileName
    */
   public void saveFile(String fileName){
      _fileName = fileName;
      FCsvWriter csvWriter = new FCsvWriter();
      try{
         csvWriter.openFile(fileName);
         for(FCsvLine item : _lines){
            csvWriter.writeLine(item);
         }
      }finally{
         if(null != csvWriter){
            csvWriter.Close();
         }
      }
   }

   public void setExecuteAction(String action){
      _executeAction = action;
   }

   public void setExecutePlsql(String plsql){
      _executePlsql = plsql;
   }

   public void setFileName(String fileName){
      _fileName = fileName;
   }

   public void setHeadLines(int lines){
      _headLines = lines;
   }

   public void store(){
      saveFile(_fileName);
   }
}
