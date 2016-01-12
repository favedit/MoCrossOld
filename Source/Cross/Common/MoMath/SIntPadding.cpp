#include "MoMtGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SIntPadding::Serialize8(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteUint8((TUint8)left);
   pOutput->WriteUint8((TUint8)top);
   pOutput->WriteUint8((TUint8)right);
   pOutput->WriteUint8((TUint8)bottom);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SIntPadding::Unserialize8(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadUint8();
   top = pInput->ReadUint8();
   right = pInput->ReadUint8();
   bottom = pInput->ReadUint8();
   return ESuccess;
}


//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SIntPadding::Serialize16(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteUint16((TUint16)left);
   pOutput->WriteUint16((TUint16)top);
   pOutput->WriteUint16((TUint16)right);
   pOutput->WriteUint16((TUint16)bottom);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SIntPadding::Unserialize16(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadUint16();
   top = pInput->ReadUint16();
   right = pInput->ReadUint16();
   bottom = pInput->ReadUint16();
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SIntPadding::Serialize(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteInt32((TInt32)left);
   pOutput->WriteInt32((TInt32)top);
   pOutput->WriteInt32((TInt32)right);
   pOutput->WriteInt32((TInt32)bottom);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SIntPadding::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadInt32();
   top = pInput->ReadInt32();
   right = pInput->ReadInt32();
   bottom = pInput->ReadInt32();
   return ESuccess;
}

MO_NAMESPACE_END
