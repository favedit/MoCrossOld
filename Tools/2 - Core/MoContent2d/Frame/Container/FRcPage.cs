using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Container
{
   //============================================================
   // <T>页控件定义。</T>
   //============================================================
   public class FRcPage : FRcContainer
   {
      // 设置是否默认选中
      protected bool _optionDefault;

      // 背景资源
      protected FRcPicture _groundResource = new FRcPicture();

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcPage(FRcFrameConsole console = null)
         : base(console) {
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.Page);
      }

      //============================================================
      // <T>获得或设置是否默认选中。</T>
      //============================================================
      public bool OptionDefault {
         get { return _optionDefault; }
         set { _optionDefault = value; }
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
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         _optionDefault = xconfig.GetBoolean("option_default", _optionDefault);
         _groundResource.LoadConfig(xconfig, "ground");
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         xconfig.SetNvl("option_default", _optionDefault);
         _groundResource.SaveConfig(xconfig, "ground");
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
         // 存储位置
         output.WriteBool(_optionDefault);
      }
   }
}
