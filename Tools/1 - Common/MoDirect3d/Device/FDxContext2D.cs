using System.Drawing;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.DirectX.Draw;

namespace MO.DirectX.Device
{
   //============================================================
   // <T>环境上下文。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FDxContext2D : FObject
   {
      protected FDxDevice2D _device;

      //============================================================
      // <T>构造环境上下文。</T>
      //============================================================
      public FDxContext2D() {
      }

      //============================================================
      // <T>获得或设置设备。</T>
      //============================================================
      public FDxDevice2D Device {
         get { return _device; }
         set { _device = value; }
      }

      //============================================================
      // <T>绘制线条。</T>
      //
      // @param brush 色刷
      // @param x1 起始横向位置
      // @param y1 起始纵向位置
      // @param x2 结束横向位置
      // @param y2 结束纵向位置
      //============================================================
      public void DrawLine(FDxBrush brush, float x1, float y1, float x2, float y2) {
         _device.Target.DrawLine(brush.Native, x1, y1, x2, y2);
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param brush 色刷
      // @param location 位置
      // @param size 大小
      //============================================================
      public void DrawRectangle(FDxBrush brush, SIntPoint2 location, SIntSize2 size) {
         _device.Target.DrawRectangle(brush.Native, new Rectangle(location.X, location.Y, size.Width, size.Height));
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param brush 色刷
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void DrawRectangle(FDxBrush brush, float x, float y, float width, float height) {
         _device.Target.DrawRectangle(brush.Native, new RectangleF(x, y, width, height));
      }

      //============================================================
      // <T>填充矩形。</T>
      //
      // @param brush 色刷
      // @param location 位置
      // @param size 大小
      //============================================================
      public void FillRectangle(FDxBrush brush, SIntPoint2 location, SIntSize2 size) {
         _device.Target.FillRectangle(brush.Native, new Rectangle(location.X, location.Y, size.Width, size.Height));
      }

      //============================================================
      // <T>填充矩形。</T>
      //
      // @param brush 色刷
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void FillRectangle(FDxBrush brush, float x, float y, float width, float height) {
         _device.Target.FillRectangle(brush.Native, new RectangleF(x, y, width, height));
      }
   }
}
