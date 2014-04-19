#ifndef __MO_CR_COMMAND_H__
#define __MO_CR_COMMAND_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

//============================================================
#define MO_SM_COMMAND_MAXCOUNT 256

MO_NAMESPACE_BEGIN

//============================================================
// command value
// command -name value
// command -name "hello text"
//============================================================
class MO_CR_DECLARE TCmdParser{
protected:
   TFsName _code;
   TInt _count;
   TCharC* _ppParameters[MO_SM_COMMAND_MAXCOUNT];
   TFsCommand _command;
public:
   TCmdParser();
   TCmdParser(TCharC* pCommand);
public:
   TBool Parse(TCharC* pCommand);
public:
   TBool IsCode(TCharC* pCode);
   TCharC* Code();
public:
   TInt Count();
   TCharC* Parameter(TInt index);
   TInt ParameterAsInt(TInt index);
   TBool HasParameter(TCharC* pName);
   TCharC* Parameter(TCharC* pName);
};

MO_NAMESPACE_END

#endif // __MO_CR_COMMAND_H__
