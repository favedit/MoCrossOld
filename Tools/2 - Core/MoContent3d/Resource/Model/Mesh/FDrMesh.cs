using System;
using System.Windows.Forms;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core;

namespace MO.Content3d.Resource.Model.Mesh
{
   //============================================================
   // <T>网格。</T>
   //============================================================
   public class FDrMesh : FObject, IDisposable
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrMesh));

      // 模型对象
      protected FDrModel _model;

      // 几何体集合
      protected FDictionary<FDrGeometry> _geometryDictionary = new FDictionary<FDrGeometry>();

      // 临时几何体集合
      protected FDictionary<FDrGeometry> _geometryTemps = new FDictionary<FDrGeometry>();

      //============================================================
      // <T>构造网格。</T>
      //============================================================
      public FDrMesh(FDrModel model) {
         _model = model;
      }

      //============================================================
      // <T>获得几何体字典。</T>
      //
      // @return 几何体字典
      //============================================================
      public FDictionary<FDrGeometry> GeometryDictionary {
         get { return _geometryDictionary; }
      }

      //============================================================
      // <T>根据名称查找几何体。</T>
      //
      // @return 几何体
      //============================================================
      public FDrGeometry Find(string name) {
         return _geometryDictionary.Find(name);
      }

      //============================================================
      // <T>根据名称获得几何体。</T>
      //
      // @return 几何体
      //============================================================
      public FDrGeometry Get(string name) {
         return _geometryDictionary.Get(name);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Geometry")) {
               string geometryName = xnode.Get("name");
               // 查找几何体
               FDrGeometry geometry = _geometryDictionary.Find(geometryName);
               if (geometry == null) {
                  RMoCore.TrackConsole.Write(this, "LoadConfig", "Geomery is not exists in model. (model={0}, geometry={1}", _model.Name, geometryName);
                  // 加载模型信息
                  geometry = new FDrGeometry(_model);
                  geometry.LoadModelConfig(xnode);
                  _geometryDictionary.Set(geometryName, geometry);
               } else {
                  // 加载模型信息
                  geometry.LoadModelConfig(xnode);
               }
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 读取列表
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Geometry")) {
               // 获得有效性
               bool valid = false;
               if(xnode.Contains("valid")) {
                  valid = xnode.GetBoolean("valid");
               } else {
                  valid = xnode.GetBoolean("is_valid");
               }
               // 读取属性
               if(valid) {
                  // 检查存在性
                  string geometryName = xnode.Get("name");
                  if(_geometryDictionary.Contains(geometryName)) {
                     RMoCore.TrackConsole.Write(this, "LoadConfig", "Model geometry name is already exists. (model={0}, geometry={1})", _model.Name, geometryName);
                  }
                  // 创建几何体
                  FDrGeometry geometry = new FDrGeometry(_model);
                  geometry.LoadConfig(xnode);
                  geometry.Index = _geometryDictionary.Count;
                  _geometryDictionary.Set(geometryName, geometry);
               }
            }
         }
      }

      //============================================================
      // <T>保存全部配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig) {
         foreach(INamePair<FDrGeometry> pair in _geometryDictionary) {
            pair.Value.SaveConfig(xconfig.CreateNode("Geometry"));
         }
         xconfig.SortByAttribute("name");
      }

      //============================================================
      // <T>保存全部配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         foreach (INamePair<FDrGeometry> pair in _geometryDictionary) {
            pair.Value.SaveInfoConfig(xconfig.CreateNode("Geometry"));
         }
         xconfig.SortByAttribute("name");
      }

      //============================================================
      public void ExportConfig(FXmlNode config) {
         foreach(INamePair<FDrGeometry> pair in _geometryDictionary) {
            FXmlNode node = config.CreateNode("Geometry");
            pair.Value.ExportConfig(node);
         }
      }

      //============================================================    
      public bool DataUnserialize(FByteStream input) {
         int count = input.ReadInt32();
         for(int n = 0; n < count; n++) {
            string name = input.ReadStringA16();
            if(_geometryDictionary.Contains(name)) {
               FDrGeometry geometry = _geometryDictionary.Get(name);
               if (!geometry.DataUnserialize(input)) {
                  return false;
               }
            } else {
               FDrGeometry geometry = new FDrGeometry(_model);
               if (!geometry.DataUnserialize(input)) {
                  return false;
               }
               RMoCore.TrackConsole.Write(this, "DataUnserialize", "Can't find geometry config in model. (model={0}, geometry={1})", _model.Code, name);
            }
         }
         return true;
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt16((short)_geometryDictionary.Count);
         foreach (FDrGeometry geometry in _geometryDictionary.Values) {
            geometry.Serialize(output);
         }
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         _geometryDictionary.Clear();
         _geometryTemps.Clear();
      }
   }
}
