#include "MoEfDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造控件对象。</T>
//============================================================
FUiControl::FUiControl(){
   _controlCd = EControlType_Unknown;
   _flags = 0;
   _optionEnable = ETrue;
   _optionVisible = ETrue;
   _dockCd = EControlDock_None;
   // 清空渲染信息
   MO_CLEAR(_pCanvas);
   MO_CLEAR(_pMaterial);
}

//============================================================
// <T>析构控件对象。</T>
//============================================================
FUiControl::~FUiControl(){
   MO_DELETE(_pMaterial);
   MO_DELETE(_pCanvas);
}

//============================================================
// <T>根据控件索引和类型获得控件对象。</T>
//
// @param index 控件索引
// @param controlCd 控件类型
// @return 对象
//============================================================
FUiControl* FUiControl::ControlGet(TInt index, EControlType controlCd){
   //if(_pChildren != NULL){
   //   FComponent* pComponent = _pChildren->Get(index);
   //   if(pComponent->IsObject(EComponent_Control)){
   //      FUiControl* pControl = (FUiControl*)pComponent;
   //      if(pControl->ControlCd() == controlCd){
   //         return pControl;
   //      }
   //   }
   //}
   return NULL;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FUiControl::OnUnserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   _flags = pInput->ReadInt32();
   _name.Unserialize(pInput);
   _label.UnserializeAutomatic(pInput);
   // 读取位置
   _optionEnable = TestFlag(EControlFlag_Enable);
   _optionVisible = TestFlag(EControlFlag_Visible);
   _dockCd = (EControlDock)pInput->ReadUint8();
   _location.x = pInput->ReadInt16();
   _location.y = pInput->ReadInt16();
   _size.width = pInput->ReadUint16();
   _size.height = pInput->ReadUint16();
   // 读取边距
   if(TestFlag(EControlFlag_Margin)){
      _margin.Unserialize8(pInput);
   }
   if(TestFlag(EControlFlag_Padding)){
      _padding.Unserialize8(pInput);
   }
   // 读取边框
   if(TestFlag(EControlFlag_BorderOuter)){
      _borderOuter.Unserialize(pInput);
   }
   if(TestFlag(EControlFlag_BorderInner)){
      _borderInner.Unserialize(pInput);
   }         
   // 读取前景
   _foreColor = pInput->ReadInt32();
   if(TestFlag(EControlFlag_LayerFore)){
      _forePicture.Unserialize(pInput);
   }
   // 读取后景
   _backColor = pInput->ReadInt32();
   if(TestFlag(EControlFlag_LayerBack)){
      _backPicture.Unserialize(pInput);
   }
   return ESuccess;
}

//============================================================
// <T>焦点测试处理。</T>
//
// @param pTester 测试信息
// @return 处理结果
//============================================================
TResult FUiControl::OnFocusTest(FFocusTester* pTester){
   SIntRectangle rectangle;
   CalculateRectangle(&rectangle);
   SIntPoint2& location = pTester->Location();
   if(rectangle.TestInRange(location.x, location.y)){
      pTester->SetStatusInRange(ETrue);
      pTester->SetStatusChildren(ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
//
// @param pInput 输入流
//============================================================
TResult FUiControl::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   TResult result = OnUnserialize(pInput);
   // 读取子控件
   TInt count = pInput->ReadInt16();
   for(TInt n = 0; n < count; n++){
      EControlType controlCd = (EControlType)pInput->ReadInt16();
      _className.Unserialize(pInput);
      FUiControl* pControl = RFaceManager::Instance().ControlConsole()->Alloc(controlCd, (TCharC*)_className);
      pControl->Unserialize(pInput);
      //ChildPush(pControl);
   }
   return result;
}

//============================================================
// <T>获得材质。</T>
//
// @return 材质
//============================================================
FMaterial* FUiControl::Material(){
   return _pMaterial;
}

//============================================================
// <T>计算范围。</T>
//
// @param pRectangle 范围
// @return 处理结果
//============================================================
TResult FUiControl::CalculateRectangle(SIntRectangle* pRectangle){
   TResult result = FDrawable::CalculateRectangle(pRectangle);
   // 存储位置
   if(result == ESuccess){
      _statusRectangle.Assign(*pRectangle);
   }
   return result;
}

//============================================================
// <T>获得客户区范围。</T>
//
// @param pRectangle 范围
// @return 处理结果
//============================================================
TResult FUiControl::CalculateClientRectangle(SIntRectangle* pRectangle){
   pRectangle->SetLocation(0, 0);
   pRectangle->SetSize((TInt)_size.width, (TInt)_size.height);
   _clientRectangle.Assign(*pRectangle);
   return ESuccess;
}

//============================================================
// <T>计算渲染信息。</T>
//
// @param renderable 渲染信息
// @return 处理结果
//============================================================
TResult FUiControl::CalculateRenderable(SRenderable& renderable){
   SRenderableItem& item = renderable.Alloc();
   // 计算坐标位置
   TInt level = 0;
   FComponent* pFind = this;
   item.location.Reset();
   while(pFind != NULL){
      //if(pFind->IsObject(EComponent_Drawable)){
      //   FDrawable* pDrawable = (FDrawable*)pFind;
      //   item.location.Add(pDrawable->Location());
      //}
      pFind = pFind->Parent();
      //level++;
   }
   // 设置属性
   item.size.Assign(_size);
   item.rotation.Assign(_rotation);
   item.coord.Assign(_coord);
   item.groundColor.Assign(_groundColor);
   // 设置层级
   if(_pMaterial != NULL){
      //_pMaterial->SetLevel(level);
   }
   return ESuccess;
}

//============================================================
// <T>获得画板。</T>
//
// @return 画板
//============================================================
FUiCanvas* FUiControl::Canvas(){
   // 检查对象
   if(_pCanvas != NULL){
      return _pCanvas;
   }
   // 创建对象
   _pCanvas = MO_CREATE(FUiCanvas);
   _pCanvas->Setup();
   _pCanvas->Resize((TInt)_size.width, (TInt)_size.height);
   // 创建材质
   //_pMaterial = MO_CREATE(FMaterial);
   //_pMaterial->SetEffectCd(ERenderEffect_2dTexture);
   //_pMaterial->SetOptionDepth(EFalse);
   //_pMaterial->SetOptionAlpha(ETrue);
   //_pMaterial->SetBlendSourceMode(ERenderBlendMode_SourceAlpha);
   //_pMaterial->SetBlendTargetMode(ERenderBlendMode_OneMinusSourceAlpha);
   //_pMaterial->TextureSet(_pCanvas->RenderTexture());
   //// 计算纹理坐标
   //SIntSize2& canvasSize = _pCanvas->Size();
   //_coord.x2 = _size.width / (TFloat)canvasSize.width;
   //_coord.y2 = _size.height / (TFloat)canvasSize.height;
   return _pCanvas;
}

//============================================================
// <T>释放内容。</T>
//
// @return 处理结果
//============================================================
TResult FUiControl::Free(){
   return ESuccess;
}

MO_NAMESPACE_END
