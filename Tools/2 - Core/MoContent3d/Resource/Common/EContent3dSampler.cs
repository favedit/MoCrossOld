using MO.Common.Lang;
namespace MO.Content3d.Resource.Common
{
   //============================================================
   // <T>渲染取样器集合。</T>
   //============================================================
   public class EContent3dSampler
   {
      // 分割像素数
      public const int SplitColorCount = 255;

      // 分割像素数
      public const int SplitPixelCount = 1024 * 256;

      // 分割数据数
      public const int SplitByteCount = 1024 * 1024;

      // 纹理总数
      public const int TextureCount = 20;

      // 空纹理
      public const int None = 0x00;

      // 环境颜色纹理
      public const int Ambient = 0x01;

      // 漫反射颜色纹理
      public const int Diffuse = 0x02;

      // 透明度纹理
      public const int Alpha = 0x03;

      // 凹凸法线纹理
      public const int Normal = 0x04;

      // 凹凸高度纹理
      public const int Height = 0x05;

      // 高光纹理
      public const int Specular = 0x06;

      // 高光级别纹理
      public const int SpecularLevel = 0x07;

      // 透光颜色纹理
      public const int TransmittanceColor = 0x08;

      // 透光级别纹理
      public const int TransmittanceLevel = 0x09;

      // 光线纹理
      public const int Light = 0x0A;

      // 反射纹理
      public const int Reflect = 0x0B;

      // 折射纹理
      public const int Refract = 0x0C;

      // 发光纹理
      public const int Emissive = 0x0D;

      // 环境纹理
      public const int Environment = 0x0E;

      // 层融合
      public const int LayerMerge = 0x0F;

      // 层1
      public const int Layer1 = 0x10;

      // 层2
      public const int Layer2 = 0x11;

      // 层3
      public const int Layer3 = 0x12;

      // 层4
      public const int Layer4 = 0x13;

      // 打包漫反射纹理
      public const int PackDiffuse = 0x14;

      // 打包法线纹理
      public const int PackNormal = 0x15;

      // 打包高光纹理
      public const int PackSpecular = 0x16;

      // 打包透光纹理
      public const int PackTransmittance = 0x17;

      // 打包光源纹理
      public const int PackLight = 0x18;

      // 纹理未知
      public const int TextureUnknown = 0;

      // 纹理2D
      public const int Texture2d = 1;

      // 纹理3D
      public const int Texture3d = 2;

      // 纹理Cube
      public const int TextureCube = 3;

      // 环境颜色纹理
      public const string AmbientCode = "Ambient";

      // 漫反射颜色纹理
      public const string DiffuseCode = "Diffuse";

      // 透明度纹理
      public const string AlphaCode = "Alpha";

      // 凹凸法线纹理
      public const string NormalCode = "Normal";

      // 凹凸高度纹理
      public const string HeightCode = "Height";

      // 高光纹理
      public const string SpecularCode = "Specular";

      // 高光级别纹理
      public const string SpecularLevelCode = "SpecularLevel";

      // 透光颜色纹理
      public const string TransmittanceColorCode = "TransmittanceColor";

      // 透光级别纹理
      public const string TransmittanceLevelCode = "TransmittanceLevel";

      // 光线纹理
      public const string LightCode = "Light";

      // 反射纹理
      public const string ReflectCode = "Reflect";

      // 折射纹理
      public const string RefractCode = "Refract";

      // 发光纹理
      public const string EmissiveCode = "Emissive";

      // 环境纹理
      public const string EnvironmentCode = "Environment";

      // 层融合
      public const string LayerMergeCode = "LayerMerge";

      // 层1
      public const string Layer1Code = "Layer1";

      // 层2
      public const string Layer2Code = "Layer2";

      // 层3
      public const string Layer3Code = "Layer3";

      // 层4
      public const string Layer4Code = "Layer4";

      // 打包漫反射纹理
      public const string PackDiffuseCode = "PackDiffuse";

      // 打包法线纹理
      public const string PackNormalCode = "PackNormal";

      // 打包高光纹理
      public const string PackSpecularCode = "PackSpecular";

      // 打包透光纹理
      public const string PackTransmittanceCode = "PackTransmittance";

      // 打包光源纹理
      public const string PackLightCode = "PackLight";

      //============================================================
      // <T>根据类型枚举获得类型名称。</T>
      //
      // @param typeCd 类型枚举
      // @return 类型名称
      //============================================================
      public static int ToPack(int typeCd) {
         switch(typeCd) {
            case Diffuse:
            case Alpha:
               return PackDiffuse;
            case Normal:
            case SpecularLevel:
               return PackNormal;
            case Specular:
            case Height:
               return PackSpecular;
            case TransmittanceColor:
            case TransmittanceLevel:
               return PackTransmittance;
            case Light:
            case Reflect:
            case Refract:
            case Emissive:
               return PackLight;
         }
         return typeCd;
      }

      //============================================================
      // <T>根据类型枚举获得类型名称。</T>
      //
      // @param typeCd 类型枚举
      // @return 类型名称
      //============================================================
      public static string ToString(int typeCd) {
         switch(typeCd) {
            case None:
               return "";
            case Ambient:
               return AmbientCode;
            case Diffuse:
               return DiffuseCode;
            case Alpha:
               return AlphaCode;
            case Normal:
               return NormalCode;
            case Height:
               return HeightCode;
            case Specular:
               return SpecularCode;
            case SpecularLevel:
               return SpecularLevelCode;
            case Light:
               return LightCode;
            case Reflect:
               return ReflectCode;
            case Refract:
               return RefractCode;
            case Emissive:
               return EmissiveCode;
            case Environment:
               return EnvironmentCode;
            case TransmittanceColor:
               return TransmittanceColorCode;
            case TransmittanceLevel:
               return TransmittanceLevelCode;
            case LayerMerge:
               return LayerMergeCode;
            case Layer1:
               return Layer1Code;
            case Layer2:
               return Layer2Code;
            case Layer3:
               return Layer3Code;
            case Layer4:
               return Layer4Code;
            case PackDiffuse:
               return PackDiffuseCode;
            case PackNormal:
               return PackNormalCode;
            case PackSpecular:
               return PackSpecularCode;
            case PackTransmittance:
               return PackTransmittanceCode;
            case PackLight:
               return PackLightCode;
         }
         throw new FFatalException("Unknown type code.");
      }
   }
}
