using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Controls;
using MO.Design2d.Frame.Common;

namespace MO.Design2d.Frame.Controls
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FUiScrollBar : FUiControl
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
      public FUiScrollBar(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      public FRcScrollBar ScrollBarResource {
         get { return _resource as FRcScrollBar; }
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void OnSetup(SUiSetupArgs args) {
         base.OnSetup(args);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);

      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }

   }
}
