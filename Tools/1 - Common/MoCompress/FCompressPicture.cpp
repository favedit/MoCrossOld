#include "MoCompressPicture.h"

namespace MoCompress {

const TByte FComOctreeNode::Mask[FComOctreeNode::MaxNumLevel] = { 0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01 };
const FComColor backgroundColor;

void ConvertAlpha(FComColor& outColor, const FComColor& backgroundColor) {
   if(outColor.alpha < 255){
      double colorFactor = outColor.alpha / 255.0f;
      double backgroundFactor = (255 - outColor.alpha) / 255.0f;
      outColor.red   = (int)(outColor.red * colorFactor + backgroundColor.red * backgroundFactor);
      outColor.green = (int)(outColor.green * colorFactor + backgroundColor.green * backgroundFactor);
      outColor.blue  = (int)(outColor.blue * colorFactor + backgroundColor.blue * backgroundFactor);
      outColor.alpha = 255;
   }
}

//============================================================
// <T>八叉树节点构造。</T>
//
// @param level 
// @param parent
//============================================================
FComOctreeNode::FComOctreeNode(TInt level, FComOctree* parent){
   red   = 0;
   green = 0;
   blue  = 0;
   pixelCount = 0;
   paletteIndex = 0;
   for(TInt n = 0; n < MaxNumLevel; n++){
      nodes[n] = NULL;
   }
   // 添加到父亲树的层链表里
   if(level < FComOctree::MaxNumList){
      parent->AddLevelNode(level, this);
   }
}

//============================================================
// <T>八叉树节点析构</T>
//============================================================
FComOctreeNode::~FComOctreeNode(){
   for(TInt n = 0; n < MaxNumLevel; n++){
      if(nodes[n]){
         delete nodes[n];
         nodes[n] = NULL;
      }
   }
}

//============================================================
// <T>取得一个节点的颜色。</T>
//============================================================
FComColor FComOctreeNode::Color(){
   FComColor color;
   if(IsLeaf()){
      if(1 == pixelCount){
         color.red   = red;
         color.green = green;
         color.blue  = blue;
         color.alpha = 255;
         return color;
      }else{
         color.red   = red    / pixelCount;
         color.green = green  / pixelCount;
         color.blue  = blue   / pixelCount;
         color.alpha = 255;
         return color;
      }
   }
   return color;
}

//============================================================
// <T>统计节点及子节点的像素引用数。</T>
//============================================================
TInt FComOctreeNode::ActiveNodesPixelCount(){
   TInt result = pixelCount;
   for(TInt n = 0; n < MaxNumChild; n++){
      if(nodes[n]){
         result += nodes[n]->pixelCount;
      }
   }
   return result;
}

//============================================================
// <T>取得所有叶子节点的集合。</T>
//
// @param outNodes 输出的节点集合容器。 
//============================================================
void FComOctreeNode::ActiveNodes(FComOctreeNodeList& outNodes){
   for(TInt n = 0; n < MaxNumChild; n++){
      if(nodes[n]){
         if(IsLeaf()){
            outNodes.push_back(nodes[n]);
         }else{
            nodes[n]->ActiveNodes(outNodes);
         }
      }
   }
}

//============================================================
// <T>节点增加一个颜色。</T>
//
// @param color   加入的颜色
// @param level   层级
// @param parent  父亲树
//============================================================
void FComOctreeNode::AddColor(FComColor& color, TInt level, FComOctree* parent){
   if(MaxNumLevel == level){
      red   += color.red;
      green += color.green;
      blue  += color.blue;
      pixelCount++;
   }else if(level < MaxNumLevel){
      TInt index = GetColorIndexAtLevel(color, level);
      if(NULL == nodes[index]){
         nodes[index] = new FComOctreeNode(level, parent);
      }
      // 继续在下一层级添加颜色
      nodes[index]->AddColor(color, level + 1, parent);
   }
}

//============================================================
// <T>取得制定颜色的调色板索引。</T>
//
// @param color 制定颜色
// @param level 层级
//============================================================
TInt FComOctreeNode::GetPaletteIndex(FComColor& color, TInt level) {
   TInt result = -1;
   if(IsLeaf()){
      result = paletteIndex;
   }else{
      TInt index = GetColorIndexAtLevel(color, level);
      if(nodes[index]){
         result = nodes[index]->GetPaletteIndex(color, level + 1);
      }   
   }
   return result;
}

//============================================================
// <T>移除所有叶子节点。</T>
//============================================================
TInt FComOctreeNode::RemoveLeaves(){
   TInt result = 0;
   for(TInt n = 0; n < MaxNumChild; n++){
      if(nodes[n]){
         red   += nodes[n]->red;
         green += nodes[n]->green;
         blue  += nodes[n]->blue;
         pixelCount += nodes[n]->pixelCount;
         delete nodes[n];
         nodes[n] = NULL;
         result++;
      }
   }
   return result - 1;
}

//============================================================
// <T>获得制定颜色在制定层级的存放索引。</T>
//
// @param color 制定颜色
// @param level 层级
//============================================================
TInt FComOctreeNode::GetColorIndexAtLevel(FComColor& color, TInt level){
   return   ((color.red    & Mask[level]) == Mask[level] ? 4 : 0) |
            ((color.green  & Mask[level]) == Mask[level] ? 2 : 0) |
            ((color.blue   & Mask[level]) == Mask[level] ? 1 : 0);
}

//============================================================
// <T>设置调色板的索引号。</T>
//============================================================
void FComOctreeNode::SetPaletteIndex(TInt index){
   paletteIndex = index;
}

//============================================================
// <T>八叉树构造函数。</T>
//============================================================
FComOctree::FComOctree(){
   _root = new FComOctreeNode(0, this);
}

//============================================================
// <T>往树中添加一个颜色。</T>
// @param color 制定颜色
//============================================================
void FComOctree::AddColor(FComColor& color){
   // 转换为透明处理后的颜色。
   ConvertAlpha(color, backgroundColor);
   _root->AddColor(color, 0, this);
}


//============================================================
// <T>取得树中存在的颜色总数量。</T>
//============================================================
TInt FComOctree::GetColorCount(){
   FComOctreeNodeList nodes;
   Leaves(nodes);
   return nodes.size();
}

//============================================================
// <T>根据树颜色使用权重中挑选除较大权重的调色本集合。</T>
//
// @param outPalettes   输出的调色板集合  
// @param count         选取的调色板数量
//============================================================
void FComOctree::MakePalette(FComColorVector& outPalettes, TInt count){
   FComOctreeNodeList nodes;
   Leaves(nodes);
   TInt leafCount = nodes.size();
   TInt paletteIndex = 0;
   FComOctreeNodeList* pLevel;
   list<FComOctreeNode*>::iterator iterator;
   for(TInt level = MaxNumList - 1; level >= 0; level--){
      pLevel = &_levels[level];
      if(pLevel->size() > 0){ 
         FComOctree::QsortOctreeNodesInList(*pLevel);
         iterator = pLevel->begin();
         while(iterator != pLevel->end()){
            leafCount -= (*iterator)->RemoveLeaves();
            if(leafCount <= count){
               break;
            }
            iterator++;
         }
         if(leafCount <= count){
            break;
         }
      }
   }
   nodes.clear();
   Leaves(nodes);
   iterator = nodes.begin();
   while(iterator != nodes.end()){
      outPalettes.push_back((*iterator)->Color());
      (*iterator)->SetPaletteIndex(paletteIndex++);
      iterator++;
   }
   if(nodes.size() == 0){
      // TODO : debug
   }
}

//============================================================
// <T>找出调色板索引。</T>
//============================================================
TInt FComOctree::FindPaletteIndex(FComColor& color){
   // 转换为透明处理后的颜色。
   ConvertAlpha(color, backgroundColor);
   return _root->GetPaletteIndex(color, 0);
}

//============================================================
// <T>清除整棵树节点和叶子节点。</T>
//============================================================
void FComOctree::Clear(){
   for(TInt n = 0; n < MaxNumList; n++){
       _levels[n].clear();
   }
   delete _root;
   _root = new FComOctreeNode(0, this);
}

//============================================================
// <T>得到所有叶子节点的集合。</T>
//
// @param outNodes  叶子节点的集合。
//============================================================
void FComOctree::Leaves(FComOctreeNodeList& outNodes){
   _root->ActiveNodes(outNodes);
}

//============================================================
// <T>添加节点到层链表中。</T>
//============================================================
void FComOctree::AddLevelNode(TInt level, FComOctreeNode* node){
   _levels[level].push_back(node);
}

//============================================================
// <T>数节点集合排序排序。</T>
//
//  @param list 参与排序的集合。
//============================================================
void FComOctree::QsortOctreeNodesInList(FComOctreeNodeList& list){
   list.sort(FComOctree::CompareOctreeNode);
}

//============================================================
// <T>升序排序节点比较函数。</T>
//
// @param one        参与比较节点一
// @param otherOne   参与比较节点二
//============================================================
TBool FComOctree::CompareOctreeNode(FComOctreeNode* one, FComOctreeNode* otherOne){
   return one->ActiveNodesPixelCount() > otherOne->ActiveNodesPixelCount();
}

}  // end namespace
