#include "MoCmMemory.h"
#include "MoCmString8.h"
#include "MoCmClass.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造收集信息存储器。</T>
//============================================================
FAllocatorStorage::FAllocatorStorage(){
   _able = EFalse;
   _lengthAlloc = 0;
   _lengthFree = 0;
   _pInfos = MO_MEM_CREATE(FAllocatorInfoVector);
}

//============================================================
// <T>析构收集信息存储器。</T>
//============================================================
FAllocatorStorage::~FAllocatorStorage(){
   _able = EFalse;
   MO_MEM_DELETE(_pInfos);
}

//============================================================
// <T>获得是否能用。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
TBool FAllocatorStorage::IsAble(){
   return _able;
}

//============================================================
// <T>开始统计内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
void FAllocatorStorage::Enable(){
   _able = ETrue;
   Reset();
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 内存指针
//============================================================
void FAllocatorStorage::Disable(TBool detail){
   Dump(detail);
   _able = EFalse;
}

//============================================================
// <T>获得内存信息。</T>
//
// @param pInfo 内存信息
// @return 处理结果
//============================================================
TBool FAllocatorStorage::FetchInfo(SMemoryInfo* pInfo){
   MO_ASSERT(pInfo);
   pInfo->lengthAlloc = _lengthAlloc;
   pInfo->lengthFree = _lengthFree;
   return ETrue;
}

//============================================================
// <T>收集一块指定大小的内存。</T>
//
// @param pTypeName 类型名称
// @param size 内存大小
// @return 内存指针
//============================================================
TAny* FAllocatorStorage::Alloc(TChar8C* pOwnerName, TChar8C* pTypeName, TInt size, TChar8C* pFileName, TInt lineNumber){
#ifdef _MO_DEBUG
   TAny* pMemory = malloc(sizeof(SAllocatorHeader) + size);
   if(_able){
      SAllocatorInfo* pInfo = NULL;
      //............................................................
      // 查找内存信息
      _locker.Enter();
      TInt count = _pInfos->Count();
      for(TInt n = 0; n < count; n++){
         SAllocatorInfo* pFind = _pInfos->Get(n);
         if(NULL != pFind){
            // 检查尺寸
            if(pFind->size != size){
               continue;
            }
            // 检查拥有者名称
            if(NULL != pOwnerName){
               if(!RString8::Equals(pFind->ownerNamePtr, pOwnerName)){
                  continue;
               }
            }
            // 检查类名称
            if(NULL != pTypeName){
               if(!RString8::Equals(pFind->typeNamePtr, pTypeName)){
                  continue;
               }
            }
            // 设置信息
            pInfo = pFind;
            break;
         }
      }
      //............................................................
      // 创建内存信息
      if(NULL == pInfo){
         pInfo = MO_MEM_CREATE(SAllocatorInfo);
         pInfo->storagePtr = this;
         pInfo->index = count;
         pInfo->ownerNamePtr = pOwnerName;
         pInfo->typeNamePtr = pTypeName;
         pInfo->fileNamePtr = pFileName;
         pInfo->lineNumber = lineNumber;
         pInfo->size = size;
         pInfo->count = 0;
         pInfo->total = 0;
         _pInfos->Push(pInfo);
      }
      //............................................................
      // 更新计数器
      pInfo->count++;
      pInfo->total++;
      _lengthAlloc += size;
      _locker.Leave();
      //............................................................
      // 设置头信息
      SAllocatorHeader* pHeader = (SAllocatorHeader*)pMemory;
      pHeader->infoPtr = pInfo;
   }else{
      *(TInt*)pMemory = 0;
   }
   return (TByte*)pMemory + sizeof(SAllocatorHeader);
#else
   return malloc(size);
#endif // _MO_DEBUG
};

//============================================================
// <T>释放内存。</T>
//
// @param pMemory 内存指针
//============================================================
void FAllocatorStorage::Free(TAny* pMemory){
   MO_ASSERT(pMemory);
#ifdef _MO_DEBUG
   // 获得头信息
   TAny* pData = (TByte*)pMemory - sizeof(SAllocatorHeader);
   if(_able){
      SAllocatorInfo* pInfo = ((SAllocatorHeader*)pData)->infoPtr;
      if(NULL != pInfo){
         if(pInfo->storagePtr == this){
            // 计算统计信息
            _locker.Enter();
            pInfo->count--;
            _lengthFree += pInfo->size;
            _locker.Leave();
         }
      }
   }
   free(pData);
#else
   free(pMemory);
#endif // _MO_DEBUG
}

//============================================================
// <T>重置统计信息。</T>
//============================================================
void FAllocatorStorage::Reset(){
   _locker.Enter();
   TInt count = _pInfos->Count();
   for(TInt n = 0; n < count; n++){
      SAllocatorInfo* pInfo = _pInfos->Get(n);
      pInfo->count = 0;
      pInfo->total = 0;
   }
   _locker.Leave();
}

//============================================================
// <T>输出调试信息。</T>
//============================================================
void FAllocatorStorage::Dump(TBool detail){
   // 检查是否可以跟踪
   if(!_able){
      return;
   }
   // 显示跟踪信息
   TFsDump dump;
   dump.Append(TC("-- Dump infos begin ------------------------------------------------------------\n"));
   TInt64 totalCapacity = 0;
   TInt validCount = 0;
   TInt count = _pInfos->Count();
   for(TInt n = 0; n < count; n++){
      SAllocatorInfo* pInfo = _pInfos->Get(n);
      // 检测方式
      if(detail){
         if(0 == pInfo->total){
            continue;
         }
      }else{
         if(0 == pInfo->count){
            continue;
         }
      }
      // 显示信息
      TInt64 capacity = pInfo->size * pInfo->count;
      totalCapacity += capacity;
      // 生成类名称
      TFsName ownerName;
      if(NULL != pInfo->ownerNamePtr){
         TClassInfo type = pInfo->ownerNamePtr;
         ownerName = type.FullName();
      }
      if(ownerName.IsEmpty()){
         // ownerName = pInfo->ownerNamePtr;
      }
      // 生成默认名称
      TFsName className;
      if(NULL != pInfo->typeNamePtr){
         TClassInfo type = pInfo->typeNamePtr;
         className = type.FullName();
      }
      if(className.IsEmpty()){
         // className = pInfo->typeNamePtr;
      }
      // 输出日志
      dump.AppendFormat(TC("-- Class [%-48s][%-64s] - [%8d] -> count=%8d, total=%8d, memory=%22s, file=%s(%d)\n"),
            (TCharC*)ownerName, (TCharC*)className, pInfo->size, pInfo->count, pInfo->total,
            RInt::FormatCapacity(capacity, TFsCode(), TFsCode::Size()), pInfo->fileNamePtr, pInfo->lineNumber);
      validCount++;
   }
   dump.Append(TC("-- Dump infos end --------------------------------------------------------------\n"));
   dump.AppendFormat(TC("   Count: %4d, Memory: %s, (alloc=%s, free=%s)"), validCount,
         RInt::FormatCapacity(totalCapacity, TFsCode(), TFsCode::Size()),
         RInt::FormatCapacity(_lengthAlloc, TFsCode(), TFsCode::Size()),
         RInt::FormatCapacity(_lengthFree, TFsCode(), TFsCode::Size()));
   MO_INFO(TC("Allocator storage info.\n%s"), (TCharC*)dump);
}

MO_NAMESPACE_END
