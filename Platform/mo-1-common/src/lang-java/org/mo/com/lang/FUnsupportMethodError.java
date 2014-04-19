package org.mo.com.lang;

//============================================================
// <T>未实现函数错误。</T>
//============================================================
public class FUnsupportMethodError
      extends FFatalError
{
   // 序列化标识
   private static final long serialVersionUID = 1L;

   //============================================================
   // <T>构造未实现函数错误。</T>
   //============================================================
   public FUnsupportMethodError(){
      super("Unsupport method.");
   }
}
