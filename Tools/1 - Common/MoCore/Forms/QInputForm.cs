using System;
using System.Windows.Forms;
using MO.Common.Lang;

namespace MO.Core.Forms
{
   //============================================================
   // <T>输入表单。</T>
   //============================================================
   public partial class QInputForm : Form
   {
      //============================================================
      protected static QInputForm _instance;

      //============================================================
      protected static QInputForm Instance {
         get {
            if(null == _instance) {
               _instance = new QInputForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造输入表单。</T>
      //============================================================
      public QInputForm() {
         InitializeComponent();
      }

      //============================================================
      private void DoOk() {
         string value = txtInput.Text;
         if(!RString.IsBlank(value)) {
            DialogResult = DialogResult.OK;
         }
      }

      //============================================================
      private void DoCancel() {
         txtInput.Text = "";
         DialogResult = DialogResult.Cancel;
      }

      //============================================================
      private void QInputForm_KeyDown(object sender, KeyEventArgs e) {
         if(Keys.Enter == e.KeyCode) {
            DoOk();
         } else if(Keys.Escape == e.KeyCode) {
            DoCancel();
         }
      }

      //============================================================
      private void btnOk_Click(object sender, EventArgs e) {
         DoOk();
      }

      //============================================================
      private void btnCancel_Click(object sender, EventArgs e) {
         DoCancel();
      }

      //============================================================
      public static string Popup(string title) {
         return Popup(title, "");
      }

      //============================================================
      // <T>探出表单处理。</T>
      //
      // @param title 标题
      // @param input 输入内容
      // @return 输出内容
      //============================================================
      public static string Popup(string title, string input) {
         QInputForm form = Instance;
         form.labTitle.Text = title;
         form.txtInput.Text = input;
         if(DialogResult.OK == form.ShowDialog()) {
            return form.txtInput.Text;
         }
         return null;
      }
   }
}
