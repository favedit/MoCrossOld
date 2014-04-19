#include "MoCrNetConnection.h"

MO_NAMESPACE_BEGIN

//============================================================
FNetTransferHandle::FNetTransferHandle(){
   MO_CLEAR(_pInvokeName);
   _command = -1;
}

//============================================================
FNetTransferHandle::~FNetTransferHandle(){
};

MO_NAMESPACE_END
