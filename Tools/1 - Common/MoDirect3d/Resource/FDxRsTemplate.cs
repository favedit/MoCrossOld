using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.Collection;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTemplate : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDxRsTemplate));

      protected string _name;

      protected FDictionary<FDxRsTemplateMaterial> _materials = new FDictionary<FDxRsTemplateMaterial>();

      protected FObjects<FDxRsTemplateRenderable> _renderables = new FObjects<FDxRsTemplateRenderable>();

      //============================================================
      public FDxRsTemplate() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public FDictionary<FDxRsTemplateMaterial> Materials {
         get { return _materials; }
      }

      //============================================================
      public FObjects<FDxRsTemplateRenderable> Renderables {
         get { return _renderables; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         _name = input.ReadString();
         //............................................................
         // 读取材质列表
         int materialCount = input.ReadInt16();
         for (int n = 0; n < materialCount; n++) {
            FDxRsTemplateMaterial material = new FDxRsTemplateMaterial();
            material.Template = this;
            material.Unserialize(input);
            _materials.Set(material.Code, material);
         }
         //............................................................
         // 读取材质列表
         int renderableCount = input.ReadInt16();
         for (int n = 0; n < renderableCount; n++) {
            FDxRsTemplateRenderable renderable = new FDxRsTemplateRenderable();
            renderable.Template = this;
            renderable.Unserialize(input);
            //_logger.Debug(this, "LoadFile", "Load renderable. (loop={0}/{1}, model={2}({3}, material={4})",
            //      n, renderableCount, renderable.ModelCode, renderable.GeometryName, renderable.MaterialCode);
            _renderables.Push(renderable);
         }
      }

      //============================================================
      public void LoadFile(string fileName) {
         _logger.Debug(this, "LoadFile", "Load resource tempalte file. (file_name={0})", fileName);
         FCompressFile file = new FCompressFile();
         file.BlockDecompress(fileName);
         Unserialize(file);
      }
   }
}
