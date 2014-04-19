using System.Windows.Forms;

namespace MO.Design3d.Template.Forms
{
   //============================================================
   // <T>模板设计表单。</T>
   //============================================================
   public partial class QDsTemplateDesignForm : Form
   {
      // 静态实例
      protected static QDsTemplateDesignForm _instance;

      //============================================================
      // <T>获得静态实例。</T>
      //============================================================
      public static QDsTemplateDesignForm Instance {
         get {
            if (_instance == null) {
               _instance = new QDsTemplateDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造模板设计表单。</T>
      //============================================================
      public QDsTemplateDesignForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开表单信息。</T>
      //============================================================
      public void Open() {
         qdsTemplateDesign.Open();
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
