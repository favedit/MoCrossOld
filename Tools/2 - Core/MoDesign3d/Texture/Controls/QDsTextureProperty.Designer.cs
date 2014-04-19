namespace MO.Design3d.Texture.Controls
{
   partial class QDsTextureProperty
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsTextureProperty));
         this.pnlBody = new System.Windows.Forms.Panel();
         this.panel6 = new System.Windows.Forms.Panel();
         this.qgvTextures = new MO.Core.Forms.Controls.QGridView();
         this.dgcBitmapIndex = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcBitmapTypeName = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcBitmapSize = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcBitmapLength = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.dgcBitmapSource = new System.Windows.Forms.DataGridViewTextBoxColumn();
         this.pnlProperty = new System.Windows.Forms.Panel();
         this.txtLabel = new System.Windows.Forms.TextBox();
         this.labLabel = new System.Windows.Forms.Label();
         this.txtCode = new System.Windows.Forms.TextBox();
         this.labCode = new System.Windows.Forms.Label();
         this.pnlViewer = new System.Windows.Forms.Panel();
         this.qpbViewer = new MO.Core.Forms.Controls.QPictureBox();
         this.spcContainer = new System.Windows.Forms.SplitContainer();
         this.pnlTools = new System.Windows.Forms.Panel();
         this.tspToolSprit = new System.Windows.Forms.ToolStrip();
         this.tspSave = new System.Windows.Forms.ToolStripButton();
         this.tsbOpen = new System.Windows.Forms.ToolStripButton();
         this.tspLine1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbScalex1p8 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex8 = new System.Windows.Forms.ToolStripButton();
         this.tsbScaleAuto = new System.Windows.Forms.ToolStripButton();
         this.tspLine2 = new System.Windows.Forms.ToolStripSeparator();
         this.tspExportMerge = new System.Windows.Forms.ToolStripButton();
         this.tspExportResource = new System.Windows.Forms.ToolStripButton();
         this.panel4 = new System.Windows.Forms.Panel();
         this.pnlBody.SuspendLayout();
         this.panel6.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.qgvTextures)).BeginInit();
         this.pnlProperty.SuspendLayout();
         this.pnlViewer.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.spcContainer)).BeginInit();
         this.spcContainer.Panel1.SuspendLayout();
         this.spcContainer.Panel2.SuspendLayout();
         this.spcContainer.SuspendLayout();
         this.pnlTools.SuspendLayout();
         this.tspToolSprit.SuspendLayout();
         this.panel4.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlBody
         // 
         this.pnlBody.Controls.Add(this.panel6);
         this.pnlBody.Controls.Add(this.pnlProperty);
         this.pnlBody.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlBody.Location = new System.Drawing.Point(0, 0);
         this.pnlBody.Name = "pnlBody";
         this.pnlBody.Size = new System.Drawing.Size(992, 311);
         this.pnlBody.TabIndex = 2;
         // 
         // panel6
         // 
         this.panel6.Controls.Add(this.qgvTextures);
         this.panel6.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel6.Location = new System.Drawing.Point(0, 59);
         this.panel6.Name = "panel6";
         this.panel6.Padding = new System.Windows.Forms.Padding(3);
         this.panel6.Size = new System.Drawing.Size(992, 252);
         this.panel6.TabIndex = 2;
         // 
         // qgvTextures
         // 
         this.qgvTextures.AllowDrop = true;
         this.qgvTextures.AllowUserToAddRows = false;
         this.qgvTextures.AllowUserToDeleteRows = false;
         this.qgvTextures.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.DisplayedCells;
         this.qgvTextures.ClipboardCopyMode = System.Windows.Forms.DataGridViewClipboardCopyMode.Disable;
         this.qgvTextures.ColumnHeadersHeight = 21;
         this.qgvTextures.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.DisableResizing;
         this.qgvTextures.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dgcBitmapIndex,
            this.dgcBitmapTypeName,
            this.dgcBitmapSize,
            this.dgcBitmapLength,
            this.dgcBitmapSource});
         this.qgvTextures.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qgvTextures.Location = new System.Drawing.Point(3, 3);
         this.qgvTextures.MultiSelect = false;
         this.qgvTextures.Name = "qgvTextures";
         this.qgvTextures.ReadOnly = true;
         this.qgvTextures.RowHeadersWidth = 20;
         this.qgvTextures.RowHeadersWidthSizeMode = System.Windows.Forms.DataGridViewRowHeadersWidthSizeMode.DisableResizing;
         this.qgvTextures.RowTemplate.Height = 16;
         this.qgvTextures.ScrollBars = System.Windows.Forms.ScrollBars.None;
         this.qgvTextures.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
         this.qgvTextures.ShowCellErrors = false;
         this.qgvTextures.Size = new System.Drawing.Size(986, 246);
         this.qgvTextures.TabIndex = 0;
         this.qgvTextures.RowEnter += new System.Windows.Forms.DataGridViewCellEventHandler(this.qgvTextures_RowEnter);
         // 
         // dgcBitmapIndex
         // 
         this.dgcBitmapIndex.HeaderText = "索引";
         this.dgcBitmapIndex.Name = "dgcBitmapIndex";
         this.dgcBitmapIndex.ReadOnly = true;
         this.dgcBitmapIndex.Width = 40;
         // 
         // dgcBitmapTypeName
         // 
         this.dgcBitmapTypeName.FillWeight = 20F;
         this.dgcBitmapTypeName.HeaderText = "类型";
         this.dgcBitmapTypeName.Name = "dgcBitmapTypeName";
         this.dgcBitmapTypeName.ReadOnly = true;
         this.dgcBitmapTypeName.Width = 237;
         // 
         // dgcBitmapSize
         // 
         this.dgcBitmapSize.FillWeight = 10F;
         this.dgcBitmapSize.HeaderText = "尺寸";
         this.dgcBitmapSize.Name = "dgcBitmapSize";
         this.dgcBitmapSize.ReadOnly = true;
         this.dgcBitmapSize.Width = 119;
         // 
         // dgcBitmapLength
         // 
         this.dgcBitmapLength.FillWeight = 10F;
         this.dgcBitmapLength.HeaderText = "大小";
         this.dgcBitmapLength.Name = "dgcBitmapLength";
         this.dgcBitmapLength.ReadOnly = true;
         this.dgcBitmapLength.Width = 119;
         // 
         // dgcBitmapSource
         // 
         this.dgcBitmapSource.FillWeight = 40F;
         this.dgcBitmapSource.HeaderText = "来源";
         this.dgcBitmapSource.Name = "dgcBitmapSource";
         this.dgcBitmapSource.ReadOnly = true;
         this.dgcBitmapSource.Width = 474;
         // 
         // pnlProperty
         // 
         this.pnlProperty.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlProperty.Controls.Add(this.txtLabel);
         this.pnlProperty.Controls.Add(this.labLabel);
         this.pnlProperty.Controls.Add(this.txtCode);
         this.pnlProperty.Controls.Add(this.labCode);
         this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlProperty.Location = new System.Drawing.Point(0, 0);
         this.pnlProperty.Name = "pnlProperty";
         this.pnlProperty.Size = new System.Drawing.Size(992, 59);
         this.pnlProperty.TabIndex = 1;
         // 
         // txtLabel
         // 
         this.txtLabel.BackColor = System.Drawing.SystemColors.Window;
         this.txtLabel.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtLabel.Location = new System.Drawing.Point(56, 30);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.ReadOnly = true;
         this.txtLabel.Size = new System.Drawing.Size(371, 21);
         this.txtLabel.TabIndex = 3;
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(21, 33);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 2;
         this.labLabel.Text = "标签";
         // 
         // txtCode
         // 
         this.txtCode.BackColor = System.Drawing.SystemColors.Window;
         this.txtCode.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtCode.Location = new System.Drawing.Point(56, 3);
         this.txtCode.Name = "txtCode";
         this.txtCode.ReadOnly = true;
         this.txtCode.Size = new System.Drawing.Size(371, 21);
         this.txtCode.TabIndex = 1;
         // 
         // labCode
         // 
         this.labCode.AutoSize = true;
         this.labCode.Location = new System.Drawing.Point(21, 8);
         this.labCode.Name = "labCode";
         this.labCode.Size = new System.Drawing.Size(29, 12);
         this.labCode.TabIndex = 0;
         this.labCode.Text = "代码";
         // 
         // pnlViewer
         // 
         this.pnlViewer.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlViewer.Controls.Add(this.qpbViewer);
         this.pnlViewer.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlViewer.Location = new System.Drawing.Point(0, 0);
         this.pnlViewer.Margin = new System.Windows.Forms.Padding(0);
         this.pnlViewer.Name = "pnlViewer";
         this.pnlViewer.Size = new System.Drawing.Size(992, 307);
         this.pnlViewer.TabIndex = 3;
         // 
         // qpbViewer
         // 
         this.qpbViewer.AutoScroll = true;
         this.qpbViewer.AutoSize = true;
         this.qpbViewer.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
         this.qpbViewer.BackColor = System.Drawing.SystemColors.ActiveCaption;
         this.qpbViewer.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qpbViewer.Location = new System.Drawing.Point(0, 0);
         this.qpbViewer.Margin = new System.Windows.Forms.Padding(0);
         this.qpbViewer.Name = "qpbViewer";
         this.qpbViewer.ScaleMode = MO.Core.Forms.Controls.EPictureBoxMode.Auto;
         this.qpbViewer.ScaleValue = 1F;
         this.qpbViewer.Size = new System.Drawing.Size(988, 303);
         this.qpbViewer.TabIndex = 0;
         // 
         // spcContainer
         // 
         this.spcContainer.Dock = System.Windows.Forms.DockStyle.Fill;
         this.spcContainer.FixedPanel = System.Windows.Forms.FixedPanel.Panel1;
         this.spcContainer.Location = new System.Drawing.Point(0, 0);
         this.spcContainer.Name = "spcContainer";
         this.spcContainer.Orientation = System.Windows.Forms.Orientation.Horizontal;
         // 
         // spcContainer.Panel1
         // 
         this.spcContainer.Panel1.Controls.Add(this.pnlBody);
         this.spcContainer.Panel1MinSize = 180;
         // 
         // spcContainer.Panel2
         // 
         this.spcContainer.Panel2.Controls.Add(this.pnlViewer);
         this.spcContainer.Size = new System.Drawing.Size(992, 622);
         this.spcContainer.SplitterDistance = 311;
         this.spcContainer.TabIndex = 4;
         // 
         // pnlTools
         // 
         this.pnlTools.Controls.Add(this.tspToolSprit);
         this.pnlTools.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlTools.Location = new System.Drawing.Point(0, 0);
         this.pnlTools.Name = "pnlTools";
         this.pnlTools.Size = new System.Drawing.Size(992, 25);
         this.pnlTools.TabIndex = 5;
         // 
         // tspToolSprit
         // 
         this.tspToolSprit.AutoSize = false;
         this.tspToolSprit.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tspToolSprit.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tspSave,
            this.tsbOpen,
            this.tspLine1,
            this.tsbScalex1p8,
            this.tsbScalex1p4,
            this.tsbScalex1p2,
            this.tsbScalex1,
            this.tsbScalex2,
            this.tsbScalex4,
            this.tsbScalex8,
            this.tsbScaleAuto,
            this.tspLine2,
            this.tspExportMerge,
            this.tspExportResource});
         this.tspToolSprit.Location = new System.Drawing.Point(0, 0);
         this.tspToolSprit.Name = "tspToolSprit";
         this.tspToolSprit.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspToolSprit.Size = new System.Drawing.Size(992, 25);
         this.tspToolSprit.TabIndex = 11;
         this.tspToolSprit.Text = "toolStrip1";
         // 
         // tspSave
         // 
         this.tspSave.Image = ((System.Drawing.Image)(resources.GetObject("tspSave.Image")));
         this.tspSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspSave.Name = "tspSave";
         this.tspSave.Size = new System.Drawing.Size(52, 22);
         this.tspSave.Text = "保存";
         this.tspSave.Click += new System.EventHandler(this.tspSave_Click);
         // 
         // tsbOpen
         // 
         this.tsbOpen.Image = ((System.Drawing.Image)(resources.GetObject("tsbOpen.Image")));
         this.tsbOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbOpen.Name = "tsbOpen";
         this.tsbOpen.Size = new System.Drawing.Size(52, 22);
         this.tsbOpen.Text = "打开";
         this.tsbOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // tspLine1
         // 
         this.tspLine1.Name = "tspLine1";
         this.tspLine1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbScalex1p8
         // 
         this.tsbScalex1p8.BackColor = System.Drawing.SystemColors.Control;
         this.tsbScalex1p8.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p8.Name = "tsbScalex1p8";
         this.tsbScalex1p8.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p8.Tag = "0.125";
         this.tsbScalex1p8.Text = "x1/8";
         this.tsbScalex1p8.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1p4
         // 
         this.tsbScalex1p4.BackColor = System.Drawing.SystemColors.Control;
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
         // tsbScalex8
         // 
         this.tsbScalex8.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex8.Name = "tsbScalex8";
         this.tsbScalex8.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex8.Tag = "8";
         this.tsbScalex8.Text = "x8";
         this.tsbScalex8.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScaleAuto
         // 
         this.tsbScaleAuto.Checked = true;
         this.tsbScaleAuto.CheckState = System.Windows.Forms.CheckState.Checked;
         this.tsbScaleAuto.Image = ((System.Drawing.Image)(resources.GetObject("tsbScaleAuto.Image")));
         this.tsbScaleAuto.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
         this.tsbScaleAuto.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScaleAuto.Name = "tsbScaleAuto";
         this.tsbScaleAuto.Size = new System.Drawing.Size(52, 22);
         this.tsbScaleAuto.Tag = "Auto";
         this.tsbScaleAuto.Text = "自动";
         this.tsbScaleAuto.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tspLine2
         // 
         this.tspLine2.Name = "tspLine2";
         this.tspLine2.Size = new System.Drawing.Size(6, 25);
         // 
         // tspExportMerge
         // 
         this.tspExportMerge.Image = ((System.Drawing.Image)(resources.GetObject("tspExportMerge.Image")));
         this.tspExportMerge.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
         this.tspExportMerge.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExportMerge.Name = "tspExportMerge";
         this.tspExportMerge.Size = new System.Drawing.Size(76, 22);
         this.tspExportMerge.Text = "导出合并";
         this.tspExportMerge.Click += new System.EventHandler(this.tspExportMerge_Click);
         // 
         // tspExportResource
         // 
         this.tspExportResource.Image = ((System.Drawing.Image)(resources.GetObject("tspExportResource.Image")));
         this.tspExportResource.ImageScaling = System.Windows.Forms.ToolStripItemImageScaling.None;
         this.tspExportResource.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExportResource.Name = "tspExportResource";
         this.tspExportResource.Size = new System.Drawing.Size(76, 22);
         this.tspExportResource.Text = "导出资源";
         this.tspExportResource.Click += new System.EventHandler(this.tspExportResource_Click);
         // 
         // panel4
         // 
         this.panel4.Controls.Add(this.spcContainer);
         this.panel4.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel4.Location = new System.Drawing.Point(0, 25);
         this.panel4.Name = "panel4";
         this.panel4.Size = new System.Drawing.Size(992, 622);
         this.panel4.TabIndex = 6;
         // 
         // QDsTextureProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel4);
         this.Controls.Add(this.pnlTools);
         this.Name = "QDsTextureProperty";
         this.Size = new System.Drawing.Size(992, 647);
         this.pnlBody.ResumeLayout(false);
         this.panel6.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.qgvTextures)).EndInit();
         this.pnlProperty.ResumeLayout(false);
         this.pnlProperty.PerformLayout();
         this.pnlViewer.ResumeLayout(false);
         this.pnlViewer.PerformLayout();
         this.spcContainer.Panel1.ResumeLayout(false);
         this.spcContainer.Panel2.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.spcContainer)).EndInit();
         this.spcContainer.ResumeLayout(false);
         this.pnlTools.ResumeLayout(false);
         this.tspToolSprit.ResumeLayout(false);
         this.tspToolSprit.PerformLayout();
         this.panel4.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlBody;
      private System.Windows.Forms.Panel pnlViewer;
      private System.Windows.Forms.SplitContainer spcContainer;
      private System.Windows.Forms.Panel pnlTools;
      private System.Windows.Forms.ToolStripButton tspSave;
      private System.Windows.Forms.ToolStripButton tspExportResource;
      private System.Windows.Forms.Panel panel4;
      public System.Windows.Forms.ToolStrip tspToolSprit;
      private MO.Core.Forms.Controls.QGridView qgvTextures;
      private System.Windows.Forms.Panel panel6;
      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.Label labCode;
      private System.Windows.Forms.TextBox txtLabel;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.TextBox txtCode;
      private System.Windows.Forms.ToolStripButton tsbOpen;
      private MO.Core.Forms.Controls.QPictureBox qpbViewer;
      private System.Windows.Forms.ToolStripSeparator tspLine1;
      private System.Windows.Forms.ToolStripButton tsbScalex1p4;
      private System.Windows.Forms.ToolStripButton tsbScalex1p2;
      private System.Windows.Forms.ToolStripButton tsbScalex1;
      private System.Windows.Forms.ToolStripButton tsbScalex2;
      private System.Windows.Forms.ToolStripButton tsbScalex4;
      private System.Windows.Forms.ToolStripButton tsbScaleAuto;
      private System.Windows.Forms.ToolStripSeparator tspLine2;
      private System.Windows.Forms.ToolStripButton tsbScalex1p8;
      private System.Windows.Forms.ToolStripButton tsbScalex8;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcBitmapIndex;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcBitmapTypeName;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcBitmapSize;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcBitmapLength;
      private System.Windows.Forms.DataGridViewTextBoxColumn dgcBitmapSource;
      private System.Windows.Forms.ToolStripButton tspExportMerge;
   }
}
