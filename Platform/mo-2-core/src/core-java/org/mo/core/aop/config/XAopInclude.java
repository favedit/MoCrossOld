package org.mo.core.aop.config;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;

//============================================================
// <T>AOP包含。</T>
//============================================================
public class XAopInclude
      extends XAbsAopMethod
{
   // 标签名称
   public static final String TAG_NAME = "Include";

   // 条件
   private String _condition;

   // 来源
   private String _source;

   //============================================================
   // <T>构造AOP包含。</T>
   //============================================================
   public XAopInclude(){
   }

   //============================================================
   // <T>获得主键。</T>
   //
   // @return 主键
   //============================================================
   @Override
   public String key(){
      return _source;
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
   // <T>获得来源。</T>
   //
   // @return 来源
   //============================================================
   public String source(){
      return _source;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      _source = RString.trim(config.text());
      if(RString.isEmpty(_source)){
         throw new FFatalError("Source is null");
      }
      RAop.configConsole().loadFile(_source);
      _condition = RString.trim(config.getNvl("condition"));
   }
}
