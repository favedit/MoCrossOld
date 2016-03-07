#include "MoFrContent3dModel.h"
#include "MoFrContent3dTemplate.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源3D模板。</T>
//============================================================
FRs3dTemplate::FRs3dTemplate(){
   _pModels = MO_CREATE(FRs3dModelCollection);
   _pRenderables = MO_CREATE(FRs3dTemplateRenderableCollection);
}

//============================================================
// <T>析构资源3D模板。</T>
//============================================================
FRs3dTemplate::~FRs3dTemplate(){
   MO_DELETE(_pModels);
   MO_DELETE(_pRenderables);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FRs3dTemplate::Unserialize(IDataInput* pInput){
   MO_CHECK(pInput, return ENull);
   // 读取父内容
   FResource3d::Unserialize(pInput);
   // 读取跟踪列表
   TInt renderableCount = pInput->ReadInt16();
   for(TInt n = 0; n < renderableCount; n++){
      // 创建模板渲染对象
      FRs3dTemplateRenderable* pRenderable = FRs3dTemplateRenderable::InstanceCreate();
      pRenderable->Unserialize(pInput);
      _pRenderables->Push(pRenderable);
      // 建立模型集合
      TCharC* pModelName = pRenderable->ModelName();
      FRs3dModel* pRsModel = RResource3dManager::Instance().ModelConsole()->Find(pModelName);
      MO_CHECK(pRsModel, return ENull);
      _pModels->PushUnique(pRsModel);
   }
   MO_DEBUG(TC("Unserialize template success. (code=%d, renderable_count=%d)"),
         _code, renderableCount);
   return ESuccess;
}

//============================================================
// <T>清空跟踪集合。</T>
//============================================================
void FRs3dTemplate::RenderableClear(){
   TInt count = _pRenderables->Count();
   for(TInt n = 0; n < count; n++){
      FRs3dTemplateRenderable* pRenderable = _pRenderables->Get(n);
      MO_DELETE(pRenderable);
   }
   _pRenderables->Clear();
}

MO_NAMESPACE_END
