#ifdef _MO_WINDOWS
#include <winsock.h>
#else
#include <unistd.h>
#include <arpa/inet.h>
#endif
#include "MoCmNet.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>主机地址转换为数字。</T>
//
// @param pHost 主机地址
// @return 数字
//============================================================
TInt RNetHost::NetHostToInt(TChar* pHost){
#ifdef _UNICODE
   return 0;
#else
   return inet_addr(pHost);
#endif
};

//============================================================
// <T>数字转换为主机地址。</T>
//
// @param host 主机地址
// @param pHost 主机字符串
// @param length 主机字符串长度
//============================================================
TInt RNetHost::NetHostToChar(TInt host, TChar* pHost, TSize length){
   struct in_addr addr;
   addr.s_addr = host;
#ifdef _UNICODE
   return 0;
#else
   return RString::SafeCopy(pHost, length, inet_ntoa(addr));
#endif
};

MO_NAMESPACE_END
