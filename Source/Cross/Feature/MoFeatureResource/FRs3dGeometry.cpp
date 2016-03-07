#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dGeometry, FInstance);

//============================================================
// <T>构造资源3D几何体。</T>
//============================================================
FRs3dGeometry::FRs3dGeometry(){
   _optionInstanced = EFalse;
   _instanceCount = 0;
   _vertexBuffer = MO_CREATE(FRs3dVertexBuffer);
   _indexBuffer = MO_CREATE(FRs3dIndexBuffer);
   _pBoneIds = MO_CREATE(FBoneIds);
   _pTrack = MO_CREATE(FRs3dTrack);
}

//============================================================
// <T>析构资源3D几何体。</T>
//============================================================
FRs3dGeometry::~FRs3dGeometry(){
   MO_DELETE(_pBoneIds);
   MO_DELETE(_pTrack);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dGeometry::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   _optionInstanced = pInput->ReadBool();
   _instanceCount = pInput->ReadUint8();
   _matrix.Unserialize(pInput);
   _outline.Unserialize(pInput);
   _materialName.UnserializeAutomatic(pInput);
   // 读取顶点缓冲
   _vertexBuffer->Unserialize(pInput);
   // 读取索引缓冲
   _indexBuffer->Unserialize(pInput);
   // 读取骨头集合
   TInt boneCount = pInput->ReadInt8();
   for(TInt n = 0; n < boneCount; n++){
      _pBoneIds->Push(pInput->ReadUint8());
   }
   // 读取跟踪
   _pTrack->Unserialize(pInput);
   MO_DEBUG(TC("Unserialize geometry success. (vertex=%d, index=%d, bone=%d, frame=%d)"),
         _vertexBuffer->Count(), _indexBuffer->Count(), boneCount, _pTrack->Frames().Count());
   return ESuccess;
}

MO_NAMESPACE_END
