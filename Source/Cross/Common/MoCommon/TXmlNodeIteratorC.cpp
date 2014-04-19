#include "MoCmXml.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造只读迭代器。</T>
//============================================================
TXmlNodeIteratorC::TXmlNodeIteratorC(){
}

//============================================================
// <T>构造只读迭代器。</T>
//
// @param iterator 只读迭代器
//============================================================
TXmlNodeIteratorC::TXmlNodeIteratorC(const TXmlNodeIteratorC& iterator){
   _pStart = iterator._pStart;
   MO_CLEAR(_pEntry);
}

//============================================================
// <T>构造只读迭代器。</T>
//
// @param pEntry 入口对象
//============================================================
TXmlNodeIteratorC::TXmlNodeIteratorC(SEntry* pEntry){
   _pStart = pEntry;
   MO_CLEAR(_pEntry);
}

//============================================================
// <T>移动到下一个位置。</T>
//
// @return 移动结果
//============================================================
TBool TXmlNodeIteratorC::Next(){
   return TListIteratorC<FXmlNode*>::Next();
}

//============================================================
// <T>移动到下一个位置。</T>
//
// @param pName 名称
// @return 移动结果
//============================================================
TBool TXmlNodeIteratorC::Next(TCharC* pName){
   while(ETrue){
      // 获得下一个位置
      _pEntry = (NULL == _pEntry) ? _pStart : _pEntry->nextPtr;
      if(NULL == _pEntry){
         break;
      }
      // 判断是否满足条件
      if(_pEntry->value->IsName(pName)){
         break;
      }
   }
   return (NULL != _pEntry);
}

MO_NAMESPACE_END
