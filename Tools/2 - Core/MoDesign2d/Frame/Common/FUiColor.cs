using System.ComponentModel;
using System.Drawing;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Direct2d.Draw;
using MO.Common.Content;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面颜色。</T>
   //============================================================
   [TypeConverter(typeof(FUiColorConverter))]
   public class FUiColor : SColor
   {
      // 色刷
      public FDxBrush brush;

      //============================================================
      // <T>构造颜色。</T>
      //============================================================
      public FUiColor(byte r = 0, byte g = 0, byte b = 0, byte a = 0, bool valid = false) {
         Valid = valid;
         A = a;
         R = r;
         G = g;
         B = b;
      }

      //============================================================
      // <T>判断是否含有色刷。</T>
      //
      // @return 是否含有色刷
      //============================================================
      public bool HasBrush() {
         return (brush != null);
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public Color Color {
         get { return Color.FromArgb(A, R, G, B); }
         set { Set(value.R, value.G, value.B, value.A); }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         // 加载有效性
         bool containsValid = xconfig.Contains(name + "_valid");
         if (containsValid) {
            _valid = xconfig.GetBoolean(name + "_valid");
         } else {
            _valid = false;
         }
         // 加载颜色
         if (xconfig.Contains(name)) {
            string value = xconfig.Get(name);
            if (!containsValid) {
               _valid = true;
            }
            Parse(value);
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         // 保存有效性
         xconfig.SetNvl(name + "_valid", _valid);
         // 保存颜色
         if (!IsEmpty()) {
            xconfig.SetNvl(name, ToString16());
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteUint8(A);
         output.WriteUint8(R);
         output.WriteUint8(G);
         output.WriteUint8(B);
      }
   }
}
