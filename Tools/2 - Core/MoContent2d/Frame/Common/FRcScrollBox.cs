using System.ComponentModel;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>滚动框容器。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FRcScrollBox : FRcContainer
   {
      // 设置自动卷轴
      protected bool _optionScrollAuto = true;

      // 卷动样式
      protected ERcScrollStyle _scrollStyleCd = ERcScrollStyle.None;

      //// 设置是否有横向卷轴
      //protected bool _optionScrollHorizontal = false;

      //// 设置是否有纵向卷轴
      //protected bool _optionScrollVertical = false;

      // 横向卷轴停靠类型
      protected ERcHorizontalDock _horizontalDockCd = ERcHorizontalDock.Bottom;

      // 纵向卷轴停靠类型
      protected ERcVerticalDock _verticalDockCd = ERcVerticalDock.Right;

      //============================================================
      // <T>构造滚动框容器。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcScrollBox(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置自动卷轴。</T>
      //============================================================
      [Category("2-配置信息")]
      [Browsable(true)]
      [DefaultValue(true)]
      [Description("是否支持自动卷轴。")]
      public bool OptionScrollAuto {
         get { return _optionScrollAuto; }
         set { _optionScrollAuto = value; }
      }

      //============================================================
      // <T>获得或设置卷动样式。</T>
      //============================================================
      [Category("2-配置信息")]
      [Browsable(true)]
      [Description("卷动样式。")]
      public ERcScrollStyle ScrollStyleCd {
         get { return _scrollStyleCd; }
         set { _scrollStyleCd = value; }
      }

      ////============================================================
      //// <T>获得或设置是否有横向卷轴。</T>
      ////============================================================
      //[Category("2-配置信息")]
      //[Browsable(true)]
      //[DefaultValue(false)]
      //[Description("是否支持横向卷轴。")]
      //public bool OptionScrollHorizontal {
      //   get { return _optionScrollHorizontal; }
      //   set { _optionScrollHorizontal = value; }
      //}

      ////============================================================
      //// <T>获得或设置是否有纵向卷轴。</T>
      ////============================================================
      //[Category("2-配置信息")]
      //[Browsable(true)]
      //[DefaultValue(false)]
      //[Description("是否支持纵向卷轴。")]
      //public bool OptionScrollVertical {
      //   get { return _optionScrollVertical; }
      //   set { _optionScrollVertical = value; }
      //}

      //============================================================
      // <T>获得或设置横向卷轴停靠类型。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [DefaultValue(ERcHorizontalDock.Bottom)]
      [Description("横向卷轴停靠类型。")]
      public ERcHorizontalDock HorizontalDockCd {
         get { return _horizontalDockCd; }
         set { _horizontalDockCd = value; }
      }

      //============================================================
      // <T>获得或设置纵向卷轴停靠类型。</T>
      //============================================================
      [Category("3-控件信息")]
      [Browsable(true)]
      [DefaultValue(ERcVerticalDock.Right)]
      [Description("纵向卷轴停靠类型。")]
      public ERcVerticalDock VerticalDockCd {
         get { return _verticalDockCd; }
         set { _verticalDockCd = value; }
      }

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 加载配置
         _optionScrollAuto = xconfig.GetBoolean("option_scroll_auto", _optionScrollAuto);
         //_optionScrollHorizontal = xconfig.GetBoolean("option_scroll_horizontal", _optionScrollHorizontal);
         //_optionScrollVertical = xconfig.GetBoolean("option_scroll_vertical", _optionScrollVertical);
         // 加载类型
         if (xconfig.Contains("scroll_style_cd")) {
            _scrollStyleCd = (ERcScrollStyle)REnum.ToValue(typeof(ERcScrollStyle), xconfig.Get("scroll_style_cd"));
         }
         if(xconfig.Contains("horizontal_dock")) {
            _horizontalDockCd = (ERcHorizontalDock)REnum.ToValue(typeof(ERcHorizontalDock), xconfig.Get("horizontal_dock"));
         }
         if(xconfig.Contains("vertical_dock")) {
            _verticalDockCd = (ERcVerticalDock)REnum.ToValue(typeof(ERcVerticalDock), xconfig.Get("vertical_dock"));
         }
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         // 存储配置
         xconfig.SetNvl("option_scroll_auto", _optionScrollAuto);
         //xconfig.SetNvl("option_scroll_horizontal", _optionScrollHorizontal);
         //xconfig.SetNvl("option_scroll_vertical", _optionScrollVertical);
         // 保存类型
         xconfig.SetNvl("scroll_style_cd", REnum.ToString(typeof(ERcScrollStyle), _scrollStyleCd));
         xconfig.SetNvl("horizontal_dock", REnum.ToString(typeof(ERcHorizontalDock), _horizontalDockCd));
         xconfig.SetNvl("vertical_dock", REnum.ToString(typeof(ERcVerticalDock), _verticalDockCd));
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
         // 存储位置
         output.WriteBool(_optionScrollAuto);
         //output.WriteBool(_optionScrollHorizontal);
         //output.WriteBool(_optionScrollVertical);
         // 存储类型
         output.WriteInt8((sbyte)_scrollStyleCd);
         output.WriteInt8((sbyte)_horizontalDockCd);
         output.WriteInt8((sbyte)_verticalDockCd);
      }
   }
}
