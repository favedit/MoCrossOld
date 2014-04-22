#include "MoEgBitmap.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造位图画板纹理。</T>
//============================================================
FBitmap::FBitmap(){
}

//============================================================
// <T>析构位图画板纹理。</T>
//============================================================
FBitmap::~FBitmap(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FBitmap::Setup(){
   return ESuccess;
}

//============================================================
// <T>改变大小。</T>
//
// @param width 宽度
// @param height 高度
// @return 处理结果
//============================================================
TResult FBitmap::Resize(TInt width, TInt height){
   return ESuccess;
}

//============================================================
// <T>过滤渲染区域。</T>
//
// @param pRegion 渲染区域
// @return 处理结果
//============================================================
TResult FBitmap::FilterRegion(FRenderRegion* pRegion){
   if(_texture){
      //pRegion->Renderables()->Push(_texture);
   }
   return ESuccess;
}

//============================================================
// <T>清空数据。</T>
//
// @param color 颜色
//============================================================
TResult FBitmap::Clear(TColor color){
   TInt size = (TInt)_size.Square();
   TColor* pWriter = (TColor*)_data->Memory();
   for(TInt n = 0; n < size; n++){
      *pWriter++ = color;
   }
   return ESuccess;
}

//============================================================
// <T>清空数据。</T>
//
// @param color 颜色
// @param pRectangle 矩形范围
//============================================================
TResult FBitmap::Clear(TColor color, SIntRectangle* pRectangle){
   // 检查参数
   MO_CHECK(pRectangle, return ENull);
   // 获得属性
   TInt left = pRectangle->left;
   TInt top = pRectangle->top;
   TInt width = pRectangle->width;
   TInt height = pRectangle->height;
   // 检查范围
   if(left + width > _size.width){
      //width = _size.width - left;
   }
   if(top + height > _size.height){
      //height = _size.height - top;
   }
   // 清空内容
   //TInt stride = _size.width - width;
   //TColor* pWriter = (TColor*)_data->Memory() + (_size.width * top) + left;
   //for(TInt y = 0; y < height; y++){
   //   for(TInt x = 0; x < width; x++){
   //      *pWriter++ = color;
   //   }
   //   pWriter += stride;
   //}
   return ESuccess;
}

//============================================================
// <T>析构位图画板。</T>
//
// @return 处理结果
//============================================================
TResult FBitmap::Update(){
   return ESuccess;
}

//============================================================
// <T>释放位图数据。</T>
//============================================================
void FBitmap::FreeBitmap(){
   //_data->Free();
}

//============================================================
// <T>释放纹理数据。</T>
//============================================================
void FBitmap::FreeTexture(){
}

MO_NAMESPACE_END
