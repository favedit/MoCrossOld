#include <MoCmSystem.h>
#include "MoCrCommand.h"

MO_NAMESPACE_BEGIN

//============================================================
TCmdParser::TCmdParser(){
   _count = 0;
   RTypes<TCharC*>::Clear(_ppParameters, MO_SM_COMMAND_MAXCOUNT);
}

//============================================================
TCmdParser::TCmdParser(TCharC* pCommand){
   _count = 0;
   RTypes<TCharC*>::Clear(_ppParameters, MO_SM_COMMAND_MAXCOUNT);
   Parse(pCommand);
}

//============================================================
TBool TCmdParser::Parse(TCharC* pCommand){
   _command = pCommand;
   TChar* pTemp = _command.Memory();
   TInt index = 0;
   TBool flag = ETrue;
   TInt length = _command.Length();
   for(TInt n = 0; n < length; n++){
      if(*pTemp == '"'){
         flag = !flag;
         pTemp++;
      }
      if(*pTemp == ' ' && flag){
         *pTemp = 0;
         pTemp++;
         _ppParameters[index++] = pTemp;
      }else{
         pTemp++;
      }
      _code.Assign((TChar*) _command);
   }
   _count = index;
   return ETrue;
}

//============================================================
TBool TCmdParser::IsCode(TCharC* pCode){
   return _code.Equals(pCode);
}

//============================================================
TCharC* TCmdParser::Code(){
   return _code;
}

//============================================================
TInt TCmdParser::Count(){
   return _count;
}

//============================================================
TCharC* TCmdParser::Parameter(TInt index){
   MO_ASSERT_RANGE(index, 0, _count);
   return _ppParameters[index];
}

//============================================================
TInt TCmdParser::ParameterAsInt(TInt index){
   MO_ASSERT_RANGE(index, 0, _count);
   TCharC* pValue = _ppParameters[index];
   return RInt::Parse(pValue);
}

//============================================================
TBool TCmdParser::HasParameter(TCharC* pName){
   MO_ASSERT(pName);
   for(TInt n = 0; n < _count; n++){
      if(0 == MO_LIB_STRING_COMPARE_IGNORECASE(pName, _ppParameters[n])){
         return ETrue;
      }
   }
   return EFalse;
}

//============================================================
TCharC* TCmdParser::Parameter(TCharC* pName){
   MO_ASSERT(pName);
   TInt count = _count - 1;
   for(TInt n = 0; n < count; n++){
      if(0 == MO_LIB_STRING_COMPARE_IGNORECASE(pName, _ppParameters[n])){
         return _ppParameters[n + 1];
      }
   }
   return NULL;
}

MO_NAMESPACE_END
