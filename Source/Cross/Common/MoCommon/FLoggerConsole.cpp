#include "MoCmLanguage.h"
#include "MoCmFormat.h"
#include "MoCmSystem.h"
#include "MoCmThread.h"

#define MO_LOGGER_PAD_LENGTH 86

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造日志控制台。</T>
//
// @return 日志控制台
//============================================================
FLoggerConsole::FLoggerConsole(){
   _name = TC("common.logger.console");
   _closed = EFalse;
   _useLogger = ETrue;
   _useViewer = ETrue;
   _format = TC("%y%m%d-%H%M%S.%s");
   _levelCd = ELoggerLevel_Debug;
   _pWriters = MO_CREATE(FLoggerWriterList);
   _pThread = MO_CREATE(FLoggerThread);
   _pThread->SetWriters(_pWriters);
}

//============================================================
// <T>析构日志控制台。</T>
//============================================================
FLoggerConsole::~FLoggerConsole(){
}

//============================================================
// <T>格式化级别。</T>
//
// @param levelCd 消息级别
// @return 级别
//============================================================
TChar FLoggerConsole::FormatLevelChar(ELoggerLevel levelCd){
   switch(levelCd){
      case ELoggerLevel_Debug:
         return 'D';
      case ELoggerLevel_Info:
         return 'I';
      case ELoggerLevel_Warn:
         return 'W';
      case ELoggerLevel_Error:
         return 'E';
      case ELoggerLevel_Fatal:
         return 'F';
      case ELoggerLevel_Crash:
         return 'C';
   }
   return 'U';
}

//============================================================
// <T>格式化输出信息。</T>
//
// @param result 格式化结果
// @param level 消息级别
// @param pSender 发送者
// @param pMethod 函数名称
// @param start 开始时间
// @param current 当前时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void FLoggerConsole::Format(TFsLogger& result, TDateTime current, ELoggerLevel levelCd, TAnyC* pSender, TCharC* pMethod, TTimeSpan span, TCharC* pMessage, va_list& params){
#ifndef _MO_ANDROID
   // 如果存在开始时间，则先计算花费时间
   TFsName datetime;
   RDateTime::ToString(datetime.Memory(), datetime.Size(), current, _format);
   // 格式化日志时间
   result.Append(datetime.MemoryC());
   result.Append('|');
   result.Append(FormatLevelChar(levelCd));
   result.Append('.');
   // 格式化线程信息
   FThread* pThread = RThreadManager::Instance().Current();
   if(NULL != pThread){
      TCharC* pThreadCode = pThread->Code();
      result.AppendFormat(TC("%s-%04d"), pThreadCode, pThread->Index());
   }else{
      TThreadId threadId = RThread::CurrentId();
      result.AppendFormat(TC("%08X"), threadId);
   }
   // 格式化函数调用信息
   result.Append(TC(" [ "));
   result.Append(pMethod);
   if(NULL != pSender){
      result.Append('@');
      result.AppendFormat(TC("%08X"), pSender);
   }
   if(span > 0){
      // 显示花费时间，精确到毫秒
      TFsNumber number;
      number.AppendFormat(TC("%lldus"), span);
      result.PadRight(' ', MO_LOGGER_PAD_LENGTH - number.Length());
      result.Append(number);
   }else{
      // 自动对齐
      result.PadRight(' ', MO_LOGGER_PAD_LENGTH);
   }
   result.Append(TC(" ] "));
#endif // _MO_ANDROID
   // 格式化可变参数字符串信息
   TChar message[MO_FS_LOGGER_LENGTH];
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   wvsprintf(message, pMessage, params);
#else
   vsprintf_s(message, MO_FS_LOGGER_LENGTH, pMessage, params);
#endif // _UNICODE
#else
   vsnprintf(message, MO_FS_LOGGER_LENGTH, pMessage, params);
#endif // _MO_WINDOWS
   message[MO_FS_LOGGER_LENGTH - 1] = 0;
   result.Append(message);
}

//============================================================
// <T>格式化输出信息。</T>
//
// @param result 格式化结果
// @param level 消息级别
// @param pSender 发送者
// @param pMethod 函数名称
// @param start 开始时间
// @param current 当前时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
void FLoggerConsole::FormatTrack(TFsLogger& result, ELoggerLevel levelCd, TAnyC* pSender, TCharC* pMethod, TTimeSpan span, TDateTime current, TCharC* pMessage, va_list& params){
   // 格式化日志类型
   result.Append(TC("MO."));
   result.Append(FormatLevelChar(levelCd));
   result.Append(TC("> "));
   // 格式化线程信息
   TThreadId threadId = RThread::CurrentId();
   result.AppendFormat(TC("%08X"), threadId);
   // 格式化函数调用信息
   result.Append(TC(" [ "));
   result.Append(pMethod);
   if(NULL != pSender){
      result.Append('@');
      result.AppendFormat(TC("%08X"), pSender);
   }
   if(span > 0){
      // 显示花费时间，精确到毫秒
      TFsInteger number;
      number.AppendFormat(TC("%lldus"), span);
      result.PadRight(' ', MO_LOGGER_PAD_LENGTH - number.Length());
      result.Append(number);
   }else{
      // 自动对齐
      result.PadRight(' ', MO_LOGGER_PAD_LENGTH);
   }
   result.Append(TC(" ] "));
   // 格式化可变参数字符串信息
   TChar message[MO_FS_LOGGER_LENGTH];
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   wvsprintf(message, pMessage, params);
#else
   vsprintf_s(message, MO_FS_LOGGER_LENGTH, pMessage, params);
#endif
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
   vsnprintf(message, MO_FS_LOGGER_LENGTH, pMessage, params);
#endif
#ifdef _MO_ANDROID
   vsnprintf(message, MO_FS_LOGGER_LENGTH, pMessage, params);
#endif
   message[MO_FS_LOGGER_LENGTH - 1] = 0;
   result.Append(message);
}

//============================================================
// <T>向外输出信息。</T>
// <P>显示方式:0（默认值）,1（高亮）,22（非粗体）,4（下划线）,24（非下划线）,5（闪烁）,25（非闪烁）,7（反显）,27（非反显）</P>
// <P>前景色:30（黑色）,31（红色）,32（绿色）,33（黄色）,34（蓝色）,35（洋红）,36（青色）,37（白色）</P>
// <P>背景色:40（黑色）,41（红色）,42（绿色）,43（黄色）,44（蓝色）,45（洋红）,46（青色）,47（白色）</P>
//
// @param code 消息代码
// @param levelCd 消息级别
// @param pSender 发送者
// @param pMethod 调用函数
// @param start 开始时间
// @param pMessage 消息内容
// @param ... 消息参数
//============================================================
TBool FLoggerConsole::Output(TInt code, ELoggerLevel levelCd, TAnyC* pSender, TCharC* pMethod, TTimeTick start, TCharC* pMessage, va_list& params){
   // 检查输出级别
   if(levelCd < _levelCd){
      return EFalse;
   }
   // 计算间隔
   TDateTime current = RDateTime::Current();
   TTimeSpan span = 0;
   if(start > 0){
      span = RTimeTick::Current() - start;
   }
   // 获得函数名称
   TFsText line = pMethod;
   TInt left = 0;
   if(line.StartsWith(TC("static "))){
      left = 7;
   }
   if(line.StartsWith(TC("virtual "))){
      left = 8;
   }
   left = MO_LIB_MAX(line.IndexOf(' ', left) + 1, 0);
   TInt right = line.IndexOf('(');
   if(-1 == right){
      right = line.Length();
   }
   TFsName method = line.SubStrC(left, right);
   TInt spaceFlag = method.Find(TC("MO::"));
   if(ENotFound != spaceFlag){
      left = spaceFlag + 4;
   }else{
      left = 0;
   }
   TFsName name = method.SubStrC(left, method.Length());
#ifdef _MO_ANDROID
   // 追加发送者
   if(NULL != pSender){
      name.Append('@');
      name.AppendFormat(TC("%08X"), pSender);
   }
   // 追加花费时间
   if(span > 0){
      // 显示花费时间，精确到毫秒
      name.AppendFormat(TC("-%lldus"), span);
   }
#endif // _MO_ANDROID
   // 格式化日志
   TFsLogger logger;
   Format(logger, current, levelCd, pSender, (TCharC*)name, span, pMessage, params);
   // 追加例外信息
#ifndef _MO_ANDROID
   if(ELoggerLevel_Fatal == levelCd){
      logger.Append(TC("\n-- Track info begin ----------------------------------------"));
      RSystem::FetchStackSimple(&logger);
      logger.Append(TC("\n-- Track info end ------------------------------------------"));
   }
#endif // _MO_ANDROID
   // 输出信息
   if(levelCd != ELoggerLevel_Crash){
      //............................................................
#ifdef _MO_WINDOWS
#ifdef _MO_DEBUG
      if(_useLogger){
         logger.Append(TC("\r\n"));
         MO_LIB_STRING_PRINT(TC("%s"), (TCharC*)logger);
      }
      if(_useViewer){
         OutputDebugString((TCharC*)logger);
      }
#else
      if(_useLogger){
         MO_LIB_STRING_PRINT(TC("%s"), (TCharC*)logger);
      }
#endif // _MO_DEBUG
#endif // _MO_WINDOWS
      //............................................................
#ifdef _MO_LINUX
      if(_useLogger){
         switch(levelCd){
            case ELoggerLevel_Debug:
               printf("%s\n", (TCharC*)logger);
               break;
            case ELoggerLevel_Info:
               printf("\033[0;32;49m%s\033[0m\n", (TCharC*)logger);
               break;
            case ELoggerLevel_Warn:
               printf("\033[0;33;49m%s\033[0m\n", (TCharC*)logger);
               break;
            case ELoggerLevel_Error:
               printf("\033[0;31;49m%s\033[0m\n", (TCharC*)logger);
               break;
            case ELoggerLevel_Fatal:
            case ELoggerLevel_Crash:
               printf("\033[1;31;49m%s\033[0m\n", (TCharC*)logger);
               break;
            default:
               printf("%s\n", (TCharC*)logger);
               break;
         }
      }
#endif // _MO_LINUX
      //............................................................
#ifdef _MO_ANDROID
      if(_useLogger){
         switch(levelCd){
            case ELoggerLevel_Debug:
               __android_log_write(ANDROID_LOG_DEBUG, (TCharC*)name, (TCharC*)logger);
               break;
            case ELoggerLevel_Info:
               __android_log_write(ANDROID_LOG_INFO, (TCharC*)name, (TCharC*)logger);
               break;
            case ELoggerLevel_Warn:
               __android_log_write(ANDROID_LOG_WARN, (TCharC*)name, (TCharC*)logger);
               break;
            case ELoggerLevel_Error:
               __android_log_write(ANDROID_LOG_ERROR, (TCharC*)name, (TCharC*)logger);
               break;
            case ELoggerLevel_Fatal:
            case ELoggerLevel_Crash:
               __android_log_write(ANDROID_LOG_FATAL, (TCharC*)name, (TCharC*)logger);
               break;
            default:
               __android_log_write(ANDROID_LOG_DEBUG, (TCharC*)name, (TCharC*)logger);
               break;
         }
      }
#endif // _MO_ANDROID
   }
   //............................................................
   // 检查是已经关闭，不再输出
   if(_closed){
      return EFalse;
   }
   //............................................................
   // 输出日志到写出器
   ILoggerWriter* pWriter = FindWriter(code);
   if(NULL != pWriter){
      pWriter->Write(current, logger, logger.Length());
   }
   return ETrue;
}

//============================================================
// <T>获得使用日志。</T>
//
// @return 使用日志
//============================================================
TBool FLoggerConsole::UseLogger(){
   return _useLogger;
}

//============================================================
// <T>设置使用日志。</T>
//
// @param useLogger 使用日志
//============================================================
void FLoggerConsole::SetUseLogger(TBool useLogger){
   _useLogger = useLogger;
}

//============================================================
// <T>获得使用视角。</T>
//
// @return 使用视角
//============================================================
TBool FLoggerConsole::UseViewer(){
   return _useViewer;
}

//============================================================
// <T>设置使用视角。</T>
//
// @param useViewer 使用视角
//============================================================
void FLoggerConsole::SetUseViewer(TBool useViewer){
   _useViewer = useViewer;
}

//============================================================
// <T>获得级别类型。</T>
//
// @return 级别类型
//============================================================
ELoggerLevel FLoggerConsole::LevelCd(){
   return _levelCd;
}

//============================================================
// <T>设置级别类型。</T>
//
// @param levelCd 级别类型
//============================================================
void FLoggerConsole::SetLevelCd(ELoggerLevel levelCd){
   _levelCd = levelCd;
}

//============================================================
// <T>查找一个写入器。</T>
//
// @param code 代码
// @return 写入器
//============================================================
ILoggerWriter* FLoggerConsole::FindWriter(TInt code){
   TListIteratorC<ILoggerWriter*> iterator = _pWriters->IteratorC();
   while(iterator.Next()){
      ILoggerWriter* pWriter = *iterator;
      if(pWriter->Code() == code){
         return pWriter;
      }
   }
   return NULL;
}

//============================================================
// <T>注册一个日志写入器。</T>
//
// @param pWriter 日志写入器
// @return 处理结果
//============================================================
TResult FLoggerConsole::Register(ILoggerWriter* pWriter){
   // 检查参数
   MO_ASSERT(pWriter);
   if(NULL != FindWriter(pWriter->Code())){
      MO_FATAL(TC("Logger writer code is already exists. (code=%d)"), pWriter->Code());
      return EFailure;
   }
   // 打开日志
   if(!pWriter->Open()){
      MO_ERROR(TC("Register logger writer failure. (code=%d)"), pWriter->Code());
      return EFailure;
   }
   // 注册日志
   _pThread->Section().Enter();
   _pWriters->Push(pWriter);
   _pThread->Section().Leave();
   return ESuccess;
}

//============================================================
// <T>注销一个日志写入器。</T>
//
// @param pWriter 日志写入器
// @return 处理结果
//============================================================
TResult FLoggerConsole::Unregister(ILoggerWriter* pWriter){
   // 检查参数
   MO_ASSERT(pWriter);
   if(NULL == FindWriter(pWriter->Code())){
      MO_FATAL(TC("Logger writer code is not exists. (code=%d)"), pWriter->Code());
      return EFailure;
   }
   // 关闭日志
   if(!pWriter->Close()){
      MO_FATAL(TC("Unregister logger writer failure. (code=%d)"), pWriter->Code());
      return EFailure;
   }
   // 注销日志
   _pThread->Section().Enter();
   _pWriters->Remove(pWriter);
   _pThread->Section().Leave();
   return ESuccess;
}

//============================================================
// <T>内部刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::InnerFlush(){
   TListIteratorC<ILoggerWriter*> iterator = _pWriters->IteratorC();
   while(iterator.Next()){
      iterator->Flush();
   }
   return ESuccess;
}

//============================================================
// <T>内部关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::InnerClose(){
   TListIteratorC<ILoggerWriter*> iterator = _pWriters->IteratorC();
   while(iterator.Next()){
      ILoggerWriter* pWriter = *iterator;
      pWriter->Close();
   }
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::Startup(){
   _pThread->Start();
   return ESuccess;
}

//============================================================
// <T>刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::Flush(){
   MO_INFO(TC("Logger console flush."));
   return InnerFlush();
}

//============================================================
// <T>例外处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::Interrupt(){
   MO_INFO(TC("Logger console interrupt."));
   return InnerFlush();
}

//============================================================
// <T>重新加载所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::Reload(){
   MO_INFO(TC("Logger console reload."));
   return InnerFlush();
}

//============================================================
// <T>卸载所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::Unload(){
   MO_INFO(TC("Logger console unload."));
   return InnerFlush();
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerConsole::Shutdown(){
   MO_INFO(TC("Logger console shutdown."));
   _closed = ETrue;
   // 刷新处理
   InnerFlush();
   InnerClose();
   return ESuccess;
}

MO_NAMESPACE_END
