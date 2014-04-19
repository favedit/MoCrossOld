package org.mo.core.aop.config;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP监听器。</T>
//============================================================
public class XAopListener
      extends XAbsAopMethod
{
   // 标签名称
   public static final String TAG_NAME = "Listener";

   // 属性类名称
   public static final String ATTR_CLASS = "class";

   // 属性接口名称
   public static final String ATTR_FACE = "face";

   // 类名称
   private String _className;

   // 接口名称
   private String _face;

   //============================================================
   // <T>构造AOP监听器。</T>
   //============================================================
   public XAopListener(){
   }

   //============================================================
   // <T>获得类名称。</T>
   //
   // @return 类名称
   //============================================================
   public String className(){
      return _className;
   }

   //============================================================
   // <T>获得接口名称。</T>
   //
   // @return 接口名称
   //============================================================
   public String faceName(){
      return _face;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      _face = RString.trim(config.get("face"));
      _className = RString.trim(config.get("class"));
   }
}
