namespace MO.Design3d.Model.Controls
{
   partial class QDsModelAnimation
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
         this.pnlAnimation = new System.Windows.Forms.Panel();
         this.labRate = new System.Windows.Forms.Label();
         this.txtRate = new System.Windows.Forms.TextBox();
         this.labFrameEnd = new System.Windows.Forms.Label();
         this.txtFrameEnd = new System.Windows.Forms.TextBox();
         this.labFrameStart = new System.Windows.Forms.Label();
         this.txtFrameStart = new System.Windows.Forms.TextBox();
         this.labName = new System.Windows.Forms.Label();
         this.txtName = new System.Windows.Forms.TextBox();
         this.pnlAnimation.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlAnimation
         // 
         this.pnlAnimation.Controls.Add(this.labRate);
         this.pnlAnimation.Controls.Add(this.txtRate);
         this.pnlAnimation.Controls.Add(this.labFrameEnd);
         this.pnlAnimation.Controls.Add(this.txtFrameEnd);
         this.pnlAnimation.Controls.Add(this.labFrameStart);
         this.pnlAnimation.Controls.Add(this.txtFrameStart);
         this.pnlAnimation.Controls.Add(this.labName);
         this.pnlAnimation.Controls.Add(this.txtName);
         this.pnlAnimation.Location = new System.Drawing.Point(3, 3);
         this.pnlAnimation.Name = "pnlAnimation";
         this.pnlAnimation.Size = new System.Drawing.Size(486, 110);
         this.pnlAnimation.TabIndex = 14;
         this.pnlAnimation.Visible = false;
         // 
         // labRate
         // 
         this.labRate.AutoSize = true;
         this.labRate.Location = new System.Drawing.Point(39, 29);
         this.labRate.Name = "labRate";
         this.labRate.Size = new System.Drawing.Size(29, 12);
         this.labRate.TabIndex = 7;
         this.labRate.Text = "比率";
         // 
         // txtRate
         // 
         this.txtRate.Location = new System.Drawing.Point(74, 27);
         this.txtRate.Name = "txtRate";
         this.txtRate.Size = new System.Drawing.Size(163, 21);
         this.txtRate.TabIndex = 6;
         // 
         // labFrameEnd
         // 
         this.labFrameEnd.AutoSize = true;
         this.labFrameEnd.Location = new System.Drawing.Point(29, 78);
         this.labFrameEnd.Name = "labFrameEnd";
         this.labFrameEnd.Size = new System.Drawing.Size(41, 12);
         this.labFrameEnd.TabIndex = 5;
         this.labFrameEnd.Text = "结束帧";
         // 
         // txtFrameEnd
         // 
         this.txtFrameEnd.Location = new System.Drawing.Point(74, 74);
         this.txtFrameEnd.Name = "txtFrameEnd";
         this.txtFrameEnd.Size = new System.Drawing.Size(163, 21);
         this.txtFrameEnd.TabIndex = 4;
         // 
         // labFrameStart
         // 
         this.labFrameStart.AutoSize = true;
         this.labFrameStart.Location = new System.Drawing.Point(29, 55);
         this.labFrameStart.Name = "labFrameStart";
         this.labFrameStart.Size = new System.Drawing.Size(41, 12);
         this.labFrameStart.TabIndex = 3;
         this.labFrameStart.Text = "开始帧";
         // 
         // txtFrameStart
         // 
         this.txtFrameStart.Location = new System.Drawing.Point(74, 51);
         this.txtFrameStart.Name = "txtFrameStart";
         this.txtFrameStart.Size = new System.Drawing.Size(163, 21);
         this.txtFrameStart.TabIndex = 2;
         // 
         // labName
         // 
         this.labName.AutoSize = true;
         this.labName.Location = new System.Drawing.Point(39, 6);
         this.labName.Name = "labName";
         this.labName.Size = new System.Drawing.Size(29, 12);
         this.labName.TabIndex = 1;
         this.labName.Text = "名称";
         // 
         // txtName
         // 
         this.txtName.Location = new System.Drawing.Point(74, 3);
         this.txtName.Name = "txtName";
         this.txtName.Size = new System.Drawing.Size(163, 21);
         this.txtName.TabIndex = 0;
         // 
         // QDrModelAnimation
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlAnimation);
         this.Name = "QDrModelAnimation";
         this.Size = new System.Drawing.Size(566, 123);
         this.pnlAnimation.ResumeLayout(false);
         this.pnlAnimation.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlAnimation;
      private System.Windows.Forms.Label labRate;
      private System.Windows.Forms.TextBox txtRate;
      private System.Windows.Forms.Label labFrameEnd;
      private System.Windows.Forms.TextBox txtFrameEnd;
      private System.Windows.Forms.Label labFrameStart;
      private System.Windows.Forms.TextBox txtFrameStart;
      private System.Windows.Forms.Label labName;
      private System.Windows.Forms.TextBox txtName;
   }
}
