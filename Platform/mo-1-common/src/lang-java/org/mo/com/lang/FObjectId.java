package org.mo.com.lang;

//============================================================
// <T>编号对象。</T>
//============================================================
public class FObjectId
      extends FObject
      implements
         IObjectId
{
   // 对象编号
   private String _objectId;

   //============================================================
   //<T>构造编号对象。</T>
   //============================================================
   public FObjectId(){
   }

   //============================================================
   // <T>获得编号。</T>
   //
   // @param 编号
   //============================================================
   @Override
   public String objectId(){
      if(_objectId == null){
         _objectId = RObjectId.nextLongId();
      }
      return _objectId;
   }
}
