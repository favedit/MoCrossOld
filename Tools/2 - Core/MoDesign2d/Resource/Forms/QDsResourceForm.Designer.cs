namespace MO.Design2d.Resource.Forms
{
   partial class QDsResourceForm
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
         if(disposing && (components != null)) {
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsResourceForm));
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbSave = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tspExport = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExit = new System.Windows.Forms.ToolStripButton();
         this.qDsResourceDesign1 = new MO.Design2d.Resource.Controls.QDsResourceDesign();
         this.tspMain.SuspendLayout();
         this.SuspendLayout();
         // 
         // sspMain
         // 
         this.sspMain.Location = new System.Drawing.Point(0, 740);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1584, 22);
         this.sspMain.TabIndex = 3;
         this.sspMain.Text = "statusStrip1";
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSave,
            this.toolStripSeparator1,
            this.tspExport,
            this.toolStripSeparator2,
            this.tsbExit});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1584, 25);
         this.tspMain.TabIndex = 4;
         this.tspMain.Text = "toolStrip1";
         // 
         // tsbSave
         // 
         this.tsbSave.Image = ((System.Drawing.Image)(resources.GetObject("tsbSave.Image")));
         this.tsbSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSave.Name = "tsbSave";
         this.tsbSave.Size = new System.Drawing.Size(52, 22);
         this.tsbSave.Text = "保存";
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tspExport
         // 
         this.tspExport.Image = ((System.Drawing.Image)(resources.GetObject("tspExport.Image")));
         this.tspExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspExport.Name = "tspExport";
         this.tspExport.Size = new System.Drawing.Size(52, 22);
         this.tspExport.Text = "导出";
         // 
         // toolStripSeparator2
         // 
         this.toolStripSeparator2.Name = "toolStripSeparator2";
         this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbExit
         // 
         this.tsbExit.Image = ((System.Drawing.Image)(resources.GetObject("tsbExit.Image")));
         this.tsbExit.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExit.Name = "tsbExit";
         this.tsbExit.Size = new System.Drawing.Size(52, 22);
         this.tsbExit.Text = "退出";
         // 
         // qDsResourceDesign1
         // 
         this.qDsResourceDesign1.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qDsResourceDesign1.Location = new System.Drawing.Point(0, 25);
         this.qDsResourceDesign1.Name = "qDsResourceDesign1";
         this.qDsResourceDesign1.Size = new System.Drawing.Size(1584, 715);
         this.qDsResourceDesign1.TabIndex = 5;
         // 
         // QDsResourceForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1584, 762);
         this.Controls.Add(this.qDsResourceDesign1);
         this.Controls.Add(this.sspMain);
         this.Controls.Add(this.tspMain);
         this.Name = "QDsResourceForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
         this.Text = "资源管理器";
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.StatusStrip sspMain;
      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tsbSave;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tspExport;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbExit;
      private Controls.QDsResourceDesign qDsResourceDesign1;

   }
}