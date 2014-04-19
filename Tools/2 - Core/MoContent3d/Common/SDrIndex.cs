using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Content3d.Common
{
   public class SDrIndex
   {
      public int V1;

      public int V2;

      public int V3;

      //============================================================
      public int this[int index] {
         get { return Get(index); }
         set { Set(index, value); }
      }

      //============================================================
      public int Get(int index) {
         if (0 == index) {
            return V1;
         } else if (1 == index) {
            return V2;
         } else if (2 == index) {
            return V3;
         }
         throw new FFatalException("Invalid index.");
      }

      //============================================================
      public void Set(int index, int value) {
         if (0 == index) {
            V1 = value;
         } else if (1 == index) {
            V2 = value;
         } else if (2 == index) {
            V3 = value;
         } else {
            throw new FFatalException("Invalid index.");
         }
      }

      //============================================================
      public void Unserialize(IInput input) {
         V1 = input.ReadInt32();
         V2 = input.ReadInt32();
         V3 = input.ReadInt32();
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         V1 = config.GetInteger("v1");
         V2 = config.GetInteger("v2");
         V3 = config.GetInteger("v3");
      }
      
      //============================================================
      public void Parse(string value) {
         string[] items = value.Split(',');
         V1 = RInt.Parse(items[0]);
         V2 = RInt.Parse(items[1]);
         V3 = RInt.Parse(items[2]);
      }

      //============================================================
      public override string ToString() {
         return V1 + "," + V2 + "," + V3;
      }
   }
}
