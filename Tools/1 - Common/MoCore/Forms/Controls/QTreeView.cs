using System;
using System.ComponentModel;
using System.Drawing;
using System.Windows.Forms;
using MO.Common.Lang;

namespace MO.Core.Forms.Controls
{
   // 事件定义
   public delegate void TreeNodeChangedHandle(object sender, EventArgs args);

   //============================================================
   // <T>树控件。</T>
   //============================================================
   [Serializable]
   public partial class QTreeView : TreeView
   {
      // 是否允许多选
      private bool _multiSelect;

      // 当前树节点
      private TreeNode _currentNode;

      // 选中节点集合
      private FObjects<TreeNode> _selectedNodes = new FObjects<TreeNode>();

      // 树节点选择变更
      public event TreeNodeChangedHandle TreeNodeChanged;

      //============================================================
      // <T>构造树控件。</T>
      //============================================================
      public QTreeView() {
         InitializeComponent();
         // 设置属性
         Sorted = false;
         ShowRootLines = false;
         Indent = 15;
         ItemHeight = 18;
      }

      //============================================================
      // <T>获得或设置是否多选。</T>
      //============================================================
      public bool IsMultiSelect {
         get { return _multiSelect; }
         set { _multiSelect = value; }
      }

      //============================================================
      // <T>获得当前节点。</T>
      //============================================================
      [Browsable(false)]
      public TreeNode CurrentNode {
         get { return _currentNode; }
      }

      //============================================================
      // <T>获得选中节点集合。</T>
      //============================================================
      [Browsable(false)]
      public FObjects<TreeNode> SelectedNodes {
         get { return _selectedNodes; }
      }

      //============================================================
      // <T>改变节点为低亮。</T>
      //
      // @param node 树目录节点
      //============================================================
      private void ChangeLowlightNode(TreeNode node) {
         node.BackColor = BackColor;
         node.ForeColor = SystemColors.ControlText;
      }

      //============================================================
      // <T>改变节点为高亮。</T>
      //
      // @param node 树目录节点
      //============================================================
      private void ChangeHighlightNode(TreeNode node) {
         node.BackColor = SystemColors.Highlight;
         node.ForeColor = SystemColors.HighlightText;
      }

      //============================================================
      // <T>设置当前节点。</T>
      //
      // @param node 树目录节点
      //============================================================
      private void SetCurrentNode(TreeNode node) {
         // 禁止选择节点
         if(_multiSelect) {
            // SelectedNode = null;
         }
         // 选择节点
         if(_currentNode != node) {
            _currentNode = node;
            // 选中处理
            if(TreeNodeChanged != null) {
               TreeNodeChanged(_currentNode, EventArgs.Empty);
            }
         }
      }

      //============================================================
      // <T>鼠标点击处理。</T>
      //
      // @param e 鼠标事件
      //============================================================
      protected override void OnMouseClick(MouseEventArgs e) {
         TreeNode node = GetNodeAt(e.X, e.Y);
         if(node != null) {
            if(_multiSelect) {
               if(!((_selectedNodes.Count == 1) && (_selectedNodes.NvlFirst == node))) {
                  if((System.Windows.Forms.Control.ModifierKeys & Keys.Control) != 0) {
                     MultiSelectNode(node);
                  } else if((System.Windows.Forms.Control.ModifierKeys & Keys.Shift) != 0) {
                     ShiftMultiSelectNode(node);
                  } else {
                     SingleSelectNode(node);
                  }
               }
            } else {
               SetCurrentNode(node);
            }
         }
      }

      //============================================================
      // <T>选中后节点处理。</T>
      //
      // @param e 树节点事件
      //============================================================
      protected override void OnAfterSelect(TreeViewEventArgs e) {
         SetCurrentNode(e.Node);
         base.OnAfterSelect(e);
      }

      //============================================================
      // <T>清除所有选中节点。</T>
      //============================================================
      public void SelectionClear() {
         // 清除选中节点
         foreach(TreeNode selectedNode in SelectedNodes) {
            selectedNode.BackColor = BackColor;
            selectedNode.ForeColor = ForeColor;
         }
         SelectedNodes.Clear();
         // 清除显示
         _currentNode = null;
         base.SelectedNode = null;
      }

      //============================================================
      // <T>增加一个多选节点。</T>
      //
      // @param node 树节点
      //============================================================
      public void SelectionPush(TreeNode node) {
         if(!_selectedNodes.Contains(node)) {
            ChangeHighlightNode(node);
            _selectedNodes.Add(node);
         }
      }

      //============================================================
      // <T>单端选中一个节点。</T>
      //
      // @param node 树节点
      //============================================================
      public void SelectionSingle(TreeNode node) {
         SelectionClear();
         SelectionPush(node);
         node.EnsureVisible();
         SelectedNode = node;
      }

      //============================================================
      // <T>单选节点处理。</T>
      //
      // @param node 树节点
      //============================================================
      private void SingleSelectNode(TreeNode node) {
         foreach(TreeNode selectedNode in SelectedNodes) {
            selectedNode.BackColor = BackColor;
            selectedNode.ForeColor = ForeColor;
         }
         SelectedNodes.Clear();
         SelectedNodes.Add(node);
         ChangeHighlightNode(node);
         SetCurrentNode(node);
      }

      //============================================================
      // <T>多选节点处理。</T>
      //
      // @param node 树节点
      //============================================================
      private void MultiSelectNode(TreeNode node) {
         if(_selectedNodes.Contains(node)) {
            _selectedNodes.Remove(node);
            ChangeLowlightNode(node);
            SetCurrentNode((TreeNode)_selectedNodes[_selectedNodes.Count - 1]);
         } else {
            _selectedNodes.Add(node);
            ChangeHighlightNode(node);
            SetCurrentNode(node);
         }
      }

      //============================================================
      // <T>追加模式多选节点处理。</T>
      //
      // @param node 树节点
      //============================================================
      private void ShiftMultiSelectNode(TreeNode node) {
         if(_selectedNodes.Contains(node)) {
            _selectedNodes.Remove(node);
            ChangeLowlightNode(node);
            SetCurrentNode((TreeNode)_selectedNodes[_selectedNodes.Count - 1]);
         } else if((_currentNode != null) && (node.Parent == _currentNode.Parent)) {
            TreeNode addNode = node;
            for(int n = System.Math.Abs(_currentNode.Index - node.Index); n > 0; n--) {
               if(!_selectedNodes.Contains(addNode)) {
                  _selectedNodes.Add(addNode);
                  ChangeHighlightNode(addNode);
               }
               addNode = _currentNode.Index > node.Index ? addNode.NextNode : addNode.PrevNode;
            }
            SetCurrentNode(node);
         }
      }

      //============================================================
      // <T>设置所有的复选框状态。</T>
      //
      // @param nodes 节点集合
      // @param value 内容
      //============================================================
      public void CheckNodeChildren(TreeNodeCollection nodes, bool value) {
         foreach(TreeNode node in nodes) {
            node.Checked = value;
            if(node.Nodes.Count > 0) {
               CheckNodeChildren(node.Nodes, value);
            }
         }
      }

      //============================================================
      // <T>查找所有选中节点集合。</T>
      //
      // @return 选中节点集合
      //============================================================
      protected void FetchCheckedNodes(TreeNodeCollection nodes, FObjects<TreeNode> checkeds) {
         foreach(TreeNode node in nodes) {
            if(node.Checked) {
               checkeds.Push(node);
            }
            if(node.Nodes.Count > 0) {
               FetchCheckedNodes(node.Nodes, checkeds);
            }
         }
      }

      //============================================================
      // <T>查找所有选中节点集合。</T>
      //
      // @return 选中节点集合
      //============================================================
      public FObjects<TreeNode> FetchCheckedNodes() {
         FObjects<TreeNode> nodes = new FObjects<TreeNode>();
         FetchCheckedNodes(Nodes, nodes);
         return nodes;
      }
   }
}
