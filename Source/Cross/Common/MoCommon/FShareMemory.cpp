#ifdef _MO_LINUX
#include <sys/ipc.h>
#include <sys/shm.h>
#endif
#include "MoCmMemory.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造一块共享内存对象。</T>
//
// @return 共享内存对象
//============================================================
FShareMemory::FShareMemory(){
   _linked = EFalse;
   _key = 0;
   _pMemory = NULL;
   _created = EFalse;
   _capacity = 0;
   _position = 0;
}

//============================================================
// <T>释放一块共享内存对象。</T>
//============================================================
FShareMemory::~FShareMemory(){
}

//============================================================
// <T>创建一块指定大小的共享内存。</T>
//
// @param key 共享内存键值
// @param capacity 内存大小
// @return 是否成功
//============================================================
TBool FShareMemory::Create(){
#ifdef _MO_WINDOWS
   // 生成名称
   TFsPath code;
   code.AppendFormat(TC("%s:%d"), (TCharC*)_keyName, _key);
   // 创建文件
   _hMemory = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, _capacity, (TCharC*)code);
   if(NULL == _hMemory){
      MO_PFATAL(CreateFileMapping);
      return EFalse;
   }
   // 映射文件
   _pMemory = (TByte*)MapViewOfFile(_hMemory, FILE_MAP_ALL_ACCESS, 0, 0, _capacity);
   if(NULL == _pMemory){
      MO_PFATAL(MapViewOfFile);
      return EFalse;
   }
   MO_INFO(TC("Create share memory. (handle=%d, memory=0x%08X, capacity=%d, code=%s)"), _hMemory, _pMemory, _capacity, (TCharC*)code);
#endif // _MO_WINDOWS
   //............................................................
#ifdef _MO_LINUX
   _pMemory = (TByte*)RShareMemory::Create(_key, _capacity);
#endif // _MO_LINUX
   //............................................................
   // 设置属性
   _linked = ETrue;
   _created = ETrue;
   _position = 0;
   return ETrue;
}

//============================================================
// <T>只读方式链接一块共享内存。</T>
//
// @param key 共享内存键值
// @return 是否成功
//============================================================
TBool FShareMemory::Connect(){
   // 声明变量
   SShareMemoryInfo info;
   RType<SShareMemoryInfo>::Clear(&info);
   //............................................................
#ifdef _MO_WINDOWS
   // 生成名称
   TFsPath code;
   code.AppendFormat(TC("%s:%d"), (TCharC*)_keyName, _key);
   // 创建文件
   _hMemory = OpenFileMapping(FILE_MAP_ALL_ACCESS, ETrue, (TCharC*)code);
   if(NULL == _hMemory){
      MO_PFATAL(OpenFileMapping);
      return EFalse;
   }
   // 映射文件
   _pMemory = (TByte*)MapViewOfFile(_hMemory, FILE_MAP_ALL_ACCESS, 0, 0, 0);
   if(NULL == _pMemory){
      TUint32 errorCode = GetLastError();
      MO_ERROR(TC("Connect share memory failure. (handle=%d, memory=0x%08X, error=%d)"), _hMemory, _pMemory, errorCode);
      MO_PFATAL(MapViewOfFile);
      return EFalse;
   }
   MO_INFO(TC("Connect share memory. (handle=%d, memory=0x%08X)"), _hMemory, _pMemory);
#endif // _MO_WINDOWS
   //............................................................
#ifdef _MO_LINUX
   _pMemory = (TByte*)RShareMemory::Connect(_key, ETrue);
#endif // _MO_LINUX
   //............................................................
   // 关联共享段信息
   _linked = ETrue;
   _created = EFalse;
   _position = 0;
   _capacity = info.size;
   return ETrue;
}

////============================================================
//// <T>尝试创建一块共享内存。</T>
////
//// @param key 共享内存键值
//// @param capacity 内存大小
//// @return 是否成功
////============================================================
//TBool FShareMemory::TryCreate(TShareKey key, TSize capacity){
//   _linked = ETrue;
//   _key = key;
//   _pMemory = (TByte*)RShareMemory::TryCreate(key, capacity, _created);
//   _capacity = capacity;
//   _position = 0;
//   return ETrue;
//}
//
////============================================================
//// <T>读写方式链接一块共享内存。</T>
////
//// @param key 共享内存键值
//// @return 是否成功
////============================================================
//TBool FShareMemory::Link(TShareKey key){
//   // 声明变量
//   SShareMemoryInfo info;
//   RType<SShareMemoryInfo>::Clear(&info);
//   // 关联共享段信息
//   _linked = ETrue;
//   _key = key;
//   _pMemory = (TByte*)RShareMemory::Connect(key, EFalse);
//   _created = EFalse;
//   _capacity = info.size;
//   _position = 0;
//   return ETrue;
//}

//============================================================
// <T>从共享内存中收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 共享内存
//============================================================
TAny* FShareMemory::Alloc(TSize size){
   TByte* pMemory = _pMemory + _position;
   _position += size;
   return pMemory;
}

//============================================================
// <T>从共享内存中跳过一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 是否成功
//============================================================
TBool FShareMemory::Skip(TSize size){
   _position += size;
   return ETrue;
}

//============================================================
// <T>在当前共享内存段内，分配新的共享内存段。</T>
//
// @param size 大小
// @return 共享内存段
//============================================================
TShareSegment FShareMemory::CreateSegment(TSize size){
   TShareSegment segment;
   segment.Initialize(_pMemory + _position, size, _created);
   _position += size;
   return segment;
}

//============================================================
// <T>释放一块共享内存对象。</T>
//
// @return 是否成功
//============================================================
TBool FShareMemory::Free(){
   // 初始化数据
   _key = 0;
   _created = EFalse;
   _capacity = 0;
   _position = 0;
   // 释放共享内存
   if(NULL != _pMemory){
      RShareMemory::Free(_key);
      _pMemory = NULL;
      return ETrue;
   }
   return EFalse;
}

MO_NAMESPACE_END
