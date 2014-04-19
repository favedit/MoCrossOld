#ifndef __MO_EO_FONT_H__
#define __MO_EO_FONT_H__
//************************************************************

#ifndef __FT2_BUILD_GENERIC_H__
#include <ft2build.h>
#endif // __FT2_BUILD_GENERIC_H__

#ifndef __FREETYPE_H__
#include FT_FREETYPE_H 
#endif // __FREETYPE_H__

#ifndef __MO_EO_COMMON_H__
#include "MoEoCommon.h"
#endif // __MO_EO_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
enum EFontDrawMode{
   EFontDrawMode_Test,
   EFontDrawMode_Draw,
};

//============================================================
struct SFontCharInfo{
   TInt left;
   TInt top;
   TInt width;
   TInt height;
};

//============================================================
// <T>字体。</T>
//============================================================
class MO_EO_DECLARE FEoFont : public FFont
{
   MO_CLASS_DECLARE_INHERITS(FEoFont, FFont);
public:
   FT_Face _face;
   FBytes* _pData;
public:
   FEoFont();
   MO_ABSTRACT ~FEoFont();
public:
   MO_OVERRIDE TResult Open();
   MO_OVERRIDE TResult Close();
protected:
   TResult InnerDrawChar(EFontDrawMode modeCd, FBitmapData* pBitmap, SIntRectangle* pRectangle, SFontInfo* pFontInfo, SFontCharInfo* pInfo, TInt positionX, TInt positionY, TInt value);
   TResult InnerDrawText(EFontDrawMode modeCd, FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TWideCharC* pText);
public:
   MO_OVERRIDE TResult CalculateAnsiSize(SFontInfo* pFontInfo, TAnsiCharC* pText);
   MO_OVERRIDE TResult CalculateWideSize(SFontInfo* pFontInfo, TWideCharC* pText);
   MO_OVERRIDE TResult DrawAnsiText(FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TAnsiCharC* pText);
   MO_OVERRIDE TResult DrawWideText(FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TWideCharC* pText);
};

//============================================================
// <T>字体控制台。</T>
//============================================================
class MO_EO_DECLARE FEoFontConsole : public FFontConsole
{
   MO_CLASS_DECLARE_INHERITS(FEoFontConsole, FFontConsole);
protected:
   FT_Library _library;
public:
   FEoFontConsole();
   MO_ABSTRACT ~FEoFontConsole();
public:
   //------------------------------------------------------------
   // <T>获得本地库。</T>
   MO_INLINE FT_Library& NativeLibrary(){
      return _library;
   }
public:
   MO_OVERRIDE void Open();
   MO_OVERRIDE void Close();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EO_FONT_H__
