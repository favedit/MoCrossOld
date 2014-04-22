#include "MoFrContent.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentConsole, FLoader);

//============================================================
// <T>构造内容控制台。</T>
//============================================================
FContentConsole::FContentConsole(){
}

//============================================================
// <T>析构内容控制台。</T>
//============================================================
FContentConsole::~FContentConsole(){
}

//============================================================
// <T>加载内容。</T>
//============================================================
TResult FContentConsole::Load(FContent* pContent){
   return ESuccess;
}

MO_NAMESPACE_END
