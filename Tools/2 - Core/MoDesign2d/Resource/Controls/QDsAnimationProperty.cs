using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Animation;
using MO.Content2d.Resource.Common;
using MO.Core.Content.Drawing;
using MO.Design2d.Resource.Forms;
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   public partial class QDsAnimationProperty : QDsResource
   {
      // 当前动画
      protected FRsResourceAnimation _resourceAnimation;

      // 当前控件集合
      protected QDsResourceClipProperty[] _clipViewers;

      // 当前控件
      protected QDsResourceClipProperty _currentViewer;

      // 保存坐标
      protected string direction;

      // 当前剪辑
      protected FRsResourceClip _currentClip;

      // 当前鼠标动作
      protected EDsMouseAction _mouseAction = EDsMouseAction.Unknown;

      // 鼠标位置
      protected SIntPoint2 _mousePosition = new SIntPoint2();

      protected Check _checkd;

      protected bool _showBarycenter = false;

      protected bool _showRidePoint = false;

      protected bool _showWingPoint = false;

      protected bool _showBloodPoint = false;

      protected bool _showOrnamentsPoint = false;

      protected int configX;

      protected int configY;

      protected int maxH = 0;

      protected int maxW = 0;

      protected FBitmap _fCutBitmap;

      public enum Check
      {
         SYSTEM,
         MODULE,
      }

      //============================================================
      public QDsAnimationProperty() {
         InitializeComponent();
         _clipViewers = new QDsResourceClipProperty[] {
               qClipUnkown, qClipRightDown,
               qClipDown, qClipLeftDwon,qClipLeft,qClipLeftUp,qClipUp,qClipRightUp ,qClipRight  
                 };
      }

      //============================================================
      // <T>当选中自定义控件帧时发生。</T>
      //
      // @autor DYWEN 20120602
      //============================================================
      protected Check Checkd {
         get { return _checkd; }
         set { _checkd = value; }
      }

      //============================================================
      public FRsResourceAnimation animation {
         get { return _resourceAnimation; }
      }

      //============================================================
      // <T>选中当前方向的图像集合。</T>
      //
      // @autor 
      //============================================================
      protected void SelectClip(FRsResourceClip clip) {
         _currentClip = clip;
         InfoText(_currentClip);
         // 设置帧信息
         txtFrameCount.Text = clip.FrameCount.ToString();
         txtFrameWidth.Text = clip.ValidRectangle.Width.ToString();
         txtFrameHeight.Text = clip.ValidRectangle.Height.ToString();
         txtFramesPerSecond.Text = clip.FrameDelay.ToString();
         txtTotalTime.Text = clip.FrameDelay.ToString();
         // 设置帧列表
         lvwFrames.Items.Clear();
         if (null != clip) {
            //if (clip._isCheckd == ERsIsCheckd.MODULE) {
            //   this.chkModule.Checked = true;
            //}
            //if (clip._isCheckd == ERsIsCheckd.SYSTEM) {
            //   this.chkSystem.Checked = true;
            //}
            foreach (FRsResourceFrame frame in clip.Frames) {
               ListViewItem qItem = new ListViewItem(frame.Index.ToString());
               qItem.SubItems.Add(frame.ValidBarycenter.ToString());
               qItem.SubItems.Add(frame.ValidRectangle.Width.ToString() + frame.ValidRectangle.Height.ToString());
               string name = frame.FileName;
               name = name.Substring(name.LastIndexOf("\\") + 1);
               qItem.SubItems.Add(name);
               lvwFrames.Items.Add(qItem);
            }
         }
         tbrModuleDelay.Clear();
         tbrModuleDelay.LoadInfomation(_currentClip);
         // 加载动画
         qdsProperty.LoadClip(clip);
         qdsProperty.DoPlay();
      }

      //============================================================
      // <T>当选中自定义控件帧时发生。</T>
      //
      // @autor DYWEN 20120602
      //============================================================
      private void selectDelay(int value) {
         // 计算延迟
         int delay = 1000 / value;
         txtFramesPerSecond.Text = tbrSystemDelay.Value.ToString();
         txtFrameDelay.Text = delay.ToString();
         // 设置延迟
         foreach (QDsResourceClipProperty viewer in _clipViewers) {
            viewer.FrameDelay = delay;
         }
         qdsProperty.FrameDelay = delay;
      }

      //============================================================
      protected void SelectClipViewer(QDsResourceClipProperty clipViewer) {
         _currentViewer = clipViewer;
         _currentClip = clipViewer.Clip;
         if (null != _currentClip) {
            foreach (QDsResourceClipProperty viewer in _clipViewers) {
               viewer.BorderStyle = BorderStyle.FixedSingle;
               viewer.BackColor = Color.Goldenrod;
            }
            clipViewer.BorderStyle = BorderStyle.Fixed3D;
            clipViewer.BackColor = Color.Transparent;
            SelectClip(_currentClip);
         }
      }

      //============================================================
      protected void LoadClipList() {
         // 加载方向
         for (int n = 0; n < (int)ERsDirection.Count; n++) {
            QDsResourceClipProperty clipViewer = _clipViewers[n];
            FRsResourceClip clip = _resourceAnimation.Clips[n];
            clipViewer.LoadClip(clip);
            //if (null != clip && clip.isShow) {
            if (clip != null) {
               clipViewer.DoPlay();
            }
         }
         // 加载当前显示
         FRsResourceClip firstClip = _resourceAnimation.FristClip;
         qdsProperty.LoadClip(firstClip);
         if (null != firstClip) {
            QDsResourceClipProperty viewer = _clipViewers[(int)firstClip.DirectionCd];
            SelectClipViewer(viewer);
            InfoText(firstClip);
         }
      }

      //============================================================
      //<T>设置重心乘骑点血条位置。<T>
      //
      //============================================================
      private void InfoText(FRsResourceClip clp) {
         // 设置剪辑重心
         configX = clp.ValidBarycenter.X;
         configY = clp.ValidBarycenter.Y;
         qdsProperty.Barycenter.Set(configX, configY);
         txtbaryCenterX.Text = configX.ToString();
         txtbaryCenterY.Text = configY.ToString();
         // 设置乘骑点
         int pointX = clp.RidePoint.X;
         int pointY = clp.RidePoint.Y;
         txtRidePointX.Text = pointX.ToString();
         txtRidePointY.Text = pointY.ToString();
         ////pointX = pointX;
         ////pointY = pointY;
         qdsProperty.RidePoint.Set(pointX, pointY);
         //int wpointX = clp.WingPoint.X;
         //int wpointY = clp.WingPoint.Y;
         //txtWingPointX.Text = wpointX.ToString();
         //txtWingPointY.Text = wpointY.ToString();
         //qdsProperty.WingPoint.Set(wpointX, wpointY);
         // 设置血条位置
         if (_currentClip.Blood != null) {
            int bloodX = clp.Blood.X;
            int bloodY = clp.Blood.Y;
            BloodX.Text = bloodX.ToString();
            BloodY.Text = bloodY.ToString();
            qdsProperty.Blood.Set(bloodX, bloodY);
         } else {
            qdsProperty.Blood.Set(0, 0);
            BloodX.Text = 0.ToString();
            BloodY.Text = 0.ToString();
         }
      }

      //============================================================
      public void DoModeDisplay() {
         tssSplit3.Visible = false;
         tsbExport.Visible = false;
         tsbSave.Visible = false;
         txtId.Enabled = false;
         txtName.Enabled = false;
         txtCode.Enabled = false;
      }

      //============================================================
      public void DoModeEdit() {
      }


      //============================================================
      public override void LoadResource(FRsResource resource) {
         cmbAlphaVelue.Text = null;
         base.LoadResource(resource);
         _resourceAnimation = resource as FRsResourceAnimation;
         // 加载信息
         LoadInformation();
         // 加载剪辑列表
         LoadClipList();
         // 设置延迟
         tbrSystemDelay.Value = 1000 / _resourceAnimation.FrameDelay;
         selectDelay(tbrSystemDelay.Value);
         // 设置关键帧
         tbrModuleDelay.Clear();
         tbrModuleDelay.LoadInfomation(_currentClip);
         switch (_resourceAnimation.TimeoutCd) {
            case "none":
               radNull.Checked = true;
               break;
            case "short":
               radShort.Checked = true;
               break;
            case "middle":
               radMiddle.Checked = true;
               break;
            case "long":
               radLong.Checked = true;
               break;
         }
         switch (_resourceAnimation.QualityCd) {
            case ERsResourceQuality.Max:
               ranPalettePixel5.Checked = true;
               break;
            case ERsResourceQuality.Middle:
               ranPalettePixel3.Checked = true;
               break;
            case ERsResourceQuality.Lower:
               ranPalettePixel2.Checked = true;
               break;
            case ERsResourceQuality.High:
               ranPalettePixel4.Checked = true;
               break;
            case ERsResourceQuality.Min:
               ranPalettePixel1.Checked = true;
               break;
            default:
               break;
         }
      }

      //============================================================
      //<T>保存</T>
      //
      // @author DYWEN 20120602
      //============================================================
      public override void SaveResource(FRsResource resource) {
         base.SaveResource(resource);
         if (_checkd == Check.SYSTEM) {
            _resourceAnimation.FrameDelay = RInt.Parse(txtFrameDelay.Text);
            //_resourceAnimation.Id = RInt.Parse(txtId.Text);
         }
         if (_showBarycenter) {
            _currentClip.ValidBarycenter.X = RInt.Parse(txtbaryCenterX.Text);
            _currentClip.ValidBarycenter.Y = RInt.Parse(txtbaryCenterY.Text);
         }
         if (_showBloodPoint) {
            _currentClip.Blood.X = RInt.Parse(BloodX.Text);
            _currentClip.Blood.Y = RInt.Parse(BloodY.Text);
         }
         if (_showRidePoint) {
            _currentClip.RidePoint.X = RInt.Parse(txtRidePointX.Text);
            _currentClip.RidePoint.Y = RInt.Parse(txtRidePointY.Text);
         }
         //if (_showWingPoint) {
         //   _currentClip.WingPoint.X = RInt.Parse(txtWingPointX.Text);
         //   _currentClip.WingPoint.Y = RInt.Parse(txtWingPointY.Text);
         //}
         if (ranPalettePixel1.Checked) {
            _resourceAnimation.QualityCd = ERsResourceQuality.Min;
         }
         if (ranPalettePixel2.Checked) {
            _resourceAnimation.QualityCd = ERsResourceQuality.Lower;
         }
         if (ranPalettePixel3.Checked) {
            _resourceAnimation.QualityCd = ERsResourceQuality.Middle;
         }
         if (ranPalettePixel4.Checked) {
            _resourceAnimation.QualityCd = ERsResourceQuality.High;
         }
         if (ranPalettePixel5.Checked) {
            _resourceAnimation.QualityCd = ERsResourceQuality.Max;
         }
      }


      //============================================================
      private void btnGray_Click(object sender, EventArgs e) {
         qdsProperty.BackColor = Color.Gray;
      }

      //============================================================
      private void btnBlue_Click(object sender, EventArgs e) {
         qdsProperty.BackColor = Color.Blue;
      }

      //============================================================
      private void btnGreen_Click(object sender, EventArgs e) {
         qdsProperty.BackColor = Color.Green;
      }

      //============================================================
      private void btnRed_Click(object sender, EventArgs e) {
         qdsProperty.BackColor = Color.Red;
      }

      //============================================================
      private void btnWhite_Click(object sender, EventArgs e) {
         qdsProperty.BackColor = Color.White;
      }

      //============================================================
      private void btnBlack_Click(object sender, EventArgs e) {
         qdsProperty.BackColor = Color.Black;
      }

      //============================================================
      private void txtKeyFrame_TextChanged(object sender, EventArgs e) {
         string text = txtKeyFrame.Text;
         int keyFrame = 0;
         try { keyFrame = RInt.Parse(text); } catch {
            MessageBox.Show("请输入数字");
         }
         if (keyFrame >= _currentClip.FrameCount) {
            MessageBox.Show("超出帧数范围");
            keyFrame = _currentClip.FrameCount - 1;
         }
         if (keyFrame < 0) {
            MessageBox.Show("超出帧数范围");
            keyFrame = 0;
         }
         txtKeyFrame.Text = keyFrame.ToString();
      }

      //============================================================
      // <T>设置重心按钮事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnSetCenter_Click(object sender, EventArgs e) {
         //Clear();
         //btnSetCenter.Tag = "1";
         //int X = int.Parse(txtbaryCenterX.Text);
         //int Y = int.Parse(txtbaryCenterY.Text);
         //qdsProperty.Barycenter.X = X;
         //qdsProperty.Barycenter.Y = Y;
         //_currentClip.ValidBarycenter.X = X;
         //_currentClip.ValidBarycenter.Y = Y;
      }

      //============================================================
      // <T>重心显示或者隐藏事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnCenterVisible_Click(object sender, EventArgs e) {
         if (_showBarycenter) {
            qdsProperty.ShowBarycenter = false;
            _showBarycenter = false;
         } else {
            qdsProperty.ShowBarycenter = true;
            _showBarycenter = true;
         }
      }

      //============================================================
      // <T>设置血条位置按钮事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnBlood_Click(object sender, EventArgs e) {
         Clear();
         btnBlood.Tag = "1";
         int X = int.Parse(BloodX.Text);
         int Y = int.Parse(BloodY.Text);
         _currentClip.Blood.X = X;
         _currentClip.Blood.Y = Y; ;
         qdsProperty.Blood.X = X;
         qdsProperty.Blood.Y = Y;
      }

      //============================================================
      // <T>血条显示或者隐藏事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnBloodVisible_Click(object sender, EventArgs e) {
         if (_showBloodPoint) {
            qdsProperty.ShowBloodPoint = false;
            _showBloodPoint = false;
         } else {
            qdsProperty.ShowBloodPoint = true;
            _showBloodPoint = true;
         }
      }

      //============================================================
      // <T>设置乘骑点位置按钮事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnSetRidePoint_Click(object sender, EventArgs e) {
         Clear();
         btnSetRidePoint.Tag = "1";
         int X = int.Parse(txtRidePointX.Text);
         int Y = int.Parse(txtRidePointY.Text);
         _currentClip.RidePoint.X = X;
         _currentClip.RidePoint.Y = Y;
         qdsProperty.RidePoint.X = X;
         qdsProperty.RidePoint.Y = Y;
      }

      //============================================================
      // <T>乘骑点显示或者隐藏事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnRidePointVisible_Click(object sender, EventArgs e) {
         if (_showRidePoint) {
            qdsProperty.ShowRidePoint = false;
            _showRidePoint = false;
         } else {
            qdsProperty.ShowRidePoint = true;
            _showRidePoint = true;
         }
      }

      //============================================================
      // <T>设置挂饰位置按钮事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnOrnaments_Click(object sender, EventArgs e) {
         Clear();
         //btnOrnaments.Tag = "1";
         //int X = int.Parse(OrnamentsX.Text);
         //int Y = int.Parse(OrnamentsY.Text);
         //_currentClip.Ornament.X = X;
         //_currentClip.Ornament.Y = Y;
         //X += _currentClip.LocalBarycenter.X;
         //Y += _currentClip.LocalBarycenter.Y;
         //qdsProperty.Ornaments.X = X;
         //qdsProperty.Ornaments.Y = Y;
      }

      //============================================================
      // <T>挂饰位置显示或者隐藏事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void btnOrnamentsVisible_Click(object sender, EventArgs e) {
         if (_showOrnamentsPoint) {
            qdsProperty.ShowOrnamentsPoint = false;
            _showOrnamentsPoint = false;
         } else {
            qdsProperty.ShowOrnamentsPoint = true;
            _showOrnamentsPoint = true;
         }
      }

      //============================================================
      // <T>保存事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbSave_Click(object sender, EventArgs e) {
         SaveInformation();
         SaveResource(_resource);
         _resourceAnimation.Store();
         MessageBox.Show("存储文件完成。");
      }

      //============================================================
      // <T>重置信息。</T>
      //============================================================
      private void Clear() {
         btnSetCenter.Tag = "0";
         btnBlood.Tag = "0";
         btnSetRidePoint.Tag = "0";
      }

      //============================================================
      protected void SaveInformation() {
         if (chbAplaa.CheckState == CheckState.Checked) {
            _resourceAnimation.OptionAlpha = true;
         } else {
            _resourceAnimation.OptionAlpha = false;
         }
      }

      //============================================================
      private void tsbPlay_Click(object sender, EventArgs e) {
         tbrModuleDelay._controls[qdsProperty.BtIndex()].BackColor = SystemColors.Control;
         qdsProperty.DoPlay();
      }

      //============================================================
      private void tsbPause_Click(object sender, EventArgs e) {
         qdsProperty.DoPause();
         tbrModuleDelay._controls[qdsProperty.BtIndex()].BackColor = Color.Red;
      }

      //============================================================
      private void tsbResume_Click(object sender, EventArgs e) {
         tbrModuleDelay._controls[qdsProperty.BtIndex()].BackColor = SystemColors.Control;
         qdsProperty.DoResume();
      }

      //============================================================
      // <T>背景色按钮事件。</T>
      // 
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbStop_Click(object sender, EventArgs e) {
         qdsProperty.DoStop();
      }

      //============================================================
      // <T>背景色按钮事件。</T>
      // 
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbBackColor_Click(object sender, EventArgs e) {
      }

      //============================================================
      // <T>加载信息。</T>
      //============================================================
      protected void LoadInformation() {
         // 加载资源
         //txtId.Text = _resourceAnimation.Id.ToString();
         if (_resourceAnimation.OptionAlpha) {
            chbAplaa.CheckState = CheckState.Checked;
         } else {
            chbAplaa.CheckState = CheckState.Unchecked;
         }
      }

      //============================================================
      // <T>加载按钮事件。</T>
      // 
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbReload_Click(object sender, EventArgs e) {
         FStrings directorys = RDirectory.ListFiles(_resource.Directory);
         foreach (string fileName in directorys) {
            bool haveIt = false;
            if (fileName.EndsWith(".png")) {
               //foreach (FDsResourceClip clip in _resourceAnimation.Clips) {
               //   if (haveIt) {
               //      break;
               //   }
               //   if (null != clip) {
               //      foreach (FDsResourceFrame frame in clip.Frames) {
               //         if (fileName.EndsWith(frame.FileName)) {
               //            haveIt = true;
               //            break;
               //         }
               //      }
               //   }
               //}
               if (!haveIt) {
                  MessageBox.Show(fileName);
               }
            }
         }
      }

      //============================================================
      //<T>调整帧速。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tbrDelay_Scroll(object sender, EventArgs e) {
         selectDelay(tbrSystemDelay.Value);
      }

      //============================================================
      // <T>选中是否透明控件。</T>
      //============================================================
      private void chbAplaa_CheckedChanged(object sender, EventArgs e) {
         if (chbAplaa.CheckState == CheckState.Checked) {
            _resourceAnimation.OptionAlpha = true;
         } else {
            _resourceAnimation.OptionAlpha = false;
         }
      }

      //============================================================
      // <T>选中剪辑事件。</T>
      //============================================================
      private void qClipUnkown_OnPictureMouseClick(object sender, MouseEventArgs e) {
         QDsResourceClipProperty clipViewer = sender as QDsResourceClipProperty;
         if (null != clipViewer) {
            SelectClipViewer(clipViewer);
         }
      }

      //============================================================
      // <T>导出事件。</T>
      //
      // @param sender 数据对象
      // @param e      事件产生者
      //============================================================
      private void tsbExport_Click(object sender, EventArgs e) {
         _resourceAnimation.Export(ERsExportMode.CompressDeflate);
      }

      //============================================================
      private void qdsProperty_OnPictureMouseClick(object sender, MouseEventArgs e) {
         qdsProperty.IsMain = false;
         string center = btnSetCenter.Tag as string;
         string blood = btnBlood.Tag as string;
         string ride = btnSetRidePoint.Tag as string;
         if (center.Equals("1")) {
            int newx = e.X;
            int newy = e.Y;
            qdsProperty.Barycenter.Set(newx, newy);
            _currentClip.ValidBarycenter.X = newx;
            _currentClip.ValidBarycenter.Y = newy;
            txtbaryCenterX.Text = newx.ToString();
            txtbaryCenterY.Text = newy.ToString();
         }
         if (blood.Equals("1")) {
            int newx = e.X;
            int newy = e.Y;
            qdsProperty.Blood.Set(newx, newy);
            newx -= _currentClip.ValidBarycenter.X;
            newy -= _currentClip.ValidBarycenter.Y;
            _currentClip.Blood.X = newx;
            _currentClip.Blood.Y = newy;
            BloodX.Text = newx.ToString();
            BloodY.Text = newy.ToString();
         }
         if (ride.Equals("1")) {
            int newx = e.X;
            int newy = e.Y;
            qdsProperty.RidePoint.Set(newx, newy);
            newx -= _currentClip.ValidBarycenter.X;
            newy -= _currentClip.ValidBarycenter.Y;
            _currentClip.RidePoint.X = newx;
            _currentClip.RidePoint.Y = newy;
            txtRidePointX.Text = newx.ToString();
            txtRidePointY.Text = newy.ToString();
         }
      }

      //============================================================
      private void qdsProperty_OnControlMouseClick(object sender, MouseEventArgs e) {
         qdsProperty.IsMain = true;
         string center = btnSetCenter.Tag as string;
         string blood = btnBlood.Tag as string;
         string ride = btnSetRidePoint.Tag as string;
         if (center.Equals("1")) {
            SIntPoint2 point = CheckPoints(e.X, e.Y);
            int newx = point.X;
            int newy = point.Y;
            qdsProperty.Barycenter.Set(newx, newy);
            _currentClip.ValidBarycenter.X = newx;
            _currentClip.ValidBarycenter.Y = newy;
            txtbaryCenterX.Text = newx.ToString();
            txtbaryCenterY.Text = newy.ToString();
         }
         if (blood.Equals("1")) {
            SIntPoint2 point = CheckPoints(e.X, e.Y);
            int newx = point.X;
            int newy = point.Y;
            qdsProperty.Blood.Set(newx, newy);
            _currentClip.Blood.X = newx;
            _currentClip.Blood.Y = newy;
            BloodX.Text = newx.ToString();
            BloodY.Text = newy.ToString();
         }
         if (ride.Equals("1")) {
            SIntPoint2 point = CheckPoints(e.X, e.Y);
            int newx = point.X;
            int newy = point.Y;
            qdsProperty.RidePoint.Set(newx, newy);
            _currentClip.RidePoint.X = newx;
            _currentClip.RidePoint.Y = newy;
            txtRidePointX.Text = newx.ToString();
            txtRidePointY.Text = newy.ToString();
         }
      }

      //============================================================
      public SIntPoint2 CheckPoints(int x, int y) {
         SIntPoint2 point = new SIntPoint2();
         int left = qdsProperty.PicLeft;
         int top = qdsProperty.PicTop;
         int width = qdsProperty.PicWidth;
         int height = qdsProperty.PicHeight;
         int centerX = Math.Abs(_currentClip.ValidBarycenter.X);
         int centerY = Math.Abs(_currentClip.ValidBarycenter.Y);
         int pointX = left + centerX;
         int pointY = top + centerY;
         point.X = x - pointX;
         point.Y = y - pointY;
         return point;
      }

      //============================================================
      // <T>当选中系统控件帧时发生。</T>
      //
      // @autor DYWEN 20120602
      //============================================================
      private void chkSystem_CheckStateChanged(object sender, EventArgs e) {
         if (chkSystem.CheckState == CheckState.Checked) {
            tbrModuleDelay.Clear();
            tbrModuleDelay.LoadInfomation(_currentClip);
            _checkd = Check.SYSTEM;
            if (_currentClip != null) {
               _currentClip._isCheckd = ERsIsCheckd.SYSTEM;
            }
            chkModule.CheckState = CheckState.Unchecked;
            tbrSystemDelay.Enabled = true;
            tbrModuleDelay.Enabled = false;
         }
         //else
         //{
         //    _checkd = Check.MODULE;
         //    chkModule.CheckState = CheckState.Checked;
         //    tbrSystemDelay.Enabled = true;
         //    tbrModuleDelay.Enabled = false;
         //}
         //if (chkSystem.CheckState == CheckState.Checked) {
         //   tbrModuleDelay.Clear();
         //   //if (_currentClip != null) {
         //   //   _currentClip._isCheckd = EDsIsCheckd.SYSTEM;
         //   //}
         //   chkModule.CheckState = CheckState.Unchecked;
         //   tbrModuleDelay.Enabled = false;
         //} else {
         //   chkModule.CheckState = CheckState.Checked;
         //   tbrModuleDelay.Enabled = true;
         //   tbrSystemDelay.Enabled = false;
         //}
         // 移动滑块时发生
         selectDelay(tbrSystemDelay.Value);
      }

      //============================================================
      // <T>当选中自定义控件帧时发生。</T>
      //
      // @autor DYWEN 20120602
      //============================================================
      private void chkModule_CheckedChanged(object sender, EventArgs e) {
         if (chkModule.CheckState == CheckState.Checked) {
            tbrModuleDelay.Clear();
            tbrModuleDelay.LoadInfomation(_currentClip);
            _checkd = Check.MODULE;
            if (_currentClip != null) {
               _currentClip._isCheckd = ERsIsCheckd.MODULE;
            }
            chkSystem.CheckState = CheckState.Unchecked;
            tbrSystemDelay.Enabled = false;
            tbrModuleDelay.Enabled = true;
         }
         //} else {
         //   _checkd = Check.SYSTEM;
         //   chkSystem.CheckState = CheckState.Checked;
         //   tbrSystemDelay.Enabled = true;
         //   tbrModuleDelay.Enabled = false;
         //}
         qdsProperty.DoPlay();
      }

      //============================================================
      // <T>选择切割的值。</T>
      //
      // @autor DYWEN 20120712
      //============================================================
      private void cmbAlphaVelue_TextChanged(object sender, EventArgs e) {
         //_resourceAnimation.AlphaVelue = RInt.Parse(cmbAlphaVelue.Text);
      }

      //============================================================
      // <T>切割。</T>
      //
      // @autor DYWEN 20120712
      //============================================================
      private void ButCutter_Click(object sender, EventArgs e) {
         _resourceAnimation.Open();
         MessageBox.Show("切割成功！");
      }

      //============================================================
      // <T>合并压缩。</T>
      //
      // @autor DYWEN 20120712
      //============================================================
      public void tsbShowImage_Click(object sender, EventArgs e) {
         GetRectangle();
         SIntPoint2 sintp2 = GetAllRectangle(maxW, maxH);
         Bitmap _bitmap = new Bitmap(sintp2.X, sintp2.Y);
         FRsResourceClip rcl = _resourceAnimation.FristClip;
         Graphics g = Graphics.FromImage(_bitmap);
         for (int i = 0; i < rcl.FrameCount; i++) {
            Bitmap bmn = rcl.Frames.Items[i].Bitmap.Native;
            _fCutBitmap = new FBitmap(bmn);
            SIntRectangle rct = _fCutBitmap.TestValidRectangle(1);
            Bitmap p = bmn.Clone(new Rectangle(rct.Left, rct.Top, rct.Width, rct.Height), PixelFormat.Format32bppArgb);
            // 防止图片缩放问题
            g.DrawImage(p, i * maxW, 0, p.Width, p.Height);
         }
         QDsResourceViewForm sci = new QDsResourceViewForm();
         foreach (Control item in sci.Controls) {
            if (item is PictureBox) {
               PictureBox picbox = item as PictureBox;
               picbox.Width = _bitmap.Width;
               picbox.Height = _bitmap.Height;
               picbox.BackgroundImage = _bitmap;
            }
         }
         String mapInfo = "宽:" + _bitmap.Width + " " + "高:" + _bitmap.Height;
         sci.Text = mapInfo;
         g.Dispose();
         sci.Show();
      }

      //============================================================
      // <T>获取有效区域</T>
      //
      // @autor DYWEN 20120816
      //============================================================
      public void GetRectangle() {
         FRsResourceClip rcl = _resourceAnimation.FristClip;
         for (int i = 0; i < rcl.FrameCount; i++) {
            Bitmap bmn = rcl.Frames.Items[i].Bitmap.Native;
            FBitmap fbitmap = new FBitmap(bmn);
            SIntRectangle rcthw = fbitmap.TestValidRectangle(1);
            int height = rcthw.Height;
            if (maxH < height) {
               maxH = height;
            }
            int width = rcthw.Width;
            if (maxW < width) {
               maxW = width;
            }
         }
      }

      //============================================================
      // <T>获取合并以后资源的大小</T>
      //
      // @autor DYWEN 20120816
      //============================================================
      public SIntPoint2 GetAllRectangle(int maxW, int maxH) {
         SIntPoint2 sintp = new SIntPoint2();
         FRsResourceClip rcl = _resourceAnimation.FristClip;
         int count = rcl.FrameCount;
         sintp.X = count * maxW;
         sintp.Y = maxH;
         return sintp;
      }

      //============================================================
      // <T>设置超时信息。</T>
      //============================================================
      private void radTimeout_CheckedChanged(object sender, EventArgs e) {
         RadioButton button = sender as RadioButton;
         if ((null != button) && (null != button.Tag)) {
            _resourceAnimation.TimeoutCd = button.Tag.ToString();
         }
      }

      //============================================================
      // <T>选择切割质量。</T>
      //============================================================
      private void ranPalettePixel_CheckedChanged(object sender, EventArgs e) {
         RadioButton button = sender as RadioButton;
         if ((null != button) && (null != button.Tag)) {
            _resourceAnimation.QualityCd = button.Tag.ToString();
         }
      }
   }
}
