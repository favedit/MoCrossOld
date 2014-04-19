using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [ComImport]
   [CoClass(typeof(FMLangStringClass))]
   [Guid("C04D65CE-B70D-11D0-B188-00AA0038C969")]
   public interface FMLangString : IMLangString {
   }
}
