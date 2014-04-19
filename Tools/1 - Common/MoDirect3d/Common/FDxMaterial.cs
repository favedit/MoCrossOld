using MO.Common.Geom;
using MO.Common.Lang;

namespace MO.DX.Core.Common
{
   //============================================================
   // <T>材质信息。</T>
   //============================================================
   public class FDxMaterial : FDxContextObject
   {
      protected SFloatColor4 _ambientColor = new SFloatColor4();

      protected SFloatColor4 _ambient = new SFloatColor4();

      protected SFloatColor4 _diffuseColor = new SFloatColor4();

      protected SFloatColor4 _diffuse = new SFloatColor4();

      protected SFloatColor4 _specularColor = new SFloatColor4();

      protected SFloatColor4 _specular = new SFloatColor4();

      protected FObjects<FDxTexture> _textures = new FObjects<FDxTexture>();

      //============================================================
      // <T>构造材质信息。</T>
      //============================================================
      public FDxMaterial() {
      }

      //============================================================
      public SFloatColor4 AmbientColor{
         get { return _ambientColor; }
      }

      //============================================================
      public SFloatColor4 Ambient {
         get { return _ambient; }
      }

      //============================================================
      public SFloatColor4 DiffuseColor {
         get { return _diffuseColor; }
      }

      //============================================================
      public SFloatColor4 Diffuse {
         get { return _diffuse; }
      }

      //============================================================
      public SFloatColor4 SpecularColor {
         get { return _specularColor; }
      }

      //============================================================
      public SFloatColor4 Specular {
         get { return _specular; }
      }

      //============================================================
      public FObjects<FDxTexture> Textures {
         get { return _textures; }
      }
   }
}
