using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Theme;
using MO.Core.Forms.Common;
using MO.Core.Forms.Geom;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面控件。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FRcControl : FRcComponent
   {
      // 设置是否可见
      protected bool _optionVisible = true;

      // 设置是否可用
      protected bool _optionEnable = true;

      // 码头类型
      protected ERcDock _dockCd = ERcDock.None;

      // 坐标
      protected SIntPoint2 _location = new SIntPoint2(0, 0);

      // 尺寸
      protected SIntSize2 _size = new SIntSize2(200, 100);

      // 外框空白
      protected FIntPadding _margin = new FIntPadding();

      // 内框空白
      protected FIntPadding _padding = new FIntPadding();

      // 外边框
      protected FRcBorder _borderOuter = new FRcBorder();

      // 外边框属性
      protected FTplThemeStyleProperty _propertyBorderOuter;

      // 内边框
      protected FRcBorder _borderInner = new FRcBorder();

      // 内边框属性
      protected FTplThemeStyleProperty _propertyBorderInner;

      // 前景颜色
      protected int _foreColor;

      // 前景颜色属性
      protected FTplThemeStyleProperty _propertyForeColor;

      // 前景资源
      protected FRcPicture _foreResource = new FRcPicture();

      // 后景颜色
      protected int _backColor;

      // 后景颜色属性
      protected FTplThemeStyleProperty _propertyBackColor;

      // 后景资源
      protected FRcPicture _backResource = new FRcPicture();

      // 鼠标点击事件
      protected string _onClick;

      // 鼠标双击事件
      protected string _onDoubleClick;

      // 鼠标落下事件
      protected string _onMouseDown;

      // 鼠标移动事件
      protected string _onMouseMove;

      // 鼠标抬起事件
      protected string _onMouseUp;

      // 鼠标进入事件
      protected string _onMouseEnter;

      // 鼠标离开事件
      protected string _onMouseLeave;

      //============================================================
      // <T>构造界面控件。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcControl(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置是否可见。</T>
      //============================================================
      public bool OptionVisible {
         get { return _optionVisible; }
         set { _optionVisible = value; }
      }

      //============================================================
      // <T>获得或设置是否可用。</T>
      //============================================================
      public bool OptionEnable {
         get { return _optionEnable; }
         set { _optionEnable = value; }
      }

      //============================================================
      // <T>获得或设置显示类型。</T>
      //============================================================
      public ERcDock DockCd {
         get { return _dockCd; }
         set { _dockCd = value; }
      }

      //============================================================
      // <T>获得坐标。</T>
      //============================================================
      public SIntPoint2 Location {
         get { return _location; }
         set { _location.Assign(value); }
      }

      //============================================================
      // <T>获得尺寸。</T>
      //============================================================
      public SIntSize2 Size {
         get { return _size; }
         set { _size.Assign(value); }
      }

      //============================================================
      // <T>获得内空白。</T>
      //============================================================
      public FIntPadding Margin {
         get { return _margin; }
         set { _margin.Assign(value); }
      }

      //============================================================
      // <T>获得外空白。</T>
      //============================================================
      public FIntPadding Padding {
         get { return _padding; }
         set { _padding.Assign(value); }
      }

      //============================================================
      // <T>获得或设置外边框。</T>
      //============================================================
      public FRcBorder BorderOuter {
         get { return _borderOuter; }
         set { _borderOuter = value; }
      }

      //============================================================
      // <T>获得或设置内边框。</T>
      //============================================================
      public FRcBorder BorderInner {
         get { return _borderInner; }
         set { _borderInner = value; }
      }

      //============================================================
      // <T>获得或设置前景颜色。</T>
      //============================================================
      public int ForeColor {
         get { return _foreColor; }
         set { _foreColor = value; }
      }

      //============================================================
      // <T>获得或设置前景资源。</T>
      //============================================================
      public FRcPicture ForeResource {
         get { return _foreResource; }
         set { _foreResource.Assign(value); }
      }

      //============================================================
      // <T>获得或设置后景颜色。</T>
      //============================================================
      public int BackColor {
         get { return _backColor; }
         set { _backColor = value; }
      }

      //============================================================
      // <T>获得或设置背景资源。</T>
      //============================================================
      public FRcPicture BackResourceId {
         get { return _backResource; }
         set { _backResource.Assign(value); }
      }

      //============================================================
      // <T>获得或设置鼠标点击事件。</T>
      //============================================================B
      public string OnClick {
         get { return _onClick; }
         set { _onClick = value; }
      }

      //============================================================
      // <T>获得或设置鼠标双击事件。</T>
      //============================================================
      public string OnDoubleClick {
         get { return _onDoubleClick; }
         set { _onDoubleClick = value; }
      }

      //============================================================
      // <T>获得或设置鼠标落下事件。</T>
      //============================================================
      public string OnMouseDown {
         get { return _onMouseDown; }
         set { _onMouseDown = value; }
      }

      //============================================================
      // <T>获得或设置鼠标抬起事件。</T>
      //============================================================
      public string OnMouseUp {
         get { return _onMouseUp; }
         set { _onMouseUp = value; }
      }

      //============================================================
      // <T>获得或设置鼠标进入事件。</T>
      //============================================================
      public string OnMouseEnter {
         get { return _onMouseEnter; }
         set { _onMouseEnter = value; }
      }

      //============================================================
      // <T>获得或设置鼠标移动事件。</T>
      //============================================================
      public string OnMouseMove {
         get { return _onMouseMove; }
         set { _onMouseMove = value; }
      }

      //============================================================
      // <T>获得或设置鼠标离开事件。</T>
      //============================================================
      public string OnMouseLeave {
         get { return _onMouseLeave; }
         set { _onMouseLeave = value; }
      }

      //============================================================
      // <T>获得横向中间坐标。</T>
      //============================================================
      public int CenterX {
         get { return _location.X + (_size.Width >> 1); }
         set { _location.X = value - (_size.Width >> 1); }
      }

      //============================================================
      // <T>获得纵向中间坐标。</T>
      //============================================================
      public int CenterY {
         get { return _location.Y + (_size.Height >> 1); }
         set { _location.Y = value - (_size.Height >> 1); }
      }

      //============================================================
      // <T>获得右坐标。</T>
      //============================================================
      public int Right {
         get { return _location.X + _size.Width; }
      }

      //============================================================
      // <T>获得下坐标。</T>
      //============================================================
      public int Bottom {
         get { return _location.Y + _size.Height; }
      }

      //============================================================
      // <T>设置边界。</T>
      //
      // @param x 横坐标
      // @param y 纵坐标
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void SetBounds(int x, int y, int width, int height) {
         _location.Set(x, y);
         _size.Set(width, height);
      }

      //============================================================
      // <T>计算全局坐标。</T>
      //
      // @param scale 缩放
      // @return 全局坐标
      //============================================================
      public virtual FIntPoint2 CalculatePosition() {
         FIntPoint2 point = new FIntPoint2();
         FRcObject find = this;
         while (find != null) {
            FRcControl control = find as FRcControl;
            if (control != null) {
               point.Add(control.Location);
            }
            find = find.Parent;
         }
         return point;
      }

      //============================================================
      // <T>计算全局坐标。</T>
      //
      // @param scale 缩放
      // @return 全局坐标
      //============================================================
      public virtual SIntRectangle CalculateRange() {
         // 获得最大范围
         int width = _size.Width;
         int height = _size.Height;
         if (HasComponment()) {
            foreach (FRcComponent component in _components) {
               FRcControl control = component as FRcControl;
               if (control != null) {
                  int right = control.Right;
                  if (right > width) {
                     width = right;
                  }
                  int bottom = control.Bottom;
                  if (bottom > height) {
                     height = bottom;
                  }
               }
            }
         }
         return new SIntRectangle(_location.X, _location.Y, width, height);
      }

      //============================================================
      // <T>获得格式化内容。</T>
      //
      // @return 格式化内容
      //============================================================
      public override string Format() {
         string format = base.Format();
         if (_optionLink) {
            format += "(";
            // 设置关联
            if (_optionLink) {
               format += "L";
            } else {
               format += "_";
            }
            format += ")";
         }
         return format;
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public override void LoadStyleProperty() {
         // 加载父样式信息
         base.LoadStyleProperty();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.control");
         if (style != null) {
            _propertyBorderInner = style.FindProperty("border_inner");
            _propertyBorderOuter = style.FindProperty("border_outer");
            _propertyForeColor = style.FindProperty("fore_color");
            _propertyBackColor = style.FindProperty("back_color");
         }
      }

      //============================================================
      // <T>加载样式内容。</T>
      //============================================================
      public override void LoadStyleValue() {
         // 加载父样式信息
         base.LoadStyleValue();
         // 加载内边框属性
         if (_propertyBorderInner != null) {
            _borderInner.LoadStyleProperty(_propertyBorderInner);
         }
         // 加载外边框属性
         if (_propertyBorderOuter != null) {
            _borderOuter.LoadStyleProperty(_propertyBorderOuter);
         }
         // 加载前景颜色属性
         if (_propertyForeColor != null) {
            _foreColor = _propertyForeColor.GetHex();
         }
         // 加载背景颜色属性
         if (_propertyBackColor != null) {
            _backColor = _propertyBackColor.GetHex();
         }
      }

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 加载配置
         _optionVisible = xconfig.GetBoolean("option_visible", _optionVisible);
         _optionEnable = xconfig.GetBoolean("option_enable", _optionEnable);
         // 加载属性
         if (xconfig.Contains("dock_cd")) {
            _dockCd = (ERcDock)REnum.ToValue(typeof(ERcDock), xconfig.Get("dock_cd"));
         }
         // 加载位置
         if (xconfig.Contains("location")) {
            _location.Parse(xconfig.Get("location"));
         }
         // 加载尺寸
         if (xconfig.Contains("size")) {
            _size.Parse(xconfig.Get("size"));
         }
         // 加载空白
         if (xconfig.Contains("margin")) {
            _margin.Parse(xconfig.Get("margin"));
         }
         if (xconfig.Contains("padding")) {
            _padding.Parse(xconfig.Get("padding"));
         }
         // 加载边框
         _borderInner.LoadConfig(xconfig, "border_inner");
         _borderOuter.LoadConfig(xconfig, "border_outer");
         // 加载前景
         _foreColor = xconfig.GetHex("fore_color", _foreColor);
         _foreResource.LoadConfig(xconfig, "fore");
         // 加载后景
         _backColor = xconfig.GetHex("back_color", _backColor);
         _backResource.LoadConfig(xconfig, "back");
         // 加载事件
         _onClick = xconfig.Get("on_click", null);
         _onDoubleClick = xconfig.Get("on_double_click", null);
         _onMouseDown = xconfig.Get("on_mouse_down", null);
         _onMouseUp = xconfig.Get("on_mouse_up", null);
         _onMouseEnter = xconfig.Get("on_mouse_enter", null);
         _onMouseMove = xconfig.Get("on_mouse_move", null);
         _onMouseLeave = xconfig.Get("on_mouse_leave", null);
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         // 存储配置
         xconfig.SetNvl("option_visible", _optionVisible);
         xconfig.SetNvl("option_enable", _optionEnable);
         // 保存属性
         if (_dockCd != ERcDock.None) {
            xconfig.SetNvl("dock_cd", REnum.ToString(typeof(ERcDock), _dockCd));
         }
         // 存储位置
         if (!_location.IsEmpty()) {
            xconfig.Set("location", _location.ToString());
         }
         // 存储尺寸
         if (!_size.IsEmpty()) {
            xconfig.SetNvl("size", _size.ToString());
         }
         // 加载空白
         if (!_margin.IsEmpty()) {
            xconfig.SetNvl("margin", _margin.ToString());
         }
         if (!_padding.IsEmpty()) {
            xconfig.SetNvl("padding", _padding.ToString());
         }
         // 存储边框
         if (_propertyBorderInner == null || (_propertyBorderInner != null && !_borderInner.EqualsStyleProperty(_propertyBorderInner))) {
            _borderInner.SaveConfig(xconfig, "border_inner");
         }
         if (_propertyBorderOuter == null || (_propertyBorderOuter != null && !_borderOuter.EqualsStyleProperty(_propertyBorderOuter))) {
            _borderOuter.SaveConfig(xconfig, "border_outer");
         }
         // 保存前景资源
         if ((_propertyForeColor == null) || (_propertyForeColor != null && _foreColor != _propertyForeColor.GetHex())) {
            xconfig.Set("fore_color", RColor.FormatHtml(_foreColor));
         }
         _foreResource.SaveConfig(xconfig, "fore");
         // 保存后景资源
         if ((_propertyBackColor == null) || (_propertyBackColor != null && _backColor != _propertyBackColor.GetHex())) {
            xconfig.Set("back_color", RColor.FormatHtml(_backColor));
         }
         _backResource.SaveConfig(xconfig, "back");
         // 存储事件
         xconfig.SetNvl("on_click", _onClick);
         xconfig.SetNvl("on_double_click", _onDoubleClick);
         xconfig.SetNvl("on_mouse_down", _onMouseDown);
         xconfig.SetNvl("on_mouse_up", _onMouseUp);
         xconfig.SetNvl("on_mouse_enter", _onMouseEnter);
         xconfig.SetNvl("on_mouse_move", _onMouseMove);
         xconfig.SetNvl("on_mouse_leave", _onMouseLeave);
      }

      //============================================================
      // <T>生成标志集合。</T>
      //
      // @return 标志集合
      //============================================================
      public override int MakeSerializeFlags() {
         int flags = base.MakeSerializeFlags();
         // 设置信息
         if (_optionEnable) {
            flags |= (int)ERcFlag.Enable;
         }
         if (_optionVisible) {
            flags |= (int)ERcFlag.Visible;
         }
         // 空白信息
         if (!_margin.IsEmpty()) {
            flags |= (int)ERcFlag.Margin;
         }
         if (!_padding.IsEmpty()) {
            flags |= (int)ERcFlag.Padding;
         }
         // 边框信息
         if (!_borderOuter.IsEmpty()) {
            flags |= (int)ERcFlag.BorderOuter;
         }
         if (!_borderInner.IsEmpty()) {
            flags |= (int)ERcFlag.BorderInner;
         }
         // 资源信息
         if (_foreResource.IsValid()) {
            flags |= (int)ERcFlag.LayerFore;
         }
         if (_backResource.IsValid()) {
            flags |= (int)ERcFlag.LayerBack;
         }
         return flags;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
         // 存储位置
         output.WriteInt8((sbyte)_dockCd);
         _location.Serialize16(output);
         _size.Serialize16(output);
         // 存储边距
         if (!_margin.IsEmpty()) {
            _margin.Serialize8(output);
         }
         if (!_padding.IsEmpty()) {
            _padding.Serialize8(output);
         }
         // 存储边框
         if (!_borderOuter.IsEmpty()) {
            _borderOuter.Serialize(output);
         }
         if (!_borderInner.IsEmpty()) {
            _borderInner.Serialize(output);
         }
         // 存储前景
         output.WriteInt32(RColor.ConvertRevert(_foreColor));
         if (_foreResource.IsValid()) {
            _foreResource.Serialize(output);
         }
         // 存储后景
         output.WriteInt32(RColor.ConvertRevert(_backColor));
         if (_backResource.IsValid()) {
            _backResource.Serialize(output);
         }
         // 存储事件
         //output.WriteString(_onClick);
         //output.WriteString(_onDoubleClick);
         //output.WriteString(_onMouseEnter);
         //output.WriteString(_onMouseLeave);
         //output.WriteString(_onMouseDown);
         //output.WriteString(_onMouseMove);
         //output.WriteString(_onMouseUp);
      }
   }
}
