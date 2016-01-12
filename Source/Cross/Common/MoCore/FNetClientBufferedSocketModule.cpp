#include "MoCrNetSocket.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造套接字模块对象。</T>
//
// @return 当前对象指针。
//============================================================
FNetClientBufferedSocketModule::FNetClientBufferedSocketModule(){
   _name = TC("Module.NetClientSockets");
}

//============================================================
// <T>析构套接字模块对象。</T>
//
// @return 无返回值
//============================================================
FNetClientBufferedSocketModule::~FNetClientBufferedSocketModule(){
}

MO_NAMESPACE_END
