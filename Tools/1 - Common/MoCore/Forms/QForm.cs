using System.ComponentModel;
using System.Windows.Forms;

namespace MO.Core.Forms
{

   public partial class QForm : Form {

      protected const string CFG_NAME = "Form";

      protected const string CFG_CONTROL = "Control";

      protected const string CFG_BOUNDS = "Bounds";

      protected const string PTY_NAME = "name";

      //============================================================
      public QForm() {
         InitializeComponent();
      }

      //============================================================
      public QForm(IContainer container) {
         container.Add(this);
         InitializeComponent();
      }

      ////============================================================
      //protected virtual FControlConfig GetRuntimeConfig() {
      //   return RFmApplication.RunConfig.Sync(CFG_NAME, PTY_NAME, Name);
      //}

      ////============================================================
      //protected override void OnLoad(EventArgs e) {
      //   FControlConfig config = GetRuntimeConfig();
      //   SIntRectangle rect = config.GetRect(CFG_BOUNDS);
      //   if (rect != null) {
      //      SetBounds(rect.Left, rect.Top, rect.Width, rect.Height);
      //   }
      //   ControlsLoadConfig(config, Controls);
      //   base.OnLoad(e);
      //}

      ////============================================================
      //protected override void OnClosed(EventArgs e) {
      //   FControlConfig config = GetRuntimeConfig();
      //   ControlsSaveConfig(config, Controls);
      //   if (WindowState == FormWindowState.Normal) {
      //      config.SetRect(CFG_BOUNDS, new SIntRectangle(Left, Top, Width, Height));
      //   }
      //   base.OnClosed(e);
      //}

      ////============================================================
      //protected virtual void ControlsLoadConfig(FControlConfig config, Control.ControlCollection controls) {
      //   //if (controls != null) {
      //   //   foreach (Control control in controls) {
      //   //      if(control is IControlConfig && !RString.IsEmpty(control.Name)) {
      //   //         IControlConfig configable = (IControlConfig)control;
      //   //         FControlConfig ctlCfg = new FControlConfig(config.Config.Sync(CFG_CONTROL, PTY_NAME, control.Name));
      //   //         configable.ControlLoadConfig(ctlCfg);
      //   //      }
      //   //      if (control.HasChildren) {
      //   //         ControlsLoadConfig(config, control.Controls);
      //   //      }
      //   //   }
      //   //}
      //}

      ////============================================================
      //protected virtual void ControlsSaveConfig(FControlConfig config, Control.ControlCollection controls) {
      //   //if (controls != null) {
      //   //   foreach (Control control in controls) {
      //   //      if(control is IControlConfig && !RString.IsEmpty(control.Name)) {
      //   //         IControlConfig configable = (IControlConfig)control;
      //   //         FControlConfig ctlCfg = new FControlConfig(config.Config.Sync(CFG_CONTROL, PTY_NAME, control.Name));
      //   //         configable.ControlSaveConfig(ctlCfg);
      //   //      }
      //   //      if (control.HasChildren) {
      //   //         ControlsSaveConfig(config, control.Controls);
      //   //      }
      //   //   }
      //   //}
      //}
   }
}
