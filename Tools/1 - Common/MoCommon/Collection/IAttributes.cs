namespace MO.Common.Collection
{
   //============================================================
   // <T>属性表接口。</T>
   //============================================================
   public interface IAttributes : IDictionary<string>
   {
      string Pack();

      bool Unpack(string pack);

      string DumpInfo();
   }
}
