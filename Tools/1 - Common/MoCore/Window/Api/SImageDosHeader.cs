using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct IMAGE_DOS_HEADER {
    *    USHORT e_magic;    // 魔术数字
    *    USHORT e_cblp;     // 文件最后页的字节数
    *    USHORT e_cp;       // 文件页数
    *    USHORT e_crlc;     // 重定义元素个数
    *    USHORT e_cparhdr;  // 头部尺寸，以段落为单位
    *    USHORT e_minalloc; // 所需的最小附加段
    *    USHORT e_maxalloc; // 所需的最大附加段
    *    USHORT e_ss;       // 初始的SS值（相对偏移量）
    *    USHORT e_sp;       // 初始的SP值
    *    USHORT e_csum;     // 校验和
    *    USHORT e_ip;       // 初始的IP值
    *    USHORT e_cs;       // 初始的CS值（相对偏移量）
    *    USHORT e_lfarlc;   // 重分配表文件地址
    *    USHORT e_ovno;     // 覆盖号
    *    USHORT e_res[4];   // 保留字
    *    USHORT e_oemid;    // OEM标识符（相对e_oeminfo）
    *    USHORT e_oeminfo;  // OEM信息
    *    USHORT e_res2[10]; // 保留字
    *    LONG e_lfanew;     // 新exe头部的文件地址
    * } */
   [StructLayout(LayoutKind.Sequential)]
   public struct SImageDosHeader {
      public ushort e_magic;
      public ushort e_cblp;
      public ushort e_cp;
      public ushort e_crlc;
      public ushort e_cparhdr;
      public ushort e_minalloc;
      public ushort e_maxalloc;
      public ushort e_ss;
      public ushort e_sp;
      public ushort e_csum;
      public ushort e_ip;
      public ushort e_cs;
      public ushort e_lfarlc;
      public ushort e_ovno;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 4)]
      public ushort[] e_res;
      public ushort e_oemid;
      public ushort e_oeminfo;
      [MarshalAs(UnmanagedType.ByValArray, SizeConst = 10)]
      public ushort[] e_res2;
      public uint e_lfanew;
   };

}
