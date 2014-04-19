using MO.Common.IO;
using MO.Common.Lang;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsModel : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDxRsModel));

      protected string _name;

      protected FDxRsMesh _mesh = new FDxRsMesh();

      //============================================================
      public FDxRsModel() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public FDxRsMesh Mesh{
         get { return _mesh; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         _name = input.ReadString();
         _mesh.Unserialize(input);
      }

      //============================================================
      public void LoadFile(string fileName) {
         _logger.Debug(this, "LoadFile", "Load resource model file. (file_name={0})", fileName);
         FCompressFile file = new FCompressFile();
         file.BlockDecompress(fileName);
         Unserialize(file);
      }
   }
}
