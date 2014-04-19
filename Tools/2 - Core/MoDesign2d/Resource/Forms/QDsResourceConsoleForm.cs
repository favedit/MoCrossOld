using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using MO.Core;
using MO.Core.Logic.Task;
using MO.Core.Logic.Track;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Forms
{
   //============================================================
   // <T>设计表单。</T>
   //============================================================
   public partial class QDsResourceConsoleForm : Form
   {
      // 设计表单
      protected static QDsResourceConsoleForm _instance;

      //============================================================
      // <T>获得设计表单单件。</T>
      //============================================================
      public static QDsResourceConsoleForm Instance {
         get {
            if(null == _instance) {
               _instance = new QDsResourceConsoleForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造设计表单。</T>
      //============================================================
      public QDsResourceConsoleForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //
      // @author TYFNG 20120409
      //============================================================
      public void Open() {
         qdsResourceDesign.Open();
         qdsResourceGroupDesign.Open();
      }

      //============================================================
      // <T>保存全部事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbSaveAll_Click(object sender, EventArgs e) {
         //// 获取当前焦点控件
         //TabPage tabPage = tbcPages.SelectedTab;
         //string typeCd = tabPage.Tag.ToString();
         //switch(typeCd) {
         //   case "resource":
         //      RDsResource.ResourceConsole.SaveAll();
         //      break;
         //   case "group":
         //      foreach(INamePair<FRsResourceGroup> pair in RDsResource.ResourceConsole.GroupDictionary) {
         //         foreach(INamePair<FDsResource> resPair in pair.Value.ResourceDictionary) {
         //            FDsResource resource = resPair.Value;
         //            resource.Store();
         //         }
         //      }
         //      break;
         //}
      }

      //============================================================
      // <T>保存选中事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbSaveSelected_Click(object sender, EventArgs e) {
         TabPage tabPage = tbcPages.SelectedTab;
         string typeCd = tabPage.Tag.ToString();
         switch(typeCd) {
            case "resource":
               qdsResourceDesign.SaveSelected();
               break;
            case "group":
               qdsResourceGroupDesign.SaveSelected();
               break;
            case "map":
               // RDsResource.MapConsole.ExportAll();
               break;
            case "Ui":
               // RDsResource.MapConsole.ExportAll();
               break;
            default:
               break;
         }
         //MessageBox.Show("保存成功！");
      }

      //============================================================
      // <T>图标资源导出处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件
      //============================================================
      private void tsbExportIconAll_Click(object sender, EventArgs e) {
         //TabPage tabPage = tbcPages.SelectedTab;
         //string typeCd = tabPage.Tag.ToString();
         //switch(typeCd) {
         //   case "resource":
         //      if(MessageBox.Show("导出全部图标资源。", "确认导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.ResourceConsole.ExportResourcePictureAll(ERsExportMode.CompressDeflate, "4");
         //         RMoCore.TaskConsole.Show();
         //      }
         //      break;
         //   default:
         //      MessageBox.Show("禁止导出图标以外的界面资源。", "警告信息");
         //      break;
         //}
      }

      //============================================================
      // <T>界面资源导出处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件
      //============================================================
      private void tsbExportUiAll_Click(object sender, EventArgs e) {
         //TabPage tabPage = tbcPages.SelectedTab;
         //string typeCd = tabPage.Tag.ToString();
         //switch(typeCd) {
         //   case "resource":
         //      if(MessageBox.Show("导出全部界面资源。", "确认导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.ResourceConsole.ExportResourcePictureAll(ERsExportMode.CompressDeflate, "7");
         //         RMoCore.TaskConsole.Show();
         //      }
         //      break;
         //   default:
         //      MessageBox.Show("禁止导出资源以外的界面资源。", "警告信息");
         //      break;
         //}
      }

      //============================================================
      // <T>图片资源导出处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件
      //============================================================
      private void tsbExportPictureAll_Click(object sender, EventArgs e) {
         //TabPage tabPage = tbcPages.SelectedTab;
         //string typeCd = tabPage.Tag.ToString();
         //switch(typeCd) {
         //   case "resource":
         //      if(MessageBox.Show("导出全部图片资源。", "确认导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.ResourceConsole.ExportResourcePictureAll(ERsExportMode.CompressDeflate, null);
         //         RMoCore.TaskConsole.Show();
         //      }
         //      break;
         //   default:
         //      MessageBox.Show("禁止导出资源以外的图片资源。", "警告信息");
         //      break;
         //}
      }

      //============================================================
      // <T>变更资源导出处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件
      //============================================================
      private void tsbExportChangedAll_Click(object sender, EventArgs e) {
         //// 获取当前焦点控件
         //TabPage tabPage = tbcPages.SelectedTab;
         //string typeCd = tabPage.Tag.ToString();
         //switch(typeCd) {
         //   case "resource":
         //      if(MessageBox.Show("导出全部资源。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.ResourceConsole.ExportResourceAll(ERsExportMode.CompressDeflate, true, true);
         //         RMoCore.TaskConsole.Show();
         //      }
         //      break;
         //   default:
         //      MessageBox.Show("禁止导出非资源类型。");
         //      break;
         //}
      }
      
      //============================================================
      // <T>全部资源导出处理。</T>
      //
      // @param sender 事件产生者
      // @param e 事件
      //============================================================
      private void tsbExportAll_Click(object sender, EventArgs e) {
         //// 获取当前焦点控件
         //TabPage tabPage = tbcPages.SelectedTab;
         //string typeCd = tabPage.Tag.ToString();
         //switch(typeCd) {
         //   case "resource":
         //      if(MessageBox.Show("导出全部资源。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.ResourceConsole.ExportResourceAll(ERsExportMode.CompressDeflate, true, false);
         //         RMoCore.TaskConsole.Show();
         //      }
         //      break;
         //   case "group":
         //      if(MessageBox.Show("导出全部资源组。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.ResourceConsole.ExportGroupAll(ERsExportMode.Storage);
         //         MessageBox.Show("导出全部资源组成功。", "确认全部导出");
         //      }
         //      break;
         //   case "map":
         //      if(MessageBox.Show("导出全部地图。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.MapConsole.ExportAll(ERsExportMode.CompressDeflate);
         //         RMoCore.TaskConsole.Show();
         //      }
         //      break;
         //   case "Ui":
         //      if(MessageBox.Show("导出全部界面。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //         RDsResource.UiConsole.ExportAll();
         //         MessageBox.Show("导出全部界面成功。", "确认全部导出");
         //      }
         //      break;
         //}
      }

      //============================================================
      // <T>选中导出事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      // @author TYFNG 20120406
      //============================================================
      private void tsbExportSelected_Click(object sender, EventArgs e) {
         if(MessageBox.Show("导出选中全部资源。", "确认选中导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            // 获取当前焦点控件
            TabPage tabPage = tbcPages.SelectedTab;
            string typeCd = tabPage.Tag.ToString();
            switch(typeCd) {
               case "resource":
                  qdsResourceDesign.ExportSelected(ERsExportMode.CompressDeflate);
                  break;
               case "group":
                  qdsResourceGroupDesign.ExportSelected(ERsExportMode.CompressDeflate);
                  break;
               default:
                  break;
            }
            RMoCore.TaskConsole.Show();
         }
      }

      //============================================================
      // <T>资源打包事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e 数据对象
      //============================================================
      private void tsbPackAll_Click(object sender, EventArgs e) {
         //if(MessageBox.Show("打包全部资源。", "确认打包", MessageBoxButtons.OKCancel) == DialogResult.OK) {
         //   RDsResource.ResourceConsole.ExportResourceAll(ERsExportMode.Storage);
         //   RMoCore.TaskConsole.Show();
         //}
      }

      //============================================================
      // <T>资源打包事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e 数据对象
      //============================================================
      private void tsbPackGroup_Click(object sender, EventArgs e) {
         if(MessageBox.Show("打包全部资源组。", "确认打包", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            // RDsResource.ResourceConsole.ExportGroupAll(ERsExportMode.Storage);
            MessageBox.Show("打包全部资源组成功。", "确认打包");
         }
      }

      //============================================================
      // <T>配置打包事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e 数据对象
      //============================================================
      private void tsbPackConfig_Click(object sender, EventArgs e) {
      }

      //============================================================
      // <T>工具箱导出定义。</T>
      //
      // @param sender 事件产生者
      // @param args 事件参数
      //============================================================
      private void tsmiToolsExportDefine_Click(object sender, EventArgs args) {
      }

      //============================================================
      // <T>资源合并。</T>
      //
      // @param sender 事件产生者
      // @param args 事件参数
      //============================================================
      private void tsmiToolsResourceMerge_Click(object sender, EventArgs e) {
      }

      //============================================================
      // <T>任务窗口按键事件。</T>
      //============================================================
      private void tsbWindowTask_Click(object sender, System.EventArgs e) {
         QTaskForm.Instance.Open();
      }

      //============================================================
      // <T>跟踪窗口按键事件。</T>
      //============================================================
      private void tsbWindowTrack_Click(object sender, System.EventArgs e) {
         QTrackForm.Instance.Show();
      }

      //============================================================
      // <T>离开事件。</T>
      //
      // @param sender 事件产生者
      // @param  e     数据对象
      // @author TYFNG 20120409
      //============================================================
      private void tsbExit_Click(object sender, EventArgs e) {
         Close();
      }

      //============================================================
      // <T>保存校验值</T>
      //============================================================
      private void tsbSaveSha_Click(object sender, EventArgs e) {
         //if("group" == tbcPages.TabPages[tbcPages.SelectedIndex].Tag.ToString()) {
         //   FXmlDocument doc = new FXmlDocument();
         //   doc.LoadFile(RDsResource.Directory + @"\Configuration\style.resource.group.xml");
         //   FXmlNode root = doc.Root;
         //   foreach(FXmlNode node in root.Nodes) {
         //      string groupId = node.Get("tid");
         //      FDsResourceGroup group = RDsResource.ResourceConsole.GroupDictionary.Find(groupId);
         //      if(null != group) {
         //         node.Attributes.Set("sha", group.Sha);
         //      } else {
         //         MessageBox.Show("group is exits");
         //      }
         //   }
         //   doc.Store();
         //   FXmlDocument document = new FXmlDocument();
         //   document.LoadFile(RDsResource.Directory + @"\Configuration\style.resource.xml");
         //   FXmlNode config = document.Root;
         //   foreach(FXmlNode resourceNode in config.Nodes) {
         //      string resourceCode = resourceNode.Get("code");
         //      FDsResource resource = RDsResource.ResourceConsole.ResourceCodes.Find(resourceCode);
         //      if(null != resource) {
         //         resourceNode.Attributes.Set("sha", resource.Sha);
         //      } else {
         //         MessageBox.Show("resource is exits");
         //      }
         //   }
         //   document.Store();
         //}
         //if("map" == tbcPages.TabPages[tbcPages.SelectedIndex].Tag.ToString()) {
         //   FXmlDocument documentXml = new FXmlDocument();
         //   documentXml.LoadFile(RDsResource.Directory + @"\Configuration\map.xml");
         //   FXmlNode configs = documentXml.Root;
         //   foreach(FXmlNode resourceNode in configs.Nodes) {
         //      int Id = RInt.Parse(resourceNode.Get("tid"));
         //      FDsMap resource = RDsResource.MapConsole.FindById(Id);
         //      if(null == resource) {
         //         continue;
         //      }
         //      resourceNode.Attributes.Set("sha", resource.Sha);
         //   }
         //   documentXml.Store();
         //}
         //MessageBox.Show("保存成功");
      }

      //============================================================
      // <T>导出样式尺寸.</T>
      //============================================================
      private void tsmToolsResourceEffect_Click(object sender, EventArgs e) {
         if (MessageBox.Show("导出样式尺寸。", "确认导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            // RDsResource.ResourceConsole.ExportMergeAll();
            MessageBox.Show("导出样式尺寸成功。", "确认导出");
         }
      }
   }
}
