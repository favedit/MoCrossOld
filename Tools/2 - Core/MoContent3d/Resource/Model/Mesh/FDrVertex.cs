using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Resource.Common;

namespace MO.Content3d.Resource.Model.Mesh
{
   public class FDrVertex : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDrVertex));

      // 编号
      protected int _id;

      // 调整编号
      protected int _adjuestId;

      // 编号
      protected int _maxWeightCount = 4;

      // 顶点
      protected SFloatPoint3 _position = new SFloatPoint3();

      // 顶点色
      protected SFloatColor4 _color = new SFloatColor4(1.0f, 1.0f, 1.0f, 1.0f);

      public string normalFix = "";

      public float Alpha;

      public int CoordId = -1;

      public FVector<int> CoordUids = new FVector<int>();
      public FVector<int> CoordIds = new FVector<int>();
      
       // 贴图坐标
      protected SFloatPoint2 _coord = new SFloatPoint2();

      // 贴图坐标
      protected SFloatPoint2 _lightCoord = new SFloatPoint2();

      // 法线
      protected SFloatVector3 _normal = new SFloatVector3();

      // 切线
      protected SFloatVector3 _tangent = new SFloatVector3();

      // 副法线
      protected SFloatVector3 _binormal = new SFloatVector3();

      // 骨骼权重集合
      protected FVector<FDrVertexWeight> _weights = new FVector<FDrVertexWeight>();

      public int NormalId = -1;

      public FVector<int> NormalUids = new FVector<int>();
      public FVector<int> NormalIds = new FVector<int>();

       // 面集合
      protected FVector<SFloatVector3> _normals = new FVector<SFloatVector3>();

      public int BinormalId = -1;

      public FVector<int> BinormalTangentUids = new FVector<int>();
      public FVector<int> BinormalTangentIds = new FVector<int>();

      // 面集合
      protected FVector<SFloatVector3> _binormals = new FVector<SFloatVector3>();

      public int TangentId = -1;

      public FVector<int> TangentIds = new FVector<int>();

       // 面集合
      protected FVector<SFloatVector3> _tangents = new FVector<SFloatVector3>();

      public bool merged;

      public float Illum;

      public FVector<int> FaceIds = new FVector<int>();

       // 面集合
      protected FVector<FDrFace> _faces = new FVector<FDrFace>();

      // 面角度集合
      protected FVector<float> _faceAngles = new FVector<float>();

      //============================================================
      // <T>获得或设置编号。</T>
      //============================================================
      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      // <T>获得或设置调整编号。</T>
      //============================================================
      public int AdjuestId {
         get { return _adjuestId; }
         set { _adjuestId = value; }
      }

      //============================================================
      // <T>获得顶点。</T>
      //============================================================
      public SFloatPoint3 Position {
         get { return _position; }
      }

      //============================================================
      // <T>获得顶点颜色。</T>
      //============================================================
      public SFloatColor4 Color {
         get { return _color; }
      }

      //============================================================
      // <T>获得贴图坐标。</T>
      //============================================================
      public SFloatPoint2 Coord {
         get { return _coord; }
      }

      //============================================================
      // <T>获得贴图坐标。</T>
      //============================================================
      public SFloatPoint2 LightCoord {
         get { return _lightCoord; }
      }

      //============================================================
      // <T>获得法线。</T>
      //============================================================
      public SFloatVector3 Normal {
         get { return _normal; }
      }

      //============================================================
      // <T>获得切线。</T>
      //============================================================
      public SFloatVector3 Tangent {
          get { return _tangent; }
      }

      //============================================================
      // <T>获得副法线。</T>
      //============================================================
      public SFloatVector3 Binormal {
         get { return _binormal; }
      }

      //============================================================
      public FVector<FDrVertexWeight> Weights {
         get { return _weights; }
      }

      //============================================================
      public FVector<SFloatVector3> Normals {
         get { return _normals; }
      }

      //============================================================
      public FVector<SFloatVector3> Tangents {
         get { return _tangents; }
      }

      //============================================================
      public FVector<SFloatVector3> Binormals {
         get { return _binormals; }
      }
      
      //============================================================
      public FVector<FDrFace> Faces {
         get { return _faces; }
      }

      //============================================================
      public FVector<float> faceAngles {
         get { return _faceAngles; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadModelConfig(FXmlNode config) {
         _position.LoadConfig(config);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveInfoConfig(FXmlNode xconfig) {
         xconfig.Set("id", _id.ToString());
         xconfig.Set("position", _position.ToString());
         xconfig.Set("coord_count", CoordUids.Count);
         xconfig.Set("normal_count", NormalUids.Count);
         xconfig.Set("binormal_tangent_count", BinormalTangentUids.Count);
         xconfig.Set("normal_fix", normalFix);
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadWeightConfig(FXmlNode config) {
         // 读取权重信息
         foreach (FXmlNode node in config.Nodes) {
            if (node.IsName("Bone")) {
               FDrVertexWeight weight = new FDrVertexWeight();
               weight.LoadConfig(node);
               _weights.Push(weight);
            }
         }
         // 排序权重内容
         if (!_weights.IsEmpty) {
            _weights.Sort(FDrVertexWeight.Comparer);
            if (_weights.Count > _maxWeightCount) {
               float total = 0;
               for(int n = 0; n < _maxWeightCount; n++){
                  total += _weights[n].Weight;
               }
               for (int n = 0; n < _maxWeightCount; n++) {
                  FDrVertexWeight weight = _weights[n];
                  weight.Weight = weight.Weight / total;
               }
            }
         }
      }

      //============================================================
      public void UnserializeWeight(IInput input) {
         // 读取顶点坐标
         int boneCount = input.ReadInt32();
         for(int n = 0; n < boneCount; n++) {
            FDrVertexWeight weight = new FDrVertexWeight();
            weight.Unserialize(input);
            _weights.Push(weight);
         }
         // 排序权重内容
         if (!_weights.IsEmpty) {
            _weights.Sort(FDrVertexWeight.Comparer);
            if (_weights.Count > _maxWeightCount) {
               float total = 0;
               for (int n = 0; n < _maxWeightCount; n++) {
                  total += _weights[n].Weight;
               }
               for (int n = 0; n < _maxWeightCount; n++) {
                  FDrVertexWeight weight = _weights[n];
                  weight.Weight = weight.Weight / total;
               }
            }
         }
      }
      
      //============================================================
      public void ExportConfig(FXmlNode config) {
         // 设置信息
         config.Set("id", _id);
         // 输出顶点列表
         FXmlNode positionNode = config.CreateNode("Position");
         positionNode.Set("x", _position.X);
         positionNode.Set("y", _position.Y);
         positionNode.Set("z", _position.Z);
         // 输出顶点列表
         FXmlNode coordNode = config.CreateNode("Coord");
         coordNode.Set("x", _coord.X);
         coordNode.Set("y", _coord.Y);
         // 输出顶点列表
         FXmlNode normalNode = config.CreateNode("Normal");
         normalNode.Set("x", _normal.X);
         normalNode.Set("y", _normal.Y);
         normalNode.Set("z", _normal.Z);
      }

      //============================================================
      public void Serialize(FDrGeometry submesh, int flags, IOutput output) {
         // 输出顶点
         if (0 != (EDrVertex.Position & flags)) {
            _position.Serialize(output);
         }
         // 输出颜色
         if (0 != (EDrVertex.Color & flags)) {
            _color.SerializeByteUnsigned4(output);
         }
         // 输出坐标
         if (0 != (EDrVertex.Coord & flags)) {
            output.WriteFloat(_coord.X);
            output.WriteFloat(1 + _coord.Y);
            if (0 != (EDrVertex.CoordLight & flags)) {
               output.WriteFloat(_lightCoord.X);
               output.WriteFloat(_lightCoord.Y);
            }
         }
         // 输出法线，副法线，切线
         if (0 != (EDrVertex.Normal & flags)) {
            _normal.SerializeByteSigned3(output);
            output.WriteUint8(1);
            //output.WriteUint8((byte)Illum);
         }
         if (0 != (EDrVertex.Binormal & flags)) {
            _binormal.SerializeByteSigned3(output);
            output.WriteUint8(1);
         }
         if (0 != (EDrVertex.Tangent & flags)) {
            _tangent.SerializeByteSigned3(output);
            output.WriteUint8(1);
         }
         // 使用两条管道，每条管道4个数据，前管道为索引内容，后管道为权重
         int count = _weights.Count;
         if (0 != (EDrVertex.BoneIndex & flags)) {
            for (int n = 0; n < _maxWeightCount; n++) {
               int value = 0;
               if (n < count) {
                  value = submesh.FindAdjustBoneIndex(_weights[n].BoneId);
               }
               output.WriteUint8((byte)value);
            }
         }
         if (0 != (EDrVertex.BoneWeight & flags)) {
            for (int n = 0; n < _maxWeightCount; n++) {
               float value = 0;
               if (n < count) {
                  value = _weights[n].Weight;
               }
               output.WriteUint8((byte)(value * 255));
            }
         }
      }

      //============================================================
      public void SerializeBoneIndex(FDrGeometry submesh, IOutput output) {
         int count = _weights.Count;
         for (int n = 0; n < _maxWeightCount; n++) {
            int value = 0;
            if (n < count) {
               value = submesh.FindAdjustBoneIndex(_weights[n].BoneId);
            }
            //output.WriteFloat(value);
            output.WriteUint8((byte)value);
         }
      }

      //============================================================
      public void SerializeBoneWeight(FDrGeometry submesh, IOutput output) {
         int count = _weights.Count;
         for (int n = 0; n < _maxWeightCount; n++) {
            float value = 0;
            if (n < count) {
               value = _weights[n].Weight;
            }
            output.WriteUint8((byte)(value * 255));
            //output.WriteFloat(value);
         }
      }
   }
}
