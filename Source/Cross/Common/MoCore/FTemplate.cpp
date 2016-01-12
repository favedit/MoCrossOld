#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplate, FInstance);

//============================================================
// <T>构造模板。</T>
//============================================================
FTemplate::FTemplate(){
   _pSources = MO_CREATE(FTemplateSourceDictionary);
   _pIncludeTags = MO_CREATE(FTemplateTagIncludeCollection);
}

//============================================================
// <T>析构模板。</T>
//============================================================
FTemplate::~FTemplate(){
   MO_DELETE(_pSources);
   MO_DELETE(_pIncludeTags);
}

//============================================================
// <T>解析一个标签内的所有内容。</T>
//
// @param parent 父标签
// @param index 索引位置
// @param tag 当前标签
//============================================================
TResult FTemplate::ParseTag(FTemplateContext* pContext, FTemplateTag* pParentTag, TInt index, FTemplateTag* pTag){
   // 处理左换行符
   if(pTag->NowrapLeft()){
      pContext->TrimLastLine();
   }
   if(pTag->NowrapRight()){
      pContext->SetTrimOnceFlag();
   }
   // 处理开始部分
   ETemplateTagResult resultBeginCd = pTag->OnBegin(pContext);
   if(resultBeginCd == ETemplateTagResult_Include){
      // 防止无限递归
      for(TInt limit = 0; limit < 10000; limit++){
         FTemplateTagCollection* pChildren = pTag->Children();
         if(pChildren != NULL){
            TInt count = pChildren->Count();
            for(TInt n = 0; n < count; n++){
               FTemplateTag* pChild = pChildren->Get(n);
               ParseTag(pContext, pTag, n, pChild);
            }
            // 处理循环部分
            ETemplateTagResult resultLoopCd = pTag->OnLoop(pContext);
            if(resultLoopCd != ETemplateTagResult_Continue){
               break;
            }else{
               // 处理循环部分的右换行符
               if(pTag->NowrapRight()){
                  pContext->SetTrimOnceFlag();
               }
            }
         }
      }
      // 处理结束部分
      pTag->OnEnd(pContext);
      // 处理结束部分的右换行符
      if(pTag->NowrapRight()){
         pContext->TrimLastLine();
      }
   }
   return ESuccess;
}

//============================================================
// <T>析构模板。</T>
//============================================================
TResult FTemplate::Parse(FTemplateContext* pContext, TCharC* pPartName){
   // 查找来源
   FTemplateSource* pTemplateSource = NULL;
   if(pPartName == NULL){
      pTemplateSource = _pSources->Get(TC("default"));
   }else{
      pTemplateSource = _pSources->Get(pPartName);
   }
   // 解析内容
   FTemplateTag* pRootTag = pTemplateSource->RootTag();
   TResult resultCd = ParseTag(pContext, NULL, -1, pRootTag);
   pContext->SourceFormat();
   return resultCd;
}

//============================================================
// <T>调整配置节点。</T>
//============================================================
TResult FTemplate::AdjustConfigNodes(FTemplateContext* pContext){
   MO_CHECK(pContext, return ENull);
   FXmlNodes* pNodes = pContext->MergeConfig()->Nodes();
   TXmlNodeIteratorC iterator = _config->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pConfig = *iterator;
      // MO_INFO("%s - %s", pConfig->Name(), pConfig->Get("name", NULL));
      if(!pConfig->Nodes()->Contains(pConfig)){
         pNodes->Push(pConfig);
      }
   }
   return ESuccess;
}

//============================================================
// <T>跟踪信息。</T>
//============================================================
void TrackTag(MString* pResult, FTemplateTag* pTag, TInt level){
   pResult->AppendRepeat(TC("   "), level);
   pResult->AppendFormat(TC("[%s]"), pTag->Name());
   FTemplateTagCollection* pChildren = pTag->Children();
   if(pChildren != NULL){
      TInt count = pChildren->Count();
      pResult->AppendFormat(TC(":%d\n"), count);
      for(TInt n = 0; n < count; n++){
         FTemplateTag* pChildTag = pChildren->Get(n);
         TrackTag(pResult, pChildTag, level + 1);
      }
   }else{
      pResult->Append(TC("\n"));
   }
}

//============================================================
// <T>跟踪信息。</T>
//
// @return 处理结果
//============================================================
TResult FTemplate::Track(){
   FString* pResult = MO_CREATE(FString);
   FTemplateSourceDictionary::TIteratorC iterator = _pSources->IteratorC();
   while(iterator.Next()){
      FTemplateSource* pTemplateSource = *iterator;
      TrackTag(pResult, pTemplateSource->RootTag(), 0);
   }
   MO_DEBUG(TC("Dump template info:\n%s"), pResult->MemoryC());
   MO_DELETE(pResult);
   return ESuccess;
}

MO_NAMESPACE_END
