#include "MoCrCsv.h"
#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建一个CSV文件。</T>
//============================================================
FCsvFile::FCsvFile(){
   _pSegments = MO_CREATE(FCsvSegmentDictionary);
   _pDefaultSegment = AddSegment(TC("default"));
}

//============================================================
// <T>销毁一个CSV文件。</T>
//============================================================
FCsvFile::~FCsvFile(){
   FDictionary<FCsvSegment*>::TIteratorC it = _pSegments->IteratorC();
   while(it.Next()){
      FCsvSegment* pSegment = it.Value();
      MO_DELETE(pSegment);
   }
   MO_DELETE(_pSegments);
}

//============================================================
// <T>添加一个段到文件。</T>
//
// @param pSegmentName 段的名称。
// @return 新加的段。
//============================================================
FCsvSegment* FCsvFile::AddSegment(TCharC* pSegmentName){
   MO_ASSERT(pSegmentName);
   FCsvSegment* pSegment = MO_CREATE(FCsvSegment);
   pSegment->SetName(pSegmentName);
   _pSegments->Set(pSegment->Name(), pSegment);
   _pCurrentSegment = pSegment;
   return _pCurrentSegment;
}

//============================================================
// <T>选取指定名字的表段，没有就例外，选取后可通过CurrentSegment()调用获得选取的段。</T>
//
// @param pSegmentName 段的名称。
//============================================================
void FCsvFile::SelectSegment(TCharC* pName){
   _pCurrentSegment = _pSegments->Get(pName);
}

//============================================================
// <T>获取默认段。</T>
//
// @return 默认的段。
//============================================================
FCsvSegment* FCsvFile::DefaultSegment(){
   return _pDefaultSegment;
}

//============================================================
// <T>获取当前段。</T>
//
// @return 当前的段。
//============================================================
FCsvSegment* FCsvFile::CurrentSegment(){
   return _pCurrentSegment;
}

//============================================================
// <T> 清空当前文件。</T>
//============================================================
void FCsvFile::Clear(){
   FDictionary<FCsvSegment*>::TIteratorC it = _pSegments->IteratorC();
   while(it.Next()){
      FCsvSegment* pSegment = it.Value();
      MO_DELETE(pSegment);
   }
   _pDefaultSegment = MO_CREATE(FCsvSegment);
   _pDefaultSegment->SetName(TC("default"));
   _pSegments->Set(TC("default"), _pDefaultSegment);
   MO_CLEAR(_pCurrentSegment);
}

//============================================================
// <T>加载某个CSV文件。</T>
//
// @param pFileName 文件名。
// @return 加载是否成功。
//============================================================
TBool FCsvFile::LoadFile(TCharC* pFileName){
   _filename = pFileName;
   Clear();
   FFileString* pFile = MO_CREATE(FFileString, pFileName);
   TFsName segmentName;
   TInt segmentStart = pFile->Find(TC("@segment.start"));
   if(ENotFound == segmentStart){
      TInt headsStart = pFile->Find(TC("@head"), segmentStart);
      TInt headsEnd =  pFile->Find(TC("@data"), headsStart);
      TInt linesStart = headsEnd + strlen("@data") + 1;
      TInt linesEnd = pFile->Find(TC("@footer.name"), linesStart);
      TInt footerStart = linesEnd;
      TInt footerEnd =  pFile->Find(TC("@segment.end"), footerStart);
      TString headsString = pFile->SubStrC(headsStart, headsEnd); 
      _pDefaultSegment->Heads()->Parse(headsString);
      TString linesString = pFile->SubStrC(linesStart, linesEnd);
      _pDefaultSegment->Lines()->Parse(linesString);
      TString footersString = pFile->SubStrC(footerStart, footerEnd);
      _pDefaultSegment->Footers()->Parse(footersString);
   }else{
      while(ENotFound != segmentStart){
         TInt segmentNameStart = pFile->IndexOf(' ', segmentStart);
         TInt segmentNameEnd = pFile->IndexOf('\n', segmentNameStart);
         TFsName segmentName = pFile->SubStrC(segmentNameStart + 1, segmentNameEnd);
         FCsvSegment* pSegment = NULL;
         TBool isDefault = segmentName.Equals(_pDefaultSegment->Name());
         if(isDefault){
            pSegment = _pDefaultSegment;
         }else{
            pSegment = MO_CREATE(FCsvSegment);
            pSegment->SetName((TCharC*)segmentName);
         }
         TInt headsStart = pFile->Find(TC("@head"), segmentStart);
         TInt headsEnd =  pFile->Find(TC("@data"), headsStart);
         TInt linesStart = headsEnd + strlen("@data") + 1;
         TInt linesEnd = pFile->Find(TC("@footer.name"), linesStart);
         TInt footerStart = linesEnd;
         TInt footerEnd =  pFile->Find(TC("@segment.end"), footerStart);
         TString headsString = pFile->SubStrC(headsStart, headsEnd); 
         pSegment->Heads()->Parse(headsString);
         TString linesString = pFile->SubStrC(linesStart, linesEnd);
         pSegment->Lines()->Parse(linesString);
         TString footersString = pFile->SubStrC(footerStart, footerEnd);
         pSegment->Footers()->Parse(footersString);
         if(!isDefault){
            _pSegments->Set((TCharC*)segmentName, pSegment);
         }
         segmentStart = pFile->Find(TC("@segment.start"), footerEnd);
      }
   }
   return ETrue;
}

//============================================================
// <T>保存CSV文件。</T>
//
// @param pFileName 文件名。
// @return 保存是否成功。
//============================================================
TBool FCsvFile::SaveFile(TCharC* pFileName){
   FFileStream* pFileStream = MO_CREATE(FFileStream);
   pFileStream->Create(pFileName);
   TDataOutput outFile(pFileStream);
   TBool saveSegmentHead = EFalse;
   TInt segmentCount = _pSegments->Count();
   if(segmentCount > 1){
      saveSegmentHead = ETrue;
   }
   FDictionary<FCsvSegment*>::TIteratorC it = _pSegments->IteratorC();
   TInt count = 0;
   while(it.Next()){
      FCsvSegment* pSegment = it.Value();
      pSegment->Store(outFile, saveSegmentHead);
      if(count != segmentCount -1){
         TFsName endSegment = TC("\n");
         outFile.Write((TCharC*)endSegment, endSegment.Length());
      }
      count++;
   }
   pFileStream->Flush();
   pFileStream->Close();
   MO_DELETE(pFileStream);
   return ETrue;
}

//============================================================
// <T>保存CSV文件到默认文件名。</T>
//
// @return 保存是否成功。
//============================================================
TBool FCsvFile::StoreFile(){ 
   MO_ASSERT(!_filename.IsEmpty());
   return SaveFile((TCharC*)_filename);
}

MO_NAMESPACE_END
