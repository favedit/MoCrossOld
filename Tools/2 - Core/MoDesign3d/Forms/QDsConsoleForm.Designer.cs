namespace MO.Design3d.Forms
{
   partial class QDsConsoleForm
   {
      /// <summary>
      /// Required designer variable.
      /// </summary>
      private System.ComponentModel.IContainer components = null;

      /// <summary>
      /// Clean up any resources being used.
      /// </summary>
      /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
      protected override void Dispose(bool disposing) {
         if (disposing && (components != null)) {
            components.Dispose();
         }
         base.Dispose(disposing);
      }

      #region Windows Form Designer generated code

      /// <summary>
      /// Required method for Designer support - do not modify
      /// the contents of this method with the code editor.
      /// </summary>
      private void InitializeComponent() {
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsConsoleForm));
         this.tpgTexture = new System.Windows.Forms.TabPage();
         this.qdrTextureDesign = new MO.Design3d.Texture.Controls.QDsTextureDesign();
         this.tspExportAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExportSelected = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbSaveSelected = new System.Windows.Forms.ToolStripButton();
         this.tbcPages = new System.Windows.Forms.TabControl();
         this.tpgTheme = new System.Windows.Forms.TabPage();
         this.qdrThemeDesign = new MO.Design3d.Theme.Controls.QDsThemeDesign();
         this.tpgMaterial = new System.Windows.Forms.TabPage();
         this.qdrMaterialDesign = new MO.Design3d.Material.Controls.QDsMaterialDesign();
         this.tpgModel = new System.Windows.Forms.TabPage();
         this.qdrModelDesign = new MO.Design3d.Model.Controls.QDsModelDesign();
         this.tpgTemplate = new System.Windows.Forms.TabPage();
         this.qdrTemplateDesign = new MO.Design3d.Template.Controls.QDsTemplateDesign();
         this.tpgTerrain = new System.Windows.Forms.TabPage();
         this.qdrTerrainDesign = new MO.Design3d.Terrain.Controls.QDsTerrainDesign();
         this.tpgScene = new System.Windows.Forms.TabPage();
         this.qdrSceneDesign = new MO.Design3d.Scene.Controls.QDsSceneDesign();
         this.tpgGroup = new System.Windows.Forms.TabPage();
         this.qdrResourceGroupDesign = new MO.Design3d.ResourceGroup.Controls.QDsResourceGroupDesign();
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbSaveAll = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExportResource = new System.Windows.Forms.ToolStripButton();
         this.tsbPackAll = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbWindowTask = new System.Windows.Forms.ToolStripButton();
         this.tsbWindowTrack = new System.Windows.Forms.ToolStripButton();
         this.tsbExit = new System.Windows.Forms.ToolStripButton();
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.tpgTexture.SuspendLayout();
         this.tbcPages.SuspendLayout();
         this.tpgTheme.SuspendLayout();
         this.tpgMaterial.SuspendLayout();
         this.tpgModel.SuspendLayout();
         this.tpgTemplate.SuspendLayout();
         this.tpgTerrain.SuspendLayout();
         this.tpgScene.SuspendLayout();
         this.tpgGroup.SuspendLayout();
         this.tspMain.SuspendLayout();
         this.SuspendLayout();
         // 
         // tpgTexture
         // 
         this.tpgTexture.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgTexture.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgTexture.Controls.Add(this.qdrTextureDesign);
         this.tpgTexture.Location = new System.Drawing.Point(4, 25);
         this.tpgTexture.Name = "tpgTexture";
         this.tpgTexture.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgTexture.Size = new System.Drawing.Size(1576, 786);
         this.tpgTexture.TabIndex = 0;
         this.tpgTexture.Tag = "texture";
         this.tpgTexture.Text = "纹理管理";
         // 
         // qdrTextureDesign
         // 
         this.qdrTextureDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrTextureDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrTextureDesign.Name = "qdrTextureDesign";
         this.qdrTextureDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrTextureDesign.TabIndex = 0;
         this.qdrTextureDesign.Tag = "qdrTextureDesign";
         // 
         // tspExportAll
         // 
         this.tspExportAll.Image = ((System.Drawing.Image)(resources.GetObject("tspExportAll.Image")));
         this.tspExportAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExportAll.Name = "tspExportAll";
         this.tspExportAll.Size = new System.Drawing.Size(76, 22);
         this.tspExportAll.Text = "全部导出";
         this.tspExportAll.Click += new System.EventHandler(this.tspExportAll_Click);
         // 
         // tsbExportSelected
         // 
         this.tsbExportSelected.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportSelected.Image")));
         this.tsbExportSelected.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportSelected.Name = "tsbExportSelected";
         this.tsbExportSelected.Size = new System.Drawing.Size(76, 22);
         this.tsbExportSelected.Text = "选中导出";
         this.tsbExportSelected.Click += new System.EventHandler(this.tsbExportSelected_Click);
         // 
         // toolStripSeparator2
         // 
         this.toolStripSeparator2.Name = "toolStripSeparator2";
         this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbSaveSelected
         // 
         this.tsbSaveSelected.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveSelected.Image")));
         this.tsbSaveSelected.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveSelected.Name = "tsbSaveSelected";
         this.tsbSaveSelected.Size = new System.Drawing.Size(76, 22);
         this.tsbSaveSelected.Text = "选中保存";
         this.tsbSaveSelected.Click += new System.EventHandler(this.tsbSaveSelected_Click);
         // 
         // tbcPages
         // 
         this.tbcPages.Appearance = System.Windows.Forms.TabAppearance.FlatButtons;
         this.tbcPages.Controls.Add(this.tpgTheme);
         this.tbcPages.Controls.Add(this.tpgTexture);
         this.tbcPages.Controls.Add(this.tpgMaterial);
         this.tbcPages.Controls.Add(this.tpgModel);
         this.tbcPages.Controls.Add(this.tpgTemplate);
         this.tbcPages.Controls.Add(this.tpgTerrain);
         this.tbcPages.Controls.Add(this.tpgScene);
         this.tbcPages.Controls.Add(this.tpgGroup);
         this.tbcPages.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tbcPages.Location = new System.Drawing.Point(0, 25);
         this.tbcPages.Name = "tbcPages";
         this.tbcPages.SelectedIndex = 0;
         this.tbcPages.Size = new System.Drawing.Size(1584, 815);
         this.tbcPages.TabIndex = 3;
         this.tbcPages.Tag = "texture";
         // 
         // tpgTheme
         // 
         this.tpgTheme.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgTheme.Controls.Add(this.qdrThemeDesign);
         this.tpgTheme.Location = new System.Drawing.Point(4, 25);
         this.tpgTheme.Name = "tpgTheme";
         this.tpgTheme.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgTheme.Size = new System.Drawing.Size(1576, 786);
         this.tpgTheme.TabIndex = 9;
         this.tpgTheme.Tag = "theme";
         this.tpgTheme.Text = "主题管理";
         this.tpgTheme.UseVisualStyleBackColor = true;
         // 
         // qdrThemeDesign
         // 
         this.qdrThemeDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrThemeDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrThemeDesign.Name = "qdrThemeDesign";
         this.qdrThemeDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrThemeDesign.TabIndex = 0;
         // 
         // tpgMaterial
         // 
         this.tpgMaterial.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgMaterial.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgMaterial.Controls.Add(this.qdrMaterialDesign);
         this.tpgMaterial.Location = new System.Drawing.Point(4, 25);
         this.tpgMaterial.Name = "tpgMaterial";
         this.tpgMaterial.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgMaterial.Size = new System.Drawing.Size(1576, 786);
         this.tpgMaterial.TabIndex = 3;
         this.tpgMaterial.Tag = "material";
         this.tpgMaterial.Text = "材质管理";
         // 
         // qdrMaterialDesign
         // 
         this.qdrMaterialDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrMaterialDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrMaterialDesign.Name = "qdrMaterialDesign";
         this.qdrMaterialDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrMaterialDesign.TabIndex = 0;
         // 
         // tpgModel
         // 
         this.tpgModel.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgModel.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgModel.Controls.Add(this.qdrModelDesign);
         this.tpgModel.Location = new System.Drawing.Point(4, 25);
         this.tpgModel.Name = "tpgModel";
         this.tpgModel.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgModel.Size = new System.Drawing.Size(1576, 786);
         this.tpgModel.TabIndex = 4;
         this.tpgModel.Tag = "model";
         this.tpgModel.Text = "模型管理";
         // 
         // qdrModelDesign
         // 
         this.qdrModelDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrModelDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrModelDesign.Name = "qdrModelDesign";
         this.qdrModelDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrModelDesign.TabIndex = 0;
         // 
         // tpgTemplate
         // 
         this.tpgTemplate.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgTemplate.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgTemplate.Controls.Add(this.qdrTemplateDesign);
         this.tpgTemplate.Location = new System.Drawing.Point(4, 25);
         this.tpgTemplate.Name = "tpgTemplate";
         this.tpgTemplate.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgTemplate.Size = new System.Drawing.Size(1576, 786);
         this.tpgTemplate.TabIndex = 5;
         this.tpgTemplate.Tag = "template";
         this.tpgTemplate.Text = "模板管理";
         // 
         // qdrTemplateDesign
         // 
         this.qdrTemplateDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrTemplateDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrTemplateDesign.Name = "qdrTemplateDesign";
         this.qdrTemplateDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrTemplateDesign.TabIndex = 0;
         // 
         // tpgTerrain
         // 
         this.tpgTerrain.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgTerrain.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgTerrain.Controls.Add(this.qdrTerrainDesign);
         this.tpgTerrain.Location = new System.Drawing.Point(4, 25);
         this.tpgTerrain.Name = "tpgTerrain";
         this.tpgTerrain.Padding = new System.Windows.Forms.Padding(3);
         this.tpgTerrain.Size = new System.Drawing.Size(1576, 786);
         this.tpgTerrain.TabIndex = 6;
         this.tpgTerrain.Tag = "tpgTerrain";
         this.tpgTerrain.Text = "地形管理";
         // 
         // qdrTerrainDesign
         // 
         this.qdrTerrainDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrTerrainDesign.Location = new System.Drawing.Point(3, 3);
         this.qdrTerrainDesign.Name = "qdrTerrainDesign";
         this.qdrTerrainDesign.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.qdrTerrainDesign.Size = new System.Drawing.Size(1566, 776);
         this.qdrTerrainDesign.TabIndex = 0;
         this.qdrTerrainDesign.Tag = "terrain";
         // 
         // tpgScene
         // 
         this.tpgScene.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgScene.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgScene.Controls.Add(this.qdrSceneDesign);
         this.tpgScene.Location = new System.Drawing.Point(4, 25);
         this.tpgScene.Name = "tpgScene";
         this.tpgScene.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgScene.Size = new System.Drawing.Size(1576, 786);
         this.tpgScene.TabIndex = 7;
         this.tpgScene.Tag = "scene";
         this.tpgScene.Text = "场景管理";
         // 
         // qdrSceneDesign
         // 
         this.qdrSceneDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrSceneDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrSceneDesign.Name = "qdrSceneDesign";
         this.qdrSceneDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrSceneDesign.TabIndex = 0;
         this.qdrSceneDesign.Tag = "scene";
         // 
         // tpgGroup
         // 
         this.tpgGroup.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgGroup.Controls.Add(this.qdrResourceGroupDesign);
         this.tpgGroup.Location = new System.Drawing.Point(4, 25);
         this.tpgGroup.Name = "tpgGroup";
         this.tpgGroup.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.tpgGroup.Size = new System.Drawing.Size(1576, 786);
         this.tpgGroup.TabIndex = 8;
         this.tpgGroup.Tag = "group";
         this.tpgGroup.Text = "资源分组";
         this.tpgGroup.UseVisualStyleBackColor = true;
         // 
         // qdrResourceGroupDesign
         // 
         this.qdrResourceGroupDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdrResourceGroupDesign.Location = new System.Drawing.Point(0, 3);
         this.qdrResourceGroupDesign.Name = "qdrResourceGroupDesign";
         this.qdrResourceGroupDesign.Size = new System.Drawing.Size(1572, 779);
         this.qdrResourceGroupDesign.TabIndex = 0;
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSaveAll,
            this.tsbSaveSelected,
            this.toolStripSeparator1,
            this.tspExportAll,
            this.tsbExportSelected,
            this.toolStripSeparator3,
            this.tsbExportResource,
            this.tsbPackAll,
            this.toolStripSeparator4,
            this.tsbWindowTask,
            this.tsbWindowTrack,
            this.toolStripSeparator2,
            this.tsbExit});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1584, 25);
         this.tspMain.TabIndex = 5;
         this.tspMain.Text = "toolStrip1";
         // 
         // tsbSaveAll
         // 
         this.tsbSaveAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveAll.Image")));
         this.tsbSaveAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveAll.Name = "tsbSaveAll";
         this.tsbSaveAll.Size = new System.Drawing.Size(76, 22);
         this.tsbSaveAll.Text = "全部保存";
         this.tsbSaveAll.Click += new System.EventHandler(this.tsbSaveAll_Click);
         // 
         // toolStripSeparator3
         // 
         this.toolStripSeparator3.Name = "toolStripSeparator3";
         this.toolStripSeparator3.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbExportResource
         // 
         this.tsbExportResource.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportResource.Image")));
         this.tsbExportResource.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportResource.Name = "tsbExportResource";
         this.tsbExportResource.Size = new System.Drawing.Size(76, 22);
         this.tsbExportResource.Text = "导出资源";
         this.tsbExportResource.Click += new System.EventHandler(this.tsbExportResource_Click);
         // 
         // tsbPackAll
         // 
         this.tsbPackAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbPackAll.Image")));
         this.tsbPackAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbPackAll.Name = "tsbPackAll";
         this.tsbPackAll.Size = new System.Drawing.Size(76, 22);
         this.tsbPackAll.Text = "打包资源";
         this.tsbPackAll.Click += new System.EventHandler(this.tsbPackAll_Click);
         // 
         // toolStripSeparator4
         // 
         this.toolStripSeparator4.Name = "toolStripSeparator4";
         this.toolStripSeparator4.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbWindowTask
         // 
         this.tsbWindowTask.Image = ((System.Drawing.Image)(resources.GetObject("tsbWindowTask.Image")));
         this.tsbWindowTask.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbWindowTask.Name = "tsbWindowTask";
         this.tsbWindowTask.Size = new System.Drawing.Size(76, 22);
         this.tsbWindowTask.Text = "任务窗口";
         this.tsbWindowTask.Click += new System.EventHandler(this.tsbWindowTask_Click);
         // 
         // tsbWindowTrack
         // 
         this.tsbWindowTrack.Image = ((System.Drawing.Image)(resources.GetObject("tsbWindowTrack.Image")));
         this.tsbWindowTrack.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbWindowTrack.Name = "tsbWindowTrack";
         this.tsbWindowTrack.Size = new System.Drawing.Size(76, 22);
         this.tsbWindowTrack.Text = "跟踪窗口";
         this.tsbWindowTrack.Click += new System.EventHandler(this.tsbWindowTrack_Click);
         // 
         // tsbExit
         // 
         this.tsbExit.Image = ((System.Drawing.Image)(resources.GetObject("tsbExit.Image")));
         this.tsbExit.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExit.Name = "tsbExit";
         this.tsbExit.Size = new System.Drawing.Size(52, 22);
         this.tsbExit.Text = "退出";
         this.tsbExit.Click += new System.EventHandler(this.tsbExit_Click);
         // 
         // sspMain
         // 
         this.sspMain.Location = new System.Drawing.Point(0, 840);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1584, 22);
         this.sspMain.TabIndex = 4;
         this.sspMain.Text = "statusStrip1";
         // 
         // QDsConsoleForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1584, 862);
         this.Controls.Add(this.tbcPages);
         this.Controls.Add(this.tspMain);
         this.Controls.Add(this.sspMain);
         this.Name = "QDsConsoleForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
         this.Text = "资源管理器";
         this.tpgTexture.ResumeLayout(false);
         this.tbcPages.ResumeLayout(false);
         this.tpgTheme.ResumeLayout(false);
         this.tpgMaterial.ResumeLayout(false);
         this.tpgModel.ResumeLayout(false);
         this.tpgTemplate.ResumeLayout(false);
         this.tpgTerrain.ResumeLayout(false);
         this.tpgScene.ResumeLayout(false);
         this.tpgGroup.ResumeLayout(false);
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.TabPage tpgTexture;
      private System.Windows.Forms.ToolStripButton tspExportAll;
      private System.Windows.Forms.ToolStripButton tsbExportSelected;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbSaveSelected;
      private System.Windows.Forms.TabControl tbcPages;
      private System.Windows.Forms.TabPage tpgMaterial;
      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tsbExit;
      private System.Windows.Forms.StatusStrip sspMain;
      private MO.Design3d.Texture.Controls.QDsTextureDesign qdrTextureDesign;
      private System.Windows.Forms.TabPage tpgModel;
      private System.Windows.Forms.TabPage tpgTemplate;
      private System.Windows.Forms.TabPage tpgTerrain;
      private System.Windows.Forms.TabPage tpgScene;
      private MO.Design3d.Material.Controls.QDsMaterialDesign qdrMaterialDesign;
      private MO.Design3d.Model.Controls.QDsModelDesign qdrModelDesign;
      private MO.Design3d.Template.Controls.QDsTemplateDesign qdrTemplateDesign;
      private MO.Design3d.Terrain.Controls.QDsTerrainDesign qdrTerrainDesign;
      private MO.Design3d.Scene.Controls.QDsSceneDesign qdrSceneDesign;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
      private System.Windows.Forms.ToolStripButton tsbWindowTask;
      private System.Windows.Forms.ToolStripButton tsbWindowTrack;
      private System.Windows.Forms.ToolStripButton tsbSaveAll;
      private System.Windows.Forms.TabPage tpgGroup;
      private MO.Design3d.ResourceGroup.Controls.QDsResourceGroupDesign qdrResourceGroupDesign;
      private System.Windows.Forms.ToolStripButton tsbPackAll;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
      private System.Windows.Forms.ToolStripButton tsbExportResource;
      private System.Windows.Forms.TabPage tpgTheme;
      private MO.Design3d.Theme.Controls.QDsThemeDesign qdrThemeDesign;
   }
}