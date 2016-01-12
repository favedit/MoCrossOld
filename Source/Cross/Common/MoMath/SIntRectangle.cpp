#include "MoMtGeom.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SIntRectangle::Serialize8(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteUint8((TUint8)left);
   pOutput->WriteUint8((TUint8)top);
   pOutput->WriteUint8((TUint8)width);
   pOutput->WriteUint8((TUint8)height);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SIntRectangle::Unserialize8(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadUint8();
   top = pInput->ReadUint8();
   width = pInput->ReadUint8();
   height = pInput->ReadUint8();
   return ESuccess;
}


//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SIntRectangle::Serialize16(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteUint16((TUint16)left);
   pOutput->WriteUint16((TUint16)top);
   pOutput->WriteUint16((TUint16)width);
   pOutput->WriteUint16((TUint16)height);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SIntRectangle::Unserialize16(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadUint16();
   top = pInput->ReadUint16();
   width = pInput->ReadUint16();
   height = pInput->ReadUint16();
   return ESuccess;
}

//============================================================
// <T>序列化数据内容到输出流。</T>
// 
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult SIntRectangle::Serialize(IDataOutput* pOutput){
   MO_CHECK(pOutput, return ENull);
   pOutput->WriteInt32((TInt32)left);
   pOutput->WriteInt32((TInt32)top);
   pOutput->WriteInt32((TInt32)width);
   pOutput->WriteInt32((TInt32)height);
   return ESuccess;
}

//============================================================
// <T>从输入流反序列化数据内容。</T>
// 
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult SIntRectangle::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   left = pInput->ReadInt32();
   top = pInput->ReadInt32();
   width = pInput->ReadInt32();
   height = pInput->ReadInt32();
   return ESuccess;
}

MO_NAMESPACE_END
