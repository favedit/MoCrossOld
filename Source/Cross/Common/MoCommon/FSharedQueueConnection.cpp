#include "MoCmPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造链接对象，不需要参数。</T>
// <P>以后使用前先要设置输入输出管道大小。</P>
//
// @return 当前实例指针
//============================================================
FSharedQueueConnection::FSharedQueueConnection(){
   _pInputQueue = MO_CREATE(FSharedQueue);
   _pOutputQueue = MO_CREATE(FSharedQueue);
}

//============================================================
// <T>构造链接对象。</T>
//
// @param inputCapacity 输入管道的容量
// @param outputCapacity 输出管道的容量
// @return 当前实例指针
//============================================================
FSharedQueueConnection::FSharedQueueConnection(TSize inputCapacity, TSize outputCapacity){
   _pInputQueue = MO_CREATE(FSharedQueue, inputCapacity);
   _pOutputQueue = MO_CREATE(FSharedQueue, outputCapacity);
}

//============================================================
// <T>析构链接对象。</T>
//============================================================
FSharedQueueConnection::~FSharedQueueConnection(){
   MO_DELETE(_pInputQueue);
   MO_DELETE(_pOutputQueue);
}

//============================================================
// <T>指定读写管道的长度。</T>
//
// @param capacity 输入队列的长度
//============================================================
void FSharedQueueConnection::SetInputCapacity(TSize capacity){
   _pInputQueue->SetCapacity(capacity);
}

//============================================================
// <T>指定读写管道的长度。</T>
//
// @param capacity 输出队列的长度
//============================================================
void FSharedQueueConnection::SetOutputCapacity(TSize capacity){
   _pOutputQueue->SetCapacity(capacity);
}

//============================================================
// <T>将共享内存段分派给链接。</T>
//
// @param segment 共享内存段的对象
//============================================================
void FSharedQueueConnection::OnSharedLink(TShareSegment& segment){
   segment.SharedLink(_pInputQueue);
   segment.SharedLink(_pOutputQueue);
}

//============================================================
// <T>获得链接所需的共享内存大小。</T>
//
// @return 链接所需的共享内存大小。
//============================================================
TSize FSharedQueueConnection::SharedCapacity(){
   TSize intputSize = _pInputQueue->SharedCapacity();
   TSize outputSize = _pOutputQueue->SharedCapacity();
   TSize capacity = intputSize + outputSize;
   return capacity;
}

//============================================================
// <T>获得收队列的指针。</T>
//
// @return 收队列的指针。
//============================================================
FSharedQueue* FSharedQueueConnection::InputQueue(){
   return _pInputQueue;
}

//============================================================
// <T>获得写队列的指针。</T>
//
// @return 写队列的指针。
//============================================================
FSharedQueue* FSharedQueueConnection::OutputQueue(){
   return _pOutputQueue;
}

MO_NAMESPACE_END
