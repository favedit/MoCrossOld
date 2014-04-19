package org.mo.web.core.container.common;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>表单字典。</T>
//============================================================
public class FWebContainerMap
      extends FDictionary<FWebContainerItem>
{
   //============================================================
   // <T>构造表单字典。</T>
   //============================================================
   public FWebContainerMap(){
      super(FWebContainerItem.class);
   }
}
