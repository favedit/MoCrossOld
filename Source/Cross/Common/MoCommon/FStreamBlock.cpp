#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造缓冲数据流分块。</T>
//============================================================
FStreamBlock::FStreamBlock(){
   MO_CLEAR(_pMemory);
   _capacity = 0;
   _length = 0;
   _lengthMax = 0;
   _position = 0;
   _lengthTotalRead = 0;
   _lengthTotalWrite = 0;
}

//============================================================
// <T>析构缓冲数据流分块。</T>
//============================================================
FStreamBlock::~FStreamBlock(){
   MO_FREE(_pMemory);
}

//============================================================
// <T>强制管道的容量。</T>
//
// @param capacity 容量
//============================================================
void FStreamBlock::ForceCapacity(TInt capacity){
   MO_ASSERT(capacity > 0);
   // 释放内存
   if(NULL != _pMemory){
      MO_FREE(_pMemory);
   }
   // 创建内存
   _pMemory = MO_TYPES_ALLOC(TByte, capacity);
   _capacity = capacity;
   _position = 0;
}

//============================================================
// <T>判断内存是否为空。</T>
//
// @return 是否为空
//============================================================
TBool FStreamBlock::IsEmpty(){
   return (0 == _position);
}

//============================================================
// <T>测试数据是否能够写入。</T>
//
// @param length 数据长度
// @return 是否能够写入
//============================================================
TBool FStreamBlock::TestPushAble(TInt length){
   return EFalse;
}

//============================================================
// <T>测试数据是否能够读取。</T>
//
// @param length 数据长度
// @return 是否能够读取
//============================================================
TBool FStreamBlock::TestPopAble(TInt length){
   return EFalse;
}

//============================================================
// <T>反转数据读取处理。</T>
//
// @return 处理结果
//============================================================
TBool FStreamBlock::FlipForRead(){
   _length = _position;
   _position = 0;
#ifdef _MO_DEBUG
   // 统计最大长度
   if(_length > _lengthMax){
      _lengthMax = _length;
   }
#endif // _DEBUG
   return ETrue;
}

//============================================================
// <T>反转数据写入处理。</T>
//
// @return 处理结果
//============================================================
TBool FStreamBlock::FlipForWrite(){
   _length = _capacity;
   _position = 0;
   return ETrue;
}

//============================================================
// <T>重置数据处理。</T>
//
// @return 处理结果
//============================================================
TBool FStreamBlock::Reset(){
   _capacity = 0;
   _length = 0;
   _lengthMax = 0;
   _position = 0;
   _lengthTotalRead = 0;
   _lengthTotalWrite = 0;
   return ETrue;
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 跟踪内容
//============================================================
void FStreamBlock::Track(MString* pTrack){
   TInt percent = _lengthMax * 100 / _capacity;
   pTrack->AppendFormat(TC("block=0x%08X, capacity=%d/%d(%d%), length=%d, position=%d"), this, _lengthMax, _capacity, percent, _length, _position);
}

MO_NAMESPACE_END
