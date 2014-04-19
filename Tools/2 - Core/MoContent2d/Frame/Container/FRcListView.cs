using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Container
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FRcListView : FRcControl
   {
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcListView(FRcFrameConsole console = null)
         : base(console) {
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.ListView);
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public override void LoadStyleProperty() {
         // 加载父样式属性
         base.LoadStyleProperty();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.list.view");
         if (style != null) {
         }
      }

      //============================================================
      // <T>加载样式内容。</T>
      //============================================================
      public override void LoadStyleValue() {
         // 加载父样式内容
         base.LoadStyleValue();
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
         // 保存处理
         base.OnSaveConfig(xconfig);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
