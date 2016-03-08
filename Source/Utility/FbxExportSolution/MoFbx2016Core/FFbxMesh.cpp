#include "MoFbxParser.h"

MO_NAMESPACE_BEGIN;
MO_NAMESPACE_USING(FBXSDK_NAMESPACE);

//============================================================
// <T>构造FBX管理器。</T>
//============================================================
FFbxMesh::FFbxMesh() {
   MO_CLEAR(_pScene);
   MO_CLEAR(_pFbxMesh);
   _pVertexs = MO_CREATE(FFbxVertexs);
   _pFaces = MO_CREATE(FFbxFaces);
   _vertexAttributeCount = 0;
   RBools::Clear(_vertexAttributes, EFbxVertexAttribute_Count);
}

//============================================================
// <T>析构FBX管理器。</T>
//============================================================
FFbxMesh::~FFbxMesh() {
   MO_CLEAR(_pScene);
   MO_DESTORY(_pFbxMesh);
   MO_DELETE(_pVertexs);
   MO_DELETE(_pFaces);
}

//============================================================
// <T>查找顶点坐标。</T>
//============================================================
TResult FFbxMesh::ReadVertexPosition(TInt vertexIndex, SFloatPoint3* pPosition){
   FbxVector4 position = _pFbxMesh->GetControlPointAt((TInt32)vertexIndex);
   pPosition->x = (TFloat)position[0];
   pPosition->y = (TFloat)position[1];
   pPosition->z = (TFloat)position[2];
   return ESuccess;
}

//============================================================
// <T>查找顶点颜色。</T>
//============================================================
TResult FFbxMesh::ReadVertexColor(TInt vertexIndex, TInt vertexCounter, SFloatColor4* pColor){
   TInt count = _pFbxMesh->GetElementVertexColorCount();
   if(count < 1){
      return EFailure;
   }
   FbxGeometryElementVertexColor* pElement = _pFbxMesh->GetElementVertexColor(0);
   if(pElement == NULL){
      return EFailure;
   }
   FbxGeometryElement::EMappingMode mappingCd = pElement->GetMappingMode();
   FbxGeometryElement::EReferenceMode referenceCd = pElement->GetReferenceMode();
   TInt index = -1;
   switch(mappingCd){
      case FbxGeometryElement::eByControlPoint:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexIndex;
         }else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexIndex);
         }else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      case FbxGeometryElement::eByPolygonVertex:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexCounter;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexCounter);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      default:{
         MO_FATAL(TC("Invalid mappingCd."));
      }
   }
   if(index != -1){
      FbxColor color = pElement->GetDirectArray().GetAt((TInt32)index);
      pColor->red = (TFloat)color.mRed;
      pColor->green = (TFloat)color.mGreen;
      pColor->blue = (TFloat)color.mBlue;
      pColor->alpha = (TFloat)color.mAlpha;
      return ESuccess;
   }
   return EFailure;
}

//============================================================
// <T>查找顶点法线。</T>
//============================================================
TResult FFbxMesh::ReadVertexNormal(TInt vertexIndex, TInt vertexCounter, SFloatVector3* pNormal){
   TInt count = _pFbxMesh->GetElementNormalCount();
   if(count < 1){
      return EFailure;
   }
   FbxGeometryElementNormal* pElement = _pFbxMesh->GetElementNormal(0);
   if(pElement == NULL){
      return EFailure;
   }
   FbxGeometryElement::EMappingMode mappingCd = pElement->GetMappingMode();
   FbxGeometryElement::EReferenceMode referenceCd = pElement->GetReferenceMode();
   TInt index = -1;
   switch(mappingCd){
      case FbxGeometryElement::eByControlPoint:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexIndex;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexIndex);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      case FbxGeometryElement::eByPolygonVertex:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexCounter;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexCounter);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      default:{
         MO_FATAL(TC("Invalid mappingCd."));
      }
   }
   if(index != -1){
      FbxVector4 data = pElement->GetDirectArray().GetAt((TInt32)index);
      pNormal->x = (TFloat)data[0];
      pNormal->y = (TFloat)data[1];
      pNormal->z = (TFloat)data[2];
      return ESuccess;
   }
   return EFailure;
}

//============================================================
// <T>查找顶点副法线。</T>
//============================================================
TResult FFbxMesh::ReadVertexBinormal(TInt vertexIndex, TInt vertexCounter, SFloatVector3* pBinormal){
   TInt count = _pFbxMesh->GetElementBinormalCount();
   if(count < 1){
      return EFailure;
   }
   FbxGeometryElementBinormal* pElement = _pFbxMesh->GetElementBinormal(0);
   if(pElement == NULL){
      return EFailure;
   }
   FbxGeometryElement::EMappingMode mappingCd = pElement->GetMappingMode();
   FbxGeometryElement::EReferenceMode referenceCd = pElement->GetReferenceMode();
   TInt index = -1;
   switch(mappingCd){
      case FbxGeometryElement::eByControlPoint:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexIndex;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexIndex);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      case FbxGeometryElement::eByPolygonVertex:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexCounter;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexCounter);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      default:{
         MO_FATAL(TC("Invalid mappingCd."));
      }
   }
   if(index != -1){
      FbxVector4 data = pElement->GetDirectArray().GetAt((TInt32)index);
      pBinormal->x = (TFloat)data[0];
      pBinormal->y = (TFloat)data[1];
      pBinormal->z = (TFloat)data[2];
      return ESuccess;
   }
   return EFailure;
}

//============================================================
// <T>查找顶点切线。</T>
//============================================================
TResult FFbxMesh::ReadVertexTangent(TInt vertexIndex, TInt vertexCounter, SFloatVector3* pTangent){
   TInt count = _pFbxMesh->GetElementTangentCount();
   if(count < 1){
      return EFailure;
   }
   FbxGeometryElementTangent* pElement = _pFbxMesh->GetElementTangent(0);
   if(pElement == NULL){
      return EFailure;
   }
   FbxGeometryElement::EMappingMode mappingCd = pElement->GetMappingMode();
   FbxGeometryElement::EReferenceMode referenceCd = pElement->GetReferenceMode();
   TInt index = -1;
   switch(mappingCd){
      case FbxGeometryElement::eByControlPoint:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexIndex;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexIndex);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      case FbxGeometryElement::eByPolygonVertex:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexCounter;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexCounter);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      default:{
         MO_FATAL(TC("Invalid mappingCd."));
      }
   }
   if(index != -1){
      FbxVector4 data = pElement->GetDirectArray().GetAt((TInt32)index);
      pTangent->x = (TFloat)data[0];
      pTangent->y = (TFloat)data[1];
      pTangent->z = (TFloat)data[2];
      return ESuccess;
   }
   return EFailure;
}

//============================================================
// <T>查找顶点切线。</T>
//============================================================
TResult FFbxMesh::ReadVertexCoord(TInt layer, TInt vertexIndex, TInt vertexCounter, SFloatPoint2* pCoord){
   TInt count = _pFbxMesh->GetElementUVCount();
   if(count < 1){
      return EFailure;
   }
   FbxGeometryElementUV* pElement = _pFbxMesh->GetElementUV((TInt32)layer);
   if(pElement == NULL){
      return EFailure;
   }
   FbxGeometryElement::EMappingMode mappingCd = pElement->GetMappingMode();
   FbxGeometryElement::EReferenceMode referenceCd = pElement->GetReferenceMode();
   TInt index = -1;
   switch(mappingCd){
      case FbxGeometryElement::eByControlPoint:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexIndex;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexIndex);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      case FbxGeometryElement::eByPolygonVertex:{
         if(referenceCd == FbxGeometryElement::eDirect){
            index = vertexCounter;
         }
         else if(referenceCd == FbxGeometryElement::eDirect){
            index = pElement->GetIndexArray().GetAt((TInt32)vertexCounter);
         }
         else{
            MO_FATAL(TC("Invalid referenceCd."));
         }
         break;
      }
      default:{
         MO_FATAL(TC("Invalid mappingCd."));
      }
   }
   if(index != -1){
      FbxVector2 data = pElement->GetDirectArray().GetAt((TInt32)index);
      pCoord->x = (TFloat)data[0];
      pCoord->y = (TFloat)data[1];
      return ESuccess;
   }
   return EFailure;
}

//============================================================
// <T>根据信息查找顶点。</T>
//============================================================
SFbxVertex* FFbxMesh::SyncVertex(SFloatPoint3* pPosition, SFloatColor4* pColor, SFloatVector3* pNormal, SFloatPoint2* pCoord){
   SFbxVertex* pVertex = NULL;
   // 查找顶点
   TInt count = _pVertexs->Count();
   for(TInt n = 0; n < count; n++){
      SFbxVertex* pFind = _pVertexs->Get(n);
      if((pPosition != NULL) && (pFind->position != *pPosition)){
         continue;
      }
      if((pColor != NULL) && (pFind->color != *pColor)){
         continue;
      }
      if((pNormal != NULL) && (pFind->normal != *pNormal)){
         continue;
      }
      if((pCoord != NULL) && (pFind->coord != *pCoord)){
         continue;
      }
      pVertex = pFind;
      break;
   }
   // 创建顶点
   if(pVertex == NULL){
      pVertex = new SFbxVertex();
      pVertex->index = _pVertexs->Count();
      if(pPosition != NULL){
         pVertex->position = *pPosition;
      }
      if(pColor != NULL){
         pVertex->color = *pColor;
      }
      if(pNormal != NULL){
         pVertex->normal = *pNormal;
      }
      if(pCoord != NULL){
         pVertex->coord = *pCoord;
      }
      _pVertexs->Push(pVertex);
   }
   return pVertex;
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FFbxMesh::Setup(){
   // 获得信息
   _code.Assign8(_pFbxNode->GetName());
   _pFbxMesh = _pFbxNode->GetMesh();
   MO_ASSERT_POINTER(_pFbxMesh);
   TString meshName;
   meshName.Assign8(_pFbxMesh->GetName());
   TInt layerCount = _pFbxMesh->GetLayerCount();
   TInt polygonCount = _pFbxMesh->GetPolygonCount();
   TInt pointCount = _pFbxMesh->GetControlPointsCount();
   TInt vertexColorCount = _pFbxMesh->GetElementVertexColorCount();
   TInt normalCount = _pFbxMesh->GetElementNormalCount();
   TInt binormalCount = _pFbxMesh->GetElementBinormalCount();
   TInt tangentCount = _pFbxMesh->GetElementTangentCount();
   TInt uvCount = _pFbxMesh->GetElementUVCount();
   MO_DEBUG(TC("Read mesh. (name=%s[%s], layer=%d, polygon=%d, point=%d, color=%d, normal=%d, binormal=%d, tangent=%d, uv=%d)"),
         (TCharC*)_code, (TCharC*)meshName, layerCount, polygonCount, pointCount, vertexColorCount, normalCount, binormalCount, tangentCount, uvCount);
   // 设置属性流
   _vertexAttributeCount = 1;
   if(vertexColorCount > 0){
      _vertexAttributeCount++;
      _vertexAttributes[EFbxVertexAttribute_Color] = ETrue;
   }
   if(normalCount > 0){
      _vertexAttributeCount++;
      _vertexAttributes[EFbxVertexAttribute_Normal] = ETrue;
   }
   if(binormalCount > 0){
      _vertexAttributeCount++;
      _vertexAttributes[EFbxVertexAttribute_Binormal] = ETrue;
   }
   if(tangentCount > 0){
      _vertexAttributeCount++;
      _vertexAttributes[EFbxVertexAttribute_Tangent] = ETrue;
   }
   // 建立数据结构
   TInt vertexCounter = 0;
   for(TInt32 polygonIndex = 0; polygonIndex < polygonCount; polygonIndex++){
      TInt polygonSize = _pFbxMesh->GetPolygonSize(polygonIndex);
      MO_ASSERT(polygonSize == 3);
      SFbxFace* pFace = new SFbxFace();
      for(TInt32 cornerIndex = 0; cornerIndex < polygonSize; cornerIndex++){
         TInt vertexIndex = _pFbxMesh->GetPolygonVertex(polygonIndex, cornerIndex);
         // 读取顶点
         SFloatPoint3 position;
         ReadVertexPosition(vertexIndex, &position);
         // 读取颜色
         SFloatColor4 color;
         SFloatColor4* pColor = NULL;
         if(vertexColorCount > 0){
            pColor = &color;
            ReadVertexColor(vertexIndex, vertexCounter, &color);
         }
         // 读取法线
         SFloatVector3 normal;
         ReadVertexNormal(vertexIndex, vertexCounter, &normal);
         // 读取副法线
         SFloatVector3 binormal;
         ReadVertexBinormal(vertexIndex, vertexCounter, &binormal);
         // 读取切线
         SFloatVector3 tangent;
         ReadVertexNormal(vertexIndex, vertexCounter, &tangent);
         // 查找顶点
         SFbxVertex* pVertex = SyncVertex(&position, NULL, NULL, NULL);
         pFace->corners[cornerIndex] = pVertex->index;
         // 累加位置
         vertexCounter++;
      }
      _pFaces->Push(pFace);
   }
   /*for(TInt32 layerIndex = 0; layerIndex < layerCount; layerIndex++){
      FbxLayer* pLayer = _pFbxMesh->GetLayer(layerIndex);
      if(pLayer != NULL){
         FbxLayerElementNormal* pElementNormal = pLayer->GetNormals();
         if(pElementNormal != NULL){
            FbxGeometryElement::EMappingMode modeCd = pElementNormal->GetMappingMode();
            FbxGeometryElement::EReferenceMode referenceCd = pElementNormal->GetReferenceMode();
            TInt directCount = pElementNormal->GetDirectArray().GetCount();
            TInt indexCount = pElementNormal->GetIndexArray().GetCount();
            MO_DEBUG(TC("Read mesh layer. (layer_index=%d, type=normal, mode_cd=%d, reference_cd=%d, direct_count=%d, index_ount=%d)"), layerIndex, modeCd, referenceCd, directCount, indexCount);
         }
         FbxLayerElementVertexColor* pElementVertexColor = pLayer->GetVertexColors();
         if(pElementVertexColor != NULL){
            FbxGeometryElement::EMappingMode modeCd = pElementVertexColor->GetMappingMode();
            FbxGeometryElement::EReferenceMode referenceCd = pElementVertexColor->GetReferenceMode();
            TInt directCount = pElementVertexColor->GetDirectArray().GetCount();
            TInt indexCount = pElementVertexColor->GetIndexArray().GetCount();
            MO_DEBUG(TC("Read mesh layer. (layer_index=%d, type=vertex_color, mode_cd=%d, reference_cd=%d, direct_count=%d, index_ount=%d)"), layerIndex, modeCd, referenceCd, directCount, indexCount);
         }
         FbxLayerElementUV* pElementUV = pLayer->GetUVs();
         if(pElementUV != NULL){
            FbxGeometryElement::EMappingMode modeCd = pElementUV->GetMappingMode();
            FbxGeometryElement::EReferenceMode referenceCd = pElementUV->GetReferenceMode();
            TInt directCount = pElementUV->GetDirectArray().GetCount();
            TInt indexCount = pElementUV->GetIndexArray().GetCount();
            MO_DEBUG(TC("Read mesh layer. (layer_index=%d, type=uv, mode_cd=%d, reference_cd=%d), direct_count=%d, index_ount=%d"), layerIndex, modeCd, referenceCd, directCount, indexCount);
         }
      }
   }*/
   return ESuccess;
}

//============================================================
// <T>序列化数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxMesh::Serialize(IDataOutput* pOutput){
   MO_ASSERT_POINTER(pOutput);
   // 写入顶点数据
   TInt vertexCount = _pVertexs->Count();
   pOutput->WriteInt32((TInt32)vertexCount);
   for(TInt32 vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++){
      SFbxVertex* pVertex = _pVertexs->Get(vertexIndex);
      SFloatPoint3& position = pVertex->position;
      pOutput->WriteFloat(position.x);
      pOutput->WriteFloat(position.y);
      pOutput->WriteFloat(position.z);
   }
   // 写入面数据
   TInt faceCount = _pFaces->Count();
   pOutput->WriteInt32((TInt32)faceCount);
   for(TInt32 faceIndex = 0; faceIndex < faceCount; faceIndex++){
      SFbxFace* pFace = _pFaces->Get(faceIndex);
      TInt* pCorners = pFace->corners;
      pOutput->WriteUint32((TUint32)pCorners[0]);
      pOutput->WriteUint32((TUint32)pCorners[1]);
      pOutput->WriteUint32((TUint32)pCorners[2]);
   }
   return ESuccess;
}


//============================================================
// <T>序列化内部数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxMesh::Store(FFbxResModelMesh* pResMesh){
   FFbxVertexs* pVertexs = _pVertexs;
   TInt vertexCount = pVertexs->Count();
   FFbxFaces* pFaces = _pFaces;
   TInt faceCount = pFaces->Count();
   // 设置属性
   pResMesh->SetCode(_code);
   // 写入顶点坐标数据流
   FFbxResStream* pPositionStream = MO_CREATE(FFbxResStream);
   pPositionStream->SetCode(TC("position"));
   pPositionStream->SetDataCount(vertexCount);
   pPositionStream->SetDataStride(sizeof(TFloat) * 3);
   pPositionStream->SetElementCount(3);
   pPositionStream->SetElementDataCd(EFbxData_Float32);
   FByteStream* pPositionData = pPositionStream->Data();
   for(TInt vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++){
      SFbxVertex* pVertex = pVertexs->Get(vertexIndex);
      SFloatPoint3& position = pVertex->position;
      pPositionData->WriteFloat(position.x);
      pPositionData->WriteFloat(position.y);
      pPositionData->WriteFloat(position.z);
   }
   pResMesh->VertexStreams()->Push(pPositionStream);
   // 写入顶点法线数据流
   //if(ContainsAttribute(EFbxVertexAttribute_Normal)){
   //   FFbxResStream* pNormalStream = MO_CREATE(FFbxResStream);
   //   pNormalStream->SetCode(TC("normal"));
   //   pNormalStream->SetDataCount(vertexCount);
   //   pNormalStream->SetDataStride(sizeof(TByte) * 3);
   //   pNormalStream->SetElementCount(3);
   //   pNormalStream->SetElementDataCd(EFbxData_Uint8);
   //   FByteStream* pNormalData = pNormalStream->Data();
   //   for(TInt vertexIndex = 0; vertexIndex < vertexCount; vertexIndex++){
   //      SFbxVertex* pVertex = pVertexs->Get(vertexIndex);
   //      SFloatVector3& normal = pVertex->normal;
   //      pNormalData->WriteUint8((TUint8)normal.x);
   //      pNormalData->WriteUint8((TUint8)normal.y);
   //      pNormalData->WriteUint8((TUint8)normal.z);
   //   }
   //   // _pStreams->Push(pPositionStream);
   //}
   //............................................................
   // 写入面索引数据流
   FFbxResStream* pIndexStream = MO_CREATE(FFbxResStream);
   FByteStream* pIndexData = pIndexStream->Data();
   pIndexStream->SetCode(TC("index16"));
   pIndexStream->SetDataCount(faceCount);
   pIndexStream->SetDataStride(sizeof(TUint16) * 3);
   pIndexStream->SetElementCount(3);
   pIndexStream->SetElementDataCd(EFbxData_Uint16);
   for(TInt faceIndex = 0; faceIndex < faceCount; faceIndex++){
      SFbxFace* pFace = pFaces->Get(faceIndex);
      TInt* pCorners = pFace->corners;
      pIndexData->WriteUint16((TUint16)pCorners[0]);
      pIndexData->WriteUint16((TUint16)pCorners[1]);
      pIndexData->WriteUint16((TUint16)pCorners[2]);
   }
   pResMesh->IndexStreams()->Push(pIndexStream);
   return ESuccess;
}

MO_NAMESPACE_END;
