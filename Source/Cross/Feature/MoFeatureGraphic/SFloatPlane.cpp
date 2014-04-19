#include "MoFgCamera.h"

MO_NAMESPACE_BEGIN

//============================================================
//<T>获得信息字符串。</T>
//============================================================
TCharC* SFloatPlane::ToString(TChar* pBuffer, TInt capacity){
   TStringRefer refer(pBuffer, capacity);
   refer.AppendFormat("Plane[a=%f, b=%f, c=%f, d=%f]", a , b, c, d);
   return pBuffer;
}

MO_NAMESPACE_END
