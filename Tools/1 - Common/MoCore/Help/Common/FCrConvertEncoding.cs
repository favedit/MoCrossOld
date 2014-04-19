using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;
using MO.Common.IO;

namespace MO.Core.Help.Parser.CSharp
{
   public class FCrConvertEncoding
   {
      //============================================================
      // <T>将utf-8格式的字符串以utf-8编码格式读取。</T>
      // @param convert 读取的字符串
      // @return 转换后的字符串
      //============================================================
      public static  string ConvertToUTF8(string convert) {
         byte[] b = System.Text.Encoding.UTF8.GetBytes(convert);
         return System.Text.Encoding.UTF8.GetString(b);
      }

      //============================================================
      // <T>将unicode格式的字符串以unicode编码格式读取。</T>
      // @param convert 读取的字符串
      // @return 转换后的字符串
      //============================================================
      public static string ConvertToUnicode(string convert) {
         byte[] b = Encoding.Unicode.GetBytes(convert);
         return System.Text.Encoding.Unicode.GetString(b);
      }

      //============================================================
      // <T>将bigEndianUnicode格式的字符串以bigEndianUnicode编码格式读取。</T>
      // @param convert 读取的字符串
      // @return 转换后的字符串
      //============================================================
      public static string ConvertToBigEndianUnicode(string convert) {
         byte[] b = Encoding.BigEndianUnicode.GetBytes(convert);
         return Encoding.BigEndianUnicode.GetString(b);
      }

      //============================================================
      // <T>将GB2312格式的字符串以GB2312编码格式读取。</T>
      // @param convert 读取的字符串
      // @return 转换后的字符串
      //============================================================
      public static string ConvertToGB2312(string convert) {
         byte[] b = Encoding.GetEncoding("GB2312").GetBytes(convert);
         return Encoding.GetEncoding("GB2312").GetString(b);
      }

      //============================================================
      // <T>读取一个文件并返回文件属于的编码格式。</T>
      // @param file 要读取的文件
      // @return 文件的编码格式
      //============================================================
      public static Encoding GetType(FileInfo file) {
         FileStream fs = new FileStream(file.FullName, FileMode.Open, FileAccess.Read);
         Encoding r = GetType(fs);
         fs.Close();
         return r;
      }

      //============================================================
      // <T>将文件转换成utf-8的编码格式。</T>
      // @param file 要转换的文件
      // @param coding 文件原有的编码格式
      //============================================================
      public static void ConvertToUTF8(FileInfo file, Encoding coding) {
         FileStream fs = new FileStream(file.FullName, FileMode.Open, FileAccess.ReadWrite);
         byte[] b = new byte[fs.Length];
         int n = (int)fs.Length;
         fs.Read(b, 0, (int)fs.Length);
         fs.Close();
         byte[] bo = System.Text.Encoding.Convert(coding, Encoding.UTF8, b);
         FileStream fso = new FileStream(file.FullName, FileMode.Create, FileAccess.Write);
         fso.Write(bo, 0, bo.Count());
         fso.Flush();
         fso.Close();
      }

      //============================================================
      // <T>根据文件流获取文件的编码格式。</T>
      // @param fs 要获取的文件流
      // @return 文件流的编码格式 
      //============================================================
      public static Encoding GetType(FileStream fs) {
         byte[] Unicode = new byte[] { 0xFF, 0xFE, 0x41 };
         byte[] UnicodeBIG = new byte[] { 0xFE, 0xFF, 0x00 };
         byte[] UTF8 = new byte[] { 0xEF, 0xBB, 0xBF }; //带BOM
         Encoding reVal = Encoding.Default;
         BinaryReader r = new BinaryReader(fs, System.Text.Encoding.Default);
         int i;
         int.TryParse(fs.Length.ToString(), out i);
         byte[] ss = r.ReadBytes(i);
         if(IsUTF8Bytes(ss) || (ss[0] == 0xEF && ss[1] == 0xBB && ss[2] == 0xBF)) {
            reVal = Encoding.UTF8;
         } else if(ss[0] == 0xFE && ss[1] == 0xFF && ss[2] == 0x00) {
            reVal = Encoding.BigEndianUnicode;
         } else if(ss[0] == 0xFF && ss[1] == 0xFE && ss[2] == 0x41) {
            reVal = Encoding.Unicode;
         }
         r.Close();
         return reVal;
      }

      //============================================================
      // <T>判断是否为UTF-8的比阿玛格式</T>
      // @param data 用以判断的字节数组
      // @return true表示为utf-8格式，false表示不是utf-8格式
      //============================================================
      private static bool IsUTF8Bytes(byte[] data) {
         int charByteCounter = 1;　 //计算当前正分析的字符应还有的字节数
         byte curByte; //当前分析的字节.
         for(int i = 0; i < data.Length; i++) {
            curByte = data[i];
            if(charByteCounter == 1) {
               if(curByte >= 0x80) {
                  //判断当前
                  while(((curByte <<= 1) & 0x80) != 0) {
                     charByteCounter++;
                  }
                  //标记位首位若为非0 则至少以2个1开始 如:110XXXXX...........1111110X　
                  if(charByteCounter == 1 || charByteCounter > 6) {
                     return false;
                  }
               }
            } else {
               //若是UTF-8 此时第一位必须为1
               if((curByte & 0xC0) != 0x80) {
                  return false;
               }
               charByteCounter--;
            }
         }
         if(charByteCounter > 1) {
            throw new Exception("非预期的byte格式");
         }
         return true;
      }

   }
}
