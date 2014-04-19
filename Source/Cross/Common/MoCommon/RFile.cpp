#include <sys/types.h>
#include <sys/stat.h>
#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include "MoCmFile.h"

#define DEFAULT_DIR_PERMISION 0755

MO_NAMESPACE_BEGIN

//============================================================
TCharC* RFile::FILE_MODE_STR[] = {TC("rb"), TC("rb+"), TC("wb"), TC("wb+"), TC("ab"), TC("ab+")};

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TBool RFile::ExistPath(TCharC* pPath){
   TBool result = EFalse;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   struct _stat st;
   if(0 == _wstat(pPath, &st)){
      result = st.st_mode & _S_IFDIR;
   }
#else
   struct _stat st;
   if(0 == _stat(pPath, &st)){
      result = st.st_mode & _S_IFDIR;
   }
#endif
#else
   if(0 == access(pPath, F_OK)){
      result = ETrue;
   }
#endif
   return result;
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TBool RFile::IsPathReadable(TCharC* pPath){
   TBool result = EFalse;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   struct _stat st;
   if(0 == _wstat(pPath, &st)){
      result = st.st_mode & _S_IREAD;
   }
#else
   struct _stat st;
   if(0 == _stat(pPath, &st)){
      result = st.st_mode & _S_IREAD;
   }
#endif
#else
   if(0 == access(pPath, R_OK)){
      result = ETrue;
   }
#endif
   return result;
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TBool RFile::IsPathWritable(TCharC* pPath){
   TBool result = EFalse;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   struct _stat st;
   if(0 == _wstat(pPath, &st)){
      result = st.st_mode & _S_IWRITE;
   }
#else
   struct _stat st;
   if(0 == _stat(pPath, &st)){
      result = st.st_mode & _S_IWRITE;
   }
#endif
#else
   if(0 == access(pPath,W_OK)){
      result = ETrue;
   }
#endif
   return result;
}

//============================================================
TBool RFile::IsPathExcutable(TCharC* pPath){
   TBool result = EFalse;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   struct _stat st;
   if(0 == _wstat(pPath, &st)){
      result = st.st_mode & _S_IEXEC;
   }
#else
   struct _stat st;
   if(0 == _stat(pPath, &st)){
      result = st.st_mode & _S_IEXEC;
   }
#endif
#else
   if(0 == access(pPath,X_OK)){
      result = ETrue;
   }
#endif
   return result;
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TBool RFile::ExistFile(TCharC* pFileName){
   TBool result = EFalse;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   struct _stat st;
   if(0 == _wstat(pFileName, &st)){
      result = st.st_mode & _S_IFMT;
   }
#else
   struct _stat st;
   if(0 == _stat(pFileName, &st)){
      result = st.st_mode & _S_IFMT;
   }
#endif
#else
   if(0 == access(pFileName,F_OK)){
      result = ETrue;
   }
#endif
   return result;
}

//============================================================
// <T>获得管道以被占用长度。</T>
//
// @return 被占用长度。
//============================================================
TInt RFile::FileSize(TCharC* pFileName){
   TInt size = -1;
   if(RFile::ExistFile(pFileName)){
#ifdef _MO_WINDOWS
      struct _stat st;
#ifdef _UNICODE
      if(0 != _wstat(pFileName, &st)){
#else
      if(0 != _stat(pFileName, &st)){
#endif
         MO_STATIC_ERROR(TC("State error:%d"), errno);
      }else{
         size = st.st_size;
      }
#else
      struct stat st;
      if(0 != stat(pFileName, &st)){
         MO_STATIC_ERROR("stat error:%d", errno);
      }else{
         size = st.st_size;
      }
#endif
   }
   return size;
}

//============================================================
// <T>创建目录。</T>
//
// @param pPath 路径字符串指针
// @return 是否创建成功。
//============================================================
TBool RFile::CreateFullDirectory(TCharC* pPath){
   TFsPath path = pPath;
   TInt index = path.IndexOf('\\');
   while(ENotFound != index){
      TFsPath parentDir = path.LeftStrC(index);
      if(!RFile::ExistPath(parentDir)){
#ifdef _MO_WINDOWS
         if(!CreateDirectory(parentDir.MemoryC(),NULL)){
            MO_STATIC_PERROR("CreateDirectory");
            return EFalse;
         }
#else
         if(-1 == mkdir(parentDir.MemoryC(), DEFAULT_DIR_PERMISION)){
            MO_STATIC_PERROR("mkdir");
            return EFalse;
         }
#endif
      }
      index = path.IndexOf('\\', index + 1);
   }
   // 不是以"\\"结尾
   if(!path.EndsWith(TC("\\"))){
#ifdef _MO_WINDOWS
      if(!path.EndsWith(TC(":"))){
         if(!CreateDirectory(pPath,NULL)){
            MO_STATIC_PERROR("CreateDirectory");
            return EFalse;
         } 
      } 
#else
      if(-1 == mkdir(pPath, DEFAULT_DIR_PERMISION)){
         MO_STATIC_PERROR("mkdir");
         return EFalse;
      }
#endif
   }
   return ETrue;
}

//============================================================
TBool RFile::CopyTo(TCharC* pSourceName, TCharC* pTargetName){
   FFileStream* pSourceFile = MO_CREATE(FFileStream);
   if(!pSourceFile->Open(pSourceName, EFileAccessMode_Read)){
      MO_STATIC_ERROR(TC("Open file falied.(name=%s)"), pSourceName);
      return EFalse;
   }
   FFileStream* pTargetFile = MO_CREATE(FFileStream);
   if(!pTargetFile->Create(pTargetName)){
      MO_STATIC_ERROR(TC("Create file falied.(name=%s)"), pTargetName);
      return EFalse;
   }
   TByte buffer[4096];
   TInt byteReaded = 0;
   while( (byteReaded = pSourceFile->Read(buffer, 4096)) > 0 ){
      pTargetFile->Write(buffer, byteReaded);
   }
   pSourceFile->Close();
   pTargetFile->Close();
   MO_DELETE(pSourceFile);
   MO_DELETE(pTargetFile);
   return ETrue;
}

MO_NAMESPACE_END
