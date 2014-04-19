using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;

namespace MO.Core.Window.Core.Hook {

   public delegate bool HKeyProcessMessage(int nCode, IntPtr wParam, IntPtr lParam);

   public class FKeyHook : FWindowHook {

      [StructLayout(LayoutKind.Sequential)]
      private class KeyboardHookStruct {
         public int vkCode;
         public int scanCode;
         public int flags;
         public int time;
         public int dwExtraInfo;
      }

      public enum EKeyEvent {
         None = 0x00,
         Down = 0x01,
         Press = 0x02,
         Up = 0x04,
         All = 0xFF
      }

      public EKeyEvent _events = EKeyEvent.All;

      public event HKeyProcessMessage ProcessMessage;

      public FKeyHook() {
         _type = EHookType.KeyboardLL;
      }

      public EKeyEvent KeyEvent {
         get { return _events; }
         set { _events = value; }
      }

      public override bool Process(int nCode, IntPtr wParam, IntPtr lParam) {
         if (ProcessMessage != null) {
            ProcessMessage(nCode, wParam, lParam);
         }
         //indicates if any of underlaing events set e.Handled flag
         /*bool handled = false;
         //it was ok and someone listens to events
         if ((nCode >= 0) && (OnKeyDown != null || KeyUp != null || KeyPress != null)) {
            //read structure KeyboardHookStruct at lParam
            KeyboardHookStruct MyKeyboardHookStruct = (KeyboardHookStruct)Marshal.PtrToStructure(lParam, typeof(KeyboardHookStruct));
            //raise KeyDown
            if (KeyDown != null && (wParam == EWindowMessage.WM_KEYDOWN || wParam == EWindowMessage.WM_SYSKEYDOWN)) {
               Keys keyData = (Keys)MyKeyboardHookStruct.vkCode;
               KeyEventArgs e = new KeyEventArgs(keyData);
               KeyDown(this, e);
               handled = handled || e.Handled;
            }

            // raise KeyPress
            if (KeyPress != null && wParam == EWindowMessage.WM_KEYDOWN) {
               bool isDownShift = ((GetKeyState(VK_SHIFT) & 0x80) == 0x80 ? true : false);
               bool isDownCapslock = (GetKeyState(VK_CAPITAL) != 0 ? true : false);

               byte[] keyState = new byte[256];
               GetKeyboardState(keyState);
               byte[] inBuffer = new byte[2];
               if (ToAscii(MyKeyboardHookStruct.vkCode,
                         MyKeyboardHookStruct.scanCode,
                         keyState,
                         inBuffer,
                         MyKeyboardHookStruct.flags) == 1) {
                  char key = (char)inBuffer[0];
                  if ((isDownCapslock ^ isDownShift) && Char.IsLetter(key)) key = Char.ToUpper(key);
                  KeyPressEventArgs e = new KeyPressEventArgs(key);
                  KeyPress(this, e);
                  handled = handled || e.Handled;
               }
            }

            // raise KeyUp
            if (KeyUp != null && (wParam == EWindowMessage.WM_KEYUP || wParam == EWindowMessage.WM_SYSKEYUP)) {
               Keys keyData = (Keys)MyKeyboardHookStruct.vkCode;
               KeyEventArgs e = new KeyEventArgs(keyData);
               KeyUp(this, e);
               handled = handled || e.Handled;
            }

         }

         //if event handled in application do not handoff to other listeners
         if (handled)
            return 1;
         else
            return CallNextHookEx(hKeyboardHook, nCode, wParam, lParam);*/
         return false;
      }

      /*public abstract bool KeyProcess(int code, IntPtr wparam, IntPtr lparam);
      public abstract bool OnKeyDown(int code, IntPtr wparam, IntPtr lparam);
      public abstract bool OnKeyUp(int code, IntPtr wparam, IntPtr lparam);
      public abstract bool OnKeyPress(int code, IntPtr wparam, IntPtr lparam);*/

   }

}
