using System;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm {

   public abstract class FAsmOpCode : FAsmOpObject {

      public const string TAG = "Code";

      public const string PTY_NAME = "name";

      private string _name;

      public string Name {
         get { return _name; }
      }

      public override void LoadConfig(FXmlNode config) {
         _name = config[PTY_NAME];
      }

      public abstract bool Parse(FAsmCommand cmd, FBytes bytes, int offset);

      public override bool Parse(FAsmCommand cmd, FBytes bytes, int offset, int bits) {
         cmd.Code = this;
         cmd.Size = 1;
         if(Parse(cmd, bytes, offset)) {
            cmd.Memory = bytes.ToArray(offset, cmd.Size);
            return true;
         }
         return false;
      }

      public string MakeDisplay(object[] parameters) {
         FString info = new FString();
         info.Append(_name.ToUpper());
         info.Append(MakeParameters(parameters));
         return info.ToString();
      }

      public string MakeParameters(object[] parameters) {
         FString info = new FString();
         if(parameters != null) {
            info.Append(' ');
            int count = parameters.Length;
            for(int n = 0; n < count; n++) {
               if(n > 0) {
                  info.Append(',');
               }
               // Parameter
               string sparam = null;
               object param = parameters[n];
               if(param == null) {
                  sparam = "";
               } else {
                  Type type = param.GetType();
                  if(type == typeof(short) || type == typeof(Int16)) {
                     sparam = "0x" + ((short)param).ToString("X4");
                  } else if(type == typeof(ushort) || type == typeof(UInt16)) {
                     sparam = "0x" + ((ushort)param).ToString("X4");
                  } else if(type == typeof(int) || type == typeof(Int32)) {
                     sparam = "0x" + ((int)param).ToString("X8");
                  } else if(type == typeof(int) || type == typeof(UInt32)) {
                     sparam = "0x" + ((uint)param).ToString("X8");
                  } else {
                     sparam = param.ToString();
                  }
               } 
               info.Append(sparam);
            }
         }
         return info.ToString();
      }

   }

}
