namespace MO.Design2d.Resource.Controls
{
   partial class QDsPictureProperty
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsPictureProperty));
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.qpbImage = new MO.Core.Forms.Controls.QPictureBox();
         this.pnlDetail = new System.Windows.Forms.Panel();
         this.btnEditGroup = new System.Windows.Forms.Button();
         this.txtGroupName = new System.Windows.Forms.TextBox();
         this.label2 = new System.Windows.Forms.Label();
         this.groupBox2 = new System.Windows.Forms.GroupBox();
         this.radLong = new System.Windows.Forms.RadioButton();
         this.radMiddle = new System.Windows.Forms.RadioButton();
         this.radShort = new System.Windows.Forms.RadioButton();
         this.radNull = new System.Windows.Forms.RadioButton();
         this.chkPandding = new System.Windows.Forms.CheckBox();
         this.groupBox1 = new System.Windows.Forms.GroupBox();
         this.ranPalettePixel1 = new System.Windows.Forms.RadioButton();
         this.ranPalettePixel2 = new System.Windows.Forms.RadioButton();
         this.ranPalettePixel3 = new System.Windows.Forms.RadioButton();
         this.ranPalettePixel4 = new System.Windows.Forms.RadioButton();
         this.ranPalettePixel5 = new System.Windows.Forms.RadioButton();
         this.gbxPalette = new System.Windows.Forms.GroupBox();
         this.rbnPaletteColor256 = new System.Windows.Forms.RadioButton();
         this.rbnPaletteColor64 = new System.Windows.Forms.RadioButton();
         this.rbnPaletteColor128 = new System.Windows.Forms.RadioButton();
         this.cbxAlpha = new System.Windows.Forms.CheckBox();
         this.txtFileName = new System.Windows.Forms.TextBox();
         this.txtLabel = new System.Windows.Forms.TextBox();
         this.labFileName = new System.Windows.Forms.Label();
         this.labLabel = new System.Windows.Forms.Label();
         this.tsbSave = new System.Windows.Forms.ToolStripButton();
         this.tsbExport = new System.Windows.Forms.ToolStripButton();
         this.tssLine3 = new System.Windows.Forms.ToolStripSeparator();
         this.tsResource = new System.Windows.Forms.ToolStrip();
         this.tsbFolder = new System.Windows.Forms.ToolStripButton();
         this.tsbBtnOpen = new System.Windows.Forms.ToolStripButton();
         this.tsbScaleAuto = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex2 = new System.Windows.Forms.ToolStripButton();
         this.dlgOpen = new System.Windows.Forms.OpenFileDialog();
         this.pnlInfo.SuspendLayout();
         this.pnlDetail.SuspendLayout();
         this.groupBox2.SuspendLayout();
         this.groupBox1.SuspendLayout();
         this.gbxPalette.SuspendLayout();
         this.tsResource.SuspendLayout();
         this.SuspendLayout();
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 23);
         // 
         // pnlInfo
         // 
         this.pnlInfo.Controls.Add(this.qpbImage);
         this.pnlInfo.Controls.Add(this.pnlDetail);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(0, 24);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(819, 427);
         this.pnlInfo.TabIndex = 22;
         // 
         // qpbImage
         // 
         this.qpbImage.AutoScroll = true;
         this.qpbImage.BackColor = System.Drawing.SystemColors.Window;
         this.qpbImage.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.qpbImage.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qpbImage.Location = new System.Drawing.Point(0, 114);
         this.qpbImage.Name = "qpbImage";
         this.qpbImage.ScaleMode = MO.Core.Forms.Controls.EPictureBoxMode.Auto;
         this.qpbImage.ScaleValue = 1F;
         this.qpbImage.Size = new System.Drawing.Size(819, 313);
         this.qpbImage.TabIndex = 14;
         // 
         // pnlDetail
         // 
         this.pnlDetail.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlDetail.Controls.Add(this.btnEditGroup);
         this.pnlDetail.Controls.Add(this.txtGroupName);
         this.pnlDetail.Controls.Add(this.label2);
         this.pnlDetail.Controls.Add(this.groupBox2);
         this.pnlDetail.Controls.Add(this.chkPandding);
         this.pnlDetail.Controls.Add(this.groupBox1);
         this.pnlDetail.Controls.Add(this.gbxPalette);
         this.pnlDetail.Controls.Add(this.cbxAlpha);
         this.pnlDetail.Controls.Add(this.txtFileName);
         this.pnlDetail.Controls.Add(this.txtLabel);
         this.pnlDetail.Controls.Add(this.labFileName);
         this.pnlDetail.Controls.Add(this.labLabel);
         this.pnlDetail.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlDetail.Location = new System.Drawing.Point(0, 0);
         this.pnlDetail.Name = "pnlDetail";
         this.pnlDetail.Size = new System.Drawing.Size(819, 114);
         this.pnlDetail.TabIndex = 13;
         // 
         // btnEditGroup
         // 
         this.btnEditGroup.Location = new System.Drawing.Point(609, 8);
         this.btnEditGroup.Name = "btnEditGroup";
         this.btnEditGroup.Size = new System.Drawing.Size(60, 23);
         this.btnEditGroup.TabIndex = 38;
         this.btnEditGroup.Text = "选择";
         this.btnEditGroup.UseVisualStyleBackColor = true;
         this.btnEditGroup.Click += new System.EventHandler(this.btnEditGroup_Click);
         // 
         // txtGroupName
         // 
         this.txtGroupName.BackColor = System.Drawing.SystemColors.Window;
         this.txtGroupName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtGroupName.Location = new System.Drawing.Point(535, 10);
         this.txtGroupName.Name = "txtGroupName";
         this.txtGroupName.Size = new System.Drawing.Size(60, 21);
         this.txtGroupName.TabIndex = 37;
         this.txtGroupName.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
         // 
         // label2
         // 
         this.label2.AutoSize = true;
         this.label2.Location = new System.Drawing.Point(488, 13);
         this.label2.Name = "label2";
         this.label2.Size = new System.Drawing.Size(41, 12);
         this.label2.TabIndex = 36;
         this.label2.Text = "资源组";
         // 
         // groupBox2
         // 
         this.groupBox2.Controls.Add(this.radLong);
         this.groupBox2.Controls.Add(this.radMiddle);
         this.groupBox2.Controls.Add(this.radShort);
         this.groupBox2.Controls.Add(this.radNull);
         this.groupBox2.Location = new System.Drawing.Point(209, 64);
         this.groupBox2.Name = "groupBox2";
         this.groupBox2.Size = new System.Drawing.Size(245, 40);
         this.groupBox2.TabIndex = 35;
         this.groupBox2.TabStop = false;
         this.groupBox2.Text = "超时类型";
         // 
         // radLong
         // 
         this.radLong.AutoSize = true;
         this.radLong.Location = new System.Drawing.Point(176, 16);
         this.radLong.Name = "radLong";
         this.radLong.Size = new System.Drawing.Size(35, 16);
         this.radLong.TabIndex = 4;
         this.radLong.TabStop = true;
         this.radLong.Tag = "long";
         this.radLong.Text = "长";
         this.radLong.UseVisualStyleBackColor = true;
         this.radLong.CheckedChanged += new System.EventHandler(this.CheckedChanged);
         // 
         // radMiddle
         // 
         this.radMiddle.AutoSize = true;
         this.radMiddle.Location = new System.Drawing.Point(123, 16);
         this.radMiddle.Name = "radMiddle";
         this.radMiddle.Size = new System.Drawing.Size(35, 16);
         this.radMiddle.TabIndex = 3;
         this.radMiddle.TabStop = true;
         this.radMiddle.Tag = "middle";
         this.radMiddle.Text = "中";
         this.radMiddle.UseVisualStyleBackColor = true;
         this.radMiddle.CheckedChanged += new System.EventHandler(this.CheckedChanged);
         // 
         // radShort
         // 
         this.radShort.AutoSize = true;
         this.radShort.Location = new System.Drawing.Point(70, 16);
         this.radShort.Name = "radShort";
         this.radShort.Size = new System.Drawing.Size(35, 16);
         this.radShort.TabIndex = 2;
         this.radShort.TabStop = true;
         this.radShort.Tag = "short";
         this.radShort.Text = "短";
         this.radShort.UseVisualStyleBackColor = true;
         this.radShort.CheckedChanged += new System.EventHandler(this.CheckedChanged);
         // 
         // radNull
         // 
         this.radNull.AutoSize = true;
         this.radNull.Location = new System.Drawing.Point(17, 16);
         this.radNull.Name = "radNull";
         this.radNull.Size = new System.Drawing.Size(35, 16);
         this.radNull.TabIndex = 1;
         this.radNull.TabStop = true;
         this.radNull.Tag = "none";
         this.radNull.Text = "无";
         this.radNull.UseVisualStyleBackColor = true;
         this.radNull.CheckedChanged += new System.EventHandler(this.CheckedChanged);
         // 
         // chkPandding
         // 
         this.chkPandding.AutoSize = true;
         this.chkPandding.Location = new System.Drawing.Point(694, 42);
         this.chkPandding.Name = "chkPandding";
         this.chkPandding.Size = new System.Drawing.Size(96, 16);
         this.chkPandding.TabIndex = 34;
         this.chkPandding.Text = "是否支持空白";
         this.chkPandding.UseVisualStyleBackColor = true;
         this.chkPandding.CheckedChanged += new System.EventHandler(this.chkPandding_CheckedChanged);
         // 
         // groupBox1
         // 
         this.groupBox1.Controls.Add(this.ranPalettePixel1);
         this.groupBox1.Controls.Add(this.ranPalettePixel2);
         this.groupBox1.Controls.Add(this.ranPalettePixel3);
         this.groupBox1.Controls.Add(this.ranPalettePixel4);
         this.groupBox1.Controls.Add(this.ranPalettePixel5);
         this.groupBox1.Location = new System.Drawing.Point(460, 64);
         this.groupBox1.Name = "groupBox1";
         this.groupBox1.Size = new System.Drawing.Size(330, 40);
         this.groupBox1.TabIndex = 33;
         this.groupBox1.TabStop = false;
         this.groupBox1.Text = "切割质量";
         // 
         // ranPalettePixel1
         // 
         this.ranPalettePixel1.AutoSize = true;
         this.ranPalettePixel1.Location = new System.Drawing.Point(229, 16);
         this.ranPalettePixel1.Name = "ranPalettePixel1";
         this.ranPalettePixel1.Size = new System.Drawing.Size(47, 16);
         this.ranPalettePixel1.TabIndex = 5;
         this.ranPalettePixel1.TabStop = true;
         this.ranPalettePixel1.Tag = "min";
         this.ranPalettePixel1.Text = "极低";
         this.ranPalettePixel1.UseVisualStyleBackColor = true;
         this.ranPalettePixel1.CheckedChanged += new System.EventHandler(this.ranPalettePixel_CheckedChanged);
         // 
         // ranPalettePixel2
         // 
         this.ranPalettePixel2.AutoSize = true;
         this.ranPalettePixel2.Location = new System.Drawing.Point(176, 16);
         this.ranPalettePixel2.Name = "ranPalettePixel2";
         this.ranPalettePixel2.Size = new System.Drawing.Size(35, 16);
         this.ranPalettePixel2.TabIndex = 4;
         this.ranPalettePixel2.TabStop = true;
         this.ranPalettePixel2.Tag = "lower";
         this.ranPalettePixel2.Text = "低";
         this.ranPalettePixel2.UseVisualStyleBackColor = true;
         this.ranPalettePixel2.CheckedChanged += new System.EventHandler(this.ranPalettePixel_CheckedChanged);
         // 
         // ranPalettePixel3
         // 
         this.ranPalettePixel3.AutoSize = true;
         this.ranPalettePixel3.Location = new System.Drawing.Point(123, 16);
         this.ranPalettePixel3.Name = "ranPalettePixel3";
         this.ranPalettePixel3.Size = new System.Drawing.Size(35, 16);
         this.ranPalettePixel3.TabIndex = 3;
         this.ranPalettePixel3.TabStop = true;
         this.ranPalettePixel3.Tag = "middle";
         this.ranPalettePixel3.Text = "中";
         this.ranPalettePixel3.UseVisualStyleBackColor = true;
         this.ranPalettePixel3.CheckedChanged += new System.EventHandler(this.ranPalettePixel_CheckedChanged);
         // 
         // ranPalettePixel4
         // 
         this.ranPalettePixel4.AutoSize = true;
         this.ranPalettePixel4.Location = new System.Drawing.Point(70, 16);
         this.ranPalettePixel4.Name = "ranPalettePixel4";
         this.ranPalettePixel4.Size = new System.Drawing.Size(35, 16);
         this.ranPalettePixel4.TabIndex = 2;
         this.ranPalettePixel4.TabStop = true;
         this.ranPalettePixel4.Tag = "high";
         this.ranPalettePixel4.Text = "高";
         this.ranPalettePixel4.UseVisualStyleBackColor = true;
         this.ranPalettePixel4.CheckedChanged += new System.EventHandler(this.ranPalettePixel_CheckedChanged);
         // 
         // ranPalettePixel5
         // 
         this.ranPalettePixel5.AutoSize = true;
         this.ranPalettePixel5.Location = new System.Drawing.Point(17, 16);
         this.ranPalettePixel5.Name = "ranPalettePixel5";
         this.ranPalettePixel5.Size = new System.Drawing.Size(47, 16);
         this.ranPalettePixel5.TabIndex = 1;
         this.ranPalettePixel5.TabStop = true;
         this.ranPalettePixel5.Tag = "max";
         this.ranPalettePixel5.Text = "极高";
         this.ranPalettePixel5.UseVisualStyleBackColor = true;
         this.ranPalettePixel5.CheckedChanged += new System.EventHandler(this.ranPalettePixel_CheckedChanged);
         // 
         // gbxPalette
         // 
         this.gbxPalette.Controls.Add(this.rbnPaletteColor256);
         this.gbxPalette.Controls.Add(this.rbnPaletteColor64);
         this.gbxPalette.Controls.Add(this.rbnPaletteColor128);
         this.gbxPalette.Location = new System.Drawing.Point(21, 64);
         this.gbxPalette.Name = "gbxPalette";
         this.gbxPalette.Size = new System.Drawing.Size(182, 40);
         this.gbxPalette.TabIndex = 15;
         this.gbxPalette.TabStop = false;
         this.gbxPalette.Text = " 调色板颜色数 ";
         // 
         // rbnPaletteColor256
         // 
         this.rbnPaletteColor256.AutoSize = true;
         this.rbnPaletteColor256.Location = new System.Drawing.Point(128, 16);
         this.rbnPaletteColor256.Name = "rbnPaletteColor256";
         this.rbnPaletteColor256.Size = new System.Drawing.Size(41, 16);
         this.rbnPaletteColor256.TabIndex = 16;
         this.rbnPaletteColor256.TabStop = true;
         this.rbnPaletteColor256.Tag = "256";
         this.rbnPaletteColor256.Text = "256";
         this.rbnPaletteColor256.UseVisualStyleBackColor = true;
         this.rbnPaletteColor256.CheckedChanged += new System.EventHandler(this.rbnPaletteColor_CheckedChanged);
         // 
         // rbnPaletteColor64
         // 
         this.rbnPaletteColor64.AutoSize = true;
         this.rbnPaletteColor64.Location = new System.Drawing.Point(19, 16);
         this.rbnPaletteColor64.Name = "rbnPaletteColor64";
         this.rbnPaletteColor64.Size = new System.Drawing.Size(35, 16);
         this.rbnPaletteColor64.TabIndex = 0;
         this.rbnPaletteColor64.TabStop = true;
         this.rbnPaletteColor64.Tag = "64";
         this.rbnPaletteColor64.Text = "64";
         this.rbnPaletteColor64.UseVisualStyleBackColor = true;
         this.rbnPaletteColor64.CheckedChanged += new System.EventHandler(this.rbnPaletteColor_CheckedChanged);
         // 
         // rbnPaletteColor128
         // 
         this.rbnPaletteColor128.AutoSize = true;
         this.rbnPaletteColor128.Location = new System.Drawing.Point(71, 16);
         this.rbnPaletteColor128.Name = "rbnPaletteColor128";
         this.rbnPaletteColor128.Size = new System.Drawing.Size(41, 16);
         this.rbnPaletteColor128.TabIndex = 15;
         this.rbnPaletteColor128.TabStop = true;
         this.rbnPaletteColor128.Tag = "128";
         this.rbnPaletteColor128.Text = "128";
         this.rbnPaletteColor128.UseVisualStyleBackColor = true;
         this.rbnPaletteColor128.CheckedChanged += new System.EventHandler(this.rbnPaletteColor_CheckedChanged);
         // 
         // cbxAlpha
         // 
         this.cbxAlpha.AutoSize = true;
         this.cbxAlpha.Location = new System.Drawing.Point(694, 12);
         this.cbxAlpha.Name = "cbxAlpha";
         this.cbxAlpha.Size = new System.Drawing.Size(96, 16);
         this.cbxAlpha.TabIndex = 30;
         this.cbxAlpha.Text = "是否支持透明";
         this.cbxAlpha.UseVisualStyleBackColor = true;
         this.cbxAlpha.CheckedChanged += new System.EventHandler(this.cbxAlpha_CheckedChanged);
         // 
         // txtFileName
         // 
         this.txtFileName.BackColor = System.Drawing.SystemColors.Window;
         this.txtFileName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtFileName.Location = new System.Drawing.Point(54, 37);
         this.txtFileName.Name = "txtFileName";
         this.txtFileName.ReadOnly = true;
         this.txtFileName.Size = new System.Drawing.Size(615, 21);
         this.txtFileName.TabIndex = 22;
         // 
         // txtLabel
         // 
         this.txtLabel.BackColor = System.Drawing.SystemColors.Window;
         this.txtLabel.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtLabel.Location = new System.Drawing.Point(54, 10);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.ReadOnly = true;
         this.txtLabel.Size = new System.Drawing.Size(400, 21);
         this.txtLabel.TabIndex = 22;
         // 
         // labFileName
         // 
         this.labFileName.AutoSize = true;
         this.labFileName.Location = new System.Drawing.Point(19, 40);
         this.labFileName.Name = "labFileName";
         this.labFileName.Size = new System.Drawing.Size(29, 12);
         this.labFileName.TabIndex = 20;
         this.labFileName.Text = "路径";
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(19, 13);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 20;
         this.labLabel.Text = "名称";
         // 
         // tsbSave
         // 
         this.tsbSave.Image = ((System.Drawing.Image)(resources.GetObject("tsbSave.Image")));
         this.tsbSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSave.Name = "tsbSave";
         this.tsbSave.Size = new System.Drawing.Size(52, 21);
         this.tsbSave.Text = "保存";
         this.tsbSave.Click += new System.EventHandler(this.tsbSave_Click);
         // 
         // tsbExport
         // 
         this.tsbExport.Image = ((System.Drawing.Image)(resources.GetObject("tsbExport.Image")));
         this.tsbExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExport.Name = "tsbExport";
         this.tsbExport.Size = new System.Drawing.Size(52, 21);
         this.tsbExport.Text = "导出";
         this.tsbExport.Click += new System.EventHandler(this.tsbExport_Click);
         // 
         // tssLine3
         // 
         this.tssLine3.Name = "tssLine3";
         this.tssLine3.Size = new System.Drawing.Size(6, 23);
         // 
         // tsResource
         // 
         this.tsResource.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbFolder,
            this.tsbBtnOpen,
            this.tssLine3,
            this.tsbScaleAuto,
            this.tsbScalex1p4,
            this.tsbScalex1p2,
            this.tsbScalex1,
            this.tsbScalex4,
            this.tsbScalex2,
            this.toolStripSeparator1,
            this.tsbSave,
            this.tsbExport});
         this.tsResource.LayoutStyle = System.Windows.Forms.ToolStripLayoutStyle.Flow;
         this.tsResource.Location = new System.Drawing.Point(0, 0);
         this.tsResource.Name = "tsResource";
         this.tsResource.Padding = new System.Windows.Forms.Padding(6, 0, 1, 0);
         this.tsResource.Size = new System.Drawing.Size(819, 24);
         this.tsResource.TabIndex = 23;
         this.tsResource.Text = "tspMain";
         // 
         // tsbFolder
         // 
         this.tsbFolder.Image = ((System.Drawing.Image)(resources.GetObject("tsbFolder.Image")));
         this.tsbFolder.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbFolder.Name = "tsbFolder";
         this.tsbFolder.Size = new System.Drawing.Size(52, 21);
         this.tsbFolder.Text = "目录";
         this.tsbFolder.Click += new System.EventHandler(this.tsbFolder_Click);
         // 
         // tsbBtnOpen
         // 
         this.tsbBtnOpen.Image = ((System.Drawing.Image)(resources.GetObject("tsbBtnOpen.Image")));
         this.tsbBtnOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbBtnOpen.Name = "tsbBtnOpen";
         this.tsbBtnOpen.Size = new System.Drawing.Size(52, 21);
         this.tsbBtnOpen.Text = "打开";
         this.tsbBtnOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // tsbScaleAuto
         // 
         this.tsbScaleAuto.Checked = true;
         this.tsbScaleAuto.CheckState = System.Windows.Forms.CheckState.Checked;
         this.tsbScaleAuto.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScaleAuto.Name = "tsbScaleAuto";
         this.tsbScaleAuto.Size = new System.Drawing.Size(36, 21);
         this.tsbScaleAuto.Tag = "Auto";
         this.tsbScaleAuto.Text = "自动";
         this.tsbScaleAuto.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1p4
         // 
         this.tsbScalex1p4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p4.Name = "tsbScalex1p4";
         this.tsbScalex1p4.Size = new System.Drawing.Size(37, 21);
         this.tsbScalex1p4.Tag = "0.25";
         this.tsbScalex1p4.Text = "x1/4";
         this.tsbScalex1p4.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1p2
         // 
         this.tsbScalex1p2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p2.Name = "tsbScalex1p2";
         this.tsbScalex1p2.Size = new System.Drawing.Size(37, 21);
         this.tsbScalex1p2.Tag = "0.5";
         this.tsbScalex1p2.Text = "x1/2";
         this.tsbScalex1p2.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1
         // 
         this.tsbScalex1.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1.Name = "tsbScalex1";
         this.tsbScalex1.Size = new System.Drawing.Size(36, 21);
         this.tsbScalex1.Tag = "1";
         this.tsbScalex1.Text = "原始";
         this.tsbScalex1.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex4
         // 
         this.tsbScalex4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex4.Name = "tsbScalex4";
         this.tsbScalex4.Size = new System.Drawing.Size(25, 21);
         this.tsbScalex4.Tag = "4";
         this.tsbScalex4.Text = "x4";
         this.tsbScalex4.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex2
         // 
         this.tsbScalex2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex2.Name = "tsbScalex2";
         this.tsbScalex2.Size = new System.Drawing.Size(25, 21);
         this.tsbScalex2.Tag = "2";
         this.tsbScalex2.Text = "x2";
         this.tsbScalex2.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // dlgOpen
         // 
         this.dlgOpen.DefaultExt = "png";
         // 
         // QDsPictureProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlInfo);
         this.Controls.Add(this.tsResource);
         this.Name = "QDsPictureProperty";
         this.Size = new System.Drawing.Size(819, 451);
         this.pnlInfo.ResumeLayout(false);
         this.pnlDetail.ResumeLayout(false);
         this.pnlDetail.PerformLayout();
         this.groupBox2.ResumeLayout(false);
         this.groupBox2.PerformLayout();
         this.groupBox1.ResumeLayout(false);
         this.groupBox1.PerformLayout();
         this.gbxPalette.ResumeLayout(false);
         this.gbxPalette.PerformLayout();
         this.tsResource.ResumeLayout(false);
         this.tsResource.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.Panel pnlInfo;
      private System.Windows.Forms.Panel pnlDetail;
      private System.Windows.Forms.CheckBox cbxAlpha;
      private System.Windows.Forms.ToolStripButton tsbSave;
      private System.Windows.Forms.ToolStripButton tsbExport;
      private System.Windows.Forms.ToolStripSeparator tssLine3;
      private System.Windows.Forms.ToolStrip tsResource;
      System.Windows.Forms.OpenFileDialog dlgOpen;
      private System.Windows.Forms.GroupBox gbxPalette;
      private System.Windows.Forms.RadioButton rbnPaletteColor256;
      private System.Windows.Forms.RadioButton rbnPaletteColor64;
      private System.Windows.Forms.RadioButton rbnPaletteColor128;
      private System.Windows.Forms.TextBox txtFileName;
      private System.Windows.Forms.Label labFileName;
      private System.Windows.Forms.TextBox txtLabel;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.ToolStripButton tsbBtnOpen;
      private System.Windows.Forms.ToolStripButton tsbScalex1p4;
      private System.Windows.Forms.ToolStripButton tsbScalex1p2;
      private System.Windows.Forms.ToolStripButton tsbScalex1;
      private System.Windows.Forms.ToolStripButton tsbScalex4;
      private System.Windows.Forms.ToolStripButton tsbScalex2;
      private System.Windows.Forms.ToolStripButton tsbScaleAuto;
      private MO.Core.Forms.Controls.QPictureBox qpbImage;
      private System.Windows.Forms.GroupBox groupBox1;
      private System.Windows.Forms.RadioButton ranPalettePixel5;
      private System.Windows.Forms.RadioButton ranPalettePixel1;
      private System.Windows.Forms.RadioButton ranPalettePixel2;
      private System.Windows.Forms.RadioButton ranPalettePixel3;
      private System.Windows.Forms.RadioButton ranPalettePixel4;
      private System.Windows.Forms.CheckBox chkPandding;
      private System.Windows.Forms.GroupBox groupBox2;
      private System.Windows.Forms.RadioButton radLong;
      private System.Windows.Forms.RadioButton radMiddle;
      private System.Windows.Forms.RadioButton radShort;
      private System.Windows.Forms.RadioButton radNull;
      private System.Windows.Forms.TextBox txtGroupName;
      private System.Windows.Forms.Label label2;
      private System.Windows.Forms.Button btnEditGroup;
      private System.Windows.Forms.ToolStripButton tsbFolder;
   }
}
