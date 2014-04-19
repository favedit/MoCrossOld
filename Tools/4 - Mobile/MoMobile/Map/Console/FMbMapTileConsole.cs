using System.Drawing;
using MO.Common;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;
using MoMobile.Map.Common;
using MoMobile.Map.Designer;

namespace MoMobile.Map.Console
{

   //============================================================
   // <T>地图瓦片控制台。</T>
   //============================================================
   public class FMbMapTileConsole:FConsole
   {
      // 实例化对象
      protected static FMbMapTileConsole _instance;

      // 目录
      protected string _directory;

      // 导出目录
      protected string _directoryExport;

      // 瓦片组集合
      protected FObjects<FMbMapTileCatalog> _mapTileCatalogs = new FObjects<FMbMapTileCatalog>();


      //============================================================
      // <T>构造地图瓦片控制台。</T>   
      //============================================================
      public FMbMapTileConsole() {
         _name = "Content.Map.Tile.Console";
      }

      //============================================================
      // <T>构造瓦片控制台。</T>   
      //============================================================
      public static FMbMapTileConsole Instance {
         get {
            if (null == _instance) {
               _instance = new FMbMapTileConsole();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>获得地图瓦片组集合</T>
      //============================================================
      public FObjects<FMbMapTileCatalog> MapTileCatalogs {
         get { return _mapTileCatalogs; }
      }

      //============================================================
      // <T>获得地图集合</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>获得瓦片导出路径</T>
      //============================================================
      public string DirectoryExport {
         get { return _directoryExport; }
         set { _directoryExport = value; }
      }

      //============================================================
      // <T>根据编号，找到对应瓦片</T>
      //============================================================
      public FMbMapTile FindMapTile(int id) {
         int layer = RInt.Parse(id.ToString().Substring(0,1));
         foreach (FMbMapTileCatalog tileCatalog in _mapTileCatalogs) {
            if (layer == tileCatalog.Id) {
               foreach (FMbMapTile maptile in tileCatalog.MapTiles) {
                  if (id == maptile.Id) {
                     return maptile;
                  }
               }
            }
         }
         return null;
      }

      //============================================================
      // <T>根据编号，找到对应瓦片组</T>
      //============================================================
      public FMbMapTileCatalog FindMapTileCatalog(int id) {
         foreach (FMbMapTileCatalog catalog in _mapTileCatalogs) {
            if (id == catalog.Id) {
               return catalog;
            }
         }
         return null;
      }

      //============================================================
      // <T>加载瓦片设置信息</T>
      //============================================================
      public override void LoadConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            string name = node.Get("name");
            if ("map_tile_directory" == name) {
               string text = node.Text;
               _directory = RMoCommon.ParseEnvironment(text);
            }
            if ("map_tile_export" == name) {
               string text = node.Text;
               _directoryExport = RMoCommon.ParseEnvironment(text);
            }
         }
         // 加载路径
         LoadDirectory(_directory);
      }

      //============================================================
      // <T>加载文件夹信息，取得地址。</T>
      // <P>加载文件下文件信息，取得文件地址。</P>
      //
      // @param directory 文件路径。
      //============================================================
      public void LoadDirectory(string directory) {
         // 加载给定路径下面的文件夹.
         FStrings paths = RDirectory.ListDirectories(directory);
         int count = paths.Count;
         for (int n = 0; n < count; n++) {
            // 检查文件夹
            string path = paths[n];
            if (path.Contains(".svn")) {
               continue;
            }
            // 取得文件夹类型名称。
            string fullName = path.Substring(path.LastIndexOf("\\")+1 );
            string id = fullName.Substring(0, fullName.IndexOf('-'));
            string label = fullName.Substring(fullName.IndexOf('-') + 1).Trim();
            // 文件夹的全路径地址。
            FDsMapTileCatalog mapTileCatalog = new FDsMapTileCatalog();
            mapTileCatalog.Resource.Id = RInt.Parse(id);
            mapTileCatalog.Resource.FullName = fullName;
            mapTileCatalog.Resource.Lable = label;
            _mapTileCatalogs.Push(mapTileCatalog.Resource);
            //加载瓦片图片集合
            FStrings files = RDirectory.ListFiles(path);
            int fileCount = files.Count;
            for (int i = 0; i < fileCount; i++) {
               string file = files[i];
               if (!file.Contains(".png")) {
                  continue;
               }
               // 取得文件名称。
               string tileId = file.Substring(file.LastIndexOf("\\") + 1, 4);
               Bitmap resource = new Bitmap(file);
               // 文件的全路径地址
               FDsMapTile mapTile = new FDsMapTile();
               mapTile.Resource.Id = RInt.Parse(id+""+tileId);
               mapTile.Resource.Resource = resource;
               mapTileCatalog.Resource.MapTiles.Push(mapTile.Resource);
            }
         }
      }

   }
}
