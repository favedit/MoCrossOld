package org.mo.core.aop.config;

//============================================================
// <T>AOP释放集合。</T>
//============================================================
public class XAopReleaseCollection
      extends XAopNodeCollection<XAopRelease>
{
   //============================================================
   // <T>构造AOP释放集合。</T>
   //============================================================
   public XAopReleaseCollection(){
      super(XAopRelease.class);
      _duplicate = true;
   }
}
