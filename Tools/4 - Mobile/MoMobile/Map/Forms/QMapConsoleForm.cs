using System;
using System.Drawing;
using System.Windows.Forms;
using MO.Common.IO;
using MoMobile.Map.Common;

namespace MoMobile.Map.Forms
{
   //============================================================
   // <T>地图列表窗体。</T>
   //============================================================
   public partial class QMapConsoleForm : Form
   {

      // 界面控制台表单
      protected static QMapConsoleForm _instance;

      //============================================================
      // <T>构造界面控制台表单。</T>
      //============================================================
      public QMapConsoleForm() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得界面控制台表单。</T>
      //============================================================
      public static QMapConsoleForm Instance {
         get {
            if (null == _instance) {
               _instance = new QMapConsoleForm();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         qdsDesign.Open();
      }

      //============================================================
      // <T>导出按钮事件处理。</T>
      //============================================================
      private void tsbExportAll_Click(object sender, EventArgs e) {
         RMobileManager.MapConsole.Serialize();
         MessageBox.Show("导出成功！");
      }

      //============================================================
      // <T>退出按钮事件处理。</T>
      //============================================================
      private void tsbExit_Click(object sender, EventArgs e) {
         Close();
      }

      //============================================================
      // <T>资源导出按钮事件处理。</T>
      //============================================================
      private void tsbResourceExport_Click(object sender, EventArgs e) {
         string directory = RMobileManager.MapTileConsole.DirectoryExport;
         //
         foreach (FMbMapTileCatalog tileCatalog in RMobileManager.MapTileConsole.MapTileCatalogs) {
            foreach (FMbMapTile tile in tileCatalog.MapTiles) {
               int id = tile.Id;
               string code = "0"+id;
               Bitmap bitmap = tile.Resource;
               int width = bitmap.Width;
               int height = bitmap.Height;
               FByteFile file = new FByteFile();
               file.WriteUint16((ushort)width);
               file.WriteUint16((ushort)height);
               for (int y = 0; y < height; y++) {
                  for (int x = 0; x < width; x++) {
                     Color color = bitmap.GetPixel(x, y);
                     file.WriteUint8(color.R);
                     file.WriteUint8(color.G);
                     file.WriteUint8(color.B);
                     file.WriteUint8(color.A);
                  }
               }
               file.SaveFile(directory+"/" + code + ".ser");
            }
         }
         MessageBox.Show("导出完成！");
      }
   }
}
