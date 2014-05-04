#include "MoPd11Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd11RenderShaderBuffer, FRenderObject);

//============================================================
// <T>构造渲染器缓冲。</T>
//============================================================
FPd11RenderShaderBuffer::FPd11RenderShaderBuffer(){
   _dataLength = 0;
   _pData = MO_CREATE(FBytes);
   _pCommit = MO_CREATE(FBytes);
   _commitLength = 0;
   _slot = -1;
   MO_CLEAR(_piBuffer);
}

//============================================================
// <T>析构渲染器缓冲。</T>
//============================================================
FPd11RenderShaderBuffer::~FPd11RenderShaderBuffer(){
   MO_DELETE(_pData);
   MO_DELETE(_pCommit);
   MO_RELEASE(_piBuffer);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderShaderBuffer::Setup(){
   MO_CHECK(_pDevice, return ENull);
   MO_CHECK(_dataLength > 0, return EOutRange);
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   // 设置缓冲
   _pData->SetLength(_dataLength);
   RBytes::Clear(_pData->Memory(), _dataLength);
   _pCommit->SetLength(_dataLength);
   RBytes::Clear(_pCommit->Memory(), _dataLength);
   // 设置描述
   D3D11_BUFFER_DESC descriptor;
   RType<D3D11_BUFFER_DESC>::Clear(&descriptor);
   descriptor.ByteWidth = _dataLength;
   descriptor.Usage = D3D11_USAGE_DEFAULT;
   descriptor.BindFlags = D3D11_BIND_CONSTANT_BUFFER;
   // 设置数据
   D3D11_SUBRESOURCE_DATA data;
   RType<D3D11_SUBRESOURCE_DATA>::Clear(&data);
   data.pSysMem = _pData->Memory();
   // 创建缓冲
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateBuffer(&descriptor, &data, &_piBuffer);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   return ESuccess;
}

//============================================================
// <T>设置常量的数据内容。</T>
//
// @param pName 名称
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FPd11RenderShaderBuffer::SetData(TInt index, TAnyC* pData, TInt length){
   // 检查参数
   MO_CHECK(index >= 0, return EOutRange);
   MO_CHECK(pData, return ENull);
   MO_CHECK(length >= 0, return EOutRange);
   TInt endPosition = index + length;
   MO_CHECK(endPosition <= _dataLength, return EOutRange);
   // 设置提交大小
   if(endPosition > _commitLength){
      _commitLength = endPosition;
   }
   // 复制内存
   TByte* pMemory = _pData->Memory() + index;
   MO_LIB_MEMORY_COPY(pMemory, length, pData, length);
   return ESuccess;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd11RenderShaderBuffer::Update(){
   MO_CHECK(_pDevice, return ENull);
   TResult resultCd = ESuccess;
   FPd11RenderDevice* pRenderDevice = _pDevice->Convert<FPd11RenderDevice>();
   //............................................................
   TByte* pMemoryData = _pData->Memory();
   TByte* pCommitData = _pCommit->Memory();
   // 查找开始不相等位置
   TInt startPosition = 0;
   for(TInt n = startPosition; n < _commitLength; n++){
      if(pMemoryData[n] != pCommitData[n]){
         startPosition = n;
         break;
      }
   }
   // 查找结束不相等位置
   TInt endPosition = _commitLength - 1;
   for(TInt n = endPosition; n >= startPosition; n--){
      if(pMemoryData[n] != pCommitData[n]){
         endPosition = n;
         break;
      }
   }
   // 提交数据
   if(startPosition != endPosition){
      TInt length = endPosition - startPosition;
      // 写入数据
      D3D11_MAPPED_SUBRESOURCE resource;
      pRenderDevice->NativeContext()->Map(_piBuffer, 0, D3D11_MAP_READ_WRITE, 0, &resource);
      MO_LIB_MEMORY_COPY(resource.pData, length, pMemoryData + startPosition, length);
      pRenderDevice->NativeContext()->Unmap(_piBuffer, 0);
      // 设置提交数据
      MO_LIB_MEMORY_COPY(pCommitData + startPosition, length, pMemoryData + startPosition, length);
   }
   _commitLength = 0;
   return ESuccess;
}

MO_NAMESPACE_END
