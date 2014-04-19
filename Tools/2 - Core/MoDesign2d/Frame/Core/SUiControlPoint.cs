using MO.Common.Geom;

namespace MO.Design2d.Frame.Core
{
   //============================================================
   // <T>界面控件点。</T>
   //============================================================
   public class SUiControlPoint
   {
      // 对齐方式
      public int AlignCd;

      // 补齐
      public int Padding = 2;

      // 坐标
      public SIntPoint2 Point = new SIntPoint2();

      // 尺寸
      public SIntSize2 Size = new SIntSize2(5, 5);

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public bool Constains(int x, int y) {
         int centerX = Size.Width / 2;
         int centerY = Size.Height / 2;
         return RRectangle.ConstainsPoint(
               Point.X - centerX - Padding, Point.Y - centerY - Padding,
               Size.Width + Padding + Padding, Size.Height + Padding + Padding,
               x, y);
      }

      //============================================================
      // <T>更新数据处理。</T>
      //
      // @param x 横坐标
      // @param y 纵坐标
      //============================================================
      public void Update(int x, int y) {
         Point.Set(x, y);
      }
   }
}
