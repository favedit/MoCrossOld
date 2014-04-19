using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;

namespace MO.Content3d.Resource.Model.Mesh
{
   public class FDrTangent
   {
      // 编号
      public bool isFixed;

      // 编号
      protected int _id;

      // 顶点
      protected SFloatVector3 _direction = new SFloatVector3();

      //============================================================
      // <T>获得或设置编号。</T>
      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }
      
      //============================================================
      // <T>获得顶点。</T>
      //============================================================
      public SFloatVector3 Direction {
         get { return _direction; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         xconfig.Set("id", _id.ToString());
         xconfig.Set("direction", _direction.ToString());
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _direction.LoadConfig(config);
      }

      //============================================================
      public float ToFloat() {
         _direction.Normalize();
         float x = (_direction.X + 1.0f) * 0.5f * 255.0f;
         float y = (_direction.Y + 1.0f) * 0.5f * 255.0f;
         float z = (_direction.Z + 1.0f) * 0.5f * 255.0f;
         return (x * 256.0f * 256.0f) + (y * 256.0f) + (z);
      }
      
      //============================================================
      public void Serialize(IOutput output) {
         _direction.Serialize(output);
      }
   }
}
