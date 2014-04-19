#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造INDEX分块位图资源工作器。</T>
//============================================================
FBitmapBlockIndexDecoder::FBitmapBlockIndexDecoder(){
   _typeCd = EDecoder_BitmapBlockIndex;
   _pInputStream = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构INDEX分块位图资源工作器。</T>
//============================================================
FBitmapBlockIndexDecoder::~FBitmapBlockIndexDecoder(){
   MO_DELETE(_pInputStream);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapBlockIndexDecoder::UnserializeInfo(IDataInput* pInput){
   _optionAlpha = pInput->ReadBool();
   _size.width = pInput->ReadInt16();
   _size.height = pInput->ReadInt16();
   _validLocation.x = pInput->ReadInt16();
   _validLocation.y = pInput->ReadInt16();
   _validSize.width = pInput->ReadInt16();
   _validSize.height = pInput->ReadInt16();
   _blockCount = pInput->ReadInt16();
   //...........................................................
   MO_DEBUG(TC("Unserialize bitmap block index decoder. (size=%dx%d, valid_location=%d,%d, valid_size=%dx%d)"), 
         _size.width, _size.height, _validLocation.x, _validLocation.y, _validSize.width, _validSize.height);
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapBlockIndexDecoder::UnserializeData(IDataInput* pInput){
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
TBool FBitmapBlockIndexDecoder::Process(){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
   // 设置输出
   TInt validSize = sizeof(TUint32) * _validSize.width * _validSize.height;
   _pInputStream->SetPosition(0);
   _pOutputStream->ForceSize(validSize);
   // 解压缩
   for(TInt n = 0; n < _blockCount; n++){
      RBitmap::DecodeBlock(_pInputStream, _pOutputStream);
   }
   _resultCd = EDecoderResult_Success;
#ifdef _MO_DEBUG
   MO_TRACK(speed.Record(), TC("Decode bitmap block index resource. (code=%d, size=%dx%d, input_data=0x%08X, input_length=%d/%d)"),
      _code, _size.width, _size.height, _pInputStream, _pInputStream->Position(), _pInputStream->Length());
#endif
   return ETrue;
}

//============================================================
// <T>完成处理。</T>
//
// @return 处理结果
//============================================================
TBool FBitmapBlockIndexDecoder::Complete(){
   MO_DELETE(_pInputStream);
   return ETrue;
}

MO_NAMESPACE_END
