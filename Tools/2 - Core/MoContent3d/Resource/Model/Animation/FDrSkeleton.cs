using System;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>模型骨骼。</T>
   //============================================================
   public class FDrSkeleton : FObject, IDisposable
   {
      protected FDrModel _model;

      protected FVector<FDrBone> _roots = new FVector<FDrBone>();

      protected FIntDictionary<FDrBone> _bones = new FIntDictionary<FDrBone>();

      protected FVector<FDrBone> _adjustBones = new FVector<FDrBone>();

      //============================================================
      // <T>构造模型骨骼。</T>
      //============================================================
      public FDrSkeleton(FDrModel model) {
         _model = model;
      }

      //============================================================
      public FVector<FDrBone> Roots {
         get { return _roots; }
      }

      //============================================================
      public FIntDictionary<FDrBone> Bones {
         get { return _bones; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode xconfig) {
         // 读取根骨骼
         foreach (FXmlNode node in xconfig.Nodes) {
            if (node.IsName("Bone")) {
               FDrBone bone = new FDrBone(_model);
               bone.LoadModelConfig(node);
               _roots.Push(bone);
            }
         }
         // 调整骨骼编号
         int count = _bones.Count;
         for(int n = 0; n < count; n++){
            FDrBone bone = _bones.Value(n);
            _adjustBones.Push(bone);
            bone.AdjustId = n;
         }
      }

      //============================================================
      public void SaveSimpleConfig(FXmlNode config) {
         config.Set("root_count", _roots.Count);
         config.Set("bone_count", _bones.Count);
      }
      
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt8((sbyte)_roots.Count);
         foreach (FDrBone bone in _roots) {
            bone.Serialize(output);
         }
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _roots.Clear();
         _bones.Clear();
         _adjustBones.Clear();
      }
   }
}
