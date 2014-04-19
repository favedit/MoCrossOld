using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档对象。</T>
   //============================================================
   public class FXmlDocumentObject : FObject
   {
      //============================================================
      // <T>构造文档对象。</T>
      //============================================================
      public FXmlDocumentObject() {
      }
      
      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
      }

      //============================================================
      // <T>加载设置文件。</T>
      //
      // @param fileName 文件路径
      //============================================================
      public virtual void LoadConfigFile(string fileName) {
         FXmlDocument document = new FXmlDocument();
         document.LoadFile(fileName);
         LoadConfig(document.Root);
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
      }

      //============================================================
      // <T>保存设置文件。<T>      
      //
      // @param fileName 保存路径
      //============================================================
      public virtual void SaveConfigFile(string fileName) {
         FXmlDocument document = new FXmlDocument();
         SaveConfig(document.Root);
         document.SaveFile(fileName);
      }
   }
}
