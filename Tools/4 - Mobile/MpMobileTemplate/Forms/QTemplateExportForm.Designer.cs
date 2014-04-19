namespace MpMobileTemplate.Forms
{
   partial class QTemplateExportForm
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QTemplateExportForm));
         this.toolStrip1 = new System.Windows.Forms.ToolStrip();
         this.Tsbonly = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
         this.TlsClose = new System.Windows.Forms.ToolStripButton();
         this.chdCommandTarget = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdCommandFrom = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdCommandType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.lvwCopy = new System.Windows.Forms.ListView();
         this.chdType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdTargets = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdSources = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.tpgExecution = new System.Windows.Forms.TabPage();
         this.panel1 = new System.Windows.Forms.Panel();
         this.chbCommand = new System.Windows.Forms.CheckBox();
         this.chkAllTrues = new System.Windows.Forms.CheckBox();
         this.label4 = new System.Windows.Forms.Label();
         this.Chyn = new System.Windows.Forms.CheckBox();
         this.labCopy = new System.Windows.Forms.Label();
         this.label2 = new System.Windows.Forms.Label();
         this.labMerger = new System.Windows.Forms.Label();
         this.txtTarget = new System.Windows.Forms.TextBox();
         this.label1 = new System.Windows.Forms.Label();
         this.txtTargets = new System.Windows.Forms.TextBox();
         this.labTemplate = new System.Windows.Forms.Label();
         this.txtSource = new System.Windows.Forms.TextBox();
         this.chdValids = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.lvwMerger = new System.Windows.Forms.ListView();
         this.pnlparent = new System.Windows.Forms.Panel();
         this.splitContainer1 = new System.Windows.Forms.SplitContainer();
         this.pnlListvew = new System.Windows.Forms.Panel();
         this.tbcMain = new System.Windows.Forms.TabControl();
         this.datatables = new System.Windows.Forms.TabPage();
         this.lvwTemplate = new System.Windows.Forms.ListView();
         this.chdValid = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdName = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdTarget = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdDescription = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.tpgXml = new System.Windows.Forms.TabPage();
         this.toolStrip1.SuspendLayout();
         this.tpgExecution.SuspendLayout();
         this.panel1.SuspendLayout();
         this.pnlparent.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
         this.splitContainer1.Panel1.SuspendLayout();
         this.splitContainer1.Panel2.SuspendLayout();
         this.splitContainer1.SuspendLayout();
         this.pnlListvew.SuspendLayout();
         this.tbcMain.SuspendLayout();
         this.datatables.SuspendLayout();
         this.tpgXml.SuspendLayout();
         this.SuspendLayout();
         // 
         // toolStrip1
         // 
         this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.Tsbonly,
            this.toolStripSeparator3,
            this.TlsClose});
         this.toolStrip1.Location = new System.Drawing.Point(0, 0);
         this.toolStrip1.Name = "toolStrip1";
         this.toolStrip1.Size = new System.Drawing.Size(1296, 25);
         this.toolStrip1.TabIndex = 1;
         this.toolStrip1.Text = "toolStrip1";
         // 
         // Tsbonly
         // 
         this.Tsbonly.Image = ((System.Drawing.Image)(resources.GetObject("Tsbonly.Image")));
         this.Tsbonly.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.Tsbonly.Name = "Tsbonly";
         this.Tsbonly.Size = new System.Drawing.Size(100, 22);
         this.Tsbonly.Text = "模板数据导出";
         this.Tsbonly.Click += new System.EventHandler(this.Tsbonly_Click);
         // 
         // toolStripSeparator3
         // 
         this.toolStripSeparator3.Name = "toolStripSeparator3";
         this.toolStripSeparator3.Size = new System.Drawing.Size(6, 25);
         // 
         // TlsClose
         // 
         this.TlsClose.Image = ((System.Drawing.Image)(resources.GetObject("TlsClose.Image")));
         this.TlsClose.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.TlsClose.Name = "TlsClose";
         this.TlsClose.Size = new System.Drawing.Size(52, 22);
         this.TlsClose.Text = "关闭";
         this.TlsClose.Click += new System.EventHandler(this.TlsClose_Click);
         // 
         // chdCommandTarget
         // 
         this.chdCommandTarget.Text = "目标";
         this.chdCommandTarget.Width = 600;
         // 
         // chdCommandFrom
         // 
         this.chdCommandFrom.Text = "来源";
         this.chdCommandFrom.Width = 600;
         // 
         // chdCommandType
         // 
         this.chdCommandType.Text = "类型";
         // 
         // lvwCopy
         // 
         this.lvwCopy.CheckBoxes = true;
         this.lvwCopy.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdCommandType,
            this.chdCommandTarget,
            this.chdCommandFrom});
         this.lvwCopy.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwCopy.FullRowSelect = true;
         this.lvwCopy.GridLines = true;
         this.lvwCopy.Location = new System.Drawing.Point(3, 3);
         this.lvwCopy.MultiSelect = false;
         this.lvwCopy.Name = "lvwCopy";
         this.lvwCopy.Size = new System.Drawing.Size(1282, 517);
         this.lvwCopy.TabIndex = 1;
         this.lvwCopy.UseCompatibleStateImageBehavior = false;
         this.lvwCopy.View = System.Windows.Forms.View.Details;
         // 
         // chdType
         // 
         this.chdType.Text = "类型";
         this.chdType.Width = 180;
         // 
         // chdTargets
         // 
         this.chdTargets.Text = "目标";
         this.chdTargets.Width = 400;
         // 
         // chdSources
         // 
         this.chdSources.Text = "来源";
         this.chdSources.Width = 400;
         // 
         // tpgExecution
         // 
         this.tpgExecution.Controls.Add(this.lvwCopy);
         this.tpgExecution.Location = new System.Drawing.Point(4, 22);
         this.tpgExecution.Name = "tpgExecution";
         this.tpgExecution.Padding = new System.Windows.Forms.Padding(3);
         this.tpgExecution.Size = new System.Drawing.Size(1288, 523);
         this.tpgExecution.TabIndex = 3;
         this.tpgExecution.Text = "执行命令";
         this.tpgExecution.UseVisualStyleBackColor = true;
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.chbCommand);
         this.panel1.Controls.Add(this.chkAllTrues);
         this.panel1.Controls.Add(this.label4);
         this.panel1.Controls.Add(this.Chyn);
         this.panel1.Controls.Add(this.labCopy);
         this.panel1.Controls.Add(this.label2);
         this.panel1.Controls.Add(this.labMerger);
         this.panel1.Controls.Add(this.txtTarget);
         this.panel1.Controls.Add(this.label1);
         this.panel1.Controls.Add(this.txtTargets);
         this.panel1.Controls.Add(this.labTemplate);
         this.panel1.Controls.Add(this.txtSource);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(1296, 96);
         this.panel1.TabIndex = 16;
         // 
         // chbCommand
         // 
         this.chbCommand.AutoSize = true;
         this.chbCommand.Checked = true;
         this.chbCommand.CheckState = System.Windows.Forms.CheckState.Checked;
         this.chbCommand.Location = new System.Drawing.Point(329, 34);
         this.chbCommand.Name = "chbCommand";
         this.chbCommand.Size = new System.Drawing.Size(96, 16);
         this.chbCommand.TabIndex = 21;
         this.chbCommand.Text = "执行命令全选";
         this.chbCommand.UseVisualStyleBackColor = true;
         // 
         // chkAllTrues
         // 
         this.chkAllTrues.AutoSize = true;
         this.chkAllTrues.Checked = true;
         this.chkAllTrues.CheckState = System.Windows.Forms.CheckState.Checked;
         this.chkAllTrues.Location = new System.Drawing.Point(171, 34);
         this.chkAllTrues.Name = "chkAllTrues";
         this.chkAllTrues.Size = new System.Drawing.Size(108, 16);
         this.chkAllTrues.TabIndex = 20;
         this.chkAllTrues.Text = "转换EXCEL全选 ";
         this.chkAllTrues.UseVisualStyleBackColor = true;
         // 
         // label4
         // 
         this.label4.AutoSize = true;
         this.label4.Location = new System.Drawing.Point(327, 53);
         this.label4.Name = "label4";
         this.label4.Size = new System.Drawing.Size(89, 12);
         this.label4.TabIndex = 19;
         this.label4.Text = "执行命令总数：";
         // 
         // Chyn
         // 
         this.Chyn.AutoSize = true;
         this.Chyn.Checked = true;
         this.Chyn.CheckState = System.Windows.Forms.CheckState.Checked;
         this.Chyn.Location = new System.Drawing.Point(22, 34);
         this.Chyn.Name = "Chyn";
         this.Chyn.Size = new System.Drawing.Size(84, 16);
         this.Chyn.TabIndex = 6;
         this.Chyn.Text = "存储表全选";
         this.Chyn.UseVisualStyleBackColor = true;
         // 
         // labCopy
         // 
         this.labCopy.Location = new System.Drawing.Point(422, 53);
         this.labCopy.Name = "labCopy";
         this.labCopy.Size = new System.Drawing.Size(60, 12);
         this.labCopy.TabIndex = 18;
         // 
         // label2
         // 
         this.label2.AutoSize = true;
         this.label2.Location = new System.Drawing.Point(169, 53);
         this.label2.Name = "label2";
         this.label2.Size = new System.Drawing.Size(95, 12);
         this.label2.TabIndex = 17;
         this.label2.Text = "转换EXCEL总数：";
         // 
         // labMerger
         // 
         this.labMerger.Location = new System.Drawing.Point(270, 53);
         this.labMerger.Name = "labMerger";
         this.labMerger.Size = new System.Drawing.Size(60, 12);
         this.labMerger.TabIndex = 16;
         // 
         // txtTarget
         // 
         this.txtTarget.Location = new System.Drawing.Point(758, 7);
         this.txtTarget.Name = "txtTarget";
         this.txtTarget.Size = new System.Drawing.Size(210, 21);
         this.txtTarget.TabIndex = 13;
         this.txtTarget.Visible = false;
         // 
         // label1
         // 
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(20, 53);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(77, 12);
         this.label1.TabIndex = 8;
         this.label1.Text = "数据集总数：";
         // 
         // txtTargets
         // 
         this.txtTargets.Location = new System.Drawing.Point(758, 61);
         this.txtTargets.Name = "txtTargets";
         this.txtTargets.Size = new System.Drawing.Size(210, 21);
         this.txtTargets.TabIndex = 15;
         this.txtTargets.Visible = false;
         // 
         // labTemplate
         // 
         this.labTemplate.Location = new System.Drawing.Point(103, 53);
         this.labTemplate.Name = "labTemplate";
         this.labTemplate.Size = new System.Drawing.Size(60, 12);
         this.labTemplate.TabIndex = 7;
         // 
         // txtSource
         // 
         this.txtSource.Location = new System.Drawing.Point(758, 34);
         this.txtSource.Name = "txtSource";
         this.txtSource.Size = new System.Drawing.Size(210, 21);
         this.txtSource.TabIndex = 14;
         this.txtSource.Visible = false;
         // 
         // chdValids
         // 
         this.chdValids.Text = "有效";
         this.chdValids.Width = 80;
         // 
         // lvwMerger
         // 
         this.lvwMerger.CheckBoxes = true;
         this.lvwMerger.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdValids,
            this.chdType,
            this.chdTargets,
            this.chdSources});
         this.lvwMerger.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwMerger.FullRowSelect = true;
         this.lvwMerger.GridLines = true;
         this.lvwMerger.HideSelection = false;
         this.lvwMerger.Location = new System.Drawing.Point(3, 3);
         this.lvwMerger.Name = "lvwMerger";
         this.lvwMerger.Size = new System.Drawing.Size(1282, 517);
         this.lvwMerger.TabIndex = 2;
         this.lvwMerger.UseCompatibleStateImageBehavior = false;
         this.lvwMerger.View = System.Windows.Forms.View.Details;
         // 
         // pnlparent
         // 
         this.pnlparent.Controls.Add(this.splitContainer1);
         this.pnlparent.Controls.Add(this.toolStrip1);
         this.pnlparent.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlparent.Location = new System.Drawing.Point(0, 0);
         this.pnlparent.Name = "pnlparent";
         this.pnlparent.Size = new System.Drawing.Size(1296, 674);
         this.pnlparent.TabIndex = 3;
         // 
         // splitContainer1
         // 
         this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
         this.splitContainer1.Location = new System.Drawing.Point(0, 25);
         this.splitContainer1.Name = "splitContainer1";
         this.splitContainer1.Orientation = System.Windows.Forms.Orientation.Horizontal;
         // 
         // splitContainer1.Panel1
         // 
         this.splitContainer1.Panel1.Controls.Add(this.pnlListvew);
         // 
         // splitContainer1.Panel2
         // 
         this.splitContainer1.Panel2.Controls.Add(this.panel1);
         this.splitContainer1.Size = new System.Drawing.Size(1296, 649);
         this.splitContainer1.SplitterDistance = 549;
         this.splitContainer1.TabIndex = 17;
         // 
         // pnlListvew
         // 
         this.pnlListvew.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
         this.pnlListvew.Controls.Add(this.tbcMain);
         this.pnlListvew.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlListvew.Location = new System.Drawing.Point(0, 0);
         this.pnlListvew.Name = "pnlListvew";
         this.pnlListvew.Size = new System.Drawing.Size(1296, 549);
         this.pnlListvew.TabIndex = 3;
         // 
         // tbcMain
         // 
         this.tbcMain.Controls.Add(this.datatables);
         this.tbcMain.Controls.Add(this.tpgXml);
         this.tbcMain.Controls.Add(this.tpgExecution);
         this.tbcMain.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tbcMain.Location = new System.Drawing.Point(0, 0);
         this.tbcMain.Name = "tbcMain";
         this.tbcMain.SelectedIndex = 0;
         this.tbcMain.Size = new System.Drawing.Size(1296, 549);
         this.tbcMain.TabIndex = 2;
         // 
         // datatables
         // 
         this.datatables.Controls.Add(this.lvwTemplate);
         this.datatables.Location = new System.Drawing.Point(4, 22);
         this.datatables.Name = "datatables";
         this.datatables.Padding = new System.Windows.Forms.Padding(3);
         this.datatables.Size = new System.Drawing.Size(1288, 523);
         this.datatables.TabIndex = 0;
         this.datatables.Text = "存储表";
         this.datatables.UseVisualStyleBackColor = true;
         // 
         // lvwTemplate
         // 
         this.lvwTemplate.CheckBoxes = true;
         this.lvwTemplate.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdValid,
            this.chdName,
            this.chdTarget,
            this.chdDescription});
         this.lvwTemplate.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwTemplate.FullRowSelect = true;
         this.lvwTemplate.GridLines = true;
         this.lvwTemplate.Location = new System.Drawing.Point(3, 3);
         this.lvwTemplate.Name = "lvwTemplate";
         this.lvwTemplate.Size = new System.Drawing.Size(1282, 517);
         this.lvwTemplate.TabIndex = 2;
         this.lvwTemplate.UseCompatibleStateImageBehavior = false;
         this.lvwTemplate.View = System.Windows.Forms.View.Details;
         // 
         // chdValid
         // 
         this.chdValid.Text = "有效性";
         this.chdValid.Width = 250;
         // 
         // chdName
         // 
         this.chdName.Text = "模板名称";
         this.chdName.Width = 250;
         // 
         // chdTarget
         // 
         this.chdTarget.Text = "数据存储位置";
         this.chdTarget.Width = 350;
         // 
         // chdDescription
         // 
         this.chdDescription.Text = "描述";
         this.chdDescription.Width = 400;
         // 
         // tpgXml
         // 
         this.tpgXml.Controls.Add(this.lvwMerger);
         this.tpgXml.Location = new System.Drawing.Point(4, 22);
         this.tpgXml.Name = "tpgXml";
         this.tpgXml.Padding = new System.Windows.Forms.Padding(3);
         this.tpgXml.Size = new System.Drawing.Size(1288, 523);
         this.tpgXml.TabIndex = 2;
         this.tpgXml.Text = "转换EXCEL为XML";
         this.tpgXml.UseVisualStyleBackColor = true;
         // 
         // QTemplateExportForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1296, 674);
         this.Controls.Add(this.pnlparent);
         this.Name = "QTemplateExportForm";
         this.Text = "模版导出工具";
         this.toolStrip1.ResumeLayout(false);
         this.toolStrip1.PerformLayout();
         this.tpgExecution.ResumeLayout(false);
         this.panel1.ResumeLayout(false);
         this.panel1.PerformLayout();
         this.pnlparent.ResumeLayout(false);
         this.pnlparent.PerformLayout();
         this.splitContainer1.Panel1.ResumeLayout(false);
         this.splitContainer1.Panel2.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
         this.splitContainer1.ResumeLayout(false);
         this.pnlListvew.ResumeLayout(false);
         this.tbcMain.ResumeLayout(false);
         this.datatables.ResumeLayout(false);
         this.tpgXml.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ToolStrip toolStrip1;
      private System.Windows.Forms.ToolStripButton Tsbonly;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
      private System.Windows.Forms.ToolStripButton TlsClose;
      private System.Windows.Forms.ColumnHeader chdCommandTarget;
      private System.Windows.Forms.ColumnHeader chdCommandFrom;
      private System.Windows.Forms.ColumnHeader chdCommandType;
      private System.Windows.Forms.ListView lvwCopy;
      private System.Windows.Forms.ColumnHeader chdType;
      private System.Windows.Forms.ColumnHeader chdTargets;
      private System.Windows.Forms.ColumnHeader chdSources;
      private System.Windows.Forms.TabPage tpgExecution;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.CheckBox chbCommand;
      private System.Windows.Forms.CheckBox chkAllTrues;
      private System.Windows.Forms.Label label4;
      private System.Windows.Forms.CheckBox Chyn;
      private System.Windows.Forms.Label labCopy;
      private System.Windows.Forms.Label label2;
      private System.Windows.Forms.Label labMerger;
      private System.Windows.Forms.TextBox txtTarget;
      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.TextBox txtTargets;
      private System.Windows.Forms.Label labTemplate;
      private System.Windows.Forms.TextBox txtSource;
      private System.Windows.Forms.ColumnHeader chdValids;
      private System.Windows.Forms.ListView lvwMerger;
      private System.Windows.Forms.Panel pnlparent;
      private System.Windows.Forms.SplitContainer splitContainer1;
      private System.Windows.Forms.Panel pnlListvew;
      private System.Windows.Forms.TabControl tbcMain;
      private System.Windows.Forms.TabPage datatables;
      private System.Windows.Forms.ListView lvwTemplate;
      private System.Windows.Forms.ColumnHeader chdValid;
      private System.Windows.Forms.ColumnHeader chdName;
      private System.Windows.Forms.ColumnHeader chdTarget;
      private System.Windows.Forms.ColumnHeader chdDescription;
      private System.Windows.Forms.TabPage tpgXml;
   }
}