using MO.Common.Geom;
using MO.Direct2d.Device;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>绘制参数。</T>
   //============================================================
   public class SUiDrawArgs
   {
      // 环境
      public FDxContext2d Context;

      // 位置
      public SIntPoint2 Position = new SIntPoint2();

      // 下拉控件
      public FUiControl DropControl;
   }
}
