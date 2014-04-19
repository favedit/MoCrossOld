package org.mo.com.encoding;

import java.security.MessageDigest;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RByte;
import org.mo.com.lang.RString;

//============================================================
// <T>SHA-1编码器。</T>
//
// @history 090704 MAOCY 创建
//============================================================
public class RSha1
{
   // 代码
   public static final String CODE = "SHA-1";

   // 字符代码
   public static final String CODE_CHAR = "ISO-8859-1";

   //============================================================
   // <T>加密一个字符串，返回加密后的16进制的字符串。</T>
   //
   // @param source 要加密的字符串
   // @return 16进制的字符串
   //============================================================
   public static String encode(String source){
      if(!RString.isEmpty(source)){
         try{
            // 创建服务实例
            MessageDigest provider = MessageDigest.getInstance(CODE);
            provider.update(source.getBytes(CODE_CHAR));
            // 转换数组为字符串
            return RByte.toHexString(provider.digest());
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
      return null;
   }

   //============================================================
   // <T>加密一个字符串，返回加密后的字节数组。</T>
   //
   // @param source 要加密的字符串
   // @return 字节数组
   //============================================================
   public static byte[] encodeBytes(String source){
      try{
         // 创建服务实例
         MessageDigest provider = MessageDigest.getInstance(CODE);
         provider.update(source.getBytes(CODE_CHAR));
         // 加密后，返回字节数组
         return provider.digest();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
