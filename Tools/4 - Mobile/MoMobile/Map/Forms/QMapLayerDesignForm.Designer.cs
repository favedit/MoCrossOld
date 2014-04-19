namespace MoMobile.Map.Forms
{
   partial class QMapLayerDesignForm
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
         this.pnlInfo = new System.Windows.Forms.Panel();
         this.panel1 = new System.Windows.Forms.Panel();
         this.label3 = new System.Windows.Forms.Label();
         this.txtCellSizeHeight = new System.Windows.Forms.TextBox();
         this.labCellSize = new System.Windows.Forms.Label();
         this.txtCellSizeWidth = new System.Windows.Forms.TextBox();
         this.labCountch = new System.Windows.Forms.Label();
         this.txtCellCountHeight = new System.Windows.Forms.TextBox();
         this.labCellCount = new System.Windows.Forms.Label();
         this.txtCellCountWidth = new System.Windows.Forms.TextBox();
         this.labTypeCd = new System.Windows.Forms.Label();
         this.labScrollCd = new System.Windows.Forms.Label();
         this.labSpeed = new System.Windows.Forms.Label();
         this.txtSpeed = new System.Windows.Forms.TextBox();
         this.cbxTypeCd = new System.Windows.Forms.ComboBox();
         this.cbxScrollCd = new System.Windows.Forms.ComboBox();
         this.btnCancel = new System.Windows.Forms.Button();
         this.btnAccept = new System.Windows.Forms.Button();
         this.labWrapCd = new System.Windows.Forms.Label();
         this.cbxWrapCd = new System.Windows.Forms.ComboBox();
         this.pnlInfo.SuspendLayout();
         this.panel1.SuspendLayout();
         this.SuspendLayout();
         // 
         // pnlInfo
         // 
         this.pnlInfo.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.pnlInfo.Controls.Add(this.panel1);
         this.pnlInfo.Controls.Add(this.btnCancel);
         this.pnlInfo.Controls.Add(this.btnAccept);
         this.pnlInfo.Dock = System.Windows.Forms.DockStyle.Fill;
         this.pnlInfo.Location = new System.Drawing.Point(0, 0);
         this.pnlInfo.Name = "pnlInfo";
         this.pnlInfo.Size = new System.Drawing.Size(428, 218);
         this.pnlInfo.TabIndex = 11;
         // 
         // panel1
         // 
         this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
         this.panel1.Controls.Add(this.labWrapCd);
         this.panel1.Controls.Add(this.cbxWrapCd);
         this.panel1.Controls.Add(this.label3);
         this.panel1.Controls.Add(this.txtCellSizeHeight);
         this.panel1.Controls.Add(this.labCellSize);
         this.panel1.Controls.Add(this.txtCellSizeWidth);
         this.panel1.Controls.Add(this.labCountch);
         this.panel1.Controls.Add(this.txtCellCountHeight);
         this.panel1.Controls.Add(this.labCellCount);
         this.panel1.Controls.Add(this.txtCellCountWidth);
         this.panel1.Controls.Add(this.labTypeCd);
         this.panel1.Controls.Add(this.labScrollCd);
         this.panel1.Controls.Add(this.labSpeed);
         this.panel1.Controls.Add(this.txtSpeed);
         this.panel1.Controls.Add(this.cbxTypeCd);
         this.panel1.Controls.Add(this.cbxScrollCd);
         this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
         this.panel1.Location = new System.Drawing.Point(0, 0);
         this.panel1.Name = "panel1";
         this.panel1.Size = new System.Drawing.Size(424, 136);
         this.panel1.TabIndex = 13;
         // 
         // label3
         // 
         this.label3.AutoSize = true;
         this.label3.Location = new System.Drawing.Point(313, 25);
         this.label3.Name = "label3";
         this.label3.Size = new System.Drawing.Size(11, 12);
         this.label3.TabIndex = 13;
         this.label3.Text = "*";
         // 
         // txtCellSizeHeight
         // 
         this.txtCellSizeHeight.Location = new System.Drawing.Point(332, 22);
         this.txtCellSizeHeight.Name = "txtCellSizeHeight";
         this.txtCellSizeHeight.ReadOnly = true;
         this.txtCellSizeHeight.Size = new System.Drawing.Size(47, 21);
         this.txtCellSizeHeight.TabIndex = 12;
         // 
         // labCellSize
         // 
         this.labCellSize.AutoSize = true;
         this.labCellSize.Location = new System.Drawing.Point(199, 25);
         this.labCellSize.Name = "labCellSize";
         this.labCellSize.Size = new System.Drawing.Size(53, 12);
         this.labCellSize.TabIndex = 11;
         this.labCellSize.Text = "格子大小";
         // 
         // txtCellSizeWidth
         // 
         this.txtCellSizeWidth.Location = new System.Drawing.Point(258, 22);
         this.txtCellSizeWidth.Name = "txtCellSizeWidth";
         this.txtCellSizeWidth.ReadOnly = true;
         this.txtCellSizeWidth.Size = new System.Drawing.Size(49, 21);
         this.txtCellSizeWidth.TabIndex = 10;
         // 
         // labCountch
         // 
         this.labCountch.AutoSize = true;
         this.labCountch.Location = new System.Drawing.Point(127, 25);
         this.labCountch.Name = "labCountch";
         this.labCountch.Size = new System.Drawing.Size(11, 12);
         this.labCountch.TabIndex = 9;
         this.labCountch.Text = "*";
         // 
         // txtCellCountHeight
         // 
         this.txtCellCountHeight.Location = new System.Drawing.Point(146, 22);
         this.txtCellCountHeight.Name = "txtCellCountHeight";
         this.txtCellCountHeight.ReadOnly = true;
         this.txtCellCountHeight.Size = new System.Drawing.Size(47, 21);
         this.txtCellCountHeight.TabIndex = 8;
         // 
         // labCellCount
         // 
         this.labCellCount.AutoSize = true;
         this.labCellCount.Location = new System.Drawing.Point(13, 25);
         this.labCellCount.Name = "labCellCount";
         this.labCellCount.Size = new System.Drawing.Size(53, 12);
         this.labCellCount.TabIndex = 7;
         this.labCellCount.Text = "格子数量";
         // 
         // txtCellCountWidth
         // 
         this.txtCellCountWidth.Location = new System.Drawing.Point(72, 22);
         this.txtCellCountWidth.Name = "txtCellCountWidth";
         this.txtCellCountWidth.ReadOnly = true;
         this.txtCellCountWidth.Size = new System.Drawing.Size(49, 21);
         this.txtCellCountWidth.TabIndex = 6;
         // 
         // labTypeCd
         // 
         this.labTypeCd.AutoSize = true;
         this.labTypeCd.Location = new System.Drawing.Point(223, 49);
         this.labTypeCd.Name = "labTypeCd";
         this.labTypeCd.Size = new System.Drawing.Size(29, 12);
         this.labTypeCd.TabIndex = 5;
         this.labTypeCd.Text = "方式";
         // 
         // labScrollCd
         // 
         this.labScrollCd.AutoSize = true;
         this.labScrollCd.Location = new System.Drawing.Point(13, 52);
         this.labScrollCd.Name = "labScrollCd";
         this.labScrollCd.Size = new System.Drawing.Size(53, 12);
         this.labScrollCd.TabIndex = 4;
         this.labScrollCd.Text = "卷动方式";
         // 
         // labSpeed
         // 
         this.labSpeed.AutoSize = true;
         this.labSpeed.Location = new System.Drawing.Point(199, 75);
         this.labSpeed.Name = "labSpeed";
         this.labSpeed.Size = new System.Drawing.Size(53, 12);
         this.labSpeed.TabIndex = 3;
         this.labSpeed.Text = "卷动速度";
         // 
         // txtSpeed
         // 
         this.txtSpeed.Location = new System.Drawing.Point(258, 72);
         this.txtSpeed.Name = "txtSpeed";
         this.txtSpeed.Size = new System.Drawing.Size(121, 21);
         this.txtSpeed.TabIndex = 2;
         // 
         // cbxTypeCd
         // 
         this.cbxTypeCd.FormattingEnabled = true;
         this.cbxTypeCd.Location = new System.Drawing.Point(258, 46);
         this.cbxTypeCd.Name = "cbxTypeCd";
         this.cbxTypeCd.Size = new System.Drawing.Size(121, 20);
         this.cbxTypeCd.TabIndex = 1;
         // 
         // cbxScrollCd
         // 
         this.cbxScrollCd.FormattingEnabled = true;
         this.cbxScrollCd.Location = new System.Drawing.Point(72, 49);
         this.cbxScrollCd.Name = "cbxScrollCd";
         this.cbxScrollCd.Size = new System.Drawing.Size(121, 20);
         this.cbxScrollCd.TabIndex = 0;
         // 
         // btnCancel
         // 
         this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
         this.btnCancel.Location = new System.Drawing.Point(334, 168);
         this.btnCancel.Name = "btnCancel";
         this.btnCancel.Size = new System.Drawing.Size(75, 23);
         this.btnCancel.TabIndex = 12;
         this.btnCancel.Text = "Cancel";
         this.btnCancel.UseVisualStyleBackColor = true;
         this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
         // 
         // btnAccept
         // 
         this.btnAccept.Location = new System.Drawing.Point(253, 168);
         this.btnAccept.Name = "btnAccept";
         this.btnAccept.Size = new System.Drawing.Size(75, 23);
         this.btnAccept.TabIndex = 11;
         this.btnAccept.Text = "Accept";
         this.btnAccept.UseVisualStyleBackColor = true;
         this.btnAccept.Click += new System.EventHandler(this.btnAccept_Click);
         // 
         // labWrapCd
         // 
         this.labWrapCd.AutoSize = true;
         this.labWrapCd.Location = new System.Drawing.Point(13, 75);
         this.labWrapCd.Name = "labWrapCd";
         this.labWrapCd.Size = new System.Drawing.Size(53, 12);
         this.labWrapCd.TabIndex = 15;
         this.labWrapCd.Text = "回卷方式";
         // 
         // cbxWrapCd
         // 
         this.cbxWrapCd.FormattingEnabled = true;
         this.cbxWrapCd.Location = new System.Drawing.Point(72, 72);
         this.cbxWrapCd.Name = "cbxWrapCd";
         this.cbxWrapCd.Size = new System.Drawing.Size(121, 20);
         this.cbxWrapCd.TabIndex = 14;
         // 
         // QMapLayerDesignForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(428, 218);
         this.Controls.Add(this.pnlInfo);
         this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
         this.Name = "QMapLayerDesignForm";
         this.Text = "层样式设计";
         this.pnlInfo.ResumeLayout(false);
         this.panel1.ResumeLayout(false);
         this.panel1.PerformLayout();
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.Panel pnlInfo;
      private System.Windows.Forms.Button btnCancel;
      private System.Windows.Forms.Button btnAccept;
      private System.Windows.Forms.Panel panel1;
      private System.Windows.Forms.ComboBox cbxTypeCd;
      private System.Windows.Forms.ComboBox cbxScrollCd;
      private System.Windows.Forms.TextBox txtSpeed;
      private System.Windows.Forms.Label labTypeCd;
      private System.Windows.Forms.Label labScrollCd;
      private System.Windows.Forms.Label labSpeed;
      private System.Windows.Forms.Label labCountch;
      private System.Windows.Forms.TextBox txtCellCountHeight;
      private System.Windows.Forms.Label labCellCount;
      private System.Windows.Forms.TextBox txtCellCountWidth;
      private System.Windows.Forms.Label label3;
      private System.Windows.Forms.TextBox txtCellSizeHeight;
      private System.Windows.Forms.Label labCellSize;
      private System.Windows.Forms.TextBox txtCellSizeWidth;
      private System.Windows.Forms.Label labWrapCd;
      private System.Windows.Forms.ComboBox cbxWrapCd;
   }
}