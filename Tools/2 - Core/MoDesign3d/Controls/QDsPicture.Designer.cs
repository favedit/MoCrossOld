namespace MO.Design3d.Controls
{
   partial class QDsPicture
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
         this.toolStrip1 = new System.Windows.Forms.ToolStrip();
         this.tsbScalex1p4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1p2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex4 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex1 = new System.Windows.Forms.ToolStripButton();
         this.tsbScalex2 = new System.Windows.Forms.ToolStripButton();
         this.tsbScaleAuto = new System.Windows.Forms.ToolStripButton();
         this.qdcPicture = new MO.Core.Forms.Controls.QPictureBox();
         this.toolStrip1.SuspendLayout();
         this.SuspendLayout();
         // 
         // toolStrip1
         // 
         this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbScalex1p4,
            this.tsbScalex1p2,
            this.tsbScalex4,
            this.tsbScalex1,
            this.tsbScalex2,
            this.tsbScaleAuto});
         this.toolStrip1.Location = new System.Drawing.Point(0, 0);
         this.toolStrip1.Name = "toolStrip1";
         this.toolStrip1.Size = new System.Drawing.Size(523, 25);
         this.toolStrip1.TabIndex = 0;
         this.toolStrip1.Text = "toolStrip1";
         // 
         // tsbScalex1p4
         // 
         this.tsbScalex1p4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p4.Name = "tsbScalex1p4";
         this.tsbScalex1p4.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p4.Tag = "0.25";
         this.tsbScalex1p4.Text = "x1/4";
         this.tsbScalex1p4.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1p2
         // 
         this.tsbScalex1p2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1p2.Name = "tsbScalex1p2";
         this.tsbScalex1p2.Size = new System.Drawing.Size(37, 22);
         this.tsbScalex1p2.Tag = "0.5";
         this.tsbScalex1p2.Text = "x1/2";
         this.tsbScalex1p2.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex4
         // 
         this.tsbScalex4.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex4.Name = "tsbScalex4";
         this.tsbScalex4.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex4.Tag = "4";
         this.tsbScalex4.Text = "x4";
         this.tsbScalex4.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex1
         // 
         this.tsbScalex1.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex1.Name = "tsbScalex1";
         this.tsbScalex1.Size = new System.Drawing.Size(36, 22);
         this.tsbScalex1.Tag = "1";
         this.tsbScalex1.Text = "原始";
         this.tsbScalex1.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScalex2
         // 
         this.tsbScalex2.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScalex2.Name = "tsbScalex2";
         this.tsbScalex2.Size = new System.Drawing.Size(25, 22);
         this.tsbScalex2.Tag = "2";
         this.tsbScalex2.Text = "x2";
         this.tsbScalex2.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // tsbScaleAuto
         // 
         this.tsbScaleAuto.Checked = true;
         this.tsbScaleAuto.CheckState = System.Windows.Forms.CheckState.Checked;
         this.tsbScaleAuto.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbScaleAuto.Name = "tsbScaleAuto";
         this.tsbScaleAuto.Size = new System.Drawing.Size(36, 22);
         this.tsbScaleAuto.Tag = "1";
         this.tsbScaleAuto.Text = "自动";
         this.tsbScaleAuto.Click += new System.EventHandler(this.tsbScale_Click);
         // 
         // qdcPicture
         // 
         this.qdcPicture.AutoScroll = true;
         this.qdcPicture.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
         this.qdcPicture.BackColor = System.Drawing.SystemColors.ActiveCaption;
         this.qdcPicture.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qdcPicture.Location = new System.Drawing.Point(0, 25);
         this.qdcPicture.Name = "qdcPicture";
         this.qdcPicture.ScaleMode = MO.Core.Forms.Controls.EPictureBoxMode.Auto;
         this.qdcPicture.ScaleValue = 1F;
         this.qdcPicture.Size = new System.Drawing.Size(523, 380);
         this.qdcPicture.TabIndex = 1;
         // 
         // QDrPicture
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.qdcPicture);
         this.Controls.Add(this.toolStrip1);
         this.Name = "QDrPicture";
         this.Size = new System.Drawing.Size(523, 405);
         this.toolStrip1.ResumeLayout(false);
         this.toolStrip1.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ToolStrip toolStrip1;
      private System.Windows.Forms.ToolStripButton tsbScalex1p4;
      private System.Windows.Forms.ToolStripButton tsbScalex1p2;
      private System.Windows.Forms.ToolStripButton tsbScalex4;
      private System.Windows.Forms.ToolStripButton tsbScalex1;
      private System.Windows.Forms.ToolStripButton tsbScalex2;
      private System.Windows.Forms.ToolStripButton tsbScaleAuto;
      private MO.Core.Forms.Controls.QPictureBox qdcPicture;


   }
}
