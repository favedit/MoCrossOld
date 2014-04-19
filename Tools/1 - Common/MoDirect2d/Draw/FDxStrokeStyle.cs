using MO.Direct2d.Common;
using SlimDX.Direct2D;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>线条样式。</T>
   //============================================================
   public class FDxStrokeStyle : FDxObject2d
   {
      // 样式
      protected StrokeStyle _native;

      // 属性集合
      protected StrokeStyleProperties _properties = new StrokeStyleProperties();

      // 点信息集合
      protected float[] _dashs;

      //============================================================
      // <T>构造线条样式。</T>
      //============================================================
      public FDxStrokeStyle() {
      }

      //============================================================
      // <T>获得本地对象。</T>
      //============================================================
      public StrokeStyle Native {
         get { return _native; }
      }

      //============================================================
      // <T>获得或设置属性。</T>
      //============================================================
      public StrokeStyleProperties Properties {
         get { return _properties; }
         set { _properties = value; }
      }

      //============================================================
      // <T>获得或设置点集合。</T>
      //============================================================
      public float[] Dashs {
         get { return _dashs; }
         set { _dashs = value; }
      }

      //============================================================
      // <T>获得或设置开始样式。</T>
      //============================================================
      public EDxCapStyle BeginCap {
         get { return (EDxCapStyle)_properties.StartCap; }
         set { _properties.StartCap = (CapStyle)value; }
      }

      //============================================================
      // <T>获得或设置结束样式。</T>
      //============================================================
      public EDxCapStyle EndCap {
         get { return (EDxCapStyle)_properties.EndCap; }
         set { _properties.EndCap = (CapStyle)value; }
      }

      //============================================================
      // <T>获得或设置线条样式。</T>
      //============================================================
      public EDxCapStyle DashCap {
         get { return (EDxCapStyle)_properties.DashCap; }
         set { _properties.DashCap = (CapStyle)value; }
      }

      //============================================================
      // <T>获得或设置线条样式。</T>
      //============================================================
      public EDxDashStyle DashStyle {
         get { return (EDxDashStyle)_properties.DashStyle; }
         set { _properties.DashStyle = (DashStyle)value; }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public override void Setup() {
         Refresh();
      }

      //============================================================
      // <T>重新加载。</T>
      //============================================================
      public void Refresh() {
         // 释放对象
         if(null != _native) {
            _native.Dispose();
         }
         // 创建对象
         if(_properties.DashStyle == SlimDX.Direct2D.DashStyle.Custom) {
            _native = new StrokeStyle(_device.Factory, _properties, _dashs);
         } else {
            _native = new StrokeStyle(_device.Factory, _properties);
         }
      }
   }
}
