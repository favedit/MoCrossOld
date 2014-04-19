using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Collection;

namespace MObj.Net.Http {

   public class FHttpRequest : IDump{

      private FHttpConnection _connection;

      private FAttributes _heads;

      private FAttributes _parameters;

      private string _protocol;

      private int _status;
      
      private string _statusNote;
      
      private FBytes _data;

      public FHttpRequest() {
         Construct();
      }

      public FHttpRequest(FHttpConnection connection) {
         _connection = connection;
         Construct();
      }

      protected void Construct() {
         _heads = new FAttributes();
         _parameters = new FAttributes();
      }

      public FHttpConnection Connection {
         get { return _connection; }
      }

      public FAttributes Heads {
         get { return _heads; }
      }

      public FAttributes Parameters {
         get { return _parameters; }
      }

      public void Receive() {
         int nIndex = 0;
         String line = _connection._socket.Input.ReadLine(Encoding.ASCII);
         String[] lines = RString.Split(line, ' ', 3);
         if (lines.Length != 3) {
            throw new FFatalException("Receive: {1}", line);
         }
         _protocol = lines[0];
         _status = Int32.Parse(lines[1]);
         _statusNote = lines[2];
         _heads.Clear();
         while (true) {
            line = _connection._socket.Input.ReadLine(Encoding.ASCII);
            if (line.Length == 0) {
               break;
            }
            nIndex = line.IndexOf(": ");
            if (nIndex == -1) {
               throw new FFatalException("receive: {1}", line);
            }
            _heads.Set(line.Substring(0, nIndex), line.Substring(nIndex + 2));
         }
         _data = null;
         String encoding = _heads["Transfer-Encoding"];
         if ("chunked".Equals(encoding, StringComparison.CurrentCultureIgnoreCase)) {
            ReadChunked();
         } else {
            int length = Int32.Parse(_heads[EHttpHead.ContentLength]);
            if (length > 0) {
               _data = _connection._socket.Input.ReadLength(length);
            }
         }
         //
         Console.WriteLine(Dump());
      }

      private void ReadChunked() {
         int length = 0;
         String line = null;
         FBytes bytes = new FBytes();
         while (true) {
            line = _connection._socket.Input.ReadLine(Encoding.ASCII);
            if (RString.IsEmpty(line)) {
               break;
            }
            line = line.Trim();
            length = Convert.ToInt32(line, 16);
            if (length == 0) {
               break;
            }
            _connection._socket.Input.ReadLength(bytes, length);
         }
         _data = bytes;
      }


      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.Append(" Protocol:", _protocol);
         info.Append(" Status:", _status.ToString());
         info.Append(' ');
         info.AppendLine(_statusNote);
         info.AppendLine(RDump.LINE_L2);
         int count = _heads.Count;
         for (int n = 0; n < count; n++) {
            info.Append(_heads.Name(n));
            info.Append(": ");
            info.AppendLine(_heads.Value(n));
         }
         info.AppendLine(RDump.LINE_L2);
         if (_data == null) {
            info.AppendLine("[null]");
         } else {
            info.AppendLine(Encoding.UTF8.GetString(_data.ToArray()));
         }
         info.AppendLine(RDump.LINE_L2);
         return info;
      }

      #endregion
   }

}

