using MO.Common.Content;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Container
{
   //============================================================
   // <T>分页控件定义。</T>
   //============================================================
   public class FRcPageControl : FRcContainer
   {
      // 页面
      protected FRcPage _activePage;

      // 背景资源
      protected FRcPicture _groundResource = new FRcPicture();

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcPageControl(FRcFrameConsole console = null)
         : base(console) {
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.PageControl);
      }

      //============================================================
      // <T>获得或设置背景资源。</T>
      //============================================================
      public FRcPicture GroundResource {
         get { return _groundResource; }
         set { _groundResource = value; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig){
         base.OnLoadConfig(xconfig);
         _groundResource.LoadConfig(xconfig, "ground");
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig){
         base.OnSaveConfig(xconfig);
         _groundResource.SaveConfig(xconfig, "ground");
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         // 选择激活页
         foreach(FRcComponent component in _components){
            if (component is FRcPage) {
               FRcPage page = component as FRcPage;
               if(_activePage == null){
                  _activePage = page;
               }
               if (page.OptionDefault) {
                  _activePage = page;
               }
            }
         }
      }
   }
}
