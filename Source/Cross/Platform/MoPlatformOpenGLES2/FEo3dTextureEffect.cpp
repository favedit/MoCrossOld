#include "MoPoe2Technique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>顶点代码。</T>
//============================================================
static TCharC* SourceVertexShader =
      "uniform mat4 vc_mvp_matrix; \n"
      "attribute vec4 va_position; \n"
      "attribute vec4 va_color; \n"
      "attribute vec2 va_coord; \n"
      //"attribute vec3 va_normal; \n"
      //"attribute vec3 va_binormal; \n"
      //"attribute vec3 va_tangent; \n"
      "varying vec4 v_color; \n"
      "varying vec2 v_coord; \n"
      //"varying vec3 v_normal; \n"
      "void main(){ \n"
      "   gl_Position = va_position * vc_mvp_matrix; \n"
      "   v_color = va_color; \n"
      "   v_coord = va_coord; \n"
      //"   v_normal = normalize(va_normal * (mat3)vc_mvp_matrix); \n"
      "} \n";

//============================================================
// <T>像素代码。</T>
//============================================================
static TCharC* SourceFragmentShader =
#ifdef _MO_ANDROID
      "precision mediump float; \n"
#endif // _MO_ANDROID
      "varying vec4 v_color; \n"
      "varying vec2 v_coord; \n"
      //"varying vec3 v_normal; \n"
      //"uniform vec3 fc_light_direction; \n"
      "uniform sampler2D fs_color; \n"
      "void main(){ \n"
      "   vec4 color = texture2D(fs_color, v_coord); \n"
      //"   if(color.a < 0.1){ discard; } \n"
      //"   vec3 normalRate = dot(v_normal, -fc_light_direction); \n"
      //"   vec4 normalColor = color * vec4(normalRate, 1) * 0.4; \n"
      //"   vec4 acolor = color * v_color * 0.6; \n"
      //"   gl_FragColor = acolor + normalColor; \n"
      "   gl_FragColor = color; \n"
      "} \n";

//============================================================
// <T>构造显示渲染技术。</T>
//============================================================
FPoe23dTextureEffect::FPoe23dTextureEffect(){
}

//============================================================
// <T>析构显示渲染技术。</T>
//============================================================
FPoe23dTextureEffect::~FPoe23dTextureEffect(){
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FPoe23dTextureEffect::Setup(){
 //  FScreenDevice* pScreenDevice = RDeviceManager::Instance().ScreenDevice();
 //  FRenderDevice* pRenderDevice = RDeviceManager::Instance().RenderDevice();
 //  //............................................................
 //  // 设置MVP矩阵
 //  SIntSize2& screenSize = pScreenDevice->Size();
 //  Resize(screenSize.width, screenSize.height);
 //  //............................................................
 //  // 创建程序
 //  FPoe2RenderProgram* pProgram = (FPoe2RenderProgram*)pRenderDevice->CreateProgrom();
 //  pProgram->Setup();
 //  pProgram->VertexShader()->Compile(SourceVertexShader);
 //  pProgram->FragmentShader()->Compile(SourceFragmentShader);

 //  pProgram->Build();
 //  GLuint progromId = pProgram->ProgramId();
 //  // 绑定属性位置
 //  glBindAttribLocation(progromId, ERenderVertexBuffer_Position, "va_position");
 //  glBindAttribLocation(progromId, ERenderVertexBuffer_Color, "va_color");
 //  glBindAttribLocation(progromId, ERenderVertexBuffer_Coord, "va_coord");
 //  glBindAttribLocation(progromId, ERenderVertexBuffer_Normal, "va_normal");
 //  glBindAttribLocation(progromId, ERenderVertexBuffer_Binormal, "va_binormal");
 //  glBindAttribLocation(progromId, ERenderVertexBuffer_Tangent, "va_tangent");
 //  pProgram->Link();
 //  // 绑定常量位置
	//_effectConsts[EEffectVertexConst_MvpMat4] = glGetUniformLocation(progromId, "vc_mvp_matrix");
 //  _effectConsts[EEffectConst_Fragment_LightDirection] = glGetUniformLocation(progromId, "fc_light_direction");
 //  // 绑定属性位置
 //  _effectSamplers[EEffectSampler_Color] = glGetUniformLocation(progromId, "fs_color");
 //  // 程序关联处理
 //  _pProgram = pProgram;
   return ESuccess;
}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FPoe23dTextureEffect::Resize(TInt width, TInt height){
   //// 计算MVP矩阵
   //_mvpMatrix.tx = -1.0f;
   //_mvpMatrix.ty = 1.0f;
   ////_mvpMatrix.rx = MO_PI_FLOAT;
   //_mvpMatrix.sx = 2.0f / (TFloat)width;
   //_mvpMatrix.sy = 2.0f / (TFloat)height;
   //_mvpMatrix.UpdateForce();
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FPoe23dTextureEffect::Draw(FRenderable* pRenderable){
   // 获得设备
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // FPoe2RenderProgram* pProgram = (FPoe2RenderProgram*)pRenderDevice->CreateProgrom();
   //............................................................
   // 获得数据
   //FRenderable3d* pTemplateRenderable = (FRenderable3d*)pRenderable;
   //SFloatMatrix3d& renderableMatrix = pTemplateRenderable->Matrix();
   ////............................................................
   //// 模型矩阵
   //SFloatMatrix3d modelMatrix;
   //FAnimation3d* pAnimation = pTemplateRenderable->FindAnimation();
   //TTimeTick currentTick = pAnimation->CurrentTick();
   //FRs3dTrack* pTrack = pTemplateRenderable->FindTrack();
   //if(!pTrack->Frames()->IsEmpty()){
   //   SRs3dFrameInfo info;
   //   pTrack->CalculateFrameInfo(info, currentTick);
   //   info.Update();
   //   modelMatrix.Append(pTrack->MatrixInvert());
   //   modelMatrix.Append(info.matrix);
   //}
   //// 计算MVP矩阵
   //SFloatMatrix3d matrixMvp;
   //matrixMvp.Assign(modelMatrix);
   //matrixMvp.Append(renderableMatrix);
   //matrixMvp.Append(_vpMatrix);
   //............................................................
   // 设置常量
   //pRenderDevice->BindConstMatrix3d(ERenderShader_Vertex, _effectConsts[EEffectVertexConst_MvpMat4], matrixMvp);
   // pRenderDevice->BindConstData(ERenderShader_Vertex, 
   //............................................................
   // 设定顶点流
   //FRs3dVertexBuffer* pVertexBuffer = pTemplateRenderable->FindVertexBuffer();
   //MO_CHECK(pVertexBuffer, return ENull);
   ////FRs3dVertexStreamVector* pStreams = pVertexBuffer->Streams();
   ////TInt vertexBufferCount = pStreams->Count();
   ////for(TInt n = 0; n < vertexBufferCount; n++){
   ////   FRs3dVertexStream* pStream = pStreams->Get(n);
   ////   FRenderVertexBuffer* pBuffer = pStream->VertexBuffer();
   ////   ERenderVertexBuffer typeCd = pStream->TypeCd();
   ////   ERenderVertexFormat formatCd = pStream->FormatCd();
   ////   pRenderDevice->BindVertexBuffer(typeCd, pBuffer, 0, formatCd);
   ////}
   ////............................................................
   //// 设置纹理
   //FMaterial3d* pMaterial = (FMaterial3d*)pTemplateRenderable->Material();
   //if(!pMaterial->MaterialTextures()->IsEmpty()){
   //   FMaterialTexture3d* pTexture = pMaterial->MaterialTextures()->First();
   //   FBitmap* pBitmap = pTexture->Bitmap();
   //   //pRenderDevice->BindTexture(_effectSamplers[EEffectSampler_Color], pBitmap->Texture());
   //}
   ////............................................................
   //// 设置索引流，绘制三角形
   //FRs3dIndexBuffer* pRsIndexBuffer = pTemplateRenderable->FindIndexBuffer();
   //FRenderIndexBuffer* pIndexBuffer = pRsIndexBuffer->IndexBuffer();
   //pRenderDevice->DrawTriangles(pIndexBuffer, 0, 3 * pRsIndexBuffer->Count());
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FPoe23dTextureEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   //MO_CHECK(pRegion, return ENull);
   //// 计算变换矩阵
   //FCamera3d* pCamera = (FCamera3d*)pRegion->Camera();
   //_vpMatrix.Assign(pCamera->Matrix());
   //FProjection3d* pProjection = (FProjection3d*)pRegion->Projection();
   //_vpMatrix.Append(pProjection->Matrix());
   //FLightDirectional3d* pLight = (FLightDirectional3d*)pRegion->Light();
   ////pLight->Direction();
   //// 设置设备状态
   //FRenderDevice* pRenderDevice = RDeviceManager::Instance().RenderDevice();
   //pRenderDevice->SetDepthMode(ETrue, ERenderDepthMode_Less);
   //// pRenderDevice->SetCullingMode(EFalse);
   //pRenderDevice->SetBlendFactors(ETrue, ERenderBlendMode_SourceAlpha, ERenderBlendMode_OneMinusSourceAlpha);
   //pRenderDevice->SetProgram(_pProgram);
   //TFloat direction[3];
   //pLight->Direction().Normalize();
   //direction[0] = pLight->Direction().x;
   //direction[1] = pLight->Direction().y;
   //direction[2] = pLight->Direction().z;
   //if(_effectConsts[EEffectConst_Fragment_LightDirection] != -1){
   //   pRenderDevice->BindConstData(
   //         ERenderShader_Fragment,
   //         _effectConsts[EEffectConst_Fragment_LightDirection],
   //         ERenderShaderParameterFormat_Float3, (TAnyC*)direction, sizeof(direction));
   //}
   ////............................................................
   //// 根据渲染类型进行分组
   //FRenderableCollection* pRenderables = pRegion->Renderables();
   //TInt offsetEnd = offset + count;
   //for(TInt n = offset; n < offsetEnd; n++){
   //   FRenderable* pRenderable = pRenderables->Get(n);
   //   Draw(pRenderable);
   //}
   ////............................................................
   //// 关闭程序
   //pRenderDevice->SetProgram(NULL);
   return ESuccess;
}

MO_NAMESPACE_END
