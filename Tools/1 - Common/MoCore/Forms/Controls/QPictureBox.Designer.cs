namespace MO.Core.Forms.Controls
{
   partial class QPictureBox
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
         if(disposing && (components != null)) {
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
         this.pbxImage = new System.Windows.Forms.PictureBox();
         this.pnlScroll = new System.Windows.Forms.Panel();
         ((System.ComponentModel.ISupportInitialize)(this.pbxImage)).BeginInit();
         this.pnlScroll.SuspendLayout();
         this.SuspendLayout();
         // 
         // pbxImage
         // 
         this.pbxImage.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.pbxImage.Location = new System.Drawing.Point(0, 0);
         this.pbxImage.Margin = new System.Windows.Forms.Padding(0);
         this.pbxImage.Name = "pbxImage";
         this.pbxImage.Size = new System.Drawing.Size(300, 200);
         this.pbxImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize;
         this.pbxImage.TabIndex = 0;
         this.pbxImage.TabStop = false;
         this.pbxImage.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbxImage_MouseDown);
         this.pbxImage.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pbxImage_MouseMove);
         this.pbxImage.MouseUp += new System.Windows.Forms.MouseEventHandler(this.pbxImage_MouseUp);
         // 
         // pnlScroll
         // 
         this.pnlScroll.AutoScroll = true;
         this.pnlScroll.Controls.Add(this.pbxImage);
         this.pnlScroll.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlScroll.Location = new System.Drawing.Point(0, 0);
         this.pnlScroll.Name = "pnlScroll";
         this.pnlScroll.Size = new System.Drawing.Size(478, 300);
         this.pnlScroll.TabIndex = 1;
         // 
         // QPictureBox
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.AutoScroll = true;
         this.BackColor = System.Drawing.SystemColors.Window;
         this.Controls.Add(this.pnlScroll);
         this.Name = "QPictureBox";
         this.Size = new System.Drawing.Size(478, 300);
         this.Resize += new System.EventHandler(this.QPictureBox_Resize);
         ((System.ComponentModel.ISupportInitialize)(this.pbxImage)).EndInit();
         this.pnlScroll.ResumeLayout(false);
         this.pnlScroll.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.PictureBox pbxImage;
      private System.Windows.Forms.Panel pnlScroll;
   }
}
