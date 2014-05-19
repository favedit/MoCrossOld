#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderParameter, FRenderObject);

//============================================================
// <T>构造渲染器参数。</T>
//============================================================
FRenderParameter::FRenderParameter(){
   _code = -1;
   _shaderCd = ERenderShader_Unknown;
   _formatCd = ERenderParameterFormat_Unknown;
   _statusUsed = EFalse;
   _slot = -1;
   _size = 0;
   MO_CLEAR(_pShader);
}

//============================================================
// <T>析构渲染器参数。</T>
//============================================================
FRenderParameter::~FRenderParameter(){
}

//============================================================
// <T>获得数据内容。</T>
//
// @param pData 数据
// @param capacity 容量
// @return 处理结果
//============================================================
TResult FRenderParameter::Get(TAny* pData, TInt capacity){
   MO_FATAL_UNSUPPORT();
   return EUnsupport;
}

//============================================================
// <T>设置数据内容。</T>
//
// @param pData 数据
// @param length 长度
// @return 处理结果
//============================================================
TResult FRenderParameter::Set(TAny* pData, TInt length){
   MO_CHECK(_buffer, return ENull);
   _buffer->Set(_slot, pData, length);
   return EUnsupport;
}

//============================================================
// <T>设置3维浮点数的数据内容。</T>
//
// @parma matrix 矩阵
// @parma count 个数
// @parma transpose 是否转置
// @return 处理结果
//============================================================
TResult FRenderParameter::SetFloat3(TFloat x, TFloat y, TFloat z){
   // 设置数据
   TFloat data[3];
   data[0] = x;
   data[1] = y;
   data[2] = z;
   // 提交数据
   TResult resultCd = Set(data, sizeof(TFloat) * 3);
   return resultCd;
}

//============================================================
// <T>设置4维浮点数的数据内容。</T>
//
// @parma matrix 矩阵
// @parma count 个数
// @parma transpose 是否转置
// @return 处理结果
//============================================================
TResult FRenderParameter::SetFloat4(TFloat x, TFloat y, TFloat z, TFloat w){
   // 设置数据
   TFloat data[4];
   data[0] = x;
   data[1] = y;
   data[2] = z;
   data[3] = w;
   // 提交数据
   TResult resultCd = Set(data, sizeof(TFloat) * 4);
   return resultCd;
}

//============================================================
// <T>设置3x3矩阵的数据内容。</T>
//
// @parma matrix 矩阵
// @parma count 个数
// @parma transpose 是否转置
// @return 处理结果
//============================================================
TResult FRenderParameter::SetMatrix3x3(const SFloatMatrix3d* pMatrix, TInt count, TBool transpose){
   MO_CHECK(pMatrix, return ENull);
   MO_CHECK(count > 0, return EFailure);
   MO_CHECK(count <= MO_EG_CONST_MATRIX_MAX, return EFailure);
   // 生成数据
   TFloat data[9 * MO_EG_CONST_MATRIX_MAX];
   TFloat* pWriter = data;
   for(TInt n = 0; n < count; n++){
      const SFloatMatrix3d& matrix = pMatrix[n];
      if(transpose){
         *pWriter++ = matrix.data[0][0];
         *pWriter++ = matrix.data[1][0];
         *pWriter++ = matrix.data[2][0];
         *pWriter++ = matrix.data[0][1];
         *pWriter++ = matrix.data[1][1];
         *pWriter++ = matrix.data[2][1];
         *pWriter++ = matrix.data[0][2];
         *pWriter++ = matrix.data[1][2];
         *pWriter++ = matrix.data[2][2];
      }else{
         *pWriter++ = matrix.data[0][0];
         *pWriter++ = matrix.data[0][1];
         *pWriter++ = matrix.data[0][2];
         *pWriter++ = matrix.data[1][0];
         *pWriter++ = matrix.data[1][1];
         *pWriter++ = matrix.data[1][2];
         *pWriter++ = matrix.data[2][0];
         *pWriter++ = matrix.data[2][1];
         *pWriter++ = matrix.data[2][2];
      }
   }
   // 提交数据
   TResult resultCd = Set(data, sizeof(TFloat) * 9 * count);
   return resultCd;
}

//============================================================
// <T>设置4x3矩阵的数据内容。</T>
//
// @parma matrix 矩阵
// @parma count 个数
// @parma transpose 是否转置
// @return 处理结果
//============================================================
TResult FRenderParameter::SetMatrix4x3(const SFloatMatrix3d* pMatrix, TInt count, TBool transpose){
   MO_CHECK(pMatrix, return ENull);
   MO_CHECK(count > 0, return EFailure);
   MO_CHECK(count <= MO_EG_CONST_MATRIX_MAX, return EFailure);
   // 生成数据
   TFloat data[12 * MO_EG_CONST_MATRIX_MAX];
   TFloat* pWriter = data;
   for(TInt n = 0; n < count; n++){
      const SFloatMatrix3d& matrix = pMatrix[n];
      if(transpose){
         *pWriter++ = matrix.data[0][0];
         *pWriter++ = matrix.data[1][0];
         *pWriter++ = matrix.data[2][0];
         *pWriter++ = matrix.data[3][0];
         *pWriter++ = matrix.data[0][1];
         *pWriter++ = matrix.data[1][1];
         *pWriter++ = matrix.data[2][1];
         *pWriter++ = matrix.data[3][1];
         *pWriter++ = matrix.data[0][2];
         *pWriter++ = matrix.data[1][2];
         *pWriter++ = matrix.data[2][2];
         *pWriter++ = matrix.data[3][2];
      }else{
         *pWriter++ = matrix.data[0][0];
         *pWriter++ = matrix.data[0][1];
         *pWriter++ = matrix.data[0][2];
         *pWriter++ = matrix.data[0][3];
         *pWriter++ = matrix.data[1][0];
         *pWriter++ = matrix.data[1][1];
         *pWriter++ = matrix.data[1][2];
         *pWriter++ = matrix.data[1][3];
         *pWriter++ = matrix.data[2][0];
         *pWriter++ = matrix.data[2][1];
         *pWriter++ = matrix.data[2][2];
         *pWriter++ = matrix.data[2][3];
      }
   }
   // 提交数据
   TResult resultCd = Set(data, sizeof(TFloat) * 12 * count);
   return resultCd;
}

//============================================================
// <T>设置4x4矩阵的数据内容。</T>
//
// @parma matrix 矩阵
// @parma count 个数
// @parma transpose 是否转置
// @return 处理结果
//============================================================
TResult FRenderParameter::SetMatrix4x4(const SFloatMatrix3d* pMatrix, TInt count, TBool transpose){
   MO_CHECK(pMatrix, return ENull);
   MO_CHECK(count > 0, return EFailure);
   MO_CHECK(count <= MO_EG_CONST_MATRIX_MAX, return EFailure);
   // 生成数据
   TFloat data[16 * MO_EG_CONST_MATRIX_MAX];
   TFloat* pWriter = data;
   for(TInt n = 0; n < count; n++){
      const SFloatMatrix3d& matrix = pMatrix[n];
      if(transpose){
         *pWriter++ = matrix.data[0][0];
         *pWriter++ = matrix.data[1][0];
         *pWriter++ = matrix.data[2][0];
         *pWriter++ = matrix.data[3][0];
         *pWriter++ = matrix.data[0][1];
         *pWriter++ = matrix.data[1][1];
         *pWriter++ = matrix.data[2][1];
         *pWriter++ = matrix.data[3][1];
         *pWriter++ = matrix.data[0][2];
         *pWriter++ = matrix.data[1][2];
         *pWriter++ = matrix.data[2][2];
         *pWriter++ = matrix.data[3][2];
         *pWriter++ = matrix.data[0][3];
         *pWriter++ = matrix.data[1][3];
         *pWriter++ = matrix.data[2][3];
         *pWriter++ = matrix.data[3][3];
      }else{
         *pWriter++ = matrix.data[0][0];
         *pWriter++ = matrix.data[0][1];
         *pWriter++ = matrix.data[0][2];
         *pWriter++ = matrix.data[0][3];
         *pWriter++ = matrix.data[1][0];
         *pWriter++ = matrix.data[1][1];
         *pWriter++ = matrix.data[1][2];
         *pWriter++ = matrix.data[1][3];
         *pWriter++ = matrix.data[2][0];
         *pWriter++ = matrix.data[2][1];
         *pWriter++ = matrix.data[2][2];
         *pWriter++ = matrix.data[2][3];
         *pWriter++ = matrix.data[3][0];
         *pWriter++ = matrix.data[3][1];
         *pWriter++ = matrix.data[3][2];
         *pWriter++ = matrix.data[3][3];
      }
   }
   // 提交数据
   TResult resultCd = Set(data, sizeof(TFloat) * 16 * count);
   return resultCd;
}

//============================================================
// <T>加载配置。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FRenderParameter::LoadConfig(FXmlNode* pConfig){
   MO_CHECK(pConfig, return ENull);
   // 设置名称
   _name = pConfig->Get("name");
   // 设置关联
   _linker = pConfig->Get("linker");
   // 设置格式
   TCharC* pShader = pConfig->Get("shader", NULL);
   if(pShader != NULL){
      _shaderCd = RRenderShader::Parse(pShader);
   }
   // 设置格式
   TCharC* pFormat = pConfig->Get("format", NULL);
   if(pFormat != NULL){
      _formatCd = RRenderParameterFormat::Parse(pFormat);
   }
   return ESuccess;
}

MO_NAMESPACE_END
