package org.mo.com.io;

import java.io.InputStream;
import org.mo.com.lang.FBytes;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>构造工具类。</T>
//============================================================
public class RInput
{
   // 读取块大小
   public static int BLOCK_SIZE = 4096;

   //============================================================
   // <T>从输入流中读取字节内容。</T>
   //
   // @param input 输入流
   // @return 字节数组
   //============================================================
   public static byte[] read(InputStream input){
      FBytes bytes = new FBytes();
      try{
         byte[] buffer = new byte[BLOCK_SIZE];
         while(true){
            int read = input.read(buffer);
            if(read <= 0){
               break;
            }
            bytes.append(buffer, 0, read);
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return bytes.toArray();
   }

   //============================================================
   // <T>从输入流中读取指定长度的字节内容。</T>
   //
   // @param input 输入流
   // @param length 长度
   // @return 字节数组
   //============================================================
   public static byte[] read(InputStream input,
                             int length){
      FBytes bytes = new FBytes();
      try{
         int remain = length;
         byte[] buffer = new byte[BLOCK_SIZE];
         while(remain > 0){
            int block = (remain > BLOCK_SIZE) ? BLOCK_SIZE : remain;
            int read = input.read(buffer, 0, block);
            bytes.append(buffer, 0, read);
            remain -= read;
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return bytes.toArray();
   }
}
