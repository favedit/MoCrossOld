using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Container;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
      //============================================================
      // <T>分页控件属性编辑器。</T>
      //============================================================
   public partial class QUiPageControlProperty : UserControl, IUiPropertyEditor
   {
      // 属性改变事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcPageControl _resource;

      //============================================================
      // <T>构造分页控件属性编辑器。</T>
      //============================================================
      public QUiPageControlProperty() {
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
         return "分页控件";
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 检查参数
         _resource = resource as FRcPageControl;
         if (_resource == null) {
            return;
         }
         // 加载属性
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
      }
   }
}
