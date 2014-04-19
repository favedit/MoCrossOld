package org.mo.com.lang.cultrue;

import java.io.UnsupportedEncodingException;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>编码定义。</T>
//============================================================
public abstract class REncoding
{
   // ASCII编码
   public static final FEncodingAscii ASCII = new FEncodingAscii();

   // GB2312编码
   public static final FEncodingGb2312 GB2312 = new FEncodingGb2312();

   // GBK编码
   public static final FEncodingGbk GBK = new FEncodingGbk();

   // SHIFT-JIS编码
   public static final FEncodingShiftJis ShiftJIS = new FEncodingShiftJis();

   // UTF-8编码
   public static final FEncodingUtf8 UTF8 = new FEncodingUtf8();

   //============================================================
   // <T>转变数据为指定编码的字符串。</T>
   //
   // @return 编码定义
   //============================================================
   public static String convertString(byte[] data,
                                      String charset){
      try{
         int start = 0;
         if(UTF8.hasBom(data)){
            start = FEncodingUtf8.BOM_SIZE;
         }
         return new String(data, start, data.length - start, charset);
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }
}
