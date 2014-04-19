using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm {

   public class FAsmOpPath : FAsmOpObject {

      public const string TAG = "Codes";

      protected const string PTY_FLAG = "flag";

      protected FAsmOpPath _object0;

      protected FAsmOpPath _object1;

      protected FAsmOpCodes _codes = new FAsmOpCodes();

      public FAsmOpPath Object0 {
         get { return _object0; }
      }

      public FAsmOpPath Object1 {
         get { return _object1; }
      }

      public FAsmOpCodes Sync(string path) {
         return Sync(path.ToCharArray(), 0);
      }

      protected FAsmOpCodes Sync(char[] path, int offset) {
         char ch = path[offset];
         if (ch == '0') {
            if (_object0 == null) {
               _object0 = new FAsmOpPath();
            }
            if (offset == path.Length - 1) {
               return _object0._codes;
            }
            return _object0.Sync(path, offset + 1);
         } else if (ch == '1') {
            if (_object1 == null) {
               _object1 = new FAsmOpPath();
            }
            if (offset == path.Length - 1) {
               return _object1._codes;
            }
            return _object1.Sync(path, offset + 1);
         } else {
            throw new FFatalException("Valid path char");
         }
      }

      public override void LoadConfig(FXmlNode config) {
         FXmlNodes nodes = config.Nodes;
         if (nodes != null) {
            foreach (FXmlNode node in nodes) {
               string flag = node[PTY_FLAG];
               if (!RString.IsEmpty(flag)) {
                  if ("0".EndsWith(flag)) {
                     //_object0 = CreateObject(node);
                  } else if ("1".EndsWith(flag)) {
                     //_object1 = CreateObject(node);
                  } else {
                     throw new FFatalException("Unknown flag. {0}", config.Dump());
                  }
               }
            }
         }
      }

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset, int bits) {
         byte data = memory[offset + bits / 8];
         bool flag = ((data & (0x01 << (7 - bits % 8))) == 0);
         FAsmOpPath opObject = flag ? _object0 : _object1;
         if (opObject != null) {
            return opObject.Parse(cmd, memory, offset, bits + 1);
         }
         int count = _codes.Count;
         for (int n = 0; n < count; n++) {
            if (_codes[n].Parse(cmd, memory, offset, 0)) {
               return true;
            }
         }
         return false;
      }

      public FString Dump() {
         FString dump = new FString();
         Dump(dump, this, "", 0);
         return dump;
      }

      public void Dump(FString dump, FAsmOpPath opPath, string path, int deep) {
         if (opPath._object0 != null) {
            Dump(dump, opPath._object0, path + "0", deep + 1);
         }
         if (opPath._object1 != null) {
            Dump(dump, opPath._object1, path + "1", deep + 1);
         }
         int count = opPath._codes.Count;
         for (int n = 0; n < count; n++) {
            dump.Append(path.PadRight(24, ' '));
            dump.Append(n);
            dump.Append(": ");
            dump.AppendLine(opPath._codes[n].Name);
         }
      }

   }

}
