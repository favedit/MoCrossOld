namespace MO.Design2d.Frame.Property
{
   partial class QUiControlProperty
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
         this.chkOptionEnable = new MO.Core.Forms.Controls.QCheckBox();
         this.txtSize = new MO.Core.Forms.Controls.QTextBox();
         this.txtLocation = new MO.Core.Forms.Controls.QTextBox();
         this.labLabel = new System.Windows.Forms.Label();
         this.label1 = new System.Windows.Forms.Label();
         this.label2 = new System.Windows.Forms.Label();
         this.txtMargin = new MO.Core.Forms.Controls.QTextBox();
         this.label3 = new System.Windows.Forms.Label();
         this.txtPadding = new MO.Core.Forms.Controls.QTextBox();
         this.txtClick = new MO.Core.Forms.Controls.QTextBox();
         this.labHint = new System.Windows.Forms.Label();
         this.label4 = new System.Windows.Forms.Label();
         this.txtMouseDown = new MO.Core.Forms.Controls.QTextBox();
         this.label5 = new System.Windows.Forms.Label();
         this.txtMouseEnter = new MO.Core.Forms.Controls.QTextBox();
         this.label6 = new System.Windows.Forms.Label();
         this.txtMouseMove = new MO.Core.Forms.Controls.QTextBox();
         this.label8 = new System.Windows.Forms.Label();
         this.txtDoubleClick = new MO.Core.Forms.Controls.QTextBox();
         this.label9 = new System.Windows.Forms.Label();
         this.txtMouseUp = new MO.Core.Forms.Controls.QTextBox();
         this.label10 = new System.Windows.Forms.Label();
         this.txtMouseLeave = new MO.Core.Forms.Controls.QTextBox();
         this.label7 = new System.Windows.Forms.Label();
         this.label11 = new System.Windows.Forms.Label();
         this.labOuterBorder = new System.Windows.Forms.Label();
         this.labInnerBorder = new System.Windows.Forms.Label();
         this.cbxDockCd = new System.Windows.Forms.ComboBox();
         this.label12 = new System.Windows.Forms.Label();
         this.qUiOuterBorderProperty = new MO.Design2d.Frame.Property.QUiBorderEditor();
         this.qUiInnerBorderProperty = new MO.Design2d.Frame.Property.QUiBorderEditor();
         this.qpeBackResource = new MO.Design2d.Frame.Property.QUiPictureEditor();
         this.qpeForeResource = new MO.Design2d.Frame.Property.QUiPictureEditor();
         this.chkOptionVisible = new MO.Core.Forms.Controls.QCheckBox();
         this.qcpForeColor = new MO.Core.Forms.Controls.QColorPicker();
         this.labForeColor = new System.Windows.Forms.Label();
         this.qcpBackColor = new MO.Core.Forms.Controls.QColorPicker();
         this.labBackColor = new System.Windows.Forms.Label();
         this.SuspendLayout();
         // 
         // chkOptionEnable
         // 
         this.chkOptionEnable.AutoSize = true;
         this.chkOptionEnable.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionEnable.Location = new System.Drawing.Point(204, 4);
         this.chkOptionEnable.Name = "chkOptionEnable";
         this.chkOptionEnable.Size = new System.Drawing.Size(57, 16);
         this.chkOptionEnable.TabIndex = 13;
         this.chkOptionEnable.Text = "有效性";
         this.chkOptionEnable.UseVisualStyleBackColor = true;
         // 
         // txtSize
         // 
         this.txtSize.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtSize.Location = new System.Drawing.Point(261, 29);
         this.txtSize.Name = "txtSize";
         this.txtSize.Size = new System.Drawing.Size(120, 19);
         this.txtSize.TabIndex = 10;
         this.txtSize.TextChanged += new System.EventHandler(this.txtSize_TextChanged);
         // 
         // txtLocation
         // 
         this.txtLocation.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtLocation.Location = new System.Drawing.Point(68, 29);
         this.txtLocation.Name = "txtLocation";
         this.txtLocation.Size = new System.Drawing.Size(120, 19);
         this.txtLocation.TabIndex = 11;
         this.txtLocation.TextChanged += new System.EventHandler(this.txtLocation_TextChanged);
         // 
         // labLabel
         // 
         this.labLabel.AutoSize = true;
         this.labLabel.Location = new System.Drawing.Point(202, 32);
         this.labLabel.Name = "labLabel";
         this.labLabel.Size = new System.Drawing.Size(29, 12);
         this.labLabel.TabIndex = 7;
         this.labLabel.Text = "尺寸";
         // 
         // label1
         // 
         this.label1.AutoSize = true;
         this.label1.Location = new System.Drawing.Point(9, 32);
         this.label1.Name = "label1";
         this.label1.Size = new System.Drawing.Size(29, 12);
         this.label1.TabIndex = 8;
         this.label1.Text = "坐标";
         // 
         // label2
         // 
         this.label2.AutoSize = true;
         this.label2.Location = new System.Drawing.Point(9, 57);
         this.label2.Name = "label2";
         this.label2.Size = new System.Drawing.Size(53, 12);
         this.label2.TabIndex = 8;
         this.label2.Text = "内部空白";
         // 
         // txtMargin
         // 
         this.txtMargin.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtMargin.Location = new System.Drawing.Point(68, 54);
         this.txtMargin.Name = "txtMargin";
         this.txtMargin.Size = new System.Drawing.Size(120, 19);
         this.txtMargin.TabIndex = 11;
         // 
         // label3
         // 
         this.label3.AutoSize = true;
         this.label3.Location = new System.Drawing.Point(202, 57);
         this.label3.Name = "label3";
         this.label3.Size = new System.Drawing.Size(53, 12);
         this.label3.TabIndex = 8;
         this.label3.Text = "外部空白";
         // 
         // txtPadding
         // 
         this.txtPadding.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtPadding.Location = new System.Drawing.Point(261, 54);
         this.txtPadding.Name = "txtPadding";
         this.txtPadding.Size = new System.Drawing.Size(120, 19);
         this.txtPadding.TabIndex = 11;
         // 
         // txtClick
         // 
         this.txtClick.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtClick.Location = new System.Drawing.Point(68, 211);
         this.txtClick.Name = "txtClick";
         this.txtClick.Size = new System.Drawing.Size(120, 19);
         this.txtClick.TabIndex = 15;
         // 
         // labHint
         // 
         this.labHint.AutoSize = true;
         this.labHint.Location = new System.Drawing.Point(9, 214);
         this.labHint.Name = "labHint";
         this.labHint.Size = new System.Drawing.Size(53, 12);
         this.labHint.TabIndex = 14;
         this.labHint.Text = "鼠标单击";
         // 
         // label4
         // 
         this.label4.AutoSize = true;
         this.label4.Location = new System.Drawing.Point(9, 239);
         this.label4.Name = "label4";
         this.label4.Size = new System.Drawing.Size(53, 12);
         this.label4.TabIndex = 14;
         this.label4.Text = "鼠标落下";
         // 
         // txtMouseDown
         // 
         this.txtMouseDown.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtMouseDown.Location = new System.Drawing.Point(68, 236);
         this.txtMouseDown.Name = "txtMouseDown";
         this.txtMouseDown.Size = new System.Drawing.Size(120, 19);
         this.txtMouseDown.TabIndex = 15;
         // 
         // label5
         // 
         this.label5.AutoSize = true;
         this.label5.Location = new System.Drawing.Point(9, 264);
         this.label5.Name = "label5";
         this.label5.Size = new System.Drawing.Size(53, 12);
         this.label5.TabIndex = 14;
         this.label5.Text = "鼠标进入";
         // 
         // txtMouseEnter
         // 
         this.txtMouseEnter.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtMouseEnter.Location = new System.Drawing.Point(68, 261);
         this.txtMouseEnter.Name = "txtMouseEnter";
         this.txtMouseEnter.Size = new System.Drawing.Size(120, 19);
         this.txtMouseEnter.TabIndex = 15;
         // 
         // label6
         // 
         this.label6.AutoSize = true;
         this.label6.Location = new System.Drawing.Point(9, 289);
         this.label6.Name = "label6";
         this.label6.Size = new System.Drawing.Size(53, 12);
         this.label6.TabIndex = 14;
         this.label6.Text = "鼠标移动";
         // 
         // txtMouseMove
         // 
         this.txtMouseMove.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtMouseMove.Location = new System.Drawing.Point(68, 286);
         this.txtMouseMove.Name = "txtMouseMove";
         this.txtMouseMove.Size = new System.Drawing.Size(120, 19);
         this.txtMouseMove.TabIndex = 15;
         // 
         // label8
         // 
         this.label8.AutoSize = true;
         this.label8.Location = new System.Drawing.Point(202, 214);
         this.label8.Name = "label8";
         this.label8.Size = new System.Drawing.Size(53, 12);
         this.label8.TabIndex = 14;
         this.label8.Text = "鼠标双击";
         // 
         // txtDoubleClick
         // 
         this.txtDoubleClick.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtDoubleClick.Location = new System.Drawing.Point(261, 211);
         this.txtDoubleClick.Name = "txtDoubleClick";
         this.txtDoubleClick.Size = new System.Drawing.Size(120, 19);
         this.txtDoubleClick.TabIndex = 15;
         // 
         // label9
         // 
         this.label9.AutoSize = true;
         this.label9.Location = new System.Drawing.Point(202, 239);
         this.label9.Name = "label9";
         this.label9.Size = new System.Drawing.Size(53, 12);
         this.label9.TabIndex = 14;
         this.label9.Text = "鼠标抬起";
         // 
         // txtMouseUp
         // 
         this.txtMouseUp.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtMouseUp.Location = new System.Drawing.Point(261, 236);
         this.txtMouseUp.Name = "txtMouseUp";
         this.txtMouseUp.Size = new System.Drawing.Size(120, 19);
         this.txtMouseUp.TabIndex = 15;
         // 
         // label10
         // 
         this.label10.AutoSize = true;
         this.label10.Location = new System.Drawing.Point(202, 264);
         this.label10.Name = "label10";
         this.label10.Size = new System.Drawing.Size(53, 12);
         this.label10.TabIndex = 14;
         this.label10.Text = "鼠标离开";
         // 
         // txtMouseLeave
         // 
         this.txtMouseLeave.BackColor = System.Drawing.SystemColors.HighlightText;
         this.txtMouseLeave.Location = new System.Drawing.Point(261, 261);
         this.txtMouseLeave.Name = "txtMouseLeave";
         this.txtMouseLeave.Size = new System.Drawing.Size(120, 19);
         this.txtMouseLeave.TabIndex = 15;
         // 
         // label7
         // 
         this.label7.AutoSize = true;
         this.label7.Location = new System.Drawing.Point(9, 108);
         this.label7.Name = "label7";
         this.label7.Size = new System.Drawing.Size(53, 12);
         this.label7.TabIndex = 14;
         this.label7.Text = "前景资源";
         // 
         // label11
         // 
         this.label11.AutoSize = true;
         this.label11.Location = new System.Drawing.Point(9, 134);
         this.label11.Name = "label11";
         this.label11.Size = new System.Drawing.Size(53, 12);
         this.label11.TabIndex = 14;
         this.label11.Text = "后景资源";
         // 
         // labOuterBorder
         // 
         this.labOuterBorder.AutoSize = true;
         this.labOuterBorder.Location = new System.Drawing.Point(9, 187);
         this.labOuterBorder.Name = "labOuterBorder";
         this.labOuterBorder.Size = new System.Drawing.Size(41, 12);
         this.labOuterBorder.TabIndex = 19;
         this.labOuterBorder.Text = "内边框";
         // 
         // labInnerBorder
         // 
         this.labInnerBorder.AutoSize = true;
         this.labInnerBorder.Location = new System.Drawing.Point(9, 160);
         this.labInnerBorder.Name = "labInnerBorder";
         this.labInnerBorder.Size = new System.Drawing.Size(41, 12);
         this.labInnerBorder.TabIndex = 22;
         this.labInnerBorder.Text = "外边框";
         // 
         // cbxDockCd
         // 
         this.cbxDockCd.FormattingEnabled = true;
         this.cbxDockCd.Location = new System.Drawing.Point(68, 3);
         this.cbxDockCd.Name = "cbxDockCd";
         this.cbxDockCd.Size = new System.Drawing.Size(120, 20);
         this.cbxDockCd.TabIndex = 23;
         // 
         // label12
         // 
         this.label12.AutoSize = true;
         this.label12.Location = new System.Drawing.Point(9, 7);
         this.label12.Name = "label12";
         this.label12.Size = new System.Drawing.Size(53, 12);
         this.label12.TabIndex = 14;
         this.label12.Text = "停靠位置";
         // 
         // qUiOuterBorderProperty
         // 
         this.qUiOuterBorderProperty.Location = new System.Drawing.Point(68, 184);
         this.qUiOuterBorderProperty.Name = "qUiOuterBorderProperty";
         this.qUiOuterBorderProperty.Size = new System.Drawing.Size(340, 21);
         this.qUiOuterBorderProperty.TabIndex = 21;
         // 
         // qUiInnerBorderProperty
         // 
         this.qUiInnerBorderProperty.Location = new System.Drawing.Point(68, 157);
         this.qUiInnerBorderProperty.Name = "qUiInnerBorderProperty";
         this.qUiInnerBorderProperty.Size = new System.Drawing.Size(340, 21);
         this.qUiInnerBorderProperty.TabIndex = 20;
         // 
         // qpeBackResource
         // 
         this.qpeBackResource.Location = new System.Drawing.Point(68, 131);
         this.qpeBackResource.Name = "qpeBackResource";
         this.qpeBackResource.Size = new System.Drawing.Size(340, 20);
         this.qpeBackResource.TabIndex = 16;
         // 
         // qpeForeResource
         // 
         this.qpeForeResource.Location = new System.Drawing.Point(68, 105);
         this.qpeForeResource.Name = "qpeForeResource";
         this.qpeForeResource.Size = new System.Drawing.Size(340, 20);
         this.qpeForeResource.TabIndex = 16;
         // 
         // chkOptionVisible
         // 
         this.chkOptionVisible.AutoSize = true;
         this.chkOptionVisible.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
         this.chkOptionVisible.Location = new System.Drawing.Point(267, 4);
         this.chkOptionVisible.Name = "chkOptionVisible";
         this.chkOptionVisible.Size = new System.Drawing.Size(57, 16);
         this.chkOptionVisible.TabIndex = 13;
         this.chkOptionVisible.Text = "可见性";
         this.chkOptionVisible.UseVisualStyleBackColor = true;
         // 
         // qcpForeColor
         // 
         this.qcpForeColor.Location = new System.Drawing.Point(68, 79);
         this.qcpForeColor.Name = "qcpForeColor";
         this.qcpForeColor.SelectColor = System.Drawing.Color.Black;
         this.qcpForeColor.Size = new System.Drawing.Size(120, 20);
         this.qcpForeColor.TabIndex = 24;
         // 
         // labForeColor
         // 
         this.labForeColor.AutoSize = true;
         this.labForeColor.Location = new System.Drawing.Point(9, 83);
         this.labForeColor.Name = "labForeColor";
         this.labForeColor.Size = new System.Drawing.Size(53, 12);
         this.labForeColor.TabIndex = 14;
         this.labForeColor.Text = "前景颜色";
         // 
         // qcpBackColor
         // 
         this.qcpBackColor.Location = new System.Drawing.Point(261, 79);
         this.qcpBackColor.Name = "qcpBackColor";
         this.qcpBackColor.SelectColor = System.Drawing.Color.Black;
         this.qcpBackColor.Size = new System.Drawing.Size(120, 20);
         this.qcpBackColor.TabIndex = 24;
         // 
         // labBackColor
         // 
         this.labBackColor.AutoSize = true;
         this.labBackColor.Location = new System.Drawing.Point(202, 83);
         this.labBackColor.Name = "labBackColor";
         this.labBackColor.Size = new System.Drawing.Size(53, 12);
         this.labBackColor.TabIndex = 8;
         this.labBackColor.Text = "背景颜色";
         // 
         // QUiControlProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.qcpBackColor);
         this.Controls.Add(this.qcpForeColor);
         this.Controls.Add(this.cbxDockCd);
         this.Controls.Add(this.labInnerBorder);
         this.Controls.Add(this.qUiOuterBorderProperty);
         this.Controls.Add(this.qUiInnerBorderProperty);
         this.Controls.Add(this.labOuterBorder);
         this.Controls.Add(this.qpeBackResource);
         this.Controls.Add(this.qpeForeResource);
         this.Controls.Add(this.label11);
         this.Controls.Add(this.txtMouseMove);
         this.Controls.Add(this.label7);
         this.Controls.Add(this.label6);
         this.Controls.Add(this.txtMouseLeave);
         this.Controls.Add(this.txtMouseEnter);
         this.Controls.Add(this.label10);
         this.Controls.Add(this.label5);
         this.Controls.Add(this.txtMouseUp);
         this.Controls.Add(this.label9);
         this.Controls.Add(this.txtMouseDown);
         this.Controls.Add(this.txtDoubleClick);
         this.Controls.Add(this.label4);
         this.Controls.Add(this.label12);
         this.Controls.Add(this.label8);
         this.Controls.Add(this.txtClick);
         this.Controls.Add(this.labForeColor);
         this.Controls.Add(this.labHint);
         this.Controls.Add(this.chkOptionVisible);
         this.Controls.Add(this.chkOptionEnable);
         this.Controls.Add(this.txtSize);
         this.Controls.Add(this.txtPadding);
         this.Controls.Add(this.txtMargin);
         this.Controls.Add(this.labBackColor);
         this.Controls.Add(this.label3);
         this.Controls.Add(this.txtLocation);
         this.Controls.Add(this.label2);
         this.Controls.Add(this.labLabel);
         this.Controls.Add(this.label1);
         this.Name = "QUiControlProperty";
         this.Size = new System.Drawing.Size(420, 312);
         this.ResumeLayout(false);
         this.PerformLayout();

      }

      #endregion

      private MO.Core.Forms.Controls.QCheckBox chkOptionEnable;
      private MO.Core.Forms.Controls.QTextBox txtSize;
      private MO.Core.Forms.Controls.QTextBox txtLocation;
      private System.Windows.Forms.Label labLabel;
      private System.Windows.Forms.Label label1;
      private System.Windows.Forms.Label label2;
      private MO.Core.Forms.Controls.QTextBox txtMargin;
      private System.Windows.Forms.Label label3;
      private MO.Core.Forms.Controls.QTextBox txtPadding;
      private MO.Core.Forms.Controls.QTextBox txtClick;
      private System.Windows.Forms.Label labHint;
      private System.Windows.Forms.Label label4;
      private MO.Core.Forms.Controls.QTextBox txtMouseDown;
      private System.Windows.Forms.Label label5;
      private MO.Core.Forms.Controls.QTextBox txtMouseEnter;
      private System.Windows.Forms.Label label6;
      private MO.Core.Forms.Controls.QTextBox txtMouseMove;
      private System.Windows.Forms.Label label8;
      private MO.Core.Forms.Controls.QTextBox txtDoubleClick;
      private System.Windows.Forms.Label label9;
      private MO.Core.Forms.Controls.QTextBox txtMouseUp;
      private System.Windows.Forms.Label label10;
      private MO.Core.Forms.Controls.QTextBox txtMouseLeave;
      private QUiPictureEditor qpeForeResource;
      private System.Windows.Forms.Label label7;
      private System.Windows.Forms.Label label11;
      private QUiPictureEditor qpeBackResource;
      private System.Windows.Forms.Label labOuterBorder;
      private QUiBorderEditor qUiInnerBorderProperty;
      private QUiBorderEditor qUiOuterBorderProperty;
      private System.Windows.Forms.Label labInnerBorder;
      private System.Windows.Forms.ComboBox cbxDockCd;
      private System.Windows.Forms.Label label12;
      private MO.Core.Forms.Controls.QCheckBox chkOptionVisible;
      private MO.Core.Forms.Controls.QColorPicker qcpForeColor;
      private System.Windows.Forms.Label labForeColor;
      private MO.Core.Forms.Controls.QColorPicker qcpBackColor;
      private System.Windows.Forms.Label labBackColor;


   }
}
