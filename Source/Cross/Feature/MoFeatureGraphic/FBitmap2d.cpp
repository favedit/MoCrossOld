#include "MoFgBitmap.h"

MO_NAMESPACE_BEGIN

////============================================================
//// <T>构造位图画板纹理。</T>
////============================================================
//FBitmap2d::FBitmap2d(){
//   MO_CLEAR(_pTexture);
//}
//
////============================================================
//// <T>析构位图画板纹理。</T>
////============================================================
//FBitmap2d::~FBitmap2d(){
//   MO_DELETE(_pTexture);
//}
//
////============================================================
//// <T>配置处理。</T>
////
//// @return 处理结果
////============================================================
//TResult FBitmap2d::Setup(){
//   MO_ASSERT(!_pTexture);
//   //// 获得设备
//   //FRenderDevice* pDevice = RDeviceManager::Instance().RenderDevice();
//   //// 创建纹理
//   //SIntSize2& bitmapSize = _size;
//   //_pTexture = pDevice->CreateFlatTexture();
//   //_pTexture->Size().Assign(bitmapSize);
//   //_pTexture->SetFormatCd(ERenderTextureFormat_BGRA);
//   //_pTexture->SetFilterCd(ERenderTextureFilter_Nearest);
//   //_pTexture->SetWrapCd(ERenderTextureWrap_Clamp);
//   //_pTexture->Setup();
//   return ESuccess;
//}
//
////============================================================
//// <T>获得渲染纹理。</T>
////
//// @return 渲染纹理
////============================================================
//FRenderTexture* FBitmap2d::RenderTexture(){
//   return _pTexture;
//}
//
////============================================================
//// <T>改变大小。</T>
////
//// @param width 宽度
//// @param height 高度
//// @return 处理结果
////============================================================
//TResult FBitmap2d::Resize(TInt width, TInt height){
//   // 变更位图大小
//   TInt fitWidth = RBitmap::CalculateFitLength(width);
//   TInt fitHeight = RBitmap::CalculateFitLength(height);
//   FBitmapData::Resize(fitWidth, fitHeight);
//   _originSize.Set(width, height);
//   // 设置纹理大小
//   _pTexture->Resize(fitWidth, fitHeight);
//   return ESuccess;
//}
//
////============================================================
//// <T>清空数据。</T>
////
//// @param color 颜色
////============================================================
//TResult FBitmap2d::Clear(TColor color){
//   TInt size = _size.Square();
//   TColor* pWriter = (TColor*)_pData->Memory();
//   for(TInt n = 0; n < size; n++){
//      *pWriter++ = color;
//   }
//   return ESuccess;
//}
//
////============================================================
//// <T>清空数据。</T>
////
//// @param color 颜色
//// @param pRectangle 矩形范围
////============================================================
//TResult FBitmap2d::Clear(TColor color, SIntRectangle* pRectangle){
//   // 检查参数
//   MO_CHECK(pRectangle, return ENull);
//   // 获得属性
//   TInt left = pRectangle->left;
//   TInt top = pRectangle->top;
//   TInt width = pRectangle->width;
//   TInt height = pRectangle->height;
//   // 检查范围
//   if(left + width > _size.width){
//      width = _size.width - left;
//   }
//   if(top + height > _size.height){
//      height = _size.height - top;
//   }
//   // 清空内容
//   TInt stride = _size.width - width;
//   TColor* pWriter = ((TColor*)_pData->Memory()) + (_size.width * top) + left;
//   for(TInt y = 0; y < height; y++){
//      for(TInt x = 0; x < width; x++){
//         *pWriter++ = color;
//      }
//      pWriter += stride;
//   }
//   return ESuccess;
//}
//
////============================================================
//// <T>析构位图画板。</T>
////
//// @return 处理结果
////============================================================
//TResult FBitmap2d::Update(){
//   // 上传数据
//   _pTexture->Upload(_pData->MemoryC(), DataLength());
//   return ESuccess;
//}
//
////============================================================
//// <T>释放位图数据。</T>
////============================================================
//void FBitmap2d::FreeBitmap(){
//   _pData->Free();
//}
//
////============================================================
//// <T>释放纹理数据。</T>
////============================================================
//void FBitmap2d::FreeTexture(){
//   _pTexture->Free();
//}

MO_NAMESPACE_END
