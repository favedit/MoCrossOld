namespace MO.Design2d.Resource.Controls
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
         this.pnlCatalog = new System.Windows.Forms.Panel();
         this.qDsGroupCatalog = new MO.Design2d.Resource.Controls.QDsResourceGroupCatalog();
         this.pnlSearch = new System.Windows.Forms.Panel();
         this.cbxResource = new System.Windows.Forms.ComboBox();
         this.labResource = new System.Windows.Forms.Label();
         this.labGroup = new System.Windows.Forms.Label();
         this.cbxGroupSearch = new System.Windows.Forms.ComboBox();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.panInfo = new System.Windows.Forms.Panel();
         this.qdsAnimation = new MO.Design2d.Resource.Controls.QDsAnimationProperty();
         this.qdsPicture = new MO.Design2d.Resource.Controls.QDsPictureProperty();
         this.pnlInfoHead = new System.Windows.Forms.Panel();
         this.labNameDisplay = new System.Windows.Forms.Label();
         this.qdsGrpProperty = new MO.Design2d.Resource.Controls.QDsResourceGroupProperty();
         this.pnlSpace = new System.Windows.Forms.Panel();
         this.pnlCatalog.SuspendLayout();
         this.pnlSearch.SuspendLayout();
         this.panInfo.SuspendLayout();
         this.pnlInfoHead.SuspendLayout();
         this.pnlSpace.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlCatalog
         // 
         this.pnlCatalog.Controls.Add(this.qDsGroupCatalog);
         this.pnlCatalog.Controls.Add(this.pnlSearch);
         this.pnlCatalog.Dock = System.Windows.Forms.DockStyle.Left;
         this.pnlCatalog.Location = new System.Drawing.Point(0, 0);
         this.pnlCatalog.Name = "pnlCatalog";
         this.pnlCatalog.Size = new System.Drawing.Size(346, 724);
         this.pnlCatalog.TabIndex = 0;
         // 
         // qDsGroupCatalog
         // 
         this.qDsGroupCatalog.CheckBoxes = true;
         this.qDsGroupCatalog.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qDsGroupCatalog.ImageIndex = 0;
         this.qDsGroupCatalog.Location = new System.Drawing.Point(0, 54);
         this.qDsGroupCatalog.Name = "qDsGroupCatalog";
         this.qDsGroupCatalog.SelectedImageIndex = 0;
         this.qDsGroupCatalog.Size = new System.Drawing.Size(346, 670);
         this.qDsGroupCatalog.TabIndex = 6;
         this.qDsGroupCatalog.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.qDsGroupCatalog_CatalogAfterSelect);
         // 
         // pnlSearch
         // 
         this.pnlSearch.Controls.Add(this.cbxResource);
         this.pnlSearch.Controls.Add(this.labResource);
         this.pnlSearch.Controls.Add(this.labGroup);
         this.pnlSearch.Controls.Add(this.cbxGroupSearch);
         this.pnlSearch.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlSearch.Location = new System.Drawing.Point(0, 0);
         this.pnlSearch.Name = "pnlSearch";
         this.pnlSearch.Size = new System.Drawing.Size(346, 54);
         this.pnlSearch.TabIndex = 5;
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
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(346, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 724);
         this.splitter1.TabIndex = 1;
         this.splitter1.TabStop = false;
         // 
         // panInfo
         // 
         this.panInfo.Controls.Add(this.pnlSpace);
         this.panInfo.Controls.Add(this.pnlInfoHead);
         this.panInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panInfo.Location = new System.Drawing.Point(350, 0);
         this.panInfo.Name = "panInfo";
         this.panInfo.Size = new System.Drawing.Size(776, 724);
         this.panInfo.TabIndex = 3;
         // 
         // qdsAnimation
         // 
         this.qdsAnimation.AutoSize = true;
         this.qdsAnimation.Location = new System.Drawing.Point(33, 231);
         this.qdsAnimation.Name = "qdsAnimation";
         this.qdsAnimation.Size = new System.Drawing.Size(677, 383);
         this.qdsAnimation.TabIndex = 4;
         // 
         // qdsPicture
         // 
         this.qdsPicture.Location = new System.Drawing.Point(33, 104);
         this.qdsPicture.Name = "qdsPicture";
         this.qdsPicture.Size = new System.Drawing.Size(677, 121);
         this.qdsPicture.TabIndex = 5;
         // 
         // pnlInfoHead
         // 
         this.pnlInfoHead.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlInfoHead.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfoHead.Controls.Add(this.labNameDisplay);
         this.pnlInfoHead.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlInfoHead.Location = new System.Drawing.Point(0, 0);
         this.pnlInfoHead.Name = "pnlInfoHead";
         this.pnlInfoHead.Size = new System.Drawing.Size(776, 32);
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
         // qdsGrpProperty
         // 
         this.qdsGrpProperty.Location = new System.Drawing.Point(33, 20);
         this.qdsGrpProperty.Name = "qdsGrpProperty";
         this.qdsGrpProperty.Size = new System.Drawing.Size(677, 78);
         this.qdsGrpProperty.TabIndex = 2;
         this.qdsGrpProperty.Click += new System.EventHandler(this.qdsGrpProperty_Click);
         // 
         // pnlSpace
         // 
         this.pnlSpace.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlSpace.Controls.Add(this.qdsPicture);
         this.pnlSpace.Controls.Add(this.qdsAnimation);
         this.pnlSpace.Controls.Add(this.qdsGrpProperty);
         this.pnlSpace.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlSpace.Location = new System.Drawing.Point(0, 32);
         this.pnlSpace.Name = "pnlSpace";
         this.pnlSpace.Size = new System.Drawing.Size(776, 692);
         this.pnlSpace.TabIndex = 6;
         // 
         // QDsResourceGroupDesign
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panInfo);
         this.Controls.Add(this.splitter1);
         this.Controls.Add(this.pnlCatalog);
         this.Name = "QDsResourceGroupDesign";
         this.Size = new System.Drawing.Size(1126, 724);
         this.pnlCatalog.ResumeLayout(false);
         this.pnlSearch.ResumeLayout(false);
         this.pnlSearch.PerformLayout();
         this.panInfo.ResumeLayout(false);
         this.pnlInfoHead.ResumeLayout(false);
         this.pnlInfoHead.PerformLayout();
         this.pnlSpace.ResumeLayout(false);
         this.pnlSpace.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlCatalog;
      private System.Windows.Forms.Splitter splitter1;
      private System.Windows.Forms.Panel pnlSearch;
      private System.Windows.Forms.ComboBox cbxGroupSearch;
      private QDsResourceGroupProperty qdsGrpProperty;
      private System.Windows.Forms.Panel panInfo;
      private System.Windows.Forms.Panel pnlInfoHead;
      private System.Windows.Forms.Label labNameDisplay;
      private QDsAnimationProperty qdsAnimation;
      private QDsPictureProperty qdsPicture;
      private QDsResourceGroupCatalog qDsGroupCatalog;
      private System.Windows.Forms.Label labResource;
      private System.Windows.Forms.Label labGroup;
      private System.Windows.Forms.ComboBox cbxResource;
      private System.Windows.Forms.Panel pnlSpace;
   }
}
