#include "MoCmFile.h"

#ifdef _MO_WINDOWS

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造函数</T>
//============================================================
FFileMapping::FFileMapping(){
   _hFile = NULL;
   _hMapping = NULL;
   _position = 0;
   _bytesInBlock = 32*1024;
   _pMemory = NULL;
}

//============================================================
// <T>构造函数</T>
//
// @param fileName 文件名称
//============================================================
FFileMapping::FFileMapping(TCharC* pFileName){
   _filename = pFileName;
   Open(pFileName);
}

//============================================================
// <T>析够函数</T>
//============================================================
FFileMapping::~FFileMapping(){
   Close();
}

//============================================================
// <T>返回文件名</T>
//
// @return 文件名
//============================================================
TCharC* FFileMapping::FileName(){
   return _filename.MemoryC();
}

//============================================================
// <T>返回文件长度</T>
//
// @return 文件长度
//============================================================
TUint64 FFileMapping::Length(){
   // TODO: Unsupport
   return 0;
}

//============================================================
// <T>返回当前文件指针</T>
//
// @return 当前文件指针
//============================================================
TUint64 FFileMapping::Position(){
   return _position;
}

//============================================================
// <T>创建文件,如果文件存在则其内容清空。</T>
//
// @param fileName 文件名
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileMapping::Create(TCharC* pFileName){
   // 获得系统信息
   SYSTEM_INFO info;
   GetSystemInfo(&info);
   _bytesInBlock = info.dwAllocationGranularity * 16;
   // 打开文件
   _hFile = CreateFile(pFileName, GENERIC_WRITE, FILE_SHARE_WRITE, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL);
   if(INVALID_HANDLE_VALUE == _hFile){
      MO_FATAL(TC("Open file failure. (error=%d, file=%s)"), GetLastError(), pFileName);
   }
   // 创建文件映射
   _hMapping = CreateFileMapping(_hFile, NULL, PAGE_READWRITE, 0, 0x4000000, NULL);
   if(INVALID_HANDLE_VALUE == _hMapping){
      MO_FATAL(TC("Create file mapping failure. (error=%d, file=%s)"), GetLastError(), pFileName);
   }
   // 获得映射内存指针
   _pMemory = (TByte*)MapViewOfFile(
      _hMapping,
      FILE_MAP_ALL_ACCESS,
      (TUint32)(_position >> 32),
      (TUint32)(_position & 0xFFFFFFFF),
      _bytesInBlock);
   // 释放文件内核对象
   CloseHandle(_hFile);
   return ETrue;
}

//============================================================
// <T>打开文件</T>
//
// @param fileName 文件名
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileMapping::Open(TCharC* pFileName){
   // 获得系统信息
   SYSTEM_INFO info;
   GetSystemInfo(&info);
   _bytesInBlock = info.dwAllocationGranularity * 16;
   // 打开文件
   _hFile = CreateFile(pFileName, GENERIC_WRITE, FILE_SHARE_WRITE, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL);
   if(INVALID_HANDLE_VALUE == _hFile){
      MO_FATAL(TC("Open file failure. (error=%d, file=%s)"), GetLastError(), pFileName);
   }
   // 创建文件映射
   _hMapping = CreateFileMapping(_hFile, NULL, PAGE_READWRITE, 0, 0x4000000, NULL);
   if(INVALID_HANDLE_VALUE == _hMapping){
      MO_FATAL(TC("Create file mapping failure. (error=%d, file=%s)"), GetLastError(), pFileName);
   }
   // 获得映射内存指针
   _pMemory = (TByte*)MapViewOfFile(
         _hMapping,
         FILE_MAP_ALL_ACCESS,
         (TUint32)(_position >> 32),
         (TUint32)(_position & 0xFFFFFFFF),
         _bytesInBlock);
   // 释放文件内核对象
   CloseHandle(_hFile);
   return ETrue;
}


//============================================================
// <T>重定位流上的文件指针</T>
//
// @param position 偏移字节位置
//============================================================
TBool FFileMapping::Seek(TInt position){
   // 设置位置
   if(_position == position){
      return EFalse;
   }
   _position = position;
   // 断开内存映射
   if(!UnmapViewOfFile(_pMemory)){
      MO_FATAL(TC("Unmap file mapping failure."));
   }
   // 获得内存映射
   _pMemory = (TByte*)MapViewOfFile(
         _hMapping,
         FILE_MAP_ALL_ACCESS,
         (TUint32)(_position >> 32),
         (TUint32)(_position & 0xFFFFFFFF),
         _bytesInBlock);
   return ETrue;
}

//============================================================
// <T>从位置pData开始读取size长度的内容存在_buffer中</T>
//
// @param pData 读取数据的起始位置
// @param size 读取数据的长度
//============================================================
TInt FFileMapping::Read(TAny* pData, TSize size){
   MO_ASSERT(pData);
   TInt readed = 0;
   if(size > 0){
      //readed = fread(pData, size, 1, _handle);
   }
   return readed;
}

//============================================================
// <T>对文件写入size长度的数据</T>
//
// @param pData 要写入数据的起始位置
// @param size 要写入数据的长度
//============================================================
TInt FFileMapping::Write(TAnyC* pData, TSize size){
   MO_ASSERT(pData);
   TSize bytesWrite = -1;
   if(size > 0){
      //bytesWrite =fwrite(pData, size, 1, _handle);
   }
   return bytesWrite;
}

//============================================================
// <T>关闭文件</T>
//
// @return 打开文件成功返回ETrue,失败则返回EFalse
//============================================================
TBool FFileMapping::Close(){
   if(!UnmapViewOfFile(_pMemory)){
      MO_FATAL(TC("Unmap file mapping failure."));
   }
   if(!CloseHandle(_hMapping)){
      MO_FATAL(TC("Close handle failure."));
   }
   return ETrue;
}

MO_NAMESPACE_END

#endif // _WINDOWS
