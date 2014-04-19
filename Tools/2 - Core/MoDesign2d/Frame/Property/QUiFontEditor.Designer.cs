namespace MO.Design2d.Frame.Property
{
   partial class QUiFontEditor
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiFontEditor));
         this.cboFontName = new System.Windows.Forms.ComboBox();
         this.checkBox1 = new System.Windows.Forms.CheckBox();
         this.txtSize = new MO.Core.Forms.Controls.QTextBox();
         this.txtColor = new MO.Core.Forms.Controls.QTextBox();
         this.chkItalic = new System.Windows.Forms.CheckBox();
         this.chkBold = new System.Windows.Forms.CheckBox();
         this.chkUnderline = new System.Windows.Forms.CheckBox();
         this.chkStrikeout = new System.Windows.Forms.CheckBox();
         this.button1 = new System.Windows.Forms.Button();
         this.SuspendLayout();
         // 
         // cboFontName
         // 
         this.cboFontName.FormattingEnabled = true;
         this.cboFontName.Items.AddRange(new object[] {
            "",
            "宋体"});
         this.cboFontName.Location = new System.Drawing.Point(18, 0);
         this.cboFontName.Name = "cboFontName";
         this.cboFontName.Size = new System.Drawing.Size(66, 20);
         this.cboFontName.TabIndex = 9;
         // 
         // checkBox1
         // 
         this.checkBox1.AutoSize = true;
         this.checkBox1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.checkBox1.Location = new System.Drawing.Point(0, 4);
         this.checkBox1.Name = "checkBox1";
         this.checkBox1.Size = new System.Drawing.Size(12, 11);
         this.checkBox1.TabIndex = 8;
         this.checkBox1.UseVisualStyleBackColor = true;
         // 
         // txtSize
         // 
         this.txtSize.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtSize.Location = new System.Drawing.Point(187, 0);
         this.txtSize.Name = "txtSize";
         this.txtSize.Size = new System.Drawing.Size(30, 19);
         this.txtSize.TabIndex = 4;
         this.txtSize.TextChanged += new System.EventHandler(this.txtSize_TextChanged);
         // 
         // txtColor
         // 
         this.txtColor.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtColor.Location = new System.Drawing.Point(90, 0);
         this.txtColor.Name = "txtColor";
         this.txtColor.Size = new System.Drawing.Size(70, 19);
         this.txtColor.TabIndex = 5;
         // 
         // chkItalic
         // 
         this.chkItalic.AutoSize = true;
         this.chkItalic.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkItalic.Location = new System.Drawing.Point(220, 3);
         this.chkItalic.Name = "chkItalic";
         this.chkItalic.Size = new System.Drawing.Size(27, 16);
         this.chkItalic.TabIndex = 8;
         this.chkItalic.Text = "I";
         this.chkItalic.UseVisualStyleBackColor = true;
         // 
         // chkBold
         // 
         this.chkBold.AutoSize = true;
         this.chkBold.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkBold.Location = new System.Drawing.Point(250, 3);
         this.chkBold.Name = "chkBold";
         this.chkBold.Size = new System.Drawing.Size(27, 16);
         this.chkBold.TabIndex = 10;
         this.chkBold.Text = "B";
         this.chkBold.UseVisualStyleBackColor = true;
         // 
         // chkUnderline
         // 
         this.chkUnderline.AutoSize = true;
         this.chkUnderline.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkUnderline.Location = new System.Drawing.Point(280, 3);
         this.chkUnderline.Name = "chkUnderline";
         this.chkUnderline.Size = new System.Drawing.Size(27, 16);
         this.chkUnderline.TabIndex = 11;
         this.chkUnderline.Text = "U";
         this.chkUnderline.UseVisualStyleBackColor = true;
         // 
         // chkStrikeout
         // 
         this.chkStrikeout.AutoSize = true;
         this.chkStrikeout.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkStrikeout.Location = new System.Drawing.Point(310, 3);
         this.chkStrikeout.Name = "chkStrikeout";
         this.chkStrikeout.Size = new System.Drawing.Size(27, 16);
         this.chkStrikeout.TabIndex = 12;
         this.chkStrikeout.Text = "S";
         this.chkStrikeout.UseVisualStyleBackColor = true;
         // 
         // button1
         // 
         this.button1.Cursor = System.Windows.Forms.Cursors.Hand;
         this.button1.FlatAppearance.BorderSize = 0;
         this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.button1.Image = ((System.Drawing.Image)(resources.GetObject("button1.Image")));
         this.button1.Location = new System.Drawing.Point(162, 0);
         this.button1.Name = "button1";
         this.button1.Size = new System.Drawing.Size(20, 20);
         this.button1.TabIndex = 13;
         this.button1.UseVisualStyleBackColor = true;
         // 
         // QUiFontEditor
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.button1);
         this.Controls.Add(this.chkStrikeout);
         this.Controls.Add(this.chkUnderline);
         this.Controls.Add(this.chkBold);
         this.Controls.Add(this.cboFontName);
         this.Controls.Add(this.chkItalic);
         this.Controls.Add(this.checkBox1);
         this.Controls.Add(this.txtSize);
         this.Controls.Add(this.txtColor);
         this.Name = "QUiFontEditor";
         this.Size = new System.Drawing.Size(477, 20);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.ComboBox cboFontName;
      private System.Windows.Forms.CheckBox checkBox1;
      private MO.Core.Forms.Controls.QTextBox txtSize;
      private MO.Core.Forms.Controls.QTextBox txtColor;
      private System.Windows.Forms.CheckBox chkItalic;
      private System.Windows.Forms.CheckBox chkBold;
      private System.Windows.Forms.CheckBox chkUnderline;
      private System.Windows.Forms.CheckBox chkStrikeout;
      private System.Windows.Forms.Button button1;
   }
}
