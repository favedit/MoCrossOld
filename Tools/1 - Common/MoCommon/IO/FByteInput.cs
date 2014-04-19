namespace MO.Common.IO
{
   public class FByteInput : FArrayInput<byte>
   {
      public FByteInput() {
      }

      public FByteInput(byte[] data)
         : base(data) {
      }

      public FByteInput(byte[] data, int offset, int length)
         : base(data, offset, length) {
      }
   }
}
