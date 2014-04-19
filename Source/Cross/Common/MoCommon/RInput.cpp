#include "MoCmStream.h"

MO_NAMESPACE_BEGIN

//============================================================
TSize RInput::CalculateStringCapacity(const MString& value){
   return sizeof(TUint32) + value.Length();
}

MO_NAMESPACE_END
