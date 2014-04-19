namespace MO.Design3d.ResourceGroup.Controls
{
   partial class QDsResourceGroupDesign
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
         this.pnlInfoHead = new System.Windows.Forms.Panel();
         this.labNameDisplay = new System.Windows.Forms.Label();
         this.panInfo = new System.Windows.Forms.Panel();
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.qdsPicture = new MO.Design3d.Controls.QDsPictureProperty();
         this.qdsGrpProperty = new MO.Design3d.ResourceGroup.Controls.QDsResourceGroupProperty();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.panel1 = new System.Windows.Forms.Panel();
         this.tvwCatalog = new System.Windows.Forms.TreeView();
         this.panel2 = new System.Windows.Forms.Panel();
         this.cbxResource = new System.Windows.Forms.ComboBox();
         this.labResource = new System.Windows.Forms.Label();
         this.labGroup = new System.Windows.Forms.Label();
         this.cbxGroupSearch = new System.Windows.Forms.ComboBox();
         this.pnlInfoHead.SuspendLayout();
         this.panInfo.SuspendLayout();
         this.pnlInfo.SuspendLayout();
         this.panel1.SuspendLayout();
         this.panel2.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlInfoHead
         // 
         this.pnlInfoHead.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlInfoHead.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfoHead.Controls.Add(this.labNameDisplay);
         this.pnlInfoHead.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlInfoHead.Location = new System.Drawing.Point(0, 0);
         this.pnlInfoHead.Name = "pnlInfoHead";
         this.pnlInfoHead.Size = new System.Drawing.Size(624, 32);
         this.pnlInfoHead.TabIndex = 3;
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
         // panInfo
         // 
         this.panInfo.Controls.Add(this.pnlInfo);
         this.panInfo.Controls.Add(this.pnlInfoHead);
         this.panInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panInfo.Location = new System.Drawing.Point(350, 0);
         this.panInfo.Name = "panInfo";
         this.panInfo.Size = new System.Drawing.Size(624, 568);
         this.panInfo.TabIndex = 6;
         // 
         // pnlInfo
         // 
         this.pnlInfo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfo.Controls.Add(this.qdsPicture);
         this.pnlInfo.Controls.Add(this.qdsGrpProperty);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(0, 32);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(624, 536);
         this.pnlInfo.TabIndex = 7;
         // 
         // qdsPicture
         // 
         this.qdsPicture.Location = new System.Drawing.Point(19, 164);
         this.qdsPicture.Name = "qdsPicture";
         this.qdsPicture.Size = new System.Drawing.Size(558, 121);
         this.qdsPicture.TabIndex = 6;
         // 
         // qdsGrpProperty
         // 
         this.qdsGrpProperty.Location = new System.Drawing.Point(19, 21);
         this.qdsGrpProperty.Name = "qdsGrpProperty";
         this.qdsGrpProperty.Size = new System.Drawing.Size(558, 117);
         this.qdsGrpProperty.TabIndex = 4;
         // 
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(346, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 568);
         this.splitter1.TabIndex = 5;
         this.splitter1.TabStop = false;
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.tvwCatalog);
         this.panel1.Controls.Add(this.panel2);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Left;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(346, 568);
         this.panel1.TabIndex = 4;
         // 
         // tvwCatalog
         // 
         this.tvwCatalog.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tvwCatalog.ImageKey = "FloderClose";
         this.tvwCatalog.ImageList = this.imlMain;
         this.tvwCatalog.Location = new System.Drawing.Point(0, 55);
         this.tvwCatalog.Name = "tvwCatalog";
         this.tvwCatalog.SelectedImageKey = "FloderOpen";
         this.tvwCatalog.Size = new System.Drawing.Size(346, 513);
         this.tvwCatalog.TabIndex = 6;
         this.tvwCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.qDsGroupCatalog_CatalogAfterSelect);
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.cbxResource);
         this.panel2.Controls.Add(this.labResource);
         this.panel2.Controls.Add(this.labGroup);
         this.panel2.Controls.Add(this.cbxGroupSearch);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel2.Location = new System.Drawing.Point(0, 0);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(346, 55);
         this.panel2.TabIndex = 5;
         // 
         // cbxResource
         // 
         this.cbxResource.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
            | System.Windows.Forms.AnchorStyles.Right)));
         this.cbxResource.FormattingEnabled = true;
         this.cbxResource.Location = new System.Drawing.Point(61, 29);
         this.cbxResource.Name = "cbxResource";
         this.cbxResource.Size = new System.Drawing.Size(282, 20);
         this.cbxResource.TabIndex = 7;
         this.cbxResource.KeyUp += new System.Windows.Forms.KeyEventHandler(this.cbxResource_KeyUp);
         // 
         // labResource
         // 
         this.labResource.AutoSize = true;
         this.labResource.Location = new System.Drawing.Point(14, 32);
         this.labResource.Name = "labResource";
         this.labResource.Size = new System.Drawing.Size(29, 12);
         this.labResource.TabIndex = 6;
         this.labResource.Text = "资源";
         // 
         // labGroup
         // 
         this.labGroup.AutoSize = true;
         this.labGroup.Location = new System.Drawing.Point(14, 7);
         this.labGroup.Name = "labGroup";
         this.labGroup.Size = new System.Drawing.Size(41, 12);
         this.labGroup.TabIndex = 5;
         this.labGroup.Text = "资源组";
         // 
         // cbxGroupSearch
         // 
         this.cbxGroupSearch.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left)
            | System.Windows.Forms.AnchorStyles.Right)));
         this.cbxGroupSearch.FormattingEnabled = true;
         this.cbxGroupSearch.Location = new System.Drawing.Point(61, 4);
         this.cbxGroupSearch.Name = "cbxGroupSearch";
         this.cbxGroupSearch.Size = new System.Drawing.Size(282, 20);
         this.cbxGroupSearch.TabIndex = 4;
         this.cbxGroupSearch.KeyUp += new System.Windows.Forms.KeyEventHandler(this.cbxGroupSearch_KeyUp);
         // 
         // QDsResourceGroupDesign
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panInfo);
         this.Controls.Add(this.splitter1);
         this.Controls.Add(this.panel1);
         this.Name = "QDsResourceGroupDesign";
         this.Size = new System.Drawing.Size(974, 568);
         this.pnlInfoHead.ResumeLayout(false);
         this.pnlInfoHead.PerformLayout();
         this.panInfo.ResumeLayout(false);
         this.pnlInfo.ResumeLayout(false);
         this.panel1.ResumeLayout(false);
         this.panel2.ResumeLayout(false);
         this.panel2.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlInfoHead;
      private System.Windows.Forms.Label labNameDisplay;
      private System.Windows.Forms.Panel panInfo;
      private System.Windows.Forms.Splitter splitter1;
      private System.Windows.Forms.ImageList imlMain;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.Panel panel2;
      private System.Windows.Forms.ComboBox cbxResource;
      private System.Windows.Forms.Label labResource;
      private System.Windows.Forms.Label labGroup;
      private System.Windows.Forms.ComboBox cbxGroupSearch;
      private MO.Design3d.ResourceGroup.Controls.QDsResourceGroupProperty qdsGrpProperty;
      private MO.Design3d.Controls.QDsPictureProperty qdsPicture;
      private System.Windows.Forms.TreeView tvwCatalog;
      private System.Windows.Forms.Panel pnlInfo;


   }
}
