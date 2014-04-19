using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using MO.Core.Forms.Common;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>控件。</T>
   //============================================================
   public partial class QControl : UserControl
   {
      protected FBorderStyle _borderOuter = new FBorderStyle();

      protected FBorderStyle _borderInner = new FBorderStyle();

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public QControl() {
         InitializeComponent();
         // 设置边框样式
         _borderInner.Assign(FBorderStyle.StyleFace);
         _borderOuter.Assign(FBorderStyle.StyleSunken);
      }

      //============================================================
      // <T>获得或设置外边框样式。</T>
      //============================================================
      [Category("外观")]
      [Description("外边框样式。")]
      [Browsable(true)]
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FBorderStyle BorderOuter {
         get { return _borderOuter; }
      }

      //============================================================
      // <T>获得或设置内边框样式。</T>
      //============================================================
      [Category("外观")]
      [Description("内边框样式。")]
      [Browsable(true)]
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FBorderStyle BorderInner {
         get { return _borderInner; }
      }

      //============================================================
      // <T>根据大小计算客户区大小。</T>
      //
      // @param size 大小
      // @return 客户区大小
      //============================================================
      protected Rectangle InnerCalculateClientRectangle(Size size) {
         Rectangle rect = new Rectangle();
         rect.X = _borderInner.Left.Width + _borderOuter.Left.Width;
         rect.Y = _borderInner.Top.Width + _borderOuter.Top.Width;
         rect.Width = size.Width - _borderInner.Right.Width - _borderOuter.Right.Width - rect.X;
         rect.Height = size.Height - _borderInner.Bottom.Width - _borderOuter.Bottom.Width - rect.Y;
         return rect;
      }

      //============================================================
      // <T>控件绘制事件。</T>
      //
      // @param e 绘制事件
      //============================================================
      protected override void OnPaint(PaintEventArgs e) {
         base.OnPaint(e);
         // 绘制外边框
         Rectangle outerRect = ClientRectangle;
         ControlPaint.DrawBorder(e.Graphics, outerRect,
            _borderOuter.Left.Color, _borderOuter.Left.Width, (ButtonBorderStyle)_borderOuter.Left.Style,
            _borderOuter.Top.Color, _borderOuter.Top.Width, (ButtonBorderStyle)_borderOuter.Top.Style,
            _borderOuter.Right.Color, _borderOuter.Right.Width, (ButtonBorderStyle)_borderOuter.Right.Style,
            _borderOuter.Bottom.Color, _borderOuter.Bottom.Width, (ButtonBorderStyle)_borderOuter.Bottom.Style);
         // 绘制内边框
         Rectangle innerRect = new Rectangle(outerRect.Left + 1, outerRect.Top + 1, outerRect.Width - 2, outerRect.Height - 2);
         ControlPaint.DrawBorder(e.Graphics, innerRect,
            _borderInner.Left.Color, _borderInner.Left.Width, (ButtonBorderStyle)_borderInner.Left.Style,
            _borderInner.Top.Color, _borderInner.Top.Width, (ButtonBorderStyle)_borderInner.Top.Style,
            _borderInner.Right.Color, _borderInner.Right.Width, (ButtonBorderStyle)_borderInner.Right.Style,
            _borderInner.Bottom.Color, _borderInner.Bottom.Width, (ButtonBorderStyle)_borderInner.Bottom.Style);
      }
   }
}
