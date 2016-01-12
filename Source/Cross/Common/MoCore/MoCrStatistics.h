#ifndef __MO_CR_STATISTICS_H__
#define __MO_CR_STATISTICS_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_SERVICE_H__
#include "MoCrService.h"
#endif // __MO_CR_SERVICE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FStatisticsConsole;

//============================================================
// <T>统计编号。</T>
typedef TUint32 TStatisticsId;
//============================================================
// <T>统计编号</T>
// [16Bit] = 65536 分组类型
// [16Bit] = 65536 代码编号
//============================================================
union SStatisticsData{
   TUint32 value;
   struct{
      TUint16 groupId;
      TUint16 itemId;
   } items;
};
//------------------------------------------------------------
struct MO_CR_DECLARE SStatisticsId{
public:
   SStatisticsData data;
public:
   //------------------------------------------------------------
   // <T>构造统计编号</T>
   SStatisticsId(){
      data.value = 0;
   }
   //------------------------------------------------------------
   // <T>构造统计编号</T>
   SStatisticsId(TStatisticsId id){
      data.value = id;
   }
   //------------------------------------------------------------
   // <T>构造统计编号</T>
   SStatisticsId(TUint16 groupId, TUint16 itemId){
      data.items.groupId = groupId;
      data.items.itemId = itemId;
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool operator==(TStatisticsId value){
      return (data.value == value);
   }
   //------------------------------------------------------------
   // <T>判断是否不相等。</T>
   MO_INLINE TBool operator!=(TStatisticsId value){
      return (data.value != value);
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void operator=(TStatisticsId value){
      data.value = value;
   }
   //------------------------------------------------------------
   // <T>获得内容。</T>
   MO_INLINE operator TStatisticsId(){
      return data.value;
   }
public:
   //------------------------------------------------------------
   // <T>获得分组编号。</T>
   MO_INLINE TUint16 GroupId(){
      return data.items.groupId;
   };
   //------------------------------------------------------------
   // <T>设置分组编号。</T>
   MO_INLINE void SetGroupId(TUint16 groupId){
      data.items.groupId = groupId;
   };
   //------------------------------------------------------------
   // <T>获得项目编号。</T>
   MO_INLINE TUint16 ItemId(){
      return data.items.itemId;
   };
   //------------------------------------------------------------
   // <T>设置项目编号。</T>
   MO_INLINE void SetItemHandle(TUint16 itemId){
      data.items.itemId = itemId;
   };
public:
   //------------------------------------------------------------
   // <T>判断是否有效。</T>
   MO_INLINE TBool IsValid(){
      return (data.value != 0);
   };
   //------------------------------------------------------------
   // <T>获得内容。</T>
   MO_INLINE TStatisticsId Get(){
      return data.value;
   }
   //------------------------------------------------------------
   // <T>设置内容。</T>
   MO_INLINE void Set(TStatisticsId value){
      data.value = value;
   }
   //------------------------------------------------------------
   // <T>重置内容。</T>
   MO_INLINE void Reset(){
      data.value = 0;
   }
public:
   //------------------------------------------------------------
   // <T>获得显示信息。</T>
   TCharC* ToDisplay(TChar* pText, TSize capacity){
      TStringRefer text(pText, capacity);
      text.AssignFormat(TC("%04X:%04X"), data.items.groupId, data.items.itemId);
      return pText;
   }
};

//============================================================
// <T>统计器接口。</T>
//
// @face
//============================================================
class MO_CR_DECLARE IStatistics{
public:
   //------------------------------------------------------------
   // <T>析构统计信息。</T>
   MO_ABSTRACT ~IStatistics(){
   }
public:
   MO_VIRTUAL TStatisticsId Code() = 0;
public:
   MO_VIRTUAL void Begin() = 0;
   MO_VIRTUAL void End() = 0;
public:
   MO_VIRTUAL TResult Update(TBool result = ETrue) = 0;
   MO_VIRTUAL TResult Reset() = 0;
};
//------------------------------------------------------------
typedef FSet<TStatisticsId, IStatistics*> FStatisticsSet;

//============================================================
// <T>统计触发器接口。</T>
//
// @face
//============================================================
class MO_CR_DECLARE IStatisticsTrigger{
public:
   //------------------------------------------------------------
   // <T>析构统计触发器接口。</T>
   MO_ABSTRACT ~IStatisticsTrigger(){
   }
public:
   MO_VIRTUAL TResult StatisticsRefresh() = 0;
};
//------------------------------------------------------------
typedef FList<IStatisticsTrigger*> FStatisticsTriggerList;

//============================================================
// <T>统计器。</T>
//
// @class
//============================================================
class MO_CR_DECLARE FStatistics :
      public FInstance,
      public IStatistics
{
   MO_CLASS_DECLARE_INHERITS(FStatistics, FInstance);
protected:
   // @attribtue 名称
   TString _name;
   // @attribtue 代码
   TStatisticsId _code;
   // @attribtue 执行次数
   TInt _count;
   // @attribtue 失败次数
   TInt _failureCount;
   // @attribtue 执行慢次数
   TInt _slowCount;
   // @attribtue 开始时刻
   TTimeTick _beginTick;
   // @attribtue 结束时刻
   TTimeTick _endTick;
   // @attribtue 最小时段
   TTimeSpan _minSpan;
   // @attribtue 当前时段
   TTimeSpan _currentSpan;
   // @attribtue 最大时段
   TTimeSpan _maxSpan;
   // @attribtue 次数时刻统计
   TTimeSpan _countSpan;
public:
   FStatistics();
   MO_ABSTRACT ~FStatistics();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TStatisticsId Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TStatisticsId code){
      _code = code;
   }
   //------------------------------------------------------------
   // <T>获得执行次数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得失败次数。</T>
   MO_INLINE TInt FailureCount(){
      return _failureCount;
   }
   //------------------------------------------------------------
   // <T>获得执行慢次数。</T>
   MO_INLINE TInt SlowCount(){
      return _slowCount;
   }
   //------------------------------------------------------------
   // <T>获得开始时刻。</T>
   MO_INLINE TTimeTick BeginTick(){
      return _beginTick;
   }
   //------------------------------------------------------------
   // <T>获得结束时刻。</T>
   MO_INLINE TTimeTick EndTick(){
      return _endTick;
   }
   //------------------------------------------------------------
   // <T>获得最小时段。</T>
   MO_INLINE TTimeSpan MinSpan(){
      return _minSpan;
   }
   //------------------------------------------------------------
   // <T>获得当前时段。</T>
   MO_INLINE TTimeSpan CurrentSpan(){
      return _currentSpan;
   }
   //------------------------------------------------------------
   // <T>获得最大时段。</T>
   MO_INLINE TTimeSpan MaxTick(){
      return _maxSpan;
   }
   //------------------------------------------------------------
   // <T>获得次数时刻统计。</T>
   MO_INLINE TTimeTick CountSpan(){
      return _countSpan;
   }
   //------------------------------------------------------------
   // <T>获得平均执行时刻。</T>
   MO_INLINE TTimeTick AverageTick(){
      if(_count > 0){
         return _countSpan / _count;
      }
      return 0;
   }
public:
   //------------------------------------------------------------
   // <T>开始处理。</T>
   MO_ABSTRACT void Begin(){
      _beginTick = RTimeTick::Current();
   }
   //------------------------------------------------------------
   // <T>结束处理。</T>
   MO_ABSTRACT void End(){
      _endTick = RTimeTick::Current();
   }
public:
   MO_ABSTRACT TResult Update(TBool result = ETrue);
   MO_ABSTRACT TResult Finish(TBool result = ETrue);
   MO_ABSTRACT TResult Reset();
public:
   MO_ABSTRACT TResult Dump(MString* pDump);
   MO_ABSTRACT TResult Track();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE GPtr<FStatistics> GStatisticsPtr;
typedef MO_CR_DECLARE FDictionary<FStatistics*> FStatisticsDictionary;

//============================================================
// <T>统计信息监视器。</T>
//
// @class
//============================================================
class MO_CR_DECLARE FStatisticsMonitor : public FMonitor{
protected:
   FStatisticsConsole* _pConsole;
public:
   FStatisticsMonitor();
   MO_ABSTRACT ~FStatisticsMonitor();
public:
   //------------------------------------------------------------
   // <T>获得触发器。</T>
   MO_INLINE FStatisticsConsole* Console(){
      return _pConsole;
   }
   //------------------------------------------------------------
   // <T>设置触发器。</T>
   MO_INLINE void SetConsole(FStatisticsConsole* pConsole){
      _pConsole = pConsole;
   }
public:
   MO_OVERRIDE TResult Process();
};

//============================================================
// <T>统计信息控制台。</T>
//
// @class
// @history 130407 MAOCY 创建
//============================================================
class MO_CR_DECLARE FStatisticsConsole : public FConsole{
protected:
   FStatisticsDictionary* _pStatisticsDictionary;
   FStatisticsSet* _pStatisticsSet;
   FStatisticsTriggerList* _pStatisticsTriggers;
   FStatisticsMonitor* _pMonitor;
public:
   FStatisticsConsole();
   MO_ABSTRACT ~FStatisticsConsole();
public:
   //------------------------------------------------------------
   // <T>获得统计器集合。</T>
   MO_INLINE FStatisticsSet* StatisticsSet(){
      return _pStatisticsSet;
   }
   //------------------------------------------------------------
   // <T>获得触发器集合。</T>
   MO_INLINE FStatisticsTriggerList* StatisticsTriggers(){
      return _pStatisticsTriggers;
   }
   //------------------------------------------------------------
   // <T>获得监视器。</T>
   MO_INLINE FStatisticsMonitor* Monitor(){
      return _pMonitor;
   }
public:
   FStatistics* SyncByName(TCharC* pName, FClass* pClass = NULL);
   //------------------------------------------------------------
   // <T>根据名称同步统计信息。</T>
   template <class T>
   MO_INLINE T* SyncByName(TCharC* pName){
      FInstance* pInstance = SyncByName(pName, T::Class());
      return pInstance->Convert<T>();
   }
public:
   IStatistics* StatisticsFind(TStatisticsId code);
   IStatistics* StatisticsFind(TInt groupId, TInt itemId);
   IStatistics* StatisticsSync(TStatisticsId code);
   IStatistics* StatisticsSync(TInt groupId, TInt itemId);
public:
   TResult StatisticsRegister(IStatistics* pStatistics);
   TResult StatisticsUnregister(IStatistics* pStatistics);
public:
   TResult TriggerRegister(IStatisticsTrigger* pTrigger);
   TResult TriggerUnregister(IStatisticsTrigger* pTrigger);
public:
   TResult StartupMonitor();
   MO_OVERRIDE TResult TriggerRefresh(TTimeTick currentTick);
public:
   MO_ABSTRACT TResult Reset();
   MO_ABSTRACT TResult Track();
};

//============================================================
// <T>统计信息管理器。</T>
//============================================================
class MO_CR_DECLARE RStatisticsManager : public RSingleton<FStatisticsConsole>{
};

MO_NAMESPACE_END

#endif // __MO_CR_STATISTICS_H__
