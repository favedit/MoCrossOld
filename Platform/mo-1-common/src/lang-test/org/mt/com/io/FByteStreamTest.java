package org.mt.com.io;

import org.mo.com.io.FByteStream;
import org.mo.com.io.base.MByteStream;

public class FByteStreamTest
{
   public static void main(String[] args){
      MByteStream bt = new FByteStream();
      // short to byte
      //		short s = -335;
      //		bt.writeInt16(s);
      //		System.out.println(byteToShort(bt._memory));
      // int to byte
      //		int s = 858520;
      //		bt.writeUint32(s);
      //		System.out.println(byteToInt(bt._memory));
      // long to byte
      //long l = 45645655;
      //bt.writeInt64(l);
      System.out.println(byteToLong(bt.memory()));
   }

   public static short byteToShort(byte[] b){
      short s = 0;
      short s0 = (short)(b[0] & 0xff);// 最低位 
      short s1 = (short)(b[1] & 0xff);
      s1 <<= 8;
      s = (short)(s0 | s1);
      return s;
   }

   public static int byteToInt(byte[] b){
      int s = 0;
      int s0 = b[0] & 0xff;// 最低位 
      int s1 = b[1] & 0xff;
      int s2 = b[2] & 0xff;
      int s3 = b[3] & 0xff;
      s3 <<= 24;
      s2 <<= 16;
      s1 <<= 8;
      s = s0 | s1 | s2 | s3;
      return s;
   }

   public static long byteToLong(byte[] b){
      long s = 0;
      long s0 = b[0] & 0xff;// 最低位 
      long s1 = b[1] & 0xff;
      long s2 = b[2] & 0xff;
      long s3 = b[3] & 0xff;
      long s4 = b[4] & 0xff;// 最低位 
      long s5 = b[5] & 0xff;
      long s6 = b[6] & 0xff;
      long s7 = b[7] & 0xff;
      // s0不变 
      s1 <<= 8;
      s2 <<= 16;
      s3 <<= 24;
      s4 <<= 8 * 4;
      s5 <<= 8 * 5;
      s6 <<= 8 * 6;
      s7 <<= 8 * 7;
      s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
      return s;
   }
}
