using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;

namespace MoMobile.Map.Common
{
   //============================================================
   // <T>地图格子对象。</T>
   //============================================================
   public class FMbMapCell
   {
      // 格子索引
      protected SIntPoint2 _index = new SIntPoint2();

      // 资源编号
      protected int _resourceId;

      //============================================================
      // <T>获取或得到格子位置索引。</T>
      //============================================================
      public SIntPoint2 Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      // <T>获取或得到格子资源编号。</T>
      //============================================================
      public int ResourceId {
         get { return _resourceId; }
         set { _resourceId = value; }
      }

      public void LoadConfig(FXmlNode config) {
         if (config.Contains("index")) {
            _index.Parse(config.Get("index"));
         }
         if (config.Contains("resource_id")) {
            _resourceId = config.GetInteger("resource_id");
         }  
      }

      //============================================================
      // <T>序列化。</T>
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteUint16((ushort)_index.X);
         output.WriteUint16((ushort)_index.Y);
         output.WriteUint32((uint)_resourceId);
      }
   }
}
