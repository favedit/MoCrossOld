using System.ComponentModel;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>控件声明。</T>
   //============================================================
   public class FRcScrollView : FRcScrollBox
   {
      // 字段声明
      protected int _designItemCount;

      // 字段声明
      protected ERcSpread _spreadCd;

      // 字段声明
      protected int _horizontalCount;

      // 字段声明
      protected int _horizontalSpace;

      // 字段声明
      protected int _verticalCount;

      // 字段声明
      protected int _verticalSpace;

      // 字段声明
      protected int _initialCount;
      
      //============================================================
      // <T>构造控件。</T>
      //============================================================
      public FRcScrollView(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置设计项目个数字段(DesignItemCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("设计项目个数")]
      public int DesignItemCount {
         get { return _designItemCount; }
         set { _designItemCount = value; }
      }

      //============================================================
      // <T>获得或设置项目展开方式字段(SpreadCd)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue(ERcSpread.None)]
      [Description("项目展开方式")]
      public ERcSpread SpreadCd {
         get { return _spreadCd; }
         set { _spreadCd = value; }
      }

      //============================================================
      // <T>获得或设置横向个数字段(HorizontalCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("横向个数")]
      public int HorizontalCount {
         get { return _horizontalCount; }
         set { _horizontalCount = value; }
      }

      //============================================================
      // <T>获得或设置横向间隔字段(HorizontalSpace)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("横向间隔")]
      public int HorizontalSpace {
         get { return _horizontalSpace; }
         set { _horizontalSpace = value; }
      }

      //============================================================
      // <T>获得或设置纵向个数字段(VerticalCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("纵向个数")]
      public int VerticalCount {
         get { return _verticalCount; }
         set { _verticalCount = value; }
      }

      //============================================================
      // <T>获得或设置纵向间隔字段(VerticalSpace)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("纵向间隔")]
      public int VerticalSpace {
         get { return _verticalSpace; }
         set { _verticalSpace = value; }
      }

      //============================================================
      // <T>获得或设置初始个数字段(InitialCount)。</T>
      //============================================================
      [Category("4-控件属性")]
      [Browsable(true)]
      [DefaultValue("")]
      [Description("初始个数")]
      public int InitialCount {
         get { return _initialCount; }
         set { _initialCount = value; }
      }

      //============================================================
      // <T>获得激活的项目。</T>
      //
      // @return 激活的项目
      //============================================================
      public FRcScrollItem ActiveItem() {
         if(_components != null) {
               foreach (FRcComponent componment in _components) {
                  FRcScrollItem item = componment as FRcScrollItem;
                  if (item != null) {
                     return item;
                  }
               }
         }
         return null;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         _designItemCount = xconfig.GetInteger("design_item_count", 1);
         _spreadCd = (ERcSpread)REnum.ToValue(typeof(ERcSpread), xconfig.Get("spread_cd", "None"));
         _horizontalCount = xconfig.GetInteger("horizontal_count", 1);
         _horizontalSpace = xconfig.GetInteger("horizontal_space", 0);
         _verticalCount = xconfig.GetInteger("vertical_count", 1);
         _verticalSpace = xconfig.GetInteger("vertical_space", 0);
         _initialCount = xconfig.GetInteger("initial_count", 0);
      }

      //============================================================
      // <T>存储设置信息。</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         xconfig.SetNvl("design_item_count", _designItemCount);
         if (_spreadCd != ERcSpread.None) {
            xconfig.SetNvl("spread_cd", REnum.ToString(typeof(ERcSpread), _spreadCd));
         }
         xconfig.SetNvl("horizontal_count", _horizontalCount);
         xconfig.SetNvl("horizontal_space", _horizontalSpace);
         xconfig.SetNvl("vertical_count", _verticalCount);
         xconfig.SetNvl("vertical_space", _verticalSpace);
         xconfig.SetNvl("initial_count", _initialCount);
      }

      //============================================================
      // <T>测试子组件是否需要序列化。</T>
      //
      // @param component 子组件
      //============================================================
      public override bool TestSerializeComponent(FRcComponent component) {
         if (!component.OptionValid) {
            return false;
         }
         if (component is FRcScrollItem) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
         // 序列化属性
         output.WriteUint8((byte)_spreadCd);
         output.WriteUint8((byte)_horizontalCount);
         output.WriteUint8((byte)_horizontalSpace);
         output.WriteUint8((byte)_verticalCount);
         output.WriteUint8((byte)_verticalSpace);
         output.WriteUint8((byte)_initialCount);
         // 获得项目数量
         int itemCount = 0;
         int total = ComponmentCount;
         for (int n = 0; n < total; n++) {
            FRcComponent component = _components[n];
            if (!component.OptionValid) {
               continue;
            }
            if (component is FRcScrollItem) {
               itemCount++;
            }
         }
         // 写入模版集合
         output.WriteInt16((short)itemCount);
         for (int n = 0; n < total; n++) {
            FRcComponent component = _components[n];
            if (!component.OptionValid) {
               continue;
            }
            if (component is FRcScrollItem) {
               FByteStream stream = new FByteStream();
               component.Serialize(stream);
               output.WriteString(component.Name);
               //output.WriteInt16((short)REnumUiComponent.Parse(_typeName));
               output.WriteString(component.ClassName);
               output.WriteInt32(stream.Length);
               output.WriteBytes(stream.Memory, 0, stream.Length);
            }
         }
      }

      //============================================================
      // <T>从输入流中反序列化内容。</T>
      //
      // @param input 输入流
      //============================================================
      public override void Unserialize(IInput input) {
         base.Unserialize(input);
         // 创建所有子节点
         int count = input.ReadInt16();
         for (int n = 0; n < count; n++) {
            string typeName = input.ReadString();
            FRcComponent component = _console.CreateComponent(typeName);
            component.Unserialize(input);
         }
      }
   }
}
