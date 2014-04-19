using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content3d.Resource.Material;

namespace MO.Content3d.Resource.Model
{
   //============================================================
   // <T>模型材质。</T>
   //============================================================
   public class FDrModelMaterial : FObject
   {
      // 名称
      protected string _name;

      // 数据名称
      protected string _dataName;

      // 环境光颜色
      protected SFloatColor4 _ambient = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 散射光颜色
      protected SFloatColor4 _diffuse = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 高光颜色
      protected SFloatColor4 _specular = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 自发光颜色
      protected SFloatColor4 _emissive = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      // 材质
      protected FDrMaterialGroup _material;

      //============================================================
      // <T>构造模型材质。</T>
      //============================================================
      public FDrModelMaterial() {
      }

      //============================================================
      // <T>获得或者设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置数据名称。</T>
      //============================================================
      public string DataName {
         get { return _dataName; }
         set { _dataName = value; }
      }

      //============================================================
      // <T>获得环境颜色</T>
      //============================================================
      public SFloatColor4 Ambient {
         get { return _ambient; }
         set { _ambient = value; }
      }

      //============================================================
      // <T>获得散射颜色</T>
      //============================================================
      public SFloatColor4 Diffuse {
         get { return _diffuse; }
         set { _diffuse = value; }
      }

      //============================================================
      // <T>获得高光颜色</T>
      //============================================================
      public SFloatColor4 Specular {
         get { return _specular; }
         set { _specular = value; }
      }

      //============================================================
      // <T>获得发光颜色</T>
      //============================================================
      public SFloatColor4 Emissive {
         get { return _emissive; }
         set { _emissive = value; }
      }

      //============================================================
      // <T>获得或设置财政。</T>
      //============================================================
      public FDrMaterialGroup Material {
         get { return _material; }
         set { _material = value; }
      }

      //============================================================
      // <T>加载模型设置。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public void LoadModelConfig(FXmlNode xconfig) {
         _dataName = xconfig.Get("name");
         // 读取环境光
         FXmlNode ambientNode = xconfig.Find("Ambient");
         if(null != ambientNode) {
            _ambient.LoadConfig3(ambientNode);
         }
         // 读取散射光
         FXmlNode diffuseNode = xconfig.Find("Diffuse");
         if(null != diffuseNode) {
            _diffuse.LoadConfig3(diffuseNode);
         }
         // 读取全反射
         FXmlNode specularNode = xconfig.Find("Specular");
         if(null != specularNode) {
            _specular.LoadConfig3(specularNode);
         }
         // 读取全反射级别
         FXmlNode specularLevelNode = xconfig.Find("SpecularLevel");
         if(null != specularLevelNode) {
            _specular.A = specularLevelNode.GetFloat("value");
         }
         // 读取自发光
         FXmlNode emissiveNode = xconfig.Find("Emissive");
         if(null != emissiveNode) {
            _emissive.LoadConfig3(emissiveNode);
         }
         FXmlNode emissiveAmtNode = xconfig.Find("EmissiveAmt");
         if(null != emissiveAmtNode) {
            _emissive.A = emissiveAmtNode.GetFloat("value");
         }
      }
   }
}
