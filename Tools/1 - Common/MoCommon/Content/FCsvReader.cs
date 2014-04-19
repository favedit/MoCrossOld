using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using MO.Common.Lang;
using MO.Common.IO;
using MO.Common.Content;
using MO.Common.System;

namespace MO.Common.Content {

   public class FCsvReader : IDisposable {

      private static ILogger _logger = RLogger.Find(typeof(FCsvReader));

      private FCsvFormat _format = new FCsvFormat();

      protected bool _skipBlank = true;

      protected FCsvHeads _heads = new FCsvHeads();

      protected string _filename;

      private Encoding _encoding;

      private FCharReader _reader;

      private int _defineCount = 0;

      private int _readedCount = 0;

      private bool _stopped = false;

      private bool _lineFormat = false;

      private bool _lineValidator = false;

      private FCsvReadStatus _status = FCsvReadStatus.Success;

      //private FFormatorParameters _formatorParameters;

      //private FValidatorParameters _validatorParameters;

      private FCsvLines _headLines = new FCsvLines();

      private FCsvLines _footerLines = new FCsvLines();

      public FCsvReader() {
      }

      public FCsvReader(string filename) {
         Open(filename);
      }

      public string FileName {
         get { return _filename; }
         set { _filename = value; }
      }

      public Encoding Encoding {
         get { return _encoding; }
         set { _encoding = value; }
      }

      public FCsvHeads Heads {
         get { return _heads; }
      }

      public FCsvFormat Format {
         get { return _format; }
         set { _format = value; }
      }

      public FCsvReadStatus Status {
         get { return _status; }
      }

      public int DefineCount {
         get { return _defineCount; }
      }

      public int ReadedCount {
         get { return _readedCount; }
      }

      public void LoadConfig(FXmlNode config) {
         if (config != null) {
            FXmlNode headsNode = config.Find(FCsvHeads.TAG_NAME);
            if (headsNode != null) {
               _heads.LoadConfig(headsNode);
            }
         }
      }

      /*public FFormatorParameters FormatorParameters {
         get { return _formatorParameters; }
         set { _formatorParameters = value; }
      }

      public FValidatorParameters ValidatorParameters {
         get { return _validatorParameters; }
         set { _validatorParameters = value; }
      }*/

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

      public bool Open(string filename) {
         if (!File.Exists(filename)) {
            throw new FFatalException("File is not exists (filename={0})", filename);
         }
         _lineFormat = false;
         _lineValidator = false;
         _filename = filename;
         StreamReader reader = null;
         if (_encoding != null) {
            reader = new StreamReader(new FileStream(filename, FileMode.Open), _encoding);
         } else {
            reader = new StreamReader(new FileStream(filename, FileMode.Open));
         }
         _reader = new FCharReader(reader, 16);
         if (_format != null) {
            if (_format.Header) {
               ReadHeads();
            }
         }
         _stopped = false;
         _readedCount = 0;
         if (_heads != null) {
            _lineFormat = _heads.HasValidator(ECsvWorkType.Read);
            _lineValidator = _heads.HasValidator(ECsvWorkType.Read);
         }
         return _reader.HasNext();
      }

      public bool HasNext() {
         return (_reader != null && !_stopped) ? _reader.HasNext() : false;
      }

      public void ReadHeads() {
         FCsvLine line = ReadLine();
         if (line != null) {
            line._type = FCsvLineType.Head;
            _headLines.Push(line);
            _logger.Debug(this, "ReadHeads", "Read heads {0}", line.Dump());
         }
      }

      protected FCsvLine NextLine() {
         if (_reader != null && _reader.HasNext()) {
            FCsvLine line = RCsv.ParseLine(_reader, _format);
            if (line != null) {
               if (_skipBlank && line.IsEmpty) {
                  return NextLine();
               }
               line._heads = _heads;
               return line;
            }
         }
         return null;
      }

      public FCsvLine ReadLine() {
         /*FCsvLine line = NextLine();
         if (line != null) {
            if (_format != null && line[0] == _format.FooterChar) {
               ReadFooter(line);
            } else {
               _readedCount++;
            }
         }
         if (line != null && line.Type == FCsvLineType.Data) {
            // Data check
            if (_lineValidator) {
               int count = _heads.Count;
               for (int n = 0; n < count; n++) {
                  FCsvHead head = _heads[n];
                  if (head.HasValidator(ECsvWorkType.Read)) {
                     _validatorParameters.Reset();
                     _validatorParameters.Value = line[n];
                     _validatorParameters.Parameters["head"] = head;
                     if (!head.Check(ECsvWorkType.Read, _validatorParameters)) {
                        if (!_validatorParameters.RaiseException) {
                           throw new FFatalException("Check csv data error. (head={0}, line={1})",
                                 _heads[n].Name, line.ToString());
                        }
                     }
                  }
               }
            }
            // Data format
            if (_lineFormat) {
               int count = _heads.Count;
               for (int n = 0; n < count; n++) {
                  FCsvHead head = _heads[n];
                  if (head.ReadFormator != null) {
                     _formatorParameters.Reset();
                     _formatorParameters.Value = line[n];
                     _formatorParameters.Parameters["head"] = head;
                     if (head.Format(ECsvWorkType.Read, _formatorParameters)) {
                        line[n] = RString.Nvl(_formatorParameters.Result);
                     }
                  }
               }
            }
         }
         return line;*/
         return null;
      }

      public void ReadFooter(FCsvLine line) {
         _stopped = true;
         line._type = FCsvLineType.Footer;
         _defineCount = RInt.Parse(line.Last);
         if (_readedCount != _defineCount) {
            _status = FCsvReadStatus.InvalidCount;
         }
         _footerLines.Push(line);
         _logger.Debug(this, "ReadFooter", "Read footer count={0}/{1}", _readedCount, _defineCount);
      }

      public void Close() {
         if (_reader != null) {
            _reader.Close();
         }
      }

      public virtual void Dispose() {
         Close();
      }

   }

}
