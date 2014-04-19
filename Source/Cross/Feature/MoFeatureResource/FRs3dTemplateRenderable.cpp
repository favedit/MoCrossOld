#include "MoFrContent3dModel.h"
#include "MoFrContent3dTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dTemplateRenderable, FInstance);

//============================================================
// <T>构造资源3D模板渲染对象。</T>
//============================================================
FRs3dTemplateRenderable::FRs3dTemplateRenderable(){
   _geometryIndex = 0;
}

//============================================================
// <T>析构资源3D模板渲染对象。</T>
//============================================================
FRs3dTemplateRenderable::~FRs3dTemplateRenderable(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dTemplateRenderable::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性内容
   _modelName.UnserializeAutomatic(pInput);
   _geometryIndex = pInput->ReadInt16();
   _materialName.UnserializeAutomatic(pInput);
   // 加载配置
   _optionInstanced = pInput->ReadBool();
   _instanceCount = pInput->ReadUint8();
   _optionDynamic = pInput->ReadBool();
   _optionMerge = pInput->ReadBool();
   _optionBoneScale = pInput->ReadBool();
   _optionSelect = pInput->ReadBool();
   _optionVisible = pInput->ReadBool();
   _optionGround = pInput->ReadBool();
   // 读取矩阵
   _matrix.Unserialize(pInput);
   return ESuccess;
}

MO_NAMESPACE_END
