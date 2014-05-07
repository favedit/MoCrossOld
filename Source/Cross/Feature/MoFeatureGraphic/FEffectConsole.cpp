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
   // 设置环境
   GPtr<FTemplateContext> context = FTemplateContext::InstanceCreate();
   context->SetSpace("shader");
   context->SetTrimBeginLine(ETrue);
   context->SetTrimEmptyLine(ETrue);
   context->SetTrimEndLine(ETrue);
   context->SetTrimCommentLine(ETrue);
#ifdef _MO_ANDROID
   context->DefineBool("os.android", ETrue);
#endif // _MO_ANDROID
   context->DefineString("device.render",                  pRenderDevice->Name());
   context->DefineInt("shader.buffer.global.static",       RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_GlobalStatic));
   context->DefineInt("shader.buffer.global.dynamic",      RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_GlobalDynamic));
   context->DefineInt("shader.buffer.technique.static",    RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_TechniqueStatic));
   context->DefineInt("shader.buffer.technique.dynamic",   RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_TechniqueDynamic));
   context->DefineInt("shader.buffer.effect.static",       RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_EffectStatic));
   context->DefineInt("shader.buffer.effect.dynamic",      RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_EffectDynamic));
   context->DefineInt("shader.buffer.renderable.dynamic",  RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_RenderableDynamic));
   context->DefineInt("shader.buffer.renderable.material", RRenderShaderBuffer::ParseSlot(ERenderShaderBuffer_RenderableMaterial));
   //............................................................
   // 创建效果器
   FEffect* pEffect = _pFactory->Create<FEffect>(pName);
   pEffect->SetName(pName);
   pEffect->SetRenderDevice(pRenderDevice);
   pEffect->Setup();
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
   FXmlNode* pTemplateConfig = pTemplate->Config();
   //pEffect->LoadConfig(context->MergeConfig());
   // 解析定义信息
   pEffect->LoadConfig(pTemplateConfig);
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
   // 构建处理
   pEffect->Build();
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
