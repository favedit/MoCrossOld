#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetRouterHandle::FNetRouterHandle(){
   MO_CLEAR(_pInvokeName);
   _command = -1;
}

//============================================================
FNetRouterHandle::~FNetRouterHandle(){
};

MO_NAMESPACE_END
