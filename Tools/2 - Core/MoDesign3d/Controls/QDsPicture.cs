using MO.Common.Lang;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace MO.Design3d.Controls
{
   public partial class QDsPicture : UserControl
   {
      public QDsPicture() {
         InitializeComponent();
      }

      //============================================================
      public bool IsEmpty() {
         //return qdcPicture.IsEmpty();
         return true;
      }

      //============================================================
      public void LoadResouce(Bitmap bitmap) {
         //qdcPicture.LoadPicture(bitmap);
      }

      //============================================================
      public void Clear() {
         qdcPicture.Clear();
      }

      //============================================================
      private void tsbScale_Click(object sender, EventArgs e) {
         ToolStripButton button = sender as ToolStripButton;
         float scale = RFloat.Parse(button.Tag.ToString());
         //qdcPicture.ChangeSize(scale);
      }
   }
}
