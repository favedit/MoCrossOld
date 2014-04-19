using System.Windows.Forms;
using MO.Core.Content.Catalog;

namespace MO.Content3d.Common
{
   public class FDrFolder : FCfgFolder
   {
      protected TreeNode _treeNode;

      //============================================================
      public bool Selected {
         get { 
            if (null != _treeNode) { 
               return _treeNode.Checked; 
            }
            return false;
         }
      }

      //============================================================
      public TreeNode TreeNode {
         get { return _treeNode; }
         set { _treeNode = value; }
      }

      //============================================================
      public override FCfgFolder CreateFolder(string name) {
         return new FDrFolder();
      }
   }
}
