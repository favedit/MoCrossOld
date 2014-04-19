namespace MO.Design2d.Frame.Forms
{
   partial class QUiResourceList
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QUiResourceList));
         this.lvResources = new System.Windows.Forms.ListView();
         this.chdId = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdValid = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.chdLabel = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.SuspendLayout();
         // 
         // lvResources
         // 
         this.lvResources.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.chdId,
            this.chdValid,
            this.chdLabel});
         this.lvResources.Dock = System.Windows.Forms.DockStyle.Fill;
         this.lvResources.FullRowSelect = true;
         this.lvResources.HideSelection = false;
         this.lvResources.LargeImageList = this.imlMain;
         this.lvResources.Location = new System.Drawing.Point(0, 0);
         this.lvResources.MultiSelect = false;
         this.lvResources.Name = "lvResources";
         this.lvResources.Size = new System.Drawing.Size(537, 283);
         this.lvResources.SmallImageList = this.imlMain;
         this.lvResources.TabIndex = 1;
         this.lvResources.UseCompatibleStateImageBehavior = false;
         this.lvResources.View = System.Windows.Forms.View.Details;
         this.lvResources.SelectedIndexChanged += new System.EventHandler(this.lvResources_SelectedIndexChanged);
         this.lvResources.DoubleClick += new System.EventHandler(this.lvResources_DoubleClick);
         // 
         // chdId
         // 
         this.chdId.Text = "编号";
         this.chdId.Width = 100;
         // 
         // chdValid
         // 
         this.chdValid.Text = "有效";
         // 
         // chdLabel
         // 
         this.chdLabel.Text = "标签";
         this.chdLabel.Width = 300;
         // 
         // imlMain
         // 
         this.imlMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlMain.ImageStream")));
         this.imlMain.TransparentColor = System.Drawing.Color.Transparent;
         this.imlMain.Images.SetKeyName(0, "Picture");
         this.imlMain.Images.SetKeyName(1, "Animation");
         // 
         // QUiResourceList
         // 
         this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
         this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
         this.Controls.Add(this.lvResources);
         this.Name = "QUiResourceList";
         this.Size = new System.Drawing.Size(537, 283);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ListView lvResources;
      private System.Windows.Forms.ColumnHeader chdId;
      private System.Windows.Forms.ColumnHeader chdValid;
      private System.Windows.Forms.ColumnHeader chdLabel;
      private System.Windows.Forms.ImageList imlMain;
   }
}
