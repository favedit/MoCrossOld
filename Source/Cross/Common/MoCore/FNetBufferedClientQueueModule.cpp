#include "MoCrNetPipe.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造套接字模块对象。</T>
//
// @return 当前对象指针。
//============================================================
FNetBufferedClientQueueModule::FNetBufferedClientQueueModule(){
   _name = TC("Module.NetClientQueue");
}

//============================================================
// <T>析构套接字模块对象。</T>
//
// @return 无返回值
//============================================================
FNetBufferedClientQueueModule::~FNetBufferedClientQueueModule(){
}

MO_NAMESPACE_END
