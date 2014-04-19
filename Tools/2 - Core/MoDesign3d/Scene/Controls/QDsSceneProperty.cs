using MO.Content2d.Common;
using MO.Content3d.Resource.Scene;
using System;
using System.Diagnostics;
using System.Windows.Forms;

namespace MO.Design3d.Scene.Controls
{
   //============================================================
   // <T>场景属性控件。</T>
   //============================================================
   public partial class QDsSceneProperty : UserControl
   {
      // 场景对象
      protected FDrScene _scene;

      //============================================================
      // <T>构造场景属性控件。</T>
      //============================================================
      public QDsSceneProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>设置数值。</T>
      //
      // @prama texture 纹理对象
      //============================================================
      public void LoadScene(FDrScene scene) {
         scene.Open();
         _scene = scene;
         // 建立目录树
         tvwCatalog.BeginUpdate();
         tvwCatalog.Nodes.Clear();
         //............................................................
         // 建立技术节点
         TreeNode sceneNode = new TreeNode("Scene: " + _scene.TechniqueName);
         sceneNode.ImageKey = "scene";
         sceneNode.SelectedImageKey = "scene";
         tvwCatalog.Nodes.Add(sceneNode);
         //............................................................
         // 建立技术节点
         TreeNode techniqueNode = new TreeNode("Technique");
         techniqueNode.ImageKey = "folder";
         techniqueNode.SelectedImageKey = "folder";
         tvwCatalog.Nodes.Add(techniqueNode);
         TreeNode techniqueDetailNode = new TreeNode(scene.Technique.Name);
         techniqueDetailNode.ImageKey = "technique";
         techniqueDetailNode.SelectedImageKey = "technique";
         foreach (FDrScenePass pass in scene.Technique.Passes) {
            TreeNode passNode = new TreeNode(pass.Name);
            passNode.ImageKey = "pass";
            passNode.SelectedImageKey = "pass";
            passNode.Tag = pass;
            techniqueDetailNode.Nodes.Add(passNode);
         }
         techniqueNode.Nodes.Add(techniqueDetailNode);
         //............................................................
         // 建立区域节点
         TreeNode regionNode = new TreeNode("Region");
         regionNode.ImageKey = "folder";
         regionNode.SelectedImageKey = "folder";
         regionNode.Tag = _scene.Region;
         qdrSceneRegionProperty.LoadRegion(_scene.Region);
         // 建立相机节点
         TreeNode cameraNode = new TreeNode("Camera");
         cameraNode.ImageKey = "item";
         cameraNode.SelectedImageKey = "item";
         cameraNode.Tag = _scene.Region.Camera;
         regionNode.Nodes.Add(cameraNode);
         // 建立光源节点
         TreeNode lightNode = new TreeNode("Light");
         lightNode.ImageKey = "item";
         lightNode.SelectedImageKey = "item";
         lightNode.Tag = _scene.Region.Light;
         regionNode.Nodes.Add(lightNode);
         tvwCatalog.Nodes.Add(regionNode);
         qdrSceneLightProperty.LoadLight(_scene.Region.Light);
         //............................................................
         // 建立天空节点
         TreeNode skyNode = new TreeNode("Sky");
         skyNode.ImageKey = "folder";
         skyNode.SelectedImageKey = "folder";
         foreach(FDrSceneDisplay display in scene.Sky.Displays) {
            TreeNode displayNode = new TreeNode(display.Source);
            displayNode.ImageKey = "display";
            displayNode.SelectedImageKey = "display";
            displayNode.Tag = displayNode;
            skyNode.Nodes.Add(displayNode);
         }
         tvwCatalog.Nodes.Add(skyNode);
         //............................................................
         // 建立地图节点
         TreeNode mapNode = new TreeNode("Map");
         mapNode.ImageKey = "folder";
         mapNode.SelectedImageKey = "folder";
         foreach (FDrSceneDisplay display in scene.Map.Displays) {
            TreeNode displayNode = new TreeNode(display.Source);
            displayNode.ImageKey = "display";
            displayNode.SelectedImageKey = "display";
            displayNode.Tag = displayNode;
            mapNode.Nodes.Add(displayNode);
         }
         tvwCatalog.Nodes.Add(mapNode);
         //............................................................
         // 建立空间节点
         TreeNode spaceNode = new TreeNode("Space");
         spaceNode.ImageKey = "folder";
         spaceNode.SelectedImageKey = "folder";
         foreach(FDrSceneDisplay display in scene.Space.Displays) {
            TreeNode displayNode = new TreeNode(display.Source);
            displayNode.ImageKey = "display";
            displayNode.SelectedImageKey = "display";
            displayNode.Tag = displayNode;
            spaceNode.Nodes.Add(displayNode);
         }
         tvwCatalog.Nodes.Add(spaceNode);
         //............................................................
         tvwCatalog.EndUpdate();
         tvwCatalog.ExpandAll();
         SelectItem(null);
      }

      //============================================================
      // <T>设置数值。</T>
      //
      // @prama texture 纹理对象
      //============================================================
      public void SaveScene() {
         qdrSceneRegionProperty.SaveRegion();
         qdrSceneLightProperty.SaveLight();
      }
      
      //============================================================
      // <T>保存资源设置。</T>
      //============================================================
      private void tspSave_Click(object sender, EventArgs e) {
         SaveScene();
         _scene.Store();
         MessageBox.Show("已经保存成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>打开资源所在文件夹。</T>
      //============================================================
      private void tsbOpen_Click(object sender, EventArgs e) {
         Process.Start(_scene.Directory);
      }

      //============================================================
      // <T>导入设置资源数据。</T>
      //============================================================
      private void tspImport_Click(object sender, EventArgs e) {
         _scene.ImportConfig();
         MessageBox.Show("已经导入配置成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>导入纹理资源数据。</T>
      //============================================================
      private void tspImportMaterial_Click(object sender, EventArgs e) {
         _scene.ImportMaterial();
         MessageBox.Show("已经导出材质成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>导出资源数据。</T>
      //============================================================
      private void tspExport_Click(object sender, EventArgs e) {
         _scene.Export(ERsExportMode.CompressLzma);
         MessageBox.Show("已经导出成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>选中显示项目。</T>
      //
      // @param item 项目
      //============================================================
      public void SelectItem(object item) {
         qdrSceneRegionProperty.Visible = false;
         qdrSceneCameraProperty.Visible = false;
         qdrSceneLightProperty.Visible = false;
         qdrSceneTerrainProperty.Visible = false;
         qdrSceneEntityProperty.Visible = false;
         if(item is FDrSceneRegion) {
            // 设置区域信息
            FDrSceneRegion region = item as FDrSceneRegion;
            qdrSceneRegionProperty.Dock = DockStyle.Fill;
            qdrSceneRegionProperty.Visible = true;
            qdrSceneRegionProperty.LoadRegion(region);
         }else  if(item is FDrSceneCamera) {
            // 设置相机信息
            FDrSceneCamera camera = item as FDrSceneCamera;
            qdrSceneCameraProperty.Dock = DockStyle.Fill;
            qdrSceneCameraProperty.Visible = true;
            qdrSceneCameraProperty.LoadCamera(camera);
         } else if(item is FDrSceneLight) {
            // 设置光源信息
            FDrSceneLight light = item as FDrSceneLight;
            qdrSceneLightProperty.Dock = DockStyle.Fill;
            qdrSceneLightProperty.Visible = true;
         }
      }

      //============================================================
      // <T>选中对象。</T>
      //============================================================
      private void tvwCatalog_AfterSelect(object sender, TreeViewEventArgs e) {
         SelectItem(e.Node.Tag);
      }
   }
}
