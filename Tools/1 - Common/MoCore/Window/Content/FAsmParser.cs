using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm {

   public class FAsmParser {

      public FAsmParser() {
         LoadConfig();
      }

      private FAsmOpPath _codes = new FAsmOpPath();

      public void LoadConfig() {
         FXmlDocument xdoc = new FXmlDocument();
         xdoc.LoadResource(GetType().Assembly, "Context.Asm.FAsmOpCode.xml");
         FXmlNode codesNode = xdoc.Root.Find("OpCodes");
         foreach(FXmlNode codeNode in codesNode.Nodes){
            //_codes.LoadConfig(codesNode);
            if (codeNode.IsName("OpCode")) {
               LoadOpCode(codeNode);
            }
         }
      }

      public void LoadOpCode(FXmlNode config) {
         string flag = config["flag"];
         string path = null;
         char[] chs = flag.ToCharArray();
         int count = chs.Length;
         for (int n = 0; n < count; n++) {
            char ch = chs[n];
            if ((n != count - 1) && (ch == '0' || ch == '1')) {
               continue;
            }
            path = flag.Substring(0, n);
            break;
         }
         FAsmOpCodes codes = _codes.Sync(path);
         codes.CreateCode(config);
      }

      public FAsmCommands Parse(uint address, byte[] memory, int offset, int length) {
         FBytes bytes = new FBytes();
         bytes.Append(memory, offset, length);
         FAsmCommands cmds = new FAsmCommands();
         int end = offset + length;
         for(int n = offset; n < end; n++) {
            FAsmCommand cmd = new FAsmCommand();
            cmd.Address = address;
            if (_codes.Parse(cmd, bytes, n, 0)) {
               address += (uint)cmd.Size;
               n += cmd.Size - 1;
               cmds.Push(cmd);
            }
         }
         return cmds;
      }

      public FString Dump() {
         FString dump = new FString();
         dump.AppendLine(RDump.LINE_L2);
         dump.Append(_codes.Dump());
         dump.AppendLine(RDump.LINE_L2);
         return dump;
      }

   }

}
