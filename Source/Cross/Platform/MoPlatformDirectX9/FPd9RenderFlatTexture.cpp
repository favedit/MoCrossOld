#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderFlatTexture, FRenderFlatTexture);

//============================================================
// <T>构造平面纹理。</T>
//============================================================
FPd9RenderFlatTexture::FPd9RenderFlatTexture(){
   MO_CLEAR(_piTexture);
}

//============================================================
// <T>析构平面纹理。</T>
//============================================================
FPd9RenderFlatTexture::~FPd9RenderFlatTexture(){
   MO_RELEASE(_piTexture);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::OnSetup(){
   FRenderFlatTexture::OnSetup();
   //glGenTextures(1, &_textureId);
   //_graphicHandle.data.uint32 = _textureId;
   //MO_FATAL_CHECK(_textureId != 0, return EFailure,
   //      "Generate flat texture id failure. (texture_id=%d)", _textureId);
   //glBindTexture(GL_TEXTURE_2D, _textureId);
   return ESuccess;
}

//============================================================
// <T>改变大小处理。</T>
//
// @param width 宽度
// @param height 高度
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::Resize(TInt width, TInt height){
   _size.Set(width, height);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return ENull);
   MO_CHECK(_pDevice, return ENull);
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   // 释放资源
   MO_RELEASE(_piTexture);
   // 创建纹理
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateTexture(_size.width, _size.height, 0, 0, D3DFMT_A8R8G8B8, D3DPOOL_MANAGED, &_piTexture, NULL);
   if(FAILED(dxResult)){
      MO_FATAL("Create texture 2d failure.");
      return EFailure;
   }
   // 上传数据
   D3DLOCKED_RECT rect;
   dxResult = _piTexture->LockRect(0, &rect, NULL, 0);
   if(FAILED(dxResult)){
      MO_FATAL("Lock buffer failure.");
      return EFailure;
   }
   MO_LIB_MEMORY_COPY(rect.pBits, length, pData, length);
   _piTexture->UnlockRect(0);
   return ESuccess;
}

//============================================================
// <T>从输入流内反序列化数据。</T>
//
// @return pInput 输入流
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::Unserialize(IDataInput* pInput){
   // 检查参数
   MO_CHECK(pInput, return ENull);
   // 读取尺寸
   _size.Unserialize16(pInput);
   // 上传数据
   TByte* pData = pInput->PositionMemory();
   Upload(pData, 4 * _size.Square());
   return ESuccess;
}

//============================================================
// <T>加载数据文件。</T>
//============================================================
TResult FPd9RenderFlatTexture::LoadDataFile(TCharC* pFileName){
   // 检查参数
   MO_CHECK(pFileName, return ENull);
   // 读取文件
   FByteFile* pFile = MO_CREATE(FByteFile);
   pFile->LoadFile(pFileName);
   // 反序列化数据
   Unserialize(pFile);
   // 释放资源
   MO_DELETE(pFile);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::Resume(){
   OnSetup();
   if(!_pData->IsEmpty()){
      Upload(_pData->MemoryC(), _pData->Length());
   }
   //MO_INFO("Resume texture. (texture_id=%d, size=%dx%d, data_length=%d)", _textureId, _size.width, _size.height, _pData->Length());
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderFlatTexture::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
