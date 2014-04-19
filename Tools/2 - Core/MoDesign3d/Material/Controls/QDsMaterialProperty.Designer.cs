namespace MO.Design3d.Material.Controls
{
   partial class QDsMaterialProperty
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
         this.panel3 = new System.Windows.Forms.Panel();
         this.qgvTextures = new MO.Core.Forms.Controls.QGridView();
         this.dgcTypeName = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcIsValid = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcSource = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcSourceType = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcSourceIndex = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcSize = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcLength = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.splContainer = new System.Windows.Forms.Splitter();
         this.panel4 = new System.Windows.Forms.Panel();
         this.qpbViewer = new MO.Core.Forms.Controls.QPictureBox();
         this.panel2 = new System.Windows.Forms.Panel();
         this.lbxThemes = new System.Windows.Forms.ListBox();
         this.miniToolStrip = new System.Windows.Forms.ToolStrip();
         this.tspSave = new System.Windows.Forms.ToolStripButton();
         this.tsbOpen = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbScalex1p4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScaleAuto = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
         this.panel1 = new System.Windows.Forms.Panel();
         this.tspToolSprit = new System.Windows.Forms.ToolStrip();
         this.qdrMaterialInfo = new MO.Design3d.Material.Controls.QDsMaterialInfoProperty();
         this.qdrMaterialGroup = new MO.Design3d.Material.Controls.QDsMaterialGroupProperty();
         this.panel3.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.qgvTextures)).BeginInit();
         this.panel4.SuspendLayout();
         this.panel2.SuspendLayout();
         this.panel1.SuspendLayout();
         this.tspToolSprit.SuspendLayout();
         this.SuspendLayout();
         // 
         // panel3
         // 
         this.panel3.Controls.Add(this.qgvTextures);
         this.panel3.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel3.Location = new System.Drawing.Point(0, 392);
         this.panel3.Name = "panel3";
         this.panel3.Padding = new System.Windows.Forms.Padding(3);
         this.panel3.Size = new System.Drawing.Size(1153, 110);
         this.panel3.TabIndex = 4;
         // 
         // qgvTextures
         // 
         this.qgvTextures.AllowDrop = true;
         this.qgvTextures.AllowUserToAddRows = false;
         this.qgvTextures.AllowUserToDeleteRows = false;
         this.qgvTextures.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCells;
         this.qgvTextures.ClipboardCopyMode = System.Windows.Forms.DataGridViewClipboardCopyMode.Disable;
         this.qgvTextures.ColumnHeadersHeight = 20;
         this.qgvTextures.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.DisableResizing;
         this.qgvTextures.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dgcTypeName,
            this.dgcIsValid,
            this.dgcSource,
            this.dgcSourceType,
            this.dgcSourceIndex,
            this.dgcSize,
            this.dgcLength});
         this.qgvTextures.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qgvTextures.Location = new System.Drawing.Point(3, 3);
         this.qgvTextures.MultiSelect = false;
         this.qgvTextures.Name = "qgvTextures";
         this.qgvTextures.ReadOnly = true;
         this.qgvTextures.RowHeadersWidth = 20;
         this.qgvTextures.RowTemplate.Height = 16;
         this.qgvTextures.ScrollBars = System.Windows.Forms.ScrollBars.None;
         this.qgvTextures.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
         this.qgvTextures.ShowCellErrors = false;
         this.qgvTextures.Size = new System.Drawing.Size(1147, 104);
         this.qgvTextures.TabIndex = 1;
         this.qgvTextures.RowEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.qgvTextures_RowEnter);
         // 
         // dgcTypeName
         // 
         this.dgcTypeName.FillWeight = 101.7259F;
         this.dgcTypeName.HeaderText = "类型名称";
         this.dgcTypeName.Name = "dgcTypeName";
         this.dgcTypeName.ReadOnly = true;
         this.dgcTypeName.Width = 120;
         // 
         // dgcIsValid
         // 
         this.dgcIsValid.FillWeight = 40F;
         this.dgcIsValid.HeaderText = "是否有效";
         this.dgcIsValid.Name = "dgcIsValid";
         this.dgcIsValid.ReadOnly = true;
         this.dgcIsValid.Width = 80;
         // 
         // dgcSource
         // 
         this.dgcSource.FillWeight = 101.7259F;
         this.dgcSource.HeaderText = "来源";
         this.dgcSource.Name = "dgcSource";
         this.dgcSource.ReadOnly = true;
         this.dgcSource.Width = 250;
         // 
         // dgcSourceType
         // 
         this.dgcSourceType.FillWeight = 101.7259F;
         this.dgcSourceType.HeaderText = "来源类型";
         this.dgcSourceType.Name = "dgcSourceType";
         this.dgcSourceType.ReadOnly = true;
         this.dgcSourceType.Width = 120;
         // 
         // dgcSourceIndex
         // 
         this.dgcSourceIndex.HeaderText = "来源索引";
         this.dgcSourceIndex.Name = "dgcSourceIndex";
         this.dgcSourceIndex.ReadOnly = true;
         this.dgcSourceIndex.Width = 80;
         // 
         // dgcSize
         // 
         this.dgcSize.FillWeight = 101.7259F;
         this.dgcSize.HeaderText = "大小";
         this.dgcSize.Name = "dgcSize";
         this.dgcSize.ReadOnly = true;
         this.dgcSize.Width = 160;
         // 
         // dgcLength
         // 
         this.dgcLength.FillWeight = 101.7259F;
         this.dgcLength.HeaderText = "尺寸";
         this.dgcLength.Name = "dgcLength";
         this.dgcLength.ReadOnly = true;
         this.dgcLength.Width = 160;
         // 
         // splContainer
         // 
         this.splContainer.Dock = System.Windows.Forms.DockStyle.Top;
         this.splContainer.Location = new System.Drawing.Point(0, 502);
         this.splContainer.Name = "splContainer";
         this.splContainer.Size = new System.Drawing.Size(1153, 3);
         this.splContainer.TabIndex = 5;
         this.splContainer.TabStop = false;
         // 
         // panel4
         // 
         this.panel4.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.panel4.Controls.Add(this.qpbViewer);
         this.panel4.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel4.Location = new System.Drawing.Point(0, 505);
         this.panel4.Name = "panel4";
         this.panel4.Size = new System.Drawing.Size(1153, 269);
         this.panel4.TabIndex = 6;
         // 
         // qpbViewer
         // 
         this.qpbViewer.AutoScroll = true;
         this.qpbViewer.AutoSize = true;
         this.qpbViewer.BackColor = System.Drawing.SystemColors.ActiveCaption;
         this.qpbViewer.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qpbViewer.Location = new System.Drawing.Point(0, 0);
         this.qpbViewer.Margin = new System.Windows.Forms.Padding(0);
         this.qpbViewer.Name = "qpbViewer";
         this.qpbViewer.ScaleMode = MO.Core.Forms.Controls.EPictureBoxMode.Auto;
         this.qpbViewer.ScaleValue = 1F;
         this.qpbViewer.Size = new System.Drawing.Size(1149, 265);
         this.qpbViewer.TabIndex = 2;
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.panel4);
         this.panel2.Controls.Add(this.splContainer);
         this.panel2.Controls.Add(this.panel3);
         this.panel2.Controls.Add(this.qdrMaterialInfo);
         this.panel2.Controls.Add(this.lbxThemes);
         this.panel2.Controls.Add(this.qdrMaterialGroup);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel2.Location = new System.Drawing.Point(0, 25);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(1153, 774);
         this.panel2.TabIndex = 5;
         // 
         // lbxThemes
         // 
         this.lbxThemes.Dock = System.Windows.Forms.DockStyle.Top;
         this.lbxThemes.FormattingEnabled = true;
         this.lbxThemes.ItemHeight = 12;
         this.lbxThemes.Items.AddRange(new object[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6"});
         this.lbxThemes.Location = new System.Drawing.Point(0, 150);
         this.lbxThemes.Name = "lbxThemes";
         this.lbxThemes.Size = new System.Drawing.Size(1153, 76);
         this.lbxThemes.TabIndex = 6;
         this.lbxThemes.SelectedIndexChanged += new System.EventHandler(this.lbxThemes_SelectedIndexChanged);
         // 
         // miniToolStrip
         // 
         this.miniToolStrip.AutoSize = false;
         this.miniToolStrip.CanOverflow = false;
         this.miniToolStrip.Dock = System.Windows.Forms.DockStyle.None;
         this.miniToolStrip.GripStyle = System.Windows.Forms.ToolStripGripStyle.Hidden;
         this.miniToolStrip.Location = new System.Drawing.Point(0, 0);
         this.miniToolStrip.Name = "miniToolStrip";
         this.miniToolStrip.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.miniToolStrip.Size = new System.Drawing.Size(1056, 25);
         this.miniToolStrip.TabIndex = 12;
         // 
         // tspSave
         // 
         this.tspSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspSave.Name = "tspSave";
         this.tspSave.Size = new System.Drawing.Size(52, 22);
         this.tspSave.Text = "保存";
         this.tspSave.Click += new System.EventHandler(this.tspSave_Click);
         // 
         // tsbOpen
         // 
         this.tsbOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbOpen.Name = "tsbOpen";
         this.tsbOpen.Size = new System.Drawing.Size(52, 22);
         this.tsbOpen.Text = "打开";
         this.tsbOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbScalex1p4
         // 
         this.tsbScalex1p4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p4.Name = "tsbScalex1p4";
         this.tsbScalex1p4.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p4.Tag = "0.25";
         this.tsbScalex1p4.Text = "x1/4";
         this.tsbScalex1p4.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1p2
         // 
         this.tsbScalex1p2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p2.Name = "tsbScalex1p2";
         this.tsbScalex1p2.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p2.Tag = "0.5";
         this.tsbScalex1p2.Text = "x1/2";
         this.tsbScalex1p2.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1
         // 
         this.tsbScalex1.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1.Name = "tsbScalex1";
         this.tsbScalex1.Size = new System.Drawing.Size(36, 22);
         this.tsbScalex1.Tag = "1";
         this.tsbScalex1.Text = "原始";
         this.tsbScalex1.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex2
         // 
         this.tsbScalex2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex2.Name = "tsbScalex2";
         this.tsbScalex2.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex2.Tag = "2";
         this.tsbScalex2.Text = "x2";
         this.tsbScalex2.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex4
         // 
         this.tsbScalex4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex4.Name = "tsbScalex4";
         this.tsbScalex4.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex4.Tag = "4";
         this.tsbScalex4.Text = "x4";
         this.tsbScalex4.Click += new System.EventHandler(this.tsbScale_Click);
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
         this.tsbScaleAuto.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // toolStripSeparator3
         // 
         this.toolStripSeparator3.Name = "toolStripSeparator3";
         this.toolStripSeparator3.Size = new System.Drawing.Size(6, 25);
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.tspToolSprit);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(1153, 25);
         this.panel1.TabIndex = 4;
         // 
         // tspToolSprit
         // 
         this.tspToolSprit.AutoSize = false;
         this.tspToolSprit.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tspToolSprit.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tspSave,
            this.tsbOpen,
            this.toolStripSeparator1,
            this.tsbScalex1p4,
            this.tsbScalex1p2,
            this.tsbScalex1,
            this.tsbScalex2,
            this.tsbScalex4,
            this.tsbScaleAuto,
            this.toolStripSeparator3});
         this.tspToolSprit.Location = new System.Drawing.Point(0, 0);
         this.tspToolSprit.Name = "tspToolSprit";
         this.tspToolSprit.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspToolSprit.Size = new System.Drawing.Size(1153, 25);
         this.tspToolSprit.TabIndex = 12;
         this.tspToolSprit.Text = "toolStrip1";
         // 
         // qdrMaterialInfo
         // 
         this.qdrMaterialInfo.BackColor = System.Drawing.SystemColors.Control;
         this.qdrMaterialInfo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.qdrMaterialInfo.Dock = System.Windows.Forms.DockStyle.Top;
         this.qdrMaterialInfo.Location = new System.Drawing.Point(0, 226);
         this.qdrMaterialInfo.Material = null;
         this.qdrMaterialInfo.Name = "qdrMaterialInfo";
         this.qdrMaterialInfo.Size = new System.Drawing.Size(1153, 166);
         this.qdrMaterialInfo.TabIndex = 0;
         // 
         // qdrMaterialGroup
         // 
         this.qdrMaterialGroup.BackColor = System.Drawing.SystemColors.Control;
         this.qdrMaterialGroup.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.qdrMaterialGroup.Dock = System.Windows.Forms.DockStyle.Top;
         this.qdrMaterialGroup.Location = new System.Drawing.Point(0, 0);
         this.qdrMaterialGroup.Name = "qdrMaterialGroup";
         this.qdrMaterialGroup.Size = new System.Drawing.Size(1153, 150);
         this.qdrMaterialGroup.TabIndex = 6;
         // 
         // QDrMaterialProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel2);
         this.Controls.Add(this.panel1);
         this.Name = "QDrMaterialProperty";
         this.Size = new System.Drawing.Size(1153, 799);
         this.panel3.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.qgvTextures)).EndInit();
         this.panel4.ResumeLayout(false);
         this.panel4.PerformLayout();
         this.panel2.ResumeLayout(false);
         this.panel1.ResumeLayout(false);
         this.tspToolSprit.ResumeLayout(false);
         this.tspToolSprit.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel panel3;
      private MO.Core.Forms.Controls.QGridView qgvTextures;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcTypeName;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcIsValid;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcSource;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcSourceType;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcSourceIndex;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcSize;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcLength;
      private System.Windows.Forms.Splitter splContainer;
      private System.Windows.Forms.Panel panel4;
      private MO.Core.Forms.Controls.QPictureBox qpbViewer;
      private System.Windows.Forms.Panel panel2;
      private MO.Design3d.Material.Controls.QDsMaterialInfoProperty qdrMaterialInfo;
      public System.Windows.Forms.ToolStrip miniToolStrip;
      private System.Windows.Forms.ToolStripButton tspSave;
      private System.Windows.Forms.ToolStripButton tsbOpen;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbScalex1p4;
      private System.Windows.Forms.ToolStripButton tsbScalex1p2;
      private System.Windows.Forms.ToolStripButton tsbScalex1;
      private System.Windows.Forms.ToolStripButton tsbScalex2;
      private System.Windows.Forms.ToolStripButton tsbScalex4;
      private System.Windows.Forms.ToolStripButton tsbScaleAuto;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
      private System.Windows.Forms.Panel panel1;
      public System.Windows.Forms.ToolStrip tspToolSprit;
      private System.Windows.Forms.ListBox lbxThemes;
      private MO.Design3d.Material.Controls.QDsMaterialGroupProperty qdrMaterialGroup;

   }
}
