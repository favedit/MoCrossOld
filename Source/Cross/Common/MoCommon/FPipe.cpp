#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据管道对象。</T>
//
// @return 数据管道对象
//============================================================
FPipe::FPipe(){
   _linked = ETrue;
   RType<SPipeInfo>::Clear(&_info);
   MO_CLEAR(_pMemory);
}

//============================================================
FPipe::FPipe(TSize capacity){
   _linked = EFalse;
   RType<SPipeInfo>::Clear(&_info);
   capacity += capacity % sizeof(TUint32);
   _info.capacity = capacity;
   _pMemory = MO_TYPES_ALLOC(TByte, capacity);
   RTypes<TByte>::Clear(_pMemory, capacity);
}

//============================================================
// <T>构造数据管道对象。</T>
//
// @param pMemory 内存指针
// @param capacity 最大容量
// @return 数据管道对象
//============================================================
FPipe::FPipe(TByte* pMemory, TSize capacity){
   _linked = ETrue;
   RType<SPipeInfo>::Clear(&_info);
   _info.capacity = capacity;
   _pMemory = pMemory;
}

//============================================================
FPipe::~FPipe(){
   if(!_linked){
      MO_FREE(_pMemory);
   }
}

//============================================================
SPipeInfo* FPipe::Info(){
   return &_info;
}

//============================================================
SPipeAtom FPipe::Atom(){
   SPipeAtom atom;
   atom.infoPtr = &_info;
   atom.memoryPtr = _pMemory;
   atom.Fetch();
   return atom;
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TInt FPipe::Length(){
   TInt first = _info.first;
   TInt last = _info.last;
   TInt length = (first <= last) ? last - first : (_info.capacity - first) + last;
   return length;
}

//============================================================
// <T>获得管道以剩余的长度。</T>
//
// @return 剩余的长度
//============================================================
TInt FPipe::Reamin(){
   TInt first = _info.first;
   TInt last = _info.last;
   TInt length = (first <= last) ? last - first : (_info.capacity - first) + last;
   return _info.capacity - length - MoPipeReserveLength;
}

//============================================================
//<T>重置管道空间。</T>
//============================================================
TBool FPipe::Reset(){
   memset(&_info, 0, sizeof(SPipeInfo));
   MO_CLEAR(_pMemory);
   return ETrue;
}

MO_NAMESPACE_END
