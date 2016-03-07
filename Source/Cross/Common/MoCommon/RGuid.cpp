#ifdef _MO_LINUX
#include <uuid/uuid.h>
#endif // _MO_LINUX
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>产生一个唯一编号。</T>
//
// @return 唯一编号
//============================================================
TGuid RGuid::Generate(){
   TGuid value;
#ifdef _MO_WINDOWS
   GUID guid;
   CoCreateGuid(&guid);
   value.AppendFormat(TC("%08X"), guid.Data1);
   value.AppendFormat(TC("%04X"), guid.Data2);
   value.AppendFormat(TC("%04X"), guid.Data3);
   for(TInt n = 0; n < 8; n++){
      value.AppendFormat(TC("%02X"), guid.Data4[n]);
   }
#endif // _MO_LINUX
#ifdef _MO_LINUX
   uuid_t uuid;
   uuid_generate(uuid);
   for(TInt n = 0; n < 16; n++){
      value.AppendFormat("%2X", uuid[n]);
   }
#endif // _MO_LINUX
   return value;
}

MO_NAMESPACE_END

