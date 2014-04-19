using System;
using System.Drawing;
using System.Drawing.Imaging;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Content.Drawing;

namespace MO.Content2d.Core
{
   //============================================================
   // <T>位图。</T>
   //============================================================
   public class FRsBitmap : FObject, IDisposable
   {
      // 类型名称
      protected string _typeName;

      // 位图
      protected Bitmap _bitmap;

      // 尺寸
      protected SIntSize _size = new SIntSize();

      // 有效位置
      protected SIntPoint2 _validLocation = new SIntPoint2();

      // 有效尺寸
      protected SIntSize2 _validSize = new SIntSize2();

      // 是否支持透明度
      protected bool _optionAlpha;

      // 有效透明度
      protected int _validAlpha = 8;

      //============================================================
      // <T>构造位图。</T>
      //============================================================
      public FRsBitmap() {
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param bitmap 位图
      //============================================================
      public FRsBitmap(Bitmap bitmap) {
         _bitmap = bitmap;
         // 更新属性
         Update();
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param bitmap 位图
      //============================================================
      public FRsBitmap(Bitmap bitmap, SIntRectangle rectangle) {
         // 设置有效区域
         _validLocation.X = rectangle.Left;
         _validLocation.Y = rectangle.Top;
         _validSize.Width = rectangle.Width;
         _validSize.Height = rectangle.Height;
         // 设置属性
         _bitmap = bitmap;
         // 更新属性
         Update();
      }

      //============================================================
      // <T>构造位图。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FRsBitmap(int width, int height) {
         _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
         // 更新属性
         Update();
      }

      //============================================================
      // <T>获得类型名称。</T>
      //
      // @return 类型名称
      //============================================================
      public string TypeName{
         get { return _typeName; }
      }

      //============================================================
      // <T>获得尺寸。</T>
      //
      // @return 尺寸
      //============================================================
      public SIntSize Size{
         get { return _size; }
      }

      //============================================================
      // <T>获得和设置支持透明度。</T>
      //============================================================
      public bool OptionAlpha {
         get { return _optionAlpha; }
         set { _optionAlpha = value; }
      }
      
      //============================================================
      // <T>获得有效位置。</T>
      //
      // @return 有效位置
      //============================================================
      public SIntPoint2 ValidLocation {
         get { return _validLocation; }
      }

      //============================================================
      // <T>获得有效尺寸。</T>
      //
      // @return 有效尺寸
      //============================================================
      public SIntSize2 ValidSize{
         get { return _validSize; }
      }

      //============================================================
      // <T>获得或设置有效透明度。</T>
      //============================================================
      public int ValidAlpha {
         get { return _validAlpha; }
         set { _validAlpha = value; }
      }
      
      //============================================================
      // <T>序列化数据内容到输出流中。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
      }

      //============================================================
      // <T>从输入流中反序列化数据内容。</T>
      //
      // @param input 输入流
      //============================================================
      public virtual void Unserialize(IInput input) {
      }

      //============================================================
      // <T>加载指定通道的位图文件。</T>
      //
      // @param fileName 文件名称
      // @param channels 通道
      //============================================================
      public void LoadFile(string fileName, int channels = ERsBitmapChannel.ChannelsARGB) {
         using (Bitmap bitmap = new Bitmap(fileName)) {
            // 获得属性
            int width = bitmap.Width;
            int height = bitmap.Height;
            if (null == _bitmap) {
               _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
               RBitmap.Clear(_bitmap, Color.FromArgb(255, 0, 0, 0).ToArgb());
            }
            // 复制通道
            RBitmap.CopyChannels(bitmap, _bitmap, channels);
         }
         // 更新属性
         Update();
      }

      //============================================================
      // <T>加载指定通道的位图文件。</T>
      //
      // @param fileName 文件名称
      // @param channels 通道
      //============================================================
      public void LoadFileChannel(string fileName, int sourceChannel = EBitmapChannel.R, int targetChannel = EBitmapChannel.A) {
         using (Bitmap bitmap = new Bitmap(fileName)) {
            // 获得属性
            int width = bitmap.Width;
            int height = bitmap.Height;
            if (null == _bitmap) {
               _bitmap = new Bitmap(width, height, PixelFormat.Format32bppArgb);
               RBitmap.Clear(_bitmap, Color.FromArgb(255, 0, 0, 0).ToArgb());
            }
            // 复制通道
            RBitmap.CopyChannel(bitmap, sourceChannel, _bitmap, targetChannel);
         }
         // 更新属性
         Update();
      }

      //============================================================
      // <T>更新属性。</T>
      //============================================================
      public void Update() {
         // 测试有效区域
         SIntRectangle rect = new SIntRectangle();
         RBitmap.TestValidRectangle(_bitmap, rect, _validAlpha);
         // 设置属性
         _size.Set(_bitmap.Width, _bitmap.Height);
         _validLocation.Set(rect.Left, rect.Top);
         _validSize.Set(rect.Width, rect.Height);
      }
      
      //============================================================
      // <T>构造位图。</T>
      //============================================================
      public virtual void Dispose() {
         // 释放图片
         if (null != _bitmap) {
            _bitmap.Dispose();
         }
         _bitmap = null;
      }
   }
}
