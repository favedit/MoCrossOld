#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造监听器集合。</T>
//============================================================
MListeners::MListeners(){
   MO_CLEAR(_pListeners);
}

//============================================================
// <T>析构监听器集合。</T>
//============================================================
MListeners::~MListeners(){
   Clear();
   MO_DELETE(_pListeners);
}

//============================================================
// <T>增加一个监听器。</T>
//
// @param pListener 监听器
//============================================================
void MListeners::Push(IListener* pListener){
   if(_pListeners == NULL){
      _pListeners = MO_CREATE(FListenerList);
   }
   _pListeners->Push(pListener);
}

//============================================================
// <T>移除一个监视器。</T>
//
// @param pListener 监听器
//============================================================
void MListeners::Remove(IListener* pListener){
   // 检查参数
   if(pListener == NULL){
      MO_ERROR(TC("Remove listener is null. (listener=0x%08X)"), pListener);
      return;
   }
   // 移除监听器
   if(_pListeners != NULL){
      if(_pListeners->Contains(pListener)){
         _pListeners->Remove(pListener);
      }else{
         MO_ERROR(TC("Remove listener is not exists. (listener=0x%08X)"), pListener);
      }
   }
   // 删除监听器
   if(pListener->ManageCd() == EListenerManage_Auto){
      MO_DELETE(pListener);
   }
}

//============================================================
// <T>调用处理。</T>
//
// @param pParameters 参数集合
// @return 处理结果
//============================================================
TBool MListeners::Process(TAny* pParameters){
   TBool result = ETrue;
   if(_pListeners != NULL){
      TListIteratorC<IListener*> iterator = _pListeners->IteratorC();
      while(iterator.Next()){
         if(!iterator->Process(pParameters)){
            result = EFalse;
         }
      }
   }
   return result;
}

//============================================================
// <T>清空监听器集合。</T>
//============================================================
void MListeners::Clear(){
   if(_pListeners == NULL){
      return;
   }
   TListIteratorC<IListener*> iterator = _pListeners->IteratorC();
   while(iterator.Next()){
      IListener* pListener = *iterator;
      if(pListener->ManageCd() == EListenerManage_Auto){
         MO_DELETE(pListener);
      }
   }
   _pListeners->Clear();
}

MO_NAMESPACE_END
