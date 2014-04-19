#include "MoFgRender.h"
#include "MoFgTechnique.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FEffect, FInstance);

//============================================================
// <T>������ȾЧ����</T>
//============================================================
FEffect::FEffect(){
}

//============================================================
// <T>������ȾЧ����</T>
//============================================================
FEffect::~FEffect(){
}

//============================================================
// <T>���ô�����</T>
//
// @return �������
//============================================================
TResult FEffect::Setup(){
   // ��������
   _program = _renderDevice->CreateProgrom();
   _program->Setup();
   _program->VertexShader()->Compile(_vertexSource);
   _program->FragmentShader()->Compile(_fragmentSource);
   _program->Build();
   _program->Link();
   return ESuccess;
}

//============================================================
// <T>����������Ϣ��</T>
//
// @param pConfig ���ô���
// @return �������
//============================================================
TResult FEffect::LoadConfig(FXmlNode* pConfig){
   _descriptor.LoadConfig(pConfig);
   return ESuccess;
}

//============================================================
// <T>������Ⱦ�����佨��Ч����������</T>
//
// @param renderableDescriptor ��Ⱦ������
// @return Ч��������
//============================================================
TResult FEffect::BuildDescripter(SRenderableDescriptor& renderableDescriptor){
   return ESuccess;
}

//============================================================
// <T>����ģ����Ϣ��</T>
//
// @param renderableDescriptor ��Ⱦ����
// @param pCode ����
// @param pTemplateContext ģ�廷��
// @return �������
//============================================================
TResult FEffect::BuildTemplate(SRenderableDescriptor& renderableDescriptor, MString* pCode, FTemplateContext* pTemplateContext){
   return ESuccess;
}

//============================================================
// <T>�����С��</T>
//
// @return �������
//============================================================
TResult FEffect::Resize(TInt width, TInt height){
   return ESuccess;
}

//============================================================
// <T>���¿�ʼ������</T>
//
// @return �������
//============================================================
TResult FEffect::UpdateBegin(){
   return ESuccess;
}

//============================================================
// <T>д�����Ⱦ����</T>
//
// @param pRenderable ����Ⱦ����
//============================================================
TResult FEffect::WriteRenderable(FRenderable* pRenderable){
   return ESuccess;
}

//============================================================
// <T>���½���������</T>
//
// @return �������
//============================================================
TResult FEffect::UpdateEnd(){
   return ESuccess;
}

//============================================================
// <T>���ƴ�����</T>
//
// @return �������
//============================================================
TResult FEffect::DrawRenderable(FRenderRegion* pRegion, FRenderable* pRenderable){
   return ESuccess;
}

//============================================================
// <T>���ƴ�����</T>
//
// @return �������
//============================================================
TResult FEffect::DrawGroup(FRenderRegion* pRegion, TInt offset, TInt count){
   MO_CHECK(pRegion, return ENull);
   FRenderableCollection* pRenderables = pRegion->VisibleRenderables();
   for(TInt n = 0; n < count; n++){
      FRenderable* pRenderable = pRenderables->Get(offset + n);
      DrawRenderable(pRegion, pRenderable);
   }
   return ESuccess;
}

MO_NAMESPACE_END