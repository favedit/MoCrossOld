namespace MO.Design2d.Frame.Property
{
   partial class QUiProperty
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
         this.pnlTitle = new System.Windows.Forms.Panel();
         this.labTitle = new System.Windows.Forms.Label();
         this.pnlBody = new System.Windows.Forms.Panel();
         this.pnlTitle.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlTitle
         // 
         this.pnlTitle.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlTitle.Controls.Add(this.labTitle);
         this.pnlTitle.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlTitle.Location = new System.Drawing.Point(0, 0);
         this.pnlTitle.Name = "pnlTitle";
         this.pnlTitle.Size = new System.Drawing.Size(855, 19);
         this.pnlTitle.TabIndex = 0;
         // 
         // labTitle
         // 
         this.labTitle.AutoSize = true;
         this.labTitle.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.labTitle.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
         this.labTitle.ForeColor = System.Drawing.SystemColors.ControlLight;
         this.labTitle.Location = new System.Drawing.Point(6, 3);
         this.labTitle.Name = "labTitle";
         this.labTitle.Size = new System.Drawing.Size(57, 12);
         this.labTitle.TabIndex = 0;
         this.labTitle.Text = "属性内容";
         // 
         // pnlBody
         // 
         this.pnlBody.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlBody.Location = new System.Drawing.Point(0, 19);
         this.pnlBody.Name = "pnlBody";
         this.pnlBody.Size = new System.Drawing.Size(855, 363);
         this.pnlBody.TabIndex = 1;
         // 
         // QUiProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlBody);
         this.Controls.Add(this.pnlTitle);
         this.Name = "QUiProperty";
         this.Size = new System.Drawing.Size(855, 382);
         this.pnlTitle.ResumeLayout(false);
         this.pnlTitle.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlTitle;
      private System.Windows.Forms.Label labTitle;
      private System.Windows.Forms.Panel pnlBody;
   }
}
