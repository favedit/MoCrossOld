namespace MO.Design3d.Scene.Controls
{
   partial class QDsSceneProperty
   {
      /// <summary> 
      /// 必需的设计器变量。
      /// </summary>
      private System.ComponentModel.IContainer components = null;

      /// <summary> 
      /// 清理所有正在使用的资源。
      /// </summary>
      /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
      protected override void Dispose(bool disposing) {
         if (disposing && (components != null)) {
            components.Dispose();
         }
         base.Dispose(disposing);
      }

      #region 组件设计器生成的代码

      /// <summary> 
      /// 设计器支持所需的方法 - 不要
      /// 使用代码编辑器修改此方法的内容。
      /// </summary>
      private void InitializeComponent() {
         this.components = new System.ComponentModel.Container();
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.tsbAdd = new System.Windows.Forms.ToolStripButton();
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.qdrSceneRegionProperty = new MO.Design3d.Scene.Controls.QDsSceneRegionProperty();
         this.qdrSceneCameraProperty = new MO.Design3d.Scene.Controls.QDsSceneCameraProperty();
         this.qdrSceneEntityProperty = new MO.Design3d.Scene.Controls.QDsSceneEntityProperty();
         this.qdrSceneTerrainProperty = new MO.Design3d.Scene.Controls.QDsSceneTerrainProperty();
         this.qdrSceneLightProperty = new MO.Design3d.Scene.Controls.QDsSceneLightProperty();
         this.tspCatalog = new System.Windows.Forms.ToolStrip();
         this.tsbExpend = new System.Windows.Forms.ToolStripButton();
         this.tsbCollapse = new System.Windows.Forms.ToolStripButton();
         this.tvwCatalog = new System.Windows.Forms.TreeView();
         this.pnlProperty = new System.Windows.Forms.Panel();
         this.pnlCatalog = new System.Windows.Forms.Panel();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.tspToolSprit = new System.Windows.Forms.ToolStrip();
         this.tspSave = new System.Windows.Forms.ToolStripButton();
         this.tsbOpen = new System.Windows.Forms.ToolStripButton();
         this.tspLine1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbScalex1p4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScaleAuto = new System.Windows.Forms.ToolStripButton();
         this.tspLine2 = new System.Windows.Forms.ToolStripSeparator();
         this.tspImportConfig = new System.Windows.Forms.ToolStripButton();
         this.tspImportMaterial = new System.Windows.Forms.ToolStripButton();
         this.tspExport = new System.Windows.Forms.ToolStripButton();
         this.pnlInfo.SuspendLayout();
         this.tspCatalog.SuspendLayout();
         this.pnlProperty.SuspendLayout();
         this.pnlCatalog.SuspendLayout();
         this.tspToolSprit.SuspendLayout();
         this.SuspendLayout();
         // 
         // tsbAdd
         // 
         this.tsbAdd.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbAdd.Name = "tsbAdd";
         this.tsbAdd.Size = new System.Drawing.Size(36, 22);
         this.tsbAdd.Tag = "Txture";
         this.tsbAdd.Text = "添加";
         // 
         // pnlInfo
         // 
         this.pnlInfo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfo.Controls.Add(this.qdrSceneRegionProperty);
         this.pnlInfo.Controls.Add(this.qdrSceneCameraProperty);
         this.pnlInfo.Controls.Add(this.qdrSceneEntityProperty);
         this.pnlInfo.Controls.Add(this.qdrSceneTerrainProperty);
         this.pnlInfo.Controls.Add(this.qdrSceneLightProperty);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(0, 0);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(509, 503);
         this.pnlInfo.TabIndex = 1;
         // 
         // qdrSceneRegionProperty
         // 
         this.qdrSceneRegionProperty.Location = new System.Drawing.Point(11, 437);
         this.qdrSceneRegionProperty.Name = "qdrSceneRegionProperty";
         this.qdrSceneRegionProperty.Size = new System.Drawing.Size(418, 64);
         this.qdrSceneRegionProperty.TabIndex = 4;
         // 
         // qdrSceneCameraProperty
         // 
         this.qdrSceneCameraProperty.Location = new System.Drawing.Point(9, 3);
         this.qdrSceneCameraProperty.Name = "qdrSceneCameraProperty";
         this.qdrSceneCameraProperty.Size = new System.Drawing.Size(463, 79);
         this.qdrSceneCameraProperty.TabIndex = 3;
         // 
         // qdrSceneEntityProperty
         // 
         this.qdrSceneEntityProperty.Location = new System.Drawing.Point(51, 308);
         this.qdrSceneEntityProperty.Name = "qdrSceneEntityProperty";
         this.qdrSceneEntityProperty.Size = new System.Drawing.Size(331, 89);
         this.qdrSceneEntityProperty.TabIndex = 2;
         // 
         // qdrSceneTerrainProperty
         // 
         this.qdrSceneTerrainProperty.Location = new System.Drawing.Point(24, 179);
         this.qdrSceneTerrainProperty.Name = "qdrSceneTerrainProperty";
         this.qdrSceneTerrainProperty.Size = new System.Drawing.Size(405, 94);
         this.qdrSceneTerrainProperty.TabIndex = 1;
         // 
         // qdrSceneLightProperty
         // 
         this.qdrSceneLightProperty.Location = new System.Drawing.Point(9, 88);
         this.qdrSceneLightProperty.Name = "qdrSceneLightProperty";
         this.qdrSceneLightProperty.Size = new System.Drawing.Size(463, 69);
         this.qdrSceneLightProperty.TabIndex = 0;
         // 
         // tspCatalog
         // 
         this.tspCatalog.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbAdd,
            this.tsbExpend,
            this.tsbCollapse});
         this.tspCatalog.Location = new System.Drawing.Point(0, 0);
         this.tspCatalog.Name = "tspCatalog";
         this.tspCatalog.Size = new System.Drawing.Size(302, 25);
         this.tspCatalog.TabIndex = 5;
         this.tspCatalog.Text = "toolStrip1";
         // 
         // tsbExpend
         // 
         this.tsbExpend.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExpend.Name = "tsbExpend";
         this.tsbExpend.Size = new System.Drawing.Size(36, 22);
         this.tsbExpend.Tag = "Txture";
         this.tsbExpend.Text = "展开";
         // 
         // tsbCollapse
         // 
         this.tsbCollapse.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbCollapse.Name = "tsbCollapse";
         this.tsbCollapse.Size = new System.Drawing.Size(36, 22);
         this.tsbCollapse.Tag = "Txture";
         this.tsbCollapse.Text = "收起";
         // 
         // tvwCatalog
         // 
         this.tvwCatalog.BorderStyle = System.Windows.Forms.BorderStyle.None;
         this.tvwCatalog.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tvwCatalog.ForeColor = System.Drawing.SystemColors.InfoText;
         this.tvwCatalog.FullRowSelect = true;
         this.tvwCatalog.HideSelection = false;
         this.tvwCatalog.ImageKey = "Floder";
         this.tvwCatalog.ImageList = this.imlMain;
         this.tvwCatalog.Location = new System.Drawing.Point(0, 25);
         this.tvwCatalog.Name = "tvwCatalog";
         this.tvwCatalog.PathSeparator = "-";
         this.tvwCatalog.SelectedImageKey = "FloderOpen";
         this.tvwCatalog.Size = new System.Drawing.Size(302, 474);
         this.tvwCatalog.TabIndex = 3;
         this.tvwCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvwCatalog_AfterSelect);
         // 
         // pnlProperty
         // 
         this.pnlProperty.Controls.Add(this.pnlInfo);
         this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlProperty.Location = new System.Drawing.Point(310, 28);
         this.pnlProperty.Name = "pnlProperty";
         this.pnlProperty.Size = new System.Drawing.Size(509, 503);
         this.pnlProperty.TabIndex = 11;
         // 
         // pnlCatalog
         // 
         this.pnlCatalog.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlCatalog.Controls.Add(this.tvwCatalog);
         this.pnlCatalog.Controls.Add(this.tspCatalog);
         this.pnlCatalog.Dock = System.Windows.Forms.DockStyle.Left;
         this.pnlCatalog.Location = new System.Drawing.Point(0, 28);
         this.pnlCatalog.Name = "pnlCatalog";
         this.pnlCatalog.Size = new System.Drawing.Size(306, 503);
         this.pnlCatalog.TabIndex = 2;
         // 
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(306, 28);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 503);
         this.splitter1.TabIndex = 10;
         this.splitter1.TabStop = false;
         // 
         // tspToolSprit
         // 
         this.tspToolSprit.AutoSize = false;
         this.tspToolSprit.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tspSave,
            this.tsbOpen,
            this.tspLine1,
            this.tsbScalex1p4,
            this.tsbScalex1p2,
            this.tsbScalex1,
            this.tsbScalex2,
            this.tsbScalex4,
            this.tsbScaleAuto,
            this.tspLine2,
            this.tspImportConfig,
            this.tspImportMaterial,
            this.tspExport});
         this.tspToolSprit.Location = new System.Drawing.Point(0, 3);
         this.tspToolSprit.Name = "tspToolSprit";
         this.tspToolSprit.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspToolSprit.Size = new System.Drawing.Size(819, 25);
         this.tspToolSprit.TabIndex = 12;
         this.tspToolSprit.Text = "toolStrip1";
         // 
         // tspSave
         // 
         this.tspSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspSave.Name = "tspSave";
         this.tspSave.Size = new System.Drawing.Size(36, 22);
         this.tspSave.Text = "保存";
         this.tspSave.Click += new System.EventHandler(this.tspSave_Click);
         // 
         // tsbOpen
         // 
         this.tsbOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbOpen.Name = "tsbOpen";
         this.tsbOpen.Size = new System.Drawing.Size(36, 22);
         this.tsbOpen.Text = "打开";
         this.tsbOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // tspLine1
         // 
         this.tspLine1.Name = "tspLine1";
         this.tspLine1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbScalex1p4
         // 
         this.tsbScalex1p4.BackColor = System.Drawing.SystemColors.Control;
         this.tsbScalex1p4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p4.Name = "tsbScalex1p4";
         this.tsbScalex1p4.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p4.Tag = "0.25";
         this.tsbScalex1p4.Text = "x1/4";
         // 
         // tsbScalex1p2
         // 
         this.tsbScalex1p2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p2.Name = "tsbScalex1p2";
         this.tsbScalex1p2.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p2.Tag = "0.5";
         this.tsbScalex1p2.Text = "x1/2";
         // 
         // tsbScalex1
         // 
         this.tsbScalex1.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1.Name = "tsbScalex1";
         this.tsbScalex1.Size = new System.Drawing.Size(36, 22);
         this.tsbScalex1.Tag = "1";
         this.tsbScalex1.Text = "原始";
         // 
         // tsbScalex2
         // 
         this.tsbScalex2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex2.Name = "tsbScalex2";
         this.tsbScalex2.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex2.Tag = "2";
         this.tsbScalex2.Text = "x2";
         // 
         // tsbScalex4
         // 
         this.tsbScalex4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex4.Name = "tsbScalex4";
         this.tsbScalex4.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex4.Tag = "4";
         this.tsbScalex4.Text = "x4";
         // 
         // tsbScaleAuto
         // 
         this.tsbScaleAuto.Checked = true;
         this.tsbScaleAuto.CheckState = System.Windows.Forms.CheckState.Checked;
         this.tsbScaleAuto.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScaleAuto.Name = "tsbScaleAuto";
         this.tsbScaleAuto.Size = new System.Drawing.Size(36, 22);
         this.tsbScaleAuto.Tag = "Auto";
         this.tsbScaleAuto.Text = "自动";
         // 
         // tspLine2
         // 
         this.tspLine2.Name = "tspLine2";
         this.tspLine2.Size = new System.Drawing.Size(6, 25);
         // 
         // tspImportConfig
         // 
         this.tspImportConfig.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspImportConfig.Name = "tspImportConfig";
         this.tspImportConfig.Size = new System.Drawing.Size(60, 22);
         this.tspImportConfig.Text = "导入配置";
         this.tspImportConfig.Click += new System.EventHandler(this.tspImport_Click);
         // 
         // tspImportMaterial
         // 
         this.tspImportMaterial.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspImportMaterial.Name = "tspImportMaterial";
         this.tspImportMaterial.Size = new System.Drawing.Size(60, 22);
         this.tspImportMaterial.Text = "导入材质";
         this.tspImportMaterial.Click += new System.EventHandler(this.tspImportMaterial_Click);
         // 
         // tspExport
         // 
         this.tspExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExport.Name = "tspExport";
         this.tspExport.Size = new System.Drawing.Size(36, 22);
         this.tspExport.Text = "导出";
         this.tspExport.Click += new System.EventHandler(this.tspExport_Click);
         // 
         // QDsSceneProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlProperty);
         this.Controls.Add(this.splitter1);
         this.Controls.Add(this.pnlCatalog);
         this.Controls.Add(this.tspToolSprit);
         this.Name = "QDsSceneProperty";
         this.Padding = new System.Windows.Forms.Padding(0, 3, 0, 0);
         this.Size = new System.Drawing.Size(819, 531);
         this.pnlInfo.ResumeLayout(false);
         this.tspCatalog.ResumeLayout(false);
         this.tspCatalog.PerformLayout();
         this.pnlProperty.ResumeLayout(false);
         this.pnlCatalog.ResumeLayout(false);
         this.pnlCatalog.PerformLayout();
         this.tspToolSprit.ResumeLayout(false);
         this.tspToolSprit.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ImageList imlMain;
      private System.Windows.Forms.ToolStripButton tsbAdd;
      private System.Windows.Forms.Panel pnlInfo;
      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.TreeView tvwCatalog;
      private System.Windows.Forms.ToolStrip tspCatalog;
      private System.Windows.Forms.ToolStripButton tsbExpend;
      private System.Windows.Forms.ToolStripButton tsbCollapse;
      private System.Windows.Forms.Splitter splitter1;
      private MO.Design3d.Scene.Controls.QDsSceneEntityProperty qdrSceneEntityProperty;
      private MO.Design3d.Scene.Controls.QDsSceneTerrainProperty qdrSceneTerrainProperty;
      private MO.Design3d.Scene.Controls.QDsSceneLightProperty qdrSceneLightProperty;
      private MO.Design3d.Scene.Controls.QDsSceneCameraProperty qdrSceneCameraProperty;
      private System.Windows.Forms.Panel pnlCatalog;
      public System.Windows.Forms.ToolStrip tspToolSprit;
      private System.Windows.Forms.ToolStripButton tspSave;
      private System.Windows.Forms.ToolStripButton tsbOpen;
      private System.Windows.Forms.ToolStripSeparator tspLine1;
      private System.Windows.Forms.ToolStripButton tsbScalex1p4;
      private System.Windows.Forms.ToolStripButton tsbScalex1p2;
      private System.Windows.Forms.ToolStripButton tsbScalex1;
      private System.Windows.Forms.ToolStripButton tsbScalex2;
      private System.Windows.Forms.ToolStripButton tsbScalex4;
      private System.Windows.Forms.ToolStripButton tsbScaleAuto;
      private System.Windows.Forms.ToolStripSeparator tspLine2;
      private System.Windows.Forms.ToolStripButton tspExport;
      private MO.Design3d.Scene.Controls.QDsSceneRegionProperty qdrSceneRegionProperty;
      private System.Windows.Forms.ToolStripButton tspImportConfig;
      private System.Windows.Forms.ToolStripButton tspImportMaterial;
   }
}
