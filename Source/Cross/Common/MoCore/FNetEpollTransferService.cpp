#include "MoCrNetTransferEpoll.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_LINUX

//============================================================
// <T>构造重叠端口服务。</T>
//============================================================
FNetEpollTransferService::FNetEpollTransferService(){
}

//============================================================
// <T>析构重叠端口服务。</T>
//============================================================
FNetEpollTransferService::~FNetEpollTransferService(){
}

#endif // _MO_LINUX

MO_NAMESPACE_END
