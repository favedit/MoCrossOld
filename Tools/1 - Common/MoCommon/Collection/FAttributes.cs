using System;
using System.Collections;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>属性集合。</T>
   //============================================================
   public class FAttributes : FDictionary<string>, IAttributes
   {
      //============================================================
      // <T>构造属性集合。</T>
      //============================================================
      public FAttributes() {
      }

      //============================================================
      // <T>构造属性集合。</T>
      //============================================================
      public FAttributes(IAttributes attributes) {
         Append(attributes);
      }

      //============================================================
      // <T>构造属性集合。</T>
      //============================================================
      public void Append(IAttributes attributes) {
         foreach (IStringPair pair in attributes) {
            Set(pair.Name, pair.Value);
         }
      }

      //============================================================
      // <T>获得指定名称的数字内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public int GetAsInt(string name) {
         string value = Get(name);
         return (null != value) ? RInt.Parse(value) : 0;
      }

      //============================================================
      // <T>获得指定名称的数字内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public int GetAsInt(string name, int defaultValue) {
         string value = Get(name);
         return (null != value) ? RInt.Parse(value) : defaultValue;
      }

      //============================================================
      // <T>获得指定名称的浮点数内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public float GetAsFloat(string name) {
         string value = Get(name);
         return (null != value) ? RFloat.Parse(value) : 0;
      }

      //============================================================
      // <T>获得指定名称的浮点数内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public float GetAsFloat(string name, float defaultValue) {
         string value = Get(name);
         return (null != value) ? RFloat.Parse(value) : defaultValue;
      }

      //============================================================
      // <T>根据名称，设置布尔内容。</T>
      //
      // @param name 名称
      // @param value 内容
      // @return 设置内容的位置
      //============================================================
      public void Set(string name, bool value) {
         Set(name, RBool.ToString(value));
      }

      //============================================================
      // <T>根据名称，设置数字内容。</T>
      //
      // @param name 名称
      // @param value 内容
      // @return 设置内容的位置
      //============================================================
      public void Set(string name, int value) {
         Set(name, value.ToString());
      }

      //============================================================
      // <T>根据名称，设置浮点数内容。</T>
      //
      // @param name 名称
      // @param value 内容
      // @return 设置内容的位置
      //============================================================
      public void Set(string name, float value) {
         Set(name, value.ToString());
      }

      //============================================================
      // <T>数据打包。</T>
      //
      // @return 设置内容的位置
      //============================================================
      public string Pack() {
         StringBuilder buffer = new StringBuilder();
         for (int n = 0; n < _count; n++) {
            // value返回的值
            string name = _names[n];
            string value = _values[n];
            if (RString.IsBlank(name)) {
               throw new Exception("Name is invalid.");
            }
            // 得到名称的长度及名称长度所占的位数
            int nameLength = name.Length;
            int nameLLength = nameLength.ToString().Length;
            // 得到值的长度及值长度所占的位数
            string valueString = "0";
            if (!RString.IsBlank(value)) {
               int valueLength = value.Length;
               int valueLLength = valueLength.ToString().Length;
               valueString = valueLLength.ToString() + valueLength.ToString() + value;
            }
            // 接受返回的字符串
            string itemPack = nameLLength.ToString() + nameLength.ToString() + name + valueString;
            // 使用StringBuilder进行字符串追加
            buffer.Append(itemPack);
         }
         return buffer.ToString();

      }

      //============================================================
      // <T>数据解包。</T>
      //
      // @param pack 打包字符串
      // @return 处理结果
      //============================================================
      public bool Unpack(string pack) {
         // 循环开始的截取字符串的起始位置
         int position = 0;
         // 循环开始
         while (position < pack.Length) {
            // 读取name和value的值
            int nameLLength = RInt.Parse(pack.Substring(position, 1));
            int nameLength = RInt.Parse(pack.Substring(1 + position, nameLLength));
            string name = pack.Substring(1 + nameLLength + position, nameLength);
            // 判断下一位是否为空
            if (1 + nameLLength + nameLength + position == pack.Length) {
               throw new Exception("Pack string is invalid.");
            }
            int valueLLength = RInt.Parse(pack.Substring(1 + position + nameLLength + nameLength, 1));
            int valueLength = RInt.Parse(pack.Substring(1 + position + nameLLength + nameLength + 1, valueLLength));
            string value = pack.Substring(1 + position + nameLLength + nameLength + valueLLength + 1, valueLength);
            // 存入截取的数值
            Set(name, value);
            //从新计算下个循环开始的位置
            position = nameLLength + 1 + nameLength + valueLLength + 1 + valueLength + position;
         }
         return (0 != _count);
      }

      //============================================================
      // <T>构造属性集合。</T>
      //============================================================
      public string DumpInfo() {
         FString buffer = new FString();
         int count = _count;
         buffer.Append(count);
         buffer.Append('[');
         for (int n = 0; n < count; n++) {
            if (n > 0) {
               buffer.Append(',');
            }
            buffer.Append(_names[n]);
            buffer.Append('=');
            buffer.Append(_values[n]);
         }
         buffer.Append(']');
         return buffer.ToString();
      }

      #region IEnumerable implements

      //============================================================
      // <T>获得枚举器。</T>
      //============================================================
      public override IEnumerator GetEnumerator() {
         return new FStringPairEnumerator(_names, _values, 0, _count);
      }

      #endregion
   }
}
