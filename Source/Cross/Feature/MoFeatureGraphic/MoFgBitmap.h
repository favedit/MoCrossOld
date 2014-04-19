#ifndef __MO_FG_BITMAP_H__
#define __MO_FG_BITMAP_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

#ifndef __MO_FG_CORE_H__
#include "MoFgCore.h"
#endif // __MO_FG_CORE_H__

#ifndef __MO_FG_GRAPHIC_H__
#include "MoFgGraphic.h"
#endif // __MO_FG_GRAPHIC_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>位图结构数据。</T>
//============================================================
struct SBitmapData{
public:
   // 位图大小
   SIntSize2 size; 
   // 位图跨幅
   TUint lineStride;
   // 翻转纵坐标
   TBool isInvertedY;
   // 位图数据地址
   TByte* pData;
public:
   //------------------------------------------------------------
   // <T>构造初始位图结构数据。<T>
   SBitmapData(){
      lineStride = 0;
      isInvertedY = EFalse;
      MO_CLEAR(pData);
   }
};

//============================================================
// <T>图形格式。</T>
//============================================================
enum EImageFormat{
   EImageFormat_A8R8G8B8,
   EImageFormat_B8G8R8A8,
};

//============================================================
// <T>图形。</T>
//============================================================
class MO_FG_DECLARE FImage : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FImage, FInstance);
public:
   // 格式
   EImageFormat _formatCd;
   // 大小
   SIntSize2 _size;
   // 原始大小
   SIntSize2 _originSize;
public:
   FImage();
   MO_ABSTRACT ~FImage();
public:
   //------------------------------------------------------------
   // <T>获得格式。</T>
   MO_INLINE EImageFormat FormatCd(){
      return _formatCd;
   }
   //------------------------------------------------------------
   // <T>设置格式。</T>
   MO_INLINE void SetFormatCd(EImageFormat formatCd){
      _formatCd = formatCd;
   }
   //------------------------------------------------------------
   // <T>获得尺寸。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得原始尺寸。</T>
   MO_INLINE SIntSize2& OriginSize(){
      return _originSize;
   }
   //------------------------------------------------------------
   // <T>获得数据宽度。</T>
   MO_INLINE TInt DataStride(){
      return 4 * _size.width;
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt DataLength(){
      return 4 * _size.width * _size.height;
   }
};

//============================================================
// <T>位图。</T>
//============================================================
class MO_FG_DECLARE FBitmapData : public FImage
{
   MO_CLASS_DECLARE_INHERITS(FBitmapData, FImage);
public:
   // 范围
   SIntRectangle _rectangle;
   // 数据
   FByteStream* _pData;
public:
   FBitmapData(TInt width = 0, TInt height = 0);
   MO_ABSTRACT ~FBitmapData();
public:
   //------------------------------------------------------------
   // <T>获得只读内存。</T>
   MO_INLINE TByteC* MemoryC(){
      return _pData->MemoryC();
   }
   //------------------------------------------------------------
   // <T>获得内存。</T>
   MO_INLINE TByte* Memory(){
      return _pData->Memory();
   }
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE FByteStream* Data(){
      return _pData;
   }
   //------------------------------------------------------------
   // <T>获得范围。</T>
   MO_INLINE SIntRectangle& Rectangle(){
      _rectangle.SetSize(_size.width, _size.height);
      return _rectangle;
   }
public:
   MO_ABSTRACT TResult Resize(TInt width, TInt height);
   TResult UploadData(TByteC* pMemory, TInt width, TInt height);
};

//============================================================
// <T>位图工具。</T>
//============================================================
class MO_FG_DECLARE RBitmap{
public:
   //------------------------------------------------------------
   // <T>合并两个像素。</T>
   static MO_INLINE TColor MergeColor(TColor sourceColor, TColor targetColor){
      TInt targetRate = ((targetColor >> 24) & 0xFF);
      //............................................................
      // 快速滤除一些颜色，当大于 0xF8 和 小于 0xF0 时不计算混合
      if(targetRate > 0xF8){
         return targetColor;
      }else if(targetRate < 0x08){
         return sourceColor;
      }
      //............................................................
      // 分解来源颜色
      TInt sourceR = ((sourceColor      ) & 0xFF);
      TInt sourceG = ((sourceColor >> 8 ) & 0xFF);
      TInt sourceB = ((sourceColor >> 16) & 0xFF);
      TInt sourceA = ((sourceColor >> 24) & 0xFF);
      //............................................................
      // 分解目标颜色
      TInt targetR = ((targetColor      ) & 0xFF);
      TInt targetG = ((targetColor >> 8 ) & 0xFF);
      TInt targetB = ((targetColor >> 16) & 0xFF);
      TInt targetA = ((targetColor >> 16) & 0xFF);
      //............................................................
      // 根据来源颜色计算合并颜色
      TInt sourceRate = 256 - targetRate;
      TInt r = (sourceR * sourceRate + targetR * targetRate) >> 8;
      TInt g = (sourceG * sourceRate + targetG * targetRate) >> 8;
      TInt b = (sourceB * sourceRate + targetB * targetRate) >> 8;
      TInt a = (sourceA * sourceRate + targetA * targetRate) >> 8;
      //............................................................
      // 合并颜色
      TColor result = r + (g << 8) + (b << 16) + (a << 24);
      return result;
   }
public:
   static TColor MergeColor3(
         SFloatPoint2 position,
         SFloatPoint2 position1, TColor color1,
         SFloatPoint2 position2, TColor color2,
         SFloatPoint2 position3, TColor color3);
public:
   static TInt CalculateFitLength(TInt value);
public:
   static TBool ConvertToBitmapData(SBitmapData& bitmapData, TByteC* pData, TInt dataLength);
   static TBool ConvertToBitmapData(SBitmapData& bitmapData, SIntPoint2& validLoction, SIntSize2& validSize, TByteC* pData, TInt dataLength);
public:
   static TBool Copy(SBitmapData& dest, TInt x, TInt y, SBitmapData& source);
   static TBool Scroll(SBitmapData& bitmapData, TInt scrollX, TInt scrollY);
   static TInt CompressData2(TInt width, TInt height, TByte* pInputData, TByte* pOutputData);
public:
   static TInt DecodeBlock(IDataInput* pInput, IDataOutput* pOutput);
};

//============================================================
// <T>位图纹理。</T>
//============================================================
//class MO_FG_DECLARE FBitmap2d : public FBitmap{
//protected:
//   FRenderFlatTexture* _pTexture;
//public:
//   FBitmap2d();
//   MO_ABSTRACT ~FBitmap2d();
//public:
//   //------------------------------------------------------------
//   // <T>获得纹理。</T>
//   MO_INLINE FRenderFlatTexture* Texture(){
//      return _pTexture;
//   }
//public:
//   MO_ABSTRACT TResult Setup();
//public:
//   MO_OVERRIDE FRenderTexture* RenderTexture();
//public:
//   MO_OVERRIDE TResult Resize(TInt width, TInt height);
//   MO_OVERRIDE TResult Clear(TColor color);
//   MO_OVERRIDE TResult Clear(TColor color, SIntRectangle* pRectangle);
//   MO_OVERRIDE TResult Update();
//public:
//   MO_OVERRIDE void FreeBitmap();
//   MO_OVERRIDE void FreeTexture();
//};
//
////============================================================
//// <T>位图纹理。</T>
////============================================================
//class MO_FG_DECLARE FBitmapCube : public FBitmap{
//protected:
//   FRenderCubeTexture* _pTexture;
//public:
//   FBitmapCube();
//   MO_ABSTRACT ~FBitmapCube();
//public:
//   //------------------------------------------------------------
//   // <T>获得纹理。</T>
//   MO_INLINE FRenderCubeTexture* Texture(){
//      return _pTexture;
//   }
//public:
//   MO_ABSTRACT TResult Setup();
//public:
//   MO_OVERRIDE FRenderTexture* RenderTexture();
//public:
//   MO_OVERRIDE TResult Resize(TInt width, TInt height);
//   MO_OVERRIDE TResult Clear(TColor color);
//   MO_OVERRIDE TResult Clear(TColor color, SIntRectangle* pRectangle);
//   MO_OVERRIDE TResult Update();
//public:
//   MO_OVERRIDE void FreeBitmap();
//   MO_OVERRIDE void FreeTexture();
//};

////============================================================
//// <T>动态位图纹理格子。</T>
////============================================================
//class MO_FG_DECLARE FDynamicBitmapCell : public FObject{
//protected:
//   SFloatCoord _coord;
//public:
//   FDynamicBitmapCell();
//   MO_ABSTRACT ~FDynamicBitmapCell();
//public:
//   //------------------------------------------------------------
//   // <T>获得纹理。</T>
//   MO_INLINE SFloatCoord Coord(){
//      return _coord;
//   }
//};
////------------------------------------------------------------
//typedef FList<FDynamicBitmapCell*> FDynamicBitmapCellList;
//
////============================================================
//// <T>动态位图纹理。</T>
////============================================================
//class MO_FG_DECLARE FDynamicBitmap : public FBitmap{
//protected:
//   SIntSize2 _limitSize;
//   SIntSize2 _cellSize;
//   SIntSize2 _cellCount;
//   FBytes* _pCells;
//   FDynamicBitmapCellList* _pBitmapCells;
//public:
//   FDynamicBitmap();
//   MO_ABSTRACT ~FDynamicBitmap();
//public:
//   //------------------------------------------------------------
//   // <T>获得限制大小。</T>
//   MO_INLINE SIntSize2& LimitSize(){
//      return _limitSize;
//   }
//   //------------------------------------------------------------
//   // <T>获得格子大小。</T>
//   MO_INLINE SIntSize2& CellSize(){
//      return _cellSize;
//   }
//protected:
//   TBool TestSizeable(TInt offsetX, TInt offsetY, TInt width, TInt height);
//   void SetSizeable(TInt offsetX, TInt offsetY, TInt width, TInt height, TInt value);
//public:
//   void SizeSetup();
//   void SizeClear();
//   FDynamicBitmapCell* SizeAlloc(SFloatRectangle& rectangle, TInt width, TInt height);
//   void SizeFree(FDynamicBitmapCell* pCell);
//};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_BITMAP_H__
