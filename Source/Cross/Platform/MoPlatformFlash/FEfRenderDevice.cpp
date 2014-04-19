#include "MoEfRender.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造舞台对象。</T>
//============================================================
FEfRenderDevice::FEfRenderDevice(){
}

//============================================================
// <T>析构舞台对象。</T>
//============================================================
FEfRenderDevice::~FEfRenderDevice(){
}

//============================================================
var FEfRenderDevice::OnContextInitialize(TAny* pSender, var args){
   AS3_Trace("FEfRenderDevice::OnContextInitialize");
   FEfRenderDevice* pRenderDevice = (FEfRenderDevice*)pSender;
   pRenderDevice->_context3d = pRenderDevice->_stage3d->context3D;
   printf("OnContextInitialize\n");
   return internal::_undefined;
}

//============================================================
var FEfRenderDevice::OnContextError(TAny* pSender, var args){
   AS3_Trace("FEfRenderDevice::OnContextError");
   FEfRenderDevice* pRenderDevice = (FEfRenderDevice*)pSender;
   pRenderDevice->_context3d = pRenderDevice->_stage3d->context3D;
   printf("OnContextError\n");
   return internal::_undefined;
}

//============================================================
// <T>配置处理。</T>
//============================================================
void FEfRenderDevice::Setup(){
   AS3_Trace("FEfRenderDevice::Setup");
   // 获得舞台
   flash::display::Stage stage = internal::get_Stage();
   // 构造设备
   var stage3ds = var(stage->stage3Ds);
   _stage3d = var(stage3ds[0]);
   _stage3d->addEventListener(flash::events::Event::CONTEXT3D_CREATE, Function::_new(OnContextInitialize, this));
   _stage3d->addEventListener(flash::events::ErrorEvent::ERROR, Function::_new(OnContextError, this));
   _stage3d->requestContext3D(flash::display3D::Context3DRenderMode::AUTO, flash::display3D::Context3DProfile::BASELINE_CONSTRAINED);
}

//============================================================
// <T>创建顶点缓冲。</T>
//============================================================
FEgRenderVertexBuffer* FEfRenderDevice::CreateVertexBuffer(TInt vertexCount, TInt vertexStride){
   // flash::display3D::Context3D _context3d;
   return NULL;
}

//============================================================
// <T>创建索引缓冲。</T>
//============================================================
FEgRenderIndexBuffer* FEfRenderDevice::CreateIndexBuffer(TInt indexCount){
   return NULL;
}

//============================================================
// <T>创建程序。</T>
//============================================================
FEgRenderProgram* FEfRenderDevice::CreateProgrom(){
   return NULL;
}

//============================================================
// <T>创建平面纹理。</T>
//============================================================
FEgRenderFlatTexture* FEfRenderDevice::CreateFlatTexture(TInt width, TInt height, EEgRenderTextureFormat formatCd, TBool optimize, TInt mipLevel){
   return NULL;
}

//============================================================
// <T>创建空间纹理。</T>
//============================================================
FEgRenderCubeTexture* FEfRenderDevice::CreateCubeTexture(TInt size, EEgRenderTextureFormat formatCd, TBool optimize, TInt mipLevel){
   return NULL;
}

//============================================================
// <T>清空内容。</T>
//============================================================
void FEfRenderDevice::Clear(TFloat red, TFloat green, TFloat blue, TFloat alpha, TFloat depth){
   _context3d->clear(red, green, blue, alpha, depth, 0, 0xFFFFFFFF);
}

//============================================================
// <T>设置背景缓冲。</T>
//============================================================
void FEfRenderDevice::SetBackBuffer(TInt width, TInt height, TInt antiAlias, TBool depthed){
   _context3d->configureBackBuffer(width, height, antiAlias, depthed, false);
}

//============================================================
// <T>设置剪裁方式。</T>
//============================================================
void FEfRenderDevice::SetCulling(EEgRenderCull cullCd){
}

//============================================================
// <T>设置合成方式。</T>
//============================================================
void FEfRenderDevice::SetBlendFactors(TCharC* sourceFactor, TCharC* destinationFactor){
}

//============================================================
// <T>设置有效区域。</T>
//============================================================
void FEfRenderDevice::SetScissorRectangle(TFloat left, TFloat top, TFloat width, TFloat height){
}

//============================================================
// <T>绑定顶点缓冲。</T>
//============================================================
void FEfRenderDevice::BindVertexBuffer(TInt slot, FEgRenderVertexBuffer* pVertexBuffer, TInt offset, EEgRenderVertexFormat formatCd){
}

//============================================================
// <T>绑定纹理。</T>
//============================================================
void FEfRenderDevice::BindTexture(TInt slot, FEgRenderTexture* pTexture){
}

//============================================================
// <T>绘制三角形。</T>
//============================================================
void FEfRenderDevice::DrawTriangles(FEgRenderIndexBuffer* pIndexBuffer, TInt offset, TInt count){
}

//============================================================
// <T>显示画面。</T>
//============================================================
void FEfRenderDevice::Present(){
   _context3d->present();
}

MO_NAMESPACE_END
