#ifndef __MO_CORE_H__
#define __MO_CORE_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_CORE_H__
#include "MoCrCore.h"
#endif // __MO_CR_CORE_H__

#ifndef __MO_CR_CONTENT_H__
#include "MoCrContent.h"
#endif // __MO_CR_CONTENT_H__

#ifndef __MO_CR_NET_H__
#include "MoCrNet.h"
#endif // __MO_CR_NET_H__

#ifndef __MO_CR_LOGIC_H__
#include "MoCrLogic.h"
#endif // __MO_CR_LOGIC_H__

#ifndef __MO_CR_DEVICE_H__
#include "MoCrDevice.h"
#endif // __MO_CR_DEVICE_H__

MO_NAMESPACE_BEGIN

MO_CR_DECLARE void MoCoreInitialize();
MO_CR_DECLARE void MoCoreRelease();

MO_NAMESPACE_END

#endif // __MO_CORE_H__
