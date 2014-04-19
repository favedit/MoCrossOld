#pragma once
#include <list>
#include <vector>

#ifndef TByte
   #define TByte unsigned char
#endif

#ifndef TInt
   #define TInt int
#endif

#ifndef TBool
   #define TBool  bool
   #define ETrue  true
   #define EFalse false
#endif

using namespace std;
namespace MoCompress {

class FComOctreeNode;
class FComOctree;

struct FComColor {
public:
   TByte red;
   TByte green;
   TByte blue;
   TByte alpha;
public:
   FComColor(){ 
      red   = 255;
      green = 255;
      blue  = 255;
      alpha = 255;
   }
};
//------------------------------------------------------------
typedef vector<FComColor> FComColorVector; 
typedef list<FComOctreeNode*> FComOctreeNodeList;
//============================================================
// <T>°Ë²æÊ÷½Úµã¡£</T>
//
// @author LSHAI 12/03/29
//============================================================
class FComOctreeNode{
   FComOctreeNode(){}
public:
   static const TByte MaxNumLevel = 8;
   static const TByte MaxNumChild = 8;
   static const TByte Mask[MaxNumLevel];
   TInt red;
   TInt green;
   TInt blue;
   TInt pixelCount;
   TInt paletteIndex;
   FComOctreeNode* nodes[MaxNumChild];
public:
   FComOctreeNode(TInt level, FComOctree* parent);
   ~FComOctreeNode();
public:
   inline TBool IsLeaf() { return pixelCount > 0; }
   FComColor Color();
   TInt ActiveNodesPixelCount();
   void ActiveNodes(FComOctreeNodeList& outNodes);
public:
   void AddColor(FComColor& color, TInt level, FComOctree* parent);
   TInt GetColorIndexAtLevel(FComColor& color, TInt level);
   TInt RemoveLeaves();
   TInt GetPaletteIndex(FComColor& color, TInt level);
   void SetPaletteIndex(TInt index);
};

//============================================================
// <T>°Ë²æÊ÷¡£</T>
//
// @author LSHAI 12/03/29
//============================================================
class FComOctree {
public:
   static const TInt MaxNumList = 7;
private:
   FComOctreeNodeList  _levels[MaxNumList];
   FComOctreeNode*      _root;
public:
   FComOctree();
public:
   void AddColor(FComColor& color);
   TInt GetColorCount();
   void MakePalette(FComColorVector& outPalettes, TInt count);
   TInt FindPaletteIndex(FComColor& color);
   void Clear();
public:
   void Leaves(FComOctreeNodeList& outNodes);
   void AddLevelNode(TInt level, FComOctreeNode* node);
public:
   static void QsortOctreeNodesInList(FComOctreeNodeList& list);
   static TBool CompareOctreeNode(FComOctreeNode* one, FComOctreeNode* otherOne);
};

}
