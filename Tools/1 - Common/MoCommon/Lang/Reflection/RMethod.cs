using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;

namespace MO.Common.Lang.Reflection
{
   ///============================================================
   /// <summary>函数工具类。</summary>
   ///============================================================
   public static class RMethod
   {
      public static void EnumInvokeMethods(object[] values, string method, params object[] args) {
         foreach (object value in values) {
            if (null != value) {
               MethodInfo mi = value.GetType().GetMethod(method);
               if (null != mi) {
                  mi.Invoke(value, args);
               }
            }
         }
      }

      public static void EnumExecuteMethods(object[] values, HExecute execute, params object[] args) {
         foreach (object value in values) {
            if (null != value) {
               execute(value);
            }
         }
      }
   }
}
