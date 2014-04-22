#ifndef __MO_EG_BITMAP_H__
#define __MO_EG_BITMAP_H__
//************************************************************

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

#ifndef __MO_EG_CORE_H__
#include "MoEgCore.h"
#endif // __MO_EG_CORE_H__

#ifndef __MO_EG_DISPLAY_H__
#include "MoEgDisplay.h"
#endif // __MO_EG_DISPLAY_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>位图纹理。</T>
//============================================================
class MO_EG_DECLARE FBitmap : public FDrawable{
protected:
   GPtr<FBitmapData> _data;
   GPtr<FRenderTexture> _texture;
   SIntSize2 _size;
public:
   FBitmap();
   MO_ABSTRACT ~FBitmap();
public:
   //------------------------------------------------------------
   // <T>获得数据。</T>
   FBitmapData* Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>获得纹理。</T>
   FRenderTexture* Texture(){
      return _texture;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_OVERRIDE TResult FilterRegion(FRenderRegion* pRegion);
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_ABSTRACT TResult Clear(TColor color);
   MO_ABSTRACT TResult Clear(TColor color, SIntRectangle* pRectangle);
   MO_ABSTRACT TResult Update();
public:
   MO_ABSTRACT void FreeBitmap();
   MO_ABSTRACT void FreeTexture();
};

//============================================================
// <T>位图画板。</T>
//============================================================
class MO_EG_DECLARE FBitmapCanvas :
      public FBitmap,
      public ICanvas{
protected:
   SIntRectangle _clientRectangle;
   SIntRectangle _clipRectangle;
public:
   FBitmapCanvas();
   MO_ABSTRACT ~FBitmapCanvas();
public:
   //------------------------------------------------------------
   // <T>获得客户范围。</T>
   MO_INLINE SIntRectangle& ClientRectangle(){
      return _clientRectangle;
   }
   //------------------------------------------------------------
   // <T>获得剪裁区域。</T>
   MO_INLINE SIntRectangle& ClipRectangle(){
      return _clipRectangle;
   }
public:
   MO_OVERRIDE TResult Setup();
   MO_OVERRIDE TResult Update();
public:
   MO_OVERRIDE TResult DrawLine(TColor color, TInt x1, TInt y1, TInt x2, TInt y2);
   MO_OVERRIDE TResult DrawLineHorizontal(TColor color, TInt x1, TInt x2, TInt y);
   MO_OVERRIDE TResult DrawLineVertical(TColor color, TInt y1, TInt y2, TInt x);
   MO_OVERRIDE TResult DrawTriangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2, TInt x3, TInt y3);
   MO_OVERRIDE TResult DrawRectangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2);
   MO_OVERRIDE TResult DrawRectangle(TColor color, SIntRectangle* pRectangle);
public:
   MO_OVERRIDE TResult FillTriangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2, TInt x3, TInt y3);
   MO_OVERRIDE TResult FillRectangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2);
   MO_OVERRIDE TResult FillRectangle(TColor color, SIntRectangle* pRectangle);
public:
   MO_OVERRIDE TResult DrawBitmap(FBitmapData* pBitmapData, TBool alpha, TInt x, TInt y);
   MO_OVERRIDE TResult DrawBitmap(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, TInt x, TInt y);
   MO_OVERRIDE TResult DrawBitmap(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, SIntRectangle* pTargetRectangle);
   MO_OVERRIDE TResult DrawBitmapGrid9(FBitmapData* pBitmapData, TBool alpha, SIntPadding* pSourcePadding, SIntRectangle* pTargetRectangle);
   MO_OVERRIDE TResult DrawBitmapGrid9(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, SIntPadding* pSourcePadding, SIntRectangle* pTargetRectangle);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_BITMAP_H__
