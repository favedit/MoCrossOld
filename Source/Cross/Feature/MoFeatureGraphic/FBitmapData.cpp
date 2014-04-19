#include "MoFgBitmap.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FBitmapData, FImage);

//============================================================
// <T>构造位图。</T>
//============================================================
FBitmapData::FBitmapData(TInt width, TInt height){
   _pData = MO_CREATE(FByteStream);
   // 重置大小
   if((width > 0) && (height > 0)){
      Resize(width, height);
   }
}

//============================================================
// <T>析构位图。</T>
//============================================================
FBitmapData::~FBitmapData(){
   MO_DELETE(_pData);
}

//============================================================
// <T>改变大小。</T>
//
// @param width 宽度
// @param height 高度
//============================================================
TResult FBitmapData::Resize(TInt width, TInt height){
   // 检查参数
   MO_CHECK(width > 0, return EInvalid);
   MO_CHECK(height > 0, return EInvalid);
   // 检查大小
   TInt originSize = sizeof(TColor) * _size.Square();
   TInt size = sizeof(TColor) * width * height;
   if(originSize == size){
      return EContinue;
   }
   // 设置大小
   _size.Set(width, height);
   _originSize.Set(width, height);
   // 设置数据
   _pData->ForceSize(size);
   return ESuccess;
}

//============================================================
// <T>上传一个位图数据。</T>
//
// @param pMemory 数据指针
// @param width 宽度
// @param height 高度
//============================================================
TResult FBitmapData::UploadData(TByteC* pMemory, TInt width, TInt height){
   // 检查参数
   MO_CHECK(pMemory, return ENull);
   MO_CHECK(width > 0, return EInvalid);
   MO_CHECK(height > 0, return EInvalid);
   // 复制数据
   TByteC* pReader = pMemory;
   TInt readerStride = sizeof(TUint32) * width;
   TByte* pWriter = _pData->Memory();
   TInt writerStride = sizeof(TUint32) * _size.width;
   for(TInt n = 0; n < height; n++){
      MO_LIB_MEMORY_COPY(pWriter, writerStride, pReader, readerStride);
      pWriter += writerStride;
      pReader += readerStride;
   }
   return ESuccess;
}

MO_NAMESPACE_END
