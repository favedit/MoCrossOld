namespace MO.Design2d.Resource.Forms
{
   partial class QDsResourceConsoleForm
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
         if(disposing && (components != null)) {
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsResourceConsoleForm));
         this.tbcPages = new System.Windows.Forms.TabControl();
         this.tpgResources = new System.Windows.Forms.TabPage();
         this.qdsResourceDesign = new MO.Design2d.Resource.Controls.QDsResourceDesign();
         this.tpgGroups = new System.Windows.Forms.TabPage();
         this.qdsResourceGroupDesign = new MO.Design2d.Resource.Controls.QDsResourceGroupDesign();
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbSaveAll = new System.Windows.Forms.ToolStripButton();
         this.tsbSaveSelected = new System.Windows.Forms.ToolStripButton();
         this.tsbSaveSha = new System.Windows.Forms.ToolStripButton();
         this.tspLine1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExportIconAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExportUiAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExportPictureAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExportSelected = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExportChangedAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExportAll = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbWindowTask = new System.Windows.Forms.ToolStripButton();
         this.tsbWindowTrack = new System.Windows.Forms.ToolStripButton();
         this.tspLine3 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExit = new System.Windows.Forms.ToolStripButton();
         this.tbcPages.SuspendLayout();
         this.tpgResources.SuspendLayout();
         this.tpgGroups.SuspendLayout();
         this.tspMain.SuspendLayout();
         this.SuspendLayout();
         // 
         // tbcPages
         // 
         this.tbcPages.Controls.Add(this.tpgResources);
         this.tbcPages.Controls.Add(this.tpgGroups);
         this.tbcPages.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tbcPages.Location = new System.Drawing.Point(0, 25);
         this.tbcPages.Name = "tbcPages";
         this.tbcPages.SelectedIndex = 0;
         this.tbcPages.Size = new System.Drawing.Size(1184, 715);
         this.tbcPages.TabIndex = 0;
         // 
         // tpgResources
         // 
         this.tpgResources.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgResources.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgResources.Controls.Add(this.qdsResourceDesign);
         this.tpgResources.Location = new System.Drawing.Point(4, 22);
         this.tpgResources.Name = "tpgResources";
         this.tpgResources.Padding = new System.Windows.Forms.Padding(3);
         this.tpgResources.Size = new System.Drawing.Size(1176, 689);
         this.tpgResources.TabIndex = 0;
         this.tpgResources.Tag = "resource";
         this.tpgResources.Text = "资源管理";
         // 
         // qdsResourceDesign
         // 
         this.qdsResourceDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdsResourceDesign.Location = new System.Drawing.Point(3, 3);
         this.qdsResourceDesign.Name = "qdsResourceDesign";
         this.qdsResourceDesign.Size = new System.Drawing.Size(1166, 679);
         this.qdsResourceDesign.TabIndex = 0;
         // 
         // tpgGroups
         // 
         this.tpgGroups.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgGroups.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgGroups.Controls.Add(this.qdsResourceGroupDesign);
         this.tpgGroups.Location = new System.Drawing.Point(4, 22);
         this.tpgGroups.Name = "tpgGroups";
         this.tpgGroups.Padding = new System.Windows.Forms.Padding(3);
         this.tpgGroups.Size = new System.Drawing.Size(1176, 689);
         this.tpgGroups.TabIndex = 3;
         this.tpgGroups.Tag = "group";
         this.tpgGroups.Text = "资源组管理";
         // 
         // qdsResourceGroupDesign
         // 
         this.qdsResourceGroupDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdsResourceGroupDesign.Location = new System.Drawing.Point(3, 3);
         this.qdsResourceGroupDesign.Name = "qdsResourceGroupDesign";
         this.qdsResourceGroupDesign.Size = new System.Drawing.Size(1166, 679);
         this.qdsResourceGroupDesign.TabIndex = 0;
         // 
         // sspMain
         // 
         this.sspMain.Location = new System.Drawing.Point(0, 740);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1184, 22);
         this.sspMain.TabIndex = 1;
         this.sspMain.Text = "statusStrip1";
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSaveAll,
            this.tsbSaveSelected,
            this.tsbSaveSha,
            this.tspLine1,
            this.tsbExportIconAll,
            this.tsbExportUiAll,
            this.tsbExportPictureAll,
            this.tsbExportSelected,
            this.toolStripSeparator2,
            this.tsbExportChangedAll,
            this.tsbExportAll,
            this.toolStripSeparator1,
            this.tsbWindowTask,
            this.tsbWindowTrack,
            this.tspLine3,
            this.tsbExit});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1184, 25);
         this.tspMain.TabIndex = 2;
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
         // tsbSaveSelected
         // 
         this.tsbSaveSelected.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveSelected.Image")));
         this.tsbSaveSelected.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveSelected.Name = "tsbSaveSelected";
         this.tsbSaveSelected.Size = new System.Drawing.Size(76, 22);
         this.tsbSaveSelected.Text = "选中保存";
         this.tsbSaveSelected.Click += new System.EventHandler(this.tsbSaveSelected_Click);
         // 
         // tsbSaveSha
         // 
         this.tsbSaveSha.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveSha.Image")));
         this.tsbSaveSha.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveSha.Name = "tsbSaveSha";
         this.tsbSaveSha.Size = new System.Drawing.Size(73, 22);
         this.tsbSaveSha.Text = "保存Sha";
         this.tsbSaveSha.Click += new System.EventHandler(this.tsbSaveSha_Click);
         // 
         // tspLine1
         // 
         this.tspLine1.Name = "tspLine1";
         this.tspLine1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbExportIconAll
         // 
         this.tsbExportIconAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportIconAll.Image")));
         this.tsbExportIconAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportIconAll.Name = "tsbExportIconAll";
         this.tsbExportIconAll.Size = new System.Drawing.Size(100, 22);
         this.tsbExportIconAll.Text = "图标资源导出";
         this.tsbExportIconAll.ToolTipText = "全部导出";
         this.tsbExportIconAll.Click += new System.EventHandler(this.tsbExportIconAll_Click);
         // 
         // tsbExportUiAll
         // 
         this.tsbExportUiAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportUiAll.Image")));
         this.tsbExportUiAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportUiAll.Name = "tsbExportUiAll";
         this.tsbExportUiAll.Size = new System.Drawing.Size(100, 22);
         this.tsbExportUiAll.Text = "界面资源导出";
         this.tsbExportUiAll.ToolTipText = "全部导出";
         this.tsbExportUiAll.Click += new System.EventHandler(this.tsbExportUiAll_Click);
         // 
         // tsbExportPictureAll
         // 
         this.tsbExportPictureAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportPictureAll.Image")));
         this.tsbExportPictureAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportPictureAll.Name = "tsbExportPictureAll";
         this.tsbExportPictureAll.Size = new System.Drawing.Size(100, 22);
         this.tsbExportPictureAll.Text = "图片资源导出";
         this.tsbExportPictureAll.ToolTipText = "全部导出";
         this.tsbExportPictureAll.Click += new System.EventHandler(this.tsbExportPictureAll_Click);
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
         // tsbExportChangedAll
         // 
         this.tsbExportChangedAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportChangedAll.Image")));
         this.tsbExportChangedAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportChangedAll.Name = "tsbExportChangedAll";
         this.tsbExportChangedAll.Size = new System.Drawing.Size(100, 22);
         this.tsbExportChangedAll.Text = "变更资源导出";
         this.tsbExportChangedAll.ToolTipText = "变更资源导出";
         this.tsbExportChangedAll.Click += new System.EventHandler(this.tsbExportChangedAll_Click);
         // 
         // tsbExportAll
         // 
         this.tsbExportAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportAll.Image")));
         this.tsbExportAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportAll.Name = "tsbExportAll";
         this.tsbExportAll.Size = new System.Drawing.Size(100, 22);
         this.tsbExportAll.Text = "全部资源导出";
         this.tsbExportAll.ToolTipText = "全部资源导出";
         this.tsbExportAll.Click += new System.EventHandler(this.tsbExportAll_Click);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
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
         // tspLine3
         // 
         this.tspLine3.Name = "tspLine3";
         this.tspLine3.Size = new System.Drawing.Size(6, 25);
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
         // QDsResourceConsoleForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1184, 762);
         this.Controls.Add(this.tbcPages);
         this.Controls.Add(this.sspMain);
         this.Controls.Add(this.tspMain);
         this.Name = "QDsResourceConsoleForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
         this.Text = "资源管理器";
         this.tbcPages.ResumeLayout(false);
         this.tpgResources.ResumeLayout(false);
         this.tpgGroups.ResumeLayout(false);
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.TabControl tbcPages;
      private System.Windows.Forms.TabPage tpgResources;
      private System.Windows.Forms.StatusStrip sspMain;
      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tsbSaveSelected;
      private System.Windows.Forms.ToolStripSeparator tspLine1;
      private System.Windows.Forms.ToolStripButton tsbExportAll;
      private System.Windows.Forms.ToolStripButton tsbExit;
      private System.Windows.Forms.TabPage tpgGroups;
      private System.Windows.Forms.ToolStripButton tsbExportSelected;
      private System.Windows.Forms.ToolStripButton tsbSaveAll;
      private System.Windows.Forms.ToolStripButton tsbWindowTask;
      private System.Windows.Forms.ToolStripButton tsbWindowTrack;
      private System.Windows.Forms.ToolStripSeparator tspLine3;
      private System.Windows.Forms.ToolStripButton tsbSaveSha;
      private MO.Design2d.Resource.Controls.QDsResourceDesign qdsResourceDesign;
      private MO.Design2d.Resource.Controls.QDsResourceGroupDesign qdsResourceGroupDesign;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbExportUiAll;
      private System.Windows.Forms.ToolStripButton tsbExportIconAll;
      private System.Windows.Forms.ToolStripButton tsbExportPictureAll;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbExportChangedAll;
   }
}