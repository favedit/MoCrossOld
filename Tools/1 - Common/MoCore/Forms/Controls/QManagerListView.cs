using System;
using System.ComponentModel;
using System.Windows.Forms;

namespace MO.Core.Forms.Controls
{
   public partial class QManagerListView : UserControl
   {

      int _columnWidth = 0;

      public QManagerListView() {
         InitializeComponent();

         foreach (ColumnHeader column in lvwControl.Columns) {
            _columnWidth += column.Width;
         }
         if (_columnWidth < lvwControl.Width) {
            ColumnHeader column = new ColumnHeader();
            column.Width = lvwControl.Width - _columnWidth - 5;
            column.Text = null;
            lvwControl.Columns.Add(column);
         }

      }

      private void lvwControl_SizeChanged(object sender, EventArgs e) {
         int index = lvwControl.Columns.Count - 1;
         ColumnHeader column = lvwControl.Columns[index];
         column.Width = lvwControl.Width - _columnWidth - 5;
      }

      //============================================================
      // <T>编辑项</T>
      //============================================================
      [Browsable(true)]
      [Category("编辑列")]
      [Description("编辑控件的列")]
      public ListView.ColumnHeaderCollection Columns {
         get {
            return lvwControl.Columns;
         }
      }

      //============================================================
      // <T>编辑项</T>
      //============================================================
      [Browsable(true)]
      [Category("编辑项")]
      [Description("编辑控件的项")]
      public ListView.ListViewItemCollection Items {
         get {
            return lvwControl.Items;
         }
      }

      //public delegate void ListViewAddItem(ListViewItem item);
      //public event ListViewAddItem AddedItem;
      //public  void Add(ListViewItem item) {
      //   AddedItem(item);
      //   // base.Add(item);
      //} 

      }
   }

   