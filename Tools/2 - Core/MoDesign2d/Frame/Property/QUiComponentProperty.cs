using MO.Content2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>组件属性编辑器</T>
   //============================================================
   public partial class QUiComponentProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcComponent _resource;

      //============================================================
      // <T>构造组件属性编辑器</T>
      //============================================================
      public QUiComponentProperty() {
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
         return "组件属性";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcComponent;
         if (_resource == null) {
            return;
         }
         // 加载属性
         chkOptionLink.Checked = _resource.OptionLink;
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
         _resource.OptionLink = chkOptionLink.Checked;
      }

      //============================================================
      // <T>关联变更事件。</T>
      //============================================================
      private void chkOptionLink_CheckedChanged(object sender, System.EventArgs e) {
         _resource.OptionLink = chkOptionLink.Checked;
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "link");
         }
      }
   }
}
