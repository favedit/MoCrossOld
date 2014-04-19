#include "MoFgBitmap.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FImage, FInstance);

//============================================================
// <T>构造图形。</T>
//============================================================
FImage::FImage(){
   _formatCd = EImageFormat_A8R8G8B8;
}

//============================================================
// <T>析构图形。</T>
//============================================================
FImage::~FImage(){
}

MO_NAMESPACE_END
