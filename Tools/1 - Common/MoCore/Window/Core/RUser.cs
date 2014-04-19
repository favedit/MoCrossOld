using System;
using System.Text;
using System.Windows.Forms;
using MO.Core.Window.Api;

namespace MO.Core.Window.Core
{
   public static class RUser
   {
      //============================================================
      public static Control GetFocusedControl() {
         Control control = null;
         IntPtr handle = RUser32.GetFocus();
         if (handle != IntPtr.Zero) {
            control = Control.FromHandle(handle);
         }
         return control;
      }

      //============================================================
      public static string GetWindowText(IntPtr hWnd) {
         int length = RUser32.GetWindowTextLength(hWnd);
         StringBuilder sb = new StringBuilder(length);
         RUser32.GetWindowText(hWnd, sb, length);
         return sb.ToString();
      }
   }
}
