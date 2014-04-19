#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dSceneMaterial, FInstance);

//============================================================
// <T>构造场景材质资源。</T>
//============================================================
FRs3dSceneMaterial::FRs3dSceneMaterial(){
}

//============================================================
// <T>析构场景材质资源。</T>
//============================================================
FRs3dSceneMaterial::~FRs3dSceneMaterial(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dSceneMaterial::Unserialize(IDataInput* pInput){
   // 读取属性
   _name.UnserializeAutomatic(pInput);
   _label.UnserializeAutomatic(pInput);
   // 读取设置
   _info.Unserialize(pInput);
   // 读取高度
   TFloat depth = pInput->ReadFloat();
   SetHeightDepth(depth);
   // 读取表面
   _surfaceRate = pInput->ReadFloat();
   _surfaceReflect = pInput->ReadFloat();
   _surfaceBright = pInput->ReadFloat();
   _surfaceBrightLevel = pInput->ReadFloat();
   _surfaceCoarse = pInput->ReadFloat();
   _surfaceCoarseLevel = pInput->ReadFloat();
   _surfaceMerge = pInput->ReadFloat();
   _surfacePower = pInput->ReadFloat();
   return ESuccess;
}

MO_NAMESPACE_END
