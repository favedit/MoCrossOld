using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Resource.Data
{
   //============================================================
   // <T>数据行对象。</T>
   //============================================================
   public class FRsDataRow : FObject
   {
      // 数据
      protected FAttributes _data = new FAttributes();

      //============================================================
      // <T>构造数据行对象。</T>
      //============================================================
      public FRsDataRow(){
      }

      //============================================================
      // <T>获得数据。</T>
      //============================================================
      public FAttributes Data {
         get { return _data; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         _data.Append(xconfig.Attributes);
      }

      //============================================================
      // <T>根据名称获得内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public string Get(string name) {
         return _data.Get(name);
      }
   }
}
