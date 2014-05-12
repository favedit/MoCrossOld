#include "MoBfRender.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FBfRenderCubeTexture, FRenderFlatTexture);

//============================================================
// <T>构造渲染CUBE纹理。</T>
//============================================================
FBfRenderCubeTexture::FBfRenderCubeTexture(){
   MO_CLEAR(_piTexture);
   MO_CLEAR(_piView);
   MO_CLEAR(_piState);
}

//============================================================
// <T>析构渲染CUBE纹理。</T>
//============================================================
FBfRenderCubeTexture::~FBfRenderCubeTexture(){
   MO_RELEASE(_piTexture);
   MO_RELEASE(_piView);
   MO_RELEASE(_piState);
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::OnSetup(){
   //glGenTextures(1, &_textureId);
   //MO_FATAL_CHECK(_textureId != 0, return EFailure,
   //      "Generate cube texture id failure. (texture_id=%d)", _textureId);
   //glBindTexture(GL_TEXTURE_CUBE_MAP, _textureId);
   return ESuccess;
}

//============================================================
// <T>改变大小处理。</T>
//
// @param width 宽度
// @param height 高度
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::Resize(TInt size){
   _size.Set(size, size);
   return ESuccess;
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::Upload(TByteC* pData, TInt length){
   // 检查参数
   MO_CHECK(pData, return ENull);
   MO_CHECK(length > 0, return ENull);
   MO_CHECK(_pDevice, return ENull);
   FBfRenderDevice* pRenderDevice = _pDevice->Convert<FBfRenderDevice>();
   // 释放资源
   MO_RELEASE(_piTexture);
   // 设置参数
   D3D10_TEXTURE2D_DESC descriptor = {0};
   descriptor.Width = _size.width;
   descriptor.Height = _size.height;
   descriptor.MipLevels = 1;
   descriptor.ArraySize = 6;
   descriptor.Format = DXGI_FORMAT_R8G8B8A8_UNORM;
   descriptor.SampleDesc.Count = 1;
   descriptor.SampleDesc.Quality = 0;
   descriptor.Usage = D3D10_USAGE_DEFAULT;
   descriptor.BindFlags = D3D10_BIND_SHADER_RESOURCE;
   descriptor.CPUAccessFlags = 0;
   descriptor.MiscFlags = D3D10_RESOURCE_MISC_TEXTURECUBE;
   //descriptor.MiscFlags = D3D10_RESOURCE_MISC_TEXTURECUBE | D3D10_RESOURCE_MISC_GENERATE_MIPS;
   // 设置内存
   D3D10_SUBRESOURCE_DATA data[6] = {0};
   for(TInt n = 0; n < 6; n++){
      D3D10_SUBRESOURCE_DATA& item = data[n];
      item.pSysMem = pData + sizeof(TUint32) * _size.Square() * n;
      item.SysMemPitch = sizeof(TUint32) * _size.width;
      item.SysMemSlicePitch = sizeof(TUint32) * _size.Square();
   }
   // 创建纹理
   HRESULT dxResult = pRenderDevice->NativeDevice()->CreateTexture2D(&descriptor, data, &_piTexture);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   // 上传数据
   D3D10_SHADER_RESOURCE_VIEW_DESC viewDescriptor;
   RType<D3D10_SHADER_RESOURCE_VIEW_DESC>::Clear(&viewDescriptor);
   viewDescriptor.Format = descriptor.Format;
   viewDescriptor.ViewDimension = D3D10_SRV_DIMENSION_TEXTURECUBE;
   viewDescriptor.TextureCube.MipLevels = -1;
   viewDescriptor.TextureCube.MostDetailedMip = 0;
   dxResult = pRenderDevice->NativeDevice()->CreateShaderResourceView(_piTexture, &viewDescriptor, &_piView);
   if(FAILED(dxResult)){
      MO_FATAL("Create buffer failure.");
      return EFailure;
   }
   // 创建取样器
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
// <T>从输入流内反序列化数据。</T>
//
// @return pInput 输入流
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::Unserialize(IDataInput* pInput){
   // 检查参数
   MO_CHECK(pInput, return ENull);
   // 读取尺寸
   _size.Unserialize16(pInput);
   // 上传数据
   TByte* pData = pInput->PositionMemory();
   Upload(pData, 4 * _size.Square());
   return ESuccess;
}

//============================================================
// <T>加载数据文件。</T>
//============================================================
TResult FBfRenderCubeTexture::LoadDataFile(TCharC* pFileName){
   // 检查参数
   MO_CHECK(pFileName, return ENull);
   // 读取文件
   FByteFile* pFile = MO_CREATE(FByteFile);
   pFile->LoadFile(pFileName);
   // 反序列化数据
   Unserialize(pFile);
   // 释放资源
   MO_DELETE(pFile);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::Suspend(){
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::Resume(){
   OnSetup();
   //if(!_pData->IsEmpty()){
   Upload(_pData->MemoryC(), _pData->Length());
   //}
   return ESuccess;
}

//============================================================
// <T>析构处理。</T>
//
// @return 处理结果
//============================================================
TResult FBfRenderCubeTexture::Dispose(){
   return ESuccess;
}

MO_NAMESPACE_END
