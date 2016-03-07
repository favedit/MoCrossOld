#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源3D骨骼。</T>
//============================================================
FRs3dSkeleton::FRs3dSkeleton(){
   _pRootBones = MO_CREATE(FRs3dBoneVector);
   _pBones = MO_CREATE(FRs3dBoneSet);
}

//============================================================
// <T>析构资源3D骨骼。</T>
//============================================================
FRs3dSkeleton::~FRs3dSkeleton(){
   MO_DELETE(_pRootBones);
   MO_DELETE(_pBones);
}

//============================================================
// <T>根据骨头编号查找骨头对象。</T>
//
// @param boneId 骨头编号
// @return 骨头对象
//============================================================
FRs3dBone* FRs3dSkeleton::Find(TInt boneId){
   return _pBones->Find((TBoneId)boneId);
}

//============================================================
// <T>增加一个骨头。</T>
//
// @param pBone 骨头
//============================================================
void FRs3dSkeleton::PushBone(FRs3dBone* pBone){
   TBoneId boneId = (TBoneId)pBone->Id();
   _pBones->Set(boneId, pBone);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSkeleton::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取所有子节点
   TInt count = pInput->ReadInt8();
   for(TInt n = 0; n < count; n++){
      FRs3dBone* pBone = MO_CREATE(FRs3dBone);
      pBone->SetSkeleton(this);
      pBone->Unserialize(pInput);
      _pRootBones->Push(pBone);
   }
   return ESuccess;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FRs3dSkeleton::Clear(){
}

MO_NAMESPACE_END
