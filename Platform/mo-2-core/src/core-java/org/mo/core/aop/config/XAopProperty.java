package org.mo.core.aop.config;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP属性。</T>
//============================================================
public class XAopProperty
      extends XAbsAopNode
{
   // 标签名称
   public static String TAG_NAME = "Property";

   // 名称
   private String _name;

   // 类型
   private String _type;

   // 内容
   private Object _value;

   //============================================================
   // <T>AOP属性。</T>
   //============================================================
   public XAopProperty(){
   }

   //============================================================
   // <T>获得主键。</T>
   //
   // @return 主键
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
   // <T>获得类型。</T>
   //
   // @return 类型
   //============================================================
   public String type(){
      return _type;
   }

   //============================================================
   // <T>设置类型。</T>
   //
   // @param type 类型
   //============================================================
   public void setType(String type){
      _type = type;
   }

   //============================================================
   // <T>获得内容。</T>
   //
   // @return 内容
   //============================================================
   public Object value(){
      return _value;
   }

   //============================================================
   // <T>设置内容。</T>
   //
   // @param value 内容
   //============================================================
   public void setValue(Object value){
      _value = value;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      super.loadConfig(config);
      _name = RString.trim(config.get("name"));
      _type = RString.trim(config.getNvl("type"));
      _value = null;
      if(config.hasNode()){
         for(FXmlNode node : config.nodes()){
            if(node.isName(XAopComponent.TAG_NAME)){
               XAopComponent cmpCfg = new XAopComponent();
               cmpCfg.loadConfig(node);
               _value = cmpCfg;
               break;
            }
         }
      }
      if(null == _value){
         _value = RString.trim(config.text());
      }
   }
}
