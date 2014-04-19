#include "MoFrContent3dModel.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRs3dIndexBuffer, FInstance);

//============================================================
// <T>构造资源3D索引缓冲。</T>
//============================================================
FRs3dIndexBuffer::FRs3dIndexBuffer(){
   _strideCd = EContent3dIndexStride_Unknown;
   _count = 0;
   _pData = MO_CREATE(FBytes);
}

//============================================================
// <T>析构资源3D索引缓冲。</T>
//============================================================
FRs3dIndexBuffer::~FRs3dIndexBuffer(){
   MO_DELETE(_pData);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dIndexBuffer::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   _count = pInput->ReadInt32();
   _strideCd = (EContent3dIndexStride)pInput->ReadInt8();
   TInt dataLength = 0;
   if(_strideCd == EContent3dIndexStride_Uint16){
      dataLength = sizeof(TUint16) * _count;
   }else if(_strideCd == EContent3dIndexStride_Uint32){
      dataLength = sizeof(TUint32) * _count;
   }else{
      MO_FATAL_UNSUPPORT();
   }
   // 读取数据
   _pData->ForceLength(dataLength);
   pInput->Read(_pData->Memory(), dataLength);
   return ESuccess;
}

MO_NAMESPACE_END
