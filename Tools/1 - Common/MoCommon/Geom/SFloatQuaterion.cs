using MO.Common.Content;
using MO.Common.IO;

namespace MO.Common.Geom
{
   //============================================================
   public class SFloatQuaterion
   {
      public float X;

      public float Y;

      public float Z;

      public float W;

      //============================================================
      public void LoadConfig(FXmlNode config) {
         X = config.GetFloat("x");
         Y = config.GetFloat("y");
         Z = config.GetFloat("z");
         W = config.GetFloat("w");
      }
      
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(X);
         output.WriteFloat(Y);
         output.WriteFloat(Z);
         output.WriteFloat(W);
      }
   }
}
