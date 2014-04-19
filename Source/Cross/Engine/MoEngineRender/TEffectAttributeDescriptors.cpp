#include "MoErTechnique.h"

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

MO_NAMESPACE_END
