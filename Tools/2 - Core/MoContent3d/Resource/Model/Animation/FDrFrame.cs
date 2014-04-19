using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Resource.Common;
using System;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>模型帧信息。</T>
   //============================================================
   public class FDrFrame : FObject
   {
      // 索引
      protected int _index;

      // 时刻
      protected int _tick;

      // 对象矩阵
      protected FDrMatrix _objectMatrix = new FDrMatrix();

      // 本地矩阵
      protected FDrMatrix _localMatrix = new FDrMatrix();

      // 世界矩阵
      protected FDrMatrix _worldMatrix = new FDrMatrix();

      //============================================================
      // <T>构造模型帧信息。</T>
      //============================================================
      public FDrFrame(){
      }

      //============================================================
      // <T>获得或设置索引。</T>
      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      // <T>获得时刻。</T>
      //============================================================
      public int Tick {
         get { return _tick; }
      }

      //============================================================
      // <T>获得对象矩阵。</T>
      //============================================================
      public FDrMatrix ObjectMatrix {
         get { return _objectMatrix; }
      }

      //============================================================
      // <T>获得对象矩阵。</T>
      //============================================================
      public FDrMatrix LocalMatrix {
         get { return _localMatrix; }
      }

      //============================================================
      // <T>获得世界矩阵。</T>
      //============================================================
      public FDrMatrix WorldMatrix {
         get { return _worldMatrix; }
      }

      //============================================================
      // <T>测试矩阵是否有缩放。</T>
      //============================================================
      public bool TestScale() {
         int scaleX = (int)(Math.Abs(_worldMatrix.Scale.X - 1.0f) * 10000);
         if (scaleX > 0) {
            return true;
         }
         int scaleY = (int)(Math.Abs(_worldMatrix.Scale.Y - 1.0f) * 10000);
         if (scaleY > 0) {
            return true;
         }
         int scaleZ = (int)(Math.Abs(_worldMatrix.Scale.Z - 1.0f) * 10000);
         if (scaleZ > 0) {
            return true;
         }
         return false;
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode config) {
         _tick = config.GetInteger("time");
         foreach (FXmlNode node in config.Nodes) {
            switch (node.Name) {
               case "LocalMatrix":
                  _localMatrix.LoadSimpleAngleConfig(node);
                  break;
               case "WorldMatrix":
                  _worldMatrix.LoadSimpleAngleConfig(node);
                  break;
            }
         }
      }

      //============================================================
      // <T>从输入流反序列化数据内容。</T>
      //
      // @param input 输入流
      //============================================================
      public void DataUnserialize(IInput input) {
         _tick = input.ReadInt32();
         _objectMatrix.UnserializeAngle(input);
         _localMatrix.UnserializeAngle(input);
         _worldMatrix.UnserializeAngle(input);
      }
      
      //============================================================
      // <T>序列化数据内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteUint16((ushort)_tick);
         _worldMatrix.Serialize(output);
      }
   }
}
