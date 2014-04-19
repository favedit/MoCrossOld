using MO.Content2d.Common;
using MO.Content3d.Resource.Theme;
using System;
using System.Windows.Forms;

namespace MO.Design3d.Theme.Controls
{
   //============================================================
   // <T>主题属性控件。</T>
   //============================================================
   public partial class QDsThemeProperty : UserControl
   {
      // 加载中
      protected bool _loading;

      // 材质对象
      protected FDrTheme _theme;

      //============================================================
      // <T>构造主题属性控件。</T>
      //============================================================
      public QDsThemeProperty() {
         InitializeComponent();
      }

      //============================================================
      // <T>获得主题对象。</T>
      //============================================================
      public FDrTheme Theme {
         get { return _theme; }
      }

      //============================================================
      // <T>加载主题信息。</T>
      //
      // @prama theme 主题
      //============================================================
      public void LoadTheme(FDrTheme theme) {
         _loading = true;
         // 打开材质
         theme.Open();
         _theme = theme;
         // 设置属性
         txtName.Text = _theme.Name;
         // 建立列表
         _loading = false;
      }

      //============================================================
      // <T>存储主题信息。</T>
      //
      // @prama theme 主题
      //============================================================
      public void SaveTheme(FDrTheme theme) {
      }

      //============================================================
      // <T>保存按键按下处理。</T>
      //============================================================
      private void tspSave_Click(object sender, EventArgs e) {
         SaveTheme(_theme);
         _theme.Store();
         MessageBox.Show("已经保存成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }

      //============================================================
      // <T>打开按键按下处理。</T>
      //============================================================
      private void tsbOpen_Click(object sender, EventArgs e) {
         if (null != _theme) {
            System.Diagnostics.Process.Start(_theme.Directory);
         }
      }
      
      //============================================================
      // <T>导出按键按下处理。</T>
      //============================================================
      private void tspExport_Click(object sender, EventArgs e) {
         _theme.Export(ERsExportMode.CompressDeflate);
         MessageBox.Show("已经导出成功。", "信息", MessageBoxButtons.OK, MessageBoxIcon.Information, MessageBoxDefaultButton.Button1);
      }
   }
}
