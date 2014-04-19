package org.mo.eng.store;

import org.mo.com.lang.FObject;

//============================================================
// <T>XML数据集合。</T>
//============================================================
public class FXmlDataset
      extends FObject
{
   // 加载标志
   protected boolean _loaded = false;

   // 信息描述
   protected FXmlDatasetMeta _meta = new FXmlDatasetMeta();

   // 行集合
   protected FXmlRows _rows = new FXmlRows();

   // 上一次修改时间
   protected long _priorModified;

   // 最后修改时间
   protected long _lastModified;

   //============================================================
   // <T>构造XML数据集合。</T>
   //============================================================
   public FXmlDataset(){
   }

   //============================================================
   // <T>获得信息描述。</T>
   //
   // @return 信息描述
   //============================================================
   public FXmlDatasetMeta meta(){
      return _meta;
   }

   //============================================================
   // <T>获得最后修改时间。</T>
   //
   // @return 最后修改时间
   //============================================================
   public long lastModified(){
      return _lastModified;
   }

   //============================================================
   // <T>测试已经修改。</T>
   //
   // @return 已经修改
   //============================================================
   public boolean testModified(){
      return _lastModified != _priorModified;
   }
   //   public FXmlNode find(String type, String name){
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
   //
   //   public FXmlNode modify(String name, FXmlNode node){
   //      FXmlNode exists = null;
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeDictionary nodes = syncNodes(node.name());
   //         exists = nodes.get(name);
   //         if(exists == null){
   //            throw new FFatalError("Node is not exists. [{0}]", node.dump());
   //         }
   //         nodes.set(name, node);
   //         _lastModified = System.currentTimeMillis();
   //      }
   //      return exists;
   //   }
   //
   //   public FXmlNodeDictionarys nodesMap(){
   //      //return _rows;
   //      return null;
   //   }
   //
   //   public void persist(String name, FXmlNode node){
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeDictionary nodes = syncNodes(node.name());
   //         if(nodes.contains(name)){
   //            throw new FFatalError("Node is exists. [{0}]", node.dump());
   //         }
   //         nodes.set(name, node);
   //         _lastModified = System.currentTimeMillis();
   //      }
   //   }
   //
   //   public FXmlNode remove(String name, FXmlNode node){
   //      FXmlNode exists = null;
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeDictionary nodes = syncNodes(node.name());
   //         exists = nodes.get(name);
   //         if(exists == null){
   //            throw new FFatalError("Node is not exists. [{0}]", node.dump());
   //         }
   //         nodes.remove(name);
   //         _lastModified = System.currentTimeMillis();
   //      }
   //      return exists;
   //   }
   //
   //   public void setLastModified(long lastModified){
   //      _priorModified = lastModified;
   //      _lastModified = lastModified;
   //   }
   //
   //   public FXmlNode synchronize(String name, FXmlNode node){
   //      FXmlNode exists = null;
   //      if(name != null){
   //         name = name.toLowerCase();
   //         FXmlNodeDictionary nodes = syncNodes(node.name());
   //         exists = nodes.get(name);
   //         nodes.set(name, node);
   //      }
   //      return exists;
   //   }
   //
   //   protected FXmlNodeDictionary syncNodes(String type){
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
}
