using MO.Bridge.Core;
using System.Runtime.InteropServices;

namespace MO.Bridge.Feature.Graphics.Render
{
   public class FRenderDevice : FBridgeObject
   {
      [DllImport("MoBridgeFeature.dll")]
      protected static extern void Setup(SBridgeLinker bridgeLinker);

      public void Setup() {
         Setup(_bridgeLinker);
      }
   }
}
