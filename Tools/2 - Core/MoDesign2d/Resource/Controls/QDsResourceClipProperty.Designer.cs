namespace MO.Design2d.Resource.Controls
{
   partial class QDsResourceClipProperty
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
         if(disposing && (components != null)) {
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
         this.components = new System.ComponentModel.Container();
         this.pbxViewer = new System.Windows.Forms.PictureBox();
         this.timPlay = new System.Windows.Forms.Timer(this.components);
         this.labReversePath = new System.Windows.Forms.Label();
         this.chbReverse = new System.Windows.Forms.CheckBox();
         this.chbReverseDirection = new System.Windows.Forms.ComboBox();
         this.labReverseCd = new System.Windows.Forms.Label();
         this.chbReverseCd = new System.Windows.Forms.ComboBox();
         ((System.ComponentModel.ISupportInitialize)(this.pbxViewer)).BeginInit();
         this.SuspendLayout();
         // 
         // pbxViewer
         // 
         this.pbxViewer.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
         this.pbxViewer.BackColor = System.Drawing.Color.White;
         this.pbxViewer.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
         this.pbxViewer.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.pbxViewer.Location = new System.Drawing.Point(2, 20);
         this.pbxViewer.Name = "pbxViewer";
         this.pbxViewer.Size = new System.Drawing.Size(119, 160);
         this.pbxViewer.TabIndex = 1;
         this.pbxViewer.TabStop = false;
         this.pbxViewer.MouseClick += new System.Windows.Forms.MouseEventHandler(this.pbxViewer_MouseClick);
         this.pbxViewer.MouseDown += new System.Windows.Forms.MouseEventHandler(this.pbxViewer_Down);
         // 
         // timPlay
         // 
         this.timPlay.Interval = 1000;
         this.timPlay.Tick += new System.EventHandler(this.timPlay_Tick);
         // 
         // labReversePath
         // 
         this.labReversePath.AutoSize = true;
         this.labReversePath.Location = new System.Drawing.Point(3, 189);
         this.labReversePath.Name = "labReversePath";
         this.labReversePath.Size = new System.Drawing.Size(29, 12);
         this.labReversePath.TabIndex = 61;
         this.labReversePath.Text = "来源";
         // 
         // chbReverse
         // 
         this.chbReverse.AutoSize = true;
         this.chbReverse.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chbReverse.Location = new System.Drawing.Point(3, 0);
         this.chbReverse.Name = "chbReverse";
         this.chbReverse.Size = new System.Drawing.Size(69, 16);
         this.chbReverse.TabIndex = 60;
         this.chbReverse.Text = "是否反转";
         this.chbReverse.UseVisualStyleBackColor = true;
         this.chbReverse.CheckedChanged += new System.EventHandler(this.chbReverse_CheckedChanged);
         // 
         // chbReverseDirection
         // 
         this.chbReverseDirection.Enabled = false;
         this.chbReverseDirection.FormattingEnabled = true;
         this.chbReverseDirection.Items.AddRange(new object[] {
            "All",
            "Down",
            "Left",
            "LeftDown",
            "LeftUp",
            "Right",
            "RightDown",
            "RightUp",
            "Up"});
         this.chbReverseDirection.Location = new System.Drawing.Point(38, 186);
         this.chbReverseDirection.Name = "chbReverseDirection";
         this.chbReverseDirection.Size = new System.Drawing.Size(82, 20);
         this.chbReverseDirection.TabIndex = 59;
         this.chbReverseDirection.TextChanged += new System.EventHandler(this.chbDirection_TextChanged);
         // 
         // labReverseCd
         // 
         this.labReverseCd.AutoSize = true;
         this.labReverseCd.Location = new System.Drawing.Point(3, 216);
         this.labReverseCd.Name = "labReverseCd";
         this.labReverseCd.Size = new System.Drawing.Size(29, 12);
         this.labReverseCd.TabIndex = 63;
         this.labReverseCd.Text = "方式";
         // 
         // chbReverseCd
         // 
         this.chbReverseCd.Enabled = false;
         this.chbReverseCd.FormattingEnabled = true;
         this.chbReverseCd.Items.AddRange(new object[] {
            "Level",
            "Plumb"});
         this.chbReverseCd.Location = new System.Drawing.Point(38, 213);
         this.chbReverseCd.Name = "chbReverseCd";
         this.chbReverseCd.Size = new System.Drawing.Size(82, 20);
         this.chbReverseCd.TabIndex = 62;
         this.chbReverseCd.TextChanged += new System.EventHandler(this.chbDirection_TextChanged);
         // 
         // QDsResourceClipProperty
         // 
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Inherit;
         this.AutoSizeMode = System.Windows.Forms.AutoSizeMode.GrowAndShrink;
         this.BackColor = System.Drawing.Color.White;
         this.Controls.Add(this.labReverseCd);
         this.Controls.Add(this.chbReverseCd);
         this.Controls.Add(this.labReversePath);
         this.Controls.Add(this.chbReverse);
         this.Controls.Add(this.chbReverseDirection);
         this.Controls.Add(this.pbxViewer);
         this.Name = "QDsResourceClipProperty";
         this.Size = new System.Drawing.Size(123, 236);
         this.Tag = this.timPlay;
         this.MouseClick += new System.Windows.Forms.MouseEventHandler(this.QDsResourceClipProperty_MouseClick);
         this.Resize += new System.EventHandler(this.QResClipViewer_Resize);
         ((System.ComponentModel.ISupportInitialize)(this.pbxViewer)).EndInit();
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private System.Windows.Forms.PictureBox pbxViewer;
      private System.Windows.Forms.Timer timPlay;
      private System.Windows.Forms.Label labReversePath;
      private System.Windows.Forms.CheckBox chbReverse;
      private System.Windows.Forms.ComboBox chbReverseDirection;
      private System.Windows.Forms.Label labReverseCd;
      private System.Windows.Forms.ComboBox chbReverseCd;
   }
}
