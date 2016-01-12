#include "MoFbxCore.h"

MO_NAMESPACE_BEGIN;
MO_NAMESPACE_USING(FBXSDK_NAMESPACE);

//============================================================
// <T>构造FBX管理器。</T>
//============================================================
FFbxMesh::FFbxMesh() {
   MO_CLEAR(_pScene);
   MO_CLEAR(_pFbxMesh);
   _pVertexs = MO_CREATE(FObjects<SFbxVertex*>);
}

//============================================================
// <T>析构FBX管理器。</T>
//============================================================
FFbxMesh::~FFbxMesh() {
   MO_CLEAR(_pScene);
   MO_DESTORY(_pFbxMesh);
   MO_DELETE(_pVertexs);
}

//============================================================
// <T>查找顶点颜色。</T>
//============================================================
TResult FFbxMesh::ReadVertexColor(TInt vertexIndex, TInt vertexCounter, SFloatColor4* pColor){
   TInt vertexColorCount = _pFbxMesh->GetElementVertexColorCount();
   if(vertexColorCount < 1){
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
   TInt normalCount = _pFbxMesh->GetElementNormalCount();
   if(normalCount < 1){
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
   TInt normalCount = _pFbxMesh->GetElementNormalCount();
   if(normalCount < 1){
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
   TInt normalCount = _pFbxMesh->GetElementTangentCount();
   if(normalCount < 1){
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
// <T>根据信息查找顶点。</T>
//============================================================
SFbxVertex* FFbxMesh::SyncVertex(SFloatPoint3* pPosition, SFloatColor4* pColor, SFloatVector3* pNormal, SFloatPoint2* pCoord){
   SFbxVertex* pVertex = NULL;
   // 查找顶点
   TInt count = _pVertexs->Count();
   for(TInt n = 0; n < count; n++){
      SFbxVertex* pFind = _pVertexs->Get(n);
      if(pFind->position == *pPosition){
         if(pFind->color == *pColor){
            if(pFind->normal == *pNormal){
               pVertex = pFind;
               break;
            }
         }
      }
   }
   // 创建顶点
   if(pVertex == NULL){
      SFbxVertex* pVertex = new SFbxVertex();
      _pVertexs->Push(pVertex);
   }
   return pVertex;
}

//============================================================
// <T>配置处理。</T>
//============================================================
TResult FFbxMesh::Setup(){
   // 处理当前结点
   TString name;
   name.Assign8(_pFbxNode->GetName());
   _pFbxMesh = _pFbxNode->GetMesh();
   MO_ASSERT_POINTER(_pFbxMesh);
   TString meshName;
   meshName.Assign8(_pFbxMesh->GetName());
   TInt layerCount = _pFbxMesh->GetLayerCount();
   TInt polygonCount = _pFbxMesh->GetPolygonCount();
   TInt pointCount = _pFbxMesh->GetControlPointsCount();
   MO_DEBUG(TC("Read mesh. (name=%s[%s], layer_count=%d, point_count=%d, polygon_count=%d"), (TCharC*)name, (TCharC*)meshName, layerCount, polygonCount, pointCount);

   // 写入顶点数据
   //for(TInt32 pointIndex = 0; pointIndex < pointCount; pointIndex++){
   //   FbxVector4 point = _pFbxMesh->GetControlPointAt(pointIndex);
   //}
   // 写入面数据
   //FbxGeometryElementVertexColor* pVertexColor = _pFbxMesh->GetElementVertexColor(0);
   TInt vertexCounter = 0;
   for(TInt32 polygonIndex = 0; polygonIndex < polygonCount; polygonIndex++){
      TInt polygonSize = _pFbxMesh->GetPolygonSize(polygonIndex);
      MO_ASSERT(polygonSize == 3);
      for(TInt32 cornerIndex = 0; cornerIndex < polygonSize; cornerIndex++){
         TInt vertexIndex = _pFbxMesh->GetPolygonVertex(polygonIndex, cornerIndex);
         SFloatColor4 color;
         ReadVertexColor(vertexIndex, vertexCounter, &color);
         SFloatVector3 normal;
         ReadVertexNormal(vertexIndex, vertexCounter, &normal);
         SFloatVector3 tangent;
         ReadVertexNormal(vertexIndex, vertexCounter, &tangent);
         vertexCounter++;
      }
   }
   //SFbxVertex* pVertex = new SFbxVertex();
   //_pVertexs->Push(pVertex);

   MO_DEBUG(TC("Read mesh. (vertexColorCount=%d)"), _pFbxMesh->GetElementVertexColorCount());
   MO_DEBUG(TC("Read mesh. (GetElementNormalCount=%d)"), _pFbxMesh->GetElementNormalCount());
   MO_DEBUG(TC("Read mesh. (GetElementBinormalCount=%d)"), _pFbxMesh->GetElementBinormalCount());
   MO_DEBUG(TC("Read mesh. (GetElementTangentCount=%d)"), _pFbxMesh->GetElementTangentCount());
   MO_DEBUG(TC("Read mesh. (GetElementUVCount=%d)"), _pFbxMesh->GetElementUVCount());

   for(TInt32 layerIndex = 0; layerIndex < layerCount; layerIndex++){
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
   }
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
   TInt pointCount = _pFbxMesh->GetControlPointsCount();
   for(TInt32 pointIndex = 0; pointIndex < pointCount; pointIndex++){
      FbxVector4 point = _pFbxMesh->GetControlPointAt(pointIndex);
      pOutput->WriteFloat((TFloat)point[0]);
      pOutput->WriteFloat((TFloat)point[1]);
      pOutput->WriteFloat((TFloat)point[2]);
   }
   //TInt layerCount = _pFbxMesh->GetLayerCount();
   //for(TInt32 layerIndex = 0; layerIndex < layerCount; layerIndex++){
   //   FbxLayer* pLayer = _pFbxMesh->GetLayer(layerIndex);
   //   FbxGeometryElement::EMappingMode modeCd = pLayer->GetNormals()->GetMappingMode();
   //}
   // 写入面数据
   //FbxGeometryElementVertexColor* pVertexColor = _pFbxMesh->GetElementVertexColor(0);
   //TInt polygonCount = _pFbxMesh->GetPolygonCount();
   //for(TInt32 polygonIndex = 0; polygonIndex < polygonCount; polygonIndex++){
   //for(TInt32 index = 0; index < 3; index++){
   //TInt vertexIndex = _pFbxMesh->GetPolygonVertex(polygonIndex, index);
         //FbxLayerElementVertexColor a = *_pFbxMesh->GetElementVertexColor(0);
         // _pFbxMesh->GetElementVertexColor(0)->GetDirectArray().GetAt(0).mRed;
         //_pFbxMesh->GetElementVertexColor(0);
         //_pFbxMesh->GetElementTangent();
         //_pFbxMesh->GetElementNormal()
         // TInt polygonCount = _pFbxMesh->GetPolygonCount(polygonIndex).mRed;
         //FbxVector4 normal;
         //_pFbxMesh->GetPolygonVertexNormal(polygonIndex, index, normal);
         //}
         //}
   return ESuccess;
}

MO_NAMESPACE_END;
