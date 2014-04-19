using MO.Common.Content;
using MO.Common.IO;
using System;

namespace MO.Common.Geom
{
   public class SFloatColor3
   {
      public float R;

      public float G;

      public float B;

      //============================================================
      public void Set(float r, float g, float b) {
          R = r;
          G = g;
          B = b;
      }

      //============================================================
      public void SetAll(float value) {
         R = value;
         G = value;
         B = value;
      }

      //============================================================
      // <T>返回向量的模</T>
      // 
      //============================================================
      public float Absolute() {
         return (float)Math.Sqrt((R * R) + (G * G) + (B * B));
      }

      //============================================================
      // <T>标准化<T/>
      //============================================================
      public void Normalize() {
         float r = 1.0f / Absolute();
         R *= r;
         G *= r;
         B *= r;
      }
      
      //============================================================
      public void Assign(SFloatColor3 value) {
         R = value.R;
         G = value.G;
         B = value.B;
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         R = config.GetFloat("r");
         G = config.GetFloat("g");
         B = config.GetFloat("b");
      }

      //============================================================
      public void Unserialize(IInput input) {
         R = input.ReadFloat();
         G = input.ReadFloat();
         B = input.ReadFloat();
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(R);
         output.WriteFloat(G);
         output.WriteFloat(B);
      }
   
      //============================================================
      public void SerializeByte3(IOutput output) {
         Normalize();
         byte cr = (byte)((R + 1) * 0.5 * 255);
         byte cg = (byte)((G + 1) * 0.5 * 255);
         byte cb = (byte)((B + 1) * 0.5 * 255);
         output.WriteUint8(cr);
         output.WriteUint8(cg);
         output.WriteUint8(cb);
      }
   }
}
