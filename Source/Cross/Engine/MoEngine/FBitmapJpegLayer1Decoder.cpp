#include <MoJpeg.h>
#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造JPEG位图资源工作器。</T>
//============================================================
FBitmapJpegLayer1Decoder::FBitmapJpegLayer1Decoder(){
   _typeCd = EDecoder_BitmapJpegLayer1;
   _pInputStream = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构JPEG位图资源工作器。</T>
//============================================================
FBitmapJpegLayer1Decoder::~FBitmapJpegLayer1Decoder(){
   MO_DELETE(_pInputStream);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer1Decoder::UnserializeInfo(IDataInput* pInput){
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer1Decoder::UnserializeData(IDataInput* pInput){
   return ETrue;
}

//============================================================
METHODDEF(void) my_error_exit (j_common_ptr cinfo){
   MO_STATIC_ERROR("Jpeg error. (code=%d)", cinfo->err->msg_code);
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer1Decoder::Process(){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
   struct jpeg_error_mgr jerror;
   jerror.error_exit = my_error_exit;
   struct jpeg_decompress_struct jworker;
   jworker.err = jpeg_std_error(&jerror);
   jpeg_create_decompress(&jworker);
   jpeg_mem_src(&jworker, _pInputStream->Memory(), _pInputStream->Length());
   jpeg_read_header(&jworker, TRUE);
   //............................................................
   // 设置输出流
   _size.width = jworker.image_width;
   _size.height = jworker.image_height;
   _pOutputStream->ForceSize(sizeof(TUint32) * _size.width * _size.height);
   TUint32* pWriter = (TUint32*)_pOutputStream->Memory();
   //............................................................
   // 解压行数据设置（缩放，颜色控件）
   jworker.out_color_space = JCS_BGRA;
   // 解压行数据
   if(jpeg_start_decompress(&jworker)){
      while(jworker.output_scanline < jworker.output_height){
         // 解压一行
         TByte* pLine = (TByte*)pWriter;
         jpeg_read_scanlines(&jworker, &pLine, 1);
         pWriter += _size.width;
      }
      _resultCd = EDecoderResult_Success;
   }else{
      _resultCd = EDecoderResult_Failure;
   }
   jpeg_finish_decompress(&jworker);
   jpeg_destroy_decompress(&jworker);
   // 处理完成
#ifdef _MO_DEBUG
   MO_TRACK(speed.Record(), TC("Decode bitmap jpeg layer1 resource. (code=%d, size=%dx%d, input_data=0x%08X, input_length=%d/%d)"),
         _code, _size.width, _size.height, _pInputStream, _pInputStream->Position(), _pInputStream->Length());
#endif
   return ETrue;
}

//============================================================
// <T>完成处理。</T>
//
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer1Decoder::Complete(){
   MO_DELETE(_pInputStream);
   return ETrue;
}

MO_NAMESPACE_END
