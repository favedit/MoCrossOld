namespace MoMobile.Map.Forms
{
   partial class QMapProperty
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
         this.panel2 = new System.Windows.Forms.Panel();
         this.lblLabel = new System.Windows.Forms.Label();
         this.lblName = new System.Windows.Forms.Label();
         this.lblId = new System.Windows.Forms.Label();
         this.txtLabel = new System.Windows.Forms.TextBox();
         this.txtName = new System.Windows.Forms.TextBox();
         this.txtId = new System.Windows.Forms.TextBox();
         this.panel2.SuspendLayout();
         this.SuspendLayout();
         // 
         // panel2
         // 
         this.panel2.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.panel2.Controls.Add(this.lblLabel);
         this.panel2.Controls.Add(this.lblName);
         this.panel2.Controls.Add(this.lblId);
         this.panel2.Controls.Add(this.txtLabel);
         this.panel2.Controls.Add(this.txtName);
         this.panel2.Controls.Add(this.txtId);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel2.Location = new System.Drawing.Point(0, 0);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(501, 420);
         this.panel2.TabIndex = 9;
         // 
         // lblLabel
         // 
         this.lblLabel.AutoSize = true;
         this.lblLabel.Location = new System.Drawing.Point(19, 74);
         this.lblLabel.Name = "lblLabel";
         this.lblLabel.Size = new System.Drawing.Size(29, 12);
         this.lblLabel.TabIndex = 1;
         this.lblLabel.Text = "标签";
         // 
         // lblName
         // 
         this.lblName.AutoSize = true;
         this.lblName.Location = new System.Drawing.Point(19, 47);
         this.lblName.Name = "lblName";
         this.lblName.Size = new System.Drawing.Size(29, 12);
         this.lblName.TabIndex = 1;
         this.lblName.Text = "名称";
         // 
         // lblId
         // 
         this.lblId.AutoSize = true;
         this.lblId.Location = new System.Drawing.Point(19, 20);
         this.lblId.Name = "lblId";
         this.lblId.Size = new System.Drawing.Size(29, 12);
         this.lblId.TabIndex = 1;
         this.lblId.Text = "编号";
         // 
         // txtLabel
         // 
         this.txtLabel.BackColor = System.Drawing.SystemColors.ButtonHighlight;
         this.txtLabel.Location = new System.Drawing.Point(78, 71);
         this.txtLabel.Name = "txtLabel";
         this.txtLabel.ReadOnly = true;
         this.txtLabel.Size = new System.Drawing.Size(384, 21);
         this.txtLabel.TabIndex = 0;
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.ButtonHighlight;
         this.txtName.Location = new System.Drawing.Point(78, 44);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(384, 21);
         this.txtName.TabIndex = 0;
         // 
         // txtId
         // 
         this.txtId.BackColor = System.Drawing.SystemColors.ButtonHighlight;
         this.txtId.Location = new System.Drawing.Point(78, 17);
         this.txtId.Name = "txtId";
         this.txtId.ReadOnly = true;
         this.txtId.Size = new System.Drawing.Size(106, 21);
         this.txtId.TabIndex = 0;
         // 
         // QMapProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.panel2);
         this.Name = "QMapProperty";
         this.Size = new System.Drawing.Size(501, 420);
         this.panel2.ResumeLayout(false);
         this.panel2.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel panel2;
      private System.Windows.Forms.Label lblLabel;
      private System.Windows.Forms.Label lblName;
      private System.Windows.Forms.Label lblId;
      private System.Windows.Forms.TextBox txtLabel;
      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.TextBox txtId;
   }
}
