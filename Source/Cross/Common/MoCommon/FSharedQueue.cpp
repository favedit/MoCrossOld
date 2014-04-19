#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造共享队列的实例。</T>
//============================================================
FSharedQueue::FSharedQueue(){
}

//============================================================
// <T>构造共享队列的实例。</T>
//
// @param capacity 数据容量
//============================================================
FSharedQueue::FSharedQueue(TSize capacity) : FSharedPipe(capacity){
   if(0 != (capacity % 4)){
      MO_FATAL(TC("Queue size is must mod 4. (capacity=%d)"), capacity);
   }
}

//============================================================
// <T>连续获得管道以剩余的最大连续长度。</T>
//
// @return 剩余的长度
//============================================================
TBool FSharedQueue::FollowPush(TInt size){
   SPipeAtom atom = Atom();
   // 设置结尾位置
   TInt last = atom.last + sizeof(TInt32) + size;
   TInt mod = atom.last % sizeof(TInt32);
   if(0 != mod){
      last += sizeof(TInt32) - mod;
   }
   if(last == atom.capacity){
      last = 0;
   }
   atom.SetLast(last);
   return ETrue;
}

//============================================================
// <T>将一个完整信息写入管道。</T>
// <P>先写入数据长度(4byte)，再写入数据内容。
//    数据内容可能被分成两端放入管道的尾部和首部。</P>
//    每次结尾位置的偏移只能为4byte的倍数(sizeof(TInt32))。
// </P>
//
// @param pData 数据指针
// @param size 数据长度
// @return 压入是否成功
//============================================================
TBool FSharedQueue::Push(TAnyC* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      MO_FATAL(TC("Push data is empty. (data=0x%08X, size=%d)"), pData, size);
   }
   // 获取管道变量
   SPipeAtom atom = Atom();
   // 判断是否管道已满
   TInt freeLength = atom.Reamin();
   if(size > freeLength){
      MO_FATAL(TC("Current queue is full. (data=0x%08X, total=%d, length=%d, first=%d, last=%d, size=%d)"), atom.memoryPtr, atom.capacity, atom.length, atom.first, atom.last, size);
   }
   // 写入数据
   TInt position = 0;
   TInt block = sizeof(TInt32);
   TByte* pRead = (TByte*)pData;
   TByte* pWrite = atom.memoryPtr + atom.last;
   if(atom.first <= atom.last){
      // 处理写入数据 [--F====L--]
      //               0123456789
      TInt remain = atom.capacity - atom.last;
      if(remain < block){
         MO_FATAL(TC("Remain free data is too small. (remain=%d)"), remain);
      }
      *(TInt32*)pWrite = (TInt32)size;
      remain -= block;
      pWrite += block;
      // 写入数据
      if(size < remain){
         position = atom.last + block + size;
         memcpy(pWrite, pRead, size);
      }else if(size == remain){
         position = 0;
         memcpy(pWrite, pRead, size);
      }else if(size > remain){
         if(remain > 0){
            position = size - remain;
            memcpy(pWrite, pRead, remain);
            memcpy(atom.memoryPtr, pRead + remain, position);
         }else if(remain == 0){
            position = size;
            memcpy(atom.memoryPtr, pRead, size);
         }else{
            MO_FATAL(TC("Remain free length is invalid. (remain=%d)"), remain);
         }
      }
   }else{
      // 处理写入数据 [==L----F==]
      //               0123456789
      *(TInt32*)pWrite = (TInt32)size;
      position = atom.last + block + size;
      memcpy(pWrite + block, pRead, size);
   }
   // 设置结尾位置
   TInt mod = position % block;
   if(0 != mod){
      position += block - mod;
   }
   if(position == atom.capacity){
      position = 0;
   }
   atom.SetLast(position);
   return ETrue;
}

//============================================================
// <T>将一个完整信息写入管道。</T>
// <P>先写入数据长度(4byte)，再写入数据内容。
//    数据内容可能被分成两端放入管道的尾部和首部。</P>
//    每次结尾位置的偏移只能为4byte的倍数(sizeof(TInt32))。
// </P>
//
// @param pData 数据指针
// @param size 数据长度
// @return 压入是否成功
//============================================================
TBool FSharedQueue::TryPush(TAnyC* pData, TInt size){
   // 检查参数
   MO_ASSERT(pData);
   if(0 == size){
      MO_WARN(TC("Push data is empty. (data=0x%08X, size=%d)"), pData, size);
      return EFalse;
   }
   // 获取管道变量
   SPipeAtom atom = Atom();
   // 判断是否管道已满
   TInt freeLength = atom.Reamin();
   if(size > freeLength){
      MO_WARN(TC("Current queue is full. (data=0x%08X, total=%d, length=%d, first=%d, last=%d, size=%d)"), atom.memoryPtr, atom.capacity, atom.length, atom.first, atom.last, size);
      return EFalse;
   }
   // 写入数据
   TInt position = 0;
   TInt block = sizeof(TInt32);
   TByte* pRead = (TByte*)pData;
   TByte* pWrite = atom.memoryPtr + atom.last;
   if(atom.first <= atom.last){
      // 处理写入数据 [--F====L--]
      //               0123456789
      TInt remain = atom.capacity - atom.last;
      if(remain < block){
         MO_WARN(TC("Remain free data is too small. (remain=%d)"), remain);
         return EFalse;
      }
      *(TInt32*)pWrite = (TInt32)size;
      remain -= block;
      pWrite += block;
      // 写入数据
      if(size < remain){
         position = atom.last + block + size;
         memcpy(pWrite, pRead, size);
      }else if(size == remain){
         position = 0;
         memcpy(pWrite, pRead, size);
      }else if(size > remain){
         if(remain > 0){
            position = size - remain;
            memcpy(pWrite, pRead, remain);
            memcpy(atom.memoryPtr, pRead + remain, position);
         }else if(remain == 0){
            position = size;
            memcpy(atom.memoryPtr, pRead, size);
         }else{
            MO_WARN(TC("Remain free length is invalid. (remain=%d)"), remain);
            return EFalse;
         }
      }
   }else{
      // 处理写入数据 [==L----F==]
      //               0123456789
      *(TInt32*)pWrite = (TInt32)size;
      position = atom.last + block + size;
      memcpy(pWrite + block, pRead, size);
   }
   // 设置结尾位置
   TInt mod = position % block;
   if(0 != mod){
      position += block - mod;
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
//    每次开始位置的偏移只能为4byte的倍数(sizeof(TInt32))。
// </P>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0表示没有读出有效数据
//============================================================
TInt FSharedQueue::Pop(TAny* pData, TInt capacity){
   // 检查参数
   MO_ASSERT(pData);
   MO_ASSERT(capacity > 0);
   TInt block = sizeof(TInt32);
   // 获取变量
   SPipeAtom atom = Atom();
   // 判断是否可以读出
   if(atom.length <= block){
      return 0;
   }
   // 读取长度
   TByte* pRead = atom.memoryPtr + atom.first;
   TInt size = *(TInt32*)pRead;
   if(0 == size){
      MO_FATAL(TC("Read data length is 0. (memory=0x%08X, total=%d, length=%d, first=%d, last=%d)"),
            atom.memoryPtr, atom.capacity, atom.length, atom.first, atom.last);
   }
   if(size > capacity){
      TFsDump buffer;
      MO_FATAL(TC("Read data length is large than capacity. (memory=0x%08X, total=%d, length=%d, first=%d, last=%d, size=%d)\n%s"),
            atom.memoryPtr, atom.capacity, atom.length, atom.first, atom.last, size,
            RByte::Dump(pRead - 32, 256, buffer.Memory(), buffer.Size()));
   }
   if(size > atom.length){
      TFsDump buffer;
      MO_FATAL(TC("Read data length is large than length. (memory=0x%08X, total=%d, length=%d, first=%d, last=%d, size=%d)\n%s"),
            atom.memoryPtr, atom.capacity, atom.length, atom.first, atom.last, size,
            RByte::Dump(pRead - 32, 256, buffer.Memory(), buffer.Size()));
   }
   pRead += block;
   // 读取数据
   TInt position = 0;
   if(atom.first < atom.last){
      // 处理读取数据 [--F====L--]
      //              [0123456789]
      position = atom.first + block + size;
      memcpy(pData, pRead, size);
   }else{
      // 处理读取数据 [==L----F==]
      //              [0123456789]
      TInt remain = atom.capacity - atom.first - block;
      if(size < remain){
         position = atom.first + block + size;
         memcpy(pData, pRead, size);
      }else if(size == remain){
         position = 0;
         memcpy(pData, pRead, size);
      }else if(size > remain){
         if(remain > 0){
            position = size - remain;
            memcpy(pData, pRead, remain);
            memcpy((TByte*)pData + remain, atom.memoryPtr, position);
         }else if(remain == 0){
            position = size;
            memcpy(pData, atom.memoryPtr, size);
         }else{
            MO_FATAL(TC("Remain free length is invalid. (remain=%d)"), remain);
         }
      }
   }
   // 设置开始位置
   TInt mod = position % block;
   if(0 != mod){
      position += block - mod;
   }
   if(position == atom.capacity){
      position = 0;
   }
   atom.SetFirst(position);
   return size;
}

//============================================================
// <T>向管道内堵塞式压入一个完整的数据。</T>
//
// @param pData 数据指针
// @param size 数据长度
// @return 压入数据是否成功，为False时当前线程会堵塞
//============================================================
TBool FSharedQueue::BlockedPush(TAnyC* pData, TInt size){
   if(Push(pData, size)) {
      return ETrue;
   }
   return EFalse;
}

//============================================================
// <T>从管道内堵塞式弹出一个完整的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据长度
// @return 读出数据的长度，为0时当前线程会堵塞
//============================================================
TInt FSharedQueue::BlockedPop(TAny* pData, TInt capacity){
   TInt length = 0;
   do{
      length = Pop(pData, capacity);
      if(0 == length){
         MO_LIB_SLEEP(1);
      }
   }while(0 == length);
   return length;
}

//============================================================
// <T>输出管道使用情况。</T>
//
// @return bool
//============================================================
TBool FSharedQueue::DumpInfo(TChar* pTag){
	if (NULL == pTag){
		return EFalse;
	}
	SPipeAtom atom = Atom();
	MO_INFO(TC("[%s]QInfo:(total=%d, length=%d, first=%d, last=%d, free=%d)"), pTag, atom.capacity, atom.length, atom.first, atom.last, atom.Reamin());
	return ETrue;
}


MO_NAMESPACE_END
