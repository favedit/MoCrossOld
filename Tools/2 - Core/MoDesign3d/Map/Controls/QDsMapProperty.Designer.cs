namespace MO.Design3d.Map.Controls
{
   partial class QDsMapProperty
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
         this.tspToolSprit = new System.Windows.Forms.ToolStrip();
         this.tspSave = new System.Windows.Forms.ToolStripButton();
         this.toolStripButton1 = new System.Windows.Forms.ToolStripButton();
         this.panel1 = new System.Windows.Forms.Panel();
         this.labLabel = new System.Windows.Forms.Label();
         this.txtLabel = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.TxtName = new System.Windows.Forms.TextBox();
         this.tspToolSprit.SuspendLayout();
         this.panel1.SuspendLayout();
         this.SuspendLayout();
         // 
         // tspToolSprit
         // 
         this.tspToolSprit.AutoSize = false;
         this.tspToolSprit.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tspSave,
            this.toolStripButton1});
         this.tspToolSprit.Location = new System.Drawing.Point(0, 0);
         this.tspToolSprit.Name = "tspToolSprit";
         this.tspToolSprit.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
         this.tspToolSprit.Size = new System.Drawing.Size(608, 25);
         this.tspToolSprit.TabIndex = 13;
         this.tspToolSprit.Text = "toolStrip1";
         // 
         // tspSave
         // 
         this.tspSave.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tspSave.Name = "tspSave";
         this.tspSave.Size = new System.Drawing.Size(52, 22);
         this.tspSave.Text = "保存";
         this.tspSave.Click += new System.EventHandler(this.tspSave_Click);
         // 
         // toolStripButton1
         // 
         this.toolStripButton1.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.toolStripButton1.Name = "toolStripButton1";
         this.toolStripButton1.Size = new System.Drawing.Size(52, 22);
         this.toolStripButton1.Text = "导出";
         this.toolStripButton1.Click += new System.EventHandler(this.toolStripButton1_Click);
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.labLabel);
         this.panel1.Controls.Add(this.txtLabel);
         this.panel1.Controls.Add(this.labName);
         this.panel1.Controls.Add(this.TxtName);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel1.Location = new System.Drawing.Point(0, 25);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(608, 385);
         this.panel1.TabIndex = 14;
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(77, 77);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 3;
         this.labLabel.Text = "标题";
         // 
         // txtLabel
         // 
         this.txtLabel.Location = new System.Drawing.Point(113, 74);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.Size = new System.Drawing.Size(146, 21);
         this.txtLabel.TabIndex = 2;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(77, 50);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 1;
         this.labName.Text = "名称";
         // 
         // TxtName
         // 
         this.TxtName.Location = new System.Drawing.Point(112, 47);
         this.TxtName.Name = "TxtName";
         this.TxtName.Size = new System.Drawing.Size(146, 21);
         this.TxtName.TabIndex = 0;
         // 
         // QDrMapView
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel1);
         this.Controls.Add(this.tspToolSprit);
         this.Name = "QDrMapView";
         this.Size = new System.Drawing.Size(608, 410);
         this.tspToolSprit.ResumeLayout(false);
         this.tspToolSprit.PerformLayout();
         this.panel1.ResumeLayout(false);
         this.panel1.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      public System.Windows.Forms.ToolStrip tspToolSprit;
      private System.Windows.Forms.ToolStripButton tspSave;
      private System.Windows.Forms.ToolStripButton toolStripButton1;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.TextBox TxtName;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.TextBox txtLabel;
   }
}
