#ifndef __MO_FG_VIEWPORT_H__
#define __MO_FG_VIEWPORT_H__
//************************************************************

#ifndef __MO_FG_COMMON_H__
#include "MoFgCommon.h"
#endif // __MO_FG_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>视角。</T>
//============================================================
class MO_FG_DECLARE FViewport : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FViewport, FInstance);
protected:
   SIntPoint2 _position;
   SIntSize2 _size;
public:
   FViewport();
   MO_ABSTRACT ~FViewport();
public:
   //------------------------------------------------------------
   // <T>设置位置。</T>
   MO_INLINE SIntPoint2& Position(){
      return _position;
   }
   //------------------------------------------------------------
   // <T>设置尺寸。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
public:
   void Set(TInt left, TInt top, TInt width, TInt height);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FG_VIEWPORT_H__
