namespace MO.Core.Forms
{
   partial class QInputForm
   {
      /// <summary>
      /// Required designer variable.
      /// </summary>
      private System.ComponentModel.IContainer components = null;

      /// <summary>
      /// Clean up any resources being used.
      /// </summary>
      /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
      protected override void Dispose(bool disposing) {
         if(disposing && (components != null)) {
            components.Dispose();
         }
         base.Dispose(disposing);
      }

      #region Windows Form Designer generated code

      /// <summary>
      /// Required method for Designer support - do not modify
      /// the contents of this method with the code editor.
      /// </summary>
      private void InitializeComponent() {
         this.txtInput = new System.Windows.Forms.TextBox();
         this.btnCancel = new System.Windows.Forms.Button();
         this.pnlTitle = new System.Windows.Forms.Panel();
         this.labTitle = new System.Windows.Forms.Label();
         this.btnOk = new System.Windows.Forms.Button();
         this.pnlTitle.SuspendLayout();
         this.SuspendLayout();
         // 
         // txtInput
         // 
         this.txtInput.Location = new System.Drawing.Point(12, 45);
         this.txtInput.Name = "txtInput";
         this.txtInput.Size = new System.Drawing.Size(342, 21);
         this.txtInput.TabIndex = 0;
         // 
         // btnCancel
         // 
         this.btnCancel.Location = new System.Drawing.Point(267, 83);
         this.btnCancel.Name = "btnCancel";
         this.btnCancel.Size = new System.Drawing.Size(87, 25);
         this.btnCancel.TabIndex = 1;
         this.btnCancel.Text = "取消";
         this.btnCancel.UseVisualStyleBackColor = true;
         this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
         // 
         // pnlTitle
         // 
         this.pnlTitle.BackColor = System.Drawing.SystemColors.ControlDarkDark;
         this.pnlTitle.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlTitle.Controls.Add(this.labTitle);
         this.pnlTitle.Dock = System.Windows.Forms.DockStyle.Top;
         this.pnlTitle.Location = new System.Drawing.Point(0, 0);
         this.pnlTitle.Name = "pnlTitle";
         this.pnlTitle.Size = new System.Drawing.Size(369, 30);
         this.pnlTitle.TabIndex = 2;
         // 
         // labTitle
         // 
         this.labTitle.AutoSize = true;
         this.labTitle.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
         this.labTitle.ForeColor = System.Drawing.SystemColors.Control;
         this.labTitle.Location = new System.Drawing.Point(10, 7);
         this.labTitle.Name = "labTitle";
         this.labTitle.Size = new System.Drawing.Size(31, 12);
         this.labTitle.TabIndex = 0;
         this.labTitle.Text = "标签";
         // 
         // btnOk
         // 
         this.btnOk.Location = new System.Drawing.Point(174, 83);
         this.btnOk.Name = "btnOk";
         this.btnOk.Size = new System.Drawing.Size(87, 25);
         this.btnOk.TabIndex = 1;
         this.btnOk.Text = "确定";
         this.btnOk.UseVisualStyleBackColor = true;
         this.btnOk.Click += new System.EventHandler(this.btnOk_Click);
         // 
         // QInputForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(369, 120);
         this.Controls.Add(this.pnlTitle);
         this.Controls.Add(this.btnOk);
         this.Controls.Add(this.btnCancel);
         this.Controls.Add(this.txtInput);
         this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
         this.KeyPreview = true;
         this.MaximizeBox = false;
         this.MinimizeBox = false;
         this.Name = "QInputForm";
         this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
         this.Text = "输入表单";
         this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.QInputForm_KeyDown);
         this.pnlTitle.ResumeLayout(false);
         this.pnlTitle.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.TextBox txtInput;
      private System.Windows.Forms.Button btnCancel;
      private System.Windows.Forms.Panel pnlTitle;
      private System.Windows.Forms.Label labTitle;
      private System.Windows.Forms.Button btnOk;
   }
}