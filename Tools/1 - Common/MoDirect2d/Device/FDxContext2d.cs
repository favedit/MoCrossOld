using MO.Common.Collection;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Direct2d.Draw;
using SlimDX;
using SlimDX.Direct2D;
using System.Drawing;

namespace MO.Direct2d.Device
{
   //============================================================
   // <T>环境上下文。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FDxContext2d : FObject
   {
      // 设备
      protected FDxDevice2d _device;

      // 目标
      protected WindowRenderTarget _target;

      // 颜色集合
      protected FDictionary<FDxSolidBrush> _designColors = new FDictionary<FDxSolidBrush>();

      //============================================================
      // <T>构造环境上下文。</T>
      //============================================================
      public FDxContext2d() {
      }

      //============================================================
      // <T>获得或设置设备。</T>
      //============================================================
      public FDxDevice2d Device {
         get { return _device; }
         set {
            _device = value;
            _target = value.Target;
         }
      }

      //============================================================
      // <T>根据指定颜色生成默认色刷。</T>
      //
      // @param color 颜色
      // @return 色刷
      //============================================================
      public FDxSolidBrush BuildSoldBrush(int color) {
         string code = color.ToString();
         FDxSolidBrush brush = _designColors.Find(code);
         if (brush == null) {
            brush = _device.CreateSolidBrush(Color.FromArgb(color));
            _designColors.Set(code, brush);
         }
         return brush;
      }

      //============================================================
      // <T>变换原始矩阵。</T>
      //============================================================
      public void TransformIdentity() {
         _target.Transform = Matrix3x2.Identity;
      }

      //============================================================
      // <T>变换矩阵坐标。</T>
      //
      // @param x 横向偏移
      // @param y 纵向偏移
      //============================================================
      public void TransformLocation(float x, float y) {
         _target.Transform = Matrix3x2.Translation(x, y);
      }

      //============================================================
      // <T>绘制线条。</T>
      //
      // @param color 颜色
      // @param x1 起始横向位置
      // @param y1 起始纵向位置
      // @param x2 结束横向位置
      // @param y2 结束纵向位置
      // @param strokeWidth 宽度
      //============================================================
      public void DrawLine(int color, float x1, float y1, float x2, float y2, float strokeWidth = 1.0f) {
         FDxSolidBrush brush = BuildSoldBrush(color);
         _target.DrawLine(brush.Native, x1, y1, x2, y2, strokeWidth);
      }

      //============================================================
      // <T>绘制线条。</T>
      //
      // @param color 颜色
      // @param x1 起始横向位置
      // @param y1 起始纵向位置
      // @param x2 结束横向位置
      // @param y2 结束纵向位置
      // @param strokeWidth 宽度
      // @param strokeStyle 样式
      //============================================================
      public void DrawLine(int color, float x1, float y1, float x2, float y2, float strokeWidth, FDxStrokeStyle strokeStyle) {
         FDxSolidBrush brush = BuildSoldBrush(color);
         _target.DrawLine(brush.Native, x1, y1, x2, y2, strokeWidth, strokeStyle.Native);
      }

      //============================================================
      // <T>绘制线条。</T>
      //
      // @param brush 色刷
      // @param x1 起始横向位置
      // @param y1 起始纵向位置
      // @param x2 结束横向位置
      // @param y2 结束纵向位置
      // @param strokeWidth 宽度
      //============================================================
      public void DrawLine(FDxBrush brush, float x1, float y1, float x2, float y2, float strokeWidth = 1.0f) {
         _target.DrawLine(brush.Native, x1, y1, x2, y2, strokeWidth);
      }

      //============================================================
      // <T>绘制线条。</T>
      //
      // @param brush 色刷
      // @param x1 起始横向位置
      // @param y1 起始纵向位置
      // @param x2 结束横向位置
      // @param y2 结束纵向位置
      // @param strokeWidth 宽度
      // @param strokeStyle 样式
      //============================================================
      public void DrawLine(FDxBrush brush, float x1, float y1, float x2, float y2, float strokeWidth, FDxStrokeStyle strokeStyle) {
         _target.DrawLine(brush.Native, x1, y1, x2, y2, strokeWidth, strokeStyle.Native);
      }

      //============================================================
      // <T>绘制线条。</T>
      //
      // @param x1 起始横向位置
      // @param y1 起始纵向位置
      // @param x2 结束横向位置
      // @param y2 结束纵向位置
      // @param brushBack 色刷
      // @param brushFore 色刷
      // @param strokeWidth 宽度
      // @param strokeStyle 样式
      //============================================================
      public void DrawLineLayer(float x1, float y1, float x2, float y2, FDxBrush brushBack, FDxBrush brushFore, float strokeWidth, FDxStrokeStyle strokeStyle) {
         _target.DrawLine(brushBack.Native, x1, y1, x2, y2, strokeWidth);
         _target.DrawLine(brushFore.Native, x1, y1, x2, y2, strokeWidth, strokeStyle.Native);
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param brush 色刷
      // @param location 位置
      // @param size 大小
      //============================================================
      public void DrawRectangle(FDxBrush brush, SIntPoint2 location, SIntSize2 size) {
         _target.DrawRectangle(brush.Native, new Rectangle(location.X, location.Y, size.Width, size.Height));
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param color 颜色
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void DrawRectangle(int color, float x, float y, float width, float height) {
         FDxSolidBrush brush = BuildSoldBrush(color);
         _target.DrawRectangle(brush.Native, new RectangleF(x, y, width, height));
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
         _target.DrawRectangle(brush.Native, new RectangleF(x, y, width, height));
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param brush 色刷
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      // @param strokeWidth 线宽
      //============================================================
      public void DrawRectangle(FDxBrush brush, float x, float y, float width, float height, float strokeWidth) {
         _target.DrawRectangle(brush.Native, new RectangleF(x, y, width, height), strokeWidth);
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param brush 色刷
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      // @param strokeWidth 样式宽度
      // @param style 样式
      //============================================================
      public void DrawRectangle(FDxBrush brush, float x, float y, float width, float height, float strokeWidth, FDxStrokeStyle style) {
         _target.DrawRectangle(brush.Native, new RectangleF(x, y, width, height), strokeWidth, style.Native);
      }

      //============================================================
      // <T>绘制矩形。</T>
      //
      // @param brush 色刷
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      // @param scale 缩放
      // @param strokeWidth 线宽度
      //============================================================
      public void DrawRectangle(FDxBrush brush, float x, float y, float width, float height, float scale, float strokeWidth) {
         _target.DrawRectangle(brush.Native, new RectangleF(x * scale, y * scale, width * scale, height * scale), strokeWidth);
      }

      //============================================================
      // <T>填充矩形。</T>
      //
      // @param color 颜色
      // @param x 横向位置
      // @param y 纵向位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void FillRectangle(int color, float x, float y, float width, float height) {
         FDxSolidBrush brush = BuildSoldBrush(color);
         _target.FillRectangle(brush.Native, new RectangleF(x, y, width, height));
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
         _target.FillRectangle(brush.Native, new RectangleF(x, y, width, height));
      }

      //============================================================
      // <T>绘制位图。</T>
      //
      // @param bitmap 位图
      // @param x 横坐标
      // @param y 纵坐标
      //============================================================
      public void DrawBitmap(FDxBitmap bitmap, int x, int y) {
         _target.DrawBitmap(bitmap.Native, new Rectangle(x, y, bitmap.Size.Width, bitmap.Size.Height));
      }

      //============================================================
      // <T>绘制位图。</T>
      //
      // @param bitmap 位图
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 横坐标
      // @param height 纵坐标
      //============================================================
      public void DrawBitmap(FDxBitmap bitmap, int x, int y, int width, int height) {
         _target.DrawBitmap(bitmap.Native, new Rectangle(x, y, width, height));
      }

      //============================================================
      // <T>绘制位图。</T>
      //
      // @param bitmap 位图
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 横坐标
      // @param height 纵坐标
      // @param scale 缩放
      //============================================================
      public void DrawBitmap(FDxBitmap bitmap, int x, int y, int width, int height, float scale = 1.0f) {
         _target.DrawBitmap(bitmap.Native, new Rectangle((int)(x * scale), (int)(y * scale), (int)(width * scale), (int)(height * scale)), 1.0f, InterpolationMode.NearestNeighbor);
      }

      //============================================================
      // <T>绘制位图。</T>
      //
      // @param bitmap 位图
      // @param bitmapRectangle 位图范围
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 横坐标
      // @param height 纵坐标
      // @param scale 缩放
      //============================================================
      public void DrawBitmap(FDxBitmap bitmap, SIntRectangle bitmapRectangle, int x, int y, int width, int height, float scale = 1.0f) {
         _target.DrawBitmap(bitmap.Native,
            new Rectangle((int)(x * scale), (int)(y * scale), (int)(width * scale), (int)(height * scale)), 1.0f, InterpolationMode.NearestNeighbor,
            new Rectangle(bitmapRectangle.Left, bitmapRectangle.Top, bitmapRectangle.Width, bitmapRectangle.Height));
      }

      //============================================================
      // <T>绘制边界位图。</T>
      //
      // @param bitmap 位图
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 宽度
      // @param height 高度
      // @param padding 空格
      //============================================================
      public void DrawBitmapPadding(FDxBitmap bitmap, int x, int y, int width, int height, SIntPadding padding, float scale = 1.0f) {
         DrawBitmapPadding(bitmap, null, x, y, width, height, padding, scale);
      }

      //============================================================
      // <T>绘制边界位图。</T>
      //
      // @param bitmap 位图
      // @param bitmapRectangle 位图范围
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 宽度
      // @param height 高度
      // @param padding 空格
      //============================================================
      public void DrawBitmapPadding(FDxBitmap bitmap, SIntRectangle bitmapRectangle, int x, int y, int width, int height, SIntPadding padding, float scale = 1.0f) {
         // 计算缩放
         int paddingLeft = padding.Left;
         int paddingTop = padding.Top;
         int paddingRight = padding.Right;
         int paddingBottom = padding.Bottom;
         width -= x;
         height -= y;
         if(scale != 1.0f) {
            x = (int)(x * scale);
            y = (int)(y * scale);
            width = (int)(width * scale);
            height = (int)(height * scale);
            paddingLeft = (int)(paddingLeft * scale);
            paddingTop = (int)(paddingTop * scale);
            paddingRight = (int)(paddingRight * scale);
            paddingBottom = (int)(paddingBottom * scale);
         }
         // 绘制对象
         int[] sxs = null;
         int[] sys = null;
         if(bitmapRectangle != null) {
            sxs = new int[4] { bitmapRectangle.Left, bitmapRectangle.Left + paddingLeft, bitmapRectangle.Right - paddingRight, bitmapRectangle.Right };
            sys = new int[4] { bitmapRectangle.Top, bitmapRectangle.Top + paddingTop, bitmapRectangle.Bottom - paddingBottom, bitmapRectangle.Bottom };
         } else {
            sxs = new int[4] { 0, paddingLeft, bitmap.Size.Width - paddingRight, bitmap.Size.Width };
            sys = new int[4] { 0, paddingTop, bitmap.Size.Height - paddingBottom, bitmap.Size.Height };
         }
         int[] dxs = new int[4] { 0, paddingLeft, width - paddingRight, width };
         int[] dys = new int[4] { 0, paddingTop, height - paddingBottom, height };
         for(int ny = 0; ny < 3; ny++) {
            for(int nx = 0; nx < 3; nx++) {
               int n = 3 * ny + nx;
               // 计算来源信息
               int sx = sxs[nx];
               int sw = sxs[nx + 1] - sx;
               if(sw <= 0) {
                  continue;
               }
               int sy = sys[ny];
               int sh = sys[ny + 1] - sy;
               if(sh <= 0) {
                  continue;
               }
               // 计算目标信息
               int dx = dxs[nx];
               int dw = dxs[nx + 1] - dx;
               if(dw <= 0) {
                  continue;
               }
               int dy = dys[ny];
               int dh = dys[ny + 1] - dy;
               if(dh <= 0) {
                  continue;
               }
               // 绘制图片
               _target.DrawBitmap(bitmap.Native, new Rectangle(x + dx, y + dy, dw, dh), 1.0f, InterpolationMode.Linear, new Rectangle(sx, sy, sw, sh));
            }
         }
      }

      //============================================================
      // <T>绘制文本。</T>
      //
      // @param text 文本
      // @param format 字体格式
      // @param color 颜色
      // @param rectangle 范围
      //============================================================
      public void DrawText(string text, FDxTextFormat format, int color, SIntRectangle rectangle){
         FDxSolidBrush brush = BuildSoldBrush(color);
         _target.DrawText(text, format.Native, new Rectangle(rectangle.Left, rectangle.Top, rectangle.Width, rectangle.Height), brush.Native);
      }

      //============================================================
      // <T>绘制文本。</T>
      //
      // @param text 文本
      // @param format 字体格式
      // @param brush 刷子
      // @param rectangle 范围
      //============================================================
      public void DrawText(string text, FDxTextFormat format, FDxBrush brush, SIntRectangle rectangle) {
         _target.DrawText(text, format.Native, new Rectangle(rectangle.Left, rectangle.Top, rectangle.Width, rectangle.Height), brush.Native);
      }

      //============================================================
      // <T>绘制文本。</T>
      //
      // @param text 文本
      // @param format 字体格式
      // @param brush 刷子
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void DrawText(string text, FDxTextFormat format, FDxBrush brush, int x, int y, int width, int height) {
         _target.DrawText(text, format.Native, new Rectangle(x, y, width, height), brush.Native);
      }

      //============================================================
      // <T>清空背景。</T>
      //
      // @param red 红色
      // @param green 绿色
      // @param blue 蓝色
      // @param alpha 透明色
      //============================================================
      public void Clear(float red = 0.0f, float green = 0.0f, float blue = 0.0f, float alpha = 1.0f){
         _target.Clear(new Color4(alpha, red, green, blue));
      }
   }
}
