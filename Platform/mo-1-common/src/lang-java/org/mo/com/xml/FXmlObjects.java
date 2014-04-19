package org.mo.com.xml;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;

//============================================================
// <T>XML对象集合。</T>
//
// @author MAOCY
// @version 1.00 - 2008/12/08
//============================================================
public class FXmlObjects
      extends FObjects<IXmlObject>
      implements
         IXmlObjects
{
   //============================================================
   // <T>构造XML对象集合。</T>
   //============================================================
   public FXmlObjects(){
      super(IXmlObject.class);
   }

   //============================================================
   // <T>判断是否查找节点。</T>
   //
   // @param xobject 节点
   // @param values 参数集合
   // @return 是否查找节点
   //============================================================
   protected static boolean isFindChild(IXmlObject xobject,
                                        String[] values){
      int count = values.length;
      if(count == 0){
         return true;
      }else if(1 == count){
         return RString.equalsIgnoreCase(xobject.name(), values[0]);
      }else if(count % 2 == 0){
         for(int n = 0; n < count; n += 2){
            String value = xobject.innerGet(values[n]);
            if(!RString.equalsIgnoreCase(value, values[n + 1])){
               return false;
            }
         }
         return true;
      }else if(count % 2 == 1){
         if(!RString.equalsIgnoreCase(xobject.name(), values[0])){
            return false;
         }
         for(int n = 1; n < count; n += 2){
            String value = xobject.innerGet(values[n]);
            if(!RString.equalsIgnoreCase(value, values[n + 1])){
               return false;
            }
         }
         return true;
      }
      return false;
   }

   //============================================================
   // <T>根据参数集合查找节点。</T>
   //
   // @param values 参数集合
   // @return 节点
   //============================================================
   @Override
   public IXmlObject find(String... values){
      int count = count();
      for(int n = 0; n < count; n++){
         IXmlObject child = get(n);
         if(null != child){
            if(isFindChild(child, values)){
               return child;
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>根据对象编号获得节点。</T>
   //
   // @param objectId 对象编号
   // @return 节点
   //============================================================
   @Override
   public IXmlObject get(String objectId){
      if(null != objectId){
         for(int n = 0; n < _count; n++){
            if(null != _items[n]){
               if(objectId.equals(_items[n].objectId())){
                  return _items[n];
               }
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>根据对象编号查找节点。</T>
   //
   // @param objectId 对象编号
   // @return 节点
   //============================================================
   @Override
   public IXmlObject search(String objectId){
      if(null != objectId){
         for(int n = 0; n < _count; n++){
            IXmlObject object = _items[n];
            if(null != object){
               if(objectId.equals(object.objectId())){
                  return object;
               }
               if(object.hasChild()){
                  IXmlObject result = object.children().search(objectId);
                  if(null != result){
                     return result;
                  }
               }
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>根据对象编号移除节点。</T>
   //
   // @param objectId 对象编号
   // @return 节点
   //============================================================
   @Override
   public IXmlObject remove(String objectId){
      IXmlObject xchild = get(objectId);
      if(null != xchild){
         super.remove(xchild);
      }
      return xchild;
   }
}
