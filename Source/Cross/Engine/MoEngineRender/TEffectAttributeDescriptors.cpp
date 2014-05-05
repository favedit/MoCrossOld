#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造效果属性描述集合。</T>
//============================================================
TEffectAttributeDescriptors::TEffectAttributeDescriptors(){
}

//============================================================
// <T>注册一个描述信息。</T>
//
// @param code 代码
// @param pName 名称
// @param formatCd 格式
//============================================================
void TEffectAttributeDescriptors::Register(TInt code, TCharC* pName, ERenderVertexFormat formatCd){
   SEffectAttributeDescriptor descriptor;
   descriptor.code = code;
   descriptor.namePtr = pName;
   descriptor.formatCd = formatCd;
   descriptor.bindIndex = _count;
   Push(descriptor);
}

//============================================================
// <T>注册一个描述信息。</T>
//
// @param pLinker 关联信息
//============================================================
TResult TEffectAttributeDescriptors::Link(FRenderShaderAttribute* pAttribute){
   MO_CHECK(pAttribute, return ENull);
   TCharC* pLinker = pAttribute->Linker();
   // 解析内容
   EEffectAttribute attributeCd = EEffectAttribute_Unknown;
   ERenderShaderAttributeFormat formatCd = ERenderShaderAttributeFormat_Unknown;
   REffectAttribute::Parse(pLinker, attributeCd, formatCd);
   pAttribute->SetFormatCd(formatCd);
   // 设置参数
   SEffectAttributeDescriptor descriptor;
   descriptor.attributePtr = pAttribute;
   descriptor.code = attributeCd;
   descriptor.namePtr = pLinker;
   descriptor.bindId = pAttribute->Slot();
   Push(descriptor);
   return ESuccess;
}

MO_NAMESPACE_END
