using MO.Common.IO;
using MO.Common.Lang;
using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Resource
{
   public class FDxRsTrackFrame : FObject
   {
      protected int _tick;

      protected SDxMatrix _localMatrix = new SDxMatrix();

      protected SDxMatrix _worldMatrix = new SDxMatrix();

      //============================================================
      public void Unserialize(IInput input) {
         _tick = input.ReadUint8();
         _localMatrix.Unserialize(input);
         _worldMatrix.Unserialize(input);
      }
   }
}
