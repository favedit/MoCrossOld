#include "MoCmMemory.h"
#include "MoCmString.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>ÍøÂçÊý¾Ý¿é´æ´¢»º³å¡£</T>
//
// @class
// @history 100118 MAOCY ´´½¨
//============================================================
TSize RMemoryCapacity::Parse(TCharC* pSize){
   TStringRefer sizeString(pSize);
   TInt baseIndex = ENotFound;
   TSize base  = 1;
   baseIndex = sizeString.LastIndexOf('m');
   if(ENotFound == baseIndex){
      baseIndex = sizeString.LastIndexOf('M');
      if(ENotFound == baseIndex){
         baseIndex = sizeString.LastIndexOf('k');
         if(ENotFound == baseIndex){
            baseIndex = sizeString.LastIndexOf('K');
            if(ENotFound != baseIndex){
               base = 1024;
            }
         }else{
            base = 1024;
         }
      }else{
         base = 1024 * 1024;
      }
   }else{
      base = 1024 * 1024;
   }
   TInt value = 0;
   if(ENotFound == baseIndex ){
      TStringRefer valueString = sizeString.SubStrC(0, baseIndex);
      value = RInt::Parse((TCharC*)valueString);
   }else{
      value = RInt::Parse((TCharC*)sizeString);
   }
   return value * base;
}

//============================================================
TCharC* RMemoryCapacity::Format(TSize size, TChar* pBuffer, TSize length){
   return pBuffer;
}


MO_NAMESPACE_END
