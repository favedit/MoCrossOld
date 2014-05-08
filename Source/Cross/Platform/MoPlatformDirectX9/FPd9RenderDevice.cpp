#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderDevice, FRenderDevice);

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FPd9RenderDevice::FPd9RenderDevice(){
   // 初始化能力描述
   _pCapability = MO_CREATE(FRenderCapability);
   _pCapability->SetCode("directx9");
   _pCapability->SetShaderVertexVersion("vs_3_0");
   _pCapability->SetShaderFragmentVersion("ps_3_0");
   // 初始化纹理数据
   //_optionTexture = EFalse;
   //_textureLimit = 0;
   // 初始化关联集合
   //_pLinkFlatTextures = MO_CREATE(FRenderFlatTextureList);
   //_pLinkCubeTextures = MO_CREATE(FRenderCubeTextureList);
   // 初始化接口指针
   //MO_CLEAR(_piSwapChain);
   //MO_CLEAR(_piDevice);
   // 注册类集合
   _pClassFactory->Register(MO_RENDEROBJECT_SHADERBUFFER,    FPd9RenderShaderBuffer::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_SHADERATTRIBUTE, FRenderShaderAttribute::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_SHADERPARAMETER, FPd9RenderShaderParameter::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_SHADERSAMPLER,   FRenderShaderSampler::Class());
   _pClassFactory->Register(MO_RENDEROBJECT_LAYOUT,          FPd9RenderLayout::Class());
   //
   //MO_CLEAR(_piRasterizerState);
   //MO_CLEAR(_piBlendEnableState);
   //MO_CLEAR(_piBlendDisableState);
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FPd9RenderDevice::~FPd9RenderDevice(){
   //MO_DELETE(_pCapability);
   //MO_RELEASE(_piBlendEnableState);
   //MO_RELEASE(_piBlendDisableState);
   //// 删除关联集合
   //MO_DELETE(_pLinkFlatTextures);
   //MO_DELETE(_pLinkCubeTextures);
   //// 释放内存
   //MO_RELEASE(_piSwapChain);
   //MO_RELEASE(_piDevice);
}

//============================================================
// <T>更新环境。</T>
//============================================================
TBool FPd9RenderDevice::UpdateContext(){
   TBool result = EFalse;
   // 设置激活的程序
   if(_pActiveProgram != _pProgram){
      //FPd9RenderProgram* pProgrom = (FPd9RenderProgram*)_pProgram;
      //glUseProgram(pProgrom->ProgramId());
      //_pProgram = _pActiveProgram;
      result = ETrue;
   }
   return result;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::Setup(){
   // 父配置处理
   TResult result = FRenderDevice::Setup();
   // 创建驱动
   _piDirect3D = Direct3DCreate9(D3D_SDK_VERSION);
   if(_piDirect3D == NULL){
      MO_FATAL("Create direct3d 9 failure.");
      return EFailure;
   }
   // 创建设备
   D3DPRESENT_PARAMETERS deviceDescriptor = {0};
   deviceDescriptor.Windowed = TRUE;
   deviceDescriptor.SwapEffect = D3DSWAPEFFECT_DISCARD;
   deviceDescriptor.BackBufferFormat = D3DFMT_UNKNOWN;
   deviceDescriptor.EnableAutoDepthStencil = TRUE;
   deviceDescriptor.AutoDepthStencilFormat = D3DFMT_D16;
   HRESULT dxResult = _piDirect3D->CreateDevice(D3DADAPTER_DEFAULT, D3DDEVTYPE_HAL, _windowHandle, D3DCREATE_SOFTWARE_VERTEXPROCESSING, &deviceDescriptor, &_piDevice);
   if(FAILED(dxResult)){
      MO_FATAL("Create device failure.");
      return EFailure;
   }
   // 设置状态
   _piDevice->SetRenderState(D3DRS_CULLMODE, D3DCULL_NONE);
   _piDevice->SetRenderState(D3DRS_LIGHTING, EFalse);
   _piDevice->SetRenderState(D3DRS_ZENABLE, ETrue);
   return ESuccess;
}

//============================================================
// <T>暂停处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::Suspend(){
   // 暂停处理
   FRenderDevice::Suspend();
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::Resume(){
   FRenderDevice::Resume();
   return ESuccess;
}

//============================================================
// <T>检查执行错误。</T>
//
// @param pCode 代码
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::CheckError(TCharC* pCode, TCharC* pMessage, ...){
   // 获得错误原因
   TBool result = EFalse;
//   GLenum errorCode = 0;
//   TCharC* pErrorReason = NULL;
//   while(ETrue){
//      // 获得错误
//      GLenum code = glGetError();
//      if(code == GL_NO_ERROR){
//         break;
//      }
//      // 获得原因
//      switch(code){
//         case GL_INVALID_OPERATION:
//            pErrorReason = "Invalid operation.";
//            break;
//         case GL_INVALID_ENUM:
//            pErrorReason = "Invalid enum.";
//            break;
//         case GL_INVALID_VALUE:
//            pErrorReason = "Invalid value.";
//            break;
//         case GL_INVALID_FRAMEBUFFER_OPERATION:
//            pErrorReason = "Invalid paramebuffer opeartion."; 
//            break;
//         case GL_OUT_OF_MEMORY:
//            pErrorReason = "Out of memory.";
//            break;
//         default:
//            pErrorReason = "Unknown"; 
//            break;
//      }
//      result = ETrue;
//      errorCode = code;
//#ifdef _MO_WINDOWS
//      break;
//#endif // _MO_WINDOWS
//   }
//   //............................................................
//   // 输出错误信息
//   if(result){
//      // 格式化可变参数字符串信息
//      TFsText message;
//      va_list params;
//      va_start(params, pMessage);
//      message.AppendFormatParameters(pMessage, params);
//      va_end(params);
//      // 输出错误信息
//      MO_ERROR("%s (code=%s, error=0x%04X(%d), reason=%s)", (TCharC*)message, pCode, errorCode, errorCode, pErrorReason);
//   }
   return result;
}

//============================================================
// <T>创建顶点缓冲。</T>
//
// @param pClass 类对象
// @return 顶点缓冲
//============================================================
FRenderVertexBuffer* FPd9RenderDevice::CreateVertexBuffer(FClass* pClass){
   FRenderVertexBuffer* pVertexBuffer = FPd9RenderVertexBuffer::InstanceCreate();
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
FRenderIndexBuffer* FPd9RenderDevice::CreateIndexBuffer(FClass* pClass){
   FRenderIndexBuffer* pIndexBuffer = FPd9RenderIndexBuffer::InstanceCreate();
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
FRenderProgram* FPd9RenderDevice::CreateProgrom(FClass* pClass){
   FPd9RenderProgram* pProgram = FPd9RenderProgram::InstanceCreate();
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
FRenderTarget* FPd9RenderDevice::CreateRenderTarget(FClass* pClass){
   FPd9RenderTarget* pRenderTarget = FPd9RenderTarget::InstanceCreate();
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
FRenderFlatTexture* FPd9RenderDevice::CreateFlatTexture(FClass* pClass){
   FPd9RenderFlatTexture* pTexture = FPd9RenderFlatTexture::InstanceCreate();
   pTexture->SetDevice(this);
   _storageTextures.Push(pTexture);
   //_pLinkFlatTextures->Push(pTexture);
   return pTexture;
}

//============================================================
// <T>创建空间纹理。</T>
//
// @param pClass 类对象
// @return 纹理
//============================================================
FRenderCubeTexture* FPd9RenderDevice::CreateCubeTexture(FClass* pClass){
   FPd9RenderCubeTexture* pTexture = FPd9RenderCubeTexture::InstanceCreate();
   pTexture->SetDevice(this);
   _storageTextures.Push(pTexture);
   //_pLinkCubeTextures->Push(pTexture);
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
TResult FPd9RenderDevice::Clear(TFloat red, TFloat green, TFloat blue, TFloat alpha, TFloat depth){
   TInt r = (TInt)(red * 255.0f);
   TInt g = (TInt)(green * 255.0f);
   TInt b = (TInt)(blue * 255.0f);
   TInt a = (TInt)(alpha * 255.0f);
   D3DCOLOR color = D3DCOLOR_ARGB(a, r, g, b);
   _piDevice->Clear(0, NULL, D3DCLEAR_TARGET | D3DCLEAR_ZBUFFER, color, depth, 0);
   return ETrue;
}

//============================================================
// <T>设置背景缓冲。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed){
   return ESuccess;
}

//============================================================
// <T>设置填充模式。</T>
//
// @param fillModeCd 填充模式
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetFillMode(ERenderFillMode fillModeCd){
   // 检查状态
   if(_fillModeCd == fillModeCd){
      return EContinue;
   }
   //D3D9_RASTERIZER_DESC description;
   //RType<D3D9_RASTERIZER_DESC>::Clear(&description);
   //description.FillMode = RDirectX9::ConvertFillMode(fillModeCd);
   //ID3D9RasterizerState* pRasterizerState = NULL;
   //_piDevice->CreateRasterizerState(&description, &pRasterizerState);
   //_piDevice->RSSetState(pRasterizerState);
//   // 设置内容
//#ifndef _MO_ANDROID
//   switch(fillModeCd){
//      case ERenderFillMode_Point:
//         glPolygonMode(GL_FRONT, GL_POINT);
//         glPolygonMode(GL_BACK, GL_POINT);
//         break;
//      case ERenderFillMode_Line:
//         glPolygonMode(GL_FRONT, GL_LINE);
//         glPolygonMode(GL_BACK, GL_LINE);
//         break;
//      case ERenderFillMode_Fill:
//         glPolygonMode(GL_FRONT, GL_FILL);
//         glPolygonMode(GL_BACK, GL_FILL);
//         break;
//   }
//#endif // _MO_ANDROID
//   _fillModeCd = fillModeCd;
//   _statistics->UpdateOptionFillCount();
   return ESuccess;
}

//============================================================
// <T>设置深度模式。</T>
//
// @param depth 深度开关
// @param depthCd 深度模式
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetDepthMode(TBool depth, ERenderDepthMode depthCd){
   // 检查状态
   if((_optionDepth == depth) && (_depthModeCd == depthCd)){
      return EContinue;
   }
   //// 设置开关
   //if(_optionDepth != depth){
   //   if(depth){
   //      glEnable(GL_DEPTH_TEST);
   //   }else{
   //      glDisable(GL_DEPTH_TEST);
   //   }
   //   _optionDepth = depth;
   //   _statistics->UpdateOptionDeepCount();
   //}
   //// 设置内容
   //if(depth && _depthModeCd != depthCd){
   //   switch(depthCd){
   //      case ERenderDepthMode_Equal:
   //         glDepthFunc(GL_EQUAL);
   //         break;
   //      case ERenderDepthMode_NotEqual:
   //         glDepthFunc(GL_NOTEQUAL);
   //         break;
   //      case ERenderDepthMode_Less:
   //         glDepthFunc(GL_LESS);
   //         break;
   //      case ERenderDepthMode_LessEqual:
   //         glDepthFunc(GL_LEQUAL);
   //         break;
   //      case ERenderDepthMode_Greater:
   //         glDepthFunc(GL_GREATER);
   //         break;
   //      case ERenderDepthMode_GreaterEqual:
   //         glDepthFunc(GL_GEQUAL);
   //         break;
   //      case ERenderDepthMode_Always:
   //         glDepthFunc(GL_ALWAYS);
   //         break;
   //   }
   //   _depthModeCd = depthCd;
   //}
   return ESuccess;
}

//============================================================
// <T>设置剪裁模式。</T>
//
// @param cull 剪裁开关
// @param cullCd 剪裁模式
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetCullingMode(TBool cull, ERenderCullMode cullCd){
   // 检查状态
   if((_optionCull == cull) && (_optionCull == cullCd)){
      return EContinue;
   }
   //RDirectX9::ConvertCullMode(cullCd);
   //if(_piRasterizerState == NULL){
   //   D3D9_RASTERIZER_DESC descriptor;
   //   RType<D3D9_RASTERIZER_DESC>::Clear(&descriptor);
   //   descriptor.FillMode = D3D9_FILL_SOLID;
   //   descriptor.CullMode = D3D9_CULL_NONE;
   //   HRESULT dxResult = _piDevice->CreateRasterizerState(&descriptor, &_piRasterizerState);
   //   if(FAILED(dxResult)){
   //      MO_FATAL("Create rasterizer state failure.");
   //      return EFailure;
   //   }
   //}
   //_piDevice->RSSetState(_piRasterizerState);
   //// 设置开关
   //if(_optionCull != cull){
   //   if(cull){
   //      glEnable(GL_CULL_FACE);
   //   }else{
   //      glDisable(GL_CULL_FACE);
   //   }
   //   _optionCull = cull;
   //   _statistics->UpdateOptionCullCount();
   //}
   //// 设置内容
   //if(cull && _cullModeCd != cullCd){
   //   switch(cullCd){
   //      case ERenderCullMode_Front:
   //         glCullFace(GL_FRONT);
   //         break;
   //      case ERenderCullMode_Back:
   //         glCullFace(GL_BACK);
   //         break;
   //      case ERenderCullMode_Both:
   //         glCullFace(GL_FRONT_AND_BACK);
   //         break;
   //   }
   //   _cullModeCd = cullCd;
   //}
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
TResult FPd9RenderDevice::SetBlendFactors(TBool blend, ERenderBlendMode sourceCd, ERenderBlendMode targetCd){
   TFloat blendFactors[4] = {0};
   if(blend == ETrue){
      //_piDevice->OMSetBlendState(_piBlendEnableState, blendFactors, 0XFFFFFFFF);
   }else{
      //_piDevice->OMSetBlendState(_piBlendDisableState, blendFactors, 0XFFFFFFFF);
   }
   //_piDevice->OMSetBlendState(_piBlendEnableState, blendFactors, 0XFFFFFFFF);
   //// 设置开关
   //if(_statusBlend != blend){
   //   if(blend){
   //      glEnable(GL_BLEND);
   //   }else{
   //      glDisable(GL_BLEND);
   //   }
   //   _statusBlend = blend;
   //   _statistics->UpdateOptionBlendCount();
   //}
   //// 设置效果
   //if(blend && ((_blendSourceCd != sourceCd) || (_blendTargetCd != targetCd))){
   //   GLenum source = ConvertBlendFactors(sourceCd);
   //   GLenum target = ConvertBlendFactors(targetCd);
   //   glBlendFunc(source, target);
   //   _blendSourceCd = sourceCd;
   //   _blendTargetCd = targetCd;
   //}
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
TResult FPd9RenderDevice::SetScissorRectangle(TInt left, TInt top, TInt width, TInt height){
   //D3D9_RECT rect;
   //rect.left = left;
   //rect.top = top;
   //rect.right = left + width;
   //rect.bottom = top + height;
   //_piDevice->RSSetScissorRects(1, &rect);
   return ETrue;
}

//============================================================
// <T>设置渲染目标。</T>
//
// @param pRenderTarget 渲染目标
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetRenderTarget(FRenderTarget* pRenderTarget){
   TResult result = ESuccess;
   //if(pRenderTarget == NULL){
   //   // 解除渲染目标
   //   glBindFramebuffer(GL_FRAMEBUFFER, 0);
   //   result = CheckError("glBindFramebuffer", "Bind frame buffer. (buffer_id=%d)", 0);
   //   if(result != ESuccess){
   //      return result;
   //   }
   //   // 修改视角
   //   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   //   SIntSize2& screenSize = pScreenDevice->Size();
   //   glViewport(0, 0, screenSize.width, screenSize.height);
   //   return ESuccess;
   //}else{
   //   // 绑定渲染目标
   //   GLuint frameBufferId = pRenderTarget->RenderId().uint32;
   //   glBindFramebuffer(GL_FRAMEBUFFER, frameBufferId);
   //   result = CheckError("glBindFramebuffer", "Bind frame buffer. (buffer_id=%d)", frameBufferId);
   //   if(result != ESuccess){
   //      return result;
   //   }
   //   // 修改视角
   //   SIntSize2& size = pRenderTarget->Size();
   //   glViewport(0, 0, size.width, size.height);
   //   // 清空颜色
   //   SFloatColor4& backgroundColor = pRenderTarget->BackgroundColor();
   //   glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, backgroundColor.alpha);
   //   //glClearDepth(1.0f);
   //   glClearStencil(0);
   //   glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
   //}
   //_pActiveRenderTarget = pRenderTarget;
   return ESuccess;
}

//============================================================
// <T>设置程序。</T>
//
// @param pProgram 程序
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetProgram(FRenderProgram* pProgram){
   TResult resultCd = ESuccess;
   // 检查程序
   //if(_pProgram == pProgram){
   //   return EContinue;
   //}
   // 设置程序
   //if(pProgram != NULL){
   //   // 设置顶点脚本
   //   FPd9RenderVertexShader* pVertexShader = pProgram->VertexShader()->Convert<FPd9RenderVertexShader>();
   //   ID3D9VertexShader* piVertexShader = pVertexShader->NativeShader();
   //   _piDevice->VSSetShader(piVertexShader);
   //   //MO_DEBUG("Set vertex shader. (shader=0x%08X)", piVertexShader);
   //   // 设置像素脚本
   //   FPd9RenderFragmentShader* pFragmentShader = pProgram->FragmentShader()->Convert<FPd9RenderFragmentShader>();
   //   ID3D9PixelShader* piFragmentShader = pFragmentShader->NativeShader();
   //   _piDevice->PSSetShader(piFragmentShader);
   //   //MO_DEBUG("Set pixel shader. (shader=0x%08X)", piFragmentShader);
   //   // 设置输入层次
   //   FPd9RenderProgram* pRenderProgram = pProgram->Convert<FPd9RenderProgram>();
   //   ID3D9InputLayout* piInputLayout = pRenderProgram->NativeInputLayout();
   //   _piDevice->IASetInputLayout(piInputLayout);
   //   //MO_DEBUG("Set input layout. (layout=0x%08X)", piInputLayout);
   //}
   _pProgram = pProgram;
   // 检查是否可以执行
   _statistics->UpdateProgramCount();
   return resultCd;
}

//============================================================
// <T>设置布局。</T>
//
// @parma pLayout 布局
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::SetLayout(FRenderLayout* pLayout){
   MO_CHECK(pLayout, return ENull);
   //// 获得顶点流
   //TResult result = ESuccess;
   //FPd9RenderLayout* pRenderLayout = pLayout->Convert<FPd9RenderLayout>();
   //// 获得信息
   //TInt count = pRenderLayout->Count();
   //ID3D9Buffer** piBuffer = pRenderLayout->Buffer();
   //UINT* bufferStride = pRenderLayout->Stride();
   //UINT* bufferOffset = pRenderLayout->Offset();
   //// 设置内容
   //_piDevice->IASetVertexBuffers(0, count, piBuffer, bufferStride, bufferOffset);
   //MO_DEBUG("Set vertex buffers. (slot=%d, count=%d)", 0, count);
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
TResult FPd9RenderDevice::BindConstData(ERenderShader shaderCd, TInt slot, ERenderShaderParameterFormat formatCd, TAnyC* pData, TInt length){
   // 检查变更
   TBool changed = UpdateConsts(shaderCd, slot, pData, length);
   if(!changed){
      return EContinue;
   }
   // 修改数据
   TResult result = ESuccess;
   //switch (formatCd){
   //   case ERenderShaderParameterFormat_Float1:{
   //      // 检查长度
   //      if(length % 4 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length / 4;
   //      glUniform1fv(slot, count, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniform1fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //   case ERenderShaderParameterFormat_Float2:{
   //      // 检查长度
   //      if(length % 8 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length / 8;
   //      glUniform2fv(slot, count, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniform2fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //   case ERenderShaderParameterFormat_Float3:{
   //      // 检查长度
   //      if(length % 12 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length / 12;
   //      glUniform3fv(slot, count, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniform3fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //   case ERenderShaderParameterFormat_Float4:{
   //      // 检查长度
   //      if(length % 16 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length / 16;
   //      glUniform4fv(slot, count, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniform4fv", "Bind const data failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //   case ERenderShaderParameterFormat_Matrix3x3:{
   //      // 检查长度
   //      if(length % 36 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length / 36;
   //      glUniformMatrix3fv(slot, count, GL_FALSE, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniformMatrix4fv", "Bind const matrix3x3 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //   case ERenderShaderParameterFormat_Matrix4x3:{
   //      // 检查长度
   //      if(length % 48 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length / 48;
   //      glUniform4fv(slot, count * 3, (const GLfloat*)pData);
   //      //glUniformMatrix4x3fv(slot, count, GL_FALSE, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniformMatrix4x3fv", "Bind const matrix4x3 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //   case ERenderShaderParameterFormat_Matrix4x4:{
   //      // 检查长度
   //      if(length % 64 != 0){
   //         MO_ERROR("Length is invalid. (length=d)", length);
   //         return EFailure;
   //      }
   //      // 修改数据
   //      TInt count = length >> 6;
   //      glUniformMatrix4fv(slot, count, GL_FALSE, (const GLfloat*)pData);
   //      // 检查错误
   //      result = CheckError("glUniformMatrix4fv", "Bind const matrix4x4 failure. (shader_cd=%d, slot=%d, pData=0x%08X, length=%d)", shaderCd, slot, pData, length);
   //      break;
   //   }
   //}
   //// MO_INFO("Bind const buffer. (slot=%d, format_cd=%d, length=%d)", slot, formatCd, length);
   //_statistics->UpdateProgramCount(length);
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
TResult FPd9RenderDevice::BindConstFloat3(ERenderShader shaderCd, TInt slot, TFloat x, TFloat y, TFloat z){
   MO_FATAL_UNSUPPORT();
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
TResult FPd9RenderDevice::BindConstFloat4(ERenderShader shaderCd, TInt slot, TFloat x, TFloat y, TFloat z, TFloat w){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>绑定常量三维矩阵。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma matrix 矩阵
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::BindConstMatrix3x3(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>绑定常量三维矩阵。</T>
//
// @parma shaderCd 渲染类型
// @parma slot 插槽
// @parma matrix 矩阵
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::BindConstMatrix4x4(ERenderShader shaderCd, TInt slot, const SFloatMatrix3d& matrix){
   MO_FATAL_UNSUPPORT();
   return ESuccess;
}

//============================================================
// <T>绑定渲染缓冲。</T>
//
// @param pBuffer 渲染缓冲
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::BindShaderBuffer(FRenderShaderBuffer* pBuffer){
   MO_CHECK(pBuffer, return ENull);
   if(!pBuffer->IsStatusUsed()){
      return EContinue;
   }
   //// 更新数据
   //FPd9RenderShaderBuffer* pRenderBuffer = pBuffer->Convert<FPd9RenderShaderBuffer>();
   //TInt slot = pRenderBuffer->Slot();
   //ERenderShader shaderCd = pRenderBuffer->ShaderCd();
   //ERenderShaderBuffer groupCd = pRenderBuffer->GroupCd();
   //ID3D9Buffer* piBuffer = pRenderBuffer->NativeiBuffer();
   //if((groupCd == ERenderShaderBuffer_Global) || (groupCd == ERenderShaderBuffer_Technique) || (groupCd == ERenderShaderBuffer_Effect)){
   //   _piDevice->VSSetConstantBuffers(slot, 1, &piBuffer);
   //   _piDevice->PSSetConstantBuffers(slot, 1, &piBuffer);
   //}else if(groupCd == ERenderShaderBuffer_Renderable){
   //   // 更新显示相关
   //   if(shaderCd == ERenderShader_Vertex){
   //      _piDevice->VSSetConstantBuffers(slot, 1, &piBuffer);
   //   }else if(shaderCd == ERenderShader_Fragment){
   //      _piDevice->PSSetConstantBuffers(slot, 1, &piBuffer);
   //   }else{
   //      MO_FATAL("Render shader type is unknown. (shader=%d)", shaderCd);
   //   }
   //}else{
   //   MO_FATAL("Render shader group is unknown. (group=%d)", groupCd);
   //}
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
TResult FPd9RenderDevice::BindVertexBuffer(TInt slot, FRenderVertexBuffer* pVertexBuffer, TInt offset, ERenderVertexFormat formatCd){
   MO_ERROR_CHECK(slot >= 0, return EFailure, "Slot value is invalid. (slot=%d)", slot);
   // 获得顶点流
   TResult result = ESuccess;
   FPd9RenderVertexBuffer* pBuffer = pVertexBuffer->Convert<FPd9RenderVertexBuffer>();
   //// 获得信息
   //ID3D9Buffer* piBuffer = pBuffer->NativeBuffer();
   //UINT bufferStride = pVertexBuffer->Stride();
   //UINT bufferOffset = offset;
   //// 设置内容
   //_piDevice->IASetVertexBuffers(slot, 1, &piBuffer, &bufferStride, &bufferOffset);
   //MO_DEBUG("Set vertex buffer. (slot=%d, buffer=0x%08X, stride=%d, offset=%d)", slot, piBuffer, bufferStride, bufferOffset);
   return result;
}

//============================================================
// <T>绑定纹理。</T>
//
// @param slot 插槽
// @param pTexture 纹理
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::BindTexture(TInt slot, FRenderTexture* pTexture){
   TResult result = ESuccess;
   //............................................................
   // 绑定纹理
   ERenderTexture textureCd = pTexture->TextureCd();
   //switch (textureCd){
   //   case ERenderTexture_Flat2d:{
   //      FPd9RenderFlatTexture* pFlatTexture = (FPd9RenderFlatTexture*)pTexture;
   //      ID3D9ShaderResourceView* piTextureView = pFlatTexture->NativeView();
   //      ID3D9SamplerState* piState = pFlatTexture->NativeState();
   //      _piDevice->PSSetShaderResources(slot, 1, &piTextureView);
   //      _piDevice->PSSetSamplers(slot, 1, &piState);
   //      //MO_DEBUG("Set texture 2d. (slot=%d, texture=0x%08X)", slot, pTexture);
   //      break;
   //   }
   //   case ERenderTexture_Cube:{
   //      FPd9RenderCubeTexture* pCubeTexture = (FPd9RenderCubeTexture*)pTexture;
   //      ID3D9ShaderResourceView* piTextureView = pCubeTexture->NativeView();
   //      ID3D9SamplerState* piState = pCubeTexture->NativeState();
   //      _piDevice->PSSetShaderResources(slot, 1, &piTextureView);
   //      _piDevice->PSSetSamplers(slot, 1, &piState);
   //      //MO_DEBUG("Set texture cube. (slot=%d, texture=0x%08X)", slot, pTexture);
   //      break;
   //   }
   //   default:{
   //      MO_FATAL("Unknown texture type.");
   //      break;
   //   }
   //}
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
TResult FPd9RenderDevice::DrawTriangles(FRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count){
   MO_CHECK(pIndexBuffer, return ENull);
   MO_CHECK(offset >= 0, return EOutRange);
   MO_CHECK(count > 0, return EOutRange);
   TResult resultCd = ESuccess;
   //// 程序绘制开始
   //_pProgram->DrawBegin();
   //// 设置索引流
   //FPd9RenderIndexBuffer* pBuffer = pIndexBuffer->Convert<FPd9RenderIndexBuffer>();
   //ID3D9Buffer* piBuffer = pBuffer->NativeBuffer();
   //if(piBuffer == NULL){
   //   MO_FATAL("Index buffer is null.");
   //   return ENull;
   //}
   //DXGI_FORMAT strideCd = RDirectX9::ConvertIndexStride(pIndexBuffer->StrideCd());
   //_piDevice->IASetPrimitiveTopology(D3D9_PRIMITIVE_TOPOLOGY_TRIANGLELIST);
   //_piDevice->IASetIndexBuffer(piBuffer, strideCd, 0);
   //// 绘制三角形
   //_renderDrawStatistics->Begin();
   //_piDevice->DrawIndexed(count, offset, 0);
   ////MO_DEBUG("Draw indexed. (offset=%d, count=%d)", offset, count);
   //_renderDrawStatistics->Finish();
   //// 程序绘制结束
   //_pProgram->DrawEnd();
   //// 检查错误
   //_statistics->UpdateDraw(count);
   return resultCd;
}

//============================================================
// <T>显示画面。</T>
//
// @return 处理结果
//============================================================
TResult FPd9RenderDevice::Present(){
   _piDevice->Present(NULL, NULL, NULL, NULL);
   return ESuccess;
}

MO_NAMESPACE_END
