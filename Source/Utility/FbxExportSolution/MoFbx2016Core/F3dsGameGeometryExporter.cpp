#include "MoFbxParser.h"

MO_NAMESPACE_BEGIN;

/*
//============================================================
F3dsGeometryExporter::F3dsGeometryExporter(F3dsGameExporter* pExporter, IGameNode* piNode, IGameMesh* piSubMesh){
   _pExporter = pExporter;
   _piNode = piNode;
   _piSubMesh = piSubMesh;
}

//============================================================
F3dsGeometryExporter::~F3dsGeometryExporter(){
}

//============================================================
// <T>存储骨骼动画。</T>
//
// @param pTrackNode 配置信息
//============================================================
TBool F3dsGeometryExporter::SerializeTrack(IDataOutput* pOutput){
   // 创建骨骼
   R3dsExporter::SerializeMatrix(pOutput, _piNode->GetObjectTM(0));
   R3dsExporter::SerializeMatrix(pOutput, _piNode->GetLocalTM(0));
   R3dsExporter::SerializeMatrix(pOutput, _piNode->GetWorldTM(0));
   // 获得控件
   TBool hasFrame = EFalse;
   IGameControl* piControl = _piNode->GetIGameControl();
   if(NULL != piControl){
      // 获得控制信息
      bool positionAnimated = piControl->IsAnimated(IGAME_POS);
      int keyPositionCount = piControl->GetIGameKeyCount(IGAME_POS);
      bool rotationAnimated = piControl->IsAnimated(IGAME_ROT);
      int keyRotationCount = piControl->GetIGameKeyCount(IGAME_ROT);
      bool scaleAnimated = piControl->IsAnimated(IGAME_SCALE);
      int keyScaleCount = piControl->GetIGameKeyCount(IGAME_SCALE);
      // 检查是否含有动画
      //if(positionAnimated || rotationAnimated || scaleAnimated){
         // 建立默认的所有屏
         IGameKeyTab keys;
         if(piControl->GetFullSampledKeys(keys, 1, IGAME_TM, false)){
            TInt32 count = keys.Count();
            pOutput->WriteInt32(count);
            for(TInt n = 0; n < count; n++){
               // 创建一帧信息
               IGameKey& key = keys[n];
               pOutput->WriteInt32(key.t);
               // 设置对象矩阵
               R3dsExporter::SerializeMatrix(pOutput, _piNode->GetObjectTM(key.t));
               R3dsExporter::SerializeMatrix(pOutput, _piNode->GetLocalTM(key.t));
               R3dsExporter::SerializeMatrix(pOutput, _piNode->GetWorldTM(key.t));
            }
            hasFrame = ETrue;
         }
      //}
   }
   if(!hasFrame){
      pOutput->WriteInt32(0);
   }
   return ETrue;
}

//============================================================
// <T>存储蒙皮的点对应的权重。</T>
// <P>使用法线点，不使用顶点。</P>
//
// @param pSkinNode 配置节点
// @param piSkin 皮肤接口
//============================================================
TBool F3dsGeometryExporter::SerializeSkin(IDataOutput* pOutput, IGameSkin* piSkin){
   // 获得基础属性
   TInt vertexCount = _piSubMesh->GetNumberOfVerts();
   TInt32 boneCount = piSkin->GetTotalBoneCount();
   pOutput->WriteInt32(boneCount);
   pOutput->WriteInt32(piSkin->GetTotalSkinBoneCount());
   //------------------------------------------------------------
   // 计算蒙皮关联的顶点数量
   TInt32 validCount = 0;
   for(TInt32 n = 0; n < vertexCount; n++){
      // 设置骨骼权重
      TInt boneCount = piSkin->GetNumberOfBones(n);
      if(boneCount > 0){
         validCount++;
         // 存储骨骼权重
         for(TInt32 i = 0; i < boneCount; i++){
            // 获得骨骼信息
            TInt boneId = piSkin->GetBoneID(n, i);
            IGameNode* piBone = _pExporter->GameNodes()->Get(boneId);
            if(!_pExporter->GameBones()->Contains(piBone)){
               _pExporter->GameBones()->Push(piBone);
            }
         }
      }
   }
   //------------------------------------------------------------
   // 存储顶点列表
   pOutput->WriteInt32(validCount);
   for(TInt32 n = 0; n < vertexCount; n++){
      // 设置骨骼权重
      TInt32 boneCount = piSkin->GetNumberOfBones(n);
      if(boneCount > 0){
         // 存储骨骼权重
         pOutput->WriteInt32(n);
         pOutput->WriteInt32(boneCount);
         for(TInt32 i = 0; i < boneCount; i++){
            // 获得骨骼信息
            TInt32 boneId = piSkin->GetBoneID(n, i);
            TFloat weight = piSkin->GetWeight(n, i);
            pOutput->WriteInt32(boneId);
            pOutput->WriteFloat(weight);
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>序列化网格信息。</T>
//
// @param pOutput 输出流
//============================================================
TBool F3dsGeometryExporter::Serialize(IDataOutput* pOutput){
   // 输出名称
   TCharC* pName = _piNode->GetName();
   FByteFile* pFile = (FByteFile*)pOutput;
   TFsName8 name;
   name.Assign16(_piNode->GetName());
   name.Serialize(pOutput);
   // 存储顶点坐标 (Vertex)
   TInt32 vertexCount = _piSubMesh->GetNumberOfVerts();
   pOutput->WriteInt32(vertexCount);
   for(TInt32 n = 0; n < vertexCount; n++){
      Point3 point;
      if(_piSubMesh->GetVertex(n, point)){
         pOutput->WriteFloat(point.x);
         pOutput->WriteFloat(point.y);
         pOutput->WriteFloat(point.z);
      }
   }
   // 顶点颜色 (Color)
   TInt32 colorCount = _piSubMesh->GetNumberOfColorVerts();
   pOutput->WriteInt32(colorCount);
   for(TInt32 n = 0; n < colorCount; n++){
      Point3 point;
      if(_piSubMesh->GetColorVertex(n, point)){
         pOutput->WriteFloat(point.x);
         pOutput->WriteFloat(point.y);
         pOutput->WriteFloat(point.z);
      }
   }
   // 顶点透明 (Alpha)
   TInt32 alphaCount = _piSubMesh->GetNumberOfAlphaVerts();
   pOutput->WriteInt32(alphaCount);
   for(TInt32 n = 0; n < alphaCount; n++){
	   TFloat alpha = 0.0f;
	   if(_piSubMesh->GetAlphaVertex(n, alpha)){
		   pOutput->WriteFloat(alpha);
	   }
   }
   // 存储贴图坐标 (Coord)
   TInt32 coordCount = _piSubMesh->GetNumberOfTexVerts();
   pOutput->WriteInt32(coordCount);
   for(TInt32 n = 0; n < coordCount; n++){
      Point2 point;
      if(_piSubMesh->GetTexVertex(n, point)){
         pOutput->WriteFloat(point.x);
         pOutput->WriteFloat(point.y);
      }
   }
   // 存储法线内容 (Normal)
   TInt32 normalCount = _piSubMesh->GetNumberOfNormals();
   pOutput->WriteInt32(normalCount);
   for(TInt32 n = 0; n < normalCount; n++){
      Point3 point;
      if(_piSubMesh->GetNormal(n, point)){
         pOutput->WriteFloat(point.x);
         pOutput->WriteFloat(point.y);
         pOutput->WriteFloat(point.z);
      }
   }
   // 设置顶点副法线 (binormal = normal * tangent)
   TInt32 binormalCount = _piSubMesh->GetNumberOfBinormals();
   pOutput->WriteInt32(binormalCount);
   for(TInt32 n = 0; n < binormalCount; n++){
      Point3 point;
      if(_piSubMesh->GetBinormal(n, point)){
         pOutput->WriteFloat(point.x);
         pOutput->WriteFloat(point.y);
         pOutput->WriteFloat(point.z);
      }
   }
   // 设置顶点切线 (tangent)
   TInt32 tangentCount = _piSubMesh->GetNumberOfTangents();
   pOutput->WriteInt32(tangentCount);
   for(TInt32 n = 0; n < tangentCount; n++){
      Point3 point;
      if(_piSubMesh->GetTangent(n, point)){
         pOutput->WriteFloat(point.x);
         pOutput->WriteFloat(point.y);
         pOutput->WriteFloat(point.z);
      }
   }
   // 设置顶点照明 (Illum)
   TInt32 illumCount = _piSubMesh->GetNumberOfIllumVerts();
   pOutput->WriteInt32(illumCount);
   for(TInt32 n = 0; n < illumCount; n++){
	   float illum;
	   if(_piSubMesh->GetIllumVertex(n, illum)){
		   pOutput->WriteFloat(illum);
	   }
   }
   // 存储三角形面 (Faces)：使用面信息，设置顶点的坐标和法线信息
   TInt32 faceCount = _piSubMesh->GetNumberOfFaces();
   pOutput->WriteInt32(faceCount);
   for(TInt32 n = 0; n < faceCount; n++){
      FaceEx* pFace = _piSubMesh->GetFace(n);
      IGameMaterial* piMaterial = _piSubMesh->GetMaterialFromFace(pFace);
      TInt32 materialIndex = (TInt32)_materials.IndexOf(piMaterial);
      // 输出面信息
      pOutput->WriteInt32(pFace->meshFaceIndex);
	   pOutput->WriteInt32(pFace->flags);
      //pOutput->WriteInt32(pFace->matID);
      pOutput->WriteInt32(materialIndex);
      pOutput->WriteInt32(pFace->smGrp);
      // 存储三角面的顶点索引
      if(vertexCount > 0){
         pOutput->WriteInt32(pFace->vert[0]);
         pOutput->WriteInt32(pFace->vert[1]);
         pOutput->WriteInt32(pFace->vert[2]);
      }
      // 存储三角面的顶点颜色索引
      if(colorCount > 0){
         pOutput->WriteInt32(pFace->color[0]);
         pOutput->WriteInt32(pFace->color[1]);
         pOutput->WriteInt32(pFace->color[2]);
      }
	  // 存储三角面的顶点透明索引
	  if(alphaCount > 0){
		  pOutput->WriteInt32(pFace->alpha[0]);
		  pOutput->WriteInt32(pFace->alpha[1]);
		  pOutput->WriteInt32(pFace->alpha[2]);
	  }
      // 存储三角面的顶点贴图索引
      if(coordCount > 0){
         pOutput->WriteInt32(pFace->texCoord[0]);
         pOutput->WriteInt32(pFace->texCoord[1]);
         pOutput->WriteInt32(pFace->texCoord[2]);
      }
      // 存储三角面的顶点法线索引
      if(normalCount > 0){
         pOutput->WriteInt32(pFace->norm[0]);
         pOutput->WriteInt32(pFace->norm[1]);
         pOutput->WriteInt32(pFace->norm[2]);
      }
      // 存储三角形副法线和切线索引
      if((binormalCount > 0) || (tangentCount > 0)){
         pOutput->WriteInt32(_piSubMesh->GetFaceVertexTangentBinormal(n, 0));
         pOutput->WriteInt32(_piSubMesh->GetFaceVertexTangentBinormal(n, 1));
         pOutput->WriteInt32(_piSubMesh->GetFaceVertexTangentBinormal(n, 2));
      }
	   // 存储三角面的顶点照明索引
	   if(illumCount > 0){
		   pOutput->WriteInt32(pFace->illum[0]);
		   pOutput->WriteInt32(pFace->illum[1]);
		   pOutput->WriteInt32(pFace->illum[2]);
	   }
      // 存储三角形边线可见性
      pOutput->WriteInt32(pFace->edgeVis[0]);
      pOutput->WriteInt32(pFace->edgeVis[1]);
      pOutput->WriteInt32(pFace->edgeVis[2]);
   }
   MO_INFO(TC("Serialize geometry. (name=%s, vertex=%d, face=%d)"), pName, vertexCount, faceCount);
   // 存储顶点信息
   SerializeTrack(pOutput);
   // 存储蒙皮信息
   IGameSkin* piSkin = _piSubMesh->GetIGameSkin();
   if(NULL != piSkin){
      pOutput->WriteInt32(1);
      SerializeSkin(pOutput, piSkin);
   }else{
      pOutput->WriteInt32(0);
   }
   return ETrue;
}

//============================================================
// <T>存储蒙皮的点对应的权重。</T>
// <P>使用法线点，不使用顶点。</P>
//
// @param pSkinNode 配置节点
// @param piSkin 皮肤接口
//============================================================
TBool F3dsGeometryExporter::ConvertSkin(FXmlNode* pSkinNode, IGameSkin* piSkin){
   // 获得基础属性
   pSkinNode->Set(TC("class_name"), piSkin->GetClassName());
   TInt vertexCount = _piSubMesh->GetNumberOfVerts();
   if(vertexCount > 0){
      pSkinNode->SetInt(TC("vertex_count"), vertexCount);
   }
   TInt32 boneCount = piSkin->GetTotalBoneCount();
   pSkinNode->SetInt(TC("bone_count"), boneCount);
   pSkinNode->SetInt(TC("skin_bone_count"), piSkin->GetTotalSkinBoneCount());
   //// 获得蒙皮矩阵
   //GMatrix matrix;
   //piSkin->GetInitSkinTM(matrix);
   //R3dsExporter::StoreGameMatrixSimple(pSkinNode->CreateNode("Matrix"), matrix);
   //// 存储骨骼列表
   //FXmlNode* pBoneListNode = pSkinNode->CreateNode("BoneList");
   //for(TInt n = 0; n < boneCount; n++){
   //   INode* piBoneNode = piSkin->GetBone(n);
   //   IGameNode* piGameNode =_pExporter->GameScene()->GetIGameNode(piBoneNode);
   //   FXmlNode* pBoneNode = pBoneListNode->CreateNode("Bone");
   //   pBoneNode->SetInt("bone_id", piGameNode->GetNodeID());
   //   GMatrix boneMatrix;
   //   piSkin->GetInitBoneTM(piGameNode, boneMatrix);
   //   R3dsExporter::StoreGameMatrixSimple(pBoneNode->CreateNode("Matrix"), boneMatrix);
   //}
   // 存储顶点列表
   FXmlNode* pVertexListNode = pSkinNode->CreateNode(TC("VertexCollection"));
   pVertexListNode->SetInt(TC("count"), vertexCount);
   for(TInt32 n = 0; n < vertexCount; n++){
      // 设置骨骼权重
      TInt32 boneCount = piSkin->GetNumberOfBones(n);
      if(boneCount > 0){
         // 存储节点
         FXmlNode* pVertexNode = pVertexListNode->CreateNode(TC("Vertex"));
         pVertexNode->SetInt(TC("id"), n);
         // 存储骨骼权重
         for(TInt32 i = 0; i < boneCount; i++){
            // 获得骨骼信息
            TInt boneId = piSkin->GetBoneID(n, i);
            IGameNode* piBone = _pExporter->GameNodes()->Get(boneId);
            if(!_pExporter->GameBones()->Contains(piBone)){
               _pExporter->GameBones()->Push(piBone);
            }
            // 存储节点骨骼
            FXmlNode* pBoneNode = pVertexNode->CreateNode(TC("Bone"));
            TFloat weight = piSkin->GetWeight(n, i);
            pBoneNode->SetInt(TC("bone_id"), boneId);
            pBoneNode->SetFloat(TC("weight"), weight);
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>导出子网格的顶点。</T>
//
// @param pVertexNode 定点信息
// @param face 面信息
// @param corner 拐角信息
//============================================================
TBool F3dsGeometryExporter::ConvertVertex(FXmlNode* pVertexNode, TInt32 face, TInt32 corner){
   // 获得面信息
   FaceEx* pFace = _piSubMesh->GetFace(face);
   TInt32 tbIndex = _piSubMesh->GetFaceVertexTangentBinormal(face, corner);
   // 设置顶点坐标
   FXmlNode* pPositionNode = pVertexNode->FindNode(TC("Position"));
   if(NULL == pPositionNode){
      Point3 point;
      if(_piSubMesh->GetVertex(pFace->vert[corner], point)){
         pPositionNode = pVertexNode->CreateNode(TC("Position"));
         R3dsExporter::StorePoint3(pPositionNode, point);
      }
   }
   // 设置顶点贴图坐标
   TInt32 uvCount = _piSubMesh->GetNumberOfTexVerts();
   if(uvCount > 0){
      FXmlNode* pCoordNode = pVertexNode->FindNode(TC("Coord"));
      if(NULL == pCoordNode){
         Point2 coord;
         if(_piSubMesh->GetTexVertex(pFace->texCoord[corner], coord)){
            pCoordNode = pVertexNode->CreateNode(TC("Coord"));
            R3dsExporter::StorePoint2(pCoordNode, coord);
         }
      }
   }
   // 设置顶点颜色
   TInt32 colorCount = _piSubMesh->GetNumberOfColorVerts();
   if(colorCount > 0){
      FXmlNode* pColorNode = pVertexNode->FindNode(TC("Color"));
      if(NULL == pColorNode){
         Point3 color;
         if(_piSubMesh->GetColorVertex(pFace->color[corner], color)){
            pColorNode = pVertexNode->CreateNode(TC("Color"));
            R3dsExporter::StorePoint3(pColorNode, color);
         }
      }
   }
   // 设置顶点法线 (normal)
   FXmlNode* pNormalNode = pVertexNode->FindNode(TC("Normal"));
   if(NULL == pNormalNode){
      Point3 normal;
      if(_piSubMesh->GetNormal(pFace, corner, normal)){
         pNormalNode = pVertexNode->CreateNode(TC("Normal"));
         R3dsExporter::StorePoint3(pNormalNode, normal);
      }
   }
   // 设置顶点副法线 (binormal = normal * tangent)
   FXmlNode* pBinormalNode = pVertexNode->FindNode(TC("Binormal"));
   if(NULL == pBinormalNode){
      Point3 binormal;
      if(_piSubMesh->GetBinormal(tbIndex, binormal)){
         pBinormalNode = pVertexNode->CreateNode(TC("Binormal"));
         R3dsExporter::StorePoint3(pBinormalNode, binormal);
      }
   }
   // 设置顶点切线 (tangent)
   FXmlNode* pTangentNode = pVertexNode->FindNode(TC("Tangent"));
   if(NULL == pTangentNode){
      Point3 tangent;
      if(_piSubMesh->GetTangent(tbIndex, tangent)){
         pTangentNode = pVertexNode->CreateNode(TC("Tangent"));
         R3dsExporter::StorePoint3(pTangentNode, tangent);
      }
   }
   // 设置顶点透明度
   TInt32 alphaCount = _piSubMesh->GetNumberOfAlphaVerts();
   if(alphaCount > 0){
      FXmlNode* pAlphaNode = pVertexNode->FindNode(TC("Alpha"));
      if(NULL == pAlphaNode){
         TFloat alpha;
         if(_piSubMesh->GetAlphaVertex(pFace->alpha[corner], alpha)){
            pAlphaNode = pVertexNode->CreateNode(TC("Alpha"));
            pAlphaNode->SetFloat(TC("value"), alpha);
         }
      }
   }
   // 设置顶点发光度
   TInt32 illumCount = _piSubMesh->GetNumberOfIllumVerts();
   if(illumCount > 0){
      FXmlNode* pIllumNode = pVertexNode->FindNode(TC("Illum"));
      if(NULL == pIllumNode){
         TFloat illum;
         if(_piSubMesh->GetIllumVertex(pFace->illum[corner], illum)){
            pIllumNode = pVertexNode->CreateNode(TC("Illum"));
            pIllumNode->SetFloat(TC("value"), illum);
         }
      }
   }
   return ETrue;
}

//============================================================
TBool F3dsGeometryExporter::ConvertControlType(FXmlNode* pTypeNode, IGameControl* piControl, TCharC* pName, IGameControlType keyType){
   int keyCount = piControl->GetIGameKeyCount(keyType);
   pTypeNode->Set(TC("name"), pName);
   pTypeNode->Set(TC("type"), R3dsClass::GetControlType(piControl->GetControlType(keyType)));
   pTypeNode->SetInt(TC("key_count"), keyCount);
   pTypeNode->SetBool(TC("is_animated"), piControl->IsAnimated(keyType));
   pTypeNode->SetBool(TC("is_leaf"), piControl->IsLeaf(keyType));
   return ETrue;
}

//============================================================
// <T>存储骨骼动画。</T>
//
// @param pTrackNode 配置信息
//============================================================
TBool F3dsGeometryExporter::ConvertTrack(FXmlNode* pTrackNode){
   // 获得节点信息
   TInt nodeId = _piNode->GetNodeID();
   // 创建骨骼
   pTrackNode->Set(TC("owner"), TC("geometry"));
   pTrackNode->Set(TC("name"), _piNode->GetName());
   GMatrix objectTm = _piNode->GetObjectTM(0);
   R3dsExporter::StoreGameMatrixSimple(pTrackNode->CreateNode(TC("ObjectMatrix")), objectTm);
   GMatrix localTm = _piNode->GetLocalTM(0);
   R3dsExporter::StoreGameMatrixSimple(pTrackNode->CreateNode(TC("LocalMatrix")), localTm);
   GMatrix worldTm = _piNode->GetWorldTM(0);
   R3dsExporter::StoreGameMatrixSimple(pTrackNode->CreateNode(TC("WorldMatrix")), worldTm);
   // 获得控件
   IGameControl* piControl = _piNode->GetIGameControl();
   if(NULL == piControl){
      return EFalse;
   }
   // 获得存储类型
   FXmlNode* pTypesNode = pTrackNode->CreateNode(TC("Types"));
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("float"), IGAME_FLOAT);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("point"), IGAME_POS);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("point3"), IGAME_POINT3);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("point4"), IGAME_POINT4);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("rotation"), IGAME_ROT);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("scale"), IGAME_SCALE);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("matrix"), IGAME_TM);
   // 获得控制信息
   bool positionAnimated = piControl->IsAnimated(IGAME_POS);
   int keyPositionCount = piControl->GetIGameKeyCount(IGAME_POS);
   bool rotationAnimated = piControl->IsAnimated(IGAME_ROT);
   int keyRotationCount = piControl->GetIGameKeyCount(IGAME_ROT);
   bool scaleAnimated = piControl->IsAnimated(IGAME_SCALE);
   int keyScaleCount = piControl->GetIGameKeyCount(IGAME_SCALE);
   // 检查是否含有动画
   if(positionAnimated || rotationAnimated || scaleAnimated){
      // 建立默认的所有屏
      IGameKeyTab keys;
      if(piControl->GetFullSampledKeys(keys, 1, IGAME_TM, false)){
         TInt count = keys.Count();
         if(count > 0){
            FXmlNode* pFramesNode = pTrackNode->CreateNode(TC("Frames"));
            pFramesNode->SetInt(TC("count"), count);
            for(TInt n=0; n<count; n++){
               // 创建一帧信息
               IGameKey& key = keys[n];
               FXmlNode* pFrameNode = pFramesNode->CreateNode(TC("Frame"));
               pFrameNode->SetInt(TC("time"), key.t);
               // 设置对象矩阵
               FXmlNode* pObjectTmNode = pFrameNode->CreateNode(TC("ObjectMatrix"));
               GMatrix objectTm = _piNode->GetObjectTM(key.t);
               R3dsExporter::StoreGameMatrixSimple(pObjectTmNode, objectTm);
               FXmlNode* pLocalTmNode = pFrameNode->CreateNode(TC("LocalMatrix"));
               GMatrix localTm = _piNode->GetLocalTM(key.t);
               R3dsExporter::StoreGameMatrixSimple(pLocalTmNode, localTm);
               FXmlNode* pWorldTmNode = pFrameNode->CreateNode(TC("WorldMatrix"));
               GMatrix worldTm = _piNode->GetWorldTM(key.t);
               R3dsExporter::StoreGameMatrixSimple(pWorldTmNode, worldTm);
            }
         }
      }
   }
   return ETrue;
}

//============================================================
// <T>存储网格信息。</T>
// <P>1. 以顶点法线数目为顶点总数，存在一个顶点有多个顶点法线的情况，
//       此时认为当前法线的顶点为多个，但这几个顶点坐标是一致的。
//    2. 因为点数增多，蒙皮的权重顶点也被分开处理。</P>
//
// @param pSubMeshNode 配置信息
//============================================================
TBool F3dsGeometryExporter::Convert(FXmlNode* pConfig){
   TFsText meshName =  _piNode->GetName();
   // 输出信息
   pConfig->Set(TC("class"), _piSubMesh->GetClassName());
   pConfig->Set(TC("name"), meshName);
   TInt vertexCount = _piSubMesh->GetNumberOfVerts();
   if(vertexCount > 0){
      pConfig->SetInt(TC("vertex_count"), vertexCount);
   }
   TInt colorCount = _piSubMesh->GetNumberOfColorVerts();
   if(colorCount > 0){
      pConfig->SetInt(TC("color_count"), colorCount);
   }
   TInt alphaCount = _piSubMesh->GetNumberOfAlphaVerts();
   if(alphaCount > 0){
      pConfig->SetInt(TC("alpha_count"), alphaCount);
   }
   TInt illumCount = _piSubMesh->GetNumberOfIllumVerts();
   if(illumCount > 0){
      pConfig->SetInt(TC("illum_count"), illumCount);
   }
   TInt normalCount = _piSubMesh->GetNumberOfNormals();
   if(normalCount > 0){
      pConfig->SetInt(TC("normal_count"), normalCount);
   }
   TInt binormalCount = _piSubMesh->GetNumberOfBinormals();
   if(binormalCount > 0){
      pConfig->SetInt(TC("binormal_count"), binormalCount);
   }
   TInt tangentCount = _piSubMesh->GetNumberOfTangents();
   if(tangentCount > 0){
      pConfig->SetInt(TC("tangent_count"), tangentCount);
   }
   TInt faceCount = _piSubMesh->GetNumberOfFaces();
   if(faceCount > 0){
      pConfig->SetInt(TC("face_count"), faceCount);
   }
   TInt coordCount = _piSubMesh->GetNumberOfTexVerts();
   if(coordCount > 0){
      pConfig->SetInt(TC("coord_count"), coordCount);
   }
   // 获得属性列表
   IPropertyContainer* piPropertyContainer = _piSubMesh->GetIPropertyContainer();
   if(NULL != piPropertyContainer){
      R3dsExporter::StoreProperties(pConfig, TC("Properties"), piPropertyContainer);
   }
   // 获得变换矩阵
   FXmlNode* pMatrixNode = pConfig->CreateNode(TC("Matrix"));
   GMatrix objectTm = _piNode->GetObjectTM(0);
   R3dsExporter::StoreGameMatrixSimple(pMatrixNode->CreateNode(TC("ObjectMatrix")), objectTm);
   GMatrix localTm = _piNode->GetLocalTM(0);
   R3dsExporter::StoreGameMatrixSimple(pMatrixNode->CreateNode(TC("LocalMatrix")), localTm);
   GMatrix worldTm = _piNode->GetWorldTM(0);
   R3dsExporter::StoreGameMatrixSimple(pMatrixNode->CreateNode(TC("WorldMatrix")), worldTm);
   // 获得层次列表
   // Tab<TInt> maps = _piSubMesh->GetActiveMapChannelNum();
   // TInt mapCount = maps.Count();
   // if(mapCount > 0){
   //    pSubMeshNode->SetInt("map_count", mapCount);
   //    // 创建层次
   //    FXmlNode* pMapsNode = pSubMeshNode->CreateNode("MapCollection");
   //    pMapsNode->SetInt("count", mapCount);
   //    for(TInt n = 0; n < mapCount; n++){
   //       FXmlNode* pMapNode = pMapsNode->CreateNode("Map");
   //       TInt mapId = maps[n];
   //       pMapNode->SetInt("id", mapId);
   //       pMapNode->SetInt("vertex_count", _piSubMesh->GetNumberOfMapVerts(mapId));
   //       pMapNode->SetInt("tangent_count", _piSubMesh->GetNumberOfTangents(mapId));
   //       pMapNode->SetInt("binormal_count", _piSubMesh->GetNumberOfBinormals(mapId));
   //    }
   // }
   // 获得关联材质列表
   if(faceCount > 0){
      for(TInt32 n = 0; n < faceCount; n++){
         FaceEx* pFace = _piSubMesh->GetFace(n);
         IGameMaterial* piMaterial = _piSubMesh->GetMaterialFromFace(pFace);
         if(piMaterial != NULL){
            if(!_materials.Contains(piMaterial)){
               _materials.Push(piMaterial);
            }
         }
      }
   }
   if(!_materials.IsEmpty()){
      FXmlNode* pMaterialsNode = pConfig->CreateNode(TC("MaterialCollection"));
      TInt32 materialCount = (TInt32)_materials.Count();
      pMaterialsNode->SetInt(TC("count"), materialCount);
      for(TInt32 n = 0; n < materialCount; n++){
         IGameMaterial* piMaterial = _materials[n];
         FXmlNode* pMaterialNode = pMaterialsNode->CreateNode(TC("Material"));
         pMaterialNode->SetInt(TC("id"), _pExporter->GameMaterials()->IndexOf(piMaterial));
         pMaterialNode->Set(TC("name"), piMaterial->GetMaterialName());
      }
   }
   // 获得变形器
   TInt32 modifiersCount = _piSubMesh->GetNumModifiers();
   if(modifiersCount > 0){
      FXmlNode* pModifiersNode = pConfig->CreateNode(TC("ModifierCollection"));
      pModifiersNode->SetInt(TC("count"), modifiersCount);
      for(TInt32 n = 0; n < modifiersCount; n++){
         IGameModifier* piModifier = _piSubMesh->GetIGameModifier(n);
         FXmlNode* pModifierNode = pModifiersNode->CreateNode(TC("Modifier"));
         pModifierNode->Set(TC("class"), piModifier->GetClassName());
         pModifierNode->Set(TC("name"), piModifier->GetInternalName());
      }
   }
   // 获得蒙皮
   IGameSkin* piSkin = _piSubMesh->GetIGameSkin();
   if(NULL != piSkin){
      FXmlNode* pSkinNode = pConfig->CreateNode(TC("Skin"));
      TInt32 totalBoneCount = piSkin->GetTotalBoneCount();
      TInt32 totalSkinBoneCount = piSkin->GetTotalSkinBoneCount();
      pSkinNode->Set(TC("class"), piSkin->GetClassName());
      pSkinNode->Set(TC("internal_name"), piSkin->GetInternalName());
      pSkinNode->SetInt(TC("bone_count"), totalBoneCount);
      pSkinNode->SetInt(TC("skin_bone_count"), totalSkinBoneCount);
   }
   // 计算网格顶点动画
   IGameControl* piControl = _piNode->GetIGameControl();
   if(NULL == piControl){
	   return EFalse;
   }
   FXmlNode* pTrackNode = pConfig->CreateNode(TC("Track"));
   // 获得存储类型
   FXmlNode* pTypesNode = pTrackNode->CreateNode(TC("Types"));
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("float"), IGAME_FLOAT);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("point"), IGAME_POS);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("point3"), IGAME_POINT3);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("point4"), IGAME_POINT4);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("rotation"), IGAME_ROT);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("scale"), IGAME_SCALE);
   ConvertControlType(pTypesNode->CreateNode(TC("Type")), piControl, TC("matrix"), IGAME_TM);
   // 获得控制信息
   bool positionAnimated = piControl->IsAnimated(IGAME_POS);
   int keyPositionCount = piControl->GetIGameKeyCount(IGAME_POS);
   bool rotationAnimated = piControl->IsAnimated(IGAME_ROT);
   int keyRotationCount = piControl->GetIGameKeyCount(IGAME_ROT);
   bool scaleAnimated = piControl->IsAnimated(IGAME_SCALE);
   int keyScaleCount = piControl->GetIGameKeyCount(IGAME_SCALE);
   // 检查是否含有动画
   //if(positionAnimated || rotationAnimated || scaleAnimated){
	   // 建立默认的所有屏
	   IGameKeyTab keys;
	   if(piControl->GetFullSampledKeys(keys, 1, IGAME_TM, false)){
		   pTrackNode->SetInt(TC("count"), keys.Count());
	   }
   //}
   return ETrue;
}

//============================================================
// <T>存储网格信息。</T>
// <P>1. 以顶点法线数目为顶点总数，存在一个顶点有多个顶点法线的情况，
//       此时认为当前法线的顶点为多个，但这几个顶点坐标是一致的。
//    2. 因为点数增多，蒙皮的权重顶点也被分开处理。</P>
//
// @param pSubMeshNode 配置信息
//============================================================
TBool F3dsGeometryExporter::ConvertFile(FXmlNode* pConfig){
   TFsText meshName =  _piNode->GetName();
   pConfig->Set(TC("name"), meshName);
   pConfig->Set(TC("class"), _piSubMesh->GetClassName());
   TInt32 vertexCount = _piSubMesh->GetNumberOfVerts();
   if(vertexCount > 0){
      pConfig->SetInt(TC("vertex_count"), vertexCount);
   }
   TInt32 colorCount = _piSubMesh->GetNumberOfColorVerts();
   if(colorCount > 0){
      pConfig->SetInt(TC("color_count"), colorCount);
   }
   TInt32 alphaCount = _piSubMesh->GetNumberOfAlphaVerts();
   if(alphaCount > 0){
      pConfig->SetInt(TC("alpha_count"), alphaCount);
   }
   TInt32 illumCount = _piSubMesh->GetNumberOfIllumVerts();
   if(illumCount > 0){
      pConfig->SetInt(TC("illum_count"), illumCount);
   }
   TInt32 normalCount = _piSubMesh->GetNumberOfNormals();
   if(normalCount > 0){
      pConfig->SetInt(TC("normal_count"), normalCount);
   }
   TInt32 binormalCount = _piSubMesh->GetNumberOfBinormals();
   if(binormalCount > 0){
      pConfig->SetInt(TC("binormal_count"), binormalCount);
   }
   TInt32 tangentCount = _piSubMesh->GetNumberOfTangents();
   if(tangentCount > 0){
      pConfig->SetInt(TC("tangent_count"), tangentCount);
   }
   TInt32 faceCount = _piSubMesh->GetNumberOfFaces();
   if(faceCount > 0){
      pConfig->SetInt(TC("face_count"), faceCount);
   }
   TInt32 coordCount = _piSubMesh->GetNumberOfTexVerts();
   if(coordCount > 0){
      pConfig->SetInt(TC("coord_count"), coordCount);
   }
   // 获得属性列表
   IPropertyContainer* piPropertyContainer = _piSubMesh->GetIPropertyContainer();
   if(NULL != piPropertyContainer){
      R3dsExporter::StoreProperties(pConfig, TC("Properties"), piPropertyContainer);
   }
   // 获得变换矩阵
   FXmlNode* pMatrixNode = pConfig->CreateNode(TC("Matrix"));
   GMatrix objectTm = _piNode->GetObjectTM(0);
   R3dsExporter::StoreGameMatrixSimple(pMatrixNode->CreateNode(TC("ObjectMatrix")), objectTm);
   GMatrix localTm = _piNode->GetLocalTM(0);
   R3dsExporter::StoreGameMatrixSimple(pMatrixNode->CreateNode(TC("LocalMatrix")), localTm);
   GMatrix worldTm = _piNode->GetWorldTM(0);
   R3dsExporter::StoreGameMatrixSimple(pMatrixNode->CreateNode(TC("WorldMatrix")), worldTm);
   // 获得层次列表
   // Tab<TInt> maps = _piSubMesh->GetActiveMapChannelNum();
   // TInt mapCount = maps.Count();
   // if(mapCount > 0){
   //    pSubMeshNode->SetInt("map_count", mapCount);
   //    // 创建层次
   //    FXmlNode* pMapsNode = pSubMeshNode->CreateNode("MapCollection");
   //    pMapsNode->SetInt("count", mapCount);
   //    for(TInt n = 0; n < mapCount; n++){
   //       FXmlNode* pMapNode = pMapsNode->CreateNode("Map");
   //       TInt mapId = maps[n];
   //       pMapNode->SetInt("id", mapId);
   //       pMapNode->SetInt("vertex_count", _piSubMesh->GetNumberOfMapVerts(mapId));
   //       pMapNode->SetInt("tangent_count", _piSubMesh->GetNumberOfTangents(mapId));
   //       pMapNode->SetInt("binormal_count", _piSubMesh->GetNumberOfBinormals(mapId));
   //    }
   // }
   // 获得关联材质列表
   if(faceCount > 0){
      _materials.Clear();
      for(TInt32 n = 0; n < faceCount; n++){
         FaceEx* pFace = _piSubMesh->GetFace(n);
         IGameMaterial* piMaterial = _piSubMesh->GetMaterialFromFace(pFace);
         if(piMaterial != NULL){
            if(!_materials.Contains(piMaterial)){
               _materials.Push(piMaterial);
            }
         }
      }
   }
   if (!_materials.IsEmpty()){
      FXmlNode* pMaterialsNode = pConfig->CreateNode(TC("MaterialCollection"));
      TInt32 materialCount = (TInt32)_materials.Count();
      pMaterialsNode->SetInt(TC("count"), materialCount);
      for(TInt32 n = 0; n < materialCount; n++){
         IGameMaterial* piMaterial = _materials[n];
         FXmlNode* pMaterialNode = pMaterialsNode->CreateNode(TC("Material"));
         pMaterialNode->SetInt(TC("id"), _pExporter->GameMaterials()->IndexOf(piMaterial));
         pMaterialNode->SetInt(TC("material_id"), piMaterial->GetMaterialID(0));
         pMaterialNode->Set(TC("name"), piMaterial->GetMaterialName());
      }
   }
   //------------------------------------------------------------
   //// 存储顶点坐标 (Vertex)
   //if(vertexCount > 0){
   //   FXmlNode* pVertexListNode = pConfig->CreateNode(TC("VertexCollection"));
   //   pVertexListNode->SetInt(TC("count"), vertexCount);
   //   for(TInt32 n = 0; n < vertexCount; n++){
   //      FXmlNode* pVertexNode = pVertexListNode->CreateNode(TC("Vertex"));
   //      Point3 point;
   //      if(_piSubMesh->GetVertex(n, point)){
   //         R3dsExporter::StorePoint3(pVertexNode, point);
   //      }
   //   }
   //}
   //// 顶点颜色 (Color)
   //if(colorCount > 0){
   //   FXmlNode* pColorListNode = pConfig->CreateNode(TC("ColorCollection"));
   //   pColorListNode->SetInt(TC("count"), colorCount);
   //   for(TInt32 n = 0; n < colorCount; n++){
   //      FXmlNode* pColorNode = pColorListNode->CreateNode(TC("Color"));
   //      Point3 point;
   //      if(_piSubMesh->GetColorVertex(n, point)){
   //         R3dsExporter::StorePoint3(pColorNode, point);
   //      }
   //   }
   //}
   //// 存储贴图坐标 (Coord)
   //if(coordCount > 0){
   //   FXmlNode* pCoordListNode = pConfig->CreateNode(TC("CoordCollection"));
   //   pCoordListNode->SetInt(TC("count"), coordCount);
   //   for(TInt32 n = 0; n < coordCount; n++){
   //      FXmlNode* pCoordNode = pCoordListNode->CreateNode(TC("Coord"));
   //      Point2 point;
   //      if(_piSubMesh->GetTexVertex(n, point)){
   //         R3dsExporter::StorePoint2(pCoordNode, point);
   //      }
   //   }
   //}
   //// 存储法线内容 (Normal)
   //if(normalCount > 0){
   //   FXmlNode* pNormalListNode = pConfig->CreateNode("NormalCollection");
   //   pNormalListNode->SetInt("count", normalCount);
   //   for(TInt n = 0; n < normalCount; n++){
   //      FXmlNode* pNormalNode = pNormalListNode->CreateNode("Normal");
   //      Point3 point;
   //      if(_piSubMesh->GetNormal(n, point)){
   //         R3dsExporter::StorePoint3(pNormalNode, point);
   //      }
   //   }
   //}
   //// 设置顶点副法线 (binormal = normal * tangent)
   //if(binormalCount > 0){
   //   FXmlNode* pBinormalListNode = pConfig->CreateNode("BinormalCollection");
   //   pBinormalListNode->SetInt("count", binormalCount);
   //   for(TInt n = 0; n < binormalCount; n++){
   //      FXmlNode* pBinormalNode = pBinormalListNode->CreateNode("Binormal");
   //      Point3 binormal;
   //      if(_piSubMesh->GetBinormal(n, binormal)){
   //         R3dsExporter::StorePoint3(pBinormalNode, binormal);
   //      }
   //   }
   //}
   //// 设置顶点切线 (tangent)
   //if(tangentCount > 0){
   //   FXmlNode* pTangentListNode = pConfig->CreateNode("TangentCollection");
   //   pTangentListNode->SetInt("count", tangentCount);
   //   for(TInt n = 0; n < tangentCount; n++){
   //      FXmlNode* pTangentNode = pTangentListNode->CreateNode("Tangent");
   //      Point3 tangent;
   //      if(_piSubMesh->GetTangent(n, tangent)){
   //         R3dsExporter::StorePoint3(pTangentNode, tangent);
   //      }
   //   }
   //}
   //// 存储三角形面 (Faces)：使用面信息，设置顶点的坐标和法线信息
   //if(faceCount > 0){
   //   FXmlNode* pFacesNode = pConfig->CreateNode("FaceCollection");
   //   pFacesNode->SetInt("count", faceCount);
   //   for(TInt n = 0; n < faceCount; n++){
   //      FXmlNode* pFaceNode = pFacesNode->CreateNode("Face");
   //      FaceEx* pFace = _piSubMesh->GetFace(n);
   //      pFaceNode->SetInt("flags", pFace->flags);
   //      pFaceNode->SetInt("material_id", pFace->matID);
   //      pFaceNode->SetInt("smoothing_group", pFace->smGrp);
   //      // 存储三角面的顶点索引
   //      if(vertexCount > 0){
   //         TFsText value;
   //         value.AppendFormat("%d,%d,%d", pFace->vert[0], pFace->vert[1], pFace->vert[2]);
   //         pFaceNode->Set("vertex", value);
   //      }
   //      //// 存储三角面的顶点颜色索引
   //      if(colorCount > 0){
   //         TFsText value;
   //         value.AppendFormat("%d,%d,%d", pFace->color[0], pFace->color[1], pFace->color[2]);
   //         pFaceNode->Set("color", value);
   //      }
   //      // 存储三角面的顶点贴图索引
   //      if(coordCount > 0){
   //         TFsText value;
   //         value.AppendFormat("%d,%d,%d", pFace->texCoord[0], pFace->texCoord[1], pFace->texCoord[2]);
   //         pFaceNode->Set("coord", value);
   //      }
   //      // 存储三角面的顶点法线索引
   //      if(normalCount > 0){
   //         TFsText value;
   //         value.AppendFormat("%d,%d,%d", pFace->norm[0], pFace->norm[1], pFace->norm[2]);
   //         pFaceNode->Set("normal", value);
   //      }
   //      // 存储三角形副法线和切线索引
   //      if(tangentCount > 0){
   //         TFsText value;
   //         value.AppendFormat("%d,%d,%d", _piSubMesh->GetFaceVertexTangentBinormal(n, 0), _piSubMesh->GetFaceVertexTangentBinormal(n, 1), _piSubMesh->GetFaceVertexTangentBinormal(n, 2));
   //         pFaceNode->Set("tangent_binormal", value);
   //      }
   //      // 存储三角形边线可见性
   //      FXmlNode* pEdgeNode = pFaceNode->CreateNode("Visible");
   //      pEdgeNode->SetInt("v1", pFace->edgeVis[0]);
   //      pEdgeNode->SetInt("v2", pFace->edgeVis[1]);
   //      pEdgeNode->SetInt("v3", pFace->edgeVis[2]);
   //   }
   //}
   //// 获得变形器
   //TInt modifiersCount = _piSubMesh->GetNumModifiers();
   //if(modifiersCount > 0){
   //   FXmlNode* pModifiersNode = pConfig->CreateNode("ModifierCollection");
   //   pModifiersNode->SetInt("count", modifiersCount);
   //   for(TInt n = 0; n < modifiersCount; n++){
   //      IGameModifier* piModifier = _piSubMesh->GetIGameModifier(n);
   //      FXmlNode* pModifierNode = pModifiersNode->CreateNode("Modifier");
   //      pModifierNode->Set("class", piModifier->GetClassName());
   //      pModifierNode->Set("name", piModifier->GetInternalName());
   //   }
   //}
   //// 获得蒙皮
   //IGameSkin* piSkin = _piSubMesh->GetIGameSkin();
   //if(NULL != piSkin){
   //   FXmlNode* pSkinNode = pConfig->CreateNode("Skin");
   //   TInt totalBoneCount = piSkin->GetTotalBoneCount();
   //   TInt totalSkinBoneCount = piSkin->GetTotalSkinBoneCount();
   //   pSkinNode->Set("class", piSkin->GetClassName());
   //   pSkinNode->Set("internal_name", piSkin->GetInternalName());
   //   pSkinNode->SetInt("bone_count", totalBoneCount);
   //   pSkinNode->SetInt("skin_bone_count", totalSkinBoneCount);
   //   // 存储蒙皮信息
   //   ConvertSkin(pSkinNode, piSkin);
   //}
   // 计算网格顶点动画
   //FXmlNode* pTrackNode = pConfig->CreateNode(TC("Track"));
   //ConvertTrack(pTrackNode);
   return ETrue;
}
*/

MO_NAMESPACE_END;
