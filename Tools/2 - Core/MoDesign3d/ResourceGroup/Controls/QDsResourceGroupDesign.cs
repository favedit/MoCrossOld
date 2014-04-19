using MO.Common.Collection;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using MO.Content3d;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design3d.ResourceGroup.Controls
{
   //============================================================
   // <T>资源组设计器。</T>
   //============================================================
   public partial class QDsResourceGroupDesign : UserControl
   {
      // 存储资源组值
      protected string _filterValue = string.Empty;

      // 存储资源值
      protected string _filterTag = string.Empty;

      //============================================================
      // <T>构造资源组设计器。</T>
      //============================================================
      public QDsResourceGroupDesign() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         //FDictionary<TreeNode> folderNodes = new FDictionary<TreeNode>();
         //// 打开资源目录
         //tvwCatalog.BeginUpdate();
         //foreach (FDrResourceGroup resourceGroup in RContent3dManager.ResourceGroupConsole.ResourceGroups.Values) {
         //   // 建立目录节点
         //   TreeNode groupNode = new TreeNode(resourceGroup.Label);
         //   groupNode.ImageKey = "FloderClose";
         //   groupNode.SelectedImageKey = "FloderOpen";
         //   groupNode.Tag = resourceGroup;
         //   tvwCatalog.Nodes.Add(groupNode);
         //   // 建立资源节点
         //   foreach (FDrResource resource in resourceGroup.Resources) {
         //      TreeNode resourceNode = new TreeNode();
         //      resourceNode.Text = resource.Code + " [" + resource.Label + "]";
         //      if (resource.OptionValid) {
         //         resourceNode.ForeColor = Color.Black;
         //         resourceNode.ImageKey = resource.TypeName;
         //         resourceNode.SelectedImageKey = resource.TypeName;
         //      } else {
         //         resourceNode.ForeColor = Color.Gray;
         //         resourceNode.ImageKey = resource.TypeName + "D";
         //         resourceNode.SelectedImageKey = resource.TypeName + "D";
         //      }
         //      resourceNode.Tag = resource;
         //      groupNode.Nodes.Add(resourceNode);
         //   }
         //}
         //tvwCatalog.EndUpdate();
         //// 选择资源
         //SelectObject(null); 
      }

      //============================================================
      // <T>选中对象。</T>
      //
      // @param value 对象
      //============================================================
      protected void SelectObject(object value) {
         pnlInfo.SuspendLayout();
         // 全部隐藏
         qdsPicture.Visible = false;
         // 对象处理
         if (value is FRsResource) {
            FRsResource resource = value as FRsResource;
            resource.Open();
            // 资源处理
            switch (resource.TypeCd) {
               case ERsResource.Picture:
                  labNameDisplay.Text = "图片：" + resource.Label;
                  qdsPicture.Dock = DockStyle.Fill;
                  //qdsPicture.LoadResource(resource);
                  qdsPicture.Visible = true;
                  break;
               case ERsResource.Animation:
                  labNameDisplay.Text = "动画：" + resource.Label;
                  //qdsAnimation.Dock = DockStyle.Fill;
                  //qdsAnimation.LoadResource(resource);
                  //qdsAnimation.Visible = true;
                  break;
               case ERsResource.Sound:
                  labNameDisplay.Text = "音效：" + resource.Label;
                  //qucSound.Dock = DockStyle.Fill;
                  //qucSound.Visible = true;
                  break;
               case ERsResource.Music:
                  labNameDisplay.Text = "音乐：" + resource.Label;
                  //qucMusic.Dock = DockStyle.Fill;
                  //qucMusic.Visible = true;
                  break;
               case ERsResource.Swf:
                  labNameDisplay.Text = "特效：" + resource.Label;
                  //qucSwf.LoadResource(_resource);
                  //qucSwf.Dock = DockStyle.Fill;
                  //qucSwf.Visible = true;
                  break;
            }
         }
         pnlInfo.ResumeLayout();
      }

      //============================================================
      private void qDsGroupCatalog_CatalogAfterSelect(object sender, TreeViewEventArgs e) {
         TreeNode node = e.Node;
         if (null != node) {
            SelectObject(node.Tag);
         }
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void ExportSelected() {
         //FObjects<FRsResource> resources = qDsGroupCatalog.SeletedResource();
         //foreach(FRsResource resource in resources) {
         //   FRsResourceExportTask task = new FRsResourceExportTask();
         //   task.Console = RDsResource.ResourceConsole;
         //   task.Exporter = resource;
         //   RMoCore.TaskConsole.Push(task);
         //}
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void SaveSelected() {
         //FObjects<FRsResource> resources = qDsGroupCatalog.SeletedResource();
         //foreach(FRsResource resource in resources) {
         //   resource.Store();
         //}
         //MessageBox.Show("保存成功！");
      }

      //============================================================
      // <T>搜索资源事件信息。</T>
      //
      // @param sender 数据对象
      // @param e      事件产生者
      // @author TYFNG 20120412
      //============================================================
      private void cbxResource_KeyUp(object sender, KeyEventArgs e) {
         //// 得到对象
         //TreeView treeView = qdsGroupCatalog as TreeView;
         //string value = cbxResource.Text;
         //if(_filterTag != value) {
         //   treeView.BeginUpdate();
         //   foreach(TreeNode item in treeView.Nodes) {
         //      item.Nodes.Clear();
         //   }
         //   treeView.Nodes.Clear();
         //   // 资源过滤               
         //   //foreach(INamePair<FRsResourceGroup> pair in RDsResource.ResourceConsole.Group2dDictionary) {
         //   //   FRsResourceGroup group = pair.Value;
         //   //   SDsResourceTag tag = group.Tag as SDsResourceTag;
         //   //   foreach(INamePair<FRsResource> resPair in group.ResourceDictionary) {
         //   //      FRsResource resource = resPair.Value;
         //   //      SDsResourceTag restag = resource.Tag as SDsResourceTag;
         //   //      if(-1 != resource.Keyword.IndexOf(value)) {
         //   //         treeView.Nodes.Add(restag.node);
         //   //      }
         //   //   }
         //   //}
         //   _filterTag = value;
         //   treeView.EndUpdate();
         //}
      }

      //============================================================
      // <T>搜索资源组事件信息。</T>
      //
      // @param sender 数据对象
      // @param e      事件产生者
      // @author TYFNG 20120412
      //============================================================
      private void cbxGroupSearch_KeyUp(object sender, KeyEventArgs e) {
         //// 得到对象
         //TreeView treeView = qDsGroupCatalog as TreeView;
         //string value = cbxGroupSearch.Text;
         //if(_filterValue != value) {
         //   treeView.BeginUpdate();
         //   foreach(TreeNode item in treeView.Nodes) {
         //      item.Nodes.Clear();
         //   }
         //   treeView.Nodes.Clear();
         //   // 资源过滤               
         //   //foreach(INamePair<FRsResourceGroup> pair in RDsResource.ResourceConsole.Group2dDictionary) {
         //   //   FRsResourceGroup group = pair.Value;
         //   //   int i = group.Id.ToString().IndexOf(value);
         //   //   int n = group.Label.IndexOf(value);
         //   //   if(-1 != i || -1 != n) {
         //   //      SDsResourceTag tag = group.Tag as SDsResourceTag;
         //   //      TreeNode node = tag.node;
         //   //      foreach(INamePair<FRsResource> resPair in group.ResourceDictionary) {
         //   //         SDsResourceTag resTag = resPair.Value.Tag as SDsResourceTag;
         //   //         node.Nodes.Add(resTag.node);
         //   //      }
         //   //      treeView.Nodes.Add(node);
         //   //   }
         //   //}
         //   _filterValue = value;
         //   treeView.EndUpdate();
         //}
      }
   }
}
