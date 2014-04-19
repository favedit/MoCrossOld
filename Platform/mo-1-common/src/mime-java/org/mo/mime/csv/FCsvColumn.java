package org.mo.mime.csv;

import org.mo.com.lang.RInteger;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;

public class FCsvColumn
{
   private static ILogger _logger = RLogger.find(FCsvColumn.class);

   public final static String HEAD_TYPE = "Head";

   public final static String PTY_DESCRIPTION = "description";

   public final static String PTY_ID = "id";

   public final static String PTY_NAME = "name";

   public final static String PTY_LABEL = "label";

   public final static String PTY_SIZE = "size";

   private String _description;

   private boolean _isValid;

   private int _id;

   private String _label;

   private String _name;

   private String _dataName;

   private boolean _isRequire;

   private int _size;

   public String dataName(){
      return _dataName;
   }

   public String description(){
      return _description;
   }

   public int id(){
      return _id;
   }

   public boolean isRequire(){
      return _isRequire;
   }

   public boolean isValid(){
      return _isValid;
   }

   public String label(){
      return _label;
   }

   public void LoadConfig(FXmlNode config){
      if(config.contains(PTY_ID)){
         _id = RInteger.parse(config.get(PTY_ID));
      }
      if(config.contains(PTY_NAME)){
         _name = config.get(PTY_NAME);
      }
      if(config.contains(PTY_SIZE)){
         _size = RInteger.parse(config.get(PTY_SIZE));
      }
      if(config.contains(PTY_DESCRIPTION)){
         _description = config.get(PTY_DESCRIPTION);
      }
      // Dump
      if(_logger.debugAble()){
         _logger.debug(this, "LoadConfig", "Load head");
      }
   }

   public String name(){
      return _name;
   }

   public void setDataName(String dataName){
      _dataName = dataName;
   }

   public void setDescription(String _description){
      this._description = _description;
   }

   public void setId(int id){
      _id = id;
   }

   public void setIsRequire(boolean _isRequire){
      this._isRequire = _isRequire;
   }

   public void setIsValid(boolean _isValid){
      this._isValid = _isValid;
   }

   public void setLabel(String label){
      _label = label;
   }

   public void setName(String name){
      _name = name;
   }

   public void setSize(int _size){
      this._size = _size;
   }

   public int size(){
      return _size;
   }
}
