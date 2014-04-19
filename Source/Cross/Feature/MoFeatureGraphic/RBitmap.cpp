#include "MoFgBitmap.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>根据内容计算最大于2的幂的最合适数据。</T>
//
// @param value 内容
// @return 合适数据
//============================================================
TInt RBitmap::CalculateFitLength(TInt value){
   if(value > 2048){
      return 4096;
   }else if(value > 1024){
      return 2048;
   }else if(value > 512){
      return 1024;
   }else if(value > 256){
      return 512;
   }else if(value > 128){
      return 256;
   }else if(value > 64){
      return 128;
   }else if(value > 32){
      return 64;
   }else if(value > 16){
      return 32;
   }else if(value > 8){
      return 16;
   }else if(value > 4){
      return 8;
   }else if(value > 2){
      return 4;
   }else if(value > 1){
      return 2;
   }
   return 1;
}

//============================================================
// <T>计算三个像素的插值。</T>
//============================================================
TColor RBitmap::MergeColor3(
      SFloatPoint2 position,
      SFloatPoint2 position1, TColor color1,
      SFloatPoint2 position2, TColor color2,
      SFloatPoint2 position3, TColor color3){
   return 0;
}

//============================================================
// <T>小图块解压操作。</T>
//
// @param bitmapData 位图数据
// @param pData 数据
// @param dataLength 数据长度
// @return 解压结果
//============================================================
TBool RBitmap::ConvertToBitmapData(SBitmapData& bitmapData, TByteC* pData, TInt dataLength){
   MO_TRACK_DEFINE( TSpeedTest speed );
   // 检查参数
   if(NULL == bitmapData.pData){
      MO_STATIC_ERROR("Parameter bitmap data is null.");
      return EFalse;
   }
   if(NULL == pData){
      MO_STATIC_ERROR("Parameter data is null.");
      return EFalse;
   }
   if(0 == dataLength){
      MO_STATIC_ERROR("Parameter data length is empty.");
      return EFalse;
   }
   //............................................................
   // 复制数据
   TInt width = bitmapData.size.width;
   TInt height = bitmapData.size.height;
   TInt size = sizeof(TUint32) * width * height;
   if(size != dataLength){
      MO_STATIC_ERROR("Parameter data length is invalid. (size=%d, data_length=%d).", size, dataLength);
      return EFalse;
   }
   TByteC* pReader = pData;
   TByte* pWriter = bitmapData.pData;
   TInt readerStride = sizeof(TUint32) * width;
   TInt writerStride = bitmapData.lineStride;
   writerStride = readerStride;
   if(0 == bitmapData.isInvertedY){
      // 图片从上向下
      for(TInt y = 0; y < height; y++){
         MO_LIB_MEMORY_COPY(pWriter, writerStride, pReader, readerStride);
         pReader += readerStride;
         pWriter += writerStride;
      }
   }else{
      // 指向最后位置
      pWriter += writerStride * height;
      // 图片从下向上
      for(TInt y = 0; y < height; y++){
         pWriter -= writerStride;
         MO_LIB_MEMORY_COPY(pWriter, writerStride, pReader, readerStride);
         pReader += readerStride;
      }
   }
   //............................................................
   MO_STATIC_TRACK(speed.Record(), TC("Convert to bitmap data. (bitmap=0x%08X, inverted_y=%d, size=%dx%d, line_stride=%d)"),
         &bitmapData, bitmapData.isInvertedY, bitmapData.size.width, bitmapData.size.height, bitmapData.lineStride);
   return ETrue;
}

//============================================================
// <T>小图块解压操作。</T>
//
// @param bitmapData 位图数据
// @param validLoction 有效坐标
// @param validSize 有效大小
// @param pData 数据
// @param dataLength 数据长度
// @return 解压结果
//============================================================
TBool RBitmap::ConvertToBitmapData(SBitmapData& bitmapData, SIntPoint2& validLoction, SIntSize2& validSize, TByteC* pData, TInt dataLength){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
      // 检查参数
   if(NULL == bitmapData.pData){
      MO_STATIC_ERROR("Parameter bitmap data is null.");
      return EFalse;
   }
   if(NULL == pData){
      MO_STATIC_ERROR("Parameter data is null.");
      return EFalse;
   }
   if(0 == dataLength){
      MO_STATIC_ERROR("Parameter data length is empty.");
      return EFalse;
   }
   //............................................................
   // 复制数据
   TInt width = bitmapData.size.width;
   TInt height = bitmapData.size.height;
   TInt validWidth = validSize.width;
   TInt validHeight = validSize.height;
   if((validWidth > width) || (validHeight > height)){
      MO_STATIC_FATAL(TC("Valid size is invalid."));
      return EFalse;
   }
   TInt readerStride = sizeof(TUint32) * validSize.width;
   TInt writerStride = sizeof(TUint32) * width;
   // 新计算读写位置
   TByteC* pReader = pData;
   TInt writerOffset = sizeof(TUint32) * validLoction.x;
   TInt readerBytes = sizeof(TUint32) * validSize.width;
   if(0 == bitmapData.isInvertedY){
      TByte* pWriter = bitmapData.pData + (writerStride * validLoction.y);
      // 图片从上向下
      for(TInt y = 0; y < validHeight; y++){
         MO_LIB_MEMORY_COPY((pWriter + writerOffset), writerStride, pReader, readerBytes);
         pReader += readerStride;
         pWriter += writerStride;
      }
   }else{
      // 指向最后位置
      TByte* pWriter = bitmapData.pData + (writerStride * (height - validLoction.y));
      // 图片从下向上
      for(TInt y = 0; y < validHeight; y++){
         pWriter -= writerStride;
         MO_LIB_MEMORY_COPY((pWriter + writerOffset), writerStride, pReader, readerBytes);
         pReader += readerStride;
      }
   }
   //............................................................
   MO_STATIC_TRACK(speed.Record(), TC("Convert to bitmap data. (bitmap=0x%08X, inverted_y=%d, size=%dx%d, valid_location=%d,%d, valid_size=%dx%d, line_stride=%d)"),
         &bitmapData, bitmapData.isInvertedY, width, height, validLoction.x, validLoction.y, validSize.width, validSize.height, bitmapData.lineStride);
   return ETrue;
}

//============================================================
// <T>覆盖图像。</T>
//
// @param dest 目的图像
// @param x    横坐标
// @param y    纵坐标
// @param source 源图像 
//============================================================
TBool RBitmap::Copy(SBitmapData& dest, TInt x, TInt y, SBitmapData& source){
   TUint32* pCopyAddress = (TUint32*)dest.pData;
   TUint32* pSourceAddress = (TUint32*)source.pData;
   TInt destWidth = dest.size.width;
   TInt destHeight = dest.size.height;
   TInt sourceWidth = source.size.width;
   TInt sourceHeight = source.size.height;
   TInt copyRow = 0;
   TInt copyWidth;
   if((x > 0 && x >= destWidth) || (y > 0 && y >= destHeight)){
      MO_STATIC_DEBUG("Copy coordinate is invlid!");
      return EFalse;
   }
   if(x < 0 && (-x >= sourceWidth) ||
      y < 0 && (-y >= sourceHeight)){
      MO_STATIC_DEBUG("Copy coordinate is invlid!");
      return EFalse;
   }
   if(y >= 0){
      // 纵坐标在目标图像内
      pCopyAddress += destWidth * y;
      copyRow = MO_LIB_MIN((destHeight - y), sourceHeight);
   }else{
      // 纵坐标在目标图像外
      pSourceAddress += sourceWidth * -y;
      copyRow = MO_LIB_MIN(destHeight, (sourceHeight + y));
   }
   if(x >= 0){
      // 横坐标在图像内
      pCopyAddress += x;
      copyWidth = MO_LIB_MIN(destWidth - x, sourceWidth);
   }else{
      // 横坐标在图像外
      pSourceAddress += x;
      copyWidth = MO_LIB_MIN(destWidth, (sourceWidth + x));
   }
   TInt copyWidthByte = copyWidth * sizeof(TUint32);
   for(TInt n = 0; n < copyRow; n++){
      // 逐行拷贝
      MO_LIB_MEMORY_COPY(pCopyAddress, copyWidthByte, pSourceAddress, copyWidthByte);
      pCopyAddress += destWidth;
      pSourceAddress += sourceWidth;
   }
   return ETrue;
}

//============================================================
// <T>卷动图像。</T>
//
// @parma bitmap 位图
// @parma scrollX 横向滚动
// @parma scrollY 纵向滚动
//============================================================
TBool RBitmap::Scroll(SBitmapData& bitmapData, TInt scrollX, TInt scrollY){
   TUint32* pData = (TUint32*)bitmapData.pData;
   TUint32* pCopyAddress = pData;
   TUint32* pSourceAddress = pData;
   TInt bitmapWidth = bitmapData.size.width;
   TInt bitmapHeight = bitmapData.size.height;
   TInt changeRow = 0;
   TInt copyWidth = bitmapWidth;
   TInt absScrollX = (scrollX < 0) ? -scrollX : scrollX;
   TInt absScrollY = (scrollY < 0) ? -scrollY : scrollY;
   if(0 == scrollX && 0 == scrollY){
      // 没有发生滚动
      changeRow = 0;
      return ETrue;
   }
   if(absScrollY >= bitmapHeight || absScrollX >= bitmapWidth){
      // 滚动超过位图
      return ETrue;
   }
   if(scrollY > 0){
      // 纵向向下移动
      changeRow = bitmapHeight - absScrollY;
      pCopyAddress = pData + absScrollY * bitmapWidth;
   }else if(scrollY < 0){
      // 纵向向上移动
      changeRow = bitmapHeight - absScrollY;
      pSourceAddress = pData + absScrollY * bitmapWidth;
     
   }else{
      // 纵向不移动
      changeRow = 0;
   }
   if(scrollX > 0){
      // 横向向右移动
      copyWidth = bitmapWidth - scrollX;
      pCopyAddress += absScrollX;
      if(0 == changeRow){
         changeRow = bitmapHeight;
      }

   }else if (scrollX < 0){
      // 横向向左移动
      copyWidth = bitmapWidth + scrollX;
      pSourceAddress += absScrollX;
      if(0 == changeRow){
         changeRow = bitmapHeight;
      }
   }
   if(0 == scrollX){
      // 横向没有移动
      memmove((void*)pCopyAddress, (void*)pSourceAddress, (copyWidth * changeRow)*sizeof(TUint32));
   }else{
      if(scrollY > 0){
         // 从后往前拷贝
         TInt endOffset = bitmapWidth * changeRow;
         TInt copyWidthByte = copyWidth*sizeof(TUint32);
         pCopyAddress += endOffset;
         pSourceAddress += endOffset;
         for(TInt n = 0; n < changeRow; n++){
            pCopyAddress -= bitmapWidth;
            pSourceAddress -= bitmapWidth;
            MO_LIB_MEMORY_COPY((void*)pCopyAddress, copyWidthByte, (void*)pSourceAddress, copyWidthByte);
         }
      }else{
         // 从前往后拷贝
         TInt copyWidthByte = copyWidth*sizeof(TUint32);
         for(TInt n = 0; n < changeRow; n++){
            MO_LIB_MEMORY_COPY((void*)pCopyAddress, copyWidthByte, (void*)pSourceAddress, copyWidthByte);
            pCopyAddress += bitmapWidth;
            pSourceAddress += bitmapWidth;
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>缩小图片数据一倍。</T>
//
// @parma width 宽度
// @parma height 高度
// @parma pInputData 输入数据
// @parma pOutputData 输出数据
// @parma outputLength 输出长度
//============================================================
TInt RBitmap::CompressData2(TInt width, TInt height, TByte* pInputData, TByte* pOutputData){
   // 计算大小
   TInt width2 = width >> 1;
   TInt height2 = height >> 1;
   TUint32* pReader = (TUint32*)pInputData;
   TUint32* pWriter = (TUint32*)pOutputData;
   // 处理图形
   if((1 == width) && (1 == height)){
      // 不处理
   }else if(1 == width){
      // 宽度为1
      for(TInt y = 0; y < height2; y++){
         TInt n = y << 1;
         // 获得四点颜色
         TUint32 p1 = pReader[n        ];
         TUint32 p2 = pReader[n + width];
         // 计算平均颜色
         TUint32 b1 = (((p1 >> 24) & 0xFF) + ((p2 >> 24) & 0xFF)) >> 1;
         TUint32 b2 = (((p1 >> 16) & 0xFF) + ((p2 >> 16) & 0xFF)) >> 1;
         TUint32 b3 = (((p1 >>  8) & 0xFF) + ((p2 >>  8) & 0xFF)) >> 1;
         TUint32 b4 = (( p1        & 0xFF) + ( p2        & 0xFF)) >> 1;
         // 写出计算平均颜色
         *pWriter++ = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
      }
   }else if(1 == height){
      // 高度为1
      for(TInt x = 0; x < width2; x++){
         TInt n = x << 1;
         // 获得四点颜色
         TUint32 p1 = pReader[n    ];
         TUint32 p2 = pReader[n + 1];
         // 计算平均颜色
         TUint32 b1 = (((p1 >> 24) & 0xFF) + ((p2 >> 24) & 0xFF)) >> 1;
         TUint32 b2 = (((p1 >> 16) & 0xFF) + ((p2 >> 16) & 0xFF)) >> 1;
         TUint32 b3 = (((p1 >>  8) & 0xFF) + ((p2 >>  8) & 0xFF)) >> 1;
         TUint32 b4 = (( p1        & 0xFF) + ( p2        & 0xFF)) >> 1;
         // 写出计算平均颜色
         *pWriter++ = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
      }
   }else{
      // 处理所有像素
      for(TInt y = 0; y < height2; y++){
         for(TInt x = 0; x < width2; x++){
            TInt n = (width * y + x) << 1;
            // 获得四点颜色
            TUint32 p1 = pReader[n            ];
            TUint32 p2 = pReader[n         + 1];
            TUint32 p3 = pReader[n + width    ];
            TUint32 p4 = pReader[n + width + 1];
            // 计算平均颜色
            TUint32 b1 = (((p1 >> 24) & 0xFF) + ((p2 >> 24) & 0xFF) + ((p3 >> 24) & 0xFF) + ((p4 >> 24) & 0xFF)) >> 2;
            TUint32 b2 = (((p1 >> 16) & 0xFF) + ((p2 >> 16) & 0xFF) + ((p3 >> 16) & 0xFF) + ((p4 >> 16) & 0xFF)) >> 2;
            TUint32 b3 = (((p1 >>  8) & 0xFF) + ((p2 >>  8) & 0xFF) + ((p3 >>  8) & 0xFF) + ((p4 >>  8) & 0xFF)) >> 2;
            TUint32 b4 = (( p1        & 0xFF) + ( p2        & 0xFF) + ( p3        & 0xFF) + ( p4        & 0xFF)) >> 2;
            // 写出计算平均颜色
            *pWriter++ = (b1 << 24) | (b2 << 16) | (b3 << 8) | b4;
         }
      }
   }
   return (TByte*)pWriter - pOutputData;
}

//============================================================
// <T>小图块解压操作。</T>
//
// <P> Flash用的颜色需要透明左乘:
// TUint32 color = pPalette[*pReader++];
// TUint32 a =    (color >> 24) & 0xFF;
// TUint32 r = ((((color >> 16) & 0xFF) * a) >> 8) & 0xFF;
// TUint32 g = ((((color >>  8) & 0xFF) * a) >> 8) & 0xFF;
// TUint32 b = ((((color      ) & 0xFF) * a) >> 8) & 0xFF;
// TUint32 result = (a << 24) | (r << 16) | (g << 8) | b;
// </P>
//============================================================
TInt RBitmap::DecodeBlock(IDataInput* pInput, IDataOutput* pOutput){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
   // 检查参数
   if(NULL == pInput){
      return EFailure;
   }
   if(NULL == pOutput){
      return EFailure;
   }
   // 读取属性
   TInt length = pInput->ReadInt32();
   if(length <= 0){
      return EFailure;
   }
   TBool optionAlpha = pInput->ReadBool();
   TInt width = pInput->ReadInt16();
   TInt height = pInput->ReadInt16();
   TInt size = width * height;
   TInt total = sizeof(TUint32) * size;
   // 读取调色板
   TInt colorCount = pInput->ReadInt16();
   TUint32* pPalette = (TUint32*)pInput->PositionMemory();
   pInput->Skip(sizeof(TUint32) * colorCount);
   // 获得写指针
   TUint32* pWriter = (TUint32*)pOutput->PositionMemory();
   // 写入小图块的颜色值
   TInt n = -1;
   TByte* pReader = pInput->PositionMemory();
   if(optionAlpha){
      // 读取含有透明通道位图
      while(++n < size){
         // 读取颜色索引，获得颜色值和透明度
         TUint32 color = pPalette[*pReader++] & 0x00FFFFFF;
         TUint32 alpha = *pReader++;
         // 写入颜色值
         *pWriter++ = (alpha << 24) + color;
      }
      pInput->Skip(size << 1);
   }else{
      // 读取不含有透明通道位图
      while(++n < size){
         *pWriter++ = pPalette[*pReader++];
      }
      pInput->Skip(size);
   }
   // 调整读取位置
   pOutput->Skip(total);
   MO_STATIC_TRACK(speed.Record(), TC("Decode bitmap block. (option_alpha=%d, size=%dx%d, color_count=%d)"), optionAlpha, width, height, colorCount);
   return total;
}

MO_NAMESPACE_END
