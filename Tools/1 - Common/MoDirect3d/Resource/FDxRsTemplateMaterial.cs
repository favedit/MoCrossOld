using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTemplateMaterial : FObject
   {
      protected FDxRsTemplate _template;

      protected string _code;

      protected string _effectName;

      protected int _optionDouble;

      protected int _optionAlpha;

      protected int _optionSymmetry;

      protected int _optionShadow;

      protected int _optionTransmittance;

      protected SFloatColor4 _ambientColor = new SFloatColor4(1.0f);

      protected SFloatColor4 _diffuseColor = new SFloatColor4(1.0f);

      protected SFloatColor4 _specularColor = new SFloatColor4(1.0f);

      protected SFloatColor4 _emissiveColor = new SFloatColor4(1.0f);

      protected FObjects<FDxRsTemplateTexture> _textures = new FObjects<FDxRsTemplateTexture>();

      //============================================================
      public FDxRsTemplateMaterial() {
      }

      //============================================================
      public FDxRsTemplate Template {
         get { return _template; }
         set { _template = value; }
      }

      //============================================================
      public string Code {
         get { return _code; }
      }

      //============================================================
      public string EffectName {
         get { return _effectName; }
      }

      //============================================================
      public int OptionDouble {
         get { return _optionDouble; }
      }

      //============================================================
      public int OptionAlpha {
         get { return _optionAlpha; }
      }

      //============================================================
      public int OptionSymmetry {
         get { return _optionSymmetry; }
      }

      //============================================================
      public int OptionShadow {
         get { return _optionShadow; }
      }

      //============================================================
      public int OptionTransmittance {
         get { return _optionTransmittance; }
      }

      //============================================================
      public SFloatColor4 AmbientColor {
         get { return _ambientColor; }
      }

      //============================================================
      public SFloatColor4 DiffuseColor {
         get { return _diffuseColor; }
      }

      //============================================================
      public SFloatColor4 SpecularColor {
         get { return _specularColor; }
      }

      //============================================================
      public SFloatColor4 EmissiveColor {
         get { return _emissiveColor; }
      }

      //============================================================
      public FObjects<FDxRsTemplateTexture> Textures {
         get { return _textures; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         // 存储属性
         _code = input.ReadString();
         _effectName = input.ReadString();
         // 存储设置
         _optionDouble = input.ReadUint8();
         _optionAlpha = input.ReadUint8();
         _optionSymmetry = input.ReadUint8();
         _optionShadow = input.ReadUint8();
         _optionTransmittance = input.ReadUint8();
         // 存储属性
         _ambientColor.Unserialize(input);
         _diffuseColor.Unserialize(input);
         _specularColor.Unserialize(input);
         _emissiveColor.Unserialize(input);
         // 存储贴图
         int count = input.ReadUint8();
         for (int n = 0; n < count; n++) {
            FDxRsTemplateTexture texture = new FDxRsTemplateTexture();
            texture.Unserialize(input);
            _textures.Push(texture);
         }
      }
   }
}
