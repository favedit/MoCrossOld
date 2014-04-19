namespace MO.Common.IO
{
   public interface IQueue : IPipe
   {
      bool Push(byte[] memory, int offset, int length);

      bool Pop(byte[] memory, int offset, int length);
   }
}
