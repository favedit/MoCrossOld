using System;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>浮点数4x4矩阵。</T>
   //============================================================
   public class SFloatMatrix
   {
      // 位置X
      public float tx = 0.0f;

      // 位置Y
      public float ty = 0.0f;

      // 位置Z
      public float tz = 0.0f;

      // 旋转X
      public float rx = 0.0f;

      // 旋转Y
      public float ry = 0.0f;

      // 旋转Z
      public float rz = 0.0f;

      // 缩放X
      public float sx = 1.0f;

      // 缩放Y
      public float sy = 1.0f;

      // 缩放Z
      public float sz = 1.0f;

      // 数据
      public float[] data = new float[16];

      //============================================================
      // <T>构造浮点数4x4矩阵。</T>
      //============================================================
      public SFloatMatrix() {
         Identity();
      }

      //============================================================
      // <T>判断数据是否变更过。</T>
      //
      // @return 是否变更
      //============================================================
      public bool IsChanged {
         get {
            if (tx != 0) {
               return true;
            }
            if (ty != 0) {
               return true;
            }
            if (tz != 0) {
               return true;
            }
            if (rx != 0) {
               return true;
            }
            if (ty != 0) {
               return true;
            }
            if (rz != 0) {
               return true;
            }
            if (sx != 1.0f) {
               return true;
            }
            if (sy != 1.0f) {
               return true;
            }
            if (sz != 1.0f) {
               return true;
            }
            return false;
         }
      }

      //============================================================
      // <T>矩阵单位化（归一化）.</T>
      //
      // 00 01 02 03
      // 04 05 06 07
      // 08 09 10 11
      // 12 13 14 15
      //============================================================
      public void Identity() {
         tx = ty = tz = 0.0f;
         rx = ry = rz = 0.0f;
         sx = sy = sz = 1.0f;
         for (int n = 0; n < 16; n++) {
            data[n] = 0.0f;
         }
         data[0] = data[5] = data[10] = data[15] = 1.0f;
      }

      //============================================================
      // <T>接收数据。</T>
      //
      // @param matrix 源矩阵
      //============================================================
      public void Assign(SFloatMatrix matrix) {
         tx = matrix.tx;
         ty = matrix.ty;
         tz = matrix.tz;
         rx = matrix.rx;
         ry = matrix.ry;
         rz = matrix.rz;
         sx = matrix.sx;
         sy = matrix.sy;
         sz = matrix.sz;
         for (int n = 0; n < 16; n++) {
            data[n] = matrix.data[n];
         }
      }

      //============================================================
      // <T>矩阵相乘更新数据。</T>
      //
      // @param data 源数据流
      //============================================================
      public void AppendData(float[] values) {
         float[] temp = new float[16];
         temp[0] = (data[0] * values[0]) + (data[1] * values[4]) + (data[2] * values[8]) + (data[3] * values[12]);
         temp[1] = (data[0] * values[1]) + (data[1] * values[5]) + (data[2] * values[9]) + (data[3] * values[13]);
         temp[2] = (data[0] * values[2]) + (data[1] * values[6]) + (data[2] * values[10]) + (data[3] * values[14]);
         temp[3] = (data[0] * values[3]) + (data[1] * values[7]) + (data[2] * values[11]) + (data[3] * values[15]);
         temp[4] = (data[4] * values[0]) + (data[5] * values[4]) + (data[6] * values[8]) + (data[7] * values[12]);
         temp[5] = (data[4] * values[1]) + (data[5] * values[5]) + (data[6] * values[9]) + (data[7] * values[13]);
         temp[6] = (data[4] * values[2]) + (data[5] * values[6]) + (data[6] * values[10]) + (data[7] * values[14]);
         temp[7] = (data[4] * values[3]) + (data[5] * values[7]) + (data[6] * values[11]) + (data[7] * values[15]);
         temp[8] = (data[8] * values[0]) + (data[9] * values[4]) + (data[10] * values[8]) + (data[11] * values[12]);
         temp[9] = (data[8] * values[1]) + (data[9] * values[5]) + (data[10] * values[9]) + (data[11] * values[13]);
         temp[10] = (data[8] * values[2]) + (data[9] * values[6]) + (data[10] * values[10]) + (data[11] * values[14]);
         temp[11] = (data[8] * values[3]) + (data[9] * values[7]) + (data[10] * values[11]) + (data[11] * values[15]);
         temp[12] = (data[12] * values[0]) + (data[13] * values[4]) + (data[14] * values[8]) + (data[15] * values[12]);
         temp[13] = (data[12] * values[1]) + (data[13] * values[5]) + (data[14] * values[9]) + (data[15] * values[13]);
         temp[14] = (data[12] * values[2]) + (data[13] * values[6]) + (data[14] * values[10]) + (data[15] * values[14]);
         temp[15] = (data[12] * values[3]) + (data[13] * values[7]) + (data[14] * values[11]) + (data[15] * values[15]);
         Array.Copy(temp, data, 16);
      }

      //============================================================
      // <T>矩阵相乘更新数据。</T>
      //
      // @param pMatrix 源矩阵
      //============================================================
      public void Append(SFloatMatrix matrix) {
         AppendData(matrix.data);
      }

      //============================================================
      // <T>解析数据。</T>
      //
      // 00 01 02 03
      // 04 05 06 07
      // 08 09 10 11
      // 12 13 14 15
      //============================================================
      public void Parse() {
         tx = data[12];
         ty = data[13];
         tz = data[14];
         sx = (float)Math.Sqrt(data[0] * data[0] + data[4] * data[4] + data[8] * data[8]);
         sy = (float)Math.Sqrt(data[1] * data[1] + data[5] * data[5] + data[9] * data[9]);
         sz = (float)Math.Sqrt(data[2] * data[2] + data[6] * data[6] + data[10] * data[10]);

         //sx = (float)Math.Sqrt(data[0] * data[0] + data[1] * data[1] + data[2] * data[2]);
         //float rsy = -data[2] / sx;
         //ry = (float)Math.Asin(rsy);
         //float rcy = (float)Math.Cos(ry);
         //float rsz = data[1] / (rcy * sx);
         //rz = (float)Math.Asin(rsz);
         //double rcx = (data[5] * rcy - data[6] * rsy * rsz) / (data[6] * Math.Cos(rz));
         //rx = (float)Math.Acos(rcx);
         //float rsx = (float)Math.Sin(rx);
         //sy = data[6] / (rsx * rcy);
         //sz = (float)(data[10] / (rcx * rcy));
         //sx = (float)Math.Sqrt(data[0] * data[0] + data[1] * data[1] + data[2] * data[2]);
         //rz = (float)Math.Atan(data[1] / data[0]);
         //ry = (float)Math.Atan(-data[2] / data[0] * Math.Cos(rz));
         //sx = (float)(-data[2] / Math.Sin(ry));
         //sy = (float)Math.Sqrt(data[4] * data[4] + data[5] * data[5] + data[6] * data[6]);
         //sz = (float)Math.Sqrt(data[8] * data[8] + data[9] * data[9] + data[10] * data[10]);
         //ry = (float)Math.Asin(-data[3]);
         //rx = (float)Math.Asin(data[6] / Math.Cos(ry));
         //rz = (float)Math.Asin(data[1] / Math.Cos(ry));
      }

      //============================================================
      // <T>更形矩阵4x4变换。</T>
      //============================================================
      public void Update() {
         float rcx = (float)Math.Cos(rx);
         float rsx = (float)Math.Sin(rx);
         float rcy = (float)Math.Cos(ry);
         float rsy = (float)Math.Sin(ry);
         float rcz = (float)Math.Cos(rz);
         float rsz = (float)Math.Sin(rz);
         data[ 0] = rcy * rcz * sx;
         data[ 1] = rcy * rsz * sx;
         data[ 2] = -rsy * sx;
         data[ 3] = 0;
         data[ 4] = (rsx * rsy * rcz - rcx * rsz) * sy;
         data[ 5] = (rsx * rsy * rsz + rcx * rcz) * sy;
         data[ 6] = rsx * rcy * sy;
         data[ 7] = 0;
         data[ 8] = (rcx * rsy * rcz + rsx * rsz) * sz;
         data[ 9] = (rcx * rsy * rsz - rsx * rcz) * sz;
         data[10] = rcx * rcy * sz;
         data[11] = 0;
         data[12] = tx;
         data[13] = ty;
         data[14] = tz;
         data[15] = 1;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadSingleConfig(FXmlNode xconfig) {
         // 读取设置
         if (xconfig.Contains("translation")) {
            SFloatVector3 translation = new SFloatVector3();
            translation.Parse(xconfig.Get("translation"));
            tx = translation.X;
            ty = translation.Y;
            tz = translation.Z;
         }
         if (xconfig.Contains("rotation")) {
            SFloatVector3 rotation = new SFloatVector3();
            rotation.Parse(xconfig.Get("rotation"));
            rx = rotation.X;
            ry = rotation.Y;
            rz = rotation.Z;
         }
         if (xconfig.Contains("scale")) {
            SFloatVector3 scale = new SFloatVector3();
            scale.Parse(xconfig.Get("scale"));
            sx = scale.X;
            sy = scale.Y;
            sz = scale.Z;
         }
         // 更新数据
         Update();
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadSimpleConfig(FXmlNode xconfig) {
         tx = xconfig.GetFloat("tx");
         ty = xconfig.GetFloat("ty");
         tz = xconfig.GetFloat("tz");
         rx = xconfig.GetFloat("rx");
         ry = xconfig.GetFloat("ry");
         rz = xconfig.GetFloat("rz");
         sx = xconfig.GetFloat("sx");
         sy = xconfig.GetFloat("sy");
         sz = xconfig.GetFloat("sz");
         Update();
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadSimpleDegreeConfig(FXmlNode xconfig) {
         tx = xconfig.GetFloat("tx");
         ty = xconfig.GetFloat("ty");
         tz = xconfig.GetFloat("tz");
         rx = xconfig.GetFloat("rx") * RFloat.DegreeRate;
         ry = xconfig.GetFloat("ry") * RFloat.DegreeRate;
         rz = xconfig.GetFloat("rz") * RFloat.DegreeRate;
         sx = xconfig.GetFloat("sx");
         sy = xconfig.GetFloat("sy");
         sz = xconfig.GetFloat("sz");
         Update();
      }

      //============================================================
      // <T>保存配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveSimpleConfig(FXmlNode xconfig) {
         xconfig.Set("tx", tx);
         xconfig.Set("ty", ty);
         xconfig.Set("tz", tz);
         xconfig.Set("rx", rx);
         xconfig.Set("ry", ry);
         xconfig.Set("rz", rz);
         xconfig.Set("sx", sx);
         xconfig.Set("sy", sy);
         xconfig.Set("sz", sz);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveSingleConfig(FXmlNode xconfig) {
         xconfig.Set("translation", tx + "," + ty + "," + tz);
         xconfig.Set("rotation", rx + "," + ry + "," + rz);
         xconfig.Set("scale", sx + "," + sy + "," + sz);
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(tx);
         output.WriteFloat(ty);
         output.WriteFloat(tz);
         output.WriteFloat(rx);
         output.WriteFloat(ry);
         output.WriteFloat(rz);
         output.WriteFloat(sx);
         output.WriteFloat(sy);
         output.WriteFloat(sz);
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeData(IOutput output) {
         for(int n = 0; n < 16; n++){
            output.WriteFloat(data[n]);
         }
      }
   }
}
