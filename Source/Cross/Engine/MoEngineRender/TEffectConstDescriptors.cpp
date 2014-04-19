#include "MoErTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造效果常量描述集合。</T>
//============================================================
TEffectConstDescriptors::TEffectConstDescriptors(){
   _count = _capacity;
}

//============================================================
// <T>注册一个描述信息。</T>
//
// @param shaderCd 渲染类型
// @param code 代码
// @param pName 名称
//============================================================
void TEffectConstDescriptors::Register(ERenderShader shaderCd, TInt code, TCharC* pName){
   SEffectConstDescriptor& descriptor = _memory[code];
   descriptor.shaderCd = shaderCd;
   descriptor.code = code;
   descriptor.namePtr = pName;
   descriptor.bindId = -1;
}

MO_NAMESPACE_END
