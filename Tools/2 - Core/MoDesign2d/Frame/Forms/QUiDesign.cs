using MO.Common.Collection;
using MO.Content2d;
using MO.Content2d.Frame.Common;
using MO.Design2d.Frame.Common;
using MO.Design2d.Resource.Controls;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Forms
{
   //============================================================
   // <T>界面设计。</T>
   //============================================================
   public partial class QUiDesign : UserControl
   {
      // 过滤内容
      protected string _filterValue = string.Empty;

      //============================================================
      // <T>构造界面设计。</T>
      //============================================================
      public QUiDesign() {
         InitializeComponent();
      }

      //============================================================
      // <T>选中资源。</T>
      //============================================================
      protected void SelectResource(object value) {
         SUiResourceTag tag = value as SUiResourceTag;
         panInfo.SuspendLayout();
         // 全部隐藏
         qDsUiProperty.Visible = false;
         if (tag != null) {
            qDsUiProperty.LoadResource(tag.frameResource);
            qDsUiProperty.Visible = true;
         }
         panInfo.ResumeLayout();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         // 初始化界面附加数据
         foreach (FNamePair<FRcFrame> pair in RContent2dManager.FrameConsole.Frames) {
            FRcFrame frame = pair.Value;
            frame.LinkerNode = new SUiResourceTag();
         }
         // 打开资源列表
         lvwResources.BeginUpdate();
         foreach (FNamePair<FRcFrame> pair in RContent2dManager.FrameConsole.Frames) {
            FRcFrame frame = pair.Value;
            // 创建列表
            ListViewItem lviResource = new ListViewItem(frame.Format());
            lviResource.ForeColor = Color.Black;
            lviResource.ImageKey = frame.TypeName;
            // 创建信息
            SUiResourceTag tag = new SUiResourceTag();
            tag.frameResource = frame;
            tag.item = lviResource;
            // 存储关联
            frame.LinkerNode = tag;
            lviResource.Tag = tag;
            lvwResources.Items.Add(lviResource);
         }
         lvwResources.EndUpdate();
         // 选择资源
         SelectResource(null);
      }

      //============================================================
      // <T>资源全部导出处理。</T>
      //============================================================
      private void qdsCatalog_CatalogAfterSelect(object sender, TreeViewEventArgs e) {
         TreeNode node = e.Node;
         if (node != null) {
            SelectResource(node.Tag);
         }
      }

      //============================================================
      // <T>资源选中处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件对象
      //============================================================
      private void lvwResources_SelectedIndexChanged(object sender, System.EventArgs e) {
         if (lvwResources.SelectedItems.Count == 1) {
            ListViewItem lviResource = lvwResources.SelectedItems[0];
            SelectResource(lviResource.Tag);
         }
      }

      //============================================================
      // <T>资源双击处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件对象
      //============================================================
      private void lvwResources_DoubleClick(object sender, System.EventArgs e) {
         if (lvwResources.SelectedItems.Count == 1) {
            ListViewItem item = lvwResources.SelectedItems[0];
            if (item != null) {
               SUiResourceTag tag = item.Tag as SUiResourceTag;
               if (tag != null) {
                  // 打开容器
                  FRcFrame frame = tag.frameResource;
                  frame.Open();
                  // 弹出画面
                  QUiDesignForm frameForm = RDesign2dManager.FrameConsole.OpenDesignForm(frame.Name);
                  frameForm.Show();
               }
            }
         }
      }

      //============================================================
      // <T>搜索按键按下。</T>
      //
      // @param sender 事件产生者
      // @param      e 数据对象
      // @author TYFNG 20120406 
      //============================================================
      private void cbxSearch_KeyUp(object sender, KeyEventArgs e) {
         string value = cbxSearch.Text.ToLower();
         if (_filterValue == value) {
            return;
         }
         // 资源过滤
         lvwResources.BeginUpdate();
         lvwResources.Items.Clear();
         foreach (FNamePair<FRcFrame> pair in RContent2dManager.FrameConsole.Frames) {
            FRcFrame frame = pair.Value;
            string format = frame.Format().ToLower();
            SUiResourceTag tag = frame.LinkerNode as SUiResourceTag;
            if (tag != null) {
               if (-1 != format.IndexOf(value)) {
                  lvwResources.Items.Add(tag.item);
               }
            }
         }
         lvwResources.EndUpdate();
         _filterValue = value;
      }

      //============================================================
      // <T>选中导出事件。</T>
      //      
      // @author TYFNG 20120406 
      //============================================================
      public void ExportSelected() {
         //FObjects<FDsResource> resources = qdsCatalog.SeletedResource();
         //foreach(FDsResource resource in resources) {
         //   FDsResourceExportTask task = new FDsResourceExportTask();
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
         //   FObjects<FDsResource> resources = qdsCatalog.SeletedResource();
         //   foreach(FDsResource resource in resources) {
         //      resource.Store();
         //   }
         //   MessageBox.Show("保存成功！");
      }
   }
}
