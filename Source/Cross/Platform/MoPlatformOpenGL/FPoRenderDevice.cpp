#include "MoPoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPoRenderDevice, FRenderDevice);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FPoRenderDevice::FPoRenderDevice(){
   _pCapability = MO_CREATE(FRenderCapability);
   // 初始化纹理数据
   _optionTexture = EFalse;
   _renderTextureActiveSlot = -1;
   _textureLimit = 0;
   // 初始化关联集合
   _pLinkFlatTextures = MO_CREATE(FRenderFlatTextureList);
   _pLinkCubeTextures = MO_CREATE(FRenderCubeTextureList);
   // 注册类集合
   _pClassFactory->Register(MO_RENDEROBJECT_LAYOUT,            FPoRenderLayout::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_PROGRAM_BUFFER,    FRenderProgramBuffer::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_PROGRAM_PARAMETER, FPoRenderProgramParameter::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_PROGRAM_ATTRIBUTE, FRenderProgramAttribute::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_PROGRAM_SAMPLER,   FRenderProgramSampler::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_BUFFER_VERTEX,     FPoRenderVertexBuffer::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_BUFFER_INDEX,      FPoRenderIndexBuffer::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_PROGRAM,           FPoRenderProgram::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_TARGET,            FPoRenderTarget::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_TEXTURE_2D,        FPoRenderFlatTexture::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_TEXTURE_CUBE,      FPoRenderCubeTexture::Class());
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FPoRenderDevice::~FPoRenderDevice(){
   MO_DELETE(_pCapability);
   // 删除关联集合
   MO_DELETE(_pLinkFlatTextures);
   MO_DELETE(_pLinkCubeTextures);
}

//============================================================
// <T>更新环境。</T>
//============================================================
TBool FPoRenderDevice::UpdateContext(){
   TBool result = EFalse;
   // 设置激活的程序
   if(_pActiveProgram != _pProgram){
      FPoRenderProgram* pProgrom = _pProgram->Convert<FPoRenderProgram>();
      GLuint programId = pProgrom->NativeCode();
      glUseProgram(programId);
      _pProgram = _pActiveProgram;
      result = ETrue;
   }
   return result;
}

//============================================================
// <T>根据代码获得设置内容。</T>
//
// @param 代码
// @return 内容
//============================================================
TInt GlRenderGetInteger(TInt code){
   GLint value = 0;
   glGetIntegerv(code, &value);
   return value;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderDevice::Setup(){
   // 父配置处理
   TResult result = FRenderDevice::Setup();
   //............................................................
#ifdef _MO_WINDOWS
   // 初始化库
   GLenum initResult = glewInit();
   if(GLEW_OK != initResult){
      const GLubyte* pReason = glewGetErrorString(initResult);
      MO_FATAL("Initialize opengl glew library failure. (reason=%s)", pReason);
   }
   // 检查版本
   if(glewIsSupported("GL_VERSION_2_0")){
      MO_INFO("Initialize OpenGL library ready for OpenGL 2.0.");
   }else{
      MO_FATAL("Initialize OpenGL library not supported.");
   }
#endif // _MO_WINDOWS
   //............................................................
   // 获得描述
   const GLubyte* byteGlVersion = glGetString(GL_VERSION);  
   const GLubyte* byteGlVendor = glGetString(GL_VENDOR);  
   const GLubyte* byteGlRenderer = glGetString(GL_RENDERER);  
   const GLubyte* byteSLVersion = glGetString(GL_SHADING_LANGUAGE_VERSION);  
   const GLubyte* byteExtensions = glGetString(GL_EXTENSIONS);  
   MO_INFO("OpenGL setup. (Version    : %s)", byteGlVersion);
   MO_INFO("OpenGL setup. (Vendor     : %s)", byteGlVendor);
   MO_INFO("OpenGL setup. (Render     : %s)", byteGlRenderer);
   MO_INFO("OpenGL setup. (GLSL       : %s)", byteSLVersion);
   MO_INFO("OpenGL setup. (Extensions : %s)", byteExtensions);
   // 获得限制
   TInt vertexConstLimit = GlRenderGetInteger(GL_MAX_VERTEX_UNIFORM_VECTORS);
   TInt fragmentConstLimit = GlRenderGetInteger(GL_MAX_FRAGMENT_UNIFORM_VECTORS);
#ifndef _MO_ANDROID
   _pCapability->SetRenderTargetLimit(GlRenderGetInteger(GL_MAX_DRAW_BUFFERS));
   //_pCapability->SetVertexConstLimit(GlRenderGetInteger(GL_MAX_VERTEX_UNIFORM_COMPONENTS) / 4);
   //_pCapability->SetFragmentConstLimit(GlRenderGetInteger(GL_MAX_FRAGMENT_UNIFORM_COMPONENTS) / 4);
#else
   //vertexConstLimit = MO_LIB_MIN(vertexConstLimit, 256);
   //fragmentConstLimit = MO_LIB_MIN(fragmentConstLimit, 256);
#endif // _MO_ANDROID
   _pCapability->SetVertexConstLimit(vertexConstLimit);
   _pCapability->SetVertexAttributeLimit(GlRenderGetInteger(GL_MAX_VERTEX_ATTRIBS));
   _pCapability->SetFragmentConstLimit(fragmentConstLimit);
   _pCapability->SetVaryingLimit(GlRenderGetInteger(GL_MAX_VARYING_VECTORS));
   _pCapability->SetSamplerLimit(GlRenderGetInteger(GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS));
   _pCapability->SetSamplerSizeLimit(GlRenderGetInteger(GL_MAX_TEXTURE_SIZE));
   _pCapability->Track();
   //MO_INFO("GL_MAX_VARYING_FLOATS=%d", GlRenderGetInteger(GL_MAX_VARYING_FLOATS));
   MO_INFO("GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS=%d", GlRenderGetInteger(GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS));
   MO_INFO("GL_MAX_TEXTURE_IMAGE_UNITS=%d", GlRenderGetInteger(GL_MAX_TEXTURE_IMAGE_UNITS));
   //MO_INFO("GL_MAX_DRAW_BUFFERS=%d", GlRenderGetInteger(GL_MAX_DRAW_BUFFERS));
   //MO_INFO("GL_MAX_TEXTURE_UNITS=%d", GlRenderGetInteger(GL_MAX_TEXTURE_UNITS));
   //............................................................
   // 设置数据
   TInt vertexConstTotal = sizeof(SFloat4) * _pCapability->VertexConstLimit();
   _pVertexConsts->ForceLength(vertexConstTotal);
   RMemory::Clear(_pVertexConsts->Memory(), _pVertexConsts->Length());
   TInt fragmentConstTotal = sizeof(SFloat4) * _pCapability->FragmentConstLimit();
   _pFragmentConsts->ForceLength(fragmentConstTotal);
   RMemory::Clear(_pFragmentConsts->Memory(), _pFragmentConsts->Length());
   //............................................................
   // 设置脚本处理器
   _shaderTransformer = FPoRenderShaderTransformer::InstanceCreate();
   _shaderOptimizer = FPoRenderShaderOptimizer::InstanceCreate();
   //............................................................
   // GL_CCW表示逆时针为背面
   // glFrontFace(GL_CCW);
   return ESuccess;
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderDevice::Suspend(){
   // 完成处理
   glFinish();
   // 清空程序
   glUseProgram(0);
   // 暂停处理
   FRenderDevice::Suspend();
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderDevice::Resume(){
   FRenderDevice::Resume();
   return ESuccess;
}

//============================================================
// <T>检查执行错误。</T>
//
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FPoRenderDevice::CheckError(TCharC* pCode, TCharC* pMessage, ...){
   // 获得错误原因
   TBool result = EFalse;
   GLenum errorCode = 0;
   TCharC* pErrorReason = NULL;
   while(ETrue){
      // 获得错误
      GLenum code = glGetError();
      if(code == GL_NO_ERROR){
         break;
      }
      // 获得原因
      switch(code){
         case GL_INVALID_OPERATION:
            pErrorReason = "Invalid operation.";
            break;
         case GL_INVALID_ENUM:
            pErrorReason = "Invalid enum.";
            break;
         case GL_INVALID_VALUE:
            pErrorReason = "Invalid value.";
            break;
         case GL_INVALID_FRAMEBUFFER_OPERATION:
            pErrorReason = "Invalid paramebuffer opeartion."; 
            break;
         case GL_OUT_OF_MEMORY:
            pErrorReason = "Out of memory.";
            break;
         default:
            pErrorReason = "Unknown"; 
            break;
      }
      result = ETrue;
      errorCode = code;
#ifdef _MO_WINDOWS
      break;
#endif // _MO_WINDOWS
   }
   //............................................................
   // 输出错误信息
   if(result){
      // 格式化可变参数字符串信息
      TFsText message;
      va_list params;
      va_start(params, pMessage);
      message.AppendFormatParameters(pMessage, params);
      va_end(params);
      // 输出错误信息
      MO_ERROR("%s (code=%s, error=0x%04X(%d), reason=%s)", (TCharC*)message, pCode, errorCode, errorCode, pErrorReason);
   }
   return result;
}

//============================================================
// <T>设置背景缓冲。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed){
   return ESuccess;
}

//============================================================
// <T>设置填充模式。</T>
//
// @param fillModeCd 填充模式
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetFillMode(ERenderFillMode fillModeCd){
   // 检查状态
   if(_fillModeCd == fillModeCd){
      return EContinue;
   }
   // 设置内容
   GLenum fillEnum = ROpenGL::ConvertFillMode(fillModeCd);
#ifndef _MO_ANDROID
   glPolygonMode(GL_FRONT, fillEnum);
   glPolygonMode(GL_BACK, fillEnum);
#endif // _MO_ANDROID
   _fillModeCd = fillModeCd;
   _statistics->UpdateOptionFillCount();
   return ESuccess;
}

//============================================================
// <T>设置深度模式。</T>
//
// @param depth 深度开关
// @param depthCd 深度模式
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetDepthMode(TBool depth, ERenderDepthMode depthCd){
   // 检查状态
   if((_optionDepth == depth) && (_depthModeCd == depthCd)){
      return EContinue;
   }
   // 设置开关
   if(_optionDepth != depth){
      if(depth){
         glEnable(GL_DEPTH_TEST);
      }else{
         glDisable(GL_DEPTH_TEST);
      }
      _optionDepth = depth;
   }
   // 设置内容
   if(depth && _depthModeCd != depthCd){
      GLenum depthEnum = ROpenGL::ConvertDepthMode(depthCd);
      glDepthFunc(depthEnum);
      _depthModeCd = depthCd;
   }
   _statistics->UpdateOptionDeepCount();
   return ESuccess;
}

//============================================================
// <T>设置剪裁模式。</T>
//
// @param cull 剪裁开关
// @param cullCd 剪裁模式
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetCullingMode(TBool cull, ERenderCullMode cullCd){
   // 检查状态
   if((_optionCull == cull) && (_optionCull == cullCd)){
      return EContinue;
   }
   // 设置开关
   if(_optionCull != cull){
      if(cull){
         glEnable(GL_CULL_FACE);
      }else{
         glDisable(GL_CULL_FACE);
      }
      _optionCull = cull;
   }
   // 设置内容
   if(cull && _cullModeCd != cullCd){
      GLenum cullEnum = ROpenGL::ConvertCullMode(cullCd);
      glCullFace(cullEnum);
      _cullModeCd = cullCd;
   }
   _statistics->UpdateOptionCullCount();
   return ESuccess;
}

//============================================================
// <T>设置合成方式。</T>
//
// @param blend 混合方式
// @param sourceCd 来源类型
// @param targetCd 目标类型
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetBlendFactors(TBool blend, ERenderBlendMode sourceCd, ERenderBlendMode targetCd){
   // 检查状态
   if((_statusBlend == blend) && (_blendSourceCd == sourceCd) && (_blendTargetCd == targetCd)){
      return EConsole;
   }
   // 设置开关
   if(_statusBlend != blend){
      if(blend){
         glEnable(GL_BLEND);
      }else{
         glDisable(GL_BLEND);
      }
      _statusBlend = blend;
   }
   // 设置效果
   if(blend && ((_blendSourceCd != sourceCd) || (_blendTargetCd != targetCd))){
      GLenum source = ROpenGL::ConvertBlendFactors(sourceCd);
      GLenum target = ROpenGL::ConvertBlendFactors(targetCd);
      glBlendFunc(source, target);
      _blendSourceCd = sourceCd;
      _blendTargetCd = targetCd;
   }
   _statistics->UpdateOptionBlendCount();
   return ETrue;
}

//============================================================
// <T>设置有效区域。</T>
//
// @param left 左位置
// @param top 上位置
// @param width 宽度
// @param height 高度
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetScissorRectangle(TInt left, TInt top, TInt width, TInt height){
   glScissor(left, top, width, height);
   return ETrue;
}

//============================================================
// <T>设置渲染目标。</T>
//
// @param pRenderTarget 渲染目标
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetRenderTarget(FRenderTarget* pRenderTarget){
   TResult result = ESuccess;
   if(pRenderTarget == NULL){
      // 解除渲染目标
      glBindFramebuffer(GL_FRAMEBUFFER, 0);
      result = CheckError("glBindFramebuffer", "Bind frame buffer. (buffer_id=%d)", 0);
      if(result != ESuccess){
         return result;
      }
      // 修改视角
      FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
      SIntSize2& screenSize = pScreenDevice->Size();
      glViewport(0, 0, screenSize.width, screenSize.height);
      return ESuccess;
   }else{
      // 绑定渲染目标
      FPoRenderTarget* pGlRenderTarget = pRenderTarget->Convert<FPoRenderTarget>();
      GLuint frameBufferId = pGlRenderTarget->NativeCode();
      glBindFramebuffer(GL_FRAMEBUFFER, frameBufferId);
      result = CheckError("glBindFramebuffer", "Bind frame buffer. (buffer_id=%d)", frameBufferId);
      if(result != ESuccess){
         return result;
      }
      // 修改视角
      SIntSize2& size = pRenderTarget->Size();
      glViewport(0, 0, size.width, size.height);
      // 清空颜色
      SFloatColor4& backgroundColor = pRenderTarget->BackgroundColor();
      glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, backgroundColor.alpha);
      //glClearDepth(1.0f);
      glClearStencil(0);
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
   }
   _pActiveRenderTarget = pRenderTarget;
   return ESuccess;
}

//============================================================
// <T>设置程序。</T>
//
// @param pProgram 程序
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetProgram(FRenderProgram* pProgram){
   // 检查程序
   //if(_pProgram == pProgram){
   //   return EContinue;
   //}
   // 获得程序编号
   GLuint programId = GL_ZERO;
   if(pProgram != NULL){
      FPoRenderProgram* pRenderProgram = pProgram->Convert<FPoRenderProgram>();
      programId = pRenderProgram->NativeCode();
   }
   // 设置程序
   glUseProgram(programId);
   _pProgram = pProgram;
   // 检查错误
   TResult result = CheckError("glUseProgram", "Set program failure. (program=0x%08X, program_id=%d)", pProgram, programId);
   // 检查是否可以执行
   _statistics->UpdateProgramCount();
   return result;
}

//============================================================
// <T>设置布局。</T>
//
// @parma pLayout 布局
// @return 处理结果
//============================================================
TResult FPoRenderDevice::SetLayout(FRenderLayout* pLayout){
   MO_CHECK(pLayout, return ENull);
   // 获得顶点流
   TResult result = ESuccess;
   FPoRenderLayout* pRenderLayout = pLayout->Convert<FPoRenderLayout>();
   // 获得信息
   TInt count = pRenderLayout->Count();
   TInt* pSlots = pRenderLayout->Slots();
   FRenderVertexBuffer** pBuffers = pRenderLayout->Buffers();
   TInt* bufferOffset = pRenderLayout->Offset();
   ERenderAttributeFormat* formatCds = pRenderLayout->Formats();
   for(TInt n = 0; n < count; n++){
      BindVertexBuffer(pSlots[n], pBuffers[n], bufferOffset[n], formatCds[n]);
   }
   return ESuccess;
}

//============================================================
// <T>绑定常量数据。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma pData 数据
// @parma length 长度
// @return 处理结果
//============================================================
TResult FPoRenderDevice::BindConst(ERenderShader shaderCd, TInt slot, ERenderParameterFormat formatCd, TAnyC* pData, TInt length){
   // 检查变更
   TBool changed = UpdateConsts(shaderCd, slot, pData, length);
   if(!changed){
      return EContinue;
   }
   // 修改数据
   TResult result = ESuccess;
   switch (formatCd){
      case ERenderParameterFormat_Float1:{
         // 检查长度
         if(length % 4 != 0){
            MO_ERROR("Length is invalid. (length=%d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 4;
         glUniform1fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform1fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderParameterFormat_Float2:{
         // 检查长度
         if(length % 8 != 0){
            MO_ERROR("Length is invalid. (length=%d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 8;
         glUniform2fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform2fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderParameterFormat_Float3:{
         // 检查长度
         if(length % 12 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 12;
         glUniform3fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform3fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderParameterFormat_Float4:{
         // 检查长度
         if(length % 16 != 0){
            MO_ERROR("Length is invalid. (length=%d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 16;
         glUniform4fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform4fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderParameterFormat_Float3x3:{
         // 检查长度
         if(length % 36 != 0){
            MO_ERROR("Length is invalid. (length=%d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 36;
         glUniformMatrix3fv(slot, count, GL_FALSE, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniformMatrix4fv", "Bind const matrix3x3 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderParameterFormat_Float4x3:{
         // 检查长度
         if(length % 48 != 0){
            MO_ERROR("Length is invalid. (length=%d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 48;
         glUniform4fv(slot, count * 3, (const GLfloat*)pData);
         //glUniformMatrix4x3fv(slot, count, GL_FALSE, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniformMatrix4x3fv", "Bind const matrix4x3 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderParameterFormat_Float4x4:{
         // 检查长度
         if(length % 64 != 0){
            MO_ERROR("Length is invalid. (length=%d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length >> 6;
         glUniformMatrix4fv(slot, count, GL_FALSE, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniformMatrix4fv", "Bind const matrix4x4 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
   }
   // MO_INFO("Bind const buffer. (slot=%d, format_cd=%d, length=%d)", slot, formatCd, length);
   _statistics->UpdateProgramCount(length);
   return ESuccess;
}

//============================================================
// <T>绑定渲染缓冲。</T>
//
// @param pBuffer 渲染缓冲
// @return 处理结果
//============================================================
TResult FPoRenderDevice::BindConstBuffer(FRenderProgramBuffer* pBuffer){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>绑定顶点缓冲。</T>
//
// @param slot 插槽
// @param pVertexBuffer 顶点缓冲
// @param offset 偏移位置
// @param formatCd 格式
// @return 处理结果
//============================================================
TResult FPoRenderDevice::BindVertexBuffer(TInt slot, FRenderVertexBuffer* pVertexBuffer, TInt offset, ERenderAttributeFormat formatCd){
   MO_ERROR_CHECK(slot >= 0, return EFailure, "Slot value is invalid. (slot=%d)", slot);
   // 获得顶点流
   TResult result = ESuccess;
   FPoRenderVertexBuffer* pBuffer = pVertexBuffer->Convert<FPoRenderVertexBuffer>();
   //............................................................
   // 设定顶点流
   GLuint bufferId = 0;
   if(pBuffer != NULL){
      bufferId = pBuffer->BufferId();
   }
   glBindBuffer(GL_ARRAY_BUFFER, bufferId);
   result = CheckError("glBindBuffer", "Bind buffer. (buffer_id=%d)", bufferId);
   if(result != ESuccess){
      return result;
   }
   //............................................................
   // 激活顶点流
   if(pBuffer != NULL){
      glEnableVertexAttribArray(slot);
      result = CheckError("glEnableVertexAttribArray", "Enable vertex attribute array. (slot=%d)", slot);
      if(result != ESuccess){
         return result;
      }
   }else{
      glDisableVertexAttribArray(slot);
      result = CheckError("glDisableVertexAttribArray", "Disable vertex attribute array. (slot=%d)", slot);
      return result;
   }
   //............................................................
   // 设置顶点流
   TInt stride = pVertexBuffer->Stride();
   switch(formatCd){
      case ERenderAttributeFormat_Float1:
         glVertexAttribPointer(slot, 1, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderAttributeFormat_Float2:
         glVertexAttribPointer(slot, 2, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderAttributeFormat_Float3:
         glVertexAttribPointer(slot, 3, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderAttributeFormat_Float4:
         glVertexAttribPointer(slot, 4, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderAttributeFormat_Byte4:
         glVertexAttribPointer(slot, 4, GL_UNSIGNED_BYTE, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderAttributeFormat_Byte4Normal:
         glVertexAttribPointer(slot, 4, GL_UNSIGNED_BYTE, GL_TRUE, stride, (const GLvoid*)offset);
         break;
      default:
         MO_FATAL("Unknown vertex format. (format_cd=%d)", formatCd);
         break;
   }
   // 检查错误
   result = CheckError("glVertexAttribPointer", "Bind vertex attribute pointer. (slot=%d, format_cd=%d)", slot, formatCd);
   _statistics->UpdateVertexBufferCount();
   // MO_INFO("Bind vertex buffer. (slot=%d, offset=%d, format_cd=%d, stride=%d, buffer_id=%d, count=%d, length=%d)", slot, offset, formatCd, stride, bufferId, pVertexBuffer->Count(), pVertexBuffer->DataLength());
   return result;
}

//============================================================
// <T>绑定纹理。</T>
//
// @param slot 插槽
// @param index 索引
// @param pTexture 纹理
// @return 处理结果
//============================================================
TResult FPoRenderDevice::BindTexture(TInt slot, TInt index, FRenderTexture* pTexture){
   TResult result = ESuccess;
   //............................................................
   // 空纹理处理
   if(pTexture == NULL){
      glBindTexture(GL_TEXTURE_2D, 0);
      result = CheckError("glBindTexture", "Bind texture clear failure. (slot=%d)", slot);
      return result;
   }
   //............................................................
   // 激活纹理
   if(_renderTextureActiveSlot != slot){
      MO_CHECK(index >= 0, return EOutRange);
      glUniform1i(slot, index);
      glActiveTexture(GL_TEXTURE0 + index);
      result = CheckError("glActiveTexture", "Active texture failure. (slot=%d, index=%d)", slot, index);
      if(result != ESuccess){
         return result;
      }
      _renderTextureActiveSlot = slot;
   }
   //............................................................
   // 绑定纹理
   ERenderTexture textureCd = pTexture->TextureCd();
   switch (textureCd){
      case ERenderTexture_Flat2d:{
         FPoRenderFlatTexture* pFlatTexture = (FPoRenderFlatTexture*)pTexture;
         GLuint textureId = pFlatTexture->TextureId();
         glBindTexture(GL_TEXTURE_2D, textureId);
         glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
         glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
         result = CheckError("glBindTexture", "Bind texture failure. (texture_id=%d)", textureId);
         if(result != ESuccess){
            return result;
         }
         break;
      }
      case ERenderTexture_Cube:{
         FPoRenderCubeTexture* pCubeTexture = (FPoRenderCubeTexture*)pTexture;
         GLuint textureId = pCubeTexture->TextureId();
         glBindTexture(GL_TEXTURE_CUBE_MAP, textureId);
         result = CheckError("glBindTexture", "Bind texture failure. (texture_id=%d)", textureId);
         if(result != ESuccess){
            return result;
         }
         break;
      }
      default:{
         MO_FATAL("Unknown texture type.");
         break;
      }
   }
   //............................................................
   // 统计数据
   _statistics->UpdateSamplerCount();
   return result;
}

//============================================================
// <T>清空内容。</T>
//
// @param red 红色
// @param green 绿色
// @param blue 蓝色
// @param alpha 透明
// @param depth 深度
// @return 处理结果
//============================================================
TResult FPoRenderDevice::Clear(TFloat red, TFloat green, TFloat blue, TFloat alpha, TFloat depth){
   glClearColor(red, green, blue, alpha);
   glClearDepthf(depth);
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
   return ETrue;
}

//============================================================
// <T>绘制三角形。</T>
//
// @param pIndexBuffer 索引缓冲
// @param offset 开始位置
// @param count 索引总数
// @return 处理结果
//============================================================
TResult FPoRenderDevice::DrawTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count){
   MO_ASSERT(pIndexBuffer);
   MO_ASSERT(offset >= 0);
   MO_ASSERT(count > 0);
   TResult result = ESuccess;
   // 获得索引流
   FPoRenderIndexBuffer* pBuffer = pIndexBuffer->Convert<FPoRenderIndexBuffer>();
   // 绘制索引流
   GLuint bufferId = pBuffer->BufferId();
   MO_ASSERT(bufferId != 0);
   glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, pBuffer->BufferId());
   result = CheckError("glBindBuffer",
         "Bind element array buffer failure. (index=0x%08X, offset=%d, count=%d, buffer_id)", pIndexBuffer, offset, count, bufferId);
   if(result != ESuccess){
       return result;
   }
   _renderDrawStatistics->Begin();
   GLenum strideCd = ROpenGL::ConvertIndexStride(pIndexBuffer->StrideCd());
   glDrawElements(GL_TRIANGLES, count, strideCd, (const GLvoid*)(sizeof(TUint16) * offset));
   _renderDrawStatistics->Finish();
   // MO_INFO("Draw elements. (buffer_id=%d, offset=%d, count=%d)", bufferId, offset, count);
   result = CheckError("glDrawElements",
         "Draw triangles failure. (index=0x%08X, offset=%d, count=%d)", pIndexBuffer, offset, count);
   if(result != ESuccess){
       return result;
   }
   glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
   result = CheckError("glBindBuffer",
         "Bind element array buffer failure. (index=0x%08X, offset=%d, count=%d)", pIndexBuffer, offset, count);
   if(result != ESuccess){
       return result;
   }
   // 检查错误
   _statistics->UpdateDraw(count);
   return result;
}

//============================================================
// <T>显示画面。</T>
//
// @return 处理结果
//============================================================
TResult FPoRenderDevice::Present(){
   //glFlush();
   return ESuccess;
}

MO_NAMESPACE_END
