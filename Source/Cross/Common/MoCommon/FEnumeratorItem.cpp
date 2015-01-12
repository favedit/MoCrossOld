#include "MoCmEnumerator.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEnumeratorItem, FInstance);

//============================================================
// <T>构造枚举项目。</T>
//============================================================
FEnumeratorItem::FEnumeratorItem(){
   _code = -1;
}

//============================================================
// <T>析构枚举项目。</T>
//============================================================
FEnumeratorItem::~FEnumeratorItem(){
}

//============================================================
// <T>获的运行信息。</T>
//
// @param pDump 信息
// @return 处理结果
//============================================================
TResult FEnumeratorItem::Dump(MString* pDump){
   pDump->AppendFormat(TC("%4d=%s"), _code, (TCharC*)_name);
   if(!_description.IsEmpty()){
      pDump->AppendFormat(TC(" [%s]"), (TCharC*)_description);
   }
   return ESuccess;
}

MO_NAMESPACE_END
