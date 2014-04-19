using MO.Common.Collection;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using MO.Core;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   public partial class QDsResourceGroupDesign : UserControl
   {
      // 存储资源组值
      protected string _filterValue = string.Empty;

      // 存储资源值
      protected string _filterTag = string.Empty;

      public QDsResourceGroupDesign() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         // 初始化资源附加数据
         //foreach(INamePair<FRsResourceGroup> pair in RDsResource.ResourceConsole.GroupDictionary) {
         //   FRsResourceGroup group = pair.Value;
         //   group.Tag = new SDsResourceTag();
         //}
         //qDsGroupCatalog.Open();
         //SelectResource(null);
      }

      //============================================================
      protected void SelectResource(object value) {
         panInfo.SuspendLayout();
         // 全部隐藏
         qdsPicture.Visible = false;
         qdsAnimation.Visible = false;
         qdsGrpProperty.Visible = false;
         if(value is FRsResourceGroup) {
            FRsResourceGroup group = value as FRsResourceGroup;
            qdsGrpProperty.Dock = DockStyle.Fill;
            qdsGrpProperty.LoadResource(group);
            qdsGrpProperty.Visible = true;
         } else if (value is FRsResource) {
            FRsResource resource = value as FRsResource;
            resource.Open();
            // 资源处理
            switch(resource.TypeCd) {
               case ERsResource.Picture:
                  labNameDisplay.Text = "图片：" + resource.Label;
                  qdsPicture.Dock = DockStyle.Fill;
                  qdsPicture.LoadResource(resource);
                  qdsPicture.Visible = true;
                  break;
               case ERsResource.Animation:
                  labNameDisplay.Text = "动画：" + resource.Label;
                  qdsAnimation.Dock = DockStyle.Fill;
                  qdsAnimation.LoadResource(resource);
                  qdsAnimation.Visible = true;
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
         panInfo.ResumeLayout();
      }

      //============================================================
      private void qdsGrpProperty_Click(object sender, EventArgs e) {
      }

      //============================================================
      private void qDsGroupCatalog_CatalogAfterSelect(object sender, TreeViewEventArgs e) {
         TreeNode node = e.Node;
         if(null != node) {
            SelectResource(node.Tag);
         }
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void ExportSelected(ERsExportMode modeCd) {
         FObjects<FRsResourceGroup> resources = qDsGroupCatalog.SeletedResource();
         foreach (FRsResourceGroup resource in resources) {
            FRsExportTask task = new FRsExportTask();
            task.ModeCd = modeCd;
            task.Exporter = resource;
            RMoCore.TaskConsole.Push(task);
         }
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void SaveSelected() {
         //FObjects<FDsResource> resources = qDsGroupCatalog.SeletedResource();
         //foreach(FDsResource resource in resources) {
         //   resource.Store();
         //}
         //MessageBox.Show("保存成功！");
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
         // TreeView treeView = qDsGroupCatalog as TreeView;
         //string value = cbxGroupSearch.Text;
         // int count = value.Length;
         // if(_filterValue != value) {
         //   treeView.BeginUpdate();
         //   foreach(TreeNode item in treeView.Nodes) {
         //      item.Nodes.Clear();
         //   }
         //   treeView.Nodes.Clear();
         //   // 资源过滤               
         //   foreach(INamePair<FDsResourceGroup> pair in RDsResource.ResourceConsole.GroupDictionary) {
         //      FDsResourceGroup group = pair.Value;
         //      string codeStr = group.Code.ToString();
         //       int i = group.Code.ToString().IndexOf(value);
         //       int n = group.Label.IndexOf(value);
         //      if(-1 != i || -1 != n) {
         //         if(codeStr.Substring(0, count).Equals(value)){
         //         SDsResourceTag tag = group.Tag as SDsResourceTag;
         //         TreeNode node = tag.node;
         //         foreach(INamePair<FDsResource> resPair in group.ResourceDictionary) {
         //            SDsResourceTag resTag = resPair.Value.Tag as SDsResourceTag;
         //            node.Nodes.Add(resTag.node);
         //         }
         //         treeView.Nodes.Add(node);
         //      }
         //      }
         //   }
         //   _filterValue = value;
         //   treeView.EndUpdate();
         //}
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
         //TreeView treeView = qDsGroupCatalog as TreeView;
         //string value = cbxResource.Text;
         //if(_filterTag != value) {
         //   treeView.BeginUpdate();
         //   foreach(TreeNode item in treeView.Nodes) {
         //      item.Nodes.Clear();
         //   }
         //   treeView.Nodes.Clear();
         //   // 资源过滤               
         //   foreach(INamePair<FDsResourceGroup> pair in RDsResource.ResourceConsole.GroupDictionary) {
         //      FDsResourceGroup group = pair.Value;
         //      SDsResourceTag tag = group.Tag as SDsResourceTag;
         //      foreach(INamePair<FDsResource> resPair in group.ResourceDictionary) {
         //         FDsResource resource = resPair.Value;
         //         SDsResourceTag restag = resource.Tag as SDsResourceTag;
         //         if(-1 != resource.Keyword.IndexOf(value)) {
         //            treeView.Nodes.Add(restag.node);
         //         }
         //      }
         //   }
         //   _filterTag = value;
         //   treeView.EndUpdate();
         //}
      }
   }
}
