using System;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   public class SFloatVector3
   {
      public float X;

      public float Y;

      public float Z;

      //============================================================
      public SFloatVector3() {
      }

      //============================================================
      public SFloatVector3(float x, float y, float z) {
         X = x;
         Y = y;
         Z = z;
      }

      //============================================================
      public SFloatVector3 Clear() {
         X = 0.0f;
         Y = 0.0f;
         Z = 0.0f;
         return this;
      }

      //============================================================
      public void Mul(float rate) {
         X *= rate;
         Y *= rate;
         Z *= rate;
      }

      //============================================================
      public void Assign(SFloatVector3 value) {
         X = value.X;
         Y = value.Y;
         Z = value.Z;
      }

      //============================================================
      public void Set(float x, float y, float z) {
         X = x;
         Y = y;
         Z = z;
      }

      //============================================================
      public void Add(SFloatPoint3 point) {
         X += point.X;
         Y += point.Y;
         Z += point.Z;
      }

      //============================================================
      public void Add(SFloatVector3 vector) {
         X += vector.X;
         Y += vector.Y;
         Z += vector.Z;
      }

      //============================================================
      public void Sub(SFloatPoint3 point) {
         X -= point.X;
         Y -= point.Y;
         Z -= point.Z;
      }

      //============================================================
      public float Dot(SFloatVector3 value) {
         return (X * value.X) + (Y * value.Y) + (Z * value.Z);
      }

      //============================================================
      public float Angle(SFloatVector3 value) {
         // 计算两单位向量点乘
         SFloatVector3 value1 = new SFloatVector3(X, Y, Z);
         value1.Normalize();
         SFloatVector3 value2 = new SFloatVector3(value.X, value.Y, value.Z);
         value2.Normalize();
         // 计算夹角
         float cos = value1.Dot(value2);
         return (float)Math.Acos(cos);
      }

      //============================================================
      // <T>返回向量的模</T>
      // 
      //============================================================
      public bool IsEmpty() {
         return (0 == X) & (0 == Y) & (0 == Z);
      }

      //============================================================
      // <T>返回向量的模</T>
      // 
      //============================================================
      public float Length() {
         return (float)Math.Sqrt((X * X) + (Y * Y) + (Z * Z));
      }

      //============================================================
      // <T>返回向量的模</T>
      // 
      //============================================================
      public float Absolute() {
         return (float)Math.Sqrt((X * X) + (Y * Y) + (Z * Z));
      }

      //============================================================
      // <T>标准化<T/>
      //============================================================
      public void Normalize() {
         float absolute = Absolute();
         if(0.0f != absolute) {
            float r = 1.0f / Absolute();
            X *= r;
            Y *= r;
            Z *= r;
         } else {
            //throw new FFatalException("Div number is null.");
            X = 0;
            Y = 0;
            Z = 0;
         }
      }

      //============================================================
      // <T>标准化<T/>
      //============================================================
      public SFloatVector3 Cross(SFloatVector3 value) {
         value.Normalize();
         SFloatVector3 result = new SFloatVector3();
         result.X = (Y * value.Z) - (Z * value.Y);
         result.Y = (Z * value.X) - (X * value.Z);
         result.Z = (X * value.Y) - (Y * value.X);
         result.Normalize();
         return result;
      }

      //============================================================
      // <T>标准化<T/>
      //============================================================
      public void Cross(SFloatVector3 value, SFloatVector3 result) {
         result.X = (Y * value.Z) - (Z * value.Y);
         result.Y = (Z * value.X) - (X * value.Z);
         result.Z = (X * value.Y) - (Y * value.X);
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
      public void LoadConfig(FXmlNode xconfig) {
         X = xconfig.GetFloat("x");
         Y = xconfig.GetFloat("y");
         Z = xconfig.GetFloat("z");
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
      public void SerializeByteSigned3(IOutput output) {
         Normalize();
         output.WriteUint8((byte)((X + 1) * 0.5 * 255));
         output.WriteUint8((byte)((Y + 1) * 0.5 * 255));
         output.WriteUint8((byte)((Z + 1) * 0.5 * 255));
      }

      //============================================================
      public float ToFloat() {
         Normalize();
         int x = (int)((X + 1.0f) * 0.5f * 255.0f);
         int y = (int)((Y + 1.0f) * 0.5f * 255.0f);
         int z = (int)((Z + 1.0f) * 0.5f * 255.0f);
         int result = (x * 256 * 256) + (y * 256) + (z);
         return (float)result;
      }
      
      //============================================================
      public override string ToString() {
         return X + "," + Y + "," + Z;
      }
   }
}
