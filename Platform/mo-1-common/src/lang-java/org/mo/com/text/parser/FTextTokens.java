package org.mo.com.text.parser;

import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>文本段集合。</T>
//============================================================
public class FTextTokens
      extends MObjects<FTextToken>
{
   //============================================================
   // <T>构造文本段集合。</T>
   //============================================================
   public FTextTokens(){
      super(FTextToken.class);
   }
}
