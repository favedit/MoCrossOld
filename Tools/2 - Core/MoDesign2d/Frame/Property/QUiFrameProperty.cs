using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>按键属性编辑器</T>
   //============================================================
   public partial class QUiFrameProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcFrame _resource;

      //============================================================
      // <T>构造按键属性编辑器</T>
      //============================================================
      public QUiFrameProperty() {
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
         return "顶层控件";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcFrame;
         if (_resource == null) {
            return;
         }
         // 加载属性
         txtCode.Text = _resource.Code.ToString();
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
         _resource.Code = RInt.Parse(txtCode.Text);
      }

      //============================================================
      // <T>文本变更事件。</T>
      //============================================================
      private void txtCode_TextChanged(object sender, System.EventArgs e) {
         _resource.Code = RInt.Parse(txtCode.Text);
         if (PropertyChanged != null) {
            PropertyChanged(this, _resource, "code");
         }
      }
   }
}
