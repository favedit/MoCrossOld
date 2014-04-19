#include "MoFrContent3dBase.h"
#include "MoFrContent3dTexture.h"
#include "MoFrContent3dMaterial.h"
#include "MoFrContent3dTheme.h"
#include "MoFrContent3dModel.h"
#include "MoFrContent3dTemplate.h"
#include "MoFrContent3dScene.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FResource3dConsole, FInstance);

//============================================================
// <T>构造资源3D控制台。</T>
//============================================================
FResource3dConsole::FResource3dConsole(){
   MO_CLEAR(_pThemeConsole);
   MO_CLEAR(_pTextureConsole);
   MO_CLEAR(_pMaterialConsole);
   MO_CLEAR(_pModelConsole);
   MO_CLEAR(_pTemplateConsole);
   MO_CLEAR(_pSceneConsole);
}

//============================================================
// <T>析构资源3D控制台。</T>
//============================================================
FResource3dConsole::~FResource3dConsole(){
   MO_DELETE(_pThemeConsole);
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
TResult FResource3dConsole::Setup(){
   _pThemeConsole = MO_CREATE(FRs3dThemeConsole);
   _pModelConsole = MO_CREATE(FRs3dModelConsole);
   _pMaterialConsole = MO_CREATE(FRs3dMaterialConsole);
   _pTextureConsole = MO_CREATE(FRs3dTextureConsole);
   _pTemplateConsole = MO_CREATE(FRs3dTemplateConsole);
   _pSceneConsole = MO_CREATE(FRs3dSceneConsole);
   return ESuccess;
}

MO_NAMESPACE_END
