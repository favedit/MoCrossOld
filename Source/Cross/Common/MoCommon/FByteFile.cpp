#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造字节数组文件。</T>
//============================================================
FByteFile::FByteFile(){
   _position = 0;
}

//============================================================
// <T>构造字节数组文件。</T>
//
// @param pFileName 文件名称
//============================================================
FByteFile::FByteFile(TCharC* pFileName){
   _position = 0;
   LoadFile(pFileName);
}

//============================================================
// <T>析构字节数组文件。</T>
//============================================================
FByteFile::~FByteFile(){
}

//============================================================
// <T>加载指定名称的数据文件。</T>
//
// @param pFileName 文件名称
// @return 处理结果
//============================================================
TBool FByteFile::LoadFile(TCharC* pFileName){
   // 打开文件
   TFileHandle handle;
#ifdef _MO_WINDOWS
   errno_t error = MO_LIB_FILE_OPEN(&handle, pFileName, TC("rb"));
   if(error != 0){
      MO_ERROR(TC("Open file failure. (error=%d, file_name=%s)"), error, pFileName);
      return EFalse;
   }
#else
   handle = fopen(pFileName, "rb");
#endif // _MO_WINDOWS
   if(handle == NULL){
      MO_PERROR(fopen);
      return EFalse;
   }
   // 获得长度
   if(ESuccess != fseek(handle, 0, SEEK_END)){
      MO_PERROR(fseek);
      return EFalse;
   }
   TInt length = ftell(handle);
   ForceSize(length);
   _length = length;
   // 从开始位置读取
   if(ESuccess != fseek(handle, 0, SEEK_SET)){
      MO_PERROR(fseek);
      return EFalse;
   }
   TInt readed = fread(_pMemory, length, 1, handle);
   if(readed != 1){
      MO_PFATAL(fread);
   }
   // 关闭文件
   if(ESuccess != fclose(handle)){
      MO_PFATAL(fclose);
   }
   return ETrue;
}

//============================================================
// <T>存储指定名称的数据文件。</T>
//
// @param pFileName 文件名称
// @return 处理结果
//============================================================
TBool FByteFile::SaveFile(TCharC* pFileName){
   TBool result = ETrue;
   // 打开文件
   TFileHandle handle;
#ifdef _MO_WINDOWS
   errno_t error = MO_LIB_FILE_OPEN(&handle, pFileName, TC("wb"));
   if(error != 0){
      MO_ERROR(TC("Open file failure. (error=%d, file_name=%s)"), error, pFileName);
      return EFalse;
   }
#else
   handle = fopen(pFileName, "w+");
#endif // _MO_WINDOWS
   if(handle == NULL){
      MO_PERROR(fopen);
      return EFalse;
   }
   // 从开始位置写入
   if(ESuccess != fseek(handle, 0, SEEK_SET)){
      MO_PERROR(fseek);
      return EFalse;
   }
   TInt writted = fwrite(_pMemory, _length, 1, handle);
   if(writted != 1){
      MO_ERROR(TC("Write file data failure. (handle=%d, memory=%08X, length=%d, writted=%d)"), handle, _pMemory, _length, writted);
      result = EFalse;
   }
   // 关闭文件
   if(ESuccess != fclose(handle)){
      MO_PFATAL(fclose);
      return EFalse;
   }
   return result;
}

//============================================================
// <T>存储指定名称的数据文件。</T>
//
// @return 处理结果
//============================================================
TBool FByteFile::Store(){
   return SaveFile(_fileName.MemoryC());
}

MO_NAMESPACE_END
