using System.Drawing;

namespace MO.Core.Forms.Common
{
   public interface IConfiguration
   {
      bool GetBoolean(string name);
      
      string GetInteger(string name);

      string GetString(string name);

      Font GetFont(string name);
   }
}
