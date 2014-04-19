package org.mo.core.bind;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>绑定信息集合。</T>
//============================================================
public class FBindInfos
      extends FDictionary<FBindInfo>
{
   //============================================================
   // <T>构造绑定信息集合。</T>
   //============================================================
   public FBindInfos(){
      super(FBindInfo.class);
   }

   //============================================================
   // <T>同步绑定信息。</T>
   //
   // @param name 名称
   // @return 绑定信息
   //============================================================
   public FBindInfo sync(String name){
      FBindInfo info = find(name);
      if(null == info){
         info = new FBindInfo();
         set(name, info);
      }
      return info;
   }
}
