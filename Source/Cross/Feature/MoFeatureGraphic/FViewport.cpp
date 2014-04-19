#include "MoFgViewport.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FViewport, FInstance);

//============================================================
// <T>构造视角。</T>
//============================================================
FViewport::FViewport(){
}

//============================================================
// <T>析构视角。</T>
//============================================================
FViewport::~FViewport(){
}

//============================================================
// <T>设置信息。</T>
//
// @param left 左边
// @param top 上边
// @param width 宽度
// @param height 高度
//============================================================
void FViewport::Set(TInt left, TInt top, TInt width, TInt height){
   _position.Set(left, top);
   _size.Set(width, height);
}

MO_NAMESPACE_END
