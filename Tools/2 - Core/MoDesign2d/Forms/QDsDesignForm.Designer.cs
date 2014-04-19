namespace MO.Design2d.Forms
{
   partial class QDsDesignForm
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsDesignForm));
         this.tbcPages = new System.Windows.Forms.TabControl();
         this.tpgResources = new System.Windows.Forms.TabPage();
         this.qdsResourceDesign = new MO.Design2d.Resource.Controls.QDsResourceDesign();
         this.tpgGroups = new System.Windows.Forms.TabPage();
         this.qdsResourceGroupDesign = new MO.Design2d.Resource.Controls.QDsResourceGroupDesign();
         this.tpgMap = new System.Windows.Forms.TabPage();
         this.tpgUi = new System.Windows.Forms.TabPage();
         this.tpgSence = new System.Windows.Forms.TabPage();
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbSaveAll = new System.Windows.Forms.ToolStripButton();
         this.tsbSaveSelected = new System.Windows.Forms.ToolStripButton();
         this.tspLine1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExportAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExportSelected = new System.Windows.Forms.ToolStripButton();
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
         this.tbcPages.Controls.Add(this.tpgMap);
         this.tbcPages.Controls.Add(this.tpgUi);
         this.tbcPages.Controls.Add(this.tpgSence);
         this.tbcPages.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tbcPages.Location = new System.Drawing.Point(0, 25);
         this.tbcPages.Name = "tbcPages";
         this.tbcPages.SelectedIndex = 0;
         this.tbcPages.Size = new System.Drawing.Size(1384, 715);
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
         this.tpgResources.Size = new System.Drawing.Size(1376, 689);
         this.tpgResources.TabIndex = 0;
         this.tpgResources.Tag = "resource";
         this.tpgResources.Text = "资源管理";
         // 
         // qdsResourceDesign
         // 
         this.qdsResourceDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdsResourceDesign.Location = new System.Drawing.Point(3, 3);
         this.qdsResourceDesign.Name = "qdsResourceDesign";
         this.qdsResourceDesign.Size = new System.Drawing.Size(1366, 679);
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
         this.tpgGroups.Size = new System.Drawing.Size(1376, 689);
         this.tpgGroups.TabIndex = 3;
         this.tpgGroups.Tag = "group";
         this.tpgGroups.Text = "资源组管理";
         // 
         // qdsResourceGroupDesign
         // 
         this.qdsResourceGroupDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdsResourceGroupDesign.Location = new System.Drawing.Point(3, 3);
         this.qdsResourceGroupDesign.Name = "qdsResourceGroupDesign";
         this.qdsResourceGroupDesign.Size = new System.Drawing.Size(1366, 679);
         this.qdsResourceGroupDesign.TabIndex = 0;
         // 
         // tpgMap
         // 
         this.tpgMap.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgMap.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgMap.Location = new System.Drawing.Point(4, 22);
         this.tpgMap.Name = "tpgMap";
         this.tpgMap.Padding = new System.Windows.Forms.Padding(3);
         this.tpgMap.Size = new System.Drawing.Size(1376, 689);
         this.tpgMap.TabIndex = 1;
         this.tpgMap.Tag = "map";
         this.tpgMap.Text = "地图管理";
         // 
         // tpgUi
         // 
         this.tpgUi.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgUi.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgUi.Location = new System.Drawing.Point(4, 22);
         this.tpgUi.Name = "tpgUi";
         this.tpgUi.Padding = new System.Windows.Forms.Padding(3);
         this.tpgUi.Size = new System.Drawing.Size(1376, 689);
         this.tpgUi.TabIndex = 2;
         this.tpgUi.Tag = "Ui";
         this.tpgUi.Text = "界面管理";
         // 
         // tpgSence
         // 
         this.tpgSence.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.tpgSence.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.tpgSence.Location = new System.Drawing.Point(4, 22);
         this.tpgSence.Name = "tpgSence";
         this.tpgSence.Padding = new System.Windows.Forms.Padding(3);
         this.tpgSence.Size = new System.Drawing.Size(1376, 689);
         this.tpgSence.TabIndex = 4;
         this.tpgSence.Tag = "sence";
         this.tpgSence.Text = " 场景管理";
         // 
         // sspMain
         // 
         this.sspMain.Location = new System.Drawing.Point(0, 740);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1384, 22);
         this.sspMain.TabIndex = 1;
         this.sspMain.Text = "statusStrip1";
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSaveAll,
            this.tsbSaveSelected,
            this.tspLine1,
            this.tsbExportAll,
            this.tsbExportSelected,
            this.toolStripSeparator1,
            this.tsbWindowTask,
            this.tsbWindowTrack,
            this.tspLine3,
            this.tsbExit});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1384, 25);
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
         // tspLine1
         // 
         this.tspLine1.Name = "tspLine1";
         this.tspLine1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbExportAll
         // 
         this.tsbExportAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportAll.Image")));
         this.tsbExportAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportAll.Name = "tsbExportAll";
         this.tsbExportAll.Size = new System.Drawing.Size(76, 22);
         this.tsbExportAll.Text = "全部导出";
         this.tsbExportAll.ToolTipText = "全部导出";
         this.tsbExportAll.Click += new System.EventHandler(this.tsbExportAll_Click);
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
         // QDsDesignForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1384, 762);
         this.Controls.Add(this.tbcPages);
         this.Controls.Add(this.sspMain);
         this.Controls.Add(this.tspMain);
         this.Name = "QDsDesignForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
         this.Text = "设计器";
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
      private System.Windows.Forms.TabPage tpgMap;
      private System.Windows.Forms.StatusStrip sspMain;
      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tsbSaveSelected;
      private System.Windows.Forms.ToolStripSeparator tspLine1;
      private System.Windows.Forms.ToolStripButton tsbExportAll;
      private System.Windows.Forms.ToolStripButton tsbExit;
      private System.Windows.Forms.TabPage tpgUi;
      private System.Windows.Forms.TabPage tpgGroups;
      private System.Windows.Forms.ToolStripButton tsbExportSelected;
      private System.Windows.Forms.ToolStripButton tsbSaveAll;
      private System.Windows.Forms.ToolStripButton tsbWindowTask;
      private System.Windows.Forms.ToolStripButton tsbWindowTrack;
      private System.Windows.Forms.ToolStripSeparator tspLine3;
      private System.Windows.Forms.TabPage tpgSence;
      private MO.Design2d.Resource.Controls.QDsResourceDesign qdsResourceDesign;
      private MO.Design2d.Resource.Controls.QDsResourceGroupDesign qdsResourceGroupDesign;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
   }
}