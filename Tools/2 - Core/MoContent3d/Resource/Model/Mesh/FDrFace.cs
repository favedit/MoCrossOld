using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Model.Mesh {
   //============================================================
   // <T>面信息。</T>
   //============================================================
   public class FDrFace {
      public int Index;

      protected int _faceIndex;

      protected int _flags;

      protected int _materialId;

      protected int _smoothGroup;

      // 顶点索引
      protected SDrIndex _vertexIndex = new SDrIndex();

      // 调整顶点索引
      protected SDrIndex _adjustVertexIndex = new SDrIndex();

      // 颜色索引
      protected SDrIndex _colorIndex = new SDrIndex();

      // 透明索引
      protected SDrIndex _alphaIndex = new SDrIndex();

      // 贴图索引
      protected SDrIndex _coordIndex = new SDrIndex();

      // 法线索引
      protected SDrIndex _normalIndex = new SDrIndex();

      // 副法线切线索引
      protected SDrIndex _tangentBinormalIndex = new SDrIndex();

      // 照明索引
      protected SDrIndex _illumIndex = new SDrIndex();

      // 可见索引
      protected SDrIndex _visibleIndex = new SDrIndex();

      //============================================================
      // <T>获得顶点索引。</T>
      //============================================================
      public SDrIndex VertexIndex {
         get { return _vertexIndex; }
      }

      //============================================================
      // <T>获得顶点索引。</T>
      //============================================================
      public SDrIndex AdjustVertexIndex {
         get { return _adjustVertexIndex; }
      }

      //============================================================
      // <T>获得颜色索引。</T>
      //============================================================
      public SDrIndex ColorIndex {
         get { return _colorIndex; }
      }

      //============================================================
      // <T>获得颜色透明。</T>
      //============================================================
      public SDrIndex AlphaIndex {
         get { return _alphaIndex; }
      }

      //============================================================
      // <T>获得贴图索引。</T>
      //============================================================
      public SDrIndex CoordIndex {
         get { return _coordIndex; }
      }

      //============================================================
      // <T>获得法线索引。</T>
      //============================================================
      public SDrIndex NormalIndex {
         get { return _normalIndex; }
      }

      //============================================================
      // <T>获得副法线切线索引。</T>
      //============================================================
      public SDrIndex TangentBinormalIndex {
         get { return _tangentBinormalIndex; }
      }

      //============================================================
      // <T>获得光照索引。</T>
      //============================================================
      public SDrIndex IllumIndex {
         get { return _illumIndex; }
      }

      //============================================================
      // <T>获得可见索引。</T>
      //============================================================
      public SDrIndex VisibleIndex {
         get { return _visibleIndex; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         // 读取顶点索引
         if (config.Contains("vertex")) {
            _vertexIndex.Parse(config.Get("vertex"));
         }
         // 读取定点色索引
         if (config.Contains("color")) {
            _colorIndex.Parse(config.Get("color"));
         }
         // 读取纹理坐标索引
         if (config.Contains("coord")) {
            _coordIndex.Parse(config.Get("coord"));
         }
         // 读取法线索引
         if (config.Contains("normal")) {
            _normalIndex.Parse(config.Get("normal"));
         }
         // 读取切线和副法线索引
         if (config.Contains("tangent_binormal")) {
            _tangentBinormalIndex.Parse(config.Get("tangent_binormal"));
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         xconfig.Set("id", Index);
         xconfig.Set("vertex", _vertexIndex.ToString());
         xconfig.Set("color", _colorIndex.ToString());
         xconfig.Set("coord", _coordIndex.ToString());
         xconfig.Set("normal", _normalIndex.ToString());
         xconfig.Set("tangent_binormal", _tangentBinormalIndex.ToString());
      }
      
      //============================================================
      public void ExportConfig(FXmlNode config) {
         // 输出顶点列表
         FXmlNode positionNode = config.CreateNode("VertexIndex");
         positionNode.Set("v1", _adjustVertexIndex.V1);
         positionNode.Set("v2", _adjustVertexIndex.V2);
         positionNode.Set("v3", _adjustVertexIndex.V3);
      }

      //============================================================
      public void DataUnserialize(FDrGeometry geometry, IInput input) {
         // 读取面信息
         _faceIndex = input.ReadInt32();
         _flags = input.ReadInt32();
         _materialId = input.ReadInt32();
         _smoothGroup = input.ReadInt32();
         // 读取顶点索引
         if (!geometry.VertexList.IsEmpty) {
            _vertexIndex.Unserialize(input);
         }
         // 读取颜色索引
         if (!geometry.ColorList.IsEmpty) {
            _colorIndex.Unserialize(input);
         }
         // 读取透明索引
         if (!geometry.AlphaList.IsEmpty) {
            _alphaIndex.Unserialize(input);
         }
         // 读取纹理索引
         if (!geometry.CoordList.IsEmpty) {
            _coordIndex.Unserialize(input);
            geometry.VertexList[_vertexIndex.V1].CoordIds.Push(_coordIndex.V1);
            geometry.VertexList[_vertexIndex.V2].CoordIds.Push(_coordIndex.V2);
            geometry.VertexList[_vertexIndex.V3].CoordIds.Push(_coordIndex.V3);
            geometry.VertexList[_vertexIndex.V1].CoordUids.PushUnique(_coordIndex.V1);
            geometry.VertexList[_vertexIndex.V2].CoordUids.PushUnique(_coordIndex.V2);
            geometry.VertexList[_vertexIndex.V3].CoordUids.PushUnique(_coordIndex.V3);
         }
         // 读取法线索引
         if (!geometry.NormalList.IsEmpty) {
            _normalIndex.Unserialize(input);
            geometry.VertexList[_vertexIndex.V1].NormalIds.Push(_normalIndex.V1);
            geometry.VertexList[_vertexIndex.V2].NormalIds.Push(_normalIndex.V2);
            geometry.VertexList[_vertexIndex.V3].NormalIds.Push(_normalIndex.V3);
            geometry.VertexList[_vertexIndex.V1].NormalUids.PushUnique(_normalIndex.V1);
            geometry.VertexList[_vertexIndex.V2].NormalUids.PushUnique(_normalIndex.V2);
            geometry.VertexList[_vertexIndex.V3].NormalUids.PushUnique(_normalIndex.V3);
         }
         // 读取切线副法线索引
         if (!geometry.TangentList.IsEmpty) {
            _tangentBinormalIndex.Unserialize(input);
            geometry.VertexList[_vertexIndex.V1].BinormalTangentIds.Push(_tangentBinormalIndex.V1);
            geometry.VertexList[_vertexIndex.V2].BinormalTangentIds.Push(_tangentBinormalIndex.V2);
            geometry.VertexList[_vertexIndex.V3].BinormalTangentIds.Push(_tangentBinormalIndex.V3);
            geometry.VertexList[_vertexIndex.V1].BinormalTangentUids.PushUnique(_tangentBinormalIndex.V1);
            geometry.VertexList[_vertexIndex.V2].BinormalTangentUids.PushUnique(_tangentBinormalIndex.V2);
            geometry.VertexList[_vertexIndex.V3].BinormalTangentUids.PushUnique(_tangentBinormalIndex.V3);
         }
         // 读取照明索引
         if (!geometry.IllumList.IsEmpty) {
            _illumIndex.Unserialize(input);
         }
         // 读取可见性
         _visibleIndex.Unserialize(input);
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteUint16((ushort)_adjustVertexIndex.V1);
         output.WriteUint16((ushort)_adjustVertexIndex.V2);
         output.WriteUint16((ushort)_adjustVertexIndex.V3);
      }
   }
}
