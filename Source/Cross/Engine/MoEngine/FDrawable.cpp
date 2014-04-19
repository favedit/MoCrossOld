#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FDrawable, FComponent);

//============================================================
// <T>构造可绘制对象。</T>
//============================================================
FDrawable::FDrawable(){
   _objectCd |= EComponent_Drawable;
   _statusVisible = ETrue;
   _statusDirty = ETrue;
   _groundColor.Set(1.0f, 1.0f, 1.0f, 1.0f);
   _coord.Set(0.0f, 0.0f, 1.0f, 1.0f);
}

//============================================================
// <T>析构可绘制对象。</T>
//============================================================
FDrawable::~FDrawable(){
}

//============================================================
// <T>转换类型。</T>
//
// @param componentCd 类型
// @return 对象
//============================================================
TAny* FDrawable::Convert(EComponent componentCd){
   if(componentCd == EComponent_Drawable){
      return (FDrawable*)this;
   }
   return NULL;
}

//============================================================
// <T>测试是否可以绘制。</T>
//
// @return 是否可以绘制
//============================================================
TBool FDrawable::TestDrawable(){
   // 检查对象类型
   if(!IsObject(EComponent_Renderable)){
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>绘制处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::OnPaint(){
   return ESuccess;
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FDrawable::OnFocusTest(FFocusTester* pTester){
   SIntRectangle rectangle;
   CalculateRectangle(&rectangle);
   SIntPoint2& location = pTester->Location();
   if(rectangle.TestInRange(location.x, location.y)){
      pTester->SetStatusInRange(ETrue);
      //pTester->SetStatusChildren(_pChildren != NULL);
   }
   return ESuccess;
}

//============================================================
// <T>计算范围。</T>
//
// @param pRectangle 范围
// @return 处理结果
//============================================================
TResult FDrawable::CalculateRectangle(SIntRectangle* pRectangle){
   MO_CHECK(pRectangle, return ENull);
   // 计算坐标位置
   FComponent* pFind = this;
   pRectangle->Reset();
   while(pFind != NULL){
      if(pFind->IsObject(EComponent_Drawable)){
         FDrawable* pDrawable = (FDrawable*)pFind;
         SFloatPoint3& position = pDrawable->Location();
         pRectangle->left += (TInt)position.x;
         pRectangle->top += (TInt)position.y;
      }
      pFind = pFind->Parent();
   }
   pRectangle->SetSize((TInt)_size.width, (TInt)_size.height);
   return ESuccess;
}

//============================================================
// <T>过滤渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FDrawable::FilterRegion(FRenderRegion* pRegion){
   MO_CHECK(pRegion, return ENull);
   return ESuccess;
}

//============================================================
// <T>更新自己变换矩阵。</T>
//============================================================
void FDrawable::UpdateSelftMatrix(SDrawableContext* pContext){
   // 追加变换矩阵
   _matrix.Assign(_matrixTransform);
   // 追加模型矩阵
   _matrix.Append(_matrixModel);
   // 追加父矩阵
   if(_pParent != NULL){
      FDrawable* pDrawable = (FDrawable*)_pParent;
      _matrix.Append(pDrawable->Matrix());
   }
}

//============================================================
// <T>更新父级变换矩阵。</T>
//============================================================
void FDrawable::UpdateParentMatrix(){
   if(_pParent != NULL){
      FDrawable* pParent = ParentDrawable();
      while(pParent != NULL){
         _matrix.Append(pParent->Matrix());
         pParent = pParent->ParentDrawable();
      }
   }
}

//============================================================
// <T>更新当前变换矩阵。</T>
//============================================================
void FDrawable::UpdateMatrix(){
   // 追加自己信息
   _matrix.Assign(_matrixModel);
   // 追加父信息
   FDrawable* pParent = ParentDrawable();
   while(pParent != NULL){
      _matrix.Append(pParent->Matrix());
      pParent = pParent->ParentDrawable();
   }
}

//============================================================
// <T>更新当前所有变换矩阵。</T>
//============================================================
void FDrawable::UpdateAllMatrix(SDrawableContext* pContext){
   // 更新自矩阵
   UpdateSelftMatrix(pContext);
}
      
//============================================================
// <T>脏更新处理。</T>
//
// @param p:context 环境
//============================================================
void FDrawable::UpdateDirty(SDrawableContext* pContext){
   //dirty = false;
   // _logger.debug("updateDirty", "Update dirty. (name={1})", name);
}

//============================================================
// <T>设置可见性。</T>
//
// @param visible 可见性
// @return 处理结果
//============================================================
TResult FDrawable::SetVisible(TBool visible){
   _statusVisible = visible;
   return ESuccess;
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FDrawable::FocusTest(FFocusTester* pTester){
   MO_CHECK(pTester, return ENull);
   // 检查可见状态
   if(!_statusVisible){
      return EFalse;
   }
   // 测试处理
   pTester->TestReset();
   TResult result = OnFocusTest(pTester);
   TBool inRange = pTester->StatusInRange();
   TBool children = pTester->StatusChildren();
   // 测试子组件集合
   if(children){
      //TInt childCount = ChildCount();
      //for(TInt n = 0; n < childCount; n++){
      //   FComponent* pComponent = _pChildren->Get(n);
      //   if(pComponent->IsObject(EComponent_Drawable)){
      //      FRenderable* pDrawable = (FRenderable*)pComponent;
      //      pDrawable->FocusTest(pTester);
      //   }
      //}
   }
   // 增加到集合内
   if(inRange){
      pTester->Push(this);
   }
   return ESuccess;
}

//============================================================
// <T>脏处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::Dirty(TBool force){
   _statusDirty = ETrue;
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::Paint(){
   TResult result = ESuccess;
   if(_statusVisible){
      if(_statusDirty){
         result = OnPaint();
         _statusDirty = EFalse;
      }
   }
   return result;
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::Update(){
   TResult result = FComponent::Update();
   // 如果脏了则绘制一次
   if(_statusDirty){
      Paint();
   }
   return result;
}


//============================================================
// <T>功能前置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::ProcessBefore(SProcessContext* pContext){
   TResult result = FComponent::ProcessBefore(pContext);
   if(_statusDirty){
      result = Paint();
   }
   return result;
}

//============================================================
// <T>逻辑后置处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::ProcessAfter(SProcessContext* pContext){
   TResult result = FComponent::ProcessAfter(pContext);
   return result;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::Resume(){
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FDrawable::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
