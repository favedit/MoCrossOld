using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using MO.Core.Forms.Common;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>样式线控件</T>
   //============================================================
   public partial class QStyleLine : UserControl
   {
      public enum EStyle
      {
         Horizontal,

         Vertical,
      };

      protected EStyle _style = EStyle.Horizontal;

      protected FLineStyle _lineFirst = new FLineStyle();

      protected FLineStyle _lineLast = new FLineStyle();

      //============================================================
      public QStyleLine() {
         InitializeComponent();
         _lineFirst.Color = SystemColors.ButtonShadow;
         _lineLast.Color = SystemColors.ButtonHighlight;
      }

      //============================================================
      protected void ChangeSize(Size size) {
         int total = 1 + _lineFirst.Width + _lineLast.Width + 1;
         if (_style == EStyle.Horizontal) {
            SetBounds(Location.X, Location.Y, size.Width, total, BoundsSpecified.Size);
         }
         if (_style == EStyle.Vertical) {
            SetBounds(Location.X, Location.Y, total, size.Height, BoundsSpecified.Size);
         }
      }

      //============================================================
      [Browsable(true)]
      [Category("样式")]
      [Description("样式类型")]
      [DefaultValue(EStyle.Horizontal)]
      public EStyle Style {
         get { return _style; }
         set { _style = value; }
      }

      //============================================================
      [Browsable(true)]
      [Category("样式")]
      [Description("开始线")]
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FLineStyle LineFirst {
         get { return _lineFirst; }
      }

      //============================================================
      [Browsable(true)]
      [Category("样式")]
      [Description("结束线")]
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FLineStyle LineLast {
         get { return _lineLast; }
      }

      //============================================================
      [Browsable(true)]
      [Category("布局")]
      [Description("控件的大小")]
      public new Size Size {
         get { return base.Size; }
         set { ChangeSize(value); }
      }

      //============================================================
      private void QStyleLine_Resize(object sender, System.EventArgs e) {
         ChangeSize(Size);
      }

      //============================================================
      protected override void OnPaint(PaintEventArgs e) {
         base.OnPaint(e);
         Graphics g = e.Graphics;
         Rectangle r = e.ClipRectangle;
         int x1 = r.Location.X;
         int x2 = r.Location.X + r.Width;
         int y1 = r.Location.Y;
         int y2 = r.Location.Y + r.Height;
         int fix = _lineFirst.Width + 1;
         // 绘制横线
         if (_style == EStyle.Horizontal) {
            // 绘制上线
            using (Pen pen = new Pen(new SolidBrush(_lineFirst.Color), _lineFirst.Width)) {
               g.DrawLine(pen, x1, y1 + 1, x2, y1 + 1);
            }
            // 绘制下线
            using (Pen pen = new Pen(new SolidBrush(_lineLast.Color), _lineLast.Width)) {
               g.DrawLine(pen, x1, y1 + fix, x2, y1 + fix);
            }
         }
         // 绘制纵线
         if (_style == EStyle.Vertical) {
            // 绘制左线
            using (Pen pen = new Pen(new SolidBrush(_lineFirst.Color), _lineFirst.Width)) {
               g.DrawLine(pen, x1 + 1, y1, x1 + 1, y2);
            }
            // 绘制右线
            using (Pen pen = new Pen(new SolidBrush(_lineLast.Color), _lineLast.Width)) {
               g.DrawLine(pen, x1 + fix, y1, x2 + fix, y1);
            }
         }
      }
   }
}
