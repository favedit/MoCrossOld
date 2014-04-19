using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Content3d.Common
{
   public class FDrMaterialBase : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDrMaterialBase));

      // 有效性
      protected bool _valid;

      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 环境光颜色
      protected SFloatColor4 _ambientColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 散射光颜色
      protected SFloatColor4 _diffuseColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 散射光相机颜色
      protected SFloatColor4 _diffuseCameraColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 高光颜色
      protected SFloatColor4 _specularColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 高光相机颜色
      protected SFloatColor4 _specularCameraColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 自发光颜色
      protected SFloatColor4 _emissiveColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 反射光颜色
      protected SFloatColor4 _reflectColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 前折射颜色
      protected SFloatColor4 _refractFrontColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 后折射颜色
      protected SFloatColor4 _refractBackColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 不透明颜色
      protected SFloatColor4 opacityColor = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 分层1颜色
      protected SFloatColor4 layer1Color = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 分层2颜色
      protected SFloatColor4 layer2Color = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 分层3颜色
      protected SFloatColor4 layer3Color = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 分层4颜色
      protected SFloatColor4 layer4Color = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      //============================================================
      // <T>构造材质定义。</T>
      //============================================================
      public FDrMaterialBase() {
      }

      //============================================================
      // <T>获得或设置有效性。</T>
      //============================================================
      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      public void Assign(FDrMaterialBase material) {
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _name = config.Nvl("name");
         _label = config.Nvl("label");
         // 读取环境光
         FXmlNode xambient = config.Find("Ambient");
         if (null != xambient) {
            _ambientColor.LoadConfig(xambient, "r", "g", "b", "power");
         }
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         config.Set("name", _name);
         config.Set("label", _label);
         FXmlNode xambient = config.CreateNode("Ambient");
         _ambientColor.SaveConfig(xambient, "r", "g", "b", "power");
      }
   }
}
