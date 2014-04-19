using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Controls
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FRcButton : FRcControl
   {
      // 鼠标手样式
      protected bool _optionHand;

      // 有效间隔
      protected int _validInterval;

      // 文本对齐方式
      protected ERcTextAlign _textAlignCd;

      // 字体
      protected FRcFont _font = new FRcFont();

      // 字体属性
      protected FTplThemeStyleProperty _propertyFont;

      // 文本内容
      protected string _text;

      // 背景资源
      protected FRcPicture _groundResource = new FRcPicture();

      // 热点外边框
      protected FRcBorder _borderHoverOuter = new FRcBorder();

      // 热点外边框属性
      protected FTplThemeStyleProperty _propertyBorderHoverOuter;

      // 热点内边框
      protected FRcBorder _borderHoverInner = new FRcBorder();

      // 热点内边框属性
      protected FTplThemeStyleProperty _propertyBorderHoverInner;

      // 热点后景颜色
      protected int _backHoverColor;

      // 热点后景颜色属性
      protected FTplThemeStyleProperty _propertyBackHoverColor;

      // 选中外边框
      protected FRcBorder _borderSelectOuter = new FRcBorder();

      // 选中外边框属性
      protected FTplThemeStyleProperty _propertyBorderSelectOuter;

      // 选中内边框
      protected FRcBorder _borderSelectInner = new FRcBorder();

      // 选中内边框属性
      protected FTplThemeStyleProperty _propertyBorderSelectInner;

      // 选中后景颜色
      protected int _backSelectColor;

      // 选中后景颜色属性
      protected FTplThemeStyleProperty _propertyBackSelectColor;

      // 鼠标点击控件处理
      protected string _onClickControl;

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcButton(FRcFrameConsole console = null) : base(console){
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.Button);
         _styleName = "control.button";
      }

      //============================================================
      // <T>获得或设置鼠标手样式。</T>
      //============================================================
      public bool OptionHand {
         get { return _optionHand; }
         set { _optionHand = value; }
      }

      //============================================================
      // <T>获得或设置有效间隔。</T>
      //============================================================
      public int ValidInterval {
         get { return _validInterval; }
         set { _validInterval = value; }
      }

      //============================================================
      // <T>获得或设置文本对齐方式。</T>
      //============================================================
      public ERcTextAlign TextAlignCd {
         get { return _textAlignCd; }
         set { _textAlignCd = value; }
      }

      //============================================================
      // <T>获得或设置字体。</T>
      //============================================================
      public FRcFont Font {
         get { return _font; }
         set { _font = value; }
      }

      //============================================================
      // <T>获得或设置文本内容。</T>
      //============================================================
      public string Text {
         get { return _text; }
         set { _text = value; }
      }

      //============================================================
      // <T>获得或设置鼠标点击控件处理。</T>
      //============================================================B
      public string OnClickControl {
         get { return _onClickControl; }
         set { _onClickControl = value; }
      }

      //============================================================
      // <T>获得显示内容。</T>
      //============================================================
      public string FormatDisplay() {
         if (!RString.IsEmpty(_text)) {
            return _text;
         }
         return _label;
      }

      //============================================================
      // <T>获得或设置背景资源。</T>
      //============================================================
      public FRcPicture GroundResource {
         get { return _groundResource; }
         set { _groundResource = value; }
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public override void LoadStyleProperty() {
         // 加载父样式属性
         base.LoadStyleProperty();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.button");
         if (style != null) {
            _propertyBorderInner = style.FindProperty("border_inner");
            _propertyBorderOuter = style.FindProperty("border_outer");
            _propertyBackColor = style.FindProperty("back_color");
            _propertyBorderHoverInner = style.FindProperty("border_hover_inner");
            _propertyBorderHoverOuter = style.FindProperty("border_hover_outer");
            _propertyBackHoverColor = style.FindProperty("back_hover_color");
            _propertyBorderSelectInner = style.FindProperty("border_select_inner");
            _propertyBorderSelectOuter = style.FindProperty("border_select_outer");
            _propertyBackSelectColor = style.FindProperty("back_select_color");
            _propertyFont = style.FindProperty("font");
         }
      }

      //============================================================
      // <T>加载样式内容。</T>
      //============================================================
      public override void LoadStyleValue() {
         // 加载父样式内容
         base.LoadStyleValue();
         // 加载字体
         if (_propertyFont != null) {
            _font.LoadStyleProperty(_propertyFont);
         }
         // 加载热点内边框属性
         if (_propertyBorderHoverInner != null) {
            _borderHoverInner.LoadStyleProperty(_propertyBorderHoverInner);
         }
         // 加载热点外边框属性
         if (_propertyBorderHoverOuter != null) {
            _borderHoverOuter.LoadStyleProperty(_propertyBorderHoverOuter);
         }
         // 加载热点背景颜色属性
         if (_propertyBackHoverColor != null) {
            _backHoverColor = _propertyBackHoverColor.GetHex();
         }
         // 加载选中内边框属性
         if (_propertyBorderSelectInner != null) {
            _borderSelectInner.LoadStyleProperty(_propertyBorderSelectInner);
         }
         // 加载选中外边框属性
         if (_propertyBorderSelectOuter != null) {
            _borderSelectOuter.LoadStyleProperty(_propertyBorderSelectOuter);
         }
         // 加载选中背景颜色属性
         if (_propertyBackSelectColor != null) {
            _backSelectColor = _propertyBackSelectColor.GetHex();
         }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig){
         base.OnLoadConfig(xconfig);
         // 读取数据
         _optionHand = xconfig.GetBoolean("option_hand", _optionHand);
         _validInterval = xconfig.GetInteger("valid_interval", _validInterval);
         if (xconfig.Contains("text_align_cd")) {
            _textAlignCd = (ERcTextAlign)REnum.ToValue(typeof(ERcTextAlign), xconfig.Get("text_align_cd"));
         }
         _font.LoadConfig(xconfig, "font");
         _text = xconfig.Get("text", _text);
         _groundResource.LoadConfig(xconfig, "ground");
         // 读取事件
         _onClickControl = xconfig.Get("on_click_control", _onClickControl);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig){
         // 保存处理
         base.OnSaveConfig(xconfig);
         // 保存数据
         xconfig.SetNvl("option_hand", _optionHand);
         xconfig.SetNvl("valid_interval", _validInterval);
         if (_textAlignCd != ERcTextAlign.None) {
            xconfig.Set("text_align_cd", _textAlignCd.ToString());
         }
         if (_propertyFont == null || (_propertyFont != null && !_font.EqualsStyleProperty(_propertyFont))) {
            _font.SaveConfig(xconfig, "font");
         }
         xconfig.SetNvl("text", _text);
         _groundResource.SaveConfig(xconfig, "ground");
         // 保存事件
         xconfig.SetNvl("on_click_control", _onClickControl);
      }

      //============================================================
      // <T>生成标志集合。</T>
      //
      // @return 标志集合
      //============================================================
      public override int MakeSerializeFlags() {
         int flags = base.MakeSerializeFlags();
         // 资源信息
         if (_groundResource.IsValid()) {
            flags |= (int)ERcFlag.LayerGround;
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
         // 写入属性
         output.WriteBool(_optionHand);
         _font.Serialize(output);
         output.WriteWideString(_text);
         output.WriteUint16((ushort)_validInterval);
         // 写入背景资源
         if (_groundResource.IsValid()) {
            _groundResource.Serialize(output);
         }
         // 写入热点信息
         _borderHoverOuter.Serialize(output);
         _borderHoverInner.Serialize(output);
         output.WriteInt32(_backHoverColor);
         // 写入选中信息
         _borderSelectOuter.Serialize(output);
         _borderSelectInner.Serialize(output);
         output.WriteInt32(_backSelectColor);
         // 存储事件
         output.WriteString(_onClickControl);
      }
   }
}
