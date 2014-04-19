using MO.Common.Geom;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Core.Forms.Geom;
using MO.Design2d.Frame.Common;
using MO.Direct2d.Common;
using MO.Direct2d.Device;
using MO.Direct2d.Draw;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Core
{
   //============================================================
   // <T>表单设计器。</T>
   //============================================================
   public class FUiFormDesigner : FObject
   {
      // 设置过
      protected bool _setuped = false;

      // 环境
      protected FUiContext _context = new FUiContext();

      // 尺寸
      protected SIntSize2 _size = new SIntSize2();

      // 格子尺寸
      protected SIntSize2 _cellSize = new SIntSize2();

      // 设计底版
      protected Panel _designPanel;

      // 界面表单
      protected FUiFrame _designFrame;

      // 控件选中器
      protected FUiControlSelection _selection = new FUiControlSelection();

      // 设计模式
      protected int _designMode = 0;

      // 设计参考图层
      protected bool _designLayers = true;

      // 设计背景
      protected bool _designBack = false;

      // 设计背景颜色
      protected Color _designBackColor = Color.Black;

      // 设计背景颜色色刷
      protected FUiColor _designBackBrush;

      // 助手对齐方式
      protected int _aideAlignCd = 0;

      // 鼠标落下点
      protected SIntPoint2 _mouseDownPoint = new SIntPoint2();

      // 辅助线宽
      protected float _lineWidth = 1.0f;

      // 辅助线刷
      protected FDxSolidBrush _lineForeBrush;

      // 辅助线样式
      protected FDxStrokeStyle _lineForeStrokeStyle;

      // 辅助线刷
      protected FDxSolidBrush _lineBackBrush;

      // 背景刷
      protected FDxBitmapBrush _backBrush = new FDxBitmapBrush();

      // 历史
      protected FUiFormHistory _history = new FUiFormHistory();

      //============================================================
      // <T>表单设计器。</T>
      //============================================================
      public FUiFormDesigner() {
         _context.Context = new FDxContext2d();
         _context.Selection = _selection;
      }

      //============================================================
      // <T>获得环境。</T>
      //============================================================
      public FUiContext Context {
         get { return _context; }
      }

      //============================================================
      // <T>获得或设置设备。</T>
      //============================================================
      public FDxDevice2d Device {
         get { return _context.Device; }
         set {
            // 设置设备
            _context.Device = value;
            // 设置环境
            _context.Context.Device = value;
         }
      }

      //============================================================
      // <T>获得格子尺寸。</T>
      //============================================================
      public SIntSize2 CellSize {
         get { return _cellSize; }
      }

      //============================================================
      // <T>获得历史。</T>
      //============================================================
      public FUiFormHistory History {
         get { return _history; }
      }

      //============================================================
      // <T>设置组件处理。</T>
      //============================================================
      public void SetupComponent(FUiComponent component) {
         SUiSetupArgs args = new SUiSetupArgs();
         args.context = _context;
         component.Setup(args);
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public void Setup() {
         // 设置环境
         _context.Setup();
         // 创建辅助线刷
         _designBackBrush = _context.BuildDesignColor(_designBackColor);
         _lineForeBrush = _context.Device.CreateSolidBrush(1.0f, 1.0f, 1.0f, 1.0f);
         _lineBackBrush = _context.Device.CreateSolidBrush(0.0f, 0.0f, 0.0f, 1.0f);
         _lineForeStrokeStyle = _context.Device.CreateStrokeStyle(EDxCapStyle.Flat, EDxCapStyle.Flat, EDxCapStyle.Flat, EDxDashStyle.Custom, new float[] { 6, 2 });
         // 创建背景刷
         _backBrush.Device = _context.Device;
         using(System.Drawing.Bitmap bitmap = new System.Drawing.Bitmap(16, 16)) {
            for(int y = 0; y < 16; y++) {
               for(int x = 0; x < 16; x++) {
                  if(((x < 8) && (y < 8)) || ((x > 8) && (y > 8))) {
                     bitmap.SetPixel(x, y, System.Drawing.Color.White);
                  } else {
                     bitmap.SetPixel(x, y, System.Drawing.Color.LightGray);
                  }
               }
            }
            _backBrush.LoadBitmap(bitmap);
         }
         _backBrush.Setup();
         _setuped = true;
      }

      //============================================================
      // <T>获得或设置设计底板。</T>
      //============================================================
      public Panel DesignPanel {
         get { return _designPanel; }
         set { _designPanel = value; }
      }

      //============================================================
      // <T>获得或设置设计表单。</T>
      //============================================================
      public FUiFrame DesignForm {
         get { return _designFrame; }
         set {
            _designFrame = value;
            _designFrame.DesignTop = true;
            _designFrame.DesignBorder = true;
            _context.TopContainer = value;
            // 设置显示层
            _designLayers = value.DesignLayers;
            _designBack = value.DesignBack;
            _designBackColor = value.DesignBackColor;
            _designBackBrush = _context.BuildDesignColor(_designBackColor);
         }
      }

      //============================================================
      // <T>获得或设置缩放。</T>
      //============================================================
      public float DesignScale {
         get { return _context.Scale; }
         set {
            // 检查缩放
            if(_context.Scale != value) {
               // 设置缩放
               _context.Scale = value;
               // 刷新处理
               Refresh();
               Paint();
            }
         }
      }

      //============================================================
      // <T>获得或设置焦点控件。</T>
      //============================================================
      public FUiControlSelection Selection {
         get { return _selection; }
         set { _selection = value; }
      }

      //============================================================
      // <T>获得或设置焦点控件。</T>
      //============================================================
      public FUiControl FocusControl {
         get { return _selection.FocusControl; }
         set { _selection.FocusControl = value; }
      }

      //============================================================
      // <T>获得设计模式。</T>
      //============================================================
      public int DesignMode {
         get { return _designMode; }
      }

      //============================================================
      // <T>获得或设置设计图层。</T>
      //============================================================
      public bool DesignLayers {
         get { return _designLayers; }
         set {
            _designLayers = value;
            if(_designFrame != null) {
               _designFrame.DesignLayers = value;
            }
         }
      }

      //============================================================
      // <T>获得或设置设计背景。</T>
      //============================================================
      public bool DesignBack {
         get { return _designBack; }
         set { 
            _designBack = value; 
            if(_designFrame != null){
               _designFrame.DesignBack = value;
            }
         }
      }

      //============================================================
      // <T>获得或设置设计背景颜色。</T>
      //============================================================
      public Color DesignBackColor {
         get { return _designBackColor; }
         set { 
            _designBackColor = value;
            _designBackBrush = _context.BuildDesignColor(value);
            if(_designFrame != null) {
               _designFrame.DesignBackColor = value;
            }
         }
      }

      //============================================================
      // <T>获得对齐方式。</T>
      //============================================================
      public int AideAlignCd {
         get { return _aideAlignCd; }
      }

      //============================================================
      // <T>测试点击信息。</T>
      //============================================================
      public SUiTestArgs TestPoint(int x, int y, SUiTestArgs args = null) {
         if (args == null) {
            args = new SUiTestArgs();
         }
         args.Point.Set(x, y);
         _designFrame.Test(args);
         return args;
      }

      //============================================================
      // <T>设置显示边框。</T>
      //
      // @param value 内容
      //============================================================
      public void DoActionBorder(bool value) {
         SUiAction action = new SUiAction();
         action.actionCd = EUiAction.DisplayBorder;
         action.value = value;
         _designFrame.Action(action);
      }

      //============================================================
      // <T>更新游标。</T>
      //============================================================
      public void UpdateCursor(int x, int y) {
         // 测试对齐方式
         _aideAlignCd = (int)ERcAlign.None;
         if(null != _selection.FocusControl) {
            _aideAlignCd = _selection.FocusControl.Designer.TestAlignCd(x, y);
         }
         // 设置游标
         switch ((ERcAlign)_aideAlignCd) {
            case ERcAlign.Center:
               _designPanel.Cursor = Cursors.SizeAll;
               break;
            case ERcAlign.Left:
            case ERcAlign.Right:
               _designPanel.Cursor = Cursors.SizeWE;
               break;
            case ERcAlign.Top:
            case ERcAlign.Bottom:
               _designPanel.Cursor = Cursors.SizeNS;
               break;
            case ERcAlign.LeftTop:
            case ERcAlign.RightBottom:
               _designPanel.Cursor = Cursors.SizeNWSE;
               break;
            case ERcAlign.RightTop:
            case ERcAlign.LeftBottom:
               _designPanel.Cursor = Cursors.SizeNESW;
               break;
            case ERcAlign.None:
               _designPanel.Cursor = Cursors.Default;
               break;
         }
      }

      //============================================================
      // <T>改变大小。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void Resize(int width, int height) {
         // 设置大小
         _size.Set(width, height);
         // 刷新数据
         Refresh();
         Paint();
      }

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      public void DrawPreviewLayers() {
         if(!_designLayers) {
            return;
         }
         FUiControlLayers layers = _designFrame.PreviewLayers;
         if(!layers.IsEmpty()) {
            float scale = _context.Scale;
            FDxContext2d context = _context.Context;
            SIntPoint2 position = _designFrame.CalculateDisplayPosition();
            int count = layers.Count;
            for(int n = count - 1; n >= 0; n--) {
               FUiControlLayer layer = layers[n];
               if(layer.OptionVisible) {
                  context.DrawBitmap(layer.Bitmap, position.X, position.Y, (int)(layer.Size.Width * scale), (int)(layer.Size.Height * scale), 1.0f);
               }
            }
         }
      }

      //============================================================
      // <T>绘制辅助线。</T>
      //============================================================
      public bool DrawAidLines() {
         // 检查状态
         FUiControl focusControl = _selection.FocusControl;
         if(focusControl == null) {
            return false;
         }
         FUiControl parent = focusControl.Parent as FUiControl;
         if(parent == null) {
            return false;
         }
         // 获得环境
         float scale = _context.Scale;
         FDxContext2d context = _context.Context;
         context.TransformIdentity();
         // 绘制对齐线
         SIntPoint2 location = focusControl.CalculateDisplayPosition();
         SIntSize2 size = focusControl.CalculateDisplaySize();
         foreach(FUiComponent component in parent.Components) {
            FUiControl control = component as FUiControl;
            // 检查变量
            if(control == null) {
               continue;
            }
            if(control == focusControl) {
               continue;
            }
            // 计算坐标
            SIntPoint2 findLocation = control.CalculateDisplayPosition();
            SIntSize2 findSize = control.CalculateDisplaySize();
            // 左辅助线
            if(location.X == findLocation.X) {
               context.DrawLineLayer(location.X, 0, location.X, _size.Height, _lineBackBrush, _lineForeBrush, _lineWidth, _lineForeStrokeStyle);
            }
            // 上辅助线
            if(location.Y == findLocation.Y) {
               context.DrawLineLayer(0, location.Y, _size.Width, location.Y, _lineBackBrush, _lineForeBrush, _lineWidth, _lineForeStrokeStyle);
            }
            // 右辅助线
            int right = findLocation.X + findSize.Width;
            if(location.X + size.Width == right) {
               context.DrawLineLayer(right, 0, right, _size.Height, _lineBackBrush, _lineForeBrush, _lineWidth, _lineForeStrokeStyle);
            }
            // 下辅助线
            int bottom = findLocation.Y + findSize.Height;
            if(location.Y + size.Height == bottom) {
               context.DrawLineLayer(0, bottom, _size.Width, bottom, _lineBackBrush, _lineForeBrush, _lineWidth, _lineForeStrokeStyle);
            }
         }
         return true;
      }

      //============================================================
      // <T>开始设计。</T>
      //
      // @param x 横坐标
      // @param y 纵坐标
      // @param modeEnable 模式切换
      // @param appendMode 追加模式
      // @param testArgs 测试参数
      //============================================================
      public void DesignBegin(int x, int y, bool modeEnable, bool appendMode, SUiTestArgs testArgs = null) {
         FUiControl focusControl = _selection.FocusControl;
         // 设置落点
         _mouseDownPoint.Set(x, y);
         // 更新游标
         UpdateCursor(x, y);
         // 设计模式
         switch ((ERcAlign)_aideAlignCd) {
            case ERcAlign.Center:
               if (modeEnable) {
                  _designMode = EUiDesignMode.Move;
                  _selection.BoundsStore();
               }
               break;
            case ERcAlign.Left:
            case ERcAlign.Right:
            case ERcAlign.Top:
            case ERcAlign.Bottom:
            case ERcAlign.LeftTop:
            case ERcAlign.RightBottom:
            case ERcAlign.RightTop:
            case ERcAlign.LeftBottom:
               if (modeEnable) {
                  _designMode = EUiDesignMode.Size;
                  _selection.BoundsStore();
               }
               break;
            case ERcAlign.None:
               _designMode = EUiDesignMode.None;
               // 选择控件
               SUiTestArgs args = TestPoint(x, y, testArgs);
               FUiControl selectControl = args.Control;
               if(appendMode && (selectControl != null) && _selection.HasSelectControl()) {
                  // 追加模式下，只能增加同父亲的对象
                  if(selectControl.Parent == focusControl.Parent) {
                     _selection.AddSelectControl(selectControl);
                  }
               } else {
                  _selection.FocusControl = selectControl;
               }
               break;
         }
      }

      //============================================================
      // <T>更新设计。</T>
      //============================================================
      public void DesignUpdate(int x, int y) {
         float scale = _context.Scale;
         FUiControl focusControl = _selection.FocusControl;
         // 偏移位置
         int cx = x - _mouseDownPoint.X;
         int cy = y - _mouseDownPoint.Y;
         // 设计更新
         switch(_designMode) {
            case EUiDesignMode.None:
               UpdateCursor(x, y);
               break;
            case EUiDesignMode.Move: {
                  int cleft = (int)(cx / scale) / _cellSize.Width * _cellSize.Width;
                  int ctop = (int)(cy / scale) / _cellSize.Height * _cellSize.Height;
                  _selection.BoundsLocation(cleft, ctop);
                  break;
               }
            case EUiDesignMode.Size: { 
               int left = 0;
               int top = 0;
               int width = 0;
               int height = 0;
               switch ((ERcAlign)_aideAlignCd) {
                  case ERcAlign.Left:
                     left += cx;
                     width -= cx;
                     break;
                  case ERcAlign.LeftTop:
                     left += cx;
                     width -= cx;
                     top += cy;
                     height -= cy;
                     break;
                  case ERcAlign.Top:
                     top += cy;
                     height -= cy;
                     break;
                  case ERcAlign.RightTop:
                     top += cy;
                     width += cx;
                     height -= cy;
                     break;
                  case ERcAlign.Right:
                     width += cx;
                     break;
                  case ERcAlign.RightBottom:
                     width += cx;
                     height += cy;
                     break;
                  case ERcAlign.Bottom:
                     height += cy;
                     break;
                  case ERcAlign.LeftBottom:
                     left += cx;
                     width -= cx;
                     height += cy;
                     break;
               }
               int cleft = (int)(left / scale) / _cellSize.Width * _cellSize.Width;
               int ctop = (int)(top / scale) / _cellSize.Height * _cellSize.Height;
               int cwidth = (int)(width / scale) / _cellSize.Width * _cellSize.Width;
               int cheight = (int)(height / scale) / _cellSize.Height * _cellSize.Height;
               _selection.BoundsSet(cleft, ctop, cwidth, cheight);
               break;
            }
         }
         // 刷新处理
         Refresh();
      }

      //============================================================
      // <T>结束设计。</T>
      //============================================================
      public void DesignEnd(int x, int y) {
         _designMode = EUiDesignMode.None;
         // 保存历史
         HistorySave();
      }

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param sender 发送者
      // @param e:event 事件
      //============================================================
      public void Paint() {
         // 检查设置
         if(!_setuped) {
            return;
         }
         // 获得设备
         FDxDevice2d device = _context.Device;
         FDxContext2d context = _context.Context;
         context.TransformIdentity();
         // 开始绘制
         device.BeginDraw();
         // 清空目标
         context.Clear();
         // 填充背景
         if(_designBack){
            context.FillRectangle(_designBackBrush.brush, 0, 0, _size.Width, _size.Height);
         }else{
            context.FillRectangle(_backBrush, 0, 0, _size.Width, _size.Height);
         }
         // 绘制预览层
         DrawPreviewLayers();
         // 绘制表单
         SUiDrawArgs args = new SUiDrawArgs();
         args.Context = context;
         if(_designFrame.DesignVisible) {
            // 绘制表单
            context.TransformIdentity();
            _designFrame.Draw(args);
            // 绘制下拉部分
            if (args.DropControl != null) {
               context.TransformIdentity();
               args.DropControl.Draw(args);
            }
         }
         // 绘制辅助器
         if(_selection.HasFocusControl()) {
            // 绘制辅助线
            DrawAidLines();
            // 绘制辅助器
            _designFrame.DrawDesign(args);
         }
         // 结束绘制
         device.EndDraw();
      }

      //============================================================
      // <T>刷新处理。</T>
      //============================================================
      public void Refresh() {
         SIntSize2 size = _designFrame.Size;
         float scale = _context.Scale;
         int left = (_size.Width - (int)(size.Width * scale)) / 2;
         if(left < 0) {
            left = 0;
         }
         int top = (_size.Height - (int)(size.Height * scale)) / 2;
         if(top < 0) {
            top = 0;
         }
         _designFrame.Location.Set(left, top);
      }

      //============================================================
      // <T>加载历史。</T>
      //============================================================
      public void HistoryLoad(FUiFormHistoryStep step) {
         //FXmlNode xconfig = step.Config;
         //// 设置属性
         //FUiFrame form = RDesign2dFace.UiConsole.CreateComponent(xconfig.Name) as FUiFrame;
         //form.Id = _designFrame.Id;
         //form.Name = _designFrame.Name;
         //form.Label = _designFrame.Label;
         //form.Directory = _designFrame.Directory;
         //form.FileName = _designFrame.FileName;
         //form.LoadConfig(xconfig);
         //// 放入列表
         //RDesign2dFace.UiConsole.Containers.Replace(_designFrame, form);
         //RDesign2dFace.UiConsole.ContainerNames.Set(form.Name, form);
         //// 构造表单
         //SUiSetupArgs args = new SUiSetupArgs();
         //args.context = _context;
         //form.Setup(args);
         //// 设置表单
         //_designFrame.Dispose();
         //_designFrame = form;
         // 绘制表单
         Paint();
      }

      //============================================================
      // <T>保存历史。</T>
      //============================================================
      public void HistorySave() {
         //// 创建历史
         //FUiFormHistoryStep step = new FUiFormHistoryStep();
         //_designFrame.SaveConfig(step.Config);
         //// 检查是否发生变化
         //bool changed = true;
         //FUiFormHistoryStep lastStep = _history.Last;
         //if(lastStep != null) {
         //   string xml = step.Config.Xml;
         //   string lastXml = lastStep.Config.Xml;
         //   if(xml == lastXml) {
         //      changed = false;
         //   }
         //}
         //// 增加历史
         //if(changed) {
         //   _history.Steps.Push(step);
         //}
      }

      //============================================================
      // <T>存储配置信息。</T>
      //============================================================
      public void Store() {
         _designFrame.FrameResource.Store();
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public void Dispose() {
         if(_context != null){
            if(_context.Device != null){
               _context.Device.Dispose();
               _context.Device = null;
            }
            _context = null;
         }
      }
   }
}
