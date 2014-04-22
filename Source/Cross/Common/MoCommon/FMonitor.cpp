#include "MoCmXml.h"
#include "MoCmMonitor.h"

#define MO_MONITOR_INTERVAL 1000000
#define MO_TAG_PROPERTY     TC("Property")
#define MO_PTY_NAME         TC("name")

MO_NAMESPACE_BEGIN

MO_CLASS_ABSTRACT_IMPLEMENT_INHERITS(FMonitor, FInstance);

//============================================================
// <T>构造监视器对象的实例。</T>
//
// @return 当前实例指针
//============================================================
FMonitor::FMonitor(){
   _name = TC("Monitor");
   _lock = ETrue;
   _needDelay = ETrue;
   _delay = 0;
   _interval = MO_MONITOR_INTERVAL;
   _processCount = 1;
   _processTimeout = 0;
   _currentDateTime = RDateTime::Current();
   _currentTimeTick = RTimeTick::Current();
   _currentTimeSpan = 0;
   _lastTimeTick = _currentTimeTick;
   _total = 0;
   _count = 0;
   _started = EFalse;
   MO_CLEAR(_pCatcher);
}

//============================================================
// <T>析构监视器对象的实例。</T>
//
// @return 当前实例指针
//============================================================
FMonitor::~FMonitor(){
}

//============================================================
// <T>判断是否指定名称。</T>
//
// @param pName 名称
// @return 是否
//============================================================
TBool FMonitor::IsName(TCharC* pName) const{
   return _name.Equals(pName);
}

//============================================================
// <T>获得名称。</T>
//
// @return 名称
//============================================================
TCharC* FMonitor::Name() const{
   return _name;
}

//============================================================
// <T>设置名称。</T>
//
// @param pName 名称
//============================================================
void FMonitor::SetName(TCharC* pName){
   _name = pName;
}

//============================================================
// <T>获得锁定。</T>
//
// @return 锁定
//============================================================
TBool FMonitor::IsLock() const{
   return _lock;
}

//============================================================
// <T>获得总数。</T>
//
// @return 总数
//============================================================
TInt64 FMonitor::Total() const{
   return _total;
}

//============================================================
// <T>设置总数。</T>
//
// @param total 总数
//============================================================
void FMonitor::SetTotal(TInt64 total){
   _total = total;
}

//============================================================
// <T>获得次数。</T>
//
// @return 次数
//============================================================
TInt64 FMonitor::Count() const{
   return _count;
}

//============================================================
// <T>获得延时。</T>
//
// @return 延时
//============================================================
TTimeSpan FMonitor::Delay() const {
   return _delay;
}

//============================================================
// <T>设置延时。</T>
//
// @param delay 延时
//============================================================
void FMonitor::SetDelay(TTimeSpan delay){
   _delay = delay;
}

//============================================================
// <T>获得间隔。</T>
//
// @return 间隔
//============================================================
TTimeSpan FMonitor::Interval() const {
   return _interval;
}

//============================================================
// <T>设置间隔。</T>
//
// @param interval 间隔
//============================================================
void FMonitor::SetInterval(TTimeSpan interval){
   _interval = interval;
}

//============================================================
// <T>获得执行次数。</T>
//
// @return 执行次数
//============================================================
TInt64 FMonitor::ProcessCount() const{
   return _processCount;
}

//============================================================
// <T>设置执行次数。</T>
//
// @param count 执行次数
//============================================================
void FMonitor::SetProcessCount(TInt64 count){
   _processCount = count;
}

//============================================================
// <T>获得执行超时。</T>
//
// @return 执行超时
//============================================================
TTimeSpan FMonitor::ProcessTimeout() const{
   return _processTimeout;
}

//============================================================
// <T>获得执行超时。</T>
//
// @param timeout 执行超时
//============================================================
void FMonitor::SetProcessTimeout(TTimeSpan timeout){
   _processTimeout = timeout;
}

//============================================================
// <T>获得当前时间。</T>
//
// @return 时间
//============================================================
TDateTime FMonitor::CurrentDateTime(){
   return _currentDateTime;
}

//============================================================
// <T>获得当前时间刻度。</T>
//
// @return 时间刻度
//============================================================
TTimeTick FMonitor::CurrentTimeTick(){
   return _currentDateTime;
}

//============================================================
// <T>获得当前时间段。</T>
//
// @return 时间段
//============================================================
TTimeTick FMonitor::CurrentTimeSpan(){
   return _currentDateTime;
}

//============================================================
// <T>获得扑捉器。</T>
//
// @return 扑捉器
//============================================================
FCatcher* FMonitor::Catcher(){
   return _pCatcher;
}

//============================================================
// <T>设置扑捉器。</T>
//
// @param 扑捉器
//============================================================
void FMonitor::SetCatcher(FCatcher* pCatcher){
   _pCatcher = pCatcher;
}

//============================================================
// <T>测试是否可以运行。</T>
//
// @param currentTick 当前时刻
// @return 是否可以
//============================================================
TBool FMonitor::Test(TTimeTick currentTick){
   TTimeSpan interval = currentTick - _lastTimeTick;
   // 首次延时判断
   if(_needDelay){
      if(interval > _delay){
         return ETrue;
      }
   }
   // 无限循判断
   if(-1 == _total){
      if(interval > _interval){
         return ETrue;
      }
   }
   // 有限循环判断
   if(_total  > 0){
      if(_count < _total){
         if(interval > _interval){
            return ETrue;
         }
      }
   }
   return EFalse;
}

//============================================================
// <T>加载设置信息。</T>
//
// @param pConfig 设置节点
// @return 处理结果
//============================================================
TBool FMonitor::LoadConfig(FXmlNode* pConfig){
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      if(iterator->IsName(MO_TAG_PROPERTY)){
         if(iterator->IsAttribute(MO_PTY_NAME, TC("lock"))){
            // 读取锁定
            _lock = iterator->TextAsBool();
            MO_DEBUG(TC("Load monitor(%s) property. (lock=%d)"), (TCharC*)_name, _lock);
         }else if(iterator->IsAttribute(MO_PTY_NAME, TC("delay"))){
            // 读取延迟(毫秒)
            TInt delay = iterator->TextAsInt();
            MO_DEBUG(TC("Load monitor(%s) property. (delay=%dms)"), (TCharC*)_name, delay);
            SetDelay(delay * 1000);
         }else if(iterator->IsAttribute(MO_PTY_NAME, TC("interval"))){
            // 读取延迟(毫秒)
            TInt interval = iterator->TextAsInt(MO_MONITOR_INTERVAL);
            MO_DEBUG(TC("Load monitor(%s) property. (interval=%dms)"), (TCharC*)_name, interval);
            SetInterval(interval * 1000);
         }else if(iterator->IsAttribute(MO_PTY_NAME, TC("process_count"))){
            // 读取处理次数
            TInt processCount = iterator->TextAsInt(MO_MONITOR_INTERVAL);
            MO_DEBUG(TC("Load monitor(%s) property. (process_count=%d)"), (TCharC*)_name, processCount);
            SetProcessCount(processCount);
         }else if(iterator->IsAttribute(MO_PTY_NAME, TC("process_timeout"))){
            // 读取延迟(毫秒)
            TInt processTimeout = iterator->TextAsInt(MO_MONITOR_INTERVAL);
            MO_DEBUG(TC("Load monitor(%s) property. (process_timeout=%dms)"), (TCharC*)_name, processTimeout);
            SetProcessTimeout(processTimeout * 1000);
         }else if(iterator->IsAttribute(MO_PTY_NAME, TC("total"))){
            // 读取处理总数
            TInt total = iterator->TextAsInt();
            MO_DEBUG(TC("Load monitor(%s) property. (total=%d)"), (TCharC*)_name, total);
            SetTotal(total);
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitor::Startup(){
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitor::Shutdown(){
   return ESuccess;
}

//============================================================
// <T>执行处理。</T>
//
// @param currentTime 当前时间
// @return 处理结果
//============================================================
TResult FMonitor::Execute(TTimeTick currentTick){
   TResult result = EFailure;
   // 启动处理
   if(!_started){
      Startup();
      _started = ETrue;
   }
   // 逻辑处理
   if(0 != _total){
      _currentDateTime = RDateTime::Current();
      _currentTimeTick = currentTick;
      _currentTimeSpan = currentTick - _lastTimeTick;
      _statistics.Begin();
#ifdef _MO_LINUX
      if(NULL != _pCatcher){
         _pCatcher->Enter();
         if(MO_TRAP_CATCHER(_pCatcher)){
            _pCatcher->Recorded();
            result = Process();
         }else{
            _pCatcher->JumpFinish();
            result = EFailure;
         }
         _pCatcher->Leave();
      }else{
         result = Process();
      }
#else
      result = Process();
#endif
      _statistics.End();
      _count++;
      _needDelay = EFalse;
      _lastTimeTick = currentTick;
      // 更新统计信息
      if(ESuccess != result){
         _statistics.Update(EFalse);
         MO_ERROR(TC("Process monitor failure. (name=%s, result=%d)"), (TCharC*)_name, result);
      }else{
         _statistics.Update(ETrue);
      }
   }
   return result;
}

MO_NAMESPACE_END
