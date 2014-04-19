using MO.Common.Content;
using MO.Common.Geom;
using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图层对象。</T>
   //============================================================
   public class FDsMapLayer
   {
      // 资源定义
      protected FMbMapLayer _resource = new FMbMapLayer();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMapLayer Resource {
         get { return _resource; }
         set { _resource = value; }
      }

      public FMbMapCell Find(int x,int y) {
         // 判断格子大小
         if (0 == _resource.CellSize.Width || 0 == _resource.CellSize.Height) {
            return null;
         }
         // 获取格子索引
         int indexX = x / _resource.CellSize.Width;
         int indexY = y / _resource.CellSize.Height;
         foreach (FMbMapCell cell in _resource.MapCell) {
            SIntPoint2 cellIndex = cell.Index;
            if (indexX == cellIndex.X && indexY == cellIndex.Y) {
               return cell;
            }
         }
         return null;
      }

      //============================================================
      // <T>保存配置文件。</T>
      //============================================================
      public void SaveConfig(FXmlNode config) {
         FXmlNode layerNode = config.CreateNode("Layer");
         layerNode.Set("cell_count",_resource.CellCount.ToString());
         layerNode.Set("cell_size",_resource.CellSize.ToString());
         FXmlNode tilesNode = layerNode.CreateNode("Tiles");
      }
   }
}
