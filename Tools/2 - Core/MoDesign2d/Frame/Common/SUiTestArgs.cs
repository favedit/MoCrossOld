using MO.Common.Geom;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>测试参数。</T>
   //============================================================
   public class SUiTestArgs
   {
      // 位置
      public SIntPoint2 Point = new SIntPoint2();

      // 控件
      public FUiControl Control;

      // 控件集合
      protected FUiControlCollection _controls = new FUiControlCollection();

      //============================================================
      // <T>获得控件集合。</T>
      //============================================================
      public FUiControlCollection Controls {
         get { return _controls; }
      }

      //============================================================
      // <T>增加一个控件。</T>
      //
      // @param control 控件
      //============================================================
      public void Push(FUiControl control) {
         Control = control;
         _controls.Push(control);
      }

      //============================================================
      // <T>增加一个控件。</T>
      //
      // @param control 控件
      //============================================================
      public void Reset() {
         Control = null;
         _controls.Clear();
      }
   }
}
