namespace MO.Design2d.Frame.Property
{
   partial class QUiObjectProperty
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
         this.label1 = new System.Windows.Forms.Label();
         this.label2 = new System.Windows.Forms.Label();
         this.txtTypeName = new MO.Core.Forms.Controls.QTextBox();
         this.txtClassName = new MO.Core.Forms.Controls.QTextBox();
         this.chkOptionValid = new MO.Core.Forms.Controls.QCheckBox();
         this.txtLabel = new MO.Core.Forms.Controls.QTextBox();
         this.labLabel = new System.Windows.Forms.Label();
         this.labHint = new System.Windows.Forms.Label();
         this.txtHint = new MO.Core.Forms.Controls.QTextBox();
         this.labName = new System.Windows.Forms.Label();
         this.txtName = new MO.Core.Forms.Controls.QTextBox();
         this.SuspendLayout();
         // 
         // label1
         // 
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(10, 6);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(53, 12);
         this.label1.TabIndex = 0;
         this.label1.Text = "类型名称";
         // 
         // label2
         // 
         this.label2.AutoSize = true;
         this.label2.Location = new System.Drawing.Point(155, 6);
         this.label2.Name = "label2";
         this.label2.Size = new System.Drawing.Size(53, 12);
         this.label2.TabIndex = 0;
         this.label2.Text = "类型扩展";
         // 
         // txtTypeName
         // 
         this.txtTypeName.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtTypeName.Location = new System.Drawing.Point(70, 3);
         this.txtTypeName.Name = "txtTypeName";
         this.txtTypeName.ReadOnly = true;
         this.txtTypeName.Size = new System.Drawing.Size(80, 19);
         this.txtTypeName.TabIndex = 3;
         // 
         // txtClassName
         // 
         this.txtClassName.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtClassName.Location = new System.Drawing.Point(214, 3);
         this.txtClassName.Name = "txtClassName";
         this.txtClassName.Size = new System.Drawing.Size(120, 19);
         this.txtClassName.TabIndex = 3;
         // 
         // chkOptionValid
         // 
         this.chkOptionValid.AutoSize = true;
         this.chkOptionValid.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionValid.Location = new System.Drawing.Point(340, 4);
         this.chkOptionValid.Name = "chkOptionValid";
         this.chkOptionValid.Size = new System.Drawing.Size(57, 16);
         this.chkOptionValid.TabIndex = 4;
         this.chkOptionValid.Text = "有效性";
         this.chkOptionValid.UseVisualStyleBackColor = true;
         // 
         // txtLabel
         // 
         this.txtLabel.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtLabel.Location = new System.Drawing.Point(70, 53);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.Size = new System.Drawing.Size(265, 19);
         this.txtLabel.TabIndex = 3;
         this.txtLabel.TextChanged += new System.EventHandler(this.txtLabel_TextChanged);
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(10, 56);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 0;
         this.labLabel.Text = "标签";
         // 
         // labHint
         // 
         this.labHint.AutoSize = true;
         this.labHint.Location = new System.Drawing.Point(10, 81);
         this.labHint.Name = "labHint";
         this.labHint.Size = new System.Drawing.Size(29, 12);
         this.labHint.TabIndex = 0;
         this.labHint.Text = "提示";
         // 
         // txtHint
         // 
         this.txtHint.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtHint.Location = new System.Drawing.Point(70, 78);
         this.txtHint.Name = "txtHint";
         this.txtHint.Size = new System.Drawing.Size(265, 19);
         this.txtHint.TabIndex = 3;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(10, 31);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 0;
         this.labName.Text = "名称";
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtName.Location = new System.Drawing.Point(70, 28);
         this.txtName.Name = "txtName";
         this.txtName.Size = new System.Drawing.Size(265, 19);
         this.txtName.TabIndex = 3;
         this.txtName.TextChanged += new System.EventHandler(this.txtName_TextChanged);
         // 
         // QUiObjectProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.chkOptionValid);
         this.Controls.Add(this.txtHint);
         this.Controls.Add(this.txtName);
         this.Controls.Add(this.txtLabel);
         this.Controls.Add(this.txtTypeName);
         this.Controls.Add(this.txtClassName);
         this.Controls.Add(this.labHint);
         this.Controls.Add(this.labName);
         this.Controls.Add(this.label2);
         this.Controls.Add(this.labLabel);
         this.Controls.Add(this.label1);
         this.Name = "QUiObjectProperty";
         this.Size = new System.Drawing.Size(505, 100);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.Label label2;
      private MO.Core.Forms.Controls.QTextBox txtClassName;
      private MO.Core.Forms.Controls.QTextBox txtTypeName;
      private MO.Core.Forms.Controls.QCheckBox chkOptionValid;
      private MO.Core.Forms.Controls.QTextBox txtLabel;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.Label labHint;
      private MO.Core.Forms.Controls.QTextBox txtHint;
      private System.Windows.Forms.Label labName;
      private MO.Core.Forms.Controls.QTextBox txtName;
   }
}
