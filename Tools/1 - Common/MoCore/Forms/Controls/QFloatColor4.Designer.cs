namespace MO.Core.Forms.Controls
{
   partial class QFloatColor4
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
         this.tkbColorAlpha = new System.Windows.Forms.TrackBar();
         this.txtColorAlpha = new System.Windows.Forms.TextBox();
         this.qcpColorRgb = new MO.Core.Forms.Controls.QColorPicker();
         ((System.ComponentModel.ISupportInitialize)(this.tkbColorAlpha)).BeginInit();
         this.SuspendLayout();
         // 
         // tkbColorAlpha
         // 
         this.tkbColorAlpha.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.tkbColorAlpha.AutoSize = false;
         this.tkbColorAlpha.Location = new System.Drawing.Point(92, 2);
         this.tkbColorAlpha.Maximum = 100;
         this.tkbColorAlpha.Name = "tkbColorAlpha";
         this.tkbColorAlpha.Size = new System.Drawing.Size(73, 18);
         this.tkbColorAlpha.TabIndex = 1;
         this.tkbColorAlpha.TickStyle = System.Windows.Forms.TickStyle.None;
         this.tkbColorAlpha.Value = 100;
         this.tkbColorAlpha.Scroll += new System.EventHandler(this.tkbColorAlpha_Scroll);
         // 
         // txtColorAlpha
         // 
         this.txtColorAlpha.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.txtColorAlpha.BackColor = System.Drawing.SystemColors.Window;
         this.txtColorAlpha.ForeColor = System.Drawing.SystemColors.GrayText;
         this.txtColorAlpha.Location = new System.Drawing.Point(167, 0);
         this.txtColorAlpha.Name = "txtColorAlpha";
         this.txtColorAlpha.ReadOnly = true;
         this.txtColorAlpha.Size = new System.Drawing.Size(28, 21);
         this.txtColorAlpha.TabIndex = 2;
         this.txtColorAlpha.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
         // 
         // qcpColorRgb
         // 
         this.qcpColorRgb.Location = new System.Drawing.Point(0, 1);
         this.qcpColorRgb.Name = "qcpColorRgb";
         this.qcpColorRgb.Size = new System.Drawing.Size(90, 20);
         this.qcpColorRgb.TabIndex = 0;
         // 
         // QFloatColor4
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.qcpColorRgb);
         this.Controls.Add(this.tkbColorAlpha);
         this.Controls.Add(this.txtColorAlpha);
         this.Name = "QFloatColor4";
         this.Size = new System.Drawing.Size(195, 21);
         ((System.ComponentModel.ISupportInitialize)(this.tkbColorAlpha)).EndInit();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.TextBox txtColorAlpha;
      private QColorPicker qcpColorRgb;
      private System.Windows.Forms.TrackBar tkbColorAlpha;
   }
}
