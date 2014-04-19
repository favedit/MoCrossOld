#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetMessageHandle::FNetMessageHandle(){
   MO_CLEAR(_pInvokeName);
   _command = -1;
}

//============================================================
FNetMessageHandle::~FNetMessageHandle(){
};

MO_NAMESPACE_END
