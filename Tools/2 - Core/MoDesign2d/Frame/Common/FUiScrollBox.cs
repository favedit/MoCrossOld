using MO.Content2d.Frame.Common;
using System.ComponentModel;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>滚动框容器。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiScrollBox : FUiContainer
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
      public FUiScrollBox(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      [Browsable(false)]
      public FRcScrollBox ScrollBoxResource {
         get { return _resource as FRcScrollBox; }
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
   }
}
