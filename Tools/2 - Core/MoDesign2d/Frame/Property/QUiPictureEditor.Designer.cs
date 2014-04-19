namespace MO.Design2d.Frame.Property
{
   partial class QUiPictureEditor
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiPictureEditor));
         this.txtCode = new MO.Core.Forms.Controls.QTextBox();
         this.button1 = new System.Windows.Forms.Button();
         this.checkBox1 = new System.Windows.Forms.CheckBox();
         this.txtLocation = new MO.Core.Forms.Controls.QTextBox();
         this.txtPadding = new MO.Core.Forms.Controls.QTextBox();
         this.cboAlignCd = new System.Windows.Forms.ComboBox();
         this.SuspendLayout();
         // 
         // txtCode
         // 
         this.txtCode.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtCode.Location = new System.Drawing.Point(18, 0);
         this.txtCode.Name = "txtCode";
         this.txtCode.Size = new System.Drawing.Size(80, 19);
         this.txtCode.TabIndex = 0;
         this.txtCode.TextChanged += new System.EventHandler(this.txtCode_TextChanged);
         // 
         // button1
         // 
         this.button1.Cursor = System.Windows.Forms.Cursors.Hand;
         this.button1.FlatAppearance.BorderSize = 0;
         this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.button1.Image = ((System.Drawing.Image)(resources.GetObject("button1.Image")));
         this.button1.Location = new System.Drawing.Point(102, 0);
         this.button1.Name = "button1";
         this.button1.Size = new System.Drawing.Size(20, 20);
         this.button1.TabIndex = 1;
         this.button1.UseVisualStyleBackColor = true;
         // 
         // checkBox1
         // 
         this.checkBox1.AutoSize = true;
         this.checkBox1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.checkBox1.Location = new System.Drawing.Point(0, 3);
         this.checkBox1.Name = "checkBox1";
         this.checkBox1.Size = new System.Drawing.Size(12, 11);
         this.checkBox1.TabIndex = 2;
         this.checkBox1.UseVisualStyleBackColor = true;
         // 
         // txtLocation
         // 
         this.txtLocation.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtLocation.Location = new System.Drawing.Point(192, 0);
         this.txtLocation.Name = "txtLocation";
         this.txtLocation.Size = new System.Drawing.Size(60, 19);
         this.txtLocation.TabIndex = 0;
         // 
         // txtPadding
         // 
         this.txtPadding.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtPadding.Location = new System.Drawing.Point(255, 0);
         this.txtPadding.Name = "txtPadding";
         this.txtPadding.Size = new System.Drawing.Size(80, 19);
         this.txtPadding.TabIndex = 0;
         // 
         // cboAlignCd
         // 
         this.cboAlignCd.FormattingEnabled = true;
         this.cboAlignCd.Location = new System.Drawing.Point(129, 0);
         this.cboAlignCd.Name = "cboAlignCd";
         this.cboAlignCd.Size = new System.Drawing.Size(60, 20);
         this.cboAlignCd.TabIndex = 3;
         // 
         // QUiPictureEditor
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.cboAlignCd);
         this.Controls.Add(this.checkBox1);
         this.Controls.Add(this.button1);
         this.Controls.Add(this.txtPadding);
         this.Controls.Add(this.txtLocation);
         this.Controls.Add(this.txtCode);
         this.Name = "QUiPictureEditor";
         this.Size = new System.Drawing.Size(468, 20);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QTextBox txtCode;
      private System.Windows.Forms.Button button1;
      private System.Windows.Forms.CheckBox checkBox1;
      private MO.Core.Forms.Controls.QTextBox txtLocation;
      private MO.Core.Forms.Controls.QTextBox txtPadding;
      private System.Windows.Forms.ComboBox cboAlignCd;
   }
}
