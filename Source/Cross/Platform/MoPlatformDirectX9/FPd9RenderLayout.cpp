#include "MoPd9Render.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FPd9RenderLayout, FRenderLayout);

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
FRenderLayoutElement* FPd9RenderLayout::FindByAttribute(FRenderShaderAttribute* pAttribute){
   TInt count = _elements.Count();
   for(TInt n = 0; n < count; n++){
      FRenderLayoutElement* pElement = _elements.Get(n);
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
   FRenderVertexStreams* pVertexStreams = _pRenderable->VertexStreams();
   GRenderShaderAttributeDictionary::TIterator iterator = _pProgram->Attributes().IteratorC();
   TInt fvf1 = 0;
   TInt fvf2 = 0;
   D3DVERTEXELEMENT9 elements[MO_INPUT_ELEMENT_MAXCNT];
   while(iterator.Next()){
      FRenderShaderAttribute* pAttribute = *iterator;
      //if(!pAttribute->IsStatusUsed()){
      //   continue;
      //}
      //............................................................
      TInt bufferCd = pAttribute->Code();
      ERenderShaderAttributeFormat formatCd = pAttribute->FormatCd();
      FRenderVertexStream* pStream = pVertexStreams->FindStream(bufferCd);
      FRenderLayoutElement* pElement = FRenderLayoutElement::InstanceCreate();
      pElement->SetAttribute(pAttribute);
      pElement->SetStream(pStream);
      Push(pElement);
      //............................................................
      // 设置缓冲信息
      if(pStream != NULL){
         FPd9RenderVertexBuffer* pVertexBuffer = pStream->VertexBuffer()->Convert<FPd9RenderVertexBuffer>();
         _total = pVertexBuffer->Count();
         _piBuffer[index] = pVertexBuffer->NativeBuffer();
         _strides[index] = pStream->Stride();
         _offsets[index] = pStream->Offset();
         if(index == 0){
            fvf1 = GetFvF(index);
         }else{
            fvf2 = GetFvF(index);
         }

         D3DDECLTYPE typeCd = RDirectX9::ConvertAttrbuteFormat(formatCd);
         D3DDECLUSAGE usageCd;
         TInt usageIndex;
         RDirectX9::ConvertAttrbuteUsage(index, &usageCd, &usageIndex);

         D3DVERTEXELEMENT9 element = {0};
         element.Stream = 0;
         element.Offset = pStream->Offset();
         element.Type = RDirectX9::ConvertAttrbuteFormat(formatCd);
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
