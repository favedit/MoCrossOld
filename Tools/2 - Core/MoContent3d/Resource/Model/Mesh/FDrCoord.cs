using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;

namespace MO.Content3d.Resource.Model.Mesh
{
   public class FDrCoord
   {
      // 编号
      protected int _id;

      // 顶点
      protected SFloatPoint2 _coord = new SFloatPoint2();

      //============================================================
      // <T>获得贴图坐标。</T>
      //============================================================
      public SFloatPoint2 Coord {
         get { return _coord; }
      }

      //============================================================
      // <T>获得或设置编号。</T>
      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         xconfig.Set("id", _id.ToString());
         xconfig.Set("position", _coord.ToString());
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _coord.LoadConfig(config);
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(_coord.X);
         output.WriteFloat(_coord.Y);
      }
   }
}
