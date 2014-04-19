#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dBone, FInstance);

//============================================================
// <T>构造资源3D骨头。</T>
//============================================================
FRs3dBone::FRs3dBone(){
   MO_CLEAR(_pSkeleton);
   _id = -1;
   MO_CLEAR(_pParent);
   MO_CLEAR(_pTrack);
   MO_CLEAR(_pBones);
}

//============================================================
// <T>析构资源3D骨头。</T>
//============================================================
FRs3dBone::~FRs3dBone(){
   Clear();
   MO_DELETE(_pBones);
}

//============================================================
// <T>增加一个子骨头。</T>
//
// @param pBone 子骨头
//============================================================
void FRs3dBone::Push(FRs3dBone* pBone){
   if(_pBones == NULL){
      _pBones = MO_CREATE(FRs3dBoneVector);
   }
   _pBones->Push(pBone);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dBone::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   _id = pInput->ReadInt8();
   // 读取所有子节点
   TInt count = pInput->ReadInt8();
   for(TInt n = 0; n < count; n++){
      FRs3dBone* pBone = MO_CREATE(FRs3dBone);
      pBone->SetSkeleton(_pSkeleton);
      pBone->SetParent(this);
      pBone->Unserialize(pInput);
      Push(pBone);
   }
   // 增加对象
   _pSkeleton->PushBone(this);
   return ESuccess;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dBone::Clear(){
   if(_pBones != NULL){
      TInt count = _pBones->Count();
      for(TInt n = 0; n < count; n++){
         FRs3dBone* pBone = _pBones->Get(n);
         MO_DELETE(pBone);
      }
      _pBones->Clear();
   }
}

MO_NAMESPACE_END
