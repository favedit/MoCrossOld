using MO.Common.Geom;
using MO.Common.Lang;
using MO.DX.Core.Common.Light;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxRegion : FObject
   {
      protected SFloatColor4 _backgroundColor = new SFloatColor4(1, 1, 1, 1);

      protected FDxCamera _camera = new FDxCamera();

      protected FDxLightDirectional _lightDirectional = new FDxLightDirectional();

      protected FObjects<FDxLight> _lights = new FObjects<FDxLight>();

      protected FDxRenderableCollection _renderables = new FDxRenderableCollection();

      //============================================================
      public FDxRegion() {
      }

      //============================================================
      public SFloatColor4 BackgroundColor {
         get { return _backgroundColor; }
      }

      //============================================================
      public FDxCamera Camera {
         get { return _camera; }
         set { _camera = value; }
      }

      //============================================================
      public FDxLightDirectional LightDirectional {
         get { return _lightDirectional; }
         set { _lightDirectional = value; }
      }

      //============================================================
      public FObjects<FDxLight> Lights {
         get { return _lights; }
      }

      //============================================================
      public FDxRenderableCollection Renderables {
         get { return _renderables; }
      }
   }
}
