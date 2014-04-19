#ifndef __MO_EG_FONT_H__
#define __MO_EG_FONT_H__
//************************************************************

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
class FBitmapData;
class FFontConsole;

//============================================================
// <T>字体信息。</T>
//============================================================
struct SFontInfo{
public:
   TCharC* fontNamePtr;
   TInt color;
   TInt size;
   TBool fix;
   TBool bold;
   TBool italic;
   TBool strikeout;
   TBool underline;
public:
   TInt width;
   TInt height;
public:
   //------------------------------------------------------------
   // <T>字体信息。</T>
   SFontInfo(){
      MO_CLEAR(fontNamePtr);
      color = 0;
      size = 0;
      fix = EFalse;
      bold = EFalse;
      italic = EFalse;
      strikeout = EFalse;
      underline = EFalse;
      width = 0;
      height = 0;
   }
};

//============================================================
// <T>字体。</T>
//============================================================
class MO_EG_DECLARE FFont : public FInstance
{
   MO_CLASS_ABSTRACT_DECLARE_INHERITS(FFont, FInstance);
protected:
   TInt _code;
   TString _name;
   TString _fontName;
   TString _assetName;
   SFontInfo _fontInfo;
   SIntSize2 _charSize;
   TBool _statusOpen;
   FFontConsole* _pConsole;
public:
   FFont();
   MO_ABSTRACT ~FFont();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TInt Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TInt code){
      _code = code;
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得字体名称。</T>
   MO_INLINE TCharC* FontName(){
      return _fontName;
   }
   //------------------------------------------------------------
   // <T>设置字体名称。</T>
   MO_INLINE void SetFontName(TCharC* pFontName){
      _fontName = pFontName;
   }
   //------------------------------------------------------------
   // <T>获得存储名称。</T>
   MO_INLINE TCharC* AssetName(){
      return _assetName;
   }
   //------------------------------------------------------------
   // <T>设置存储名称。</T>
   MO_INLINE void SetAssetName(TCharC* pAssetName){
      _assetName = pAssetName;
   }
   //------------------------------------------------------------
   // <T>获得字符大小。</T>
   MO_INLINE SIntSize2& CharSize(){
      return _charSize;
   }
   //------------------------------------------------------------
   // <T>获得状态打开。</T>
   MO_INLINE TBool IsStatusOpen(){
      return _statusOpen;
   }
   //------------------------------------------------------------
   // <T>获得控制台。</T>
   MO_INLINE FFontConsole* Console(){
      return _pConsole;
   }
   //------------------------------------------------------------
   // <T>设置控制台。</T>
   MO_INLINE void SetConsole(FFontConsole* pConsole){
      _pConsole = pConsole;
   }
public:
   MO_ABSTRACT TResult Open();
   MO_ABSTRACT TResult Close();
public:
   MO_VIRTUAL TResult CalculateAnsiSize(SFontInfo* pFontInfo, TAnsiCharC* pText) = 0;
   MO_VIRTUAL TResult CalculateWideSize(SFontInfo* pFontInfo, TWideCharC* pText) = 0;
   MO_VIRTUAL TResult DrawAnsiText(FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TAnsiCharC* pText) = 0;
   MO_VIRTUAL TResult DrawWideText(FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TWideCharC* pText) = 0;
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FObjects<FFont*> FFontCollection;
typedef MO_EG_DECLARE FList<FFont*> FFontList;

//============================================================
// <T>字体控制台。</T>
//============================================================
class MO_EG_DECLARE FFontConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FFontConsole, FConsole);
public:
   TBool _statusOpen;
   FFont* _pDefaultFont;
   FFontCollection* _pFonts;
public:
   FFontConsole();
   MO_ABSTRACT ~FFontConsole();
public:
   //------------------------------------------------------------
   // <T>获得状态打开。</T>
   MO_INLINE TBool IsStatusOpen(){
      return _statusOpen;
   }
   //------------------------------------------------------------
   // <T>获得默认字体。</T>
   MO_INLINE FFont* DefaultFont(){
      return _pDefaultFont;
   }
   //------------------------------------------------------------
   // <T>设置默认字体。</T>
   MO_INLINE void SetDefaultFont(FFont* pDefaultFont){
      _pDefaultFont = pDefaultFont;
   }
public:
   FFont* FindFontByCode(TInt code);
   FFont* FindFontByName(TCharC* pName);
public:
   void RegisterFont(FFont* pFont);
   void UnregisterFont(FFont* pFont);
public:
   MO_ABSTRACT void Open();
   MO_ABSTRACT void Close();
};

//============================================================
// <T>字体管理器。</T>
//============================================================
class MO_EG_DECLARE RFontManager : public RSingleton<FFontConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_FONT_H__
