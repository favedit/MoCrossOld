#include "MoCmPipe.h"
#include "MoCmSingleton.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>无参数构造函数。</T>
//
// @return 当前实例指针 指针。
//============================================================
FSharedPipe::FSharedPipe(){
   _capacity = 0;
   MO_CLEAR(_gInfo);
   MO_CLEAR(_gData);
}

//============================================================
// <T>带参数构造函数</T>
//
// @param capacity 管道的容量
// @return 当前实例指针。
//============================================================
FSharedPipe::FSharedPipe(TSize capacity){
   _capacity = capacity;
   MO_CLEAR(_gInfo);
   MO_CLEAR(_gData);
}

//============================================================
// <T>计算共享管道所需内存空间。</T>
//
// @return 所需空间。
//============================================================
TSize FSharedPipe::CalculateCapacity(TSize capacity){
   return sizeof(SPipeInfo) + capacity;
}

//============================================================
TSize FSharedPipe::Capacity(){
   return _capacity;
}

//============================================================
// <T>设置FSharedPipe所需内存空间。</T>
//
// @param capacity 存放数据的内存长度。
//============================================================
void FSharedPipe::SetCapacity(TSize capacity){
   _capacity = capacity;
}

//============================================================
// <T>初始化共享内存。</T>
//============================================================
void FSharedPipe::OnSharedInitialize(){
   _gInfo->capacity = _capacity;
   _gInfo->first = 0;
   _gInfo->last = 0;
}

//============================================================
// <T>分配共享内存。</T>
//============================================================
void FSharedPipe::OnSharedLink(TShareSegment& segment){
   // 获得信息部分
   _gInfo = segment.TypeAlloc<SPipeInfo>();
   // 获得内存部分
   _gData = (TByte*)segment.Alloc(_capacity);
}

//============================================================
// <T>计算共享管道所需内存空间。</T>
//
// @return 所需空间。
//============================================================
TSize FSharedPipe::SharedCapacity(){
   return CalculateCapacity(_capacity);
}

//============================================================
// <T>获得计算共享管道所需内存空间。</T>
//
// @return 所需空间。
//============================================================
SPipeInfo* FSharedPipe::Info(){
   return _gInfo;
}

//============================================================
// <T>获取共享管道的成员。</T>
//
// @param pMemory 数据的存放内存
// @param capacity 存放数据的内存长度。
// @param length 已存数据长度
// @param first 读位置
// @param last 写位置
// @return 无返回
//============================================================
SPipeAtom FSharedPipe::Atom(){
   SPipeAtom atom;
   atom.infoPtr = _gInfo;
   atom.memoryPtr = _gData;
   atom.Fetch();
   return atom;
}

//============================================================
// <T>不使用共享内存创建对象。</T>
//
// @return 是否为空
//============================================================
TBool FSharedPipe::Create(){
   // 创建内存
   MO_ASSERT(_capacity > 0);
   TSize capacity = SharedCapacity();
   MO_ASSERT(capacity > 0);
   TByte* pMemory = MO_TYPES_ALLOC(TByte, capacity);
   RTypes<TByte>::Clear(pMemory, capacity);
   // 关联共享内存
   TShareSegment segment(pMemory, capacity, ETrue);
   segment.SharedLink(this);
   return ETrue;
}

//============================================================
// <T>判断管道是否为空。</T>
//
// @return 是否为空
//============================================================
TBool FSharedPipe::IsEmpty(){
   SPipeAtom atom = Atom();
   return atom.IsEmpty();
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TInt FSharedPipe::Length(){
   SPipeAtom atom = Atom();
   return atom.length;
}

//============================================================
// <T>获得管道以剩余的长度。</T>
//
// @return 剩余的长度
//============================================================
TInt FSharedPipe::Reamin(){
   SPipeAtom atom = Atom();
   return atom.Reamin();
}

//============================================================
// <T>重置FSharePipe对象。</T>
//
// @return TBool变量表示初始化成功。
//============================================================
TBool FSharedPipe::Reset(){
   SPipeAtom atom = Atom();
   atom.Clear();
   return ETrue;
}

MO_NAMESPACE_END
