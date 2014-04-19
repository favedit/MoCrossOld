using MO.Common.Collection;
using MO.Content2d.Resource.Common;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   //============================================================
   // <T>资源组属性。</T>
   //============================================================
   public partial class QDsResourceGroupProperty : UserControl
   {
      // 资源组
      protected FRsResourceGroup _resourceGroup;

      //============================================================
      // <T>构造资源组属性。</T>
      //============================================================
      public QDsResourceGroupProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>加载信息。</T>
      //
      // @param group 数据对象
      //============================================================
      public void LoadResource(FRsResourceGroup group) {
         _resourceGroup = group;
         // 开始更新
         lvwResources.BeginUpdate();
         // 清除处理
         lvwResources.Items.Clear();
         //foreach (INamePair<FDsResource> pair in group.ResourceDictionary) {
         //   FDsResource resource = pair.Value;
         //   ListViewItem lviResource = new ListViewItem(resource.Label);
         //   if (resource.IsValid) {
         //      lviResource.ForeColor = Color.Black;
         //      lviResource.ImageKey = resource.TypeName;
         //   } else {
         //      lviResource.ForeColor = Color.Gray;
         //      lviResource.ImageKey = resource.TypeName + "D";
         //   }
         //   lviResource.SubItems.Add(resource.Directory);
         //   lviResource.Tag = resource;
         //   lvwResources.Items.Add(lviResource);
         //}
         // 结束更新
         lvwResources.EndUpdate();
      }
   }
}
