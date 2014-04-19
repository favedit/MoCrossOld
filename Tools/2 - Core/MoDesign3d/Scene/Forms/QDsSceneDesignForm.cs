using System.Windows.Forms;

namespace MO.Design3d.Scene.Forms
{
   //============================================================
   // <T>场景设计表单。</T>
   //============================================================
   public partial class QDsSceneDesignForm : Form
   {
      // 静态实例
      protected static QDsSceneDesignForm _instance;

      //============================================================
      // <T>获得静态实例。</T>
      //============================================================
      public static QDsSceneDesignForm Instance {
         get {
            if (_instance == null) {
               _instance = new QDsSceneDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>场景纹理设计表单。</T>
      //============================================================
      public QDsSceneDesignForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开表单信息。</T>
      //============================================================
      public void Open() {
         qdsSceneDesign.Open();
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
