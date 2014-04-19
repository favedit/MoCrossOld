using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Common.Collection
{
   //============================================================
   // <T>属性集合。</T>
   //============================================================
   public class FProperties : FObjects<FProperty>
   {
      //============================================================
      // <T>构造属性集合。</T>
      //============================================================
      public FProperties() { 
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt8((sbyte)_count);
         for(int n = 0; n < _count; n++) {
            FProperty property = _items[n];
            property.Serialize(output);
         }
      }

      //============================================================
      // <T>获得显示内容。</T>
      //
      // @return 内容
      //============================================================
      public string FormatDisplay() {
         FString source = new FString();
         for(int n = 0; n < _count; n++) {
            FProperty property = _items[n];
            if(n > 0) {
               source.Append("\r\n");
            }
            source.Append(property.Name + "=" + property.Value);
         }
         return source.ToString();
      }

      //============================================================
      // <T>解析显示内容。</T>
      //
      // @param pack 内容
      //============================================================
      public void ParseDisplay(string pack) {
         // 清空处理
         Clear();
         // 分解行号
         string[] lines = pack.Split('\n');
         foreach(string line in lines) {
            // 检查分行
            string itemsLine = line.Trim();
            if(RString.IsEmpty(itemsLine)) {
               continue;
            }
            // 分解项目
            string[] items = RString.SplitTwo(itemsLine, '=');
            FProperty property = new FProperty();
            property.Name = items[0];
            property.Value = items[1];
            Push(property);
         }
      }

      //============================================================
      // <T>获得显示内容。</T>
      //
      // @return 内容
      //============================================================
      public string Pack() {
         FString source = new FString();
         for(int n = 0; n < _count; n++) {
            FProperty property = _items[n];
            if(n > 0){
               source.Append(";");
            }
            source.Append(property.Name + "=" + property.Value);
         }
         return source.ToString();
      }

      //============================================================
      // <T>解析显示内容。</T>
      //
      // @param pack 内容
      //============================================================
      public void Parse(string pack) {
         // 清空处理
         Clear();
         // 分解行号
         string[] lines = pack.Split(';');
         foreach(string line in lines) {
            // 检查分行
            string itemsLine = line.Trim();
            if(RString.IsEmpty(itemsLine)) {
               continue;
            }
            // 分解项目
            string[] items = RString.SplitTwo(itemsLine, '=');
            FProperty property = new FProperty();
            property.Name = items[0];
            property.Value = items[1];
            Push(property);
         }
      }

      //============================================================
      // <T>获得字符串。</T>
      //
      // @return 字符串
      //============================================================
      public override string ToString() {
         return Pack();
      }
   }
}
