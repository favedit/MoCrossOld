//============================================================
//   DYNAMIC LINK LIBRARY : Mo3DSExporter Project Overview
//------------------------------------------------------------
// 从3DS MAX中导出模型的动态链接库。
//------------------------------------------------------------
// Copyright From MAOCY
// 2010-05-02 (^o^)
//============================================================

#include <MoCommon.h>
#include <MoFbx2016Core.h>
#include "MpFbxDefine.h"

MO_NAMESPACE_INCLUDE;
MO_NAMESPACE_USING(FBXSDK_NAMESPACE);

void ProcessNode(FbxNode* pNode){
   TChar8C* pName = pNode->GetName();
   printf("%s\n", pName);
   //FbxNodeAttribute::EType attributeType;
   //if(pNode->GetNodeAttribute()){
   //   switch(pNode->GetNodeAttribute()->GetAttributeType()){
   //      case KFbxNodeAttribute::eMESH:
   //         //ProcessMesh(pNode);
   //         break;
   //      case KFbxNodeAttribute::eSKELETON:
   //         //ProcessSkeleton(pNode);
   //         break;
   //      case KFbxNodeAttribute::eLIGHT:
   //         //ProcessLight(pNode);
   //         break;
   //      case KFbxNodeAttribute::eCAMERA:
   //         //ProcessCamera();
   //         break;
   //   }
   //}
   TInt nodeCount = pNode->GetChildCount();
   for(TInt32 i = 0; i < nodeCount; i++){
      FbxNode* pChildNode = pNode->GetChild(i);
      ProcessNode(pChildNode);
   }
}


//============================================================
// <T>加载动态库。</T>
//
// @param hInstance 实例句柄
// @param reason 加载类型
// @param pReserved 保留字
// @return 类名
//============================================================
TInt32 main(TChar** pArguments, TInt count){
   TString fileName = TC("D:/Resource/xiong/xiong.fbx");
   //TString targetFileName = TC("D:/Microbject/MoCloud3d/Script/source/ar3/model/xiong.model");
   //TString targetConfigName = TC("D:/Microbject/MoCloud3d/Script/source/ar3/model/xiong.xml");
   TString targetFileName = TC("D:/Microbject/MoTypeScript/Script/res/model/xiong.model");
   TString targetConfigName = TC("D:/Microbject/MoTypeScript/Script/res/model/xiong.xml");
   // 初始化处理
   MoInitialize();
   // 创建管理器
   FFbxManager* pManager = MO_CREATE(FFbxManager);
   pManager->Setup();
   // 创建场景
   FFbxScene* pScene = MO_CREATE(FFbxScene);
   pScene->SetManager(pManager);
   pScene->Setup();
   pScene->LoadFile(fileName);
   // 存储资源
   FFbxResModel* pModel = MO_CREATE(FFbxResModel);
   pScene->Store(pModel);
   pModel->SaveFile(targetFileName);
   pModel->SaveConfigFile(targetConfigName);
   // 释放处理
   MO_DELETE(pScene);
   MO_DELETE(pManager);
   MoRelease();
   // getchar();
   return 0;
}
