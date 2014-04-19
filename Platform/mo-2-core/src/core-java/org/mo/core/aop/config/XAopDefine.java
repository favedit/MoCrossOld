package org.mo.core.aop.config;

import org.mo.com.lang.RString;
import org.mo.com.validator.RStringValidator;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP设置定义。</T>
//============================================================
public class XAopDefine
      extends XAbsAopNode
{
   // 标签名称
   public static final String TAG_NAME = "Define";

   // 属性名称
   public static final String PTY_NAME = "name";

   // 名称
   private String _name;

   // 内容
   private String _value;

   // 条件
   private String _condition;

   //============================================================
   // <T>构造AOP设置定义。</T>
   //============================================================
   public XAopDefine(){
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
   // <T>获得内容。</T>
   //
   // @return 内容
   //============================================================
   public String value(){
      return _value;
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
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      super.loadConfig(config);
      // 获得名称
      _name = RString.trim(config.get(PTY_NAME));
      RStringValidator.checkEmpty(_name, PTY_NAME);
      // 获得内容
      _value = RString.trim(config.text());
      // 获得条件
      _condition = RString.trim(config.getNvl("condition"));
   }
}
