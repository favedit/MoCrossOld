#ifndef __MO_EF_DISPLAY_H__
#define __MO_EF_DISPLAY_H__
//************************************************************

#ifndef __MO_EF_COMMON_H__
#include "MoEfCommon.h"
#endif // __MO_EF_COMMON_H__

MO_NAMESPACE_BEGIN

//==========================================================
typedef TInt TControlId;
class FUiControl;

//==========================================================
// <T>显示控件类型</T>
//==========================================================
enum EControlType{
   // 未知
   EControlType_Unknown,
   // 静态标签
   EControlType_StaticLabel,
   // 标签
   EControlType_Label,
   // 单选框
   EControlType_Radio,
   // 复选框
   EControlType_Check,
   // 数字框
   EControlType_Number,
   // 编辑框
   EControlType_Edit,
   // 富编辑框
   EControlType_RichEdit,
   // 按键
   EControlType_Button,
   // 滑动条
   EControlType_Slider,
   // 滚动条
   EControlType_ScrollBar,
   // 进度条
   EControlType_ProgressBar,
   // 日历
   EControlType_Calendar,
   // 图片框
   EControlType_PictureBox,
   // 字体框
   EControlType_FontBox,
   // 动画框
   EControlType_MovieBox,
   // 插槽项目
   EControlType_SlotItem,
   // 插槽
   EControlType_Slot,
   // 面板
   EControlType_Panel,
   // 页控件
   EControlType_Page,
   // 分页控件
   EControlType_PageControl,
   // 选择项目
   EControlType_SelectItem,
   // 选择列表
   EControlType_Select,
   // 菜单按键
   EControlType_MenuItem,
   // 菜单
   EControlType_Menu,
   // 列表项目
   EControlType_ListItem,
   // 列表视图
   EControlType_ListView,
   // 树节点类型
   EControlType_TreeNodeType,
   // 树节点层级
   EControlType_TreeNodeLevel,
   // 树节点
   EControlType_TreeNode,
   // 树目录
   EControlType_TreeView,
   // 表格格子
   EControlType_GridCell,
   // 表格复选框格子
   EControlType_GridCellCheck,
   // 表格数字格子
   EControlType_GridCellNumber,
   // 表格文本格子
   EControlType_GridCellText,
   // 表格行
   EControlType_GridRow,
   // 表格列
   EControlType_GridColumn,
   // 表格复选框
   EControlType_GridColumnCheck,
   // 表格数字
   EControlType_GridColumnNumber,
   // 表格文本列
   EControlType_GridColumnText,
   // 表格
   EControlType_GridView,
   // 原件
   EControlType_Widget,
   // 停靠栏
   EControlType_Bar,
   // 表单
   EControlType_Form,
   // 表格
   EControlType_Table,
   // 窗体
   EControlType_Window,
};

//==========================================================
// <T>显示控件标志</T>
//==========================================================
enum EControlFlag{
   // 支持关联
   EControlFlag_Link = 0x00000001,
   // 有效性
   EControlFlag_Enable = 0x00000002,
   // 可见性
   EControlFlag_Visible = 0x00000004,
   // 支持内空白
   EControlFlag_Margin = 0x00000008,
   // 支持外空白
   EControlFlag_Padding = 0x00000010,
   // 显示边框
   EControlFlag_BorderInner = 0x00000020,
   // 显示边框
   EControlFlag_BorderOuter = 0x00000040,
   // 支持属性
   EControlFlag_Property = 0x00000080,
   // 支持数据
   EControlFlag_Data = 0x00000100,
   // 支持边框层
   EControlFlag_LayerBorder = 0x00001000,
   // 支持前景层
   EControlFlag_LayerFore = 0x00002000,
   // 支持背景层
   EControlFlag_LayerGround = 0x00004000,
   // 支持后景层
   EControlFlag_LayerBack = 0x00008000,
   // 支持控件标签
   EControlFlag_ControlLabel = 0x00010000,
   // 支持控件数字
   EControlFlag_ControlNumber = 0x00020000,
   // 支持控件多行
   EControlFlag_ControlLines = 0x00040000,
   // 支持控件页面
   EControlFlag_ControlHtml = 0x00080000,
   // 支持字体
   EControlFlag_ControlFont = 0x00100000,
   // 支持图标
   EControlFlag_ControlIcon = 0x00200000,
};

//============================================================
// <T>控件停靠。</T>
//============================================================
enum EControlDock{
   // 无
   EControlDock_None = 0,
   // 左
   EControlDock_Left = 1,
   // 左上
   EControlDock_LeftTop = 2,
   // 上
   EControlDock_Top = 3,
   // 右上
   EControlDock_RightTop = 4,
   // 右
   EControlDock_Right = 5,
   // 右下
   EControlDock_RightDown = 6,
   // 下
   EControlDock_Down = 7,
   // 左下
   EControlDock_LeftDown = 8,
   // 中间
   EControlDock_Center = 9,
   // 填充
   EControlDock_Fill = 10,
   // 鼠标
   EControlDock_Mouse = 11,
};

//==========================================================
// <T>显示控件标志</T>
//==========================================================
enum EControlImageAlign{
   // 无
   EControlImageAlign_None = 0,
   // 左
   EControlImageAlign_Left = 1,
   // 左上
   EControlImageAlign_LeftTop = 2,
   // 上
   EControlImageAlign_Top = 3,
   // 右上
   EControlImageAlign_RightTop = 4,
   // 右
   EControlImageAlign_Right = 5,
   // 右下
   EControlImageAlign_RightBottom = 6,
   // 下
   EControlImageAlign_Bottom = 7,
   // 左下
   EControlImageAlign_LeftBottom = 8,
   // 中间
   EControlImageAlign_Center = 9,
   // 周围空白
   EControlImageAlign_Padding = 10,
   // 九宫格
   EControlImageAlign_Square = 11,
};

//============================================================
// <T>控件文本对齐。</T>
//============================================================
enum EControlTextAlign{
   // 未知
   EControlTextAlign_None = 0,
   // 左对齐
   EControlTextAlign_Left = 1,
   // 中间对齐
   EControlTextAlign_Center = 2,
   // 右对齐
   EControlTextAlign_Right = 3,
};

//============================================================
// <T>界面线样式。</T>
//============================================================
enum EUiLineStyle{
   EUiLineStyle_Solid,
};

//============================================================
// <T>界面线。</T>
//============================================================
struct MO_EF_DECLARE SUiLine{
public:
   TColor color;
   TInt width;
   EUiLineStyle styleCd;
public:
   SUiLine();
public:
   TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>界面边框。</T>
//============================================================
struct MO_EF_DECLARE SUiBorder{
public:
   SUiLine left;
   SUiLine top;
   SUiLine right;
   SUiLine bottom;
public:
   SUiBorder();
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty(){
      return (left.width == 0) && (top.width == 0) && (right.width == 0) && (bottom.width == 0);
   }
public:
   TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>交界面字体。</T>
//============================================================
struct MO_EF_DECLARE SUiFont{
public:
   TString fontName;
   TInt color;
   TInt size;
   TBool fix;
   TBool bold;
   TBool italic;
   TBool strikeout;
   TBool underline;
public:
   SUiFont();
public:
   TResult Unserialize(IDataInput* pInput);
public:
   void SaveInfo(SFontInfo& info);
};

//============================================================
// <T>界面图片。</T>
//============================================================
struct MO_EF_DECLARE SUiPicture{
public:
   TResourceId resourceId;
   EControlImageAlign alignCd;
   SIntPoint2 location;
   SIntSize2 size;
   SIntPadding padding;
public:
   SUiPicture();
public:
   //------------------------------------------------------------
   // <T>判断是否有效。</T>
   MO_INLINE TBool IsValid(){
      return (resourceId != 0);
   }
public:
   TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>界面画板。</T>
//============================================================
class MO_EF_DECLARE FUiCanvas : public FBitmapCanvas{
public:
   FUiCanvas();
   MO_ABSTRACT ~FUiCanvas();
public:
   TResult DrawBorder(SIntRectangle* pRectangle, SUiBorder* pBorder);
};

//============================================================
// <T>界面工具类。</T>
//============================================================
class MO_EF_DECLARE RUiUtil{
public:
   static TResult CalculateDock(SIntPoint2* pLocation, SIntRectangle* pClientRect, SIntSize2* pTargetSize, EControlDock dockCd, SIntPadding* pDockPadding = NULL);
};

//============================================================
// <T>控件对象。</T>
//============================================================
class MO_EF_DECLARE FUiControl : public FDisplay{
protected:
   EControlType _controlCd;
   TString _className;
   TInt _flags;
   TBool _optionEnable;
   TBool _optionVisible;
   TWideString _label;
   EControlDock _dockCd;
   SIntPadding _margin;
   SIntPadding _padding;
   SUiBorder _borderOuter;
   SUiBorder _borderInner;
   TColor _foreColor;
   SUiPicture _forePicture;
   TColor _backColor;
   SUiPicture _backPicture;
   SIntRectangle _clientRectangle;
   SIntRectangle _statusRectangle;
protected:
   FMaterial* _pMaterial;
   FUiCanvas* _pCanvas;
public:
   FUiControl();
   MO_ABSTRACT ~FUiControl();
public:
   //------------------------------------------------------------
   // <T>获得控件类型。</T>
   MO_INLINE EControlType ControlCd(){
      return _controlCd;
   }
   //------------------------------------------------------------
   // <T>获得类型名称。</T>
   MO_INLINE TCharC* ClassName(){
      return _className;
   }
   //------------------------------------------------------------
   // <T>获得标志集合。</T>
   MO_INLINE TInt Flags(){
      return _flags;
   }
   //------------------------------------------------------------
   // <T>测试标志。</T>
   MO_INLINE TBool TestFlag(TInt flag){
      return (_flags & flag) == flag;
   }
   //------------------------------------------------------------
   // <T>获得标签。</T>
   MO_INLINE TWideCharC* Label(){
      return _label;
   }
   //------------------------------------------------------------
   // <T>设置标签。</T>
   MO_INLINE void SetLabel(TWideCharC* pLabel){
      _label = pLabel;
   }
   //------------------------------------------------------------
   // <T>获得停靠类型。</T>
   MO_INLINE EControlDock DockCd(){
      return _dockCd;
   }
   //------------------------------------------------------------
   // <T>设置停靠类型。</T>
   MO_INLINE void SetDockCd(EControlDock dockCd){
      _dockCd = dockCd;
   }
   //------------------------------------------------------------
   // <T>获得内空白。</T>
   MO_INLINE SIntPadding& Margin(){
      return _margin;
   }
   //------------------------------------------------------------
   // <T>获得外空白。</T>
   MO_INLINE SIntPadding& Padding(){
      return _padding;
   }
   //------------------------------------------------------------
   // <T>获得外边框。</T>
   MO_INLINE SUiBorder& BorderOuter(){
      return _borderOuter;
   }
   //------------------------------------------------------------
   // <T>获得内边框。</T>
   MO_INLINE SUiBorder& BorderInner(){
      return _borderInner;
   }
public:
   //------------------------------------------------------------
   // <T>设置二维坐标。</T>
   MO_INLINE void SetLocationInt2(TInt x, TInt y){
      _location.Set((TFloat)x, (TFloat)y);
   }
public:
   FUiControl* ControlGet(TInt index, EControlType controlCd);
public:
   MO_ABSTRACT TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnFocusTest(FFocusTester* pTester);
public:
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
public:
   MO_OVERRIDE FMaterial* Material();
   MO_OVERRIDE TResult CalculateRectangle(SIntRectangle* pRectangle);
   MO_ABSTRACT TResult CalculateClientRectangle(SIntRectangle* pRectangle);
   MO_OVERRIDE TResult CalculateRenderable(SRenderable& renderable);
   MO_ABSTRACT FUiCanvas* Canvas();
   MO_OVERRIDE TResult Free();
};
//------------------------------------------------------------
typedef FVector<FUiControl*> FUiControlVector;
typedef FList<FUiControl*> FUiControlList;

//============================================================
// <T>控件容器。</T>
//============================================================
class MO_EF_DECLARE FUiControlContainer : public FUiControl{
public:
   FUiControlContainer();
   MO_ABSTRACT ~FUiControlContainer();
public:
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>控件缓冲池。</T>
//============================================================
class MO_EF_DECLARE FUiControlPool : public FPool<FUiControl*>{
protected:
   EControlType _typeCd;
   TCharC* _pClassName;
public:
   FUiControlPool();
   MO_ABSTRACT ~FUiControlPool();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE EControlType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>获得类名称。</T>
   MO_INLINE TCharC* ClassName(){
      return _pClassName;
   }
public:
   MO_VIRTUAL FUiControl* Create() = 0;
public:
   FUiControl* Alloc();
   void Free(FUiControl* pControl);
};
//------------------------------------------------------------
typedef FVector<FUiControlPool*> FUiControlPoolCollection;

//==========================================================
#define MO_DEF_CONTROL_POOL(D, P, C, T, N) \
   class D P : public FUiControlPool{ \
   public: \
      P(){ \
         _typeCd = T; \
         _pClassName = N; \
      } \
   public: \
      FUiControl* Create(){ \
         return MO_CREATE(C); \
      } \
   }; \

//============================================================
// <T>控件控制台。</T>
//============================================================
class MO_EF_DECLARE FUiControlConsole : public FConsole{
protected:
   // 控件缓冲池集合
   FUiControlPoolCollection* _pPools;
public:
   FUiControlConsole();
   MO_ABSTRACT ~FUiControlConsole();
public:
   //------------------------------------------------------------
   // <T>获得控件缓冲池集合。</T>
   MO_INLINE FUiControlPoolCollection* Pools(){
      return _pPools;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_ABSTRACT FUiControlPool* PoolFind(EControlType typeCd, TCharC* pClassName = NULL);
   MO_ABSTRACT TResult PoolRegister(FUiControlPool* pPool);
   MO_ABSTRACT TResult PoolUnregister(FUiControlPool* pPool);
public:
   FUiControl* Alloc(EControlType typeCd, TCharC* pClassName = NULL);
   TResult Free(FUiControl* pControl);
public:
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>顶层容器。</T>
//============================================================
class MO_EF_DECLARE FUiFrame : public FUiControlContainer{
protected:
   TControlId _code;
   SIntPadding _dockPadding;
public:
   FUiFrame();
   MO_ABSTRACT ~FUiFrame();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TControlId Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE void SetCode(TControlId code){
      _code = code;
   }
public:
   MO_OVERRIDE TResult OnUnserialize(IDataInput* pInput);
   MO_OVERRIDE TResult OnPaint();
   MO_ABSTRACT TResult OnShow();
   MO_ABSTRACT TResult OnHide();
public:
   TResult Show();
   TResult Hide();
};
//------------------------------------------------------------
typedef MO_EF_DECLARE FSet<TControlId, FUiFrame*> FUiFrameSet;

//============================================================
// <T>控件控制台。</T>
//============================================================
class MO_EF_DECLARE FUiFrameConsole : public FConsole{
protected:
   // 控件缓冲池集合
   FUiControlPoolCollection* _pPools;
   // 帧集合
   FUiFrameSet* _pFrames;
public:
   FUiFrameConsole();
   MO_ABSTRACT ~FUiFrameConsole();
public:
   //------------------------------------------------------------
   // <T>获得控件缓冲池集合。</T>
   MO_INLINE FUiControlPoolCollection* Pools(){
      return _pPools;
   }
   //------------------------------------------------------------
   // <T>获得页面集合。</T>
   MO_INLINE FUiFrameSet* Frames(){
      return _pFrames;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Open();
public:
   FUiFrame* Find(TControlId frameId);
public:
   MO_ABSTRACT TResult Dispose();
};

//============================================================
// <T>控件控制台。</T>
//============================================================
class MO_EF_DECLARE FUiFaceConsole : public FConsole{
protected:
   // 控件控制台
   FUiControlConsole* _pControlConsole;
   // 页面控制台
   FUiFrameConsole* _pFrameConsole;
public:
   FUiFaceConsole();
   MO_ABSTRACT ~FUiFaceConsole();
public:
   //------------------------------------------------------------
   // <T>获得控件缓冲池集合。</T>
   MO_INLINE FUiControlConsole* ControlConsole(){
      return _pControlConsole;
   }
   //------------------------------------------------------------
   // <T>获得页面控制台。</T>
   MO_INLINE FUiFrameConsole* FrameConsole(){
      return _pFrameConsole;
   }
public:
   MO_ABSTRACT TResult Setup();
   MO_ABSTRACT TResult Open();
};

//============================================================
// <T>界面管理器。</T>
//============================================================
class MO_EF_DECLARE RFaceManager : public RSingleton<FUiFaceConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EF_DISPLAY_H__
