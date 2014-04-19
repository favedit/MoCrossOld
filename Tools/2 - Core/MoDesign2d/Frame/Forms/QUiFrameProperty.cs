using MO.Content2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>顶层容器属性。</T>
   //============================================================
   public partial class QUiFrameProperty : UserControl
   {
      //============================================================
      // <T>构造顶层容器属性。</T>
      //============================================================
      public QUiFrameProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载资源内容。</T>
      //============================================================
      public void LoadResource(FRcFrame frame) {
         txtCode.Text = frame.Code.ToString();
         txtName.Text = frame.Name;
         txtLabel.Text = frame.Label;
      }

      //============================================================
      public void SaveResource() {
      }
   }
}
