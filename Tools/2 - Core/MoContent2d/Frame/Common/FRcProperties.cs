using System.ComponentModel;
using MO.Common.Collection;
using MO.Core.Forms.Controls;

namespace MO.Content2d.Frame.Common
{
   [TypeConverter(typeof(FPropertiesConverter))]
   public class FRcProperties : FProperties
   {
   }
}
