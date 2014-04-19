#ifndef __MO_EG_DECODER_H__
#define __MO_EG_DECODER_H__
//************************************************************

#ifndef __MO_GM_COMMON_H__
#include "MoEgCommon.h"
#endif // __MO_GM_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
class FDecoder;
class FBitmapDecoder;
class FBitmapIndexDecoder;
class FBitmapJpegResourceWorker;
class FDecoderWorker;
class FDecoderConsole;

//============================================================
// <T>位图解码标识。</T>
//============================================================
static const TChar indexStr[] = TC("ix");
static const TChar blockIndexStr[] = TC("bi");
static const TChar jpgStr[] = TC("jp");

//============================================================
// <T>资源工作器类型。</T>
//============================================================
enum EDecoder{
   EDecoder_Unknown          = 0,
   EDecoder_BitmapIndex      = 1,
   EDecoder_BitmapBlockIndex = 2,
   EDecoder_BitmapJpegLayer1 = 3,
   EDecoder_BitmapJpegLayer2 = 4,
};

//============================================================
// <T>资源工作器状态。</T>
//============================================================
enum EDecoderStatus{
   EDecoderStatus_Unknown,
   EDecoderStatus_Processing,
   EDecoderStatus_Finish,
};

//============================================================
// <T>资源工作器状态。</T>
//============================================================
enum EDecoderResult{
   EDecoderResult_Unknown,
   EDecoderResult_Success,
   EDecoderResult_Failure,
};

//============================================================
// <T>资源工作器。</T>
//============================================================
class MO_EG_DECLARE FDecoder : public FObject{
protected:
   TInt _code;
   EDecoder   _typeCd;
   EDecoderResult _resultCd;
   TSpeedTest _tester;
public:
   FDecoder();
   MO_ABSTRACT ~FDecoder();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TInt Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TInt code){
      _code = code;
   }
   //------------------------------------------------------------
   // <T>获得结果。</T>
   MO_INLINE EDecoder TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>获得结果。</T>
   MO_INLINE void SetTypeCd(EDecoder typeCd){
      _typeCd = typeCd;
   }
   //------------------------------------------------------------
   // <T>获得结果。</T>
   MO_INLINE EDecoderResult ResultCd(){
      return _resultCd;
   }
   //------------------------------------------------------------
   // <T>设置结果。</T>
   MO_INLINE void SetResultCd(EDecoderResult resultCd) {
      _resultCd = resultCd;
   }
public:
   MO_ABSTRACT TBool UnserializeInfo(IDataInput* pInput);
   MO_ABSTRACT TBool UnserializeData(IDataInput* pInput);
public:
   MO_VIRTUAL TBool Process() = 0;
   MO_ABSTRACT TBool Complete();
   MO_ABSTRACT TBool Fetch(IOutput* pOutput);
public:
   MO_ABSTRACT TBool Decode(IOutput* pOutput);
};
//------------------------------------------------------------
typedef MO_EG_DECLARE FList<FDecoder*> FDecoderList;

//============================================================
// <T>位图资源工作器。</T>
//============================================================
class MO_EG_DECLARE FBitmapDecoder : public FDecoder{
protected:
   TBool _optionAlpha;
   SIntSize2 _size;
   SIntPoint2 _validLocation;
   SIntSize2 _validSize;
   FByteStream* _pOutputStream;
public:
   FBitmapDecoder();
   MO_ABSTRACT ~FBitmapDecoder();
public:
   //------------------------------------------------------------
   // <T>获得宽度。</T>
   MO_INLINE TBool OptionAlpha(){
      return _optionAlpha;
   }

   //------------------------------------------------------------
   // <T>获得宽度。</T>
   MO_INLINE void SetOptionAlpha(TBool optionAlpha){
      _optionAlpha = optionAlpha;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2 Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>设置大小。</T>
   MO_INLINE void SetSize(SIntSize2 size){
      _size = size;
   }
   //------------------------------------------------------------
   // <T>获得有效位置。</T>
   MO_INLINE SIntPoint2& ValidLocation(){
      return _validLocation;
   }
   //------------------------------------------------------------
   // <T>设置有效位置。</T>
   MO_INLINE void SetValidLocation(SIntPoint2& validLocation){
      _validLocation = validLocation;
   }
   //------------------------------------------------------------
   // <T>获得有效尺寸。</T>
   MO_INLINE SIntSize2& ValidSize(){
      return _validSize;
   }
   //------------------------------------------------------------
   // <T>设置有效尺寸。</T>
   MO_INLINE void SetValidSize(SIntSize2& validSize){
      _validSize = validSize;
   }
   //------------------------------------------------------------
   // <T>获得输出流。</T>
   MO_INLINE FByteStream* OutputStream(){
      return _pOutputStream;
   }
public:
   MO_OVERRIDE TBool Fetch(IOutput* pOutput);
};

//============================================================
// <T>INDEX位图资源工作器。</T>
//============================================================
class MO_EG_DECLARE FBitmapIndexDecoder : public FBitmapDecoder{
protected:
   FByteStream* _pInputStream;
public:
   FBitmapIndexDecoder();
   MO_ABSTRACT ~FBitmapIndexDecoder();
public:
   //------------------------------------------------------------
   // <T>获得输入流。</T>
   MO_INLINE FByteStream* InputStream(){
      return _pInputStream;
   }
public:
   MO_ABSTRACT TBool UnserializeInfo(IDataInput* pInput);
   MO_ABSTRACT TBool UnserializeData(IDataInput* pInput);
public:
   MO_OVERRIDE TBool Process();
   MO_OVERRIDE TBool Complete();
};

//============================================================
// <T>INDEX分块位图资源工作器。</T>
//============================================================
class MO_EG_DECLARE FBitmapBlockIndexDecoder : public FBitmapDecoder{
protected:
   TInt _blockCount;
   FByteStream* _pInputStream;
public:
   FBitmapBlockIndexDecoder();
   MO_ABSTRACT ~FBitmapBlockIndexDecoder();
public:
   //------------------------------------------------------------
   // <T>获得输入流。</T>
   MO_INLINE TInt BlockCount(){
      return _blockCount;
   }
   //------------------------------------------------------------
   // <T>获得输入流。</T>
   MO_INLINE void SetBlockCount(TInt blockCount){
      _blockCount = blockCount;
   }
   //------------------------------------------------------------
   // <T>获得输入流。</T>
   MO_INLINE FByteStream* InputStream(){
      return _pInputStream;
   }
   //------------------------------------------------------------
   // <T>获得输出流。</T>
   MO_INLINE FByteStream* OutputStream(){
      return _pOutputStream;
   }
public:
   MO_ABSTRACT TBool UnserializeInfo(IDataInput* pInput);
   MO_ABSTRACT TBool UnserializeData(IDataInput* pInput);
public:
   MO_OVERRIDE TBool Process();
   MO_OVERRIDE TBool Complete();
};

//============================================================
// <T>JPEG单层位图资源工作器。</T>
//============================================================
class MO_EG_DECLARE FBitmapJpegLayer1Decoder : public FBitmapDecoder{
protected:
   FByteStream* _pInputStream;
public:
   FBitmapJpegLayer1Decoder();
   MO_ABSTRACT ~FBitmapJpegLayer1Decoder();
public:
   //------------------------------------------------------------
   // <T>获得输入流。</T>
   MO_INLINE FByteStream* InputStream(){
      return _pInputStream;
   }
   //------------------------------------------------------------
   // <T>获得输出流。</T>
   MO_INLINE FByteStream* OutputStream(){
      return _pOutputStream;
   }
public:
   MO_ABSTRACT TBool UnserializeInfo(IDataInput* pInput);
   MO_ABSTRACT TBool UnserializeData(IDataInput* pInput);
public:
   MO_OVERRIDE TBool Process();
   MO_OVERRIDE TBool Complete();
};

//============================================================
// <T>JPEG位图资源工作器。</T>
//============================================================
class MO_EG_DECLARE FBitmapJpegLayer2Decoder : public FBitmapDecoder{
protected:
   FByteStream* _pInput1Stream;
   FByteStream* _pInput2Stream;
public:
   FBitmapJpegLayer2Decoder();
   MO_ABSTRACT ~FBitmapJpegLayer2Decoder();
public:
   //------------------------------------------------------------
   // <T>获得输入流1。</T>
   MO_INLINE FByteStream* Input1Stream(){
      return _pInput1Stream;
   }
   //------------------------------------------------------------
   // <T>获得输入流2。</T>
   MO_INLINE FByteStream* Input2Stream(){
      return _pInput2Stream;
   }
   //------------------------------------------------------------
   // <T>获得输出流。</T>
   MO_INLINE FByteStream* OutputStream(){
      return _pOutputStream;
   }
public:
   MO_ABSTRACT TBool UnserializeInfo(IDataInput* pInput);
   MO_ABSTRACT TBool UnserializeData(IDataInput* pInput);
public:
   MO_OVERRIDE TBool Process();
   MO_OVERRIDE TBool Complete();
};

//============================================================
// <T>资源工作器线程。</T>
//============================================================
class MO_EG_DECLARE FDecoderWorker : public FWorker{
public:
   FDecoderWorker();
   MO_ABSTRACT ~FDecoderWorker();
public:
   MO_OVERRIDE TResult OnProcess();
};

//============================================================
// <T>资源工作器控制台。</T>
//============================================================
class MO_EG_DECLARE FDecoderConsole : public FConsole{
protected:
   TThreadMutex _mutex;
   FDecoderWorker* _pWorker;
   FDecoderList* _pWaitDecoders;
   FDecoderList* _pFinishDecoders;
public:
   FDecoderConsole();
   MO_ABSTRACT ~FDecoderConsole();
public:
   //------------------------------------------------------------
   // <T>获得等待的工作列表。</T>
   MO_INLINE FDecoderList* WaitDecoders(){
      return _pWaitDecoders;
   }
   //------------------------------------------------------------
   // <T>获得完成的工作列表。</T>
   MO_INLINE FDecoderList* FinishDecoders(){
      return _pFinishDecoders;
   }
public:
   void Startup();
   void Shutdown();
public:
   FDecoder* CreateIndexDecoder(IDataInput* pInput);
   FDecoder* CreateBlockIndexDecoder(IDataInput* pInput);
   FDecoder* CreateJpegDecoder(IDataInput* pInput);
   FDecoder* CreateDecoder(IDataInput* pInput);
public:
   TBool IsFinishWorker(FDecoder* pWorker);
   TInt ListFinish(IDataOutput* pOutput);
public:
   void PushWaitWorker(FDecoder* pWorker);
   FDecoder* PopWaitWorker();
   void PushFinishWorker(FDecoder* pWorker);
   FDecoder* PopFinishWorker(TInt code = -1);
   TBool FreeFinishWorker(FDecoder* pWorker);
   TBool ReleaseFinishWorker(FDecoder* pWorker);
};
//------------------------------------------------------------
typedef RSingleton<FDecoderConsole> RDecoderManager;

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_DECODER_H__
