using System.Windows.Forms;
using MoMobile.Map.Common;

namespace MoMobile.Map.Forms
{
   //============================================================
   // <T>地图属性控件。</T>
   //============================================================
   public partial class QMapProperty : UserControl
   {
      //============================================================
      public QMapProperty() {
         InitializeComponent();
      }

      //============================================================
      public void LoadResource(FMbMap map) {
         txtId.Text = map.Tid.ToString();
         txtLabel.Text = map.Label;
         txtName.Text = map.Name;
      }
   }
}
