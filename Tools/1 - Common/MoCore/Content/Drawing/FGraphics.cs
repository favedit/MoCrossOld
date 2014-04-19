using System;
using System.Drawing;

namespace MO.Core.Content.Drawing
{

   public class FGraphics : IDisposable{

      private Graphics _graphics;

      public FGraphics(Graphics graphics) {
         _graphics = graphics;
      }

      #region IDisposable implements

      public void Dispose() {
         if(null != _graphics) {
            _graphics.Dispose();
         }
      }

      #endregion
   }
}
