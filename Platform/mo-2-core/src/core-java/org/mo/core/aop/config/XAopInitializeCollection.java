package org.mo.core.aop.config;

//============================================================
// <T>AOP初始化集合。</T>
//============================================================
public class XAopInitializeCollection
      extends XAopNodeCollection<XAopInitialize>
{
   //============================================================
   // <T>构造AOP初始化集合。</T>
   //============================================================
   public XAopInitializeCollection(){
      super(XAopInitialize.class);
      _duplicate = true;
   }
}
