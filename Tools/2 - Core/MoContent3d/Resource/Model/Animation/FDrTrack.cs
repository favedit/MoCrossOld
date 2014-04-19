using System.Collections.Generic;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Resource.Common;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>模型跟踪信息。</T>
   //============================================================
   public class FDrTrack : IComparer<FDrTrack>
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrTrack));

      // 模型
      protected FDrModel _model;

      // 骨头编号
      protected int _boneId;

      // 配置骨骼缩放
      protected int _optionBoneScale = EDrFlag.Inherit;

      // 骨头
      protected FDrBone _bone;

      // 对象矩阵
      protected FDrMatrix _objectMatrix = new FDrMatrix();

      // 本地矩阵
      protected FDrMatrix _localMatrix = new FDrMatrix();

      // 世界矩阵
      protected FDrMatrix _worldMatrix = new FDrMatrix();

      // 帧集合
      protected FVector<FDrFrame> _frameList = new FVector<FDrFrame>();

      //============================================================
      // <T>构造模型跟踪信息。</T>
      //============================================================
      public FDrTrack(FDrModel model) {
         _model = model;
      }

      //============================================================
      // <T>获得或设置骨骼缩放。</T>
      //============================================================
      public int OptionBoneScale {
         get { return _optionBoneScale; }
         set { _optionBoneScale = value; }
      }
      
      //============================================================
      public int Compare(FDrTrack source, FDrTrack target) {
         return source.Bone.AdjustId - target.Bone.AdjustId;
      }

      //============================================================
      public FDrBone Bone {
         get { return _bone; }
      }

      //============================================================
      public int AdjustId {
         get{
            int adjustId = 0;
            if (null != _bone) {
               adjustId = _bone.AdjustId;
            }
            return adjustId;
         }
      }

      //============================================================
      public FVector<FDrFrame> FrameList {
         get { return _frameList; }
      }

      //============================================================
      public int FrameTick {
         get {
            if (_frameList.Count > 1) {
               int tick0 = _frameList.Get(0).Tick;
               int tick1 = _frameList.Get(1).Tick;
               return tick1 - tick0;
            }
            return 0;
         }
      }

      //============================================================
      // <T>测试矩阵是否有缩放。</T>
      //============================================================
      public bool TestScale() {
         foreach (FDrFrame frame in _frameList) {
            if (frame.TestScale()) {
               return true;
            }
         }
         return false;
      }
      
      //============================================================
      public void LoadFrameListConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Frame")) {
               FDrFrame frame = new FDrFrame();
               frame.LoadModelConfig(node);
               _frameList.Push(frame);
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadGeomeryModelConfig(FXmlNode xconfig) {
         // 读取属性信息
         _boneId = 0;
         // 读取所有子节点
         foreach (FXmlNode xnode in xconfig.Nodes) {
            switch (xnode.Name) {
               case "LocalMatrix":
                  _localMatrix.LoadSimpleAngleConfig(xnode);
                  break;
               case "WorldMatrix":
                  _worldMatrix.LoadSimpleAngleConfig(xnode);
                  break;
               case "Types":
                  break;
               case "Frames":
               case "FrameCollection":
                  LoadFrameListConfig(xnode);
                  break;
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode config) {
         // 读取属性信息
         _boneId = config.GetInteger("bone_id");
         _bone = _model.Skeleton.Bones.Get(_boneId);
         // 读取所有子节点
         foreach (FXmlNode node in config.Nodes) {
            switch (node.Name) {
               case "LocalMatrix":
                  _localMatrix.LoadSimpleAngleConfig(node);
                  break;
               case "WorldMatrix":
                  _worldMatrix.LoadSimpleAngleConfig(node);
                  break;
               case "Types":
                  break;
               case "Frames":
               case "FrameCollection":
                  LoadFrameListConfig(node);
                  break;
            }
         }
      }
      
      //============================================================
      // <T>保存配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveSimpleConfig(FXmlNode config) {
         config.Set("id", _bone.AdjustId);
         config.Set("name", _bone.Name);
         config.Set("bone_id", _bone.Id);
         config.Set("frame_count", _frameList.Count);
      }

      //============================================================
      public void DataUnserialize(IInput input) {
         // 读取属性信息
         _boneId = 0;
         _objectMatrix.UnserializeAngle(input);
         _localMatrix.UnserializeAngle(input);
         _worldMatrix.UnserializeAngle(input);
         // 读取所有子节点
         int count = input.ReadInt32();
         for(int n = 0; n < count; n ++) {
            FDrFrame frame = new FDrFrame();
            frame.DataUnserialize(input);
            _frameList.Push(frame);
         }
         // 测试是否含有骨骼缩放
         _optionBoneScale = TestScale() ? EDrFlag.Yes : EDrFlag.Inherit;
      }

      //============================================================
      public void Serialize(IOutput output) {
         // _logger.Debug(this, "Serialize", "Serialize track success. (bone_id={0}, frame_count={1})", _bone.AdjustId, _frameList.Count);
         // 输出骨骼信息
         int adjustId = 0;
         if (null != _bone) {
            adjustId = _bone.AdjustId;
         }
         output.WriteUint8((byte)_optionBoneScale);
         output.WriteUint8((byte)adjustId);
         output.WriteUint16((ushort)FrameTick);
         // 输出矩阵
         _worldMatrix.Serialize(output);
         // 输出所有帧信息
         output.WriteInt16((short)_frameList.Count);
         foreach (FDrFrame frame in _frameList) {
            frame.Serialize(output);
         }
      }
   }
}
