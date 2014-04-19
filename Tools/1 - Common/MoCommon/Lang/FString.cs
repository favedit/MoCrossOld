using System;
using System.Text;

namespace MO.Common.Lang
{
   //============================================================
   // <T>字符串对象。</T>
   //============================================================
   public class FString : FTypes<char>
   {
      // 换行符
      public static char ENTER_CHR = '\n';

      //============================================================
      // <T>追加一个字符串。</T>
      //
      // @param self 自对象
      // @param value 字符串
      //============================================================
      public static FString operator +(FString self, string value) {
         if(null != value) {
            self.Append(value);
         }
         return self;
      }

      //============================================================
      // <T>追加一个对象。</T>
      //
      // @param self 自对象
      // @param value 追加对象
      //============================================================
      public static FString operator +(FString self, object value) {
         if(null != value) {
            self.Append(value.ToString());
         }
         return self;
      }

      //============================================================
      // <T>一个字符串对象转换为字符串。</T>
      //
      // @param value 字符串对象
      // @return 字符串对象
      //============================================================
      public static implicit operator string(FString value) {
         return value.ToString();
      }

      //============================================================
      // <T>一个字符串转换为字符串对象。</T>
      //
      // @param value 字符串
      // @return 字符串对象
      //============================================================
      public static explicit operator FString(string value) {
         return new FString(value);
      }

      //============================================================
      // <T>构造字符串对象。</T>
      //============================================================
      public FString() {
      }

      //============================================================
      // <T>构造字符串对象。</T>
      //
      // @param chars 字符数组
      //============================================================
      public FString(char[] chars) {
         Append(chars);
      }

      //============================================================
      // <T>构造字符串对象。</T>
      //
      // @param value 字符串
      //============================================================
      public FString(string value) {
         Append(value);
      }

      //============================================================
      // <T>构造字符串对象。</T>
      //
      // @param bytes 字节数组
      // @param encoding 字节编码
      //============================================================
      public FString(byte[] bytes, Encoding encoding) {
         Append(encoding.GetString(bytes));
      }

      //============================================================
      // <T>判断一个字符串是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsBlank() {
         return (0 == ToString().Trim().Length);
      }

      //============================================================
      // <T>接收一个字符串到尾部。</T>
      //
      // @param value 字符串
      //============================================================
      public void Assign(string value) {
         Clear();
         Append(value);
      }

      //============================================================
      // <T>追加一个字符串到尾部。</T>
      //
      // @param value 字符串
      //============================================================
      public void Append(string value) {
         if(null != value) {
            int length = value.Length;
            if(length > 0) {
               char[] chars = value.ToCharArray();
               base.Append(chars, 0, length);
            }
         }
      }

      //============================================================
      // <T>追加多个字符串到尾部。</T>
      //
      // @param values 字符串列表
      //============================================================
      public void Append(params string[] values) {
         if(null != values) {
            foreach(string value in values) {
               if(null != value) {
                  int length = value.Length;
                  if(length > 0) {
                     char[] chars = value.ToCharArray();
                     base.Append(chars, 0, length);
                  }
               }
            }
         }
      }

      //============================================================
      // <T>追加多个对象到尾部。</T>
      //
      // @param values 对象列表
      //============================================================
      public void Append(params object[] values) {
         if(null != values) {
            foreach(object value in values) {
               if(null != value) {
                  if(value.GetType() == typeof(char)) {
                     base.Add((char)value);
                  } else {
                     Append(value.ToString());
                  }
               }
            }
         }
      }

      //============================================================
      // <T>重复追加一个字符到尾部。</T>
      //
      // @param value 字符
      // @param repeat 重复次数
      //============================================================
      public void AppendRepeat(char value, int repeat) {
         for(int n = 0; n < repeat; n++) {
            base.Add(value);
         }
      }

      //============================================================
      // <T>重复追加一个字符串到尾部。</T>
      //
      // @param value 字符串
      // @param repeat 重复次数
      //============================================================
      public void AppendRepeat(string value, int repeat) {
         if(null != value) {
            int length = value.Length;
            if(length > 0) {
               char[] chars = value.ToCharArray();
               for(int n = 0; n < repeat; n++) {
                  base.Append(chars, 0, length);
               }
            }
         }
      }

      //============================================================
      // <T>增加一个属性对。</T>
      //
      // @param name 名称
      // @param value 内容
      // @param hasNext 是否存在下一个
      //============================================================
      public void AppendProperty(string name, object value, bool hasNext) {
         Append(name);
         Append('=');
         if(null != value) {
            Append(value.ToString());
         }
         if(hasNext) {
            Append(", ");
         }
      }

      //============================================================
      // <T>增加一个属性对。</T>
      //
      // @param name 名称
      // @param value 内容
      //============================================================
      public void AppendProperty(string name, string value) {
         AppendProperty(name, value, false);
      }

      //============================================================
      // <T>追加一个格式化字符串到尾部。</T>
      //
      // @param value 格式化字符串
      // @param parameters 参数列表
      //============================================================
      public void AppendFormat(string value, params object[] parameters) {
         Append(String.Format(value, parameters));
      }

      //============================================================
      // <T>增加一空行。</T>
      //
      // @param name 名称
      // @param value 内容
      //============================================================
      public void AppendLine() {
         base.Add(ENTER_CHR);
      }

      //============================================================
      // <T>增加一行字符串。</T>
      //
      // @param value 内容
      //============================================================
      public void AppendLine(string value) {
         if(null != value) {
            int length = value.Length;
            if(length > 0) {
               char[] chars = value.ToCharArray();
               base.Append(chars, 0, length);
            }
         }
         base.Add(ENTER_CHR);
      }

      //============================================================
      // <T>增加多行字符串列表。</T>
      //
      // @param values 字符串列表
      //============================================================
      public void AppendLine(params string[] values) {
         if(values != null) {
            foreach(string value in values) {
               if(null != value) {
                  base.Append(value.ToCharArray());
                  base.Add(ENTER_CHR);
               }
            }
         }
      }

      //============================================================
      // <T>增加多行对象列表。</T>
      //
      // @param values 对象列表
      //============================================================
      public void AppendLine(params object[] values) {
         if(values != null) {
            foreach(object value in values) {
               if(null != value) {
                  base.Append(value.ToString().ToCharArray());
                  base.Add(ENTER_CHR);
               }
            }
         }
      }

      //============================================================
      // <T>刷新字符串到外部。</T>
      //
      // @return 类型数组
      //============================================================
      public new string Flush() {
         string result = new String(_memory, 0, _length);
         _length = 0;
         return result;
      }

      //============================================================
      // <T>转换为默认编码的字节数组。</T>
      //
      // @return 字节数组
      //============================================================
      public byte[] ToBytes() {
         return Encoding.UTF8.GetBytes(_memory, 0, _length);
      }

      //============================================================
      // <T>转换为指定编码的字节数组。</T>
      //
      // @param encoding 编码
      // @return 字节数组
      //============================================================
      public byte[] ToBytes(Encoding encoding) {
         return encoding.GetBytes(_memory, 0, _length);
      }

      //============================================================
      // <T>替换源字符串到目标字符串。</T>
      //
      // @param source 源字符串
      // @param target 目标字符串
      //============================================================
      public void Replace(string source, string target) {
         string value = new String(_memory, 0, _length);
         string result = value.Replace(source, target);
         Assign(result);
      }

      //============================================================
      // <T>转换为字符串。</T>
      //
      // @return 字符串
      //============================================================
      public override string ToString() {
         return new String(_memory, 0, _length);
      }
   }
}
