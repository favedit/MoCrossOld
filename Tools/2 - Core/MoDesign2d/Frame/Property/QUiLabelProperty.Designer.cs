namespace MO.Design2d.Frame.Property
{
   partial class QUiLabelProperty
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
         this.quiFont = new MO.Design2d.Frame.Property.QUiFontEditor();
         this.txtText = new MO.Core.Forms.Controls.QTextBox();
         this.labText = new System.Windows.Forms.Label();
         this.quiGroundResource = new MO.Design2d.Frame.Property.QUiPictureEditor();
         this.label1 = new System.Windows.Forms.Label();
         this.label7 = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // quiFont
         // 
         this.quiFont.Location = new System.Drawing.Point(69, 28);
         this.quiFont.Name = "quiFont";
         this.quiFont.Size = new System.Drawing.Size(348, 20);
         this.quiFont.TabIndex = 53;
         // 
         // txtText
         // 
         this.txtText.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtText.Location = new System.Drawing.Point(69, 3);
         this.txtText.Name = "txtText";
         this.txtText.Size = new System.Drawing.Size(336, 19);
         this.txtText.TabIndex = 50;
         this.txtText.TextChanged += new System.EventHandler(this.txtText_TextChanged);
         // 
         // labText
         // 
         this.labText.AutoSize = true;
         this.labText.Location = new System.Drawing.Point(9, 6);
         this.labText.Name = "labText";
         this.labText.Size = new System.Drawing.Size(53, 12);
         this.labText.TabIndex = 49;
         this.labText.Text = "文本内容";
         // 
         // quiGroundResource
         // 
         this.quiGroundResource.Location = new System.Drawing.Point(69, 54);
         this.quiGroundResource.Name = "quiGroundResource";
         this.quiGroundResource.Size = new System.Drawing.Size(348, 20);
         this.quiGroundResource.TabIndex = 48;
         // 
         // label1
         // 
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(9, 31);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(53, 12);
         this.label1.TabIndex = 46;
         this.label1.Text = "文本字体";
         // 
         // label7
         // 
         this.label7.AutoSize = true;
         this.label7.Location = new System.Drawing.Point(9, 57);
         this.label7.Name = "label7";
         this.label7.Size = new System.Drawing.Size(53, 12);
         this.label7.TabIndex = 47;
         this.label7.Text = "背景资源";
         // 
         // QUiLabelProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.quiFont);
         this.Controls.Add(this.txtText);
         this.Controls.Add(this.labText);
         this.Controls.Add(this.quiGroundResource);
         this.Controls.Add(this.label1);
         this.Controls.Add(this.label7);
         this.Name = "QUiLabelProperty";
         this.Size = new System.Drawing.Size(420, 82);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private QUiFontEditor quiFont;
      private MO.Core.Forms.Controls.QTextBox txtText;
      private System.Windows.Forms.Label labText;
      private QUiPictureEditor quiGroundResource;
      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.Label label7;
   }
}
