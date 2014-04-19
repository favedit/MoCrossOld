using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Controls
{
   public class FRcScrollBar : FRcControl
   {
      // 可滚动下限 
      protected float _minimumValue;

      // 可滚动上限
      protected float _maximumValue;

      // 当前值
      protected float _currentValue;

      // 点击滚动箭头，位置变化的幅度
      protected float _changeSmall;

      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcScrollBar(FRcFrameConsole console = null) : base(console){
         _typeName = REnum.ToString<ERcComponent>(ERcComponent.ScrollBar);
         _styleName = "control.scroll.bar";
      }

      //============================================================
      // <T>获得或设置可滚动下限。</T>
      //============================================================
      public float MinimumValue {
         get { return _minimumValue; }
         set { _minimumValue = value; }
      }

      //============================================================
      // <T>获得或设置可滚动上限。</T>
      //============================================================
      public float MaximumValue {
         get { return _maximumValue; }
         set { _maximumValue = value; }
      }

      //============================================================
      // <T>获得或设置当前值。</T>
      //============================================================
      public float CurrentValue {
         get { return _currentValue; }
         set { _currentValue = value; }
      }

      //============================================================
      // <T>获得或设置点击滚动箭头位置变化幅度。</T>
      //============================================================
      public float ChangeSmall {
         get { return _changeSmall; }
         set { _changeSmall = value; }
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public override void LoadStyleProperty() {
         // 加载父样式属性
         base.LoadStyleProperty();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.scroll.bar");
         if (null != style) {
            
         }
      }

      //============================================================
      // <T>加载样式内容。</T>
      //============================================================
      public override void LoadStyleValue() {
         // 加载父样式内容
         base.LoadStyleValue();
         // 加载字体
         
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 读取数据
         _minimumValue = xconfig.GetFloat("minimum_value", _minimumValue);
         _maximumValue = xconfig.GetFloat("maximum_value", _maximumValue);
         _currentValue = xconfig.GetFloat("current_value", _currentValue);
         _changeSmall = xconfig.GetFloat("change_small", _changeSmall);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         // 保存处理
         base.OnSaveConfig(xconfig);
         // 保存数据
         xconfig.SetNvl("minimum_value", _minimumValue);
         xconfig.SetNvl("maximum_value", _maximumValue);
         xconfig.SetNvl("current_value", _currentValue);
         xconfig.SetNvl("change_small", _changeSmall);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);

      }
   }
}
