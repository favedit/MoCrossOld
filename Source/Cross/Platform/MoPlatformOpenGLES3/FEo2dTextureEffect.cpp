#include "MoEoTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>顶点代码。</T>
//============================================================
static TCharC* SourceVertexShader =
      "uniform mat4 vc_mvp_matrix; \n"
      "attribute vec4 va_position; \n"
      "attribute vec4 va_color; \n"
      "attribute vec2 va_coord; \n"
      "varying vec4 v_color; \n"
      "varying vec2 v_coord; \n"
      "void main(){ \n"
      "   gl_Position = va_position * vc_mvp_matrix; \n"
      "   v_color = va_color; \n"
      "   v_coord = va_coord; \n"
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
      "uniform sampler2D fs_color; \n"
      "void main(){ \n"
      "   gl_FragColor = v_color * texture2D(fs_color, v_coord); \n"
      "} \n";

//============================================================
// <T>构造显示渲染技术。</T>
//============================================================
FEo2dTextureEffect::FEo2dTextureEffect(){
   _limitCount = 16384;
   _count = 0;
   MO_CLEAR(_pVertexPositionBuffer);
   _pPositionData = MO_CREATE(FDataStream);
   MO_CLEAR(_pVertexColorBuffer);
   _pColorData = MO_CREATE(FDataStream);
   MO_CLEAR(_pVertexCoordBuffer);
   _pCoordData = MO_CREATE(FDataStream);
   MO_CLEAR(_pIndexBuffer);
   // 清空内容
   for(TInt n = 0; n < EEffectVertexAttribute_Count; n++){
      _effectAttributes[n] = -1;
   }
   for(TInt n = 0; n < EEffectSampler_Count; n++){
      _effectSamplers[n] = -1;
   }
}

//============================================================
// <T>析构显示渲染技术。</T>
//============================================================
FEo2dTextureEffect::~FEo2dTextureEffect(){
   MO_DELETE(_pVertexPositionBuffer);
   MO_DELETE(_pPositionData);
   MO_DELETE(_pVertexColorBuffer);
   MO_DELETE(_pColorData);
   MO_DELETE(_pVertexCoordBuffer);
   MO_DELETE(_pCoordData);
   MO_DELETE(_pIndexBuffer);
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FEo2dTextureEffect::Setup(){
   FScreenDevice* pScreenDevice = RDeviceManager::Instance().Find<FScreenDevice>();
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   //............................................................
   // 设置MVP矩阵
   SIntSize2& screenSize = pScreenDevice->Size();
   _mvpMatrix.tx = -1;
   _mvpMatrix.ty = 1;
   _mvpMatrix.rx = MO_PI_FLOAT;
   _mvpMatrix.sx = 2.0f / (TFloat)screenSize.width;
   _mvpMatrix.sy = 2.0f / (TFloat)screenSize.height;
   _mvpMatrix.UpdateForce();
   //............................................................
   // 创建程序
   FEoRenderProgram* pProgram = (FEoRenderProgram*)pRenderDevice->CreateProgrom();
   pProgram->Setup();
   pProgram->VertexShader()->Compile(SourceVertexShader);
   pProgram->FragmentShader()->Compile(SourceFragmentShader);
   pProgram->Build();
   // 绑定常量位置
   GLuint progromId = pProgram->ProgramId();
	//_effectConsts[EEffectVertexConst_MvpMat4] = glGetUniformLocation(progromId, "vc_mvp_matrix");
   // 绑定属性位置
   glBindAttribLocation(progromId, EEffectVertexAttribute_Position, "va_position");
   glBindAttribLocation(progromId, EEffectVertexAttribute_Color, "va_color");
   glBindAttribLocation(progromId, EEffectVertexAttribute_Coord, "va_coord");
   // 绑定属性位置
   _effectSamplers[EEffectSampler_Diffuse] = glGetUniformLocation(progromId, "fs_color");
   // 程序关联处理
   _program = pProgram;
   //............................................................
   // 创建顶点流
   TInt vertexCount = 4 * _limitCount;
   for(TInt n = 0; n < _limitCount; n++){
      // 写入点1数据
      _pPositionData->WriteFloat4(0.0f, 0.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      _pCoordData->WriteFloat2(0.0f, 0.0f);
      // 写入点2数据
      _pPositionData->WriteFloat4(1.0f, 0.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      _pCoordData->WriteFloat2(1.0f, 0.0f);
      // 写入点3数据
      _pPositionData->WriteFloat4(1.0f, 1.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      _pCoordData->WriteFloat2(1.0f, 1.0f);
      // 写入点4数据
      _pPositionData->WriteFloat4(0.0f, 1.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      _pCoordData->WriteFloat2(0.0f, 1.0f);
   }
   // 上传顶点位置
   _pVertexPositionBuffer = (FEoRenderVertexBuffer*)pRenderDevice->CreateVertexBuffer();
   _pVertexPositionBuffer->SetCount(vertexCount);
   _pVertexPositionBuffer->SetStride(4 * 4);
   _pVertexPositionBuffer->Setup();
   _pVertexPositionBuffer->Upload(_pPositionData->Memory(), _pPositionData->Length());
   // 上传顶点颜色
   _pVertexColorBuffer = (FEoRenderVertexBuffer*)pRenderDevice->CreateVertexBuffer();
   _pVertexColorBuffer->SetCount(vertexCount);
   _pVertexColorBuffer->SetStride(1 * 4);
   _pVertexColorBuffer->Setup();
   _pVertexColorBuffer->Upload(_pColorData->Memory(), _pColorData->Length());
   // 上传顶点纹理坐标
   _pVertexCoordBuffer = (FEoRenderVertexBuffer*)pRenderDevice->CreateVertexBuffer();
   _pVertexCoordBuffer->SetCount(vertexCount);
   _pVertexCoordBuffer->SetStride(4 * 2);
   _pVertexCoordBuffer->Setup();
   _pVertexCoordBuffer->Upload(_pCoordData->Memory(), _pCoordData->Length());
   //............................................................
   // 创建索引流
   TInt vertexIndex = 0;
   _pIndexBuffer = (FEoRenderIndexBuffer*)pRenderDevice->CreateIndexBuffer();
   _pIndexBuffer->SetCount(6 * _limitCount);
   _pIndexBuffer->Setup();
   FByteStream* pIndexData = MO_CREATE(FByteStream);
   for(TInt n = 0; n < _limitCount; n++){
      // 第一个三角形
      pIndexData->WriteUint16(vertexIndex + 0);
      pIndexData->WriteUint16(vertexIndex + 2);
      pIndexData->WriteUint16(vertexIndex + 1);
      // 第二个三角形
      pIndexData->WriteUint16(vertexIndex + 0);
      pIndexData->WriteUint16(vertexIndex + 3);
      pIndexData->WriteUint16(vertexIndex + 2);
      // 计算面点索引
      vertexIndex += 4;
   }
   _pIndexBuffer->Upload(pIndexData->Memory(), pIndexData->Length());
   MO_DELETE(pIndexData);
   return ESuccess;
}

//============================================================
// <T>更新开始处理。</T>
//============================================================
TResult FEo2dTextureEffect::UpdateBegin(){
   _count = 0;
   _pPositionData->Reset();
   _pColorData->Reset();
   _pCoordData->Reset();
   return ESuccess;
}

//============================================================
// <T>写入可渲染对象。</T>
//
// @param pRenderable 可渲染对象
//============================================================
TResult FEo2dTextureEffect::WriteRenderable(FRenderable* pRenderable){
   // 计算数据
   SRenderable renderable;
   //pRenderable->CalculateRenderable(renderable);
   TInt count = renderable.items.Count();
   for(TInt n = 0; n < count; n++){
      SRenderableItem& item = renderable.items.Get(n);
      // 计算位置
      SFloatPoint3& location = item.location;
      SFloatSize3& size = item.size;
      SFloatVector3& rotation = item.rotation;
      SFloatCoord& coord = item.coord;
      SFloatMatrix3d& matrix = item.matrix;
      // 计算坐标
      SFloatPoint3 p1 = matrix.TransformPoint3(0, 0, 0);
      SFloatPoint3 p2 = matrix.TransformPoint3(size.width, 0, 0);
      SFloatPoint3 p3 = matrix.TransformPoint3(size.width, size.height, 0);
      SFloatPoint3 p4 = matrix.TransformPoint3(0, size.height, 0);
      // 输出位置
      _pPositionData->WriteFloat4(location.x + p1.x, location.y + p1.y, location.z, 1.0f);
      _pPositionData->WriteFloat4(location.x + p2.x, location.y + p2.y, location.z, 1.0f);
      _pPositionData->WriteFloat4(location.x + p3.x, location.y + p3.y, location.z, 1.0f);
      _pPositionData->WriteFloat4(location.x + p4.x, location.y + p4.y, location.z, 1.0f);
      // 输出颜色
      SFloatColor4& groundColor = item.groundColor;
      for(TInt n = 0; n < 4; n++){
         _pColorData->WriteInt8((TByte)(255 * groundColor.red));
         _pColorData->WriteInt8((TByte)(255 * groundColor.green));
         _pColorData->WriteInt8((TByte)(255 * groundColor.blue));
         _pColorData->WriteInt8((TByte)(255 * groundColor.alpha));
      }
      // 输出纹理
      _pCoordData->WriteFloat2(coord.x1, coord.y1);
      _pCoordData->WriteFloat2(coord.x2, coord.y1);
      _pCoordData->WriteFloat2(coord.x2, coord.y2);
      _pCoordData->WriteFloat2(coord.x1, coord.y2);
   }
   // 修正计数器
   _count += count;
   return ESuccess;
}

//============================================================
// <T>更新结束处理。</T>
//============================================================
TResult FEo2dTextureEffect::UpdateEnd(){
   // 上传顶点数据
   _pVertexPositionBuffer->Upload(_pPositionData->MemoryC(), _pPositionData->Position());
   _pVertexColorBuffer->Upload(_pColorData->MemoryC(), _pColorData->Position());
   _pVertexCoordBuffer->Upload(_pCoordData->MemoryC(), _pCoordData->Position());
   return ESuccess;
}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FEo2dTextureEffect::Resize(TInt width, TInt height){
   // 计算MVP矩阵
   _mvpMatrix.tx = -1;
   _mvpMatrix.ty = 1;
   _mvpMatrix.rx = MO_PI_FLOAT;
   _mvpMatrix.sx = 2.0f / (TFloat)width;
   _mvpMatrix.sy = 2.0f / (TFloat)height;
   _mvpMatrix.UpdateForce();
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FEo2dTextureEffect::Draw(){
   //// 设置设备状态
   //FRenderDevice* pRenderDevice = RDeviceManager::Instance().RenderDevice();
   //pRenderDevice->SetDepthMode(_pMaterial->OptionDepth());
   //pRenderDevice->SetBlendFactors(_pMaterial->OptionAlpha(), _pMaterial->BlendSourceMode(), _pMaterial->BlendTargetMode());
   ////............................................................
   //// 设定程序
   //pRenderDevice->SetProgram(_program);
   //// 设置常量
   //pRenderDevice->BindConstMatrix3d(ERenderShader_Vertex, _effectConsts[EEffectVertexConst_MvpMatrix], _mvpMatrix);
   //// 设定顶点流
   //pRenderDevice->BindVertexBuffer(EEffectVertexAttribute_Position, _pVertexPositionBuffer, 0, ERenderVertexFormat_Float4);
   //pRenderDevice->BindVertexBuffer(EEffectVertexAttribute_Color, _pVertexColorBuffer, 0, ERenderVertexFormat_ByteNormal4);
   //pRenderDevice->BindVertexBuffer(EEffectVertexAttribute_Coord, _pVertexCoordBuffer, 0, ERenderVertexFormat_Float2);
   ////............................................................
   //// 设置纹理
   //if(_pMaterial->HasTexture()){
   //   FTextureCollection* pTextures = _pMaterial->Textures();
   //   TInt count = pTextures->Count();
   //   for(TInt n = 0; n < count; n++){
   //      // 获得纹理
   //      ITexture* pTexture = pTextures->Get(n);
   //      FRenderTexture* pRenderTexture = pTexture->RenderTexture();
   //      // 绑定纹理
   //      ERenderSampler slotCd = pRenderTexture->SlotCd();
   //      if(slotCd == ERenderSampler_Diffuse){
   //         pRenderDevice->BindTexture(_effectSamplers[EEffectSampler_Color], pRenderTexture);
   //      }
   //   }
   //}
   ////............................................................
   //// 设置索引流，绘制三角形
   //pRenderDevice->DrawTriangles(_pIndexBuffer, 0, 6 * _count);
   return ESuccess;
}

MO_NAMESPACE_END
