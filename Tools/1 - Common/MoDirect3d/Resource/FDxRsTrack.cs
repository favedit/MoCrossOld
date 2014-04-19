using MO.Common.IO;
using MO.Common.Lang;
using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTrack : FObject
   {
      protected int _boneId;

      protected SDxMatrix _localMatrix = new SDxMatrix();

      protected SDxMatrix _worldMatrix = new SDxMatrix();

      protected FObjects<FDxRsTrackFrame> _frames = new FObjects<FDxRsTrackFrame>();
      
      //============================================================
      public void Unserialize(IInput input) {
         _boneId = input.ReadUint8();
         _localMatrix.Unserialize(input);
         _worldMatrix.Unserialize(input);
         int count = input.ReadUint16();
         for(int n = 0; n < count; n++) {
            FDxRsTrackFrame frame = new FDxRsTrackFrame();
            frame.Unserialize(input);
            _frames.Push(frame);
         }
      }
   }
}
