using MO.Common.Content;
using MoMobile.Map.Common;

namespace MoMobile.Map.Designer
{
   //============================================================
   // <T>地图对象。</T>
   //============================================================
   public class FDsMap
   {
      // 资源定义
      protected FMbMap _resource = new FMbMap();

      //============================================================
      // <T>获取或得到资源。</T>
      //============================================================
      public FMbMap Resource {
         get { return _resource; }
         set { _resource = value; }
      }

      //============================================================
      // <T>保存配置文件。</T>
      //============================================================
      public void SaveFile() {
         FXmlDocument xmldoc = new FXmlDocument();
         FXmlNode node = xmldoc.Root;
         node.Name = "Map";
         node.Set("tid", _resource.Tid);
         node.Set("name", _resource.Name);
         node.Set("label", _resource.Label);
         node.Set("map_size", _resource.Size.ToString());
         // 保存出生点
         FXmlNode birthsNode = node.CreateNode("Births");
         foreach (FMbMapBirth birth in _resource.Births) {
            FXmlNode birthNode = birthsNode.CreateNode("Birth");
            birthNode.Set("location", birth.Location.ToString());
            foreach (FMbMapBirthEnemy enemy in birth.BirthEnemys) {
               FXmlNode enemyNode = birthNode.CreateNode("BirthEnemy");
               enemyNode.Set("template_id", enemy.TemplateId);
            }
         }

         // 保存层
         FXmlNode layersNode = node.CreateNode("Layers");
         foreach (FMbMapLayer layer in _resource.Layers) {
            FXmlNode layerNode = layersNode.CreateNode("Layer");
            layerNode.Set("cell_count",layer.CellCount.ToString());
            layerNode.Set("cell_size", layer.CellSize.ToString());
            layerNode.Set("scroll_cd", layer.ScrollCd.ToString());
            layerNode.Set("type_cd", layer.TypeCd.ToString());
            layerNode.Set("scroll_speed", layer.ScrollSpeed);
            layerNode.Set("wrap_cd", layer.WrapCd.ToString());
            FXmlNode cellsNode = layerNode.CreateNode("Tiles");
            foreach (FMbMapCell cell in layer.MapCell) {
               if (0 == cell.ResourceId) {
                  continue;
               }
               FXmlNode cellNode = cellsNode.CreateNode("Tile");
               cellNode.Set("index", cell.Index.ToString());
               cellNode.Set("resource_id", cell.ResourceId);
            }
         }
         
         xmldoc.SaveFile(_resource.Directory+"\\config.xml");
      }
   }
}
