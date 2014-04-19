#ifndef __MO_FL_LOGGER_H__
#define __MO_FL_LOGGER_H__

#ifndef __MO_FL_COMMON_H__
#include "MoFlCommon.h"
#endif // __MO_FL_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>日志写出器。</T>
//
// @class
//============================================================
class MO_FL_DECLARE FBufferedLoggerWriter :
      public FObject,
      public ILoggerWriter{
protected:
   TInt _code;
   TInt _fileCount;
   TInt _capacity;
   TInt  _length;
   TFsName _name;
   TFsPath _path;
   TDateTime _today;
   FBytes* _pBuffer;
   FFileStream* _pStream;
   TThreadSection _section;
   TBool _forceToFlush;
   TInt _flushLength;
   TTimeSpan _flushTimeout;
   TDateTime _lastFlushDate;
public:
   FBufferedLoggerWriter();
   MO_ABSTRACT( ~FBufferedLoggerWriter() );
public:
   void SetCode(TInt code);
   void SetName(TCharC* pName);
   void SetPath(TCharC* pPath);
   void SetCapacity(TSize capacity);
   void SetForceToFlush(TBool isForceFlush);
public:
   TBool MakeFilePattern(TFsFileName& fileName);
   TInt CalculateCount(TCharC* pFilePattern, TInt baseCount = 1);
public:
   MO_OVERRIDE TBool Open();
   MO_OVERRIDE TBool Create();
   MO_OVERRIDE TInt Code();
   MO_OVERRIDE TBool Write(TDateTime time, TCharC* pMessage, TInt length);
   MO_OVERRIDE TBool Close();
public:
   MO_OVERRIDE TBool Refresh();
   MO_OVERRIDE TBool Flush();
};

//============================================================
// <T>日志模块。</T>
//
// @history 100317 MAOCY 创建
//============================================================
class MO_FL_DECLARE FLoggerModule : public FModule{
protected:
   TFsPath _path;
public:
   FLoggerModule();
   MO_ABSTRACT ~FLoggerModule();
public:
   MO_ABSTRACT TResult OnLoadConfig(FXmlNode* pConfig);
};
//------------------------------------------------------------
class MO_FL_DECLARE RLoggerModule : public RModuleSingleton<FLoggerModule>{
};

MO_NAMESPACE_END

#endif // __MO_FL_LOGGER_H__
