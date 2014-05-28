#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderProgramBuffer, FRenderInstance);

//============================================================
// <T>构造渲染器缓冲。</T>
//============================================================
FRenderProgramBuffer::FRenderProgramBuffer(){
   _groupCd = ERenderShaderBuffer_Unknown;
   _bufferCd = ERenderShaderBuffer_Unknown;
   _dataLength = 0;
   _statusUsed = EFalse;
   _statusChanged = EFalse;
   _pData = MO_CREATE(FBytes);
   _shaderCd = ERenderShader_Unknown;
   _slot = -1;
}

//============================================================
// <T>析构渲染器缓冲。</T>
//============================================================
FRenderProgramBuffer::~FRenderProgramBuffer(){
   MO_DELETE(_pData);
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderProgramBuffer::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置信息
   _bufferCd = RRenderShaderBuffer::Parse(_linker);
   _groupCd = RRenderShaderBuffer::ParseGroup(_bufferCd);
   _slot = RRenderShaderBuffer::ParseSlot(_bufferCd);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderProgramBuffer::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   if(_dataLength == 0){
      return ESuccess;
   }
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
TResult FRenderProgramBuffer::Set(TInt slot, TAnyC* pData, TInt length){
   // 检查是否使用
   if(!_statusUsed){
      return EContinue;
   }
   // 检查参数
   MO_CHECK(slot >= 0, return EOutRange);
   MO_CHECK(pData, return ENull);
   MO_CHECK(length >= 0, return EOutRange);
   MO_CHECK(slot + length <= _dataLength, return EOutRange);
   // 检查数据变更
   TByte* pMemory = _pData->Memory() + slot;
   if(!_statusChanged){
      if(MO_LIB_MEMORY_COMPARE(pMemory, pData, length) == 0){
         return EContinue;
      }
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
TResult FRenderProgramBuffer::Commit(){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderProgramBuffer::Update(){
   // 检查是否使用
   if(!_statusUsed){
      return EContinue;
   }
   // 更新数据
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   // 提交数据
   if(_statusChanged){
      Commit();
      _statusChanged = EFalse;
   }
   return ESuccess;
}

//============================================================
// <T>绑定处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderProgramBuffer::Bind(){
   return ESuccess;
}

//============================================================
// <T>获得内部运行信息。</T>
//
// @param pDump 输出缓冲
// @return 处理结果
//============================================================
TResult FRenderProgramBuffer::Dump(MString* pDump, TBool detail){
   MO_CHECK(pDump, return ENull);
   pDump->AppendFormat("Buffer: name=%s, linker=%s, slot=%d", (TCharC*)_name, (TCharC*)_linker, _slot);
   return ESuccess;
}

MO_NAMESPACE_END
