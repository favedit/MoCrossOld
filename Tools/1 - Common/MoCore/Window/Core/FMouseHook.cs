using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Core.Hook {

   public class FMouseHook  : FWindowHook {

      public FMouseHook() {
         _type = EHookType.MouseLL;
      }


      public override bool Process(int code, IntPtr wparam, IntPtr lparam) {
         /*// if ok and someone listens to our events
         if ((nCode >= 0) && (OnMouseActivity != null)) {
            //Marshall the data from callback.
            MouseLLHookStruct mouseHookStruct = (MouseLLHookStruct)Marshal.PtrToStructure(lParam, typeof(MouseLLHookStruct));

            //detect button clicked
            MouseButtons button = MouseButtons.None;
            short mouseDelta = 0;
            switch (wParam) {
               case WM_LBUTTONDOWN:
                  //case WM_LBUTTONUP: 
                  //case WM_LBUTTONDBLCLK: 
                  button = MouseButtons.Left;
                  break;
               case WM_RBUTTONDOWN:
                  //case WM_RBUTTONUP: 
                  //case WM_RBUTTONDBLCLK: 
                  button = MouseButtons.Right;
                  break;
               case WM_MOUSEWHEEL:
                  //If the message is WM_MOUSEWHEEL, the high-order word of mouseData member is the wheel delta. 
                  //One wheel click is defined as WHEEL_DELTA, which is 120. 
                  //(value >> 16) & 0xffff; retrieves the high-order word from the given 32-bit value
                  mouseDelta = (short)((mouseHookStruct.mouseData >> 16) & 0xffff);
                  //TODO: X BUTTONS (I havent them so was unable to test)
                  //If the message is WM_XBUTTONDOWN, WM_XBUTTONUP, WM_XBUTTONDBLCLK, WM_NCXBUTTONDOWN, WM_NCXBUTTONUP, 
                  //or WM_NCXBUTTONDBLCLK, the high-order word specifies which X button was pressed or released, 
                  //and the low-order word is reserved. This value can be one or more of the following values. 
                  //Otherwise, mouseData is not used. 
                  break;
            }

            //double clicks
            int clickCount = 0;
            if (button != MouseButtons.None)
               if (wParam == WM_LBUTTONDBLCLK || wParam == WM_RBUTTONDBLCLK) clickCount = 2;
               else clickCount = 1;

            //generate event 
            MouseEventArgs e = new MouseEventArgs(
                                               button,
                                               clickCount,
                                               mouseHookStruct.pt.x,
                                               mouseHookStruct.pt.y,
                                               mouseDelta);
            //raise it
            OnMouseActivity(this, e);
         }*/
         return false;
      }


   }

}
