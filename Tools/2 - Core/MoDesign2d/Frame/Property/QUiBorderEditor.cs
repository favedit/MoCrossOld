using MO.Content2d.Frame.Common;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>边框属性编辑器</T>
   //============================================================
   public partial class QUiBorderEditor : UserControl
   {
      // 资源对象
      protected FRcBorder _resource;

      //============================================================
      // <T>构造边框属性编辑器</T>
      //============================================================
      public QUiBorderEditor() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得分组名称。</T>
      //
      // @return 分组名称
      //============================================================
      public string GroupLabel() {
         return "边框控件：点击内容触发点击事件。";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcBorder resource) {
         _resource = resource;
         // 设置属性
         txtBorder.Text = resource.ToString();
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
      }

      //============================================================
      // <T>点击按钮事件处理。</T>
      //============================================================
      private void btnBorder_Click(object sender, EventArgs e) {
         QUiBorderPropertyForm.Instance.Border = _resource;
         QUiBorderPropertyForm.Instance.LoadBorder();
         QUiBorderPropertyForm.Instance.Show();
         _resource = QUiBorderPropertyForm.Instance.Border;
      }
   }
}
