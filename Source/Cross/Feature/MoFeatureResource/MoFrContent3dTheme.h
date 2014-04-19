#ifndef __MO_FR_CONTENT3D_THEME_H__
#define __MO_FR_CONTENT3D_THEME_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT3D_BASE_H__
#include "MoFrContent3dBase.h"
#endif // __MO_FR_CONTENT3D_BASE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源3D主题。</T>
//============================================================
class MO_FR_DECLARE FRs3dTheme : public FObject{
protected:
public:
   FRs3dTheme();
   MO_ABSTRACT ~FRs3dTheme();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FSet<TResourceId, FRs3dTheme*> FRs3dThemeSet;
typedef MO_FR_DECLARE FDictionary<FRs3dTheme*> FRs3dThemeDictionary;

//============================================================
// <T>资源3D主题控制台。</T>
//============================================================
class MO_FR_DECLARE FRs3dThemeConsole : public FObject{
protected:
   FRs3dThemeSet* _pThemes;
public:
   FRs3dThemeConsole();
   MO_ABSTRACT ~FRs3dThemeConsole();
public:
   FRs3dTheme* DefaultTheme();
   FRs3dTheme* Find(TResourceId resourceId);
public:
   TResult Unserialize(IDataInput* pInput);
   TResult Open(TCharC* pCode);
   TResult Clear();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT3D_THEME_H__
