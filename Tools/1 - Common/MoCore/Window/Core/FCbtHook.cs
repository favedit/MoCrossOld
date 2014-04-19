using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Core.Hook {


   public delegate bool HCbtProcessMessage(int code, IntPtr wparam, IntPtr lparam);

   public class FCbtHook  : FWindowHook {

      public FCbtHook() {
         _type = EHookType.CBT;
      }

      public event HCbtProcessMessage ProcessMessage;

      public override bool Process(int code, IntPtr wparam, IntPtr lparam) {
         if (ProcessMessage != null) {
            return ProcessMessage(code, wparam, lparam);
         }
         return false;
      }

   }

}
