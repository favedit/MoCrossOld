using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Texture;
using MO.Content3d.Resource.Theme;

namespace MO.Content3d.Resource.Material
{
   //============================================================
   // <T>材质组定义。</T>
   //============================================================
   public class FDrMaterialGroup : FDrResource
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrMaterialGroup));

      // 效果名称
      protected string _effectName;

      // 变换名称
      protected string _transformName;

      // 设置光源影响
      protected int _optionLight = EDrFlag.Inherit;

      // 设置合并
      protected int _optionMerge = EDrFlag.Inherit;

      // 设置排序
      protected int _optionSort = EDrFlag.Inherit;

      // 设置排序级别
      protected int _sortLevel = 1;

      // 设置透明
      protected int _optionAlpha = EDrFlag.Inherit;

      // 设置是否写深度 (不深度的话，不写影子区)
      protected int _optionDepth = EDrFlag.Inherit;

      // 设置比较
      protected string _optionCompare = "less";

      // 设置双面
      protected int _optionDouble = EDrFlag.Inherit;

      // 设置阴影
      protected int _optionShadow = EDrFlag.Inherit;

      // 设置自阴影
      protected int _optionShadowSelf = EDrFlag.Inherit;

      // 设置动态
      protected int _optionDynamic = EDrFlag.Inherit;

      // 设置透射
      protected int _optionTransmittance = EDrFlag.Inherit;

      // 设置不透明度
      protected int _optionOpacity = EDrFlag.Inherit;

      // 材质集合
      protected FVector<FDrMaterial> _materials = new FVector<FDrMaterial>();

      // 纹理集合
      protected FVector<FDrMaterialTexture> _textures = new FVector<FDrMaterialTexture>();

      //============================================================
      // <T>构造材质定义。</T>
      //============================================================
      public FDrMaterialGroup() {
         _typeName = "r3.mg";
         _typeLabel = "Material";
      }

      //============================================================
      // <T>获得或设置效果名称。</T>
      //============================================================
      public string EffectName {
         get { return _effectName; }
         set { _effectName = value; }
      }

      //============================================================
      // <T>获得或设置变换名称。</T>
      //============================================================
      public string TransformName {
         get { return _transformName; }
         set { _transformName = value; }
      }

      //============================================================
      // <T>获得或设置光源影响。</T>
      //============================================================
      public int OptionLight {
         get { return _optionLight; }
         set { _optionLight = value; }
      }

      //============================================================
      // <T>获得或设置合并。</T>
      //============================================================
      public int OptionMerge {
         get { return _optionMerge; }
         set { _optionMerge = value; }
      }

      //============================================================
      // <T>获得或设置排序。</T>
      //============================================================
      public int OptionSort {
         get { return _optionSort; }
         set { _optionSort = value; }
      }

      //============================================================
      // <T>获得或设置排序级别。</T>
      //============================================================
      public int SortLevel {
         get { return _sortLevel; }
         set { _sortLevel = value; }
      }

      //============================================================
      // <T>获得或设置透明。</T>
      //============================================================
      public int OptionAlpha {
         get { return _optionAlpha; }
         set { _optionAlpha = value; }
      }

      //============================================================
      // <T>获得或设置深度。</T>
      //============================================================
      public int OptionDepth {
         get { return _optionDepth; }
         set { _optionDepth = value; }
      }

      //============================================================
      // <T>获得或设置比较方式。</T>
      //============================================================
      public string OptionCompare {
         get { return _optionCompare; }
         set { _optionCompare = value; }
      }

      //============================================================
      // <T>获得或设置双面。</T>
      //============================================================
      public int OptionDouble {
         get { return _optionDouble; }
         set { _optionDouble = value; }
      }

      //============================================================
      // <T>获得或设置阴影。</T>
      //============================================================
      public int OptionShadow {
         get { return _optionShadow; }
         set { _optionShadow = value; }
      }

      //============================================================
      // <T>获得或设置自阴影。</T>
      //============================================================
      public int OptionShadowSelf {
         get { return _optionShadowSelf; }
         set { _optionShadowSelf = value; }
      }

      //============================================================
      // <T>获得或设置动态。</T>
      //============================================================
      public int OptionDynamic {
         get { return _optionDynamic; }
         set { _optionDynamic = value; }
      }

      //============================================================
      // <T>获得或设置透射。</T>
      //============================================================
      public int OptionTransmittance {
         get { return _optionTransmittance; }
         set { _optionTransmittance = value; }
      }

      //============================================================
      // <T>获得或设置不透明。</T>
      //============================================================
      public int OptionOpacity {
         get { return _optionOpacity; }
         set { _optionOpacity = value; }
      }

      //============================================================
      // <T>查找指定主题的材质。</T>
      //============================================================
      public FDrMaterial FindMaterial(string themeName) {
         string themeCode = RDrUtil.FormatPathToCode(themeName);
         foreach (FDrMaterial material in _materials) {
            if (material.ThemeCode == themeCode) {
               return material;
            }
         }
         return null;
      }

      //============================================================
      // <T>查找指定主题的材质。</T>
      //============================================================
      public FDrMaterial SyncMaterial(string themeName) {
         string themeCode = RDrUtil.FormatPathToCode(themeName);
         foreach (FDrMaterial material in _materials) {
            if (material.ThemeCode == themeCode) {
               return material;
            }
         }
         FDrMaterial createMaterial = new FDrMaterial();
         createMaterial.Group = this;
         createMaterial.Assign(_materials.First);
         createMaterial.ThemeName = themeName;
         _materials.Push(createMaterial);
         _materials.Sort(new FDrMaterial());
         return createMaterial;
      }

      //============================================================
      // <T>获得材质集合。</T>
      //============================================================
      public FVector<FDrMaterial> Materials {
         get { return _materials; }
      }

      //============================================================
      // <T>查找指定类型的纹理。</T>
      //============================================================
      public FDrModelMaterialTexture FindTexture(int typeCd) {
         foreach (FDrModelMaterialTexture texture in _textures) {
            if (texture.TypeCd == typeCd) {
               return texture;
            }
         }
         return null;
      }

      //============================================================
      // <T>获得纹理集合。</T>
      //============================================================
      public FVector<FDrMaterialTexture> Textures {
         get { return _textures; }
      }

      //============================================================
      // <T>接收材质信息。</T>
      //============================================================
      public void Assign(FDrMaterialGroup material) {
         _name = material._name;
         _textures.Append(material._textures);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取属性
         _effectName = xconfig.Nvl("effect_name");
         _transformName = xconfig.Nvl("transform_name");
         _optionLight = xconfig.GetInteger("option_light", _optionLight);
         _optionMerge = xconfig.GetInteger("option_merge", _optionMerge);
         _optionSort = xconfig.GetInteger("option_sort", _optionSort);
         _sortLevel = xconfig.GetInteger("sort_level", _sortLevel);
         _optionAlpha = xconfig.GetInteger("option_alpha", _optionAlpha);
         _optionDepth = xconfig.GetInteger("option_depth", _optionDepth);
         _optionCompare = xconfig.Get("option_compare", _optionCompare);
         _optionDouble = xconfig.GetInteger("option_double", _optionDouble);
         _optionShadow = xconfig.GetInteger("option_shadow", _optionShadow);
         _optionShadowSelf = xconfig.GetInteger("option_shadow_self", _optionShadowSelf);
         _optionDynamic = xconfig.GetInteger("option_dynamic", _optionDynamic);
         _optionTransmittance = xconfig.GetInteger("option_transmittance", _optionTransmittance);
         _optionOpacity = xconfig.GetInteger("option_opacity", _optionOpacity);
         // 查找节点
         FXmlNode xmaterialConfig = null;
         FXmlNode xmaterials = xconfig.Find("Materials");
         FXmlNode xtextures = xconfig.Find("Textures");
         // 读取纹理列表
         if (null == xmaterials) {
            FXmlNode xmaterial = xconfig.Find("Material");
            if (null != xmaterial) {
               FDrMaterial material = new FDrMaterial();
               material.Group = this;
               material.LoadConfig(xmaterial);
               _materials.Push(material);
               // 查找纹理节点
               xtextures = xmaterial.Find("Textures");
               xmaterialConfig = xmaterial;
            }
         } else {
            // 找到默认节点
            FXmlNode xdefault = null;
            foreach (FXmlNode xmaterial in xmaterials.Nodes) {
               if (xmaterial.IsName("Material")) {
                  string themeCode = RDrUtil.FormatPathToCode(xmaterial.Get("theme_name"));
                  xdefault = xmaterial;
                  xmaterialConfig = xmaterial;
                  if ("shadow" == themeCode) {
                     break;
                  }
               }
            }
            foreach (FDrTheme theme in RContent3dManager.ThemeConsole.Themes.Values) {
               string code = theme.Code;
               // 查找加载信息
               bool finded = false;
               foreach (FXmlNode xmaterial in xmaterials.Nodes) {
                  if (xmaterial.IsName("Material")) {
                     string themeCode = RDrUtil.FormatPathToCode(xmaterial.Get("theme_name"));
                     if (code == themeCode) {
                        FDrMaterial material = new FDrMaterial();
                        material.Group = this;
                        material.LoadConfig(xmaterial);
                        FDrMaterial findMaterial = FindMaterial(material.ThemeName);
                        if (null == findMaterial) {
                           _materials.Push(material);
                        }
                        finded = true;
                        break;
                     }
                  }
               }
               // 如果不存在，则新建材质
               if (!finded) {
                  if (null != xdefault) {
                     FDrMaterial material = new FDrMaterial();
                     material.Group = this;
                     material.LoadConfig(xdefault);
                     material.Theme = theme;
                     material.ThemeName = theme.Name;
                     _materials.Push(material);
                  }
               }
            }
         }
         // 如果效果名称不存在，则获得首个材质设定
         if (!xconfig.Contains("effect_name") && (null != xmaterialConfig)) {
            _effectName = xmaterialConfig.Nvl("effect_name");
            _transformName = xmaterialConfig.Nvl("transform_name");
            _optionLight = xmaterialConfig.GetInteger("option_light", _optionLight);
            _optionMerge = xmaterialConfig.GetInteger("option_merge", _optionMerge);
            _optionSort = xmaterialConfig.GetInteger("option_sort", _optionSort);
            _sortLevel = xmaterialConfig.GetInteger("sort_level", _sortLevel);
            _optionAlpha = xmaterialConfig.GetInteger("option_alpha", _optionAlpha);
            _optionDepth = xmaterialConfig.GetInteger("option_depth", _optionDepth);
            _optionCompare = xmaterialConfig.Get("option_compare", _optionCompare);
            _optionDouble = xmaterialConfig.GetInteger("option_double", _optionDouble);
            _optionShadow = xmaterialConfig.GetInteger("option_shadow", _optionShadow);
            _optionShadowSelf = xmaterialConfig.GetInteger("option_shadow_self", _optionShadowSelf);
            _optionDynamic = xmaterialConfig.GetInteger("option_dynamic", _optionDynamic);
            _optionTransmittance = xmaterialConfig.GetInteger("option_transmittance", _optionTransmittance);
            _optionOpacity = xmaterialConfig.GetInteger("option_opacity", _optionOpacity); 
         }
         foreach(FDrMaterial material in _materials){
            material.LoadGroup(this);
         }
         // 读取纹理列表
         if (null != xtextures) {
            foreach (FXmlNode xtexture in xtextures.Nodes) {
               if (xtexture.IsName("Texture")) {
                  FDrMaterialTexture materialTexture = new FDrMaterialTexture();
                  materialTexture.Material = this;
                  materialTexture.LoadConfig(xtexture);
                  FDrTexture texture = RContent3dManager.TextureConsole.Find(materialTexture.Source);
                  if (null != texture) {
                     materialTexture.Texture = texture;
                     materialTexture.IsValid = true;
                  } else {
                     RMoCore.TrackConsole.Write(this, "LoadConfig", "Texture is not exists in material. (texture={0}, file_name={1})", materialTexture.Source, _configFileName);
                     materialTexture.IsValid = false;
                  }
                  _textures.Push(materialTexture);
               }
            }
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         // 存储基础
         xconfig.Name = "MaterialGroup";
         xconfig.Set("name", _name);
         xconfig.Set("label", _label);
         // 存储属性
         xconfig.Set("effect_name", _effectName);
         xconfig.Set("transform_name", _transformName);
         xconfig.Set("option_light", _optionLight);
         xconfig.Set("option_merge", _optionMerge);
         xconfig.Set("option_sort", _optionSort);
         xconfig.Set("sort_level", _sortLevel);
         xconfig.Set("option_alpha", _optionAlpha);
         xconfig.Set("option_depth", _optionDepth);
         xconfig.Set("option_compare", _optionCompare);
         xconfig.Set("option_double", _optionDouble);
         xconfig.Set("option_shadow", _optionShadow);
         xconfig.Set("option_shadow_self", _optionShadowSelf);
         xconfig.Set("option_dynamic", _optionDynamic);
         xconfig.Set("option_transmittance", _optionTransmittance);
         xconfig.Set("option_opacity", _optionOpacity);
         // 存储材质列表
         FXmlNode xmaterials = xconfig.CreateNode("Materials");
         foreach (FDrMaterial material in _materials) {
            material.SaveConfig(xmaterials.CreateNode("Material"));
         }
         // 存储纹理列表
         FXmlNode xtextures = xconfig.CreateNode("Textures");
         foreach (FDrMaterialTexture texture in _textures) {
            texture.SaveConfig(xtextures.CreateNode("Texture"));
         }
      }

      //============================================================
      // <T>刷新材质集合。</T>
      //============================================================
      public void RefreshMaterials() {
         foreach (FDrMaterial material in _materials) {
            material.LoadGroup(this);
         }
      }

      //============================================================
      // <T>加载设置文件。</T>
      //============================================================
      public void LoadConfigFile(string fileName) {
         FXmlDocument xdoc = new FXmlDocument(fileName);
         LoadConfig(xdoc.Root);
      }
      
      //============================================================
      // <T>打开材质信息。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         // 加载设置文件
         _optionValid = RFile.Exists(_configFileName);
         if (_optionValid) {
            LoadConfigFile(_configFileName);
         }
      }

      //============================================================
      // <T>打开材质信息。</T>
      //============================================================
      public override void Open() {
         if(!_opened) {
            base.Open();
            foreach(FDrMaterialTexture texture in _textures) {
               texture.Open();
            }
         }
      }

      //============================================================
      // <T>存储设置文件。</T>
      //============================================================
      public void Store() {
         // 打开内容
         Open();
         // 存储设置
         FXmlDocument xdoc = new FXmlDocument();
         SaveConfig(xdoc.Root);
         xdoc.SaveFile(_configFileName);
         _logger.Debug(this, "Store", "Save material config success. (file_name={0})", _configFileName);
         // 释放内容
         Dispose();
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 存储属性
         output.WriteInt8((sbyte)_materials.Count);
         foreach (FDrMaterial material in _materials) {
            material.Serialize(output);
         }
         // 存储贴图
         output.WriteInt8((sbyte)_textures.Count);
         foreach (FDrMaterialTexture texture in _textures) {
            texture.Serialize(output);
         }
      }

      //============================================================
      // <T>导出处理。</T>
      //============================================================
      public void Export() {
         Open();
         // 存储文件
         string exportFileName = RContent3dManager.MaterialConsole.ExportDirectory + "\\mt_" + Code + ".swf";
         FCompressFile file = new FCompressFile();
         Serialize(file);
         file.Compress(exportFileName);
         _logger.Debug(this, "Export", "Export material success. (file_name={0})", exportFileName);
         Dispose();
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public override void Dispose() {
         if(_opened) {
            foreach(FDrMaterialTexture texture in _textures) {
               texture.Dispose();
            }
            // 释放内容
            base.Dispose();
         }
      }
   }
}
