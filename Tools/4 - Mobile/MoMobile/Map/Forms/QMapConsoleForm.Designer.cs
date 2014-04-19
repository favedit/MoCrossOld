namespace MoMobile.Map.Forms
{
   partial class QMapConsoleForm
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QMapConsoleForm));
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbExportAll = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbWindowTask = new System.Windows.Forms.ToolStripButton();
         this.tsbWindowTrack = new System.Windows.Forms.ToolStripButton();
         this.tspLine3 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExit = new System.Windows.Forms.ToolStripButton();
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.qdsDesign = new MoMobile.Map.Forms.QMapDesign();
         this.tsbResourceExport = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tspMain.SuspendLayout();
         this.SuspendLayout();
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbExportAll,
            this.toolStripSeparator1,
            this.tsbResourceExport,
            this.toolStripSeparator2,
            this.tsbWindowTask,
            this.tsbWindowTrack,
            this.tspLine3,
            this.tsbExit});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1160, 25);
         this.tspMain.TabIndex = 7;
         this.tspMain.Text = "toolStrip1";
         // 
         // tsbExportAll
         // 
         this.tsbExportAll.Image = ((System.Drawing.Image)(resources.GetObject("tsbExportAll.Image")));
         this.tsbExportAll.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExportAll.Name = "tsbExportAll";
         this.tsbExportAll.Size = new System.Drawing.Size(76, 22);
         this.tsbExportAll.Text = "全部导出";
         this.tsbExportAll.ToolTipText = "全部导出";
         this.tsbExportAll.Click += new System.EventHandler(this.tsbExportAll_Click);
         // 
         // toolStripSeparator2
         // 
         this.toolStripSeparator2.Name = "toolStripSeparator2";
         this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbWindowTask
         // 
         this.tsbWindowTask.Image = ((System.Drawing.Image)(resources.GetObject("tsbWindowTask.Image")));
         this.tsbWindowTask.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbWindowTask.Name = "tsbWindowTask";
         this.tsbWindowTask.Size = new System.Drawing.Size(76, 22);
         this.tsbWindowTask.Text = "任务窗口";
         // 
         // tsbWindowTrack
         // 
         this.tsbWindowTrack.Image = ((System.Drawing.Image)(resources.GetObject("tsbWindowTrack.Image")));
         this.tsbWindowTrack.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbWindowTrack.Name = "tsbWindowTrack";
         this.tsbWindowTrack.Size = new System.Drawing.Size(76, 22);
         this.tsbWindowTrack.Text = "跟踪窗口";
         // 
         // tspLine3
         // 
         this.tspLine3.Name = "tspLine3";
         this.tspLine3.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbExit
         // 
         this.tsbExit.Image = ((System.Drawing.Image)(resources.GetObject("tsbExit.Image")));
         this.tsbExit.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExit.Name = "tsbExit";
         this.tsbExit.Size = new System.Drawing.Size(52, 22);
         this.tsbExit.Text = "退出";
         this.tsbExit.Click += new System.EventHandler(this.tsbExit_Click);
         // 
         // sspMain
         // 
         this.sspMain.Location = new System.Drawing.Point(0, 598);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1160, 22);
         this.sspMain.TabIndex = 6;
         this.sspMain.Text = "statusStrip1";
         // 
         // qdsDesign
         // 
         this.qdsDesign.Location = new System.Drawing.Point(0, 25);
         this.qdsDesign.Name = "qdsDesign";
         this.qdsDesign.Size = new System.Drawing.Size(1160, 573);
         this.qdsDesign.TabIndex = 8;
         // 
         // tsbResourceExport
         // 
         this.tsbResourceExport.Image = ((System.Drawing.Image)(resources.GetObject("tsbResourceExport.Image")));
         this.tsbResourceExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbResourceExport.Name = "tsbResourceExport";
         this.tsbResourceExport.Size = new System.Drawing.Size(76, 22);
         this.tsbResourceExport.Text = "资源导出";
         this.tsbResourceExport.ToolTipText = "资源导出";
         this.tsbResourceExport.Click += new System.EventHandler(this.tsbResourceExport_Click);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // QMapConsoleForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1160, 620);
         this.Controls.Add(this.qdsDesign);
         this.Controls.Add(this.tspMain);
         this.Controls.Add(this.sspMain);
         this.Name = "QMapConsoleForm";
         this.Text = "地图设计控制台";
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tsbExportAll;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbWindowTask;
      private System.Windows.Forms.ToolStripButton tsbWindowTrack;
      private System.Windows.Forms.ToolStripSeparator tspLine3;
      private System.Windows.Forms.ToolStripButton tsbExit;
      private System.Windows.Forms.StatusStrip sspMain;
      private QMapDesign qdsDesign;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbResourceExport;
   }
}