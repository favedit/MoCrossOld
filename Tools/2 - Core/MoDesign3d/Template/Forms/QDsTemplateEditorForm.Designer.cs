namespace MO.Design3d.Template.Forms
{
   partial class QDsTemplateEditorForm
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsTemplateEditorForm));
            this.pnlViewportBorder = new System.Windows.Forms.Panel();
            this.pnlViewport = new System.Windows.Forms.Panel();
            this.cmsScene = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.tsmiModeSelect = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiLine = new System.Windows.Forms.ToolStripSeparator();
            this.tsmiModeTranslation = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiModeRotation = new System.Windows.Forms.ToolStripMenuItem();
            this.tsmiModeScale = new System.Windows.Forms.ToolStripMenuItem();
            this.tsbDropMode = new System.Windows.Forms.ToolStripDropDownButton();
            this.pnlProperty = new System.Windows.Forms.Panel();
            this.pnlCatalog = new System.Windows.Forms.Panel();
            this.tclCatalog = new System.Windows.Forms.TabControl();
            this.tpgTamplateTree = new System.Windows.Forms.TabPage();
            this.tpgContextTree = new System.Windows.Forms.TabPage();
            this.statusStrip1 = new System.Windows.Forms.StatusStrip();
            this.timRefresh = new System.Windows.Forms.Timer(this.components);
            this.toolStrip1 = new System.Windows.Forms.ToolStrip();
            this.tsbSave = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.tsbCatalog = new System.Windows.Forms.ToolStripButton();
            this.tsbProperty = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.tsbWireFrame = new System.Windows.Forms.ToolStripButton();
            this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
            this.tslMode = new System.Windows.Forms.ToolStripLabel();
            this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
            this.tsbClose = new System.Windows.Forms.ToolStripButton();
            this.pnlSpace = new System.Windows.Forms.Panel();
            this.splCatalog = new System.Windows.Forms.Splitter();
            this.splProperty = new System.Windows.Forms.Splitter();
            this.pnlViewportBorder.SuspendLayout();
            this.cmsScene.SuspendLayout();
            this.pnlCatalog.SuspendLayout();
            this.tclCatalog.SuspendLayout();
            this.tpgTamplateTree.SuspendLayout();
            this.toolStrip1.SuspendLayout();
            this.pnlSpace.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlViewportBorder
            // 
            this.pnlViewportBorder.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.pnlViewportBorder.Controls.Add(this.pnlViewport);
            this.pnlViewportBorder.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlViewportBorder.Location = new System.Drawing.Point(364, 0);
            this.pnlViewportBorder.Name = "pnlViewportBorder";
            this.pnlViewportBorder.Size = new System.Drawing.Size(537, 515);
            this.pnlViewportBorder.TabIndex = 11;
            // 
            // pnlViewport
            // 
            this.pnlViewport.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlViewport.Location = new System.Drawing.Point(0, 0);
            this.pnlViewport.Name = "pnlViewport";
            this.pnlViewport.Size = new System.Drawing.Size(533, 511);
            this.pnlViewport.TabIndex = 0;
            this.pnlViewport.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pnlViewport_MouseDown);
            this.pnlViewport.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pnlViewport_MouseMove);
            this.pnlViewport.MouseUp += new System.Windows.Forms.MouseEventHandler(this.pnlViewport_MouseUp);
            this.pnlViewport.Resize += new System.EventHandler(this.pnlViewport_Resize);
            // 
            // cmsScene
            // 
            this.cmsScene.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsmiModeSelect,
            this.tsmiLine,
            this.tsmiModeTranslation,
            this.tsmiModeRotation,
            this.tsmiModeScale});
            this.cmsScene.Name = "cmsScene";
            this.cmsScene.Size = new System.Drawing.Size(101, 98);
            // 
            // tsmiModeSelect
            // 
            this.tsmiModeSelect.Name = "tsmiModeSelect";
            this.tsmiModeSelect.Size = new System.Drawing.Size(100, 22);
            this.tsmiModeSelect.Text = "选择";
            this.tsmiModeSelect.Click += new System.EventHandler(this.tsmiModeSelect_Click);
            // 
            // tsmiLine
            // 
            this.tsmiLine.Name = "tsmiLine";
            this.tsmiLine.Size = new System.Drawing.Size(97, 6);
            // 
            // tsmiModeTranslation
            // 
            this.tsmiModeTranslation.Name = "tsmiModeTranslation";
            this.tsmiModeTranslation.Size = new System.Drawing.Size(100, 22);
            this.tsmiModeTranslation.Text = "平移";
            this.tsmiModeTranslation.Click += new System.EventHandler(this.tsmiModeTranslation_Click);
            // 
            // tsmiModeRotation
            // 
            this.tsmiModeRotation.Name = "tsmiModeRotation";
            this.tsmiModeRotation.Size = new System.Drawing.Size(100, 22);
            this.tsmiModeRotation.Text = "旋转";
            this.tsmiModeRotation.Click += new System.EventHandler(this.tsmiModeRotation_Click);
            // 
            // tsmiModeScale
            // 
            this.tsmiModeScale.Name = "tsmiModeScale";
            this.tsmiModeScale.Size = new System.Drawing.Size(100, 22);
            this.tsmiModeScale.Text = "缩放";
            this.tsmiModeScale.Click += new System.EventHandler(this.tsmiModeScale_Click);
            // 
            // tsbDropMode
            // 
            this.tsbDropMode.Image = ((System.Drawing.Image)(resources.GetObject("tsbDropMode.Image")));
            this.tsbDropMode.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbDropMode.Name = "tsbDropMode";
            this.tsbDropMode.Size = new System.Drawing.Size(61, 22);
            this.tsbDropMode.Text = "选择";
            // 
            // pnlProperty
            // 
            this.pnlProperty.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.pnlProperty.Dock = System.Windows.Forms.DockStyle.Right;
            this.pnlProperty.Location = new System.Drawing.Point(905, 0);
            this.pnlProperty.Name = "pnlProperty";
            this.pnlProperty.Size = new System.Drawing.Size(279, 515);
            this.pnlProperty.TabIndex = 10;
            this.pnlProperty.Visible = false;
            // 
            // pnlCatalog
            // 
            this.pnlCatalog.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.pnlCatalog.Controls.Add(this.tclCatalog);
            this.pnlCatalog.Dock = System.Windows.Forms.DockStyle.Left;
            this.pnlCatalog.Location = new System.Drawing.Point(0, 0);
            this.pnlCatalog.Name = "pnlCatalog";
            this.pnlCatalog.Size = new System.Drawing.Size(360, 515);
            this.pnlCatalog.TabIndex = 9;
            this.pnlCatalog.Visible = false;
            // 
            // tclCatalog
            // 
            this.tclCatalog.Controls.Add(this.tpgTamplateTree);
            this.tclCatalog.Controls.Add(this.tpgContextTree);
            this.tclCatalog.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tclCatalog.Location = new System.Drawing.Point(0, 0);
            this.tclCatalog.Name = "tclCatalog";
            this.tclCatalog.SelectedIndex = 0;
            this.tclCatalog.Size = new System.Drawing.Size(356, 511);
            this.tclCatalog.TabIndex = 1;
            // 
            // tpgTamplateTree
            // 
            this.tpgTamplateTree.Location = new System.Drawing.Point(4, 22);
            this.tpgTamplateTree.Name = "tpgTamplateTree";
            this.tpgTamplateTree.Padding = new System.Windows.Forms.Padding(3);
            this.tpgTamplateTree.Size = new System.Drawing.Size(348, 485);
            this.tpgTamplateTree.TabIndex = 0;
            this.tpgTamplateTree.Text = "模板目录";
            this.tpgTamplateTree.UseVisualStyleBackColor = true;
            // 
            // tpgContextTree
            // 
            this.tpgContextTree.Location = new System.Drawing.Point(4, 22);
            this.tpgContextTree.Name = "tpgContextTree";
            this.tpgContextTree.Padding = new System.Windows.Forms.Padding(3);
            this.tpgContextTree.Size = new System.Drawing.Size(348, 485);
            this.tpgContextTree.TabIndex = 1;
            this.tpgContextTree.Text = "环境目录";
            this.tpgContextTree.UseVisualStyleBackColor = true;
            // 
            // statusStrip1
            // 
            this.statusStrip1.Location = new System.Drawing.Point(0, 540);
            this.statusStrip1.Name = "statusStrip1";
            this.statusStrip1.Size = new System.Drawing.Size(1184, 22);
            this.statusStrip1.TabIndex = 7;
            this.statusStrip1.Text = "statusStrip1";
            // 
            // timRefresh
            // 
            this.timRefresh.Interval = 20;
            this.timRefresh.Tick += new System.EventHandler(this.timRefresh_Tick);
            // 
            // toolStrip1
            // 
            this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSave,
            this.toolStripSeparator2,
            this.tsbCatalog,
            this.tsbProperty,
            this.toolStripSeparator1,
            this.tsbWireFrame,
            this.toolStripSeparator3,
            this.tslMode,
            this.tsbDropMode,
            this.toolStripSeparator4,
            this.tsbClose});
            this.toolStrip1.Location = new System.Drawing.Point(0, 0);
            this.toolStrip1.Name = "toolStrip1";
            this.toolStrip1.Size = new System.Drawing.Size(1184, 25);
            this.toolStrip1.TabIndex = 14;
            this.toolStrip1.Text = "toolStrip1";
            // 
            // tsbSave
            // 
            this.tsbSave.Image = ((System.Drawing.Image)(resources.GetObject("tsbSave.Image")));
            this.tsbSave.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbSave.Name = "tsbSave";
            this.tsbSave.Size = new System.Drawing.Size(52, 22);
            this.tsbSave.Text = "保存";
            // 
            // toolStripSeparator2
            // 
            this.toolStripSeparator2.Name = "toolStripSeparator2";
            this.toolStripSeparator2.Size = new System.Drawing.Size(6, 25);
            // 
            // tsbCatalog
            // 
            this.tsbCatalog.BackColor = System.Drawing.SystemColors.Control;
            this.tsbCatalog.Image = ((System.Drawing.Image)(resources.GetObject("tsbCatalog.Image")));
            this.tsbCatalog.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbCatalog.Name = "tsbCatalog";
            this.tsbCatalog.Size = new System.Drawing.Size(64, 22);
            this.tsbCatalog.Text = "目录框";
            this.tsbCatalog.Click += new System.EventHandler(this.tsbCatalog_Click);
            // 
            // tsbProperty
            // 
            this.tsbProperty.Image = ((System.Drawing.Image)(resources.GetObject("tsbProperty.Image")));
            this.tsbProperty.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbProperty.Name = "tsbProperty";
            this.tsbProperty.Size = new System.Drawing.Size(64, 22);
            this.tsbProperty.Text = "属性框";
            this.tsbProperty.Click += new System.EventHandler(this.tsbProperty_Click);
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
            // 
            // tsbWireFrame
            // 
            this.tsbWireFrame.Image = ((System.Drawing.Image)(resources.GetObject("tsbWireFrame.Image")));
            this.tsbWireFrame.ImageTransparentColor = System.Drawing.Color.Magenta;
            this.tsbWireFrame.Name = "tsbWireFrame";
            this.tsbWireFrame.Size = new System.Drawing.Size(76, 22);
            this.tsbWireFrame.Text = "线框模式";
            this.tsbWireFrame.Click += new System.EventHandler(this.tsbWireFrame_Click);
            // 
            // toolStripSeparator3
            // 
            this.toolStripSeparator3.Name = "toolStripSeparator3";
            this.toolStripSeparator3.Size = new System.Drawing.Size(6, 25);
            // 
            // tslMode
            // 
            this.tslMode.Name = "tslMode";
            this.tslMode.Size = new System.Drawing.Size(32, 22);
            this.tslMode.Text = "模式";
            // 
            // toolStripSeparator4
            // 
            this.toolStripSeparator4.Name = "toolStripSeparator4";
            this.toolStripSeparator4.Size = new System.Drawing.Size(6, 25);
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
            // pnlSpace
            // 
            this.pnlSpace.Controls.Add(this.pnlViewportBorder);
            this.pnlSpace.Controls.Add(this.splCatalog);
            this.pnlSpace.Controls.Add(this.pnlCatalog);
            this.pnlSpace.Controls.Add(this.splProperty);
            this.pnlSpace.Controls.Add(this.pnlProperty);
            this.pnlSpace.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlSpace.Location = new System.Drawing.Point(0, 25);
            this.pnlSpace.Name = "pnlSpace";
            this.pnlSpace.Size = new System.Drawing.Size(1184, 515);
            this.pnlSpace.TabIndex = 15;
            // 
            // splCatalog
            // 
            this.splCatalog.Location = new System.Drawing.Point(360, 0);
            this.splCatalog.Name = "splCatalog";
            this.splCatalog.Size = new System.Drawing.Size(4, 515);
            this.splCatalog.TabIndex = 13;
            this.splCatalog.TabStop = false;
            this.splCatalog.Visible = false;
            // 
            // splProperty
            // 
            this.splProperty.Dock = System.Windows.Forms.DockStyle.Right;
            this.splProperty.Location = new System.Drawing.Point(901, 0);
            this.splProperty.Name = "splProperty";
            this.splProperty.Size = new System.Drawing.Size(4, 515);
            this.splProperty.TabIndex = 15;
            this.splProperty.TabStop = false;
            this.splProperty.Visible = false;
            // 
            // QDsTemplateEditorForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1184, 562);
            this.Controls.Add(this.pnlSpace);
            this.Controls.Add(this.toolStrip1);
            this.Controls.Add(this.statusStrip1);
            this.KeyPreview = true;
            this.Name = "QDsTemplateEditorForm";
            this.Text = "模板编辑器";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.QDsTemplateEditorForm_FormClosing);
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.QDsTemplateEditorForm_FormClosed);
            this.Load += new System.EventHandler(this.QDsTemplateEditorForm_Load);
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.QDsTemplateEditorForm_KeyDown);
            this.KeyUp += new System.Windows.Forms.KeyEventHandler(this.QDsTemplateEditorForm_KeyUp);
            this.pnlViewportBorder.ResumeLayout(false);
            this.cmsScene.ResumeLayout(false);
            this.pnlCatalog.ResumeLayout(false);
            this.tclCatalog.ResumeLayout(false);
            this.tpgTamplateTree.ResumeLayout(false);
            this.toolStrip1.ResumeLayout(false);
            this.toolStrip1.PerformLayout();
            this.pnlSpace.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.Panel pnlViewportBorder;
      private System.Windows.Forms.Panel pnlProperty;
      private System.Windows.Forms.Panel pnlCatalog;
      private System.Windows.Forms.StatusStrip statusStrip1;
      private System.Windows.Forms.Timer timRefresh;
      private System.Windows.Forms.ToolStrip toolStrip1;
      private System.Windows.Forms.Panel pnlSpace;
      private System.Windows.Forms.Splitter splProperty;
      private System.Windows.Forms.Splitter splCatalog;
      private System.Windows.Forms.ToolStripButton tsbSave;
      private System.Windows.Forms.ToolStripButton tsbClose;
      private System.Windows.Forms.Panel pnlViewport;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
      private System.Windows.Forms.ToolStripButton tsbCatalog;
      private System.Windows.Forms.ToolStripButton tsbProperty;
      private System.Windows.Forms.TabControl tclCatalog;
      private System.Windows.Forms.TabPage tpgTamplateTree;
      private System.Windows.Forms.TabPage tpgContextTree;
      private System.Windows.Forms.ToolStripButton tsbWireFrame;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
      private System.Windows.Forms.ContextMenuStrip cmsScene;
      private System.Windows.Forms.ToolStripMenuItem tsmiModeSelect;
      private System.Windows.Forms.ToolStripMenuItem tsmiModeRotation;
      private System.Windows.Forms.ToolStripMenuItem tsmiModeScale;
      private System.Windows.Forms.ToolStripSeparator tsmiLine;
      private System.Windows.Forms.ToolStripMenuItem tsmiModeTranslation;
      private System.Windows.Forms.ToolStripDropDownButton tsbDropMode;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
      private System.Windows.Forms.ToolStripLabel tslMode;

   }
}