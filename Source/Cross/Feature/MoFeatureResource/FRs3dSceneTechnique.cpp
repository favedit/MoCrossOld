#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneTechnique, FInstance);

//============================================================
// <T>构造场景技术资源。</T>
//============================================================
FRs3dSceneTechnique::FRs3dSceneTechnique(){
}

//============================================================
// <T>析构场景技术资源。</T>
//============================================================
FRs3dSceneTechnique::~FRs3dSceneTechnique(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneTechnique::Unserialize(IDataInput* pInput){
   _name.UnserializeAutomatic(pInput);
   // 读取渲染过程集合
   TInt passCount = pInput->ReadInt32();
   for(TInt n = 0; n < passCount; n++){
      FRs3dScenePass* pPass = FRs3dScenePass::InstanceCreate();
      pPass->Unserialize(pInput);
      _passes.Push(pPass);
   }
   return ESuccess;
}

MO_NAMESPACE_END
