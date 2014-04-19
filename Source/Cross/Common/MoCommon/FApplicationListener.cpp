#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造应用监听器。</T>
//============================================================
FApplicationListener::FApplicationListener(){
   _listenerCd = EApplicationListener_Unknown;
   MO_CLEAR(_pListeners);
}

//============================================================
// <T>析构应用监听器。</T>
//============================================================
FApplicationListener::~FApplicationListener(){
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TBool FApplicationListener::Process(){
   switch(_listenerCd){
      case EApplicationListener_Interrrupt:
         _pListeners->Interrupt();
         break;
      case EApplicationListener_Reload:
         _pListeners->Reload();
         break;
      case EApplicationListener_Unload:
         _pListeners->Unload();
         break;
      case EApplicationListener_Shutdown:
         _pListeners->Shutdown();
         break;
      default:
         MO_ERROR(TC("Unknown listener code. (listener_cd=%d)"), _listenerCd);
         break;
   }
   return ETrue;
}

MO_NAMESPACE_END
