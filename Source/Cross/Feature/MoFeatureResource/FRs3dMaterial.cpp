#include "MoFrContent3dMaterial.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dMaterial, FResource3d);

//============================================================
// <T>构造资源3D材质。</T>
//============================================================
FRs3dMaterial::FRs3dMaterial(){
}

//============================================================
// <T>析构资源3D材质。</T>
//============================================================
FRs3dMaterial::~FRs3dMaterial(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dMaterial::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取父信息
   FResource3d::Unserialize(pInput);
   _info.Unserialize(pInput);
   // 读取纹理集合
   TInt count = pInput->ReadInt8();
   for(TInt n = 0; n < count; n++){
      FRs3dMaterialTexture* pTexture = FRs3dMaterialTexture::InstanceCreate();
      pTexture->Unserialize(pInput);
      _textures.Push(pTexture);
   }
   return ESuccess;
}
      
//============================================================
// <T>重置数据。</T>
//============================================================
void FRs3dMaterial::Reset(){
   _info.Reset();
}

MO_NAMESPACE_END
