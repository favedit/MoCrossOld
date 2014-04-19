namespace MO.Design3d.Controls
{
   partial class QDsImageList
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
         this.components = new System.ComponentModel.Container();
         this.listView1 = new System.Windows.Forms.ListView();
         this.imageList = new System.Windows.Forms.ImageList(this.components);
         this.SuspendLayout();
         // 
         // listView1
         // 
         this.listView1.CheckBoxes = true;
         this.listView1.Dock = System.Windows.Forms.DockStyle.Fill;
         this.listView1.Location = new System.Drawing.Point(0, 0);
         this.listView1.Name = "listView1";
         this.listView1.Size = new System.Drawing.Size(731, 388);
         this.listView1.TabIndex = 0;
         this.listView1.UseCompatibleStateImageBehavior = false;
         this.listView1.ItemActivate += new System.EventHandler(this.listView1_ItemActivate);
         this.listView1.ItemCheck += new System.Windows.Forms.ItemCheckEventHandler(this.listView1_ItemCheck);
         this.listView1.Click += new System.EventHandler(this.listView1_Click);
         // 
         // imageList
         // 
         this.imageList.ColorDepth = System.Windows.Forms.ColorDepth.Depth32Bit;
         this.imageList.ImageSize = new System.Drawing.Size(128, 128);
         this.imageList.TransparentColor = System.Drawing.Color.Transparent;
         // 
         // QDrImageList
         // 
         this.Controls.Add(this.listView1);
         this.Name = "QDrImageList";
         this.Size = new System.Drawing.Size(731, 388);
         this.ResumeLayout(false);

      }

      #endregion

      private System.Windows.Forms.ImageList imageList;
      public System.Windows.Forms.ListView listView1;
   }
}
