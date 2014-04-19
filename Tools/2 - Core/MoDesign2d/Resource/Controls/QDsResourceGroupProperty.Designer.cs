namespace MO.Design2d.Resource.Controls
{
   partial class QDsResourceGroupProperty
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
         this.lvwResources = new System.Windows.Forms.ListView();
         this.chdLabel = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.SuspendLayout();
         // 
         // lvwResources
         // 
         this.lvwResources.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
         this.lvwResources.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdLabel});
         this.lvwResources.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvwResources.FullRowSelect = true;
         this.lvwResources.HideSelection = false;
         this.lvwResources.Location = new System.Drawing.Point(0, 0);
         this.lvwResources.MultiSelect = false;
         this.lvwResources.Name = "lvwResources";
         this.lvwResources.Size = new System.Drawing.Size(702, 478);
         this.lvwResources.TabIndex = 7;
         this.lvwResources.UseCompatibleStateImageBehavior = false;
         this.lvwResources.View = System.Windows.Forms.View.Details;
         // 
         // chdLabel
         // 
         this.chdLabel.Text = "名称";
         this.chdLabel.Width = 320;
         // 
         // QDsResourceGroupProperty
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.lvwResources);
         this.Name = "QDsResourceGroupProperty";
         this.Size = new System.Drawing.Size(702, 478);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ListView lvwResources;
      private System.Windows.Forms.ColumnHeader chdLabel;
   }
}
