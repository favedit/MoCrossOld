#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源工作器控制台。</T>
//============================================================
FDecoderConsole::FDecoderConsole(){
   _pWorker = MO_CREATE(FDecoderWorker);
   _pWaitDecoders = MO_CREATE(FDecoderList);
   _pFinishDecoders = MO_CREATE(FDecoderList);
}

//============================================================
// <T>析构资源工作器控制台。</T>
//============================================================
FDecoderConsole::~FDecoderConsole(){
   MO_DELETE(_pWorker);
   MO_DELETE(_pWaitDecoders);
   MO_DELETE(_pFinishDecoders);
}

//============================================================
// <T>启动处理。</T>
//============================================================
void FDecoderConsole::Startup(){
   RWorkerManager::Instance().Register(_pWorker);
}

//============================================================
// <T>关闭处理。</T>
//============================================================
void FDecoderConsole::Shutdown(){
   RWorkerManager::Instance().Unregister(_pWorker);
}

//============================================================
// <T>创建读取索引资源工作器。</T>
//
// @param pInput 输入流
//============================================================
FDecoder* FDecoderConsole::CreateIndexDecoder(IDataInput* pInput){
   // 序列化索引块位图
   TBool optionAlpha = pInput->ReadBool();
   SIntSize2 size;
   size.width = pInput->ReadInt16();
   size.height = pInput->ReadInt16();
   SIntPoint2 validLocation;
   validLocation.x = pInput->ReadInt16();
   validLocation.y = pInput->ReadInt16();
   SIntSize2 validSize;
   validSize.width = pInput->ReadInt16();
   validSize.height = pInput->ReadInt16();
   //............................................................
   // 创建工作器
   FBitmapIndexDecoder* pDecoder = MO_CREATE(FBitmapIndexDecoder);
   pDecoder->SetOptionAlpha(optionAlpha);
   pDecoder->SetSize(size);
   pDecoder->SetValidLocation(validLocation);
   pDecoder->SetValidSize(validSize);
   // 读取数据
   TInt dataLength = pInput->ReadInt32();
   pDecoder->InputStream()->ForceSize(dataLength);
   pDecoder->InputStream()->Write(pInput->PositionMemory(), dataLength);
   pInput->Skip(dataLength);
   ////...........................................................
   MO_DEBUG(TC("Create bitmap index decoder. (size=%dx%d, valid_location=%d,%d, valid_size=%dx%d, data_length=%d)"),
      size.width, size.height, validLocation.x, validLocation.y, validSize.width, validSize.height, dataLength);
   return pDecoder;
}

//============================================================
// <T>创建读取块索引资源工作器。</T>
//
// @param pInput 输入流
//============================================================
FDecoder* FDecoderConsole::CreateBlockIndexDecoder(IDataInput* pInput){
   // 序列化索引位图
   TBool optionAlpha = pInput->ReadBool();
   SIntSize2 size;
   size.width = pInput->ReadInt16();
   size.height = pInput->ReadInt16();
   SIntPoint2 validLocation;
   validLocation.x = pInput->ReadInt16();
   validLocation.y = pInput->ReadInt16();
   SIntSize2 validSize;
   validSize.width = pInput->ReadInt16();
   validSize.height = pInput->ReadInt16();
   //............................................................
   // 创建工作器
   TInt blockCount = pInput->ReadInt16();
   FBitmapBlockIndexDecoder* pDecoder = MO_CREATE(FBitmapBlockIndexDecoder);
   // 设置宽度，高度，分块数量
   pDecoder->SetOptionAlpha(optionAlpha);
   pDecoder->SetSize(size);
   pDecoder->SetValidLocation(validLocation);
   pDecoder->SetValidSize(validSize);
   pDecoder->SetBlockCount(blockCount);
   TInt dataLength = pInput->ReadInt32();
   pDecoder->InputStream()->ForceSize(dataLength);
   pDecoder->InputStream()->Write(pInput->PositionMemory(), dataLength);
   pInput->Skip(dataLength);
   //...........................................................
   MO_DEBUG(TC("Create bitmap block index decoder. (size=%dx%d, valid_location=%d,%d, valid_size=%dx%d, data_length=%d)"), 
      size.width, size.height, validLocation.x, validLocation.y, validSize.width, validSize.height, dataLength);
   return pDecoder;
}

//============================================================
// <T>创建读取Jpeg资源工作器。</T>
//
// @param pInput 输入流
//============================================================
FDecoder* FDecoderConsole::CreateJpegDecoder(IDataInput* pInput){
   FDecoder* pDecoder = NULL;
   // 序列化JPEG位图
   SIntSize2 size;
   size.width = pInput->ReadInt16();
   size.height = pInput->ReadInt16();
   SIntPoint2 validLocation;
   validLocation.x = pInput->ReadInt16();
   validLocation.y = pInput->ReadInt16();
   SIntSize2 validSize;
   validSize.width = pInput->ReadInt16();
   validSize.height = pInput->ReadInt16();
   // 读取数据通道
   TInt dataRgbLength = 0;
   TInt dataAlphaLength = 0;
   int channels = pInput->ReadInt8();
   if(2 == channels){
      // 创建双层工作器
      FBitmapJpegLayer2Decoder* pLayer2Worker = MO_CREATE(FBitmapJpegLayer2Decoder);
      pLayer2Worker->SetSize(size);
      pLayer2Worker->SetValidLocation(validLocation);
      pLayer2Worker->SetValidSize(validSize);
      // 设置输入流1
      dataRgbLength = pInput->ReadInt32();
      pLayer2Worker->Input1Stream()->ForceSize(dataRgbLength);
      pLayer2Worker->Input1Stream()->Write(pInput->PositionMemory(), dataRgbLength);
      pInput->Skip(dataRgbLength);
      // 设置输入流2
      dataAlphaLength = pInput->ReadInt32();
      pLayer2Worker->Input2Stream()->ForceSize(dataAlphaLength);
      pLayer2Worker->Input2Stream()->Write(pInput->PositionMemory(), dataAlphaLength);
      pInput->Skip(dataAlphaLength);
      pDecoder = pLayer2Worker;
   }else if(1 == channels){
      // 创建单层工作器
      FBitmapJpegLayer1Decoder* pLayer1Worker = MO_CREATE(FBitmapJpegLayer1Decoder);
      pLayer1Worker->SetSize(size);
      pLayer1Worker->SetValidLocation(validLocation);
      pLayer1Worker->SetValidSize(validSize);
      // 设置输入流
      dataRgbLength = pInput->ReadInt32();
      pLayer1Worker->InputStream()->ForceSize(dataRgbLength);
      pLayer1Worker->InputStream()->Write(pInput->PositionMemory(), dataRgbLength);
      pInput->Skip(dataRgbLength);
      pDecoder = pLayer1Worker;
   }else{
      MO_FATAL("Invalid jpeg channles. (count=%d)", channels);
   }
   //............................................................
   MO_DEBUG(TC("Create bitmap jpeg decoder. (size=%dx%d, valid_location=%d,%d, valid_size=%dx%d, data_rgb_length=%d, data_alpha_length=%d)"), 
      size.width, size.height, validLocation.x, validLocation.y, validSize.width, validSize.height, dataRgbLength, dataAlphaLength);
   return pDecoder;
}

//============================================================
// <T>创建读取资源工作器。</T>
//
// @param pInput 输入流
//============================================================
FDecoder* FDecoderConsole::CreateDecoder(IDataInput* pInput){
   FDecoder* pWorker = NULL;
   TString typeCd = pInput->ReadString();
   if(typeCd.Equals(indexStr)){
      // 索引数据
      pWorker = CreateIndexDecoder(pInput);
   }else if(typeCd.Equals(blockIndexStr)){
      // 块索引数据
      pWorker = CreateBlockIndexDecoder(pInput);
   }else if(typeCd.Equals(jpgStr)){
      // jpeg数据
      pWorker = CreateJpegDecoder(pInput);
   }else{
      MO_ERROR(TC("Unknown resource type. (type_cd=%s)"), (TCharC*)typeCd);
   }
   return pWorker;
}

//============================================================
// <T>检测工作器是否完成。</T>
//
// @param pWorker 工作器
// @return 返回结果
//============================================================
TBool FDecoderConsole::IsFinishWorker(FDecoder* pWorker){
   TBool result = EFalse;
   if(NULL != pWorker){
      _mutex.Enter();
      result = _pFinishDecoders->Contains(pWorker);
      _mutex.Leave();
   }
   return result;
}

//============================================================
// <T>列出完成列表。</T>
//
// @param pOutput 输出流
// @return 完成的个数
//============================================================
TInt FDecoderConsole::ListFinish(IDataOutput* pOutput){
   TInt finishCount = 0;
   _mutex.Enter();
   finishCount = _pFinishDecoders->Count();
   TListIteratorC<FDecoder*> iterator = _pFinishDecoders->IteratorC();
   while(iterator.Next()){
      FDecoder* pWorker = *iterator;
      pOutput->WriteInt32(pWorker->Code());
   }
   _mutex.Leave();
   return finishCount;
}

//============================================================
// <T>放入一个等待处理的工作器。</T>
//
// @param pWorker 工作器
//============================================================
void FDecoderConsole::PushWaitWorker(FDecoder* pWorker){
   // 放入等待的工作器
   _mutex.Enter();
   _pWaitDecoders->Push(pWorker);
   _mutex.Leave();
}

//============================================================
// <T>弹出一个等待处理的工作器。</T>
//
// @param pWorker 工作器
//============================================================
FDecoder* FDecoderConsole::PopWaitWorker(){
   FDecoder* pWorker = NULL;
   _mutex.Enter();
   if(!_pWaitDecoders->IsEmpty()){
      pWorker = _pWaitDecoders->Shift();
   }
   _mutex.Leave();
   return pWorker;
}

//============================================================
// <T>放入一个处理完成的工作器。</T>
//
// @param pWorker 工作器
//============================================================
void FDecoderConsole::PushFinishWorker(FDecoder* pWorker){
   _mutex.Enter();
   _pFinishDecoders->Push(pWorker);
   _mutex.Leave();
}

//============================================================
// <T>弹出一个处理完成的工作器。</T>
//
// @param code 代码
// @return 工作器
//============================================================
FDecoder* FDecoderConsole::PopFinishWorker(TInt code){
   FDecoder* pWorker = NULL;
   _mutex.Enter();
   if(!_pFinishDecoders->IsEmpty()){
      if(-1 == code){
         pWorker = _pFinishDecoders->Shift();
      }else{
         TListIteratorC<FDecoder*> iterator = _pFinishDecoders->IteratorC();
         while(iterator.Next()){
            FDecoder* pFind = *iterator;
            if(pFind->Code() == code){
               pWorker = pFind;
               break;
            }
         }
         if(NULL != pWorker){
            _pFinishDecoders->Remove(pWorker);
         }
      }
   }
   _mutex.Leave();
   return pWorker;
}

//============================================================
// <T>释放一个完成的工作器</T>
//
// @param pWorker 工作器
// @return 返回结果
//============================================================
TBool FDecoderConsole::FreeFinishWorker(FDecoder* pWorker){
   TBool result = EFalse;
   if(NULL != pWorker){
      _mutex.Enter();
      if(_pFinishDecoders->Contains(pWorker)){
         _pFinishDecoders->Remove(pWorker);
         result = ETrue;
      }
   }
   _mutex.Leave();
   return result;
}

//============================================================
// <T>释放一个完成的工作器</T>
//
// @param pWorker 工作器
// @return 返回结果
//============================================================
TBool FDecoderConsole::ReleaseFinishWorker(FDecoder* pWorker){
   TBool result = EFalse;
   if(NULL != pWorker){
      _mutex.Enter();
      if(_pFinishDecoders->Contains(pWorker)){
         _pFinishDecoders->Remove(pWorker);
         MO_DELETE(pWorker);
         result = ETrue;
      }
   }
   _mutex.Leave();
   return result;
}

MO_NAMESPACE_END
