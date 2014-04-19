package org.mo.core.aop.config;

//============================================================
// <T>AOP组件集合集合。</T>
//============================================================
public class XAopComponentsCollection
      extends XAopNodeCollection<XAopComponents>
{
   //============================================================
   // <T>构造AOP组件集合集合。</T>
   //============================================================
   public XAopComponentsCollection(){
      super(XAopComponents.class);
   }

   //============================================================
   // <T>根据接口匹配组件对象。</T>
   //
   // @param face 接口
   // @return 组件对象
   //============================================================
   public XAopComponent matchByFace(String face){
      for(int n = 0; n < _count; n++){
         XAopComponents xcomponents = _items[n];
         if(xcomponents.match(face)){
            return xcomponents.createComponent(face);
         }
      }
      return null;
   }
}
