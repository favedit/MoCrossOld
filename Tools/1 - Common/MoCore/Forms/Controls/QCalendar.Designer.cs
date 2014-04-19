namespace MO.Core.Forms.Controls
{
   partial class QCalendar
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
         this.dtpContent = new System.Windows.Forms.DateTimePicker();
         this.monthCalendar1 = new System.Windows.Forms.MonthCalendar();
         this.comboBox1 = new System.Windows.Forms.ComboBox();
         this.SuspendLayout();
         // 
         // dtpContent
         // 
         this.dtpContent.CalendarForeColor = System.Drawing.SystemColors.AppWorkspace;
         this.dtpContent.CalendarMonthBackground = System.Drawing.SystemColors.HotTrack;
         this.dtpContent.CalendarTitleBackColor = System.Drawing.SystemColors.ActiveCaptionText;
         this.dtpContent.CalendarTitleForeColor = System.Drawing.SystemColors.ActiveCaption;
         this.dtpContent.CalendarTrailingForeColor = System.Drawing.SystemColors.ButtonShadow;
         this.dtpContent.Location = new System.Drawing.Point(42, 3);
         this.dtpContent.Name = "dtpContent";
         this.dtpContent.ShowCheckBox = true;
         this.dtpContent.ShowUpDown = true;
         this.dtpContent.Size = new System.Drawing.Size(200, 21);
         this.dtpContent.TabIndex = 0;
         // 
         // monthCalendar1
         // 
         this.monthCalendar1.Location = new System.Drawing.Point(42, 55);
         this.monthCalendar1.Name = "monthCalendar1";
         this.monthCalendar1.TabIndex = 1;
         // 
         // comboBox1
         // 
         this.comboBox1.FormattingEnabled = true;
         this.comboBox1.Location = new System.Drawing.Point(42, 30);
         this.comboBox1.Name = "comboBox1";
         this.comboBox1.Size = new System.Drawing.Size(121, 20);
         this.comboBox1.TabIndex = 2;
         // 
         // QCalendar
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.Controls.Add(this.comboBox1);
         this.Controls.Add(this.monthCalendar1);
         this.Controls.Add(this.dtpContent);
         this.Name = "QCalendar";
         this.Size = new System.Drawing.Size(280, 244);
         this.ResumeLayout(false);

      }

      #endregion

      protected System.Windows.Forms.DateTimePicker dtpContent;
      private System.Windows.Forms.MonthCalendar monthCalendar1;
      private System.Windows.Forms.ComboBox comboBox1;

   }
}
