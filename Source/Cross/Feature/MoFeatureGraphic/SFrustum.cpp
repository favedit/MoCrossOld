#include "MoFgCamera.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造空间视截体。</T>
//============================================================
SFrustum::SFrustum(){
}

//============================================================
// <T>更新中心</T>
//============================================================
void SFrustum::UpdateCenter(){
   // 计算空间内位置
   TInt n = 0;
   minX = minY = minZ = MO_TP_FLOAT_MAX;
   maxX = maxY = maxZ = MO_TP_FLOAT_MIN;
   while(n < 24){
      TFloat x = coners[n++];
      if(x < minX){
         minX = x;
      }
      if(x > maxX){
         maxX = x;
      }
      TFloat y = coners[n++];
      if(y < minY){
         minY = y;
      }
      if(y > maxY){
         maxY = y;
      }
      TFloat z = coners[n++];
      if(z < minZ){
         minZ = z;
      }
      if(z > maxZ){
         maxZ = z;
      }
   }
   // 计算中心位置
   center.x = (minX + maxX) * 0.5f;
   center.y = (minY + maxY) * 0.5f;
   center.z = (minZ + maxZ) * 0.5f;
   radius = sqrt((minX - minY) * (minX - minY) + (minZ - maxX) * (minZ - maxX) + (maxY - maxZ) * (maxY - maxZ)) * 0.5f;
}

//============================================================
// <T>更新处理</T>
//
// @param pva:viewportAngle 视角角度
// @param pvw:viewportWidth 视角宽度
// @param pvh:viewportHeight 视角高度
// @param pvn:viewportNear 视角近平面
// @param pvf:viewportFar 视角远平面
// @param pfr:frontRate 前平面比率
// @param pbr:backRate 后平面比率
// @param matrix 矩阵
//============================================================
void SFrustum::Update(TFloat pva, TFloat pvw, TFloat pvh, TFloat pvn, TFloat pvf, TFloat pfr, TFloat pbr, SFloatMatrix3d& matrix){
   // 计算视角信息
   TFloat aspect = pvw / pvh;
   TFloat znear = -pvf * pbr;
   TFloat zfar = pvf * pfr;
   TFloat fov = tan(MO_GRAPHIC_DEGREE_RATE * pva * 0.5f);
   TFloat nearY = znear * fov;
   TFloat nearX = nearY * aspect;
   TFloat farY = zfar * fov;
   TFloat farX = farY * aspect;
   // 设置空间坐标
   TFloat points[MO_FRUSTUM_CONER_POINT_COUNT] = {
      -nearX,  nearY, znear,
       nearX,  nearY, znear,
       nearX, -nearY, znear,
      -nearX, -nearY, znear,
      -farX,   farY,  zfar,
       farX,   farY,  zfar,
       farX,  -farY,  zfar,
      -farX,  -farY,  zfar};
   // 设置转换矩阵
   conerMatrix.Assign(matrix);
   conerMatrix.Invert();
   conerMatrix.Transform(coners, points, MO_FRUSTUM_CONER_COUNT);
   // 计算空间内位置
   UpdateCenter();
}

MO_NAMESPACE_END
