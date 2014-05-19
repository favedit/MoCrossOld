#include "MoE3Texture.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTexture3dBitmap, FInstance);

//============================================================
// <T>构造资源3D纹理位图。</T>
//============================================================
FTexture3dBitmap::FTexture3dBitmap(){
   MO_CLEAR(_pResource);
}

//============================================================
// <T>析构资源3D纹理位图。</T>
//============================================================
FTexture3dBitmap::~FTexture3dBitmap(){
}

//============================================================
// <T>加载数据资源。</T>
//
// @param pResource 资源
// @return 处理结果
//============================================================
TResult FTexture3dBitmap::LoadResource(FRs3dTextureBitmap* pResource){
   MO_CHECK(pResource, return ENull);
   _pResource = pResource;
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FTexture3dBitmap::Open(){
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 读取位图
   ERenderTexture textureCd = (ERenderTexture)_pResource->TextureCd();
   TCharC* pCode = _pResource->Code();
   FBytes* pData = _pResource->Data();
   SIntSize2& size = _pResource->Size();
   // 创建纹理
   if(textureCd == ERenderTexture_Flat2d){
      _renderTexture = pRenderDevice->CreateFlatTexture();
      _renderTexture->Size().Assign(size);
   }else if(textureCd == ERenderTexture_Cube){
      _renderTexture = pRenderDevice->CreateCubeTexture();
      _renderTexture->Size().Set(size.width, size.width);
   }else{
      MO_FATAL_UNSUPPORT();
   }
   //_renderTexture->SetSamplerCd(pCode);
   _renderTexture->SetFilterCd(ERenderTextureFilter_Linear);
   _renderTexture->Setup();
   // 上传数据
   _renderTexture->Upload(pData->MemoryC(), pData->Length());
   return ESuccess;
}

//============================================================
// <T>关闭打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FTexture3dBitmap::Close(){
   _renderTexture = NULL;
   return ESuccess;
}

MO_NAMESPACE_END
