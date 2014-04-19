using MO.Common.Geom;
using MO.Content2d.Resource.Animation;
using MO.Content2d.Resource.Picture;
using MO.Core.Content.Drawing;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   public partial class QDsResourceClipProperty : UserControl
   {
      protected FRsResourceClip _clip;

      protected FRsResourcePicture _picture = new FRsResourcePicture();

      protected SIntPoint2 _barycenter = new SIntPoint2();

      protected SIntPoint2 _ridePoint = new SIntPoint2();

      protected SIntPoint2 _bloodPoint = new SIntPoint2();

      protected SIntPoint2 _wingPoint = new SIntPoint2();

      protected SIntPoint2 _ornamentsPoint = new SIntPoint2();

      protected bool _showBarycenter = false;

      protected bool _showRidePoint = false;

      protected bool _showWingPoint = false;

      protected bool _showBloodPoint = false;

      protected bool _showOrnamentsPoint = false;

      protected bool _autoPlay;

      protected bool _autoScale;

      protected float _clipScale = 1.0f;

      protected int _frameCurrent;

      protected FBitmap _bitmap;

      protected bool _isDrLine = true;

      protected bool _isMain = false;

      public int _btindex;

      protected Bitmap bitMap = new Bitmap(1000, 1000);

      public event MouseEventHandler OnPictureMouseClick;

      public event MouseEventHandler OnControlMouseClick;

      protected int n = 0;

      private Point o;

      protected string _controlName;

      protected string reverseDirectionText;

      protected string reverseCdText;

      // 构建新对象
      FRsResourceClip newClip = new FRsResourceClip();

      public QDsResourceClipProperty() {
         InitializeComponent();
         this.Controls.Add(chbReverse);
         this.Controls.Add(labReverseCd);
         this.Controls.Add(labReversePath);
         this.Controls.Add(chbReverseCd);
         this.Controls.Add(chbReverseDirection);
      }

      //============================================================
      public FRsResourceClip Clip {
         get { return _clip; }
      }

      //============================================================
      public SIntPoint2 Barycenter {
         get { return _barycenter; }
      }

      //============================================================
      public SIntPoint2 RidePoint {
         get { return _ridePoint; }
      }

      //============================================================
      public SIntPoint2 Blood {
         get { return _bloodPoint; }
      }

      //============================================================
      public SIntPoint2 Ornaments {
         get { return _ornamentsPoint; }
      }

      //============================================================
      [DefaultValue(false)]
      public bool ShowBarycenter {
         get { return _showBarycenter; }
         set { _showBarycenter = value; }
      }

      //============================================================
      [DefaultValue(false)]
      public bool ShowRidePoint {
         get { return _showRidePoint; }
         set { _showRidePoint = value; }
      }

      //============================================================
      [DefaultValue(false)]
      public bool ShowBloodPoint {
         get { return _showBloodPoint; }
         set { _showBloodPoint = value; }
      }

      //============================================================
      [DefaultValue(false)]
      public bool ShowOrnamentsPoint {
         get { return _showOrnamentsPoint; }
         set { _showOrnamentsPoint = value; }
      }

      //============================================================
      public FRsResourcePicture Picture {
         get { return _picture; }
         set { _picture = value; }
      }

      public int PicLeft {
         get { return pbxViewer.Bounds.Left; }
      }
      public int PicTop {
         get { return pbxViewer.Bounds.Top; }
      }

      public int PicWidth {
         get { return pbxViewer.ClientRectangle.Width; }
      }
      public int PicHeight {
         get { return pbxViewer.ClientRectangle.Height; }
      }

      public bool IsMain {
         get { return _isMain; }
         set { _isMain = value; }
      }

      //============================================================
      [DefaultValue(false)]
      public bool AutoPlay {
         get { return _autoPlay; }
         set {
            _autoPlay = value;
            if (value) {
               timPlay.Start();
            } else {
               timPlay.Stop();
            }
         }
      }

      //============================================================
      [DefaultValue(false)]
      public bool AutoScale {
         get { return _autoScale; }
         set { _autoScale = value; }
      }

      //============================================================
      [DefaultValue(false)]
      public int FrameDelay {
         set {
            if (null != _clip) {
               _clip.FrameDelay = value;
               timPlay.Stop();
               timPlay.Interval = value;
               timPlay.Start();
            }
         }
      }

      //============================================================
      [DefaultValue(0)]
      public int FrameCurrent {
         get { return _frameCurrent; }
         set { _frameCurrent = value; }
      }

      //============================================================
      public void LoadClip(FRsResourceClip clip) {
         chbReverse.CheckState = CheckState.Unchecked;
         chbReverseDirection.Text = null;
         chbReverseCd.Text = null;
         FRsResourcePicture pic = new FRsResourcePicture();
         _clip = clip;
         _frameCurrent = 0;
         if (null != clip) {
            DirectionShow();
            int width = (int)(_clip.ValidRectangle.Width * _clipScale);
            int height = (int)(_clip.ValidRectangle.Height * _clipScale);
            if (0 != width && 0 != height) {
               ChangeSize(width, height);
            }
            pbxViewer.Visible = true;
         } else {
            pbxViewer.Visible = false;
         }
      }

      //============================================================
      public void DirectionShow() {
         bool bl = (bool)_clip.IsReversed;
         this.chbReverse.Checked = bl;
         this.chbReverseDirection.Text = _clip.ReverseDirection.ToString();
         this.chbReverseCd.Text = _clip.ReverseCd.ToString();
         chbReverse.Checked = _clip.IsReversed;
      }

      //============================================================
      public float ClipScale {
         get { return _clipScale; }
         set { _clipScale = value; }
      }

      //============================================================
      // <T>控制帧的播放速度</T>
      //
      // @author DYWEN 20120605
      //============================================================
      public void DrawFrame() {
         if (null != _clip && !_clip.IsReversed) {
            _clip.Open();
            using (Graphics g = Graphics.FromImage(_bitmap.Native)) {
               g.Clear(BackColor);
               FRsResourceFrame frame = _clip.Frames[_frameCurrent];
               //加载耽搁pose的延迟
               this.timPlay.Interval = frame.Delay;
               int x = (int)((frame.ValidRectangle.Left - Clip.ValidRectangle.Left) * _clipScale);
               int y = (int)((frame.ValidRectangle.Top - Clip.ValidRectangle.Top) * _clipScale);
               int w = (int)(frame.ValidRectangle.Width * _clipScale);
               int h = (int)(frame.ValidRectangle.Height * _clipScale);
               if (!frame.ValidRectangle.IsEmpty) {
                  g.DrawImage(frame.ValidBitmap, x, y, w, h);
               }
               if (_showBarycenter) {
                  Pen p = new Pen(Color.Red, 10);
                  g.FillEllipse(p.Brush, _barycenter.X - 5, _barycenter.Y - 5, 10, 10);
               }
               if (_showRidePoint) {
                  Pen p = new Pen(Color.Blue, 10);
                  g.FillEllipse(p.Brush, _ridePoint.X - 5, _ridePoint.Y - 5, 10, 10);
               }
               if (_showBloodPoint) {
                  Pen p = new Pen(Color.Black, 10);
                  g.FillEllipse(p.Brush, _bloodPoint.X - 5, _bloodPoint.Y - 5, 10, 10);
               }
               if (_showOrnamentsPoint) {
                  Pen p = new Pen(Color.Green, 10);
                  g.FillEllipse(p.Brush, _ornamentsPoint.X - 5, _ornamentsPoint.Y - 5, 10, 10);
               }
               if (_showWingPoint) {
                  Pen p = new Pen(Color.Yellow, 10);
                  g.FillEllipse(p.Brush, _wingPoint.X - 5, _wingPoint.Y - 5, 10, 10);
               }
            }
            _frameCurrent++;
            if (_frameCurrent >= _clip.Frames.Count) {
               _frameCurrent = 0;
            }
            //pbxViewer.Invalidate();
            pbxViewer.Invalidate(new Rectangle(0, 0, pbxViewer.Width, pbxViewer.Height), false);
         }
      }

      //============================================================
      public void DoMoveFrame(int frameIndex) {
         _frameCurrent = frameIndex;
         timPlay.Start();
      }

      //============================================================
      public void DoPlay() {
         _frameCurrent = 0;
         timPlay.Start();
      }

      //============================================================
      public void DoPause() {
         FRsResourceFrame frame = _clip.Frames[_frameCurrent];
         _btindex = frame.Index;
         timPlay.Stop();
         BtIndex();
      }

      public int BtIndex() {
         return _btindex;
      }

      //============================================================
      public void DoResume() {
         timPlay.Start();
      }

      //============================================================
      public void DoStop() {
         _frameCurrent = 0;
         timPlay.Stop();
      }

      //============================================================
      public void ChangeSize(int width, int height) {
         SuspendLayout();
         // 是否自动缩放
         if (_autoScale) {
            AutoScroll = false;
            float rx = (float)ClientSize.Width / (float)_clip.ValidRectangle.Width;
            float ry = (float)ClientSize.Height / (float)_clip.ValidRectangle.Height;
            _clipScale = Math.Min(Math.Min(rx, ry), 1.0f);
            width = (int)(_clip.ValidRectangle.Width * _clipScale);
            height = (int)(_clip.ValidRectangle.Height * _clipScale);
         }
         // 重置位置
         if ((width <= ClientSize.Width) && (height <= ClientSize.Height)) {
            AutoScroll = false;
            pbxViewer.Left = ClientSize.Width / 2 - width / 2;
            pbxViewer.Top = ClientSize.Height / 2 - height / 2;
         } else {
            AutoScroll = true;
            if (width <= ClientSize.Width) {
               pbxViewer.Left = ClientSize.Width / 2 - width / 2;
            } else {
               pbxViewer.Left = 0;
            }
            if ((height <= ClientSize.Height)) {
               pbxViewer.Top = ClientSize.Height / 2 - height / 2;
            } else {
               pbxViewer.Top = 0;
            }
         }

         pbxViewer.Width = width;
         pbxViewer.Height = height;
         // 设置图片（仅在图形变大的情况下重新设置）
         if (null == _bitmap) {
            _bitmap = new FBitmap(width, height);
         } else {
            if ((width > _bitmap.Native.Width) || (height > _bitmap.Native.Height)) {
               _bitmap.Dispose();
               _bitmap = new FBitmap(width, height);
            }
         }
         pbxViewer.Width = width;
         pbxViewer.Height = height;
         pbxViewer.Image = _bitmap.Native;
         ResumeLayout();
      }

      //============================================================
      public void ChangeScale(float scale) {
         _clipScale = scale;
         if (null != _clip) {
            int width = (int)(_clip.ValidRectangle.Width * _clipScale);
            int height = (int)(_clip.ValidRectangle.Height * _clipScale);
            ChangeSize(width, height);
         }
      }

      //============================================================
      private void QResClipViewer_Resize(object sender, System.EventArgs e) {
         if (null != _clip) {
            int width = (int)(_clip.ValidRectangle.Width * _clipScale);
            int height = (int)(_clip.ValidRectangle.Height * _clipScale);
            ChangeSize(width, height);
         }
      }

      //============================================================
      public void DrwImage(int x, int y) {
         using (Graphics g = this.CreateGraphics()) {
            g.Clear(BackColor);
            if (IsMain) {
               if (_showBarycenter) {
                  Pen p = new Pen(Color.Red, 10);
                  g.FillEllipse(p.Brush, x - 5, y - 5, 10, 10);
               }
               if (_showRidePoint) {
                  Pen p = new Pen(Color.Blue, 10);
                  g.FillEllipse(p.Brush, x - 5, y - 5, 10, 10);
               }
               if (_showBloodPoint) {
                  Pen p = new Pen(Color.Black, 10);
                  g.FillEllipse(p.Brush, x - 5, y - 5, 10, 10);
               }
               if (_showOrnamentsPoint) {
                  Pen p = new Pen(Color.Green, 10);
                  g.FillEllipse(p.Brush, x - 5, y - 5, 10, 10);
               }
               if (_showWingPoint) {
                  Pen p = new Pen(Color.Yellow, 10);
                  g.FillEllipse(p.Brush, x - 5, y - 5, 10, 10);
               }
            }
         }
      }

      //============================================================
      private void timPlay_Tick(object sender, System.EventArgs e) {
         DrawFrame();
      }

      //============================================================
      private void pbxViewer_Down(object sender, MouseEventArgs e) {
         o = new Point(e.X, e.Y);
      }

      private void pbxViewer_MouseClick(object sender, MouseEventArgs e) {
         if (null != OnPictureMouseClick) {
            OnPictureMouseClick(this, e);
         }
      }

      //============================================================
      protected override void OnPaint(PaintEventArgs e) {
         base.OnPaint(e);
      }

      //============================================================
      private void QDsResourceClipProperty_MouseClick(object sender, MouseEventArgs e) {
         if (null != OnControlMouseClick) {
            OnControlMouseClick(this, e);
         }
         DrwImage(e.X, e.Y);
      }

      //============================================================
      // <T>获取对象是否反转。</T>
      //
      // @author DYWEN 20120712
      //============================================================
      private void chbDirection_TextChanged(object sender, EventArgs e) {
         _controlName = this.Name;
         if (_controlName == "qdsProperty") {
            return;
         }
         QDsAnimationProperty prop = this.Parent.Parent as QDsAnimationProperty;
         if(prop != null) {
            FRsResourceAnimation animtion = prop.animation;
            if(chbReverse.CheckState == CheckState.Checked) {
               newClip.DirectionCd = nameType(_controlName);
               //FRsResourceClip clip = animtion.SyncClip(animtion.FristClip, (int)newClip.DirectionCd);
               //reverseDirectionText = chbReverseDirection.Text;
               //reverseCdText = chbReverseCd.Text;
               //clip.ReverseDirection = ReverseDirectionType(reverseDirectionText);
               //clip.ReverseCd = ReverseCd(reverseCdText);
               //clip.IsReversed = chbReverse.Checked;
            }
         }
      }

      //============================================================
      // <T>转换数据。</T>
      //
      // @author DYWEN 20120712
      //============================================================
      public int ObjectInsert(string directionCd) {
         switch (directionCd) {
            case "All":
               return 0;
            case "RightDown":
               return 1;
            case "Down":
               return 2;
            case "LeftDown":
               return 3;
            case "Left":
               return 4;
            case "LeftUp":
               return 5;
            case "Up":
               return 6;
            case "RightUp":
               return 7;
            case "Right":
               return 8;
            case "Count":
               return 9;
            default:
               break;
         }
         return 0;
      }

      //============================================================
      // <T>数据转换。</T>
      //
      // @author DYWEN 20120712
      //============================================================
      public ERsDirection ReverseDirectionType(string reverseDirectionText) {
         switch (reverseDirectionText) {
            case "All":
               return ERsDirection.All;
            case "RightDown":
               return ERsDirection.RightDown;
            case "Down":
               return ERsDirection.Down;
            case "LeftDown":
               return ERsDirection.LeftDown;
            case "Left":
               return ERsDirection.Left;
            case "LeftUp":
               return ERsDirection.LeftUp;
            case "Up":
               return ERsDirection.Up;
            case "RightUp":
               return ERsDirection.RightUp;
            case "Right":
               return ERsDirection.Right;
            case "Count":
               return ERsDirection.Count;
            default:
               break;
         }
         return ERsDirection.All;
      }

      //============================================================
      // <T>s数据转换。</T>
      //
      // @author DYWEN 20120712
      //============================================================
      public ERsReverse ReverseCd(string reverseCdText) {
         switch (reverseCdText) {
            case "Level":
               return ERsReverse.Level;
            case "Plumb":
               return ERsReverse.Plumb;
            default:
               break;
         }
         return ERsReverse.Level;
      }

      //============================================================
      // <T>启用反转的时候启用反转选项。</T>
      //
      // @author DYWEN 20120712
      //============================================================
      private void chbReverse_CheckedChanged(object sender, EventArgs e) {
         if (chbReverse.CheckState == CheckState.Checked) {
            newClip.IsReversed = true;
            chbReverseDirection.Enabled = true;
            chbReverseCd.Enabled = true;
         } else {
            chbReverseDirection.Enabled = false;
            chbReverseCd.Enabled = false;
            newClip.IsReversed = false;
            QDsAnimationProperty prop = this.Parent.Parent as QDsAnimationProperty;
            if(prop != null) {
               FRsResourceAnimation animtion = prop.animation;
               // animtion.RemoveClip((int)newClip.DirectionCd);
            }
         }
      }

      //============================================================
      // <T>取得控件标识，确定启用控件对象。</T>
      //
      // @author DYWEN 20120712
      //============================================================
      public ERsDirection nameType(string _controlName) {
         switch (_controlName) {
            case "qClipUnkown":
               return ERsDirection.All;
            case "qClipRightDown":
               return ERsDirection.RightDown;
            case "qClipDown":
               return ERsDirection.Down;
            case "qClipLeftDwon":
               return ERsDirection.LeftDown;
            case "qClipLeft":
               return ERsDirection.Left;
            case "qClipLeftUp":
               return ERsDirection.LeftUp;
            case "qClipUp":
               return ERsDirection.Up;
            case "qClipRightUp":
               return ERsDirection.RightUp;
            case "qClipRight":
               return ERsDirection.Right;
            default:
               return ERsDirection.All;
         }
      }
   }
}
