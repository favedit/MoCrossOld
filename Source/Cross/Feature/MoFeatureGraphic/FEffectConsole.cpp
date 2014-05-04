#include "MoFgTechnique.h"
#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染效果控制台。</T>
//============================================================
FEffectConsole::FEffectConsole(){
   _pFactory = MO_CREATE(FClassFactory);
}

//============================================================
// <T>析构渲染效果控制台。</T>
//============================================================
FEffectConsole::~FEffectConsole(){
   MO_DELETE(_pFactory);
}

//============================================================
// <T>根据名称和渲染信息建立渲染器。</T>
//
// @param pName 名称
// @param pRenderable 渲染信息
// @return 效果器
//============================================================
FEffect* FEffectConsole::Build(TCharC* pName, FRenderable* pRenderable){
   MO_ASSERT(pName);
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   TCharC* pDeviceCode = pRenderDevice->Capability()->Code();
   //............................................................
   GPtr<FTemplateContext> context = FTemplateContext::InstanceCreate();
   context->SetSpace("shader");
   context->SetTrimBeginLine(ETrue);
   context->SetTrimEmptyLine(ETrue);
   context->SetTrimEndLine(ETrue);
   context->SetTrimCommentLine(ETrue);
#ifdef _MO_ANDROID
   context->DefineBool("os.android", ETrue);
#endif // _MO_ANDROID
   //............................................................
   // 创建效果器
   FEffect* pEffect = _pFactory->Create<FEffect>(pName);
   pEffect->SetRenderDevice(pRenderDevice);
   //............................................................
   // 建立渲染信息
   if(pRenderable != NULL){
      TFsText templateName;
      SRenderableDescriptor& descriptor = pRenderable->Descriptor();
      pEffect->BuildTemplate(descriptor, &templateName, context);
   }
   //............................................................
   // 打开模板
   TFsPath path;
   path.AssignFormat("asset:/shader/%s/%s.xml", pDeviceCode, pName);
   FTemplate* pTemplate = RTemplateManager::Instance().Load(context, path);
   pEffect->LoadConfig(pTemplate->Config());
   //pEffect->LoadConfig(context->MergeConfig());
   //............................................................
   // 建立顶点渲染代码
   context->SourceReset();
   if(pTemplate->Parse(context, "vertex") == ESuccess){
      TCharC* pVertexCode = context->SourceMemory();
      pEffect->SetVertexSource(pVertexCode);
   }else{
      MO_FATAL("Build vertex souce failure.");
   }
   // 建立像素渲染代码
   context->SourceReset();
   if(pTemplate->Parse(context, "fragment") == ESuccess){
      TCharC* pFragmentCode = context->SourceMemory();
      pEffect->SetFragmentSource(pFragmentCode);
   }else{
      MO_FATAL("Build fragment souce failure.");
   }
   //............................................................
   FRenderProgram* pProgram = pEffect->Program();
   // 解析定义信息
   FXmlNode* pConfig = pTemplate->Config();
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      //............................................................
      // 建立参数定义集合
      if(pNode->IsName("Parameter")){
         FRenderShaderParameter* pParameter = pRenderDevice->ClassFactory()->Create<FRenderShaderParameter>(MO_RENDEROBJECT_SHADERPARAMETER);
         pParameter->LoadConfig(pNode);
         pProgram->ParameterPush(pParameter);
         continue;
      }
      //............................................................
      // 建立属性定义集合
      if(pNode->IsName("Attribute")){
         FRenderShaderAttribute* pAttribute = pRenderDevice->ClassFactory()->Create<FRenderShaderAttribute>(MO_RENDEROBJECT_SHADERATTRIBUTE);
         pAttribute->LoadConfig(pNode);
         pProgram->AttributePush(pAttribute);
         continue;
      }
      //............................................................
      // 建立取样器定义集合
      if(pNode->IsName("Sampler")){
         FRenderShaderSampler* pSampler = pRenderDevice->ClassFactory()->Create<FRenderShaderSampler>(MO_RENDEROBJECT_SHADERSAMPLER);
         pSampler->LoadConfig(pNode);
         pProgram->SamplerPush(pSampler);
         continue;
      }
   }
   //............................................................
   // 配置处理
   pEffect->SetName(pName);
   pEffect->Setup();
   return pEffect;
}

//============================================================
// <T>查找一个模板渲染器。</T>
//
// @param pName 名称
// @return 渲染器
//============================================================
FEffect* FEffectConsole::FindTemplate(TCharC* pName){
   FEffect* pEffect = _templateEffects.Find(pName);
   if(pEffect == NULL){
      FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
      pEffect = _pFactory->Create<FEffect>(pName);
      MO_CHECK(pEffect, return NULL);
      pEffect->SetRenderDevice(pRenderDevice);
      pEffect->SetName(pName);
      _templateEffects.Set(pName, pEffect);
   }
   return pEffect;
}

//============================================================
// <T>根据名称和渲染信息获得一个效果器。</T>
//
// @param pName 名称
// @param pRenderable 渲染信息
// @return 效果器
//============================================================
FEffect* FEffectConsole::Find(TCharC* pName, FRenderable* pRenderable){
   // 构建渲染识别代码
   TFsCode flag;
   flag.Append(pName);
   if(pRenderable != NULL){
      SRenderableDescriptor& descriptor = pRenderable->Descriptor();
      if(!descriptor.setuped){
         pRenderable->BuildDescriptor();
         descriptor.Build();
      }
      FEffect* pTemplateEffect = FindTemplate(pName);
      pTemplateEffect->BuildTemplate(descriptor, &flag, NULL);
   }
   // 查找效果器
   FEffect* pEffect = _effects.Find(flag);
   if(pEffect == NULL){
      pEffect = Build(pName, pRenderable);
      pEffect->SetFlag(flag);
      _effects.Set(flag, pEffect);
   }
   return pEffect;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FEffectConsole::Suspend(){
   _effects.Clear();
   _templateEffects.Clear();
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FEffectConsole::Resume(){
   return ESuccess;
}

MO_NAMESPACE_END
