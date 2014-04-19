#ifndef __MO_ENGINE_FLASH_H__
#define __MO_ENGINE_FLASH_H__

#ifndef __MO_EF_PUBLIC_H__
#include "MoEfPublic.h"
#endif // __MO_EF_PUBLIC_H__

#ifndef __MO_EF_COMMON_H__
#include "MoEfCommon.h"
#endif // __MO_EF_COMMON_H__

#ifndef __MO_EF_RENDER_H__
#include "MoEfRender.h"
#endif // __MO_EF_RENDER_H__

MO_NAMESPACE_BEGIN

MO_EF_DECLARE void MoFlashInitialize();
MO_EF_DECLARE void MoFlashRelease();

MO_NAMESPACE_END

#endif //__MO_ENGINE_FLASH_H__
