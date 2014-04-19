package org.mo.com.lang.cultrue;

import org.mo.com.lang.FObject;

//============================================================
// <T>国家格式。</T>
//============================================================
public class FCultureCountry
      extends FObject
{
   // 代码
   protected String _code = RCulture.DEFAULT_CODE;

   // 语言
   protected String _language = RCulture.DEFAULT_LANGUAGE;

   // 编码
   protected String _encoding = RCulture.DEFAULT_ENCODING;

   //============================================================
   // <T>构造国家格式。</T>
   //============================================================
   public FCultureCountry(){
   }

   //============================================================
   // <T>获得代码。</T>
   //
   // @return 代码
   //============================================================
   public String code(){
      return _code;
   }

   //============================================================
   // <T>设置代码。</T>
   //
   // @param code 代码
   //============================================================
   public void setCode(String code){
      _code = code;
   }

   //============================================================
   // <T>获得语言。</T>
   //
   // @return 语言
   //============================================================
   public String language(){
      return _language;
   }

   //============================================================
   // <T>设置语言。</T>
   //
   // @param language 语言
   //============================================================
   public void setLanguage(String language){
      _language = language;
   }

   //============================================================
   // <T>获得编码。</T>
   //
   // @return 编码
   //============================================================
   public String encoding(){
      return _encoding;
   }

   //============================================================
   // <T>设置编码。</T>
   //
   // @param encoding 编码
   //============================================================
   public void setEncoding(String encoding){
      _encoding = encoding;
   }

   //============================================================
   // <T>接收国家格式。</T>
   //
   // @param cultureCountry 国家格式
   //============================================================
   public void assign(FCultureCountry cultureCountry){
      _code = cultureCountry.code();
      _language = cultureCountry.language();
      _encoding = cultureCountry.encoding();
   }
   //   public void construct(FXmlNode config){
   //      _name = config.nodeText("name");
   //      _language = config.nodeText("language");
   //   }
   //
   //   /**
   //    * <p>获得当前实例运行时的内部信息</p>
   //    *
   //    * @return 运行时的内部信息
   //    */
   //   @Override
   //   public TDumpInfo dump(TDumpInfo info){
   //      info.append(RClass.dumpClass((this)));
   //      info.append(" [ Name:").append(_name);
   //      info.append(" Language:").append(_language).append(" ]");
   //      return info;
   //   }
   //
}
