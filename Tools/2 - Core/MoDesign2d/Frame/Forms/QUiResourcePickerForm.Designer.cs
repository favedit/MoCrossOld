
namespace MO.Design2d.Frame.Forms
{
   partial class QUiResourcePickerForm
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiResourcePickerForm));
         this.cancel = new MO.Core.Forms.Controls.QButton();
         this.select = new MO.Core.Forms.Controls.QButton();
         this.toolStrip1 = new System.Windows.Forms.ToolStrip();
         this.tsbSelect = new System.Windows.Forms.ToolStripButton();
         this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
         this.tsbClose = new System.Windows.Forms.ToolStripButton();
         this.qrpResourcePicker = new MO.Design2d.Frame.Forms.QUiResourcePicker();
         this.toolStrip1.SuspendLayout();
         this.SuspendLayout();
         // 
         // cancel
         // 
         this.cancel.Location = new System.Drawing.Point(806, 12);
         this.cancel.Name = "cancel";
         this.cancel.Size = new System.Drawing.Size(75, 23);
         this.cancel.TabIndex = 4;
         this.cancel.Text = "取消";
         this.cancel.UseVisualStyleBackColor = true;
         // 
         // select
         // 
         this.select.Location = new System.Drawing.Point(658, 12);
         this.select.Name = "select";
         this.select.Size = new System.Drawing.Size(75, 23);
         this.select.TabIndex = 3;
         this.select.Text = "选择";
         this.select.UseVisualStyleBackColor = true;
         // 
         // toolStrip1
         // 
         this.toolStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.tsbSelect,
            this.toolStripSeparator1,
            this.tsbClose});
         this.toolStrip1.Location = new System.Drawing.Point(0, 0);
         this.toolStrip1.Name = "toolStrip1";
         this.toolStrip1.Size = new System.Drawing.Size(1091, 25);
         this.toolStrip1.TabIndex = 6;
         this.toolStrip1.Text = "toolStrip1";
         // 
         // tsbSelect
         // 
         this.tsbSelect.Image = ((System.Drawing.Image)(resources.GetObject("tsbSelect.Image")));
         this.tsbSelect.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbSelect.Name = "tsbSelect";
         this.tsbSelect.Size = new System.Drawing.Size(52, 22);
         this.tsbSelect.Text = "选中";
         this.tsbSelect.Click += new System.EventHandler(this.tsbSelect_Click);
         // 
         // toolStripSeparator1
         // 
         this.toolStripSeparator1.Name = "toolStripSeparator1";
         this.toolStripSeparator1.Size = new System.Drawing.Size(6, 25);
         // 
         // tsbClose
         // 
         this.tsbClose.Image = ((System.Drawing.Image)(resources.GetObject("tsbClose.Image")));
         this.tsbClose.ImageTransparentColor = System.Drawing.Color.Magenta;
         this.tsbClose.Name = "tsbClose";
         this.tsbClose.Size = new System.Drawing.Size(52, 22);
         this.tsbClose.Text = "关闭";
         this.tsbClose.Click += new System.EventHandler(this.tsbClose_Click);
         // 
         // qrpResourcePicker
         // 
         this.qrpResourcePicker.Dock = System.Windows.Forms.DockStyle.Fill;
         this.qrpResourcePicker.Location = new System.Drawing.Point(0, 25);
         this.qrpResourcePicker.Name = "qrpResourcePicker";
         this.qrpResourcePicker.Size = new System.Drawing.Size(1091, 637);
         this.qrpResourcePicker.TabIndex = 8;
         this.qrpResourcePicker.ResourceDoubleClick += new System.EventHandler(this.tsbSelect_Click);
         // 
         // QUiResourcePickerForm
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.ClientSize = new System.Drawing.Size(1091, 662);
         this.Controls.Add(this.qrpResourcePicker);
         this.Controls.Add(this.toolStrip1);
         this.Controls.Add(this.cancel);
         this.Controls.Add(this.select);
         this.Name = "QUiResourcePickerForm";
         this.Text = "资源选取属性";
         this.toolStrip1.ResumeLayout(false);
         this.toolStrip1.PerformLayout();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QButton cancel;
      private MO.Core.Forms.Controls.QButton select;
      private System.Windows.Forms.ToolStrip toolStrip1;
      private System.Windows.Forms.ToolStripButton tsbSelect;
      private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
      private System.Windows.Forms.ToolStripButton tsbClose;
      private QUiResourcePicker qrpResourcePicker;
   }
}