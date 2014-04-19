using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Container;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>构造页面属性编辑器。</T>
   //============================================================
   public partial class QUiPageProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcPage _resource;

      //============================================================
      // <T>构造页面属性编辑器。</T>
      //============================================================
      public QUiPageProperty() {
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
         return "页面控件";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcPage;
         if (_resource == null) {
            return;
         }
         // 加载属性
         chkOptionDefault.Checked = _resource.OptionDefault;
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
         _resource.OptionDefault = chkOptionDefault.Checked;
      }

      //============================================================
      // <T>默认页变更事件。</T>
      //============================================================
      private void chkOptionDefault_CheckedChanged(object sender, System.EventArgs e) {
         _resource.OptionDefault = chkOptionDefault.Checked;
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "option_default");
         }
      }
   }
}
