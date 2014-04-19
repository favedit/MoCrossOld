#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据队列的实例。</T>
//============================================================
FQueue::FQueue(){
}

//============================================================
// <T>构造数据队列的实例。</T>
//
// @param capacity 数据容量
//============================================================
FQueue::FQueue(TSize capacity) : FPipe(capacity){
}

//============================================================
// <T>构造数据队列的实例。</T>
//
// @param pMemory 数据内存
// @param capacity 数据容量
//============================================================
FQueue::FQueue(TByte* pMemory, TSize capacity) : FPipe(pMemory, capacity){
}

//============================================================
// <T>析构数据队列的实例。</T>
//============================================================
FQueue::~FQueue(){
}

//============================================================
// <T>将一个完整信息写入管道。</T>
// <P>先写入数据长度(4byte)，再写入数据内容。
//    数据内容可能被分成两端放入管道的尾部和首部。</P>
//    每次结尾位置的偏移只能为4byte的倍数(sizeof(TUint32))。
// </P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 压入是否成功
//============================================================
TBool FQueue::Push(TAnyC* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      return ETrue;
   }
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
      MO_ASSERT(remain >= (TInt)sizeof(TUint32));
      *(TUint32*)(atom.memoryPtr + atom.last) = size;
      remain -= sizeof(TUint32);
      TByte* pLast = atom.memoryPtr + atom.last + sizeof(TUint32);
      // 写入数据
      if(size < remain){
         memcpy(pLast, pData, size);
         position = atom.last + sizeof(TUint32) + size;
      }else if(size == remain){
         memcpy(pLast, pData, size);
         position = 0;
      }else{
         memcpy(pLast, pData, remain);
         memcpy(atom.memoryPtr, (TByte*)pData + remain, size - remain);
         position = size - remain;
      }
   }else{
      // 处理写入数据 [===L---F====]
      TByte* pWrite = atom.memoryPtr + atom.last;
      *(TUint32*)pWrite = size;
      pWrite += sizeof(TUint32);
      // 写入数据
      memcpy(pWrite, pData, size);
      position = atom.last + sizeof(TUint32) + size;
   }
   // 设置结尾位置
   TInt mod = position % sizeof(TUint32);
   if(0 != mod){
      position += sizeof(TUint32) - mod;
   }
   if(position == atom.capacity){
      position = 0;
   }
   atom.SetLast(position);
   return ETrue;
}

//============================================================
// <T>从管道内弹出一个完整的消息。</T>
// <P>先读出数据长度(4byte)，再读出数据内容。
//    数据内容可能被分成两端放入管道的尾部和首部。</P>
//    每次开始位置的偏移只能为4byte的倍数(sizeof(TUint32))。
// </P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据
//============================================================
TInt FQueue::Pop(TAny* pData, TInt capacity){
   // 检查参数
   MO_ASSERT(pData);
   MO_ASSERT(capacity > 0);
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以读出
   if(atom.length <= (TInt)sizeof(TUint32)){
      return 0;
   }
   TByte* pRead = atom.memoryPtr + atom.first;
   TInt size = *(TUint32*)pRead;
   MO_ASSERT(size > 0);
   MO_ASSERT(size <= atom.capacity);
   MO_ASSERT(size <= atom.length);
   pRead += sizeof(TUint32);
   // 读取数据
   TInt position = 0;
   if(atom.first < atom.last){
      // 处理读取数据 [---F===L----]
      memcpy(pData, pRead, size);
      position = atom.first + sizeof(TUint32) + size;
   }else{
      // 处理读取数据 [===L---F====]
      TInt remain = atom.capacity - atom.first - sizeof(TUint32);
      if(size < remain){
         memcpy(pData, pRead, size);
         position = atom.first + sizeof(TUint32) + size;
      }else if(size == remain){
         memcpy(pData, pRead, size);
         position = 0;
      }else{
         memcpy(pData, pRead, remain);
         memcpy((TByte*)pData + remain, atom.memoryPtr, size - remain);
         position = size - remain;
      }
   }
   // 设置开始位置
   TInt mod = position % sizeof(TUint32);
   if(0 != mod){
      position += sizeof(TUint32) - mod;
   }
   if(position == atom.capacity){
      position = 0;
   }
   atom.SetFirst(position);
   return size;
}

MO_NAMESPACE_END
