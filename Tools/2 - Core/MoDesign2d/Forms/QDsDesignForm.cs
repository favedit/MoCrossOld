using MO.Content2d;
using MO.Content2d.Common;
using MO.Core;
using MO.Core.Logic.Task;
using MO.Core.Logic.Track;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Forms
{
   //============================================================
   // <T>设计表单。</T>
   //============================================================
   public partial class QDsDesignForm : Form
   {
      // 设计表单
      protected static QDsDesignForm _instance;

      //============================================================
      // <T>获得设计表单单件。</T>
      //============================================================
      public static QDsDesignForm Instance {
         get {
            if (null == _instance) {
               _instance = new QDsDesignForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造设计表单。</T>
      //============================================================
      public QDsDesignForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //
      // @author TYFNG 20120409
      //============================================================
      public void Open() {
         qdsResourceDesign.Open();
         //qdsResourceGroupDesign.Open();
         //qdsMapDesign.Open();
         //qDsUiDesign.Open();
         //qDsSenceDesign.Open();
      }

      //============================================================
      // <T>保存全部事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      //============================================================
      private void tsbSaveAll_Click(object sender, EventArgs e) {
         // 获取当前焦点控件
         TabPage tabPage = tbcPages.SelectedTab;
         string typeCd = tabPage.Tag.ToString();
         switch (typeCd) {
            case "resource":
               RContent2dManager.ResourceConsole.SaveAll();
               break;
            case "group":
               //foreach (INamePair<FDsResourceGroup> pair in RDsResource.ResourceConsole.GroupDictionary) {
               //   foreach (INamePair<FDsResource> resPair in pair.Value.ResourceDictionary) {
               //      FDsResource resource = resPair.Value;
               //      resource.Store();
               //   }
               //}
               break;
         }
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
         switch (typeCd) {
            case "resource":
               qdsResourceDesign.SaveSelected();
               break;
            case "group":
               //qdsResourceGroupDesign.SaveSelected();
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
      // <T>导出全部事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e 数据对象
      //============================================================
      private void tsbExportAll_Click(object sender, EventArgs e) {
         // 获取当前焦点控件
         TabPage tabPage = tbcPages.SelectedTab;
         string typeCd = tabPage.Tag.ToString();
         switch (typeCd) {
            case "resource":
               if (MessageBox.Show("导出全部资源。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
                  RContent2dManager.ResourceConsole.TaskExportAll(ERsExportMode.Data);
                  RMoCore.TaskConsole.Show();
               }
               break;
            //case "group":
            //   if (MessageBox.Show("导出全部资源组。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            //      RContent2dManager.ResourceConsole.ExportGroupAll(ERsExportMode.Data);
            //      MessageBox.Show("导出全部资源组成功。", "确认全部导出");
            //   }
            //   break;
            //case "map":
            //   if(MessageBox.Show("导出全部地图。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            //      RDsResource.MapConsole.ExportAll(ERsExportMode.CompressDeflate);
            //      RMoCore.TaskConsole.Show();
            //   }
            //   break;
            //case "Ui":
            //   if(MessageBox.Show("导出全部界面。", "确认全部导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            //      RDsResource.UiConsole.ExportAll();
            //      MessageBox.Show("导出全部界面成功。", "确认全部导出");
            //   }
            //   break;
         }
      }

      //============================================================
      // <T>选中导出事件处理。</T>
      //
      // @param sender 事件产生者
      // @param e      数据对象
      // @author TYFNG 20120406
      //============================================================
      private void tsbExportSelected_Click(object sender, EventArgs e) {
         if (MessageBox.Show("导出选中全部资源。", "确认选中导出", MessageBoxButtons.OKCancel) == DialogResult.OK) {
            // 获取当前焦点控件
            TabPage tabPage = tbcPages.SelectedTab;
            string typeCd = tabPage.Tag.ToString();
            switch (typeCd) {
               //case "resource":
               //   qdsResourceDesign.ExportSelected(ERsExportMode.CompressDeflate);
               //   break;
               //case "group":
               //   qdsResourceGroupDesign.ExportSelected(ERsExportMode.CompressDeflate);
               //   break;
               //case "map":
               //   RDsResource.MapConsole.ExportAll(ERsExportMode.CompressDeflate);
               //   break;
               case "Ui":
                  //RDsResource.MapConsole.ExportAll();
                  break;
               default:
                  break;
            }
            RMoCore.TaskConsole.Show();
         }
      }

      //============================================================
      // <T>任务窗口按键事件。</T>
      //
      // @param sender 事件产生者
      // @param e 事件对象
      //============================================================
      private void tsbWindowTask_Click(object sender, System.EventArgs e) {
         QTaskForm.Instance.Open();
      }

      //============================================================
      // <T>跟踪窗口按键事件。</T>
      //
      // @param sender 事件产生者
      // @param e 事件对象
      //============================================================
      private void tsbWindowTrack_Click(object sender, System.EventArgs e) {
         QTrackForm.Instance.Show();
      }

      //============================================================
      // <T>离开事件。</T>
      //
      // @param sender 事件产生者
      // @param e 事件对象
      //============================================================
      private void tsbExit_Click(object sender, EventArgs e) {
         Close();
      }
   }
}
