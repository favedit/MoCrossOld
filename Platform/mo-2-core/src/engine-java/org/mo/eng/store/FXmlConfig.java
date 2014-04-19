package org.mo.eng.store;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodeDictionary;
import org.mo.com.xml.RXmlObject;

public class FXmlConfig
{
   private static ILogger _logger = RLogger.find(FXmlConfig.class);

   protected FXmlDocument _document;

   protected FXmlNode _config;

   protected Object _instance;

   protected IXmlConfigConvert _convert;

   //   private long _lastModified;
   protected FXmlConfigMeta _meta = new FXmlConfigMeta();

   //private long _priorModified;
   public FXmlConfig(){
   }

   public FXmlConfig(IXmlConfigConvert convert){
      _convert = convert;
   }

   public FXmlNode config(){
      return _config;
   }

   public FXmlDocument document(){
      return _document;
   }

   public void free(){
      _document = null;
      _config = null;
      _instance = null;
   }

   public Object instance(){
      return _instance;
   }

   //   public FXmlNode find(String type,
   //                        String name){
   //      if(type != null && name != null){
   //         type = type.toLowerCase();
   //         name = name.toLowerCase();
   //         //         FXmlNodeMap nodes = _rows.get(type);
   //         //         if(nodes != null){
   //         //            return nodes.get(name);
   //         //         }
   //      }
   //      return null;
   //   }
   public boolean isLoaded(){
      return (null != _document);
   }

   public void load(){
      String fileName = _meta.fileName();
      _document = new FXmlDocument(fileName);
      if(_logger.debugAble()){
         _logger.debug(this, "load", "Load xml config [{1}]", fileName);
      }
      _config = _document.root();
      _config.set("name", _meta.name());
      _instance = _convert.convertNodeToInstance(_config);
   }

   //   public long lastModified(){
   //      return _lastModified;
   //   }
   public FXmlConfigMeta meta(){
      return _meta;
   }

   public FXmlNodeDictionary nodesMap(){
      //return _rows;
      return null;
   }

   //   public FXmlNode modify(String name,
   //                          FXmlNode node){
   //      FXmlNode exists = null;
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeMap nodes = syncNodes(node.name());
   //         exists = nodes.get(name);
   //         if(exists == null){
   //            throw new FFatalError("Node is not exists. [{1}]", node.dump());
   //         }
   //         nodes.set(name, node);
   //         _lastModified = System.currentTimeMillis();
   //      }
   //      return exists;
   //   }
   public void setConfig(FXmlNode config){
      _config = config;
   }

   //   public void persist(String name,
   //                       FXmlNode node){
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeMap nodes = syncNodes(node.name());
   //         if(nodes.contains(name)){
   //            throw new FFatalError("Node is exists. [{1}]", node.dump());
   //         }
   //         nodes.set(name, node);
   //         _lastModified = System.currentTimeMillis();
   //      }
   //   }
   //   public FXmlNode remove(String name,
   //                          FXmlNode node){
   //      FXmlNode exists = null;
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeMap nodes = syncNodes(node.name());
   //         exists = nodes.get(name);
   //         if(exists == null){
   //            throw new FFatalError("Node is not exists. [{1}]", node.dump());
   //         }
   //         nodes.remove(name);
   //         _lastModified = System.currentTimeMillis();
   //      }
   //      return exists;
   //   }
   public void setInstance(Object instance){
      _instance = instance;
   }

   public void store(){
      if(null != _instance){
         _config = _convert.convertInstanceToNode(_instance);
         _document = new FXmlDocument(_config);
      }else{
         _document = new FXmlDocument();
         _config = _document.root();
      }
      // 不保存控件类型
      _document.root().removeAttribute(RXmlObject.OBJECT_TYPE);
      for(FXmlNode node : _document.root().allNodes()){
         node.removeAttribute(RXmlObject.OBJECT_TYPE);
      }
      // 保存文件
      _document.saveFile(_meta.fileName());
   }
   //   public void setLastModified(long lastModified){
   //      _priorModified = lastModified;
   //      _lastModified = lastModified;
   //   }
   //   public FXmlNode synchronize(String name,
   //                               FXmlNode node){
   //      FXmlNode exists = null;
   //      if(null != name){
   //         name = name.toLowerCase();
   //         FXmlNodeMap nodes = syncNodes(node.name());
   //         exists = nodes.get(name);
   //         nodes.set(name, node);
   //      }
   //      return exists;
   //   }
   //   protected FXmlNodeMap syncNodes(String type){
   //      //      if(type != null){
   //      //         type = type.toLowerCase();
   //      //         FXmlNodeMap nodes = _rows.get(type);
   //      //         if(nodes == null){
   //      //            nodes = new FXmlNodeMap();
   //      //            _rows.set(type, nodes);
   //      //         }
   //      //         return nodes;
   //      //      }
   //      return null;
   //   }
   //   public boolean testModified(){
   //      return _lastModified != _priorModified;
   //   }
}
