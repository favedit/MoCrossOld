using System;
using System.Windows.Forms;
using MoMobile.Map.Common;

namespace MoMobile.Map.Forms
{
   //============================================================
   // <T>地图属性控件。</T>
   //============================================================
   public partial class QMapDesign : UserControl
   {
      //============================================================
      // <T>构造界面设计。</T>
      //============================================================
      public QMapDesign() {
         InitializeComponent();
      }

      //============================================================
      // <T>打开资源信息。</T>
      //============================================================
      public void Open() {
         // 初始化界面附加数据
         lvwResources.BeginUpdate();
         lvwResources.Items.Clear();
         foreach (FMbMap map in RMobileManager.MapConsole.Maps) {
            ListViewItem listViewItem = new ListViewItem(map.Name);
            listViewItem.Tag = map;
            lvwResources.Items.Add(listViewItem);
         }
         lvwResources.EndUpdate();
      }

      //============================================================
      // <T>搜索处理。</T>
      //============================================================
      private void cbxSearch_KeyUp(object sender, KeyEventArgs e) {

      }

      //============================================================
      // <T>鼠标双击事件处理。</T>
      //============================================================
      private void lvwResources_DoubleClick(object sender, EventArgs e) {
         if (1 == lvwResources.SelectedItems.Count) {
            ListViewItem item = lvwResources.SelectedItems[0];
            if (null != item) {
               FMbMap map = item.Tag as FMbMap;
               QMapDesignForm mapDesignForm = QMapDesignForm.Instance;
               if (null!= map) {
                  mapDesignForm.LoadMap(map);
               }
               mapDesignForm.Show();
            }
         }
      }

      private void lvwResources_SelectedIndexChanged(object sender, EventArgs e) {
         if (1 == lvwResources.SelectedItems.Count) {
            ListViewItem lvResource = lvwResources.SelectedItems[0];
            FMbMap map = lvResource.Tag as FMbMap;
            qMapProperty1.LoadResource(map);
         }
      }
   }
}
