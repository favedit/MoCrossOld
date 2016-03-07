#include "MoFbxParser.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造FBX管理器。</T>
//============================================================
FFbxManager::FFbxManager(){
   MO_CLEAR(_pFbxManager);
   MO_CLEAR(_pFbxIoSettings);
}

//============================================================
// <T>析构FBX管理器。</T>
//============================================================
FFbxManager::~FFbxManager() {
   MO_DESTORY(_pFbxIoSettings);
   MO_DESTORY(_pFbxManager);
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FFbxManager::Setup(){
   // 创建管理器
   _pFbxManager = FbxManager::Create();
   MO_ASSERT_POINTER(_pFbxManager);
   // 创建设置参数
   _pFbxIoSettings = FbxIOSettings::Create(_pFbxManager, IOSROOT);
   _pFbxManager->SetIOSettings(_pFbxIoSettings);
   // 设置扩展路径
   FbxString path = FbxGetApplicationDirectory();
   FbxString extension = "dll";
   _pFbxManager->LoadPluginsDirectory(path.Buffer(), extension.Buffer());
   // 获得版本号
   FbxManager::GetFileFormatVersion(_version.major, _version.minor, _version.revision);
   return TResult();
}

MO_NAMESPACE_END; 
