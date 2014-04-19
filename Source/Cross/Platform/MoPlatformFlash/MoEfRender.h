#ifndef __MO_EF_RENDER_H__
#define __MO_EF_RENDER_H__
//************************************************************

#ifndef __MO_EF_COMMON_H__
#include "MoEfCommon.h"
#endif // __MO_EF_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>渲染程序。</T>
//============================================================
class FEfRenderProgrom : public FObject{
public:
   FEfRenderProgrom();
   MO_ABSTRACT ~FEfRenderProgrom();
public:
   MO_VIRTUAL void Compile(EEgRenderProgrom progromCd, TCharC* pSource) = 0;
};

//============================================================
// <T>渲染程序。</T>
//============================================================
class FEfRenderDevice : public FEgRenderDevice{
protected:
   flash::display::Stage3D _stage3d;
   flash::display3D::Context3D _context3d;
public:
   FEfRenderDevice();
   MO_ABSTRACT ~FEfRenderDevice();
protected:
   static var OnContextInitialize(TAny* pSender, var args);
   static var OnContextError(TAny* pSender, var args);
 public:
   MO_OVERRIDE void Setup();
public:
   MO_OVERRIDE FEgRenderVertexBuffer* CreateVertexBuffer(TInt vertexCount, TInt vertexStride);
   MO_OVERRIDE FEgRenderIndexBuffer* CreateIndexBuffer(TInt indexCount);
   MO_OVERRIDE FEgRenderProgram* CreateProgrom();
   MO_OVERRIDE FEgRenderFlatTexture* CreateFlatTexture(TInt width, TInt height, EEgRenderTextureFormat formatCd, TBool optimize = EFalse, TInt mipLevel = 0);
   MO_OVERRIDE FEgRenderCubeTexture* CreateCubeTexture(TInt size, EEgRenderTextureFormat formatCd, TBool optimize = EFalse, TInt mipLevel = 0);
public:
   MO_OVERRIDE void Clear(TFloat red = 0.0f, TFloat green = 0.0f, TFloat blue = 0.0f, TFloat alpha = 1.0f, TFloat depth = 1.0f);
   MO_OVERRIDE void SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed = ETrue);
   MO_OVERRIDE void SetCulling(EEgRenderCull cullCd);
   MO_OVERRIDE void SetBlendFactors(TCharC* sourceFactor, TCharC* destinationFactor);
   MO_OVERRIDE void SetScissorRectangle(TFloat left, TFloat top, TFloat width, TFloat height);
   MO_OVERRIDE void BindVertexBuffer(TInt slot, FEgRenderVertexBuffer* pVertexBuffer, TInt offset, EEgRenderVertexFormat formatCd);
   MO_OVERRIDE void BindTexture(TInt slot, FEgRenderTexture* pTexture);
   MO_OVERRIDE void DrawTriangles(FEgRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count);
   MO_OVERRIDE void Present();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EF_RENDER_H__
