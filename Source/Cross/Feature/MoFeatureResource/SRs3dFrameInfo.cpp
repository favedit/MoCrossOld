#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源3D帧信息。</T>
//============================================================
SRs3dFrameInfo::SRs3dFrameInfo(){
   tick = 0;
   playRate = 1.0f;
   MO_CLEAR(currentPtr);
   MO_CLEAR(nextPtr);
   rate = 1.0f;
   alpha = 1.0f;
}

//============================================================
// <T>更新处理</T>
//
// @return 处理结果
//============================================================
TResult SRs3dFrameInfo::Update(){
   // 检查参数
   if(currentPtr == NULL){
      return EFailure;
   }
   if(nextPtr == NULL){
      return EFailure;
   }
   // 获得矩阵
   SFloatMatrix3d& matrixCurrent = currentPtr->Matrix();
   // 计算插值矩阵
   if(rate != 0){
      // 计算中间矩阵
      //SFloatMatrix3d& matrixNext = nextPtr->Matrix();
      //matrix.tx = matrixCurrent.tx + (matrixNext.tx - matrixCurrent.tx) * rate;
      //matrix.ty = matrixCurrent.ty + (matrixNext.ty - matrixCurrent.ty) * rate;
      //matrix.tz = matrixCurrent.tz + (matrixNext.tz - matrixCurrent.tz) * rate;
      //matrix.rx = matrixCurrent.rx + (matrixNext.rx - matrixCurrent.rx) * rate;
      //matrix.ry = matrixCurrent.ry + (matrixNext.ry - matrixCurrent.ry) * rate;
      //matrix.rz = matrixCurrent.rz + (matrixNext.rz - matrixCurrent.rz) * rate;
      //matrix.sx = matrixCurrent.sx + (matrixNext.sx - matrixCurrent.sx) * rate;
      //matrix.sy = matrixCurrent.sy + (matrixNext.sy - matrixCurrent.sy) * rate;
      //matrix.sz = matrixCurrent.sz + (matrixNext.sz - matrixCurrent.sz) * rate;
      //matrix.rx = matrix.rx / 180.0f * MO_PI_FLOAT;
      //matrix.ry = matrix.ry / 180.0f * MO_PI_FLOAT;
      //matrix.rz = matrix.rz / 180.0f * MO_PI_FLOAT;
      //matrix.UpdateForce();
      matrix.Assign(matrixCurrent);
      //matrix.interpolateTo(nm, rate);
      //matrix.parse();
      // TODO：原生函数计算插值，缩放始终为1
      //matrix.sx = cm.sx + (nm.sx - cm.sx) * rate;
      //matrix.sy = cm.sy + (nm.sy - cm.sy) * rate;
      //matrix.sz = cm.sz + (nm.sz - cm.sz) * rate;
      //matrix.updateForce();
      // 计算插值透明度
      //alpha = (next.alpha - current.alpha) * rate + current.alpha;
   }else{
      // 计算插值透明度
      matrix.Assign(matrixCurrent);
      //alpha = currentPtr->Alpha();
   }
   return ESuccess;
}

MO_NAMESPACE_END
