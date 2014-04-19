using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>复选框控件。</T>
   //============================================================
   public partial class QCheckBox : CheckBox
   {
      //============================================================
      // <T>构造复选框控件。</T>
      //============================================================
      public QCheckBox() {
         InitializeComponent();
         FlatStyle = FlatStyle.Flat;
      }
   }
}
