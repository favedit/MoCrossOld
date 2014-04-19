#include "MoFlNet.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FNetLoggerConsole, FConsole);

//============================================================
// <T>构造网络日志控制台。</T>
//============================================================
FNetLoggerConsole::FNetLoggerConsole(){
   _name = "Net.Logger.Console";
}

//============================================================
// <T>析构网络日志控制台。</T>
//============================================================
FNetLoggerConsole::~FNetLoggerConsole(){
}

//============================================================
// <T>注册一个书写器。</T>
//
// @param pWriter 书写器
//============================================================
void FNetLoggerConsole::Register(FNetLoggerWriter* pWriter){
   _writers.Push(pWriter);
   RLoggerManager::Instance().Register(pWriter);
}

//============================================================
// <T>注销一个书写器。</T>
//
// @param pWriter 书写器
//============================================================
void FNetLoggerConsole::Unregister(FNetLoggerWriter* pWriter){
   _writers.Remove(pWriter);
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetLoggerConsole::Startup(){
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetLoggerConsole::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetLoggerConsole::Resume(){
   return ESuccess;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FNetLoggerConsole::Shutdown(){
   return ESuccess;
}

MO_NAMESPACE_END
