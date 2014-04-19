#include "MoEoTechnique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>顶点代码。</T>
//============================================================
static TCharC* SourceVertexShader =
      "uniform mat4 c_mvp_matrix; \n"
      "attribute vec4 a_position; \n"
      "attribute vec4 a_color; \n"
      "varying vec4 v_color; \n"
      "void main(){ \n"
      "   gl_Position = a_position * c_mvp_matrix; \n"
      "   v_color = a_color; \n"
      "} \n";

//============================================================
// <T>像素代码。</T>
//============================================================
static TCharC* SourceFragmentShader =
#ifdef _MO_ANDROID
      "precision mediump float; \n"
#endif // _MO_ANDROID
      "varying vec4 v_color; \n"
      "void main(){ \n"
      "   gl_FragColor = v_color; \n"
      "} \n";

//============================================================
// <T>构造显示颜色渲染器。</T>
//============================================================
FEo2dColorEffect::FEo2dColorEffect(){
   _limitCount = 16384;
   _count = 0;
   MO_CLEAR(_pVertexPositionBuffer);
   _pPositionData = MO_CREATE(FDataStream);
   MO_CLEAR(_pVertexColorBuffer);
   _pColorData = MO_CREATE(FDataStream);
   MO_CLEAR(_pIndexBuffer);
}

//============================================================
// <T>析构显示颜色渲染器。</T>
//============================================================
FEo2dColorEffect::~FEo2dColorEffect(){
   MO_DELETE(_pVertexPositionBuffer);
   MO_DELETE(_pPositionData);
   MO_DELETE(_pVertexColorBuffer);
   MO_DELETE(_pColorData);
   MO_DELETE(_pIndexBuffer);
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FEo2dColorEffect::Setup(){
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 创建程序
   FEoRenderProgram* pProgram = (FEoRenderProgram*)pRenderDevice->CreateProgrom();
   pProgram->Setup();
   pProgram->VertexShader()->Compile(SourceVertexShader);
   pProgram->FragmentShader()->Compile(SourceFragmentShader);
   pProgram->Build();
   // 绑定常量位置
   GLuint progromId = pProgram->ProgramId();
	//_effectConsts[EEffectVertexConst_MvpMat4] = glGetUniformLocation(progromId, "c_mvp_matrix");
   // 绑定属性位置
   glBindAttribLocation(progromId, EEffectVertexAttribute_Position, "a_position");
   glBindAttribLocation(progromId, EEffectVertexAttribute_Color, "a_color");
   // 程序关联处理
   _program = pProgram;
   // 创建顶点流
   for(TInt n = 0; n < _limitCount; n++){
      // 写入点1数据
      _pPositionData->WriteFloat4(0.0f, 0.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      // 写入点2数据
      _pPositionData->WriteFloat4(1.0f, 0.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      // 写入点3数据
      _pPositionData->WriteFloat4(1.0f, 1.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
      // 写入点4数据
      _pPositionData->WriteFloat4(0.0f, 1.0f, 0.0f, 1.0f);
      _pColorData->WriteByte4(255, 255, 255, 255);
   }
   // 上传顶点位置
   _pVertexPositionBuffer = (FEoRenderVertexBuffer*)pRenderDevice->CreateVertexBuffer();
   _pVertexPositionBuffer->SetCount(4 * _limitCount);
   _pVertexPositionBuffer->SetStride(4 * 4);
   _pVertexPositionBuffer->Setup();
   _pVertexPositionBuffer->Upload(_pPositionData->Memory(), _pPositionData->Length());
   // 上传顶点颜色
   _pVertexColorBuffer = (FEoRenderVertexBuffer*)pRenderDevice->CreateVertexBuffer();
   _pVertexColorBuffer->SetCount(4 * _limitCount);
   _pVertexColorBuffer->SetStride(1 * 4);
   _pVertexColorBuffer->Setup();
   _pVertexColorBuffer->Upload(_pColorData->Memory(), _pColorData->Length());
   // 创建索引流
   _pIndexBuffer = (FEoRenderIndexBuffer*)pRenderDevice->CreateIndexBuffer();
   _pIndexBuffer->SetCount(6 * _limitCount);
   _pIndexBuffer->Setup();
   FByteStream* pIndexData = MO_CREATE(FByteStream);
   for(TInt n = 0; n < _limitCount; n++){
      TInt index = 4 * n;
      // 第一个三角形
      pIndexData->WriteUint16(index + 0);
      pIndexData->WriteUint16(index + 2);
      pIndexData->WriteUint16(index + 1);
      // 第二个三角形
      pIndexData->WriteUint16(index + 0);
      pIndexData->WriteUint16(index + 3);
      pIndexData->WriteUint16(index + 2);
   }
   _pIndexBuffer->Upload(pIndexData->Memory(), pIndexData->Length());
   MO_DELETE(pIndexData);
   return ESuccess;
}

//============================================================
// <T>更新开始处理。</T>
//============================================================
TResult FEo2dColorEffect::UpdateBegin(){
   _limitCount = 0;
   _count = 0;
   _pPositionData->Reset();
   _pColorData->Reset();
   return ESuccess;
}

//============================================================
// <T>写入可渲染对象。</T>
//
// @param pRenderable 可渲染对象
//============================================================
TResult FEo2dColorEffect::WriteRenderable(FRenderable* pRenderable){
   // 检查限制
   if(_count >= _limitCount){
      return EFailure;
   }
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
   }
   // 修正计数器
   _count += count;
   return ESuccess;
}

//============================================================
// <T>更新结束处理。</T>
//============================================================
TResult FEo2dColorEffect::UpdateEnd(){
   // 上传顶点数据
   _pVertexPositionBuffer->Upload(_pPositionData->MemoryC(), _pPositionData->Position());
   _pVertexColorBuffer->Upload(_pColorData->MemoryC(), _pColorData->Position());
   return ESuccess;
}

//============================================================
// <T>变更大小。</T>
//
// @return 处理结果
//============================================================
TResult FEo2dColorEffect::Resize(TInt width, TInt height){
   // 计算MVP矩阵
   _mvpMatrix.tx = -1;
   _mvpMatrix.ty = 1;
   _mvpMatrix.rx = MO_PI_FLOAT;
   _mvpMatrix.sx = 2.0f / (TFloat)width;
   _mvpMatrix.sy = 2.0f / (TFloat)height;
   _mvpMatrix.Update();
   return ESuccess;
}

//============================================================
// <T>绘制处理。</T>
//============================================================
TResult FEo2dColorEffect::Draw(){
   //// 获得引用
   //FRenderDevice* pRenderDevice = RDeviceManager::Instance().RenderDevice();
   //// 设定程序
   //FEoRenderProgram* pProgram = (FEoRenderProgram*)_program;
   //pRenderDevice->SetProgram(_program);
   //// 设置常量
   //pRenderDevice->BindConstMatrix3d(ERenderShader_Vertex, _effectConsts[EEffectVertexConst_MvpMatrix], _mvpMatrix);
   //// 设定顶点流
   //pRenderDevice->BindVertexBuffer(EEffectVertexAttribute_Position, _pVertexPositionBuffer, 0, ERenderVertexFormat_Float4);
   //pRenderDevice->BindVertexBuffer(EEffectVertexAttribute_Color, _pVertexColorBuffer, 0, ERenderVertexFormat_Byte4);
   //// 设置索引流，绘制三角形
   //pRenderDevice->DrawTriangles(_pIndexBuffer, 0, 6 * _count);
   return ESuccess;
}

MO_NAMESPACE_END
