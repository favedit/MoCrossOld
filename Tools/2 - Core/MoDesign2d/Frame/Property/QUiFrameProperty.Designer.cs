namespace MO.Design2d.Frame.Property
{
   partial class QUiFrameProperty
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
         this.qpeGroundResource = new MO.Design2d.Frame.Property.QUiPictureEditor();
         this.label7 = new System.Windows.Forms.Label();
         this.txtCode = new MO.Core.Forms.Controls.QTextBox();
         this.labCode = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // qpeGroundResource
         // 
         this.qpeGroundResource.Location = new System.Drawing.Point(68, 28);
         this.qpeGroundResource.Name = "qpeGroundResource";
         this.qpeGroundResource.Size = new System.Drawing.Size(340, 20);
         this.qpeGroundResource.TabIndex = 41;
         // 
         // label7
         // 
         this.label7.AutoSize = true;
         this.label7.Location = new System.Drawing.Point(3, 31);
         this.label7.Name = "label7";
         this.label7.Size = new System.Drawing.Size(53, 12);
         this.label7.TabIndex = 33;
         this.label7.Text = "背景资源";
         // 
         // txtCode
         // 
         this.txtCode.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtCode.Location = new System.Drawing.Point(68, 3);
         this.txtCode.Name = "txtCode";
         this.txtCode.Size = new System.Drawing.Size(120, 19);
         this.txtCode.TabIndex = 43;
         this.txtCode.TextChanged += new System.EventHandler(this.txtCode_TextChanged);
         // 
         // labCode
         // 
         this.labCode.AutoSize = true;
         this.labCode.Location = new System.Drawing.Point(8, 6);
         this.labCode.Name = "labCode";
         this.labCode.Size = new System.Drawing.Size(29, 12);
         this.labCode.TabIndex = 42;
         this.labCode.Text = "代码";
         // 
         // QUiFrameProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.txtCode);
         this.Controls.Add(this.labCode);
         this.Controls.Add(this.qpeGroundResource);
         this.Controls.Add(this.label7);
         this.Name = "QUiFrameProperty";
         this.Size = new System.Drawing.Size(420, 56);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private QUiPictureEditor qpeGroundResource;
      private System.Windows.Forms.Label label7;
      private MO.Core.Forms.Controls.QTextBox txtCode;
      private System.Windows.Forms.Label labCode;
   }
}
