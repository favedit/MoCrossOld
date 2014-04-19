package org.mo.com.lang.cultrue;

//============================================================
// <T>UTF-8编码定义。</T>
//============================================================
public class FEncodingUtf8
      extends FEncoding
{
   // 数据头大小
   public final static int BOM_SIZE = 3;

   // 数据头定义
   public final static byte[] BOM_BYTES = {(byte)0xEF, (byte)0xBB, (byte)0xBF};

   //============================================================
   // <T>构造UTF-8编码定义。</T>
   //============================================================
   public FEncodingUtf8(){
      super("UTF-8");
   }

   //============================================================
   // <T>是否含有数据头定义。</T>
   //
   // @param data 数据
   // @return 是否含有
   //============================================================
   public boolean hasBom(byte[] data){
      return (BOM_BYTES[0] == data[0]) && (BOM_BYTES[1] == data[1]) && (BOM_BYTES[2] == data[2]);
   }
}
