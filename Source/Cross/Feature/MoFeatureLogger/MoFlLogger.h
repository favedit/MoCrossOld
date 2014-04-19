#ifndef __MO_FL_LOGGER_H__
#define __MO_FL_LOGGER_H__

#ifndef __MO_FL_COMMON_H__
#include "MoFlCommon.h"
#endif // __MO_FL_COMMON_H__

#ifndef __MO_FL_FILE_H__
#include "MoFlFile.h"
#endif // __MO_FL_FILE_H__

#ifndef __MO_FL_NET_H__
#include "MoFlNet.h"
#endif // __MO_FL_NET_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>日志功能。</T>
//
// @history 140413 MAOCY 创建
//============================================================
class MO_FL_DECLARE FLoggerFeature : public FFeature
{
   MO_CLASS_DECLARE_INHERITS(FLoggerFeature, FFeature);
protected:
   GPtr<FNetLoggerConsole> _netLoggerConsole;
public:
   FLoggerFeature();
   MO_ABSTRACT ~FLoggerFeature();
public:
   //------------------------------------------------------------
   // <T>获得网络日志控制台。</T>
   MO_INLINE FNetLoggerConsole* NetLoggerConsole(){
      return _netLoggerConsole;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Suspend();
   MO_OVERRIDE TResult Resume();
   MO_OVERRIDE TResult Shutdown();
};

//============================================================
// <T>日志功能管理器。</T>
//
// @history 140413 MAOCY 创建
//============================================================
class MO_FL_DECLARE RLoggerFeature : public RSingleton<FLoggerFeature>{
};

MO_NAMESPACE_END

#endif // __MO_FL_LOGGER_H__
