#include "MoCmCompress.h"
#ifdef _MO_LINUX
#include <zlib.h>
#endif // _MO_LINUX

MO_NAMESPACE_BEGIN

//============================================================
// <T>压缩数据。</T>
//
// @param pOutput 输出数据
// @param outputCapacity 输出数据容量
// @param pOutput 输入数据
// @param outputLength 输入长度
// @param pLength 处理长度
// @return 是否成功
//============================================================
TBool RCompress::Deflate(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength){
   MO_ASSERT(pOutput);
   MO_ASSERT(pInput);
   MO_ASSERT(pLength);
#ifdef _MO_LINUX
   // 设置数据
   z_stream stream;
   memset(&stream, 0, sizeof(z_stream));
   stream.next_in = (Bytef*)pInput;
   stream.avail_in = inputlength;
   stream.next_out = (Bytef*)pOutput;
   stream.avail_out = outputCapacity;
   // 初始化
   TInt initResult = deflateInit(&stream, Z_BEST_SPEED);
   if(initResult != Z_OK){
      return EFalse;
   }
   // 压缩数据
   while((0 != stream.avail_in) && ((TInt)stream.total_out < outputCapacity)){
      TInt flushResult = deflate(&stream, Z_NO_FLUSH);
      if(flushResult != Z_OK){
         return EFalse;
      }
   }
   // 压缩结束
   while(ETrue){
      TInt finishResult = deflate(&stream, Z_FINISH);
      if(Z_STREAM_END == finishResult){
         break;
      }
      if(Z_OK != finishResult){
         return EFalse;
      }
   }
   // 释放内容
   TInt endResult = deflateEnd(&stream);
   if(endResult != Z_OK){
      return EFalse;
   }
   *pLength = stream.total_out;
#endif
   return ETrue;
}

//============================================================
// <T>解压缩数据。</T>
//
// @param pOutput 输出数据
// @param outputCapacity 输出数据容量
// @param pOutput 输入数据
// @param outputLength 输入长度
// @param pLength 处理长度
// @return 是否成功
//============================================================
TBool RCompress::Inflate(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength){
   MO_ASSERT(pOutput);
   MO_ASSERT(pInput);
   MO_ASSERT(pLength);
#ifdef _MO_LINUX
   // 设置数据
   z_stream stream;
   memset(&stream, 0, sizeof(z_stream));
   stream.next_in = (Bytef*)pInput;
   stream.avail_in = inputlength;
   stream.next_out = (Bytef*)pOutput;
   stream.avail_out = outputCapacity;
   // 初始化
   TInt initResult = inflateInit(&stream);
   if(initResult != Z_OK){
      return EFalse;
   }
   // 解压缩数据
   while(((TInt)stream.total_in < inputlength) && ((TInt)stream.total_out < outputCapacity)){
      TInt flushResult = inflate(&stream, Z_NO_FLUSH);
      if(Z_STREAM_END == flushResult){
         break;
      }
      if(Z_OK != flushResult){
         return EFalse;
      }
   }
   // 释放内容
   TInt endResult = inflateEnd(&stream);
   if(endResult != Z_OK){
      return EFalse;
   }
   *pLength = stream.total_out;
#endif // _MO_LINUX
   return ETrue;
}

//============================================================
// <T>压缩数据。</T>
//
// @param pOutput 压缩后数据。
// @param outputLength 压缩后长度。
// @param pOutput 压缩前数据。
// @param outputLength 压缩前长度。
// @return 是否成功
//============================================================
TBool RCompress::CompressRLE(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength){
   TByte* pWrite = &pOutput[2];
   // 统计各个字符出现次数
   TInt array[256];
   memset(array, 0, sizeof(array));
   for(TInt n = 0; n < inputlength; n++){
      TInt value = pInput[n];
      array[value]++;
   }
   // 找到出现次数最少的
   TInt a = 0;
   for(TInt n = 0; n < 256; n++){
      if(array[n] < array[a]){
         a = n;
      }
   }
   TByte k = (TByte)a;
   // 将控制字符放入
   TInt length = 1;
   pOutput[length] = k;
   length++;
   // 压缩
   for(TInt n = 0; n < inputlength; n++){
      // 如果当前字符是控制字符
      if(k == pInput[n]){
         *pWrite++ = k;
         *pWrite++ = k;
         TInt count = 1;
         for(TInt m = n + 1; m < inputlength; m++){
            if(pInput[m] == pInput[n]){
               count++;
               n = m;
               if(count == 255){
                  break;
               }
            }else{
               break;
            }
         }
         *pWrite++ = count;
      }else{
         // 当前点不是控制字符
         TInt count = 1;
         for(TInt m = n + 1; m < inputlength; m++){
            if(pInput[m] == pInput[n]){
               count++;
               n = m;
               if(count == 255){
                  break;
               }
            }else{
               break;
            }
         }
         // 如果当前字符与后续字符相同
         if(count > 1){
            *pWrite++ = k;
            *pWrite++ = pInput[n];
            *pWrite++ = count;
         }else{
            *pWrite++ = pInput[n];
         }
      }
   }
   // 计算长度
   length = pWrite - pOutput;
   if(length + 1 > outputCapacity){
      return EFalse;
   }
   if(inputlength + 1 > length){
      pOutput[0] = 'Y';
   }else{
      pOutput[0] = 'N';
      if(0 < inputlength){
         memcpy(pOutput + 1, pInput, inputlength);
      }
      length = 1 + inputlength;
   }
   outputCapacity = length;
   *pLength = length;
   return ETrue;
}

//============================================================
// <T>压缩数据。</T>
//
// @param pOutput 解压后数据。
// @param outputLength 解压后长度。
// @param pOutput 解压前数据。
// @param outputLength 解压前长度。
// @return 是否成功
//============================================================
TBool RCompress::UncompressRLE(TByte* pOutput, TInt outputCapacity, TByteC* pInput, TInt inputlength, TInt* pLength){
   if(0 == inputlength){
      *pLength = 0;
      return ETrue;
   }
   // 是否进行压缩
   if(pInput[0] == 'Y'){
      TByte* pWrite = pOutput;
      // 获得控制字符
      TChar k = pInput[1];
      for(TInt n = 2; n < inputlength; n++){
         if(pInput[n] == k){
            TByte value = pInput[++n];
            TInt count = pInput[++n];
            for(TInt m = 0; m < count; m++){
               *pWrite++ = value;
            }
         }else{
            *pWrite++ = pInput[n];
         }
      }
      TInt length = pWrite - pOutput;
      if(length > outputCapacity){
         return EFalse;
      }
      *pLength = length;
   }else{
      if(outputCapacity < inputlength){
         return EFalse;
      }
      for(TInt n = 1; n < inputlength; n++){
         pOutput[n -1] = pInput[n];
      }
      *pLength = inputlength;
   }
   return ETrue;
}

MO_NAMESPACE_END
