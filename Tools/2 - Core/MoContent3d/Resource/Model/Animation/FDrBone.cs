using System.Collections.Generic;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Model.Animation
{
   //============================================================
   // <T>构造模型骨头。</T>
   //============================================================
   public class FDrBone : FObject, IComparer<FDrBone>
   {
      protected FDrModel _model;

      protected int _id;

      protected int _adjustId;

      protected string _name;

      protected FVector<FDrBone> _children = new FVector<FDrBone>();

      //============================================================
      // <T>模型骨头。</T>
      //============================================================
      public FDrBone(FDrModel model) {
         _model = model;
      }

      //============================================================
      public int Compare(FDrBone source, FDrBone target) {
         return source.AdjustId - target.AdjustId;
      }

      //============================================================
      public int Id {
         get { return _id; }
      }

      //============================================================
      public int AdjustId {
         get { return _adjustId; }
         set { _adjustId = value; }
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public FVector<FDrBone> Children {
         get { return _children; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode config) {
         // 读取基本属性
         _id = config.GetInteger("bone_id");
         _name = config.Get("name");
         _model.Skeleton.Bones.Set(_id, this);
         // 读取子节点
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Bone")) {
               FDrBone bone = new FDrBone(_model);
               bone.LoadModelConfig(node);
               _children.Push(bone);
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         xconfig.Set("id", _id);
         xconfig.Set("adjust_id", _adjustId);
         xconfig.Set("children_count", _children.Count);
      }

      //============================================================
      public void Serialize(IOutput output) {
         // 保存编号
         output.WriteInt8((sbyte)_adjustId);
         // 保存所有子
         output.WriteInt8((sbyte)_children.Count);
         foreach (FDrBone bone in _children) {
            bone.Serialize(output);
         }
      }
   }
}
