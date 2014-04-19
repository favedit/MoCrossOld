namespace MO.Common.Content
{
   //============================================================
   // <T>文档环境。</T>
   //============================================================
   public class FXmlObjectContext : IXmlObjectContext
   {
      // 文档对象
      internal IXmlObjectDocument _document;

      // 顶层对象
      internal IXmlObject _top;

      // 父对象
      internal IXmlObject _parent;

      // 设置信息
      internal FXmlNode _config;

      //============================================================
      // <T>构造文档环境。</T>
      //============================================================
      public FXmlObjectContext() {
      }

      #region IXmlContext implements

      //============================================================
      // <T>获得对象文档。</T>
      //
      // @return 对象文档
      //============================================================
      public IXmlObjectDocument Document {
         get { return _document; }
      }

      //============================================================
      // <T>获得顶层对象。</T>
      //
      // @return 顶层对象
      //============================================================
      public IXmlObject Top {
         get { return _top; }
      }

      //============================================================
      // <T>获得父对象。</T>
      //
      // @return 父对象
      //============================================================
      public IXmlObject Parent {
         get { return _parent; }
      }

      //============================================================
      // <T>获得设置。</T>
      //
      // @return 设置
      //============================================================
      public FXmlNode Config {
         get { return _config; }
      }

      #endregion
   }
}
