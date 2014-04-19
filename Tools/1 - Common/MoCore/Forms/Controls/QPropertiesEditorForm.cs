using System.Windows.Forms;
using MO.Common.Collection;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>属性集合编辑表单。</T>
   //============================================================
   public partial class QPropertiesEditorForm : Form
   {
      // 属性集合
      protected FProperties _properties;

      //============================================================
      // <T>构造属性集合编辑表单。</T>
      //============================================================
      public QPropertiesEditorForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得或设置属性集合。</T>
      //============================================================
      public FProperties Properties {
         get { return _properties; }
         set {
            if(value != null) {
               _properties = value;
               txtInfo.Text = value.FormatDisplay();
            }
         }
      }

      //============================================================
      // <T>获得或设置属性集合。</T>
      //============================================================
      private void tsbSave_Click(object sender, System.EventArgs e) {
         DialogResult = DialogResult.OK;
         _properties.ParseDisplay(txtInfo.Text);
         Close();
      }

      //============================================================
      // <T>获得或设置属性集合。</T>
      //============================================================
      private void tsbExit_Click(object sender, System.EventArgs e) {
         DialogResult = DialogResult.Cancel;
         Close();
      }
   }
}
