namespace MO.Design2d.Resource.Forms
{
   partial class QDsResourceViewForm
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsResourceViewForm));
         this.toolStrip1 = new System.Windows.Forms.ToolStrip();
         this.tsbImageCut = new System.Windows.Forms.ToolStripButton();
         this.tsbSaveImagePath = new System.Windows.Forms.ToolStripButton();
         this.saveFileDialogInfo = new System.Windows.Forms.SaveFileDialog();
         this.picBoxImage = new System.Windows.Forms.PictureBox();
         this.toolStrip1.SuspendLayout();
         ((System.ComponentModel.ISupportInitialize)(this.picBoxImage)).BeginInit();
         this.SuspendLayout();
         // 
         // toolStrip1
         // 
         this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSaveImagePath,
            this.tsbImageCut});
         this.toolStrip1.Location = new System.Drawing.Point(0, 0);
         this.toolStrip1.Name = "toolStrip1";
         this.toolStrip1.Size = new System.Drawing.Size(929, 25);
         this.toolStrip1.TabIndex = 0;
         this.toolStrip1.Text = "toolStrip1";
         // 
         // tsbImageCut
         // 
         this.tsbImageCut.Image = ((System.Drawing.Image)(resources.GetObject("tsbImageCut.Image")));
         this.tsbImageCut.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbImageCut.Name = "tsbImageCut";
         this.tsbImageCut.Size = new System.Drawing.Size(76, 22);
         this.tsbImageCut.Text = "压缩存储";
         this.tsbImageCut.Click += new System.EventHandler(this.tsbImageCut_Click);
         // 
         // tsbSaveImagePath
         // 
         this.tsbSaveImagePath.Image = ((System.Drawing.Image)(resources.GetObject("tsbSaveImagePath.Image")));
         this.tsbSaveImagePath.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSaveImagePath.Name = "tsbSaveImagePath";
         this.tsbSaveImagePath.Size = new System.Drawing.Size(52, 22);
         this.tsbSaveImagePath.Text = "另存";
         this.tsbSaveImagePath.Click += new System.EventHandler(this.tsbSaveImagePath_Click);
         // 
         // picBoxImage
         // 
         this.picBoxImage.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.picBoxImage.Location = new System.Drawing.Point(0, 28);
         this.picBoxImage.Name = "picBoxImage";
         this.picBoxImage.Size = new System.Drawing.Size(929, 545);
         this.picBoxImage.TabIndex = 2;
         this.picBoxImage.TabStop = false;
         // 
         // QDsShowImageForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.BackColor = System.Drawing.SystemColors.ButtonFace;
         this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
         this.ClientSize = new System.Drawing.Size(929, 576);
         this.Controls.Add(this.picBoxImage);
         this.Controls.Add(this.toolStrip1);
         this.Name = "QDsShowImageForm";
         this.Text = "图片资源处理";
         this.toolStrip1.ResumeLayout(false);
         this.toolStrip1.PerformLayout();
         ((System.ComponentModel.ISupportInitialize)(this.picBoxImage)).EndInit();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ToolStrip toolStrip1;
      private System.Windows.Forms.ToolStripButton tsbImageCut;
      private System.Windows.Forms.SaveFileDialog saveFileDialogInfo;
      private System.Windows.Forms.PictureBox picBoxImage;
      private System.Windows.Forms.ToolStripButton tsbSaveImagePath;


   }
}