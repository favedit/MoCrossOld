#include "MoCmFile.h"

#define MO_MAX_LINE 512

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造函数。</T>
//============================================================
FFileLines::FFileLines(){
   _pLines = MO_CREATE(FStringVector);
}

//============================================================
// <T>构造函数, 加载指定文件内容。</T>
//============================================================
FFileLines::FFileLines(TCharC* pFileName){
   MO_ASSERT(pFileName);
   _fileName = pFileName;
   _pLines = MO_CREATE(FStringVector);
   LoadFile(pFileName);
}

//============================================================
// <T>析构。</T>
//============================================================
FFileLines::~FFileLines(){
   Clear();
   MO_DELETE(_pLines);
}

//============================================================
// <T>得到指定行。</T>
//============================================================
FString* FFileLines::operator[](TInt index){
   MO_ASSERT_RANGE(index, 0, _pLines->Count());
   return _pLines->Get(index);
}

//============================================================
// <T>得到所有行。</T>
//============================================================
FStringVector* FFileLines::Lines(){
   return _pLines;
}


//============================================================
// <T>得到指定行。</T>
//============================================================
FString *FFileLines::Line(TInt index){
   MO_ASSERT_RANGE(index, 0, _pLines->Count());
   return _pLines->Get(index);
}

//============================================================
// <T>加入新行。</T>
//============================================================
void FFileLines::Push(TCharC* pLine){
   if(pLine) {
      FString *fsp;
      fsp = MO_CREATE(FString, pLine);
      _pLines->Push(fsp);
   }
}

//============================================================
// <T>清除原有的行。</T>
//============================================================
void FFileLines::Clear(){
   TInt c = _pLines->Count();
   for(TInt i = 0; i < c; ++i){
      FString* pLine = _pLines->Get(i);
      MO_DELETE(pLine);
   }
   _pLines->Clear();
}

//============================================================
// <T>从文件中载入行。</T>
//============================================================
TBool FFileLines::LoadFile(TCharC* pFileName){
   if(!pFileName){
      return EFalse;
   }
   FILE* fp;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   if(_wfopen_s(&fp, pFileName, TC("r"))){
#else
   if(fopen_s(&fp, pFileName, "r")){
#endif
      return EFalse;
   }
#else
   if(!(fp = fopen(pFileName, "r")))
      return EFalse;
#endif
   Clear();
   TChar *p;
   TChar buf[MO_MAX_LINE];
   FString *fsp;
#ifdef _UNICODE
   while(fgetws(buf, sizeof(buf), fp)) {
#else
   while(fgets(buf, sizeof(buf), fp)) {
#endif
      fsp = MO_CREATE(FString);
#ifdef _UNICODE
      while(!(p = wcsrchr(buf, '\n'))) {
#else
      while(!(p = strrchr(buf, '\n'))) {
#endif
         fsp->Append(buf);
#ifdef _UNICODE
      if(!fgetws(buf, sizeof(buf), fp))
#else
      if(!fgets(buf, sizeof(buf), fp))
#endif
            break;
      }
      if(p){
         *p = 0;
      }
#ifdef _UNICODE
      if((p = wcsrchr(buf, '\r'))) {
#else
      if((p = strrchr(buf, '\r'))) {
#endif
         *p = 0;
      }
      fsp->Append(buf);
      _pLines->Push(fsp);
   }
   fclose(fp);

   return ETrue;
}

//============================================================
// <T>将所有行存入文件，文件名称由构造中的变量指定。</T>。
//============================================================
TBool FFileLines::Store(){
   return SaveFile(_fileName.MemoryZ());
}

//============================================================
// <T>将所有行存入指定文件。</T>
//============================================================
TBool FFileLines::SaveFile(TCharC* pFileName){
   if(!pFileName){
      return EFalse;
   }
   FILE* pFile = NULL;
#ifdef _MO_WINDOWS
#ifdef _UNICODE
   if(_wfopen_s(&pFile, pFileName, TC("w"))){
#else
   if(fopen_s(&pFile, pFileName, "w")){
#endif
      return EFalse;
   }
#else
   if(!(pFile = fopen(pFileName, "w"))){
      MO_PERROR(fopen);
      return EFalse;
   }
#endif
   TVectorIteratorC<FString*> iterator = _pLines->IteratorC();
   while(iterator.Next()){
#ifdef _UNICODE
      fputws(iterator->MemoryZ(), pFile);
      fputwc('\n', pFile);
#else
      fputs(iterator->MemoryZ(), pFile);
      fputc('\n', pFile);
#endif
   }
   fclose(pFile);
   return ETrue;
}

MO_NAMESPACE_END
