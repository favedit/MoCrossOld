using System;

namespace MO.Common.Lang
{
   //============================================================
   // <T>例外。</T>
   //============================================================
   public class FException : Exception
   {
      // 例外对象
      protected Exception _reason;

      // 消息内容
      protected object _description;

      // 消息参数
      protected object[] _parameters;

      //============================================================
      // <T>构造例外。</T>
      //============================================================
      public FException() {
      }

      //============================================================
      // <T>构造例外。</T>
      //
      // @param description 消息对象
      // @param args 参数集合
      //============================================================
      public FException(object description, params object[] args) {
         Link(null, description, args);
      }

      //============================================================
      // <T>构造例外。</T>
      //
      // @param exception 例外对象
      //============================================================
      public FException(Exception exception) {
         Link(exception, null, null);
      }

      //============================================================
      // <T>构造例外。</T>
      //
      // @param exception 例外对象
      // @param description 消息对象
      // @param args 参数集合
      //============================================================
      public FException(Exception exception, object description, params object[] args) {
         Link(exception, description, args);
      }

      //============================================================
      // <T>关联例外信息。</T>
      //
      // @param exception 例外对象
      // @param description 消息对象
      // @param args 参数集合
      //============================================================
      protected void Link(Exception exception, object description, object[] args) {
         _reason = exception;
         _description = description;
         _parameters = args;
      }

      //============================================================
      // <T>获得例外对象。</T>
      //
      // @return 例外对象
      //============================================================
      public Exception Reason {
         get { return _reason; }
      }

      //============================================================
      // <T>获得消息内容。</T>
      //
      // @return 消息内容
      //============================================================
      public object Description {
         get { return _description; }
      }

      //============================================================
      // <T>获得消息参数。</T>
      //
      // @return 消息参数
      //============================================================
      public object[] Parameters {
         get { return _parameters; }
      }

      //============================================================
      // <T>获得消息描述。</T>
      //
      // @return 消息描述
      //============================================================
      public string Describe {
         get {
            string describe = null;
            if (null != _description) {
               // 获得消息
               Type type = _description.GetType();
               if(type.IsEnum) {
                  IResource resource = RResource.Find(type);
                  describe = resource.FindDisplay(_description.ToString());
               } else {
                  describe = _description.ToString();
               }
               // 格式化参数
               if(null != _parameters) {
                  describe = String.Format(describe, _parameters);
               }
            } else {
               describe = RString.Join(",", _parameters);
            }
            return describe;
         }
      }

      //============================================================
      // <T>获得消息描述。</T>
      //
      // @return 消息描述
      //============================================================
      public override string ToString() {
         return Describe;
      }
   }
}
