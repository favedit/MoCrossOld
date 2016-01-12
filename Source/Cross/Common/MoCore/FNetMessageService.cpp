#include "MoCrMessage.h"
#include "MoCrNetTransfer.h"

#define MO_TAG_PROPERTY TC("Property")
#define MO_PTR_NAME     TC("name")

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造消息服务。</T>
//============================================================
FNetMessageService::FNetMessageService(){
   _groupId = 0;
   _serverId = 0;
   _workerCount = 0;
}

//============================================================
// <T>析构消息服务。</T>
//============================================================
FNetMessageService::~FNetMessageService(){
}

//============================================================
// <T>加载设置信息。</T>
//
// @param pConfig 设置节点
// @return 处理结果
//============================================================
TResult FNetMessageService::LoadConfig(FXmlNode* pConfig){
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next(MO_TAG_PROPERTY)){
      FXmlNode* pNode = *iterator;
      if(pNode->IsAttribute(MO_PTR_NAME, TC("group_id"))){
         // 读取服务的组号
         _groupId = pNode->TextAsInt();
         MO_DEBUG(TC("Load service(%s) property. (group_id=%d)"), (TCharC*)_name, _groupId);
      }else if(pNode->IsAttribute(MO_PTR_NAME, TC("id"))){
         // 读取服务的编号
         _serverId = pNode->TextAsInt();
         MO_DEBUG(TC("Load service(%s) property. (id=%d)"), (TCharC*)_name, _serverId);
      }else if(pNode->IsAttribute(MO_PTR_NAME, TC("label"))){
         // 读取服务的名称
         _label = pNode->Text();
         MO_DEBUG(TC("Load service(%s) property. (label=%s)"), (TCharC*)_name, (TCharC*)_label);
      }else if(pNode->IsAttribute(MO_PTR_NAME, TC("worker_count"))){
         // 读取工作器数量
         _workerCount = pNode->TextAsInt();
         MO_DEBUG(TC("Load service(%s) property. (worker_count=%d)"), (TCharC*)_name, _workerCount);
      }
   }
   return ESuccess;
}

//============================================================
// <T>处理未注册过的消息。</T>
//
// @param pMessage 消息对象
// @return 处理结果
//============================================================
TResult FNetMessageService::OnUnknownRouter(TNetRouter* pRouter){
   MO_ASSERT(pRouter);
   // 未处理消息
#ifdef _MO_DEBUG
   TChar dump[MO_FS_DUMP_LENGTH];
   TChar format[MO_FS_DUMP_LENGTH];
   MO_ERROR(TC("Unknown process router.\n%s\n%s"),
         pRouter->Dump(dump, MO_FS_DUMP_LENGTH),
         pRouter->DumpMemory(format, MO_FS_DUMP_LENGTH));
#else
   TUint16 code = pRouter->MessageHead().Code();
   TCharC* pName = RNetMessageFactory::CodeName(code);
   TNetRouterHead& routerHead = pRouter->RouterHead();
   MO_ERROR(TC("Unknown process router. (name=%s, code=%d, source_type=%d, source_type=%d)"),
         pName, code, routerHead.SourceType(), routerHead.SourceType(), routerHead.TargetType());
#endif
   return ESuccess;
}

//============================================================
// <T>注册所有消息。</T>
//
// @return 处理结果
//============================================================
TResult FNetMessageService::RegisterAllProcessors(){
   return ESuccess;
}

//============================================================
// <T>注销所有消息。</T>
//
// @return 处理结果
//============================================================
TResult FNetMessageService::UnregisterAllProcessors(){
   return ESuccess;
}

//============================================================
// <T>处理消息。</T>
//
// @param pMessage 消息对象
// @return 处理结果
//============================================================
TResult FNetMessageService::ProcessTransfer(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   // 处理消息
   TChar dump[MO_FS_DUMP_LENGTH];
   TChar format[MO_FS_DUMP_LENGTH];
   MO_ERROR(TC("Process router.\n%s%s"),
         pTransfer->Dump(dump, MO_FS_DUMP_LENGTH),
         pTransfer->DumpMemory(format, MO_FS_DUMP_LENGTH));
   return ESuccess;
}

//============================================================
// <T>纷发消息。</T>
//
// @param pMessage 消息对象
// @return 处理结果
//============================================================
TResult FNetMessageService::DispatchTransfer(TNetTransfer* pTransfer){
   MO_ASSERT(pTransfer);
   // 纷发消息
   TChar dump[MO_FS_DUMP_LENGTH];
   TChar format[MO_FS_DUMP_LENGTH];
   MO_ERROR(TC("Dispatch router.\n%s%s"),
         pTransfer->Dump(dump, MO_FS_DUMP_LENGTH),
         pTransfer->DumpMemory(format, MO_FS_DUMP_LENGTH));
   return ESuccess;
}

MO_NAMESPACE_END
