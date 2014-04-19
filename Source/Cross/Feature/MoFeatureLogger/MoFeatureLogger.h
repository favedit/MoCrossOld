#ifndef __MO_FEATURE_LOGGER_H__
#define __MO_FEATURE_LOGGER_H__

#ifndef __MO_FL_COMMON_H__
#include "MoFlCommon.h"
#endif // __MO_FL_COMMON_H__

#ifndef __MO_FL_LOGGER_H__
#include "MoFlLogger.h"
#endif // __MO_FL_LOGGER_H__

MO_NAMESPACE_BEGIN

MO_FL_DECLARE void MoFeatureLoggerInitialize();
MO_FL_DECLARE void MoFeatureLoggerStartup();
MO_FL_DECLARE void MoFeatureLoggerShutdown();
MO_FL_DECLARE void MoFeatureLoggerRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_LOGGER_H__
