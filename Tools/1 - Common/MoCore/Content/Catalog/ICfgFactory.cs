namespace MO.Core.Content.Catalog
{
   public interface ICfgFactory
   {
      FCfgObject CreateObject(string type);

      FCfgFolder CreateFolder();
   }
}
