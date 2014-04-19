namespace MO.Common.System {

   /*public class FLoggerFileListener : FLoggerListener {

      private static ILogger _logger = RLogger.Find(typeof(FLoggerListener));

      private string NAME_FORMAT = "_YYYYMMDD_HH24MISS.txt";

      protected Encoding _encoding = Encoding.UTF8;

      protected string _fileName;

      protected string _filePath;

      protected FileMode _fileMode = FileMode.Append;

      protected FileAccess _fileAccess = FileAccess.Write;

      public StreamWriter _writer;

      public FLoggerFileListener() {
      }

      public string FilePath {
         get { return _filePath; }
         set { _filePath = value; }
      }

      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      public Encoding Encoding {
         get { return _encoding; }
         set { _encoding = value; }
      }

      public override void Initialize() {
         try {
            if (_filePath == null) {
               if (_fileName == null) {
                  Assembly assembly = Assembly.GetEntryAssembly();
                  FFileInfo fileinfo = new FFileInfo(assembly.ManifestModule.Name);
                  _fileName = fileinfo.ShortName + RDate.Format(NAME_FORMAT);
               }
               _filePath = RFile.MakeFileName(RSystem.Location(), _fileName);
            }
            // Check log directory
            FFileInfo info = new FFileInfo(_filePath);
            if (!Directory.Exists(info.DirectoryName)) {
               Directory.CreateDirectory(info.DirectoryName);
            }
            // Make log file
            FileStream stream = File.Open(_filePath, _fileMode, _fileAccess, FileShare.Read | FileShare.Delete);
            _writer = new StreamWriter(stream, _encoding);
         } catch (Exception e) {
            throw new FFatalException(e);
         }
      }

      /*public override void Output(FLoggerParameters parameters) {
         if (_writer != null) {
            FString format = new FString();
            if (Format(format, parameters)) {
               _writer.WriteLine(format);
            }
         }
      }*

      public override void Close() {
         if (_writer != null) {
            _writer.Flush();
            _writer.Close();
            _writer = null;
         }
      }

      public void Delete() {
         Close();
         if (File.Exists(_filePath)) {
            try {
               File.Delete(_filePath);
            } catch (Exception e) {
               _logger.Error(null, "Delete", e, "Delete file failed. ({0})", _filePath);
               Console.WriteLine(e.Message);
            }
         }
      }
   }*/
}
