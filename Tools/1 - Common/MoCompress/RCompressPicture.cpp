#include "MoCompressPicture.h"
#include "MoCompress.h"

using namespace MoCompress;

//============================================================
// <T>压缩图片颜色数据。</T>
//============================================================
int RCompressPicture::Compress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength){
   int result = 0;
   size_t dstDataLen = inputLength + 1024;
   TByte* pCont = (TByte*)malloc(dstDataLen);
   // 获得输入数据
   TByte* pInput = (TByte*)malloc(inputLength);
   pin_ptr<System::Byte> inputPtr = &input[inputOffset];
   memcpy(pInput, (void*)inputPtr, inputLength);
   // 取得宽高数据
   TInt width  = *(TInt*)pInput[0];
   TInt height = *(TInt*)pInput[4];
   // 除去宽和高的8个字节。
   TInt colorDataLength = inputLength - 8;
   TByte* pColorDate = pInput + 8;
   // 构造树
   FComOctree* pTree = new FComOctree();
   FComColor color;
   for(TInt n = 0; n < colorDataLength; n += 4){
      color.red   = pColorDate[n];
      color.green = pColorDate[n + 1];
      color.blue  = pColorDate[n + 2];
      color.alpha = pColorDate[n + 3];
      pTree->AddColor(color);
   }
   TInt numberColorCount = pTree->GetColorCount();
   return result; 
}

//============================================================
// <T>解压图片颜色数据。</T>
//============================================================
int RCompressPicture::Uncompress(array<System::Byte>^ output, int outputOffset, int outputLength, array<System::Byte>^ input, int inputOffset, int inputLength){
   int result = 0;
   return result;
}
