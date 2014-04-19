#include "MoCrModule.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造模块控制台。</T>
//
// @return 模块控制台
//============================================================
FModuleConsole::FModuleConsole(){
   _pModules = MO_CREATE(FModuleList);
}

//============================================================
// <T>析构模块控制台。</T>
//============================================================
FModuleConsole::~FModuleConsole(){
   MO_DELETE(_pModules);
}

//============================================================
// <T>获得模块列表。</T>
//
// @return 模块列表
//============================================================
FModuleList* FModuleConsole::Modules(){
   return _pModules;
}

//============================================================
// <T>注册模块。</T>
//
// @param pModule 模块
//============================================================
void FModuleConsole::Register(FModule* pModule){
   _pModules->Push(pModule);
}

//============================================================
// <T>注销模块。</T>
//
// @param pModule 模块
//============================================================
void FModuleConsole::Unregister(FModule* pModule){
   _pModules->Remove(pModule);
}

//============================================================
// <T>初始化所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FModuleConsole::Initialize(){
   TListIteratorC<FModule*> iterator = _pModules->IteratorC();
   while(iterator.Next()){
      iterator->Initialize();
   }
   return ESuccess;
}

//============================================================
// <T>中断所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FModuleConsole::Interrupt(){
   TListIteratorC<FModule*> iterator = _pModules->IteratorC();
   while(iterator.Next()){
      iterator->Release();
   }
   return ESuccess;
}

//============================================================
// <T>重新加载所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FModuleConsole::Reload(){
   TListIteratorC<FModule*> iterator = _pModules->IteratorC();
   while(iterator.Next()){
      iterator->Reload();
   }
   return ESuccess;
}

//============================================================
// <T>卸载所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FModuleConsole::Unload(){
   TListIteratorC<FModule*> iterator = _pModules->IteratorC();
   while(iterator.Next()){
      iterator->Unload();
   }
   return ESuccess;
}

//============================================================
// <T>释放所有模块。</T>
//
// @return 处理结果
//============================================================
TResult FModuleConsole::Release(){
   TListIteratorC<FModule*> iterator = _pModules->IteratorC();
   while(iterator.Next()){
      iterator->Release();
   }
   return ESuccess;
}

MO_NAMESPACE_END
