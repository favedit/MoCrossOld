namespace MO.Design3d.Template.Controls
{
   partial class QDsTemplateProperty
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
         if(disposing && (components != null)) {
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsTemplateProperty));
         this.imlNode = new System.Windows.Forms.ImageList(this.components);
         this.panel2 = new System.Windows.Forms.Panel();
         this.tspToolSprit = new System.Windows.Forms.ToolStrip();
         this.tsbSave = new System.Windows.Forms.ToolStripButton();
         this.tsbOpen = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbView = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbImport = new System.Windows.Forms.ToolStripButton();
         this.tspExportLight = new System.Windows.Forms.ToolStripButton();
         this.tspExportLightResource = new System.Windows.Forms.ToolStripButton();
         this.tspExport = new System.Windows.Forms.ToolStripButton();
         this.pnlBody = new System.Windows.Forms.Panel();
         this.pnlProperty = new System.Windows.Forms.Panel();
         this.panel4 = new System.Windows.Forms.Panel();
         this.qdrTemplateMovieProperty = new MO.Design3d.Template.Controls.QDsTemplateMovieProperty();
         this.qdrMaterialProperty = new MO.Design3d.Material.Controls.QDsMaterialProperty();
         this.qdrTemplateRenderableProperty = new MO.Design3d.Template.Controls.QDsTemplateRenderableProperty();
         this.palTempalte = new System.Windows.Forms.Panel();
         this.qgvGeometries = new MO.Core.Forms.Controls.QGridView();
         this.chdRenderableModel = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.chdRenderableMesh = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.chdRenderableMaterial = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.chdRenderableOptionSelect = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.chdRenderableOptionVisible = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.tvwCatalog = new MO.Core.Forms.Controls.QGmConfigTreeView(this.components);
         this.labOptionGround = new System.Windows.Forms.Panel();
         this.qdrOptionGround = new MO.Design3d.Controls.QDsFlag();
         this.label1 = new System.Windows.Forms.Label();
         this.labOptionMergeMaterial = new System.Windows.Forms.Label();
         this.labOptionMergeVertex = new System.Windows.Forms.Label();
         this.qdrOptionMergeMaterial = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionMergeVertex = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionSelect = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionLightMap = new MO.Design3d.Controls.QDsFlag();
         this.qdrOptionLoad = new MO.Design3d.Controls.QDsFlag();
         this.txtGeometryInfo = new System.Windows.Forms.TextBox();
         this.txtLabel = new System.Windows.Forms.TextBox();
         this.labGeometryInfo = new System.Windows.Forms.Label();
         this.labLabel = new System.Windows.Forms.Label();
         this.labOptionSelect = new System.Windows.Forms.Label();
         this.labOptionLightMap = new System.Windows.Forms.Label();
         this.txtName = new System.Windows.Forms.TextBox();
         this.labOptionLoad = new System.Windows.Forms.Label();
         this.labName = new System.Windows.Forms.Label();
         this.panel2.SuspendLayout();
         this.tspToolSprit.SuspendLayout();
         this.pnlBody.SuspendLayout();
         this.pnlProperty.SuspendLayout();
         this.panel4.SuspendLayout();
         this.palTempalte.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.qgvGeometries)).BeginInit();
         this.labOptionGround.SuspendLayout();
         this.SuspendLayout();
         // 
         // imlNode
         // 
         this.imlNode.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlNode.ImageStream")));
         this.imlNode.TransparentColor = System.Drawing.Color.Transparent;
         this.imlNode.Images.SetKeyName(0, "Floder");
         this.imlNode.Images.SetKeyName(1, "FloderOpen");
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.tspToolSprit);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel2.Location = new System.Drawing.Point(0, 0);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(987, 28);
         this.panel2.TabIndex = 16;
         // 
         // tspToolSprit
         // 
         this.tspToolSprit.AutoSize = false;
         this.tspToolSprit.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tspToolSprit.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSave,
            this.tsbOpen,
            this.toolStripSeparator1,
            this.tsbView,
            this.toolStripSeparator2,
            this.tsbImport,
            this.tspExportLight,
            this.tspExportLightResource,
            this.tspExport});
         this.tspToolSprit.Location = new System.Drawing.Point(0, 0);
         this.tspToolSprit.Name = "tspToolSprit";
         this.tspToolSprit.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspToolSprit.Size = new System.Drawing.Size(987, 28);
         this.tspToolSprit.TabIndex = 13;
         this.tspToolSprit.Text = "toolStrip1";
         // 
         // tsbSave
         // 
         this.tsbSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSave.Name = "tsbSave";
         this.tsbSave.Size = new System.Drawing.Size(36, 25);
         this.tsbSave.Text = "保存";
         this.tsbSave.Click += new System.EventHandler(this.tsbSave_Click);
         // 
         // tsbOpen
         // 
         this.tsbOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbOpen.Name = "tsbOpen";
         this.tsbOpen.Size = new System.Drawing.Size(36, 25);
         this.tsbOpen.Text = "打开";
         this.tsbOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 28);
         // 
         // tsbView
         // 
         this.tsbView.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbView.Name = "tsbView";
         this.tsbView.Size = new System.Drawing.Size(36, 25);
         this.tsbView.Text = "查看";
         this.tsbView.Click += new System.EventHandler(this.tsbView_Click);
         // 
         // toolStripSeparator2
         // 
         this.toolStripSeparator2.Name = "toolStripSeparator2";
         this.toolStripSeparator2.Size = new System.Drawing.Size(6, 28);
         // 
         // tsbImport
         // 
         this.tsbImport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbImport.Name = "tsbImport";
         this.tsbImport.Size = new System.Drawing.Size(60, 25);
         this.tsbImport.Text = "导入位置";
         this.tsbImport.Click += new System.EventHandler(this.tsbImport_Click);
         // 
         // tspExportLight
         // 
         this.tspExportLight.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExportLight.Name = "tspExportLight";
         this.tspExportLight.Size = new System.Drawing.Size(60, 25);
         this.tspExportLight.Text = "导出光照";
         this.tspExportLight.Click += new System.EventHandler(this.tspExportLight_Click);
         // 
         // tspExportLightResource
         // 
         this.tspExportLightResource.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExportLightResource.Name = "tspExportLightResource";
         this.tspExportLightResource.Size = new System.Drawing.Size(84, 25);
         this.tspExportLightResource.Text = "导出光照资源";
         this.tspExportLightResource.Click += new System.EventHandler(this.tspExportLightResource_Click);
         // 
         // tspExport
         // 
         this.tspExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExport.Name = "tspExport";
         this.tspExport.Size = new System.Drawing.Size(60, 25);
         this.tspExport.Text = "导出资源";
         this.tspExport.Click += new System.EventHandler(this.tsbExport_Click);
         // 
         // pnlBody
         // 
         this.pnlBody.Controls.Add(this.pnlProperty);
         this.pnlBody.Controls.Add(this.labOptionGround);
         this.pnlBody.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlBody.Location = new System.Drawing.Point(0, 28);
         this.pnlBody.Name = "pnlBody";
         this.pnlBody.Size = new System.Drawing.Size(987, 611);
         this.pnlBody.TabIndex = 17;
         // 
         // pnlProperty
         // 
         this.pnlProperty.Controls.Add(this.panel4);
         this.pnlProperty.Controls.Add(this.splitter1);
         this.pnlProperty.Controls.Add(this.tvwCatalog);
         this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlProperty.Location = new System.Drawing.Point(0, 96);
         this.pnlProperty.Name = "pnlProperty";
         this.pnlProperty.Size = new System.Drawing.Size(987, 515);
         this.pnlProperty.TabIndex = 15;
         // 
         // panel4
         // 
         this.panel4.Controls.Add(this.qdrTemplateMovieProperty);
         this.panel4.Controls.Add(this.qdrMaterialProperty);
         this.panel4.Controls.Add(this.qdrTemplateRenderableProperty);
         this.panel4.Controls.Add(this.palTempalte);
         this.panel4.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel4.Location = new System.Drawing.Point(331, 0);
         this.panel4.Name = "panel4";
         this.panel4.Size = new System.Drawing.Size(656, 515);
         this.panel4.TabIndex = 15;
         // 
         // qdrTemplateMovieProperty
         // 
         this.qdrTemplateMovieProperty.Location = new System.Drawing.Point(6, 324);
         this.qdrTemplateMovieProperty.Name = "qdrTemplateMovieProperty";
         this.qdrTemplateMovieProperty.Size = new System.Drawing.Size(600, 100);
         this.qdrTemplateMovieProperty.TabIndex = 17;
         // 
         // qdrMaterialProperty
         // 
         this.qdrMaterialProperty.Location = new System.Drawing.Point(6, 6);
         this.qdrMaterialProperty.Name = "qdrMaterialProperty";
         this.qdrMaterialProperty.Size = new System.Drawing.Size(600, 100);
         this.qdrMaterialProperty.TabIndex = 16;
         // 
         // qdrTemplateRenderableProperty
         // 
         this.qdrTemplateRenderableProperty.Location = new System.Drawing.Point(6, 112);
         this.qdrTemplateRenderableProperty.Name = "qdrTemplateRenderableProperty";
         this.qdrTemplateRenderableProperty.Size = new System.Drawing.Size(600, 100);
         this.qdrTemplateRenderableProperty.TabIndex = 15;
         // 
         // palTempalte
         // 
         this.palTempalte.Controls.Add(this.qgvGeometries);
         this.palTempalte.Location = new System.Drawing.Point(6, 218);
         this.palTempalte.Name = "palTempalte";
         this.palTempalte.Size = new System.Drawing.Size(600, 100);
         this.palTempalte.TabIndex = 14;
         // 
         // qgvGeometries
         // 
         this.qgvGeometries.AllowDrop = true;
         this.qgvGeometries.AllowUserToAddRows = false;
         this.qgvGeometries.AllowUserToDeleteRows = false;
         this.qgvGeometries.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCellsExceptHeaders;
         this.qgvGeometries.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
         this.qgvGeometries.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.chdRenderableModel,
            this.chdRenderableMesh,
            this.chdRenderableMaterial,
            this.chdRenderableOptionSelect,
            this.chdRenderableOptionVisible});
         this.qgvGeometries.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qgvGeometries.Location = new System.Drawing.Point(0, 0);
         this.qgvGeometries.Name = "qgvGeometries";
         this.qgvGeometries.RowTemplate.Height = 18;
         this.qgvGeometries.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
         this.qgvGeometries.Size = new System.Drawing.Size(600, 100);
         this.qgvGeometries.TabIndex = 0;
         // 
         // chdRenderableModel
         // 
         this.chdRenderableModel.HeaderText = "模型";
         this.chdRenderableModel.Name = "chdRenderableModel";
         this.chdRenderableModel.Width = 200;
         // 
         // chdRenderableMesh
         // 
         this.chdRenderableMesh.HeaderText = "网格";
         this.chdRenderableMesh.Name = "chdRenderableMesh";
         this.chdRenderableMesh.Width = 120;
         // 
         // chdRenderableMaterial
         // 
         this.chdRenderableMaterial.HeaderText = "材质";
         this.chdRenderableMaterial.Name = "chdRenderableMaterial";
         this.chdRenderableMaterial.Width = 200;
         // 
         // chdRenderableOptionSelect
         // 
         this.chdRenderableOptionSelect.HeaderText = "可选择";
         this.chdRenderableOptionSelect.Name = "chdRenderableOptionSelect";
         this.chdRenderableOptionSelect.Width = 70;
         // 
         // chdRenderableOptionVisible
         // 
         this.chdRenderableOptionVisible.HeaderText = "可见性";
         this.chdRenderableOptionVisible.Name = "chdRenderableOptionVisible";
         this.chdRenderableOptionVisible.Width = 70;
         // 
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(327, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 515);
         this.splitter1.TabIndex = 14;
         this.splitter1.TabStop = false;
         // 
         // tvwCatalog
         // 
         this.tvwCatalog.Dock = System.Windows.Forms.DockStyle.Left;
         this.tvwCatalog.FullRowSelect = true;
         this.tvwCatalog.HideSelection = false;
         this.tvwCatalog.ImageIndex = 0;
         this.tvwCatalog.ImageList = this.imlNode;
         this.tvwCatalog.ImeMode = System.Windows.Forms.ImeMode.On;
         this.tvwCatalog.Location = new System.Drawing.Point(0, 0);
         this.tvwCatalog.Name = "tvwCatalog";
         this.tvwCatalog.PathSeparator = "-";
         this.tvwCatalog.RightToLeft = System.Windows.Forms.RightToLeft.No;
         this.tvwCatalog.SelectedImageIndex = 0;
         this.tvwCatalog.Size = new System.Drawing.Size(327, 515);
         this.tvwCatalog.TabIndex = 9;
         this.tvwCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvwDetails_AfterSelect);
         // 
         // labOptionGround
         // 
         this.labOptionGround.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.labOptionGround.Controls.Add(this.qdrOptionGround);
         this.labOptionGround.Controls.Add(this.label1);
         this.labOptionGround.Controls.Add(this.labOptionMergeMaterial);
         this.labOptionGround.Controls.Add(this.labOptionMergeVertex);
         this.labOptionGround.Controls.Add(this.qdrOptionMergeMaterial);
         this.labOptionGround.Controls.Add(this.qdrOptionMergeVertex);
         this.labOptionGround.Controls.Add(this.qdrOptionSelect);
         this.labOptionGround.Controls.Add(this.qdrOptionLightMap);
         this.labOptionGround.Controls.Add(this.qdrOptionLoad);
         this.labOptionGround.Controls.Add(this.txtGeometryInfo);
         this.labOptionGround.Controls.Add(this.txtLabel);
         this.labOptionGround.Controls.Add(this.labGeometryInfo);
         this.labOptionGround.Controls.Add(this.labLabel);
         this.labOptionGround.Controls.Add(this.labOptionSelect);
         this.labOptionGround.Controls.Add(this.labOptionLightMap);
         this.labOptionGround.Controls.Add(this.txtName);
         this.labOptionGround.Controls.Add(this.labOptionLoad);
         this.labOptionGround.Controls.Add(this.labName);
         this.labOptionGround.Dock = System.Windows.Forms.DockStyle.Top;
         this.labOptionGround.Location = new System.Drawing.Point(0, 0);
         this.labOptionGround.Name = "labOptionGround";
         this.labOptionGround.Size = new System.Drawing.Size(987, 96);
         this.labOptionGround.TabIndex = 14;
         // 
         // qdrOptionGround
         // 
         this.qdrOptionGround.Location = new System.Drawing.Point(720, 62);
         this.qdrOptionGround.Name = "qdrOptionGround";
         this.qdrOptionGround.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionGround.TabIndex = 57;
         // 
         // label1
         // 
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(652, 67);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(53, 12);
         this.label1.TabIndex = 56;
         this.label1.Text = "地面配置";
         // 
         // labOptionMergeMaterial
         // 
         this.labOptionMergeMaterial.AutoSize = true;
         this.labOptionMergeMaterial.Location = new System.Drawing.Point(652, 38);
         this.labOptionMergeMaterial.Name = "labOptionMergeMaterial";
         this.labOptionMergeMaterial.Size = new System.Drawing.Size(53, 12);
         this.labOptionMergeMaterial.TabIndex = 55;
         this.labOptionMergeMaterial.Text = "合并材质";
         // 
         // labOptionMergeVertex
         // 
         this.labOptionMergeVertex.AutoSize = true;
         this.labOptionMergeVertex.Location = new System.Drawing.Point(407, 38);
         this.labOptionMergeVertex.Name = "labOptionMergeVertex";
         this.labOptionMergeVertex.Size = new System.Drawing.Size(53, 12);
         this.labOptionMergeVertex.TabIndex = 55;
         this.labOptionMergeVertex.Text = "合并顶点";
         // 
         // qdrOptionMergeMaterial
         // 
         this.qdrOptionMergeMaterial.Location = new System.Drawing.Point(720, 33);
         this.qdrOptionMergeMaterial.Name = "qdrOptionMergeMaterial";
         this.qdrOptionMergeMaterial.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionMergeMaterial.TabIndex = 54;
         // 
         // qdrOptionMergeVertex
         // 
         this.qdrOptionMergeVertex.Location = new System.Drawing.Point(475, 33);
         this.qdrOptionMergeVertex.Name = "qdrOptionMergeVertex";
         this.qdrOptionMergeVertex.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionMergeVertex.TabIndex = 54;
         // 
         // qdrOptionSelect
         // 
         this.qdrOptionSelect.Location = new System.Drawing.Point(475, 62);
         this.qdrOptionSelect.Name = "qdrOptionSelect";
         this.qdrOptionSelect.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionSelect.TabIndex = 54;
         // 
         // qdrOptionLightMap
         // 
         this.qdrOptionLightMap.Location = new System.Drawing.Point(720, 6);
         this.qdrOptionLightMap.Name = "qdrOptionLightMap";
         this.qdrOptionLightMap.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionLightMap.TabIndex = 54;
         // 
         // qdrOptionLoad
         // 
         this.qdrOptionLoad.Location = new System.Drawing.Point(475, 6);
         this.qdrOptionLoad.Name = "qdrOptionLoad";
         this.qdrOptionLoad.Size = new System.Drawing.Size(140, 23);
         this.qdrOptionLoad.TabIndex = 54;
         // 
         // txtGeometryInfo
         // 
         this.txtGeometryInfo.BackColor = System.Drawing.SystemColors.Window;
         this.txtGeometryInfo.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtGeometryInfo.Location = new System.Drawing.Point(68, 65);
         this.txtGeometryInfo.Name = "txtGeometryInfo";
         this.txtGeometryInfo.ReadOnly = true;
         this.txtGeometryInfo.Size = new System.Drawing.Size(313, 21);
         this.txtGeometryInfo.TabIndex = 3;
         // 
         // txtLabel
         // 
         this.txtLabel.BackColor = System.Drawing.SystemColors.Window;
         this.txtLabel.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtLabel.Location = new System.Drawing.Point(68, 35);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.ReadOnly = true;
         this.txtLabel.Size = new System.Drawing.Size(313, 21);
         this.txtLabel.TabIndex = 3;
         // 
         // labGeometryInfo
         // 
         this.labGeometryInfo.AutoSize = true;
         this.labGeometryInfo.Location = new System.Drawing.Point(9, 68);
         this.labGeometryInfo.Name = "labGeometryInfo";
         this.labGeometryInfo.Size = new System.Drawing.Size(53, 12);
         this.labGeometryInfo.TabIndex = 2;
         this.labGeometryInfo.Text = "几何信息";
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(9, 38);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 2;
         this.labLabel.Text = "标题";
         // 
         // labOptionSelect
         // 
         this.labOptionSelect.AutoSize = true;
         this.labOptionSelect.Location = new System.Drawing.Point(407, 67);
         this.labOptionSelect.Name = "labOptionSelect";
         this.labOptionSelect.Size = new System.Drawing.Size(53, 12);
         this.labOptionSelect.TabIndex = 0;
         this.labOptionSelect.Text = "选择配置";
         // 
         // labOptionLightMap
         // 
         this.labOptionLightMap.AutoSize = true;
         this.labOptionLightMap.Location = new System.Drawing.Point(652, 11);
         this.labOptionLightMap.Name = "labOptionLightMap";
         this.labOptionLightMap.Size = new System.Drawing.Size(53, 12);
         this.labOptionLightMap.TabIndex = 0;
         this.labOptionLightMap.Text = "光照贴图";
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(68, 8);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(313, 21);
         this.txtName.TabIndex = 1;
         // 
         // labOptionLoad
         // 
         this.labOptionLoad.AutoSize = true;
         this.labOptionLoad.Location = new System.Drawing.Point(407, 11);
         this.labOptionLoad.Name = "labOptionLoad";
         this.labOptionLoad.Size = new System.Drawing.Size(53, 12);
         this.labOptionLoad.TabIndex = 0;
         this.labOptionLoad.Text = "加载配置";
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(9, 11);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 0;
         this.labName.Text = "名称";
         // 
         // QDsTemplateProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlBody);
         this.Controls.Add(this.panel2);
         this.Name = "QDsTemplateProperty";
         this.Size = new System.Drawing.Size(987, 639);
         this.panel2.ResumeLayout(false);
         this.tspToolSprit.ResumeLayout(false);
         this.tspToolSprit.PerformLayout();
         this.pnlBody.ResumeLayout(false);
         this.pnlProperty.ResumeLayout(false);
         this.panel4.ResumeLayout(false);
         this.palTempalte.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.qgvGeometries)).EndInit();
         this.labOptionGround.ResumeLayout(false);
         this.labOptionGround.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel panel2;
      public System.Windows.Forms.ToolStrip tspToolSprit;
      private System.Windows.Forms.ToolStripButton tsbSave;
      private System.Windows.Forms.ToolStripButton tspExport;
      private System.Windows.Forms.Panel pnlBody;
      private System.Windows.Forms.ToolStripButton tsbView;
      private System.Windows.Forms.ToolStripButton tsbImport;
      private System.Windows.Forms.ImageList imlNode;
      private System.Windows.Forms.ToolStripButton tsbOpen;
      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.Panel labOptionGround;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.TextBox txtLabel;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.Label labOptionLoad;
      private MO.Design3d.Controls.QDsFlag qdrOptionLoad;
      private System.Windows.Forms.Label labOptionMergeVertex;
      private MO.Design3d.Controls.QDsFlag qdrOptionMergeVertex;
      private System.Windows.Forms.Panel panel4;
      private System.Windows.Forms.Panel palTempalte;
      private MO.Core.Forms.Controls.QGridView qgvGeometries;
      private System.Windows.Forms.Splitter splitter1;
      private MO.Core.Forms.Controls.QGmConfigTreeView tvwCatalog;
      private MO.Design3d.Material.Controls.QDsMaterialProperty qdrMaterialProperty;
      private MO.Design3d.Template.Controls.QDsTemplateRenderableProperty qdrTemplateRenderableProperty;
      private MO.Design3d.Template.Controls.QDsTemplateMovieProperty qdrTemplateMovieProperty;
      private System.Windows.Forms.TextBox txtGeometryInfo;
      private System.Windows.Forms.Label labGeometryInfo;
      private MO.Design3d.Controls.QDsFlag qdrOptionSelect;
      private System.Windows.Forms.Label labOptionSelect;
      private System.Windows.Forms.ToolStripButton tspExportLight;
      private System.Windows.Forms.ToolStripButton tspExportLightResource;
      private MO.Design3d.Controls.QDsFlag qdrOptionLightMap;
      private System.Windows.Forms.Label labOptionLightMap;
      private System.Windows.Forms.Label labOptionMergeMaterial;
      private MO.Design3d.Controls.QDsFlag qdrOptionMergeMaterial;
      private System.Windows.Forms.DataGridViewTextBoxColumn chdRenderableModel;
      private System.Windows.Forms.DataGridViewTextBoxColumn chdRenderableMesh;
      private System.Windows.Forms.DataGridViewTextBoxColumn chdRenderableMaterial;
      private System.Windows.Forms.DataGridViewCheckBoxColumn chdRenderableOptionSelect;
      private System.Windows.Forms.DataGridViewCheckBoxColumn chdRenderableOptionVisible;
      private MO.Design3d.Controls.QDsFlag qdrOptionGround;
      private System.Windows.Forms.Label label1;
   }
}
