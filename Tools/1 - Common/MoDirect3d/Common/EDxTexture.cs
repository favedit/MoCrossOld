using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;

namespace MO.DX.Core.Common
{
   //============================================================
   public class EDxTexture
   {
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

      // 打包漫反射纹理
      public const int PackDiffuse = 0x10;

      // 打包法线纹理
      public const int PackNormal = 0x11;

      // 打包高光纹理
      public const int PackSpecular = 0x12;

      // 打包透光纹理
      public const int PackTransmittance = 0x13;

      // 打包光源纹理
      public const int PackLight = 0x14;

      //============================================================
      public static int TryParse(string type) {
         switch (type) {
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
         }
         return -1;
      }
      
      //============================================================
      public static int Parse(string type) {
         int typeCd = TryParse(type);
         if (-1 == typeCd) {
            throw new FFatalException("Unknown type.");
         }
         return typeCd;
      }

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
            case Light:
            case Reflect:
            case Refract:
            case Emissive:
               return PackLight;
            case TransmittanceColor:
            case TransmittanceLevel:
               return PackTransmittance;
            case Environment:
               return Environment;
         }
         throw new FFatalException("Unknown type code.");
      }

      //============================================================
      public static string ToName(int typeCd) {
         switch(typeCd) {
            case Ambient:
               return "Ambient";
            case Diffuse:
               return "Diffuse";
            case Alpha:
               return "Alpha";
            case Normal:
               return "Normal";
            case Height:
               return "Height";
            case Specular:
               return "Specular";
            case SpecularLevel:
               return "SpecularLevel";
            case Light:
               return "Light";
            case Reflect:
               return "Reflect";
            case Refract:
               return "Refract";
            case Emissive:
               return "Emissive";
            case Environment:
               return "Environment";
            case TransmittanceColor:
               return "TransmittanceColor";
            case TransmittanceLevel:
               return "TransmittanceLevel";
         }
         throw new FFatalException("Unknown type code.");
      }
      
      //============================================================
      public static string ToString(int typeCd) {
         switch (typeCd) {
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
         }
         throw new FFatalException("Unknown type code.");
      }
   }
}
