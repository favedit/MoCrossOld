#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造图片资源。</T>
//============================================================
FPictureResource::FPictureResource(){
   _typeCd = EResourceType_Picture;
   _optionPadding = EFalse;
   _optionAlpha = EFalse;
   //MO_CLEAR(_pBitmap);
}

//============================================================
// <T>析构图片资源。</T>
//============================================================
FPictureResource::~FPictureResource(){
   //MO_DELETE(_pBitmap);
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FPictureResource::Unserialize(IDataInput* pInput){
   // 读取基本属性
   FResource::Unserialize(pInput);
   // 读取资源属性
   _optionPadding = pInput->ReadBool();
   _optionAlpha = pInput->ReadBool();
   // 读取大小
   _size.width = pInput->ReadInt16();
   _size.height = pInput->ReadInt16();
   // 读取有效区域
   _validLocation.x = pInput->ReadInt16();
   _validLocation.y = pInput->ReadInt16();
   _validSize.width = pInput->ReadInt16();
   _validSize.height = pInput->ReadInt16();
   // 读取数据
   //MO_ASSERT(!_pBitmap);
   //_pBitmap = MO_CREATE(FBitmap);
   //_pBitmap->Setup();
   //_pBitmap->Resize(_size.width, _size.height);
   //_pBitmap->UploadData(pInput->PositionMemory(), _size.width, _size.height);
   //_pBitmap->Update();
   //// 读取数据
   //MO_DEBUG(TC("Unserialize resource picture info. (code=%d, option_padding=%d, option_alpha=%d, size=%dx%d, valid_location=%d,%d, valid_size=%dx%d)"),
   //      _code, _optionPadding, _optionAlpha, _size.width, _size.height, _validLocation.x, _validLocation.y, _validSize.width, _validSize.height);
   return ESuccess;
}

MO_NAMESPACE_END
