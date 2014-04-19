//============================================================
// <T>共通定义。</T>
//============================================================
#ifndef __MO_EF_PUBLIC_H__
#define __MO_EF_PUBLIC_H__

#ifndef AS3_H
#include <AS3/AS3.h>
#endif // AS3_H

#ifndef FLASHpp_h
#include <Flash++.h>
#endif // FLASHpp_h

#ifdef _MO_FLASH
using namespace AS3::ui;
#else
using namespace AS3::local;
#endif // _MO_FLASH

#ifndef __MO_COMMON_H__
#include <MoCommon.h>
#endif // __MO_COMMON_H__

#ifndef __MO_ENGINE_H__
#include <MoEngine.h>
#endif // __MO_ENGINE_H__

//============================================================
/// @define 导出定义
#ifdef _MO_EF_EXPORTS
#define MO_EF_DECLARE MO_EXPORT
#else
#define MO_EF_DECLARE MO_IMPORT
#endif

MO_NAMESPACE_BEGIN

MO_NAMESPACE_END

#endif // __MO_EF_PUBLIC_H__
