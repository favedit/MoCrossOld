#include "MoPoe2Technique.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造显示渲染技术。</T>
//============================================================
FPoe23dSkeleton4Program::FPoe23dSkeleton4Program(){
   // 清空数据
   _boneCount = 0;
   _weightCount = 0;
   // 清空常量位置
	_vertexConstMvpMatrix = -1;
   _vertexConstBoneMatrixs = -1;
   // 清空属性位置
   _attributePosition = -1;
   _attributeColor = -1;
   _attributeCoord = -1;
   _attributeBoneIndex = -1;
   _attributeBoneWeight = -1;
   // 绑定纹理位置
   _samplerColor = -1;
}

//============================================================
// <T>析构显示渲染技术。</T>
//============================================================
FPoe23dSkeleton4Program::~FPoe23dSkeleton4Program(){
}

//============================================================
// <T>生成顶点渲染程序。</T>
//
// @param pSource 渲染来源
// @return 处理结果
//============================================================
TResult FPoe23dSkeleton4Program::MakeVertexSource(FRenderSource* pSource){
   pSource->Clear();
   // 常量定义
   pSource->AppendSource("uniform mat4 vc_mvp_matrix;");
   pSource->AppendSource("uniform mat4 vc_bone_matrixs[20];");
   // 属性定义
   pSource->AppendSource("attribute vec4 va_position;");
   pSource->AppendSource("attribute vec4 va_color;");
   pSource->AppendSource("attribute vec2 va_coord;");
   pSource->AppendSource("attribute vec4 va_bone_index;");
   pSource->AppendSource("attribute vec4 va_bone_weight;");
   // 插值定义
   pSource->AppendSource("varying vec4 v_color;");
   pSource->AppendSource("varying vec2 v_coord;");
   // 程序定义
   pSource->AppendSource("void main(){");
   pSource->AppendSource("   vec4 position = vec4(0, 0, 0, 0);");
   pSource->AppendSource("   position += va_position * vc_bone_matrixs[int(va_bone_index.x)] * va_bone_weight.x;");
   pSource->AppendSource("   position += va_position * vc_bone_matrixs[int(va_bone_index.y)] * va_bone_weight.y;");
   pSource->AppendSource("   position += va_position * vc_bone_matrixs[int(va_bone_index.z)] * va_bone_weight.z;");
   pSource->AppendSource("   position += va_position * vc_bone_matrixs[int(va_bone_index.w)] * va_bone_weight.w;");
   pSource->AppendSource("   gl_Position = position * vc_mvp_matrix;");
   pSource->AppendSource("   v_color = va_color;");
   pSource->AppendSource("   v_coord = va_coord;");
   pSource->AppendSource("}");
   return ESuccess;
}

//============================================================
// <T>生成像素渲染程序。</T>
//
// @param pSource 渲染来源
// @return 处理结果
//============================================================
TResult FPoe23dSkeleton4Program::MakeFragmentSource(FRenderSource* pSource){
   pSource->Clear();
   // 平台定义
#ifdef _MO_ANDROID
   pSource->AppendSource("precision mediump float;");
#endif // _MO_ANDROID
   // 插值定义
   pSource->AppendSource("varying vec4 v_color;");
   pSource->AppendSource("varying vec2 v_coord;");
   // 取样器定义
   pSource->AppendSource("uniform sampler2D fs_color;");
   // 程序定义
   pSource->AppendSource("void main(){");
   pSource->AppendSource("   vec4 color = texture2D(fs_color, v_coord);");
   pSource->AppendSource("   if(color.a < 0.1){ discard; }");
   //pSource->AppendSource("   gl_FragColor = color * v_color;");
   pSource->AppendSource("   gl_FragColor = vec4(1, 1, 1, 1);");
   pSource->AppendSource("}");
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FPoe23dSkeleton4Program::Build(){
 //  FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
 //  //............................................................
 //  // 创建程序
 //  MakeVertexSource(_pVertexShader->Source());
 //  _pVertexShader->Compile(_pVertexShader->Source()->MemoryC());
 //  MakeFragmentSource(_pFragmentShader->Source());
 //  _pFragmentShader->Compile(_pFragmentShader->Source()->MemoryC());
 //  FPoe2RenderProgram::Build();
 //  // 绑定常量位置
	//_vertexConstMvpMatrix = glGetUniformLocation(_programId, "vc_mvp_matrix");
 //  _vertexConstBoneMatrixs = glGetUniformLocation(_programId, "vc_bone_matrixs");
 //  // 绑定属性位置
 //  _attributePosition = glGetAttribLocation(_programId, "va_position");
 //  _attributeColor = glGetAttribLocation(_programId, "va_color");
 //  _attributeCoord = glGetAttribLocation(_programId, "va_coord");
 //  _attributeBoneIndex = glGetAttribLocation(_programId, "va_bone_index");
 //  _attributeBoneWeight = glGetAttribLocation(_programId, "va_bone_weight");
 //  // 清空纹理位置
 //  _samplerColor = glGetUniformLocation(_programId, "fs_color");
   return ESuccess;
}

MO_NAMESPACE_END
