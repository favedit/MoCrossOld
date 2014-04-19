using System;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>浮点3维位置。</T>
   //============================================================
   public class SFloatPoint3
   {
      public float X;

      public float Y;

      public float Z;

      //============================================================
      // <T>构造浮点3维位置。</T>
      //============================================================
      public SFloatPoint3() {
      }

      //============================================================
      // <T>构造浮点3维位置。</T>
      //============================================================
      public SFloatPoint3(string source) {
         Parse(source);
      }

      //============================================================
      public SFloatPoint3(float x, float y, float z) {
         X = x;
         Y = y;
         Z = z;
      }

      //============================================================
      public void Set(float x, float y, float z) {
         X = x;
         Y = y;
         Z = z;
      }

      //============================================================
      public void Assign(SFloatPoint3 value) {
         X = value.X;
         Y = value.Y;
         Z = value.Z;
      }

      //============================================================
      public void InnerMin(SFloatPoint3 position) {
         X = Math.Min(X, position.X);
         Y = Math.Min(Y, position.Y);
         Z = Math.Min(Z, position.Z);
      }

      //============================================================
      public void InnerMax(SFloatPoint3 position) {
         X = Math.Max(X, position.X);
         Y = Math.Max(Y, position.Y);
         Z = Math.Max(Z, position.Z);
      }

      //============================================================
      // <T>解析数据。</T>
      //
      // @param source 来源
      //============================================================
      public void Parse(string source) {
         string[] values = source.Split(',');
         if(3 == values.Length) {
            X = RFloat.Parse(values[0]);
            Y = RFloat.Parse(values[1]);
            Z = RFloat.Parse(values[2]);
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         X = config.GetFloat("x");
         Y = config.GetFloat("y");
         Z = config.GetFloat("z");
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         xconfig.Set("x", X);
         xconfig.Set("y", Y);
         xconfig.Set("z", Z);
      }
      
      //============================================================
      public void Unserialize(IInput input) {
         X = input.ReadFloat();
         Y = input.ReadFloat();
         Z = input.ReadFloat();
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(X);
         output.WriteFloat(Y);
         output.WriteFloat(Z);
      }
   
      //============================================================
      public override string ToString() {
         return X + "," + Y + "," + Z;
      }
   }
}
