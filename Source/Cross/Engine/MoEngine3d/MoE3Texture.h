#ifndef __MO_E3_TEXTURE_H__
#define __MO_E3_TEXTURE_H__
//************************************************************

#ifndef __MO_E3_COMMON_H__
#include "MoE3Common.h"
#endif // __MO_E3_COMMON_H__

#ifndef __MO_E3_DISPLAY_H__
#include "MoE3Display.h"
#endif // __MO_E3_DISPLAY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>纹理3D位图。</T>
//============================================================
class MO_E3_DECLARE FTexture3dBitmap : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FTexture3dBitmap, FInstance);
protected:
   FRs3dTextureBitmap* _pResource;
   GPtr<FRenderTexture> _renderTexture;
public:
   FTexture3dBitmap();
   MO_ABSTRACT ~FTexture3dBitmap();
public:
   //------------------------------------------------------------
   // <T>获得位图资源。</T>
   MO_INLINE FRs3dTextureBitmap* Resource(){
      return _pResource;
   }
   //------------------------------------------------------------
   // <T>获得渲染纹理。</T>
   MO_INLINE FRenderTexture* RenderTexture(){
      return _renderTexture;
   }
public:
   MO_ABSTRACT TResult LoadResource(FRs3dTextureBitmap* pResource);
public:
   TResult Open();
   TResult Close();
};
//------------------------------------------------------------
typedef MO_E3_DECLARE FObjects<FTexture3dBitmap*> FTexture3dBitmapCollection;

//============================================================
// <T>资源3D纹理控制台。</T>
//============================================================
class MO_E3_DECLARE FTexture3dTexture : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FTexture3dTexture, FInstance);
protected:
   FRs3dTexture* _pResource;
   FTexture3dBitmapCollection* _pBitmaps;
public:
   FTexture3dTexture();
   MO_ABSTRACT ~FTexture3dTexture();
public:
   //------------------------------------------------------------
   // <T>获得位图集合。</T>
   MO_INLINE FRs3dTexture* Resource(){
      return _pResource;
   }
   //------------------------------------------------------------
   // <T>获得位图集合。</T>
   MO_INLINE FTexture3dBitmapCollection* Bitmaps(){
      return _pBitmaps;
   }
public:
   FTexture3dBitmap* FindByCode(TCharC* pCode);
public:
   MO_ABSTRACT TResult LoadResource(FRs3dTexture* pResource);
   MO_ABSTRACT TResult Open();
   MO_ABSTRACT TResult Close();
};
//------------------------------------------------------------
typedef MO_E3_DECLARE FDictionary<FTexture3dTexture*> FTexture3dTextureDictionary;

//============================================================
// <T>实体3D纹理管理器。</T>
//============================================================
class MO_E3_DECLARE FTexture3dConsole : public FConsole{
protected:
   FTexture3dTextureDictionary* _pTextures;
public:
   FTexture3dConsole();
   MO_ABSTRACT ~FTexture3dConsole();
public:
   //------------------------------------------------------------
   // <T>获得纹理集合。</T>
   MO_INLINE FTexture3dTextureDictionary* Textures(){
      return _pTextures;
   }
public:
   FTexture3dTexture* Find(TCharC* pName);
   FTexture3dTexture* Load(TCharC* pName);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_E3_TEXTURE_H__
