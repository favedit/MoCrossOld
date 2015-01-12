#include "MoCmEnumerator.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEnumerator, FInstance);

//============================================================
// <T>构造枚举器。</T>
//============================================================
FEnumerator::FEnumerator(){
}

//============================================================
// <T>析构枚举器。</T>
//============================================================
FEnumerator::~FEnumerator(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEnumerator::Setup(){
   return ESuccess;
}

//============================================================
// <T>根据代码号查找枚举项目。</T>
//
// @param code 代码
// @return 处理结果
//============================================================
FEnumeratorItem* FEnumerator::FindByCode(TInt code){
   GEnumeratorItemPtrs::TIterator iterator = _items.Iterator();
   while(iterator.Next()){
      FEnumeratorItem* pItem = *iterator;
      if(pItem->Code() == code){
         return pItem;
      }
   }
   return NULL;
}

//============================================================
// <T>根据名称查找枚举项目。</T>
//
// @param pName 名称
// @return 处理结果
//============================================================
FEnumeratorItem* FEnumerator::FindByName(TCharC* pName){
   GEnumeratorItemPtrs::TIterator iterator = _items.Iterator();
   while(iterator.Next()){
      FEnumeratorItem* pItem = *iterator;
      if(pItem->IsName(pName)){
         return pItem;
      }
   }
   return NULL;
}

//============================================================
// <T>解析名称为代码。</T>
//
// @param pName 名称
// @return 代码
//============================================================
TInt FEnumerator::Parse(TCharC* pName){
   MO_CHECK(pName, return -1);
   FEnumeratorItem* pItem = FindByName(pName);
   MO_FATAL_CHECK(pItem, return -1, TC("Parse enum code failure. (enum=%s, name=%s)"), (TCharC*)_name, pName);
   TInt code = pItem->Code();
   return code;
}

//============================================================
// <T>解析名称为代码。</T>
//
// @param pName 名称
// @param defaultValue 默认内容
// @return 代码
//============================================================
TInt FEnumerator::Parse(TCharC* pName, TInt defaultValue){
   MO_CHECK(pName, return defaultValue);
   FEnumeratorItem* pItem = FindByName(pName);
   TInt code = defaultValue;
   if(pItem != NULL){
      code = pItem->Code();
   }
   return code;
}

//============================================================
// <T>格式代码为名称。</T>
//
// @param code 代码
// @return 名称
//============================================================
TCharC* FEnumerator::Format(TInt code){
   MO_CHECK(code >= 0, return NULL);
   FEnumeratorItem* pItem = FindByCode(code);
   MO_FATAL_CHECK(pItem, return NULL, TC("Parse enum name failure. (enum=%s, code=%d)"), (TCharC*)_name, code);
   TCharC* pName = pItem->Name();
   return pName;
}

//============================================================
// <T>格式代码为名称。</T>
//
// @param code 代码
// @param pDefaultValue 默认内容
// @return 名称
//============================================================
TCharC* FEnumerator::Format(TInt code, TCharC* pDefaultValue){
   MO_CHECK(code >= 0, return pDefaultValue);
   FEnumeratorItem* pItem = FindByCode(code);
   TCharC* pName = pDefaultValue;
   if(pItem != NULL){
      pName = pItem->Name();
   }
   return pName;
}

//============================================================
// <T>注册一个枚举项目。</T>
//
// @param pItem 枚举项目
// @return 处理结果
//============================================================
TResult FEnumerator::Register(FEnumeratorItem* pItem){
   MO_CHECK(pItem, return ENull);
   // 检查重复
   if(_items.Contains(pItem)){
      MO_FATAL(TC("Duplicate item in enumerator. (enum=%s, name=%s)"), (TCharC*)_name, pItem->Name());
      return EDuplicate;
   }
   // 设置内容
   _items.Push(pItem);
   return ESuccess;
}

//============================================================
// <T>注册一个枚举项目。</T>
//
// @param code 代码
// @param pName 名称
// @param pDescription 注解
// @return 处理结果
//============================================================
TResult FEnumerator::Register(TInt code, TCharC* pName, TCharC* pDescription){
   MO_CHECK(code >= 0, return EOutRange);
   MO_CHECK(pName, return ENull);
   // 创建枚举项目
   FEnumeratorItem* pItem = FEnumeratorItem::InstanceCreate();
   pItem->SetCode(code);
   pItem->SetName(pName);
   pItem->SetDescription(pDescription);
   // 注册枚举项目
   TResult resultCd = Register(pItem);
   return resultCd;
}

//============================================================
// <T>注销一个枚举项目。</T>
//
// @param pItem 枚举项目
// @return 处理结果
//============================================================
TResult FEnumerator::Unrgister(FEnumeratorItem* pItem){
   MO_CHECK(pItem, return ENull);
   // 检查存在性
   if(!_items.Contains(pItem)){
      MO_FATAL(TC("Not exists item in enumerator. (enum=%s, name=%s)"), (TCharC*)_name, pItem->Name());
      return ENotExists;
   }
   // 设置内容
   _items.Remove(pItem);
   return ESuccess;
}

//============================================================
// <T>获的运行信息。</T>
//
// @param pDump 信息
// @return 处理结果
//============================================================
TResult FEnumerator::Dump(MString* pDump){
   pDump->AppendLine(_name);
   GEnumeratorItemPtrs::TIterator iterator = _items.Iterator();
   while(iterator.Next()){
      FEnumeratorItem* pItem = *iterator;
      pDump->Append(MO_DUMP_INDENT);
      pItem->Dump(pDump);
      pDump->AppendLine();
   }
   return ESuccess;
}

MO_NAMESPACE_END
