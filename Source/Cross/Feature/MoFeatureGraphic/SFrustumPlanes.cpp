#include "MoFgCamera.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造视截体平面类型。</T>
//============================================================
SFrustumPlanes::SFrustumPlanes(){
}

//============================================================
// <T>检查点是否在视截体内。</T>
//
// @param x 坐标X
// @param y 坐标Y
// @param z 坐标Z
//============================================================
TBool SFrustumPlanes::ContainsPoint(TFloat x, TFloat y, TFloat z){
   for(TInt n = 0; n < EFrustumPlane_Count; n++){
      if(planes[n].Dot(x, y, z) < 0){
         return EFalse;
      }
   }
   return ETrue;
}
      
//============================================================
// <T>检查立方体是否在视截体内。</T>
//
// @param centerX 中心点X坐标
// @param centerY 中心点Y坐标
// @param centerZ 中心点Z坐标
// @param size 大小
// @return 是否包含
//============================================================
TBool SFrustumPlanes::ContainsCube(TFloat centerX, TFloat centerY, TFloat centerZ, TFloat size){
   for(TInt n = 0; n < EFrustumPlane_Count; n++){
      if(planes[n].Dot(centerX - size, centerY - size, centerZ - size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX + size, centerY - size, centerZ - size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX - size, centerY + size, centerZ - size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX + size, centerY + size, centerZ - size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX - size, centerY - size, centerZ + size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX + size, centerY - size, centerZ + size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX - size, centerY + size, centerZ + size) >= 0){
         continue;
      }
      if(planes[n].Dot(centerX + size, centerY + size, centerZ + size) >= 0){
         continue;
      }
      return EFalse;
   }
   return ETrue;
}
      
//============================================================
// <T>检查长方体是否在视截体内。</T>
//
// @param cx:centerX 中心点X坐标
// @param cy:centerY 中心点Y坐标
// @param cz:centerZ 中心点Z坐标
// @param sx:sizeX X大小
// @param sy:sizeY Y大小
// @param sz:sizeZ Z大小
// @return 是否包含
//============================================================
TBool SFrustumPlanes::ContainsRectangle(TFloat cx, TFloat cy, TFloat cz, TFloat sx, TFloat sy, TFloat sz){
   for(TInt n = 0; n < EFrustumPlane_Count; n++){
      if(planes[n].Dot(cx - sx, cy - sy, cz - sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx + sx, cy - sy, cz - sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx - sx, cy + sy, cz - sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx + sx, cy + sy, cz - sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx - sx, cy - sy, cz + sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx + sx, cy - sy, cz + sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx - sx, cy + sy, cz + sz) >= 0){
         continue;
      }
      if(planes[n].Dot(cx + sx, cy + sy, cz + sz) >= 0){
         continue;
      }
      return EFalse;
   }
   return ETrue;
}
      
//============================================================
// <T>检查长方体是否在视截体内。</T>
//
// @param p:corners 顶点集合
// @return 是否包含
//============================================================
TBool SFrustumPlanes::ContainsCorners(TFloat* pCorners){
   for(TInt n = 0; n < EFrustumPlane_Count; n++){
      if(planes[n].Dot(pCorners[ 0], pCorners[ 1], pCorners[ 2]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[ 3], pCorners[ 4], pCorners[ 5]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[ 6], pCorners[ 7], pCorners[ 8]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[ 9], pCorners[10], pCorners[11]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[12], pCorners[13], pCorners[14]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[15], pCorners[16], pCorners[17]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[18], pCorners[19], pCorners[20]) >= 0){
         continue;
      }
      if(planes[n].Dot(pCorners[21], pCorners[22], pCorners[23]) >= 0){
         continue;
      }
      return EFalse;
   }
   return ETrue;
}
      
//============================================================
// <T>检查球体是否在视截体内。</T>
//
// @param px:centerX 中心点X坐标
// @param py:centerY 中心点Y坐标
// @param pz:centerZ 中心点Z坐标
// @param pr:radius 半径
// @return 是否包含
//============================================================
TBool SFrustumPlanes::ContainsSphere(TFloat px, TFloat py, TFloat pz, TFloat pr){
   for(TInt n = 0; n < EFrustumPlane_Count; n++){
      if(planes[n].Dot(px, py, pz) < -pr){
         return EFalse;
      }
   }
   return ETrue;
}
      
//============================================================
// <T>更新可视范围信息。</T>
//
// @param p 矩阵数据
//============================================================
void SFrustumPlanes::UpdateVision(TFloat* pVision){
   // 计算视截体的近平面
   SFloatPlane& nearPlane = planes[EFrustumPlane_Near];
   nearPlane.a = pVision[4 * 0 + 2];
   nearPlane.b = pVision[4 * 1 + 2];
   nearPlane.c = pVision[4 * 2 + 2];
   nearPlane.d = pVision[4 * 3 + 2];
   nearPlane.Normalize();
   // 计算视截体的远平面
   SFloatPlane& farPlane = planes[EFrustumPlane_Far];
   farPlane.a = pVision[4 * 0 + 3] - pVision[4 * 0 + 2];
   farPlane.b = pVision[4 * 1 + 3] - pVision[4 * 1 + 2];
   farPlane.c = pVision[4 * 2 + 3] - pVision[4 * 2 + 2];
   farPlane.d = pVision[4 * 3 + 3] - pVision[4 * 3 + 2];
   farPlane.Normalize();
   // 计算视截体的左平面
   SFloatPlane& leftPlane = planes[EFrustumPlane_Left];
   leftPlane.a = pVision[4 * 0 + 3] + pVision[4 * 0 + 0];
   leftPlane.b = pVision[4 * 1 + 3] + pVision[4 * 1 + 0];
   leftPlane.c = pVision[4 * 2 + 3] + pVision[4 * 2 + 0];
   leftPlane.d = pVision[4 * 3 + 3] + pVision[4 * 3 + 0];
   leftPlane.Normalize();
   // 计算视截体的右平面
   SFloatPlane& rightPlane = planes[EFrustumPlane_Right];
   rightPlane.a = pVision[4 * 0 + 3] - pVision[4 * 0 + 0];
   rightPlane.b = pVision[4 * 1 + 3] - pVision[4 * 1 + 0];
   rightPlane.c = pVision[4 * 2 + 3] - pVision[4 * 2 + 0];
   rightPlane.d = pVision[4 * 3 + 3] - pVision[4 * 3 + 0];
   rightPlane.Normalize();
   // 计算视截体的顶平面
   SFloatPlane& topPlane = planes[EFrustumPlane_Top];
   topPlane.a = pVision[4 * 0 + 3] - pVision[4 * 0 + 1];
   topPlane.b = pVision[4 * 1 + 3] - pVision[4 * 1 + 1];
   topPlane.c = pVision[4 * 2 + 3] - pVision[4 * 2 + 1];
   topPlane.d = pVision[4 * 3 + 3] - pVision[4 * 3 + 1];
   topPlane.Normalize();
   // 计算视截体的底平面
   SFloatPlane& bottomPlane = planes[EFrustumPlane_Bottom];
   bottomPlane.a = pVision[4 * 0 + 3] + pVision[4 * 0 + 1];
   bottomPlane.b = pVision[4 * 1 + 3] + pVision[4 * 1 + 1];
   bottomPlane.c = pVision[4 * 2 + 3] + pVision[4 * 2 + 1];
   bottomPlane.d = pVision[4 * 3 + 3] + pVision[4 * 3 + 1];
   bottomPlane.Normalize();
}

MO_NAMESPACE_END
