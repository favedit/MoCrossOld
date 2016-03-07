#include "MoFbxParser.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造FBX场景。</T>
//============================================================
FFbxScene::FFbxScene() {
   MO_CLEAR(_pManager);
   MO_CLEAR(_pFbxScene);
   _pMeshs = MO_CREATE(FFbxMeshs);
}

//============================================================
// <T>析构FBX场景。</T>
//============================================================
FFbxScene::~FFbxScene() {
   // 释放所有网格
   if(_pMeshs != NULL){
      TInt count = _pMeshs->Count();
      for(TInt n = 0; n < count; n++){
         FFbxMesh* pMesh = _pMeshs->Get(n);
         MO_DELETE(pMesh);
      }
      MO_DELETE(_pMeshs);
   }
   MO_DESTORY(_pFbxScene);
   MO_CLEAR(_pManager);
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FFbxScene::Setup(){
   FbxManager* pFbxManager = _pManager->FbxManager();
   _pFbxScene = FbxScene::Create(pFbxManager, "");
   return ESuccess;
}

//============================================================
// <T>网格节点处理。</T>
//============================================================
TResult FFbxScene::ProcessMesh(FbxNode * pFbxNode){
   // 获得网格
   FbxMesh* pFbxMesh = pFbxNode->GetMesh();
   if(pFbxMesh == NULL){
      return EFailure;
   }
   // 创建网格
   FFbxMesh* pMesh = MO_CREATE(FFbxMesh);
   pMesh->SetScene(this);
   pMesh->SetNodeHandle(pFbxNode);
   pMesh->Setup();
   _pMeshs->Push(pMesh);
   return ESuccess;
}

//============================================================
// <T>节点处理。</T>
//============================================================
TResult FFbxScene::ProcessNode(FbxNode * pFbxNode){
   // 处理结点信息
   TString name;
   name.Assign8(pFbxNode->GetName());
   FbxNodeAttribute::EType typeCd = FbxNodeAttribute::eUnknown;
   TCharC* pTypeName = NULL;
   FbxNodeAttribute* pFbxNodeAttribute = pFbxNode->GetNodeAttribute();
   if(pFbxNodeAttribute != NULL){
      typeCd = pFbxNodeAttribute->GetAttributeType();
      pTypeName = RFbxEnum::ParseName(typeCd);
   }
   MO_DEBUG(TC("Process node. (name=%s, type=%d[%s])"), (TCharC*)name, typeCd, pTypeName);
   // 处理结点类型
   switch(typeCd){
      case FbxNodeAttribute::eMesh:
         ProcessMesh(pFbxNode);
         break;
      case FbxNodeAttribute::eSkeleton:
         //ProcessSkeleton(pFbxNode);
         break;
      case FbxNodeAttribute::eLight:
         //ProcessLight(pFbxNode);
         break;
      case FbxNodeAttribute::eCamera:
         //ProcessCamera(pFbxNode);
         break;
   }
   // 处理子结点集合
   TInt nodeCount = pFbxNode->GetChildCount();
   for(TInt32 i = 0; i < nodeCount; i++){
      FbxNode* pChildNode = pFbxNode->GetChild(i);
      ProcessNode(pChildNode);
   }
   return ESuccess;
}

//============================================================
// <T>序列化数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxScene::Store(FFbxResModel* pResModel){
   MO_ASSERT_POINTER(pResModel);
   // 设置属性
   pResModel->SetCode(_code);
   // 设置网格集合
   TInt meshCount = _pMeshs->Count();
   for(TInt n = 0; n < meshCount; n++){
      FFbxMesh* pFbxMesh = _pMeshs->Get(n);
      // 创建网格
      FFbxResModelMesh* pResMesh = MO_CREATE(FFbxResModelMesh);
      pFbxMesh->Store(pResMesh);
      pResModel->Meshs()->Push(pResMesh);
   }
   return ESuccess;
}

//============================================================
// <T>加载文件。</T>
//
// @param pFileName 文件名称
// @return 处理结果
//============================================================
TResult FFbxScene::LoadFile(TCharC* pFileName){
   MO_ASSERT_POINTER(pFileName);
   // 设置名称
   TFileInfo fileInfo = pFileName;
   _code = fileInfo.Code();
   // 获得参数
   FbxManager* pFbxManager = _pManager->FbxManager();
   FbxIOSettings* pFbxIoSettings = _pManager->FbxIoSettings();
   SFbxSdkVersion& managerVersion = _pManager->Version();
   TString8 fileName;
   fileName.Assign16(pFileName);
   // 创建导入器
   FbxImporter* pImporter = FbxImporter::Create(pFbxManager, NULL);
   TBool initializeStatusCd = pImporter->Initialize(fileName.MemoryC(), -1, pFbxIoSettings);
   if(!initializeStatusCd){
      MO_FATAL(TC("Open fbx file failure. (file_name={1})"), pFileName);
   }
   // 获得版本号
   SFbxSdkVersion version;
   pImporter->GetFileVersion(version.major, version.minor, version.revision);
   if(managerVersion.major < version.major){
      MO_FATAL(TC("Open fbx major version failure. (file_name={1})"), pFileName);
   }else if (managerVersion.minor < version.minor) {
      MO_FATAL(TC("Open fbx minor version failure. (file_name={1})"), pFileName);
   }
   // 导入场景
   _pFbxScene->Clear();
   TBool importStatusCd = pImporter->Import(_pFbxScene);
   if (!importStatusCd) {
      MO_FATAL(TC("Import fbx scene failure. (file_name={1})"), pFileName);
   }
   // 释放导入器
   MO_DESTORY(pImporter);
   // 处理节点
   FbxNode* pRootNode = _pFbxScene->GetRootNode();
   ProcessNode(pRootNode);
   return ESuccess;
}

MO_NAMESPACE_END;
