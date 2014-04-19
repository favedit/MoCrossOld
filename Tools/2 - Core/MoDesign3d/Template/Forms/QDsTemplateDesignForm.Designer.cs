namespace MO.Design3d.Template.Forms
{
   partial class QDsTemplateDesignForm
   {
      /// <summary>
      /// Required designer variable.
      /// </summary>
      private System.ComponentModel.IContainer components = null;

      /// <summary>
      /// Clean up any resources being used.
      /// </summary>
      /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
      protected override void Dispose(bool disposing) {
         if (disposing && (components != null)) {
            components.Dispose();
         }
         base.Dispose(disposing);
      }

      #region Windows Form Designer generated code

      /// <summary>
      /// Required method for Designer support - do not modify
      /// the contents of this method with the code editor.
      /// </summary>
      private void InitializeComponent() {
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsTemplateDesignForm));
         this.tspMainToolbar = new System.Windows.Forms.ToolStrip();
         this.tsbSaveAll = new System.Windows.Forms.ToolStripButton();
         this.tsbSaveSelected = new System.Windows.Forms.ToolStripButton();
         this.tssperCloseSave = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExportAll = new System.Windows.Forms.ToolStripButton();
         this.tsbExprotSelected = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbClose = new System.Windows.Forms.ToolStripButton();
         this.sspMainStatus = new System.Windows.Forms.StatusStrip();
         this.qdsTemplateDesign = new MO.Design3d.Template.Controls.QDsTemplateDesign();
         this.tspMainToolbar.SuspendLayout();
         this.SuspendLayout();
         // 
         // tspMainToolbar
         // 
         this.tspMainToolbar.BackColor = System.Drawing.SystemColors.ControlLight;
         this.tspMainToolbar.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSaveAll,
            this.tsbSaveSelected,
            this.tssperCloseSave,
            this.tsbExportAll,
            this.tsbExprotSelected,
            this.toolStripSeparator2,
            this.tsbClose});
         this.tspMainToolbar.Location = new System.Drawing.Point(0, 0);
         this.tspMainToolbar.Name = "tspMainToolbar";
         this.tspMainToolbar.Size = new System.Drawing.Size(1019, 25);
         this.tspMainToolbar.TabIndex = 24;
         // 
         // tsbSaveAll
         // 
         this.tsbSaveAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveAll.Image")));
         this.tsbSaveAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveAll.Name = "tsbSaveAll";
         this.tsbSaveAll.Size = new System.Drawing.Size(76, 22);
         this.tsbSaveAll.Text = "全部保存";
         // 
         // tsbSaveSelected
         // 
         this.tsbSaveSelected.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveSelected.Image")));
         this.tsbSaveSelected.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveSelected.Name = "tsbSaveSelected";
         this.tsbSaveSelected.Size = new System.Drawing.Size(76, 22);
         this.tsbSaveSelected.Text = "选中保存";
         // 
         // tssperCloseSave
         // 
         this.tssperCloseSave.Name = "tssperCloseSave";
         this.tssperCloseSave.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbExportAll
         // 
         this.tsbExportAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportAll.Image")));
         this.tsbExportAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportAll.Name = "tsbExportAll";
         this.tsbExportAll.Size = new System.Drawing.Size(76, 22);
         this.tsbExportAll.Text = "全部导出";
         // 
         // tsbExprotSelected
         // 
         this.tsbExprotSelected.Image = ((System.Drawing.Image)(resources.GetObject("tsbExprotSelected.Image")));
         this.tsbExprotSelected.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExprotSelected.Name = "tsbExprotSelected";
         this.tsbExprotSelected.Size = new System.Drawing.Size(76, 22);
         this.tsbExprotSelected.Text = "选中导出";
         // 
         // toolStripSeparator2
         // 
         this.toolStripSeparator2.Name = "toolStripSeparator2";
         this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbClose
         // 
         this.tsbClose.Image = ((System.Drawing.Image)(resources.GetObject("tsbClose.Image")));
         this.tsbClose.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbClose.Name = "tsbClose";
         this.tsbClose.Size = new System.Drawing.Size(52, 22);
         this.tsbClose.Text = "关闭";
         this.tsbClose.Click += new System.EventHandler(this.tsbClose_Click);
         // 
         // sspMainStatus
         // 
         this.sspMainStatus.Location = new System.Drawing.Point(0, 580);
         this.sspMainStatus.Name = "sspMainStatus";
         this.sspMainStatus.Size = new System.Drawing.Size(1019, 22);
         this.sspMainStatus.TabIndex = 25;
         this.sspMainStatus.Text = "statusStrip1";
         // 
         // qdsTemplateDesign
         // 
         this.qdsTemplateDesign.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdsTemplateDesign.Location = new System.Drawing.Point(0, 25);
         this.qdsTemplateDesign.Name = "qdsTemplateDesign";
         this.qdsTemplateDesign.Size = new System.Drawing.Size(1019, 555);
         this.qdsTemplateDesign.TabIndex = 26;
         // 
         // QDsTemplateDesignForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1019, 602);
         this.Controls.Add(this.qdsTemplateDesign);
         this.Controls.Add(this.sspMainStatus);
         this.Controls.Add(this.tspMainToolbar);
         this.Name = "QDsTemplateDesignForm";
         this.Text = "QDsTemplateDesignForm";
         this.tspMainToolbar.ResumeLayout(false);
         this.tspMainToolbar.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ToolStrip tspMainToolbar;
      private System.Windows.Forms.ToolStripButton tsbSaveAll;
      private System.Windows.Forms.ToolStripButton tsbSaveSelected;
      private System.Windows.Forms.ToolStripSeparator tssperCloseSave;
      private System.Windows.Forms.ToolStripButton tsbExportAll;
      private System.Windows.Forms.ToolStripButton tsbExprotSelected;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbClose;
      private System.Windows.Forms.StatusStrip sspMainStatus;
      private Controls.QDsTemplateDesign qdsTemplateDesign;
   }
}