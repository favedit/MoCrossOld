#include "MoCmFile.h"

MO_NAMESPACE_BEGIN
//============================================================
#define MO_FILE_BUFFER 16384

//============================================================
// <T>构造文件数据流。</T>
//============================================================
FFileStream::FFileStream(){
   _opened = EFalse;
   _handle = 0;
}

//============================================================
// <T>构造文件数据流。</T>
//
// @param fileName 文件名称
// @param modeCd 模式类型
//============================================================
FFileStream::FFileStream(TCharC* pFileName, TFileAccessMode modeCd){
   _filename = pFileName;
   _opened = EFalse;
   _handle = 0;
   Open(pFileName, modeCd);
}

//============================================================
// <T>析够文件数据流。</T>
//============================================================
FFileStream::~FFileStream(){
   Close();
}

//============================================================
// <T>获得文件名</T>
//
// @return 文件名
//============================================================
TCharC* FFileStream::FileName(){
   return _filename.MemoryZ();
}

//============================================================
// <T>获得文件长度</T>
//
// @return 文件长度
//============================================================
TInt FFileStream::Length(){
   // 获得当前位置
   TInt position = ftell(_handle);
   // 移动到尾部
   if(fseek(_handle, 0, SEEK_END)){
      MO_PERROR(fseek);
      return -1;
   }
   // 获得长度
   TInt length = ftell(_handle);
   // 移动到原来位置
   if(fseek(_handle, position, SEEK_SET)){
      MO_PERROR(fseek);
      return -1;
   }
   return length;
}

//============================================================
// <T>返回当前文件位置。</T>
//
// @return 当前位置
//============================================================
TInt FFileStream::Position(){
   return ftell(_handle);
}

//============================================================
// <T>创建文件,如果文件存在则其内容清空。</T>
//
// @param fileName 文件名
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileStream::Create(TCharC* pFileName){
#ifdef _MO_WINDOWS
   TFsPath fullPath = pFileName;
   TInt index = fullPath.LastIndexOf('\\');
   if(-1 != index){
      TFsPath path = fullPath.LeftStrC(index);
      RFile::CreateFullDirectory(path.MemoryC());
   }
#ifdef _UNICODE
   _handle = _wfopen(pFileName, TC("wb"));
#else
   fopen_s(&_handle, pFileName, "wb");
#endif
#else
   _handle = fopen(pFileName, "wb");
#endif
   if(NULL == _handle){
      MO_ERROR(TC("Create file failure. (filename=%s)"), pFileName);
      MO_PERROR(fopen);
      return EFalse;
   }
   _opened = ETrue;
   _filename = pFileName;
   return ETrue;
}

//============================================================
// <T>打开文件</T>
//
// @param fileName 文件名
// @param modeCd 模式类型
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileStream::Open(const TCharC* pFileName, TFileAccessMode modeCd){
   TCharC* pMode = RFile::FILE_MODE_STR[modeCd];
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   errno_t result = 0;
   _handle = _wfopen(pFileName, pMode);
#else
   errno_t result = fopen_s(&_handle, pFileName, pMode);
#endif
   if(0 != result){
      MO_ERROR(TC("Open file failure. (file_name=%s, mode=%s)"), pFileName, pMode);
      MO_PERROR(fopen_s);
   }
#else
   _handle = fopen(pFileName, pMode);
#endif
   // 检查句柄
   if(NULL == _handle){
      MO_ERROR(TC("Open file failure. (file_name=%s, mode=%s)"), pFileName, pMode);
      MO_PERROR(fopen);
      return EFalse;
   }
   // 放在首位置
   Seek(0);
   _opened = ETrue;
   _filename = pFileName;
   return ETrue;
}

//============================================================
// <T>追加方式打开文件。</T>
//
// @param fileName 文件名
// @return 处理结果
//============================================================
TBool FFileStream::OpenAppend(TCharC* pFileName){
   return Open(pFileName, EFileAccessMode_AppendWrite);
}

//============================================================
// <T>重定位流上的文件指针</T>
//
// @param position 偏移字节位置
// @return 处理结果
//============================================================
TBool FFileStream::Seek(TInt position){
   if(fseek(_handle, position, SEEK_SET)){
      MO_PERROR(fseek);
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>重定位文件位置到尾部。</T>
//
// @param position 偏移字节位置
// @return 处理结果
//============================================================
TInt FFileStream::SeekEnd(TInt position){
   if(fseek(_handle, position, SEEK_END)){
      MO_PFATAL(fseek);
   }
   return Position();
}

//============================================================
// <T>从位置读取指定长度的数据。</T>
//
// @param pData 数据指针
// @param capacity 数据容量
// @return 读取长度
//============================================================
TInt FFileStream::Read(TAny* pData, TInt capacity){
   MO_ASSERT(pData);
   TInt result = 0;
   if(capacity > 0){
      if(feof(_handle)){
         return 0;
      }
      result = fread(pData, 1, capacity, _handle);
   }
   return result;
}

//============================================================
// <T>从当前位置写入指定长度的数据内容</T>
//
// @param pData 数据指针
// @param length 数据长度
// @return 写入长度
//============================================================
TInt FFileStream::Write(TAnyC* pData, TInt length){
   MO_ASSERT(pData);
   TInt result = 0;
   if(length > 0){
	  result = fwrite(pData, length, 1, _handle);
   }
   return (1 == result) ? length : 0;
}

//============================================================
// <T>清除文件缓冲区</T>
//
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileStream::Flush(){
   if(ESuccess != fflush(_handle)){
      return EFalse;
   }
   return ETrue;
}

//============================================================
// <T>保存文件</T>
//
// @param fileName 要保存文件的文件明
//============================================================
TBool FFileStream::SaveAs(TCharC* pFileName){
   // 当前文件指针指向首位置
   TInt size = Length();
   rewind(_handle);
   // 打开目标文件
#ifdef _MO_WINDOWS
   TFileHandle handle;
#ifdef _UNICODE
   handle = _wfopen(pFileName, TC("wb"));
#else
   fopen_s(&handle, pFileName, "wb");
#endif
#else
   TFileHandle handle = fopen(pFileName, "wb");
#endif
   if(NULL == handle){
      MO_PERROR(fopen);
      return EFalse;
   }
   // 复制信息到目标文件
   TBool result = ETrue;
   TByte buffer[MO_FILE_BUFFER];
   while(size > 0){
      TInt length = (size > MO_FILE_BUFFER) ? MO_FILE_BUFFER : size;
      // 复制数据
      TInt lengthRead = fread(buffer, length, 1, _handle);
      TInt lengthWrite = fwrite(buffer, lengthRead, 1, handle);
      // 检查写出长度
      if(lengthRead != lengthWrite){
         MO_PERROR(fwrite);
         result = EFalse;
         break;
      }
      size = size - lengthRead;
   }
   // 关闭目标文件
   if(ESuccess != fclose(handle)){
      MO_PERROR(fclose);
      return EFalse;
   }
   return result;
}

//============================================================
// <T>关闭文件</T>
//
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileStream::Close(){
   if(_opened){
      // 刷新数据
      Flush();
      // 关闭
      if(ESuccess != fclose(_handle)){
         return EFalse;
      }
      _opened = EFalse;
   }
   return ETrue;
}

MO_NAMESPACE_END
