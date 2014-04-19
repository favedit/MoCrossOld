using System;
using System.Windows.Forms;

namespace MO.Core.Forms.Dialog
{
   //============================================================
   public partial class QExceptionDialogForm : Form
   {
      protected object _throwOwner;

      protected Exception _throwException;

      protected object[] _throwValues;

      //============================================================
      public QExceptionDialogForm() {
         InitializeComponent();
      }

      //============================================================
      public object ThrowOwner {
         get { return _throwOwner; }
         set { _throwOwner = value; }
      }

      //============================================================
      public Exception ThrowException {
         get { return _throwException; }
         set { _throwException = value; }
      }

      //============================================================
      public object[] ThrowValues {
         get { return _throwValues; }
         set { _throwValues = value; }
      }

      //============================================================
      private void QDesignErrorForm_Load(object sender, EventArgs e) {
         if (null != _throwException) {
            pgdOwner.SelectedObject = _throwOwner;
            txtMessage.Text = _throwException.Message;
            rtbDetail.Text = _throwException.StackTrace;
            // 拥有者
            if (null != _throwOwner) {
               ListViewItem qItem = new ListViewItem();
               qItem.Text = "Owner";
               qItem.SubItems.Add(_throwOwner.GetType().FullName);
               qItem.SubItems.Add("[" + _throwOwner + "]");
               qItem.Tag = _throwOwner;
               lsvValues.Items.Add(qItem);
               qItem.Selected = true;
            }
            // 内容列表
            if (null != _throwValues) {
               foreach (object value in _throwValues) {
                  ListViewItem qItem = new ListViewItem();
                  qItem.Text = "Value";
                  if (null != value) {
                     qItem.SubItems.Add(value.GetType().FullName);
                  } else {
                     qItem.SubItems.Add("[null]");
                  }
                  qItem.SubItems.Add("[" + value + "]");
                  qItem.Tag = qItem;
                  lsvValues.Items.Add(qItem);
               }
            }
         }
      }

      //============================================================
      private void btnClose_Click(object sender, EventArgs e) {
         Close();
      }

      //============================================================
      private void lsvValues_Click(object sender, EventArgs e) {
         if (1 == lsvValues.SelectedItems.Count) {
            ListViewItem qItem = lsvValues.SelectedItems[0];
            pgdOwner.SelectedObject = qItem.Tag;
         }
      }
   }
}
