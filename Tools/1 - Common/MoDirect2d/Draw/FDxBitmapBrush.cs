using SlimDX.Direct2D;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>固定色刷。</T>
   //
   // @history MAOCY 120223
   //============================================================
   public class FDxBitmapBrush : FDxBrush
   {
      // 位图
      protected FDxBitmap _bitmap;

      // 位图刷子属性
      protected BitmapBrushProperties _bitmapProperties = new BitmapBrushProperties();

      //============================================================
      // <T>构造固定色刷。</T>
      //============================================================
      public FDxBitmapBrush() {
      }

      //============================================================
      // <T>获得位图。</T>
      //============================================================
      public FDxBitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      // <T>获得位图刷子属性。</T>
      //============================================================
      public BitmapBrushProperties BitmapProperties {
         get { return _bitmapProperties; }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public override void Setup() {
         Refresh();
      }

      //============================================================
      // <T>加载文件。</T>
      //
      // @param fileName 文件
      //============================================================
      public void LoadBitmap(System.Drawing.Bitmap bitmap) {
         _bitmap = new FDxBitmap();
         _bitmap.Device = _device;
         _bitmap.LoadBitmap(bitmap);
      }

      //============================================================
      // <T>加载文件。</T>
      //
      // @param fileName 文件
      //============================================================
      public void LoadFile(string fileName) {
         _bitmap = new FDxBitmap();
         _bitmap.Device = _device;
         using(System.Drawing.Bitmap bitmap = new System.Drawing.Bitmap(fileName)) {
            _bitmap.LoadBitmap(bitmap);
         }
      }
      
      //============================================================
      // <T>重新加载。</T>
      //============================================================
      public void Refresh() {
         // 释放对象
         if (null != _native) {
            _native.Dispose();
         }
         // 创建对象
         _bitmapProperties.VerticalExtendMode = ExtendMode.Wrap;
         _bitmapProperties.HorizontalExtendMode = ExtendMode.Wrap;
         _native = new BitmapBrush(_device.Target, _bitmap.Native, _bitmapProperties);
      }
   }
}
