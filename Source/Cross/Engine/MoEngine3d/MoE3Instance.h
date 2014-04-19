#ifndef __MO_E3_INSTANCE_H__
#define __MO_E3_INSTANCE_H__
//************************************************************

#ifndef __MO_EG_DISPLAY3D_H__
#include "MoE3Display.h"
#endif // __MO_EG_DISPLAY3D_H__

#ifndef __MO_E3_TEXTURE_H__
#include "MoE3Texture.h"
#endif // __MO_E3_TEXTURE_H__

#ifndef __MO_E3_MATERIAL_H__
#include "MoE3Material.h"
#endif // __MO_E3_MATERIAL_H__

#ifndef __MO_E3_MODEL_H__
#include "MoE3Model.h"
#endif // __MO_E3_MODEL_H__

#ifndef __MO_E3_SCENE_H__
#include "MoE3Scene.h"
#endif // __MO_E3_SCENE_H__

#ifndef __MO_E3_TEMPLATE_H__
#include "MoE3Template.h"
#endif // __MO_E3_TEMPLATE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FTexture3dConsole;
class FMaterial3dConsole;
class FAnimation3dConsole;
class FModel3dConsole;
class FTemplate3dConsole;
class FScene3dConsole;

//============================================================
// <T>实体3D控制台。</T>
//============================================================
class MO_E3_DECLARE FInstance3dConsole : public FConsole{
protected:
   FTexture3dConsole* _pTextureConsole;
   FMaterial3dConsole* _pMaterialConsole;
   FModel3dConsole* _pModelConsole;
   FTemplate3dConsole* _pTemplateConsole;
   FScene3dConsole* _pSceneConsole;
public:
   FInstance3dConsole();
   MO_ABSTRACT ~FInstance3dConsole();
public:
   //------------------------------------------------------------
   // <T>获得纹理管理器。</T>
   MO_INLINE FTexture3dConsole* TextureConsole(){
      return _pTextureConsole;
   }
   //------------------------------------------------------------
   // <T>获得材质管理器。</T>
   MO_INLINE FMaterial3dConsole* MaterialConsole(){
      return _pMaterialConsole;
   }
   //------------------------------------------------------------
   // <T>获得模型管理器。</T>
   MO_INLINE FModel3dConsole* ModelConsole(){
      return _pModelConsole;
   }
   //------------------------------------------------------------
   // <T>获得模板管理器。</T>
   MO_INLINE FTemplate3dConsole* TemplateConsole(){
      return _pTemplateConsole;
   }
   //------------------------------------------------------------
   // <T>获得场景管理器。</T>
   MO_INLINE FScene3dConsole* SceneConsole(){
      return _pSceneConsole;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};
//------------------------------------------------------------
typedef MO_E3_DECLARE FObjects<FRs3dModel*> FRs3dModelCollection;

//============================================================
// <T>实体3D管理器。</T>
//============================================================
class MO_E3_DECLARE RInstance3dManager : public RSingleton<FInstance3dConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_E3_INSTANCE_H__
