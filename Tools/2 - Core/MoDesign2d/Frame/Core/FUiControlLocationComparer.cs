using System.Collections.Generic;
using MO.Common.Lang;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Core
{
   //============================================================
   // <T>控件位置比较器。</T>
   //============================================================
   public class FUiControlLocationComparer : FObject, IComparer<FUiControl>
   {
      // 排序方式
      public EUiControlLocation _locationCd;

      // 正序排列
      public bool _asc;

      //============================================================
      // <T>构造控件位置比较器。</T>
      //
      // @param value 内容
      //============================================================
      public FUiControlLocationComparer(EUiControlLocation locationCd, bool asc) {
         _locationCd = locationCd;
         _asc = asc;
      }

      //============================================================
      // <T>构造控件位置比较器。</T>
      //============================================================
      public int Compare(FUiControl source, FUiControl target) {
         if(_locationCd == EUiControlLocation.X){
            int x1 = source.Location.X;
            int x2 = target.Location.X;
            return _asc ? (x1 - x2) : (x2 - x1);
         }
         if(_locationCd == EUiControlLocation.Y){
            int y1 = source.Location.Y;
            int y2 = target.Location.Y;
            return _asc ? (y1 - y2) : (y2 - y1);
         }
         return 0;
      }
   }
}
