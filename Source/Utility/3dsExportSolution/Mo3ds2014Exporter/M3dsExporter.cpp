#include "Mo3dsDefine.h"
#include "Mo3dsExporter.h"

MO_NAMESPACE_BEGIN;

//============================================================
// <T>构造场景导出器。</T>
//
// @param pDocument 输出文档
// @param piScene 场景
// @param time 时间
// @param piInterface 接口
// @return 场景导出器
//============================================================
M3dsExporter::M3dsExporter(){
   // 设置变量
   _piScene = NULL;
   _time = 0;
   _piInterface = NULL;
   _piRootNode = NULL;
   _pSceneNode = NULL;
   // 创建集合
   _pMaterials = MO_CREATE(FMaterialVector);
   // 创建输出文档
   _pDocument = MO_CREATE(FXmlDocument);
   InnerInitialize(_pDocument->Root());
   // 创建文件流
   _pFileStream = MO_CREATE(FByteFile);
   _pFileStream->EnsureSize(1024*1024);
}

//============================================================
// <T>构造场景导出器。</T>
//
// @param pDocument 输出文档
// @param piScene 场景
// @param time 时间
// @param piInterface 接口
// @return 场景导出器
//============================================================
M3dsExporter::M3dsExporter(IScene* piScene, TimeValue time, Interface* piInterface){
   // 设置变量
   _piScene = piScene;
   _time = time;
   _piInterface = piInterface;
   _piRootNode = _piInterface->GetRootNode();
   // 创建集合
   _pMaterials = MO_CREATE(FMaterialVector);
   // 创建输出文档
   _pDocument = MO_CREATE(FXmlDocument);
   _pFileStream = MO_CREATE(FByteFile);
   InnerInitialize(_pDocument->Root());
}

//============================================================
// <T>析构场景导出器。</T>
//============================================================
M3dsExporter::~M3dsExporter(){
   MO_DELETE(_pMaterials);
   MO_DELETE(_pDocument);
   MO_DELETE(_pFileStream);
}

//============================================================
void M3dsExporter::InnerInitialize(FXmlNode* pConfig){
   // 设置根节点
   pConfig->SetName(TC("M3xExport"));
   pConfig->Set(TC("version"), TC("1.0"));
   // 创建场景
   _pSceneNode = pConfig->CreateNode(TC("Scene"));
   // 网格列表节点
   _pStructNode = _pSceneNode->CreateNode(TC("Struct"));
   // 材质列表节点
   _pMaterialsNode = _pSceneNode->CreateNode(TC("MaterialCollection"));
   // 网格列表节点
   _pMeshNode = _pSceneNode->CreateNode(TC("Mesh"));
   // 骨骼节点
   _pSkeletonNode = _pSceneNode->CreateNode(TC("Skeleton"));
   // 动画节点
   _pAnimationNode = _pSceneNode->CreateNode(TC("Animation"));
   // 日志节点
   _pLoggerNode = pConfig->CreateNode(TC("Logger"));
}

//============================================================
// <T>转换场景为配置节点。</T>
//
// @param pConfig 配置节点
//============================================================
void M3dsExporter::Convert(){
}

//============================================================
void M3dsExporter::SaveAs(TCharC* pFileName, TCharC* pEncoding){
   _pDocument->SetEncoding(pEncoding);
   _pDocument->SaveFile8(pFileName);
}

MO_NAMESPACE_END;