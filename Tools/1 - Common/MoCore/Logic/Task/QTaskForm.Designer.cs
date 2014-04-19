namespace MO.Core.Logic.Task
{
   partial class QTaskForm
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QTaskForm));
         this.lvwTasks = new System.Windows.Forms.ListView();
         this.chdDateTime = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdSender = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdMessage = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.tspClear = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbExit = new System.Windows.Forms.ToolStripButton();
         this.sspMain = new System.Windows.Forms.StatusStrip();
         this.toolStripStatusLabel1 = new System.Windows.Forms.ToolStripStatusLabel();
         this.tsslTaskCount = new System.Windows.Forms.ToolStripStatusLabel();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbSave = new System.Windows.Forms.ToolStripButton();
         this.tspMain = new System.Windows.Forms.ToolStrip();
         this.tsbRefresh = new System.Windows.Forms.ToolStripButton();
         this.timRefresh = new System.Windows.Forms.Timer(this.components);
         this.sspMain.SuspendLayout();
         this.tspMain.SuspendLayout();
         this.SuspendLayout();
         // 
         // lvwTasks
         // 
         this.lvwTasks.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdDateTime,
            this.chdSender,
            this.chdMessage});
         this.lvwTasks.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwTasks.FullRowSelect = true;
         this.lvwTasks.HoverSelection = true;
         this.lvwTasks.Location = new System.Drawing.Point(0, 25);
         this.lvwTasks.MultiSelect = false;
         this.lvwTasks.Name = "lvwTasks";
         this.lvwTasks.Size = new System.Drawing.Size(1104, 515);
         this.lvwTasks.TabIndex = 5;
         this.lvwTasks.UseCompatibleStateImageBehavior = false;
         this.lvwTasks.View = System.Windows.Forms.View.Details;
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
         // tspClear
         // 
         this.tspClear.Image = ((System.Drawing.Image)(resources.GetObject("tspClear.Image")));
         this.tspClear.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspClear.Name = "tspClear";
         this.tspClear.Size = new System.Drawing.Size(52, 22);
         this.tspClear.Text = "清空";
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
         this.tsbExit.Text = "关闭";
         this.tsbExit.Click += new System.EventHandler(this.tsbExit_Click);
         // 
         // sspMain
         // 
         this.sspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripStatusLabel1,
            this.tsslTaskCount});
         this.sspMain.Location = new System.Drawing.Point(0, 540);
         this.sspMain.Name = "sspMain";
         this.sspMain.Size = new System.Drawing.Size(1104, 22);
         this.sspMain.TabIndex = 7;
         this.sspMain.Text = "statusStrip1";
         // 
         // toolStripStatusLabel1
         // 
         this.toolStripStatusLabel1.Name = "toolStripStatusLabel1";
         this.toolStripStatusLabel1.Size = new System.Drawing.Size(56, 17);
         this.toolStripStatusLabel1.Text = "任务总数";
         // 
         // tsslTaskCount
         // 
         this.tsslTaskCount.AutoSize = false;
         this.tsslTaskCount.BorderSides = ((System.Windows.Forms.ToolStripStatusLabelBorderSides)((((System.Windows.Forms.ToolStripStatusLabelBorderSides.Left | System.Windows.Forms.ToolStripStatusLabelBorderSides.Top) 
            | System.Windows.Forms.ToolStripStatusLabelBorderSides.Right) 
            | System.Windows.Forms.ToolStripStatusLabelBorderSides.Bottom)));
         this.tsslTaskCount.Name = "tsslTaskCount";
         this.tsslTaskCount.Size = new System.Drawing.Size(80, 17);
         this.tsslTaskCount.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbSave
         // 
         this.tsbSave.Image = ((System.Drawing.Image)(resources.GetObject("tsbSave.Image")));
         this.tsbSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSave.Name = "tsbSave";
         this.tsbSave.Size = new System.Drawing.Size(52, 22);
         this.tsbSave.Text = "保存";
         // 
         // tspMain
         // 
         this.tspMain.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbRefresh,
            this.tsbSave,
            this.toolStripSeparator1,
            this.tspClear,
            this.toolStripSeparator2,
            this.tsbExit});
         this.tspMain.Location = new System.Drawing.Point(0, 0);
         this.tspMain.Name = "tspMain";
         this.tspMain.Size = new System.Drawing.Size(1104, 25);
         this.tspMain.TabIndex = 6;
         this.tspMain.Text = "toolStrip1";
         // 
         // tsbRefresh
         // 
         this.tsbRefresh.Image = ((System.Drawing.Image)(resources.GetObject("tsbRefresh.Image")));
         this.tsbRefresh.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbRefresh.Name = "tsbRefresh";
         this.tsbRefresh.Size = new System.Drawing.Size(52, 22);
         this.tsbRefresh.Text = "刷新";
         this.tsbRefresh.Click += new System.EventHandler(this.tsbRefresh_Click);
         // 
         // timRefresh
         // 
         this.timRefresh.Interval = 2000;
         this.timRefresh.Tick += new System.EventHandler(this.timRefresh_Tick);
         // 
         // QTaskForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1104, 562);
         this.Controls.Add(this.lvwTasks);
         this.Controls.Add(this.sspMain);
         this.Controls.Add(this.tspMain);
         this.Name = "QTaskForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
         this.Text = "任务管理器";
         this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.QTaskForm_FormClosing);
         this.sspMain.ResumeLayout(false);
         this.sspMain.PerformLayout();
         this.tspMain.ResumeLayout(false);
         this.tspMain.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ListView lvwTasks;
      private System.Windows.Forms.ColumnHeader chdDateTime;
      private System.Windows.Forms.ColumnHeader chdSender;
      private System.Windows.Forms.ColumnHeader chdMessage;
      private System.Windows.Forms.ToolStripButton tspClear;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbExit;
      private System.Windows.Forms.StatusStrip sspMain;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbSave;
      private System.Windows.Forms.ToolStrip tspMain;
      private System.Windows.Forms.Timer timRefresh;
      private System.Windows.Forms.ToolStripStatusLabel toolStripStatusLabel1;
      private System.Windows.Forms.ToolStripStatusLabel tsslTaskCount;
      private System.Windows.Forms.ToolStripButton tsbRefresh;
   }
}