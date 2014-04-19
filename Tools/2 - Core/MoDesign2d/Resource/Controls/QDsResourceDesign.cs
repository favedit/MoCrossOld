using MO.Common.Collection;
using MO.Common.Lang;
using MO.Content2d;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using MO.Core;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Controls
{
   //============================================================
   // <T>资源设计器。</T>
   //============================================================
   public partial class QDsResourceDesign : UserControl
   {
      // 上次搜索信息
      protected string _filterValue = String.Empty;

      //============================================================
      // <T>构造资源设计器。</T>
      //============================================================
      public QDsResourceDesign() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         FRsResourceDictionary resourceDictionary = RContent2dManager.ResourceConsole.Resources;
         // 初始化资源附加数据
         foreach (IIntPair<FRsResource> pair in resourceDictionary) {
            pair.Value.Tag = new SDsResourceTag();
         }
         // 打开资源目录
         qdsCatalog.Open();
         // 打开资源列表
         lvwResources.BeginUpdate();
         foreach (IIntPair<FRsResource> pair in resourceDictionary) {
            FRsResource resource = pair.Value;
            ListViewItem lviResource = new ListViewItem(resource.Label);
            if (resource.OptionValid) {
               lviResource.ForeColor = Color.Black;
               lviResource.ImageKey = resource.TypeName;
            } else {
               lviResource.ForeColor = Color.Gray;
               lviResource.ImageKey = resource.TypeName + "D";
            }
            // 设置关联
            SDsResourceTag tag = resource.Tag as SDsResourceTag;
            tag.item = lviResource;
            lviResource.Tag = resource;
            lvwResources.Items.Add(lviResource);
         }
         lvwResources.EndUpdate();
         // 选择资源
         SelectResource(null);
      }

      //============================================================
      // <T>搜索按键按下。</T>
      //
      // @param sender 事件产生者
      // @param      e 数据对象
      //============================================================
      private void cbxSearch_KeyUp(object sender, KeyEventArgs e) {
         string value = cbxSearch.Text;
         if (_filterValue == value) {
            return;
         }
         // 资源过滤
         lvwResources.BeginUpdate();
         lvwResources.Items.Clear();
         foreach (IIntPair<FRsResource> pair in RContent2dManager.ResourceConsole.Resources) {
            FRsResource resource = pair.Value;
            SDsResourceTag tag = resource.Tag as SDsResourceTag;
            if (-1 != resource.Keyword.IndexOf(value)) {
               lvwResources.Items.Add(tag.item);
            }
         }
         lvwResources.EndUpdate();
         _filterValue = value;
      }

      //============================================================
      protected void SelectResource(object value) {
         pnlInfo.SuspendLayout();
         // 全部隐藏
         qdsPicture.Visible = false;
         qdsAnimation.Visible = false;
         // 对象处理
         if (value is FRsResourceFolder) {
            FRsResourceFolder folder = value as FRsResourceFolder;
            // 目录处理
            labNameDisplay.Text = "目录：" + folder.Label;
         } else if (value is FRsResource) {
            FRsResource resource = value as FRsResource;
            resource.Open();
            // 资源处理
            switch (resource.TypeCd) {
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
         pnlInfo.ResumeLayout();
      }

      //============================================================
      // <T>资源全部导出处理。</T>
      //============================================================
      private void qdsCatalog_CatalogAfterSelect(object sender, TreeViewEventArgs e) {
         TreeNode node = e.Node;
         if(null != node) {
            SelectResource(node.Tag);
         }
      }

      //============================================================
      // <T>资源全部导出处理。</T>
      //============================================================
      private void lvwResources_SelectedIndexChanged(object sender, System.EventArgs e) {
         if(1 == lvwResources.SelectedItems.Count) {
            ListViewItem lviResource = lvwResources.SelectedItems[0];
            SelectResource(lviResource.Tag);
         }
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void SaveSelected() {
         FObjects<FRsResource> resources = qdsCatalog.SeletedResource();
         foreach (FRsResource resource in resources) {
            resource.Store();
         }
         MessageBox.Show("保存成功！");
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void ExportSelected(ERsExportMode modeCd) {
         FObjects<FRsResource> resources = qdsCatalog.SeletedResource();
         foreach (FRsResource resource in resources) {
            FRsExportTask task = new FRsExportTask();
            task.ModeCd = modeCd;
            task.Exporter = resource;
            RMoCore.TaskConsole.Push(task);
         }
      }
   }
}
