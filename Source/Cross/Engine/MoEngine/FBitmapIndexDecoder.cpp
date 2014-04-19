#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造INDEX位图资源工作器。</T>
//============================================================
FBitmapIndexDecoder::FBitmapIndexDecoder(){
   _typeCd = EDecoder_BitmapIndex;
   _pInputStream = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构INDEX位图资源工作器。</T>
//============================================================
FBitmapIndexDecoder::~FBitmapIndexDecoder(){
   MO_DELETE(_pInputStream);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapIndexDecoder::UnserializeInfo(IDataInput* pInput){
   _optionAlpha = pInput->ReadBool();
   _size.width = pInput->ReadInt16();
   _size.height = pInput->ReadInt16();
   _validLocation.x = pInput->ReadInt16();
   _validLocation.y = pInput->ReadInt16();
   _validSize.width = pInput->ReadInt16();
   _validSize.height = pInput->ReadInt16();
   //...........................................................
   MO_DEBUG(TC("Unserialize bitmap index decoder. (size=%dx%d, valid_location=%d,%d, valid_size=%dx%d)"),
         _size.width, _size.height, _validLocation.x, _validLocation.y, _validSize.width, _validSize.height);
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapIndexDecoder::UnserializeData(IDataInput* pInput){
   TInt dataLength = pInput->ReadInt32();
   _pInputStream->ForceSize(dataLength);
   _pInputStream->Write(pInput->PositionMemory(), dataLength);
   pInput->Skip(dataLength);
   return ETrue;
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TBool FBitmapIndexDecoder::Process(){
   MO_TRACK_DEFINE( TSpeedTest speed );
   // 设置输出
   TInt validSize = sizeof(TUint32) * _validSize.width * _validSize.height;
   _pInputStream->SetPosition(0);
   _pOutputStream->ForceSize(validSize);
   // 解压数据
   RBitmap::DecodeBlock(_pInputStream, _pOutputStream);
   _resultCd = EDecoderResult_Success;
   // 处理完成
   MO_TRACK(speed.Record(), TC("Decode bitmap index resource. (code=%d, valid_size=%dx%d, input_data=0x%08X, input_position=%d, input_length=%d)"),
         _code, _validSize.width, _validSize.height, _pInputStream->MemoryC(), _pInputStream->Position(), _pInputStream->Length());
   return ETrue;
}

//============================================================
// <T>完成处理。</T>
//
// @return 处理结果
//============================================================
TBool FBitmapIndexDecoder::Complete(){
   MO_DELETE(_pInputStream);
   return ETrue;
}

MO_NAMESPACE_END
