using System.Collections.Generic;
using System.Drawing;
using MO.Common.Lang;
using MO.Core.Content.Picture.Helpers;

namespace MO.Core.Content.Picture.Quantizers.Octree16
{
   //============================================================
   // <T>OCTree颜色优化器。</T>
   //============================================================
   public class FOctreeQuantizer16 : IColorQuantizer
   {
      protected FOctreeNode16 _root;

      protected FOctreeNode16s[] _levels = new FOctreeNode16s[7];

      protected FOctreeNode16s _leafs = new FOctreeNode16s();

      //============================================================
      // <T>构造OCTree颜色优化器。</T>
      //============================================================
      public FOctreeQuantizer16() {
         for(int n = 0; n < 7; n++) {
            _levels[n] = new FOctreeNode16s();
         }
         _root = new FOctreeNode16(this, 0);
      }

      //============================================================
      // <T>获得所有有效节点。</T>
      //============================================================
      public void FilterLeaf(FOctreeNode16s leafs) {
         leafs.Clear();
         _root.FilterLeaf(leafs);
      }

      //============================================================
      // <T>增加一个叶节点到指定层级。</T>
      //
      // @param level 指定层级
      // @param node 叶节点
      //============================================================
      internal void AddLevelNode(int level, FOctreeNode16 node) {
         _levels[level].Push(node);
      }

      //============================================================
      // <T>增加一个新颜色。</T>
      //
      // @param color 颜色
      //============================================================
      public void AddColor(Color color) {
         _root.AddColor(color, 0, this);
      }

      //============================================================
      // <T>获得颜色总数。</T>
      //
      // @param color 颜色总数
      //============================================================
      public int GetColorCount() {
         return _root.LeafCount;
      }

      //============================================================
      // <T>生成指定颜色数目的调色板。</T>
      //
      // @param count 颜色数目
      // @return 颜色列表;
      //============================================================
      public List<Color> MakePalette(int count) {
         // 获得所有节点
         int leafCount = _root.LeafCount;
         FOctreeNode16Sorter sorter = new FOctreeNode16Sorter();
         // 合并节点叶子
         for (int level = 6; level >= 0; level--) {
            FOctreeNode16s nodes = _levels[level];
            if(!nodes.IsEmpty) {
               nodes.Sort(sorter);
               foreach(FOctreeNode16 node in nodes) {
                  int removeCount = node.RemoveLeaves(leafCount - count + 1);
                  leafCount -= removeCount;
                  if(leafCount <= count) {
                     break;
                  }
               }
               if (leafCount <= count) {
                  break;
               }
            }
            nodes.Clear();
         }
         if(leafCount > count) {
            throw new FFatalException("Make palette failure. (count={0})", leafCount);
         }
         // 增加颜色列表
         int paletteIndex = 0;
         List<Color> result = new List<Color>();
         FilterLeaf(_leafs);
         foreach(FOctreeNode16 node in _leafs) {
            result.Add(node.Color);
            node.SetPaletteIndex(paletteIndex++);
         }
         // 检查结果
         return result;
      }

      //============================================================
      // <T>找到指定颜色在调色板内的索引位置。</T>
      //
      // @param color 颜色
      // @return 索引位置
      //============================================================
      public int FindPaletteIndex(Color color) {
         return _root.GetPaletteIndex(color, 0);
      }

      //============================================================
      // <T>清除所有内容。</T>
      //============================================================
      public void Clear() {
         for(int n = 0; n < 16; n++) {
            _levels[n].Clear();
         }
         _root = new FOctreeNode16(this, 0);
      }
   }
}
