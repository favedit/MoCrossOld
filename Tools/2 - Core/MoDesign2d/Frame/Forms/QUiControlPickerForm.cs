using System;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>控件选择器。</T>
   //============================================================
   public partial class QUiControlPickerForm : Form
   {
      // 唯一实例
      protected static QUiControlPickerForm _instance;

      //============================================================
      // <T>获得唯一实例。</T>
      //============================================================
      public static QUiControlPickerForm Instance {
         get {
            if(_instance == null) {
               _instance = new QUiControlPickerForm();
            }
            return _instance;
         }
      }

      // 选中控件名称
      protected string _selectControlName;

      //============================================================
      // <T>构造控件选择器。</T>
      //============================================================
      public QUiControlPickerForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得选中控件名称。</T>
      //============================================================
      public string SelectControlName {
         get { return _selectControlName; }
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      private void QUiControlPicker_FormClosing(object sender, FormClosingEventArgs e) {
         Hide();
      }

      //============================================================
      // <T>双击处理。</T>
      //============================================================
      private void lvwControls_DoubleClick(object sender, EventArgs e) {
         if(lvwControls.SelectedItems.Count == 1) {
            DialogResult = DialogResult.OK;
            _selectControlName = (string)lvwControls.SelectedItems[0].Tag;
         } else {
            DialogResult = DialogResult.Cancel;
         }
         Close();
      }

      //============================================================
      // <T>选中处理。</T>
      //============================================================
      private void btnAccept_Click(object sender, EventArgs e) {
         DialogResult = DialogResult.Cancel;
         Close();
      }

      //============================================================
      // <T>取消处理。</T>
      //============================================================
      private void btnCancel_Click(object sender, EventArgs e) {
         DialogResult = DialogResult.Cancel;
         Close();
      }
   }
}
