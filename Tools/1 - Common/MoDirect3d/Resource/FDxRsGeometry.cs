using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsGeometry : FObject
   {
      protected string _name;

      protected string _materialName;

      protected string _effectName;

      protected SFloatMatrix3D _localMatrix = new SFloatMatrix3D();

      protected SFloatMatrix3D _worldMatrix = new SFloatMatrix3D();

      protected SFloatPoint3 _outlineMin = new SFloatPoint3();

      protected SFloatPoint3 _outlineMax = new SFloatPoint3();

      protected int _vertexSize;

      protected int _vertexPosition;

      protected int _vertexColor;

      protected int _vertexCoord;

      protected int _vertexNormal;

      protected int _vertexBinormal;

      protected int _vertexTangent;

      protected int _vertexBoneIndex;

      protected int _vertexBoneWeight;

      protected FByteStream _vertexBuffer = new FByteStream();

      protected FByteStream _faceBuffer = new FByteStream();

      protected FDxRsTrack _track = new FDxRsTrack();

      //============================================================
      public FDxRsGeometry() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public string MaterialName {
         get { return _materialName; }
      }

      //============================================================
      public string EffectName {
         get { return _effectName; }
      }

      //============================================================
      public SFloatMatrix3D LocalMatrix {
         get { return _localMatrix; }
      }

      //============================================================
      public SFloatMatrix3D WorldMatrix {
         get { return _worldMatrix; }
      }

      //============================================================
      public SFloatPoint3 OutlineMin {
         get { return _outlineMin; }
      }

      //============================================================
      public SFloatPoint3 OutlineMax {
         get { return _outlineMax; }
      }

      //============================================================
      public int VertexSize {
         get { return _vertexSize; }
      }

      //============================================================
      public int VertexPosition {
         get { return _vertexPosition; }
      }

      //============================================================
      public int VertexColor {
         get { return _vertexColor; }
      }

      //============================================================
      public int VertexCoord {
         get { return _vertexCoord; }
      }

      //============================================================
      public int VertexNormal {
         get { return _vertexNormal; }
      }

      //============================================================
      public int VertexBinormal {
         get { return _vertexBinormal; }
      }

      //============================================================
      public int VertexTangent {
         get { return _vertexTangent; }
      }

      //============================================================
      public int VertexBoneIndex {
         get { return _vertexBoneIndex; }
      }

      //============================================================
      public int VertexBoneWeight {
         get { return _vertexBoneWeight; }
      }

      //============================================================
      public FByteStream VertexBuffer {
         get { return _vertexBuffer; }
      }

      //============================================================
      public FByteStream FaceBuffer {
         get { return _faceBuffer; }
      }
      
      //============================================================
      public void Unserialize(IInput input) {
         _name = input.ReadString();
         _materialName = input.ReadString();
         _effectName = input.ReadString();
         // 读取矩阵
         _localMatrix.Unserialize(input);
         _worldMatrix.Unserialize(input);
         // 读取轮廓
         _outlineMin.Unserialize(input);
         _outlineMax.Unserialize(input);
         // 读取标识
         int flag = input.ReadInt16();
         _vertexPosition = input.ReadInt8();
         _vertexColor = input.ReadInt8();
         _vertexCoord = input.ReadInt8();
         _vertexNormal = input.ReadInt8();
         _vertexBinormal = input.ReadInt8();
         _vertexTangent = input.ReadInt8();
         _vertexBoneIndex = input.ReadInt8();
         _vertexBoneWeight = input.ReadInt8();
         // 读取顶点
         int vertexCount = input.ReadInt32();
         _vertexSize = input.ReadUint8();
         _vertexSize = 20;
         for(int n = 0; n < vertexCount; n++) {
            // 输出顶点
            if(-1 != _vertexPosition) {
               _vertexBuffer.WriteFloat(input.ReadFloat());
               _vertexBuffer.WriteFloat(input.ReadFloat());
               _vertexBuffer.WriteFloat(input.ReadFloat());
               _vertexBuffer.WriteFloat(1.0f);
            }
            // 输出颜色
            if(-1 != _vertexColor) {
               _vertexBuffer.WriteFloat((float)input.ReadUint8() / 255);
               _vertexBuffer.WriteFloat((float)input.ReadUint8() / 255);
               _vertexBuffer.WriteFloat((float)input.ReadUint8() / 255);
               _vertexBuffer.WriteFloat((float)input.ReadUint8() / 255);
            } else {
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
            }
            // 输出坐标
            if(-1 != _vertexCoord) {
               _vertexBuffer.WriteFloat(input.ReadFloat());
               _vertexBuffer.WriteFloat(input.ReadFloat());
            } else {
               _vertexBuffer.WriteFloat(0.0f);
               _vertexBuffer.WriteFloat(0.0f);
            }
            // 输出法线，副法线，切线
            if(-1 != _vertexNormal) {
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(input.ReadUint8());
            } else {
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
            }
            if(-1 != _vertexBinormal) {
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
            } else {
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
            }
            if(-1 != _vertexTangent) {
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
               _vertexBuffer.WriteFloat(((float)input.ReadUint8() / 255 - 0.5f) * 2.0f);
            } else {
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
               _vertexBuffer.WriteFloat(1.0f);
            }
            // 使用两条管道，每条管道4个数据，前管道为索引内容，后管道为权重
            if(-1 != _vertexBoneIndex) {
               //_vertexBuffer.WriteFloat(input.ReadFloat());
               //_vertexBuffer.WriteFloat(input.ReadFloat());
               //_vertexBuffer.WriteFloat(input.ReadFloat());
               //_vertexBuffer.WriteFloat(input.ReadFloat());
            }
            if(-1 != _vertexBoneWeight) {
               //_vertexBuffer.WriteFloat(input.ReadFloat());
               //_vertexBuffer.WriteFloat(input.ReadFloat());
               //_vertexBuffer.WriteFloat(input.ReadFloat());
               //_vertexBuffer.WriteFloat(input.ReadFloat());
            }
         }
         // 读取面索引
         int faceCount = input.ReadInt32();
         for(int n = 0; n < faceCount; n++) {
            _faceBuffer.WriteUint32(input.ReadUint16());
            _faceBuffer.WriteUint32(input.ReadUint16());
            _faceBuffer.WriteUint32(input.ReadUint16());
         }
         // 读取骨骼列表
         int boneCount = input.ReadUint8();
         for(int n = 0; n < boneCount; n++) {
            input.ReadUint8();
         }
         // 读取骨骼列表
         _track.Unserialize(input);
      }
   }
}
