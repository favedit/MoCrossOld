#include <MoJpeg.h>
#include "MoEgDecoder.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造JPEG位图资源工作器。</T>
//============================================================
FBitmapJpegLayer2Decoder::FBitmapJpegLayer2Decoder(){
   _typeCd = EDecoder_BitmapJpegLayer2;
   _pInput1Stream = MO_CREATE(FByteStream);
   _pInput2Stream = MO_CREATE(FByteStream);
}

//============================================================
// <T>析构JPEG位图资源工作器。</T>
//============================================================
FBitmapJpegLayer2Decoder::~FBitmapJpegLayer2Decoder(){
   MO_DELETE(_pInput1Stream);
   MO_DELETE(_pInput2Stream);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer2Decoder::UnserializeInfo(IDataInput* pInput){
   return ETrue;
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer2Decoder::UnserializeData(IDataInput* pInput){
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
TBool FBitmapJpegLayer2Decoder::Process(){
#ifdef _MO_DEBUG
   TSpeedTest speed;
#endif
   // 解压颜色层
   struct jpeg_error_mgr jerror1;
   jerror1.error_exit = my_error_exit;
   struct jpeg_decompress_struct jworker1;
   jworker1.err = jpeg_std_error(&jerror1);
   jpeg_create_decompress(&jworker1);
   jpeg_mem_src(&jworker1, _pInput1Stream->Memory(), _pInput1Stream->Length());
   jpeg_read_header(&jworker1, TRUE);
   // 解压透明层
   struct jpeg_error_mgr jerror2;
   jerror2.error_exit = my_error_exit;
   struct jpeg_decompress_struct jworker2;
   jworker2.err = jpeg_std_error(&jerror2);
   jpeg_create_decompress(&jworker2);
   jpeg_mem_src(&jworker2, _pInput2Stream->Memory(), _pInput2Stream->Length());
   jpeg_read_header(&jworker2, TRUE);
   //............................................................
   // 设置输出流
   _size.width = jworker1.image_width;
   _size.height = jworker1.image_height;
   _pOutputStream->ForceSize(sizeof(TUint32) * _size.width * _size.height);
   TUint32* pWriter = (TUint32*)_pOutputStream->Memory();
   //............................................................
   // 解压行数据
   jworker1.out_color_space = JCS_BGRE;
   jworker2.out_color_space = JCS_GRAYSCALE;
   if(jpeg_start_decompress(&jworker1) && jpeg_start_decompress(&jworker2)){
      TInt width = _size.width;
      TInt lineStride = sizeof(TUint32) * width;
      TUint32* pLine1 = (TUint32*)MO_MEM_ALLOC(lineStride);
      TUint8* pLine2 = (TUint8*)MO_MEM_ALLOC(width);
      while((jworker1.output_scanline < jworker1.output_height) && (jworker2.output_scanline < jworker2.output_height)){
         // 解压一行
         TByte* pLineWriter1 = (TByte*)pLine1;
         jpeg_read_scanlines(&jworker1, &pLineWriter1, 1);
         // 解压一行
         TByte* pLineWriter2 = (TByte*)pLine2;
         jpeg_read_scanlines(&jworker2, &pLineWriter2, 1);
         // 输出颜色 (ARGB)
         for(TInt n = 0; n < width; n++){
            *pWriter++ = pLine1[n] | (pLine2[n] << 24);
         }
      }
      MO_MEM_FREE(pLine1);
      MO_MEM_FREE(pLine2);
      _resultCd = EDecoderResult_Success;
   }else{
      _resultCd = EDecoderResult_Failure;
   }
   jpeg_finish_decompress(&jworker1);
   jpeg_finish_decompress(&jworker2);
   jpeg_destroy_decompress(&jworker1);
   jpeg_destroy_decompress(&jworker2);
#ifdef _MO_DEBUG
   MO_TRACK(speed.Record(), TC("Decode bitmap jpeg layer2 resource. (code=%d, size=%dx%d, input1_data=0x%08X, input1_length=%d/%d, input2_data=0x%08X, input2_length=%d/%d)"),
         _code, _size.width, _size.height,
         _pInput1Stream, _pInput1Stream->Position(), _pInput1Stream->Length(),
         _pInput2Stream, _pInput2Stream->Position(), _pInput2Stream->Length());
#endif
   return ETrue;
}

//============================================================
// <T>完成处理。</T>
//
// @return 处理结果
//============================================================
TBool FBitmapJpegLayer2Decoder::Complete(){
   MO_DELETE(_pInput1Stream);
   MO_DELETE(_pInput2Stream);
   return ETrue;
}

MO_NAMESPACE_END
