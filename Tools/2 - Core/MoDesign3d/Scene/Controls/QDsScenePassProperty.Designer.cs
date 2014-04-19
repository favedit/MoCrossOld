namespace MO.Design3d.Scene.Controls
{
   partial class QDsScenePassProperty
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
         this.txtName = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.labRenderTargetSize = new System.Windows.Forms.Label();
         this.txtRenderTargetWidth = new System.Windows.Forms.TextBox();
         this.txtRenderTargetHeight = new System.Windows.Forms.TextBox();
         this.labRenderTargetX = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // txtName
         // 
         this.txtName.BackColor = System.Drawing.SystemColors.Window;
         this.txtName.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtName.Location = new System.Drawing.Point(95, 12);
         this.txtName.Name = "txtName";
         this.txtName.ReadOnly = true;
         this.txtName.Size = new System.Drawing.Size(405, 21);
         this.txtName.TabIndex = 80;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(14, 15);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 79;
         this.labName.Text = "名称";
         // 
         // labRenderTargetSize
         // 
         this.labRenderTargetSize.AutoSize = true;
         this.labRenderTargetSize.Location = new System.Drawing.Point(14, 42);
         this.labRenderTargetSize.Name = "labRenderTargetSize";
         this.labRenderTargetSize.Size = new System.Drawing.Size(77, 12);
         this.labRenderTargetSize.TabIndex = 79;
         this.labRenderTargetSize.Text = "渲染目标尺寸";
         // 
         // txtRenderTargetWidth
         // 
         this.txtRenderTargetWidth.BackColor = System.Drawing.SystemColors.Window;
         this.txtRenderTargetWidth.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtRenderTargetWidth.Location = new System.Drawing.Point(95, 39);
         this.txtRenderTargetWidth.Name = "txtRenderTargetWidth";
         this.txtRenderTargetWidth.ReadOnly = true;
         this.txtRenderTargetWidth.Size = new System.Drawing.Size(105, 21);
         this.txtRenderTargetWidth.TabIndex = 80;
         // 
         // txtRenderTargetHeight
         // 
         this.txtRenderTargetHeight.BackColor = System.Drawing.SystemColors.Window;
         this.txtRenderTargetHeight.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtRenderTargetHeight.Location = new System.Drawing.Point(223, 39);
         this.txtRenderTargetHeight.Name = "txtRenderTargetHeight";
         this.txtRenderTargetHeight.ReadOnly = true;
         this.txtRenderTargetHeight.Size = new System.Drawing.Size(105, 21);
         this.txtRenderTargetHeight.TabIndex = 80;
         // 
         // labRenderTargetX
         // 
         this.labRenderTargetX.AutoSize = true;
         this.labRenderTargetX.Location = new System.Drawing.Point(206, 42);
         this.labRenderTargetX.Name = "labRenderTargetX";
         this.labRenderTargetX.Size = new System.Drawing.Size(11, 12);
         this.labRenderTargetX.TabIndex = 79;
         this.labRenderTargetX.Text = "X";
         // 
         // QDrScenePassProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.txtRenderTargetHeight);
         this.Controls.Add(this.txtRenderTargetWidth);
         this.Controls.Add(this.labRenderTargetX);
         this.Controls.Add(this.labRenderTargetSize);
         this.Controls.Add(this.txtName);
         this.Controls.Add(this.labName);
         this.Name = "QDrScenePassProperty";
         this.Size = new System.Drawing.Size(838, 586);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.TextBox txtName;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.Label labRenderTargetSize;
      private System.Windows.Forms.TextBox txtRenderTargetWidth;
      private System.Windows.Forms.TextBox txtRenderTargetHeight;
      private System.Windows.Forms.Label labRenderTargetX;
   }
}
