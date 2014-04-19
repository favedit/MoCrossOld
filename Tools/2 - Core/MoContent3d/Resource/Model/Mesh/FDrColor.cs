using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;

namespace MO.Content3d.Resource.Model.Mesh
{
   public class FDrColor
   {
      // 编号
      protected int _id;

      // 顶点
      protected SFloatColor3 _color = new SFloatColor3();

      //============================================================
      // <T>获得贴图坐标。</T>
      //============================================================
      public SFloatColor3 Color {
         get { return _color; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _color.R = config.GetFloat("x");
         _color.G = config.GetFloat("y");
         _color.B = config.GetFloat("z");
      }

      //============================================================
      public void Serialize(IOutput output) {
         _color.Serialize(output);
      }
   }
}
