#include "MoFgBitmap.h"

MO_NAMESPACE_BEGIN

////============================================================
//// <T>构造位图画板纹理。</T>
////============================================================
//FDynamicBitmap::FDynamicBitmap(){
//   _limitSize.Set(1024, 1024);
//   _cellSize.Set(32, 32);
//   _pCells = MO_CREATE(FBytes);
//   _pBitmapCells = MO_CREATE(FDynamicBitmapCellList);
//}
//
////============================================================
//// <T>析构位图画板纹理。</T>
////============================================================
//FDynamicBitmap::~FDynamicBitmap(){
//   MO_DELETE(_pCells);
//   MO_DELETE(_pBitmapCells);
//}
//
////============================================================
//// <T>配置处理。</T>
////============================================================
//void FDynamicBitmap::SizeSetup(){
//   _cellCount.width = _limitSize.width / _cellSize.width;
//   _cellCount.height = _limitSize.height / _cellSize.height;
//   TInt size = _cellCount.Square();
//   _pCells->SetLength(size);
//   for(TInt n = 0; n < size; n++){
//      _pCells->Set(n, 0);
//   }
//}
//
////============================================================
//// <T>配置处理。</T>
////============================================================
//void FDynamicBitmap::SizeClear(){
//}
//
////============================================================
//// <T>配置处理。</T>
////============================================================
//TBool FDynamicBitmap::TestSizeable(TInt offsetX, TInt offsetY, TInt width, TInt height){
//   for(TInt y = 0; y < height; y++){
//      for(TInt x = 0; x < width; x++){
//         TInt index = _cellCount.width * (offsetY + y) + (offsetX + x);
//         TInt value = _pCells->Get(index);
//         if(value != 0){
//            return EFalse;
//         }
//      }
//   }
//   return ETrue;
//}
//
////============================================================
//// <T>配置处理。</T>
////============================================================
//void FDynamicBitmap::SetSizeable(TInt offsetX, TInt offsetY, TInt width, TInt height, TInt value){
//   for(TInt y = 0; y < height; y++){
//      for(TInt x = 0; x < width; x++){
//         TInt index = _cellCount.width * (offsetY + y) + (offsetX + x);
//         _pCells->Set(index, (TByte)value);
//      }
//   }
//}
//
////============================================================
//// <T>配置处理。</T>
////============================================================
//FDynamicBitmapCell* FDynamicBitmap::SizeAlloc(SFloatRectangle& rectangle, TInt width, TInt height){
//   // 计算格子数量
//   TInt widthCount = width / _cellSize.width;
//   if(width % _cellSize.width != 0){
//      widthCount++;
//   }
//   TInt heightCount = height / _cellSize.height;
//   if(height % _cellSize.height != 0){
//      heightCount++;
//   }
//   // 查找格子
//   for(TInt y = 0; y < _cellCount.height; y++){
//      for(TInt x = 0; x < _cellCount.width; x++){
//         TInt index = _cellCount.width * y + x;
//         TInt value = _pCells->Get(index);
//         if(value == 0){
//            if(TestSizeable(x, y, widthCount, heightCount)){
//               SetSizeable(x, y, widthCount, heightCount, 1);
//               FDynamicBitmapCell* pCell = MO_CREATE(FDynamicBitmapCell);
//               TFloat coordX1 = (TFloat)(_cellSize.width * x) / (TFloat)_size.width;
//               TFloat coordY1 = (TFloat)(_cellSize.height * y) / (TFloat)_size.height;
//               TFloat coordX2 = (TFloat)(_cellSize.width * x + width) / (TFloat)_size.width;
//               TFloat coordY2 = (TFloat)(_cellSize.height * y + height) / (TFloat)_size.height;
//               pCell->Coord().Set(coordX1, coordY1, coordX2, coordY2);
//               _pBitmapCells->Push(pCell);
//               return pCell;
//            }
//         }
//      }
//   }
//   return NULL;
//}
//
////============================================================
//// <T>配置处理。</T>
////============================================================
//void FDynamicBitmap::SizeFree(FDynamicBitmapCell* pCell){
//}

MO_NAMESPACE_END
