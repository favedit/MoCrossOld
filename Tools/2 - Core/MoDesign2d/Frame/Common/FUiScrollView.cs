using MO.Common.Geom;
using MO.Content2d.Frame.Common;
using System.ComponentModel;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiScrollView : FUiScrollBox
   {
      // 字段声明
      protected int _designItemCount;

      // 字段声明
      protected int _horizontalCount;

      // 字段声明
      protected int _horizontalSpace;

      // 字段声明
      protected int _verticalCount;

      // 字段声明
      protected int _verticalSpace;

      // 字段声明
      protected int _initialCount;
      
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiScrollView(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      [Browsable(false)]
      public FRcScrollView ScrollViewResource {
         get { return _resource as FRcScrollView; }
      }

      //============================================================
      // <T>获得或设置设计项目个数字段(DesignItemCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("设计项目个数")]
      public int DesignItemCount {
         get { return _designItemCount; }
         set { _designItemCount = value; }
      }

      //============================================================
      // <T>获得或设置横向个数字段(HorizontalCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("横向个数")]
      public int HorizontalCount {
         get { return _horizontalCount; }
         set { _horizontalCount = value; }
      }

      //============================================================
      // <T>获得或设置横向间隔字段(HorizontalSpace)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("横向间隔")]
      public int HorizontalSpace {
         get { return _horizontalSpace; }
         set { _horizontalSpace = value; }
      }

      //============================================================
      // <T>获得或设置纵向个数字段(VerticalCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("纵向个数")]
      public int VerticalCount {
         get { return _verticalCount; }
         set { _verticalCount = value; }
      }

      //============================================================
      // <T>获得或设置纵向间隔字段(VerticalSpace)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("纵向间隔")]
      public int VerticalSpace {
         get { return _verticalSpace; }
         set { _verticalSpace = value; }
      }

      //============================================================
      // <T>获得或设置初始个数字段(InitialCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("初始个数")]
      public int InitialCount {
         get { return _initialCount; }
         set { _initialCount = value; }
      }

      //============================================================
      // <T>获得激活的项目。</T>
      //
      // @return 激活的项目
      //============================================================
      public FUiScrollItem ActiveItem() {
         if(_components != null) {
               foreach (FUiComponent componment in _components) {
                  FUiScrollItem item = componment as FUiScrollItem;
                  if (item != null) {
                     return item;
                  }
               }
         }
         return null;
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args){
         base.OnSetup(args);
      }

      //============================================================
      // <T>测试处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void Test(SUiTestArgs args) {
         // 检查有效性
         if (!ComponentResource.OptionValid) {
            return;
         }
         // 开始测试
         bool result = OnTest(args);
         // 子控件测试
         if (result) {
            FUiScrollItem item = ActiveItem();
            if (item != null) {
               // 测试分页
               item.Test(args);
               // 绘制节点
               if (_components != null) {
                  foreach (FUiComponent componment in _components) {
                     FUiControl control = componment as FUiControl;
                     if (control == null) {
                           continue;
                     }
                     //if (control is FUiBaseListItem) {
                     //   continue;
                     //}
                     control.Test(args);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>开始绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawBegin(SUiDrawArgs args){
         base.OnDrawBegin(args);
         // 绘制背景资源
         //DrawResource(_groundResource);
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawAfter(SUiDrawArgs args){
         base.OnDrawAfter(args);
      }

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void Draw(SUiDrawArgs args) {
         // 检查有效性
         if (!ComponentResource.OptionValid) {
            return;
         }
         // 设置数据
         bool visible = TestVisible();
         SIntPoint2 position = CalculatePosition();
         args.Position.Assign(position);
         // 计算数据
         _designLocation.Assign(CalculateDisplayPosition());
         _designSize.Assign(CalculateDisplaySize());
         // 开始层处理
         _deviceLayer.Begin(_designLocation.X, _designLocation.Y, _designSize.Width, _designSize.Height);
         // 检查可见性
         if (visible) {
            OnDrawBegin(args);
         }
         //............................................................
         // 子控件绘制
         if (_components != null) {
            // 绘制分页
            FUiScrollItem item = ActiveItem();
            if(item != null) {
               ERcSpread spreadCd = ScrollViewResource.SpreadCd;
               // 绘制多个
               int itemWidth = item.Size.Width + _horizontalSpace;
               int itemHeight = item.Size.Height + _verticalSpace;
               if ((spreadCd == ERcSpread.Horizontal) && (_verticalCount > 0)) {
                  for (int n = 0; n < _designItemCount; n++) {
                     int cx = n % _verticalCount;
                     int cy = (int)(n / _verticalCount);
                     item.Location.Set(itemWidth * cx, itemHeight * cy);
                     item.Draw(args);
                  }
               } else if ((spreadCd == ERcSpread.Vertical) && (_horizontalCount > 0)) {
                  for (int n = 0; n < _designItemCount; n++) {
                     int cx = n % _horizontalCount;
                     int cy = (int)(n / _horizontalCount);
                     item.Location.Set(itemWidth * cx, itemHeight * cy);
                     item.Draw(args);
                  }
               }
               // 恢复设置
               item.Location.Set(0, 0);
            }
            // 绘制节点
            foreach (FUiComponent componment in _components) {
               FUiControl control = componment as FUiControl;
               if (control == null) {
                  continue;
               }
               if (control is FUiScrollItem) {
                  continue;
               }
               control.Draw(args);
            }
         }
         //............................................................
         // 结束绘制
         if (visible) {
            _context.Context.TransformLocation(_designLocation.X, _designLocation.Y);
            OnDrawAfter(args);
         }
         // 结束层处理
         _deviceLayer.End();
      }
   }
}
