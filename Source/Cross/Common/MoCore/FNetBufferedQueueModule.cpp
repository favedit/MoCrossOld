#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造链接对象，不需要参数。</T>
// <P>以后使用前先要设置输入输出管道大小。</P>
//
// @return 当前实例指针
//============================================================
FNetBufferedQueueModule::FNetBufferedQueueModule(){
   _name = TC("Module.NetQueue");
   // 初始化所有对象
   _pStorage = MO_CREATE(FSharedNetQueueStorage);
}

//============================================================
// <T>析构链接对象。</T>
//============================================================
FNetBufferedQueueModule::~FNetBufferedQueueModule(){
   MO_DELETE(_pStorage);
}

//============================================================
// <T>加载设置信息。</T>
//
// @return 处理结果
//============================================================
TResult FNetBufferedQueueModule::OnLoadConfig(FXmlNode* pConfig){
   return _pStorage->LoadConfig(pConfig);
}

//============================================================
// <T>获得存储对象。</T>
//
// @return 收队列的指针。
//============================================================
FSharedNetQueueStorage* FNetBufferedQueueModule::Storage(){
   return _pStorage;
}

//============================================================
// <T>获得管道链接。</T>
//
// @return 收队列的指针。
//============================================================
FNetBufferedQueueConnection* FNetBufferedQueueModule::Connection(){
   return _pStorage->Connection();
}

MO_NAMESPACE_END
