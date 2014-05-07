#include "MoErCore.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>解析字符串为枚举内容。</T>
//
// @param pValue 字符串
// @param fillModeCd 默认内容
// @return 枚举内容
//============================================================
TResult REffectParameter::Parse(TCharC* pValue, EEffectParameter& parameterCd, ERenderShader& shaderCd, ERenderShaderParameterFormat& formatCd){
   MO_CHECK(pValue, return ENull);
   TFsCode code = pValue;
   code.ToLower();
   if(code.Equals("unknown")){
      parameterCd = EEffectParameter_Unknown;
      shaderCd = ERenderShader_Unknown;
      formatCd = ERenderShaderParameterFormat_Unknown;
   }else if(code.Equals("vertexinstance")){
      parameterCd = EEffectParameter_VertexInstance;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float1;
   }else if(code.Equals("vertexmodelmatrix3x3")){
      parameterCd = EEffectParameter_VertexModelMatrix3x3;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float3x3;
   }else if(code.Equals("vertexmodelmatrix4x4")){
      parameterCd = EEffectParameter_VertexModelMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("vertexviewmatrix4x4")){
      parameterCd = EEffectParameter_VertexViewMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexprojectionmatrix4x4")){
      parameterCd = EEffectParameter_VertexProjectionMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexviewprojectionmatrix4x4")){
      parameterCd = EEffectParameter_VertexViewProjectionMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexmodelviewprojectionmatrix3x3")){
      parameterCd = EEffectParameter_VertexModelViewProjectionMatrix3x3;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float3x3;
   }else if(code.Equals("vertexmodelviewprojectionmatrix4x4")){
      parameterCd = EEffectParameter_VertexModelViewProjectionMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("vertexbonematrix4x4")){
      parameterCd = EEffectParameter_VertexBoneMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexcameraposition")){
      parameterCd = EEffectParameter_VertexCameraPosition;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexcameradirection")){
      parameterCd = EEffectParameter_VertexCameraDirection;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexlightmodelmatrix4x4")){
      parameterCd = EEffectParameter_VertexLightModelMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("vertexlightmodelviewmatrix4x4")){
      parameterCd = EEffectParameter_VertexLightModelViewMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("vertexlightmodelviewprojectrionmatrix4x4")){
      parameterCd = EEffectParameter_VertexLightModelViewProjectionMatrix4x4;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("vertexlightposition")){
      parameterCd = EEffectParameter_VertexLightPosition;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("vertexlightdirection")){
      parameterCd = EEffectParameter_VertexLightDirection;
      shaderCd = ERenderShader_Vertex;
      formatCd = ERenderShaderParameterFormat_Float4;
   }
   //............................................................
   else if(code.Equals("fragmentcamera")){
      parameterCd = EEffectParameter_FragmentCamera;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentcameraposition")){
      parameterCd = EEffectParameter_FragmentCameraPosition;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentcameradirection")){
      parameterCd = EEffectParameter_FragmentCameraDirection;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentlightmodelmatrix4x4")){
      parameterCd = EEffectParameter_FragmentLightModelMatrix4x4;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("fragmentlightmodelviewprojectrionmatrix4x4")){
      parameterCd = EEffectParameter_FragmentLightModelViewProjectrionMatrix4x4;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4x4;
   }else if(code.Equals("fragmentlightposition")){
      parameterCd = EEffectParameter_FragmentLightPosition;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentlightdirection")){
      parameterCd = EEffectParameter_FragmentLightDirection;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentlightdepth")){
      parameterCd = EEffectParameter_FragmentLightDepth;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentshadowmaterial")){
      parameterCd = EEffectParameter_FragmentShadowMaterial;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentshadowmaterialinv")){
      parameterCd = EEffectParameter_FragmentShadowMaterialInv;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentcolor")){
      parameterCd = EEffectParameter_FragmentColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentalpha")){
      parameterCd = EEffectParameter_FragmentAlpha;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentambientcolor")){
      parameterCd = EEffectParameter_FragmentAmbientColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentdiffusecolor")){
      parameterCd = EEffectParameter_FragmentDiffuseColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentdiffuseviewcolor")){
      parameterCd = EEffectParameter_FragmentDiffuseViewColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentspecularcolor")){
      parameterCd = EEffectParameter_FragmentSpecularColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentspecular")){
      parameterCd = EEffectParameter_FragmentSpecular;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentspecularviewcolor")){
      parameterCd = EEffectParameter_FragmentSpecularViewColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentspecularview")){
      parameterCd = EEffectParameter_FragmentSpecularView;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else if(code.Equals("fragmentreflectcolor")){
      parameterCd = EEffectParameter_FragmentReflectColor;
      shaderCd = ERenderShader_Fragment;
      formatCd = ERenderShaderParameterFormat_Float4;
   }else{
      MO_STATIC_FATAL("Parse effect parameter failure. (value=%s)", pValue);
      return EFailure;
   }
   return ESuccess;
}

//============================================================
// <T>格式化枚举内容为字符串。</T>
//
// @param fillModeCd 枚举内容
// @return 字符串
//============================================================
TCharC* REffectParameter::Format(EEffectParameter parameterCd){
   switch(parameterCd){
      case EEffectParameter_VertexInstance:
         return "VertexInstance";
      case EEffectParameter_VertexModelMatrix3x3:
         return "VertexModelMatrix3x3";
      case EEffectParameter_VertexModelMatrix4x4:
         return "VertexModelMatrix4x4";
      case EEffectParameter_VertexViewMatrix4x4:
         return "VertexViewMatrix4x4";
      case EEffectParameter_VertexProjectionMatrix4x4:
         return "VertexProjectionMatrix4x4";
      case EEffectParameter_VertexViewProjectionMatrix4x4:
         return "VertexViewProjectionMatrix4x4";
      case EEffectParameter_VertexModelViewProjectionMatrix3x3:
         return "VertexModelViewProjectionMatrix3x3";
      case EEffectParameter_VertexModelViewProjectionMatrix4x4:
         return "VertexModelViewProjectionMatrix4x4";
      case EEffectParameter_VertexBoneMatrix4x4:
         return "VertexBoneMatrix4x4";
      case EEffectParameter_VertexCameraPosition:
         return "VertexCameraPosition";
      case EEffectParameter_VertexCameraDirection:
         return "VertexCameraDirection";
      case EEffectParameter_VertexLightPosition:
         return "VertexLightPosition";
      case EEffectParameter_VertexLightDirection:
         return "VertexLightDirection";
      //............................................................
      case EEffectParameter_FragmentCameraPosition:
         return "FragmentCameraPosition";
      case EEffectParameter_FragmentCameraDirection:
         return "FragmentCameraDirection";
      case EEffectParameter_FragmentLightPosition:
         return "FragmentLightPosition";
      case EEffectParameter_FragmentLightDirection:
         return "FragmentLightDirection";
      case EEffectParameter_FragmentLightDepth:
         return "FragmentLightDepth";
      case EEffectParameter_FragmentShadowMaterial:
         return "FragmentShadowMaterial";
      case EEffectParameter_FragmentShadowMaterialInv:
         return "FragmentShadowMaterialInv";
      case EEffectParameter_FragmentColor:
         return "FragmentColor";
      case EEffectParameter_FragmentAlpha:
         return "FragmentAlpha";
      case EEffectParameter_FragmentAmbientColor:
         return "FragmentAmbientColor";
      case EEffectParameter_FragmentDiffuseColor:
         return "FragmentDiffuseColor";
      case EEffectParameter_FragmentDiffuseViewColor:
         return "FragmentDiffuseViewColor";
      case EEffectParameter_FragmentSpecularColor:
         return "FragmentSpecularColor";
      case EEffectParameter_FragmentSpecular:
         return "FragmentSpecular";
      case EEffectParameter_FragmentSpecularViewColor:
         return "FragmentSpecularViewColor";
      case EEffectParameter_FragmentSpecularView:
         return "FragmentSpecularView";
      case EEffectParameter_FragmentReflectColor:
         return "ReflectColor";
   }
   return "Unknown";
}

MO_NAMESPACE_END
