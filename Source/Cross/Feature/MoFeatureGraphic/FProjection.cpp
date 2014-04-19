#include "MoFgProjection.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FProjection, FInstance);

//============================================================
// <T>构造投影。</T>
//============================================================
FProjection::FProjection(){
   _size.Set(800, 600);
   _angle = 30.0f;
   _fieldOfView = MO_GRAPHIC_DEGREE_RATE * _angle;
   _znear = 0.1f;
   _zfar = 1000.0f;
}

//============================================================
// <T>析构投影。</T>
//============================================================
FProjection::~FProjection(){
}

//============================================================
// <T>获得截面距离。</T>
//
// @return 截面距离
//============================================================
TFloat FProjection::Distance(){
   return _zfar - _znear;
}
      
//============================================================
// <T>获得角度。</T>
//
// @return 角度
//============================================================
TFloat FProjection::Radius(){
   TFloat aspect = (TFloat)_size.width / (TFloat)_size.height;
   TFloat fov = tan(MO_GRAPHIC_DEGREE_RATE * _angle * 0.5f);
   TFloat farY = _zfar * fov;
   TFloat farX = farY * aspect;
   return sqrt((farX * farX) + (farY * farY) + (_zfar * _zfar));
}
      
//============================================================
// <T>接收数据。</T>
//
// @param pProjection 数据
//============================================================
TResult FProjection::Assign(FProjection* pProjection){
   // 检查参数
   MO_CHECK(pProjection, return ENull);
   // 接收数据
   _size.Assign(pProjection->_size);
   _angle = pProjection->_angle;
   _fieldOfView = pProjection->_fieldOfView;
   _znear = pProjection->_znear;
   _zfar = pProjection->_zfar;
   // 更新数据
   return Update();
}
      
//============================================================
// <T>更新矩阵。</T>
//============================================================
TResult FProjection::Update(){
   _fieldOfView = MO_GRAPHIC_DEGREE_RATE * _angle;
   SPerspectiveMatrix3d matrix;
   matrix.PerspectiveFieldOfViewLH(_fieldOfView, (TFloat)_size.width / (TFloat)_size.height, _znear, _zfar);
   _matrix.Assign(matrix);
   return ESuccess;
}

MO_NAMESPACE_END
