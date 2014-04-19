using MO.Common.Lang;
using MO.Content3d;
using MO.Core;
using MO.Core.Logic.Track;
using MO.Design3d.Material.Controls;
using MO.Design3d.Model.Controls;
using MO.Design3d.Scene.Controls;
using MO.Design3d.Template.Controls;
using MO.Design3d.Texture.Controls;
using System.Windows.Forms;

namespace MO.Design3d.Forms
{
   //============================================================
   // <T>控制台表单。</T>
   //============================================================
   public partial class QDsConsoleForm : Form
   {
      // 静态实例
      protected static QDsConsoleForm _instance;

      //============================================================
      // <T>获得静态实例。</T>
      //============================================================
      public static QDsConsoleForm Instance {
         get {
            if (_instance == null) {
               _instance = new QDsConsoleForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>构造控制台表单。</T>
      //============================================================
      public QDsConsoleForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开表单信息。</T>
      //============================================================
      public void Open() {
         // 打开界面
         qdrThemeDesign.Open();
         qdrTextureDesign.Open();
         qdrMaterialDesign.Open();
         qdrModelDesign.Open();
         qdrTemplateDesign.Open();
         qdrSceneDesign.Open();
         qdrResourceGroupDesign.Open();
         // 选择界面
         tbcPages.SelectedIndex = 3;
      }

      //============================================================
      // <T>全部保存按键事件。</T>
      //============================================================
      private void tsbSaveAll_Click(object sender, System.EventArgs e) {
         // 获得选中页和对象
         TabPage page = tbcPages.SelectedTab;
         string typeName = page.Tag.ToString();
         // 导出全部数据
         DialogResult resultCd;
         switch (typeName) {
            case "texture":
               resultCd = MessageBox.Show("是否要保存全部纹理？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.TextureConsole.SaveAll();
                  MessageBox.Show("保存全部纹理成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "material":
               resultCd = MessageBox.Show("是否要保存全部材质？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.MaterialConsole.SaveAll();
                  MessageBox.Show("保存全部材质成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "model":
               resultCd = MessageBox.Show("是否要保存全部模型？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.ModelConsole.SaveAll();
                  MessageBox.Show("保存全部模型成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "template":
               resultCd = MessageBox.Show("是否要保存全部模板？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.TemplateConsole.SaveAll();
                  MessageBox.Show("保存全部模板成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "scene":
               resultCd = MessageBox.Show("是否要保存全部场景？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.SenceConsole.SaveAll();
                  MessageBox.Show("保存全部场景成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            default:
               throw new FFatalException("Unknown type. (type_name={0})", typeName);
         }
      }

      //============================================================
      // <T>选中保存按键事件。</T>
      //============================================================
      private void tsbSaveSelected_Click(object sender, System.EventArgs e) {
         // 获得选中页和对象
         TabPage page = tbcPages.SelectedTab;
         string typeName = page.Tag.ToString();
         object control = null;
         if (page.Controls.Count > 0) {
            control = page.Controls[0];
         }
         // 导出全部数据
         DialogResult resultCd;
         switch (typeName) {
            case "texture":
               resultCd = MessageBox.Show("是否要保存选中纹理？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsTextureDesign texture = control as QDsTextureDesign;
                  texture.SaveSelect();
                  MessageBox.Show("保存选中纹理成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "material":
               resultCd = MessageBox.Show("是否要保存选中材质？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsMaterialDesign material = control as QDsMaterialDesign;
                  material.SaveSelect();
                  MessageBox.Show("保存选中材质成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "model":
               resultCd = MessageBox.Show("是否要保存选中模型？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsModelDesign model = control as QDsModelDesign;
                  model.SaveSelect();
                  MessageBox.Show("保存选中模型成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "template":
               resultCd = MessageBox.Show("是否要保存选中模板？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsTemplateDesign template = control as QDsTemplateDesign;
                  template.SaveSelect();
                  MessageBox.Show("保存选中模板成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            case "scene":
               resultCd = MessageBox.Show("是否要保存选中场景？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsSceneDesign scene = control as QDsSceneDesign;
                  scene.SaveSelect();
                  MessageBox.Show("保存选中场景成功", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information);
               }
               break;
            default:
               throw new FFatalException("Unknown type. (type_name={0})", typeName);
         }
      }

      //============================================================
      // <T>全部导出按键事件。</T>
      //============================================================
      private void tspExportAll_Click(object sender, System.EventArgs e) {
         // 获得选中页和对象
         TabPage page = tbcPages.SelectedTab;
         string typeName = page.Tag.ToString();
         object control = null;
         if (page.Controls.Count > 0) {
            control = page.Controls[0];
         }
         DialogResult resultCd;
         // 导出全部数据
         switch (typeName) {
            case "theme":
               resultCd = MessageBox.Show("是否要导出全部主题？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.ThemeConsole.TaskExportAll();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "texture":
               resultCd = MessageBox.Show("是否要导出全部纹理？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.TextureConsole.TaskExportAll();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "material":
               MessageBox.Show("禁止导出全部材质。", "提示", MessageBoxButtons.OK, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1);
               break;
            case "model":
               resultCd = MessageBox.Show("是否要导出全部模型？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.ModelConsole.TaskExportAll();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "template":
               resultCd = MessageBox.Show("是否要导出全部模板？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.TemplateConsole.TaskExportAll();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "scene":
               resultCd = MessageBox.Show("是否要导出全部场景？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  RContent3dManager.SenceConsole.TaskExportAll();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "group":
               resultCd = MessageBox.Show("是否要导出全部资源组？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  //RContent3dManager.ResourceGroupConsole.ExportAll();
                  //RDrManager.ResourceGroupConsole.TaskExportAll();
                  //RDrManager.ResourceGroupConsole.ExportTransfer();
                  MessageBox.Show("导出全部资源组成功。");
               }
               break;
            default:
               throw new FFatalException("Unknown type. (type_name={0})", typeName);
         }
      }

      //============================================================
      // <T>选中导出按键事件。</T>
      //============================================================
      private void tsbExportSelected_Click(object sender, System.EventArgs e) {
         // 获得选中页和对象
         TabPage page = tbcPages.SelectedTab;
         string typeName = page.Tag.ToString();
         object control = null;
         if (page.Controls.Count > 0) {
            control = page.Controls[0];
         }
         // 导出选中数据
         DialogResult resultCd;
         switch (typeName) {
            case "texture":
               resultCd = MessageBox.Show("是否要导出选中纹理？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsTextureDesign texture = control as QDsTextureDesign;
                  texture.ExportSelected();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "material":
               MessageBox.Show("禁止导出选中材质。", "提示", MessageBoxButtons.OK, MessageBoxIcon.Warning, MessageBoxDefaultButton.Button1);
               break;
            case "model":
               resultCd = MessageBox.Show("是否要导出选中模型？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsModelDesign model = control as QDsModelDesign;
                  model.ExportSelected();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "template":
               resultCd = MessageBox.Show("是否要导出选中模板？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsTemplateDesign template = control as QDsTemplateDesign;
                  template.ExportSelected();
                  RMoCore.TaskConsole.Show();
               }
               break;
            case "scene":
               resultCd = MessageBox.Show("是否要导出选中场景？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
               if (resultCd == DialogResult.OK) {
                  QDsSceneDesign scene = control as QDsSceneDesign;
                  scene.ExportSelected();
                  RMoCore.TaskConsole.Show();
               }
               break;
            default:
               throw new FFatalException("Unknown type. (type_name={0})", typeName);
         }
      }

      //============================================================
      // <T>导出所有资源。</T>
      //============================================================
      private void tsbExportResource_Click(object sender, System.EventArgs e) {
         DialogResult resultCd = MessageBox.Show("是否要导出所有存储资源？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question, MessageBoxDefaultButton.Button2);
         if (resultCd == DialogResult.OK) {
            //RContent3dManager.ResourceConsole.TaskExportAll();
            RMoCore.TaskConsole.Show();
         }
      }

      //============================================================
      // <T>打包所有资源。</T>
      //============================================================
      private void tsbPackAll_Click(object sender, System.EventArgs e) {
         //RContent3dManager.ResourceGroupConsole.ExportTransfer();
         MessageBox.Show("导出所有资源打包。");
      }

      //============================================================
      // <T>任务窗口按键事件。</T>
      //============================================================
      private void tsbWindowTask_Click(object sender, System.EventArgs e) {
         // 显示任务列表
         RMoCore.TaskConsole.Show();
      }

      //============================================================
      // <T>跟踪窗口按键事件。</T>
      //============================================================
      private void tsbWindowTrack_Click(object sender, System.EventArgs e) {
         QTrackForm.Instance.Show();
      }

      //============================================================
      // <T>退出按键事件。</T>
      //============================================================
      private void tsbExit_Click(object sender, System.EventArgs e) {
         Close();
      }
   }
}
