#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染描述信息。</T>
//============================================================
SEffectDescriptor::SEffectDescriptor(){
   MO_CLEAR(namePtr);
   // 设置状态
   fillModeCd = ERenderFillMode_Fill;
   optionCullMode = ETrue;
   cullModeCd = ERenderCullMode_Front;
   optionDepthTest = ETrue;
   depthModeCd = ERenderDepthMode_Less;
   optionDepthWrite = ETrue;
   optionBlendMode = EFalse;
   blendSourceMode = ERenderBlendMode_SourceAlpha;
   blendTargetMode = ERenderBlendMode_OneMinusSourceAlpha;
   optionAlphaTest = EFalse;
   // 设置模式
   supportInstance = EFalse;
   supportVertexColor = EFalse;
   supportVertexCoord = EFalse;
   supportVertexNormal = EFalse;
   supportVertexNormalFull = EFalse;
   supportSkeleton = EFalse;
   // 设置模式
   supportAlpha = EFalse;
   supportBump = EFalse;
   supportAmbient = EFalse;
   supportDiffuse = EFalse;
   supportDiffuseView = EFalse;
   supportSpecularColor = EFalse;
   supportSpecularLevel = EFalse;
   supportSpecularView = EFalse;
   supportEnvironment = EFalse;
   supportLight = EFalse;
   supportReflect = EFalse;
   supportRefract = EFalse;
   supportEmissive = EFalse;
   supportHeight = EFalse;
}

//============================================================
// <T>加载配置信息。</T>
//============================================================
TResult SEffectDescriptor::LoadConfig(FXmlNode* pConfig){
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      // 读取状态
      if(pNode->IsName("State")){
         TFsName name = pNode->Get("name");
         if(name.Equals("fill_mode")){
            // 获得填充模式
            TCharC* pValue = pNode->Get("value");
            fillModeCd = RRenderFillMode::Parse(pValue);
         }else if(name.Equals("cull_mode")){
            // 获得剔除模式
            TCharC* pValue = pNode->Get("value");
            cullModeCd = RRenderCullMode::Parse(pValue);
            optionCullMode = (cullModeCd != ERenderCullMode_None);
         }else if(name.Equals("depth_mode")){
            // 获得深度测试
            TCharC* pValue = pNode->Get("value");
            depthModeCd = RRenderDepthMode::Parse(pValue);
            optionDepthTest = (depthModeCd != ERenderDepthMode_None);
         }else if(name.Equals("depth_write")){
            // 获得深度写入
            optionDepthWrite = pNode->GetAsBool("value", optionDepthWrite);
         }else if(name.Equals("blend_mode")){
            // 获得融合模式
            optionBlendMode = pNode->GetAsBool("value", optionBlendMode);
            if(optionBlendMode){
               TCharC* pSource = pNode->Get("source");
               blendSourceMode = RRenderBlendMode::Parse(pSource);
               TCharC* pTarget = pNode->Get("target");
               blendTargetMode = RRenderBlendMode::Parse(pTarget);
            }
         }else if(name.Equals("alpha_test")){
            // 获得透明测试
            optionAlphaTest = pNode->GetAsBool("value", optionAlphaTest);
         }
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
