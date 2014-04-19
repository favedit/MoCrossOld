namespace MO.Design2d.Frame.Property
{
   partial class QUiComponentProperty
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
         this.chkOptionLink = new MO.Core.Forms.Controls.QCheckBox();
         this.SuspendLayout();
         // 
         // chkOptionLink
         // 
         this.chkOptionLink.AutoSize = true;
         this.chkOptionLink.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionLink.Location = new System.Drawing.Point(14, 3);
         this.chkOptionLink.Name = "chkOptionLink";
         this.chkOptionLink.Size = new System.Drawing.Size(69, 16);
         this.chkOptionLink.TabIndex = 13;
         this.chkOptionLink.Text = "是否关联";
         this.chkOptionLink.UseVisualStyleBackColor = true;
         this.chkOptionLink.CheckedChanged += new System.EventHandler(this.chkOptionLink_CheckedChanged);
         // 
         // QUiComponentProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.chkOptionLink);
         this.Name = "QUiComponentProperty";
         this.Size = new System.Drawing.Size(379, 23);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QCheckBox chkOptionLink;

   }
}
