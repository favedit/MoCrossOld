using MO.Common.Geom;

namespace MO.Core.Forms.Device
{
   //============================================================
   public class RMouse
   {
      protected static bool _pressed;

      protected static EKeyStatus[] _status = new EKeyStatus[16];

      protected static SIntPoint2 _locationDown = new SIntPoint2();

      //============================================================
      public static bool IsPressed() {
         return _pressed;
      }

      //============================================================
      public static SIntPoint2 LocationDown {
         get { return _locationDown; }
      }

      //============================================================
      public static bool TestPressed() {
         for (int n = _status.Length - 1; n >= 0; n--) {
            if (EKeyStatus.Pressed == _status[n]) {
               return true;
            }
         }
         return false;
      }

      //============================================================
      public static bool IsPressedKey(int keyCode) {
         return (EKeyStatus.Pressed == _status[keyCode]);
      }

      //============================================================
      public static void ProcessDown(int keyCode) {
         _status[keyCode] = EKeyStatus.Pressed;
         _pressed = true;
      }

      //============================================================
      public static void ProcessUp(int keyCode) {
         _status[keyCode] = EKeyStatus.Unpressed;
         _pressed = TestPressed();
      }
   }
}
