using MO.Common.Content;
using MO.Core.Forms.Core;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Map
{
   //============================================================
   public class FDrMapLayer
   {
      protected int _index;

      protected FBitmap _bitmap = new FBitmap();

      protected string _materialName;

      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      public FBitmap Bitmap {
         get { return _bitmap; }
      }

      //============================================================
      public string MaterialName {
         get { return _materialName; }
         set { _materialName = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         // 加载材质名称
         _materialName = xconfig.Get("material");
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         // 设置材质名称
         config.Set("index", _index);
         config.Set("material", _materialName);
      }
   }
}
