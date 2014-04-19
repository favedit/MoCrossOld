package org.mo.com.lang.cultrue;

import java.io.UnsupportedEncodingException;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>编码定义。</T>
//============================================================
public class FEncoding
{
   // 编码
   protected String _encoding;

   //============================================================
   // <T>构造编码定义。</T>
   //
   // @param encoding 编码
   //============================================================
   public FEncoding(String encoding){
      _encoding = encoding;
   }

   //============================================================
   // <T>转换字节数组为字符串。</T>
   //
   // @param data 字节数组
   // @return 字符串
   //============================================================
   public String covertString(byte[] data){
      try{
         return new String(data, _encoding);
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>转换字符串为字节数组。</T>
   //
   // @param data 字符串
   // @return 字节数组
   //============================================================
   public byte[] covertBytes(String text){
      try{
         return text.getBytes(_encoding);
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 字符串
   //============================================================
   @Override
   public String toString(){
      return _encoding;
   }
}
