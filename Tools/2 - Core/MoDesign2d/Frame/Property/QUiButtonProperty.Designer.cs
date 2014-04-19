namespace MO.Design2d.Frame.Property
{
   partial class QUiButtonProperty
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
         this.label7 = new System.Windows.Forms.Label();
         this.chkOptionLabel = new MO.Core.Forms.Controls.QCheckBox();
         this.txtText = new MO.Core.Forms.Controls.QTextBox();
         this.labText = new System.Windows.Forms.Label();
         this.chkOptionHand = new MO.Core.Forms.Controls.QCheckBox();
         this.label1 = new System.Windows.Forms.Label();
         this.quiFont = new MO.Design2d.Frame.Property.QUiFontEditor();
         this.quiGroundResource = new MO.Design2d.Frame.Property.QUiPictureEditor();
         this.SuspendLayout();
         // 
         // label7
         // 
         this.label7.AutoSize = true;
         this.label7.Location = new System.Drawing.Point(10, 79);
         this.label7.Name = "label7";
         this.label7.Size = new System.Drawing.Size(53, 12);
         this.label7.TabIndex = 33;
         this.label7.Text = "背景资源";
         // 
         // chkOptionLabel
         // 
         this.chkOptionLabel.AutoSize = true;
         this.chkOptionLabel.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionLabel.Location = new System.Drawing.Point(70, 28);
         this.chkOptionLabel.Name = "chkOptionLabel";
         this.chkOptionLabel.Size = new System.Drawing.Size(69, 16);
         this.chkOptionLabel.TabIndex = 44;
         this.chkOptionLabel.Text = "显示标签";
         this.chkOptionLabel.UseVisualStyleBackColor = true;
         // 
         // txtText
         // 
         this.txtText.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtText.Location = new System.Drawing.Point(70, 3);
         this.txtText.Name = "txtText";
         this.txtText.Size = new System.Drawing.Size(332, 19);
         this.txtText.TabIndex = 43;
         this.txtText.TextChanged += new System.EventHandler(this.txtText_TextChanged);
         // 
         // labText
         // 
         this.labText.AutoSize = true;
         this.labText.Location = new System.Drawing.Point(10, 6);
         this.labText.Name = "labText";
         this.labText.Size = new System.Drawing.Size(53, 12);
         this.labText.TabIndex = 42;
         this.labText.Text = "文本内容";
         // 
         // chkOptionHand
         // 
         this.chkOptionHand.AutoSize = true;
         this.chkOptionHand.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionHand.Location = new System.Drawing.Point(145, 28);
         this.chkOptionHand.Name = "chkOptionHand";
         this.chkOptionHand.Size = new System.Drawing.Size(69, 16);
         this.chkOptionHand.TabIndex = 44;
         this.chkOptionHand.Text = "手状鼠标";
         this.chkOptionHand.UseVisualStyleBackColor = true;
         // 
         // label1
         // 
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(10, 53);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(53, 12);
         this.label1.TabIndex = 33;
         this.label1.Text = "文本字体";
         // 
         // quiFont
         // 
         this.quiFont.Location = new System.Drawing.Point(70, 50);
         this.quiFont.Name = "quiFont";
         this.quiFont.Size = new System.Drawing.Size(404, 20);
         this.quiFont.TabIndex = 45;
         // 
         // quiGroundResource
         // 
         this.quiGroundResource.Location = new System.Drawing.Point(70, 76);
         this.quiGroundResource.Name = "quiGroundResource";
         this.quiGroundResource.Size = new System.Drawing.Size(366, 20);
         this.quiGroundResource.TabIndex = 41;
         // 
         // QUiButtonProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.quiFont);
         this.Controls.Add(this.chkOptionHand);
         this.Controls.Add(this.chkOptionLabel);
         this.Controls.Add(this.txtText);
         this.Controls.Add(this.labText);
         this.Controls.Add(this.quiGroundResource);
         this.Controls.Add(this.label1);
         this.Controls.Add(this.label7);
         this.Name = "QUiButtonProperty";
         this.Size = new System.Drawing.Size(556, 100);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private QUiPictureEditor quiGroundResource;
      private System.Windows.Forms.Label label7;
      private MO.Core.Forms.Controls.QCheckBox chkOptionLabel;
      private MO.Core.Forms.Controls.QTextBox txtText;
      private System.Windows.Forms.Label labText;
      private MO.Core.Forms.Controls.QCheckBox chkOptionHand;
      private QUiFontEditor quiFont;
      private System.Windows.Forms.Label label1;
   }
}
