using System.Windows.Forms;

namespace MO.Design2d.Resource.Forms
{
   //============================================================
   // <T>资源表单。</T>
   //============================================================
   public partial class QDsResourceForm : Form
   {
      // 资源表单
      protected static QDsResourceForm _instance;

      //============================================================
      // <T>获得资源表单单件。</T>
      //============================================================
      public static QDsResourceForm Instance {
         get{
            if(null == _instance) {
               _instance = new QDsResourceForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造资源表单。</T>
      //============================================================
      public QDsResourceForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
      }

   }
}
