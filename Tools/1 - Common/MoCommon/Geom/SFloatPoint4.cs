using MO.Common.Content;

namespace MO.Common.Geom
{
   public class SFloatPoint4
   {
      public float X;

      public float Y;

      public float Z;

      public float W;

      //============================================================
      public void Assign(SFloatPoint4 value) {
         X = value.X;
         Y = value.Y;
         Z = value.Z;
         W = value.W;
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         X = config.GetFloat("x");
         Y = config.GetFloat("y");
         Z = config.GetFloat("z");
         W = config.GetFloat("w");
      }
   }
}
