#include "MoCmXml.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得只读迭代器。</T>
//
// @return 只读迭代器
//============================================================
TXmlNodeIteratorC FXmlNodes::IteratorC(){
   return TXmlNodeIteratorC(_pFirst);
}

MO_NAMESPACE_END
