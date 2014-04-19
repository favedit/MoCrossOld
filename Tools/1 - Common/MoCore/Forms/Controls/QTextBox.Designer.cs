namespace MO.Core.Forms.Controls
{
   partial class QTextBox
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
         this.txtContent = new System.Windows.Forms.TextBox();
         this.pbxButton = new System.Windows.Forms.PictureBox();
         this.pbxSign = new System.Windows.Forms.PictureBox();
         ((System.ComponentModel.ISupportInitialize)(this.pbxButton)).BeginInit();
         ((System.ComponentModel.ISupportInitialize)(this.pbxSign)).BeginInit();
         this.SuspendLayout();
         // 
         // txtContent
         // 
         this.txtContent.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left)));
         this.txtContent.BorderStyle = System.Windows.Forms.BorderStyle.None;
         this.txtContent.Location = new System.Drawing.Point(6, 2);
         this.txtContent.Name = "txtContent";
         this.txtContent.Size = new System.Drawing.Size(96, 14);
         this.txtContent.TabIndex = 2;
         this.txtContent.TextChanged += new System.EventHandler(this.txtContent_TextChanged);
         this.txtContent.KeyDown += new System.Windows.Forms.KeyEventHandler(this.QTextBox_KeyDown);
         this.txtContent.MouseDoubleClick += new System.Windows.Forms.MouseEventHandler(this.txtContent_MouseDoubleClick);
         // 
         // pbxButton
         // 
         this.pbxButton.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.pbxButton.BackColor = System.Drawing.SystemColors.Menu;
         this.pbxButton.BackgroundImage = global::MO.Core.Properties.Resources.TextDrop;
         this.pbxButton.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Center;
         this.pbxButton.Location = new System.Drawing.Point(102, 0);
         this.pbxButton.Name = "pbxButton";
         this.pbxButton.Size = new System.Drawing.Size(16, 18);
         this.pbxButton.TabIndex = 3;
         this.pbxButton.TabStop = false;
         this.pbxButton.Visible = false;
         this.pbxButton.Click += new System.EventHandler(this.btnControl_Click);
         this.pbxButton.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbxButton_MouseDown);
         this.pbxButton.MouseLeave += new System.EventHandler(this.pbxButton_MouseLeave);
         this.pbxButton.MouseHover += new System.EventHandler(this.pbxButton_MouseHover);
         this.pbxButton.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pbxButton_MouseMove);
         // 
         // pbxSign
         // 
         this.pbxSign.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left)));
         this.pbxSign.BackColor = System.Drawing.SystemColors.Window;
         this.pbxSign.BackgroundImage = global::MO.Core.Properties.Resources.TextSign;
         this.pbxSign.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
         this.pbxSign.Location = new System.Drawing.Point(2, 2);
         this.pbxSign.Name = "pbxSign";
         this.pbxSign.Size = new System.Drawing.Size(4, 10);
         this.pbxSign.TabIndex = 1;
         this.pbxSign.TabStop = false;
         this.pbxSign.Visible = false;
         this.pbxSign.Click += new System.EventHandler(this.pbxSign_Click);
         this.pbxSign.MouseMove += new System.Windows.Forms.MouseEventHandler(this.pbxSign_MouseMove);
         // 
         // QTextBox
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.BackColor = System.Drawing.SystemColors.HighlightText;
         this.Controls.Add(this.pbxSign);
         this.Controls.Add(this.pbxButton);
         this.Controls.Add(this.txtContent);
         this.Name = "QTextBox";
         this.Size = new System.Drawing.Size(120, 20);
         this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.QTextBox_KeyDown);
         this.Resize += new System.EventHandler(this.QTextBox_Resize);
         ((System.ComponentModel.ISupportInitialize)(this.pbxButton)).EndInit();
         ((System.ComponentModel.ISupportInitialize)(this.pbxSign)).EndInit();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      protected System.Windows.Forms.PictureBox pbxSign;
      protected System.Windows.Forms.TextBox txtContent;
      protected System.Windows.Forms.PictureBox pbxButton;



   }
}
