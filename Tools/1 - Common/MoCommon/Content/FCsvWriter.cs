using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using MO.Common.Lang;
using MO.Common.IO;

namespace MO.Common.Content {

   public class FCsvWriter : IDisposable {

      private string _filename;

      private StreamWriter _writer;

      private FCsvHeads _heads = new FCsvHeads();

      private FCsvFormat _format = new FCsvFormat();

      private int _writedCount = 0;

      private FCsvLines _headLines = new FCsvLines();

      private FCsvLines _footerLines = new FCsvLines();

      public FCsvWriter() {
      }

      public FCsvWriter(string filename) {
         OpenFile(filename);
      }

      public string FileName {
         get { return _filename; }
         set { _filename = value; }
      }

      public FCsvHeads Heads {
         get { return _heads; }
      }

      public FCsvLine CreateLine(FCsvLineType type) {
         FCsvLine line = new FCsvLine(type);
         line._heads = _heads;
         return line;
      }

      public int WritedCount {
         get { return _writedCount; }
      }

      public bool HasHeadLines {
         get { return !_headLines.IsEmpty(); }
      }

      public bool HasFooterLines {
         get { return !_footerLines.IsEmpty(); }
      }

      public FCsvLines HeadLines {
         get { return _headLines; }
      }

      public FCsvLines FooterLines {
         get { return _footerLines; }
      }

      public FCsvFormat Format {
         get { return _format; }
         set { _format = value; }
      }

      public FCsvLine AddHeadLine() {
         FCsvLine line = new FCsvLine(FCsvLineType.Head);
         _headLines.Push(line);
         return line;
      }

      public FCsvLine AddFooterLine() {
         FCsvLine line = new FCsvLine(FCsvLineType.Footer);
         _footerLines.Push(line);
         return line;
      }

      public void LoadConfig(FXmlNode config) {
         if (config != null) {
            FXmlNode headsNode = config.Find(FCsvHeads.TAG_NAME);
            if (headsNode != null) {
               _heads.LoadConfig(headsNode);
            }
         }
      }

      public void OpenFile(string filename) {
         _writedCount = 0;
         _filename = filename;
         _writer = new StreamWriter(File.Open(filename, FileMode.Create));
         if (_format != null) {
            if (_format.Header) {
               WriteHeads();
            }
         }
      }

      public void WriteHeads() {
         if(_headLines.IsEmpty()) {
            FCsvLine line = new FCsvLine(FCsvLineType.Head);
            int count = _heads.Count;
            for (int n = 0; n < count; n++) {
               line[n] = _heads[n].Description;
            }
            WriteLine(line);
         } else {
            foreach (FCsvLine line in _headLines) {
               WriteLine(line);
            }
         }
      }

      public void WriteLine(FCsvLine line) {
         if (line != null) {
            if (_writer == null) {
               throw new FFatalException("Not found opened handle");
            }
            FString result = new FString();
            if (line.Type == FCsvLineType.Data) {
               _writedCount++;
               line.ToString(result, _format.Delimiter, _format.DataClose);
            } else {
               line.ToString(result, _format.Delimiter, false);
            }
            _writer.WriteLine(result.ToString());
         }
      }

      public void WriteFooter() {
         if(_headLines.IsEmpty()) {
            FCsvLine line = new FCsvLine(FCsvLineType.Footer);
            int count = _heads.Count;
            line.Append(_format.FooterChar);
            line.Append(_writedCount.ToString());
            WriteLine(line);
         } else {
            foreach (FCsvLine line in _footerLines) {
               WriteLine(line);
            }
         }
      }

      public void Close() {
         if (_writer != null) {
            if (_format != null && _format.Footer) {
               WriteFooter();
            }
            _writer.Close();
            _writer = null;
         }
      }

      public void Flush() {
         _writer.Flush();
      }

      public void Dispose() {
         Close();
      }
   }

}
