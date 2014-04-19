namespace MO.Common.IO
{
   public class FCharInput : FArrayInput<char>
   {
      public FCharInput() {
      }

      public FCharInput(char[] data)
         : base(data) {
      }

      public FCharInput(char[] data, int offset, int length)
         : base(data, offset, length) {
      }
   }
}
