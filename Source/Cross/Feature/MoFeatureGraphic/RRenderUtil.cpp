#include "MoFgRender.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>计算当前设备支持合并最大数。</T>
//
// @return 最大数
//============================================================
TInt RRenderUtil::CalculateMergeLimit(){
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 计算常量缓冲限制
   TInt vertexConstLimit = pRenderDevice->Capability()->VertexConstLimit();
   TInt constLimit = (vertexConstLimit - MO_EG_CONST_RESERVE) / 4;
   return constLimit;
}

//============================================================
// <T>计算当前设备支持实例的最大个数。</T>
//
// @param vertexCount 顶点数量
// @param boneCount 骨头数量
//============================================================
TInt RRenderUtil::CalculateInstanceCount(TInt vertexCount, TInt boneCount){
   FRenderDevice* pRenderDevice = RDeviceManager::Instance().Find<FRenderDevice>();
   // 计算常量缓冲限制
   TInt vertexConstLimit = pRenderDevice->Capability()->VertexConstLimit();
   TInt constRequire = (3 * boneCount) + 4;
   TInt constLimit = (vertexConstLimit - MO_EG_CONST_RESERVE) / constRequire;
   TInt instanceCount = constLimit;
   // 计算顶点限制
   if(vertexCount > 0){
      TInt vertexCountLimit = pRenderDevice->Capability()->VertexCountLimit();
      TInt vertexLimit = vertexCountLimit / vertexCount;
      instanceCount = MO_LIB_MIN(instanceCount, vertexLimit);
   }
   // 计算其他限制
   instanceCount = MO_LIB_MIN(instanceCount, MO_EG_CONST_INSTANCE_MAX);
   return instanceCount;
}

MO_NAMESPACE_END
