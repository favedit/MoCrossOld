using MO.Common.Content;
using MO.Content2d;
using MoMobile.Map.Console;
using MoMobile.Template;

namespace MoMobile
{
   //============================================================
   // <T>资源管理器。</T>
   //============================================================
   public class RMobileManager
   {
      // 工作目录
      protected static string _directory;

      // 地图控制台
      protected static FMbMapConsole _mapConsole = new FMbMapConsole();

      // 瓦片控制台
      protected static FMbMapTileConsole _mapTileConsole = new FMbMapTileConsole();

      // 瓦片控制台
      protected static FMbTemplateConsole _templateConsole = new FMbTemplateConsole();

      //============================================================
      // <T>工作目录。</T>
      //============================================================
      public static string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>获得地图控制台。</T>
      //===========================================================
      public static FMbMapConsole MapConsole {
         get { return _mapConsole; }
      }

      //============================================================
      // <T>获得瓦片控制台。</T>
      //===========================================================
      public static FMbMapTileConsole MapTileConsole {
         get { return _mapTileConsole; }
      }

      //============================================================
      // <T>获得模版控制台。</T>
      //===========================================================
      public static FMbTemplateConsole TemplateConsole {
         get { return _templateConsole; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode node in xconfig.Nodes) {
            string name = node.Get("name");
            if ("Content.Map.Console" == name) {
               _mapConsole.LoadConfig(node);
            }
            if ("Content.Map.Tile.Console" == name) {
               _mapTileConsole.LoadConfig(node);
            }
            if ("Content.Template.Console" == name) {
               _templateConsole.LoadConfig(node);
            }
         }
      }

      //============================================================
      // <T>配置文件。</T>
      //============================================================
      public static void Setup() {
         RContent2dManager.Setup();
      }

      //============================================================
      // <T>释放控制台。</T>
      //============================================================
      public static void Release() {
      }

   }
}
