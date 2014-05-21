#include "MoFgBase.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FRenderableData, FInstance);

//============================================================
// <T>构造渲染对象属性。</T>
//============================================================
FRenderableData::FRenderableData(){
   _vertexCount = 0;
   MO_CLEAR(_pIndexBuffer);
}

//============================================================
// <T>析构渲染对象属性。</T>
//============================================================
FRenderableData::~FRenderableData(){
}

//============================================================
// <T>查找指定代码的属性。</T>
//
// @param pCode 代码
// @return 属性
//============================================================
FRenderableAttribute* FRenderableData::AttributeFind(TCharC* pCode){
   MO_CHECK(pCode, return NULL);
   TInt count = _attributes.Count();
   for(TInt n = 0; n < count; n++){
      FRenderableAttribute* pRenderableAttribute = _attributes.Get(n);
      if(pRenderableAttribute->IsCode(pCode)){
         return pRenderableAttribute;
      }
   }
   return NULL;
}

//============================================================
// <T>获得指定代码的属性。</T>
//
// @param pCode 代码
// @return 属性
//============================================================
FRenderableAttribute* FRenderableData::AttributeGet(TCharC* pCode){
   MO_CHECK(pCode, return NULL);
   FRenderableAttribute* pRenderableAttribute = AttributeFind(pCode);
   if(pRenderableAttribute == NULL){
      MO_FATAL("Can't find renderable attribute. (code=%s)", pCode);
   }
   return pRenderableAttribute;
}

//============================================================
// <T>增加一个属性。</T>
//
// @param pAttribute 属性
// @return 处理结果
//============================================================
TResult FRenderableData::AttributePush(FRenderableAttribute* pAttribute){
   MO_CHECK(pAttribute, return ENull);
   // 查找是否存在
   TCharC* pCode = pAttribute->Code();
   FRenderableAttribute* pFind = AttributeFind(pCode);
   if(pFind != NULL){
      MO_FATAL("Attribute is already exists. (code=%s)", pCode);
      return EDuplicate;
   }
   // 添加属性
   _attributes.Push(pAttribute);
   return ESuccess;
}

//============================================================
// <T>移除一个属性。</T>
//
// @param pAttribute 属性
// @return 处理结果
//============================================================
TResult FRenderableData::AttributeRemove(FRenderableAttribute* pAttribute){
   MO_CHECK(pAttribute, return ENull);
   // 查找是否存在
   if(!_attributes.Contains(pAttribute)){
      MO_FATAL("Attribute is not exists. (code=%s)", pAttribute->Code());
      return ENotExists;
   }
   // 添加属性
   _attributes.Push(pAttribute);
   return ESuccess;
}

//============================================================
// <T>接收指定数据。</T>
//
// @param pData 数据
// @return 处理结果
//============================================================
TResult FRenderableData::Assign(FRenderableData* pData){
   MO_CHECK(pData, return ENull);
   // 设置属性
   _vertexCount = pData->VertexCount();
   // 设置属性集合
   _attributes.Clear();
   GRenderableAttributePtrs::TIterator iterator = pData->Attributes().Iterator();
   while(iterator.Next()){
      _attributes.Push(*iterator);
   }
   // 设置索引
   _pIndexBuffer = pData->IndexBuffer();
   return ESuccess;
}

MO_NAMESPACE_END
