#include "MoEgDisplay.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造位图画板。</T>
//============================================================
FBitmapCanvas::FBitmapCanvas(){
}

//============================================================
// <T>析构位图画板。</T>
//============================================================
FBitmapCanvas::~FBitmapCanvas(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FBitmapCanvas::Setup(){
   return FBitmap::Setup();
}

//============================================================
// <T>更新处理。</T>
//
// @return 处理结果
//============================================================
TResult FBitmapCanvas::Update(){
   return FBitmap::Update();
}

//============================================================
// <T>绘制一条直线。</T>
//
// @param color 颜色
// @param x1 开始横坐标
// @param y1 开始纵坐标
// @param x2 结束横坐标
// @param y2 结束纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawLine(TColor color, TInt x1, TInt y1, TInt x2, TInt y2){
   // 获得属性
   TInt width = _size.width;
   TInt height = _size.height;
   TColor* pColors = (TColor*)_data->Memory();
   // 计算因子
   TInt k = 0;
   TInt x12 = x2 - x1;
   TInt y12 = y2 - y1;
   if(x12 * y12 < 0){
      k = 1;
   }
   TInt x = x1;
   TInt y = y1;
   TInt dx = MO_LIB_ABS(x12);
   TInt dy = MO_LIB_ABS(y12);
   TBool f = 0;
   if(dx < dy){
      f = 1;
      MO_LIB_SWAP(TInt,dx,dy);
      MO_LIB_SWAP(TInt,x,y);
   }
   TInt e = (dy << 1) - dx;
   // 绘制处理
   for(TInt n = 0; n <= dx; n++){
      if(f == 1){
         TInt index = x * width + y;
         pColors[index] = color;
      }else{
         TInt index = y * width + x;
         pColors[index] = color;
      }
      x++;
      if(0 < e){
         e = e + ((dy - dx) << 1);
         if(0 == k){
            y++;
         }else{
            y--;
         }
      }else{
         e = e + (dy << 1);
      }
   }
   return ESuccess;
}

//============================================================
// <T>绘制一条横向直线。</T>
//
// @param color 颜色
// @param x1 开始横坐标
// @param x2 结束横坐标
// @param y 纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawLineHorizontal(TColor color, TInt x1, TInt x2, TInt y){
   // 获得属性
   TInt width = _size.width;
   TColor* pColors = (TColor*)_data->Memory();
   // 获取有效区域
   TInt bottom = _clipRectangle.Bottom();
   TInt right = _clipRectangle.Right();
   TInt left = _clipRectangle.left;
   TInt top = _clipRectangle.top;
   // 判断是否超出画板范围
   if((y < top) || (y > bottom)){
      return EFailure;
   }
   if(x1 < left){
      x1 = left;
   }
   if(x2 >= right){
      x2 = right;
   }
   // 绘制横向直线
   TInt index = width * y + x1;
   for(TInt n = x1; n <= x2; n++){
      pColors[index++] = color;
   }
   return ESuccess;
}

//============================================================
// <T>绘制一条纵向直线。</T>
//
// @param color 颜色
// @param y1 开始纵坐标
// @param y2 结束纵坐标
// @param x 横坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawLineVertical(TColor color, TInt y1, TInt y2, TInt x){
   // 获得属性
   TInt width = _size.width;
   TColor* pColors = (TColor*)_data->Memory();
   // 获得有效区域
   TInt bottom = _clipRectangle.Bottom();
   TInt right = _clipRectangle.Right();
   TInt left = _clipRectangle.left;
   TInt top = _clipRectangle.top;
   // 判断是否超出画板范围
   if((x < left) || (x > right)){
      return EFailure;
   }
   if(y1 < top){
      y1 = top;
   }
   if(y2 >= bottom){
      y2 = bottom;
   }
   // 绘制纵向直线
   TInt index = width * y1 + x;
   for(TInt n = y1; n <= y2; n++){
      pColors[index] = color;
      index += width;
   }
   return ESuccess;
}

//============================================================
// <T>绘制一个三角形。</T>
//
// @param color 颜色
// @param x1 第一个点横坐标
// @param y1 第一个点纵坐标
// @param x2 第二点点横坐标
// @param y2 第二个点纵坐标
// @param x3 第三点点横坐标
// @param y3 第三个点纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawTriangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2, TInt x3, TInt y3){
   // 绘制第一条线
   DrawLine(color, x1, y1, x2, y2);
   // 绘制第二条线
   DrawLine(color, x2, y2, x3, y3);
   // 绘制第三条线
   DrawLine(color, x1, y1, x3, y3);
   return ESuccess;
}

//============================================================
// <T>绘制一个矩形。</T>
//
// @param color 颜色
// @param x1 开始横坐标
// @param y1 开始纵坐标
// @param x2 结束横坐标
// @param y2 结束纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawRectangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2){
   TInt width = _size.width;
   TInt height = _size.height;
   TColor* pColors = (TColor*)_data->Memory();
   // 绘制左边框
   DrawLineVertical(color, y1, y2, x1);
   // 绘制上边框
   DrawLineHorizontal(color, x1, x2, y1);
   // 绘制右边框
   DrawLineVertical(color, y1, y2, x2);
   // 绘制下边框
   DrawLineHorizontal(color, x1, x2, y2);
   return ESuccess;
}

//============================================================
// <T>绘制一个矩形。</T>
//
// @param color 颜色
// @param rectangle 矩形
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawRectangle(TColor color, SIntRectangle* pRectangle){
   MO_CHECK(pRectangle, return ENull);
   return DrawRectangle(color, pRectangle->left, pRectangle->top, pRectangle->Right(), pRectangle->Bottom());
}

//============================================================
// <T>填充一个三角形。</T>
//
// @param color 颜色
// @param x1 第一个点横坐标
// @param y1 第一个点纵坐标
// @param x2 第二点点横坐标
// @param y2 第二个点纵坐标
// @param x3 第三点点横坐标
// @param y3 第三个点纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::FillTriangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2, TInt x3, TInt y3){
   // 获得属性
   TInt width = _size.width;
   TColor* pColors = (TColor*)_data->Memory();
   // 绘制第一条线
   DrawLine(color, x1, y1, x2, y2);
   // 绘制第二条线
   DrawLine(color, x2, y2, x3, y3);
   // 绘制第三条线
   DrawLine(color, x1, y1, x3, y3);
   // 获得区域
   TInt minX;
   TInt minY;
   TInt maxX;
   TInt maxY;
   minX = MO_LIB_MIN(x1,x2);
   minX = MO_LIB_MIN(x3,minX);
   maxX = MO_LIB_MAX(x1,x2);
   maxX = MO_LIB_MAX(x3,maxX);
   minY = MO_LIB_MIN(y1,y2);
   minY = MO_LIB_MIN(y3,minY);
   maxY = MO_LIB_MAX(y1,y2);
   maxY = MO_LIB_MAX(y3,maxY);
   // 获取开始位置索引
   //TInt index = minY * width +minX;
   TInt beginX;
   TInt endX;
   // 开始填充三角形
   for(TInt y = minY; y <= maxY; y++){
      for(TInt x = minX; x <= maxX; x++){
         TInt index = y * width + x;
         TColor nowColor = pColors[index];
         if(color == nowColor){
            beginX = x;
            break;
         }
         index+=width;
      }
      for(TInt x = maxX; x >= minX; x--){
         TInt index =y * width +x;
         TColor nowColor = pColors[index];
         if(color == nowColor){
            endX = x;
            break;
         }
         index+=width;
      }
      if(beginX != endX){
         TInt nowIndex= y * width +beginX;
         for(TInt x = beginX; x <= endX; x++){
            pColors[nowIndex] = color;
            nowIndex ++;
         }
      }
   }
   return ESuccess;
}

//============================================================
// <T>填充一个矩形。</T>
//
// @param color 颜色
// @param x1 开始横坐标
// @param y1 开始纵坐标
// @param x2 结束横坐标
// @param y2 结束纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::FillRectangle(TColor color, TInt x1, TInt y1, TInt x2, TInt y2){
   // 获得属性
   TInt width = _size.width;
   TColor* pColors = (TColor*)_data->Memory();
   // 获取有效区域
   TInt right = _clipRectangle.Right();
   TInt bottom = _clipRectangle.Bottom();
   TInt left = _clipRectangle.left;
   TInt top = _clipRectangle.top;
   // 判断位置
   if(x1 > x2){
      MO_LIB_SWAP(TInt, x1, x2);
   }
   if(y1 > y2){
      MO_LIB_SWAP(TInt, y1, y2);
   }
   // 判断是否超出范围
   if(x1 < left){
      x1 = left;
   }
   if(y1 < top){
      y1 = top;
   }
   if(x2 > right){
      x2 = right;
   }
   if(y2 > bottom){
      y2 = bottom;
   }
   // 获取开始位置索引
   TInt index = width * y1 + x1;
   // 填充第一行
   TInt w = x2 - x1;
   TColor* pLine = pColors + index;
   for(TInt i = 0; i <= w; i++){
      *pLine++ = color;
   }
   // 填充剩余行
   TInt length = sizeof(TColor) * (x2 - x1 + 1);
   TInt beginIndex = index + width;
   for(TInt n = y1+1; n <= y2; n++){
      MO_LIB_MEMORY_COPY(pColors + beginIndex, length, pColors + index, length);
      beginIndex += width;
   }
   return ESuccess;
}

//============================================================
// <T>填充一个矩形。</T>
//
// @param color 颜色
// @param rectangle 矩形
// @return 处理结果
//============================================================
TResult FBitmapCanvas::FillRectangle(TColor color, SIntRectangle* pRectangle){
   MO_CHECK(pRectangle, return ENull);
   return FillRectangle(color, pRectangle->left, pRectangle->top, pRectangle->Right(), pRectangle->Bottom());
}

//============================================================
// <T>绘制一个位图。</T>
//
// @param pBitmapData 位图
// @param x 横坐标
// @param y 纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawBitmap(FBitmapData* pBitmapData, TBool alpha, TInt x, TInt y){
   DrawBitmap(pBitmapData, alpha, NULL, x, y);
   return ESuccess;
}

//============================================================
// <T>绘制一个位图。</T>
//
// @param pBitmapData 位图
// @param pSourceRectangle 来源范围
// @param x 横坐标
// @param y 纵坐标
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawBitmap(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, TInt x, TInt y){
   // 获得属性
   TInt width = _size.width;
   TInt height = _size.height;
   TColor* pColors = (TColor*)_data->Memory();
   // 获取范围
   TInt right = _clipRectangle.Right();
   TInt bottom = _clipRectangle.Bottom();
   TInt left = _clipRectangle.left;
   TInt top = _clipRectangle.top;
   // 获得位图
   TColor* pBitmapColors =  (TColor*)pBitmapData->Memory();
   TInt bitmapWidth = pBitmapData->Size().width;
   TInt bitmapHeight = pBitmapData->Size().height;
   TInt bitmapRight;
   TInt bitmapBottom;
   if(NULL == pSourceRectangle){
      bitmapRight = x + bitmapWidth;
      bitmapBottom = y + bitmapHeight;
   }else {
      bitmapRight = pSourceRectangle->Right();
      bitmapBottom = pSourceRectangle->Bottom();
   }
   // 获取有效开始位置和结束位置
   if(x < left){
      x = left;
   }
   if(y < top){
      y = top;
   }
   if(bitmapRight > right){
      bitmapRight = right;
   }
   if(bitmapBottom > bottom){
      bitmapBottom = bottom;
   }
   // 获取有效宽度和有效高度
   TInt bitmapValidWidth;
   TInt bitmapValidHeight;
   if(NULL == pSourceRectangle){
      bitmapValidWidth = bitmapRight - x;
      bitmapValidHeight = bitmapBottom - y;
   }else {
      bitmapValidWidth = pSourceRectangle->width;
      bitmapValidHeight = pSourceRectangle->height;
   }
   // 判断是否进行绘制
   if(bitmapValidWidth <= 0 || bitmapValidHeight <= 0){
      return EFailure;
   }
   // 开始绘制
   if(alpha){
      TInt targetIndex = width * y + x;
      TInt sourceIndex = bitmapWidth * pSourceRectangle->top + pSourceRectangle->left;
      for(TInt n = 0; n < bitmapValidHeight; n++){
         TColor* pTarget = pColors + targetIndex;
         TColor* pSource = pBitmapColors + sourceIndex;
         for(TInt m = 0; m < bitmapValidWidth; m++){
            *pTarget++ = RBitmap::MergeColor(*pTarget, *pSource++);
         }
         targetIndex += width;
         sourceIndex += bitmapWidth;
      }
   } else {
      TInt length = sizeof(TColor) * (bitmapValidWidth + 1);
      TInt targetIndex = width * y + x;
      TInt sourceIndex = bitmapWidth * pSourceRectangle->top + pSourceRectangle->left;
      for(TInt n = 0; n < bitmapValidHeight; n++){
         MO_LIB_MEMORY_COPY(pColors + targetIndex, length, pBitmapColors + sourceIndex, length);
         targetIndex += width;
         sourceIndex += bitmapWidth;
      }
   }
   return ESuccess;
}

//============================================================
// <T>绘制一个位图。</T>
//
// @param pBitmapData 位图
// @param alpha 是否透明
// @param pSourceRectangle 来源范围
// @param pTargetRectangle 目标范围
//============================================================
TResult FBitmapCanvas::DrawBitmap(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, SIntRectangle* pTargetRectangle){
   // 获取属性
   TInt width = _size.width;
   TColor* pColors = (TColor*)_data->Memory();
   // 获取范围
   TInt right = _clipRectangle.Right();
   TInt bottom = _clipRectangle.Bottom();
   TInt left = _clipRectangle.left;
   TInt top = _clipRectangle.top;
   // 获取位图
   TColor* pBitmapColors =  (TColor*)pBitmapData->Memory();
   TInt bitmapWidth = pBitmapData->Size().width;
   // 获取来源矩形
   TInt sourceWidth = pSourceRectangle->width;
   TInt sourceHeight = pSourceRectangle->height;
   TInt sourceleft = pSourceRectangle->left;
   TInt sourceTop = pSourceRectangle->top;
   TInt sourceRight = pSourceRectangle->Right();
   TInt sourceBottom = pSourceRectangle->Bottom();
   // 获取目标矩形
   TInt targetWidth = pTargetRectangle->width;
   TInt targetHeight = pTargetRectangle->height;
   TInt targetLeft = pTargetRectangle->left;
   TInt targetTop = pTargetRectangle->top;
   TInt targetRight = pTargetRectangle->Right();
   TInt targetBottom = pTargetRectangle->Bottom();
   // 获取有效位置
   if(targetLeft < left){
      targetLeft = left;
   }
   if(targetTop < top){
      targetTop = top;
   }
   if(targetRight > right ){
      targetRight = right;
   }
   if(targetBottom > bottom){
      targetBottom = bottom;
   }
   // 获取比率
   TFloat widthRatio = (TFloat)sourceWidth / targetWidth;
   TFloat heightRatio = (TFloat)sourceHeight / targetHeight;
   // 遍历像素点获得点坐标
   TInt maxX = targetRight - targetLeft;
   TInt maxY = targetBottom - targetTop;
   for(TInt y = 0; y <= maxY; y++){
      TFloat sourceY = heightRatio * y;
      for(TInt x = 0; x <= maxX; x++){
         TFloat sourceX = widthRatio * x;
         // 获取当前像素点坐标
         TInt locationX = ((TInt)sourceX) + sourceleft;
         TInt locationY = (TInt)sourceY + sourceTop;
         TInt bitmapIndex = locationY * bitmapWidth + locationX;
         TInt index = (y + targetTop) * width + (x + targetLeft);
         pColors[index] = pBitmapColors[bitmapIndex];  
      }
   }
   return ESuccess;
}

//============================================================
// <T>绘制一个9宫格位图。</T>
//
// @param pBitmapData 位图
// @param pSourcePadding 来源间隔
// @param pTargetRectangle 目标范围
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawBitmapGrid9(FBitmapData* pBitmapData, TBool alpha, SIntPadding* pSourcePadding, SIntRectangle* pTargetRectangle){
   // 获取位图属性
   TInt bitmapWidth = pBitmapData->OriginSize().width;
   TInt bitmapHeight = pBitmapData->OriginSize().height;
   // 获取目标矩形属性
   TInt targetWidth = pTargetRectangle->width;
   TInt targetHeight = pTargetRectangle->height;
   TInt targetTop = pTargetRectangle->top;
   TInt targetLeft = pTargetRectangle->left;
   TInt targetRight = pTargetRectangle->Right();
   TInt targetBottom = pTargetRectangle->Bottom();
   // 获取偏移量
   TInt paddingLeft = pSourcePadding->left;
   TInt paddingTop = pSourcePadding->top;
   TInt paddingRight = pSourcePadding->right;
   TInt paddingBottom = pSourcePadding->bottom;
   // 获取来源中间矩形大小
   TInt sourceCenterWidth = bitmapWidth - paddingLeft -paddingRight;
   TInt sourceCenterHeight = bitmapHeight - paddingTop - paddingBottom;
   // 获取来源中间矩形结束点位置
   TInt sourceRightBottomX = bitmapWidth -paddingRight;
   TInt sourceRightBottomY = bitmapHeight -paddingBottom;
   // 获取目标中间矩形大小
   TInt targetCenterWidth = targetWidth - paddingLeft -paddingRight;
   TInt targetCenterHeight = targetHeight - paddingTop - paddingBottom;
   TInt targetCenterLeft = targetLeft + paddingLeft;
   TInt targetCenterTop = targetTop + paddingTop;
   // 获取目标中间矩形结束点位置
   TInt targetRightBottomX = targetLeft + targetWidth -paddingRight;
   TInt targetRightBottomY = targetTop + targetHeight -paddingBottom;
   // 绘制左上
   if (0 != paddingLeft && 0 != paddingTop){
      SIntRectangle leftTopRectanle(0, 0, paddingLeft, paddingTop);
      DrawBitmap(pBitmapData, alpha, &leftTopRectanle, targetLeft, targetTop);
   }
   // 绘制右上
   if(0 != paddingRight && 0 != paddingTop){
      SIntRectangle rightTopRectanle(sourceRightBottomX, 0, paddingRight, paddingTop);
      DrawBitmap(pBitmapData, alpha, &rightTopRectanle, targetRightBottomX, targetTop);
   }
   // 绘制右下
   if(0 != paddingRight && 0 != paddingBottom){
      SIntRectangle rightBottomRectanle(sourceRightBottomX, sourceRightBottomY, paddingRight, paddingBottom);
      DrawBitmap(pBitmapData, alpha, &rightBottomRectanle, targetRightBottomX, targetRightBottomY);
   }
   // 绘制左下
   if(0 != paddingLeft && 0 != paddingBottom){
      SIntRectangle leftBottomRectanle(0, sourceRightBottomY, paddingLeft, paddingBottom);
      DrawBitmap(pBitmapData, alpha, &leftBottomRectanle, targetLeft, targetRightBottomY);
   }
   // 绘制中上
   if(0 != targetCenterWidth && 0 != paddingTop){
      SIntRectangle centerTopSourceRectanle(paddingLeft, 0, sourceCenterWidth, paddingTop);
      SIntRectangle centerTopTargetRectanle(targetCenterLeft, targetTop, targetCenterWidth, paddingTop);
      DrawBitmap(pBitmapData, alpha, &centerTopSourceRectanle, &centerTopTargetRectanle);
   }
   // 绘制中右
   if(0 != paddingRight && 0 != targetCenterHeight){
      SIntRectangle centerRightSourceRectanle(sourceRightBottomX, paddingTop, paddingRight, sourceCenterHeight);
      SIntRectangle centerRightTargetRectanle(targetCenterLeft + targetCenterWidth, targetCenterTop, paddingRight, targetCenterHeight);
      DrawBitmap(pBitmapData, alpha, &centerRightSourceRectanle, &centerRightTargetRectanle);
   }
   // 绘制中下
   if(0 != targetCenterWidth && 0 != paddingBottom){
      SIntRectangle centerBottomSourceRectanle(paddingLeft,sourceRightBottomY,sourceCenterWidth,paddingBottom);
      SIntRectangle centerBottomTargetRectanle(targetCenterLeft, targetCenterTop + targetCenterHeight, targetCenterWidth, paddingBottom);
      DrawBitmap(pBitmapData, alpha, &centerBottomSourceRectanle, &centerBottomTargetRectanle);
   }
   // 绘制中左
   if(0 != paddingLeft && 0 != sourceCenterHeight){
      SIntRectangle centerLeftSourceRectanle(0, paddingTop, paddingLeft, sourceCenterHeight);
      SIntRectangle centerLeftTargetRectanle(targetLeft, targetCenterTop, paddingLeft, targetCenterHeight);
      DrawBitmap(pBitmapData, alpha, &centerLeftSourceRectanle, &centerLeftTargetRectanle);
   }
   // 绘制中
   if(0 != targetCenterWidth && 0 != targetCenterHeight){
      SIntRectangle centerSourceRectanle(paddingLeft, paddingTop, sourceCenterWidth, sourceCenterHeight);
      SIntRectangle centerTargetRectanle(targetCenterLeft, targetCenterTop, targetCenterWidth, targetCenterHeight);
      DrawBitmap(pBitmapData, alpha, &centerSourceRectanle, &centerTargetRectanle);
   }
   return ESuccess;
}

//============================================================
// <T>绘制一个9宫格位图。</T>
//
// @param pBitmapData 位图
// @param pSourceRectangle 来源范围
// @param pSourcePadding 来源间隔
// @param pTargetRectangle 目标范围
// @return 处理结果
//============================================================
TResult FBitmapCanvas::DrawBitmapGrid9(FBitmapData* pBitmapData, TBool alpha, SIntRectangle* pSourceRectangle, SIntPadding* pSourcePadding, SIntRectangle* pTargetRectangle){
   return ESuccess;
}

MO_NAMESPACE_END
