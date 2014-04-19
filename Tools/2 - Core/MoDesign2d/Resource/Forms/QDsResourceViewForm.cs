using MO.Common.IO;
using MO.Content2d.Core;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design2d.Resource.Forms
{
   public partial class QDsResourceViewForm : Form
   {
      //
      protected Bitmap _bitMap;

      // 取得图片
      protected Image _image;

      public QDsResourceViewForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>资源图片压缩。</T>
      //============================================================
      private void tsbImageCut_Click(object sender, EventArgs e) {
         _image = picBoxImage.BackgroundImage;
         int width = _image.Width;
         int height = _image.Height; 
         _bitMap = new Bitmap(_image);
         FCompressFile CompressFile = new FCompressFile();
         FRsBitmapJpg dsBitmap = new FRsBitmapJpg(_bitMap);
         dsBitmap.Serialize(CompressFile);
         SaveFilePath(CompressFile);
      }

      //============================================================
      // <T>资源图片压缩路径。</T>
      //============================================================
      public void SaveFilePath(FCompressFile CompressFile) {
         saveFileDialogInfo.Filter = "*.mrp|";
         saveFileDialogInfo.FilterIndex = 1;
         this.saveFileDialogInfo.InitialDirectory = @"c:\";
         saveFileDialogInfo.RestoreDirectory = true;
         saveFileDialogInfo.DefaultExt = "mrp";
         if (saveFileDialogInfo.ShowDialog() == DialogResult.OK) {
            string fileName = this.saveFileDialogInfo.FileName;
            CompressFile.SaveFile(fileName);
            MessageBox.Show("压缩成功！");
         }
      }

      //============================================================
      // <T>资源图片存储路径。</T>
      //============================================================
      private void tsbSaveImagePath_Click(object sender, EventArgs e) {
         _image = picBoxImage.BackgroundImage;
         int width = _image.Width;
         int height = _image.Height;
         _bitMap = new Bitmap(_image);
         saveFileDialogInfo.Filter = "*.jpg|";
         saveFileDialogInfo.FilterIndex = 1;
         this.saveFileDialogInfo.InitialDirectory = @"c:\";
         saveFileDialogInfo.RestoreDirectory = true;
         saveFileDialogInfo.DefaultExt = "jpg";
         if (saveFileDialogInfo.ShowDialog() == DialogResult.OK) {
            string fileName = this.saveFileDialogInfo.FileName;
            _bitMap.Save(fileName);
            MessageBox.Show("保存成功！");
         }
      }
   }
}
