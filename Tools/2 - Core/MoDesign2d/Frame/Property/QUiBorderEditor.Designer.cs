namespace MO.Design2d.Frame.Property
{
   partial class QUiBorderEditor
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiBorderEditor));
         this.btnBorder = new System.Windows.Forms.Button();
         this.txtBorder = new MO.Core.Forms.Controls.QTextBox();
         this.SuspendLayout();
         // 
         // btnBorder
         // 
         this.btnBorder.Cursor = System.Windows.Forms.Cursors.Hand;
         this.btnBorder.FlatAppearance.BorderSize = 0;
         this.btnBorder.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.btnBorder.Image = ((System.Drawing.Image)(resources.GetObject("btnBorder.Image")));
         this.btnBorder.Location = new System.Drawing.Point(317, 0);
         this.btnBorder.Name = "btnBorder";
         this.btnBorder.Size = new System.Drawing.Size(18, 20);
         this.btnBorder.TabIndex = 46;
         this.btnBorder.UseVisualStyleBackColor = true;
         this.btnBorder.Click += new System.EventHandler(this.btnBorder_Click);
         // 
         // txtBorder
         // 
         this.txtBorder.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtBorder.Location = new System.Drawing.Point(0, 1);
         this.txtBorder.Name = "txtBorder";
         this.txtBorder.Size = new System.Drawing.Size(314, 19);
         this.txtBorder.TabIndex = 45;
         // 
         // QUiBorderProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.btnBorder);
         this.Controls.Add(this.txtBorder);
         this.Name = "QUiBorderProperty";
         this.Size = new System.Drawing.Size(340, 21);
         this.ResumeLayout(false);

      }

      #endregion

      private MO.Core.Forms.Controls.QTextBox txtBorder;
      private System.Windows.Forms.Button btnBorder;
   }
}
