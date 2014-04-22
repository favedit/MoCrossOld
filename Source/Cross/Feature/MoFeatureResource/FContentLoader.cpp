#include "MoFrContent.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentLoader, FLoader);

//============================================================
// <T>构造内容加载器。</T>
//============================================================
FContentLoader::FContentLoader(){
}

//============================================================
// <T>析构内容加载器。</T>
//============================================================
FContentLoader::~FContentLoader(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FContentLoader::Process(){
   return ESuccess;
}

MO_NAMESPACE_END
