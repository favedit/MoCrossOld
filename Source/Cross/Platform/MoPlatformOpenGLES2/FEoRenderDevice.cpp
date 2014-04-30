#include "MoEoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEoRenderDevice, FRenderDevice);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FEoRenderDevice::FEoRenderDevice(){
   _pCapability = MO_CREATE(FRenderCapability);
   // 初始化纹理数据
   _optionTexture = EFalse;
   _renderTextureActiveSlot = -1;
   _textureLimit = 0;
   // 初始化关联集合
   _pLinkFlatTextures = MO_CREATE(FRenderFlatTextureList);
   _pLinkCubeTextures = MO_CREATE(FRenderCubeTextureList);
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FEoRenderDevice::~FEoRenderDevice(){
   MO_DELETE(_pCapability);
   // 删除关联集合
   MO_DELETE(_pLinkFlatTextures);
   MO_DELETE(_pLinkCubeTextures);
}

//============================================================
// <T>更新环境。</T>
//============================================================
TBool FEoRenderDevice::UpdateContext(){
   TBool result = EFalse;
   // 设置激活的程序
   if(_pActiveProgram != _pProgram){
      FEoRenderProgram* pProgrom = (FEoRenderProgram*)_pProgram;
      glUseProgram(pProgrom->ProgramId());
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
TResult FEoRenderDevice::Setup(){
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
   // GL_CCW表示逆时针为背面
   // glFrontFace(GL_CCW);
   return ESuccess;
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderDevice::Suspend(){
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
TResult FEoRenderDevice::Resume(){
   FRenderDevice::Resume();
   return ESuccess;
}

//============================================================
// <T>检查执行错误。</T>
//
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FEoRenderDevice::CheckError(TCharC* pCode, TCharC* pMessage, ...){
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
// <T>创建顶点缓冲。</T>
//
// @param pClass 类对象
// @return 顶点缓冲
//============================================================
FRenderVertexBuffer* FEoRenderDevice::CreateVertexBuffer(FClass* pClass){
   FRenderVertexBuffer* pVertexBuffer = FEoRenderVertexBuffer::InstanceCreate();
   pVertexBuffer->SetDevice(this);
   _storageVertexBuffers.Push(pVertexBuffer);
   return pVertexBuffer;
}

//============================================================
// <T>创建索引缓冲。</T>
//
// @param pClass 类对象
// @return 索引缓冲
//============================================================
FRenderIndexBuffer* FEoRenderDevice::CreateIndexBuffer(FClass* pClass){
   FRenderIndexBuffer* pIndexBuffer = FEoRenderIndexBuffer::InstanceCreate();
   pIndexBuffer->SetDevice(this);
   _storageIndexBuffers.Push(pIndexBuffer);
   return pIndexBuffer;
}

//============================================================
// <T>创建程序。</T>
//
// @param pClass 类对象
// @return 程序
//============================================================
FRenderProgram* FEoRenderDevice::CreateProgrom(FClass* pClass){
   FEoRenderProgram* pProgram = FEoRenderProgram::InstanceCreate();
   pProgram->SetDevice(this);
   _storagePrograms.Push(pProgram);
   return pProgram;
}

//============================================================
// <T>创建渲染目标。</T>
//
// @param pClass 类对象
// @return 渲染目标
//============================================================
FRenderTarget* FEoRenderDevice::CreateRenderTarget(FClass* pClass){
   FEoRenderTarget* pRenderTarget = FEoRenderTarget::InstanceCreate();
   pRenderTarget->SetDevice(this);
   _storageTargets.Push(pRenderTarget);
   return pRenderTarget;
}

//============================================================
// <T>创建平面纹理。</T>
//
// @param pClass 类对象
// @return 纹理
//============================================================
FRenderFlatTexture* FEoRenderDevice::CreateFlatTexture(FClass* pClass){
   FEoRenderFlatTexture* pTexture = FEoRenderFlatTexture::InstanceCreate();
   pTexture->SetDevice(this);
   _storageTextures.Push(pTexture);
   _pLinkFlatTextures->Push(pTexture);
   return pTexture;
}

//============================================================
// <T>创建空间纹理。</T>
//
// @param pClass 类对象
// @return 纹理
//============================================================
FRenderCubeTexture* FEoRenderDevice::CreateCubeTexture(FClass* pClass){
   FEoRenderCubeTexture* pTexture = FEoRenderCubeTexture::InstanceCreate();
   pTexture->SetDevice(this);
   _storageTextures.Push(pTexture);
   _pLinkCubeTextures->Push(pTexture);
   return pTexture;
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
TResult FEoRenderDevice::Clear(TFloat red, TFloat green, TFloat blue, TFloat alpha, TFloat depth){
   glClearColor(red, green, blue, alpha);
   glClearDepthf(depth);
   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
   return ETrue;
}

//============================================================
// <T>设置背景缓冲。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderDevice::SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed){
   return ESuccess;
}

//============================================================
// <T>设置填充模式。</T>
//
// @param fillModeCd 填充模式
// @return 处理结果
//============================================================
TResult FEoRenderDevice::SetFillMode(ERenderFillMode fillModeCd){
   // 检查状态
   if(_fillModeCd == fillModeCd){
      return EContinue;
   }
   // 设置内容
#ifndef _MO_ANDROID
   switch(fillModeCd){
      case ERenderFillMode_Point:
         glPolygonMode(GL_FRONT, GL_POINT);
         glPolygonMode(GL_BACK, GL_POINT);
         break;
      case ERenderFillMode_Line:
         glPolygonMode(GL_FRONT, GL_LINE);
         glPolygonMode(GL_BACK, GL_LINE);
         break;
      case ERenderFillMode_Fill:
         glPolygonMode(GL_FRONT, GL_FILL);
         glPolygonMode(GL_BACK, GL_FILL);
         break;
   }
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
TResult FEoRenderDevice::SetDepthMode(TBool depth, ERenderDepthMode depthCd){
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
      _statistics->UpdateOptionDeepCount();
   }
   // 设置内容
   if(depth && _depthModeCd != depthCd){
      switch(depthCd){
         case ERenderDepthMode_Equal:
            glDepthFunc(GL_EQUAL);
            break;
         case ERenderDepthMode_NotEqual:
            glDepthFunc(GL_NOTEQUAL);
            break;
         case ERenderDepthMode_Less:
            glDepthFunc(GL_LESS);
            break;
         case ERenderDepthMode_LessEqual:
            glDepthFunc(GL_LEQUAL);
            break;
         case ERenderDepthMode_Greater:
            glDepthFunc(GL_GREATER);
            break;
         case ERenderDepthMode_GreaterEqual:
            glDepthFunc(GL_GEQUAL);
            break;
         case ERenderDepthMode_Always:
            glDepthFunc(GL_ALWAYS);
            break;
      }
      _depthModeCd = depthCd;
   }
   return ESuccess;
}

//============================================================
// <T>设置剪裁模式。</T>
//
// @param cull 剪裁开关
// @param cullCd 剪裁模式
// @return 处理结果
//============================================================
TResult FEoRenderDevice::SetCullingMode(TBool cull, ERenderCullMode cullCd){
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
      _statistics->UpdateOptionCullCount();
   }
   // 设置内容
   if(cull && _cullModeCd != cullCd){
      switch(cullCd){
         case ERenderCullMode_Front:
            glCullFace(GL_FRONT);
            break;
         case ERenderCullMode_Back:
            glCullFace(GL_BACK);
            break;
         case ERenderCullMode_Both:
            glCullFace(GL_FRONT_AND_BACK);
            break;
      }
      _cullModeCd = cullCd;
   }
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
TResult FEoRenderDevice::SetBlendFactors(TBool blend, ERenderBlendMode sourceCd, ERenderBlendMode targetCd){
   // 设置开关
   if(_statusBlend != blend){
      if(blend){
         glEnable(GL_BLEND);
      }else{
         glDisable(GL_BLEND);
      }
      _statusBlend = blend;
      _statistics->UpdateOptionBlendCount();
   }
   // 设置效果
   if(blend && ((_blendSourceCd != sourceCd) || (_blendTargetCd != targetCd))){
      GLenum source = ROpenGLES2::ConvertBlendFactors(sourceCd);
      GLenum target = ROpenGLES2::ConvertBlendFactors(targetCd);
      glBlendFunc(source, target);
      _blendSourceCd = sourceCd;
      _blendTargetCd = targetCd;
   }
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
TResult FEoRenderDevice::SetScissorRectangle(TInt left, TInt top, TInt width, TInt height){
   glScissor(left, top, width, height);
   return ETrue;
}

//============================================================
// <T>设置渲染目标。</T>
//
// @param pRenderTarget 渲染目标
// @return 处理结果
//============================================================
TResult FEoRenderDevice::SetRenderTarget(FRenderTarget* pRenderTarget){
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
      GLuint frameBufferId = pRenderTarget->RenderId().uint32;
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
TResult FEoRenderDevice::SetProgram(FRenderProgram* pProgram){
   // 检查程序
   //if(_pProgram == pProgram){
   //   return EContinue;
   //}
   // 获得程序编号
   GLuint programId = GL_ZERO;
   if(pProgram != NULL){
      FEoRenderProgram* pRenderProgram = (FEoRenderProgram*)pProgram;
      programId = pRenderProgram->ProgramId();
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
// <T>绑定常量数据。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma pData 数据
// @parma length 长度
// @return 处理结果
//============================================================
TResult FEoRenderDevice::BindConstData(ERenderShader shaderCd, TInt slot, ERenderShaderConstForamt formatCd, TAnyC* pData, TInt length){
   // 检查变更
   TBool changed = UpdateConsts(shaderCd, slot, pData, length);
   if(!changed){
      return EContinue;
   }
   // 修改数据
   TResult result = ESuccess;
   switch (formatCd){
      case ERenderShaderConstForamt_Float1:{
         // 检查长度
         if(length % 4 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 4;
         glUniform1fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform1fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderShaderConstForamt_Float2:{
         // 检查长度
         if(length % 8 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 8;
         glUniform2fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform2fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderShaderConstForamt_Float3:{
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
      case ERenderShaderConstForamt_Float4:{
         // 检查长度
         if(length % 16 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 16;
         glUniform4fv(slot, count, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniform4fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderShaderConstForamt_Matrix3x3:{
         // 检查长度
         if(length % 36 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
            return EFailure;
         }
         // 修改数据
         TInt count = length / 36;
         glUniformMatrix3fv(slot, count, GL_FALSE, (const GLfloat*)pData);
         // 检查错误
         result = CheckError("glUniformMatrix4fv", "Bind const matrix3x3 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
         break;
      }
      case ERenderShaderConstForamt_Matrix4x3:{
         // 检查长度
         if(length % 48 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
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
      case ERenderShaderConstForamt_Matrix4x4:{
         // 检查长度
         if(length % 64 != 0){
            MO_ERROR("Length is invalid. (length=d)", length);
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
// <T>绑定常量四维浮点数。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma x X内容
// @parma y Y内容
// @parma z Z内容
// @parma w W内容
// @return 处理结果
//============================================================
TResult FEoRenderDevice::BindConstFloat3(ERenderShader shaderCd, TInt slot, TFloat x, TFloat y, TFloat z){
   // 检查变更
   TFloat data[3] = {x, y, z};
   TInt length = sizeof(TFloat) * 3;
   TBool changed = UpdateConsts(shaderCd, slot, data, length);
   if(!changed){
      return EContinue;
   }
   // 修改数据
   glUniform3fv(slot, 1, (const GLfloat*)data);
   // 检查错误
   TResult result = CheckError("glUniform3fv", "Bind const float3 failure. (shader_cd=%d, slot=%d, value=%f,%f,%f)", shaderCd, slot, x, y, z);
   _statistics->UpdateProgramCount(sizeof(data));
   return result;
}

//============================================================
// <T>绑定常量四维浮点数。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma x X内容
// @parma y Y内容
// @parma z Z内容
// @parma w W内容
// @return 处理结果
//============================================================
TResult FEoRenderDevice::BindConstFloat4(ERenderShader shaderCd, TInt slot, TFloat x, TFloat y, TFloat z, TFloat w){
   // 检查变更
   TFloat data[4] = {x, y, z, w};
   TInt length = sizeof(TFloat) * 4;
   TBool changed = UpdateConsts(shaderCd, slot, data, length);
   if(!changed){
      return EContinue;
   }
   // 修改数据
   glUniform4fv(slot, 1, (const GLfloat*)data);
   // 检查错误
   TResult result = CheckError("glUniform4fv", "Bind const float4 failure. (shader_cd=%d, slot=%d, value=%f,%f,%f,%f)", shaderCd, slot, x, y, z, w);
   _statistics->UpdateProgramCount(sizeof(data));
   return result;
}

//============================================================
// <T>绑定常量三维矩阵。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma matrix 矩阵
// @return 处理结果
//============================================================
TResult FEoRenderDevice::BindConstMatrix3x3(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix){
   // 检查变更
   TAnyC* pMemory = matrix.MemoryC();
   TInt length = sizeof(TFloat) * 9;
   TBool changed = UpdateConsts(shaderCd, slot, pMemory, length);
   if(!changed){
      return EContinue;
   }
   // 转置矩阵
   GLfloat data[3][3];
   data[0][0] = matrix.data[0][0];
   data[0][1] = matrix.data[1][0];
   data[0][2] = matrix.data[2][0];
   data[1][0] = matrix.data[0][1];
   data[1][1] = matrix.data[1][1];
   data[1][2] = matrix.data[2][1];
   data[2][0] = matrix.data[0][2];
   data[2][1] = matrix.data[1][2];
   data[2][2] = matrix.data[2][2];
   glUniformMatrix3fv(slot, 1, GL_FALSE, (const GLfloat*)data);
   // 检查错误
   TResult result = CheckError("glUniformMatrix3fv", "Bind const matrix3x3 failure. (shader_cd=%d, slot=%d)", shaderCd, slot);
   _statistics->UpdateProgramCount(length);
   return result;
}

//============================================================
// <T>绑定常量三维矩阵。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma matrix 矩阵
// @return 处理结果
//============================================================
TResult FEoRenderDevice::BindConstMatrix4x4(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix){
   // 检查变更
   TAnyC* pMemory = matrix.MemoryC();
   TInt length = sizeof(TFloat) * 16;
   TBool changed = UpdateConsts(shaderCd, slot, pMemory, length);
   if(!changed){
      return EContinue;
   }
   // 修改数据
#ifdef _MO_ANDROID
   // 转置矩阵
   GLfloat data[4][4];
   data[0][0] = matrix.data[0][0];
   data[0][1] = matrix.data[1][0];
   data[0][2] = matrix.data[2][0];
   data[0][3] = matrix.data[3][0];
   data[1][0] = matrix.data[0][1];
   data[1][1] = matrix.data[1][1];
   data[1][2] = matrix.data[2][1];
   data[1][3] = matrix.data[3][1];
   data[2][0] = matrix.data[0][2];
   data[2][1] = matrix.data[1][2];
   data[2][2] = matrix.data[2][2];
   data[2][3] = matrix.data[3][2];
   data[3][0] = matrix.data[0][3];
   data[3][1] = matrix.data[1][3];
   data[3][2] = matrix.data[2][3];
   data[3][3] = matrix.data[3][3];
   glUniformMatrix4fv(slot, 1, GL_FALSE, (const GLfloat*)data);
#else
   glUniformMatrix4fv(slot, 1, GL_TRUE, (const GLfloat*)pMemory);
#endif // _MO_ANDROID
   // 检查错误
   TResult result = CheckError("glUniformMatrix4fv", "Bind const matrix4x4 failure. (shader_cd=%d, slot=%d)", shaderCd, slot);
   _statistics->UpdateProgramCount(length);
   return result;
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
TResult FEoRenderDevice::BindVertexBuffer(TInt slot, FRenderVertexBuffer* pVertexBuffer, TInt offset, ERenderVertexFormat formatCd){
   MO_ERROR_CHECK(slot >= 0, return EFailure, "Slot value is invalid. (slot=%d)", slot);
   // 获得顶点流
   TResult result = ESuccess;
   FEoRenderVertexBuffer* pBuffer = pVertexBuffer->Convert<FEoRenderVertexBuffer>();
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
      case ERenderVertexFormat_Byte4:
         glVertexAttribPointer(slot, 4, GL_UNSIGNED_BYTE, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderVertexFormat_ByteNormal4:
         glVertexAttribPointer(slot, 4, GL_UNSIGNED_BYTE, GL_TRUE, stride, (const GLvoid*)offset);
         break;
      case ERenderVertexFormat_Float1:
         glVertexAttribPointer(slot, 1, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderVertexFormat_Float2:
         glVertexAttribPointer(slot, 2, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderVertexFormat_Float3:
         glVertexAttribPointer(slot, 3, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
         break;
      case ERenderVertexFormat_Float4:
         glVertexAttribPointer(slot, 4, GL_FLOAT, GL_FALSE, stride, (const GLvoid*)offset);
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
// @param pTexture 纹理
// @return 处理结果
//============================================================
TResult FEoRenderDevice::BindTexture(TInt slot, FRenderTexture* pTexture){
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
      TInt index = pTexture->Index();
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
         FEoRenderFlatTexture* pFlatTexture = (FEoRenderFlatTexture*)pTexture;
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
         FEoRenderCubeTexture* pCubeTexture = (FEoRenderCubeTexture*)pTexture;
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
// <T>绘制三角形。</T>
//
// @param pIndexBuffer 索引缓冲
// @param offset 开始位置
// @param count 索引总数
// @return 处理结果
//============================================================
TResult FEoRenderDevice::DrawTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count){
   MO_ASSERT(pIndexBuffer);
   MO_ASSERT(offset >= 0);
   MO_ASSERT(count > 0);
   TResult result = ESuccess;
   // 获得索引流
   FEoRenderIndexBuffer* pBuffer = pIndexBuffer->Convert<FEoRenderIndexBuffer>();
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
   GLenum strideCd = ROpenGLES2::ConvertIndexStride(pIndexBuffer->StrideCd());
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
TResult FEoRenderDevice::Present(){
   //glFlush();
   return ESuccess;
}

MO_NAMESPACE_END
