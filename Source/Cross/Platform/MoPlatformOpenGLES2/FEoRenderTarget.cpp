#include "MoEoRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEoRenderTarget, FRenderTarget);

//============================================================
// <T>构造平面纹理。</T>
//============================================================
FEoRenderTarget::FEoRenderTarget(){
   _frameBufferId = 0;
   _depthStencilId = 0;
   _depthBufferId = 0;
}

//============================================================
// <T>析构平面纹理。</T>
//============================================================
FEoRenderTarget::~FEoRenderTarget(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderTarget::OnSetup(){
   TResult resultCd = ESuccess;
   //............................................................
   // 获得存储缓冲
   GLint storageFrameBufferId = 0;
   // glGetIntegerv(GL_FRAMEBUFFER, &storageFrameBufferId);
   //............................................................
   // 创建帧缓冲
   glGenFramebuffers(1, &_frameBufferId);
   resultCd = _pDevice->CheckError("glGenFramebuffers",
         "Create frame buffer faliure. (framebuffer_id=%d)", _frameBufferId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   glBindFramebuffer(GL_FRAMEBUFFER, _frameBufferId);
   resultCd = _pDevice->CheckError("glBindFramebuffer",
         "Bind frame buffer faliure. (framebuffer_id=%d)", _frameBufferId);
   if(resultCd != ESuccess){
      return resultCd;
   }
   //............................................................
   // 创建深度和模板缓冲区
   if(_optionDepth && _optionStencil){
      //GLfloat border[] = {1.0f, 0.0f, 0.0f, 0.0f};
      //glGenTextures(1, &_depthStencilId);
      //glBindTexture(GL_TEXTURE_2D, _depthStencilId);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
      //glTexParameterfv(GL_TEXTURE_2D, GL_TEXTURE_BORDER_COLOR, border);
      ////glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_MODE, GL_COMPARE_REF_TO_TEXTURE);
      ////glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_COMPARE_FUNC, GL_LESS);
      //glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH24_STENCIL8, _size.width, _size.height, 0, GL_DEPTH_STENCIL, GL_UNSIGNED_INT_24_8, NULL);
      //resultCd = pRenderDevice->CheckError("glGenTextures",
      //      "Generate depth and stencil buffer failure. (depthstencil_id=%d)", _depthStencilId);
      //if(resultCd != ESuccess){
      //   return resultCd;
      //}
      //// 绑定深度和模板缓冲区
      //glFramebufferTexture2D(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_TEXTURE_2D, _depthStencilId, 0);
      //glFramebufferTexture2D(GL_FRAMEBUFFER, GL_STENCIL_ATTACHMENT, GL_TEXTURE_2D, _depthStencilId, 0);
      //resultCd = pRenderDevice->CheckError("glFramebufferTexture2D",
      //      "Set depth and stencil buffer to frame buffer failure. (framebuffer_id=%d, depthstencil_id=%d)", _frameBufferId, _depthStencilId);
      //if(resultCd != ESuccess){
      //   return resultCd;
      //}
   }
   //............................................................
   // 创建深度缓冲区
   if(_optionDepth && !_depthStencilId){
      glGenRenderbuffers(1, &_depthBufferId);
      resultCd = _pDevice->CheckError("glGenRenderbuffers",
            "Create depth frame buffer faliure. (framebuffer_id=%d)", _depthBufferId);
      if(resultCd != ESuccess){
         return resultCd;
      }
      glBindRenderbuffer(GL_RENDERBUFFER, _depthBufferId);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);  
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
      resultCd = _pDevice->CheckError("glBindRenderbuffer",
            "Bind depth frame buffer faliure. (framebuffer_id=%d)", _depthBufferId);
      if(resultCd != ESuccess){
         return resultCd;
      }
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT16, _size.width, _size.height);
      //glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT32, _size.width, _size.height);
      resultCd = _pDevice->CheckError("glRenderbufferStorage",
            "Set depth buffer storage failure. (depthbuffer_id=%d, depth=%d)", _depthBufferId, 32);
      if(resultCd != ESuccess){
         return resultCd;
      }
      // 绑定深度缓冲区
      glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, _depthBufferId);
      resultCd = _pDevice->CheckError("glFramebufferRenderbuffer",
            "Set depth buffer to frame buffer failure. (framebuffer_id=%d, depthbuffer_id=%d)", _frameBufferId, _depthBufferId);
      if(resultCd != ESuccess){
         return resultCd;
      }
   }
   //............................................................
   // 绑定纹理缓冲集合
   TInt textureCount = _pTextures->Count();
   for(TInt n = 0; n < textureCount; n++){
      FRenderTexture* pTexture = _pTextures->Get(n);
      GLuint textureId = pTexture->GraphicHandle().data.uint32;
      // 设置信息
      glBindTexture(GL_TEXTURE_2D, textureId);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);  
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
      glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);  
      //glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
      // 设置存储
      glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, _size.width, _size.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, NULL);
      //glTexImage2D(GL_TEXTURE_2D, 0, GL_R32F, _size.width, _size.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, NULL);
      //glTexImage2D(GL_TEXTURE_2D, 0, GL_DEPTH_COMPONENT, _size.width, _size.height, 0, GL_DEPTH_COMPONENT, GL_UNSIGNED_BYTE, NULL);
      resultCd = _pDevice->CheckError("glTexImage2D",
            "Alloc texture storage. (texture_id, size=%dx%d)", textureId, _size.width, _size.height);
      if(resultCd != ESuccess){
         MO_FATAL_UNSUPPORT();
         return resultCd;
      }
      // 绑定数据
      glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL_TEXTURE_2D, textureId, 0);
      resultCd = _pDevice->CheckError("glFramebufferTexture2D",
            "Set color buffer into frame buffer failure. (framebuffer_id=%d, texture_id=%d)", _frameBufferId, textureId);
      if(resultCd != ESuccess){
         MO_FATAL_UNSUPPORT();
         return resultCd;
      }
   }
   //............................................................
   // 检查缓冲结果
   //GLenum status = glCheckFramebufferStatus(GL_FRAMEBUFFER);
   //if(status != GL_FRAMEBUFFER_COMPLETE){
   //   MO_FATAL("Frame buffer status is invalid.");
   //}
   _renderId.uint32 = _frameBufferId;
   //............................................................
   // 回复存储缓冲
   glBindFramebuffer(GL_FRAMEBUFFER, storageFrameBufferId);
   return resultCd;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderTarget::Suspend(){
   TResult resultCd = ESuccess;
   if(_depthBufferId != 0){
      glDeleteRenderbuffers(1, &_depthBufferId);
      resultCd = _pDevice->CheckError("glDeleteRenderbuffers",
            "Delete render depth buffer failure. (depthbuffer_id=%d)", _depthBufferId);
      _depthBufferId = 0;
   }
   if(_frameBufferId != 0){
      glDeleteFramebuffers(1, &_frameBufferId);
      resultCd = _pDevice->CheckError("glDeleteFramebuffers",
            "Delete frame buffer failure. (framebuffer_id=%d)", _frameBufferId);
      _frameBufferId = 0;
   }
   return resultCd;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderTarget::Resume(){
   OnSetup();
   MO_INFO("Resume rendertarget. (target_id=%d)", _frameBufferId);
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FEoRenderTarget::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
