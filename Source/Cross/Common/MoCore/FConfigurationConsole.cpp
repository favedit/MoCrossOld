#include <MoCmSystem.h>
#include "MoCrConfigruation.h"

#define MO_TAG_INCLUDE   TC("Include")
#define MO_TAG_COMPONENT TC("Component")
#define MO_PTY_FILE      TC("file")
#define MO_PTY_NAME      TC("name")

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造可设置对象控制台。</T>
//
// @return 可设置对象控制台
//============================================================
FConfigurationConsole::FConfigurationConsole(){
   _pConfigurations = MO_CREATE(FConfiguraList);
   _pNodes = MO_CREATE(FXmlNodeDictionary);
   _pDocuments = MO_CREATE(FXmlDocumentList);
}

//============================================================
// <T>析构可设置对象控制台。</T>
//============================================================
FConfigurationConsole::~FConfigurationConsole(){
   MO_DELETE(_pNodes);
   MO_DELETE(_pConfigurations);
   if(!_pDocuments->IsEmpty()){
      TListIteratorC<FXmlDocument*> iterator =  _pDocuments->IteratorC();
      while(iterator.Next()){
         FXmlDocument* pDocument = *iterator;
         MO_DELETE(pDocument);
      }
   }
   MO_DELETE(_pDocuments);
}

//============================================================
// <T>注册可设置对象。</T>
//
// @param pConfig 设置对象
//============================================================
void FConfigurationConsole::Register(IConfiguration* pConfiguration){
   _pConfigurations->Push(pConfiguration);
}

//============================================================
// <T>注销可设置对象。</T>
//
// @param pConfig 可设置对象
//============================================================
void FConfigurationConsole::Unregister(IConfiguration* pConfiguration){
   _pConfigurations->Remove(pConfiguration);
}

//============================================================
// <T>加载设置文件。</T>
//
// @param pFileName 文件名称
//============================================================
TBool FConfigurationConsole::LoadFile(TCharC* pFileName){
   // 打开文件
   FXmlDocument* pDocument = MO_CREATE(FXmlDocument);
   if(!pDocument->LoadFile(pFileName)){
      // 加载文件失败。
      MO_PFATAL(LoadFile);
      return EFalse;
   }
   _pDocuments->Push(pDocument);
   // 读取设置节点
   if(pDocument->HasNode()){
      TXmlNodeIteratorC iterator = pDocument->Root()->NodeIteratorC();
      while(iterator.Next()){
         if(iterator->IsName(MO_TAG_INCLUDE)){
            // 加载其它设置
            TCharC* pName = iterator->Get(MO_PTY_FILE);
            TCharC* pFileName = REnvironment::MappingPath(pName);
            LoadFile(pFileName);
         }else if(iterator->IsName(MO_TAG_COMPONENT)){
            // 加载组件设置
            TCharC* pName = iterator->Get(MO_PTY_NAME);
            if(_pNodes->Contains(pName)){
               MO_FATAL(TC("Duplicate component define. (file=%s, name=%s)"), pFileName, pName);
               return EFalse;
            }
            _pNodes->Set(pName, *iterator);
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>卸载文件信息。</T>
//
// @return 处理结果
//============================================================
TBool FConfigurationConsole::UnloadFiles(){
   // 卸载所有文档对象
   if(!_pDocuments->IsEmpty()){
      TListIteratorC<FXmlDocument*> iterator =  _pDocuments->IteratorC();
      while(iterator.Next()){
         FXmlDocument* pDocument = *iterator;
         MO_DELETE(pDocument);
      }
      _pDocuments->Clear();
   }
   // 清空节点集合
   _pNodes->Clear();
   return ETrue;
}

//============================================================
// <T>加载设置信息。</T>
//
// @param pFileName 文件名称
//============================================================
TBool FConfigurationConsole::LoadConfiguration(TCharC* pFileName){
   // 加载设置文件
   LoadFile(pFileName);
   // 加载设置信息
   if(!_pConfigurations->IsEmpty()){
      TListIteratorC<IConfiguration*> iterator = _pConfigurations->IteratorC();
      while(iterator.Next()){
         IConfiguration* pConfiguration = *iterator;
         TCharC* pName = pConfiguration->Name();
         MO_ASSERT(pName);
         FXmlNode* pConfig = _pNodes->Find(pName);
         if(pConfig != NULL){
            MO_DEBUG(TC("Load component config. (name=%s)"), pName);
            pConfiguration->LoadConfig(pConfig);
         }
      }
   }
   // 卸载文件
   UnloadFiles();
   return ETrue;
}

MO_NAMESPACE_END
