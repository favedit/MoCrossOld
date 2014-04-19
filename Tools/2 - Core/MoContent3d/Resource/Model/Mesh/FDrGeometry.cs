using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Model.Animation;
using MO.Core;
using System;

namespace MO.Content3d.Resource.Model.Mesh
{
   //============================================================
   // <T>几何体。</T>
   //============================================================
   public class FDrGeometry : FObject
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrGeometry));

      // 模型对象
      protected FDrModel _model;

      // 索引位置
      protected int _index;

      // 名称
      protected string _name;

      // 有效性
      protected bool _valid;

      // 配置实体
      protected int _optionInstanced = EDrFlag.Inherit;

      // 配置动态
      protected int _optionDynamic = EDrFlag.Inherit;

      // 配置骨骼缩放
      protected int _optionBoneScale = EDrFlag.Inherit;

      // 阴影设置影子
      protected int _optionShadow = EDrFlag.Yes;

      // 自阴影设置
      protected int _optionSelfShadow = EDrFlag.Yes;

      // 法线缩放配置
      protected int _optionNormalScale = EDrFlag.Yes;

      // 法线配置
      protected int _optionNormalFull = EDrFlag.Yes;

      // 光照配置
      protected int _optionLight = EDrFlag.Inherit;

      // 设置双面
      protected int _optionDouble = EDrFlag.Inherit;

      // 设置对称
      protected int _optionSymmetry = EDrFlag.Inherit;

      // 设置透射
      protected int _optionTransmittance = EDrFlag.Inherit;

      // 设置选中
      protected int _optionSelect = EDrFlag.Inherit;

      // 设置地面
      protected int _optionGround = EDrFlag.Inherit;

      // 实体化个数
      protected int _instanceCount = 1;

      // 纹理有效性
      protected bool _coordInvalid;

      // 轮廓中心
      protected SFloatPoint3 _outlineCenter = new SFloatPoint3();

      // 轮廓最小
      protected SFloatPoint3 _outlineMin = new SFloatPoint3(float.MaxValue, float.MaxValue, float.MaxValue);

      // 轮廓最大
      protected SFloatPoint3 _outlineMax = new SFloatPoint3(float.MinValue, float.MinValue, float.MinValue);

      // 局部矩阵
      protected FDrMatrix _localMatrix = new FDrMatrix();

      // 世界矩阵
      protected FDrMatrix _worldMatrix = new FDrMatrix();

      // 材质名称
      protected string _materialName;

      // 模型材质
      protected FDrModelMaterial _modelMaterial;

      // 材质对象
      protected FDrMaterialGroup _material;

      // 顶点列表
      protected FVector<FDrVertex> _vertexList = new FVector<FDrVertex>();

      // 颜色集合
      protected FVector<FDrColor> _colorList = new FVector<FDrColor>();

      // 透明集合
      protected FVector<float> _alphaList = new FVector<float>();

      // 纹理坐标集合
      protected FVector<FDrCoord> _coordList = new FVector<FDrCoord>();

      // 法线集合
      protected FVector<FDrNormal> _normalList = new FVector<FDrNormal>();

      // 副法线集合
      protected FVector<FDrBinormal> _binormalList = new FVector<FDrBinormal>();

      // 切线集合
      protected FVector<FDrTangent> _tangentList = new FVector<FDrTangent>();

      // 环境光集合
      protected FVector<float> _illumList = new FVector<float>();

      // 面集合
      protected FVector<FDrFace> _faceList = new FVector<FDrFace>();

      // 调整顶点集合
      protected FVector<FDrVertex> _adjustVertexList = new FVector<FDrVertex>();

      // 调整顶点字典
      protected FDictionary<FDrVertex> _adjustVertexDictionary = new FDictionary<FDrVertex>();

      // 调整顶点字典
      protected FIntDictionary<FDrBone> _adjustBones = new FIntDictionary<FDrBone>();

      // 最大权重数目
      protected int _weightMaxCount;

      // 跟踪
      protected FDrTrack _track;

      // 临时几何体集合
      protected FVector<FDrChannel> _channels = new FVector<FDrChannel>();

      //============================================================
      // <T>构造几何体。</T>
      //============================================================
      public FDrGeometry(FDrModel model) {
         _model = model;
         _track = new FDrTrack(model);
      }

      //============================================================
      public FVector<FDrChannel> Channels {
         get { return _channels; }
      }

      //============================================================
      // <T>获得模型。</T>
      //============================================================
      public FDrModel Model {
         get { return _model; }
      }

      //============================================================
      // <T>获得索引位置。</T>
      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      // <T>获得名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      // <T>获得或设置有效性。</T>
      //============================================================
      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      //============================================================
      // <T>获得或设置配置实体。</T>
      //============================================================
      public int OptionInstanced {
         get { return _optionInstanced; }
         set { _optionInstanced = value; }
      }

      //============================================================
      // <T>获得或设置实体个数。</T>
      //============================================================
      public int InstanceCount {
         get { return _instanceCount; }
         set { _instanceCount = value; }
      }

      //============================================================
      // <T>获得或设置配置动态。</T>
      //============================================================
      public int OptionDynamic {
         get { return _optionDynamic; }
         set { _optionDynamic = value; }
      }

      //============================================================
      // <T>获得或设置骨骼缩放。</T>
      //============================================================
      public int OptionBoneScale {
         get { return _optionBoneScale; }
         set { _optionBoneScale = value; }
      }

      //============================================================
      // <T>获得或设置是否阴影。</T>
      //============================================================
      public int OptionShadow {
         get { return _optionShadow; }
         set { _optionShadow = value; }
      }

      //============================================================
      // <T>获得或设置是否自阴影。</T>
      //============================================================
      public int OptionSelfShadow {
         get { return _optionSelfShadow; }
         set { _optionSelfShadow = value; }
      }

      //============================================================
      // <T>获得或设置法线缩放。</T>
      //============================================================
      public int OptionNormalScale {
         get { return _optionNormalScale; }
         set { _optionNormalScale = value; }
      }

      //============================================================
      // <T>获得或设置法线配置。</T>
      //============================================================
      public int OptionNormalFull {
         get { return _optionNormalFull; }
         set { _optionNormalFull = value; }
      }

      //============================================================
      // <T>获得或设置光照配置。</T>
      //============================================================
      public int OptionLight {
         get { return _optionLight; }
         set { _optionLight = value; }
      }

      //============================================================
      // <T>获得或设置是否双面。</T>
      //============================================================
      public int OptionDouble {
         get { return _optionDouble; }
         set { _optionDouble = value; }
      }

      //============================================================
      // <T>获得或设置是否对称。</T>
      //============================================================
      public int OptionSymmetry {
         get { return _optionSymmetry; }
         set { _optionSymmetry = value; }
      }

      //============================================================
      // <T>获得或设置是否透射。</T>
      //============================================================
      public int OptionTransmittance {
         get { return _optionTransmittance; }
         set { _optionTransmittance = value; }
      }

      //============================================================
      // <T>获得或设置是否选中。</T>
      //============================================================
      public int OptionSelect {
         get { return _optionSelect; }
         set { _optionSelect = value; }
      }

      //============================================================
      // <T>获得或设置是否地面。</T>
      //============================================================
      public int OptionGround {
         get { return _optionGround; }
         set { _optionGround = value; }
      }

      //============================================================
      // <T>获得轮廓最小。</T>
      //============================================================
      public SFloatPoint3 OutlineMin {
         get { return _outlineMin; }
      }

      //============================================================
      // <T>获得轮廓最大。</T>
      //============================================================
      public SFloatPoint3 OutlineMax {
         get { return _outlineMax; }
      }
      
      //============================================================
      // <T>获得材质代码。</T>
      //============================================================
      public string MaterialCode {
         get { return RDrUtil.FormatPathToCode(_materialName); }
      }

      //============================================================
      // <T>获得或设置材质名称。</T>
      //============================================================
      public string MaterialName {
         get { return _materialName; }
         set { _materialName = value; }
      }

      //============================================================
      // <T>获得或设置材质。</T>
      //============================================================
      public FDrMaterialGroup Material {
         get { return _material; }
         set { _material = value; }
      }

      //============================================================
      // <T>获得局部矩阵。</T>
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
      // <T>获得顶点集合。</T>
      //============================================================
      public FVector<FDrVertex> VertexList {
         get { return _vertexList; }
      }

      //============================================================
      // <T>获得颜色集合。</T>
      //============================================================
      public FVector<FDrColor> ColorList {
         get { return _colorList; }
      }

      //============================================================
      // <T>获得透明集合。</T>
      //============================================================
      public FVector<float> AlphaList {
         get { return _alphaList; }
      }

      //============================================================
      // <T>获得纹理集合。</T>
      //============================================================
      public FVector<FDrCoord> CoordList {
         get { return _coordList; }
      }

      //============================================================
      // <T>获得法线集合。</T>
      //============================================================
      public FVector<FDrNormal> NormalList {
         get { return _normalList; }
      }

      //============================================================
      // <T>获得副法线集合。</T>
      //============================================================
      public FVector<FDrBinormal> BinormalList {
         get { return _binormalList; }
      }

      //============================================================
      // <T>获得切线集合。</T>
      //============================================================
      public FVector<FDrTangent> TangentList {
         get { return _tangentList; }
      }

      //============================================================
      // <T>获得环境光集合。</T>
      //============================================================
      public FVector<float> IllumList {
         get { return _illumList; }
      }

      //============================================================
      // <T>获得面集合。</T>
      //============================================================
      public FVector<FDrFace> FaceList {
         get { return _faceList; }
      }

      //============================================================
      // <T>获得调整顶点集合。</T>
      //============================================================
      public FDictionary<FDrVertex> AdjustVertexDictionary {
         get { return _adjustVertexDictionary; }
      }

      //============================================================
      // <T>获得调整骨头集合。</T>
      //============================================================
      public FIntDictionary<FDrBone> AdjustBones {
         get { return _adjustBones; }
      }

      //============================================================
      // <T>获得最大权重数目。</T>
      //============================================================
      public int WeightMaxCount {
         get { return _weightMaxCount; }
      }

      //============================================================
      // <T>测试动画是否有缩放。</T>
      //============================================================
      public bool TestScale() {
         _optionBoneScale = EDrFlag.Inherit;
         // 输出骨骼列表
         foreach (IPair<int, FDrBone> pair in _adjustBones) {
            int boneId = pair.Value.AdjustId;
            FDrTrack track = _model.Animation.FindTrack(boneId);
            if (track.TestScale()) {
               _optionBoneScale = EDrFlag.Yes;
               break;
            }
         }
         // 计算实体化最大个数
         int boneMatrix = 3;
         int vertexCount = _adjustVertexDictionary.Count;
         int vertexInstance = 0;
         if (vertexCount > 0) {
            vertexInstance = RDrCompiler.VertexMaxCount / vertexCount;
         }
         int matrixInstance = RDrCompiler.VertexConstMaxCount / (3 + boneMatrix * _adjustBones.Count);
         _instanceCount = Math.Min(vertexInstance, matrixInstance);
         return false;
      }

      //============================================================
      public int FindAdjustBoneIndex(int boneId) {
         int count = _adjustBones.Count;
         for (int n = 0; n < count; n++) {
            if (_adjustBones.Value(n).Id == boneId) {
               return n;
            }
         }
         throw new FFatalException();
      }

      //============================================================
      public FDrVertex SyncVertex(int faceIndex, int vertexId, int coordId, int normalId, int tangentId, out bool exists) {
         string name = faceIndex + "," + vertexId + "," + coordId + "," + normalId + "," + tangentId;
         FDrVertex vertex = _adjustVertexDictionary.Find(name);
         if (null == vertex) {
            vertex = new FDrVertex();
            vertex.AdjuestId = _adjustVertexList.Count;
            _adjustVertexList.Push(vertex);
            _adjustVertexDictionary.Set(name, vertex);
            exists = false;
         } else {
            exists = true;
         }
         return vertex;
      }

      //============================================================
      // <T>计算子网格内指定顶点的法线。<T>
      // <P>暂时使用平均值，未来考虑角度占的权重。</P>
      //
      // @param vertexId 顶点编号
      // @param vector 输出向量
      //============================================================
      public bool CalculateVertexNormal(FDrVertex vertex) {
         // 检查面数
         if (1 == vertex.FaceIds.Count) {
            return false;
         }
         // 计算总角度
         float angleTotal = 0.0f;
         foreach (FDrFace face in vertex.Faces) {
            int corner = -1;
            // 计算两个点间的向量
            int line1Corner = -1;
            int line2Corner = -1;
            SFloatVector3 line1 = new SFloatVector3();
            SFloatVector3 line2 = new SFloatVector3();
            bool first = true;
            for (int c = 0; c < 3; c++) {
               int findVectorId = face.VertexIndex[c];
               if (findVectorId == vertex.Id) {
                  corner = c;
               } else {
                  if (first) {
                     line1Corner = c;
                     FDrVertex vertex1 = _vertexList[findVectorId];
                     line1.Set(vertex1.Position.X, vertex1.Position.Y, vertex1.Position.Z);
                     first = false;
                  } else {
                     line2Corner = c;
                     FDrVertex vertex2 = _vertexList[findVectorId];
                     line2.Set(vertex2.Position.X, vertex2.Position.Y, vertex2.Position.Z);
                  }
               }
            }
            if (-1 != corner) {
               // 求两个向量间的角度
               line1.Sub(vertex.Position);
               line1.Normalize();
               line2.Sub(vertex.Position);
               line2.Normalize();
               float angle = line1.Angle(line2);
               vertex.faceAngles.Push(angle);
               angleTotal += angle;
            }
         }
         if (vertex.faceAngles.IsEmpty) {
            return false;
         }
         // 计算平均顶点法线
         SFloatVector3 normal = vertex.Normal.Clear();
         SFloatVector3 binormal = vertex.Binormal.Clear();
         SFloatVector3 tangent = vertex.Tangent.Clear();
         int count = vertex.faceAngles.Count;
         for (int n = 0; n < count; n++) {
            float angle = vertex.faceAngles[n];
            float rate = angle / angleTotal;
            SFloatVector3 vnormal = vertex.Normals[n];
            vnormal.Mul(rate);
            normal.Add(vnormal);
            if (!vertex.Binormals.IsEmpty) {
               SFloatVector3 vbinormal = vertex.Binormals[n];
               vbinormal.Mul(rate);
               binormal.Add(vbinormal);
            }
            if (!vertex.Tangents.IsEmpty) {
               SFloatVector3 vtangent = vertex.Tangents[n];
               vtangent.Mul(rate);
               tangent.Add(vtangent);
            }
         }
         normal.Normalize();
         binormal.Normalize();
         //if (tangent.Length() < 0.1) {
         //   tangent.Assign(new SFloatVector3(0, 0, 1).Cross(normal));
         //   if (Math.Abs(normal.X) > 0.55) {
         //      tangent.Set(0, 0, 1);
         //   } else if (Math.Abs(normal.Y) > 0.55) {
         //      tangent.Set(1, 0, 0);
         //   } else if (Math.Abs(normal.Z) > 0.55) {
         //      tangent.Set(0, 1, 0);
         //   }
         //}
         tangent.Normalize();
         return true;
      }

      //============================================================
      public void LoadMatrixConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            switch (node.Name) {
               case "LocalMatrix":
                  _localMatrix.LoadSimpleConfig(node);
                  break;
               case "WorldMatrix":
                  _worldMatrix.LoadSimpleConfig(node);
                  break;
            }
         }
      }

      //============================================================
      // <T>加载顶点列表配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadMaterialsConfig(FXmlNode config) {
         // 查找第一个关联材质
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Material")) {
               string dataName = node.Get("name");
               _modelMaterial = _model.Materials.DataNames.Find(dataName);
               break;
            }
         }
         // 创建材质
         string materialCode = MaterialCode;
         if(null == _modelMaterial) {
            _modelMaterial = _model.Materials.Names.Find(materialCode);
            if(null == _modelMaterial) {
               _modelMaterial = new FDrModelMaterial();
            }
         } 
         // 设置材质信息
         _modelMaterial.Name = _materialName;
         _modelMaterial.Material = _material;
         // 设置对象
         _model.Materials.Names.Set(materialCode, _modelMaterial);
      }

      //============================================================
      // <T>加载顶点列表配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadVertexCollectionConfig(FXmlNode config) {
         int id = 0;
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Vertex")) {
               FDrVertex vertex = new FDrVertex();
               vertex.Id = id++;
               vertex.LoadModelConfig(node);
               _outlineMin.InnerMin(vertex.Position);
               _outlineMax.InnerMax(vertex.Position);
               _vertexList.Push(vertex);
            }
         }
      }

      //============================================================
      // <T>加载顶点颜色配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadColorCollectionConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Color")) {
               FDrColor color = new FDrColor();
               color.LoadConfig(node);
               _colorList.Push(color);
            }
         }
      }

      //============================================================
      // <T>加载顶点UV坐标配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadCoordCollectionConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Coord")) {
               FDrCoord coord = new FDrCoord();
               coord.LoadConfig(node);
               _coordList.Push(coord);
            }
         }
      }

      //============================================================
      // <T>加载顶点法线配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadNormalCollectionConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Normal")) {
               FDrNormal normal = new FDrNormal();
               normal.LoadConfig(node);
               _normalList.Push(normal);
            }
         }
      }

      //============================================================
      // <T>加载顶点副法线配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadBinormalCollectionConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Binormal")) {
               FDrBinormal binormal = new FDrBinormal();
               binormal.LoadConfig(node);
               _binormalList.Push(binormal);
            }
         }
      }

      //============================================================
      // <T>加载顶点切线配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadTangentCollectionConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Tangent")) {
               FDrTangent tangent = new FDrTangent();
               tangent.LoadConfig(node);
               _tangentList.Push(tangent);
            }
         }
      }

      //============================================================
      // <T>加载三角形面列表配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadFaceCollectionConfig(FXmlNode config) {
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Face")) {
               FDrFace face = new FDrFace();
               face.LoadConfig(node);
               _faceList.Push(face);
            }
         }
      }

      //============================================================
      public void LoadModifierCollectionConfig(FXmlNode config) {
      }

      //============================================================
      public void LoadSkinListConfig(FXmlNode config) {
         FXmlNode vertexListNode = config.Find("VertexCollection");
         if (null != vertexListNode) {
            foreach (FXmlNode vertexNode in vertexListNode.Nodes) {
               if (vertexNode.IsName("Vertex")) {
                  int id = vertexNode.GetInteger("id");
                  FDrVertex vertex = _vertexList[id];
                  vertex.LoadWeightConfig(vertexNode);
               }
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode config) {
         // 读取属性
         _name = config.Get("name");
         // 读取所有子节点
         foreach (FXmlNode node in config.Nodes) {
            switch (node.Name) {
               case "Matrix":
                  LoadMatrixConfig(node);
                  break;
               case "MaterialCollection":
                  LoadMaterialsConfig(node);
                  break;
               case "VertexCollection":
                  LoadVertexCollectionConfig(node);
                  break;
               case "ColorCollection":
                  LoadColorCollectionConfig(node);
                  break;
               case "CoordCollection":
                  LoadCoordCollectionConfig(node);
                  break;
               case "NormalCollection":
                  LoadNormalCollectionConfig(node);
                  break;
               case "BinormalCollection":
                  LoadBinormalCollectionConfig(node);
                  break;
               case "TangentCollection":
                  LoadTangentCollectionConfig(node);
                  break;
               case "FaceCollection":
                  LoadFaceCollectionConfig(node);
                  break;
               case "ModifierCollection":
                  LoadModifierCollectionConfig(node);
                  break;
               case "Skin":
                  LoadSkinListConfig(node);
                  break;
               case "Track":
                  _track.LoadGeomeryModelConfig(node);
                  break;
            }
         }
         Update();
      }

      //============================================================
      // 更新内部信息
      public void Update() {
         //------------------------------------------------------------
         // 计算影响到的所有骨骼信息
         _weightMaxCount = 0;
         foreach (FDrVertex vertex in _vertexList) {
            if (!vertex.Weights.IsEmpty) {
               int weightCount = vertex.Weights.Count;
               if (weightCount > _weightMaxCount) {
                  _weightMaxCount = weightCount;
               }
               foreach (FDrVertexWeight weight in vertex.Weights) {
                  int boneId = weight.BoneId;
                  if (boneId >= 0) {
                     if (!_adjustBones.Contains(boneId)) {
                        FDrBone bone = _model.Skeleton.Bones.Get(boneId);
                        _adjustBones.Set(boneId, bone);
                     }
                  }
               }
            }
         }
         //------------------------------------------------------------
         // 根据面信息调整原始顶点信息
         ////int fixCount = 0;
         //foreach (FDrVertex vertex in _vertexList) {
         //   //if (vertex.Position.X == 0.0f) {
         //   if (vertex.Position.X > 0.0f) {
         //      int btcount = vertex.BinormalTangentIds.Count;
         //      //if (btcount % 2 == 0) {
         //         for (int n = 0; n < btcount; n++) {
         //            int binormalTangentId = vertex.BinormalTangentIds[n];
         //            FDrNormal normal = _normalList[vertex.NormalIds[n]];
         //            FDrBinormal binormal = _binormalList[binormalTangentId];
         //            FDrTangent tangent = _tangentList[binormalTangentId];
         //            //binormal.Direction.Set(-binormal.Direction.X, -binormal.Direction.Y, -binormal.Direction.Z);
         //            //tangent.Direction.Set(-tangent.Direction.X, -tangent.Direction.Y, -tangent.Direction.Z);
         //         //   if (tangent.Direction.X > 0) {
         //         //      //tangent.Direction.Set(1, 0, 0);
         //         //   } else {
         //         //      tangent.Direction.Set(-1, 0, 0);
         //         //      binormal.Direction.Assign(normal.Direction.Cross(tangent.Direction));
         //         //   }
         //            //fixCount++;
         //            //int btSourceId = vertex.BinormalTangentIds[n];
         //            //FDrTangent btSource = _tangentList[btSourceId];
         //            //if (!btSource.isFixed) {
         //            //   for (int i = n + 1; i < btcount; i++) {
         //            //      int btTargetId = vertex.BinormalTangentIds[i];
         //            //      FDrTangent btTarget = _tangentList[btTargetId];
         //            //      //if ((btSource.Direction.X == -btTarget.Direction.X) && (btSource.Direction.Y == btTarget.Direction.Y) && (btSource.Direction.Z == btTarget.Direction.Z)){
         //            //      if (!btTarget.isFixed) {
         //            //         //int sourcex = (int)(btSource.Direction.X * 10000);
         //            //         //int targetx = (int)(btTarget.Direction.X * 10000);
         //            //         if (btSource.Direction.X == -btTarget.Direction.X) {
         //            //            if (btSource.Direction.X > 0) {
         //            //               btSource.Direction.Set(1, 0, 0);
         //            //               btTarget.Direction.Set(-1, 0, 0);
         //            //            } else {
         //            //               btSource.Direction.Set(-1, 0, 0);
         //            //               btTarget.Direction.Set(1, 0, 0);
         //            //            }
         //            //            btSource.isFixed = true;
         //            //            btTarget.isFixed = true;
         //            //            fixCount++;
         //            //         }
         //            //      }
         //            //   }
         //            //}
         //         }
         //         //vertex.normalFix = "X";
         //      //}
         //   }
         //}
         //------------------------------------------------------------
         // 根据面信息调整节点信息
         int faceIndex = -1;
         foreach (FDrFace face in _faceList) {
            faceIndex++;
            for (int n = 0; n < 3; n++) {
               // 获得索引
               int vertexIndex = face.VertexIndex[n];
               int coordIndex = face.CoordIndex[n];
               int normalIndex = face.NormalIndex[n];
               int tangentBinormalIndex = face.TangentBinormalIndex[n];
               // 设置内容
               bool exists = false;
               FDrVertex vertex = null;
               if (_optionNormalFull == EDrFlag.Yes) {
                  if (_channels.Count > 2) {
                     vertex = SyncVertex(face.Index, vertexIndex, coordIndex, normalIndex, tangentBinormalIndex, out exists);
                  } else {
                     vertex = SyncVertex(0, vertexIndex, coordIndex, normalIndex, tangentBinormalIndex, out exists);
                  }
               } else {
                  if (_channels.Count > 2) {
                     vertex = SyncVertex(face.Index, vertexIndex, coordIndex, normalIndex, 0, out exists);
                  } else {
                     vertex = SyncVertex(0, vertexIndex, coordIndex, normalIndex, 0, out exists);
                  }
               }
               // 设置顶点信息
               if (!exists) {
                  vertex.merged = _vertexList[vertexIndex].merged;
                  vertex.Position.Assign(_vertexList[vertexIndex].Position);
                  vertex.FaceIds.Push(faceIndex);
                  vertex.Faces.Push(face);
                  if (!_colorList.IsEmpty) {
                     int colorIndex = face.ColorIndex[n];
                     vertex.Color.Assign3(_colorList[colorIndex].Color);
                     vertex.Color.A = 1.0f;
                  }
                  if (!_alphaList.IsEmpty) {
                     int alphaIndex = face.AlphaIndex[n];
                     vertex.Alpha = _alphaList[alphaIndex];
                     vertex.Color.A = vertex.Alpha;
                  }
                  if (!_coordList.IsEmpty) {
                     vertex.Coord.Assign(_coordList[coordIndex].Coord);
                  }
                  if (!_normalList.IsEmpty) {
                     vertex.Normal.Assign(_normalList[normalIndex].Direction);
                     vertex.Normal.Normalize();
                  }
                  if (!_binormalList.IsEmpty) {
                     vertex.Binormal.Assign(_binormalList[tangentBinormalIndex].Direction);
                     vertex.Binormal.Normalize();
                  }
                  if (!_tangentList.IsEmpty) {
                     vertex.Tangent.Assign(_tangentList[tangentBinormalIndex].Direction);
                     vertex.Tangent.Normalize();
                  }
                  if (!_illumList.IsEmpty) {
                     int illumIndex = face.IllumIndex[n];
                     vertex.Illum = _illumList[illumIndex];
                  }
                  if (_weightMaxCount > 0) {
                     vertex.Weights.Assign(_vertexList[vertexIndex].Weights);
                  }
                  if (_channels.Count > 2) {
                     FDrChannelFace channelFace = _channels[2].Indexs[face.Index];
                     SFloatPoint3 channelPoint = channelFace.Points[n];
                     vertex.LightCoord.X = channelPoint.X;
                     vertex.LightCoord.Y = channelPoint.Y;
                     if ((vertex.LightCoord.X != vertex.Coord.X) || (vertex.LightCoord.Y != -vertex.Coord.Y)) {
                        vertex.LightCoord.X = channelPoint.X;
                        vertex.LightCoord.Y = channelPoint.Y;
                     }
                  }
               }
               face.AdjustVertexIndex.Set(n, vertex.AdjuestId);
            }
         }
         //------------------------------------------------------------
         // 重新计算顶点平均信息
         //foreach (FDrVertex vertex in _vertexList) {
         //   CalculateVertexNormal(vertex);
         //}
         //_logger.Debug(this, "Update", "Update geometry success. (name={0}, material={1}, vertex_count={2}, face_count={3}, bone_count={4}, frame_count={5})",
            //_name, _materialName, _adjustVertexList.Count, _faceList.Count, _adjustBones.Count, _track.FrameList.Count);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         // 读取属性
         _valid = config.GetBoolean("valid");
         _name = config.Get("name");
         _materialName = config.Get("material").Replace('/', '\\');
         // 获得材质对象
         _material = RContent3dManager.MaterialConsole.FindGroup(_materialName);
         if(null == _material) {
            RMoCore.TrackConsole.Write(this, "LoadMaterialsConfig", "Geomery material is not exists. (model={0}, geometry={1}, material={2})",
               _model.Name, _name, _materialName);
         }
         // 读取选项
         _optionInstanced = config.GetInteger("option_instanced", _optionInstanced);
         _optionDynamic = config.GetInteger("option_dynamic", _optionDynamic);
         _optionBoneScale = config.GetInteger("option_bone_scale", _optionBoneScale);
         _optionShadow = config.GetInteger("option_shadow", _optionShadow);
         _optionSelfShadow = config.GetInteger("option_self_shadow", _optionSelfShadow);
         _optionNormalFull = config.GetInteger("option_normal", _optionNormalFull);
         _optionLight = config.GetInteger("option_light", _optionLight);
         _optionDouble = config.GetInteger("option_double", _optionDouble);
         _optionSymmetry = config.GetInteger("option_symmetry", _optionSymmetry);
         _optionTransmittance = config.GetInteger("option_transmittance", _optionTransmittance);
         _optionSelect = config.GetInteger("option_select", _optionSelect);
         _optionGround = config.GetInteger("option_ground", _optionGround);
         // 实体个数
         _instanceCount = config.GetInteger("instance_count", _instanceCount);
      }

      //============================================================
      // <T>保存全部配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         // 存储属性
         config.Set("valid", _valid);
         config.Set("name", _name);
         if (null != _material) {
            config.Set("material", _material.Name);
         } else {
            config.Set("material", _materialName);
         }
         // 存储设置
         config.Set("option_instanced", _optionInstanced);
         config.Set("option_dynamic", _optionDynamic);
         config.Set("option_bone_scale", _optionBoneScale);
         config.Set("option_shadow", _optionShadow);
         config.Set("option_self_shadow", _optionSelfShadow);
         config.Set("option_normal", _optionNormalFull);
         config.Set("option_light", _optionLight);
         config.Set("option_double", _optionDouble);
         config.Set("option_symmetry", _optionSymmetry);
         config.Set("option_transmittance", _optionTransmittance);
         config.Set("option_select", _optionSelect);
         config.Set("option_ground", _optionGround);
         // 实体个数
         config.Set("instance_count", _instanceCount);
      }

      //============================================================
      // <T>保存全部配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode config) {
         // 存储属性
         config.Set("valid", _valid);
         config.Set("name", _name);
         if (null != _material) {
            config.Set("material", _material.Name);
         } else {
            config.Set("material", _materialName);
         }
         // 存储设置
         config.Set("option_double", _optionDouble);
         config.Set("option_shadow", _optionShadow);
         config.Set("option_symmetry", _optionSymmetry);
         config.Set("option_transmittance", _optionTransmittance);
         config.Set("option_select", _optionSelect);
         config.Set("option_ground", _optionGround);
         config.Set("option_bone_scale", _optionBoneScale);
         config.Set("option_instanced", _optionInstanced);
         config.Set("option_dynamic", _optionDynamic);
         // 实体个数
         config.Set("instance_count", _instanceCount);
         config.Set("bone_count", _adjustBones.Count);
         // 存储顶点集合
         FXmlNode xvertexs = config.CreateNode("VertexCollection");
         foreach (FDrVertex vertex in _vertexList){
            vertex.SaveInfoConfig(xvertexs.CreateNode("Vertex"));
         }
         // 存储集合
         FXmlNode xcoords = config.CreateNode("Coord");
         foreach (FDrCoord coord in _coordList) {
            coord.SaveInfoConfig(xcoords.CreateNode("Coord"));
         }
         // 存储顶点集合
         FXmlNode xnormals = config.CreateNode("NormalCollection");
         foreach (FDrNormal normal in _normalList) {
            normal.SaveInfoConfig(xnormals.CreateNode("Normal"));
         }
         // 存储副法线集合
         FXmlNode xbinormals = config.CreateNode("BinormalCollection");
         foreach (FDrBinormal binormal in _binormalList) {
            binormal.SaveInfoConfig(xbinormals.CreateNode("Binormal"));
         }
         // 存储切线集合
         FXmlNode xtangents = config.CreateNode("TangentCollection");
         foreach (FDrTangent tangent in _tangentList) {
            tangent.SaveInfoConfig(xtangents.CreateNode("Tangent"));
         }
         // 存储切线集合
         FXmlNode xfaces = config.CreateNode("FaceCollection");
         foreach (FDrFace face in _faceList) {
            face.SaveInfoConfig(xfaces.CreateNode("Face"));
         }
         // 存储骨头集合
         FXmlNode xbones = config.CreateNode("BoneCollection");
         foreach (FDrBone bone in _adjustBones.Values) {
            bone.SaveInfoConfig(xbones.CreateNode("Bone"));
         }
      }

      //============================================================
      // <T>保存全部配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveNormal() {
         //string normalFileName = _model.Directory + "/" + _name + ".png";
         //FDrNormalMap normalMap = new FDrNormalMap();
         //normalMap.Export3d(this, normalFileName);
      }
      
      //============================================================
      public void ExportConfig(FXmlNode config) {
         // 输出顶点列表
         FXmlNode vertexListNode = config.CreateNode("VertexList");
         vertexListNode.Set("count", _adjustVertexDictionary.Count);
         foreach (INamePair<FDrVertex> pair in _adjustVertexDictionary) {
            FXmlNode vertexNode = vertexListNode.CreateNode("Vertex");
            pair.Value.ExportConfig(vertexNode);
         }
         // 输出面列表
         FXmlNode faceListNode = config.CreateNode("FaceList");
         faceListNode.Set("count", _faceList.Count);
         foreach (FDrFace face in _faceList) {
            FXmlNode faceNode = faceListNode.CreateNode("Face");
            face.ExportConfig(faceNode);
         }
      }

      //============================================================
      public bool DataUnserialize(IInput input) {
         // 读取顶点坐标
         int vertexCount = input.ReadInt32();
         for (int n = 0; n < vertexCount; n++) {
            FDrVertex vertex = new FDrVertex();
            vertex.Id = n;
            vertex.Position.Unserialize(input);
            _outlineMin.InnerMin(vertex.Position);
            _outlineMax.InnerMax(vertex.Position);
            _vertexList.Push(vertex);
         }
         _outlineCenter.X = (_outlineMax.X + _outlineMin.X) / 2;
         _outlineCenter.Y = (_outlineMax.Y + _outlineMin.Y) / 2;
         _outlineCenter.Z = (_outlineMax.Z + _outlineMin.Z) / 2;
         // 读取顶点颜色
         int colorCount = input.ReadInt32();
         for (int n = 0; n < colorCount; n++) {
            FDrColor color = new FDrColor();
            color.Color.Unserialize(input);
            _colorList.Push(color);
         }
         // 读取顶点透明
         int alphaCount = input.ReadInt32();
         for (int n = 0; n < alphaCount; n++) {
            _alphaList.Push(input.ReadFloat());
         }
         // 读取顶点纹理
         int coordCount = input.ReadInt32();
         for (int n = 0; n < coordCount; n++) {
            FDrCoord coord = new FDrCoord();
            coord.Id = n;
            coord.Coord.Unserialize(input);
            if (!_coordInvalid) {
               if ((coord.Coord.X < 0.0f) || (coord.Coord.X > 1.0f) || (coord.Coord.Y < -1.0f) || (coord.Coord.Y > 1.0f)) {
                  //RMoCore.TrackConsole.Write(this, "DataUnserialize", "Model coord is invalid. (model={0}, coord={1})", _model.Code, coord.Coord);
                  _coordInvalid = true;
               }
            }
            _coordList.Push(coord);
         }
         // 读取顶点法线
         int normalCount = input.ReadInt32();
         for (int n = 0; n < normalCount; n++) {
            FDrNormal normal = new FDrNormal();
            normal.Id = n;
            normal.Direction.Unserialize(input);
            _normalList.Push(normal);
         }
         // 读取顶点副法线
         int binormalCount = input.ReadInt32();
         for (int n = 0; n < binormalCount; n++) {
            FDrBinormal binormal = new FDrBinormal();
            binormal.Id = n;
            binormal.Direction.Unserialize(input);
            _binormalList.Push(binormal);
         }
         // 读取顶点切线
         int tangentCount = input.ReadInt32();
         for (int n = 0; n < tangentCount; n++) {
            FDrTangent tangent = new FDrTangent();
            tangent.Id = n;
            tangent.Direction.Unserialize(input);
            _tangentList.Push(tangent);
         }
         // 读取顶点光照
         int illumCount = input.ReadInt32();
         for (int n = 0; n < illumCount; n++) {
            _illumList.Push(input.ReadFloat());
         }
         // 读取面信息
         int faceCount = input.ReadInt32();
         for (int n = 0; n < faceCount; n++) {
            FDrFace face = new FDrFace();
            face.Index = n;
            face.DataUnserialize(this, input);
            _faceList.Push(face);
         }
         // 读取通道信息
         //int channelCount = input.ReadInt32();
         //for (int n = 0; n < channelCount; n++) {
         //   FDrChannel channel = new FDrChannel();
         //   channel.DataUnserialize(input);
         //   _channels.Push(channel);
         //}
         // 读取跟踪信息
         _track.DataUnserialize(input);
         // 读取蒙皮信息
         int skin = input.ReadInt32();
         if (skin > 0) {
            int boneCount = input.ReadInt32();
            int boneTotal = input.ReadInt32();
            int skinVertexCount = input.ReadInt32();
            for (int n = 0; n < skinVertexCount; n++) {
               int vertexIndex = input.ReadInt32();
               _vertexList[vertexIndex].UnserializeWeight(input);
            }
         }
         //_logger.Debug(this, "DataUnserialize", "Data unserialize geometry success. (name={0}, material={1}, vertex_count={2}, normal_count={3}, binormal_count={4}, tangent_count={5}, face_count={6}, bone_count={7}, frame_count={8})",
         //_name, _materialName, _vertexList.Count, _normalList.Count, _binormalList.Count, _tangentList.Count, _faceList.Count, _adjustBones.Count, _track.FrameList.Count);
         // 更新信息
         Update();
         return true;
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //============================================================
      public void Serialize(IOutput output) {
         // 存储设置
         output.WriteUint8((byte)_optionInstanced);
         output.WriteUint8((byte)_instanceCount);
         // 输出矩阵信息
         _worldMatrix.Serialize(output);
         // 输出轮廓
         _outlineMin.Serialize(output);
         _outlineMax.Serialize(output);
         // 输出材质编号
         // output.WriteInt32(_material.CodeNumber);
         if (_material != null) {
            output.WriteString(_material.Code);
         } else {
            output.WriteString(null);
         }
         //............................................................
         // 输出顶点信息
         if (_adjustVertexDictionary.Count >= 65536) {
            _logger.Debug(this, "Serialize", "Too many vectex. (name={0}, vertex_count={1})", _name, _adjustVertexDictionary.Count);
            throw new FFatalException("Too many vectex. (name={0}, vertex_count={1})", _name, _adjustVertexDictionary.Count);
         }
         //............................................................
         // 计算标志
         int streamCount = 0;
         int flags = 0;
         int offset = 0;
         using(FByteStream stream = new FByteStream()) {
            if(!_adjustVertexDictionary.IsEmpty) {
               streamCount++;
               flags |= EDrVertex.Position;
               int vertexStride = sizeof(float) * 3;
               stream.WriteUint8((byte)EVertexBuffer3d.Position);
               stream.WriteUint8((byte)ERenderVertexFormat.Float3);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if(!_colorList.IsEmpty) {
               streamCount++;
               flags |= EDrVertex.Color;
               int vertexStride = sizeof(byte) * 4;
               stream.WriteUint8((byte)EVertexBuffer3d.Color);
               stream.WriteUint8((byte)ERenderVertexFormat.ByteNormal4);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if(!_coordList.IsEmpty) {
               streamCount++;
               flags |= EDrVertex.Coord;
               int vertexStride = sizeof(float) * 2;
               stream.WriteUint8((byte)EVertexBuffer3d.Coord);
               stream.WriteUint8((byte)ERenderVertexFormat.Float2);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if(EDrFlag.Yes == _optionLight) {
               streamCount++;
               flags |= EDrVertex.CoordLight;
               int vertexStride = sizeof(float) * 2;
               stream.WriteUint8((byte)EVertexBuffer3d.CoordLight);
               stream.WriteUint8((byte)ERenderVertexFormat.Float2);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if(!_normalList.IsEmpty) {
               streamCount++;
               flags |= EDrVertex.Normal;
               int vertexStride = sizeof(byte) * 4;
               stream.WriteUint8((byte)EVertexBuffer3d.Normal);
               stream.WriteUint8((byte)ERenderVertexFormat.ByteNormal4);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if ((_optionNormalFull == EDrFlag.Yes) && (_binormalList.IsEmpty)) {
               streamCount++;
               flags |= EDrVertex.Binormal;
               int vertexStride = sizeof(byte) * 4;
               stream.WriteUint8((byte)EVertexBuffer3d.Binormal);
               stream.WriteUint8((byte)ERenderVertexFormat.ByteNormal4);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if ((_optionNormalFull == EDrFlag.Yes) && (_tangentList.IsEmpty)) {
               streamCount++;
               flags |= EDrVertex.Tangent;
               int vertexStride = sizeof(byte) * 4;
               stream.WriteUint8((byte)EVertexBuffer3d.Tangent);
               stream.WriteUint8((byte)ERenderVertexFormat.ByteNormal4);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            if(_weightMaxCount > 0) {
               // 写出骨头索引
               streamCount++;
               flags |= EDrVertex.BoneIndex;
               int vertexStride = sizeof(byte) * 4;
               stream.WriteUint8((byte)EVertexBuffer3d.BoneIndex);
               stream.WriteUint8((byte)ERenderVertexFormat.Byte4);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
               // 写出骨头权重
               streamCount++;
               flags |= EDrVertex.BoneWeight;
               vertexStride = sizeof(byte) * 4;
               stream.WriteUint8((byte)EVertexBuffer3d.BoneWeight);
               stream.WriteUint8((byte)ERenderVertexFormat.ByteNormal4);
               stream.WriteUint8((byte)vertexStride);
               stream.WriteUint8((byte)offset);
               offset += vertexStride;
            }
            // 写入流信息
            output.WriteInt8((sbyte)streamCount);
            output.WriteBytes(stream.Memory, 0, stream.Length);
         }
         //............................................................
         // 输出顶点信息
         output.WriteInt32(_adjustVertexDictionary.Count);
         output.WriteUint8((byte)offset);
         foreach (INamePair<FDrVertex> pair in _adjustVertexDictionary) {
            pair.Value.Serialize(this, flags, output);
         }
         //............................................................
         // 输出索引信息
         int faceCount = _faceList.Count;
         output.WriteInt32(3 * faceCount);
         output.WriteUint8((byte)EDrStride.Uint16);
         for (int n = 0; n < faceCount; n++) {
            _faceList[n].Serialize(output);
         }
         //............................................................
         // 输出骨骼列表
         output.WriteUint8((byte)_adjustBones.Count);
         foreach (FDrBone bone in _adjustBones.Values) {
            output.WriteUint8((byte)bone.AdjustId);
         }
         //............................................................
         // 输出跟踪列表
         _track.Serialize(output);
         //_logger.Debug(this, "Serialize", "Serialize sub mesh success. (id={0}, name={1}, material={2}, vertex_count={3}, vertex_size={4}, face_count={5}, bone_count={6}, frame_count={7})",
         //_adjustId, _name, _materialName, _adjustVertexDictionary.Count, index, _faceList.Count, _adjustBones.Count, _track.FrameList.Count);
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //============================================================
      public void Serialize2(IOutput output) {
         output.WriteString(_name);
         output.WriteString(MaterialCode);
         // 存储设置
         output.WriteUint8((byte)_optionInstanced);
         output.WriteUint8((byte)_instanceCount);
         output.WriteUint8((byte)_optionDynamic);
         output.WriteUint8((byte)_optionBoneScale);
         output.WriteUint8((byte)_optionShadow);
         output.WriteUint8((byte)_optionSelfShadow);
         output.WriteUint8((byte)_optionNormalFull);
         output.WriteUint8((byte)_optionLight);
         output.WriteUint8((byte)_optionSelect);
         output.WriteUint8((byte)_optionGround);
         // 输出矩阵信息
         _worldMatrix.Serialize(output);
         // 输出轮廓
         _outlineMin.Serialize(output);
         _outlineMax.Serialize(output);
         // 输出顶点标志
         int flags = 0;
         output.WriteInt8((sbyte)(_adjustVertexDictionary.IsEmpty ? 0 : 1));
         if (!_adjustVertexDictionary.IsEmpty) {
            flags |= EDrVertex.Position;
         }
         output.WriteInt8((sbyte)(_colorList.IsEmpty ? 0 : 1));
         if (!_colorList.IsEmpty) {
            flags |= EDrVertex.Color;
         }
         output.WriteInt8((sbyte)(_coordList.IsEmpty ? 0 : 1));
         if (!_coordList.IsEmpty) {
            flags |= EDrVertex.Coord;
         }
         output.WriteInt8((sbyte)((EDrFlag.Yes == _optionLight) ? 1 : 0));
         if (EDrFlag.Yes == _optionLight) {
            flags |= EDrVertex.CoordLight;
         }
         output.WriteInt8((sbyte)(_normalList.IsEmpty ? 0 : 1));
         if (!_normalList.IsEmpty) {
            flags |= EDrVertex.Normal;
         }
         if (EDrFlag.Yes == _optionNormalFull) {
            output.WriteInt8((sbyte)(_binormalList.IsEmpty ? 0 : 1));
            if (!_binormalList.IsEmpty) {
               flags |= EDrVertex.Binormal;
            }
            output.WriteInt8((sbyte)(_tangentList.IsEmpty ? 0 : 1));
            if (!_tangentList.IsEmpty) {
               flags |= EDrVertex.Tangent;
            }
         } else {
            output.WriteInt8((sbyte)0);
            output.WriteInt8((sbyte)0);
         }
         output.WriteInt8((sbyte)(_weightMaxCount > 0 ? 1 : 0));
         if (_weightMaxCount > 0) {
            flags |= EDrVertex.BoneIndex;
            flags |= EDrVertex.BoneWeight;
         }
         // 输出顶点信息
         if (_adjustVertexDictionary.Count >= 65536) {
            _logger.Debug(this, "Serialize", "Too many vectex. (name={0}, vertex_count={1})", _name, _adjustVertexDictionary.Count);
            //throw new FFatalException("Too many vectex. (name={0}, vertex_count={1})", _name, _adjustVertexDictionary.Count);
         }
         // 写入顶点总数
         output.WriteInt32(_adjustVertexDictionary.Count);
         foreach (INamePair<FDrVertex> pair in _adjustVertexDictionary) {
            pair.Value.Serialize(this, flags, output);
         }
         // 输出面信息
         int faceCount = _faceList.Count;
         output.WriteUint8((byte)EDrStride.Uint16);
         output.WriteInt32(3 * faceCount);
         for (int n = 0; n < faceCount; n++ ) {
            _faceList[n].Serialize(output);
         }
         // 输出骨骼列表
         output.WriteUint8((byte)_adjustBones.Count);
         foreach (FDrBone bone in _adjustBones.Values) {
            output.WriteUint8((byte)bone.AdjustId);
         }
         // 输出跟踪列表
         _track.Serialize(output);
         //_logger.Debug(this, "Serialize", "Serialize sub mesh success. (id={0}, name={1}, material={2}, vertex_count={3}, vertex_size={4}, face_count={5}, bone_count={6}, frame_count={7})",
            //_adjustId, _name, _materialName, _adjustVertexDictionary.Count, index, _faceList.Count, _adjustBones.Count, _track.FrameList.Count);
      }
   }
}
