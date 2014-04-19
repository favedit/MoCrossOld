using System.ComponentModel;
using System.Windows.Forms;
using MO.Common.Lang;
using MO.Core.Content.Catalog;

namespace MO.Core.Forms.Controls
{
   public partial class QGmConfigTreeView : TreeView
   {
      public delegate void HBuildNodeEventHandler(TreeNode node, FCfgObject child);

      protected FCfgFolder _folder;

      public event HBuildNodeEventHandler OnBuildNode;
      
      //============================================================
      public QGmConfigTreeView() {
         InitializeComponent();
      }

      //============================================================
      public QGmConfigTreeView(IContainer container) {
         container.Add(this);
         InitializeComponent();
      }

      //============================================================
      protected void BuildTreeNodes(TreeNode node, FCfgFolder parent) {
         // 建立文件夹列表
         if(parent.HasFolder()) {
            foreach(FCfgFolder folder in parent.Folders) {
               // 建立当前节点
               TreeNode childNode = new TreeNode(folder.Label);
               childNode.Tag = folder;
               if(null == node) {
                  Nodes.Add(childNode);
               } else {
                  node.Nodes.Add(childNode);
               }
               // 建立子节点列表
               BuildTreeNodes(childNode, folder);
            }
         }
         // 建立资源列表
         if(parent.HasObject()) {
            foreach(FCfgObject childObject in parent.Objects) {
               // 建立当前节点
               TreeNode childNode = new TreeNode(childObject.Label);
               if(null != OnBuildNode) {
                  OnBuildNode(childNode, childObject);
               }
               childNode.Tag = childObject;
               if(null == node) {
                  Nodes.Add(childNode);
               } else {
                  node.Nodes.Add(childNode);
               }
            }
         }
      }

      //============================================================
      public void LoadFolder(FCfgFolder folder) {
         // 设置目录对象
         _folder = folder;
         // 设置树目录
         BeginUpdate();
         Nodes.Clear();
         BuildTreeNodes(null, _folder);
         EndUpdate();
      }

      //============================================================
      public void DoNodeUp() {
         TreeNode selectNode = SelectedNode;
         if(null != selectNode) {
            TreeNode prevNode = selectNode.PrevNode;
            if(null != prevNode) {
               TreeNode parentNode = selectNode.Parent;
               if(null != parentNode) {
                  parentNode.Nodes.Remove(selectNode);
                  parentNode.Nodes.Insert(prevNode.Index, selectNode);
               } else {
                  Nodes.Remove(selectNode);
                  Nodes.Insert(prevNode.Index, selectNode);
               }
            }
            SelectedNode = selectNode;
         }
      }

      //============================================================
      public void DoNodeDown() {
         TreeNode selectNode = SelectedNode;
         if(null != selectNode) {
            TreeNode nextNode = selectNode.NextNode;
            if(null != nextNode) {
               TreeNode parentNode = selectNode.Parent;
               if(null != parentNode) {
                  parentNode.Nodes.Remove(selectNode);
                  parentNode.Nodes.Insert(nextNode.Index + 1, selectNode);
               } else {
                  Nodes.Remove(selectNode);
                  Nodes.Insert(nextNode.Index + 1, selectNode);
               }
            }
            SelectedNode = selectNode;
         }
      }

      //============================================================
      private void RefreshNodes(TreeNodeCollection nodes) {
         int count = nodes.Count;
         for(int n = 0; n < count; n++) {
            TreeNode node = nodes[n];
            if(null != node) {
               FCfgObject cfgObject = node.Tag as FCfgObject;
               if(null != cfgObject) {
                  cfgObject.DisplayIndex = n;
               }
               RefreshNodes(node.Nodes);
            }
         }
      }

      //============================================================
      public void DoSaveAll() {
         if(null != _folder) {
            RefreshNodes(Nodes);
            _folder.SaveAll();
            // 显示完成
            MessageBox.Show("已经成功保存！", "保存提示", MessageBoxButtons.OK);
         }
      }
      
      //============================================================
      public void DoInsertFolder() {
         // 新建节点
         TreeNode node = new TreeNode();
         Nodes.Add(node);
         // 开始编辑
         LabelEdit = true;
         SelectedNode = node;
         node.BeginEdit();
      }

      //============================================================
      public void DoInsertSubFolder() {
         TreeNode selectNode = SelectedNode;
         if(null != selectNode) {
            // 新建节点
            TreeNode node = new TreeNode();
            selectNode.Nodes.Add(node);
            selectNode.Expand();
            // 开始编辑
            LabelEdit = true;
            SelectedNode = node;
            node.BeginEdit();
         }
      }
      
      //============================================================
      public void DoDelete() {
         TreeNode node = SelectedNode;
         if(null != node) {
            FCfgFolder folder = node.Tag as FCfgFolder;
            // 画面提示
            if(DialogResult.OK == MessageBox.Show("确认删除！", "删除确认", MessageBoxButtons.OKCancel)) {
               if(null == node.Parent) {
                  Nodes.Remove(node);
               } else {
                  node.Parent.Nodes.Remove(node);
               }
               // 删除目录
               folder.Delete();
            }
         }
      }

      //============================================================
      public void DoAfterLabelEdit(NodeLabelEditEventArgs e) {
         TreeNode node = e.Node;
         string label = e.Label;
         if(RString.IsBlank(label)) {
            // 取消修改
            if(null == node.Tag) {
               // 删除新节点
               if(null == node.Parent) {
                  Nodes.Remove(node);
               } else {
                  node.Parent.Nodes.Remove(node);
               }
            }
            node.EndEdit(false);
            e.CancelEdit = true;
         } else {
            // 确定修改
            node.Text = e.Label;
            node.EndEdit(true);
            e.CancelEdit = false;
            // 修改资源目录
            FCfgObject cfgObject = node.Tag as FCfgObject;
            if(null == cfgObject) {
               FCfgFolder cfgFolder = null;
               if(null == node.Parent) {
                  cfgFolder = _folder;
               } else {
                  cfgFolder = node.Parent.Tag as FCfgFolder;
               }
               node.Tag = cfgFolder.CreateFolder(label);
            }
         }
      }
   }
}
