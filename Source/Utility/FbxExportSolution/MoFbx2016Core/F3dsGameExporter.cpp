#include "MoFbxParser.h"

MO_NAMESPACE_BEGIN;

/*
//============================================================
// <T>构造游戏导出器。</T>
//============================================================
F3dsGameExporter::F3dsGameExporter(IScene* piScene, TimeValue time, Interface* piInterface) :
      M3dsExporter(piScene, time, piInterface){
   _pNodes = MO_CREATE(FGameNodeVector, 1024);
   _pMaterials = MO_CREATE(FGameMaterialVector, 256);
   _pBones = MO_CREATE(FGameBoneVector, 256);
   _pGeometrys = MO_CREATE(FGameGeometryVector);
   _pGeometrySet = MO_CREATE(FGameGeometrySet);
}

//============================================================
// <T>析构游戏导出器。</T>
//============================================================
F3dsGameExporter::~F3dsGameExporter(){
   MO_DELETE(_pNodes);
   MO_DELETE(_pMaterials);
   MO_DELETE(_pBones);
   MO_DELETE(_pGeometrys);
   MO_DELETE(_pGeometrySet);
}

//============================================================
void F3dsGameExporter::ErrorProc(IGameError error){
   FXmlNode* pErrorNode = _pLoggerNode->CreateNode(TC("Error"));
   TFsLogger logger;
   TCharC* pMessage = GetLastIGameErrorText();
   logger.AppendFormat(TC("Raise error. (code=%d, message=%s)"), error, pMessage);
   pErrorNode->SetText(logger);
}

//============================================================
IGameScene* F3dsGameExporter::GameScene(){
   return _piGameScene;
}

//============================================================
IGameConversionManager* F3dsGameExporter::GameConversionManager(){
   return _piGameConversionManager;
}

//============================================================
FGameNodeVector* F3dsGameExporter::GameNodes(){
   return _pNodes;
}

//============================================================
FGameMaterialVector* F3dsGameExporter::GameMaterials(){
   return _pMaterials;
}

//============================================================
FGameBoneVector* F3dsGameExporter::GameBones(){
   return _pBones;
}

//============================================================
// <T>存储所有节点结构。</T>
// <P>如果节点接口没有获得过Object接口，则无法获得Control接口。</P>
//
// @param pNode 配置节点
// @param piNode 游戏节点
//============================================================
void F3dsGameExporter::ConvertStruct(FXmlNode* pNode, IGameNode* piNode){
   // 存储节点集合
   TInt nodeId = piNode->GetNodeID();
   _pNodes->ExtendSet(nodeId, piNode);
   // 获得游戏对象
   INode* piMaxNode = piNode->GetMaxNode();
   // 存储属性内容
   pNode->SetInt(TC("id"), nodeId);
   if(NULL != piMaxNode){
      TInt buffId = piMaxNode->GetGBufID();
      if(buffId > 0){
         pNode->SetInt(TC("g_buff_id"), buffId);
      }
   }
   TFsName name = piNode->GetName();
   pNode->Set(TC("name"), name);
   if(piNode->IsTarget()){
      pNode->SetBool(TC("target"), true);
   }
   if(piNode->IsGroupOwner()){
      pNode->SetBool(TC("is_group"), true);
   }
   if(piNode->IsNodeHidden()){
      pNode->SetBool(TC("is_hidden"), true);
   }
   // 是否摄像机
   if(name.StartsWith(TC("cl_"))){
      _pBones->Push(piNode);
   }
   // 处理所有子节点
   TInt32 count = piNode->GetChildCount();
   if(count > 0){
      for(TInt32 n = 0; n < count; n++){
         IGameNode* piChildren = piNode->GetNodeChild(n);
         if(NULL != piChildren){
            ConvertStruct(pNode->CreateNode(TC("Node")), piChildren);
         }
      }
   }
}

//============================================================
void F3dsGameExporter::ConvertSubTexture(FXmlNode* pTextureNode, IGameMaterial* piMaterial, Texmap* pTexmap){
   //CStr className;
   //pTexmap->GetClassName(className);
   //pTextureNode->Set(TC("class"), className);
   pTextureNode->Set(TC("name"), pTexmap->GetName());
   pTextureNode->Set(TC("node_name"), pTexmap->NodeName());
   pTextureNode->Set(TC("full_name"), pTexmap->GetFullName());
   // 查询子节点
   //int count = pTexmap->NumSubTexmaps();
   //pTextureNode->SetInt("count", count);
   /*if(count > 0){
      FXmlNode* pSubTextureListNode = pTextureNode->CreateNode("TextureList");
      for(int n = 0; n < count; n++){
         Texmap* pSubTexmap = pTexmap->GetSubTexmap(n);
         FXmlNode* pSubTextureNode = pSubTextureListNode->CreateNode("Texture");
         ConvertSubTexture(pSubTextureNode, piMaterial, pSubTexmap);
      }
   }*
}

//============================================================
// <T>转换纹理贴图。</T>
//
// @param pTextureNode 纹理节点
// @param piMaterial 材质接口
// @param piTextureMap 纹理接口
//============================================================
void F3dsGameExporter::ConvertTexture(FXmlNode* pTextureNode, IGameMaterial* piMaterial, IGameTextureMap* piTextureMap){
   TInt32 slotIndex = piTextureMap->GetStdMapSlot();
   TFsName className = piTextureMap->GetClassName();
   // 设置属性内容
   pTextureNode->Set(TC("class"), className);
   pTextureNode->Set(TC("name"), (TCharC*)piTextureMap->GetMaxTexmap()->GetName());
   pTextureNode->Set(TC("slot_name"), (TCharC*)piMaterial->GetMaxMaterial()->GetSubTexmapSlotName(slotIndex));
   pTextureNode->Set(TC("texture_name"), piTextureMap->GetTextureName());
   pTextureNode->Set(TC("texture_class"), piTextureMap->GetTextureClass());
   pTextureNode->SetInt(TC("map_channel"), piTextureMap->GetMapChannel());
   pTextureNode->SetInt(TC("std_map_slot"), piTextureMap->GetStdMapSlot());
   // 设置文件名称
   if(className.Equals(TC("Bitmap"))){
      pTextureNode->Set(TC("file"), piTextureMap->GetBitmapFileName());
   }
   // 获得剪裁区
   if(piTextureMap->IsEntitySupported()){
      // 获得剪裁区(ClipU)
      IGameProperty* piClipU = piTextureMap->GetClipUData();
      if(NULL != piClipU){
         R3dsExporter::StoreProperty(pTextureNode->CreateNode(TC("ClipU")), TC("value"), piClipU);
      }
      // 获得剪裁区(ClipV)
      IGameProperty* piClipV = piTextureMap->GetClipVData();
      if(NULL != piClipV){
         R3dsExporter::StoreProperty(pTextureNode->CreateNode(TC("ClipV")), TC("value"), piClipV);
      }
      // 获得剪裁区(ClipW)
      IGameProperty* piClipW = piTextureMap->GetClipWData();
      if(NULL != piClipW){
         R3dsExporter::StoreProperty(pTextureNode->CreateNode(TC("ClipW")), TC("value"), piClipW);
      }
      // 获得剪裁区(ClipH)
      IGameProperty* piClipH = piTextureMap->GetClipHData();
      if(NULL != piClipH){
         R3dsExporter::StoreProperty(pTextureNode->CreateNode(TC("ClipH")), TC("value"), piClipH);
      }
   }
   // 获取属性
   IPropertyContainer* piPropertyContainer = piTextureMap->GetIPropertyContainer();
   if(NULL != piPropertyContainer){
      R3dsExporter::StoreProperties(pTextureNode, TC("PropertyList"), piPropertyContainer);
   }
   Texmap* pTexmap = piTextureMap->GetMaxTexmap();
   if(NULL != pTexmap){
      int count = pTexmap->NumSubTexmaps();
      if(count > 0){
         FXmlNode* pSubTextureListNode = pTextureNode->CreateNode(TC("TextureList"));
         pSubTextureListNode->SetInt(TC("count"), count);
         for(int n = 0; n < count; n++){
            Texmap* pSubTexmap = pTexmap->GetSubTexmap(n);
            if(NULL != pSubTexmap){
               FXmlNode* pSubTextureNode = pSubTextureListNode->CreateNode(TC("Texture"));
               ConvertSubTexture(pSubTextureNode, piMaterial, pSubTexmap);
            }
         }
      }
   }
}

//============================================================
// <T>转换材质信息。</T>
//
// @param pMaterialNode 材质节点
// @param piMaterial 材质信息
//============================================================
void F3dsGameExporter::ConvertMaterial(FXmlNode* pMaterialNode, IGameMaterial* piMaterial){
   // 追加材质
   _pMaterials->Push(piMaterial);
   // 获得材质
   //pMaterialNode->SetInt("id", _pMaterials->IndexOf(piMaterial));
   pMaterialNode->Set(TC("class"), piMaterial->GetMaterialClass());
   pMaterialNode->Set(TC("name"), piMaterial->GetMaterialName());
   // 存储属性集合
   IPropertyContainer* piPropertyContainer = piMaterial->GetIPropertyContainer();
   if(NULL != piPropertyContainer){
      R3dsExporter::StoreProperties(pMaterialNode, TC("PropertyList"), piPropertyContainer);
   }
   // 获得漫反射颜色
   IGameProperty* piDiffuse = piMaterial->GetDiffuseData();
   if(NULL != piDiffuse){
      R3dsExporter::StoreColorProperty(pMaterialNode->CreateNode(TC("Diffuse")), piDiffuse);
   }
   // 获得环境颜色
   IGameProperty* piAmbient = piMaterial->GetAmbientData();
   if(NULL != piAmbient){
      R3dsExporter::StoreColorProperty(pMaterialNode->CreateNode(TC("Ambient")), piAmbient);
   }
   // 获得高光颜色
   IGameProperty* piSpecular = piMaterial->GetSpecularData();
   if(NULL != piSpecular){
      R3dsExporter::StoreColorProperty(pMaterialNode->CreateNode(TC("Specular")), piSpecular);
   }
   IGameProperty* piSpecularLevel = piMaterial->GetSpecularLevelData();
   if(NULL != piSpecularLevel){
      R3dsExporter::StoreProperty(pMaterialNode->CreateNode(TC("SpecularLevel")), TC("value"), piSpecularLevel);
   }
   // 获得自发光颜色
   IGameProperty* piEmissive = piMaterial->GetEmissiveData();
   if(NULL != piEmissive){
      R3dsExporter::StoreColorProperty(pMaterialNode->CreateNode(TC("Emissive")), piEmissive);
   }
   IGameProperty* piEmissiveAmt = piMaterial->GetEmissiveAmtData();
   if(NULL != piEmissiveAmt){
      R3dsExporter::StoreProperty(pMaterialNode->CreateNode(TC("EmissiveAmt")), TC("value"), piEmissiveAmt);
   }
   // 获得光泽度
   IGameProperty* piGlossiness = piMaterial->GetGlossinessData();
   if(NULL != piGlossiness){
      R3dsExporter::StoreProperty(pMaterialNode->CreateNode(TC("Glossiness")), TC("value"), piGlossiness);
   }
   // 获得不透明度 (百分比)
   IGameProperty* piOpacity = piMaterial->GetOpacityData();
   if(NULL != piOpacity){
      R3dsExporter::StoreProperty(pMaterialNode->CreateNode(TC("Opacity")), TC("value"), piOpacity);
   }
   // 获取纹理列表
   TInt32 textureCount = piMaterial->GetNumberOfTextureMaps();
   if(textureCount > 0){
      FXmlNode* pTexturesNode = pMaterialNode->CreateNode(TC("Textures"));
      for(TInt32 n = 0; n < textureCount; n++){
         IGameTextureMap* piTextureMap = piMaterial->GetIGameTextureMap(n);
         if(NULL != piTextureMap){
            FXmlNode* pTextureNode = pTexturesNode->CreateNode(TC("Texture"));
            pTextureNode->SetInt(TC("id"), n);
            ConvertTexture(pTextureNode, piMaterial, piTextureMap);
         }
      }
   }
   // 获得多材质材质
   if(piMaterial->IsMultiType()){
      TInt32 subCount = piMaterial->GetSubMaterialCount();
      for(TInt32 n = 0; n < subCount; n++){
         IGameMaterial* piSubMaterial = piMaterial->GetSubMaterial(n);
         if(NULL != piSubMaterial){
            FXmlNode* pSubMaterialNode = pMaterialNode->CreateNode(TC("Material"));
            pSubMaterialNode->SetInt(TC("id"), n);
            ConvertMaterial(pSubMaterialNode, piSubMaterial);
         }
      }
   }
}

//============================================================
// <T>导出所有材质信息。</T>
//============================================================
void F3dsGameExporter::ConvertMaterials(FXmlNode* pMaterialsNode){
   TInt32 count = _piGameScene->GetRootMaterialCount();
   pMaterialsNode->SetInt(TC("count"), count);
   for(TInt32 n = 0; n < count; n++){
      IGameMaterial* piMaterial = _piGameScene->GetRootMaterial(n);
      if(NULL != piMaterial){
         FXmlNode* pMaterialNode = pMaterialsNode->CreateNode(TC("Material"));
         ConvertMaterial(pMaterialNode, piMaterial);
      }
   }
}

//============================================================
// <T>转换网格信息。</T>
//
// @param pMeshNode 网格节点
//============================================================
void F3dsGameExporter::ConvertMesh(FXmlNode* pMeshNode){
   Tab<IGameNode*> piMeshNodes = _piGameScene->GetIGameNodeByType(IGameObject::IGAME_MESH);
   //............................................................
   // 获得有效网格集合
   TInt32 count = piMeshNodes.Count();
   for(TInt32 n = 0; n < count; n++){
      IGameNode* piGameNode = piMeshNodes[n];
      // 获得关联对象
      IGameObject* piObject = piGameNode->GetIGameObject();
      if(piObject == NULL){
         continue;
      }
      if(!piObject->IsRenderable()){
         continue;
      }
      // 获得网格
      IGameMesh* piSubMesh = (IGameMesh*)piObject;
      if(!piSubMesh->InitializeData()){
         continue;
      }
      if(!piSubMesh->InitializeBinormalData()){
         continue;
      }
      // 检查是否为骨骼几何体
      TFsText nodeName = piGameNode->GetName();
      if(nodeName.StartsWith(MO_MESH_BONE_FIX)){
         continue;
      }
      // 优化顶点法线
      piSubMesh->SetCreateOptimizedNormalList();
      // 存储节点
      TInt32 nodeId = piGameNode->GetNodeID();
      if(!_pGeometrys->Contains(piGameNode)){
         _pGeometrys->Push(piGameNode);
         _pGeometrySet->Set(nodeId, piGameNode);
      }
   }
   //............................................................
   // 输出有效网格集合
   TInt validCount = _pGeometrys->Count();
   pMeshNode->SetInt(TC("count"), validCount);
   _pFileStream->WriteInt32((TInt32)validCount);
   for(TInt n = 0; n < validCount; n++){
      IGameNode* piGameNode = _pGeometrys->Get(n);
      IGameMesh* piSubMesh = (IGameMesh*)piGameNode->GetIGameObject();
      // 存储网格信息
      F3dsGeometryExporter* pGeometryExporter = MO_CREATE(F3dsGeometryExporter, this, piGameNode, piSubMesh);
      pGeometryExporter->Convert(pMeshNode->CreateNode(TC("Geometry")));
      pGeometryExporter->Serialize(_pFileStream);
      MO_DELETE(pGeometryExporter);
   }
}

//============================================================
// <T>转换网格骨骼。</T>
//
// @param pParentNode 父节点
// @param piNode 游戏节点
//============================================================
void F3dsGameExporter::ConvertSkeleton(FXmlNode* pParentNode, IGameNode* piNode){
   FXmlNode* pBoneNode = pParentNode;
   // 处理骨骼节点
   if(_pBones->Contains(piNode)){
      pBoneNode = pParentNode->CreateNode(TC("Bone"));
      TInt nodeId = piNode->GetNodeID();
      pBoneNode->SetInt(TC("bone_id"), nodeId);
      pBoneNode->Set(TC("name"), piNode->GetName());
   }
   // 处理所有子节点
   TInt32 count = piNode->GetChildCount();
   for(TInt32 n = 0; n < count; n++){
      // 获得子节点
      IGameNode* piChild = piNode->GetNodeChild(n);
      if(piChild != NULL){
         ConvertSkeleton(pBoneNode, piChild);
      }
   }
}

//============================================================
// <T>存储场景信息。</T>
//
// @param pScene 配置节点
//============================================================
void F3dsGameExporter::ConvertScene(FXmlNode* pScene){
   // 存储场景信息
   TInt32 topCount = _piGameScene->GetTopLevelNodeCount();
   pScene->SetInt(TC("start_time"), _piGameScene->GetSceneStartTime());
   pScene->SetInt(TC("end_time"), _piGameScene->GetSceneEndTime());
   pScene->SetInt(TC("tick"), _piGameScene->GetSceneTicks());
   pScene->Set(TC("file"), _piGameScene->GetSceneFileName());
   // 存储材质信息
   ConvertMaterials(_pMaterialsNode);
   // 存储网格信息
   ConvertMesh(_pMeshNode);
   // 存储骨骼信息
   _pSkeletonNode->SetInt(TC("bone_count"), _pBones->Count());
   for(TInt32 n = 0; n < topCount; n++){
      IGameNode* piNode = _piGameScene->GetTopLevelNode(n);
      if(piNode != NULL){
         ConvertSkeleton(_pSkeletonNode, piNode);
      }
   }
   // 存储所有骨骼动画
   Interval range = _piInterface->GetAnimRange();
   TInt tick = GetTicksPerFrame();
   TInt frameCount = range.Duration() / tick;
   _pAnimationNode->SetInt(TC("frame_tick"), tick);
   _pAnimationNode->SetInt(TC("frame_count"), frameCount);
   _pAnimationNode->SetInt(TC("frame_start"), range.Start());
   _pAnimationNode->SetInt(TC("frame_end"), range.End());
   _pAnimationNode->SetInt(TC("bone_count"), _pBones->Count());
   if(!_pBones->IsEmpty()){
      TVectorIteratorC<IGameNode*> iterator = _pBones->IteratorC();
      while(iterator.Next()){
         F3dsGameTrackExporter* pTrackExporter = MO_CREATE(F3dsGameTrackExporter, this, *iterator);
         pTrackExporter->Convert(_pAnimationNode->CreateNode(TC("Track")));
         MO_DELETE(pTrackExporter);
      }
   }
}

//============================================================
// <T>转换场景为配置节点。</T>
//
// @param pConfig 配置节点
//============================================================
void F3dsGameExporter::Convert(){
   // 创建输出文件流
   TInt find = _fileName.LastFind(TC("."));
   TFsFileName fileName = _fileName;
   fileName.SetLength(find);
   fileName.Append(TC(".msh"));
   MO_INFO(TC("Mesh data file export. (file_name=%s)"), (TCharC*)fileName);
   // 设置错误回调
   //SetErrorCallBack(this);
   // 设置坐标系
   _piGameConversionManager = GetConversionManager();
   _piGameConversionManager->SetCoordSystem(IGameConversionManager::IGAME_D3D);
   // 初始化游戏场景
   _piGameScene = GetIGameInterface();
   _piGameScene->InitialiseIGame(_piRootNode, true);
   _piGameScene->SetStaticFrame(0);
   TInt32 topCount = _piGameScene->GetTopLevelNodeCount();
   // 存储节点结构
   TInt32 total = _piGameScene->GetTotalNodeCount();
   _pStructNode->SetInt(TC("total"), total);
   for(TInt32 n = 0; n < topCount; n++){
      IGameNode* piNode = _piGameScene->GetTopLevelNode(n);
      if(piNode != NULL){
         ConvertStruct(_pStructNode->CreateNode(TC("Node")), piNode);
      }
   }
   // 存储场景信息
   ConvertScene(_pSceneNode);
   // 关闭文件流
   _pFileStream->SaveFile(fileName);
   // 释放内存
   _piGameScene->ReleaseIGame();
   MO_CLEAR(_piGameScene);
}
*/
MO_NAMESPACE_END;
