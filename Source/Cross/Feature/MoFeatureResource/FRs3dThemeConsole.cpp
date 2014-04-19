#include "MoFrContent3dMaterial.h"
#include "MoFrContent3dTheme.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源3D主题控制台。</T>
//============================================================
FRs3dThemeConsole::FRs3dThemeConsole(){
}

//============================================================
// <T>析构资源3D主题控制台。</T>
//============================================================
FRs3dThemeConsole::~FRs3dThemeConsole(){
}

//============================================================
// <T>获得默认主题。</T>
//
// @return 主题
//============================================================
FRs3dTheme* FRs3dThemeConsole::DefaultTheme(){
   return NULL;
}

//============================================================
// <T>根据资源编号查找主题。</T>
//
// @param resourceId 资源编号
// @return 主题
//============================================================
FRs3dTheme* FRs3dThemeConsole::Find(TResourceId resourceId){
   return NULL;
}

//============================================================
// <T>从输入流里反序列化信息内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dThemeConsole::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   GRs3dMaterialPtrDictionary& materials = RResource3dManager::Instance().MaterialConsole()->Materials();
   TInt count = pInput->ReadInt32();
   for(TInt n = 0; n < count; n++){
      FRs3dMaterial* pMaterial = MO_CREATE(FRs3dMaterial);
      pMaterial->Unserialize(pInput);
      materials.Set(pMaterial->Name(), pMaterial);
   }
   MO_DEBUG("Unserial theme material success. (material_count=%d)", count);
   return ESuccess;
}

//============================================================
// <T>打开处理。</T>
//
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FRs3dThemeConsole::Open(TCharC* pCode){
   FAssetStream* pStream = RAssetManager::Instance().OpenAssetStream(pCode);
   MO_ERROR_CHECK(pStream, return EFailure, "Open theme failure. (code=%s)", pCode);
   Unserialize(pStream);
   RAssetManager::Instance().CloseAssetStream(pStream);
   return ESuccess;
}

//============================================================
// <T>清空内容。</T>
//============================================================
TResult FRs3dThemeConsole::Clear(){
   return ESuccess;
}

MO_NAMESPACE_END
