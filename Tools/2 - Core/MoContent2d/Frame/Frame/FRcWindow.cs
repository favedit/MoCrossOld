using MO.Common.Content;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Frame
{
   //============================================================
   // <T>窗口定义。</T>
   //============================================================
   public class FRcWindow : FRcFrame
   {
      // 标题区高度
      protected int _titleHeight = 24;

      //============================================================
      // <T>构造窗口定义。</T>
      //============================================================
      public FRcWindow(FRcFrameConsole console = null)
         : base(console) {
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.Window);
      }

      //============================================================
      // <T>获得或设置标题区高度。</T>
      //============================================================
      public int TitleHeight {
         get { return _titleHeight; }
         set { _titleHeight = value; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
      }
   }
}
