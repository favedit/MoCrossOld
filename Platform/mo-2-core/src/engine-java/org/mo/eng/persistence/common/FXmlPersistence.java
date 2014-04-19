package org.mo.eng.persistence.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.eng.persistence.IPersistence;

//============================================================
// <T>配置持久化对象。</T>
//============================================================
public class FXmlPersistence
      extends FObject
      implements
         IPersistence
{
   // 持久化配置集合
   protected FXmlPersistenceConfigs _configs = new FXmlPersistenceConfigs();

   //============================================================
   // <T>构造配置持久化对象。</T>
   //============================================================
   public FXmlPersistence(){
   }

   //============================================================
   // <T>构造配置持久化对象。</T>
   //============================================================
   public IXmlObject createObject(FXmlNode config,
                                  EXmlConfig type){
      String name = config.name();
      FXmlPersistenceConfig pc = _configs.find(name);
      if(null == pc){
         throw new FFatalError("Unknown xml object: (name={1}, config={2})", name, config.xml());
      }
      IXmlObject instance = null;
      try{
         instance = pc.newInstance();
         instance.loadConfig(config, type);
      }catch(Exception e){
         throw new FFatalError(e, "Create instance failure. (config={1}, xml={2})", pc.dump(), config.dump());
      }
      return instance;
   }

   //============================================================
   // <T>加载配置信息。</T>
   //
   // @param xconfig 配置信息
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      for(FXmlNode node : config.nodes()){
         if(node.isName("Component")){
            FXmlPersistenceConfig pc = new FXmlPersistenceConfig(node);
            _configs.set(pc.name(), pc);
         }
      }
   }

   //============================================================
   // <T>加载配置信息。</T>
   //
   // @param xconfig 配置
   // @param typeCd 类型
   //============================================================
   public IXmlObject loadObject(FXmlNode xconfig,
                                EXmlConfig typeCd){
      return loadObject(null, xconfig, typeCd);
   }

   //============================================================
   // <T>加载配置对象。</T>
   //
   // @param instance 实例
   // @param xconfig 配置
   // @param typeCd 类型
   //============================================================
   public IXmlObject loadObject(IXmlObject instance,
                                FXmlNode xconfig,
                                EXmlConfig typeCd){
      if(null == instance){
         instance = createObject(xconfig, typeCd);
      }
      if(xconfig.hasNode()){
         for(FXmlNode node : xconfig.nodes()){
            IXmlObject child = createObject(node, typeCd);
            if(node.hasNode()){
               loadObject(child, node, typeCd);
            }
            instance.children().push(child);
         }
      }
      return instance;
   }

   //============================================================
   // <T>存储配置对象。</T>
   //
   // @param instance 实例
   // @param typeCd 类型
   //============================================================
   public FXmlNode saveObject(IXmlObject instance,
                              EXmlConfig typeCd){
      FXmlDocument xdoc = new FXmlDocument();
      FXmlNode config = xdoc.root();
      saveObject(xdoc, instance, config, typeCd);
      return config;
   }

   //============================================================
   // <T>存储配置对象。</T>
   //
   // @param xdocument 文档
   // @param instance 实例
   // @param xconfig 配置
   // @param typeCd 类型
   //============================================================
   public void saveObject(FXmlDocument xdocument,
                          IXmlObject instance,
                          FXmlNode xconfig,
                          EXmlConfig typeCd){
      instance.saveConfig(xconfig, typeCd);
      IXmlObjects children = instance.children();
      int count = children.count();
      for(int n = 0; n < count; n++){
         IXmlObject child = children.get(n);
         if(null != child){
            // 创建节点类型
            FXmlNode node = null;
            if(!RString.isEmpty(child.innerText())){
               node = xdocument.createDataNode(child.name());
            }else{
               node = xdocument.createNode(child.name());
            }
            // 保存数据对象
            saveObject(xdocument, child, node, typeCd);
            xconfig.push(node);
         }
      }
   }
}
