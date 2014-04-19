using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public class RApi {

      public const int ErrorSuccess = 0;

      public static readonly IntPtr InvalidHandleValue = new IntPtr(-1);

      public static bool IsValidHandle(IntPtr handle) {
         return (handle != InvalidHandleValue);
      }

   }

}
