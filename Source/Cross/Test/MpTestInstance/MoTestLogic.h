#ifndef __MP_TEST_LOGIC_H__
#define __MP_TEST_LOGIC_H__

#ifdef _MO_ANDROID
#ifndef JNI_H_
#include <jni.h>
#endif // JNI_H_
#endif // _MO_ANDROID

#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_CORE_H__
#include <MoCore.h>
#endif // __MO_CORE_H__

#ifndef __MO_ENGINE_H__
#include <MoEngine.h>
#endif // __MO_ENGINE_H__

MO_NAMESPACE_BEGIN

extern FPictureResource* g_pPictureResource;
extern FPictureResource* g_pLightResource;

TResult DoParticle(TFloat x, TFloat y);

MO_NAMESPACE_END

#endif //__MP_TEST_LOGIC_H__
