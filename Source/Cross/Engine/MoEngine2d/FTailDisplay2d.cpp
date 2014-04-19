#include "MoE2Display.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造跟踪位图对象。</T>
//============================================================
FTailDisplay2d::FTailDisplay2d(){
   _optionTail = EFalse;
   MO_CLEAR(_pTailController);
}

//============================================================
// <T>析构跟踪位图对象。</T>
//============================================================
FTailDisplay2d::~FTailDisplay2d(){
   MO_DELETE(_pTailController);
}

//============================================================
// <T>设置是否使用跟踪。</T>
//
// @param value 是否使用
// @return 处理结果
//============================================================
TResult FTailDisplay2d::SetOptionTail(TBool value){
   _optionTail = value;
   if(_optionTail){
      if(_pTailController == NULL){
         _pTailController = MO_CREATE(FTailController);
      }
      _pTailController->Reset();
   }
   return ESuccess;
}

//============================================================
// <T>计算渲染信息。</T>
//
// @param renderable 渲染信息
// @return 处理结果
//============================================================
TResult FTailDisplay2d::CalculateRenderable(SRenderable& renderable){
   //if(_optionTail){
   //   TFsTailInfoVector& infos = _pTailController->Infos();
   //   TInt count = infos.Count();
   //   TFloat width = _size.width;
   //   TFloat height = _size.height;
   //   for(TInt n = 0; n < count; n++){
   //      STailInfo& info = infos.Get(n);
   //      SRenderableItem& item = renderable.Alloc();
   //      // 设置坐标
   //      item.location.Assign(info.location);
   //      // 设置尺寸
   //      //item.size.Assign(info.size);
   //      item.size.Set(width, height);
   //      width *= 0.95f;
   //      height *= 0.95f;
   //      // 设置方向
   //      item.rotation.Assign(info.rotation);
   //      // 设置纹理
   //      //item.coord.Assign(info.coord);
   //      item.coord.Assign(_coord);
   //      // 设置背景颜色
   //      //item.groundColor.Assign(info.groundColor);
   //      item.groundColor.Assign(_groundColor);
   //      // 设置内容
   //      item.matrix.Identity();
   //      item.matrix.Translate(-_gravityCenter.x, -_gravityCenter.y, -_gravityCenter.z);
   //      item.matrix.Rotation(info.rotation.x, info.rotation.y, info.rotation.z);
   //   }
   //}else{
   //   SRenderableItem& item = renderable.Alloc();
   //   // 设置坐标
   //   item.location.Assign(_location);
   //   // 设置尺寸
   //   item.size.Assign(_size);
   //   // 设置方向
   //   item.rotation.Assign(_rotation);
   //   // 设置纹理
   //   item.coord.Assign(_coord);
   //   // 设置背景颜色
   //   item.groundColor.Assign(_groundColor);
   //   // 设置内容
   //   item.matrix.Identity();
   //   item.matrix.Translate(-_gravityCenter.x, -_gravityCenter.y, -_gravityCenter.z);
   //   item.matrix.Rotation(_rotation.x, _rotation.y, _rotation.z);
   //   //renderable.matrix.UpdateForce();
   //}
   return ESuccess;
}

//============================================================
// <T>粒子处理。</T>
//
// @param data 粒子数据
// @return 处理结果
//============================================================
TResult FTailDisplay2d::DoParticle(SParticleData& data){
   //// 移动处理
   //if(data.actionCd == EParticleAction_Move){
   //   if(_optionTail){
   //      STailInfo info;
   //      info.location.Assign(data.location);
   //      info.size.Assign(_size);
   //      info.rotation.Assign(data.rotation);
   //      _pTailController->Push(info);
   //   }else{
   //      _location.Assign(data.location);
   //      // _size.Assign(data.size);
   //      _rotation.Assign(data.rotation);
   //   }
   //}
   //// 释放处理
   //if(data.actionCd == EParticleAction_Free){
   //   Free();
   //}
   return ESuccess;
}

MO_NAMESPACE_END
