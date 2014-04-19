using System;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Content3d.Resource.Material;

namespace MO.Content3d.Resource.Model
{
   //============================================================
   // <T>模型材质集合。</T>
   //============================================================
   public class FDrModelMaterialCollection : FObject, IDisposable
   {
      protected FDrModel _model;

      protected FDictionary<FDrModelMaterial> _names = new FDictionary<FDrModelMaterial>();

      protected FDictionary<FDrModelMaterial> _dataNames = new FDictionary<FDrModelMaterial>();

      //============================================================
      // <T>构造模型材质集合。</T>
      //============================================================
      public FDrModelMaterialCollection(FDrModel model) {
         _model = model;
      }

      //============================================================
      public FDictionary<FDrModelMaterial> Names {
         get { return _names; }
      }

      //============================================================
      public FDictionary<FDrModelMaterial> DataNames {
         get { return _dataNames; }
      }

      //============================================================
      public FDrModelMaterial FindByName(string name){
         return _names.Find(name);
      }

      //============================================================
      public void LoadModelConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Material")) {
               // 读取材质信息
               FDrModelMaterial material = new FDrModelMaterial();
               material.LoadModelConfig(xnode);
               _dataNames.Set(material.DataName, material);
               // 读取子材质
               foreach(FXmlNode xsubnode in xnode.Nodes) {
                  if(xsubnode.IsName("Material")) {
                     LoadModelConfig(xsubnode);
                  }
               }
            }
         }
      }

      //============================================================
      public void SaveConfig(FXmlNode config) {
         foreach(INamePair<FDrMaterialGroup> pair in _names) {
            FXmlNode materialNode = config.CreateNode("Material");
            pair.Value.SaveConfig(materialNode);
         }
         config.SortByAttribute("name");
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _names.Clear();
         _dataNames.Clear();
      }
   }
}
