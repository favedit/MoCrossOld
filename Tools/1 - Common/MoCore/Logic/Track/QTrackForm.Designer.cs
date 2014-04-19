namespace MO.Core.Logic.Track
{
   partial class QTrackForm
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
         this.components = new System.ComponentModel.Container();
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QTrackForm));
         this.lvwTracks = new System.Windows.Forms.ListView();
         this.chdDateTime = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdSender = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdMessage = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbSave = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tspClear = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbClose = new System.Windows.Forms.ToolStripButton();
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.timRefresh = new System.Windows.Forms.Timer(this.components);
         this.tspMain.SuspendLayout();
         this.SuspendLayout();
         // 
         // lvwTracks
         // 
         this.lvwTracks.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdDateTime,
            this.chdSender,
            this.chdMessage});
         this.lvwTracks.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwTracks.FullRowSelect = true;
         this.lvwTracks.HideSelection = false;
         this.lvwTracks.Location = new System.Drawing.Point(0, 25);
         this.lvwTracks.MultiSelect = false;
         this.lvwTracks.Name = "lvwTracks";
         this.lvwTracks.ShowGroups = false;
         this.lvwTracks.Size = new System.Drawing.Size(1104, 515);
         this.lvwTracks.TabIndex = 0;
         this.lvwTracks.UseCompatibleStateImageBehavior = false;
         this.lvwTracks.View = System.Windows.Forms.View.Details;
         // 
         // chdDateTime
         // 
         this.chdDateTime.Text = "时间";
         this.chdDateTime.Width = 120;
         // 
         // chdSender
         // 
         this.chdSender.Text = "发生源";
         this.chdSender.Width = 360;
         // 
         // chdMessage
         // 
         this.chdMessage.Text = "消息";
         this.chdMessage.Width = 600;
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSave,
            this.toolStripSeparator1,
            this.tspClear,
            this.toolStripSeparator2,
            this.tsbClose});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1104, 25);
         this.tspMain.TabIndex = 3;
         this.tspMain.Text = "toolStrip1";
         // 
         // tsbSave
         // 
         this.tsbSave.Image = ((System.Drawing.Image)(resources.GetObject("tsbSave.Image")));
         this.tsbSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSave.Name = "tsbSave";
         this.tsbSave.Size = new System.Drawing.Size(52, 22);
         this.tsbSave.Text = "保存";
         this.tsbSave.Click += new System.EventHandler(this.tsbSave_Click);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tspClear
         // 
         this.tspClear.Image = ((System.Drawing.Image)(resources.GetObject("tspClear.Image")));
         this.tspClear.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspClear.Name = "tspClear";
         this.tspClear.Size = new System.Drawing.Size(52, 22);
         this.tspClear.Text = "清空";
         this.tspClear.Click += new System.EventHandler(this.tspClear_Click);
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
         // sspMain
         // 
         this.sspMain.Location = new System.Drawing.Point(0, 540);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1104, 22);
         this.sspMain.TabIndex = 4;
         this.sspMain.Text = "statusStrip1";
         // 
         // timRefresh
         // 
         this.timRefresh.Interval = 1000;
         this.timRefresh.Tick += new System.EventHandler(this.timRefresh_Tick);
         // 
         // QTrackForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1104, 562);
         this.Controls.Add(this.lvwTracks);
         this.Controls.Add(this.sspMain);
         this.Controls.Add(this.tspMain);
         this.Name = "QTrackForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
         this.Text = "跟踪管理器";
         this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.QTrackForm_FormClosing);
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ListView lvwTracks;
      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.ToolStripButton tsbSave;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tspClear;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbClose;
      private System.Windows.Forms.StatusStrip sspMain;
      private System.Windows.Forms.ColumnHeader chdDateTime;
      private System.Windows.Forms.ColumnHeader chdSender;
      private System.Windows.Forms.ColumnHeader chdMessage;
      private System.Windows.Forms.Timer timRefresh;
   }
}