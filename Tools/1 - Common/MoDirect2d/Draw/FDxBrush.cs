using MO.Direct2d.Common;
using SlimDX.Direct2D;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>色刷。</T>
   //
   // @history MAOCY 120223
   //============================================================
   public class FDxBrush : FDxObject2d
   {
      // 本地对象
      protected Brush _native;

      // 属性
      protected BrushProperties _properties = new BrushProperties();

      //============================================================
      // <T>构造色刷。</T>
      //============================================================
      public FDxBrush() {
      }
   
      //============================================================
      // <T>获得本地对象。</T>
      //============================================================
      public Brush Native {
         get { return _native; }
      }

      //============================================================
      // <T>获得属性。</T>
      //============================================================
      public BrushProperties Properties {
         get { return _properties; }
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
