using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Controls
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FRcCheck : FRcControl
   {
      // 数据真值
      protected string _dataTrue = RBool.TrueString;

      // 数据假值
      protected string _dataFalse = RBool.FalseString;

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcCheck(FRcFrameConsole console = null)
         : base(console) {
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.Check);
      }

      //============================================================
      // <T>获得或设置数据真值。</T>
      //============================================================
      public string DataTrue {
         get { return _dataTrue; }
         set { _dataTrue = value; }
      }

      //============================================================
      // <T>获得或设置数据假值。</T>
      //============================================================
      public string DataFalse {
         get { return _dataFalse; }
         set { _dataFalse = value; }
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public override void LoadStyleProperty() {
         // 加载父样式属性
         base.LoadStyleProperty();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.check");
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
         // 获得属性
         _dataTrue = xconfig.Get("data_true", _dataTrue);
         _dataFalse = xconfig.Get("data_false", _dataFalse);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         // 保存处理
         base.OnSaveConfig(xconfig);
         // 保存属性
         xconfig.SetNvl("data_true", _dataTrue);
         xconfig.SetNvl("data_false", _dataFalse);
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
