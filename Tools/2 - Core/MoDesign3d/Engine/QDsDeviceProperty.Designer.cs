namespace MO.Design3d.Engine
{
   partial class QDsDeviceProperty
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
         this.chkWireFrame = new System.Windows.Forms.CheckBox();
         this.SuspendLayout();
         // 
         // chkWireFrame
         // 
         this.chkWireFrame.AutoSize = true;
         this.chkWireFrame.Location = new System.Drawing.Point(3, 3);
         this.chkWireFrame.Name = "chkWireFrame";
         this.chkWireFrame.Size = new System.Drawing.Size(72, 16);
         this.chkWireFrame.TabIndex = 0;
         this.chkWireFrame.Text = "线框模式";
         this.chkWireFrame.UseVisualStyleBackColor = true;
         this.chkWireFrame.CheckedChanged += new System.EventHandler(this.chkWireFrame_CheckedChanged);
         // 
         // QDsDeviceProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.chkWireFrame);
         this.Name = "QDsDeviceProperty";
         this.Size = new System.Drawing.Size(416, 417);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.CheckBox chkWireFrame;
   }
}
