using MO.Content2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>属性控件。</T>
   //============================================================
   public partial class QUiProperty : UserControl
   {
      // 编辑器
      IUiPropertyEditor _editor;

      //============================================================
      // <T>构造属性控件。</T>
      //============================================================
      public QUiProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>关联控件。</T>
      //
      // @param control 控件
      //============================================================
      public void LinkPanel(UserControl control) {
         control.Dock = DockStyle.Fill;
         Height = Height - pnlBody.Height + control.Height;
         pnlBody.Controls.Add(control);
         _editor = control as IUiPropertyEditor;
         labTitle.Text = _editor.GroupLabel();
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         _editor.LoadResource(resource);
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
         _editor.SaveResource();
      }
   }
}
