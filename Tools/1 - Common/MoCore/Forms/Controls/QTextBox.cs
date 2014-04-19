using MO.Common.Lang;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>文本框控件。</T>
   //============================================================
   public partial class QTextBox : QControl
   {
      // 最大高度
      protected int _heighMax = 19;

      // 允许标志
      protected bool _signEnable = true;

      // 标志图片
      protected bool _signImage = false;

      // 允许按键
      protected bool _buttonEnable = false;

      // 按键图片
      protected bool _buttonImage = false;

      // 原始内容
      protected string _textOrign = String.Empty;

      // 分发点击事件
      [Browsable(true)]
      [Category("自定义事件")]
      [Description("自定义事件")]
      public event EventHandler ButtonClick;

      // 分发Click事件
      [Browsable(true)]
      [Category("自定义事件")]
      [Description("pic_Click事件")]
      public event EventHandler SignClick;

      // 分发KeyDown事件
      [Browsable(true)]
      [Category("自定义事件")]
      [Description("自定义事件")]
      public event KeyEventHandler Key;

      // 分发KeyDown事件
      [Browsable(true)]
      [Category("自定义事件")]
      [Description("当鼠标双击的时候发生")]
      public new event MouseEventHandler MouseDoubleClick;

      [Browsable(true)]
      [Category("自定义事件")]
      [Description("TextChanged事件")]
      public new event EventHandler TextChanged;

      //============================================================
      // <T>构造文本框控件。</T>
      //============================================================
      public QTextBox() {
         InitializeComponent();
      }

      //============================================================
      // <T>内部设置大小尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      protected void InnerSetSize(int width, int height) {
         // 设置尺寸
         Width = width;
         Height = height = _heighMax;
         // 设置信息
         int contentLeft = _borderOuter.Left.Width + _borderInner.Left.Width + 1;
         int buttonLeft = contentLeft;
         // 设置标志
         if (_signEnable) {
            pbxSign.SetBounds(2, 2, 4, 6);
            contentLeft += pbxSign.Width;
            buttonLeft += pbxSign.Width;
         }
         // 设置按键
         int contentWidth = width - contentLeft - 3;
         if (_buttonEnable) {
            contentWidth -= pbxButton.Width;
            pbxButton.SetBounds(buttonLeft + contentWidth + 1, 2, pbxButton.Width, height - 4);
         }
         // 设置内容
         txtContent.SetBounds(contentLeft, 3, contentWidth, height);
         Invalidate();
      }

      //============================================================
      // <T>获取或设定是否显示变更标志。</T>
      //============================================================
      [Category("配置")]
      [Description("是否显示变更标志。")]
      [Browsable(true)]
      [DefaultValue(true)]
      public bool SignEnable {
         get { return _signEnable; }
         set {
            _signEnable = value;
            pbxSign.Visible = _signEnable;
            InnerSetSize(Width, Height);
         }
      }

      //============================================================
      // <T>获取或设定变更标志图片。</T>
      //============================================================
      [Category("配置")]
      [Description("变更标志图片。")]
      [Browsable(true)]
      [DefaultValue(null)]
      public Image SignImage {
         get { return _signImage ? pbxSign.BackgroundImage : null; }
         set {
            pbxSign.BackgroundImage = value;
            _signImage = true;
         }
      }

      //============================================================
      // <T>获取或设定是否显示按键。</T>
      //============================================================
      [Category("配置")]
      [Description("是否显示按键。")]
      [Browsable(true)]
      [DefaultValue(false)]
      public bool ButtonEnable {
         get { return _buttonEnable; }
         set {
            _buttonEnable = value;
            pbxButton.Visible = value;
            InnerSetSize(Width, Height);
         }
      }

      //============================================================
      // <T>获取或设定变更按键图片。</T>
      //============================================================
      [Category("配置")]
      [Description("变更按键图片。")]
      [Browsable(true)]
      [DefaultValue(null)]
      public Image ButtonImage {
         get { return _buttonImage ? pbxButton.BackgroundImage : null; }
         set {
            pbxButton.BackgroundImage = value;
            _buttonImage = true;
         }
      }

      //============================================================
      // <T>获取或设定文本内容。</T>
      //============================================================
      [Category("配置")]
      [Description("文本内容。")]
      [Browsable(true)]
      [DefaultValue("")]
      public new string Text {
         get { return txtContent.Text; }
         set {
            txtContent.Text = value;
            _textOrign = value;
            // 设置文本改变标志不可见
            if (_signEnable) {
               pbxSign.Visible = false;
               Invalidate();
            }
         }
      }

      //============================================================
      // <T>获取原始文本内容。</T>
      //============================================================
      [Category("配置")]
      [Description("原始文本内容。")]
      [Browsable(true)]
      [DefaultValue("")]
      public string TextOrign {
         get { return _textOrign; }
      }

      //============================================================
      // <T>获取或设定是否内容只读。</T>
      //============================================================
      [Browsable(true)]
      [Category("配置")]
      [Description("是否内容只读。")]
      [DefaultValue(false)]
      public bool ReadOnly {
         get { return txtContent.ReadOnly; }
         set {
            txtContent.ReadOnly = value;
            if (value) {
               txtContent.BackColor = SystemColors.Info;
            }
         }
      }

      //============================================================
      // <T>获取输入焦点。</T>
      //============================================================
      [Browsable(false)]
      public override bool Focused {
         get { return txtContent.Focused; }
      }

      //============================================================
      // <T>获取或设定尺寸大小。</T>
      //============================================================
      [Browsable(true)]
      public new Size Size {
         get { return base.Size; }
         set { InnerSetSize(value.Width, value.Height); }
      }

      //============================================================
      // <T>处理大小改变事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void QTextBox_Resize(object sender, EventArgs e) {
         InnerSetSize(Width, Height);
      }

      //============================================================
      // <T>处理键盘按下事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void QTextBox_KeyDown(object sender, KeyEventArgs e) {
         if (Key != null) {
            Key(sender, e);
         }
      }

      //============================================================
      // <T>处理鼠标按下控件事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void btnControl_Click(object sender, EventArgs e) {
         if (ButtonClick != null) {
            ButtonClick(sender, e);
         }
      }

      //============================================================
      // <T>处理鼠标按下变更标志事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void pbxSign_MouseMove(object sender, MouseEventArgs e) {
         Cursor.Current = Cursors.Hand;
      }

      //============================================================
      // <T>处理鼠标单击变更标志事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void pbxSign_Click(object sender, EventArgs e) {
         // 设置文本
         txtContent.Text = RString.Nvl(_textOrign);
         pbxSign.Visible = false;
         // 产生事件
         if (SignClick != null) {
            SignClick(sender, e);
         }
         // 光标定位到文本最后 
         txtContent.Select(txtContent.TextLength, 0);
         txtContent.ScrollToCaret();
      }

      //============================================================
      // <T>处理鼠标双击内容事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void txtContent_MouseDoubleClick(object sender, MouseEventArgs e) {
         if (MouseDoubleClick != null) {
            MouseDoubleClick(sender, e);
         }
      }

      //============================================================
      // <T>处理内容变更事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void txtContent_TextChanged(object sender, EventArgs e) {
         // 设置文本改变标志是否可见
         if (_signEnable) {
            bool changed = (Text != _textOrign);
            if (pbxSign.Visible != changed) {
               pbxSign.Visible = changed;
               Invalidate();
            }
         }
         // 产生事件
         if (null != TextChanged) {
            TextChanged(sender, e);
         }
      }

      //============================================================
      // <T>处理鼠标划过按键事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void pbxButton_MouseHover(object sender, EventArgs e) {
         pbxButton.BorderStyle = BorderStyle.None;
      }

      //============================================================
      // <T>处理鼠标离开按键事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void pbxButton_MouseLeave(object sender, EventArgs e) {
         pbxButton.BackColor = SystemColors.Control;
         pbxButton.BorderStyle = BorderStyle.None;
      }

      //============================================================
      // <T>处理鼠标按下按键事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void pbxButton_MouseDown(object sender, MouseEventArgs e) {
         pbxButton.BorderStyle = BorderStyle.Fixed3D;
      }

      //============================================================
      // <T>处理鼠标移动按键事件。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void pbxButton_MouseMove(object sender, MouseEventArgs e) {
         Cursor.Current = Cursors.Hand;
         pbxButton.BackColor = Color.Gainsboro;
      }

      //============================================================
      // <T>重写按钮外观样式</T>
      //============================================================
      protected override void OnPaint(PaintEventArgs e) {
         if (txtContent.ReadOnly) {
            e.Graphics.Clear(SystemColors.Info);
         } else {
            e.Graphics.Clear(SystemColors.Window);
         }
         base.OnPaint(e);
      }
   }
}
