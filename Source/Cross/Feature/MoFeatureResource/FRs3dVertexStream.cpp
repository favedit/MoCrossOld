#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dVertexStream, FInstance);

//============================================================
// <T>构造资源3D顶点缓冲。</T>
//============================================================
FRs3dVertexStream::FRs3dVertexStream(){
   MO_CLEAR(_pBuffer);
   _formatCd = EContent3dVertexFormat_Float4;
   _stride = 0;
   _offset = 0;
}

//============================================================
// <T>析构资源3D顶点缓冲。</T>
//============================================================
FRs3dVertexStream::~FRs3dVertexStream(){
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dVertexStream::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取属性
   _code.Unserialize(pInput);
   _formatCd = (EContent3dVertexFormat)pInput->ReadUint8();
   _stride = pInput->ReadUint8();
   _offset = pInput->ReadUint8();
   return ESuccess;
}

MO_NAMESPACE_END
