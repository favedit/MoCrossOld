#include "MoCmRuntime.h"

MO_NAMESPACE_BEGIN

//============================================================
ILoggerConsole* RLogger::_pConsole = NULL;

//============================================================
// <T>关联日志输出器。</T>
//
// @param pConsole 日志输出器
//============================================================
void RLogger::Link(ILoggerConsole* pConsole){
   _pConsole = pConsole;
}

//============================================================
// <T>获得日志控制台。</T>
//
// @return 日志控制台
//============================================================
ILoggerConsole* RLogger::Console(){
   return _pConsole;
}

//============================================================
// <T>输出信息。</T>
//
// @param type 消息类型
// @param level 消息级别
// @param pSender 发送者
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Output(TInt type, ELoggerLevel level, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, va_list& params){
   if(_pConsole != NULL){
      // 使用管理器输出信息
#ifdef _MO_UNICODE
      TChar method[MO_FS_NAME_LENGTH];
      if(pMethod != NULL){
         TInt n = 0;
         for(; n < MO_FS_NAME_LENGTH - 1; n++){
            method[n] = *pMethod++;
         }
         method[n] = 0;
      }
      _pConsole->Output(type, level, pSender, method, start, pMessage, params);
#else
      _pConsole->Output(type, level, pSender, pMethod, start, pMessage, params);
#endif // _MO_UNICODE
   }else{
//      // 直接输出信息
//#ifdef _UNICODE
//      vwprintf(pMessage, params);
//#else
//      vprintf(pMessage, params);
//#endif // _UNICODE
   }
}

//============================================================
// <T>输出调试信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Debug(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Debug, pSender, pMethod, 0, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出调试信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Debug(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Debug, pSender, pMethod, start, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出提示信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Info(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Info, pSender, pMethod, 0, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出提示信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Info(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Info, pSender, pMethod, start, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出警告信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Warn(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Warn, pSender, pMethod, 0, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出警告信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Warn(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Warn, pSender, pMethod, start, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出错误信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Error(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Error, pSender, pMethod, 0, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出错误信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Error(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Error, pSender, pMethod, start, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出系统错误信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Fatal(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Fatal, pSender, pMethod, 0, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出系统错误信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Fatal(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Fatal, pSender, pMethod, start, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出系统崩溃信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Crash(TInt type, TAnyC* pSender, TChar8C* pMethod, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Crash, pSender, pMethod, 0, pMessage, params);
   va_end(params);
}

//============================================================
// <T>输出系统崩溃信息。</T>
//
// @param type 消息类型
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void RLogger::Crash(TInt type, TAnyC* pSender, TChar8C* pMethod, TTimeTick start, TCharC* pMessage, ...){
   // 格式化可变参数字符串信息
   va_list params;
   va_start(params, pMessage);
   // 输出日志信息
   Output(type, ELoggerLevel_Crash, pSender, pMethod, start, pMessage, params);
   va_end(params);
}

MO_NAMESPACE_END
