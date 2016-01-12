#include "MoCrAsset.h"
#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateConsole, FConsole);

//============================================================
// <T>构造模板控制台。</T>
//============================================================
FTemplateConsole::FTemplateConsole(){
   _pParser = MO_CREATE(FTemplateParser);
   _pTemplates = MO_CREATE(FTemplateDictionary);
   _pParser->SetSpace(TC("shader"));
}

//============================================================
// <T>析构模板控制台。</T>
//============================================================
FTemplateConsole::~FTemplateConsole(){
   MO_DELETE(_pParser);
   MO_DELETE(_pTemplates);
}

//============================================================
// <T>解析一个标签内的所有内容。</T>
//
// @param parent 父标签
// @param index 索引位置
// @param tag 当前标签
//============================================================
FTemplate* FTemplateConsole::Load(FTemplateContext* pContext, TCharC* pTemplateName){
   MO_CHECK(pContext, return NULL);
   MO_CHECK(pTemplateName, return NULL);
   // 查找模板
   FTemplate* pTemplate = _pTemplates->Find(pTemplateName);
   if(pTemplate != NULL){
      return pTemplate;
   }
   //............................................................
   // 打开模板数据
   TStringBuffer source;
   TResult result = RAssetManager::Instance().OpenAssetString(&source, pTemplateName);
   if(result != ESuccess){
      MO_FATAL(TC("Open template failure. (name=%s)"), pTemplateName);
      return NULL;
   }
   TCharC* pSpace = pContext->Space();
   GPtr<FXmlParser> xmlParser = FXmlParser::InstanceCreate();
   FXmlNode* pConfig = xmlParser->LoadNode(source);
   //............................................................
   // 创建模板
   pTemplate = FTemplate::InstanceCreate();
   pTemplate->SetConfig(pConfig);
   pTemplate->AdjustConfigNodes(pContext);
   //............................................................
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      if(pNode->IsName(TC("Source"))){
         // 处理包含情况
         if(pNode->Contains(TC("include"))){
         }else{
            //............................................................
            // 解析内容
            TCharC* pName = pNode->Get(TC("name"), TC("default"));
            TCharC* pSource = pNode->Text();
            // 创建构建器
            GPtr<FTemplateBuilder> builder = FTemplateBuilder::InstanceCreate();
            builder->SetConsole(this);
            builder->SetContext(pContext);
            builder->SetTemplate(pTemplate);
            // 创建解析器
            GPtr<FTemplateParser> parser = FTemplateParser::InstanceCreate();
            parser->SetBuilder(builder);
            parser->SetSpace(pSpace);
            // 解析内容
            FTemplateSource* pTemplateSource = parser->Load(pSource);
            MO_FATAL_CHECK(pTemplateSource, return NULL, TC("Build template failure."));
            pTemplate->Sources()->Set(pName, pTemplateSource);
         }
      }
   }
   //............................................................
   // 存储模板
   _pTemplates->Set(pTemplateName, pTemplate);
   return pTemplate;
}

MO_NAMESPACE_END
