#include "MoFgGeom.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造3D投影矩阵。</T>
//============================================================
SPerspectiveMatrix3d::SPerspectiveMatrix3d(){
}

//============================================================
// <T>计算左手矩阵。</T>
//============================================================
void SPerspectiveMatrix3d::PerspectiveLH(TFloat width, TFloat height, TFloat zNear, TFloat zFar){
   // 填充行1数据
   data[0][0] = 2.0f * zNear / width;
   data[0][1] = 0.0f;
   data[0][2] = 0.0f;
   data[0][3] = 0.0f;
   // 填充行2数据
   data[1][0] = 0.0f;
   data[1][1] = 2.0f * zNear / height;
   data[1][2] = 0.0f;
   data[1][3] = 0.0f;
   // 填充行3数据
   data[2][0] = 0.0f;
   data[2][1] = 0.0f;
   data[2][2] = zFar / (zFar - zNear);
   data[2][3] = 1.0f;
   // 填充行4数据
   data[3][0] = 0.0f;
   data[3][1] = 0.0f;
   data[3][2] = (zNear * zFar) / (zNear - zFar);
   data[3][3] = 0.0f;
}

//============================================================
// <T>V计算右手矩阵。</T>
//============================================================
void SPerspectiveMatrix3d::PerspectiveRH(TFloat width, TFloat height, TFloat zNear, TFloat zFar){
   // 填充行1数据
   data[0][0] = 2.0f * zNear / width;
   data[0][1] = 0.0f;
   data[0][2] = 0.0f;
   data[0][3] = 0.0f;
   // 填充行2数据
   data[1][0] = 0.0f;
   data[1][1] = 2.0f * zNear / height;
   data[1][2] = 0.0f;
   data[1][3] = 0.0f;
   // 填充行3数据
   data[2][0] = 0.0f;
   data[2][1] = 0.0f;
   data[2][2] = zFar / (zNear - zFar);
   data[2][3] = 1.0f;
   // 填充行4数据
   data[3][0] = 0.0f;
   data[3][1] = 0.0f;
   data[3][2] = (zNear * zFar) / (zNear - zFar);
   data[3][3] = 0.0f;
}
   
//============================================================
// <T>根据FOV计算左手矩阵。</T>
//============================================================
void SPerspectiveMatrix3d::PerspectiveFieldOfViewLH(TFloat fieldOfViewY, TFloat aspectRatio, TFloat zNear, TFloat zFar){
	TFloat yScale = 1.0f / tan(fieldOfViewY * 0.5f);
	TFloat xScale = yScale / aspectRatio;
   // 填充行1数据
   data[0][0] = xScale;
   data[0][1] = 0.0f;
   data[0][2] = 0.0f;
   data[0][3] = 0.0f;
   // 填充行2数据
   data[1][0] = 0.0f;
   data[1][1] = yScale;
   data[1][2] = 0.0f;
   data[1][3] = 0.0f;
   // 填充行3数据
   data[2][0] = 0.0f;
   data[2][1] = 0.0f;
   data[2][2] = zFar / (zFar - zNear);
   data[2][3] = 1.0f;
   // 填充行4数据
   data[3][0] = 0.0f;
   data[3][1] = 0.0f;
   data[3][2] = (zNear * zFar) / (zNear - zFar);
   data[3][3] = 0.0f;
}

//============================================================
// <T>根据FOV计算右手矩阵。</T>
//============================================================
void SPerspectiveMatrix3d::PerspectiveFieldOfViewRH(TFloat fieldOfViewY, TFloat aspectRatio, TFloat zNear, TFloat zFar){
	TFloat yScale = 1.0f / tan(fieldOfViewY * 0.5f);
	TFloat xScale = yScale / aspectRatio;
   // 填充行1数据
   data[0][0] = xScale;
   data[0][1] = 0.0f;
   data[0][2] = 0.0f;
   data[0][3] = 0.0f;
   // 填充行2数据
   data[1][0] = 0.0f;
   data[1][1] = yScale;
   data[1][2] = 0.0f;
   data[1][3] = 0.0f;
   // 填充行3数据
   data[2][0] = 0.0f;
   data[2][1] = 0.0f;
   data[2][2] = zFar / (zNear - zFar);
   data[2][3] = 1.0f;
   // 填充行4数据
   data[3][0] = 0.0f;
   data[3][1] = 0.0f;
   data[3][2] = (zNear * zFar) / (zFar - zNear);
   data[3][3] = 0.0f;
}

MO_NAMESPACE_END
