using MO.Common.Content;
using MO.Common.IO;

namespace MO.Common.Geom
{
   //============================================================
   // <T>浮点轮廓3。</T>
   //============================================================
   public class SFloatOutline3
   {
      public SFloatPoint3 min = new SFloatPoint3();

      public SFloatPoint3 max = new SFloatPoint3();

      //============================================================
      public void Assign(SFloatOutline3 value) {
         min.Assign(value.min);
         max.Assign(value.max);
      }

      //============================================================
      public void InitializeMin() {
         min.Set(float.MinValue, float.MinValue, float.MinValue);
         max.Set(float.MaxValue, float.MaxValue, float.MaxValue);
      }

      //============================================================
      public void InitializeMax() {
         min.Set(float.MaxValue, float.MaxValue, float.MaxValue);
         max.Set(float.MinValue, float.MinValue, float.MinValue);
      }

      //============================================================
      public void InnerMin(SFloatOutline3 value) {
         min.InnerMax(value.min);
         max.InnerMin(value.max);
      }

      //============================================================
      public void InnerMax(SFloatOutline3 value) {
         min.InnerMin(value.min);
         max.InnerMax(value.max);
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         //R = config.GetFloat("r");
         //G = config.GetFloat("g");
         //B = config.GetFloat("b");
      }

      //============================================================
      public void Serialize(IOutput output) {
         min.Serialize(output);
         max.Serialize(output);
      }

      //============================================================
      public void Unserialize(IInput input) {
         min.Unserialize(input);
         max.Unserialize(input);
      }
   }
}
