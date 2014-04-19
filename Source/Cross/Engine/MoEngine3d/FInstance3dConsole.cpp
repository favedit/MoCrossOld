#include "MoE3Instance.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造实体3D控制台。</T>
//============================================================
FInstance3dConsole::FInstance3dConsole(){
   MO_CLEAR(_pTextureConsole);
   MO_CLEAR(_pMaterialConsole);
   MO_CLEAR(_pModelConsole);
   MO_CLEAR(_pTemplateConsole);
   MO_CLEAR(_pSceneConsole);
}

//============================================================
// <T>析构实体3D控制台。</T>
//============================================================
FInstance3dConsole::~FInstance3dConsole(){
   MO_DELETE(_pTextureConsole);
   MO_DELETE(_pMaterialConsole);
   MO_DELETE(_pModelConsole);
   MO_DELETE(_pTemplateConsole);
   MO_DELETE(_pSceneConsole);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FInstance3dConsole::Setup(){
   _pTextureConsole = MO_CREATE(FTexture3dConsole);
   _pMaterialConsole = MO_CREATE(FMaterial3dConsole);
   _pModelConsole = MO_CREATE(FModel3dConsole);
   _pTemplateConsole = MO_CREATE(FTemplate3dConsole);
   _pSceneConsole = MO_CREATE(FScene3dConsole);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FInstance3dConsole::Suspend(){
   _pTextureConsole->Suspend();
   _pMaterialConsole->Suspend();
   _pModelConsole->Suspend();
   _pTemplateConsole->Suspend();
   _pSceneConsole->Suspend();
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FInstance3dConsole::Resume(){
   _pTextureConsole->Resume();
   _pMaterialConsole->Resume();
   _pModelConsole->Resume();
   _pTemplateConsole->Resume();
   _pSceneConsole->Resume();
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FInstance3dConsole::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
