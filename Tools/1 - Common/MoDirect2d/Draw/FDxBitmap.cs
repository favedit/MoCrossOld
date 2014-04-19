using MO.Common.Geom;
using MO.Direct2d.Common;
using SlimDX;
using SlimDX.Direct2D;
using SlimDX.DXGI;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>位图。</T>
   //============================================================
   public class FDxBitmap : FDxObject2d
   {
      // 大小
      protected SIntSize2 _size = new SIntSize2();

      // 本地对象
      protected Bitmap _native;

      // 位图属性
      protected BitmapProperties _properties = new BitmapProperties();

      //============================================================
      // <T>构造色刷。</T>
      //============================================================
      public FDxBitmap() {
      }

      //============================================================
      // <T>获得本地对象。</T>
      //============================================================
      public SIntSize2 Size {
         get { return _size; }
      }

      //============================================================
      // <T>获得本地对象。</T>
      //============================================================
      public Bitmap Native {
         get { return _native; }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public override void Setup() {
         base.Setup();
      }

      //============================================================
      // <T>加载位图数据。</T>
      //
      // @param bitmap 位图
      //============================================================
      public void LoadBitmap(System.Drawing.Bitmap bitmap) {
         // 打开图片
         int width = bitmap.Width;
         int height = bitmap.Height;
         _size.Set(width, height);
         // 读取数据
         System.Drawing.Imaging.BitmapData bitmapData = bitmap.LockBits(
               new System.Drawing.Rectangle(0, 0, width, height), System.Drawing.Imaging.ImageLockMode.ReadOnly, System.Drawing.Imaging.PixelFormat.Format32bppPArgb);
         DataStream dataStream = new DataStream(bitmapData.Scan0, bitmapData.Stride * bitmapData.Height, true, false);
         _properties.PixelFormat = new PixelFormat(Format.B8G8R8A8_UNorm, AlphaMode.Premultiplied);
         // 创建对象
         _native = new Bitmap(_device.Target, new System.Drawing.Size(width, height), dataStream, bitmapData.Stride, _properties);
         bitmap.UnlockBits(bitmapData);
      }

      //============================================================
      // <T>加载文件。</T>
      //
      // @param fileName 文件名称
      //============================================================
      public void LoadFile(string fileName) {
         using(System.Drawing.Bitmap bitmap = new System.Drawing.Bitmap(fileName)) {
            LoadBitmap(bitmap);
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         if(_native != null) {
            _native.Dispose();
            _native = null;
         }
         base.Dispose();
      }
   }
}
