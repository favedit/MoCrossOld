using MO.Content2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>对象属性编辑器</T>
   //============================================================
   public partial class QUiObjectProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcObject _resource;

      //============================================================
      // <T>构造对象属性编辑器</T>
      //============================================================
      public QUiObjectProperty() {
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
         return "对象属性";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource;
         if (_resource == null) {
            return;
         }
         // 加载属性
         chkOptionValid.Checked = resource.OptionValid;
         txtTypeName.Text = resource.TypeName;
         txtClassName.Text = resource.ClassName;
         txtName.Text = resource.Name;
         txtLabel.Text = resource.Label;
         txtHint.Text = resource.Hint;
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
         _resource.OptionValid = chkOptionValid.Checked;
         _resource.ClassName = txtClassName.Text;
         _resource.Name = txtName.Text;
         _resource.Label = txtLabel.Text;
         _resource.Hint = txtHint.Text;
      }

      //============================================================
      // <T>名称变更事件。</T>
      //============================================================
      private void txtName_TextChanged(object sender, System.EventArgs e) {
         _resource.Name = txtName.Text;
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "name");
         }
      }

      //============================================================
      // <T>标签变更事件。</T>
      //============================================================
      private void txtLabel_TextChanged(object sender, System.EventArgs e) {
         _resource.Label = txtLabel.Text;
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "label");
         }
      }
   }
}
