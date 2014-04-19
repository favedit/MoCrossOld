using MO.Content2d.Resource.Common;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   //============================================================
   // <T>资源控件。</T>
   //============================================================
   public partial class QDsResource : UserControl
   {
      // 资源对象
      protected FRsResource _resource;

      //============================================================
      // <T>构造资源控件。</T>
      //============================================================
      public QDsResource() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载资源信息。</T>
      //
      // @param resource 资源对象
      //============================================================
      public virtual void LoadResource(FRsResource resource) {
         _resource = resource;
      }

      //============================================================
      // <T>保存资源信息。</T>
      //
      // @param resource 资源对象
      //============================================================
      public virtual void SaveResource(FRsResource resource) {
      }
   }
}
