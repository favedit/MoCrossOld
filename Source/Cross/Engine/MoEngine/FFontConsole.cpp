#include "MoEgFont.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FFontConsole, FConsole);

//============================================================
// <T>构造字体。</T>
//============================================================
FFontConsole::FFontConsole(){
   _statusOpen = EFalse;
   MO_CLEAR(_pDefaultFont);
   _pFonts = MO_CREATE(FFontCollection);
}

//============================================================
// <T>析构字体。</T>
//============================================================
FFontConsole::~FFontConsole(){
   MO_DELETE(_pFonts);
}

//============================================================
// <T>根据代码获得字体。</T>
//
// @param code 代码
// @return 字体
//============================================================
FFont* FFontConsole::FindFontByCode(TInt code){
   TInt count = _pFonts->Count();
   for(TInt n = 0; n < count; n++){
      FFont* pFont = _pFonts->Get(n);
      if(pFont->Code() == code){
         return pFont;
      }
   }
   return NULL;
}

//============================================================
// <T>根据名称获得字体。</T>
//
// @param pName 名称
// @return 字体
//============================================================
FFont* FFontConsole::FindFontByName(TCharC* pName){
   TInt count = _pFonts->Count();
   for(TInt n = 0; n < count; n++){
      FFont* pFont = _pFonts->Get(n);
      if(RString::Equals(pFont->Name(), pName)){
         return pFont;
      }
   }
   return NULL;
}

//============================================================
// <T>注册一个字体。</T>
//
// @param pFont 字体
//============================================================
void FFontConsole::RegisterFont(FFont* pFont){
   MO_ASSERT(pFont);
   // 打开字体
   pFont->SetConsole(this);
   if(!pFont->IsStatusOpen()){
      pFont->Open();
   }
   // 放入缓冲
   _pFonts->Push(pFont);
   // 注册的第一个字体为默认字体
   if(_pDefaultFont == NULL){
      _pDefaultFont = pFont;
   }
}

//============================================================
// <T>注销一个字体。</T>
//
// @param pFont 字体
//============================================================
void FFontConsole::UnregisterFont(FFont* pFont){
   MO_ASSERT(pFont);
   _pFonts->Remove(pFont);
}

//============================================================
// <T>打开处理。</T>
//============================================================
void FFontConsole::Open(){
   _statusOpen = ETrue;
}

//============================================================
// <T>关闭处理。</T>
//============================================================
void FFontConsole::Close(){
   _statusOpen = EFalse;
}

MO_NAMESPACE_END
