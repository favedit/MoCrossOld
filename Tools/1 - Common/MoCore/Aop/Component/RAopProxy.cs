using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Remoting.Messaging;
using MO.Common.Lang;

namespace MO.Core.Aop.Component {

   public static class RAopProxy {

      public const string PTY_ARGS = "__Args";

      public static string GetCallerInfo(IMethodCallMessage caller) {
         FDumpInfo info = new FDumpInfo();
         // Args
         info.Append("Args[");
         object[] args = caller.Args;
         if (args != null) {
            int count = args.Length;
            for (int n = 0; n < count; n++) {
               if (n > 0) {
                  info.Append(',');
               }
               info.AppendDump(args[n]);
            }
         }
         info.Append("]");
         return info.ToString();
      }

      public static string GetReturnInfo(IMethodReturnMessage result) {
         FDumpInfo info = new FDumpInfo();
         info.Append("Result[");
         info.AppendDump(result.ReturnValue);
         info.Append("]");
         return info.ToString();
      }

   }

}
