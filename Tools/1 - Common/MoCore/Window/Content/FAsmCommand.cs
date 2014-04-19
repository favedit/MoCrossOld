using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm {
   
   public class FAsmCommand : IDump{

      private uint _address;

      private int _size;

      private FAsmOpCode _code;

      private byte[] _memory;

      private object[] _attributes;

      public FAsmCommand() { 
      }

      public FAsmCommand(FAsmOpCode code) {
         _code = code;
      }
      
      public uint Address {
         get { return _address; }
         set { _address = value; }
      }

      public int Size {
         get { return _size; }
         set { _size = value; }
      }

      public object[] Attributes {
         get { return _attributes; }
         set { _attributes = value; }
      }

      public FAsmOpCode Code {
         get { return _code; }
         set { _code = value; }
      }

      public byte[] Memory {
         get { return _memory; }
         set { _memory = value; }
      }

      public string MakeData() {
         //return RByte.MakeString(_memory);
         return null;
      }

      public string MakeDisplay() {
         return _code.MakeDisplay(_attributes);
      }

      public string MakeParameters() {
         return _code.MakeParameters(_attributes);
      }

      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.Append(_address.ToString("X8"));
         info.Append(' ');
         info.Append(MakeData().PadRight(20, ' '));
         info.Append(_code.Name.PadRight(8, ' ').ToUpper());
         info.Append(MakeParameters());
         return info;
      }

      #endregion
   }

}