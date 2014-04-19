using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   public class SFloatVector4
   {
      public float X;

      public float Y;

      public float Z;

      public float W;

      //============================================================
      public SFloatVector4() {
      }

      //============================================================
      public SFloatVector4(float x, float y, float z, float w) {
         X = x;
         Y = y;
         Z = z;
         W = w;
      }

      //============================================================
      public void Assign(SFloatVector4 value) {
         X = value.X;
         Y = value.Y;
         Z = value.Z;
         W = value.W;
      }

      //============================================================
      // <T>解析数据。</T>
      //
      // @param source 来源
      //============================================================
      public void Parse3(string source) {
         string[] values = source.Split(',');
         if(3 == values.Length) {
            X = RFloat.Parse(values[0]);
            Y = RFloat.Parse(values[1]);
            Z = RFloat.Parse(values[2]);
         }
      }

      //============================================================
      // <T>解析数据。</T>
      //
      // @param source 来源
      //============================================================
      public void Parse4(string source) {
         string[] values = source.Split(',');
         if(4 == values.Length) {
            X = RFloat.Parse(values[0]);
            Y = RFloat.Parse(values[1]);
            Z = RFloat.Parse(values[2]);
            W = RFloat.Parse(values[3]);
         }
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         X = config.GetFloat("x");
         Y = config.GetFloat("y");
         Z = config.GetFloat("z");
         W = config.GetFloat("w");
      }

      //============================================================
      public override string ToString() {
         return X + "," + Y + "," + Z + "," + W;
      }
   }
}
