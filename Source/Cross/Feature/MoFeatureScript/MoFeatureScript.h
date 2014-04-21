#ifndef __MO_FEATURE_SCRIPT_H__
#define __MO_FEATURE_SCRIPT_H__

#ifndef __MO_FC_COMMON_H__
#include "MoFcCommon.h"
#endif // __MO_FC_COMMON_H__

#ifndef __MO_FC_SCRIPT_H__
#include "MoScript.h"
#endif // __MO_FC_SCRIPT_H__

MO_NAMESPACE_BEGIN

MO_FC_DECLARE void MoFeatureScriptInitialize();
MO_FC_DECLARE void MoFeatureScriptStartup();
MO_FC_DECLARE void MoFeatureScriptShutdown();
MO_FC_DECLARE void MoFeatureScriptRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_SCRIPT_H__
