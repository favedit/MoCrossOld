namespace MO.Design2d.Frame.Property
{
   partial class QUiPageProperty
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
         this.chkOptionDefault = new MO.Core.Forms.Controls.QCheckBox();
         this.SuspendLayout();
         // 
         // chkOptionDefault
         // 
         this.chkOptionDefault.AutoSize = true;
         this.chkOptionDefault.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionDefault.Location = new System.Drawing.Point(62, 3);
         this.chkOptionDefault.Name = "chkOptionDefault";
         this.chkOptionDefault.Size = new System.Drawing.Size(57, 16);
         this.chkOptionDefault.TabIndex = 55;
         this.chkOptionDefault.Text = "默认页";
         this.chkOptionDefault.UseVisualStyleBackColor = true;
         this.chkOptionDefault.CheckedChanged += new System.EventHandler(this.chkOptionDefault_CheckedChanged);
         // 
         // QUiPageProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.chkOptionDefault);
         this.Name = "QUiPageProperty";
         this.Size = new System.Drawing.Size(420, 22);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QCheckBox chkOptionDefault;

   }
}
