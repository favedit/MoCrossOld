package org.mo.eng.persistence.common;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>持久化配置集合。</T>
//============================================================
public class FXmlPersistenceConfigs
      extends FDictionary<FXmlPersistenceConfig>
{
   //============================================================
   // <T>构造持久化配置集合。</T>
   //============================================================
   public FXmlPersistenceConfigs(){
      super(FXmlPersistenceConfig.class);
   }
}
