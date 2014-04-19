using MO.Common.Lang;

namespace MO.Core.Window.Core {

   public class FFunctionInfo {

      public uint BaseAddress;

      public string Name;

      public uint NameAddress;

      public int NameAddressRva;

      public int FunctionIndex;

      public uint FunctionAddress;

      public int FunctionAddressRva;

      public uint FunctionRealAddress {
         get { return BaseAddress + FunctionAddress; }
      }

      public FModuleInfo Module;

      public override string ToString() {
         FString info = new FString();
         info.Append(FunctionIndex.ToString().PadLeft(3, '0'));
         info.Append(' ');
         info.Append(FunctionAddress.ToString("X8"));
         info.Append('(');
         info.Append(FunctionAddressRva.ToString("X8"));
         info.Append(')');
         info.Append(' ');
         info.Append(Name);
         return info.ToString();
      }

   }

}
