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
   // <T>地图控制台。</T>   
   //============================================================
   public class FMbMapConsole : FConsole
   {
      // 实例化对象
      protected static FMbMapConsole _instance;

      // 目录
      protected string _directory;

      // 地图合集
      protected FObjects<FMbMap> _maps = new FObjects<FMbMap>();

      //============================================================
      // <T>构造地图控制台。</T>   
      //============================================================
      public FMbMapConsole() {
         _name = "Content.Map.Console";
      }

      //============================================================
      // <T>构造地图控制台。</T>   
      //============================================================
      public static FMbMapConsole Instance {
         get {
            if (null == _instance) {
               _instance = new FMbMapConsole();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>获得地图集合</T>
      //============================================================
      public FObjects<FMbMap> Maps {
         get { return _maps; }
      }

      //============================================================
      // <T>获得或设置路径</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>根据代码查找资源对象。</T>
      //
      // @param code 代码
      // @return 资源对象
      //============================================================
      public FMbMap FindById(int id) {
         foreach (FMbMap map in _maps) {
            if (id == map.Tid) {
               return map;
            }
         }
         return null;
      }
      
      //============================================================
      // <T>加载地图设置信息</T>
      //============================================================
      public override void LoadConfig(FXmlNode config) {
         // 加载属性
         foreach (FXmlNode node in config.Nodes) {
            string name = node.Get("name");
            if ("map_directory" == name) {
               string text = node.Text;
               _directory = RMoCommon.ParseEnvironment(text);
            }
         }
         // 加载路径
         LoadDirectory(_directory);
      }

      //============================================================
      // <T>序列化。</T>
      //============================================================
      public void Serialize() {
         string saveFile = RMoCommon.ParseEnvironment("${resource.root}") + "\\db\\map.db";

         FByteFile file = new FByteFile();
         file.WriteInt16((short)_maps.Count);
         foreach (FMbMap map in _maps) {
            map.Serialize(file);
         }

         file.SaveFile(saveFile);
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
            string name = path.Substring(path.LastIndexOf("\\")+1);
            string id = name.Substring(0, name.IndexOf('-'));
            string label = name.Substring(name.IndexOf('-')+1).Trim();
            // 文件夹的全路径地址。
            FDsMap map = new FDsMap();
            map.Resource.Directory = path;
            map.Resource.Tid = RInt.Parse(id);
            map.Resource.Name = name;
            map.Resource.Label = label;
            map.Resource.LoadDirectory();
            _maps.Push(map.Resource);
         }
      }
   }
}
