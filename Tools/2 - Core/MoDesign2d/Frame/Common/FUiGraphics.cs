using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Direct2d.Device;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>绘图板。</T>
   //============================================================
   public class FUiGraphics : FObject
   {
      // 环境
      protected FDxContext2d _context;

      //============================================================
      // <T>构造绘图板。</T>
      //
      // @param context 环境
      //============================================================
      public FUiGraphics(FDxContext2d context = null) {
         _context = context;
      }

      //============================================================
      // <T>获得或设置环境。</T>
      //============================================================
      public FDxContext2d Context {
         get { return _context; }
         set { _context = value; }
      }

      //============================================================
      // <T>绘制边框。</T>
      //
      // @param border 边框描述
      //============================================================
      public void DrawBorder(SIntRectangle rectangle, FRcBorder border) {
      }
   }
}
