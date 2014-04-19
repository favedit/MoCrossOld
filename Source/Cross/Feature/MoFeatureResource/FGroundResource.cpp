#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造地图资源。</T>
//============================================================
FGroundResource::FGroundResource(){
   _typeCd = EResourceType_Ground;
   //_typeName = "2g";
   _pOutputStream = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构地图资源。</T>
//============================================================
FGroundResource::~FGroundResource(){
   MO_DELETE(_pOutputStream);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FGroundResource::UnserializeInfo(IDataInput* pInput){
   //// 读取基本属性
   //FCompressResource::UnserializeInfo(pInput);
   //// 序列化索引块位图
   //_size.width = pInput->ReadInt16();
   //_size.height = pInput->ReadInt16();
   //MO_DEBUG(TC("Unserialize resource ground info. (code=%d, code_name=%s, type_name=%s, timeout=%d, size=%dx%d)"),
   //      _code, (TCharC*)_codeName, (TCharC*)_typeName, _timeout, _size.width, _size.height);
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FGroundResource::UnserializeData(IDataInput* pInput){
   //MO_TRACK_DEFINE( TSpeedTest tester );
   //// 解压位图
   //FBitmapDecoder* pDecoder = (FBitmapDecoder*)RDecoderManager::Instance().CreateDecoder(pInput);
   //if(NULL == pDecoder){
   //   return EFalse;
   //}
   //pDecoder->Decode(_pOutputStream);
   //MO_DELETE(pDecoder);
   //// 输出结果
   //MO_TRACK(tester.Record(), TC("Unserialize resource ground data. (code=%d, code_name=%s, type_name=%s, timeout=%d, size=%dx%d)"),
   //   _code, (TCharC*)_codeName, (TCharC*)_typeName, _timeout, _size.width, _size.height);
   return ETrue;
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TBool FGroundResource::Process(){
   //TSpeedTest tester;
   //// 读取信息
   //if(!UnserializeInfo(_pInputStream)){
   //   MO_ERROR(TC("Unserialize info failure. (code=%d, code_name=%s, type_name=%s)"), _code, (TCharC*)_codeName, (TCharC*)_typeName);
   //   return EFalse;
   //}
   //// 读取数据
   //if(!UnserializeData(_pInputStream)){
   //   MO_ERROR(TC("Unserialize data failure. (code=%d, code_name=%s, type_name=%s)"), _code, (TCharC*)_codeName, (TCharC*)_typeName);
   //   return EFalse;
   //}
   //MO_DELETE(_pInputStream);
   //// 完成处理
   //Complete();
   //MO_TRACK(tester.Record(), TC("Process complete resource. (code=%d, code_name=%s, type_name=%s)"), _code, (TCharC*)_codeName, (TCharC*)_typeName);
   return ETrue;
}

//============================================================
// <T>获取资源基础属性。</T>
//
// @param pOutput 输出流
//============================================================
TBool FGroundResource::FetchInfo(IDataOutput* pOutput){
   //// 输出基本属性
   //FCompressResource::FetchInfo(pOutput);
   //// 输出资源属性
   //pOutput->WriteInt16((TInt16)_size.width);
   //pOutput->WriteInt16((TInt16)_size.height);
   return ETrue;
}

//============================================================
// <T>获取位图。</T>
//
// @param bitmap 输出位图 
// @param bitmapId 位图编号
//============================================================
//TBool FGroundResource::FetchBitmap(SBitmapData& bitmapData, TInt bitmapCode){
//   //if(NULL != _pOutputStream){
//   //   MO_DEBUG(TC("Fetch resource ground bitmap data. (code=%d, code_name=%s, type_name=%s, size=%dx%d, output_stream=0x%08X, output_length=%d)"),
//   //         _code, (TCharC*)_codeName, (TCharC*)_typeName, _size.width, _size.height, _pOutputStream, _pOutputStream->Length());
//   //   bitmapData.size.Assign(_size);
//   //   RBitmap::ConvertToBitmapData(bitmapData, _pOutputStream->MemoryC(), _pOutputStream->Length());
//   //   return ETrue;
//   //}else{
//   //   MO_ERROR(TC("Fetch resource ground data failure. (code=%d, code_name=%s, type_name=%s, size=%dx%d, output_stream=0x%08X, output_length=%d)"),
//   //            _code, (TCharC*)_codeName, (TCharC*)_typeName, _size.width, _size.height, _pOutputStream, _pOutputStream->Length());
//   //   return EFalse;
//   //}
//   return EFalse;
//}

MO_NAMESPACE_END
