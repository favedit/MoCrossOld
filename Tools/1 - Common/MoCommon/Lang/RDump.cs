using System;
using MO.Common.Lang.Reflection;

namespace MO.Common.Lang
{
   //============================================================
   // <T>调试工具类。</T>
   //============================================================
   public class RDump
   {
      // 转储分隔行1
      public static String LINE_L1 = RString.Repeat('.', 60);

      // 转储分隔行2
      public static String LINE_L2 = RString.Repeat('-', 60);

      // 转储分隔行3
      public static String LINE_L3 = RString.Repeat('=', 60);

      // 转储分隔行4
      public static String LINE_L4 = RString.Repeat('*', 60);

      // 转储分隔符
      public const string SPACE = "   ";

      //============================================================
      // <T>追加开始调试信息。</T>
      //
      // @param info 转储信息
      //============================================================
      public static void StartDump(FDumpInfo info) {
         AppendSpace(info, info.Deep);
         AppendClass(info, info.Level, info.Instance);
      }

      //============================================================
      // <T>追加结束调试信息。</T>
      //
      // @param info 转储信息
      //============================================================
      public static void StopDump(FDumpInfo info) {
      }

      //=========================================  ===================
      // <T>追加调试信息。</T>
      //
      // @param info 转储信息        
      // @return 转储信息
      //============================================================
      public static void Append(FString dump, EDumpLevel level, int deep, object refer) {
         AppendSpace(dump, deep);
         AppendClass(dump, level, refer);
      }

      //============================================================
      // <T>追加制表符。</T>
      //
      // @param dump 转储信息
      // @param deep 制表深度
      //============================================================
      public static void AppendSpace(FString dump, int deep) {
         dump.AppendRepeat(SPACE, deep);
      }

      //============================================================
      // <T>追加类的转储信息。</T>
      //
      // @param dump 转储信息
      // @param level 转储级别
      // @param refer 引用对象
      //============================================================
      public static void AppendClass(FString dump, EDumpLevel level, object refer) {
         if(level >= EDumpLevel.Normal) {
            RClass.Dump(dump, refer);
            dump.Append(' ');
         }
      }
   }
}
