using MO.DX.Core.Common;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxSpatialMaterial : FDxMaterial
   {
      protected string _name;

      //============================================================
      public FDxSpatialMaterial() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public void LoadResource(FDxRsTemplateMaterial resource) {
         _name = resource.Code;
         // 加载颜色
         _ambientColor.Assign(resource.AmbientColor);
         _diffuseColor.Assign(resource.DiffuseColor);
         _specularColor.Assign(resource.SpecularColor);
         // 加载纹理
         int textureCount = resource.Textures.Count;
         for(int n = 0; n < textureCount; n++) {
            FDxRsTemplateTexture rsTexture = resource.Textures[n];
            FDxSpatialTexture texture = new FDxSpatialTexture();
            texture.Device = _device;
            texture.LoadResource(rsTexture);
            _textures.Push(texture);
         }
      }
   }
}
