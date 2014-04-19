#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>读取型数据</T>
//
// @return 返回size长度的数据
//============================================================
TString FFileStringStream::ReadString(TInt size){
   TString str;
   // fread(&str, size, 1, _handle);
   return str;
}

//============================================================
// <T>写读取一行数据</T>
//
// @return 返回一行数据
//============================================================
TString FFileStringStream::ReadLine(){
   TString str;
   TChar *buff = NULL;
   TInt size;
   size = Length();
#ifdef _UNICODE
   str = fgetws(buff, size, _handle);
#else
   str = fgets(buff, size, _handle);
#endif
   return str;
}

//============================================================
// <T>写读取一行数据</T>
//
// @param pBuffer 行数据缓存
// @param bufferLength 缓存大小
// @return 读到数据的长度
//============================================================
 TInt FFileStringStream::ReadLine(TChar* pBuffer, TInt bufferLength){
#ifdef _UNICODE
    TChar* pLine = fgetws(pBuffer, bufferLength, _handle);
#else
    TChar* pLine = fgets(pBuffer, bufferLength, _handle);
#endif
    if(NULL == pLine){
      if(feof(_handle)){
         return 0;
      }else{
         MO_ERROR(TC("fgets error"));
         return -1;
      }
    }
    return RString::Length(pLine);
 }

//============================================================
// <T>写入数据</T>
//
// @param value 要写入的数据
//============================================================
void FFileStringStream::WriteString(const TCharC* pValue){
#ifdef _UNICODE
   // TODO: 可能不正确
   fwrite(pValue, RString::Length(pValue), 1, _handle);
#else
   fwrite(pValue, RString::Length(pValue), 1, _handle);
#endif
}

//============================================================
// <T>写入一行数据</T>
//
// @param line 要写入的数据
//============================================================
void FFileStringStream::WriteLine(const TCharC* pLine){
#ifdef _UNICODE
   // TODO: 可能不正确
   fwrite(pLine, RString::Length(pLine), 1, _handle);
#else
   fwrite(pLine, RString::Length(pLine), 1, _handle);
#endif
   fputs("\n", _handle);
}

MO_NAMESPACE_END
