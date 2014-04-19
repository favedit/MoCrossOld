namespace MO.Core.Forms.Controls
{
   partial class QColorPicker
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
         this.txtValue = new System.Windows.Forms.TextBox();
         this.pnlColor = new System.Windows.Forms.Panel();
         this.pnlContanier = new System.Windows.Forms.Panel();
         this.dlgColor = new System.Windows.Forms.ColorDialog();
         this.pnlContanier.SuspendLayout();
         this.SuspendLayout();
         // 
         // txtValue
         // 
         this.txtValue.BorderStyle = System.Windows.Forms.BorderStyle.None;
         this.txtValue.Location = new System.Drawing.Point(3, 3);
         this.txtValue.Name = "txtValue";
         this.txtValue.Size = new System.Drawing.Size(100, 14);
         this.txtValue.TabIndex = 0;
         this.txtValue.Leave += new System.EventHandler(this.txtValue_Leave);
         // 
         // pnlColor
         // 
         this.pnlColor.BackColor = System.Drawing.Color.Black;
         this.pnlColor.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.pnlColor.Location = new System.Drawing.Point(109, 3);
         this.pnlColor.Name = "pnlColor";
         this.pnlColor.Size = new System.Drawing.Size(29, 21);
         this.pnlColor.TabIndex = 1;
         this.pnlColor.Click += new System.EventHandler(this.pnlColor_Click);
         // 
         // pnlContanier
         // 
         this.pnlContanier.BackColor = System.Drawing.SystemColors.Window;
         this.pnlContanier.Controls.Add(this.txtValue);
         this.pnlContanier.Controls.Add(this.pnlColor);
         this.pnlContanier.Location = new System.Drawing.Point(3, 3);
         this.pnlContanier.Name = "pnlContanier";
         this.pnlContanier.Size = new System.Drawing.Size(191, 28);
         this.pnlContanier.TabIndex = 2;
         // 
         // dlgColor
         // 
         this.dlgColor.FullOpen = true;
         this.dlgColor.SolidColorOnly = true;
         // 
         // QColorPicker
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.pnlContanier);
         this.Name = "QColorPicker";
         this.Size = new System.Drawing.Size(226, 34);
         this.Resize += new System.EventHandler(this.QColorPicker_Resize);
         this.pnlContanier.ResumeLayout(false);
         this.pnlContanier.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.TextBox txtValue;
      private System.Windows.Forms.Panel pnlColor;
      private System.Windows.Forms.Panel pnlContanier;
      private System.Windows.Forms.ColorDialog dlgColor;
   }
}
