using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d;
using MO.Content3d.Common;
using MO.Content3d.Resource.Material;
using MO.Content3d.Resource.Texture;
using MO.Core.Content.Catalog;
using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design3d.Controls
{
   public partial class QDsImageList : UserControl
   {
      // 文件夹
      private FCfgFolder _folder = new FCfgFolder();

      // 更改图片大小
      FDrImageSize _sizeChange = new FDrImageSize();

      // 树
      private TreeView _tree = null;

      // 触发事件
      public event EventHandler ItemActivate;

      // 触发事件
      public event EventHandler ItemClick;

      // 是否选中
      protected bool bo = false;

      public  TreeView Tree {
         get { return _tree; }
         set { _tree = value; }
      }

      //============================================================
      public FCfgFolder Folder {
         get { return _folder; }
         set { _folder = value; }
      }

      //============================================================
      public QDsImageList() {
         InitializeComponent();
      }

      //============================================================
      public QDsImageList(IContainer container) {
         container.Add(this);
         InitializeComponent();
      }

      //============================================================
      //<T>展现图片<T>
      //============================================================
      public void ShowImage() {
         listView1.SuspendLayout();
         listView1.BeginUpdate();
         imageList.Images.Clear();
         listView1.Items.Clear();
         listView1.Groups.Clear();
         if (_folder != null) {
            foreach (FCfgFolder fol in _folder.Folders) {
               if (fol.Tag is FDrTexture) {
                  TextureImage(fol);
               } else if (fol.Tag is FDrMaterialGroup) {
                  MaterialImage(fol);
               }              
            }
         }
         listView1.LargeImageList = imageList;
         SetImage();
         listView1.EndUpdate();
         listView1.ResumeLayout();
      }

      //============================================================
      //<T>展现纹理图片<T>
      //============================================================
      private void MaterialImage(FCfgFolder fol) {
         FDrMaterialGroup material = fol.Tag as FDrMaterialGroup;
         ListViewGroup group = new ListViewGroup();
         int n = 1;         
         material.Open();
         foreach (FDrModelMaterialTexture mat in material.Textures) {
            if (null != mat && mat.Texture != null) {
               string path = mat.Texture.Directory + "\\icon\\" + mat.SourceType + ".jpg";
               if (RFile.Exists(path)) {
                  Bitmap bit = new Bitmap(path);
                  imageList.Images.Add(bit);
                  int count = imageList.Images.Count;
                  imageList.Images.SetKeyName(count - 1, mat.SourceType + ".jpg");
                  n++;
               }
            }
         }
         group.Tag = material;
         group.Name = fol.Label + "-" + n.ToString();
         listView1.Groups.Add(group);
      }

      //============================================================
      //<T>展现纹理图片<T>
      //============================================================
      private void TextureImage(FCfgFolder fol) {
         FDrTexture text = fol.Tag as FDrTexture;
         ListViewGroup group = new ListViewGroup();
         text.Open();
         string diePath = RContent3dManager.TempDirectory + "\\icon\\" + text.Name;
         int count = 0;
         foreach (FDrTextureBitmap bit in text.Bitmaps) {
            //if (bit.Bool != null && bit.Bool.Equals("Y")) {
            //   string Spath = diePath + "\\" + bit.Source;
            //   string Opath = text.Path + "\\" + bit.Source;
            //   if (!Directory.Exists(diePath)) {
            //      Directory.CreateDirectory(diePath);
            //   }
            //   DirectoryInfo directoryinfo = new DirectoryInfo(Spath);
            //   DateTime date = directoryinfo.LastWriteTime;
            //   DateTime now = DateTime.Now;
            //   TimeSpan ts = now.Subtract(date);
            //   if (ts.Hours > 0 || ts.Minutes > 10) {
            //      _sizeChange.CreateIcon(Opath, Spath, 128, 128);
            //   }
            //   if (RFile.Exists(Spath)) {
            //      count = GetImage(count, bit, Spath);
            //   } else {
            //      _sizeChange.CreateIcon(Opath, Spath, 128, 128);
            //      count = GetImage(count, bit, Spath);
            //   }
            //}
         }
         group.Tag = text;
         group.Name = fol.Label + "-" + count.ToString();
         listView1.Groups.Add(group);
      }

      //============================================================
      //<T>获取相应的图片<T>
      //============================================================
      private int GetImage(int count, FDrTextureBitmap bit, string Spath) {
         Bitmap bitm = new Bitmap(Spath);
         string name = bit.Source;
         imageList.Images.Add(bitm);
         int n = imageList.Images.Count;
         // 将图片的名称保存在Key中
         imageList.Images.SetKeyName(n - 1, name);
         count++;
         return count;
      }

      //============================================================
      //<T>设置图片的相应属性<T>
      //============================================================
      private void SetImage() {
         int info = 0;
         int count = 0;
         foreach (ListViewGroup group in listView1.Groups) {
            string[] str = group.Name.Split('-');
            info = count;
            count = count + RInt.Parse(str[1])-1;
            for (; info < count; info++) {
               ListViewItem item = new ListViewItem();
               Image im = imageList.Images[info];
               // 设置图片的名称
               item.Text = imageList.Images.Keys[info];
               item.ImageIndex = info;
               item.Group = group;
               // 设置分组的名称
               item.Group.Header = group.Name;
               listView1.Items.Add(item);
            }
         }
      }

      //============================================================
      //<T>激活项<T>
      //============================================================
      private void listView1_ItemActivate(object sender, EventArgs e) {
         if (null != ItemActivate) {
            ItemActivate(this, e);
         }
      }

      //============================================================
      //<T>选择节点<T>
      //============================================================
      private void listView1_Click(object sender, EventArgs e) {
         ListView listView = sender as ListView;
         ListViewItem item = listView.SelectedItems[0];
         item.Checked = true;
          if (null != ItemClick) {
           ItemClick(this, e);
         }
      }
    

      private void listView1_ItemCheck(object sender, ItemCheckEventArgs e) {
         ListViewItem ite = listView1.Items[e.Index];
         if(bo){
            bo = false;
            return;
         }
         foreach (ListViewItem item in listView1.Items) {
            if (item.Checked == true) {
               if (ite.Text.Equals(item.Text)) {
                  bo = true;
                  item.Checked = false;
               }
            }
         }
      }
   }
}
