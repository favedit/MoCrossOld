using MO.Bridge;
using MO.Feature.Graphics.Render;

namespace MpScriptTest
{
   class Program
   {
      static void Main(string[] args) {
         RBridgeManager.Initialize();
         FRenderDevice renderDevice = new FRenderDevice();
         int refer = renderDevice.ReferCount();
         RBridgeManager.Release();
      }
   }
}
