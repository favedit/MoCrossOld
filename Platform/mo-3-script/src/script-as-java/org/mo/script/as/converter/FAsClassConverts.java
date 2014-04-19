package org.mo.script.as.converter;

import org.mo.com.lang.FDictionary;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AS类转换器集合。</T>
//============================================================
public class FAsClassConverts
      extends FDictionary<FAsClassConvert>
{
   //============================================================
   // <T>构造AS类转换器集合。</T>
   //============================================================
   public FAsClassConverts(){
      super(FAsClassConvert.class);
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void loadConfig(FXmlNode xconfig){
      for(FXmlNode convertNode : xconfig.nodes()){
         if(convertNode.isName("Convert")){
            FAsClassConvert convert = new FAsClassConvert();
            convert.loadConfig(convertNode);
            set(convert.asClassName(), convert);
         }
      }
   }
}
