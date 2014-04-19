using System.Windows.Forms;

namespace MO.Design3d.Model.Forms
{
   //============================================================
   // <T>模型设计表单。</T>
   //============================================================
   public partial class QDsModelDesignForm : Form
   {
      // 静态实例
      protected static QDsModelDesignForm _instance;

      //============================================================
      // <T>获得静态实例。</T>
      //============================================================
      public static QDsModelDesignForm Instance {
         get {
            if (_instance == null) {
               _instance = new QDsModelDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造模型设计表单。</T>
      //============================================================
      public QDsModelDesignForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开表单信息。</T>
      //============================================================
      public void Open() {
         qdsModelDesign.Open();
      }

      //============================================================
      // <T>关闭处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbClose_Click(object sender, System.EventArgs e) {
         Close();
      }
   }
}
