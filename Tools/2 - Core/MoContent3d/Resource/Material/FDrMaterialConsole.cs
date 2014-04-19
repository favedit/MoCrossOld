using System;
using MO.Common.Collection;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Texture;
using MO.Common.Lang;
using MO.Core.Content.Catalog;
using MO.Common.IO;
using MO.Content3d.Resource.Theme;

namespace MO.Content3d.Resource.Material
{
   //============================================================
   // <T>材质控制台信息。</T>
   //============================================================
   public class FDrMaterialConsole : FDrResourceConsole
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrMaterialConsole));

      // 材质字典
      protected FDictionary<FDrMaterialGroup> _materials = new FDictionary<FDrMaterialGroup>();

      //============================================================
      // <T>构造材质控制台信息。</T>
      //============================================================
      public FDrMaterialConsole() {
         _name = "Content3d.Material.Console";
      }

      //============================================================
      // <T>获得材质字典。</T>
      //
      // @return 材质字典
      //============================================================
      public FDictionary<FDrMaterialGroup> Materials {
         get { return _materials; }
      }

      //============================================================
      // <T>根据名称查找材质。</T>
      //
      // @param name 名称
      // @return 材质
      //============================================================
      public FDrMaterialGroup FindGroup(string name) {
         string code = RDrUtil.FormatPathToCode(name);
         return _materials.Find(code);
      }

      //============================================================
      // <T>根据材质组合主题名称查找材质。</T>
      //
      // @param themeName 主题名称
      // @param groupName 材质组名称
      // @return 材质
      //============================================================
      public FDrMaterial Find(string themeName, string groupName) {
         string groupCode = RDrUtil.FormatPathToCode(groupName);
         string themeCode = RDrUtil.FormatPathToCode(themeName);
         FDrMaterialGroup group = _materials.Find(groupCode);
         if (null != group) {
            return group.FindMaterial(themeCode);
         }
         return null;
      }

      //============================================================
      // <T>根据材质组合主题名称查找材质。</T>
      //
      // @param themeName 主题名称
      // @param groupName 材质组名称
      // @return 材质
      //============================================================
      public FDrMaterial FindDefault(string themeName, string groupName) {
         string groupCode = RDrUtil.FormatPathToCode(groupName);
         string themeCode = RDrUtil.FormatPathToCode(themeName);
         FDrMaterialGroup group = _materials.Find(groupCode);
         FDrMaterial material = null;
         if (null != group) {
            material = group.FindMaterial(themeCode);
         }
         if (null == material) {
            if (null != group) {
               if (!group.Materials.IsEmpty) {
                  material = group.Materials.First;
               }
            }
         }
         return material;
      }

      //============================================================
      // <T>扫描所有节点。</T>
      //
      // @param folder 文件夹
      // @param path 路径
      //============================================================
      private void ScanNodes(FDrFolder folder, string path) {
         string fileTag = string.Empty;
         // 文件夹排序
         folder.Folders.Sort();
         // 循环取得每个文件
         foreach (FDrFolder subfloder in folder.Folders) {
            // 获得经过处理的名称
            string[] items = subfloder.Name.Split('-');
            if (items.Length >= 3) {
               string type = items[0];
               string dotPath = path + "\\" + items[1];
               if (type == "fd") {
                  subfloder.Type = "folder";
                  subfloder.Label = items[1] + " [" + items[2] + "]";
               } else if (type == "mt") {
                  subfloder.Type = "material";
                  FDrMaterialGroup materialGroup = new FDrMaterialGroup();
                  subfloder.Label = items[1] + " [" + items[2] + "]";
                  materialGroup.Name = dotPath;
                  materialGroup.Label = items[2];
                  materialGroup.Directory = subfloder.Directory;
                  materialGroup.DirectoryExprot = _exportDirectory;
                  materialGroup.Scan();
                  // 存储对照表
                  if (materialGroup.OptionValid) {
                     subfloder.Tag = materialGroup;
                     _materials.Set(materialGroup.Code, materialGroup);
                     _folders.Push(subfloder);
                  }
               }
               ScanNodes(subfloder, dotPath);
            }
         }
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public override void Open() {
         base.Open();
         // 扫描节点
         ScanNodes(_folder, String.Empty);
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public void ScanTextures() {
         string rootDirectory = _folder.Directory;
         foreach (INamePair<FDrTexture> pair in RContent3dManager.TextureConsole.Textures) {
            FDrTexture texture = pair.Value;
            String name = texture.Name;
            FDrMaterialGroup materialGroup = FindGroup(name);
            if (materialGroup != null) {
               continue;
            }
            // 创建材质组
            FDrFolder folder = texture.Folder;
            FObjects<FCfgFolder> stack = folder.FetchFolderStack(false);
            string materialPath = folder.FolderPath().Replace("tx-", "mt-");
            string materialDirectory = rootDirectory + materialPath;
            RDirectory.MakeDirectories(materialDirectory);
            FDrTheme theme = RContent3dManager.ThemeConsole.DefaultTheme;
            // 创建材质组
            materialGroup = new FDrMaterialGroup();
            materialGroup.Name = name;
            materialGroup.ConfigFileName = materialDirectory + "/config.xml";
            // 创建材质
            FDrMaterial material = new FDrMaterial();
            material.Theme = theme;
            material.EffectName = theme.EffectName;
            materialGroup.Materials.Push(material);
            foreach (FDrTextureBitmap bitmap in texture.Bitmaps) {
               FDrMaterialTexture materialTexture = new FDrMaterialTexture();
               materialTexture.TypeCd = bitmap.TypeCd;
               materialTexture.Source = texture.Name;
               materialTexture.SourceTypeCd = bitmap.TypeCd;
               materialTexture.SourceIndex = bitmap.Index;
               materialGroup.Textures.Push(materialTexture);
            }
            // 存储材质
            materialGroup.Store();
            _materials.Set(name, materialGroup);
            _logger.Debug(this, "ScanTextures", "Create material. (name={0})", materialDirectory);
         }
      }

      //============================================================
      // <T>存储全部</T>
      //============================================================
      public void SaveAll() {
         foreach (INamePair<FDrMaterialGroup> pair in _materials) {
            pair.Value.Store();
         }
      }
   }
}
