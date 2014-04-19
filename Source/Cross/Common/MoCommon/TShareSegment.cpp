#ifdef _MO_LINUX
#include <sys/ipc.h>
#include <sys/shm.h>
#endif
#include "MoCmMemory.h"
#include "MoCmLanguage.h"
#include "MoCmShared.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>初始化共享内存段。</T>
//
// @param pMemory 内存指针
// @param length 数据长度
//============================================================
void TShareSegment::Initialize(TAny* pMemory, TInt length, TBool created){
   _pMemory = (TByte*)pMemory;
   _position = 0;
   _length = length;
   _created = created;
}

//============================================================
// <T>是否被创建。</T>
// <T>如果内存是被关联后直接使用的，返回非。</T>
//
// @return
//    <L value="ETrue">共享内存是新建的</L>
//    <L value="EFalse">共享内存是关联后使用的</L>
//============================================================
TBool TShareSegment::IsCreated(){
   return _created;
}

//============================================================
// <T>获得关联的内存指针。</T>
//
// @return 内存指针
//============================================================
TAny* TShareSegment::Memory(){
   return _pMemory;
}

//============================================================
// <T>获得当前内存位置。</T>
//
// @return 内存位置
//============================================================
TInt TShareSegment::Position(){
   return _position;
}

//============================================================
// <T>获得内存长度。</T>
//
// @return 内存长度
//============================================================
TInt TShareSegment::Length(){
   return _length;
}

//============================================================
// <T>获得剩余的内存指针。</T>
//
// @return 剩余的内存指针
//============================================================
TInt TShareSegment::Remain(){
   return _length - _position;
}

//============================================================
// <T>获得当前位置的内存指针。</T>
//
// @return 内存指针
//============================================================
TAny* TShareSegment::RemainMemory(){
   return _pMemory + _position;
}

//============================================================
// <T>从共享内存中收集一块指定大小的内存。</T>
//
// @param size 内存大小
// @return 共享内存
//============================================================
TAny* TShareSegment::Alloc(TSize size){
   TByte* pMemory = NULL;
   if(size > 0){
      pMemory = _pMemory + _position;
      _position += size;
   }
   return pMemory;
}

//============================================================
// <T>在当前共享内存段内，分配新的共享内存段。</T>
//
// @param size 大小
// @return 共享内存段
//============================================================
TShareSegment TShareSegment::CreateSegment(TSize size){
   TShareSegment segment(_pMemory + _position, size, _created);
   _position += size;
   return segment;
}

//============================================================
// <T>分配共享对象大小的内存块，和共享对象关联。</T>
//
// @param pShared 共享对象
//============================================================
void TShareSegment::SharedLink(MShared* pShared){
   MO_ASSERT(pShared);
   TSize capacity = pShared->SharedCapacity();
   TShareSegment segment = CreateSegment(capacity);
   pShared->SharedLink(segment);
}

//============================================================
// <T>检测共享内存是否分配完整。</T>
//============================================================
void TShareSegment::Check(){
   if(_position != _length){
      TChar lengthBuffer[MO_MEMORY_FORMATLENGTH];
      TChar positionBuffer[MO_MEMORY_FORMATLENGTH];
      MO_FATAL(TC("Share memory segment is invalid. (length=%s, position=%s)"),
            RInt::FormatCapacity(_length, lengthBuffer, MO_MEMORY_FORMATLENGTH),
            RInt::FormatCapacity(_position, positionBuffer, MO_MEMORY_FORMATLENGTH));
   }
}

MO_NAMESPACE_END
