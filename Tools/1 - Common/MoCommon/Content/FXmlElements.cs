using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档元素列表。</T>
   //============================================================
   public class FXmlElements : FObjects<FXmlElement>
   {
      // 文档对象
      internal FXmlDocument _document;

      //============================================================
      // <T>查找一个指定名称的元素对象。</T>
      //
      // @param name 元素名称
      // @return 元素对象
      //============================================================
      public FXmlElement Find(string name) {
         for(int n = 0; n < _count; n++) {
            if(_items[n].IsName(name)) {
               return _items[n];
            }
         }
         return null;
      }
   }
}
