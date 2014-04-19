package org.mo.web.protocol.context;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页配置输出器。</T>
//============================================================
public class FWebXmlOutput
      implements
         IWebOutput
{
   // 配置节点
   protected FXmlNode _config;

   // 属性集合
   protected FAttributes _parameters;

   //============================================================
   // <T>构造网页配置输出器。</T>
   //============================================================
   public FWebXmlOutput(){
   }

   //============================================================
   // <T>构造网页配置输出器。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public FWebXmlOutput(FXmlNode xconfig){
      _config = xconfig;
   }

   //============================================================
   // <T>获得设置节点。</T>
   //
   // @return 设置节点
   //============================================================
   @Override
   public FXmlNode config(){
      if(_config == null){
         _config = new FXmlNode();
      }
      return _config;
   }

   //============================================================
   // <T>获得类型。</T>
   //
   // @return 类型
   //============================================================
   public String type(){
      return _parameters.get("type");
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   @Override
   public void setName(String name){
      config().setName(name);
   }

   //============================================================
   // <T>设置属性内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   @Override
   public void set(String name,
                   String value){
      config().set(name, value);
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public FAttributes parameters(){
      if(_parameters == null){
         _parameters = new FAttributes();
         String parameters = _config.nodeText("Attributes");
         if(!RString.isEmpty(parameters)){
            _parameters.unpack(parameters);
         }
      }
      return _parameters;
   }

   //============================================================
   // <T>获得参数内容。</T>
   //
   // @param name 名称
   // @return 参数内容
   //============================================================
   public String parameter(String name){
      return parameters().get(name);
   }
}
