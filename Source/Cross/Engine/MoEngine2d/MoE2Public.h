//============================================================
// <T>共通定义。</T>
//============================================================
#ifndef __MO_E2_PUBLIC_H__
#define __MO_E2_PUBLIC_H__

#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_CORE_H__
#include <MoCore.h>
#endif // __MO_CORE_H__

#ifndef __MO_MATH_H__
#include <MoMath.h>
#endif // __MO_MATH_H__

#ifndef __MO_FEATURE_GRAPHIC_H__
#include <MoFeatureGraphic.h>
#endif // __MO_FEATURE_GRAPHIC_H__

#ifndef __MO_FEATURE_RESOURCE_H__
#include <MoFeatureResource.h>
#endif // __MO_FEATURE_RESOURCE_H__

#ifndef __MO_FEATURE_PHYSICS_H__
#include <MoFeaturePhysics.h>
#endif // __MO_FEATURE_PHYSICS_H__

#ifndef __MO_ENGINE_H__
#include <MoEngine.h>
#endif // __MO_ENGINE_H__

//============================================================
/// @define 导出定义
#ifdef _MO_E2_EXPORTS
#define MO_E2_DECLARE MO_EXPORT
#else
#define MO_E2_DECLARE MO_IMPORT
#endif // _MO_E2_EXPORTS

MO_NAMESPACE_BEGIN

MO_NAMESPACE_END

#endif // __MO_E2_PUBLIC_H__
