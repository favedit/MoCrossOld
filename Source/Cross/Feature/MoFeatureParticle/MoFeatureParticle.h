#ifndef __MO_FEATURE_PARTICLE_H__
#define __MO_FEATURE_PARTICLE_H__

#ifndef __MO_FT_COMMON_H__
#include "MoFtCommon.h"
#endif // __MO_FT_COMMON_H__

#ifndef __MO_FT_PARTICLE_H__
#include "MoFtParticle.h"
#endif // __MO_FT_PARTICLE_H__

MO_NAMESPACE_BEGIN

MO_FT_DECLARE void MoFeatureParticleInitialize();
MO_FT_DECLARE void MoFeatureParticleStartup();
MO_FT_DECLARE void MoFeatureParticleShutdown();
MO_FT_DECLARE void MoFeatureParticleRelease();

MO_NAMESPACE_END

#endif //__MO_FEATURE_PARTICLE_H__
