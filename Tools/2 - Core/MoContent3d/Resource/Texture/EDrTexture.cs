using MO.Common.Lang;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>纹理定义。</T>
   //============================================================
   public class EDrTexture
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

      //============================================================
      // <T>尝试根据类型名称获得类型枚举。</T>
      //
      // @param typeName 类型名称
      // @return 类型枚举
      //============================================================
      public static int TryParse(string typeName) {
         switch(typeName) {
            case "ambient":
               return Ambient;
            case "diffuse":
               return Diffuse;
            case "alpha":
               return Alpha;
            case "normal":
               return Normal;
            case "height":
               return Height;
            case "specular":
               return Specular;
            case "specular.level":
               return SpecularLevel;
            case "light":
               return Light;
            case "reflect":
               return Reflect;
            case "refract":
               return Refract;
            case "emissive":
               return Emissive;
            case "environment":
               return Environment;
            case "transmittance.color":
               return TransmittanceColor;
            case "transmittance.level":
               return TransmittanceLevel;
            case "layer.merge":
               return LayerMerge;
            case "layer.1":
               return Layer1;
            case "layer.2":
               return Layer2;
            case "layer.3":
               return Layer3;
            case "layer.4":
               return Layer4;
            case "pack.diffuse":
               return PackDiffuse;
            case "pack.normal":
               return PackNormal;
            case "pack.specular":
               return PackSpecular;
            case "pack.transmittance":
               return PackTransmittance;
            case "pack.light":
               return PackLight;
         }
         return -1;
      }

      //============================================================
      // <T>根据类型名称获得类型枚举。</T>
      //
      // @param typeName 类型名称
      // @return 类型枚举
      //============================================================
      public static int Parse(string typeName) {
         int typeCd = TryParse(typeName);
         if (-1 == typeCd) {
            throw new FFatalException("Unknown type.");
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
         switch (typeCd) {
            case None:
               return "";
            case Ambient:
               return "ambient";
            case Diffuse:
               return "diffuse";
            case Alpha:
               return "alpha";
            case Normal:
               return "normal";
            case Height:
               return "height";
            case Specular:
               return "specular";
            case SpecularLevel:
               return "specular.level";
            case Light:
               return "light";
            case Reflect:
               return "reflect";
            case Refract:
               return "refract";
            case Emissive:
               return "emissive";
            case Environment:
               return "environment";
            case TransmittanceColor:
               return "transmittance.color";
            case TransmittanceLevel:
               return "transmittance.level";
            case LayerMerge:
               return "layer.merge";
            case Layer1:
               return "layer.1";
            case Layer2:
               return "layer.2";
            case Layer3:
               return "layer.3";
            case Layer4:
               return "layer.4";
            case PackDiffuse:
               return "pack.diffuse";
            case PackNormal:
               return "pack.normal";
            case PackSpecular:
               return "pack.specular";
            case PackTransmittance:
               return "pack.transmittance";
            case PackLight:
               return "pack.light";
         }
         throw new FFatalException("Unknown type code.");
      }

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
   }
}
