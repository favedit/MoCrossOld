using MO.Content2d.Frame.Common;
using System.ComponentModel;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面容器。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiContainer : FUiControl
   {
      //============================================================
      // <T>构造界面容器。</T>
      //
      // @param console 控制台
      //============================================================
      public FUiContainer(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      [Browsable(false)]
      public FRcContainer ContainerResource {
         get { return _resource as FRcContainer; }
      }
   }
}
