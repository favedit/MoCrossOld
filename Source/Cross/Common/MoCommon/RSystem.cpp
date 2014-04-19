#ifdef _MO_LINUX
#include <unistd.h>
#include <fcntl.h>
#include <sys/resource.h>
#include <sys/times.h>
#endif
#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得系统信息。</T>
//
// @param pInfo 系统信息
// @param processId 进程编号
// @param threadId 线程编号
// @return 处理结果
//============================================================
TBool RSystem::FetchInfo(SSystemInfo* pInfo, TInt processId, TInt threadId){
   TBool result = EFalse;
#ifdef _MO_LINUX
   // 检查参数
   MO_ASSERT(pInfo);
   if(processId < 0){
      processId = getpid();
   }
   // 获得信息
   TFsCommand command;
   command.AppendFormat("ps -o %%cpu,rss,%%mem,pid,tid -mp %d", processId);
   FILE* pFile = popen(command.MemoryC(), "r"); 
   if(NULL == pFile){
      MO_STATIC_PERROR(popen);
      return EFalse; 
   }
   TFsCommand line;
   if(NULL != fgets(line.Memory(), line.Size(), pFile)){
      if(fgets(line.Memory(), line.Size(), pFile) != NULL){
         TFloat readCpu = 0; 
         TInt32 readMemory = 0; 
         TFloat readMemPercent = 0; 
         TInt32 readPid = 0; 
         TInt32 readTid = 0;
         sscanf(line.Memory(), "%f %d %f %d -", &readCpu, &readMemory, &readMemPercent, &readPid);
         pInfo->cpuRate = readCpu;
         pInfo->memory = readMemory * 1024;
         if(-1 == threadId){
            result = true; 
         }else{
            while(fgets(line.Memory(), line.Size(), pFile) != NULL){
               sscanf(line.Memory(), "%f - - - %d", &readCpu, &readTid);
               if(readTid == threadId){
                  pInfo->cpuRate = readCpu;
                  pInfo->memory = readMemory * 1024;
                  result = ETrue;
                  break;
               }
            }
            if(readTid != threadId){
               MO_STATIC_ERROR("Thread id is not exists. (process_id=%d, thread_id=%d)", processId, threadId);
            }
         }
      }else{
         MO_STATIC_ERROR("Process id is not exists. (process_id=%d)", processId);
      }
   }else{
      MO_STATIC_PERROR(fgets);
   }
   pclose(pFile);
#endif // _MO_LINUX
   return result;
}

//============================================================
// <T>获得堆栈信息。</T>
//
// @param pData 数据
// @return 处理结果
//============================================================
TBool RSystem::FetchStackSimple(MString* pData){
   MO_ASSERT(pData);
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   TFsNote trackInfo;
   TAny* ppTracks[MO_SYS_STACK_MAXCNT];
   TInt count = backtrace(ppTracks, MO_SYS_STACK_MAXCNT);
   TChar8** ppSymbols = backtrace_symbols(ppTracks, count);
   for(TInt n = 0; n < count; n++){
      // Dump stack info to other file
      TChar8C* pSymbol = ppSymbols[n];
      TInt length = RString::Length(pSymbol);
      if(length >= TFsNote::Size()){
         length = TFsNote::Size() - 1;
      }
      trackInfo.Assign(pSymbol, length);
      trackInfo.Fix();
      pData->AppendFormat("\n   Stack track: %2d -- %s", count - n, (TCharC*)trackInfo);
   }
   free(ppSymbols);
#endif // __CYGWIN__
#endif // _MO_LINUX
   return ETrue;
}

//============================================================
// <T>获得堆栈信息。</T>
//
// @param pData 数据
// @return 处理结果
//============================================================
TBool RSystem::FetchStackDetail(MString* pData){
   MO_ASSERT(pData);
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   TFsNote trackInfo;
   TAny* ppTracks[MO_SYS_STACK_MAXCNT];
   TInt count = backtrace(ppTracks, MO_SYS_STACK_MAXCNT);
   TChar8** ppSymbols = backtrace_symbols(ppTracks, count);
   //............................................................
   // 生成行号查询命令行
   TFsCommand command;
   command.AppendFormat("addr2line");
   for(TInt n = 0; n < count; n++){
      TChar8C* pSymbol = ppSymbols[n];
      TInt length = RString::Length(pSymbol);
      if(length >= TFsNote::Size()){
         length = TFsNote::Size() - 1;
      }
      trackInfo.Assign(pSymbol, length);
      trackInfo.Fix();
      // 转换为行号
      TInt begin = trackInfo.LastIndexOf('[');
      TInt end = trackInfo.LastIndexOf(']');
      if((-1 != begin) && (-1 != end) && (end > begin)){
         TFsCode code = trackInfo.MidStrC(begin + 1, end - begin - 1);
         command.AppendFormat(" %s", (TCharC*)code);
      }
   }
   command.AppendFormat(" -e %s", RApplication::GetArgument(0));
   //............................................................
   // 获得行信息集合
   TFsFileName fileNames[MO_SYS_STACK_MAXCNT];
   TFsNumber fileLines[MO_SYS_STACK_MAXCNT];
   FILE* pFile = popen((TCharC*)command, "r"); 
   if(NULL != pFile){
      TInt level = 0;
      TFsCommand line;
      while(NULL != fgets((TChar*)line, line.Size(), pFile)){
         line.Fix();
         TInt splite = line.IndexOf(':');
         fileNames[level] = line.LeftStrC(splite);
         TInt spliteEnd = line.IndexOf('\n');
         if(-1 != spliteEnd){
            fileLines[level] = line.MidStrC(splite + 1, spliteEnd - splite - 1);
         }else{
            fileLines[level] = line.MidStrC(splite + 1);
         }
         level++;
      }
      pclose(pFile);
   }
   //............................................................
   // 获得内容
   for(TInt n = 0; n < count; n++){
      // Dump stack info to other file
      TChar8C* pSymbol = ppSymbols[n];
      TInt length = RString::Length(pSymbol);
      if(length >= TFsNote::Size()){
         length = TFsNote::Size() - 1;
      }
      trackInfo.Assign(pSymbol, length);
      trackInfo.Fix();
      pData->AppendFormat("\n   Stack track: %2d - %s > %s (%s)",
            count - n, (TCharC*)trackInfo, (TCharC*)fileNames[n], (TCharC*)fileLines[n]);
   }
   free(ppSymbols);
#endif // __CYGWIN__
#endif // _MO_LINUX
   return ETrue;
}

//============================================================
// <T>获得堆栈信息。</T>
//
// @param pData 数据
// @return 处理结果
//============================================================
TBool RSystem::FetchStack(MString* pData){
   MO_ASSERT(pData);
#ifdef _MO_DEBUG
   RSystem::FetchStackDetail(pData);
#else
   RSystem::FetchStackSimple(pData);
#endif
   return ETrue;
}

//============================================================
// <T>显示系统信息。</T>
//============================================================
void RSystem::Dump(MString* pDump){
#ifdef _MO_DEBUG
#ifndef __CYGWIN__
   SMemoryInfo memoryInfo;
   if(NULL != RMemory::Storage()){
      RMemory::Storage()->FetchInfo(&memoryInfo);
   }
   SSystemInfo info;
   if(FetchInfo(&info)){
      if(NULL == pDump){
         MO_STATIC_INFO(TC("System info: cpu_rate = %02.1f, system_memory=%22s, application_memory=%22s"),
               info.cpuRate,
               RInt::FormatCapacity((TSize)info.memory, TFsCode(), TFsCode::Size()),
               RInt::FormatCapacity((TSize)(memoryInfo.lengthAlloc - memoryInfo.lengthFree), TFsCode(), TFsCode::Size()));
      }else{
         pDump->AppendFormat(TC("System info: cpu_rate = %02.1f, system_memory=%22s, application_memory=%22s"),
               info.cpuRate,
               RInt::FormatCapacity((TSize)info.memory, TFsCode(), TFsCode::Size()),
               RInt::FormatCapacity((TSize)(memoryInfo.lengthAlloc - memoryInfo.lengthFree), TFsCode(), TFsCode::Size()));
      }
   }
#endif // __CYGWIN__
#endif // _MO_DEBUG
}

MO_NAMESPACE_END
