#ifndef __MO_MATH_H__
#define __MO_MATH_H__

#ifndef __MO_MT_COMMON_H__
#include "MoMtCommon.h"
#endif // __MO_MT_COMMON_H__

#ifndef __MO_MT_FLOAT_H__
#include "MoMtFloat.h"
#endif // __MO_MT_FLOAT_H__

#ifndef __MO_MT_GEOM_H__
#include "MoMtGeom.h"
#endif // __MO_MT_GEOM_H__

#ifndef __MO_MT_POINT_H__
#include "MoMtPoint.h"
#endif // __MO_MT_POINT_H__

#ifndef __MO_MT_VECTOR_H__
#include "MoMtVector.h"
#endif // __MO_MT_VECTOR_H__

#ifndef __MO_MT_QUATERNION_H__
#include "MoMtQuaternion.h"
#endif // __MO_MT_QUATERNION_H__

#ifndef __MO_MT_OUTLINE_H__
#include "MoMtOutline.h"
#endif // __MO_MT_OUTLINE_H__

#ifndef __MO_MT_MATRIX_H__
#include "MoMtMatrix.h"
#endif // __MO_MT_MATRIX_H__

#ifndef __MO_MT_FORMAT_H__
#include "MoMtFormat.h"
#endif // __MO_MT_FORMAT_H__

MO_NAMESPACE_BEGIN

MO_MT_DECLARE void MoMathInitialize();
MO_MT_DECLARE void MoMathRelease();

MO_NAMESPACE_END

#endif // __MO_MATH_H__
