using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [ComImport]
   [CoClass(typeof(FMLangConvertCharsetClass))]
   [Guid("D66D6F98-CDAA-11D0-B822-00C04FC9B31F")]
   public interface FMLangConvertCharset : IMLangConvertCharset {
   }

}
