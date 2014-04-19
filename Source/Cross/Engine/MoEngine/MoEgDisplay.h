#ifndef __MO_EG_DISPLAY_H__
#define __MO_EG_DISPLAY_H__
//************************************************************

#ifndef __MO_EG_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_EG_COMMON_H__

#ifndef __MO_EG_CORE_H__
#include "MoEgCore.h"
#endif // __MO_EG_CORE_H__

MO_NAMESPACE_BEGIN

//============================================================
// 接口定义
class IHoverable;
class IFocusable;
class IDragable;
// 类型定义
class FComponent;
typedef MO_EG_DECLARE FObjects<FComponent*> FComponentCollection;
class FDrawable;
typedef MO_EG_DECLARE FObjects<FDrawable*> FDrawableCollection;
class FRenderable2;
class FDisplay;
typedef MO_EG_DECLARE FObjects<FDisplay*> FDisplayCollection;
typedef MO_EG_DECLARE FList<FDisplay*> FDisplayList;
class FStageFrame;
class FStage;
class FParticleController;
class FFocusTester;

//==========================================================
// <T>组件类型</T>
//==========================================================
enum EComponent{
   // 对象
   EComponent_Object = 0x0001,
   // 组件
   EComponent_Component = 0x0002,
   // 可绘制对象
   EComponent_Drawable = 0x0004,
   // 可绘制对象层
   EComponent_DrawableLayer = 0x0008,
   // 渲染对象
   EComponent_Renderable = 0x0010,
   // 可获得鼠标对象
   EComponent_Mouseable = 0x0020,
   // 可获得热点对象
   EComponent_Hoverable = 0x0040,
   // 可获得焦点对象
   EComponent_Focusable = 0x0080,
   // 可移动对象
   EComponent_Move = 0x0100,
   // 显示舞台
   EComponent_Stage = 0x0200,
   // 显示对象
   EComponent_Display = 0x0400,
   // 显示容器
   EComponent_DisplayContainer = 0x0800,
   // 空间对象
   EComponent_Spatial = 0x1000,
   // 控件对象
   EComponent_Control = 0x2000,
   // 控件容器
   EComponent_ControlContainer = 0x4000,
   // 顶层容器
   EComponent_Frame = 0x8000,
};

//==========================================================
// <T>显示类型</T>
//==========================================================
enum EDisplay{
   // 对象
   EDisplay_2d = 1,
   // 组件
   EDisplay_3d = 2,
};
//----------------------------------------------------------
typedef TInt TComponentType;
typedef TInt TDisplayType;

//==========================================================
// <T>显示层类型</T>
//==========================================================
enum EDisplayLayer{
   // 未知
   EDisplayLayer_Unknown = 0,
   // 界面层
   EDisplayLayer_Face = 1,
   // 前景层
   EDisplayLayer_Fore = 2,
   // 粒子层
   EDisplayLayer_Particle = 3,
   // 精灵层
   EDisplayLayer_Sprite = 4,
   // 精灵层
   EDisplayLayer_Terrain = 5,
   // 背景层
   EDisplayLayer_Ground = 6,
   // 后景层
   EDisplayLayer_Back = 7,
};

//==========================================================
// <T>舞台帧类型</T>
//==========================================================
enum EStageFrame{
   // 未知
   EStageFrame_Unknown = 0,
   // 背景帧
   EStageFrame_Ground = 1,
   // 场景帧
   EStageFrame_Scene = 2,
   // 界面帧
   EStageFrame_Face = 3,
};

//============================================================
// <T>画板接口。</T>
//============================================================
class MO_EG_DECLARE ICanvas{
public:
   //------------------------------------------------------------
   // <T>析构画板接口。</T>
   MO_ABSTRACT ~ICanvas(){
   }
public:
   MO_VIRTUAL TResult Update() = 0;
};

//============================================================
// <T>可获得鼠标接口。</T>
//============================================================
class MO_EG_DECLARE IMouseable{
public:
   //------------------------------------------------------------
   // <T>析构可获得鼠标接口。</T>
   MO_ABSTRACT ~IMouseable(){
   }
public:
   MO_VIRTUAL TResult MouseDown(SMouseEvent* pEvent) = 0;
   MO_VIRTUAL TResult MouseMove(SMouseEvent* pEvent) = 0;
   MO_VIRTUAL TResult MouseUp(SMouseEvent* pEvent) = 0;
};

//============================================================
// <T>可获得焦点接口。</T>
//============================================================
class MO_EG_DECLARE IFocusable{
public:
   //------------------------------------------------------------
   // <T>析构可获得焦点接口。</T>
   MO_ABSTRACT ~IFocusable(){
   }
public:
   MO_VIRTUAL TResult FocusEnter() = 0;
   MO_VIRTUAL TResult FocusLeave() = 0;
};

//============================================================
// <T>可获得热点接口。</T>
//============================================================
class MO_EG_DECLARE IHoverable{
public:
   //------------------------------------------------------------
   // <T>析构可获得热点接口。</T>
   MO_ABSTRACT ~IHoverable(){
   }
public:
   MO_VIRTUAL TResult HoverEnter() = 0;
   MO_VIRTUAL TResult HoverLeave() = 0;
};

//============================================================
// <T>可拖拽接口。</T>
//============================================================
class MO_EG_DECLARE IDragable{
public:
   //------------------------------------------------------------
   // <T>析构可拖拽接口。</T>
   MO_ABSTRACT ~IDragable(){
   }
public:
   MO_VIRTUAL TResult DragBegin() = 0;
   MO_VIRTUAL TResult DragMove() = 0;
   MO_VIRTUAL TResult DragEnd() = 0;
};

//============================================================
// <T>跟踪信息。</T>
//============================================================
struct MO_EG_DECLARE STailInfo{
public:
   // 坐标
   SFloatPoint3 location;
   // 尺寸
   SFloatSize3 size;
   // 旋转
   SFloatVector3 rotation;
   // 背景颜色
   SFloatColor4 groundColor;
   // 纹理坐标
   SFloatCoord coord;
public:
   //------------------------------------------------------------
   // <T>构造跟踪信息。</T>
   STailInfo(){
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TFixVector<STailInfo, 64> TFsTailInfoVector;

//============================================================
// <T>跟踪控制器。</T>
//============================================================
class MO_EG_DECLARE FTailController : public FObject{
protected:
   TInt _limit;
   TInt _interval;
   TFsTailInfoVector _infos;
   TTimeTick _lastTick;
public:
   FTailController();
   MO_ABSTRACT ~FTailController();
public:
   //------------------------------------------------------------
   // <T>获得极限数量。</T>
   MO_INLINE TInt Limit(){
      return _limit;
   }
   //------------------------------------------------------------
   // <T>设置极限数量。</T>
   MO_INLINE void SetLimit(TInt limit){
      _limit = limit;
   }
   //------------------------------------------------------------
   // <T>获得毫秒间隔。</T>
   MO_INLINE TInt Interval(){
      return _interval;
   }
   //------------------------------------------------------------
   // <T>设置极限数量。</T>
   MO_INLINE void SetInterval(TInt interval){
      _interval = interval;
   }
   //------------------------------------------------------------
   // <T>构造信息集合。</T>
   MO_INLINE TFsTailInfoVector& Infos(){
      return _infos;
   }
public:
   TResult Push(STailInfo& info);
   TResult Reset();
};

//============================================================
// <T>组件属性。</T>
//============================================================
class MO_EG_DECLARE FComponentProperty{
protected:
   TString _name;
public:
   FComponentProperty();
   MO_ABSTRACT ~FComponentProperty();
public:
   //------------------------------------------------------------
   // <T>判断名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
public:
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};
//----------------------------------------------------------
typedef FVector<FComponentProperty*> FComponentPropertyCollection;

//============================================================
// <T>组件属性集合。</T>
//============================================================
class MO_EG_DECLARE FComponentProperties{
protected:
   FComponentPropertyCollection* _pProperties;
public:
   FComponentProperties();
   MO_ABSTRACT ~FComponentProperties();
public:
   //------------------------------------------------------------
   // <T>判断属性集合。</T>
   MO_INLINE FComponentPropertyCollection* Properties(){
      return _pProperties;
   }
public:
   TResult Serialize(IDataOutput* pOutput);
   TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>处理上下文。</T>
//============================================================
class MO_EG_DECLARE SProcessContext{
public:
   FInstance* senderPtr;
public:
   //------------------------------------------------------------
   // <T>构造处理上下文。</T>
   SProcessContext(){
      MO_CLEAR(senderPtr);
   }
};

//============================================================
// <T>组件对象。</T>
//============================================================
class MO_EG_DECLARE FComponent : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FComponent, FInstance);
protected:
   TString _name;
   TBool _optionValid;
   TComponentType _objectCd;
   TInt _flagCd;
   FComponent* _pParent;
   // FComponentCollection* _pChildren;
   FComponentProperties* _pProperties;
   TBool _statusConstruct;
   TBool _statusSetup;
   TBool _statusReady;
   TTimeTick _lastUpdate;
public:
   FComponent();
   MO_ABSTRACT ~FComponent();
public:
   //------------------------------------------------------------
   // <T>判断是否为指定名称。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return _name.Equals(pName);
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>判断是否指定对象。</T>
   MO_INLINE TBool IsObject(TInt value){
      return (_objectCd & value) == value;
   }
   //------------------------------------------------------------
   // <T>获得是否有父组件。</T>
   MO_INLINE TBool HasParent(){
      return _pParent != NULL;
   }
   //------------------------------------------------------------
   // <T>获得父组件。</T>
   MO_INLINE FComponent* Parent(){
      return _pParent;
   }
   //------------------------------------------------------------
   // <T>设置父组件。</T>
   MO_INLINE void SetParent(FComponent* pParent){
      _pParent = pParent;
   }
   ////------------------------------------------------------------
   //// <T>判断是否含有子组件。</T>
   //MO_INLINE TBool HasChild(){
   //   return (_pChildren != NULL) ? !_pChildren->IsEmpty() : EFalse;
   //}
   ////------------------------------------------------------------
   //// <T>获得子组件总数。</T>
   //MO_INLINE TInt ChildCount(){
   //   return (_pChildren != NULL) ? _pChildren->Count() : 0;
   //}
   ////------------------------------------------------------------
   //// <T>获得子组件集合。</T>
   //MO_INLINE FComponentCollection* Children(){
   //   if(_pChildren == NULL){
   //      _pChildren = MO_CREATE(FComponentCollection);
   //   }
   //   return _pChildren;
   //}
   ////------------------------------------------------------------
   //// <T>获得指定索引位置的子组件。</T>
   //MO_INLINE FComponent* Child(TInt index){
   //   return _pChildren->Get(index);
   //}
   //------------------------------------------------------------
   // <T>获得最后更新时间。</T>
   MO_INLINE TTimeTick LastUpdate(){
      return _lastUpdate;
   }
public:
   MO_ABSTRACT FComponent* TopComponent();
   //MO_ABSTRACT TResult RemoveFromParent();
   //FComponent* ChildGet(TCharC* pName);
   //FComponent* ChildFind(TCharC* pName);
   //FComponent* ChildSearch(TCharC* pName);
   //MO_ABSTRACT TResult ChildPush(FComponent* pComponent);
   //MO_ABSTRACT TResult ChildPushDirect(FComponent* pComponent);
   //MO_ABSTRACT TResult ChildRemove(FComponent* pComponent);
   //MO_ABSTRACT TResult ChildSwap(FComponent* pSource, FComponent* pTarget);
   //MO_ABSTRACT TResult ChildClear();
public:
   MO_ABSTRACT TResult OnConstruct();
   MO_ABSTRACT TResult OnSerialize(IDataOutput* pOutput);
   MO_ABSTRACT TResult OnUnserialize(IDataInput* pInput);
   MO_ABSTRACT TResult OnSetupBefore();
   MO_ABSTRACT TResult OnSetupAfter();
   MO_ABSTRACT TResult OnDispose();
public:
   MO_ABSTRACT TResult Construct();
   MO_ABSTRACT TResult Serialize(IDataOutput* pOutput);
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Update();
   MO_ABSTRACT TResult ProcessBefore(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessAfter(SProcessContext* pContext);
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>绘制环境。</T>
//============================================================
struct MO_EG_DECLARE SDrawableContext{
public:
   //const TInt LevelHigh = 1;
   //const TInt LevelLower = 2;
   TInt interval;
   TBool delay;
   TInt delayLevel;
   TBool dirty;
   //FCamera* cameraPtr;
   //FLight lightPtr;
   //FByteStream* dataPtr:
};

//============================================================
// <T>可绘制对象。</T>
//============================================================
class MO_EG_DECLARE FDrawable : public FComponent
{
   MO_CLASS_DECLARE_INHERITS(FDrawable, FComponent);
protected:
   // 状态可见
   TBool _statusVisible;
   // 状态脏
   TBool _statusDirty;
   // 坐标
   SFloatPoint3 _location;
   // 尺寸
   SFloatSize3 _size;
   // 旋转
   SFloatVector3 _rotation;
   // 背景颜色
   SFloatColor4 _groundColor;
   // 纹理坐标
   SFloatCoord _coord;
   // 变换矩阵
   SFloatMatrix3d _matrix;
   // 模型变换矩阵
   SFloatMatrix3d _matrixModel;
   // 形状变换矩阵
   SFloatMatrix3d _matrixTransform;
   // 最终变换矩阵
   SFloatMatrix3d _matrixFinal;
public:
   FDrawable();
   MO_ABSTRACT ~FDrawable();
public:
   //------------------------------------------------------------
   // <T>获得父绘制组件。</T>
   MO_INLINE FDrawable* ParentDrawable(){
      return (FDrawable*)_pParent;
   }
   //------------------------------------------------------------
   // <T>获得可见状态。</T>
   MO_INLINE TBool StatusVisible(){
      return _statusVisible;
   }
   //------------------------------------------------------------
   // <T>获得脏状态。</T>
   MO_INLINE TBool StatusDirty(){
      return _statusDirty;
   }
   //------------------------------------------------------------
   // <T>获得坐标。</T>
   MO_INLINE SFloatPoint3& Location(){
      return _location;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SFloatSize3& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得旋转。</T>
   MO_INLINE SFloatVector3& Rotation(){
      return _rotation;
   }
   //------------------------------------------------------------
   // <T>获得背景颜色。</T>
   MO_INLINE SFloatColor4& GroundColor(){
      return _groundColor;
   }
   //------------------------------------------------------------
   // <T>获得纹理坐标。</T>
   MO_INLINE SFloatCoord& Coord(){
      return _coord;
   }
   //------------------------------------------------------------
   // <T>获得变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得模型变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& MatrixModel(){
      return _matrixModel;
   }
   //------------------------------------------------------------
   // <T>获得即时变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& MatrixFinal(){
      return _matrixFinal;
   }
public:
   MO_ABSTRACT TAny* Convert(EComponent componentCd);
public:
   MO_ABSTRACT TResult OnPaint();
   MO_ABSTRACT TResult OnFocusTest(FFocusTester* pTester);
public:
   MO_ABSTRACT TBool TestDrawable();
   MO_ABSTRACT TResult CalculateRectangle(SIntRectangle* pRectangle);
   MO_ABSTRACT TResult FilterRegion(FRenderRegion* pRegion);
public:
   MO_ABSTRACT void UpdateSelftMatrix(SDrawableContext* pContext);
   MO_ABSTRACT void UpdateParentMatrix();
   MO_ABSTRACT void UpdateMatrix();
   MO_ABSTRACT void UpdateAllMatrix(SDrawableContext* pContext);
   MO_ABSTRACT void UpdateDirty(SDrawableContext* pContext);
public:
   MO_ABSTRACT TResult SetVisible(TBool visible);
   MO_ABSTRACT TResult FocusTest(FFocusTester* pTester);
   MO_ABSTRACT TResult Dirty(TBool force = EFalse);
   MO_ABSTRACT TResult Paint();
   MO_ABSTRACT TResult Update();
public:
   MO_OVERRIDE TResult ProcessBefore(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessAfter(SProcessContext* pContext);
public:
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>渲染内容。</T>
//============================================================
struct SRenderableContent{
public:
   FRenderVertexStreams* vertexStreamsPtr;
   FRenderIndexBuffer* indexBufferPtr;
   FRenderTextures* texturesPtr;
public:
   //------------------------------------------------------------
   // <T>构造渲染参数。</T>
   SRenderableContent(){
      MO_CLEAR(vertexStreamsPtr);
      MO_CLEAR(indexBufferPtr);
      MO_CLEAR(texturesPtr);
   }
};

//============================================================
// <T>显示对象。</T>
//============================================================
class MO_EG_DECLARE FDisplay : public FDrawable
{
   MO_CLASS_DECLARE_INHERITS(FDisplay, FDrawable);
protected:
   TDisplayType _typeCd;
   FDisplayCollection* _pDisplays;
   FRenderableCollection* _pRenderables;
public:
   FDisplay();
   MO_ABSTRACT ~FDisplay();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TDisplayType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>获得显示集合。</T>
   MO_INLINE FDisplayCollection* Displays(){
      return _pDisplays;
   }
   //------------------------------------------------------------
   // <T>获得渲染集合。</T>
   MO_INLINE FRenderableCollection* Renderables(){
      return _pRenderables;
   }
public:
   MO_ABSTRACT TResult DisplayPush(FDisplay* pDisplay);
   MO_ABSTRACT TResult DisplayRemove(FDisplay* pDisplay);
   MO_ABSTRACT TResult DisplayClear();
   MO_ABSTRACT TResult RenderablePush(FRenderable* pRenderable);
   MO_ABSTRACT TResult RenderableRemove(FRenderable* pRenderable);
   MO_ABSTRACT TResult RenderableClear();
public:
   MO_OVERRIDE TResult FilterRegion(FRenderRegion* pRegion);
   MO_OVERRIDE void UpdateAllMatrix(SDrawableContext* pContext);
   MO_OVERRIDE TResult ProcessBefore(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessAfter(SProcessContext* pContext);
   MO_OVERRIDE TResult Free();
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};

//============================================================
// <T>显示缓冲池。</T>
//============================================================
class MO_EG_DECLARE FDisplayPool : public FPool<FDrawable*>{
protected:
   TDisplayType _typeCd;
public:
   FDisplayPool();
   MO_ABSTRACT ~FDisplayPool();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TDisplayType TypeCd(){
      return _typeCd;
   }
public:
   MO_VIRTUAL FDrawable* Create() = 0;
public:
   FDrawable* Alloc();
   void Free(FDrawable* pParticle);
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FObjects<FDisplayPool*> FDisplayPoolCollection;

//============================================================
// <T>可绘制对象层。</T>
//============================================================
class MO_EG_DECLARE FDisplayLayer : public FDisplay
{
   MO_CLASS_DECLARE_INHERITS(FDisplayLayer, FDisplay);
protected:
   EDisplayLayer _layerCd;
   FStageFrame* _pStageFrame;
   FParticleController* _pParticleController;
   GPtr<FVisualRegion> _visualRegion;
public:
   FDisplayLayer();
   MO_ABSTRACT ~FDisplayLayer();
public:
   //------------------------------------------------------------
   // <T>获得显示层类型。</T>
   MO_INLINE EDisplayLayer LayerCd(){
      return _layerCd;
   }
   //------------------------------------------------------------
   // <T>设置显示层类型。</T>
   MO_INLINE void SetLayerCd(EDisplayLayer layerCd){
      _layerCd = layerCd;
   }
   //------------------------------------------------------------
   // <T>获得舞台。</T>
   MO_INLINE FStageFrame* StageFrame(){
      return _pStageFrame;
   }
   //------------------------------------------------------------
   // <T>设置舞台。</T>
   MO_INLINE void SetStageFrame(FStageFrame* pStage){
      _pStageFrame = pStage;
   }
   //------------------------------------------------------------
   // <T>获得粒子控制器。</T>
   MO_INLINE FParticleController* ParticleController(){
      return _pParticleController;
   }
   //------------------------------------------------------------
   // <T>获得可见区域。</T>
   MO_INLINE FVisualRegion* VisualRegion(){
      return _visualRegion;
   }
public:
   TResult OnFocusTest(FFocusTester* pTester);
public:
   MO_ABSTRACT TResult Setup();
   MO_OVERRIDE TResult FilterRegion(FRenderRegion* pRegion);
   MO_ABSTRACT TResult ProcessBefore(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessAfter(SProcessContext* pContext);
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FVector<FDisplayLayer*> FDisplayLayerCollection;

//============================================================
// <T>空间体。</T>
//============================================================
class MO_EG_DECLARE FSpatial : public FDrawable{
protected:
   FDisplayCollection* _pDisplays;
public:
   FSpatial();
   MO_ABSTRACT ~FSpatial();
public:
   //------------------------------------------------------------
   // <T>获得显示集合。</T>
   MO_INLINE FDisplayCollection* Displays(){
      return _pDisplays;
   }
public:
   MO_ABSTRACT TResult DisplayPush(FDisplay* pDisplay);
   MO_ABSTRACT TResult DisplayRemove(FDisplay* pDisplay);
   MO_ABSTRACT TResult DisplayClear();
public:
   MO_OVERRIDE TResult FilterRegion(FRenderRegion* pRegion);
   MO_OVERRIDE void UpdateAllMatrix(SDrawableContext* pContext);
   MO_OVERRIDE TResult ProcessBefore(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessAfter(SProcessContext* pContext);
   MO_OVERRIDE TResult Free();
};

//============================================================
// <T>位图纹理。</T>
//============================================================
class MO_EG_DECLARE FBitmap : public FDrawable{
protected:
   GPtr<FBitmapData> _data;
   GPtr<FRenderTexture> _texture;
   SIntSize2 _size;
public:
   FBitmap();
   MO_ABSTRACT ~FBitmap();
public:
   //------------------------------------------------------------
   // <T>获得数据。</T>
   FBitmapData* Data(){
      return _data;
   }
   //------------------------------------------------------------
   // <T>获得纹理。</T>
   FRenderTexture* Texture(){
      return _texture;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_OVERRIDE TResult FilterRegion(FRenderRegion* pRegion);
public:
   MO_OVERRIDE TResult Resize(TInt width, TInt height);
   MO_ABSTRACT TResult Clear(TColor color);
   MO_ABSTRACT TResult Clear(TColor color, SIntRectangle* pRectangle);
   MO_ABSTRACT TResult Update();
public:
   MO_ABSTRACT void FreeBitmap();
   MO_ABSTRACT void FreeTexture();
};

//============================================================
// <T>位图画板。</T>
//============================================================
class MO_EG_DECLARE FBitmapCanvas :
      public FBitmap,
      public ICanvas{
protected:
   SIntRectangle _clientRectangle;
   SIntRectangle _clipRectangle;
public:
   FBitmapCanvas();
   MO_ABSTRACT ~FBitmapCanvas();
public:
   //------------------------------------------------------------
   // <T>获得客户范围。</T>
   MO_INLINE SIntRectangle& ClientRectangle(){
      return _clientRectangle;
   }
   //------------------------------------------------------------
   // <T>获得剪裁区域。</T>
   MO_INLINE SIntRectangle& ClipRectangle(){
      return _clipRectangle;
   }
public:
   MO_OVERRIDE TResult Setup();
   MO_OVERRIDE TResult Update();
public:
   MO_OVERRIDE TResult DrawLine(TColor color, TInt x1, TInt y1, TInt x2, TInt y2);
   MO_OVERRIDE TResult DrawLineHorizontal(TColor color, TInt x1, TInt x2, TInt y);
   MO_OVERRIDE TResult DrawLineVertical(TColor color, TInt y1, TInt y2, TInt x);
   MO_OVERRIDE TResult DrawTriangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2, TInt x3, TInt y3);
   MO_OVERRIDE TResult DrawRectangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2);
   MO_OVERRIDE TResult DrawRectangle(TColor color, SIntRectangle* pRectangle);
public:
   MO_OVERRIDE TResult FillTriangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2, TInt x3, TInt y3);
   MO_OVERRIDE TResult FillRectangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2);
   MO_OVERRIDE TResult FillRectangle(TColor color, SIntRectangle* pRectangle);
public:
   MO_OVERRIDE TResult DrawBitmap(FBitmapData* pBitmapData, TBool alpha, TInt x, TInt y);
   MO_OVERRIDE TResult DrawBitmap(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, TInt x, TInt y);
   MO_OVERRIDE TResult DrawBitmap(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, SIntRectangle* pTargetRectangle);
   MO_OVERRIDE TResult DrawBitmapGrid9(FBitmapData* pBitmapData, TBool alpha, SIntPadding* pSourcePadding, SIntRectangle* pTargetRectangle);
   MO_OVERRIDE TResult DrawBitmapGrid9(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, SIntPadding* pSourcePadding, SIntRectangle* pTargetRectangle);
};

//============================================================
// <T>显示动画命令类型。</T>
//============================================================
enum EMovieAction{
   EMovieAction_Play,
   EMovieAction_Visible,
   EMovieAction_Dispose,
};

//============================================================
// <T>显示动画命令。</T>
//============================================================
struct MO_EG_DECLARE SMovieAction{
public:
   // 命令类型
   EMovieAction actionCd;
   // 可见性
   TBool visible;
   // 方向
   TInt directionCd;
   // 循环次数
   TInt loop;
   // 速率
   TFloat rate;
public:
   //------------------------------------------------------------
   // <T>构造显示动画命令。</T>
   SMovieAction(){
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TList<SMovieAction> TMovieActionList;

//============================================================
// <T>帧处理事件。</T>
//============================================================
class MO_EG_DECLARE SFrameEvent : public SEvent{
public:
   //------------------------------------------------------------
   // <T>构造帧处理事件。</T>
   SFrameEvent(TAny* pSender) : SEvent(pSender){
   }
};
//------------------------------------------------------------
typedef MO_EG_DECLARE TListeners<SFrameEvent> TFrameListeners;

//============================================================
// <T>舞台对象。</T>
//============================================================
class MO_EG_DECLARE FStageFrame : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FStageFrame, FInstance);
protected:
   // 名称
   TString _name;
   // 舞台
   FStage* _pStage;
   // 帧类型
   EStageFrame _frameCd;
   // 背景颜色
   SFloatColor4 _backgroundColor;
   // 渲染目标
   GPtr<FRenderTarget> _renderTarget;
   // 显示层集合
   FDisplayLayerCollection* _pLayers;
public:
   FStageFrame();
   MO_ABSTRACT ~FStageFrame();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = _name;
   }
   //------------------------------------------------------------
   // <T>获得舞台。</T>
   MO_INLINE FStage* Stage(){
      return _pStage;
   }
   //------------------------------------------------------------
   // <T>设置舞台。</T>
   MO_INLINE void SetStage(FStage* pStage){
      _pStage = pStage;
   }
   //------------------------------------------------------------
   // <T>获得帧类型。</T>
   MO_INLINE EStageFrame FrameCd(){
      return _frameCd;
   }
   //------------------------------------------------------------
   // <T>设置帧类型。</T>
   MO_INLINE void SetFrameCd(EStageFrame frameCd){
      _frameCd = frameCd;
   }
   //------------------------------------------------------------
   // <T>获得背景颜色。</T>
   MO_INLINE SFloatColor4& BackgroundColor(){
      return _backgroundColor;
   }
   //------------------------------------------------------------
   // <T>获得渲染目标。</T>
   MO_INLINE FRenderTarget* RenderTarget(){
      return _renderTarget;
   }
   //------------------------------------------------------------
   // <T>设置喧染目标。</T>
   MO_INLINE void SetRenderTarget(FRenderTarget* pRenderTarget){
      return _renderTarget = pRenderTarget;
   }
   //------------------------------------------------------------
   // <T>获得层集合。</T>
   MO_INLINE FDisplayLayerCollection* Layers(){
      return _pLayers;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   FDisplayLayer* LayerFind(EDisplayLayer frameCd);
   void LayerClear();
   void LayerPush(FDisplayLayer* pFrame);
   void LayerRemove(FDisplayLayer* pFrame);
public:
   MO_ABSTRACT TResult FocusTest(FFocusTester* pTester);
public:
   MO_ABSTRACT TResult Active();
   MO_ABSTRACT TResult Deactive();
public:
   MO_ABSTRACT TResult BuildRegion(FRenderRegion* pRegion);
public:
   MO_ABSTRACT TResult ProcessBefore(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessInput();
   MO_ABSTRACT TResult ProcessLogic();
   MO_ABSTRACT TResult ProcessAfter(SProcessContext* pContext);
public:
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Dispose();
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FObjects<FStageFrame*> FStageFrameCollection;

//============================================================
// <T>舞台。</T>
//============================================================
class MO_EG_DECLARE FStage: public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FStage, FInstance);
protected:
   GPtr<FRenderView> _activeView;
   // 视角集合
   FRenderViewCollection* _pViews;
   // 背景色
   SFloatColor4 _backgroundColor;
   // 方向光源
   GPtr<FDirectionalLight> _directionalLight;
   // 光源集合
   FLightCollection* _pLights;
   // 舞台帧集合
   FStageFrameCollection* _pFrames;
   // 帧进入监听集合
   TFrameListeners _listenersFrameEnter;
   // 帧离开监听集合
   TFrameListeners _listenersFrameLeave;
public:
   FStage();
   MO_ABSTRACT ~FStage();
public:
   //------------------------------------------------------------
   // <T>获得视角集合。</T>
   MO_INLINE FRenderViewCollection* Views(){
      return _pViews;
   }
   //------------------------------------------------------------
   // <T>获得背景色。</T>
   MO_INLINE SFloatColor4& BackgroundColor(){
      return _backgroundColor;
   }
   //------------------------------------------------------------
   // <T>获得方向光源。</T>
   MO_INLINE FDirectionalLight* DirectionalLight(){
      return _directionalLight;
   }
   //------------------------------------------------------------
   // <T设置方向光源。</T>
   MO_INLINE void SetDirectionalLight(FDirectionalLight* pDirectionalLight){
      _directionalLight = pDirectionalLight;
   }
   //------------------------------------------------------------
   // <T>获得光源集合。</T>
   MO_INLINE FLightCollection* Lights(){
      return _pLights;
   }
   //------------------------------------------------------------
   // <T>获得帧集合。</T>
   MO_INLINE FStageFrameCollection* Frames(){
      return _pFrames;
   }
   //------------------------------------------------------------
   // <T>获得帧进入监听集合。</T>
   MO_INLINE TFrameListeners& ListenersFrameEnter(){
      return _listenersFrameEnter;
   }
   //------------------------------------------------------------
   // <T>获得帧进入监听集合。</T>
   MO_INLINE TFrameListeners& ListenersFrameLeave(){
      return _listenersFrameLeave;
   }
public:
   FStageFrame* FrameFind(EStageFrame frameCd);
   void FrameClear();
   void FramePush(FStageFrame* pLayer);
   void FrameRemove(FStageFrame* pLayer);
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_ABSTRACT TResult Active();
   MO_ABSTRACT TResult Deactive();
public:
   MO_ABSTRACT TResult BuildRegion(FRenderRegion* pRegion);
public:
   MO_ABSTRACT TResult ProcessBefore(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessInput();
   MO_ABSTRACT TResult ProcessLogic();
   MO_ABSTRACT TResult ProcessAfter(SProcessContext* pContext);
public:
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>舞台控制台。</T>
//============================================================
class MO_EG_DECLARE FStageConsole : public FConsole{
protected:
   // 舞台
   FStage* _pActiveStage;
   // 显示处理器
   GPtr<FProcessor> _displayProcessor;
   // 渲染处理器
   GPtr<FProcessor> _renderableProcessor;
public:
   FStageConsole();
   MO_ABSTRACT ~FStageConsole();
public:
   //------------------------------------------------------------
   // <T>获得激活的舞台。</T>
   MO_INLINE FStage* ActiveStage(){
      return _pActiveStage;
   }
   //------------------------------------------------------------
   // <T>获得显示处理器。</T>
   MO_INLINE FProcessor* DisplayProcessor(){
      return _displayProcessor;
   }
   //------------------------------------------------------------
   // <T>获得渲染处理器。</T>
   MO_INLINE FProcessor* RenderableProcessor(){
      return _renderableProcessor;
   }
public:
   MO_ABSTRACT void Setup();
   MO_ABSTRACT TResult Shutdown();
public:
   MO_ABSTRACT TResult SelectStage(FStage* pStage);
};

//============================================================
// <T>舞台管理器。</T>
//============================================================
class MO_EG_DECLARE RStageManager : public RSingleton<FStageConsole>{
};

//============================================================
// <T>焦点测试器。</T>
//============================================================
class MO_EG_DECLARE FFocusTester : public FObject{
public:
   TBool _statusInRange;
   TBool _statusChildren;
   TInt _level;
   SIntPoint2 _location;
   FDrawableCollection* _pDrawables;
public:
   FFocusTester();
   MO_ABSTRACT ~FFocusTester();
public:
   //------------------------------------------------------------
   // <T>获得是否在范围内。</T>
   MO_INLINE TBool StatusInRange(){
      return _statusInRange;
   }
   //------------------------------------------------------------
   // <T>设置是否在范围内。</T>
   MO_INLINE void SetStatusInRange(TBool statusInRange){
      _statusInRange = statusInRange;
   }
   //------------------------------------------------------------
   // <T>获得是否查询子对象。</T>
   MO_INLINE TBool StatusChildren(){
      return _statusChildren;
   }
   //------------------------------------------------------------
   // <T>设置是否查询子对象。</T>
   MO_INLINE void SetStatusChildren(TBool statusChildren){
      _statusChildren = statusChildren;
   }
   //------------------------------------------------------------
   // <T>获得层级。</T>
   MO_INLINE TInt Level(){
      return _level;
   }
   //------------------------------------------------------------
   // <T>设置层级。</T>
   MO_INLINE void SetLevel(TInt level){
      _level = level;
   }
   //------------------------------------------------------------
   // <T>获得位置。</T>
   MO_INLINE SIntPoint2& Location(){
      return _location;
   }
   //------------------------------------------------------------
   // <T>获得可绘制对象集合。</T>
   MO_INLINE FDrawableCollection* Drawables(){
      return _pDrawables;
   }
public:
   //------------------------------------------------------------
   // <T>获得激活的对象。</T>
   MO_INLINE FDrawable* ActiveDrawable(){
      return _pDrawables->IsEmpty() ? NULL : _pDrawables->First();
   }
   //------------------------------------------------------------
   // <T>增加一个对象。</T>
   MO_INLINE void Push(FDrawable* pDrawable){
      _pDrawables->Push(pDrawable);
   }
   //------------------------------------------------------------
   // <T>清空内容。</T>
   MO_INLINE void TestReset(){
      _statusInRange = EFalse;
      _statusChildren = EFalse;
   }
   //------------------------------------------------------------
   // <T>清空内容。</T>
   MO_INLINE void Clear(){
      _level = 0;
      _pDrawables->Clear();
   }
};

//============================================================
// <T>焦点控制台。</T>
//============================================================
class MO_EG_DECLARE FFocusConsole : public FConsole{
public:
   SIntPoint2 _focusLocation;
   FDrawable* _pHoverDrawable;
   FDrawable* _pFocusDrawable;
   FDrawable* _pDragDrawable;
   SIntPoint2 _startLocation;
   SIntPoint2 _dragLocation;
   SIntPoint2 _dragDrawableLocation;
   FDrawableCollection* _pFocusDrawables;
   FFocusTester* _pTester;
public:
   FFocusConsole();
   MO_ABSTRACT ~FFocusConsole();
public:
   //------------------------------------------------------------
   // <T>获得焦点位置。</T>
   MO_INLINE SIntPoint2& FocusLocation(){
      return _focusLocation;
   }
   //------------------------------------------------------------
   // <T>获得热点对象。</T>
   MO_INLINE FDrawable* HoverDrawable(){
      return _pHoverDrawable;
   }
   //------------------------------------------------------------
   // <T>获得焦点对象。</T>
   MO_INLINE FDrawable* FocusDrawable(){
      return _pFocusDrawable;
   }
   //------------------------------------------------------------
   // <T>获得焦点对象集合。</T>
   MO_INLINE FDrawableCollection* FocusDrawables(){
      return _pFocusDrawables;
   }
protected:
   FFocusTester* TestPosition(TInt x, TInt y);
public:
   TResult OnMouseDown(SMouseEvent* pEvent);
   TResult OnMouseMove(SMouseEvent* pEvent);
   TResult OnMouseUp(SMouseEvent* pEvent);
public:
   TResult Setup();
   TResult HoverDrawable(FDrawable* pDrawable);
   TResult FocusDrawable(FDrawable* pDrawable);
   TResult DragDrawable(FDrawable* pDrawable, TInt x, TInt y);
   TResult Process(EMouseButton buttonCd, TInt x, TInt y);
};

//============================================================
// <T>焦点管理器。</T>
//============================================================
class MO_EG_DECLARE RFocusManager : public RSingleton<FFocusConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_DISPLAY_H__
