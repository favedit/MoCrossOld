#include <iomanip>
#include <MoJpeg.h>
#include "MoCompress.h"

using namespace MoCompress;

//============================================================
// <T>压缩图片颜色数据。</T>
// <P>channelCd=1:RGB颜色</P>
// <P>channelCd=2:Alpha颜色为彩色图</P>
// <P>channelCd=3:Alpha颜色为单色图</P>
//============================================================
int RCompressJpg::Compress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength, int width, int height, int quality, int channelCd){
   // 获得输入数据
   unsigned char* pInput = (unsigned char*)malloc(inputLength);
   pin_ptr<System::Byte> inputPtr = &input[inputOffset];
   memcpy(pInput, (void*)inputPtr, inputLength);
   // 声明并初始化解压缩对象，同时制定错误信息管理器
   struct jpeg_compress_struct cinfo;
   // 设置错误处理
   struct jpeg_error_mgr jerr;
   cinfo.err = jpeg_std_error(&jerr);
   jpeg_create_compress(&cinfo);
   cinfo.progressive_mode = false;
   cinfo.image_width = width;
   cinfo.image_height = height;
   if(3 == channelCd){
      cinfo.input_components = 1;
      cinfo.in_color_space = JCS_GRAYSCALE;
   }else{
      cinfo.input_components = 3;
      cinfo.in_color_space = JCS_RGB;
   }
   // 设定输出数据
   unsigned char* pWrite = NULL;
   unsigned long writeLength = 0;
   jpeg_mem_dest(&cinfo, &pWrite, &writeLength);
   // 设置默认参数
   jpeg_set_defaults(&cinfo);
   // 设置压缩品质
   jpeg_set_quality(&cinfo, quality, TRUE);
   //jpeg_quality_scaling(quality);
   //jpeg_set_linear_quality(&cinfo, 16, TRUE);
   // 开始压缩处理
   jpeg_start_compress(&cinfo, TRUE);
   // 压缩全部数据
   int widthStride = 0;
   if(0 == channelCd){
      widthStride = 3 * width;
   }else{
      widthStride = 4 * width;
   }
   unsigned char* pLine = (unsigned char*)malloc(widthStride);
   JSAMPROW rowPtr = pLine;
   while(cinfo.next_scanline < cinfo.image_height){
      int linePosition = 0;
      int inputPosition = widthStride * cinfo.next_scanline;
      for(int n = 0; n < width; n++){
         if(channelCd == 0){
            pLine[linePosition    ] = pInput[inputPosition + 2];
            pLine[linePosition + 1] = pInput[inputPosition + 1];
            pLine[linePosition + 2] = pInput[inputPosition    ];
            linePosition += 3;
            inputPosition += 3;
         }else if(channelCd == 1){
            pLine[linePosition    ] = pInput[inputPosition + 2];
            pLine[linePosition + 1] = pInput[inputPosition + 1];
            pLine[linePosition + 2] = pInput[inputPosition    ];
            linePosition += 3;
            inputPosition += 4;
         }else if(channelCd == 2){
            pLine[linePosition    ] = pInput[inputPosition + 3];
            pLine[linePosition + 1] = pInput[inputPosition + 3];
            pLine[linePosition + 2] = pInput[inputPosition + 3];
            linePosition += 3;
            inputPosition += 4;
         }else if(channelCd == 3){
            pLine[linePosition    ] = pInput[inputPosition + 3];
            linePosition += 1;
            inputPosition += 4;
         }
      }
      jpeg_write_scanlines(&cinfo, &rowPtr, 1);
   }
   free(pLine);
   // 压缩全部完成
   jpeg_finish_compress(&cinfo);
   // 输出压缩数据
   if(writeLength > 0){
      pin_ptr<System::Byte> outputPtr = &output[outputOffset];
      memcpy((void*)outputPtr, (void*)pWrite, writeLength);
   }
   // 释放所有资源
   jpeg_destroy_compress(&cinfo);
   free(pInput);
   return writeLength; 
}

//============================================================
// <T>解压图片颜色数据。</T>
//============================================================
int RCompressJpg::Uncompress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength){
   int result = 0;
   return result;
}
