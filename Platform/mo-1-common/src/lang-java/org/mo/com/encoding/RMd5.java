package org.mo.com.encoding;

import java.security.MessageDigest;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RByte;

//============================================================
// <T>MD5编码器。</T>
//
// @history 090704 MAOCY 创建
//============================================================
public class RMd5
{
   // 代码
   public static final String CODE = "MD5";

   //============================================================
   // <T>加密一个字符串，返回加密后的16进制的字符串。</T>
   //
   // @param source 要加密的字符串
   // @return 16进制的字符串
   //============================================================
   public static String encode(String source){
      try{
         // 创建服务实例
         MessageDigest provider = MessageDigest.getInstance(CODE);
         // 转换数组为字符串
         return RByte.toHexString(provider.digest(source.getBytes()));
      }catch(Exception e){
         throw new FFatalError(e);
      }
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
         // 加密后，返回字节数组
         return provider.digest(source.getBytes());
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
