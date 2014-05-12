#include "MoBfRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FBfRenderFlatTexture, FRenderFlatTexture);

//============================================================
// <T>����ƽ��������</T>
//============================================================
FBfRenderFlatTexture::FBfRenderFlatTexture(){
   MO_CLEAR(_piTexture);
   MO_CLEAR(_piView);
   MO_CLEAR(_piState);
}

//============================================================
// <T>����ƽ��������</T>
//============================================================
FBfRenderFlatTexture::~FBfRenderFlatTexture(){
   MO_RELEASE(_piTexture);
   MO_RELEASE(_piView);
   MO_RELEASE(_piState);
}

//============================================================
// <T>���ô�����</T>
//
// @return �������
//============================================================
TResult FBfRenderFlatTexture::OnSetup(){
   FRenderFlatTexture::OnSetup();
   //glGenTextures(1, &_textureId);
   //_graphicHandle.data.uint32 = _textureId;
   //MO_FATAL_CHECK(_textureId != 0, return EFailure,
   //      "Generate flat texture id failure. (texture_id=%d)", _textureId);
   //glBindTexture(GL_TEXTURE_2D, _textureId);
   return ESuccess;
}

//============================================================
// <T>�ı��С������</T>
//
// @param width ����
// @param height �߶�
// @return �������
//============================================================
TResult FBfRenderFlatTexture::Resize(TInt width, TInt height){
   _size.Set(width, height);
   return ESuccess;
}

//============================================================
// <T>���ô�����</T>
//
// @return �������
//============================================================
TResult FBfRenderFlatTexture::Upload(TByteC* pData, TInt length){
   // ������
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return ENull);
   MO_CHECK(_pDevice, return ENull);
   FBfRenderDevice* pRenderDevice = _pDevice->Convert<FBfRenderDevice>();
   // �ͷ���Դ
   MO_RELEASE(_piTexture);
   // ���ò���
   D3D10_TEXTURE2D_DESC descriptor;
   RType<D3D10_TEXTURE2D_DESC>::Clear(&descriptor);
   descriptor.Width = _size.width;
   descriptor.Height = _size.height;
   descriptor.MipLevels = 1;
   descriptor.ArraySize = 1;
   descriptor.Format = DXGI_FORMAT_R8G8B8A8_UNORM;
   descriptor.SampleDesc.Count = 1;
   descriptor.Usage = D3D10_USAGE_DEFAULT;
   descriptor.BindFlags = D3D10_BIND_SHADER_RESOURCE;
   // �����ڴ�
   D3D10_SUBRESOURCE_DATA data;
   RType<D3D10_SUBRESOURCE_DATA>::Clear(&data);
   data.pSysMem = pData;
   data.SysMemPitch = sizeof(TUint32) * _size.width;
   // ��������
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateTexture2D(&descriptor, &data, &_piTexture);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   // �ϴ�����
   D3D10_SHADER_RESOURCE_VIEW_DESC viewDescriptor;
   RType<D3D10_SHADER_RESOURCE_VIEW_DESC>::Clear(&viewDescriptor);
   viewDescriptor.Format = descriptor.Format;
   viewDescriptor.ViewDimension = D3D10_SRV_DIMENSION_TEXTURE2D;
   viewDescriptor.Texture2D.MipLevels = descriptor.MipLevels;
   viewDescriptor.Texture2D.MostDetailedMip = 0;
   dxResult = pRenderDevice->NativeDevice()->CreateShaderResourceView(_piTexture, &viewDescriptor, &_piView);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   // ����ȡ����
   D3D10_SAMPLER_DESC samplerDescriptor;
   RType<D3D10_SAMPLER_DESC>::Clear(&samplerDescriptor);
   samplerDescriptor.Filter = D3D10_FILTER_MIN_MAG_MIP_LINEAR;
   samplerDescriptor.AddressU = D3D10_TEXTURE_ADDRESS_WRAP;
   samplerDescriptor.AddressV = D3D10_TEXTURE_ADDRESS_WRAP;
   samplerDescriptor.AddressW = D3D10_TEXTURE_ADDRESS_WRAP;
   samplerDescriptor.ComparisonFunc = D3D10_COMPARISON_NEVER;
   samplerDescriptor.MinLOD = 0;
   samplerDescriptor.MaxLOD = D3D10_FLOAT32_MAX;
   dxResult = pRenderDevice->NativeDevice()->CreateSamplerState(&samplerDescriptor, &_piState);
   if(FAILED(dxResult)){
      MO_FATAL("Create sampler state failure.");
      return EFailure;
   }
   //D3D10_MAPPED_TEXTURE2D mappedData;
   //_piTexture->Map(D3D10CalcSubresource(0, 0, 1), D3D10_MAP_WRITE_DISCARD, 0, &mappedData);
   //TByte* pMappedData = (TByte*)mappedData.pData;
   //MO_LIB_MEMORY_COPY(pMappedData, length, pData, length);
   //_piTexture->Unmap(D3D10CalcSubresource(0, 0, 1));
   return ESuccess;
}

//============================================================
// <T>���������ڷ����л����ݡ�</T>
//
// @return pInput ������
// @return �������
//============================================================
TResult FBfRenderFlatTexture::Unserialize(IDataInput* pInput){
   // ������
   MO_CHECK(pInput, return ENull);
   // ��ȡ�ߴ�
   _size.Unserialize16(pInput);
   // �ϴ�����
   TByte* pData = pInput->PositionMemory();
   Upload(pData, 4 * _size.Square());
   return ESuccess;
}

//============================================================
// <T>���������ļ���</T>
//============================================================
TResult FBfRenderFlatTexture::LoadDataFile(TCharC* pFileName){
   // ������
   MO_CHECK(pFileName, return ENull);
   // ��ȡ�ļ�
   FByteFile* pFile = MO_CREATE(FByteFile);
   pFile->LoadFile(pFileName);
   // �����л�����
   Unserialize(pFile);
   // �ͷ���Դ
   MO_DELETE(pFile);
   return ESuccess;
}

//============================================================
// <T>��������</T>
//
// @return �������
//============================================================
TResult FBfRenderFlatTexture::Suspend(){
   return ESuccess;
}

//============================================================
// <T>����������</T>
//
// @return �������
//============================================================
TResult FBfRenderFlatTexture::Resume(){
   OnSetup();
   if(!_pData->IsEmpty()){
      Upload(_pData->MemoryC(), _pData->Length());
   }
   //MO_INFO("Resume texture. (texture_id=%d, size=%dx%d, data_length=%d)", _textureId, _size.width, _size.height, _pData->Length());
   return ESuccess;
}

//============================================================
// <T>����������</T>
//
// @return �������
//============================================================
TResult FBfRenderFlatTexture::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END