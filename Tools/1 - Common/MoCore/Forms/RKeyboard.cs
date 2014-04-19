using System.Windows.Forms;

namespace MO.Core.Forms
{
   //============================================================
   // <T>按键管理器。</T>
   //============================================================
   public class RKeyboard
   {
      // SHIFT按键集合
      protected static SKey _keyShift = new SKey();

      // CTRL按键集合
      protected static SKey _keyCtrl = new SKey();

      // ALT按键集合
      protected static SKey _keyAlt = new SKey();

      // 按键集合
      protected static SKey[] _keys = new SKey[256];

      //============================================================
      // <T>构造按键管理器。</T>
      //============================================================
      static RKeyboard() {
         int count = _keys.Length;
         for(int n = 0; n < count; n++ ) {
            SKey key = new SKey();
            key.code = n;
            key.pressed = false;
            _keys[n] = key;
         }
      }

      //============================================================
      // <T>SHIFT按键是否被按下。</T>
      //
      // @return 是否被按下
      //============================================================
      public static bool IsShiftPressed() {
         return _keyShift.pressed;
      }

      //============================================================
      // <T>CTRL按键是否被按下。</T>
      //
      // @return 是否被按下
      //============================================================
      public static bool IsCtrlPressed() {
         return _keyCtrl.pressed;
      }

      //============================================================
      // <T>ALT按键是否被按下。</T>
      //
      // @return 是否被按下
      //============================================================
      public static bool IsAltPressed() {
         return _keyAlt.pressed;
      }

      //============================================================
      // <T>按键按下处理。</T>
      //
      // @param e 事件
      //============================================================
      public static void DoKeyDown(KeyEventArgs e) {
         _keyShift.pressed = e.Shift;
         _keyCtrl.pressed = e.Control;
         _keyAlt.pressed = e.Alt;
      }

      //============================================================
      // <T>按键抬起处理。</T>
      //
      // @param e 事件
      //============================================================
      public static void DoKeyUp(KeyEventArgs e) {
         _keyShift.pressed = e.Shift;
         _keyCtrl.pressed = e.Control;
         _keyAlt.pressed = e.Alt;
      }
   }
}
