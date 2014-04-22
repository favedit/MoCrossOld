#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造动画资源。</T>
//============================================================
FAnimationResource::FAnimationResource(){
   _typeCd = EResourceType_Animation;
   _spanTotal = 0;
   _pFrames = MO_CREATE(FIntVector);
   _pClips = MO_CREATE(FAnimationClipVector);
   //MO_CLEAR(_pBitmap);
}

//============================================================
// <T>析构动画资源。</T>
//============================================================
FAnimationResource::~FAnimationResource(){
   MO_DELETE(_pFrames);
   TInt count = _pClips->Count();
   for(TInt n = 0; n < count; n++){
      FAnimationClip* pClip = _pClips->Get(n);
      MO_DELETE(pClip);
   }
   MO_DELETE(_pClips);
   // 删除位图
   //MO_DELETE(_pBitmap);
}

//============================================================
// <T>测试是否已经失效。</T>
//
// @return 是否已经失效
//============================================================
TBool FAnimationResource::TestInvalid(){
   TInt count = _pClips->Count();
   for(TInt n = 0; n < count; n++){
      FAnimationClip* pClip = _pClips->Get(n);
      if(!pClip->TestInvalid()){
         return EFalse;
      }
   }
   return ETrue;
}

//============================================================
// <T>根据间隔计算帧位置</T>
//
// @param span 间隔
// @return 帧位置
//============================================================
TInt FAnimationResource::CalculateFrameIndex(TInt span){
   TInt spanClip = span % _spanTotal;
   TInt count = _pFrames->Count();
   for(TInt n = count - 1; n >= 0; n--){
      TInt spanFrame = _pFrames->Get(n);
      if(spanClip > spanFrame){
         return n;
      }
   }
   return 0;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FAnimationResource::Unserialize(IDataInput* pInput){
   // 读取基本属性
   FResource2d::Unserialize(pInput);
   // 读取资源属性
   _size.Unserialize16(pInput);
   _baryCenter.Unserialize16(pInput);
   _mergeSize.Unserialize16(pInput);
   //_mergeFitSize.width = (TFloat)RBitmap::CalculateFitLength(_mergeSize.width);
   //_mergeFitSize.height = (TFloat)RBitmap::CalculateFitLength(_mergeSize.height);
   // 读取延时集合
   _pFrames->Push(0);
   TInt frameCount = pInput->ReadUint16();
   for(TInt n = 0; n < frameCount; n++){
      // 单位毫秒
      TInt span = pInput->ReadUint16();
      _spanTotal += span;
      _pFrames->Push(_spanTotal);
   }
   // 读取剪辑集合
   TInt clipCount = pInput->ReadUint8();
   for(TInt n = 0; n < clipCount; n++){
      FAnimationClip* pClip = MO_CREATE(FAnimationClip);
      pClip->SetAnimation(this);
      pClip->Unserialize(pInput);
      _pClips->Push(pClip);
   }
   //............................................................
   //// 读取图片
   //TInt size = sizeof(TUint32) * _mergeSize.Square();
   //MO_ASSERT(!_pBitmap);
   //_pBitmap = MO_CREATE(FBitmap);
   //_pBitmap->Setup();
   //_pBitmap->Resize(_mergeSize.width, _mergeSize.height);
   //_pBitmap->UploadData(pInput->PositionMemory(), _mergeSize.width, _mergeSize.height);
   //_pBitmap->Update();
   //MO_DEBUG(TC("Unserialize resource animation success. (code=%d, size=%dx%d, merge_size=%dx%d)"),
   //      _code, _size.width, _size.height, _mergeSize.width, _mergeSize.height);
   return ESuccess;
}

MO_NAMESPACE_END
