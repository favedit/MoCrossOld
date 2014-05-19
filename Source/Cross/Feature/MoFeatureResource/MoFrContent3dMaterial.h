#ifndef __MO_FR_CONTENT3D_MATERIAL_H__
#define __MO_FR_CONTENT3D_MATERIAL_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT3D_BASE_H__
#include "MoFrContent3dBase.h"
#endif // __MO_FR_CONTENT3D_BASE_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源3D材质纹理。</T>
//============================================================
class MO_FR_DECLARE FRs3dMaterialTexture : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dMaterialTexture, FInstance);
protected:
   TString _code;
   TString _textureName;
public:
   FRs3dMaterialTexture();
   MO_ABSTRACT ~FRs3dMaterialTexture();
public:
   //------------------------------------------------------------
   // <T>判断是否指定代码。</T>
   MO_INLINE TBool IsCode(TCharC* pCode){
      return _code.Equals(pCode);
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TCharC* Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TCharC* pCode){
      _code = pCode;
   }
   //------------------------------------------------------------
   // <T>获得纹理编号。</T>
   MO_INLINE TCharC* TextureName(){
      return _textureName;
   }
public:
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dMaterialTexture> GRs3dMaterialTexturePtrs;

//============================================================
// <T>资源3D材质。</T>
//============================================================
class MO_FR_DECLARE FRs3dMaterial : public FResource3d
{
   MO_CLASS_DECLARE_INHERITS(FRs3dMaterial, FResource3d);
protected:
   // 材质信息
   SMaterialInfo _info;
   // 纹理集合
   GRs3dMaterialTexturePtrs _textures;
public:
   FRs3dMaterial();
   MO_ABSTRACT ~FRs3dMaterial();
public:
   //------------------------------------------------------------
   // <T>获得材质信息。</T>
   MO_INLINE SMaterialInfo& Info(){
      return _info;
   }
   //------------------------------------------------------------
   // <T>获得纹理集合。</T>
   MO_INLINE GRs3dMaterialTexturePtrs& Textures(){
      return _textures;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
public:
   void Reset();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrDictionary<FRs3dMaterial> GRs3dMaterialPtrDictionary;

//============================================================
// <T>资源3D材质控制台。</T>
//============================================================
class MO_FR_DECLARE FRs3dMaterialConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FRs3dMaterialConsole, FConsole);
protected:
   GRs3dMaterialPtrDictionary _materials;
public:
   FRs3dMaterialConsole();
   MO_ABSTRACT ~FRs3dMaterialConsole();
public:
   //------------------------------------------------------------
   // <T>获得材质字典。</T>
   MO_INLINE GRs3dMaterialPtrDictionary& Materials(){
      return _materials;
   }
public:
   FRs3dMaterial* Find(TCharC* pName);
   FRs3dMaterial* Get(TCharC* pName);
public:
   void Clear();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT3D_MATERIAL_H__
