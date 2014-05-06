#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderShaderBuffer, FRenderObject);

//============================================================
// <T>构造渲染器缓冲。</T>
//============================================================
FRenderShaderBuffer::FRenderShaderBuffer(){
   _dataLength = 0;
   _statusChanged = EFalse;
   _pData = MO_CREATE(FBytes);
   _shaderCd = ERenderShader_Unknown;
   _slot = -1;
}

//============================================================
// <T>析构渲染器缓冲。</T>
//============================================================
FRenderShaderBuffer::~FRenderShaderBuffer(){
   MO_DELETE(_pData);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderShaderBuffer::Setup(){
   MO_CHECK(_pDevice, return ENull);
   MO_CHECK(_dataLength > 0, return EOutRange);
   // 设置缓冲
   if(_pData->Length() != _dataLength){
      _pData->SetLength(_dataLength);
      RBytes::Clear(_pData->Memory(), _dataLength);
   }
   return ESuccess;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param slot 插槽
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FRenderShaderBuffer::Set(TInt slot, TAnyC* pData, TInt length){
   // 检查参数
   MO_CHECK(slot >= 0, return EOutRange);
   MO_CHECK(pData, return ENull);
   MO_CHECK(length >= 0, return EOutRange);
   MO_CHECK(slot + length <= _dataLength, return EOutRange);
   // 检查数据变更
   TByte* pMemory = _pData->Memory() + slot;
   if(!_statusChanged){
      //if(MO_LIB_MEMORY_COMPARE(pMemory, pData, length) == 0){
      //   return EContinue;
      //}
      _statusChanged = ETrue;
   }
   // 复制内存
   MO_LIB_MEMORY_COPY(pMemory, length, pData, length);
   return ESuccess;
}

//============================================================
// <T>提交处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderShaderBuffer::Commit(){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderShaderBuffer::Update(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   // 提交数据
   //if(_statusChanged){
   //   Commit();
   //   _statusChanged = EFalse;
   //}
   Commit();
   return ESuccess;
}

MO_NAMESPACE_END
