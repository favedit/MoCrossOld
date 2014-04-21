#ifndef __MO_FP_TAIL_H__
#define __MO_FP_TAIL_H__
//************************************************************

#ifndef __MO_FP_COMMON_H__
#include "MoFpCommon.h"
#endif // __MO_FP_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>跟踪信息。</T>
//============================================================
struct MO_FP_DECLARE STailInfo{
public:
   // 坐标
   SFloatPoint3 location;
   // 尺寸
   SFloatSize3 size;
   // 旋转
   SFloatVector3 rotation;
   // 背景颜色
   SFloatColor4 groundColor;
   // 纹理坐标
   SFloatCoord coord;
public:
   //------------------------------------------------------------
   // <T>构造跟踪信息。</T>
   STailInfo(){
   }
};
//------------------------------------------------------------
typedef MO_FP_DECLARE TFixVector<STailInfo, 64> TFsTailInfoVector;

//============================================================
// <T>跟踪控制器。</T>
//============================================================
class MO_FP_DECLARE FTailController : public FObject{
protected:
   TInt _limit;
   TInt _interval;
   TFsTailInfoVector _infos;
   TTimeTick _lastTick;
public:
   FTailController();
   MO_ABSTRACT ~FTailController();
public:
   //------------------------------------------------------------
   // <T>获得极限数量。</T>
   MO_INLINE TInt Limit(){
      return _limit;
   }
   //------------------------------------------------------------
   // <T>设置极限数量。</T>
   MO_INLINE void SetLimit(TInt limit){
      _limit = limit;
   }
   //------------------------------------------------------------
   // <T>获得毫秒间隔。</T>
   MO_INLINE TInt Interval(){
      return _interval;
   }
   //------------------------------------------------------------
   // <T>设置极限数量。</T>
   MO_INLINE void SetInterval(TInt interval){
      _interval = interval;
   }
   //------------------------------------------------------------
   // <T>构造信息集合。</T>
   MO_INLINE TFsTailInfoVector& Infos(){
      return _infos;
   }
public:
   TResult Push(STailInfo& info);
   TResult Reset();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FP_TAIL_H__
