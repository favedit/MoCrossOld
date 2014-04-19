using MO.Content3d;
using MO.Content3d.Resource.Map;
using System;
using System.Windows.Forms;

namespace MO.Design3d.Map.Controls
{
   public partial class QDsMapProperty : UserControl 
   {
      // 地图对象
      protected FDrMap _map;

      //============================================================
      public QDsMapProperty() {
         InitializeComponent();
      }

      //============================================================
      public FDrMap Map {
         get { return _map; }
         set { _map = value; }
      }

      //============================================================
      // <T>保存。</T>
      //============================================================
      private void tspSave_Click(object sender, EventArgs e) {
         if(null!=_map){
            _map.Store();
         }
      }

      //============================================================
      // <T>导出。</T>
      //============================================================
      private void toolStripButton1_Click(object sender, EventArgs e) {
         RContent3dManager.MapConsole.TaskExport(_map);
      }

      private void SetValue() {
         txtLabel.Text = _map.Label;
         TxtName.Text = _map.Name;
      }
   }
}
