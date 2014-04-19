namespace MO.Design3d.Model.Controls
{
   partial class QDsModelProperty
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsModelProperty));
         System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle6 = new System.Windows.Forms.DataGridViewCellStyle();
         System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle7 = new System.Windows.Forms.DataGridViewCellStyle();
         System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle8 = new System.Windows.Forms.DataGridViewCellStyle();
         System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle9 = new System.Windows.Forms.DataGridViewCellStyle();
         System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle10 = new System.Windows.Forms.DataGridViewCellStyle();
         this.tvwCatalog = new MO.Core.Forms.Controls.QGmConfigTreeView(this.components);
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.pnlProperty = new System.Windows.Forms.Panel();
         this.pnlModelProperty = new System.Windows.Forms.Panel();
         this.dgvGeometries = new System.Windows.Forms.DataGridView();
         this.dgcGeometryName = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryInstanced = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometryInstance = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryDynamic = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometryBoneScale = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometryBoneCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryWeightCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryShadow = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometrySelfShadow = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometryNormal = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometryDouble = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometrySelect = new System.Windows.Forms.DataGridViewCheckBoxColumn();
         this.dgcGeometryMaterialName = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryVertexCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryFaceCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryNormalCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryBinormalCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcGeometryTangentCount = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.splitter2 = new System.Windows.Forms.Splitter();
         this.dgvMaterials = new System.Windows.Forms.DataGridView();
         this.dgcMaterialName = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcMaterialLabel = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.qdrModelGeometryProperty = new MO.Design3d.Model.Controls.QDsModelGeometryProperty();
         this.qdrMaterialProperty = new MO.Design3d.Material.Controls.QDsMaterialProperty();
         this.panel2 = new System.Windows.Forms.Panel();
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tspSave = new System.Windows.Forms.ToolStripButton();
         this.tsbOpen = new System.Windows.Forms.ToolStripButton();
         this.tssSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tspExportInfo = new System.Windows.Forms.ToolStripButton();
         this.tssSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tspExport = new System.Windows.Forms.ToolStripButton();
         this.panel4 = new System.Windows.Forms.Panel();
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.panel3 = new System.Windows.Forms.Panel();
         this.txtLabel = new System.Windows.Forms.TextBox();
         this.labLabel = new System.Windows.Forms.Label();
         this.txtBoneCount = new System.Windows.Forms.TextBox();
         this.txtName = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.labOptionBoneScale = new System.Windows.Forms.Label();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.dataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dataGridViewTextBoxColumn2 = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dataGridViewTextBoxColumn3 = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dataGridViewTextBoxColumn4 = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dataGridViewTextBoxColumn5 = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dataGridViewTextBoxColumn6 = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.pnlProperty.SuspendLayout();
         this.pnlModelProperty.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.dgvGeometries)).BeginInit();
         ((System.ComponentModel.ISupportInitialize)(this.dgvMaterials)).BeginInit();
         this.panel2.SuspendLayout();
         this.tspMain.SuspendLayout();
         this.panel4.SuspendLayout();
         this.pnlInfo.SuspendLayout();
         this.panel3.SuspendLayout();
         this.SuspendLayout();
         // 
         // tvwCatalog
         // 
         this.tvwCatalog.Dock = System.Windows.Forms.DockStyle.Left;
         this.tvwCatalog.FullRowSelect = true;
         this.tvwCatalog.HideSelection = false;
         this.tvwCatalog.ImageIndex = 0;
         this.tvwCatalog.ImageList = this.imlMain;
         this.tvwCatalog.ImeMode = System.Windows.Forms.ImeMode.On;
         this.tvwCatalog.Location = new System.Drawing.Point(0, 0);
         this.tvwCatalog.MinimumSize = new System.Drawing.Size(80, 4);
         this.tvwCatalog.Name = "tvwCatalog";
         this.tvwCatalog.PathSeparator = "-";
         this.tvwCatalog.RightToLeft = System.Windows.Forms.RightToLeft.No;
         this.tvwCatalog.SelectedImageIndex = 0;
         this.tvwCatalog.Size = new System.Drawing.Size(258, 700);
         this.tvwCatalog.TabIndex = 9;
         this.tvwCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvwCatalog_AfterSelect);
         // 
         // imlMain
         // 
         this.imlMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlMain.ImageStream")));
         this.imlMain.TransparentColor = System.Drawing.Color.Transparent;
         this.imlMain.Images.SetKeyName(0, "Floder");
         this.imlMain.Images.SetKeyName(1, "FloderOpen");
         this.imlMain.Images.SetKeyName(2, "Model");
         this.imlMain.Images.SetKeyName(3, "Geometry");
         this.imlMain.Images.SetKeyName(4, "Material");
         this.imlMain.Images.SetKeyName(5, "Movie");
         // 
         // pnlProperty
         // 
         this.pnlProperty.Controls.Add(this.pnlModelProperty);
         this.pnlProperty.Controls.Add(this.qdrModelGeometryProperty);
         this.pnlProperty.Controls.Add(this.qdrMaterialProperty);
         this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlProperty.Location = new System.Drawing.Point(0, 61);
         this.pnlProperty.Name = "pnlProperty";
         this.pnlProperty.Size = new System.Drawing.Size(910, 639);
         this.pnlProperty.TabIndex = 14;
         // 
         // pnlModelProperty
         // 
         this.pnlModelProperty.Controls.Add(this.dgvGeometries);
         this.pnlModelProperty.Controls.Add(this.splitter2);
         this.pnlModelProperty.Controls.Add(this.dgvMaterials);
         this.pnlModelProperty.Location = new System.Drawing.Point(12, 6);
         this.pnlModelProperty.Name = "pnlModelProperty";
         this.pnlModelProperty.Size = new System.Drawing.Size(870, 468);
         this.pnlModelProperty.TabIndex = 17;
         // 
         // dgvGeometries
         // 
         this.dgvGeometries.AllowUserToAddRows = false;
         this.dgvGeometries.AllowUserToDeleteRows = false;
         this.dgvGeometries.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
         this.dgvGeometries.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dgcGeometryName,
            this.dgcGeometryInstanced,
            this.dgcGeometryInstance,
            this.dgcGeometryDynamic,
            this.dgcGeometryBoneScale,
            this.dgcGeometryBoneCount,
            this.dgcGeometryWeightCount,
            this.dgcGeometryShadow,
            this.dgcGeometrySelfShadow,
            this.dgcGeometryNormal,
            this.dgcGeometryDouble,
            this.dgcGeometrySelect,
            this.dgcGeometryMaterialName,
            this.dgcGeometryVertexCount,
            this.dgcGeometryFaceCount,
            this.dgcGeometryNormalCount,
            this.dgcGeometryBinormalCount,
            this.dgcGeometryTangentCount});
         this.dgvGeometries.Dock = System.Windows.Forms.DockStyle.Fill;
         this.dgvGeometries.Location = new System.Drawing.Point(0, 238);
         this.dgvGeometries.Name = "dgvGeometries";
         this.dgvGeometries.RowTemplate.Height = 20;
         this.dgvGeometries.Size = new System.Drawing.Size(870, 230);
         this.dgvGeometries.TabIndex = 16;
         // 
         // dgcGeometryName
         // 
         dataGridViewCellStyle6.ForeColor = System.Drawing.Color.Gray;
         this.dgcGeometryName.DefaultCellStyle = dataGridViewCellStyle6;
         this.dgcGeometryName.HeaderText = "名称";
         this.dgcGeometryName.Name = "dgcGeometryName";
         this.dgcGeometryName.ReadOnly = true;
         // 
         // dgcGeometryInstanced
         // 
         this.dgcGeometryInstanced.HeaderText = "实体";
         this.dgcGeometryInstanced.Name = "dgcGeometryInstanced";
         this.dgcGeometryInstanced.Width = 70;
         // 
         // dgcGeometryInstance
         // 
         dataGridViewCellStyle7.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight;
         this.dgcGeometryInstance.DefaultCellStyle = dataGridViewCellStyle7;
         this.dgcGeometryInstance.HeaderText = "实体个数";
         this.dgcGeometryInstance.Name = "dgcGeometryInstance";
         this.dgcGeometryInstance.Width = 80;
         // 
         // dgcGeometryDynamic
         // 
         this.dgcGeometryDynamic.HeaderText = "动态";
         this.dgcGeometryDynamic.Name = "dgcGeometryDynamic";
         this.dgcGeometryDynamic.Width = 70;
         // 
         // dgcGeometryBoneScale
         // 
         this.dgcGeometryBoneScale.HeaderText = "骨骼缩放";
         this.dgcGeometryBoneScale.Name = "dgcGeometryBoneScale";
         this.dgcGeometryBoneScale.ReadOnly = true;
         this.dgcGeometryBoneScale.Resizable = System.Windows.Forms.DataGridViewTriState.True;
         this.dgcGeometryBoneScale.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
         this.dgcGeometryBoneScale.Width = 80;
         // 
         // dgcGeometryBoneCount
         // 
         dataGridViewCellStyle8.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight;
         this.dgcGeometryBoneCount.DefaultCellStyle = dataGridViewCellStyle8;
         this.dgcGeometryBoneCount.HeaderText = "骨骼个数";
         this.dgcGeometryBoneCount.Name = "dgcGeometryBoneCount";
         this.dgcGeometryBoneCount.ReadOnly = true;
         this.dgcGeometryBoneCount.Width = 80;
         // 
         // dgcGeometryWeightCount
         // 
         dataGridViewCellStyle9.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleRight;
         this.dgcGeometryWeightCount.DefaultCellStyle = dataGridViewCellStyle9;
         this.dgcGeometryWeightCount.HeaderText = "骨骼权重";
         this.dgcGeometryWeightCount.Name = "dgcGeometryWeightCount";
         this.dgcGeometryWeightCount.ReadOnly = true;
         this.dgcGeometryWeightCount.Width = 80;
         // 
         // dgcGeometryShadow
         // 
         this.dgcGeometryShadow.HeaderText = "阴影";
         this.dgcGeometryShadow.Name = "dgcGeometryShadow";
         this.dgcGeometryShadow.Resizable = System.Windows.Forms.DataGridViewTriState.True;
         this.dgcGeometryShadow.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
         this.dgcGeometryShadow.Width = 70;
         // 
         // dgcGeometrySelfShadow
         // 
         this.dgcGeometrySelfShadow.HeaderText = "自阴影";
         this.dgcGeometrySelfShadow.Name = "dgcGeometrySelfShadow";
         this.dgcGeometrySelfShadow.Width = 70;
         // 
         // dgcGeometryNormal
         // 
         this.dgcGeometryNormal.HeaderText = "法线";
         this.dgcGeometryNormal.Name = "dgcGeometryNormal";
         this.dgcGeometryNormal.Width = 70;
         // 
         // dgcGeometryDouble
         // 
         this.dgcGeometryDouble.HeaderText = "双面";
         this.dgcGeometryDouble.Name = "dgcGeometryDouble";
         this.dgcGeometryDouble.Resizable = System.Windows.Forms.DataGridViewTriState.True;
         this.dgcGeometryDouble.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
         this.dgcGeometryDouble.Width = 70;
         // 
         // dgcGeometrySelect
         // 
         this.dgcGeometrySelect.HeaderText = "选择";
         this.dgcGeometrySelect.Name = "dgcGeometrySelect";
         // 
         // dgcGeometryMaterialName
         // 
         this.dgcGeometryMaterialName.HeaderText = "材质名称";
         this.dgcGeometryMaterialName.Name = "dgcGeometryMaterialName";
         this.dgcGeometryMaterialName.Width = 200;
         // 
         // dgcGeometryVertexCount
         // 
         this.dgcGeometryVertexCount.HeaderText = "顶点个数";
         this.dgcGeometryVertexCount.Name = "dgcGeometryVertexCount";
         // 
         // dgcGeometryFaceCount
         // 
         this.dgcGeometryFaceCount.HeaderText = "面个数";
         this.dgcGeometryFaceCount.Name = "dgcGeometryFaceCount";
         this.dgcGeometryFaceCount.Width = 80;
         // 
         // dgcGeometryNormalCount
         // 
         this.dgcGeometryNormalCount.HeaderText = "法线个数";
         this.dgcGeometryNormalCount.Name = "dgcGeometryNormalCount";
         this.dgcGeometryNormalCount.Width = 80;
         // 
         // dgcGeometryBinormalCount
         // 
         this.dgcGeometryBinormalCount.HeaderText = "副法线个数";
         this.dgcGeometryBinormalCount.Name = "dgcGeometryBinormalCount";
         this.dgcGeometryBinormalCount.Width = 90;
         // 
         // dgcGeometryTangentCount
         // 
         this.dgcGeometryTangentCount.HeaderText = "切线个数";
         this.dgcGeometryTangentCount.Name = "dgcGeometryTangentCount";
         this.dgcGeometryTangentCount.Width = 80;
         // 
         // splitter2
         // 
         this.splitter2.Dock = System.Windows.Forms.DockStyle.Top;
         this.splitter2.Location = new System.Drawing.Point(0, 234);
         this.splitter2.Name = "splitter2";
         this.splitter2.Size = new System.Drawing.Size(870, 4);
         this.splitter2.TabIndex = 17;
         this.splitter2.TabStop = false;
         // 
         // dgvMaterials
         // 
         this.dgvMaterials.AllowUserToAddRows = false;
         this.dgvMaterials.AllowUserToDeleteRows = false;
         this.dgvMaterials.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
         this.dgvMaterials.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dgcMaterialName,
            this.dgcMaterialLabel});
         this.dgvMaterials.Dock = System.Windows.Forms.DockStyle.Top;
         this.dgvMaterials.Location = new System.Drawing.Point(0, 0);
         this.dgvMaterials.Name = "dgvMaterials";
         this.dgvMaterials.RowTemplate.Height = 20;
         this.dgvMaterials.Size = new System.Drawing.Size(870, 234);
         this.dgvMaterials.TabIndex = 16;
         // 
         // dgcMaterialName
         // 
         this.dgcMaterialName.HeaderText = "名称";
         this.dgcMaterialName.Name = "dgcMaterialName";
         this.dgcMaterialName.Width = 300;
         // 
         // dgcMaterialLabel
         // 
         this.dgcMaterialLabel.HeaderText = "标签";
         this.dgcMaterialLabel.Name = "dgcMaterialLabel";
         this.dgcMaterialLabel.Width = 200;
         // 
         // qdrModelGeometryProperty
         // 
         this.qdrModelGeometryProperty.Location = new System.Drawing.Point(12, 557);
         this.qdrModelGeometryProperty.Name = "qdrModelGeometryProperty";
         this.qdrModelGeometryProperty.Size = new System.Drawing.Size(870, 67);
         this.qdrModelGeometryProperty.TabIndex = 15;
         this.qdrModelGeometryProperty.Visible = false;
         // 
         // qdrMaterialProperty
         // 
         this.qdrMaterialProperty.Location = new System.Drawing.Point(12, 480);
         this.qdrMaterialProperty.Name = "qdrMaterialProperty";
         this.qdrMaterialProperty.Size = new System.Drawing.Size(870, 71);
         this.qdrMaterialProperty.TabIndex = 14;
         this.qdrMaterialProperty.Visible = false;
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.tspMain);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel2.Location = new System.Drawing.Point(0, 0);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(1172, 28);
         this.panel2.TabIndex = 14;
         // 
         // tspMain
         // 
         this.tspMain.AutoSize = false;
         this.tspMain.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tspSave,
            this.tsbOpen,
            this.tssSeparator1,
            this.tspExportInfo,
            this.tssSeparator2,
            this.tspExport});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspMain.Size = new System.Drawing.Size(1172, 28);
         this.tspMain.TabIndex = 13;
         this.tspMain.Text = "toolStrip1";
         // 
         // tspSave
         // 
         this.tspSave.Image = ((System.Drawing.Image)(resources.GetObject("tspSave.Image")));
         this.tspSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspSave.Name = "tspSave";
         this.tspSave.Size = new System.Drawing.Size(52, 25);
         this.tspSave.Text = "保存";
         this.tspSave.Click += new System.EventHandler(this.tspSave_Click);
         // 
         // tsbOpen
         // 
         this.tsbOpen.Image = ((System.Drawing.Image)(resources.GetObject("tsbOpen.Image")));
         this.tsbOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbOpen.Name = "tsbOpen";
         this.tsbOpen.Size = new System.Drawing.Size(52, 25);
         this.tsbOpen.Text = "打开";
         this.tsbOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // tssSeparator1
         // 
         this.tssSeparator1.Name = "tssSeparator1";
         this.tssSeparator1.Size = new System.Drawing.Size(6, 28);
         // 
         // tspExportInfo
         // 
         this.tspExportInfo.Image = ((System.Drawing.Image)(resources.GetObject("tspExportInfo.Image")));
         this.tspExportInfo.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExportInfo.Name = "tspExportInfo";
         this.tspExportInfo.Size = new System.Drawing.Size(76, 25);
         this.tspExportInfo.Text = "导出信息";
         this.tspExportInfo.Click += new System.EventHandler(this.tspExportInfo_Click);
         // 
         // tssSeparator2
         // 
         this.tssSeparator2.Name = "tssSeparator2";
         this.tssSeparator2.Size = new System.Drawing.Size(6, 28);
         // 
         // tspExport
         // 
         this.tspExport.Image = ((System.Drawing.Image)(resources.GetObject("tspExport.Image")));
         this.tspExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExport.Name = "tspExport";
         this.tspExport.Size = new System.Drawing.Size(76, 25);
         this.tspExport.Text = "导出资源";
         this.tspExport.Click += new System.EventHandler(this.tspExport_Click);
         // 
         // panel4
         // 
         this.panel4.Controls.Add(this.pnlInfo);
         this.panel4.Controls.Add(this.splitter1);
         this.panel4.Controls.Add(this.tvwCatalog);
         this.panel4.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel4.Location = new System.Drawing.Point(0, 28);
         this.panel4.Name = "panel4";
         this.panel4.Size = new System.Drawing.Size(1172, 700);
         this.panel4.TabIndex = 15;
         // 
         // pnlInfo
         // 
         this.pnlInfo.Controls.Add(this.pnlProperty);
         this.pnlInfo.Controls.Add(this.panel3);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(262, 0);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(910, 700);
         this.pnlInfo.TabIndex = 16;
         // 
         // panel3
         // 
         this.panel3.Controls.Add(this.txtLabel);
         this.panel3.Controls.Add(this.labLabel);
         this.panel3.Controls.Add(this.txtBoneCount);
         this.panel3.Controls.Add(this.txtName);
         this.panel3.Controls.Add(this.labName);
         this.panel3.Controls.Add(this.labOptionBoneScale);
         this.panel3.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel3.Location = new System.Drawing.Point(0, 0);
         this.panel3.Name = "panel3";
         this.panel3.Size = new System.Drawing.Size(910, 61);
         this.panel3.TabIndex = 0;
         // 
         // txtLabel
         // 
         this.txtLabel.BackColor = System.Drawing.SystemColors.Window;
         this.txtLabel.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtLabel.Location = new System.Drawing.Point(69, 33);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.ReadOnly = true;
         this.txtLabel.Size = new System.Drawing.Size(415, 21);
         this.txtLabel.TabIndex = 61;
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(10, 36);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 60;
         this.labLabel.Text = "标题";
         // 
         // txtBoneCount
         // 
         this.txtBoneCount.BackColor = System.Drawing.SystemColors.Window;
         this.txtBoneCount.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtBoneCount.Location = new System.Drawing.Point(581, 6);
         this.txtBoneCount.Name = "txtBoneCount";
         this.txtBoneCount.ReadOnly = true;
         this.txtBoneCount.Size = new System.Drawing.Size(95, 21);
         this.txtBoneCount.TabIndex = 59;
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(69, 6);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(415, 21);
         this.txtName.TabIndex = 59;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(10, 9);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 58;
         this.labName.Text = "名称";
         // 
         // labOptionBoneScale
         // 
         this.labOptionBoneScale.AutoSize = true;
         this.labOptionBoneScale.Location = new System.Drawing.Point(522, 9);
         this.labOptionBoneScale.Name = "labOptionBoneScale";
         this.labOptionBoneScale.Size = new System.Drawing.Size(53, 12);
         this.labOptionBoneScale.TabIndex = 57;
         this.labOptionBoneScale.Text = "骨骼总数";
         // 
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(258, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 700);
         this.splitter1.TabIndex = 15;
         this.splitter1.TabStop = false;
         // 
         // dataGridViewTextBoxColumn1
         // 
         this.dataGridViewTextBoxColumn1.HeaderText = "类型";
         this.dataGridViewTextBoxColumn1.MinimumWidth = 20;
         this.dataGridViewTextBoxColumn1.Name = "dataGridViewTextBoxColumn1";
         this.dataGridViewTextBoxColumn1.Width = 68;
         // 
         // dataGridViewTextBoxColumn2
         // 
         dataGridViewCellStyle10.WrapMode = System.Windows.Forms.DataGridViewTriState.True;
         this.dataGridViewTextBoxColumn2.DefaultCellStyle = dataGridViewCellStyle10;
         this.dataGridViewTextBoxColumn2.FillWeight = 55F;
         this.dataGridViewTextBoxColumn2.HeaderText = "是否有效";
         this.dataGridViewTextBoxColumn2.Name = "dataGridViewTextBoxColumn2";
         this.dataGridViewTextBoxColumn2.Width = 27;
         // 
         // dataGridViewTextBoxColumn3
         // 
         this.dataGridViewTextBoxColumn3.FillWeight = 108.8F;
         this.dataGridViewTextBoxColumn3.HeaderText = "来源";
         this.dataGridViewTextBoxColumn3.MinimumWidth = 20;
         this.dataGridViewTextBoxColumn3.Name = "dataGridViewTextBoxColumn3";
         this.dataGridViewTextBoxColumn3.Width = 75;
         // 
         // dataGridViewTextBoxColumn4
         // 
         this.dataGridViewTextBoxColumn4.FillWeight = 133.3911F;
         this.dataGridViewTextBoxColumn4.HeaderText = "资源类型";
         this.dataGridViewTextBoxColumn4.MinimumWidth = 20;
         this.dataGridViewTextBoxColumn4.Name = "dataGridViewTextBoxColumn4";
         this.dataGridViewTextBoxColumn4.Width = 90;
         // 
         // dataGridViewTextBoxColumn5
         // 
         this.dataGridViewTextBoxColumn5.FillWeight = 152.2843F;
         this.dataGridViewTextBoxColumn5.HeaderText = "大小";
         this.dataGridViewTextBoxColumn5.MinimumWidth = 15;
         this.dataGridViewTextBoxColumn5.Name = "dataGridViewTextBoxColumn5";
         this.dataGridViewTextBoxColumn5.Width = 104;
         // 
         // dataGridViewTextBoxColumn6
         // 
         this.dataGridViewTextBoxColumn6.FillWeight = 106.6121F;
         this.dataGridViewTextBoxColumn6.HeaderText = "尺寸";
         this.dataGridViewTextBoxColumn6.MinimumWidth = 20;
         this.dataGridViewTextBoxColumn6.Name = "dataGridViewTextBoxColumn6";
         this.dataGridViewTextBoxColumn6.Width = 72;
         // 
         // QDsModelProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel4);
         this.Controls.Add(this.panel2);
         this.Name = "QDsModelProperty";
         this.Size = new System.Drawing.Size(1172, 728);
         this.pnlProperty.ResumeLayout(false);
         this.pnlModelProperty.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.dgvGeometries)).EndInit();
         ((System.ComponentModel.ISupportInitialize)(this.dgvMaterials)).EndInit();
         this.panel2.ResumeLayout(false);
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.panel4.ResumeLayout(false);
         this.pnlInfo.ResumeLayout(false);
         this.panel3.ResumeLayout(false);
         this.panel3.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private MO.Core.Forms.Controls.QGmConfigTreeView tvwCatalog;
      private System.Windows.Forms.Panel panel2;
      public System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tspSave;
      private System.Windows.Forms.ToolStripButton tspExport;
      private System.Windows.Forms.Panel panel4;
      private System.Windows.Forms.ToolStripButton tsbOpen;
      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn1;
      private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn2;
      private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn3;
      private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn4;
      private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn5;
      private System.Windows.Forms.DataGridViewTextBoxColumn dataGridViewTextBoxColumn6;
      private System.Windows.Forms.ToolStripSeparator tssSeparator1;
      private MO.Design3d.Material.Controls.QDsMaterialProperty qdrMaterialProperty;
      private MO.Design3d.Model.Controls.QDsModelGeometryProperty qdrModelGeometryProperty;
      private System.Windows.Forms.ImageList imlMain;
      private System.Windows.Forms.Splitter splitter1;
      private System.Windows.Forms.Panel pnlInfo;
      private System.Windows.Forms.Panel panel3;
      private System.Windows.Forms.Label labOptionBoneScale;
      private System.Windows.Forms.TextBox txtLabel;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.Panel pnlModelProperty;
      private System.Windows.Forms.DataGridView dgvMaterials;
      private System.Windows.Forms.Splitter splitter2;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcMaterialName;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcMaterialLabel;
      private System.Windows.Forms.DataGridView dgvGeometries;
      private System.Windows.Forms.TextBox txtBoneCount;
      private System.Windows.Forms.ToolStripButton tspExportInfo;
      private System.Windows.Forms.ToolStripSeparator tssSeparator2;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryName;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometryInstanced;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryInstance;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometryDynamic;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometryBoneScale;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryBoneCount;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryWeightCount;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometryShadow;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometrySelfShadow;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometryNormal;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometryDouble;
      private System.Windows.Forms.DataGridViewCheckBoxColumn dgcGeometrySelect;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryMaterialName;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryVertexCount;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryFaceCount;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryNormalCount;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryBinormalCount;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcGeometryTangentCount;
   }
}
