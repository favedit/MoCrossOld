using System;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Text.RegularExpressions;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>列表控件。</T>
   //============================================================
   public class QListView : ListView
   {
      // 是否自动排序
      protected bool _autoSort = true;

      // 排序列编号
      protected int _sortColumn = -1;

      // 列宽度
      protected int _coulumnWidth = 0;

      // 排序方式
      protected SortOrder _order = SortOrder.Ascending;

      //============================================================
      // <T>构造列表控件。</T>
      //============================================================
      public QListView() {
         InitializeComponent();
      }

      //============================================================
      // <T>初始化组件集合。</T>
      //============================================================
      private void InitializeComponent() {
         SuspendLayout();
         FullRowSelect = true;
         HideSelection = false;
         MultiSelect = false;
         View = System.Windows.Forms.View.Details;
         ResumeLayout(false);

      }

      //============================================================
      // <T>获得或设置是否自动排序。</T>
      //============================================================
      [Browsable(true)]
      [Category("行为")]
      [Description("点击列头时自动排序。")]
      [DefaultValue(true)]
      public bool AutoSort {
         get { return _autoSort; }
         set { _autoSort = value; }
      }

      //============================================================
      // <T>获得或设置是否行选择。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(true)]
      public new bool FullRowSelect {
         get { return base.FullRowSelect; }
         set { base.FullRowSelect = value; }
      }

      //============================================================
      // <T>获得或设置是否隐藏选择。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(false)]
      public new bool HideSelection {
         get { return base.HideSelection; }
         set { base.HideSelection = value; }
      }

      //============================================================
      // <T>获得或设置是否多选。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(false)]
      public new bool MultiSelect {
         get { return base.MultiSelect; }
         set { base.MultiSelect = value; }
      }

      //============================================================
      // <T>获得或设置查看方式。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(View.Details)]
      public new View View {
         get { return base.View; }
         set { base.View = value; }
      }

      //============================================================
      // <T>获得或设置排序方式。</T>
      //============================================================
      [Browsable(false)]
      public SortOrder Order {
         get { return _order; }
         set { _order = value; }
      }

      //============================================================
      // <T>点击列头处理。</T>
      //============================================================
      protected override void OnColumnClick(ColumnClickEventArgs e) {
         if (_autoSort) {
            BeginUpdate();
            base.OnColumnClick(e);
            if (null == this.ListViewItemSorter) {
               ListViewItemSorter = new FListViewColumnComparer(e.Column, SortOrder.Ascending);
            } else {
               if (SortOrder.Ascending == _order) {
                  _order = SortOrder.Descending;
                  ListViewItemSorter = new FListViewColumnComparer(e.Column, SortOrder.Descending);
               } else {
                  _order = SortOrder.Ascending;
                  ListViewItemSorter = new FListViewColumnComparer(e.Column, SortOrder.Ascending);
               }
               FListViewColumnComparer comparer = this.ListViewItemSorter as FListViewColumnComparer;
               this.Sort();
            }
            EndUpdate();
         }
      }

      ////============================================================
      //// <T>点击列头处理。</T>
      ////============================================================
      //public void columnWidth() {
      //   foreach (ColumnHeader Columnitem in Columns) {
      //      _coulumnWidth += lvwControl.Width;
      //   }
      //   if (_coulumnWidth < lvwControl.Width) {
      //      ColumnHeader column = new ColumnHeader();
      //      column.Width = lvwControl.Width - _coulumnWidth;
      //      column.Text = null;
      //      lvwControl.Columns.Add(column);
      //   }
      //}
   }
}
