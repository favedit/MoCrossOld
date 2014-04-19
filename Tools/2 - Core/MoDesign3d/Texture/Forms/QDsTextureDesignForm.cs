using System.Windows.Forms;

namespace MO.Design3d.Texture.Forms
{
   //============================================================
   // <T>纹理设计表单。</T>
   //============================================================
   public partial class QDsTextureDesignForm : Form
   {
      // 静态实例
      protected static QDsTextureDesignForm _instance;

      //============================================================
      // <T>获得静态实例。</T>
      //============================================================
      public static QDsTextureDesignForm Instance {
         get {
            if (_instance == null) {
               _instance = new QDsTextureDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造纹理设计表单。</T>
      //============================================================
      public QDsTextureDesignForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开表单信息。</T>
      //============================================================
      public void Open() {
         qdsTextureDesign.Open();
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
