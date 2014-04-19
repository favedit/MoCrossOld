namespace MO.Design2d.Resource.Controls
{
   partial class QDsResourceDesign
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsResourceDesign));
         this.pnlProperty = new System.Windows.Forms.Panel();
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.qdsAnimation = new MO.Design2d.Resource.Controls.QDsAnimationProperty();
         this.qdsPicture = new MO.Design2d.Resource.Controls.QDsPictureProperty();
         this.pnlInfoHead = new System.Windows.Forms.Panel();
         this.labNameDisplay = new System.Windows.Forms.Label();
         this.pnlBody = new System.Windows.Forms.Panel();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.tbcPages = new System.Windows.Forms.TabControl();
         this.tabPage1 = new System.Windows.Forms.TabPage();
         this.qdsCatalog = new MO.Design2d.Resource.Controls.QDsResourceCatalog();
         this.tabPage2 = new System.Windows.Forms.TabPage();
         this.lvwResources = new System.Windows.Forms.ListView();
         this.chdLabel = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.panel1 = new System.Windows.Forms.Panel();
         this.cbxSearch = new System.Windows.Forms.ComboBox();
         this.pnlProperty.SuspendLayout();
         this.pnlInfo.SuspendLayout();
         this.pnlInfoHead.SuspendLayout();
         this.pnlBody.SuspendLayout();
         this.tbcPages.SuspendLayout();
         this.tabPage1.SuspendLayout();
         this.tabPage2.SuspendLayout();
         this.panel1.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlProperty
         // 
         this.pnlProperty.Controls.Add(this.pnlInfo);
         this.pnlProperty.Controls.Add(this.pnlInfoHead);
         this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlProperty.Location = new System.Drawing.Point(383, 0);
         this.pnlProperty.Name = "pnlProperty";
         this.pnlProperty.Size = new System.Drawing.Size(1217, 800);
         this.pnlProperty.TabIndex = 2;
         // 
         // pnlInfo
         // 
         this.pnlInfo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfo.Controls.Add(this.qdsAnimation);
         this.pnlInfo.Controls.Add(this.qdsPicture);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(0, 32);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(1217, 768);
         this.pnlInfo.TabIndex = 1;
         // 
         // qdsAnimation
         // 
         this.qdsAnimation.AutoSize = true;
         this.qdsAnimation.Location = new System.Drawing.Point(10, 180);
         this.qdsAnimation.Name = "qdsAnimation";
         this.qdsAnimation.Size = new System.Drawing.Size(920, 477);
         this.qdsAnimation.TabIndex = 0;
         // 
         // qdsPicture
         // 
         this.qdsPicture.Location = new System.Drawing.Point(10, 4);
         this.qdsPicture.Name = "qdsPicture";
         this.qdsPicture.Size = new System.Drawing.Size(1070, 170);
         this.qdsPicture.TabIndex = 1;
         // 
         // pnlInfoHead
         // 
         this.pnlInfoHead.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlInfoHead.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfoHead.Controls.Add(this.labNameDisplay);
         this.pnlInfoHead.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlInfoHead.Location = new System.Drawing.Point(0, 0);
         this.pnlInfoHead.Name = "pnlInfoHead";
         this.pnlInfoHead.Size = new System.Drawing.Size(1217, 32);
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
         // pnlBody
         // 
         this.pnlBody.Controls.Add(this.pnlProperty);
         this.pnlBody.Controls.Add(this.splitter1);
         this.pnlBody.Controls.Add(this.tbcPages);
         this.pnlBody.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlBody.Location = new System.Drawing.Point(0, 0);
         this.pnlBody.Name = "pnlBody";
         this.pnlBody.Size = new System.Drawing.Size(1600, 800);
         this.pnlBody.TabIndex = 5;
         // 
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(379, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 800);
         this.splitter1.TabIndex = 1;
         this.splitter1.TabStop = false;
         // 
         // tbcPages
         // 
         this.tbcPages.Appearance = System.Windows.Forms.TabAppearance.FlatButtons;
         this.tbcPages.Controls.Add(this.tabPage1);
         this.tbcPages.Controls.Add(this.tabPage2);
         this.tbcPages.Dock = System.Windows.Forms.DockStyle.Left;
         this.tbcPages.Location = new System.Drawing.Point(0, 0);
         this.tbcPages.Multiline = true;
         this.tbcPages.Name = "tbcPages";
         this.tbcPages.SelectedIndex = 0;
         this.tbcPages.Size = new System.Drawing.Size(379, 800);
         this.tbcPages.TabIndex = 0;
         // 
         // tabPage1
         // 
         this.tabPage1.Controls.Add(this.qdsCatalog);
         this.tabPage1.Location = new System.Drawing.Point(4, 25);
         this.tabPage1.Name = "tabPage1";
         this.tabPage1.Size = new System.Drawing.Size(371, 771);
         this.tabPage1.TabIndex = 0;
         this.tabPage1.Text = "资源目录";
         this.tabPage1.UseVisualStyleBackColor = true;
         // 
         // qdsCatalog
         // 
         this.qdsCatalog.CheckBoxes = true;
         this.qdsCatalog.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdsCatalog.FullRowSelect = true;
         this.qdsCatalog.HideSelection = false;
         this.qdsCatalog.ImageKey = "FloderClose";
         this.qdsCatalog.Indent = 15;
         this.qdsCatalog.IsMultiSelect = false;
         this.qdsCatalog.ItemHeight = 18;
         this.qdsCatalog.LineColor = System.Drawing.Color.Blue;
         this.qdsCatalog.Location = new System.Drawing.Point(0, 0);
         this.qdsCatalog.Name = "qdsCatalog";
         this.qdsCatalog.SelectedImageKey = "FloderOpen";
         this.qdsCatalog.Size = new System.Drawing.Size(371, 771);
         this.qdsCatalog.TabIndex = 0;
         this.qdsCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.qdsCatalog_CatalogAfterSelect);
         // 
         // tabPage2
         // 
         this.tabPage2.Controls.Add(this.lvwResources);
         this.tabPage2.Controls.Add(this.panel1);
         this.tabPage2.Location = new System.Drawing.Point(4, 25);
         this.tabPage2.Name = "tabPage2";
         this.tabPage2.Padding = new System.Windows.Forms.Padding(3);
         this.tabPage2.Size = new System.Drawing.Size(352, 571);
         this.tabPage2.TabIndex = 1;
         this.tabPage2.Text = "资源列表";
         this.tabPage2.UseVisualStyleBackColor = true;
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
         this.lvwResources.Location = new System.Drawing.Point(3, 32);
         this.lvwResources.MultiSelect = false;
         this.lvwResources.Name = "lvwResources";
         this.lvwResources.Size = new System.Drawing.Size(346, 536);
         this.lvwResources.SmallImageList = this.imlMain;
         this.lvwResources.TabIndex = 4;
         this.lvwResources.UseCompatibleStateImageBehavior = false;
         this.lvwResources.View = System.Windows.Forms.View.Details;
         this.lvwResources.SelectedIndexChanged += new System.EventHandler(this.lvwResources_SelectedIndexChanged);
         // 
         // chdLabel
         // 
         this.chdLabel.Text = "资源名称";
         this.chdLabel.Width = 320;
         // 
         // imlMain
         // 
         this.imlMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlMain.ImageStream")));
         this.imlMain.TransparentColor = System.Drawing.Color.Transparent;
         this.imlMain.Images.SetKeyName(0, "FloderClose");
         this.imlMain.Images.SetKeyName(1, "FloderOpen");
         this.imlMain.Images.SetKeyName(2, "Picture");
         this.imlMain.Images.SetKeyName(3, "PictureD");
         this.imlMain.Images.SetKeyName(4, "Animation");
         this.imlMain.Images.SetKeyName(5, "AnimationD");
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.cbxSearch);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel1.Location = new System.Drawing.Point(3, 3);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(346, 29);
         this.panel1.TabIndex = 4;
         // 
         // cbxSearch
         // 
         this.cbxSearch.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.cbxSearch.FormattingEnabled = true;
         this.cbxSearch.Location = new System.Drawing.Point(3, 4);
         this.cbxSearch.Name = "cbxSearch";
         this.cbxSearch.Size = new System.Drawing.Size(340, 20);
         this.cbxSearch.TabIndex = 4;
         this.cbxSearch.KeyUp += new System.Windows.Forms.KeyEventHandler(this.cbxSearch_KeyUp);
         // 
         // QDsResourceDesign
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlBody);
         this.Name = "QDsResourceDesign";
         this.Size = new System.Drawing.Size(1600, 800);
         this.pnlProperty.ResumeLayout(false);
         this.pnlInfo.ResumeLayout(false);
         this.pnlInfo.PerformLayout();
         this.pnlInfoHead.ResumeLayout(false);
         this.pnlInfoHead.PerformLayout();
         this.pnlBody.ResumeLayout(false);
         this.tbcPages.ResumeLayout(false);
         this.tabPage1.ResumeLayout(false);
         this.tabPage2.ResumeLayout(false);
         this.panel1.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.Panel pnlInfo;
      private QDsAnimationProperty qdsAnimation;
      private QDsPictureProperty qdsPicture;
      private System.Windows.Forms.Panel pnlInfoHead;
      private System.Windows.Forms.Label labNameDisplay;
      private System.Windows.Forms.Panel pnlBody;
      private System.Windows.Forms.Splitter splitter1;
      private QDsResourceCatalog qdsCatalog;
      private System.Windows.Forms.TabControl tbcPages;
      private System.Windows.Forms.TabPage tabPage1;
      private System.Windows.Forms.TabPage tabPage2;
      private System.Windows.Forms.ListView lvwResources;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.ComboBox cbxSearch;
      private System.Windows.Forms.ColumnHeader chdLabel;
      private System.Windows.Forms.ImageList imlMain;
   }
}
