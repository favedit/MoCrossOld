#include "MoFlFile.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>日志模块构造器。</T>
//
// @return 无返回值
//============================================================
FLoggerModule::FLoggerModule(){
   _name = "Module.Logger";
}

//============================================================
// <T>日志模块析构器。</T>
//
// @return 无返回值
//============================================================
FLoggerModule::~FLoggerModule(){
}

//============================================================
// <T>加载日志模块配置。</T>
//
// @param FXmlNode* pConfig 配置文件的xml根结点
// @return 加载首否成功
//============================================================
TResult FLoggerModule::OnLoadConfig(FXmlNode* pConfig){
   TXmlNodeIteratorC iterator = pConfig->NodeIteratorC();
   while(iterator.Next()){
      if(iterator->IsName("Property")){
         TFsPath path = REnvironment::MappingPath("%s",  iterator->Text());
         _path.Assign(path.MemoryC());
      }else if(iterator->IsName("Writer")){
         // 创建缓冲日志
         FBufferedLoggerWriter *pWriter = MO_CREATE(FBufferedLoggerWriter);
         TBool forceFlush = iterator->GetAsBool("force_flush", EFalse);
         TInt code = iterator->GetAsInt("id");
         TCharC* pName = iterator->Get("name");
         TInt capacity = iterator->GetAsInt("capacity");
         TFileInfo path = (TCharC*)_path;
         MO_DEBUG("Read logger writer config. (code=%d, name=%s, capacity=%d, path=%s)",
               code, pName, capacity, path.FullName());
         pWriter->SetCode(code);
         pWriter->SetName(pName);
         pWriter->SetCapacity(capacity);
         pWriter->SetPath(path.FullName());
         pWriter->SetForceToFlush(forceFlush);
         RLoggerManager::Instance().Register(pWriter);
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
