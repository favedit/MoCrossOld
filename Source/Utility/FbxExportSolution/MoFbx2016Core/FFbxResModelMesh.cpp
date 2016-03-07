#include "MoFbxResource.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造FBX场景。</T>
//============================================================
FFbxResModelMesh::FFbxResModelMesh() {
   _typeName = TC("ModelMesh");
   _guid = RGuid::Generate();
   _pVertexStreams = MO_CREATE(FFbxResStreams);
   _pIndexStreams = MO_CREATE(FFbxResStreams);
}

//============================================================
// <T>析构FBX场景。</T>
//============================================================
FFbxResModelMesh::~FFbxResModelMesh() {
   MO_DELETE(_pVertexStreams);
   MO_DELETE(_pIndexStreams);
}

//============================================================
// <T>序列化内部数据到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TResult FFbxResModelMesh::Serialize(IDataOutput* pOutput){
   FFbxResourceComponent::Serialize(pOutput);
   // 输出顶点数据流集合
   TInt vertexStreamCount = _pVertexStreams->Count();
   pOutput->WriteInt32((TInt32)vertexStreamCount);
   for(TInt n = 0; n < vertexStreamCount; n++){
      FFbxResStream* pStream = _pVertexStreams->Get(n);
      pStream->Serialize(pOutput);
   }
   // 输出索引数据流集合
   TInt indexStreamCount = _pIndexStreams->Count();
   pOutput->WriteInt32((TInt32)indexStreamCount);
   for(TInt n = 0; n < indexStreamCount; n++){
      FFbxResStream* pStream = _pIndexStreams->Get(n);
      pStream->Serialize(pOutput);
   }
   return ESuccess;
}

//============================================================
// <T>保存内部数据到配置节点。</T>
//
// @param pConfig 配置节点
// @return 处理结果
//============================================================
TResult FFbxResModelMesh::SaveConfig(FXmlNode* pConfig){
   FFbxResourceComponent::SaveConfig(pConfig);
   // 输出网格集合
   //TInt meshCount = _pMeshs->Count();
   //pOutput->WriteInt32((TInt32)meshCount);
   //for(TInt n = 0; n < meshCount; n++){
   //   FFbxResMesh* pMesh = _pMeshs->Get(n);
   //   pMesh->Serialize(pOutput);
   //}
   return ESuccess;
}

MO_NAMESPACE_END;
