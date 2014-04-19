#include "MoEoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEoRenderFlatTexture, FRenderFlatTexture);

//============================================================
// <T>构造平面纹理。</T>
//============================================================
FEoRenderFlatTexture::FEoRenderFlatTexture(){
   _textureId = 0;
}

//============================================================
// <T>析构平面纹理。</T>
//============================================================
FEoRenderFlatTexture::~FEoRenderFlatTexture(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderFlatTexture::OnSetup(){
   FRenderFlatTexture::OnSetup();
   glGenTextures(1, &_textureId);
   _graphicHandle.data.uint32 = _textureId;
   MO_FATAL_CHECK(_textureId != 0, return EFailure,
         "Generate flat texture id failure. (texture_id=%d)", _textureId);
   glBindTexture(GL_TEXTURE_2D, _textureId);
   return ESuccess;
}

//============================================================
// <T>改变大小处理。</T>
//
// @param width 宽度
// @param height 高度
// @return 处理结果
//============================================================
TResult FEoRenderFlatTexture::Resize(TInt width, TInt height){
   _size.Set(width, height);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderFlatTexture::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return ENull);
   // 检查编号
   MO_FATAL_CHECK(_textureId != 0, return EFailure,
         "Texture id is invalid. (texture_id=%d)", _textureId);
   // 绑定纹理
   glBindTexture(GL_TEXTURE_2D, _textureId);
   // 设置过滤
   switch(_filterCd){
      case ERenderTextureFilter_Nearest:
         glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
         glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
         break;
      case ERenderTextureFilter_Linear:
         glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
         glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
         break;
   }
   // 设置展开
   switch(_wrapCd){
      case MO::ERenderTextureWrap_Clamp:
         //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
         break;
      case MO::ERenderTextureWrap_Repeat:
         //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
         break;
   }
   //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
   //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
   // 上传数据
   glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, _size.width, _size.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, (const GLvoid*)pData);
   if(_pData->MemoryC() != pData){
      _pData->Assign(pData, length);
   }
   return ESuccess;
}

//============================================================
// <T>从输入流内反序列化数据。</T>
//
// @return pInput 输入流
// @return 处理结果
//============================================================
TResult FEoRenderFlatTexture::Unserialize(IDataInput* pInput){
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
TResult FEoRenderFlatTexture::LoadDataFile(TCharC* pFileName){
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
TResult FEoRenderFlatTexture::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderFlatTexture::Resume(){
   OnSetup();
   if(!_pData->IsEmpty()){
      Upload(_pData->MemoryC(), _pData->Length());
   }
   MO_INFO("Resume texture. (texture_id=%d, size=%dx%d, data_length=%d)", _textureId, _size.width, _size.height, _pData->Length());
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderFlatTexture::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
