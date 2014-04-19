namespace MO.Design3d.Controls
{
   partial class QDsFlag
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
         this.rdbNo = new System.Windows.Forms.RadioButton();
         this.rdbInherit = new System.Windows.Forms.RadioButton();
         this.rdbYes = new System.Windows.Forms.RadioButton();
         this.SuspendLayout();
         // 
         // rdbNo
         // 
         this.rdbNo.AutoSize = true;
         this.rdbNo.Location = new System.Drawing.Point(56, 3);
         this.rdbNo.Name = "rdbNo";
         this.rdbNo.Size = new System.Drawing.Size(35, 16);
         this.rdbNo.TabIndex = 47;
         this.rdbNo.TabStop = true;
         this.rdbNo.Tag = "1";
         this.rdbNo.Text = "否";
         this.rdbNo.UseVisualStyleBackColor = true;
         this.rdbNo.CheckedChanged += new System.EventHandler(this.rdbFlag_CheckedChanged);
         // 
         // rdbInherit
         // 
         this.rdbInherit.AutoSize = true;
         this.rdbInherit.Location = new System.Drawing.Point(3, 3);
         this.rdbInherit.Name = "rdbInherit";
         this.rdbInherit.Size = new System.Drawing.Size(47, 16);
         this.rdbInherit.TabIndex = 49;
         this.rdbInherit.TabStop = true;
         this.rdbInherit.Tag = "0";
         this.rdbInherit.Text = "继承";
         this.rdbInherit.UseVisualStyleBackColor = true;
         this.rdbInherit.CheckedChanged += new System.EventHandler(this.rdbFlag_CheckedChanged);
         // 
         // rdbYes
         // 
         this.rdbYes.AutoSize = true;
         this.rdbYes.Location = new System.Drawing.Point(97, 3);
         this.rdbYes.Name = "rdbYes";
         this.rdbYes.Size = new System.Drawing.Size(35, 16);
         this.rdbYes.TabIndex = 48;
         this.rdbYes.TabStop = true;
         this.rdbYes.Tag = "2";
         this.rdbYes.Text = "是";
         this.rdbYes.UseVisualStyleBackColor = true;
         this.rdbYes.CheckedChanged += new System.EventHandler(this.rdbFlag_CheckedChanged);
         // 
         // QDrFlag
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.rdbNo);
         this.Controls.Add(this.rdbInherit);
         this.Controls.Add(this.rdbYes);
         this.Name = "QDrFlag";
         this.Size = new System.Drawing.Size(140, 23);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.RadioButton rdbNo;
      private System.Windows.Forms.RadioButton rdbInherit;
      private System.Windows.Forms.RadioButton rdbYes;
   }
}
