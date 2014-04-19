using System;
using System.Windows.Forms;

namespace MO.Design3d.Material.Forms
{
   //============================================================
   // <T>材质设计表单。</T>
   //============================================================
   public partial class QDsMaterialDesignForm : Form
   {
      // 静态实例
      protected static QDsMaterialDesignForm _instance;

      //============================================================
      // <T>获得静态实例。</T>
      //============================================================
      public static QDsMaterialDesignForm Instance {
         get {
            if (_instance == null) {
               _instance = new QDsMaterialDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造材质设计表单。</T>
      //============================================================
      public QDsMaterialDesignForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开表单信息。</T>
      //============================================================
      public void Open() {
         qdsMaterialDesign.Open();
      }

      //============================================================
      // <T>关闭处理。</T>
      //
      // @param sender 发送者
      // @param e 事件
      //============================================================
      private void tsbClose_Click(object sender, EventArgs e) {
         Close();
      }
   }
}
