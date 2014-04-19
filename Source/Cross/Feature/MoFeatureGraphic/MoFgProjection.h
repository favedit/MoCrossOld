#ifndef __MO_FG_PROJECTION_H__
#define __MO_FG_PROJECTION_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#define MO_DISPLAY3d_CONER_COUNT       8
#define MO_DISPLAY3d_CONER_POINT_COUNT 24

MO_NAMESPACE_BEGIN

//============================================================
// <T>投影。</T>
//============================================================
class MO_FG_DECLARE FProjection : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FProjection, FInstance);
protected:
   // 大小
   SIntSize2 _size;
   // 角度
   TFloat _angle;
   // 夹角
   TFloat _fieldOfView;
   // 缩放
   TFloat _scale;
   // 近平面
   TFloat _znear;
   // 远平面
   TFloat _zfar;
   // 变换矩阵
   SFloatMatrix3d _matrix;
public:
   FProjection();
   MO_ABSTRACT ~FProjection();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得角度。</T>
   MO_INLINE TFloat Angle(){
      return _angle;
   }
   //------------------------------------------------------------
   // <T>设置角度。</T>
   MO_INLINE void SetAngle(TFloat angle){
      _angle = angle;
   }
   //------------------------------------------------------------
   // <T>获得近平面。</T>
   MO_INLINE TFloat Znear(){
      return _znear;
   }
   //------------------------------------------------------------
   // <T>设置近平面。</T>
   MO_INLINE void SetZnear(TFloat znear){
      _znear = znear;
   }
   //------------------------------------------------------------
   // <T>获得远平面。</T>
   MO_INLINE TFloat Zfar(){
      return _zfar;
   }
   //------------------------------------------------------------
   // <T>设置远平面。</T>
   MO_INLINE void SetZfar(TFloat zfar){
      _zfar = zfar;
   }
   //------------------------------------------------------------
   // <T>设置近平面和远平面。</T>
   MO_INLINE void SetZ(TFloat znear, TFloat zfar){
      _znear = znear;
      _zfar = zfar;
   }
   //------------------------------------------------------------
   // <T>获得变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
public:
   TFloat Distance();
   TFloat Radius();
public:
   TResult Assign(FProjection* pProjection);
   TResult Update();
};
//------------------------------------------------------------
typedef MO_FG_DECLARE FObjects<FProjection*> FProjectionCollection;

//============================================================
// <T>正交矩阵投影视角。</T>
//============================================================
class MO_FG_DECLARE FOrthoProjection : public FProjection
{
   MO_CLASS_DECLARE_INHERITS(FOrthoProjection, FProjection);
public:
   FOrthoProjection();
   MO_ABSTRACT ~FOrthoProjection();
};

//============================================================
// <T>透视矩阵投影视角。</T>
//============================================================
class MO_FG_DECLARE FPerspectiveProjection : public FProjection
{
   MO_CLASS_DECLARE_INHERITS(FPerspectiveProjection, FProjection);
public:
   FPerspectiveProjection();
   MO_ABSTRACT ~FPerspectiveProjection();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_PROJECTION_H__
