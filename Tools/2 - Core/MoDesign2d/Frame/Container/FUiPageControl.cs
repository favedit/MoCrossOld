using MO.Common.Geom;
using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Container;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Container
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiPageControl : FUiContainer
   {
      // 激活分页
      protected FUiPage _activePage;

      // 背景资源
      protected FUiPicture _groundResource = new FUiPicture();

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FUiPageControl(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcPageControl PageControlResource {
         get { return _resource as FRcPageControl; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);
         _groundResource.LoadResource(PageControlResource.GroundResource);
         // 选择激活页
         foreach (FUiComponent component in _components) {
            if (component is FUiPage) {
               FUiPage page = component as FUiPage;
               if (_activePage == null) {
                  _activePage = page;
               }
               if (page.PageResource.OptionDefault) {
                  _activePage = page;
               }
            }
         }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args) {
         base.OnSetup(args);
         SetupResource(_groundResource);
      }

      //============================================================
      // <T>激活子节点处理。</T>
      //
      // @param component 组件
      //============================================================
      public override void ActiveChildren(FUiComponent component) {
         base.ActiveChildren(component);
         if (component is FUiPage) {
            _activePage = component as FUiPage;
         }
      }

      //============================================================
      // <T>测试处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void Test(SUiTestArgs args) {
         // 检查有效性
         if (!PageControlResource.OptionValid) {
            return;
         }
         // 开始测试
         bool result = OnTest(args);
         // 子控件测试
         if (result) {
            if (_activePage != null) {
               // 测试分页
               _activePage.Test(args);
               // 绘制节点
               if (_components != null) {
                  foreach (FUiComponent componment in _components) {
                     FUiControl control = componment as FUiControl;
                     if (control == null) {
                        continue;
                     }
                     if (control is FUiPage) {
                        continue;
                     }
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
      public override void OnDrawBegin(SUiDrawArgs args) {
         base.OnDrawBegin(args);
         // 绘制处理
         if (TestVisible()) {
            // 绘制背景资源
            DrawResource(_groundResource);
         }
      }

      //============================================================
      // <T>结束绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnDrawAfter(SUiDrawArgs args) {
         base.OnDrawAfter(args);
      }

      //============================================================
      // <T>绘制处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void Draw(SUiDrawArgs args) {
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
         // 子控件测试
         if (_activePage != null) {
            // 绘制分页
            _activePage.Draw(args);
            // 绘制节点
            if (_components != null) {
               foreach (FUiComponent componment in _components) {
                  FUiControl control = componment as FUiControl;
                  if (control == null) {
                     continue;
                  }
                  if (control is FUiPage) {
                     continue;
                  }
                  control.Draw(args);
               }
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
