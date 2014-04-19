package org.mo.core.aop.config;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP参数。</T>
//============================================================
public class XAopParameter
      extends XAbsAopNode
{
   // 标签名称
   public static String TAG_NAME = "Parameter";

   // 属性名称
   public static final String PTY_NAME = "name";

   // 属性类型
   public static final String PTY_TYPE = "type";

   // 类型设置
   public static String TYPE_CONFIG = "config";

   // 名称
   protected String _name;

   // 类型
   protected String _type;

   // 对象
   protected Object _value;

   //============================================================
   // <T>构造AOP参数。</T>
   //============================================================
   public XAopParameter(){
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
   // <T>判断是否设置。</T>
   //
   // @return 是否设置
   //============================================================
   public boolean isConfig(){
      return TYPE_CONFIG.equalsIgnoreCase(_type);
   }

   //============================================================
   // <T>判断是否组件。</T>
   //
   // @return 是否组件
   //============================================================
   public boolean isComponent(){
      return XAopComponent.TAG_NAME.equalsIgnoreCase(_type);
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
   public void loadConfig(FXmlNode xconfig){
      // 读取属性
      super.loadConfig(xconfig);
      try{
         _name = RString.trim(xconfig.get("name", null));
         _type = RString.trim(xconfig.get("type", null));
         _value = null;
         // 读取组件
         if(xconfig.hasNode()){
            for(FXmlNode node : xconfig.nodes()){
               if(node.isName(XAopComponent.TAG_NAME)){
                  XAopComponent xcomponent = new XAopComponent();
                  xcomponent.loadConfig(node);
                  _type = XAopComponent.TAG_NAME;
                  _value = xcomponent;
                  break;
               }else if(isConfig()){
                  _value = xconfig;
               }
            }
         }
         // 读取内容
         if(null == _value){
            _value = RString.trim(xconfig.text());
         }
      }catch(Throwable e){
         throw new FFatalError(e, "Load config failure. (config={1})", xconfig);
      }
   }
}
