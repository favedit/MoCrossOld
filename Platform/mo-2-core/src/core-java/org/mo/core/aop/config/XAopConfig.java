package org.mo.core.aop.config;

import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.validator.RStringValidator;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP设置节点。</T>
//============================================================
public class XAopConfig
      extends XAbsAopNode
{
   // 标签名称
   public static final String TAG_NAME = "Config";

   // 属性名称定义
   public static final String PTY_NAME = "name";

   // 属性类定义
   public static final String PTY_CLASS = "class";

   // 属性集合定义
   public static final String PTY_COLLECTION = "collection";

   // 属性条件定义
   public static final String PTY_CONDITION = "condition";

   // 名称
   protected String _name;

   // 类对象
   protected FClass<?> _class;

   // 集合
   protected FClass<?> _collection;

   // 条件
   protected String _condition;

   //============================================================
   // <T>构造AOP设置节点。</T>
   //============================================================
   public XAopConfig(){
   }

   //============================================================
   // <T>获得键内容。</T>
   //
   // @return 键内容
   //============================================================
   @Override
   public String key(){
      return _name;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FClass<?> clazz(){
      return _class;
   }

   //============================================================
   // <T>获得集合类对象。</T>
   //
   // @return 集合类对象
   //============================================================
   public FClass<?> collection(){
      return _collection;
   }

   //============================================================
   // <T>获得条件。</T>
   //
   // @return 条件
   //============================================================
   public String condition(){
      return _condition;
   }

   //============================================================
   // <T>创建对象。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public <V extends IAopNode> V createInstance(){
      return (V)_class.newInstance();
   }

   //============================================================
   // <T>创建集合。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public <V extends IAopNodes<IAopNode>> V createCollection(){
      return (V)_collection.newInstance();
   }

   //============================================================
   // <T>加载XML设置信息。</T>
   //
   // @param xconfig 设置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      super.loadConfig(config);
      // 获得名称
      _name = RString.trim(config.get(PTY_NAME));
      RStringValidator.checkEmpty(_name, PTY_NAME);
      // 获得类对象
      String clazz = RString.trim(config.get(PTY_CLASS));
      _class = RClass.find(clazz);
      // 获得集合对象
      String collection = RString.trim(config.get(PTY_COLLECTION));
      if(!RString.isEmpty(collection)){
         _collection = RClass.find(collection);
      }else{
         _collection = RClass.find(XAopNodeCollection.class);
      }
      // 获得条件
      _condition = RString.trim(config.getNvl(PTY_CONDITION));
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" [");
      info.append(" name=");
      info.append(_name);
      info.append(" class=");
      info.append(_class);
      info.append(" collection=");
      info.append(_collection);
      info.append(" condition=");
      info.append(_condition);
      info.append(" ]");
      return info;
   }
}
