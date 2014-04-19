using MO.Common.Content;
using MO.DX.Core.Device;
using MO.DX.Core.Model;
using MO.DX.Core.Program.Effect;
using MO.DX.Core.Program.Technique;
using MO.DX.Core.Resource;
using MO.DX.Core.Stage.Common;
using MO.DX.Core.Stage.Scene;

namespace MO.DX.Core
{
   //============================================================
   public class RDxCore
   {
      protected static FDxAdapter _adapter = new FDxAdapter();

      protected static FDxFactory _factory = new FDxFactory();

      protected static FDxRsTextureConsole _textureResourceConsole = new FDxRsTextureConsole();

      protected static FDxRsModelConsole _modelResourceConsole = new FDxRsModelConsole();

      protected static FDxRsTemplateConsole _templateResourceConsole = new FDxRsTemplateConsole();

      protected static FDxTextureConsole _textureConsole = new FDxTextureConsole();

      protected static FDxGeometryConsole _geometryConsole = new FDxGeometryConsole();

      protected static FDxModelConsole _modelConsole = new FDxModelConsole();

      protected static FDxSpatialConsole _spatialConsole = new FDxSpatialConsole();

      protected static FDxEffectConsole _effectConsole = new FDxEffectConsole();

      protected static FDxTechniqueConsole _techniqueConsole = new FDxTechniqueConsole();

      protected static FDxStageConsole _stageConsole = new FDxStageConsole();

      protected static FDxSceneConsole _sceneConsole = new FDxSceneConsole();

      //============================================================
      public static FDxAdapter Adapter {
         get { return _adapter; }
      }

      //============================================================
      public static FDxFactory Factory {
         get { return _factory; }
         set { _factory = value; }
      }

      //============================================================
      public static FDxRsTextureConsole TextureResourceConsole {
         get { return _textureResourceConsole; }
      }

      //============================================================
      public static FDxRsModelConsole ModelResourceConsole {
         get { return _modelResourceConsole; }
      }

      //============================================================
      public static FDxRsTemplateConsole TemplateResourceConsole {
         get { return _templateResourceConsole; }
      }

      //============================================================
      public static FDxTextureConsole TextureConsole {
         get { return _textureConsole; }
      }

      //============================================================
      public static FDxGeometryConsole GeometryConsole {
         get { return _geometryConsole; }
      }

      //============================================================
      public static FDxModelConsole ModelConsole {
         get { return _modelConsole; }
      }

      //============================================================
      public static FDxSpatialConsole SpatialConsole {
         get { return _spatialConsole; }
      }

      //============================================================
      public static FDxEffectConsole EffectConsole {
         get { return _effectConsole; }
      }

      //============================================================
      public static FDxTechniqueConsole TechniqueConsole {
         get { return _techniqueConsole; }
      }

      //============================================================
      public static FDxStageConsole StageConsole {
         get { return _stageConsole; }
      }

      //============================================================
      public static FDxSceneConsole SceneConsole {
         get { return _sceneConsole; }
      }

      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes){
            string name = xnode["name"];
            switch(name){
               case "resource.texture":
                  _textureResourceConsole.LoadConfig(xnode);
                  break;
               case "resource.model":
                  _modelResourceConsole.LoadConfig(xnode);
                  break;
               case "resource.template":
                  _templateResourceConsole.LoadConfig(xnode);
                  break;
               case "engine3d.effect.console":
                  _effectConsole.LoadConfig(xnode);
                  break;
            }
         }
      }

      //============================================================
      public static void Setup() {
      }
   }
}
