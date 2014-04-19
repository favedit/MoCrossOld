using System;
using System.Collections;
using System.Windows.Forms;
using System.Text.RegularExpressions;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>列表列比较器。</T>
   //============================================================
   public class FListViewColumnComparer : IComparer
   {
      // 列编号
      private int _columnId = 0;

      // 排序方式
      private SortOrder _orderCd = SortOrder.Ascending;

      //============================================================
      // <T>构造列表列比较器。</T>
      //============================================================
      public FListViewColumnComparer() {
      }

      //============================================================
      // <T>构造列表列比较器。</T>
      //
      // @param columnId 列编号
      // @param orderCd 排序方式
      //============================================================
      public FListViewColumnComparer(int columnId, SortOrder orderCd) {
         _columnId = columnId;
         _orderCd = orderCd;
      }

      //============================================================
      // <T>比较器参数。</T>
      //
      // @param source 来源
      // @param target 目标
      //============================================================
      public int Compare(object source, object target) {
         int result = -1;
         ListViewItem sourceItem = source as ListViewItem;
         ListViewItem targetItem = target as ListViewItem;
         if (ValidateNum(sourceItem.SubItems[_columnId].Text) && ValidateNum(targetItem.SubItems[_columnId].Text)) {
            result = decimal.Compare(Convert.ToDecimal(sourceItem.SubItems[_columnId].Text), Convert.ToDecimal(targetItem.SubItems[_columnId].Text));
            return (_orderCd == SortOrder.Descending) ? -result : result;
         } else {
            result = String.Compare(sourceItem.SubItems[_columnId].Text, targetItem.SubItems[_columnId].Text);
            return (_orderCd == SortOrder.Descending) ? -result : result;
         }
      }

      //============================================================
      // <T>判断是否为数字。</T>
      //============================================================
      public static bool ValidateNum(string InputStr) {
         if (InputStr != "") {
            if (Regex.IsMatch(InputStr.Trim(' '), @"^(0|[+-]?0\.\d+|[+-]?[1-9]([0-9]+)?(\.\d+)?)$"))
               return true;
            else
               return false;
         }
         return true;
      }
   }
}
