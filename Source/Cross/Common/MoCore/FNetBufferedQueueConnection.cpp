#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造链接对象，不需要参数。</T>
// <P>以后使用前先要设置输入输出管道大小。</P>
//
// @return 当前实例指针
//============================================================
FNetBufferedQueueConnection::FNetBufferedQueueConnection(){
   _pInputQueue = MO_CREATE(FNetBufferedQueue);
   _pOutputQueue = MO_CREATE(FNetBufferedQueue);
}

//============================================================
// <T>析构链接对象。</T>
//============================================================
FNetBufferedQueueConnection::~FNetBufferedQueueConnection(){
   MO_DELETE(_pInputQueue);
   MO_DELETE(_pOutputQueue);
}

//============================================================
// <T>设置处理。</T>
//
// @param capacity 容量
// @param pAllocator 收集器
//============================================================
void FNetBufferedQueueConnection::Setup(TInt capacity, FBufferedQueueBlockAllocator* pAllocator){
   MO_ASSERT(capacity > 0);
   // 设置输入流
   _pInputQueue->SetLockCd(EStreamLock_Lock);
   _pInputQueue->Pool()->SetCapacity(capacity);
   _pInputQueue->SetAllocator(pAllocator);
   // 设置输出流
   _pOutputQueue->SetLockCd(EStreamLock_Lock);
   _pOutputQueue->Pool()->SetCapacity(capacity);
   _pOutputQueue->SetAllocator(pAllocator);
}

//============================================================
// <T>显示运行时信息。</T>
//
// @param capacity 容量
// @param pAllocator 收集器
//============================================================
void FNetBufferedQueueConnection::Dump(){
   // 输出输入管道信息
   SQueueInfo inputInfo;
   _pInputQueue->FetchInfo(&inputInfo);
   MO_INFO(TC("Dump input queue info.  (length=%8d:%8d, push=%8lld:%24s, pop=%8lld:%24s)"),
         inputInfo.count, inputInfo.length,
         inputInfo.countPush, RInt::FormatCapacity(inputInfo.lengthPush, TFsCode(), TFsCode::Size()),
         inputInfo.countPop, RInt::FormatCapacity(inputInfo.lengthPop, TFsCode(), TFsCode::Size()));
   // 输出输出管道信息
   SQueueInfo outputInfo;
   _pOutputQueue->FetchInfo(&outputInfo);
   MO_INFO(TC("Dump output queue info. (length=%8d:%8d, push=%8lld:%24s, pop=%8lld:%24s)"),
         outputInfo.count, outputInfo.length,
         outputInfo.countPush, RInt::FormatCapacity(outputInfo.lengthPush, TFsCode(), TFsCode::Size()),
         outputInfo.countPop, RInt::FormatCapacity(outputInfo.lengthPop, TFsCode(), TFsCode::Size()));
}

MO_NAMESPACE_END
