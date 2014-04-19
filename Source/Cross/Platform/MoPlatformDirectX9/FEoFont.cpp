#include <freetype\ftglyph.h>
#include "MoEoFont.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEoFont, FFont);

//============================================================
// <T>构造字体。</T>
//============================================================
FEoFont::FEoFont(){
   RType<FT_Face>::Clear(&_face);
   _pData = MO_CREATE(FBytes);
}

//============================================================
// <T>析构字体。</T>
//============================================================
FEoFont::~FEoFont(){
   MO_DELETE(_pData);
}

//============================================================
// <T>打开处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoFont::Open(){
   FFont::Open();
   // 加载数据
   RAssetManager::Instance().OpenData(_pData, _assetName);
   // 创建表面对象
   FEoFontConsole* pConsole = (FEoFontConsole*)_pConsole;
   FT_Library& library = pConsole->NativeLibrary();
   FT_Error faceResult = FT_New_Memory_Face(library, _pData->MemoryC(), _pData->Length(), 0, &_face);
   //MO_DELETE(pBytes);
   if(faceResult){
      MO_FATAL("Create face failure. (error=%d)", faceResult);
   }
   // 设置字符大小
   FT_Error sizeResult = FT_Set_Pixel_Sizes(_face, _charSize.width, _charSize.height);
   if(sizeResult){
      MO_FATAL("Set pixel size failure. (error=%d)", sizeResult);
   }
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoFont::Close(){
   // 释放资源
   FT_Done_Face(_face);
   // 关闭处理
   FFont::Close();
   return ESuccess;
}

//============================================================
// <T>绘制一个字符。</T>
// @param pBitmapData 位图数据
// @param pFontInfo 字体信息
// @param pCharInfo 字符信息
// @param x 横坐标
// @param y 纵坐标
// @param value 内容
//============================================================
TResult FEoFont::InnerDrawChar(EFontDrawMode modeCd, FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, SFontCharInfo* pCharInfo, TInt positionX, TInt positionY, TInt value){
   // 加载字形
   TInt index = FT_Get_Char_Index(_face, value);
   FT_Load_Glyph(_face, index, FT_LOAD_DEFAULT);
   // 获得字形
   FT_Glyph glyph;
   FT_Error glyphResult = FT_Get_Glyph(_face->glyph, &glyph);
   MO_FATAL_CHECK(glyphResult == 0, return EFailure,
         "Get glyph failure. (error=%d)", glyphResult);
   // 渲染位图
   FT_Error bitmapResult = FT_Glyph_To_Bitmap(&glyph, FT_RENDER_MODE_NORMAL, NULL, ETrue);
   MO_FATAL_CHECK(bitmapResult == 0, return EFailure,
         "Glyph to bitmap failure. (error=%d)", bitmapResult);
   //............................................................
   // 获得位图信息
   FT_BitmapGlyph glyphBitmap = (FT_BitmapGlyph)glyph;
   TInt charX = glyphBitmap->left;
   TInt charY = _charSize.height - glyphBitmap->top;
   FT_Bitmap& bitmap = glyphBitmap->bitmap;
   TInt bitmapWidth = bitmap.width;
   TInt bitmapHeight = bitmap.rows;
   pCharInfo->left = charX;
   pCharInfo->top = charY;
   pCharInfo->width = charX + bitmapWidth;
   pCharInfo->height = charY + bitmapHeight;
   if(modeCd == EFontDrawMode_Test){
      return ESuccess;
   }
   //............................................................
   // 计算有效范围
   TInt width = bitmapWidth;
   TInt right = pRectangle->Right();
   if(positionX + charX + bitmapWidth > right){
      width = right - charX - positionX;
   }
   TInt height = bitmapHeight;
   TInt bottom = pRectangle->Bottom();
   if(positionY + charY + bitmapHeight > bottom){
      height = bottom - charY - positionY;
   }
   //............................................................
   // 分解颜色
   TInt targetR = ((pFontInfo->color      ) & 0xFF);
   TInt targetG = ((pFontInfo->color >> 8 ) & 0xFF);
   TInt targetB = ((pFontInfo->color >> 16) & 0xFF);
   TInt targetA = ((pFontInfo->color >> 24) & 0xFF);
   //............................................................
   // 绘制处理
   TByte* pReader = bitmap.buffer;
   TInt memoryStride = pBitmapData->DataStride();
   TByte* pMemory = pBitmapData->Memory() + (memoryStride * (positionY + charY)) + (4 * (positionX + charX));
   for(TInt y = 0 ; y < height; y++){
      TUint32* pWriter = (TUint32*)pMemory;
      for(TInt x = 0 ; x < width; x++){
         TColor sourceColor = *pWriter;
         TInt targetRate = *pReader++;
         TInt sourceRate = 256 - targetRate;
         TInt r = (((sourceColor      ) & 0xFF) * sourceRate + targetR * targetRate) >> 8;
         TInt g = (((sourceColor >> 8 ) & 0xFF) * sourceRate + targetG * targetRate) >> 8;
         TInt b = (((sourceColor >> 16) & 0xFF) * sourceRate + targetB * targetRate) >> 8;
         TInt a = (((sourceColor >> 24) & 0xFF) * sourceRate + targetA * targetRate) >> 8;
         *pWriter++ = r + (g << 8) + (b << 16) + (a << 24);
      }
      pMemory += memoryStride;
   }
   FT_Done_Glyph(glyph);
   MO_CLEAR(glyph);
   return ESuccess;
}

//============================================================
// <T>绘制字符串。</T>
//
// @param pBitmapData 位图数据
// @param pRectangle 范围（如果为空，怎默认范围为整张位图）
// @param pFontInfo 字体信息
// @param pText 文本内容
//============================================================
TResult FEoFont::InnerDrawText(EFontDrawMode modeCd, FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TWideCharC* pText){
   // 检查参数
   if(modeCd == EFontDrawMode_Draw){
      MO_CHECK(pBitmapData, return ENull);
   }
   MO_CHECK(pText, return ENull);
   // 设置参数
   SFontInfo* pInfo = pFontInfo;
   if(pInfo == NULL){
      pInfo = &_fontInfo;
   }
   TInt startX = 0;
   TInt startY = 0;
   if(modeCd == EFontDrawMode_Draw){
      if(pRectangle == NULL){
         pRectangle = &pBitmapData->Rectangle();
      }
      startX = pRectangle->left;
      startY = pRectangle->top;
   }
   //............................................................
   // 设置倾斜
   FT_Matrix matrix;
   matrix.xx = 0x10000L;
   matrix.yy = 0x10000L;
   matrix.yx = 0;
   if(pInfo->italic){
      matrix.xy = (FT_Fixed)(0.5f * 0x10000L);
   }else{
      matrix.xy = 0;
   }
   FT_Set_Transform(_face, &matrix, 0);
   //............................................................
   // 绘制字符串
   TInt width = 0;
   TInt height = 0;
   TInt minLeft = _charSize.width;
   TInt minTop = _charSize.height;
   TInt x = startX;
   TInt y = startY;
   SFontCharInfo charInfo;
   TBool fix = pInfo->fix;
   TInt length = RWideString::Length(pText);
   for(TInt n = 0; n < length; n++){
      TInt value = pText[n];
      // 处理特殊字符
      if(value == '\r'){
         continue;
      }
      // 处理回车
      if(value == '\n'){
         x = startX;
         y += _charSize.height;
         continue;
      }
      // 处理缩进(3字宽)
      if(value == '\t'){
         x += _charSize.width * 3;
         continue;
      }
      // 绘制字符
      InnerDrawChar(modeCd, pBitmapData, pRectangle, pInfo, &charInfo, x, y, value);
      // 调整下个字符位置
      if(fix){
         x += _charSize.width;
      }else{
         x += charInfo.width;
      }
      // 计算范围
      if(charInfo.top < minTop){
         minTop = charInfo.top;
      }
      if(charInfo.left < minLeft){
         minLeft = charInfo.left;
      }
      TInt offsetX = x - startX;
      if(offsetX > width){
         width = offsetX;
      }
      TInt offsetY = y + charInfo.height - startY;
      if(offsetY > height){
         height = offsetY;
      }
   }
   // 设置输出信息
   if(pFontInfo != NULL){
      pFontInfo->width = width + minLeft;
      pFontInfo->height = height + minTop;
   }
   return ESuccess;
}

//============================================================
// <T>计算字符串大小。</T>
//
// @param pFontInfo 字体信息
// @param pText 文本内容
//============================================================
TResult FEoFont::CalculateAnsiSize(SFontInfo* pFontInfo, TAnsiCharC* pText){
   // 字符转码
   TString16 value;
   value.Assign8(pText);
   TChar16C* pWideText = value.MemoryC();
   // 计算尺寸
   return InnerDrawText(EFontDrawMode_Test, NULL, NULL, pFontInfo, pWideText);
}

//============================================================
// <T>计算字符串大小。</T>
//
// @param pFontInfo 字体信息
// @param pText 文本内容
//============================================================
TResult FEoFont::CalculateWideSize(SFontInfo* pFontInfo, TWideCharC* pText){
   return InnerDrawText(EFontDrawMode_Test, NULL, NULL, pFontInfo, pText);
}

//============================================================
// <T>绘制字符串。</T>
//
// @param pBitmapData 位图数据
// @param pRectangle 范围（如果为空，怎默认范围为整张位图）
// @param pFontInfo 字体信息
// @param pText 文本内容
//============================================================
TResult FEoFont::DrawAnsiText(FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TAnsiCharC* pText){
   // 字符转码
   TString16 value;
   value.Assign8(pText);
   TChar16C* pWideText = value.MemoryC();
   // 绘制文字
   return InnerDrawText(EFontDrawMode_Draw, pBitmapData, pRectangle, pFontInfo, pWideText);
}

//============================================================
// <T>绘制字符串。</T>
//
// @param pBitmapData 位图数据
// @param pRectangle 范围（如果为空，怎默认范围为整张位图）
// @param pFontInfo 字体信息
// @param pText 文本内容
//============================================================
TResult FEoFont::DrawWideText(FBitmapData* pBitmapData, SIntRectangle* pRectangle, SFontInfo* pFontInfo, TWideCharC* pText){
   return InnerDrawText(EFontDrawMode_Draw, pBitmapData, pRectangle, pFontInfo, pText);
}

MO_NAMESPACE_END
