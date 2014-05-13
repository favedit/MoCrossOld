#include "MoErTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FAutomaticEffect, FEffect);

//============================================================
// <T>构造渲染效果。</T>
//============================================================
FAutomaticEffect::FAutomaticEffect(){
   // 设置功能
   _descriptor.supportVertexColor = ETrue;
   _descriptor.supportVertexCoord = ETrue;
   _descriptor.supportVertexNormal = ETrue;
   _descriptor.supportVertexNormalFull = ETrue;
   _descriptor.supportAlpha = ETrue;
   _descriptor.supportBump = ETrue;
   _descriptor.supportAmbient = ETrue;
   _descriptor.supportDiffuse = ETrue;
   _descriptor.supportDiffuseView = ETrue;
   _descriptor.supportSpecularColor = ETrue;
   _descriptor.supportSpecularLevel = ETrue;
   _descriptor.supportSpecularView = ETrue;
   _descriptor.supportEnvironment = ETrue;
   _descriptor.supportLight = ETrue;
   _descriptor.supportReflect = ETrue;
   _descriptor.supportRefract = ETrue;
   _descriptor.supportEmissive = ETrue;
   _descriptor.supportHeight = ETrue;
   // 设置缓冲
   _pParameters = MO_CREATE(FRenderShaderParameterCollection);
   _pParameters->SetCount(EEffectParameter_Count);
   _pAttributes = MO_CREATE(FRenderShaderAttributeCollection);
   _pAttributes->SetCount(EEffectAttribute_Count);
   _pSamplers = MO_CREATE(FRenderShaderSamplerCollection);
   _pSamplers->SetCount(ERenderSampler_Count);
}

//============================================================
// <T>析构渲染效果。</T>
//============================================================
FAutomaticEffect::~FAutomaticEffect(){
   MO_DELETE(_pParameters);
   MO_DELETE(_pAttributes);
   MO_DELETE(_pSamplers);
}

//============================================================
// <T>加载配置信息。</T>
//
// @param pConfig 配置处理
// @return 处理结果
//============================================================
TResult FAutomaticEffect::LoadConfig(FXmlNode* pConfig){
   FEffect::LoadConfig(pConfig);
   _config = pConfig;
   return ESuccess;
}

//============================================================
// <T>绑定描述器集合。</T>
//
// @return 处理结果
//============================================================
TResult FAutomaticEffect::BindDescriptors(){
   MO_CHECK(_program, return ENull);
   // 绑定属性位置
   //TEffectAttributeDescriptors::TIteratorC iterator = _attributeDescriptors.IteratorC();
   //while(iterator.Next()){
   //   const SEffectAttributeDescriptor& descriptor = *iterator;
   //   _program->BindAttribute(descriptor.bindIndex, descriptor.namePtr);
   //}
   //............................................................
   // 建立常量描述器
   GRenderShaderParameterDictionary& parameters = _program->Parameters();
   if(!parameters.IsEmpty()){
      GRenderShaderParameterDictionary::TIterator iterator = parameters.Iterator();
      while(iterator.Next()){
         FRenderShaderParameter* pParameter = *iterator;
         if(pParameter->IsStatusUsed()){
            // 解析内容
            TCharC* pLinker = pParameter->Linker();
            EEffectParameter parameterCd = EEffectParameter_Unknown;
            ERenderShader shaderCd = ERenderShader_Unknown;
            ERenderShaderParameterFormat formatCd = ERenderShaderParameterFormat_Unknown;
            REffectParameter::Parse(pLinker, parameterCd, shaderCd, formatCd);
            // 设置参数
            pParameter->SetCode(parameterCd);
            pParameter->SetShaderCd(shaderCd);
            pParameter->SetFormatCd(formatCd);
            _pParameters->Set(parameterCd, pParameter);
            MO_DEBUG("Build shader parameter. (code=%d, name=%s, format=%d)", parameterCd, pParameter->Name(), pParameter->FormatCd());
         }
      }
   }
   //............................................................
   // 建立属性描述器
   GRenderShaderAttributeDictionary& attributes = _program->Attributes();
   if(!attributes.IsEmpty()){
      GRenderShaderAttributeDictionary::TIterator iterator = attributes.Iterator();
      while(iterator.Next()){
         FRenderShaderAttribute* pAttribute = *iterator;
         if(pAttribute->IsStatusUsed()){
            // 解析内容
            TCharC* pLinker = pAttribute->Linker();
            EEffectAttribute attributeCd = EEffectAttribute_Unknown;
            ERenderShaderAttributeFormat formatCd = ERenderShaderAttributeFormat_Unknown;
            REffectAttribute::Parse(pLinker, attributeCd, formatCd);
            // 设置参数
            pAttribute->SetCode(attributeCd);
            pAttribute->SetFormatCd(formatCd);
            _pAttributes->Set(attributeCd, pAttribute);
         }
      }
   }
   //............................................................
   // 建立属性描述器
   GRenderShaderSamplerDictionary& sampler = _program->Samplers();
   if(!sampler.IsEmpty()){
      GRenderShaderSamplerDictionary::TIterator iterator = sampler.Iterator();
      while(iterator.Next()){
         FRenderShaderSampler* pSampler = *iterator;
         if(pSampler->IsStatusUsed()){
            TCharC* pLinker = pSampler->Linker();
            // 解析内容
            ERenderSampler samplerCd = RRenderSampler::Parse(pLinker);
            ERenderSampler packCd = RRenderSampler::ParsePack(samplerCd);
            // 设置参数
            pSampler->SetCode(samplerCd);
            _pSamplers->Set(samplerCd, pSampler);
            _pSamplers->Set(packCd, pSampler);
         }
      }
   }
   return ESuccess;
}

//============================================================
// <T>关联描述器集合。</T>
//
// @return 处理结果
//============================================================
TResult FAutomaticEffect::LinkDescriptors(){
   MO_CHECK(_program, return ENull);
   //TInt fragmentConstLimit = _renderDevice->Capability()->FragmentConstLimit();
   //// 关联常量集合
   //TEffectParameterDescriptors::TIterator parameterIterator = _parameterDescriptors.Iterator();
   //while(parameterIterator.Next()){
   //   SEffectParameterDescriptor& descriptor = *parameterIterator;
   //   if((descriptor.code != -1) && (descriptor.bindId == -1)){
   //      descriptor.bindId = _program->FindDefine(descriptor.namePtr);
   //      MO_INFO("Find const location. (name=%s, code=%d, bind_id=%d)",
   //            descriptor.namePtr, descriptor.code, descriptor.bindId);
   //   }
   //}
   //// 关联属性集合
   //TEffectAttributeDescriptors::TIterator attributeIterator = _attributeDescriptors.Iterator();
   //while(attributeIterator.Next()){
   //   SEffectAttributeDescriptor& descriptor = *attributeIterator;
   //   if((descriptor.bindIndex != -1) && (descriptor.bindId == -1)){
   //      descriptor.bindId = _program->FindAttribute(descriptor.namePtr);
   //      if(descriptor.bindId != -1){
   //         MO_INFO("Find attribute location. (name=%s, code=%d, bind_id=%d)",
   //               descriptor.namePtr, descriptor.code, descriptor.bindId);
   //      }
   //   }
   //}
   //// 关联取样器集合
   //TEffectSamplerDescriptors::TIterator samplerIterator = _samplerDescriptors.Iterator();
   //while(samplerIterator.Next()){
   //   SEffectSamplerDescriptor& descriptor = *samplerIterator;
   //   if((descriptor.code != -1) && (descriptor.bindId == -1)){
   //      descriptor.bindId = _program->FindDefine(descriptor.namePtr);
   //   }
   //}
   //TInt samplerIndex = 0;
   //for(TInt n = 0; n < 1024; n++){
   //   SEffectSamplerDescriptor* pDescriptor = _samplerDescriptors.FindByBindId(n);
   //   if(pDescriptor != NULL){
   //      pDescriptor->index = samplerIndex++;
   //      MO_INFO("Find sampler location. (name=%s, code=%d, bind_id=%d, index=%d)",
   //            pDescriptor->namePtr, pDescriptor->code, pDescriptor->bindId, pDescriptor->index);
   //   }
   //}
   return ESuccess;
}

//============================================================
// <T>绑定常量三维坐标。</T>
//
// @param shaderCd 渲染类型
// @param bindCd 绑定类型
// @param point 三维坐标
//============================================================
TResult FAutomaticEffect::BindConstPosition3(TInt bindCd, SFloatPoint3& point){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if(pParameter != NULL){
      pParameter->SetFloat3(point.x, point.y, point.z);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量三维方向。</T>
//
// @param shaderCd 渲染类型
// @param bindCd 绑定类型
// @param vector 三维方向
//============================================================
TResult FAutomaticEffect::BindConstVector3(TInt bindCd, SFloatVector3& vector){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if(pParameter != NULL){
      pParameter->SetFloat3(vector.x, vector.y, vector.z);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量四维浮点数。</T>
//
// @param bindCd 绑定代码
// @param x 浮点数X
// @param y 浮点数Y
// @param z 浮点数Z
// @param w 浮点数W
//============================================================
TResult FAutomaticEffect::BindConstFloat4(TInt bindCd, TFloat x, TFloat y, TFloat z, TFloat w){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if(pParameter != NULL){
      pParameter->SetFloat4(x, y, z, w);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量四维浮点数。</T>
//
// @param bindCd 绑定代码
// @param x 浮点数X
// @param y 浮点数Y
// @param z 浮点数Z
// @param w 浮点数W
//============================================================
TResult FAutomaticEffect::BindConstColor4(TInt bindCd, const SFloatColor4& color){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if(pParameter != NULL){
      pParameter->SetFloat4(color.red, color.green, color.blue, color.alpha);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量4X4矩阵。</T>
//
// @param shaderCd 渲染类型
// @param bindCd 绑定类型
// @param matrix 4X4矩阵
//============================================================
TResult FAutomaticEffect::BindConstMatrix3x3(TInt bindCd, SFloatMatrix3d* pMatrix, TInt count){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if((pParameter != NULL) && (count > 0)){
      pParameter->SetMatrix3x3(pMatrix, count, ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量4X4矩阵。</T>
//
// @param shaderCd 渲染类型
// @param bindCd 绑定类型
// @param matrix 4X4矩阵
//============================================================
TResult FAutomaticEffect::BindConstMatrix4x3(TInt bindCd, SFloatMatrix3d* pMatrix, TInt count){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if((pParameter != NULL) && (count > 0)){
      pParameter->SetMatrix4x3(pMatrix, count, ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量4X4矩阵。</T>
//
// @param shaderCd 渲染类型
// @param bindCd 绑定类型
// @param matrix 4X4矩阵
//============================================================
TResult FAutomaticEffect::BindConstMatrix4x4(TInt bindCd, SFloatMatrix3d* pMatrix, TInt count){
   FRenderShaderParameter* pParameter = _pParameters->Get(bindCd);
   if((pParameter != NULL) && (count > 0)){
      pParameter->SetMatrix4x4(pMatrix, count, ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>绑定属性描述器集合。</T>
//
// @param pRenderable 渲染对象
// @return 处理结果
//============================================================
TResult FAutomaticEffect::BindAttributeDescriptors(FRenderable* pRenderable){
   // 获得属性
   FRenderVertexStreams* pVertexStreams = pRenderable->VertexStreams();
   MO_CHECK(pVertexStreams, return ENull);
   //............................................................
   // 创建布局
   FRenderableEffect* pRenderableEffect = pRenderable->ActiveEffect();
   FRenderLayout* pLayout = pRenderableEffect->Layout();
   if(pLayout == NULL){
      pLayout = _renderDevice->CreateObject<FRenderLayout>(MO_RENDEROBJECT_LAYOUT);
      pLayout->SetProgram(_program);
      pLayout->SetRenderable(pRenderable);
      pLayout->Setup();
      pRenderableEffect->SetLayout(pLayout);
   }
   //............................................................
   // 关联属性集合
   //GRenderShaderAttributeDictionary::TIterator iterator = _program->Attributes().IteratorC();
   //while(iterator.Next()){
   //   FRenderShaderAttribute* pAttribute = *iterator;
   //   if(pAttribute->IsStatusUsed()){
   //      ERenderVertexBuffer bufferCd = (ERenderVertexBuffer)pAttribute->Code();
   //      FRenderVertexStream* pVertexStream = pVertexStreams->FindStream(bufferCd);
   //      if(pVertexStream != NULL){
   //         _renderDevice->BindVertexStream(pAttribute->Slot(), pVertexStream);
   //         //MO_INFO("Bind attribute stream. (name=%s, bind_id=%d, code=%d, format=%d)", descriptor.namePtr, descriptor.bindId, descriptor.code, descriptor.formatCd);
   //      }
   //   }
   //}
   //............................................................
   _renderDevice->SetLayout(pLayout);
   return ESuccess;
}

//============================================================
// <T>绑定属性描述器集合。</T>
//
// @param pRenderable 渲染对象
// @return 处理结果
//============================================================
TResult FAutomaticEffect::UnbindAttributeDescriptors(FRenderable* pRenderable){
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   MO_CHECK(pRenderDevice, return ENull);
   //............................................................
   //// 获得属性
   //FRenderVertexStreams* pVertexStreams = pRenderable->VertexStreams();
   //MO_CHECK(pVertexStreams, return ENull);
   ////............................................................
   //// 关联属性集合
   //TEffectAttributeDescriptors::TIterator attributeIterator = _attributeDescriptors.Iterator();
   //while(attributeIterator.Next()){
   //   SEffectAttributeDescriptor& descriptor = *attributeIterator;
   //   if(descriptor.bindId != -1){
   //      pRenderDevice->BindVertexStream(descriptor.bindId, NULL);
   //   }
   //}
   return ESuccess;
}

//============================================================
// <T>绑定取样器描述器集合。</T>
//
// @param pRenderable 渲染对象
// @return 处理结果
//============================================================
TResult FAutomaticEffect::BindSampler(TInt bindCd, FRenderTexture* pTexture){
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   MO_CHECK(pRenderDevice, return ENull);
   //............................................................
   // 关联属性集合
   FRenderShaderSampler* pSampler = _pSamplers->Get(bindCd);
   if(pSampler != NULL){
      //pTexture->SetIndex(descriptor.index);
      pRenderDevice->BindTexture(pSampler->Slot(), pTexture);
   }
   return ESuccess;
}

//============================================================
// <T>绑定取样器描述器集合。</T>
//
// @param pRenderable 渲染对象
// @return 处理结果
//============================================================
TResult FAutomaticEffect::BindSamplerDescriptors(FRenderable* pRenderable){
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   MO_CHECK(pRenderDevice, return ENull);
   //............................................................
   // 关联属性集合
   GRenderShaderSamplerDictionary::TIterator iterator = _program->Samplers().IteratorC();
   while(iterator.Next()){
      FRenderShaderSampler* pSampler = *iterator;
      if(pSampler->IsStatusUsed()){
         ERenderSampler packCd = pSampler->PackCd();
         FRenderTexture* pTexture = pRenderable->FindTexture(packCd);
         if(pTexture != NULL){
            // pTexture->SetIndex(pSampler->Slot());
            pRenderDevice->BindTexture(pSampler->Slot(), pTexture);
         }
      }
   }
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FAutomaticEffect::OnSetup(){
   TXmlNodeIteratorC iterator = _config->NodeIteratorC();
   //while(iterator.Next()){
   //   FXmlNode* pNode = *iterator;
   //   //............................................................
   //   // 建立参数定义集合
   //   if(pNode->IsName("Parameter")){
   //      TCharC* pName = pNode->Get("name");
   //      TCharC* pBuffer = pNode->Get("linker");
   //      TCharC* pFormat = pNode->Get("format");
   //      ERenderVertexBuffer bufferCd = RRenderVertexBuffer::Parse(pBuffer);
   //      ERenderVertexFormat formatCd = RRenderVertexFormat::Parse(pFormat);
   //      //_constDescriptors.Register(bufferCd, pName, formatCd);
   //      continue;
   //   }
   //   //............................................................
   //   // 建立属性定义集合
   //   if(pNode->IsName("Attribute")){
   //      TCharC* pName = pNode->Get("name");
   //      TCharC* pBuffer = pNode->Get("linker");
   //      TCharC* pFormat = pNode->Get("format");
   //      ERenderVertexBuffer bufferCd = RRenderVertexBuffer::Parse(pBuffer);
   //      ERenderVertexFormat formatCd = RRenderVertexFormat::Parse(pFormat);
   //      //_attributeDescriptors.Register(bufferCd, pName, formatCd);
   //      continue;
   //   }
   //   //............................................................
   //   // 建立取样器定义集合
   //   if(pNode->IsName("Sampler")){
   //      TCharC* pName = pNode->Get("name");
   //      TCharC* pBuffer = pNode->Get("linker");
   //      TCharC* pSource = pNode->Get("source");
   //      EEffectSampler bufferCd = REffectSampler::Parse(pBuffer);
   //      ERenderSampler samplerCd = RRenderSampler::Parse(pSource);
   //      //_samplerDescriptors.Register(bufferCd, pName, samplerCd);
   //      continue;
   //   }
   //}
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FAutomaticEffect::Build(){
   //............................................................
   // 编译程序
   _program->VertexShader()->Compile(_vertexSource);
   _program->FragmentShader()->Compile(_fragmentSource);
   //............................................................
   // 建立处理
   _program->Build();
   BindDescriptors();
   //............................................................
   // 关联处理
   _program->Link();
   LinkDescriptors();
   //............................................................
   // 改变大小
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   SIntSize2& screenSize = pScreenDevice->Size();
   Resize(screenSize.width, screenSize.height);
   return ESuccess;
}

//============================================================
// <T>根据渲染描述其建立效果描述器。</T>
//
// @param renderableDescriptor 渲染描述器
// @return 效果描述器
//============================================================
TResult FAutomaticEffect::BuildDescripter(SRenderableDescriptor& renderableDescriptor){
   return ESuccess;
}

//============================================================
// <T>建立模板信息。</T>
//
// @param renderableDescriptor 渲染描述
// @param pCode 代码
// @param pTemplateContext 模板环境
// @return 处理结果
//============================================================
TResult FAutomaticEffect::BuildTemplate(SRenderableDescriptor& renderableDescriptor, MString* pCode, FTemplateContext* pTemplateContext){
   // 设置缓冲编号
   if(pTemplateContext){
      pTemplateContext->DefineInt("parameter.buffer.global", 0);
      pTemplateContext->DefineInt("parameter.buffer.effectcamera", 1);
      pTemplateContext->DefineInt("parameter.buffer.effectlight", 2);
      pTemplateContext->DefineInt("parameter.buffer.rendertramsform", 3);
      pTemplateContext->DefineInt("parameter.buffer.rendermaterial", 3);
   }
   //............................................................
   // 支持顶点颜色
   _dynamicDescriptor.supportVertexColor = (_descriptor.supportVertexColor && renderableDescriptor.supportVertexColor);
   if(_dynamicDescriptor.supportVertexColor){
      pCode->Append("|AC");
      if(pTemplateContext){
         pTemplateContext->DefineBool("vertex.attribute.color", ETrue);
      }
   }
   // 支持顶点纹理
   _dynamicDescriptor.supportVertexCoord = (_descriptor.supportVertexCoord && renderableDescriptor.supportVertexCoord);
   if(_dynamicDescriptor.supportVertexCoord){
      pCode->Append("|AD");
      if(pTemplateContext){
         pTemplateContext->DefineBool("vertex.attribute.coord", ETrue);
      }
   }
   // 支持法线
   _dynamicDescriptor.supportVertexNormal = (_descriptor.supportVertexCoord && renderableDescriptor.supportNormal);
   if(_dynamicDescriptor.supportVertexNormal){
      pCode->Append("|AN");
      if(pTemplateContext){
         pTemplateContext->DefineBool("vertex.attribute.normal", ETrue);
      }
   }
   // 支持全法线
   _dynamicDescriptor.supportVertexNormalFull = (_descriptor.supportVertexCoord && renderableDescriptor.supportVertexNormalFull);
   if(_dynamicDescriptor.supportVertexNormalFull){
      pCode->Append("|AF");
      if(pTemplateContext){
         pTemplateContext->DefineBool("vertex.attribute.normal.full", ETrue);
      }
   }
   //............................................................
   // 支持实例技术
   TBool techniqueInstance = RTechniqueManager::Instance().Capability().optionInstance;
   _dynamicDescriptor.supportInstance = (_descriptor.supportInstance && techniqueInstance);
   if(_dynamicDescriptor.supportInstance){
      pCode->Append("|SI");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.instance", ETrue);
      }
   }
   // 支持骨骼技术
   _dynamicDescriptor.supportSkeleton = _descriptor.supportSkeleton;
   if(_dynamicDescriptor.supportSkeleton){
      pCode->Append("|SS");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.skeleton", ETrue);
      }
   }
   //............................................................
   // 支持透明技术
   _dynamicDescriptor.supportAlpha = _descriptor.supportAlpha && renderableDescriptor.supportAlpha;
   if(_dynamicDescriptor.supportAlpha){
      pCode->Append("|RA");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.alpha", ETrue);
      }
      _descriptor.optionBlendMode = ETrue;
   }else{
      _descriptor.optionBlendMode = EFalse;
   }
   // 支持环境色技术
   _dynamicDescriptor.supportAmbient = _descriptor.supportAmbient;
   if(_dynamicDescriptor.supportAmbient){
      pCode->Append("|TA");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.ambient", ETrue);
      }
      if(renderableDescriptor.ContainsSampler(ERenderSampler_Diffuse)){
         pCode->Append("|TAS");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.ambient.sampler", ETrue);
         }
      }
   }
   // 支持散射技术
   _dynamicDescriptor.supportDiffuse = (_descriptor.supportDiffuse && (renderableDescriptor.supportNormal || renderableDescriptor.supportBump));
   if(_descriptor.supportDiffuse){
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.diffuse", ETrue);
      }
      if(renderableDescriptor.supportBump){
         pCode->Append("|TDD");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.diffuse.dump", ETrue);
         }
      }else if(renderableDescriptor.supportNormal){
         pCode->Append("|TDN");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.diffuse.normal", ETrue);
         }
      }
   }
   // 支持视角散射技术
   _dynamicDescriptor.supportDiffuseView = (_descriptor.supportDiffuseView && (renderableDescriptor.supportNormal || renderableDescriptor.supportBump));
   if(_descriptor.supportDiffuseView){
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.diffuse.view", ETrue);
      }
      if(renderableDescriptor.supportBump){
         pCode->Append("|TDVD");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.diffuse.view.dump", ETrue);
         }
      }else if(renderableDescriptor.supportNormal){
         pCode->Append("|TDVN");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.diffuse.view.normal", ETrue);
         }
      }
   }
   // 支持高光技术
   _dynamicDescriptor.supportSpecularColor = (_descriptor.supportSpecularColor && renderableDescriptor.supportSpecularColor);
   _dynamicDescriptor.supportSpecularLevel = (_descriptor.supportSpecularLevel && renderableDescriptor.supportSpecularLevel);
   if((_dynamicDescriptor.supportSpecularColor || _dynamicDescriptor.supportSpecularLevel) && renderableDescriptor.supportNormal){
      pCode->Append("|TS");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.specular", ETrue);
      }
      // 支持高光颜色技术
      if(_dynamicDescriptor.supportSpecularColor){
         pCode->Append("|TSC");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.specular.color", ETrue);
         }
      }
      // 支持高光级别技术
      if(renderableDescriptor.supportSpecularLevel){
         pCode->Append("|TSL");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.specular.level", ETrue);
         }
      }else{
         pCode->Append("|NSL");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.specular.normal", ETrue);
         }
      }
   }
   // 支持视角高光技术
   _dynamicDescriptor.supportSpecularView = _descriptor.supportSpecularView;
   if(_dynamicDescriptor.supportSpecularView && renderableDescriptor.supportNormal){
      pCode->Append("|TSV");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.specular.view", ETrue);
      }
      // 支持高光颜色技术
      if(_dynamicDescriptor.supportSpecularColor){
         pCode->Append("|TSVC");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.specular.view.color", ETrue);
         }
      }
      // 支持高光级别技术
      if(renderableDescriptor.supportSpecularLevel){
         pCode->Append("|TSVL");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.specular.view.level", ETrue);
         }
      }else{
         pCode->Append("|NSVL");
         if(pTemplateContext){
            pTemplateContext->DefineBool("support.specular.view.normal", ETrue);
         }
      }
   }
   //............................................................
   // 支持环境技术
   _dynamicDescriptor.supportEnvironment = (_descriptor.supportEnvironment && renderableDescriptor.supportEnvironment);
   if(_dynamicDescriptor.supportEnvironment){
      pCode->Append("|TE");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.environment", ETrue);
      }
   }
   // 支持收光技术
   _dynamicDescriptor.supportLight = (_descriptor.supportLight && renderableDescriptor.supportLight);
   if(_dynamicDescriptor.supportLight){
      pCode->Append("|TL");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.light", ETrue);
      }
   }
   // 支持反射技术
   _dynamicDescriptor.supportReflect = (_descriptor.supportReflect && renderableDescriptor.supportReflect);
   if(_dynamicDescriptor.supportReflect){
      pCode->Append("|TRL");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.reflect", ETrue);
      }
   }
   // 支持折射技术
   _dynamicDescriptor.supportRefract = (_descriptor.supportRefract && renderableDescriptor.supportRefract);
   if(_dynamicDescriptor.supportRefract){
      pCode->Append("|TRF");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.refract", ETrue);
      }
   }
   // 支持发光技术
   _dynamicDescriptor.supportEmissive = (_descriptor.supportEmissive && renderableDescriptor.supportEmissive);
   if(_dynamicDescriptor.supportEmissive){
      pCode->Append("|TE");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.emissive", ETrue);
      }
   }
   // 支持高度技术
   _dynamicDescriptor.supportHeight = (_descriptor.supportHeight && renderableDescriptor.supportHeight);
   if(_dynamicDescriptor.supportHeight){
      pCode->Append("|TH");
      if(pTemplateContext){
         pTemplateContext->DefineBool("support.height", ETrue);
      }
   }
   //............................................................
   // 计算最大实例个数
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   TInt instanceCount = RRenderUtil::CalculateInstanceCount(renderableDescriptor.vertexCount, renderableDescriptor.boneCount);
   if(_dynamicDescriptor.supportInstance && pTemplateContext){
      pTemplateContext->DefineInt("instance.count", instanceCount);
   }
   // 计算骨头实例个数
   if(_dynamicDescriptor.supportSkeleton && pTemplateContext){
      pTemplateContext->DefineBool("support.bone.weight.1", ETrue);
      pTemplateContext->DefineBool("support.bone.weight.2", ETrue);
      pTemplateContext->DefineBool("support.bone.weight.3", ETrue);
      pTemplateContext->DefineBool("support.bone.weight.4", ETrue);
   }
   return ESuccess;
}

//============================================================
// <T>设置材质选项。</T>
//
// @param pMaterial 材质
// @return 处理结果
//============================================================
TResult FAutomaticEffect::SetMaterialOption(FMaterial* pMaterial){
   // 设置透明
   TBool optionAlpha = pMaterial->OptionAlpha();
   if(optionAlpha){
      _renderDevice->SetBlendFactors(_descriptor.optionBlendMode, _descriptor.blendSourceMode, _descriptor.blendTargetMode);
   }else{
      _renderDevice->SetBlendFactors(EFalse);
   }
   // 设置双面
   TBool optionDouble = pMaterial->OptionDouble();
   if(optionDouble){
      _renderDevice->SetCullingMode(EFalse);
   }else{
      _renderDevice->SetCullingMode(_descriptor.optionCullMode, _descriptor.cullModeCd);
   }
   return ESuccess;
}

//============================================================
// <T>单个方法绘制渲染集合处理。</T>
//============================================================
TResult FAutomaticEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   return ESuccess;
}

//============================================================
// <T>实例方法绘制渲染集合处理。</T>
//============================================================
TResult FAutomaticEffect::DrawInstanceRenderable(FRenderRegion* pRegion, FInstanceRenderable* pInstanceRenderable, TInt offset, TInt count){
   return ESuccess;
}

//============================================================
// <T>绘制一组渲染集合处理。</T>
//
// @param pRegion 区域
// @param offset 位置
// @param count 个数
//============================================================
TResult FAutomaticEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   // 设置设备状态
   _renderDevice->SetFillMode(_descriptor.fillModeCd);
   _renderDevice->SetDepthMode(_descriptor.optionDepthTest, _descriptor.depthModeCd);
   //............................................................
   FRenderableCollection* pRenderables = pRegion->VisibleRenderables();
   TBool instanceDraw = EFalse;
   if(_dynamicDescriptor.supportInstance && (count > 1)){
      for(TInt n = 0; n < count; ){
         // 根据网格进行分组
         TInt groupBegin = n;
         TInt groupEnd = count;
         FRenderable* pGroupRenderable = pRenderables->Get(offset + n);
         FRenderIndexBuffer* pGroupIndexBuffer = pGroupRenderable->IndexBuffer();
         for(TInt i = n; i < count; i++){
            FRenderable* pRenderable = pRenderables->Get(offset + i);
            FRenderIndexBuffer* pIndexBuffer = pRenderable->IndexBuffer();
            if(pGroupIndexBuffer != pIndexBuffer){
               groupEnd = i;
               break;
            }
            n++;
         }
         // 设置选项
         SetMaterialOption(pGroupRenderable->Material());
         // 绘制当前渲染组
         TInt instanceCount = groupEnd - groupBegin;
         if(instanceCount > 1){
            FInstanceRenderable* pInstanceRenderable = RInstance3dManager::Instance().ModelConsole()->LoadInstance(pGroupRenderable);
            DrawInstanceRenderable(pRegion, pInstanceRenderable, groupBegin, groupEnd - groupBegin);
         }else{
            DrawRenderable(pRegion, pGroupRenderable);
         }
      }
      instanceDraw = ETrue;
   }
   //............................................................
   // 单个绘制渲染集合
   if(!instanceDraw){
      for(TInt n = 0; n < count; n++){
         FRenderable* pRenderable = pRenderables->Get(offset + n);
         SetMaterialOption(pRenderable->Material());
         DrawRenderable(pRegion, pRenderable);
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
