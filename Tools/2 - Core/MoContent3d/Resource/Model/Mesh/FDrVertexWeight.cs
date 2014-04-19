using System.Collections.Generic;
using MO.Common.Content;
using MO.Common.IO;

namespace MO.Content3d.Resource.Model.Mesh
{
   public class FDrVertexWeight : IComparer<FDrVertexWeight>
   {
      public static FDrVertexWeight Comparer = new FDrVertexWeight();

      protected int _boneId;

      protected float _weight;

      //============================================================
      public int Compare(FDrVertexWeight source, FDrVertexWeight target) {
         if ((null != source) && (null != target)) {
            if (float.IsNaN(source.Weight) || float.IsNaN(target.Weight)) {
               return 0;
            }
            return (int)((target.Weight - source.Weight) * 10000);
         }
         return 0;
      }

      //============================================================
      public int BoneId {
         get { return _boneId; }
      }

      //============================================================
      public float Weight {
         get { return _weight; }
         set { _weight = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _boneId = config.GetInteger("bone_id");
         _weight = config.GetFloat("weight");
      }

      //============================================================
      public void Unserialize(IInput input) {
         _boneId = input.ReadInt32();
         _weight = input.ReadFloat();
      }
         
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(_boneId);
         output.WriteFloat(_weight);
      }
   }
}
