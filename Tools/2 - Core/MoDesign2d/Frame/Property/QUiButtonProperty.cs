using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Controls;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>按键属性编辑器</T>
   //============================================================
   public partial class QUiButtonProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcButton _resource;

      //============================================================
      // <T>构造按键属性编辑器</T>
      //============================================================
      public QUiButtonProperty() {
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
         return "按键控件：点击内容触发点击事件。";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcButton;
         if (_resource == null) {
            return;
         }
         // 加载属性
         txtText.Text = _resource.Text;
         chkOptionHand.Checked = _resource.OptionHand;
         quiFont.LoadResource(_resource.Font);
         quiGroundResource.LoadResource(_resource.GroundResource);
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
         _resource.OptionHand = chkOptionHand.Checked;
         quiFont.SaveResource();
         quiGroundResource.SaveResource();
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
