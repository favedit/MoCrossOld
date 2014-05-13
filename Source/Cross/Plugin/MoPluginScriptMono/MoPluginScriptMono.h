#ifndef __MO_PLUGIN_SCRIPT_MONO_H__
#define __MO_PLUGIN_SCRIPT_MONO_H__

#ifndef __MO_PSM_COMMON_H__
#include "MoPsmCommon.h"
#endif // __MO_PSM_COMMON_H__

#ifndef __MO_PSM_SCRIPT_H__
#include "MoPsmScript.h"
#endif // __MO_PSM_SCRIPT_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>插件脚本Mono功能。</T>
//============================================================
class MO_PSM_DECLARE FPluginScriptMonoFeature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FPluginScriptMonoFeature, FFeature);
public:
   FPluginScriptMonoFeature();
   MO_ABSTRACT ~FPluginScriptMonoFeature();
public:
   MO_OVERRIDE TResult Construct();
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
MO_PSM_DECLARE void MoPluginScriptMono();

MO_NAMESPACE_END

#endif //__MO_PLUGIN_SCRIPT_MONO_H__
