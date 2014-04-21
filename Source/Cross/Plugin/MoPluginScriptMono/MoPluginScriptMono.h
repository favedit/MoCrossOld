#ifndef __MO_PLUGIN_SCRIPT_MONO_H__
#define __MO_PLUGIN_SCRIPT_MONO_H__

#ifndef __MO_PSM_COMMON_H__
#include "MoPsmCommon.h"
#endif // __MO_PSM_COMMON_H__

#ifndef __MO_PSM_SCRIPT_H__
#include "MoPsmScript.h"
#endif // __MO_PSM_SCRIPT_H__

MO_NAMESPACE_BEGIN

MO_PSM_DECLARE void MoPluginScriptMonoInitialize();
MO_PSM_DECLARE void MoPluginScriptMonoStartup();
MO_PSM_DECLARE void MoPluginScriptMonoShutdown();
MO_PSM_DECLARE void MoPluginScriptMonoRelease();

MO_NAMESPACE_END

#endif //__MO_PLUGIN_SCRIPT_MONO_H__
