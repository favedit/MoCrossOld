package org.mo.com.lang;

//============================================================
// <T>对象编号。</T>
//============================================================
public class RObjectId
{
   // 整数编号
   private static int _intId = 0;

   // 长整数编号
   private static long _longId = 0;

   //============================================================
   // <T>获得下一个整数编号。</T>
   //
   // @return 整数编号
   //============================================================
   public static String nextId(){
      return Integer.toHexString(Math.abs(_intId++));
   }

   //============================================================
   // <T>获得下一个长整数编号。</T>
   //
   // @return 长整数编号
   //============================================================
   public static String nextLongId(){
      return Long.toHexString(Math.abs(_longId++));
   }
}
