namespace MO.Core.Forms.Common
{
   public interface IConfigControl
   {
      void LoadControlConfig(IConfiguration config);

      void SaveControlConfig(IConfiguration config);
   }
}
