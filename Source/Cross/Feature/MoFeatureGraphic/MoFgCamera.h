#ifndef __MO_FG_CAMERA_H__
#define __MO_FG_CAMERA_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_PROJECTION_H__
#include "MoFgProjection.h"
#endif // __MO_FG_PROJECTION_H__

#ifndef __MO_FG_VIEWPORT_H__
#include "MoFgViewport.h"
#endif // __MO_FG_VIEWPORT_H__

#define MO_FRUSTUM_CONER_COUNT       8
#define MO_FRUSTUM_CONER_POINT_COUNT 24

MO_NAMESPACE_BEGIN

//============================================================
// <T>视截体平面类型。</T>
//============================================================
enum EFrustumPlane{
   // 近平面
   EFrustumPlane_Near = 0,
   // 远平面
   EFrustumPlane_Far = 1,
   // 左平面
   EFrustumPlane_Left = 2,
   // 右平面
   EFrustumPlane_Right = 3,
   // 上平面
   EFrustumPlane_Top = 4,
   // 下平面
   EFrustumPlane_Bottom = 5,
   // 平面总数
   EFrustumPlane_Count = 6,
};

//============================================================
//<T>浮点数平面。</T>
//============================================================
class MO_FG_DECLARE SFloatPlane{
public:
	// 浮点类型a
   TFloat a;
	// 浮点类型b
   TFloat b;
	// 浮点类型c
   TFloat c;
	// 浮点类型d
   TFloat d;
public:
   //------------------------------------------------------------
   //<T>构造浮点数平面。</T>
   MO_INLINE SFloatPlane(TFloat valueA = 0.0f, TFloat valueB = 0.0f, TFloat valueC = 0.0f, TFloat valueD = 0.0f){
      a = valueA;
      b = valueB;
      c = valueC;
      d = valueD;
   }
public:
   //------------------------------------------------------------
   //<T>单位标准化处理。</T>
   MO_INLINE void Normalize(){
      TFloat r = 1 / sqrt((a * a) + (b * b) + (c * c));
      a *= r;
      b *= r;
      c *= r;
      d *= r;
   }
   //------------------------------------------------------------
   //<T>点乘处理。</T>
   MO_INLINE TFloat Dot(TFloat x, TFloat y, TFloat z){
      return (x * a) + (y * b) + (z * c ) + d;
   }
public:
   TCharC* ToString(TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>视截体平面集合。</T>
//============================================================
class MO_FG_DECLARE SFrustumPlanes{
public:
   SFloatPlane planes[EFrustumPlane_Count];
public:
   SFrustumPlanes();
public:
   TBool ContainsPoint(TFloat x, TFloat y, TFloat z);
   TBool ContainsCube(TFloat centerX, TFloat centerY, TFloat centerZ, TFloat size);
   TBool ContainsRectangle(TFloat cx, TFloat cy, TFloat cz, TFloat sx, TFloat sy, TFloat sz);
   TBool ContainsCorners(TFloat* pCorners);
   TBool ContainsSphere(TFloat px, TFloat py, TFloat pz, TFloat pr);
public:
   void UpdateVision(TFloat* pVision);
};

//============================================================
// <T>空间视截体。</T>
//============================================================
class MO_FG_DECLARE SFrustum{
public:
   // 转换矩阵
   SFloatMatrix3d conerMatrix;
   // 中心点
   SFloatPoint3 center;
   // 半径
   TFloat radius;
   // 最小X坐标
   TFloat minX;
   // 最大X坐标
   TFloat maxX;
   // 最小Y坐标
   TFloat minY;
   // 最大Y坐标
   TFloat maxY;
   // 最小Z坐标
   TFloat minZ;
   // 最大Z坐标
   TFloat maxZ;
   // 顶点集合
   TFloat coners[MO_FRUSTUM_CONER_POINT_COUNT];
public:
   SFrustum();
public:
   void UpdateCenter();
   void Update(TFloat pva, TFloat pvw, TFloat pvh, TFloat pvn, TFloat pvf, TFloat pfr, TFloat pbr, SFloatMatrix3d& matrix);
};

//============================================================
// <T>相机。</T>
//============================================================
class MO_FG_DECLARE FCamera : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FCamera, FInstance);
protected:
   // 类型名称
   TFsName _typeName;
   // 变换矩阵
   SFloatMatrix3d _matrix;
   // 相机位置
   SFloatPoint3 _position;
   // 相机方向
   SFloatVector3 _direction;
   // 中心前位置
   TFloat _centerFront;
   // 中心后位置
   TFloat _centerBack;
   // 近焦平面位置
   TFloat _focalNear;
   // 远焦平面位置
   TFloat _focalFar;
   // 可见视截体
   SFrustumPlanes _planes;
   // 视截体
   SFrustum _frustum;
   // 上轴线
   SFloatVector3 _axisUp;
   // X轴线
   SFloatVector3 _axisX;
   // Y轴线
   SFloatVector3 _axisY;
   // Z轴线
   SFloatVector3 _axisZ;
   // 投影变换
   GPtr<FProjection> _projection;
   // 渲染目标
   GPtr<FViewport> _viewport;
public:
   FCamera();
   MO_ABSTRACT ~FCamera();
public:
   //------------------------------------------------------------
   // <T>获得变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得相机位置。</T>
   MO_INLINE SFloatPoint3& Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>获得相机方向。</T>
   MO_INLINE SFloatVector3& Direction(){
      return _direction;
   }
   //------------------------------------------------------------
   // <T>获得投影变换。</T>
   MO_INLINE FProjection* Projection(){
      return _projection;
   }
   //------------------------------------------------------------
   // <T>设置投影变换。</T>
   MO_INLINE void SetProjection(FProjection* pProjection){
      _projection = pProjection;
   }
   //------------------------------------------------------------
   // <T>获得变换矩阵。</T>
   MO_INLINE FViewport* Viewport(){
      return _viewport;
   }
   //------------------------------------------------------------
   // <T>设置变换矩阵。</T>
   MO_INLINE void SetViewport(FViewport* pViewport){
      _viewport = pViewport;
   }
public:
   void DoWalk(TFloat value);
   void DoStrafe(TFloat value);
   void DoFly(TFloat value);
   void DoYaw(TFloat angle);
   void DoPitch(TFloat angle);
   void LookAt(TFloat x, TFloat y, TFloat z);
public:
   TResult UpdateFrustum();
   TResult Update();
public:
   void Transform();
   TCharC* ToString();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FCamera*> FCameraCollection;

//============================================================
// <T>正交矩阵相机。</T>
//============================================================
class MO_FG_DECLARE FOrthoCamera : public FCamera
{
   MO_CLASS_DECLARE_INHERITS(FOrthoCamera, FCamera);
public:
   FOrthoCamera();
   MO_ABSTRACT ~FOrthoCamera();
};

//============================================================
// <T>透视矩阵相机。</T>
//============================================================
class MO_FG_DECLARE FPerspectiveCamera : public FCamera
{
   MO_CLASS_DECLARE_INHERITS(FPerspectiveCamera, FCamera);
public:
   FPerspectiveCamera();
   MO_ABSTRACT ~FPerspectiveCamera();
public:
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_CAMERA_H__
