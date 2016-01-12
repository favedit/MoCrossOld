#include "MoCrStatistics.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FStatistics, FInstance);

//============================================================
// <T>构造统计器。</T>
//============================================================
FStatistics::FStatistics(){
   _code = 0;
   Reset();
}

//============================================================
// <T>析构统计器。</T>
//============================================================
FStatistics::~FStatistics(){
}

//============================================================
// <T>更新处理。</T>
//
// @param result 结果
// @return 处理结果
//============================================================
TResult FStatistics::Update(TBool result){
   _count++;
   if(!result){
      _failureCount++;
   }
   TTimeSpan span = _endTick - _beginTick;
   // 计算最小时刻
   if(_minSpan == 0){
      _minSpan = span;
   }else if(span < _minSpan){
      _minSpan = span;
   }
   // 计算最大时刻
   if(_maxSpan == 0){
      _maxSpan = span;
   }else if(span > _maxSpan){
      _maxSpan = span;
   }
   // 检查执行速度 (10毫秒)
   if(span > 10000){
      _slowCount++;
   }
   // 计算总时间
   _currentSpan = span;
   _countSpan += span;
   return ESuccess;
}

//============================================================
// <T>结束处理。</T>
//
// @param result 结果
// @return 处理结果
//============================================================
TResult FStatistics::Finish(TBool result){
   End();
   return Update(result);
}

//============================================================
// <T>重置处理。</T>
//
// @return 处理结果
//============================================================
TResult FStatistics::Reset(){
   _count = 0;
   _failureCount = 0;
   _slowCount = 0;
   _beginTick = 0;
   _endTick = 0;
   _minSpan = 0;
   _currentSpan = 0;
   _maxSpan = 0;
   _countSpan = 0;
   return ESuccess;
}

//============================================================
// <T>获得跟踪信息。</T>
//
// @return 处理结果
//============================================================
TResult FStatistics::Dump(MString* pDump){
   MO_ASSERT(pDump);
   TTimeTick averageSpan = AverageTick();
   pDump->AppendFormat(TC("%-40s: count=%6d, span=%6lld (%6lld ~ %6lld), average_span=%6lld, count_span=%8lld"),
         (TCharC*)_name, _count, _currentSpan, _minSpan, _maxSpan, averageSpan, _countSpan);
   return ESuccess;
}

//============================================================
// <T>显示跟踪信息。</T>
//
// @return 处理结果
//============================================================
TResult FStatistics::Track(){
   TFsDump dump;
   TResult resultCd = Dump(&dump);
   MO_INFO((TCharC*)dump);
   return resultCd;
}

MO_NAMESPACE_END
