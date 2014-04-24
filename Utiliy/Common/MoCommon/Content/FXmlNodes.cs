using MO.Common.Lang;

namespace MO.Common.Content
{
   //============================================================
   // <T>文档节点列表。</T>
   //============================================================
   public class FXmlNodes : FObjects<FXmlNode>
   {
      // 文档对象
      internal FXmlDocument _document;

      //============================================================
      // <T>是否含有节点名称。</T>
      //
      // @param name 节点名称
      // @return 是否含有
      //============================================================
      public bool Contains(string name, params string[] args) {
         return (Find(name, args) != null);
      }

      //============================================================
      // <T>查找节点对象。</T>
      //
      // @param name 节点名称
      // @param args 属性列表
      // @return 节点对象
      //============================================================
      public FXmlNode Find(string name, params string[] args) {
         foreach(FXmlNode node in this) {
            if(node.Equals(name, args)) {
               return node;
            }
         }
         return null;
      }

      //============================================================
      // <T>根据路径，查找节点对象。</T>
      //
      // @param path 路径
      // @param args 属性列表
      // @return 节点对象
      //============================================================
      public FXmlNode FindPath(string path, params string[] args) {
         FXmlNode find = null;
         FXmlNodes parent = this;
         foreach(string name in path.Split('.')) {
            foreach(FXmlNode node in parent) {
               if(node.Equals(name, args)) {
                  find = node;
                  break;
               }
            }
            if((null != find) && find.HasNode()) {
               parent = find.Nodes;
            }
         }
         return find;
      }
   }
}
