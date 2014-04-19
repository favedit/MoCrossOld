namespace MO.Design3d.Controls
{
   partial class QDsPictureProperty
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
         this.components = new System.ComponentModel.Container();
         this.panel1 = new System.Windows.Forms.Panel();
         this.TxturePanel = new System.Windows.Forms.Panel();
         this.qPictureBox = new System.Windows.Forms.PictureBox();
         this.panel2 = new System.Windows.Forms.Panel();
         this.toolStrip1 = new System.Windows.Forms.ToolStrip();
         this.tsbRestore = new System.Windows.Forms.ToolStripButton();
         this.tsbMagnify2 = new System.Windows.Forms.ToolStripButton();
         this.tsbMagnify4 = new System.Windows.Forms.ToolStripButton();
         this.tsbMagnify8 = new System.Windows.Forms.ToolStripButton();
         this.tsbAutoSize = new System.Windows.Forms.ToolStripButton();
         this.timZoom = new System.Windows.Forms.Timer(this.components);
         this.panel1.SuspendLayout();
         this.TxturePanel.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.qPictureBox)).BeginInit();
         this.panel2.SuspendLayout();
         this.toolStrip1.SuspendLayout();
         this.SuspendLayout();
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.TxturePanel);
         this.panel1.Controls.Add(this.panel2);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(636, 378);
         this.panel1.TabIndex = 0;
         // 
         // TxturePanel
         // 
         this.TxturePanel.AutoScroll = true;
         this.TxturePanel.BackColor = System.Drawing.SystemColors.ButtonShadow;
         this.TxturePanel.Controls.Add(this.qPictureBox);
         this.TxturePanel.Dock = System.Windows.Forms.DockStyle.Fill;
         this.TxturePanel.Location = new System.Drawing.Point(0, 25);
         this.TxturePanel.Name = "TxturePanel";
         this.TxturePanel.Size = new System.Drawing.Size(636, 353);
         this.TxturePanel.TabIndex = 2;
         // 
         // qPictureBox
         // 
         this.qPictureBox.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
         this.qPictureBox.Cursor = System.Windows.Forms.Cursors.Arrow;
         this.qPictureBox.Location = new System.Drawing.Point(25, 32);
         this.qPictureBox.Name = "qPictureBox";
         this.qPictureBox.Size = new System.Drawing.Size(154, 61);
         this.qPictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
         this.qPictureBox.TabIndex = 0;
         this.qPictureBox.TabStop = false;
         this.qPictureBox.MouseDown += new System.Windows.Forms.MouseEventHandler(this.qPictureBox_MouseDown);
         this.qPictureBox.MouseMove += new System.Windows.Forms.MouseEventHandler(this.qPictureBox_MouseMove);
         // 
         // panel2
         // 
         this.panel2.Controls.Add(this.toolStrip1);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel2.Location = new System.Drawing.Point(0, 0);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(636, 25);
         this.panel2.TabIndex = 3;
         // 
         // toolStrip1
         // 
         this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbRestore,
            this.tsbMagnify2,
            this.tsbMagnify4,
            this.tsbMagnify8,
            this.tsbAutoSize});
         this.toolStrip1.Location = new System.Drawing.Point(0, 0);
         this.toolStrip1.Name = "toolStrip1";
         this.toolStrip1.Size = new System.Drawing.Size(636, 25);
         this.toolStrip1.TabIndex = 2;
         this.toolStrip1.Text = "toolStrip1";
         // 
         // tsbRestore
         // 
         this.tsbRestore.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbRestore.Name = "tsbRestore";
         this.tsbRestore.Size = new System.Drawing.Size(52, 22);
         this.tsbRestore.Text = "还原";
         this.tsbRestore.Click += new System.EventHandler(this.tsbRestore_Click);
         // 
         // tsbMagnify2
         // 
         this.tsbMagnify2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbMagnify2.Name = "tsbMagnify2";
         this.tsbMagnify2.Size = new System.Drawing.Size(67, 22);
         this.tsbMagnify2.Text = "放大X2";
         this.tsbMagnify2.Click += new System.EventHandler(this.tsbMagnify2_Click);
         // 
         // tsbMagnify4
         // 
         this.tsbMagnify4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbMagnify4.Name = "tsbMagnify4";
         this.tsbMagnify4.Size = new System.Drawing.Size(67, 22);
         this.tsbMagnify4.Text = "放大X4";
         this.tsbMagnify4.Click += new System.EventHandler(this.tsbMagnify4_Click);
         // 
         // tsbMagnify8
         // 
         this.tsbMagnify8.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbMagnify8.Name = "tsbMagnify8";
         this.tsbMagnify8.Size = new System.Drawing.Size(67, 22);
         this.tsbMagnify8.Text = "放大X8";
         this.tsbMagnify8.Click += new System.EventHandler(this.tsbMagnify8_Click);
         // 
         // tsbAutoSize
         // 
         this.tsbAutoSize.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbAutoSize.Name = "tsbAutoSize";
         this.tsbAutoSize.Size = new System.Drawing.Size(76, 22);
         this.tsbAutoSize.Text = "自动适应";
         this.tsbAutoSize.Click += new System.EventHandler(this.tsbAutoSize_Click);
         // 
         // QDrPictureProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel1);
         this.Name = "QDrPictureProperty";
         this.Size = new System.Drawing.Size(636, 378);
         this.panel1.ResumeLayout(false);
         this.TxturePanel.ResumeLayout(false);
         ((System.ComponentModel.ISupportInitialize)(this.qPictureBox)).EndInit();
         this.panel2.ResumeLayout(false);
         this.panel2.PerformLayout();
         this.toolStrip1.ResumeLayout(false);
         this.toolStrip1.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.Panel TxturePanel;
      private System.Windows.Forms.PictureBox qPictureBox;
      private System.Windows.Forms.Panel panel2;
      private System.Windows.Forms.ToolStrip toolStrip1;
      private System.Windows.Forms.ToolStripButton tsbRestore;
      private System.Windows.Forms.ToolStripButton tsbMagnify2;
      private System.Windows.Forms.ToolStripButton tsbMagnify4;
      private System.Windows.Forms.ToolStripButton tsbMagnify8;
      private System.Windows.Forms.ToolStripButton tsbAutoSize;
      private System.Windows.Forms.Timer timZoom;


   }
}
