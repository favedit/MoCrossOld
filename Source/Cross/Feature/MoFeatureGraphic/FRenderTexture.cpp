#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderTexture, FTexture);

//============================================================
// <T>构造渲染纹理。</T>
//============================================================
FRenderTexture::FRenderTexture(){
   MO_CLEAR(_pDevice);
   _textureCd = ERenderTexture_Unknown;
   _formatCd = ERenderTextureFormat_BGRA;
   _filterCd = ERenderTextureFilter_Linear;
   _wrapCd = ERenderTextureWrap_Repeat;
   _pData = MO_CREATE(FBytes);
}

//============================================================
// <T>析构渲染纹理。</T>
//============================================================
FRenderTexture::~FRenderTexture(){
   MO_DELETE(_pData);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderTexture::OnSetup(){
   TResult result = FTexture::OnSetup();
   _graphicHandle.instancePtr = this;
   return result;
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderTexture::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FRenderTexture::Resume(){
   return ESuccess;
}

//============================================================
// <T>上传数据。</T>
//
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FRenderTexture::Upload(TByteC* pData, TInt length){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>从输入流中反序列化数据。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRenderTexture::Unserialize(IDataInput* pInput){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

MO_NAMESPACE_END
