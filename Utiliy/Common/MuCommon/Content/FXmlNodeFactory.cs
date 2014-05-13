using MO.Common.Collection;

namespace MO.Common.Content
{
   //============================================================
   // <T>节点工厂。</T>
   //============================================================
   public class FXmlNodeFactory : IXmlNodeFactory
   {
      // 文档对象
      private FXmlDocument _document;

      //============================================================
      // <T>构造节点工厂。</T>
      //============================================================
      public FXmlNodeFactory() {
      }

      //============================================================
      // <T>构造节点工厂。</T>
      //
      // @param document 文档对象
      //============================================================
      public FXmlNodeFactory(FXmlDocument document) {
         _document = document;
      }

      //============================================================
      // <T>获得或设置文档对象。</T>
      //
      // @param value 文档对象
      // @return 文档对象
      //============================================================
      public FXmlDocument Document {
         get { return _document; }
         set { _document = value; }
      }

      #region IXmlNodeFactory implements

      //============================================================
      // <T>创建文档节点。</T>
      //
      // @param name 节点名称
      // @param attributes 属性列表
      // @return 文档节点
      //============================================================
      public FXmlNode CreateNode(string name, FAttributes attributes) {
         FXmlNode node = new FXmlNode(name, attributes);
         node._document = _document;
         return node;
      }

      //============================================================
      // <T>创建文档注释。</T>
      //
      // @return 注释节点
      //============================================================
      public FXmlComment CreateComment() {
         FXmlComment comment = new FXmlComment();
         comment._document = _document;
         return comment;
      }

      #endregion
   }
}
