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
// 类型定义
class FComponent;
class FDrawable;
class FDisplay;
class FStageLayer;
class FStage;
class FParticleController;
class FFocusTester;
// 集合定义
typedef MO_EG_DECLARE FObjects<FDrawable*> FDrawableCollection;
typedef MO_EG_DECLARE FObjects<FDisplay*> FDisplayCollection;
typedef MO_EG_DECLARE FList<FDisplay*> FDisplayList;

//==========================================================
// <T>组件配置</T>
//==========================================================
enum EComponentOption{
   // 有效
   EComponentOption_Valid = 0x01,
};

//==========================================================
// <T>组件状态</T>
//==========================================================
enum EComponentStatus{
   // 构建
   EComponentStatus_Construct = 0x01,
   // 配置
   EComponentStatus_Setup = 0x02,
   // 准备
   EComponentStatus_Ready = 0x04,
};

//============================================================
// <T>组件属性。</T>
//============================================================
class MO_EG_DECLARE FComponentProperty : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FComponentProperty, FInstance);
protected:
   TString _name;
   TInt _version;
public:
   FComponentProperty();
   MO_ABSTRACT ~FComponentProperty();
public:
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
   // <T>获得版本。</T>
   MO_INLINE TInt Version(){
      return _version;
   }
   //------------------------------------------------------------
   // <T>设置版本。</T>
   MO_INLINE void SetVersion(TInt version){
      _version = version;
   }
public:
   MO_ABSTRACT TResult Serialize(IDataOutput* pOutput);
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//----------------------------------------------------------
typedef MO_EG_DECLARE GPtrDictionary<FComponentProperty> GComponentPropertyDictionary;

//============================================================
// <T>组件属性集合。</T>
//============================================================
class MO_EG_DECLARE FComponentProperties : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FComponentProperties, FInstance);
protected:
   GComponentPropertyDictionary _properties;
public:
   FComponentProperties();
   MO_ABSTRACT ~FComponentProperties();
public:
   //------------------------------------------------------------
   // <T>判断属性集合。</T>
   MO_INLINE GComponentPropertyDictionary& Properties(){
      return _properties;
   }
public:
   MO_ABSTRACT TResult Serialize(IDataOutput* pOutput);
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>组件对象。</T>
//============================================================
class MO_EG_DECLARE FComponent : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FComponent, FInstance);
protected:
   TString _name;
   TInt _optionFlag;
   TInt _statusFlag;
   FComponent* _pParent;
   GPtr<FComponentProperties> _properties;
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
   // <T>判断是否处于指定配置。</T>
   MO_INLINE TBool IsOption(TInt value){
      return (_optionFlag & value) == value;
   }
   //------------------------------------------------------------
   // <T>设置配置内容。</T>
   MO_INLINE void SetOption(TInt flag, TBool value){
      if(value){
         _optionFlag |= flag;
      }else{
         _optionFlag &= ~flag;
      }
   }
   //------------------------------------------------------------
   // <T>判断是否处于指定状态。</T>
   MO_INLINE TBool IsStatus(TInt value){
      return (_statusFlag & value) == value;
   }
   //------------------------------------------------------------
   // <T>设置状态内容。</T>
   MO_INLINE void SetStatus(TInt flag, TBool value){
      if(value){
         _statusFlag |= flag;
      }else{
         _statusFlag &= ~flag;
      }
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
   //------------------------------------------------------------
   // <T>获得属性集合。</T>
   MO_INLINE FComponentProperties* Properties(){
      return _properties;
   }
   //------------------------------------------------------------
   // <T>获得最后更新时间。</T>
   MO_INLINE TTimeTick LastUpdate(){
      return _lastUpdate;
   }
public:
   MO_ABSTRACT FComponent* TopComponent();
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
   MO_ABSTRACT TResult Update(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessBefore(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessAfter(SProcessContext* pContext);
   MO_ABSTRACT TResult Dispose();
};
//------------------------------------------------------------
typedef MO_EG_DECLARE GPtrs<FComponent> GComponentPtrs;

//============================================================
// <T>脚本对象。</T>
//============================================================
class MO_EG_DECLARE FScriptable : public FComponent
{
   MO_CLASS_DECLARE_INHERITS(FScriptable, FComponent);
protected:
   GPtr<FScriptInstance> _scriptInstance;
public:
   FScriptable();
   MO_ABSTRACT ~FScriptable();
public:
   //------------------------------------------------------------
   // <T>获得脚本实例。</T>
   MO_INLINE FScriptInstance* ScriptInstance(){
      return _scriptInstance;
   }
   //------------------------------------------------------------
   // <T>设置脚本实例。</T>
   MO_INLINE void SetScriptInstance(FScriptInstance* pScriptInstance){
      _scriptInstance = pScriptInstance;
   }
public:
   MO_ABSTRACT TResult ProcessBefore(SProcessContext* pContext);
   MO_ABSTRACT TResult ProcessAfter(SProcessContext* pContext);
};
//------------------------------------------------------------
typedef MO_EG_DECLARE GPtrs<FScriptable> GScriptablePtrs;

//============================================================
// <T>绘制环境。</T>
//============================================================
struct MO_EG_DECLARE SDrawableContext{
public:
   TInt interval;
   TBool delay;
   TInt delayLevel;
   TBool dirty;
};

//============================================================
// <T>可绘制对象。</T>
//============================================================
class MO_EG_DECLARE FDrawable : public FComponent
{
   MO_CLASS_DECLARE_INHERITS(FDrawable, FComponent);
protected:
   // 状态准备好
   TBool _statusReady;
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
   // 前景颜色
   SFloatColor4 _foreColor;
   // 背景颜色
   SFloatColor4 _groundColor;
   // 纹理坐标
   SFloatCoord _coord;
   // 变换矩阵
   SFloatMatrix3d _matrix;
   // 模型矩阵
   SFloatMatrix3d _matrixModel;
   // 变换矩阵
   SFloatMatrix3d _matrixTransform;
public:
   FDrawable();
   MO_ABSTRACT ~FDrawable();
public:
   //------------------------------------------------------------
   // <T>获得父绘制组件。</T>
   MO_INLINE FDrawable* ParentDrawable(){
      return _pParent->Convert<FDrawable>();
   }
   //------------------------------------------------------------
   // <T>获得准备状态。</T>
   MO_INLINE TBool StatusReady(){
      return _statusReady;
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
   // <T>获得前景颜色。</T>
   MO_INLINE SFloatColor4& ForeColor(){
      return _foreColor;
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
   // <T>获得矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得模型矩阵。</T>
   MO_INLINE SFloatMatrix3d& MatrixModel(){
      return _matrixModel;
   }
   //------------------------------------------------------------
   // <T>获得变换矩阵。</T>
   MO_INLINE SFloatMatrix3d& MatrixTransform(){
      return _matrixTransform;
   }
public:
   MO_ABSTRACT TResult OnPaint();
   MO_ABSTRACT TResult OnFocusTest(FFocusTester* pTester);
public:
   MO_ABSTRACT TBool TestReady();
   MO_ABSTRACT TBool TestDrawable();
   MO_ABSTRACT TResult CalculateRectangle(SIntRectangle* pRectangle);
   MO_ABSTRACT TResult FilterRegion(FRenderRegion* pRegion);
public:
   MO_ABSTRACT TResult UpdateSelftMatrix(SDrawableContext* pContext);
   MO_ABSTRACT TResult UpdateParentMatrix();
   MO_ABSTRACT TResult UpdateMatrix();
   MO_ABSTRACT TResult UpdateAllMatrix(SDrawableContext* pContext);
   MO_ABSTRACT TResult UpdateDirty(SDrawableContext* pContext);
public:
   MO_ABSTRACT TResult SetVisible(TBool visible);
   MO_ABSTRACT TResult FocusTest(FFocusTester* pTester);
   MO_ABSTRACT TResult Dirty(TBool force = EFalse);
   MO_ABSTRACT TResult Paint();
public:
   MO_OVERRIDE TResult Update(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessBefore(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessAfter(SProcessContext* pContext);
public:
   MO_ABSTRACT TResult Suspend();
   MO_ABSTRACT TResult Resume();
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>显示对象。</T>
//============================================================
class MO_EG_DECLARE FDisplay : public FDrawable
{
   MO_CLASS_DECLARE_INHERITS(FDisplay, FDrawable);
public:
   typedef GPtrs<FDisplay> GDisplayPtrs;
protected:
   GDisplayPtrs _displays;
   GRenderablePtrs _renderables;
   GScriptablePtrs _scriptables;
public:
   FDisplay();
   MO_ABSTRACT ~FDisplay();
public:
   //------------------------------------------------------------
   // <T>获得显示集合。</T>
   MO_INLINE GDisplayPtrs& Displays(){
      return _displays;
   }
   //------------------------------------------------------------
   // <T>获得渲染集合。</T>
   MO_INLINE GRenderablePtrs& Renderables(){
      return _renderables;
   }
   //------------------------------------------------------------
   // <T>获得脚本集合。</T>
   MO_INLINE GScriptablePtrs& Scriptables(){
      return _scriptables;
   }
public:
   MO_ABSTRACT TResult RenderablePush(FRenderable* pRenderable);
   MO_ABSTRACT TResult RenderableRemove(FRenderable* pRenderable);
   MO_ABSTRACT TResult RenderableClear();
   MO_ABSTRACT TResult DisplayPush(FDisplay* pDisplay);
   MO_ABSTRACT TResult DisplayRemove(FDisplay* pDisplay);
   MO_ABSTRACT TResult DisplayClear();
   MO_ABSTRACT TResult ScriptablePush(FScriptable* pScriptable);
   MO_ABSTRACT TResult ScriptableRemove(FScriptable* pScriptable);
   MO_ABSTRACT TResult ScriptableClear();
public:
   MO_OVERRIDE TResult FilterRegion(FRenderRegion* pRegion);
   MO_OVERRIDE TResult UpdateAllMatrix(SDrawableContext* pContext);
   MO_OVERRIDE TResult Update(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessBefore(SProcessContext* pContext);
   MO_OVERRIDE TResult ProcessAfter(SProcessContext* pContext);
   MO_OVERRIDE TResult Free();
public:
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Dispose();
};
//------------------------------------------------------------
typedef MO_EG_DECLARE GPtrs<FDisplay> GDisplayPtrs;

//============================================================
// <T>可绘制对象层。</T>
//============================================================
class MO_EG_DECLARE FDisplayLayer : public FDisplay
{
   MO_CLASS_DECLARE_INHERITS(FDisplayLayer, FDisplay);
protected:
   FStageLayer* _pStageLayer;
   FParticleController* _pParticleController;
   GPtr<FVisualRegion> _visualRegion;
public:
   FDisplayLayer();
   MO_ABSTRACT ~FDisplayLayer();
public:
   //------------------------------------------------------------
   // <T>获得舞台层。</T>
   MO_INLINE FStageLayer* StageLayer(){
      return _pStageLayer;
   }
   //------------------------------------------------------------
   // <T>设置舞台层。</T>
   MO_INLINE void SetStageLayer(FStageLayer* pStageLayer){
      _pStageLayer = pStageLayer;
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
class MO_EG_DECLARE FStageLayer : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FStageLayer, FInstance);
protected:
   // 名称
   TString _name;
   // 舞台
   FStage* _pStage;
   // 背景颜色
   SFloatColor4 _backgroundColor;
   // 渲染目标
   GPtr<FRenderTarget> _renderTarget;
   // 显示层集合
   FDisplayLayerCollection* _pLayers;
public:
   FStageLayer();
   MO_ABSTRACT ~FStageLayer();
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
   FDisplayLayer* LayerFind(TCharC* pName);
   TResult LayerPush(FDisplayLayer* pLayer);
   TResult LayerRemove(FDisplayLayer* pLayer);
   TResult LayerClear();
public:
   MO_ABSTRACT TResult FocusTest(FFocusTester* pTester);
public:
   MO_ABSTRACT TResult Active();
   MO_ABSTRACT TResult Deactive();
public:
   MO_ABSTRACT TResult BuildRegion(FRenderRegion* pRegion);
public:
   MO_ABSTRACT TResult Update(SProcessContext* pContext);
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
typedef MO_EG_DECLARE GPtrs<FStageLayer> GStageLayerPtrs;

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
   // 场景层集合
   GStageLayerPtrs _layers;
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
   // <T>获得场景层集合。</T>
   MO_INLINE GStageLayerPtrs& Layers(){
      return _layers;
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
   FStageLayer* LayerFind(TCharC* pName);
   TResult LayerPush(FStageLayer* pLayer);
   TResult LayerRemove(FStageLayer* pLayer);
   TResult LayerClear();
public:
   MO_OVERRIDE TResult Setup();
public:
   MO_ABSTRACT TResult Active();
   MO_ABSTRACT TResult Deactive();
public:
   MO_ABSTRACT TResult BuildRegion(FRenderRegion* pRegion);
public:
   MO_ABSTRACT TResult Update(SProcessContext* pContext);
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
   MO_ABSTRACT TResult Startup();
   MO_ABSTRACT TResult Shutdown();
public:
   MO_ABSTRACT TResult SelectStage(FStage* pStage);
};

//============================================================
// <T>舞台管理器。</T>
//============================================================
class MO_EG_DECLARE RStageManager : public RSingleton<FStageConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_DISPLAY_H__
