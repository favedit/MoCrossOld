using MO.Common.Content;
using MO.Common.IO;

namespace MO.Common.Geom
{
   public class SFloatPoint2
   {
      public float X;

      public float Y;

      //============================================================
      public void Assign(SFloatPoint2 value) {
         X = value.X;
         Y = value.Y;
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         X = config.GetFloat("x");
         Y = config.GetFloat("y");
      }

      //============================================================
      public void Unserialize(IInput input) {
         X = input.ReadFloat();
         Y = input.ReadFloat();
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(X);
         output.WriteFloat(Y);
      }
   
       //============================================================
      public override string ToString() {
          return X + "," + Y;
      }
   }
}
