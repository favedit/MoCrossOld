#ifndef __MO_FG_GEOM_H__
#define __MO_FG_GEOM_H__
//************************************************************

#ifndef __MO_FG_PUBLIC_H__
#include "MoFgPublic.h"
#endif // __MO_FG_PUBLIC_H__

MO_NAMESPACE_BEGIN

#define MO_ENG_GEOM_PI2         (MO_PI_FLOAT * 2)
#define MO_ENG_GEOM_RADIAN_RATE (180.0f / MO_PI_FLOAT)
#define MO_ENG_GEOM_DEGREE_RATE (MO_PI_FLOAT / 180.0f)

//============================================================
// <T>3Dæÿ’Û°£</T>
//============================================================
struct MO_FG_DECLARE SMatrix3d : public SFloatMatrix3d{
public:
   SMatrix3d();
};

//============================================================
// <T>3DÕ∂”∞æÿ’Û°£</T>
//============================================================
struct MO_FG_DECLARE SPerspectiveMatrix3d : public SMatrix3d{
public:
   SPerspectiveMatrix3d();
public:
   void PerspectiveLH(TFloat width, TFloat height, TFloat zNear, TFloat zFar);
   void PerspectiveRH(TFloat width, TFloat height, TFloat zNear, TFloat zFar);
   void PerspectiveFieldOfViewLH(TFloat fieldOfViewY, TFloat aspectRatio, TFloat zNear, TFloat zFar);
	void PerspectiveFieldOfViewRH(TFloat fieldOfViewY, TFloat aspectRatio, TFloat zNear, TFloat zFar);
};
   
MO_NAMESPACE_END
      
//************************************************************
#endif // __MO_FG_GEOM_H__
