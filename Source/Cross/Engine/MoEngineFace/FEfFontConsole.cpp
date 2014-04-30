#include "MoEfFont.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEoFontConsole, FFontConsole);

//============================================================
// <T>构造字体控制台。</T>
//============================================================
FEoFontConsole::FEoFontConsole(){
   RType<FT_Library>::Clear(&_library);
}

//============================================================
// <T>析构字体控制台。</T>
//============================================================
FEoFontConsole::~FEoFontConsole(){
}

//============================================================
// <T>打开处理。</T>
//============================================================
void FEoFontConsole::Open(){
   FFontConsole::Open();
   // 初始化库
   FT_Error result = FT_Init_FreeType(&_library);
   if(result){
      MO_FATAL("Initialize free type failure. (error=%d)", result);
   }
}

//============================================================
// <T>关闭处理。</T>
//============================================================
void FEoFontConsole::Close(){
   // 释放资源
   FT_Done_FreeType(_library);
   // 关闭处理
   FFontConsole::Close();
}

MO_NAMESPACE_END
