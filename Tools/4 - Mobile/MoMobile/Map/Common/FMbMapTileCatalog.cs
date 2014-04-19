using MO.Common.Lang;

namespace MoMobile.Map.Common
{
   //============================================================
   // <T>地图瓦片组对象。</T>
   //============================================================
   public class FMbMapTileCatalog
   {
      // 编号
      protected int _id;

      // 标签
      protected string _label;

      // 全名
      protected string _fullName;

      // 瓦片集合
      protected FObjects<FMbMapTile> _mapTiles = new FObjects<FMbMapTile>();

      //============================================================
      // <T>获取或得到编号。</T>
      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      // <T>获取或得到标签。</T>
      //============================================================
      public string Lable {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获取或得到全名。</T>
      //============================================================
      public string FullName {
         get { return _fullName; }
         set { _fullName = value; }
      }

      //============================================================
      // <T>获得地图瓦片集合</T>
      //============================================================
      public FObjects<FMbMapTile> MapTiles {
         get { return _mapTiles; }
      }

   }
}
