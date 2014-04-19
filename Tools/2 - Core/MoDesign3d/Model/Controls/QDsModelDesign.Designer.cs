namespace MO.Design3d.Model.Controls
{
   partial class QDsModelDesign
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsModelDesign));
         this.tpgList = new System.Windows.Forms.TabPage();
         this.lvwResources = new System.Windows.Forms.ListView();
         this.chdLabel = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.panel3 = new System.Windows.Forms.Panel();
         this.cbxSearch = new System.Windows.Forms.ComboBox();
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.qdrModelProperty = new MO.Design3d.Model.Controls.QDsModelProperty();
         this.tvwCatalog = new MO.Core.Forms.Controls.QTreeView();
         this.pnlProperty = new System.Windows.Forms.Panel();
         this.pnlInfoHead = new System.Windows.Forms.Panel();
         this.labNameDisplay = new System.Windows.Forms.Label();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.tpgCatalog = new System.Windows.Forms.TabPage();
         this.tspCatalog = new System.Windows.Forms.ToolStrip();
         this.tsbAdd = new System.Windows.Forms.ToolStripButton();
         this.tsbExpend = new System.Windows.Forms.ToolStripButton();
         this.tsbCollapse = new System.Windows.Forms.ToolStripButton();
         this.tbcPages = new System.Windows.Forms.TabControl();
         this.tpgList.SuspendLayout();
         this.panel3.SuspendLayout();
         this.pnlInfo.SuspendLayout();
         this.pnlProperty.SuspendLayout();
         this.pnlInfoHead.SuspendLayout();
         this.tpgCatalog.SuspendLayout();
         this.tspCatalog.SuspendLayout();
         this.tbcPages.SuspendLayout();
         this.SuspendLayout();
         // 
         // tpgList
         // 
         this.tpgList.Controls.Add(this.lvwResources);
         this.tpgList.Controls.Add(this.panel3);
         this.tpgList.Location = new System.Drawing.Point(4, 25);
         this.tpgList.Name = "tpgList";
         this.tpgList.Size = new System.Drawing.Size(306, 626);
         this.tpgList.TabIndex = 1;
         this.tpgList.Text = " 列表 ";
         this.tpgList.UseVisualStyleBackColor = true;
         // 
         // lvwResources
         // 
         this.lvwResources.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.lvwResources.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdLabel});
         this.lvwResources.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwResources.FullRowSelect = true;
         this.lvwResources.HideSelection = false;
         this.lvwResources.LargeImageList = this.imlMain;
         this.lvwResources.Location = new System.Drawing.Point(0, 29);
         this.lvwResources.MultiSelect = false;
         this.lvwResources.Name = "lvwResources";
         this.lvwResources.Size = new System.Drawing.Size(306, 597);
         this.lvwResources.StateImageList = this.imlMain;
         this.lvwResources.TabIndex = 8;
         this.lvwResources.UseCompatibleStateImageBehavior = false;
         this.lvwResources.View = System.Windows.Forms.View.Details;
         // 
         // chdLabel
         // 
         this.chdLabel.Text = "名称";
         this.chdLabel.Width = 320;
         // 
         // imlMain
         // 
         this.imlMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlMain.ImageStream")));
         this.imlMain.TransparentColor = System.Drawing.Color.Transparent;
         this.imlMain.Images.SetKeyName(0, "Floder");
         this.imlMain.Images.SetKeyName(1, "FloderOpen");
         this.imlMain.Images.SetKeyName(2, "Model");
         // 
         // panel3
         // 
         this.panel3.Controls.Add(this.cbxSearch);
         this.panel3.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel3.Location = new System.Drawing.Point(0, 0);
         this.panel3.Name = "panel3";
         this.panel3.Size = new System.Drawing.Size(306, 29);
         this.panel3.TabIndex = 7;
         // 
         // cbxSearch
         // 
         this.cbxSearch.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.cbxSearch.FormattingEnabled = true;
         this.cbxSearch.Location = new System.Drawing.Point(3, 4);
         this.cbxSearch.Name = "cbxSearch";
         this.cbxSearch.Size = new System.Drawing.Size(300, 20);
         this.cbxSearch.TabIndex = 4;
         // 
         // pnlInfo
         // 
         this.pnlInfo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfo.Controls.Add(this.qdrModelProperty);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(0, 32);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(770, 623);
         this.pnlInfo.TabIndex = 1;
         // 
         // qdrModelProperty
         // 
         this.qdrModelProperty.Location = new System.Drawing.Point(13, 16);
         this.qdrModelProperty.Name = "qdrModelProperty";
         this.qdrModelProperty.Size = new System.Drawing.Size(694, 537);
         this.qdrModelProperty.TabIndex = 0;
         // 
         // tvwCatalog
         // 
         this.tvwCatalog.BorderStyle = System.Windows.Forms.BorderStyle.None;
         this.tvwCatalog.CheckBoxes = true;
         this.tvwCatalog.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tvwCatalog.ForeColor = System.Drawing.SystemColors.InfoText;
         this.tvwCatalog.FullRowSelect = true;
         this.tvwCatalog.HideSelection = false;
         this.tvwCatalog.ImageIndex = 0;
         this.tvwCatalog.ImageList = this.imlMain;
         this.tvwCatalog.Indent = 15;
         this.tvwCatalog.IsMultiSelect = false;
         this.tvwCatalog.ItemHeight = 18;
         this.tvwCatalog.Location = new System.Drawing.Point(0, 25);
         this.tvwCatalog.Name = "tvwCatalog";
         this.tvwCatalog.PathSeparator = "-";
         this.tvwCatalog.SelectedImageIndex = 0;
         this.tvwCatalog.ShowRootLines = false;
         this.tvwCatalog.Size = new System.Drawing.Size(306, 601);
         this.tvwCatalog.TabIndex = 3;
         this.tvwCatalog.AfterCheck += new System.Windows.Forms.TreeViewEventHandler(this.tvwCatalog_AfterCheck);
         this.tvwCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.tvwCatalog_AfterSelect);
         // 
         // pnlProperty
         // 
         this.pnlProperty.Controls.Add(this.pnlInfo);
         this.pnlProperty.Controls.Add(this.pnlInfoHead);
         this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlProperty.Location = new System.Drawing.Point(317, 0);
         this.pnlProperty.Name = "pnlProperty";
         this.pnlProperty.Size = new System.Drawing.Size(770, 655);
         this.pnlProperty.TabIndex = 11;
         // 
         // pnlInfoHead
         // 
         this.pnlInfoHead.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlInfoHead.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfoHead.Controls.Add(this.labNameDisplay);
         this.pnlInfoHead.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlInfoHead.Location = new System.Drawing.Point(0, 0);
         this.pnlInfoHead.Name = "pnlInfoHead";
         this.pnlInfoHead.Size = new System.Drawing.Size(770, 32);
         this.pnlInfoHead.TabIndex = 0;
         // 
         // labNameDisplay
         // 
         this.labNameDisplay.AutoSize = true;
         this.labNameDisplay.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
         this.labNameDisplay.ForeColor = System.Drawing.Color.White;
         this.labNameDisplay.Location = new System.Drawing.Point(8, 8);
         this.labNameDisplay.Name = "labNameDisplay";
         this.labNameDisplay.Size = new System.Drawing.Size(44, 12);
         this.labNameDisplay.TabIndex = 0;
         this.labNameDisplay.Text = "名称：";
         // 
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(314, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(3, 655);
         this.splitter1.TabIndex = 10;
         this.splitter1.TabStop = false;
         // 
         // tpgCatalog
         // 
         this.tpgCatalog.Controls.Add(this.tvwCatalog);
         this.tpgCatalog.Controls.Add(this.tspCatalog);
         this.tpgCatalog.Location = new System.Drawing.Point(4, 25);
         this.tpgCatalog.Name = "tpgCatalog";
         this.tpgCatalog.Size = new System.Drawing.Size(306, 626);
         this.tpgCatalog.TabIndex = 0;
         this.tpgCatalog.Text = " 目录 ";
         this.tpgCatalog.UseVisualStyleBackColor = true;
         // 
         // tspCatalog
         // 
         this.tspCatalog.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbAdd,
            this.tsbExpend,
            this.tsbCollapse});
         this.tspCatalog.Location = new System.Drawing.Point(0, 0);
         this.tspCatalog.Name = "tspCatalog";
         this.tspCatalog.Size = new System.Drawing.Size(306, 25);
         this.tspCatalog.TabIndex = 5;
         this.tspCatalog.Text = "toolStrip1";
         // 
         // tsbAdd
         // 
         this.tsbAdd.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbAdd.Name = "tsbAdd";
         this.tsbAdd.Size = new System.Drawing.Size(36, 22);
         this.tsbAdd.Tag = "Txture";
         this.tsbAdd.Text = "添加";
         // 
         // tsbExpend
         // 
         this.tsbExpend.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExpend.Name = "tsbExpend";
         this.tsbExpend.Size = new System.Drawing.Size(36, 22);
         this.tsbExpend.Tag = "Txture";
         this.tsbExpend.Text = "展开";
         this.tsbExpend.Click += new System.EventHandler(this.tsbExpend_Click);
         // 
         // tsbCollapse
         // 
         this.tsbCollapse.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbCollapse.Name = "tsbCollapse";
         this.tsbCollapse.Size = new System.Drawing.Size(36, 22);
         this.tsbCollapse.Tag = "Txture";
         this.tsbCollapse.Text = "收起";
         this.tsbCollapse.Click += new System.EventHandler(this.tsbCollapse_Click);
         // 
         // tbcPages
         // 
         this.tbcPages.Appearance = System.Windows.Forms.TabAppearance.FlatButtons;
         this.tbcPages.Controls.Add(this.tpgCatalog);
         this.tbcPages.Controls.Add(this.tpgList);
         this.tbcPages.Dock = System.Windows.Forms.DockStyle.Left;
         this.tbcPages.Location = new System.Drawing.Point(0, 0);
         this.tbcPages.Name = "tbcPages";
         this.tbcPages.SelectedIndex = 0;
         this.tbcPages.Size = new System.Drawing.Size(314, 655);
         this.tbcPages.TabIndex = 12;
         // 
         // QDsModelDesign
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlProperty);
         this.Controls.Add(this.splitter1);
         this.Controls.Add(this.tbcPages);
         this.Name = "QDsModelDesign";
         this.Size = new System.Drawing.Size(1087, 655);
         this.tpgList.ResumeLayout(false);
         this.panel3.ResumeLayout(false);
         this.pnlInfo.ResumeLayout(false);
         this.pnlProperty.ResumeLayout(false);
         this.pnlInfoHead.ResumeLayout(false);
         this.pnlInfoHead.PerformLayout();
         this.tpgCatalog.ResumeLayout(false);
         this.tpgCatalog.PerformLayout();
         this.tspCatalog.ResumeLayout(false);
         this.tspCatalog.PerformLayout();
         this.tbcPages.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.TabPage tpgList;
      private System.Windows.Forms.ListView lvwResources;
      private System.Windows.Forms.ColumnHeader chdLabel;
      private System.Windows.Forms.ImageList imlMain;
      private System.Windows.Forms.Panel panel3;
      private System.Windows.Forms.ComboBox cbxSearch;
      private System.Windows.Forms.Panel pnlInfo;
      private MO.Core.Forms.Controls.QTreeView tvwCatalog;
      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.Panel pnlInfoHead;
      private System.Windows.Forms.Label labNameDisplay;
      private System.Windows.Forms.Splitter splitter1;
      private System.Windows.Forms.TabPage tpgCatalog;
      private System.Windows.Forms.ToolStrip tspCatalog;
      private System.Windows.Forms.ToolStripButton tsbAdd;
      private System.Windows.Forms.ToolStripButton tsbExpend;
      private System.Windows.Forms.ToolStripButton tsbCollapse;
      private System.Windows.Forms.TabControl tbcPages;
      private MO.Design3d.Model.Controls.QDsModelProperty qdrModelProperty;
   }
}
