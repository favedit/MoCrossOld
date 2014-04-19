namespace MO.Design2d.Frame.Forms
{
   partial class QUiDesign
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiDesign));
         this.cbxSearch = new System.Windows.Forms.ComboBox();
         this.lvwResources = new System.Windows.Forms.ListView();
         this.chdLabel = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.panel2 = new System.Windows.Forms.Panel();
         this.panInfo = new System.Windows.Forms.Panel();
         this.pnlInfoHead = new System.Windows.Forms.Panel();
         this.labNameDisplay = new System.Windows.Forms.Label();
         this.splitter1 = new System.Windows.Forms.Splitter();
         this.panel1 = new System.Windows.Forms.Panel();
         this.qDsUiProperty = new MO.Design2d.Frame.Forms.QUiFrameProperty();
         this.panel2.SuspendLayout();
         this.panInfo.SuspendLayout();
         this.pnlInfoHead.SuspendLayout();
         this.panel1.SuspendLayout();
         this.SuspendLayout();
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
         // lvwResources
         // 
         this.lvwResources.BorderStyle = System.Windows.Forms.BorderStyle.None;
         this.lvwResources.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdLabel});
         this.lvwResources.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwResources.FullRowSelect = true;
         this.lvwResources.HideSelection = false;
         this.lvwResources.Location = new System.Drawing.Point(0, 29);
         this.lvwResources.MultiSelect = false;
         this.lvwResources.Name = "lvwResources";
         this.lvwResources.Size = new System.Drawing.Size(346, 565);
         this.lvwResources.SmallImageList = this.imlMain;
         this.lvwResources.TabIndex = 6;
         this.lvwResources.UseCompatibleStateImageBehavior = false;
         this.lvwResources.View = System.Windows.Forms.View.Details;
         this.lvwResources.SelectedIndexChanged += new System.EventHandler(this.lvwResources_SelectedIndexChanged);
         this.lvwResources.DoubleClick += new System.EventHandler(this.lvwResources_DoubleClick);
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
         this.imlMain.Images.SetKeyName(0, "Window");
         this.imlMain.Images.SetKeyName(1, "Form");
         this.imlMain.Images.SetKeyName(2, "Bar");
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.cbxSearch);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel2.Location = new System.Drawing.Point(0, 0);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(346, 29);
         this.panel2.TabIndex = 5;
         // 
         // panInfo
         // 
         this.panInfo.Controls.Add(this.qDsUiProperty);
         this.panInfo.Controls.Add(this.pnlInfoHead);
         this.panInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panInfo.Location = new System.Drawing.Point(350, 0);
         this.panInfo.Name = "panInfo";
         this.panInfo.Size = new System.Drawing.Size(704, 594);
         this.panInfo.TabIndex = 9;
         // 
         // pnlInfoHead
         // 
         this.pnlInfoHead.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlInfoHead.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfoHead.Controls.Add(this.labNameDisplay);
         this.pnlInfoHead.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlInfoHead.Location = new System.Drawing.Point(0, 0);
         this.pnlInfoHead.Name = "pnlInfoHead";
         this.pnlInfoHead.Size = new System.Drawing.Size(704, 32);
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
         // splitter1
         // 
         this.splitter1.Location = new System.Drawing.Point(346, 0);
         this.splitter1.Name = "splitter1";
         this.splitter1.Size = new System.Drawing.Size(4, 594);
         this.splitter1.TabIndex = 8;
         this.splitter1.TabStop = false;
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.lvwResources);
         this.panel1.Controls.Add(this.panel2);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Left;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(346, 594);
         this.panel1.TabIndex = 7;
         // 
         // qDsUiProperty
         // 
         this.qDsUiProperty.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qDsUiProperty.Location = new System.Drawing.Point(0, 32);
         this.qDsUiProperty.Name = "qDsUiProperty";
         this.qDsUiProperty.Size = new System.Drawing.Size(704, 562);
         this.qDsUiProperty.TabIndex = 4;
         // 
         // QUiDesign
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panInfo);
         this.Controls.Add(this.splitter1);
         this.Controls.Add(this.panel1);
         this.Name = "QUiDesign";
         this.Size = new System.Drawing.Size(1054, 594);
         this.panel2.ResumeLayout(false);
         this.panInfo.ResumeLayout(false);
         this.pnlInfoHead.ResumeLayout(false);
         this.pnlInfoHead.PerformLayout();
         this.panel1.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ComboBox cbxSearch;
      private System.Windows.Forms.ListView lvwResources;
      private System.Windows.Forms.ColumnHeader chdLabel;
      private System.Windows.Forms.ImageList imlMain;
      private System.Windows.Forms.Panel panel2;
      private System.Windows.Forms.Panel panInfo;
      private System.Windows.Forms.Panel pnlInfoHead;
      private System.Windows.Forms.Label labNameDisplay;
      private System.Windows.Forms.Splitter splitter1;
      private System.Windows.Forms.Panel panel1;
      private QUiFrameProperty qDsUiProperty;
   }
}
