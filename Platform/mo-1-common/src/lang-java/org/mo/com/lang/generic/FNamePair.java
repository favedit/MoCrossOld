package org.mo.com.lang.generic;

import org.mo.com.lang.INamePair;

//============================================================
// <T>名称和内容成对的对象。</T>
//
// @history 130220 创建
//============================================================
public class FNamePair<V>
      extends FPair<String, V>
      implements
         INamePair<V>
{
   //============================================================
   // <T>构造名称和内容成对的对象。</T>
   //============================================================
   public FNamePair(){
   }
}
