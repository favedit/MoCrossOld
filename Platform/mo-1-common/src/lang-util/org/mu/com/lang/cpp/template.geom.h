#ifndef __MO_CM_STRUCT_H__
#define __MO_CM_STRUCT_H__

#ifndef _MATH_H
#include <math.h>
#endif // _MATH_H

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_STREAM_H__
#include "MoCmStream.h"
#endif // __MO_CM_STREAM_H__

MO_NAMESPACE_BEGIN

{range_int}

{range_float}

{range_double}

{size2_int}

{size2_float}

{size2_double}

{size3_int}

{size3_float}

{size3_double}

{point2_int}

{point2_float}

{point2_double}

{point3_int}

{point3_float}

{point3_double}

{point4_int}

{point4_float}

{point4_double}

{vector2_int}

{vector2_float}

{vector2_double}

{vector3_int}

{vector3_float}

{vector3_double}

{vector4_int}

{vector4_float}

{vector4_double}

{color4_float}

{quaternion_float}

{quaternion_double}

{rectangle_int}

{rectangle_float}

{rectangle_double}

{padding_int}

{padding_float}

{padding_double}

{matrix4x4_float}

{matrix4x4_double}

{matrix2d_float}

{matrix2d_double}

{matrix3d_float}

{matrix3d_double}

//============================================================
// <T>几何工具。</T>
//============================================================
class MO_CM_DECLARE RGeomUtil{
public:
   static TFloat Length3(TFloat x1, TFloat y1, TFloat z1, TFloat x2, TFloat y2, TFloat z2);
};

MO_NAMESPACE_END

#endif // __MO_CM_STRUCT_H__
