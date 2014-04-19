namespace MO.Core.Forms.Dialog
{
   partial class QExceptionDialogForm
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
         if (disposing && (components != null)) {
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
         this.panel1 = new System.Windows.Forms.Panel();
         this.panel2 = new System.Windows.Forms.Panel();
         this.lsvValues = new System.Windows.Forms.ListView();
         this.chdFlag = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdType = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdValue = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.pgdOwner = new System.Windows.Forms.PropertyGrid();
         this.rtbDetail = new System.Windows.Forms.RichTextBox();
         this.panel3 = new System.Windows.Forms.Panel();
         this.btnClose = new System.Windows.Forms.Button();
         this.txtMessage = new System.Windows.Forms.RichTextBox();
         this.panel1.SuspendLayout();
         this.panel2.SuspendLayout();
         this.panel3.SuspendLayout();
         this.SuspendLayout();
         // 
         // panel1
         // 
         this.panel1.Controls.Add(this.txtMessage);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(1018, 222);
         this.panel1.TabIndex = 0;
         // 
         // panel2
         // 
         this.panel2.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.panel2.Controls.Add(this.lsvValues);
         this.panel2.Controls.Add(this.pgdOwner);
         this.panel2.Controls.Add(this.rtbDetail);
         this.panel2.Dock = System.Windows.Forms.DockStyle.Fill;
         this.panel2.Location = new System.Drawing.Point(0, 222);
         this.panel2.Name = "panel2";
         this.panel2.Size = new System.Drawing.Size(1018, 552);
         this.panel2.TabIndex = 1;
         // 
         // lsvValues
         // 
         this.lsvValues.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.lsvValues.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdFlag,
            this.chdType,
            this.chdValue});
         this.lsvValues.FullRowSelect = true;
         this.lsvValues.HideSelection = false;
         this.lsvValues.Location = new System.Drawing.Point(12, 12);
         this.lsvValues.MultiSelect = false;
         this.lsvValues.Name = "lsvValues";
         this.lsvValues.Size = new System.Drawing.Size(992, 106);
         this.lsvValues.TabIndex = 2;
         this.lsvValues.UseCompatibleStateImageBehavior = false;
         this.lsvValues.View = System.Windows.Forms.View.Details;
         this.lsvValues.Click += new System.EventHandler(this.lsvValues_Click);
         // 
         // chdFlag
         // 
         this.chdFlag.Text = "标志";
         this.chdFlag.Width = 90;
         // 
         // chdType
         // 
         this.chdType.Text = "类型";
         this.chdType.Width = 340;
         // 
         // chdValue
         // 
         this.chdValue.Text = "内容";
         this.chdValue.Width = 550;
         // 
         // pgdOwner
         // 
         this.pgdOwner.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.pgdOwner.Location = new System.Drawing.Point(562, 128);
         this.pgdOwner.Name = "pgdOwner";
         this.pgdOwner.Size = new System.Drawing.Size(442, 368);
         this.pgdOwner.TabIndex = 1;
         // 
         // rtbDetail
         // 
         this.rtbDetail.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.rtbDetail.BackColor = System.Drawing.SystemColors.ButtonShadow;
         this.rtbDetail.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.rtbDetail.Location = new System.Drawing.Point(12, 128);
         this.rtbDetail.Name = "rtbDetail";
         this.rtbDetail.ReadOnly = true;
         this.rtbDetail.Size = new System.Drawing.Size(544, 368);
         this.rtbDetail.TabIndex = 0;
         this.rtbDetail.Text = "";
         // 
         // panel3
         // 
         this.panel3.Controls.Add(this.btnClose);
         this.panel3.Dock = System.Windows.Forms.DockStyle.Bottom;
         this.panel3.Location = new System.Drawing.Point(0, 726);
         this.panel3.Name = "panel3";
         this.panel3.Size = new System.Drawing.Size(1018, 48);
         this.panel3.TabIndex = 2;
         // 
         // btnClose
         // 
         this.btnClose.DialogResult = System.Windows.Forms.DialogResult.Cancel;
         this.btnClose.Location = new System.Drawing.Point(883, 10);
         this.btnClose.Name = "btnClose";
         this.btnClose.Size = new System.Drawing.Size(123, 31);
         this.btnClose.TabIndex = 0;
         this.btnClose.Text = "关闭 (&C)";
         this.btnClose.UseVisualStyleBackColor = true;
         this.btnClose.Click += new System.EventHandler(this.btnClose_Click);
         // 
         // txtMessage
         // 
         this.txtMessage.BackColor = System.Drawing.SystemColors.Info;
         this.txtMessage.Dock = System.Windows.Forms.DockStyle.Fill;
         this.txtMessage.Location = new System.Drawing.Point(0, 0);
         this.txtMessage.Name = "txtMessage";
         this.txtMessage.ReadOnly = true;
         this.txtMessage.Size = new System.Drawing.Size(1018, 222);
         this.txtMessage.TabIndex = 0;
         this.txtMessage.Text = "";
         // 
         // QExceptionDialogForm
         // 
         this.AcceptButton = this.btnClose;
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.CancelButton = this.btnClose;
         this.ClientSize = new System.Drawing.Size(1018, 774);
         this.Controls.Add(this.panel3);
         this.Controls.Add(this.panel2);
         this.Controls.Add(this.panel1);
         this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
         this.Name = "QExceptionDialogForm";
         this.Text = "设计错误信息";
         this.Load += new System.EventHandler(this.QDesignErrorForm_Load);
         this.panel1.ResumeLayout(false);
         this.panel2.ResumeLayout(false);
         this.panel3.ResumeLayout(false);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.Panel panel2;
      private System.Windows.Forms.RichTextBox rtbDetail;
      private System.Windows.Forms.Panel panel3;
      private System.Windows.Forms.Button btnClose;
      private System.Windows.Forms.PropertyGrid pgdOwner;
      private System.Windows.Forms.ListView lsvValues;
      private System.Windows.Forms.ColumnHeader chdFlag;
      private System.Windows.Forms.ColumnHeader chdType;
      private System.Windows.Forms.ColumnHeader chdValue;
      private System.Windows.Forms.RichTextBox txtMessage;
   }
}