#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
MPipe::MPipe(){
}

//============================================================
MPipe::~MPipe(){
}

//============================================================
// <T>判断管道是否为空。</T>
//
// @return 是否为空
//============================================================
TBool MPipe::IsEmpty(){
   SPipeAtom atom = Atom();
   return (atom.length > 0);
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TInt MPipe::Length(){
   SPipeAtom atom = Atom();
   return atom.length;
}

//============================================================
// <T>获得管道以剩余的长度。</T>
//
// @return 剩余的长度
//============================================================
TInt MPipe::Reamin(){
   SPipeAtom atom = Atom();
   return atom.capacity - atom.length - MoPipeReserveLength;
}


//============================================================
// <T>获得管道以剩余的最大连续长度。</T>
//
// @return 剩余的长度
//============================================================
TInt MPipe::FollowReamin(){
   SPipeAtom atom = Atom();
   TInt followRemain = 0;
   if(atom.first < atom.last){
      // 处理读取数据 [---F===L----]
      followRemain = atom.capacity - atom.last - MoPipeReserveLength;
   }else{
      // 处理读取数据 [===L---F====]
      followRemain = atom.first - atom.last - MoPipeReserveLength;
   }
   return followRemain;
}

//============================================================
TByte* MPipe::FollowMemory(){
   SPipeAtom atom = Atom();
   return atom.memoryPtr + atom.last;
}

//============================================================
// <T>向连续管道中写入数据。</T>
//
// @param size 大小
// @return 处理结果
//============================================================
TBool MPipe::FollowWrite(TInt size){
   SPipeAtom atom = Atom();
   atom.SetLast(atom.last + size);
   return ETrue;
}

//============================================================
// <T>从连续管道中读出数据。</T>
//
// @param size 大小
// @return 处理结果
//============================================================
TBool MPipe::FollowRead(TInt size){
   SPipeAtom atom = Atom();
   atom.SetFirst(atom.first + size);
   return ETrue;
}

//============================================================
// <T>尝试将一整块数据拾取到数据缓冲区。</T>
// <P>不影响内部数据开始和结束位置。</P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 拾取到数据的长度
//============================================================
TInt MPipe::Peek(TAny* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return 0;
   }
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以读出
   TUint32 peeked = MO_LIB_MIN(size, atom.length);
   // 读取数据
   if(atom.first < atom.last){
      // 处理读取数据 [---F===L----]
      memcpy(pData, atom.memoryPtr + atom.first, peeked);
   }else{
      // 处理读取数据 [===L---F====]
      TUint32 remain = atom.capacity - atom.first;
      if(peeked <= remain){
         memcpy(pData, atom.memoryPtr + atom.first, peeked);
      }else{
         memcpy(pData, atom.memoryPtr + atom.first, remain);
         memcpy((TByte*)pData + remain, atom.memoryPtr, peeked - remain);
      }
   }
   return peeked;
}

//============================================================
// <T>往管道写入指定长度数据。</T>
// <P>如果管道可写入的部分不足，则只写入能够写进的数据。</P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 实际写入数据长度
//============================================================
TInt MPipe::Write(TAnyC* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return 0;
   }
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以写入
   TInt position = 0;
   TInt remainLength = atom.capacity - atom.length - MoPipeReserveLength;
   TInt writted = MO_LIB_MIN(remainLength, size);
   // 写入数据
   if(atom.first <= atom.last){
      // 处理写入数据 [---F===L----]
      TInt remain = atom.capacity - atom.last;
      // 写入数据
      if(writted < remain){
         position = atom.last + writted;
         memcpy(atom.memoryPtr + atom.last, pData, writted);
      }else if(writted == remain){
         memcpy(atom.memoryPtr + atom.last, pData, writted);
      }else{
         position = writted - remain;
         memcpy(atom.memoryPtr + atom.last, pData, remain);
         memcpy(atom.memoryPtr, (TByte*)pData + remain, position);
      }
   }else{
      // 处理写入数据 [===L---F====]
      position = atom.last + writted;
      memcpy(atom.memoryPtr + atom.last, pData, writted);
   }
   // 设置结尾位置
   atom.SetLast(position);
   return writted;
}

//============================================================
// <T>从管道读取指定长度的数据。</T>
// <P>如果可读数据不足，则只读取能够读取到的数据。</P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 实际取走数据长度。
//============================================================
TInt MPipe::Read(TAny* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return 0;
   }
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以读出
   TInt position = 0;
   TInt readed = MO_LIB_MIN(size, atom.length);
   // 读取数据
   if(atom.first < atom.last){
      // 处理读取数据 [---F===L----]
      position = atom.first + readed;
      memcpy(pData, atom.memoryPtr + atom.first, readed);
   }else{
      // 处理读取数据 [===L---F====]
      TInt remain = atom.capacity - atom.first;
      if(readed < remain){
         position = atom.first + readed;
         memcpy(pData, atom.memoryPtr + atom.first, readed);
      }else if(readed == remain){
         memcpy(pData, atom.memoryPtr + atom.first, readed);
      }else{
         position = readed - remain;
         memcpy(pData, atom.memoryPtr + atom.first, remain);
         memcpy((TByte*)pData + remain, atom.memoryPtr, position);
      }
   }
   // 设置开始位置
   atom.SetFirst(position);
   return readed;
}

//============================================================
// <T>尝试将一整块数据拾取到数据缓冲区。</T>
// <P>不影响内部数据开始和结束位置。</P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 拾取是否成功
//============================================================
TBool MPipe::TryPeek(TAny* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return ETrue;
   }
   // _locker.Enter();
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以读出
   if(size > atom.length){
      return EFalse;
   }
   // 读取数据
   if(atom.first < atom.last){
      // 处理读取数据 [---F===L----]
      memcpy(pData, atom.memoryPtr + atom.first, size);
   }else{
      // 处理读取数据 [===L---F====]
      TInt remain = atom.capacity - atom.first;
      if(size <= remain){
         memcpy(pData, atom.memoryPtr + atom.first, size);
      }else{
         memcpy(pData, atom.memoryPtr + atom.first, remain);
         memcpy((TByte*)pData + remain, atom.memoryPtr, size - remain);
      }
   }
   // _locker.Leave();
   return ETrue;
}

//============================================================
// <T>尝试将一整块数据写入数据缓冲区。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 写入是否成功
//============================================================
TBool MPipe::TryWrite(TAnyC* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return ETrue;
   }
   // _locker.Enter();
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以写入
   if((atom.capacity - atom.length) < (size + MoPipeReserveLength)){
      return EFalse;
   }
   // 写入数据
   TInt position = 0;
   if(atom.first <= atom.last){
      // 处理写入数据 [---F===L----]
      TInt remain = atom.capacity - atom.last;
      // 写入数据
      if(size < remain){
         position = atom.last + size;
         memcpy(atom.memoryPtr + atom.last, pData, size);
      }else if(size == remain){
         memcpy(atom.memoryPtr + atom.last, pData, size);
      }else{
         position = size - remain;
         memcpy(atom.memoryPtr + atom.last, pData, remain);
         memcpy(atom.memoryPtr, (TByte*)pData + remain, position);
      }
   }else{
      // 处理写入数据 [===L---F====]
      position = atom.last + size;
      memcpy(atom.memoryPtr + atom.last, pData, size);
   }
   // 设置结尾
   atom.SetLast(position);
   // _locker.Leave();
   return ETrue;
}

//============================================================
// <T>尝试将一整块数据读取数据缓冲区。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读取是否成功
//============================================================
TBool MPipe::TryRead(TAny* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return ETrue;
   }
   // _locker.Enter();
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以读出
   if(size > atom.length){
      return EFalse;
   }
   // 读取数据
   TInt position = 0;
   if(atom.first < atom.last){
      // 处理读取数据 [---F===L----]
      position = atom.first + size;
      memcpy(pData, atom.memoryPtr + atom.first, size);
   }else{
      // 处理读取数据 [===L---F====]
      TInt remain = atom.capacity - atom.first;
      if(size < remain){
         position = atom.first + size;
         memcpy(pData, atom.memoryPtr + atom.first, size);
      }else if(size == remain){
         memcpy(pData, atom.memoryPtr + atom.first, size);
      }else{
         position = size - remain;
         memcpy(pData, atom.memoryPtr + atom.first, remain);
         memcpy((TByte*)pData + remain, atom.memoryPtr, position);
      }
   }
   // 设置结尾
   atom.SetFirst(position);
   // _locker.Leave();
   return ETrue;
}

//============================================================
// <T>重置管道。</T>
//
// @return 处理结果
//============================================================
TBool MPipe::Reset(){
   SPipeAtom atom = Atom();
   atom.Set(0, 0);
   return ETrue;
}

//============================================================
// <T>关闭管道。</T>
//
// @return 处理结果
//============================================================
TBool MPipe::Close(){
   return ETrue;
}

//============================================================
void MPipe::Track(){
   SPipeAtom atom = Atom();
   MO_DEBUG(TC("Track pipe. (memory=0x%08X, capacity=%d, length=%d, first=%d, last=%d)"),
         atom.memoryPtr, atom.capacity, atom.length, atom.first, atom.last);
}

MO_NAMESPACE_END
