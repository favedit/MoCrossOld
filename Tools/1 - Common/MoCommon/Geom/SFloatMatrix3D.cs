using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using System;

namespace MO.Common.Geom
{
   //============================================================
   // <T>4x4矩阵。</T>
   //============================================================
   public class SFloatMatrix3D
   {
      public bool dirty;

      public float tx = 0.0f;

      public float ty = 0.0f;

      public float tz = 0.0f;

      public float rx = 0.0f;

      public float ry = 0.0f;

      public float rz = 0.0f;

      public float sx = 1.0f;

      public float sy = 1.0f;

      public float sz = 1.0f;

      public float[] data = new float[16];

      //============================================================
      public SFloatMatrix3D(){
         Identity();
      }
   
      //============================================================
      // <T>设置平移内容。</T>
      //============================================================
      public void Translate(float x, float y, float z){
         tx = x;
         ty = y;
         tz = z;
         dirty = true;
      }
      
      //============================================================
      // <T>设置旋转内容。</T>
      //============================================================
      public void Rotation(float x, float y, float z){
         rx = x;
         ry = y;
         rz = z;
         dirty = true;
      }
      
      //============================================================
      // <T>设置缩放内容。</T>
      //============================================================
      public void Scale(float x, float y, float z){
         sx = x;
         sy = y;
         sz = z;
         dirty = true;
      }
      
      //============================================================
      // <T>判断矩阵是否相等。</T>
      //============================================================
      public bool EqualsData(float[] values){
         for(int n = 0; n < 16; n++){
            if(data[n] != values[n]){
               return false;
            }
         }
         return true;
      }
      
      //============================================================
      // <T>判断矩阵是否相等。</T>
      //============================================================
      public bool Equals(SFloatMatrix3D value){
         return EqualsData(value.data);
      }
      
      //============================================================
      // <T>接收矩阵数据。</T>
      //============================================================
      public void AssignData(float[] values){
         for (int n = 0; n < 16; n++){
            data[n] = values[n];
         }
      }

      //============================================================
      // <T>接收矩阵。</T>
      //============================================================
      public void Assign(SFloatMatrix3D value){
         tx = value.tx;
         ty = value.ty;
         tz = value.tz;
         rx = value.rx;
         ry = value.ry;
         rz = value.rz;
         sx = value.sx;
         sy = value.sy;
         sz = value.sz;
         //------------------------------------------------------------
         AssignData(value.data);
      }
      
      //============================================================
      // <T>接收数据。</T>
      // <P>返回值决定内部是否发生变化。</P>
      //============================================================
      public bool AttachData(float[] values){
         for(int n = 0; n < 16; n++){
            if(data[n] != values[n]){
               for (int i = 0; i < 16; i++){
                  data[i] = values[i];
               }
               return true;
            }
         }
         return false;
      }

      //============================================================
      public bool Attach(SFloatMatrix3D value){
         return AttachData(value.data);
      }
      
      //============================================================
      // <T>更新内容到数据中。</T>
      //============================================================
      public void Identity(){
         for(int n = 0; n < 16; n++){
            data[n] = (0 == (n % 5)) ? 1.0f : 0.0f;
         }
      }
      
      //============================================================
      // <T>设置平移内容。</T>
      //============================================================
      public void Transform(float[] outputData, float[] inputData, int offset, int count){
         for(int n = offset; n < count; n += 3){
            outputData[n    ] = (inputData[n] * data[0]) + (inputData[n + 1] * data[4]) +(inputData[n + 2] * data[ 8]) + data[12];
            outputData[n + 1] = (inputData[n] * data[1]) + (inputData[n + 1] * data[5]) +(inputData[n + 2] * data[ 9]) + data[13];
            outputData[n + 2] = (inputData[n] * data[2]) + (inputData[n + 1] * data[6]) +(inputData[n + 2] * data[10]) + data[14];
         }
      }

      //============================================================
      // <T>设置平移内容。</T>
      //============================================================
      public void TransformPoint3(SFloatPoint3 output, SFloatPoint3 input) {
         output.X = (input.X * data[0]) + (input.Y * data[4]) +(input.Z * data[ 8]) + data[12];
         output.Y = (input.X * data[1]) + (input.Y * data[5]) +(input.Z * data[ 9]) + data[13];
         output.Z = (input.X * data[2]) + (input.Y * data[6]) +(input.Z * data[10]) + data[14];
      }

      //============================================================
      // <T>设置平移内容。</T>
      //============================================================
      public void Transform3x3Point3(SFloatPoint3 output, SFloatPoint3 input) {
         output.X = (input.X * data[0]) + (input.Y * data[4]) +(input.Z * data[ 8]);
         output.Y = (input.X * data[1]) + (input.Y * data[5]) +(input.Z * data[ 9]);
         output.Z = (input.X * data[2]) + (input.Y * data[6]) +(input.Z * data[10]);
      }

      //============================================================
      // <T>设置平移内容。</T>
      //============================================================
      public void Transform3x3Vector3(SFloatVector3 output, SFloatVector3 input) {
         output.X = (input.X * data[0]) + (input.Y * data[4]) + (input.Z * data[ 8]);
         output.Y = (input.X * data[1]) + (input.Y * data[5]) + (input.Z * data[ 9]);
         output.Z = (input.X * data[2]) + (input.Y * data[6]) + (input.Z * data[10]);
      }

      //============================================================
      // <T>设置平移内容。</T>
      //============================================================
      public void TransformPosition3x4(SFloatPoint3 input, SFloatPoint4 output) {
         output.X = (input.X * data[0]) + (input.Y * data[4]) + (input.Z * data[ 8]) + data[12];
         output.Y = (input.X * data[1]) + (input.Y * data[5]) + (input.Z * data[ 9]) + data[13];
         output.Z = (input.X * data[2]) + (input.Y * data[6]) + (input.Z * data[10]) + data[14];
         output.W = (          data[3]) + (          data[7]) + (          data[11]) + data[15];
         // output.align();
      }

      //============================================================
      //  0  1  2  3
      //  4  5  6  7
      //  8  9 10 11
      // 12 13 14 15
      //============================================================
      public void AppendData(float[] values){
         // 矩阵计算
         float v00 = (data[ 0] * values[0]) + (data[ 1] * values[4]) + (data[ 2] * values[ 8]) + (data[ 3] * values[12]);
         float v01 = (data[ 0] * values[1]) + (data[ 1] * values[5]) + (data[ 2] * values[ 9]) + (data[ 3] * values[13]);
         float v02 = (data[ 0] * values[2]) + (data[ 1] * values[6]) + (data[ 2] * values[10]) + (data[ 3] * values[14]);
         float v03 = (data[ 0] * values[3]) + (data[ 1] * values[7]) + (data[ 2] * values[11]) + (data[ 3] * values[15]);
         float v04 = (data[ 4] * values[0]) + (data[ 5] * values[4]) + (data[ 6] * values[ 8]) + (data[ 7] * values[12]);
         float v05 = (data[ 4] * values[1]) + (data[ 5] * values[5]) + (data[ 6] * values[ 9]) + (data[ 7] * values[13]);
         float v06 = (data[ 4] * values[2]) + (data[ 5] * values[6]) + (data[ 6] * values[10]) + (data[ 7] * values[14]);
         float v07 = (data[ 4] * values[3]) + (data[ 5] * values[7]) + (data[ 6] * values[11]) + (data[ 7] * values[15]);
         float v08 = (data[ 8] * values[0]) + (data[ 9] * values[4]) + (data[10] * values[ 8]) + (data[11] * values[12]);
         float v09 = (data[ 8] * values[1]) + (data[ 9] * values[5]) + (data[10] * values[ 9]) + (data[11] * values[13]);
         float v10 = (data[ 8] * values[2]) + (data[ 9] * values[6]) + (data[10] * values[10]) + (data[11] * values[14]);
         float v11 = (data[ 8] * values[3]) + (data[ 9] * values[7]) + (data[10] * values[11]) + (data[11] * values[15]);
         float v12 = (data[12] * values[0]) + (data[13] * values[4]) + (data[14] * values[ 8]) + (data[15] * values[12]);
         float v13 = (data[12] * values[1]) + (data[13] * values[5]) + (data[14] * values[ 9]) + (data[15] * values[13]);
         float v14 = (data[12] * values[2]) + (data[13] * values[6]) + (data[14] * values[10]) + (data[15] * values[14]);
         float v15 = (data[12] * values[3]) + (data[13] * values[7]) + (data[14] * values[11]) + (data[15] * values[15]);
         // 复制内容
         data[ 0] = v00;
         data[ 1] = v01;
         data[ 2] = v02;
         data[ 3] = v03;
         data[ 4] = v04;
         data[ 5] = v05;
         data[ 6] = v06;
         data[ 7] = v07;
         data[ 8] = v08;
         data[ 9] = v09;
         data[10] = v10;
         data[11] = v11;
         data[12] = v12;
         data[13] = v13;
         data[14] = v14;
         data[15] = v15;
      }
      
      //============================================================
      // <T>更新内容到数据中。</T>
      //  0  1  2  3
      //  4  5  6  7
      //  8  9 10 11
      // 12 13 14 15
      //============================================================
      public void Append(SFloatMatrix3D value){
         AppendData(value.data);
      }
      
      //============================================================
      // <T>追加X轴旋转。</T>
      //  1 0 0 0
      //  0 1 0 0
      //  0 0 1 0
      //  x y z 1 
      //============================================================
      public void AppendTranslate(float px, float py, float pz){
         float[] temp = new float[16];
         temp[ 0] = 1;
         temp[ 1] = 0;
         temp[ 2] = 0;
         temp[ 3] = 0;
         temp[ 4] = 0;
         temp[ 5] = 1;
         temp[ 6] = 0;
         temp[ 7] = 0;
         temp[ 8] = 0;
         temp[ 9] = 0;
         temp[10] = 1;
         temp[11] = 0;
         temp[12] = px;
         temp[13] = py;
         temp[14] = pz;
         temp[15] = 1;
         AppendData(temp);
      }
      
      //============================================================
      // <T>追加Y轴旋转。</T>
      //  1    0   0 0
      //  0  cos sin 0
      //  0 -sin cos 0
      //  0    0   0 1 
      //============================================================
      public void AppendRotationX(float angle){
         float sin = (float)Math.Sin(angle);
         float cos = (float)Math.Cos(angle);
         float[] temp = new float[16];
         temp[ 0] = cos;
         temp[ 1] = 0;
         temp[ 2] = sin;
         temp[ 3] = 0;
         temp[ 4] = 0;
         temp[ 5] = 1;
         temp[ 6] = 0;
         temp[ 7] = 0;
         temp[ 8] = -sin;
         temp[ 9] = 0;
         temp[10] = cos;
         temp[11] = 0;
         temp[12] = 0;
         temp[13] = 0;
         temp[14] = 0;
         temp[15] = 1;
         AppendData(temp);
      }
      
      //============================================================
      // <T>追加Y轴旋转。</T>
      //  cos   0  sin  0
      //  0     1    0  0
      //  -sin  0  cos  0
      //  0     0    0  1 
      //============================================================
      public void AppendRotationY(float angle){
         float sin = (float)Math.Sin(angle);
         float cos = (float)Math.Cos(angle);
         float[] temp = new float[16];
         temp[ 0] = cos;
         temp[ 1] = 0;
         temp[ 2] = sin;
         temp[ 3] = 0;
         temp[ 4] = 0;
         temp[ 5] = 1;
         temp[ 6] = 0;
         temp[ 7] = 0;
         temp[ 8] = -sin;
         temp[ 9] = 0;
         temp[10] = cos;
         temp[11] = 0;
         temp[12] = 0;
         temp[13] = 0;
         temp[14] = 0;
         temp[15] = 1;
         AppendData(temp);
      }
      
      //============================================================
      // <T>追加Y轴旋转。</T>
      //  cos  sin  0 0
      //  -sin cos  1 0
      //  0      0  1 0
      //  0      0  0 1 
      //============================================================
      public void AppendRotationZ(float angle){
         float sin = (float)Math.Sin(angle);
         float cos = (float)Math.Cos(angle);
         float[] temp = new float[16];
         temp[ 0] = cos;
         temp[ 1] = sin;
         temp[ 2] = 0;
         temp[ 3] = 0;
         temp[ 4] = -sin;
         temp[ 5] = cos;
         temp[ 6] = 1;
         temp[ 7] = 0;
         temp[ 8] = 0;
         temp[ 9] = 0;
         temp[10] = 1;
         temp[11] = 0;
         temp[12] = 0;
         temp[13] = 0;
         temp[14] = 0;
         temp[15] = 1;
         AppendData(temp);
      }
      
      //============================================================
      // <T>追加Y轴旋转。</T>
      //  1    0   0 0
      //  0  cos sin 0
      //  0 -sin cos 0
      //  0    0   0 1 
      //============================================================
      public void AppendRotation(float x, float y, float z){
         float rsx = (float)Math.Sin(x);
         float rcx = (float)Math.Cos(x);
         float rsy = (float)Math.Sin(y);
         float rcy = (float)Math.Cos(y);
         float rsz = (float)Math.Sin(z);
         float rcz = (float)Math.Cos(z);
         float[] temp = new float[16];
         temp[0] = rcy * rcz;
         temp[1] = rcy * rsz;
         temp[2] = -rsy;
         temp[3] = 0;
         temp[4] = rsx * rsy * rcz - rcx * rsz;
         temp[5] = rsx * rsy * rsz + rcx * rcz;
         temp[6] = rsx * rcy;
         temp[7] = 0;
         temp[8] = rcx * rsy * rcz + rsx * rsz;
         temp[9] = rcx * rsy * rsz - rsx * rcz;
         temp[10] = rcx * rcy;
         temp[11] = 0;
         temp[12] = 0;
         temp[13] = 0;
         temp[14] = 0;
         temp[15] = 1;
         dirty = false;
         AppendData(temp);
      }

      //============================================================
      // <T>追加缩放。</T>
      //  scaleX      0      0 0
      //  0      scaleY      0 0
      //  0           0 scaleZ 0
      //  0           0      0 1 
      //============================================================
      public void AppendScale(float px, float py, float pz){
         float[] temp = new float[16];
         temp[ 0] = px;
         temp[ 1] = 0;
         temp[ 2] = 0;
         temp[ 3] = 0;
         temp[ 4] = 0;
         temp[ 5] = py;
         temp[ 6] = 0;
         temp[ 7] = 0;
         temp[ 8] = 0;
         temp[ 9] = 0;
         temp[10] = pz;
         temp[11] = 0;
         temp[12] = 0;
         temp[13] = 0;
         temp[14] = 0;
         temp[15] = 1;
         AppendData(temp);
      }
      
      //============================================================
      // <T>追加缩放。</T>
      //  scaleX      0      0 0
      //  0      scaleY      0 0
      //  0           0 scaleZ 0
      //  0           0      0 1 
      //============================================================
      public void AppendScaleAll(float value){
         float[] temp = new float[16];
         temp[ 0] = value;
         temp[ 1] = 0;
         temp[ 2] = 0;
         temp[ 3] = 0;
         temp[ 4] = 0;
         temp[ 5] = value;
         temp[ 6] = 0;
         temp[ 7] = 0;
         temp[ 8] = 0;
         temp[ 9] = 0;
         temp[10] = value;
         temp[11] = 0;
         temp[12] = 0;
         temp[13] = 0;
         temp[14] = 0;
         temp[15] = 1;
         AppendData(temp);
      }

      //============================================================
      // <T>向量数乘</T>
      //
      // @params value 要相乘的向量
      //============================================================
      public void Move(SFloatVector3 value, float distince){
         tx += distince * value.X;
         ty += distince * value.Y;
         tz += distince * value.Z;
         dirty = true;
      }
      
      //============================================================
      public void MoveXZ(SFloatVector3 value, float distince){
         tx += distince * value.X;
         tz += distince * value.Z;
         dirty = true;
      }
      
      //============================================================
      public void MoveZX(SFloatVector3 value, float distince){
         tx += distince * value.Z;
         tz += distince * value.X;
         dirty = true;
      }
      
      //============================================================
      public void MoveY(float distince){
         ty += distince;
         dirty = true;
      }

      //============================================================
      // <T>解析数据到内容中。</T>
      //============================================================
      public void UpdateForce(){
         float rsx = (float)Math.Sin(rx);
         float rcx = (float)Math.Cos(rx);
         float rsy = (float)Math.Sin(ry);
         float rcy = (float)Math.Cos(ry);
         float rsz = (float)Math.Sin(rz);
         float rcz = (float)Math.Cos(rz);
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
         dirty = false;
      }
      
      //============================================================
      public void Update(){
         if(dirty){
            UpdateForce();
         }
      }
      
      //============================================================
      // <T>反序列化数据信息。</T>
      //
      // @param p:input 输入数据流
      //============================================================
      public void UpdateAll(SFloatPoint3 pt, SFloatVector3 pd, SFloatVector3 ps){
         // 读取平移信息
         tx = pt.X;
         ty = pt.Y;
         tz = pt.Z;
         // 读取旋转弧度
         rx = pd.X;
         ry = pd.Y;
         rz = pd.Z;
         // 读取缩放信息
         sx = ps.X;
         sy = ps.Y;
         sz = ps.Z;
         // 更新信息
         UpdateForce();
      }

      //============================================================
      public void Changed(){
         // serial++;
      }
      
      //============================================================
      // <T>解析数据到内容中。</T>
      //============================================================
      public void Parse(){
         if(dirty){
            dirty = false;
         }
      }
      
      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param config 设置信息
      //============================================================
      public void loadConfig(FXmlNode config){
         //// 读取坐标信息
         //String vt = config.get("translation");
         //if(vt){
         //   var vts:Array = vt.split(",");
         //   tx = parseFloat(vts[0]);
         //   ty = parseFloat(vts[1]);
         //   tz = parseFloat(vts[2]);
         //}
         //// 读取旋转信息
         //var ve:String = config.get("euler");
         //if(ve){
         //   var ves:Array = ve.split(",");
         //   rx = parseFloat(ves[0]) * RMath.DegreeRate;
         //   ry = parseFloat(ves[1]) * RMath.DegreeRate;
         //   rz = parseFloat(ves[2]) * RMath.DegreeRate;
         //}
         //// 读取旋转信息
         //var vr:String = config.get("rotation");
         //if(vr){
         //   var vrs:Array = vr.split(",");
         //   rx = parseFloat(vrs[0]);
         //   ry = parseFloat(vrs[1]);
         //   rz = parseFloat(vrs[2]);
         //}
         //// 读取缩放信息
         //var vs:String = config.get("scale");
         //if(vs){
         //   var vss:Array = vs.split(",");
         //   sx = parseFloat(vss[0]);
         //   sy = parseFloat(vss[1]);
         //   sz = parseFloat(vss[2]);
         //}
         //// 更新信息
         //updateForce();
      }
      
      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param config 设置信息
      //============================================================
      public void SaveConfig(FXmlNode config){
         Parse();
         config.Set("translation", tx + "," + ty + "," + tz);
         config.Set("rotation", rx + "," + ry + "," + rz);
         config.Set("scale", sx + "," + sy + "," + sz);
      }
      
      //============================================================
      // <T>序列化数据信息。</T>
      //
      // @param p:output 输出数据流
      //============================================================
      public void Serialize(IOutput output){
         Parse();
         // 存储平移信息
         output.WriteFloat(tx);
         output.WriteFloat(ty);
         output.WriteFloat(tz);
         // 存储旋转弧度
         output.WriteFloat(rx);
         output.WriteFloat(ry);
         output.WriteFloat(rz);
         // 存储缩放信息
         output.WriteFloat(sx);
         output.WriteFloat(sy);
         output.WriteFloat(sz);
      }

      //============================================================
      // <T>序列化数据信息。</T>
      //
      // @param p:output 输出数据流
      //============================================================
      public void SerializeData(IOutput output){
         for(int n = 0; n < 16; n++){
            output.WriteFloat(data[n]);
         }
      }

      //============================================================
      // <T>反序列化数据信息。</T>
      //
      // @param p:input 输入数据流
      //============================================================
      public void Unserialize(IInput input){
         // 读取平移信息
         tx = input.ReadFloat();
         ty = input.ReadFloat();
         tz = input.ReadFloat();
         // 读取旋转弧度
         rx = input.ReadFloat();
         ry = input.ReadFloat();
         rz = input.ReadFloat();
         // 读取缩放信息
         sx = input.ReadFloat();
         sy = input.ReadFloat();
         sz = input.ReadFloat();
         // 更新信息
         UpdateForce();
      }
   }
}
