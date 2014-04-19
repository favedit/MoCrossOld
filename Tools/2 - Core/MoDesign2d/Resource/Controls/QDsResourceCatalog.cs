using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using MO.Core.Forms.Controls;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   //============================================================
   // <T>资源树控件。</T>
   //============================================================
   public partial class QDsResourceCatalog : QTreeView
   {
      //============================================================
      // <T>构造资源树控件。</T>
      //============================================================
      public QDsResourceCatalog() {
         InitializeComponent();
      }

      //============================================================
      // <T>建立资源目录。</T>
      //============================================================
      protected void BuildResourceNode(FRsResourceFolder folder, TreeNodeCollection nodes) {
         // 建立所有子目录
         if(!folder.Folders.IsEmpty()) {
            foreach (FRsResourceFolder subfolder in folder.Folders) {
               TreeNode node = new TreeNode(subfolder.Label);
               node.Tag = subfolder;
               nodes.Add(node);
               // 递归处理子目录
               BuildResourceNode(subfolder, node.Nodes);
            }
         }
         // 建立所有子资源
         if(!folder.Resources.IsEmpty()) {
            foreach(FRsResource resource in folder.Resources) {
               string typeFullName = ERsResource.ToFullName(resource.TypeName);
               TreeNode node = new TreeNode(resource.Label);
               if(resource.OptionValid) {
                  node.ForeColor = Color.Black;
                  node.ImageKey = typeFullName;
                  node.SelectedImageKey = typeFullName;
               } else {
                  node.ForeColor = Color.Gray;
                  node.ImageKey = typeFullName + "D";
                  node.SelectedImageKey = typeFullName + "D";
               }
               node.Tag = resource;
               nodes.Add(node);
            }
         }
      }

      //============================================================
      // <T>打开资源集合。</T>
      //============================================================
      public void Open() {
         foreach (FRsResourceFolder folder in RContent2dManager.ResourceConsole.Folders) {
            BuildResourceNode(folder, Nodes);
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
            SelectedResource(node.Nodes, resources);
         }
      }

      //============================================================
      // <T>选中改变状态事件。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      public FObjects<FRsResource> SeletedResource() {
         FObjects<FRsResource> resources = new FObjects<FRsResource>();
         SelectedResource(Nodes, resources);
         return resources;
      }

      //============================================================
      public void SetStauts(TreeNodeCollection nodes, bool result) {
         foreach (TreeNode node in nodes) {
            node.Checked = result;
            SetStauts(node.Nodes, result);
         }
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
         SetStauts(node.Nodes,node.Checked);
         ResumeLayout();
      }

      //============================================================
      // 离开焦点时候显示选中的节点
      private void QDsResourceCatalog_Leave(object sender, System.EventArgs e){
         if(null != SelectedNode){
            SelectedNode.BackColor = Color.CadetBlue;
         }
      } 
   }
}
