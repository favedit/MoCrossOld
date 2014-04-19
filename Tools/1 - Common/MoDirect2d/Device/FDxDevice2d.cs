using System;
using System.Drawing;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Direct2d.Common;
using MO.Direct2d.Draw;
using SlimDX.Direct2D;

namespace MO.Direct2d.Device
{
   //============================================================
   // <T>设备。</T>
   //============================================================
   public class FDxDevice2d : FObject
   {
      // 工厂
      protected static SlimDX.Direct2D.Factory _factory = new SlimDX.Direct2D.Factory();

      // 写入工厂
      protected static SlimDX.DirectWrite.Factory _writeFactory = new SlimDX.DirectWrite.Factory();

      // 渲染目标
      protected WindowRenderTarget _target;

      // 句柄
      protected IntPtr _handle;

      // 尺寸
      protected SIntSize2 _size = new SIntSize2();

      //============================================================
      // <T>构造设备。</T>
      //============================================================
      public FDxDevice2d() {
      }

      //============================================================
      // <T>获得或设置句柄。</T>
      //============================================================
      public IntPtr Handle {
         get { return _handle; }
         set { _handle = value; }
      }

      //============================================================
      // <T>获得大小。</T>
      //============================================================
      public SIntSize2 Size {
         get { return _size; }
      }

      //============================================================
      // <T>获得工厂。</T>
      //============================================================
      public Factory Factory {
         get { return _factory; }
      }

      //============================================================
      // <T>获得写入工厂。</T>
      //============================================================
      public SlimDX.DirectWrite.Factory WriteFactory {
         get { return _writeFactory; }
      }

      //============================================================
      // <T>获得渲染目标。</T>
      //============================================================
      public WindowRenderTarget Target {
         get { return _target; }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public void Setup() {
         // 设置属性
         WindowRenderTargetProperties properties = new WindowRenderTargetProperties();
         properties.Handle = _handle;
         properties.PixelSize = new Size(_size.Width, _size.Height);
         // 创建目标
         _target = new WindowRenderTarget(_factory, properties);
         _target.AntialiasMode = AntialiasMode.PerPrimitive;
      }

      //============================================================
      // <T>创建色刷。</T>
      //
      // @parma color 颜色
      // @return 色刷
      //============================================================
      public FDxSolidBrush CreateSolidBrush(Color color) {
         FDxSolidBrush result = new FDxSolidBrush();
         result.Device = this;
         result.Color.Set(color.R, color.G, color.B, color.A);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建色刷。</T>
      //
      // @parma color 颜色
      // @return 色刷
      //============================================================
      public FDxSolidBrush CreateSolidBrush(SColor color) {
         FDxSolidBrush result = new FDxSolidBrush();
         result.Device = this;
         result.Color.Assign(color);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建色刷。</T>
      //
      // @parma red 红色
      // @parma green 绿色
      // @parma blue 蓝色
      // @parma alpha 透明度
      // @return 色刷
      //============================================================
      public FDxSolidBrush CreateSolidBrush(float red = 1.0f, float green = 1.0f, float blue = 1.0f, float alpha = 1.0f) {
         FDxSolidBrush result = new FDxSolidBrush();
         result.Device = this;
         result.Color.SetFloat(red, green, blue, alpha);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建线条样式。</T>
      //
      // @parma beginCap 开始样式
      // @parma endCap 结束样式
      // @parma lineCap 线条样式
      // @parma lineStyle 线条样式
      // @parma dashs 点集合
      // @return 线条样式
      //============================================================
      public FDxStrokeStyle CreateStrokeStyle(EDxCapStyle beginCap, EDxCapStyle endCap, EDxCapStyle lineCap, EDxDashStyle lineStyle, float[] dashs = null) {
         FDxStrokeStyle result = new FDxStrokeStyle();
         result.Device = this;
         result.BeginCap = beginCap;
         result.EndCap = endCap;
         result.DashCap = lineCap;
         if(dashs != null) {
            result.DashStyle = EDxDashStyle.Custom;
            result.Dashs = dashs;
         } else {
            result.DashStyle = lineStyle;
         }
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建位图。</T>
      //
      // @parma bitmap 位图
      // @return 位图
      //============================================================
      public FDxBitmap CreateBitmap(System.Drawing.Bitmap bitmap) {
         FDxBitmap result = new FDxBitmap();
         result.Device = this;
         result.LoadBitmap(bitmap);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建位图。</T>
      //
      // @parma fileName 文件名称
      // @return 位图
      //============================================================
      public FDxBitmap CreateBitmap(string fileName) {
         FDxBitmap result = new FDxBitmap();
         result.Device = this;
         result.LoadFile(fileName);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建层。</T>
      //
      // @return 层
      //============================================================
      public FDxLayer CreateLayer() {
         FDxLayer result = new FDxLayer();
         result.Device = this;
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建层。</T>
      //
      // @param size 大小
      // @return 层
      //============================================================
      public FDxLayer CreateLayer(SFloatSize size) {
         FDxLayer result = new FDxLayer();
         result.Device = this;
         result.Size.Assign(size);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建层。</T>
      //
      // @param width 宽度
      // @param height 高度
      // @return 层
      //============================================================
      public FDxLayer CreateLayer(float width, float height) {
         FDxLayer result = new FDxLayer();
         result.Device = this;
         result.Size.Set(width, height);
         result.Setup();
         return result;
      }

      //============================================================
      // <T>创建字体格式。</T>
      //
      // @parma fontFamilyName 字体名称
      // @parma fontWeight 字体粗细
      // @parma fontStyle 字体样式
      // @parma fontStretch 字体拉伸
      // @parma fontSize 字体大小
      // @parma localName 本地名称
      // @return 字体格式
      //============================================================
      public FDxTextFormat CreateTextFormat(string fontFamilyName, string fontWeight = "Normal", string fontStyle = "Normal", string fontStretch = "Normal", float fontSize = 9.0f, string localName = null){
         FDxTextFormat format = new FDxTextFormat();
         format.Device = this;
         format.FontFamilyName = fontFamilyName;
         format.FontWeight = fontWeight;
         format.FontStyle = fontStyle;
         format.FontStretch = fontStretch;
         format.FontSize = fontSize;
         format.LocalName = localName;
         format.Setup();
         return format;
      }

      //============================================================
      // <T>开始绘制。</T>
      //============================================================
      public void BeginDraw() {
         _target.BeginDraw();
      }

      //============================================================
      // <T>结束绘制。</T>
      //============================================================
      public void EndDraw() {
         _target.EndDraw();
      }

      //============================================================
      // <T>改变大小处理。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void Resize(int width, int height) {
         if((_size.Width != width) || (_size.Height != height)) {
            _size.Set(width, height);
            _target.Resize(new Size(width, height));
         }
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public void Dispose() {
         // 释放目标
         _target.Dispose();
         // 释放工厂
         _factory.Dispose();
      }
   }
}
