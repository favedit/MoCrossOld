using MO.Core.Forms.Common;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>颜色选取控件<T>
   //============================================================
   public partial class QColorPicker : QControl
   {
      // 最大高度
      protected int _heighMax = 20;

      // 颜色宽度
      protected int _colorWidth = 18;

      // 颜色
      protected Color _selectColor = Color.Black;

      //============================================================
      // <T>构造颜色选取控件<T>
      //============================================================
      public QColorPicker() {
         InitializeComponent();
      }

      //============================================================
      // <T>设置颜色<T>
      //============================================================
      protected void InnerSetSize(int width, int height) {
         // 设置尺寸
         Width = width;
         Height = height = _heighMax;
         // 设置信息
         int contentLeft = _borderOuter.Left.Width + _borderInner.Left.Width;
         int contentRight = _borderOuter.Bottom.Width + _borderInner.Bottom.Width;
         int contentTop = _borderOuter.Top.Width + _borderInner.Top.Width;
         int contentBottom = _borderOuter.Bottom.Width + _borderInner.Bottom.Width;
         // 设置容器
         pnlContanier.SetBounds(
               contentLeft,
               contentTop,
               width - contentLeft - contentRight,
               height - contentTop - contentBottom);
         // 设置内容框
         txtValue.SetBounds(
               contentLeft,
               contentTop,
               width - _colorWidth - contentLeft - contentRight - 2,
               height - contentTop - contentBottom);
         // 设置颜色框
         pnlColor.SetBounds(
               width - _colorWidth - contentLeft - contentRight,
               contentTop - 2,
               _colorWidth,
               height - contentTop - contentBottom);
         Invalidate();
      }

      //============================================================
      // <T>大小</T>
      //============================================================
      [Browsable(true)]
      public new Size Size {
         get { return base.Size; }
         set { InnerSetSize(value.Width, value.Height); }
      }

      //============================================================
      // <T>刷新颜色处理。</T>
      //============================================================
      public void RefreshColor(){
         pnlColor.BackColor = _selectColor;
         txtValue.Text = RColor.FormatHex(_selectColor.ToArgb());
      }

      //============================================================
      // <T>获取或获得选中颜色。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(typeof(Color), "Color.Black")]
      public Color SelectColor {
         get { return _selectColor; }
         set { 
            _selectColor = value;
            RefreshColor();
         }
      }

      //============================================================
      // <T>获取或获得选中颜色文本。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue("FF000000")]
      public string SelectColorText {
         get { return RColor.FormatHex(_selectColor.ToArgb()); }
         set { 
            _selectColor = RColor.ParseHexColor(value);
            RefreshColor();
         }
      }

      //============================================================
      // <T>获取或获得选中颜色内容。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(-16777216)]
      public int SelectColorValue {
         get { return _selectColor.ToArgb(); }
         set { 
            _selectColor = Color.FromArgb(value);
            RefreshColor();
         }
      }

      //============================================================
      // <T>调整下拉框的高度</T>
      //============================================================
      private void QColorPicker_Resize(object sender, EventArgs e) {
         InnerSetSize(Width, Height);
      }

      //============================================================
      // <T>鼠标点击事件处理。</T>
      //============================================================
      private void pnlColor_Click(object sender, EventArgs e) {
         dlgColor.Color = _selectColor;
         DialogResult resultCd = dlgColor.ShowDialog();
         if (resultCd == DialogResult.OK) {
            _selectColor = dlgColor.Color;
            RefreshColor();
         }
      }

      //============================================================
      // <T>文本变更。</T>
      //============================================================
      private void txtValue_Leave(object sender, EventArgs e) {
         _selectColor = RColor.ParseHexColor(txtValue.Text);
         RefreshColor();
      }
   }
}
