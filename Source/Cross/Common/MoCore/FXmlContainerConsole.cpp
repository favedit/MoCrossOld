#include "MoCrConfigruation.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造配置文件容器控制台。</T>
//
// @return 配置文件容器控制台
//============================================================
FXmlContainerConsole::FXmlContainerConsole(){
   _pDocuments = MO_CREATE(FXmlDocumentDictionary);
}

//============================================================
// <T>析构配置文件容器控制台。</T>
//============================================================
FXmlContainerConsole::~FXmlContainerConsole(){
   if(!_pDocuments->IsEmpty()){
      TDictionaryIteratorC<FXmlDocument*> iterator = _pDocuments->IteratorC();
      while(iterator.Next()){
         FXmlDocument* pDocument = *iterator;
         MO_DELETE(pDocument);
      }
   }
   MO_DELETE(_pDocuments);
}

//============================================================
FXmlDocument* FXmlContainerConsole::Find(TCharC* pFileName){
   return _pDocuments->Find(pFileName);
}

//============================================================
FXmlDocument* FXmlContainerConsole::Get(TCharC* pFileName){
   return _pDocuments->Get(pFileName);
}

//============================================================
FXmlDocument* FXmlContainerConsole::Sync(TCharC* pFileName){
   FXmlDocument* pDocument = _pDocuments->Find(pFileName);
   if(NULL == pDocument){
      pDocument = MO_CREATE(FXmlDocument);
      if(pDocument->LoadFile(pFileName)){
         _pDocuments->Set(pFileName, pDocument);
      }
   }
   return pDocument;
}

MO_NAMESPACE_END
