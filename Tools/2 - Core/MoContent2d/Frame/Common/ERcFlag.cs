namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面标志</T>
   //============================================================
   public enum ERcFlag : int
   {
      // 支持关联
      Link = 0x00000001,

      // 有效性
      Enable = 0x00000002,

      // 可见性
      Visible = 0x00000004,

      // 支持内空白
      Margin = 0x00000008,

      // 支持外空白
      Padding = 0x00000010,

      // 显示边框
      BorderInner = 0x00000020,

      // 显示边框
      BorderOuter = 0x00000040,

      // 支持属性
      Property = 0x00000080,

      // 支持数据
      Data = 0x00000100,

      // 支持边框层
      LayerBorder = 0x00001000,

      // 支持前景层
      LayerFore = 0x00002000,

      // 支持背景层
      LayerGround = 0x00004000,

      // 支持后景层
      LayerBack = 0x00008000,

      // 支持控件标签
      ControlLabel = 0x00010000,

      // 支持控件数字
      ControlNumber = 0x00020000,

      // 支持控件多行
      ControlLines = 0x00040000,

      // 支持控件页面
      ControlHtml = 0x00080000,

      // 支持字体
      ControlFont = 0x00100000,

      // 支持图标
      ControlIcon = 0x00200000,
   }
}
