namespace MO.Design3d.Theme.Controls
{
   partial class QDsThemeProperty
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsThemeProperty));
         this.panel2 = new System.Windows.Forms.Panel();
         this.txtName = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbOpen = new System.Windows.Forms.ToolStripButton();
         this.tspSave = new System.Windows.Forms.ToolStripButton();
         this.tspToolSprit = new System.Windows.Forms.ToolStrip();
         this.tsbExport = new System.Windows.Forms.ToolStripButton();
         this.panel1 = new System.Windows.Forms.Panel();
         this.colorShow = new System.Windows.Forms.ColorDialog();
         this.panel2.SuspendLayout();
         this.tspToolSprit.SuspendLayout();
         this.panel1.SuspendLayout();
         this.SuspendLayout();
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.txtName);
         this.panel2.Controls.Add(this.labName);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel2.Location = new System.Drawing.Point(0, 25);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(991, 640);
         this.panel2.TabIndex = 7;
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(96, 6);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(335, 21);
         this.txtName.TabIndex = 80;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(13, 9);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 79;
         this.labName.Text = "名称";
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbOpen
         // 
         this.tsbOpen.Image = ((System.Drawing.Image)(resources.GetObject("tsbOpen.Image")));
         this.tsbOpen.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbOpen.Name = "tsbOpen";
         this.tsbOpen.Size = new System.Drawing.Size(52, 22);
         this.tsbOpen.Text = "打开";
         this.tsbOpen.Click += new System.EventHandler(this.tsbOpen_Click);
         // 
         // tspSave
         // 
         this.tspSave.Image = ((System.Drawing.Image)(resources.GetObject("tspSave.Image")));
         this.tspSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspSave.Name = "tspSave";
         this.tspSave.Size = new System.Drawing.Size(52, 22);
         this.tspSave.Text = "保存";
         this.tspSave.Click += new System.EventHandler(this.tspSave_Click);
         // 
         // tspToolSprit
         // 
         this.tspToolSprit.AutoSize = false;
         this.tspToolSprit.Dock = System.Windows.Forms.DockStyle.Fill;
         this.tspToolSprit.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tspSave,
            this.tsbOpen,
            this.toolStripSeparator1,
            this.tsbExport});
         this.tspToolSprit.Location = new System.Drawing.Point(0, 0);
         this.tspToolSprit.Name = "tspToolSprit";
         this.tspToolSprit.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspToolSprit.Size = new System.Drawing.Size(991, 25);
         this.tspToolSprit.TabIndex = 12;
         this.tspToolSprit.Text = "toolStrip1";
         // 
         // tsbExport
         // 
         this.tsbExport.Image = ((System.Drawing.Image)(resources.GetObject("tsbExport.Image")));
         this.tsbExport.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbExport.Name = "tsbExport";
         this.tsbExport.Size = new System.Drawing.Size(52, 22);
         this.tsbExport.Text = "导出";
         this.tsbExport.Click += new System.EventHandler(this.tspExport_Click);
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.tspToolSprit);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(991, 25);
         this.panel1.TabIndex = 6;
         // 
         // QDsThemeProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel2);
         this.Controls.Add(this.panel1);
         this.Name = "QDsThemeProperty";
         this.Size = new System.Drawing.Size(991, 665);
         this.panel2.ResumeLayout(false);
         this.panel2.PerformLayout();
         this.tspToolSprit.ResumeLayout(false);
         this.tspToolSprit.PerformLayout();
         this.panel1.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel panel2;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbOpen;
      private System.Windows.Forms.ToolStripButton tspSave;
      public System.Windows.Forms.ToolStrip tspToolSprit;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.ColorDialog colorShow;
      private System.Windows.Forms.ToolStripButton tsbExport;
   }
}
