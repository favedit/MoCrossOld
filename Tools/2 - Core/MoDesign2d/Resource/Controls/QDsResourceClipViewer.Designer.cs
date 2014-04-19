namespace MO.Design2d.Resource.Controls
{
   partial class QDsResourceClipViewer
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
         this.pbxViewer = new System.Windows.Forms.PictureBox();
         ((System.ComponentModel.ISupportInitialize)(this.pbxViewer)).BeginInit();
         this.SuspendLayout();
         // 
         // pbxViewer
         // 
         this.pbxViewer.Location = new System.Drawing.Point(3, 3);
         this.pbxViewer.Name = "pbxViewer";
         this.pbxViewer.Size = new System.Drawing.Size(117, 139);
         this.pbxViewer.TabIndex = 0;
         this.pbxViewer.TabStop = false;
         this.pbxViewer.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbxViewer_MouseDown);
         this.pbxViewer.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pbxViewer_MouseMove);
         this.pbxViewer.MouseUp += new System.Windows.Forms.MouseEventHandler(this.pbxViewer_MouseUp);
         // 
         // QDsResourceClipViewer
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.AutoScroll = true;
         this.BackColor = System.Drawing.Color.White;
         this.Controls.Add(this.pbxViewer);
         this.Name = "QDsResourceClipViewer";
         this.Size = new System.Drawing.Size(130, 174);
         this.Resize += new System.EventHandler(this.QResPictureViewer_Resize);
         ((System.ComponentModel.ISupportInitialize)(this.pbxViewer)).EndInit();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.PictureBox pbxViewer;
   }
}
