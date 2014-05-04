#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderShaderParameter, FRenderObject);

//============================================================
// <T>构造渲染器参数。</T>
//============================================================
FRenderShaderParameter::FRenderShaderParameter(){
   _shaderCd = ERenderShader_Unknown;
   _formatCd = ERenderShaderParameterFormat_Unknown;
   _statusUsed = EFalse;
   MO_CLEAR(_pShader);
}

//============================================================
// <T>析构渲染器参数。</T>
//============================================================
FRenderShaderParameter::~FRenderShaderParameter(){
}

//============================================================
// <T>获得数据内容。</T>
//
// @param pData 数据
// @param capacity 容量
// @return 处理结果
//============================================================
TResult FRenderShaderParameter::Get(TAny* pData, TInt capacity){
   MO_FATAL_UNSUPPORT();
   return EUnsupport;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FRenderShaderParameter::Set(TAny* pData, TInt length){
   MO_FATAL_UNSUPPORT();
   return EUnsupport;
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderShaderParameter::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置格式
   TCharC* pShader = pConfig->Get("shader");
   _shaderCd = RRenderShader::Parse(pShader);
   // 设置格式
   TCharC* pFormat = pConfig->Get("format");
   _formatCd = RRenderShaderParameterFormat::Parse(pFormat);
   return ESuccess;
}

MO_NAMESPACE_END
