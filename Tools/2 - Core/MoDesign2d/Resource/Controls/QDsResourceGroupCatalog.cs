using MO.Common.Collection;
using MO.Common.Lang;
using MO.Content2d.Resource.Common;
using MO.Core.Forms.Controls;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   public partial class QDsResourceGroupCatalog : QTreeView
   {
      //============================================================
      // <T>构造资源树控件。</T>
      //============================================================
      public QDsResourceGroupCatalog() {
         InitializeComponent();
      }

      //============================================================
      // <T>建立资源目录。</T>
      //============================================================
      protected void BuildResourceNode(FRsResourceGroup group) {
         string label = "(" + group.Code + ")" + group.Label;
         TreeNode treeNode = new TreeNode(label);
         SDsResourceTag resourceTag = group.Tag as SDsResourceTag;
         resourceTag.node = treeNode;
         treeNode.Tag = group;
         Nodes.Add(treeNode);
         //foreach (INamePair<FRsResource> pair in group.ResourceDictionary) {
         //   FDsResource resource = pair.Value;
         //   TreeNode node = new TreeNode(resource.Label);
         //   if (resource.IsValid) {
         //      node.ForeColor = Color.Black;
         //      node.ImageKey = resource.TypeName;
         //      node.SelectedImageKey = resource.TypeName;
         //   } else {
         //      node.ForeColor = Color.Gray;
         //      node.ImageKey = resource.TypeName + "D";
         //      node.SelectedImageKey = resource.TypeName + "D";
         //   }
         //   node.Tag = resource;
         //   SDsResourceTag tag = resource.Tag as SDsResourceTag;
         //   tag.node = node;
         //   treeNode.Nodes.Add(node);
         //}
      }

      //============================================================
      // <T>打开资源集合。</T>
      //============================================================
      public void Open() {
         //foreach (INamePair<FRsResourceGroup> pair in RDsResource.ResourceConsole.GroupDictionary) {
         //   BuildResourceNode(pair.Value);
         //}
      }

      //============================================================
      // <T>选中改变状态事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      public FObjects<FRsResourceGroup> SeletedResource() {
         FObjects<FRsResourceGroup> groupList = new FObjects<FRsResourceGroup>();
         foreach (TreeNode node in Nodes) {
            FRsResourceGroup group = node.Tag as FRsResourceGroup;
            if (node.Checked) {
               groupList.Push(group);
            }
         }
         return groupList;
         //          FObjects<FDsResource> resources = new FObjects<FDsResource>();
         //          SelectedResource(Nodes, resources);
         //          return resources;
      }

      //============================================================
      // <T>选中改变状态事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tvwCatalog_AfterCheck(object sender, TreeViewEventArgs e) {
         TreeNode node = e.Node;
         SuspendLayout();
         SetStauts(node.Nodes, node.Checked);
         ResumeLayout();
      }

      //============================================================      
      public void SetStauts(TreeNodeCollection nodes, bool result) {
         foreach (TreeNode node in nodes) {
            node.Checked = result;
            SetStauts(node.Nodes, result);
         }
      }

      //============================================================      
      public void SelectedResource(TreeNodeCollection nodes, FObjects<FRsResource> resources) {
         foreach (TreeNode node in nodes) {
            if (node.Checked) {
               FRsResource resouce = node.Tag as FRsResource;
               if (resouce != null) {
                  resources.Push(resouce);
               }
            }
            //SelectedResource(node.Nodes, resources);
         }
      }
   }
}
