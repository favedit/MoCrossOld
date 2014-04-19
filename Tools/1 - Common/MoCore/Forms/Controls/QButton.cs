using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using System.Drawing;
using System.Drawing.Drawing2D;

namespace MO.Core.Forms.Controls
{
    [ToolboxBitmap(typeof(Button))]
    public class QButton : Button {
      /*

#region About Constructor
      public UButton() {
         this.SetStyle(ControlStyles.SupportsTransparentBackColor |
                       ControlStyles.UserPaint |
                       ControlStyles.ResizeRedraw |
                       ControlStyles.DoubleBuffer, true);
         this.SetStyle(ControlStyles.Opaque, false);
         this.FlatAppearance.BorderSize = 0;
         this.FlatStyle = FlatStyle.Flat;
         this.BackColor = Color.Transparent;

         timer1.Interval = 5;
         timer1.Tick += new EventHandler(timer1_Tick);

      }
      protected override void OnCreateControl() {
         base.OnCreateControl();
         A0 = ColorBase.A;
         R0 = ColorBase.R;
         G0 = ColorBase.G;
         B0 = ColorBase.B;
         _colorStroke = _baseStroke;

         Rectangle r = new Rectangle(new Point(-1, -1), new Size(this.Width + _radius, this.Height + _radius));
         #region Transform to SmoothRectangle Region
         if (this.Size != null) {
            GraphicsPath pathregion = new GraphicsPath();
            DrawArc(r, pathregion);
            this.Region = new Region(pathregion);
         }
         #endregion
      }
      #endregion

      #region About Image Settings
      private e_imagelocation _imagelocation;
      public enum e_imagelocation {
         Top, Bottom, Left, Right, None
      }
      public e_imagelocation ImageLocation {
         get { return _imagelocation; }
         set { _imagelocation = value; this.Refresh(); }
      }
      private int _imageoffset;
      public int ImageOffset {
         get { return _imageoffset; }
         set { _imageoffset = value; }
      }
      private Point _maximagesize;
      public Point MaxImageSize {
         get { return _maximagesize; }
         set { _maximagesize = value; }
      }

      #endregion

      #region About Button Settings
      private e_showbase _showbase;
      private e_showbase _tempshowbase;
      public enum e_showbase {
         Yes, No
      }
      public e_showbase ShowBase {
         get { return _showbase; }
         set { _showbase = value; this.Refresh(); }
      }
      private int _radius = 6;
      public int Radius {
         get { return _radius; }
         set { if (_radius > 0) _radius = value; this.Refresh(); }
      }
      private e_groupPos _grouppos;
      public enum e_groupPos {
         None, Left, Center, Right, Top, Bottom
      }
      public e_groupPos GroupPos {
         get { return _grouppos; }
         set { _grouppos = value; this.Refresh(); }
      }

      private e_arrow _arrow;
      public enum e_arrow {
         None, ToRight, ToDown
      }
      public e_arrow Arrow {
         get { return _arrow; }
         set { _arrow = value; this.Refresh(); }
      }
      private e_splitbutton _splitbutton;
      public enum e_splitbutton {
         No, Yes
      }
      public e_splitbutton SplitButton {
         get { return _splitbutton; }
         set { _splitbutton = value; this.Refresh(); }
      }
      private int _splitdistance = 0;
      public int SplitDistance {
         get { return _splitdistance; }
         set { _splitdistance = value; this.Refresh(); }
      }
      private string _title = "";
      public string Title {
         get { return _title; }
         set { _title = value; this.Refresh(); }
      }
      private bool _keeppress = false;
      public bool KeepPress {
         get { return _keeppress; }
         set { _keeppress = value; }
      }
      private bool _ispressed = false;
      public bool IsPressed {
         get { return _ispressed; }
         set { _ispressed = value; }
      }


      #endregion

      #region Menu Pos
      private Point _menupos = new Point(0, 0);
      public Point MenuPos {
         get { return _menupos; }
         set { _menupos = value; }
      }
      #endregion

      #region Colors
      private Color _baseColor = Color.FromArgb(209, 209, 209);
      private Color _onColor = Color.FromArgb(255, 255, 255);
      private Color _pressColor = Color.FromArgb(255, 255, 255);

      private Color _baseStroke = Color.FromArgb(255, 255, 255);
      private Color _onStroke = Color.FromArgb(255, 255, 255);
      private Color _pressStroke = Color.FromArgb(255, 255, 255);
      private Color _colorStroke = Color.FromArgb(255, 255, 255);
      private int A0, R0, G0, B0;

      public Color ColorBase {
         get { return _baseColor; }
         set {
            _baseColor = value;
            R0 = _baseColor.R;
            B0 = _baseColor.B;
            G0 = _baseColor.G;
            A0 = _baseColor.A;
            RibbonColor hsb = new RibbonColor(_baseColor);
            if (hsb.BC < 50) {
               hsb.SetBrightness(60);
            } else {
               hsb.SetBrightness(30);
            }
            if (_baseColor.A > 0)
               _baseStroke = Color.FromArgb(100, hsb.GetColor());
            else
               _baseStroke = Color.FromArgb(0, hsb.GetColor());
            this.Refresh();
         }
      }
      public Color ColorOn {
         get { return _onColor; }
         set {
            _onColor = value;

            RibbonColor hsb = new RibbonColor(_onColor);
            if (hsb.BC < 50) {
               hsb.SetBrightness(60);
            } else {
               hsb.SetBrightness(30);
            }
            if (_baseStroke.A > 0)
               _onStroke = Color.FromArgb(100, hsb.GetColor());
            else
               _onStroke = Color.FromArgb(0, hsb.GetColor());
            this.Refresh();
         }
      }
      public Color ColorPress {
         get { return _pressColor; }
         set {
            _pressColor = value;

            RibbonColor hsb = new RibbonColor(_pressColor);
            if (hsb.BC < 50) {
               hsb.SetBrightness(60);
            } else {
               hsb.SetBrightness(30);
            }
            if (_baseStroke.A > 0)
               _pressStroke = Color.FromArgb(100, hsb.GetColor());
            else
               _pressStroke = Color.FromArgb(0, hsb.GetColor());
            this.Refresh();

         }
      }
      public Color ColorBaseStroke {
         get { return _baseStroke; }
         set { _baseStroke = value; }
      }
      public Color ColorOnStroke {
         get { return _onStroke; }
         set { _onStroke = value; }
      }
      public Color ColorPressStroke {
         get { return _pressStroke; }
         set { _pressStroke = value; }
      }
      public Color GetColorIncreased(Color color, int h, int s, int b) {
         RibbonColor _color = new RibbonColor(color);
         int ss = _color.GetSaturation();
         float vc = b + _color.GetBrightness();
         float hc = h + _color.GetHue();
         float sc = s + ss;


         _color.VC = vc;
         _color.HC = hc;
         _color.SC = sc;

         return _color.GetColor();


      }

      public Color GetColor(int A, int R, int G, int B) {
         if (A + A0 > 255) { A = 255; } else { A = A + A0; }
         if (R + R0 > 255) { R = 255; } else { R = R + R0; }
         if (G + G0 > 255) { G = 255; } else { G = G + G0; }
         if (B + B0 > 255) { B = 255; } else { B = B + B0; }

         return Color.FromArgb(A, R, G, B);
      }
      #endregion

      protected override void OnPaint(PaintEventArgs pevent) {
         #region Variables & Conf
         Graphics g = pevent.Graphics;
         g.SmoothingMode = SmoothingMode.HighQuality;
         g.InterpolationMode = InterpolationMode.High;
         Rectangle r = new Rectangle(new Point(-1, -1), new Size(this.Width + _radius, this.Height + _radius));
         #endregion

         #region Paint
         GraphicsPath path = new GraphicsPath();
         Rectangle rp = new Rectangle(new Point(0, 0), new Size(this.Width - 1, this.Height - 1)); DrawArc(rp, path);
         FillGradients(g, path);
         DrawImage(g);
         DrawString(g);
         DrawArrow(g);
         #endregion
      }

      protected override void OnResize(EventArgs e) {
         Rectangle r = new Rectangle(new Point(-1, -1), new Size(this.Width + _radius, this.Height + _radius));
         if (this.Size != null) {
            GraphicsPath pathregion = new GraphicsPath();
            DrawArc(r, pathregion);
            this.Region = new Region(pathregion);
         }
         base.OnResize(e);
      }

      #region Paint Methods
      public void FillGradients(Graphics gr, GraphicsPath pa) {
         int origin = this.Height / 3; int end = this.Height; int oe = (end - origin) / 2;
         LinearGradientBrush lgbrush; Rectangle rect;
         if (_showbase == e_showbase.Yes) {
            rect = new Rectangle(new Point(0, 0), new Size(this.Width - 1, this.Height - 1));
            pa = new GraphicsPath();
            DrawArc(rect, pa);
            lgbrush = new LinearGradientBrush(rect, Color.Transparent, Color.Transparent, LinearGradientMode.Vertical);

            #region Main Gradient
            float[] pos = new float[4];
            pos[0] = 0.0F; pos[1] = 0.3F; pos[2] = 0.35F; pos[3] = 1.0F;
            Color[] colors = new Color[4];
            if (i_mode == 0) {
               colors[0] = GetColor(0, 35, 24, 9);
               colors[1] = GetColor(0, 13, 8, 3);
               colors[2] = Color.FromArgb(A0, R0, G0, B0);
               colors[3] = GetColor(0, 28, 29, 14);
            } else {
               colors[0] = GetColor(0, 0, 50, 100);
               colors[1] = GetColor(0, 0, 0, 30);
               colors[2] = Color.FromArgb(A0, R0, G0, B0);
               colors[3] = GetColor(0, 0, 50, 100);
            }
            ColorBlend mix = new ColorBlend();
            mix.Colors = colors;
            mix.Positions = pos;
            lgbrush.InterpolationColors = mix;
            gr.FillPath(lgbrush, pa);
            #endregion

            #region Fill Band
            rect = new Rectangle(new Point(0, 0), new Size(this.Width, this.Height / 3));
            pa = new GraphicsPath(); int _rtemp = _radius; _radius = _rtemp - 1;
            DrawArc(rect, pa);
            if (A0 > 80) {
               gr.FillPath(new SolidBrush(Color.FromArgb(60, 255, 255, 255)), pa);
            }
            _radius = _rtemp;
            #endregion

            #region SplitFill
            if (_splitbutton == e_splitbutton.Yes & mouse) {
               FillSplit(gr);
            }
            #endregion

            #region Shadow
            if (i_mode == 2) {
               rect = new Rectangle(1, 1, this.Width - 2, this.Height);
               pa = new GraphicsPath();
               DrawShadow(rect, pa);
               gr.DrawPath(new Pen(Color.FromArgb(50, 20, 20, 20), 2.0F), pa);

            } else {
               rect = new Rectangle(1, 1, this.Width - 2, this.Height - 1);
               pa = new GraphicsPath();
               DrawShadow(rect, pa);
               if (A0 > 80) {
                  gr.DrawPath(new Pen(Color.FromArgb(100, 250, 250, 250), 3.0F), pa);
               }
            }
            #endregion

            #region SplitLine

            if (_splitbutton == e_splitbutton.Yes) {
               if (_imagelocation == e_imagelocation.Top) {
                  switch (i_mode) {
                     case 1:
                        gr.DrawLine(new Pen(_onStroke), new Point(1, this.Height - _splitdistance), new Point(this.Width - 1, this.Height - _splitdistance));
                        break;
                     case 2:
                        gr.DrawLine(new Pen(_pressStroke), new Point(1, this.Height - _splitdistance), new Point(this.Width - 1, this.Height - _splitdistance));
                        break;
                     default:
                        break;
                  }
               } else if (_imagelocation == e_imagelocation.Left) {
                  switch (i_mode) {
                     case 1:
                        gr.DrawLine(new Pen(_onStroke), new Point(this.Width - _splitdistance, 0), new Point(this.Width - _splitdistance, this.Height));
                        break;
                     case 2:
                        gr.DrawLine(new Pen(_pressStroke), new Point(this.Width - _splitdistance, 0), new Point(this.Width - _splitdistance, this.Height));
                        break;
                     default:
                        break;
                  }
               }

            }
            #endregion

            rect = new Rectangle(new Point(0, 0), new Size(this.Width - 1, this.Height - 1));
            pa = new GraphicsPath();
            DrawArc(rect, pa);
            gr.DrawPath(new Pen(_colorStroke, 0.9F), pa);

            pa.Dispose(); lgbrush.Dispose();


         }
      }

      int offsetx = 0, offsety = 0, imageheight = 0, imagewidth = 0;
      public void DrawImage(Graphics gr) {
         if (this.Image != null) {
            offsety = _imageoffset; offsetx = _imageoffset;
            if (_imagelocation == e_imagelocation.Left | _imagelocation == e_imagelocation.Right) {
               imageheight = this.Height - offsety * 2;
               if (imageheight > _maximagesize.Y & _maximagesize.Y != 0) { imageheight = _maximagesize.Y; }
               imagewidth = (int)((Convert.ToDouble(imageheight) / this.Image.Height) * this.Image.Width);
            } else if (_imagelocation == e_imagelocation.Top | _imagelocation == e_imagelocation.Bottom) {
               imagewidth = this.Width - offsetx * 2;
               if (imagewidth > _maximagesize.X & _maximagesize.X != 0) { imagewidth = _maximagesize.X; }
               imageheight = (int)((Convert.ToDouble(imagewidth) / this.Image.Width) * this.Image.Height);

            }
            switch (_imagelocation) {
               case e_imagelocation.Left:
                  gr.DrawImage(this.Image, new Rectangle(offsetx, offsety, imagewidth, imageheight));
                  break;
               case e_imagelocation.Right:
                  gr.DrawImage(this.Image, new Rectangle(this.Width - imagewidth - offsetx, offsety, imagewidth, imageheight));
                  break;
               case e_imagelocation.Top:
                  offsetx = this.Width / 2 - imagewidth / 2;
                  gr.DrawImage(this.Image, new Rectangle(offsetx, offsety, imagewidth, imageheight));
                  break;
               case e_imagelocation.Bottom:
                  gr.DrawImage(this.Image, new Rectangle(offsetx, this.Height - imageheight - offsety, imagewidth, imageheight));
                  break;
               default:
                  break;
            }
         }
      }

      public void DrawString(Graphics gr) {
         if (this.Text != "") {
            int textwidth = (int)gr.MeasureString(this.Text, this.Font).Width;
            int textheight = (int)gr.MeasureString(this.Text, this.Font).Height;

            int extraoffset = 0;
            Font fontb = new Font(this.Font, FontStyle.Bold);
            if (_title != "") {
               extraoffset = textheight / 2;
            }

            string s1 = this.Text; string s2 = "";
            int jump = this.Text.IndexOf("\\n");

            if (jump != -1) {
               s2 = s1.Substring(jump + 3); s1 = s1.Substring(0, jump);
            }

            #region Calc Color Brightness
            RibbonColor __color = new RibbonColor(Color.FromArgb(R0, G0, B0));
            RibbonColor forecolor = new RibbonColor(this.ForeColor);
            Color _forecolor;

            if (__color.GetBrightness() > 50) {
               forecolor.BC = 1;
               forecolor.SC = 80;
            } else {
               forecolor.BC = 99;
               forecolor.SC = 20;
            }
            _forecolor = forecolor.GetColor();
            #endregion

            switch (_imagelocation) {
               case e_imagelocation.Left:
                  if (this.Title != "") {
                     gr.DrawString(this.Title, fontb, new SolidBrush(_forecolor), new PointF(offsetx + imagewidth + 4, this.Font.Size / 2));
                     gr.DrawString(s1, this.Font, new SolidBrush(_forecolor), new PointF(offsetx + imagewidth + 4, 2 * this.Font.Size + 1));
                     gr.DrawString(s2, this.Font, new SolidBrush(_forecolor), new PointF(offsetx + imagewidth + 4, 3 * this.Font.Size + 4));
                  } else {
                     gr.DrawString(s1, this.Font, new SolidBrush(_forecolor), new PointF(offsetx + imagewidth + 4, this.Height / 2 - this.Font.Size + 1));
                  }

                  break;
               case e_imagelocation.Right:
                  gr.DrawString(this.Title, fontb, new SolidBrush(_forecolor), new PointF(offsetx, this.Height / 2 - this.Font.Size + 1 - extraoffset));
                  gr.DrawString(this.Text, this.Font, new SolidBrush(_forecolor), new PointF(offsetx, extraoffset + this.Height / 2 - this.Font.Size + 1));
                  break;
               case e_imagelocation.Top:
                  gr.DrawString(this.Text, this.Font, new SolidBrush(_forecolor), new PointF(this.Width / 2 - textwidth / 2 - 1, offsety + imageheight));
                  break;
               case e_imagelocation.Bottom:
                  gr.DrawString(this.Text, this.Font, new SolidBrush(_forecolor), new PointF(this.Width / 2 - textwidth / 2 - 1, this.Height - imageheight - textheight - 1));
                  break;
               default:
                  break;
            }

            fontb.Dispose();

         }
      }

      public void DrawArc(Rectangle re, GraphicsPath pa) {
         int _radiusX0Y0 = _radius, _radiusXFY0 = _radius, _radiusX0YF = _radius, _radiusXFYF = _radius;
         switch (_grouppos) {
            case e_groupPos.Left:
               _radiusXFY0 = 1; _radiusXFYF = 1;
               break;
            case e_groupPos.Center:
               _radiusX0Y0 = 1; _radiusX0YF = 1; _radiusXFY0 = 1; _radiusXFYF = 1;
               break;
            case e_groupPos.Right:
               _radiusX0Y0 = 1; _radiusX0YF = 1;
               break;
            case e_groupPos.Top:
               _radiusX0YF = 1; _radiusXFYF = 1;
               break;
            case e_groupPos.Bottom:
               _radiusX0Y0 = 1; _radiusXFY0 = 1;
               break;
         }
         pa.AddArc(re.X, re.Y, _radiusX0Y0, _radiusX0Y0, 180, 90);
         pa.AddArc(re.Width - _radiusXFY0, re.Y, _radiusXFY0, _radiusXFY0, 270, 90);
         pa.AddArc(re.Width - _radiusXFYF, re.Height - _radiusXFYF, _radiusXFYF, _radiusXFYF, 0, 90);
         pa.AddArc(re.X, re.Height - _radiusX0YF, _radiusX0YF, _radiusX0YF, 90, 90);
         pa.CloseFigure();

      }

      public void DrawShadow(Rectangle re, GraphicsPath pa) {
         int _radiusX0Y0 = _radius, _radiusXFY0 = _radius, _radiusX0YF = _radius, _radiusXFYF = _radius;
         switch (_grouppos) {
            case e_groupPos.Left:
               _radiusXFY0 = 1; _radiusXFYF = 1;
               break;
            case e_groupPos.Center:
               _radiusX0Y0 = 1; _radiusX0YF = 1; _radiusXFY0 = 1; _radiusXFYF = 1;
               break;
            case e_groupPos.Right:
               _radiusX0Y0 = 1; _radiusX0YF = 1;
               break;
            case e_groupPos.Top:
               _radiusX0YF = 1; _radiusXFYF = 1;
               break;
            case e_groupPos.Bottom:
               _radiusX0Y0 = 1; _radiusXFY0 = 1;
               break;
         }
         pa.AddArc(re.X, re.Y, _radiusX0Y0, _radiusX0Y0, 180, 90);
         pa.AddArc(re.Width - _radiusXFY0, re.Y, _radiusXFY0, _radiusXFY0, 270, 90);
         pa.AddArc(re.Width - _radiusXFYF, re.Height - _radiusXFYF, _radiusXFYF, _radiusXFYF, 0, 90);
         pa.AddArc(re.X, re.Height - _radiusX0YF, _radiusX0YF, _radiusX0YF, 90, 90);
         pa.CloseFigure();

      }

      public void DrawArrow(Graphics gr) {
         int _size = 1;

         RibbonColor __color = new RibbonColor(Color.FromArgb(R0, G0, B0));
         RibbonColor forecolor = new RibbonColor(this.ForeColor);
         Color _forecolor;

         if (__color.GetBrightness() > 50) {
            forecolor.BC = 1;
            forecolor.SC = 80;
         } else {
            forecolor.BC = 99;
            forecolor.SC = 20;
         }
         _forecolor = forecolor.GetColor();

         switch (_arrow) {
            case e_arrow.ToDown:
               if (_imagelocation == e_imagelocation.Left) {
                  Point[] points = new Point[3];
                  points[0] = new Point(this.Width - 8 * _size - _imageoffset, this.Height / 2 - _size / 2);
                  points[1] = new Point(this.Width - 2 * _size - _imageoffset, this.Height / 2 - _size / 2);
                  points[2] = new Point(this.Width - 5 * _size - _imageoffset, this.Height / 2 + _size * 2);
                  gr.FillPolygon(new SolidBrush(_forecolor), points);
               } else if (_imagelocation == e_imagelocation.Top) {
                  Point[] points = new Point[3];
                  points[0] = new Point(this.Width / 2 + 8 * _size - _imageoffset, this.Height - _imageoffset - 5 * _size);
                  points[1] = new Point(this.Width / 2 + 2 * _size - _imageoffset, this.Height - _imageoffset - 5 * _size);
                  points[2] = new Point(this.Width / 2 + 5 * _size - _imageoffset, this.Height - _imageoffset - 2 * _size);
                  gr.FillPolygon(new SolidBrush(_forecolor), points);
               }
               break;
            case e_arrow.ToRight:
               if (_imagelocation == e_imagelocation.Left) {
                  int arrowxpos = this.Width - _splitdistance + 2 * _imageoffset;
                  Point[] points = new Point[3];
                  points[0] = new Point(arrowxpos + 4, this.Height / 2 - 4 * _size);
                  points[1] = new Point(arrowxpos + 8, this.Height / 2);
                  points[2] = new Point(arrowxpos + 4, this.Height / 2 + 4 * _size);
                  gr.FillPolygon(new SolidBrush(_forecolor), points);
               }
               break;
            default:
               break;
         }

      }

      public void FillSplit(Graphics gr) {
         Color _tranp = Color.FromArgb(200, 255, 255, 255);
         int x1 = this.Width - _splitdistance; int x2 = 0;
         int y1 = this.Height - _splitdistance; int y2 = 0;
         SolidBrush btransp = new SolidBrush(_tranp);
         #region Horizontal
         if (_imagelocation == e_imagelocation.Left) {
            if (xmouse < this.Width - _splitdistance & mouse) //Small button
                {
               Rectangle _r = new Rectangle(x1 + 1, 1, this.Width - 2, this.Height - 1);
               GraphicsPath p = new GraphicsPath();
               int _rtemp = _radius; _radius = 4;
               DrawArc(_r, p);
               _radius = _rtemp;
               gr.FillPath(btransp, p);

            } else if (mouse) //Big Button
                {
               Rectangle _r = new Rectangle(x2 + 1, 1, this.Width - _splitdistance - 1, this.Height - 1);
               GraphicsPath p = new GraphicsPath();
               int _rtemp = _radius; _radius = 4;
               DrawArc(_r, p);
               _radius = _rtemp;
               gr.FillPath(btransp, p);
            }

         }
         #endregion

         #region Vertical
 else if (_imagelocation == e_imagelocation.Top) {
            if (ymouse < this.Height - _splitdistance & mouse) //Small button
                {
               Rectangle _r = new Rectangle(1, y1 + 1, this.Width - 1, this.Height - 1);
               GraphicsPath p = new GraphicsPath();
               int _rtemp = _radius; _radius = 4;
               DrawArc(_r, p);
               _radius = _rtemp;
               gr.FillPath(btransp, p);
            } else if (mouse) //Big Button
                {
               Rectangle _r = new Rectangle(1, y2 + 1, this.Width - 1, this.Height - _splitdistance - 1);
               GraphicsPath p = new GraphicsPath();
               int _rtemp = _radius; _radius = 4;
               DrawArc(_r, p);
               _radius = _rtemp;
               gr.FillPath(btransp, p);
            }
         }
         #endregion
         btransp.Dispose();

      }
      #endregion

      #region About Fading
      private Timer timer1 = new Timer();
      int i_factor = 35;
      public int FadingSpeed {
         get { return i_factor; }
         set {
            if (value > -1) {
               i_factor = value;
            }
         }
      }
      int i_fR = 1; int i_fG = 1; int i_fB = 1; int i_fA = 1;
      void timer1_Tick(object sender, EventArgs e) {
         #region Entering
         if (i_mode == 1) {
            if (System.Math.Abs(ColorOn.R - R0) > i_factor) { i_fR = i_factor; } else { i_fR = 1; }
            if (System.Math.Abs(ColorOn.G - G0) > i_factor) { i_fG = i_factor; } else { i_fG = 1; }
            if (System.Math.Abs(ColorOn.B - B0) > i_factor) { i_fB = i_factor; } else { i_fB = 1; }

            if (ColorOn.R < R0) {
               R0 -= i_fR;
            } else if (ColorOn.R > R0) {
               R0 += i_fR;
            }

            if (ColorOn.G < G0) {
               G0 -= i_fG;
            } else if (ColorOn.G > G0) {
               G0 += i_fG;
            }

            if (ColorOn.B < B0) {
               B0 -= i_fB;
            } else if (ColorOn.B > B0) {
               B0 += i_fB;
            }

            if (ColorOn == Color.FromArgb(R0, G0, B0)) {
               timer1.Stop();
            } else {
               this.Refresh();
            }
         }
         #endregion

         #region Leaving
         if (i_mode == 0) {

            if (System.Math.Abs(ColorBase.R - R0) < i_factor) { i_fR = 1; } else { i_fR = i_factor; }
            if (System.Math.Abs(ColorBase.G - G0) < i_factor) { i_fG = 1; } else { i_fG = i_factor; }
            if (System.Math.Abs(ColorBase.B - B0) < i_factor) { i_fB = 1; } else { i_fB = i_factor; }
            if (System.Math.Abs(ColorBase.A - A0) < i_factor) { i_fA = 1; } else { i_fA = i_factor; }

            if (ColorBase.R < R0) {
               R0 -= i_fR;
            } else if (ColorBase.R > R0) {
               R0 += i_fR;
            }
            if (ColorBase.G < G0) {
               G0 -= i_fG;
            } else if (ColorBase.G > G0) {
               G0 += i_fG;
            }
            if (ColorBase.B < B0) {
               B0 -= i_fB;
            } else if (ColorBase.B > B0) {
               B0 += i_fB;
            }
            if (ColorBase.A < A0) {
               A0 -= i_fA;
            } else if (ColorBase.A > A0) {
               A0 += i_fA;
            }
            if (ColorBase == Color.FromArgb(A0, R0, G0, B0)) {
               timer1.Stop();

            } else {

               this.Refresh();
            }

         }
         #endregion

         this.Refresh();
      }
      #endregion

      #region Mouse Events
      int i_mode = 0; //0 Entering, 1 Out,2 Press
      int xmouse = 0, ymouse = 0; bool mouse = false;

      protected override void OnMouseEnter(EventArgs e) {
         base.OnMouseEnter(e);
         _colorStroke = ColorOnStroke;
         _tempshowbase = _showbase;
         _showbase = e_showbase.Yes;
         i_mode = 1;
         xmouse = PointToClient(Cursor.Position).X;
         mouse = true;
         A0 = 200;
         if (i_factor == 0) {
            R0 = _onColor.R; G0 = _onColor.G; B0 = _onColor.B;
         }
         timer1.Start();
      }

      protected override void OnMouseLeave(EventArgs e) {
         base.OnMouseLeave(e);
         UpdateLeave();
      }

      public void UpdateLeave() {
         if (_keeppress == false | (_keeppress == true & _ispressed == false)) {
            _colorStroke = ColorBaseStroke;
            _showbase = _tempshowbase;
            i_mode = 0;
            mouse = false;
            if (i_factor == 0) {
               R0 = _baseColor.R; G0 = _baseColor.G; B0 = _baseColor.B;
               this.Refresh();
            } else {
               timer1.Stop();
               timer1.Start();
            }
         }
      }

      protected override void OnMouseDown(MouseEventArgs mevent) {
         base.OnMouseDown(mevent);
         R0 = ColorPress.R; G0 = ColorPress.G; B0 = ColorPress.B;
         _colorStroke = ColorPressStroke;
         _showbase = e_showbase.Yes;
         i_mode = 2;
         xmouse = PointToClient(Cursor.Position).X;
         ymouse = PointToClient(Cursor.Position).Y;
         mouse = true;
      }

      protected override void OnMouseUp(MouseEventArgs mevent) {
         R0 = ColorOn.R; G0 = ColorOn.G; B0 = ColorOn.B;
         _colorStroke = ColorOnStroke;
         _showbase = e_showbase.Yes;
         i_mode = 1;
         mouse = true;

         #region ClickSplit
         if (_imagelocation == e_imagelocation.Left & xmouse > this.Width - _splitdistance & _splitbutton == e_splitbutton.Yes) {
            if (_arrow == e_arrow.ToDown) {
               if (this.ContextMenuStrip != null)
                  this.ContextMenuStrip.Opacity = 1.0;
               this.ContextMenuStrip.Show(this, 0, this.Height);

            } else if (_arrow == e_arrow.ToRight) {
               if (this.ContextMenuStrip != null) {
                  ContextMenuStrip menu = this.ContextMenuStrip;
                  this.ContextMenuStrip.Opacity = 1.0;
                  if (this.MenuPos.Y == 0) {
                     this.ContextMenuStrip.Show(this, this.Width + 2, -this.Height);
                  } else {
                     this.ContextMenuStrip.Show(this, this.Width + 2, this.MenuPos.Y);
                  }
               }

            }
         } else if (_imagelocation == e_imagelocation.Top & ymouse > this.Height - _splitdistance & _splitbutton == e_splitbutton.Yes) {
            if (_arrow == e_arrow.ToDown) {
               if (this.ContextMenuStrip != null)
                  this.ContextMenuStrip.Show(this, 0, this.Height);
            }
         }
         #endregion
 else {
            base.OnMouseUp(mevent);

            #region Keep Press
            if (_keeppress) {
               _ispressed = true;

               try {
                  foreach (Control _control in this.Parent.Controls) {
                     if (typeof(UButton) == _control.GetType() & _control.Name != this.Name) {
                        ((UButton)(_control))._ispressed = false;
                        ((UButton)(_control)).UpdateLeave();
                     }
                  }
               } catch { }


            }
            #endregion

         }


      }

      protected override void OnMouseMove(MouseEventArgs mevent) {
         if (mouse & this.SplitButton == e_splitbutton.Yes) {
            xmouse = PointToClient(Cursor.Position).X;
            ymouse = PointToClient(Cursor.Position).Y;
            this.Refresh();
         }
         base.OnMouseMove(mevent);
      }


      #endregion
   }

   public class RibbonColor {

      #region Constructors
      public RibbonColor(Color color) {
         rc = color.R;
         gc = color.G;
         bc = color.B;
         ac = color.A;

         HSV();
      }

      public RibbonColor(uint alpha, int hue, int saturation, int brightness) {
         hc = hue;
         sc = saturation;
         vc = brightness;
         ac = alpha;

         GetColor();
      }
      #endregion

      #region Alpha
      private uint ac = 0; //Alpha > -1
      public uint AC { get { return ac; } set { System.Math.Min(value, 255); } }
      #endregion

      #region RGB
      private int rc = 0, gc = 0, bc = 0; //RGB Components > -1 

      public int RC { get { return rc; } set { rc = System.Math.Min(value, 255); } }
      public int GC { get { return gc; } set { gc = System.Math.Min(value, 255); } }
      public int BC { get { return bc; } set { bc = System.Math.Min(value, 255); } }


      public Color GetColor() {

         int conv;
         double hue, sat, val;
         int basis;

         hue = (float)hc / 100.0f;
         sat = (float)sc / 100.0f;
         val = (float)vc / 100.0f;

         if ((float)sc == 0) // Gray Colors
            {
            conv = (int)(255.0f * val);
            rc = gc = bc = conv;
            return Color.FromArgb((int)rc, (int)gc, (int)bc);
         }

         basis = (int)(255.0f * (1.0 - sat) * val);

         switch ((int)((float)hc / 60.0f)) {
            case 0:
               rc = (int)(255.0f * val);
               gc = (int)((255.0f * val - basis) * (hc / 60.0f) + basis);
               bc = (int)basis;
               break;

            case 1:
               rc = (int)((255.0f * val - basis) * (1.0f - ((hc % 60) / 60.0f)) + basis);
               gc = (int)(255.0f * val);
               bc = (int)basis;
               break;

            case 2:
               rc = (int)basis;
               gc = (int)(255.0f * val);
               bc = (int)((255.0f * val - basis) * ((hc % 60) / 60.0f) + basis);
               break;

            case 3:
               rc = (int)basis;
               gc = (int)((255.0f * val - basis) * (1.0f - ((hc % 60) / 60.0f)) + basis);
               bc = (int)(255.0f * val);
               break;

            case 4:
               rc = (int)((255.0f * val - basis) * ((hc % 60) / 60.0f) + basis);
               gc = (int)basis;
               bc = (int)(255.0f * val);
               break;

            case 5:
               rc = (int)(255.0f * val);
               gc = (int)basis;
               bc = (int)((255.0f * val - basis) * (1.0f - ((hc % 60) / 60.0f)) + basis);
               break;
         }
         return Color.FromArgb((int)ac, (int)rc, (int)gc, (int)bc);

      }

      public uint GetRed() {
         return GetColor().R;
      }

      public uint GetGreen() {
         return GetColor().G;
      }

      public uint GetBlue() {
         return GetColor().B;
      }

      #endregion

      #region HSV

      private int hc = 0, sc = 0, vc = 0;

      public float HC { get { return hc; } set { hc = (int)System.Math.Min(value, 359); hc = (int)System.Math.Max(hc, 0); } }
      public float SC { get { return sc; } set { sc = (int)System.Math.Min(value, 100); sc = (int)System.Math.Max(sc, 0); } }
      public float VC { get { return vc; } set { vc = (int)System.Math.Min(value, 100); vc = (int)System.Math.Max(vc, 0); } }

      public enum C { Red, Green, Blue, None }
      private int maxval = 0, minval = 0;
      private C CompMax, CompMin;

      private void HSV() {
         hc = this.GetHue();
         sc = this.GetSaturation();
         vc = this.GetBrightness();
      }

      public void CMax() {
         if (rc > gc) {
            if (rc < bc) { maxval = bc; CompMax = C.Blue; } else { maxval = rc; CompMax = C.Red; }
         } else {
            if (gc < bc) { maxval = bc; CompMax = C.Blue; } else { maxval = gc; CompMax = C.Green; }
         }
      }

      public void CMin() {
         if (rc < gc) {
            if (rc > bc) { minval = bc; CompMin = C.Blue; } else { minval = rc; CompMin = C.Red; }
         } else {
            if (gc > bc) { minval = bc; CompMin = C.Blue; } else { minval = gc; CompMin = C.Green; }
         }

      }

      public int GetBrightness()  //Brightness is from 0 to 100
      {
         CMax(); return 100 * maxval / 255;
      }

      public int GetSaturation() //Saturation from 0 to 100
      {
         CMax(); CMin();
         if (CompMax == C.None)
            return 0;
         else if (maxval != minval) {
            Decimal d_sat = Decimal.Divide(minval, maxval);
            d_sat = Decimal.Subtract(1, d_sat);
            d_sat = Decimal.Multiply(d_sat, 100);
            return Convert.ToUInt16(d_sat);
         } else {
            return 0;
         }

      }

      public int GetHue() {
         CMax(); CMin();

         if (maxval == minval) {
            return 0;
         } else if (CompMax == C.Red) {
            if (gc >= bc) {
               Decimal d1 = Decimal.Divide((gc - bc), (maxval - minval));
               return Convert.ToUInt16(60 * d1);
            } else {
               Decimal d1 = Decimal.Divide((bc - gc), (maxval - minval));
               d1 = 60 * d1;
               return Convert.ToUInt16(360 - d1);
            }
         } else if (CompMax == C.Green) {
            if (bc >= rc) {
               Decimal d1 = Decimal.Divide((bc - rc), (maxval - minval));
               d1 = 60 * d1;
               return Convert.ToUInt16(120 + d1);
            } else {
               Decimal d1 = Decimal.Divide((rc - bc), (maxval - minval));
               d1 = 60 * d1;
               return Convert.ToUInt16(120 - d1);
            }


         } else if (CompMax == C.Blue) {
            if (rc >= gc) {
               Decimal d1 = Decimal.Divide((rc - gc), (maxval - minval));
               d1 = 60 * d1;
               return Convert.ToUInt16(240 + d1);
            } else {
               Decimal d1 = Decimal.Divide((gc - rc), (maxval - minval));
               d1 = 60 * d1;
               return Convert.ToUInt16(240 - d1);
            }
         } else {
            return 0;
         }
      }  //Hue from 0 to 100

      #endregion

      #region Methods

      public bool IsDark() {
         if (BC > 50) {
            return false;
         } else {
            return true;
         }
      }

      public void IncreaseBrightness(int val) {
         this.VC = this.VC + val;
      }

      public void SetBrightness(int val) {
         this.VC = val;
      }

      public void IncreaseHue(int val) {
         this.HC = this.HC + val;
      }

      public void SetHue(int val) {
         this.HC = val;

      }

      public void IncreaseSaturation(int val) {
         this.SC = this.SC + val;
      }

      public void SetSaturation(int val) {
         this.SC = val;
      }

      public Color IncreaseHSV(int h, int s, int b) {
         this.HC = this.HC + h;
         this.SC = this.SC + s;
         this.VC = this.VC + b;
         return GetColor();
      }

      #endregion
       */
   }

}
