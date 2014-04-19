#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造界面画板。</T>
//============================================================
FUiCanvas::FUiCanvas(){
}

//============================================================
// <T>析构界面画板。</T>
//============================================================
FUiCanvas::~FUiCanvas(){
}

//============================================================
// <T>绘制界面边框。</T>
//
// @param color 颜色
// @param border 边框
// @return 处理结果
//============================================================
TResult FUiCanvas::DrawBorder(SIntRectangle* pRectangle, SUiBorder* pborder){
   // 检查参数
   MO_CHECK(pRectangle, return ENull);
   MO_CHECK(pborder, return ENull);
   // 计算边界
   TInt left = pRectangle->left;
   TInt top = pRectangle->top;
   TInt right = pRectangle->Right();
   TInt bottom = pRectangle->Bottom();
   // 绘制右边线
   DrawLineVertical(pborder->right.color, top, bottom, right);
   // 绘制下边线
   DrawLineHorizontal(pborder->bottom.color, left, right, bottom);
   // 绘制左边线
   DrawLineVertical(pborder->left.color, top, bottom, left);
   // 绘制上边线
   DrawLineHorizontal(pborder->top.color, left, right, top);
   return ESuccess;
}

MO_NAMESPACE_END
