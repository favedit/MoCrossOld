#include "MoFrContent3dTexture.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dTextureBitmap, FInstance);

//============================================================
// <T>构造资源3D纹理位图。</T>
//============================================================
FRs3dTextureBitmap::FRs3dTextureBitmap(){
   _textureCd = EContent3dTexture_Unknown;
   _samplerCd = EContent3dSampler_Unknown;
   _pData = MO_CREATE(FBytes);
}

//============================================================
// <T>析构资源3D纹理位图。</T>
//============================================================
FRs3dTextureBitmap::~FRs3dTextureBitmap(){
   MO_DELETE(_pData);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dTextureBitmap::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   _textureCd = (EContent3dTexture)pInput->ReadUint8();
   _samplerCd = (EContent3dSampler)pInput->ReadUint8();
   // 读取位图
   _size.Unserialize16(pInput);
   TInt dataLength = sizeof(TUint32) * _size.Square();
   _pData->ForceLength(dataLength);
   pInput->Read(_pData->Memory(), dataLength);
   return ESuccess;
}

MO_NAMESPACE_END
