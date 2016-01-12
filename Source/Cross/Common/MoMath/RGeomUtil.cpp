#include "MoMtGeom.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>«Ûø’º‰2µ„æ‡¿Î</T>
//============================================================
TFloat RGeomUtil::Length3(TFloat x1, TFloat y1, TFloat z1, TFloat x2, TFloat y2, TFloat z2){
   TFloat cx = x2 - x1;
   TFloat cy = y2 - y1;
   TFloat cz = z2 - z1;
   TFloat r2 = (cx * cx) + (cy * cy) + (cz * cz);
   return (TFloat)sqrt(r2);
}

MO_NAMESPACE_END
