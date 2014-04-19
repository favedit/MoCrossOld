package org.mo.game.editor.core.dataset;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.data.ESqlDatabaseType;
import org.mo.com.data.ISqlConnect;
import org.mo.com.lang.FString;
import org.mo.com.xml.FXmlNode;

public class FDatasetBuilderArgs{

   private EDatasetBuildAction _action;

   private FObjectDictionary _attributes;

   private ESqlDatabaseType _databaseType;

   private Object _instance;

   private FXmlNode _config;

   private String _name;

   private String _dataGroup;

   private FString _source;

   private ISqlConnect _sqlConnect;

   private EDatasetSourceType _type;

   public FDatasetBuilderArgs(ISqlConnect sqlConnect){
      _sqlConnect = sqlConnect;
   }

   //   public FDatasetBuilderArgs(Object instance){
   //      _instance = instance;
   //   }

   public FDatasetBuilderArgs(Object instance,
                              EDatasetBuildAction action,
                              EDatasetSourceType type){
      _instance = instance;
      _action = action;
      _type = type;
   }

   public EDatasetBuildAction action(){
      return _action;
   }

   public FObjectDictionary attributes(){
      if(null == _attributes){
         _attributes = new FObjectDictionary();
      }
      return _attributes;
   }

   public ESqlDatabaseType databaseType(){
      return _databaseType;
   }

   public String dataGroup(){
      return _dataGroup;
   }

   @SuppressWarnings("unchecked")
   public <V> V instance(){
      return (V) _instance;
   }

   public FXmlNode config(){
      return _config;
   }

   
   public void setConfig(FXmlNode config){
      _config = config;
   }

   public String name(){
      return _name;
   }

   public void setAction(EDatasetBuildAction action){
      _action = action;
   }

   public void setDatabaseType(ESqlDatabaseType databaseType){
      _databaseType = databaseType;
   }

   public void setDataGroup(String dataGroup){
      _dataGroup = dataGroup;
   }

   public void setInstance(Object instance){
      _instance = instance;
   }

   public void setName(String name){
      _name = name;
   }

   public void setSource(FString source){
      _source = source;
   }

   public void setSqlConnect(ISqlConnect sqlConnect){
      _sqlConnect = sqlConnect;
   }

   public void setType(EDatasetSourceType type){
      _type = type;
   }

   public FString source(){
      return _source;
   }

   public ISqlConnect sqlConnect(){
      return _sqlConnect;
   }

   public EDatasetSourceType type(){
      return _type;
   }
}
