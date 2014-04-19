using MO.Content3d.Common;
using System.ComponentModel;
using System.Windows.Forms;

namespace MO.Design3d.Controls
{
   //============================================================
   // <T>标志控件。</T>
   //============================================================
   public partial class QDsFlag : UserControl
   {
      // 标志
      protected int _dataValue = EDrFlag.Inherit;

      //============================================================
      // <T>构造标志控件。</T>
      //============================================================
      public QDsFlag() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得或设置标志。</T>
      //============================================================
      [DefaultValue(0)]
      public int DataValue {
         get { return _dataValue; }
         set {
            _dataValue = value;
            switch(_dataValue) {
               case EDrFlag.No:
                  rdbNo.Checked = true;
                  break;
               case EDrFlag.Yes:
                  rdbYes.Checked = true;
                  break;
               default:
                  rdbInherit.Checked = true;
                  break;
            }
         }
      }

      //============================================================
      // <T>按键变更状态事件。</T>
      //============================================================
      private void rdbFlag_CheckedChanged(object sender, System.EventArgs e) {
         if(rdbInherit.Checked) {
            _dataValue = EDrFlag.Inherit;
         } else if(rdbNo.Checked) {
            _dataValue = EDrFlag.No;
         } else if(rdbYes.Checked) {
            _dataValue = EDrFlag.Yes;
         }
      }
   }
}
