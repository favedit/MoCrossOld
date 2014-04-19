namespace MO.Design2d.Resource.Controls
{
   partial class QDsResourceCatalog
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
         System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(QDsResourceCatalog));
         this.imlMain = new System.Windows.Forms.ImageList(this.components);
         this.SuspendLayout();
         // 
         // imlMain
         // 
         this.imlMain.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imlMain.ImageStream")));
         this.imlMain.TransparentColor = System.Drawing.Color.Transparent;
         this.imlMain.Images.SetKeyName(0, "FloderClose");
         this.imlMain.Images.SetKeyName(1, "FloderOpen");
         this.imlMain.Images.SetKeyName(2, "Picture");
         this.imlMain.Images.SetKeyName(3, "PictureD");
         this.imlMain.Images.SetKeyName(4, "Animation");
         this.imlMain.Images.SetKeyName(5, "AnimationD");
         // 
         // QDsResourceCatalog
         // 
         this.CheckBoxes = true;
         this.ImageKey = "FloderClose";
         this.ImageList = this.imlMain;
         this.LineColor = System.Drawing.Color.Blue;
         this.SelectedImageKey = "FloderOpen";
         this.ShowRootLines = true;
         this.Size = new System.Drawing.Size(438, 477);
         this.AfterCheck += new System.Windows.Forms.TreeViewEventHandler(this.tvwCatalog_AfterCheck);
         this.Leave += new System.EventHandler(this.QDsResourceCatalog_Leave);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ImageList imlMain;
   }
}
