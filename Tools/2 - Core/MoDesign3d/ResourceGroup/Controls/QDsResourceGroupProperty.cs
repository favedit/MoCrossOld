using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MO.Content2d.Resource.Common;

namespace MO.Design3d.ResourceGroup.Controls
{
   public partial class QDsResourceGroupProperty : UserControl
   {
      protected FRsResourceGroup _resourceGroup;

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
         //foreach (INamePair<FRsResource> pair in group.ResourceDictionary) {
         //   FRsResource resource = pair.Value;
         //   ListViewItem lviResource = new ListViewItem(resource.Label);
         //   if (resource.IsValid) {
         //      lviResource.ForeColor = Color.Black;
         //      lviResource.ImageKey = resource.TypeName;
         //   } else {
         //      lviResource.ForeColor = Color.Gray;
         //      lviResource.ImageKey = resource.TypeName + "D";
         //      lviResource.SubItems.Add(resource.Directory);
         //   }
         //   lviResource.Tag = resource;
         //   lvwResources.Items.Add(lviResource);
         //}
      }
   }
}
