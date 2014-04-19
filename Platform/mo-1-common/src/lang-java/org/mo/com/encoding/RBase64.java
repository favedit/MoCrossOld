/*
 * @(#)FBase64.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.com.encoding;

import java.io.UnsupportedEncodingException;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;

//============================================================
// <T>BASE64编码器。</T>
//============================================================
public class RBase64
{
   // 编码字符集合
   private static char[] ENCODE_CHARS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
         'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

   // 解码字节集合
   private static byte[] DECODE_CHARS = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
         55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43,
         44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

   //============================================================
   // <T>对字节数组进行编码。</T>
   //
   // @param data 字节数组
   // @return 编码字符串
   //============================================================
   public static String encode(byte[] data){
      FString result = new FString();
      int length = data.length;
      int i = 0;
      int b1, b2, b3;
      while(i < length){
         b1 = data[i++] & 0xff;
         if(i == length){
            result.append(ENCODE_CHARS[b1 >>> 2]);
            result.append(ENCODE_CHARS[(b1 & 0x3) << 4]);
            result.append("==");
            break;
         }
         b2 = data[i++] & 0xff;
         if(i == length){
            result.append(ENCODE_CHARS[b1 >>> 2]);
            result.append(ENCODE_CHARS[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            result.append(ENCODE_CHARS[(b2 & 0x0f) << 2]);
            result.append("=");
            break;
         }
         b3 = data[i++] & 0xff;
         result.append(ENCODE_CHARS[b1 >>> 2]);
         result.append(ENCODE_CHARS[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
         result.append(ENCODE_CHARS[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
         result.append(ENCODE_CHARS[b3 & 0x3f]);
      }
      return result.toString();
   }

   //============================================================
   // <T>对字符串进行编码。</T>
   //
   // @param source 来源字符串
   // @param encode 来源编码
   // @return 编码字符串
   //============================================================
   public static String encode(String source,
                               String encode){
      try{
         return encode(source.getBytes(encode));
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>对字符串进行解码。</T>
   //
   // @param source 编码字符串
   // @return 解码字节集合
   //============================================================
   public static byte[] decode(String source){
      try{
         byte[] data = source.getBytes("US-ASCII");
         FString result = new FString();
         int len = data.length;
         int i = 0;
         int b1, b2, b3, b4;
         while(i < len){
            do{
               b1 = DECODE_CHARS[data[i++]];
            }while(i < len && b1 == -1);
            if(b1 == -1)
               break;
            do{
               b2 = DECODE_CHARS[data[i++]];
            }while(i < len && b2 == -1);
            if(b2 == -1)
               break;
            result.append((char)((b1 << 2) | ((b2 & 0x30) >>> 4)));
            do{
               b3 = data[i++];
               if(b3 == 61)
                  return result.toString().getBytes("ISO-8859-1");
               b3 = DECODE_CHARS[b3];
            }while(i < len && b3 == -1);
            if(b3 == -1)
               break;
            result.append((char)(((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
            do{
               b4 = data[i++];
               if(b4 == 61)
                  return result.toString().getBytes("ISO-8859-1");
               b4 = DECODE_CHARS[b4];
            }while(i < len && b4 == -1);
            if(b4 == -1)
               break;
            result.append((char)(((b3 & 0x03) << 6) | b4));
         }
         return result.toString().getBytes("ISO-8859-1");
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>对字符串进行解码。</T>
   //
   // @param source 编码字符串
   // @param encode 编码方式
   // @return 解码字符串
   //============================================================
   public static String decode(String source,
                               String encode){
      try{
         return new String(decode(source), encode);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
