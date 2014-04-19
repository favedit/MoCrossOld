using MO.Common.Lang;
using MO.Common.System;
using MO.DX.Core.Device;
using MO.DX.Core.Program.Effect.Function;
using MO.Common.Collection;
using MO.Common.Content;

namespace MO.DX.Core.Program.Effect
{
   //============================================================
   // <T>效果控制台。</T>
   //============================================================
   public class FDxEffectConsole : FConsole
   {
      protected string _path;

      protected FDictionary<FDxEffect> _effects = new FDictionary<FDxEffect>();

      //============================================================
      public FDxEffectConsole() {
      }

      //============================================================s
      public FDictionary<FDxEffect> Effects {
         get { return _effects; }
      }

      //============================================================s
      public FDxEffect Create(string type) {
         switch (type) {
            case "growing":
               return new FDxGrowingEffect();
            case "normal.map":
               return new FDxNormalMapEffect();
            case "light.map":
               return new FDxLightMapEffect();
            case "simple":
               return new FDxSimpleEffect();
            case "design.scene":
               return new FDxDesignSceneEffect();
            case "design.line":
               return new FDxDesignLineEffect();
            case "design.merge":
               return new FDxDesignMergeEffect();
            case "design.ui":
               return new FDxDesignUiEffect();
         }
         throw new FFatalException("Unknown effect type. (type={0})", type);
      }

      //============================================================s
      public FDxEffect Get(FDxDevice3D device, string type) {
         FDxEffect effect = _effects.Find(type);
         if(null == effect) {
            effect = Create(type);
            effect.Device = device;
            effect.LoadFile(_path + "\\" + type + ".fx");
            effect.Setup();
            _effects.Set(type, effect);
         }
         return effect;
      }

      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Property")) {
               string name = xnode["name"];
               switch(name) {
                  case "path":
                     _path = xnode.Text;
                     break;
               }
            }
         }
      }
   }
}
