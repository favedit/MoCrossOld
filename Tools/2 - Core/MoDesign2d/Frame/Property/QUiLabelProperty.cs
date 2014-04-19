using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Controls;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{   
   //============================================================
   // <T>标签属性编辑器</T>
   //============================================================
   public partial class QUiLabelProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcLabel _resource;

      //============================================================
      // <T>构造标签属性编辑器</T>
      //============================================================
      public QUiLabelProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>配置处理。</T>
      //============================================================
      public void Setup() {
      }

      //============================================================
      // <T>获得分组名称。</T>
      //
      // @return 分组名称
      //============================================================
      public string GroupLabel() {
         return "标签控件";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcLabel;
         if (_resource == null) {
            return;
         }
         // 加载属性
         txtText.Text = _resource.Text;
         quiFont.LoadResource(_resource.Font);
         //quiGroundResource.LoadResource(_resource.GroundResource);
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
         // 检查参数
         if (_resource == null) {
            return;
         }
         // 保存属性
         _resource.Text = txtText.Text;
         quiFont.SaveResource();
         //quiGroundResource.SaveResource();
      }

      //============================================================
      // <T>文本变更事件。</T>
      //============================================================
      private void txtText_TextChanged(object sender, System.EventArgs e) {
         _resource.Text = txtText.Text;
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "text");
         }
      }
   }
}
