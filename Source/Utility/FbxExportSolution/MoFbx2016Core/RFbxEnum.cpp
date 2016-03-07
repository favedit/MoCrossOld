#include "MoFbxParser.h"

MO_NAMESPACE_BEGIN;
MO_NAMESPACE_USING(FBXSDK_NAMESPACE);

//============================================================
// <T>²éÕÒ¶¥µã×ø±ê¡£</T>
//============================================================
TCharC* RFbxEnum::ParseName(FbxNodeAttribute::EType typeCd){
   switch(typeCd){
      case FbxNodeAttribute::eUnknown:
         return TC("Unknown");
      case FbxNodeAttribute::eNull:
         return TC("Null");
      case FbxNodeAttribute::eMarker:
         return TC("Marker");
      case FbxNodeAttribute::eSkeleton:
         return TC("Skeleton");
      case FbxNodeAttribute::eMesh:
         return TC("Mesh");
      case FbxNodeAttribute::eNurbs:
         return TC("Nurbs");
      case FbxNodeAttribute::ePatch:
         return TC("Patch");
      case FbxNodeAttribute::eCamera:
         return TC("Camera");
      case FbxNodeAttribute::eCameraStereo:
         return TC("CameraStereo");
      case FbxNodeAttribute::eCameraSwitcher:
         return TC("CameraSwitcher");
      case FbxNodeAttribute::eLight:
         return TC("Light");
      case FbxNodeAttribute::eOpticalReference:
         return TC("OpticalReference");
      case FbxNodeAttribute::eOpticalMarker:
         return TC("OpticalMarker");
      case FbxNodeAttribute::eNurbsCurve:
         return TC("NurbsCurve");
      case FbxNodeAttribute::eTrimNurbsSurface:
         return TC("TrimNurbsSurface");
      case FbxNodeAttribute::eBoundary:
         return TC("Boundary");
      case FbxNodeAttribute::eNurbsSurface:
         return TC("NurbsSurface");
      case FbxNodeAttribute::eShape:
         return TC("Shape");
      case FbxNodeAttribute::eLODGroup:
         return TC("LODGroup");
      case FbxNodeAttribute::eSubDiv:
         return TC("SubDiv");
      case FbxNodeAttribute::eCachedEffect:
         return TC("CachedEffect");
      case FbxNodeAttribute::eLine:
         return TC("Line");
      default:
         MO_STATIC_FATAL(TC("Invalid type. (type_cd=%d)"), typeCd);
   }
   return TC("Unknown");
}

MO_NAMESPACE_END;
