using System.Windows.Forms;
using MO.Common.Lang;
using MO.Core.Forms.Device;
using MO.DX.Core;
using MO.DX.Core.Device;
using MO.DX.Core.Model;
using MO.DX.Core.Stage.Common;
using MO.Design3d.Core.Scene;
using MO.Design3d.Common;
using System.Drawing;

namespace MO.Design3d.Template.Forms
{
    //============================================================
    public partial class QDsTemplateEditorForm : Form
   {
      #region 唯一实例
      protected static QDsTemplateEditorForm _instance;

      //============================================================
      public static QDsTemplateEditorForm Instance {
         get {
            if (null == _instance) {
               _instance = new QDsTemplateEditorForm();
            }
            return _instance;
         }
      }
      #endregion

      protected bool _autoClose;

      protected string _templateName;

      protected FDxDevice3D _device;

      protected FDxDesignScene _scene;

      protected FDxSpatial _spatial;

      //============================================================
      public QDsTemplateEditorForm() {
         InitializeComponent();
      }

      //============================================================
      public bool AutoClose {
         get { return _autoClose; }
         set { _autoClose = value; }
      }

      //============================================================
      public void LoadTemplate(string templateName) {
         _templateName = templateName;
         if (null != _scene) {
            // 清空场景
            _scene.Reset();
            // 查看指定模板
            _spatial = RDxCore.SpatialConsole.Create(_device, _templateName);
            _scene.Push(_spatial);
            // 加载模板
            //qdsTemplateTree.LoadSpatial(_spatial);
         }
      }

      //============================================================
      private void QDsTemplateEditorForm_Load(object sender, System.EventArgs e) {
         // 创建设备
         _device = RDxCore.Adapter.CreateDevice(pnlViewport.Handle, pnlViewport.Width, pnlViewport.Height);
         // 创建舞台
         FDxStage stage = new FDxStage();
         RDxCore.StageConsole.ActiveStage = stage;
         // 创建场景
         _scene = new FDxDesignScene();
         _scene.Device = _device;
         _scene.Setup();
         _scene.Region.BackgroundColor.Set(0, 0, 0, 1);
         _scene.Region.BackgroundColor.Set(0.5f, 0.5f, 0.5f, 1.0f);
         _scene.Region.Camera.Viewport.width = pnlSpace.Width;
         _scene.Region.Camera.Viewport.height = pnlSpace.Height;
         _scene.Region.Camera.Viewport.Update();
         _scene.Region.Camera.Position.Set(0, 200, -500);
         _scene.Region.Camera.LookAt(0, 200, 0);
         _scene.Region.LightDirectional.Position.Set(1000, 1000, 0);
         _scene.Region.LightDirectional.Camera.Position.Set(1000, 1000, 0);
         _scene.Region.LightDirectional.Camera.LookAt(0, 200, 0);
         // 加载模型
         if (!RString.IsEmpty(_templateName)) {
            LoadTemplate(_templateName);
         }
         // 加载场景
         // qdsDeviceProperty.LoadDevice(_device);
         // qdsSceneTree.LoadScene(_scene);
         // 开始时钟
         timRefresh.Enabled = true;
         CenterToScreen();
      }

      //============================================================
      private void QDsTemplateEditorForm_FormClosing(object sender, FormClosingEventArgs e) {
         if (!_autoClose) {
            e.Cancel = true;
            Hide();
         }
      }

      //============================================================
      private void QDsTemplateEditorForm_FormClosed(object sender, FormClosedEventArgs e) {
         timRefresh.Enabled = false;
      }

      //============================================================
      private void QDsTemplateEditorForm_KeyDown(object sender, KeyEventArgs e) {
         // 显示和隐藏操作界面
         if (e.KeyCode == Keys.Space) {
            tsbCatalog_Click(sender, e);
            tsbProperty_Click(sender, e);
         }
         // 按键按下操作
         RKeybord.ProcessDown((int)e.KeyCode);
         // 场景处理
         _scene.ProcessKeyDown((int)e.KeyCode);
      }

      //============================================================
      private void QDsTemplateEditorForm_KeyUp(object sender, KeyEventArgs e) {
         // 按键抬起操作
         RKeybord.ProcessUp((int)e.KeyCode);
         // 场景处理
         _scene.ProcessKeyUp((int)e.KeyCode);
      }

      //============================================================
      private void pnlViewport_MouseDown(object sender, MouseEventArgs e) {
         _scene.ProcessMouseDown(ConvertMouseButton(e.Button), e.X, e.Y);
         if(e.Button == MouseButtons.Right) {
            Point point = pnlViewport.PointToScreen(new Point(e.X, e.Y));
            cmsScene.Show(point);
         }
      }

      //============================================================
      private void pnlViewport_MouseMove(object sender, MouseEventArgs e) {
         _scene.ProcessMouseMove(ConvertMouseButton(e.Button), e.X, e.Y);
      }

      //============================================================
      private void pnlViewport_MouseUp(object sender, MouseEventArgs e) {
         _scene.ProcessMouseUp(ConvertMouseButton(e.Button), e.X, e.Y);
      }

      //============================================================
      private void pnlViewport_Resize(object sender, System.EventArgs e)
      {
         _scene.Region.Camera.Viewport.width = pnlViewport.Width;
         _scene.Region.Camera.Viewport.height = pnlViewport.Height;
         _scene.Region.Camera.Viewport.Update();
         _device.ConfigureBackBuffer(pnlViewport.Width, pnlViewport.Height);
      }

      //============================================================
      private void ProcessInput() {
         if (RKeybord.IsPressedKey(EKeyCode.Left)) {
            _spatial.DoRotationY(0.1f);
         } else if (RKeybord.IsPressedKey(EKeyCode.Right)) {
            _spatial.DoRotationY(-0.1f);
         }
      }

      //============================================================
      private void timRefresh_Tick(object sender, System.EventArgs e) {
         // 停止计时
         timRefresh.Enabled = false;
         // 处理属于
         ProcessInput();
         // 绘制画面
         _scene.Process();
         _device.Present();
         // 开始计时
         timRefresh.Enabled = true;
      }

      //============================================================
      private void tsbClose_Click(object sender, System.EventArgs e) {
         Close();
      }

      //============================================================
      private void tsbCatalog_Click(object sender, System.EventArgs e) {
         tsbCatalog.Checked = !tsbCatalog.Checked;
         splCatalog.Visible = tsbCatalog.Checked;
         pnlCatalog.Visible = tsbCatalog.Checked;
      }

      //============================================================
      private void tsbProperty_Click(object sender, System.EventArgs e) {
         tsbProperty.Checked = !tsbProperty.Checked;
         splProperty.Visible = tsbProperty.Checked;
         pnlProperty.Visible = tsbProperty.Checked;
      }

      //============================================================
      private void tsbWireFrame_Click(object sender, System.EventArgs e) {
         tsbWireFrame.Checked = !tsbWireFrame.Checked;
         _device.ModeWireFrame = tsbWireFrame.Checked;
      }

      //============================================================
      private int ConvertMouseButton(MouseButtons button) {
         switch (button) {
            case MouseButtons.Left:
               return EMouseButton.Left;
            case MouseButtons.Middle:
               return EMouseButton.Middle;
            case MouseButtons.Right:
               return EMouseButton.Right;
            case MouseButtons.XButton1:
               return EMouseButton.XButton1;
            case MouseButtons.XButton2:
               return EMouseButton.XButton2;
         }
         return EMouseButton.None;
      }

      //============================================================
      private void tsmiModeSelect_Click(object sender, System.EventArgs e) {
         _scene.ModeCd = EDxDesignMode.Select;
         tsbDropMode.Text = tsmiModeSelect.Text;
      }

      //============================================================
      private void tsmiModeTranslation_Click(object sender, System.EventArgs e) {
         _scene.ModeCd = EDxDesignMode.Translation;
         tsbDropMode.Text = tsmiModeTranslation.Text;
      }

      //============================================================
      private void tsmiModeRotation_Click(object sender, System.EventArgs e) {
         _scene.ModeCd = EDxDesignMode.Rotation;
         tsbDropMode.Text = tsmiModeRotation.Text;
      }

      //============================================================
      private void tsmiModeScale_Click(object sender, System.EventArgs e) {
         _scene.ModeCd = EDxDesignMode.Scale;
         tsbDropMode.Text = tsmiModeScale.Text;
      }
   }
}
