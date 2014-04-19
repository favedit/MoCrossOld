#ifndef __MO_FG_STREAM_H__
#define __MO_FG_STREAM_H__
//************************************************************

#ifndef __MO_FG_PUBLIC_H__
#include "MoFgPublic.h"
#endif // __MO_FG_PUBLIC_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>Êý¾ÝÁ÷¡£</T>
//============================================================
class MO_FG_DECLARE FDataStream : public FByteStream{
public:
   FDataStream();
   MO_ABSTRACT ~FDataStream();
public:
   void WriteByte4(TByte value1 = 0, TByte value2 = 0, TByte value3 = 0, TByte value4 = 0);
   void WriteFloat2(TFloat value1 = 0.0f, TFloat value2 = 0.0f);
   void WriteFloat4(TFloat value1 = 0.0f, TFloat value2 = 0.0f, TFloat value3 = 0.0f, TFloat value4 = 0.0f);
};

MO_NAMESPACE_END
      
//************************************************************
#endif // __MO_FG_STREAM_H__
