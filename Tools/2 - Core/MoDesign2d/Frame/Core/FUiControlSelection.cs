using MO.Common.Lang;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Core
{
   //============================================================
   // <T>界面选择控件集合。</T>
   //============================================================
   public class FUiControlSelection : FObjects<FUiControl>
   {
      // 焦点控件
      protected FUiControl _focusControl;

      //============================================================
      // <T>构造界面选择控件集合。</T>
      //============================================================
      public FUiControlSelection() {
      }

      //============================================================
      // <T>获得是否有焦点控件。</T>
      //
      // @return 是否有焦点控件
      //============================================================
      public bool HasFocusControl() {
         return (_focusControl != null);
      }

      //============================================================
      // <T>获得是否有选择控件。</T>
      //
      // @return 是否有选择控件
      //============================================================
      public bool HasSelectControl() {
         return (Count > 0);
      }

      //============================================================
      // <T>获得或设置焦点控件。</T>
      //============================================================
      public FUiControl FocusControl {
         get { return _focusControl; }
         set {
            // 检查变化
            if(_focusControl == value) {
               return;
            }
            // 清空选择
            Clear();
            // 取消旧焦点
            if(_focusControl != null) {
               _focusControl.Designer.DesignSelect = false;
               _focusControl.Designer.DesignFocus = false;
            }
            // 设置新焦点
            _focusControl = value;
            if(_focusControl != null) {
               _focusControl.Designer.DesignSelect = true;
               _focusControl.Designer.DesignFocus = true;
               // 放入选择列表
               PushUnique(_focusControl);
            }
         }
      }

      //============================================================
      // <T>追加一个选中控件。</T>
      //============================================================
      public void AddSelectControl(FUiControl control) {
         // 检查参数
         if(control == null) {
            return;
         }
         // 追加节点
         PushUnique(control);
         // 新追加的控件为焦点对象
         ClearFocus();
         _focusControl = control;
         _focusControl.Designer.DesignFocus = true;
         _focusControl.Designer.DesignSelect = true;
      }

      //============================================================
      // <T>追加一个选中控件。</T>
      //============================================================
      public void AddSelectNotFocusControl(FUiControl control) {
         // 检查参数
         if(control == null) {
            return;
         }
         // 追加节点
         PushUnique(control);
         // 新追加的控件为焦点对象
         control.Designer.DesignSelect = true;
      }

      //============================================================
      // <T>存储边界内容。</T>
      //============================================================
      public void BoundsStore() {
         foreach(FUiControl control in this) {
            FUiControlDesigner designer = control.Designer;
            if(designer != null) {
               designer.BoundsStore();
            }
         }
      }

      //============================================================
      // <T>移动边界内容。</T>
      //
      // @param x 横向移动位置
      // @param y 纵向移动位置
      //============================================================
      public void BoundsLocation(int x, int y) {
         foreach(FUiControl control in this) {
            FUiControlDesigner designer = control.Designer;
            if(designer != null) {
               designer.BoundsLocation(x, y);
            }
         }
      }

      //============================================================
      // <T>改变边界内容。</T>
      //
      // @param cx 横向移动位置
      // @param cy 纵向移动位置
      // @param width 宽度
      // @param height 高度
      //============================================================
      public void BoundsSet(int x, int y, int width, int height) {
         foreach(FUiControl control in this) {
            FUiControlDesigner designer = control.Designer;
            if(designer != null) {
               designer.BoundsLocation(x, y);
               designer.BoundsSize(width, height);
            }
         }
      }

      //============================================================
      // <T>设计对齐处理。</T>
      //
      // @param alignCd 对齐方式
      // @param step 间隔
      //============================================================
      public void DesignAlign(int alignCd, int step) {
         // 检查变量
         if(!HasFocusControl()) {
            return;
         }
         if(!HasSelectControl()) {
            return;
         }
         // 获得焦点对象
         int left = _focusControl.Location.X;
         int right = _focusControl.Location.X + _focusControl.Size.Width;
         int hmiddle = (left + right) / 2;
         int top = _focusControl.Location.Y;
         int bottom = _focusControl.Location.Y + _focusControl.Size.Height;
         int vmiddle = (top + bottom) / 2;
         // 控件集合处理
         int count = Count;
         FObjects<FUiControl> controls = new FObjects<FUiControl>();
         controls.Assign(this);
         if(alignCd == EUiDesignAlign.HorizontalAvg) {
            if (count > 3) {
               controls.Sort(new FUiControlLocationComparer(EUiControlLocation.X, true));
               int firstX = controls.First.CenterX;
               int lastX = controls.Last.CenterX;
               int stepX = (lastX - firstX) / (count - 1);
               for (int n = 0; n < count; n++) {
                  FUiControl control = controls.Get(n);
                  control.CenterX = firstX + stepX * n;
               }
            }
         } else if (alignCd == EUiDesignAlign.HorizontalAvgSize) {
            if (count > 3) {
               controls.Sort(new FUiControlLocationComparer(EUiControlLocation.X, true));
               int x = controls.First.Location.X;
               for (int n = 0; n < count; n++) {
                  FUiControl control = controls.Get(n);
                  control.Location.X = x;
                  x += control.Size.Width + step;
               }
            }
         } else if (alignCd == EUiDesignAlign.VerticalAvg) {
            if (count > 3) {
               controls.Sort(new FUiControlLocationComparer(EUiControlLocation.Y, true));
               int firstY = controls.First.CenterY;
               int lastY = controls.Last.CenterY;
               int stepY = (lastY - firstY) / (count - 1);
               for (int n = 0; n < count; n++) {
                  FUiControl control = controls.Get(n);
                  control.CenterY = firstY + stepY * n;
               }
            }
         } else if (alignCd == EUiDesignAlign.VerticalAvgSize) {
            if (count > 3) {
               controls.Sort(new FUiControlLocationComparer(EUiControlLocation.Y, true));
               int y = controls.First.Location.Y;
               for (int n = 0; n < count; n++) {
                  FUiControl control = controls.Get(n);
                  control.Location.Y = y;
                  y += control.Size.Height + step;
               }
            }
         } else {
            // 处理其他情况
            foreach (FUiControl control in this) {
               // 检查控件
               if (control == _focusControl) {
                  continue;
               }
               // 对齐处理
               switch (alignCd) {
                  case EUiDesignAlign.Center:
                     control.Location.X = hmiddle - (control.Size.Width >> 1);
                     control.Location.Y = vmiddle - (control.Size.Height >> 1);
                     break;
                  case EUiDesignAlign.Left:
                     control.Location.X = left;
                     break;
                  case EUiDesignAlign.HorizontalMiddle:
                     control.Location.X = hmiddle - (control.Size.Width >> 1);
                     break;
                  case EUiDesignAlign.Right:
                     control.Location.X = right - control.Size.Width;
                     break;
                  case EUiDesignAlign.Top:
                     control.Location.Y = top;
                     break;
                  case EUiDesignAlign.VerticalMiddle:
                     control.Location.Y = vmiddle - (control.Size.Height >> 1);
                     break;
                  case EUiDesignAlign.Bottom:
                     control.Location.Y = bottom - control.Size.Height;
                     break;
               }
            }
         }
      }

      //============================================================
      // <T>设计移动处理。</T>
      //
      // @param moveCd 移动方式
      // @param step 移动大小
      //============================================================
      public void DesignMove(int moveCd, int step) {
         // 检查变量
         if(!HasFocusControl()) {
            return;
         }
         if(!HasSelectControl()) {
            return;
         }
         // 控件集合处理
         foreach(FUiControl control in this) {
            // 对齐处理
            switch(moveCd) {
               case EUiDesignMove.Left:
                  control.Location.X -= step;
                  break;
               case EUiDesignMove.Up:
                  control.Location.Y -= step;
                  break;
               case EUiDesignMove.Right:
                  control.Location.X += step;
                  break;
               case EUiDesignMove.Down:
                  control.Location.Y += step;
                  break;
            }
         }
      }

      //============================================================
      // <T>清空所有焦点控件。</T>
      //============================================================
      public void ClearFocus() {
         foreach(FUiControl control in this) {
            FUiControlDesigner designer = control.Designer;
            if(designer != null) {
               designer.DesignFocus = false;
            }
         }
      }

      //============================================================
      // <T>清空所有选中控件。</T>
      //============================================================
      public void ClearSelect() {
         foreach(FUiControl control in this) {
            FUiControlDesigner designer = control.Designer;
            if(designer != null) {
               designer.DesignSelect = false;
            }
         }
      }

      //============================================================
      // <T>清空所有选中控件。</T>
      //============================================================
      public override void Clear() {
         // 清空焦点
         if(_focusControl != null) {
            _focusControl.Designer.DesignFocus = false;
            _focusControl.Designer.DesignSelect = false;
            _focusControl = null;
         }
         // 清空设置
         foreach(FUiControl control in this) {
            FUiControlDesigner designer = control.Designer;
            if(designer != null) {
               designer.DesignFocus = false;
               designer.DesignSelect = false;
            }
         }
         // 清空集合
         base.Clear();
      }
   }
}