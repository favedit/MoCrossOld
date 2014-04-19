namespace MO.Core.Forms.Controls
{
   partial class QManagerListView
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
         System.Windows.Forms.ListViewItem listViewItem1 = new System.Windows.Forms.ListViewItem("");
         System.Windows.Forms.ListViewItem listViewItem2 = new System.Windows.Forms.ListViewItem("");
         this.lvwControl = new System.Windows.Forms.ListView();
         this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.SuspendLayout();
         // 
         // lvwControl
         // 
         this.lvwControl.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2});
         this.lvwControl.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwControl.FullRowSelect = true;
         this.lvwControl.HideSelection = false;
         this.lvwControl.Items.AddRange(new System.Windows.Forms.ListViewItem[] {
            listViewItem1,
            listViewItem2});
         this.lvwControl.Location = new System.Drawing.Point(0, 0);
         this.lvwControl.MultiSelect = false;
         this.lvwControl.Name = "lvwControl";
         this.lvwControl.Size = new System.Drawing.Size(331, 154);
         this.lvwControl.Sorting = System.Windows.Forms.SortOrder.Descending;
         this.lvwControl.TabIndex = 0;
         this.lvwControl.UseCompatibleStateImageBehavior = false;
         this.lvwControl.View = System.Windows.Forms.View.Details;
         this.lvwControl.SizeChanged += new System.EventHandler(this.lvwControl_SizeChanged);
         // 
         // QManagerListView
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.lvwControl);
         this.Name = "QManagerListView";
         this.Size = new System.Drawing.Size(331, 154);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ListView lvwControl;
      private System.Windows.Forms.ColumnHeader columnHeader1;
      private System.Windows.Forms.ColumnHeader columnHeader2;
   }
}
