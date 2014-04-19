#include "MoFmCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>计算停靠位置。</T>
//
// @param pLocation 输出位置
// @param clientRect 客户区域
// @param targetSize 目标大小
// @param dockCd 停靠方式
// @param pDockPadding 停靠间距
// @return 处理结果
//============================================================
TResult RUiUtil::CalculateDock(SIntPoint2* pLocation, SIntRectangle* pClientRect, SIntSize2* pTargetSize, EControlDock dockCd, SIntPadding* pDockPadding){
   MO_CHECK(pLocation, return ENull);
   MO_CHECK(pClientRect, return ENull);
   MO_CHECK(pTargetSize, return ENull);
   // 获得停靠间距
   TInt paddingLeft = 0;
   TInt paddingTop = 0;
   TInt paddingRight = 0;
   TInt paddingBottom = 0;
   if(pDockPadding != NULL){
      paddingLeft = pDockPadding->left;
      paddingTop = pDockPadding->top;
      paddingRight = pDockPadding->right;
      paddingBottom = pDockPadding->bottom;
   }
   // 计算参数
   TInt width = pClientRect->width - pTargetSize->width;
   TInt height = pClientRect->height - pTargetSize->height;
   TInt centerX = width >> 1;
   TInt centerY = height >> 1;
   // 设置停靠位置
   switch(dockCd){
      case EControlDock_Left:
         // 靠左边
         pLocation->Set(paddingLeft, centerY);
         break;
      case EControlDock_LeftTop:
         // 靠左上边
         pLocation->Set(paddingLeft, paddingTop);
         break;
      case EControlDock_Top:
         // 靠上边
         pLocation->Set(centerX, paddingTop);
         break;
      case EControlDock_RightTop:
         // 靠右上边
         pLocation->Set(width - paddingRight, paddingTop);
         break;
      case EControlDock_Right:
         // 靠右边
         pLocation->Set(width - paddingRight, centerY);
         break;
      case EControlDock_RightDown:
         // 靠右下边
         pLocation->Set(width - paddingRight, height - paddingBottom);
         break;
      case EControlDock_Down:
         // 靠下边
         pLocation->Set(centerX, height - paddingBottom);
         break;
      case EControlDock_LeftDown:
         // 靠左下边
         pLocation->Set(paddingLeft, height - paddingBottom);
         break;
      case EControlDock_Center:
         // 靠中间
         pLocation->Set(centerX, centerY);
         break;
   }
   return ESuccess;
}

MO_NAMESPACE_END
