using System;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;

namespace MO.Face.Controls
{
   //============================================================
   // <T>按键控件。</T>
   //
   // @history MAOCY 140430
   //============================================================
   public partial class FButton : UserControl
   {
      // 状态按钮可用
      protected bool _statusEnable;

      // 状态鼠标落下
      protected bool _statusDown = false;

      // 按钮抬起图片
      protected ImageSource _imageUp;

      // 按钮热点图片
      protected ImageSource _imageHover;

      // 按钮落下图片
      protected ImageSource _imageDown;

      // 按钮禁止图片
      protected ImageSource _imageDisable;

      // 文本属性
      protected string _text = "";

      // 文本字体
      protected FontFamily _textFamily;

      // 文本尺寸
      protected double _textSize;

      // 文本颜色
      protected Brush _textColor;

      // 点击事件
      public event EventHandler ClickHandle;

      //============================================================
      // <T>构造按键。</T>
      //
      // @history MAOCY 140430
      //============================================================
      public FButton() {
         InitializeComponent();
      }

      #region 属性赋值

      //============================================================
      // <T>获得或设置按钮可用。</T>
      //============================================================
      public bool IsEnable {
         get { return _statusEnable; }
         set {
            _statusEnable = value;
            imgIcon.Source = _statusEnable ? _imageUp : _imageDisable;
         }
      }

      //============================================================
      // <T>获得或设置按钮弹起图片。</T>
      //============================================================
      public ImageSource ImageUp {
         get { return _imageUp; }
         set {
            _imageUp = value;
            imgIcon.Source = _imageUp;
         }
      }

      //============================================================
      // <T>获得或设置按钮划过图片。</T>
      //============================================================
      public ImageSource ImageHover {
         get { return _imageHover; }
         set { _imageHover = value; }
      }

      //============================================================
      // <T>获得或设置按钮按下图片。</T>
      //============================================================
      public ImageSource ImageDown {
         get { return _imageDown; }
         set { _imageDown = value; }
      }

      //============================================================
      // <T>获得或设置按钮禁用图片。</T>
      //============================================================
      public ImageSource ImageDisable {
         get { return _imageDisable; }
         set { _imageDisable = value; }
      }

      //============================================================
      // <T>获得或设置按钮文本。</T>
      //============================================================
      public string Text {
         get { return _text; }
         set {
            _text = value;
            labText.Content = _text;
         }
      }

      //============================================================
      // <T>获得或设置按钮字体。</T>
      //============================================================
      public FontFamily TextFamily {
         get { return _textFamily; }
         set {
            _textFamily = value;
            labText.FontFamily = _textFamily;
         }
      }

      //============================================================
      // <T>获得或设置按钮字号。</T>
      //============================================================
      public double TextSize {
         get { return _textSize; }
         set {
            _textSize = value;
            labText.FontSize = _textSize;
         }
      }

      //============================================================
      // <T>获得或设置文字颜色。</T>
      //============================================================
      public Brush TextColor {
         get { return _textColor; }
         set {
            _textColor = value;
            labText.Foreground = _textColor;
         }
      }
      #endregion

      #region 按钮事件

      //============================================================
      // <T>鼠标进入事件处理。</T>
      //============================================================
      private void imgIcon_MouseEnter(object sender, MouseEventArgs e) {
         if (_statusEnable) {
            if (null != _imageHover) {
               imgIcon.Source = _imageHover;
            }
         }
      }

      //============================================================
      // <T>鼠标离开事件处理。</T>
      //============================================================
      private void imgIcon_MouseLeave(object sender, MouseEventArgs e) {
         if (_statusEnable) {
            _statusDown = false;
            imgIcon.Source = _imageUp;
         }
      }

      //============================================================
      // <T>鼠标按下事件处理。</T>
      //============================================================
      private void imgIcon_MouseLeftButtonDown(object sender, MouseButtonEventArgs e) {
         if (_statusEnable) {
            _statusDown = true;
            if (null != _imageDown) {
               imgIcon.Source = _imageDown;
            }
         }
      }

      //============================================================
      // <T>鼠标抬起事件处理。</T>
      //============================================================
      private void imgIcon_MouseLeftButtonUp(object sender, MouseButtonEventArgs e) {
         if (_statusEnable) {
            // 完成在控件上点击
            if (_statusDown) {
               _statusDown = false;
               imgIcon.Source = _imageUp;
               // 触发点击事件
               if (null != ClickHandle) ClickHandle(this, null);
            }
         }
      }
      #endregion
   }
}