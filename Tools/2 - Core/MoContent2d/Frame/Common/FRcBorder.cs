using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Theme;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>边框。</T>
   //============================================================
   [TypeConverter(typeof(FRcBorderConverter))]
   public class FRcBorder : FObject
   {
      //  左边线
      protected FRcLine _left = new FRcLine();

      // 上线条
      protected FRcLine _top = new FRcLine();

      // 右边线
      protected FRcLine _right = new FRcLine();

      // 下线条
      protected FRcLine _bottom = new FRcLine();

      //============================================================
      // <T>构造边框。</T>
      //============================================================
      public FRcBorder() {
      }

      //============================================================
      // <T>判断是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         return (_left.Width == 0) && (_top.Width == 0) && (_right.Width == 0) && (_bottom.Width == 0);
      }

      //============================================================
      // <T>获得或设置左边线。</T>
      //============================================================
      public FRcLine Left {
         get { return _left; }
         set { _left = value; }
      }

      //============================================================
      // <T>获得或设置上线条。</T>
      //============================================================
      public FRcLine Top {
         get { return _top; }
         set { _top = value; }
      }

      //============================================================
      // <T>获得或设置右边线。</T>
      //============================================================
      public FRcLine Right {
         get { return _right; }
         set { _right = value; }
      }

      //============================================================
      // <T>获得或设置下线条。</T>
      //============================================================
      public FRcLine Bottom {
         get { return _bottom; }
         set { _bottom = value; }
      }

      //============================================================
      // <T>加载主题样式属性。</T>
      //
      // @param property 主题样式属性
      //============================================================
      public bool EqualsStyleProperty(FTplThemeStyleProperty property) {
         if (property == null) {
            return false;
         }
         if (_left.ToString() != property.GetString("left")){
            return false;
         }
         if (_top.ToString() != property.GetString("top")){
            return false;
         }
         if (_right.ToString() != property.GetString("right")){
            return false;
         }
         if (_bottom.ToString() != property.GetString("bottom")){
            return false;
         }
         return true;
      }

      //============================================================
      // <T>加载主题样式属性。</T>
      //
      // @param property 主题样式属性
      //============================================================
      public void LoadStyleProperty(FTplThemeStyleProperty property) {
         if (property == null) {
            return;
         }
         _left.Parse(property.GetString("left"));
         _top.Parse(property.GetString("top"));
         _right.Parse(property.GetString("right"));
         _bottom.Parse(property.GetString("bottom"));
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         // 加载左边线
         _left.LoadConfig(xconfig, name + "_left");
         // 加载上边线
         _top.LoadConfig(xconfig, name + "_top");
         // 加载右边线
         _right.LoadConfig(xconfig, name + "_right");
         // 加载下边线
         _bottom.LoadConfig(xconfig, name + "_bottom");
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         // 检查是否为空
         if(IsEmpty()){
            return;
         }
         // 保存左边线
         _left.SaveConfig(xconfig, name + "_left");
         // 保存上边线
         _top.SaveConfig(xconfig, name + "_top");
         // 保存右边线
         _right.SaveConfig(xconfig, name + "_right");
         // 保存下边线
         _bottom.SaveConfig(xconfig, name + "_bottom");
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         _left.Serialize(output);
         _top.Serialize(output);
         _right.Serialize(output);
         _bottom.Serialize(output);
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public bool Parse(string value) {
         if (value == null) {
            return false;
         }
         // 获得左边线
         int start = value.IndexOf('(');
         int end = value.IndexOf(')');
         if ((-1 == start) || (-1 == end)) {
            return false;
         }
         if (!_left.Parse(value.Substring(start + 1, end - start - 1))) {
            return false;
         }
         value = value.Substring(end + 1);
         // 获得上边线
         start = value.IndexOf('(');
         end = value.IndexOf(')');
         if ((-1 == start) || (-1 == end)) {
            return false;
         }
         if (!_top.Parse(value.Substring(start + 1, end - start - 1))) {
            return false;
         }
         value = value.Substring(end + 1);
         // 获得右边线
         start = value.IndexOf('(');
         end = value.IndexOf(')');
         if ((-1 == start) || (-1 == end)) {
            return false;
         }
         if (!_right.Parse(value.Substring(start + 1, end - start - 1))) {
            return false;
         }
         value = value.Substring(end + 1);
         // 获得下边线
         start = value.IndexOf('(');
         end = value.IndexOf(')');
         if ((-1 == start) || (-1 == end)) {
            return false;
         }
         if (!_bottom.Parse(value.Substring(start + 1, end - start - 1))) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         string result = "";
         result += "(" + _left.ToString() + ")";
         result += ",(" + _top.ToString() + ")";
         result += ",(" + _right.ToString() + ")";
         result += ",(" + _bottom.ToString() + ")";
         return result;
      }
   }
}
