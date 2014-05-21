#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderLayout, FRenderProgramLayout);

//============================================================
// <T>构造渲染层信息。</T>
//============================================================
FPd9RenderLayout::FPd9RenderLayout(){
   MO_CLEAR(_piDeclaration);
}

//============================================================
// <T>析构渲染层信息。</T>
//============================================================
FPd9RenderLayout::~FPd9RenderLayout(){
   MO_RELEASE(_piDeclaration);
}

//============================================================
FRenderProgramLayoutElement* FPd9RenderLayout::FindByAttribute(FRenderProgramAttribute* pAttribute){
   TInt count = _elements.Count();
   for(TInt n = 0; n < count; n++){
      FRenderProgramLayoutElement* pElement = _elements.Get(n);
      if(pElement->Attribute() == pAttribute){
         return pElement;
      }
   }
   return NULL;
}

TInt GetFvF(TInt index){
   switch(index){
      case 0:
         return D3DFVF_XYZ;
      case 1:
         return D3DFVF_TEX0;
      case 2:
         return D3DFVF_TEX1;
      case 3:
         return D3DFVF_TEX2;
      case 4:
         return D3DFVF_TEX3;
      case 5:
         return D3DFVF_TEX4;
      case 6:
         return D3DFVF_TEX5;
      case 7:
         return D3DFVF_TEX6;
      case 8:
         return D3DFVF_TEX7;
      case 9:
         return D3DFVF_TEX8;
   }
   return 0;
}

//============================================================
TResult FPd9RenderLayout::OnSetup(){
   MO_CHECK(_pDevice, return ENull);
   TInt position = 0;
   TInt index = 0;
   FPd9RenderDevice* pRenderDevice = _pDevice->Convert<FPd9RenderDevice>();
   FRenderableData* pRenderableData = _pRenderable->Data();
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   TInt fvf1 = 0;
   TInt fvf2 = 0;
   D3DVERTEXELEMENT9 elements[MO_INPUT_ELEMENT_MAXCNT];
   while(iterator.Next()){
      FRenderProgramAttribute* pAttribute = *iterator;
      //if(!pAttribute->IsStatusUsed()){
      //   continue;
      //}
      //............................................................
      TCharC* pBufferCode = pAttribute->Linker();
      FRenderableAttribute* pRenderableAttribute = pRenderableData->AttributeFind(pBufferCode);
      //FRenderProgramLayoutElement* pElement = FRenderProgramLayoutElement::InstanceCreate();
      //pElement->SetAttribute(pAttribute);
      //pElement->SetStream(pStream);
      //Push(pElement);
      //............................................................
      // 设置缓冲信息
      if(pAttribute != NULL){
         FPd9RenderVertexBuffer* pVertexBuffer = pRenderableAttribute->VertexBuffer()->Convert<FPd9RenderVertexBuffer>();
         _total = pVertexBuffer->Count();
         _piBuffer[index] = pVertexBuffer->NativeBuffer();
         _strides[index] = pVertexBuffer->Stride();
         _offsets[index] = pRenderableAttribute->Offset();
         if(index == 0){
            fvf1 = GetFvF(index);
         }else{
            fvf2 = GetFvF(index);
         }
         D3DDECLUSAGE usageCd;
         TInt usageIndex;
         RDirectX9::ConvertAttrbuteUsage(index, &usageCd, &usageIndex);
         // 设置元素信息
         D3DVERTEXELEMENT9 element = {0};
         element.Stream = 0;
         element.Offset = pRenderableAttribute->Offset();
         element.Type = RDirectX9::ConvertAttrbuteFormat(pRenderableAttribute->FormatCd());
         element.Method = D3DDECLMETHOD_DEFAULT;
         element.Usage = usageCd;
         element.UsageIndex = usageIndex;
         elements[index] = element;
         index++;
      }
   }
   _formatCd = fvf1 + fvf2;
   _count = index;
   // 创建顶点声明
   D3DVERTEXELEMENT9 elementEnd = D3DDECL_END();
   elements[index] = elementEnd;
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateVertexDeclaration(elements, &_piDeclaration);
   if(FAILED(dxResult)){
      return pRenderDevice->CheckError(dxResult, "CreateVertexDeclaration", "Create vertex declaration failure. (count=%d)", _count);
   }
   return ESuccess;
}

MO_NAMESPACE_END
