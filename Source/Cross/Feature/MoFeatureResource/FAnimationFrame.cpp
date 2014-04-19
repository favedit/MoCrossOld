#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造动画帧。</T>
//============================================================
FAnimationFrame::FAnimationFrame(){
   MO_CLEAR(_pAnimation);
   MO_CLEAR(_pClip);
   _optionEmpty = EFalse;
   _delay = 0;
}

//============================================================
// <T>析构动画帧。</T>
//============================================================
FAnimationFrame::~FAnimationFrame(){
}

//============================================================
// <T>测试是否已经失效。</T>
//
// @return 是否已经失效
//============================================================
TBool FAnimationFrame::TestInvalid(){
   if(_optionEmpty){
      return ETrue;
   }
   return EFailure;
}

//============================================================
// <T>从输入流里反序列化信息内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TResult FAnimationFrame::Unserialize(IDataInput* pInput){
   // 读取资源属性
   _optionEmpty = pInput->ReadBool();
   _delay = pInput->ReadInt32();
   // 读取位图
   if(!_optionEmpty){
      // 读取大小
      _size.Unserialize16(pInput);
      // 读取有效区域
      _validLocation.Unserialize16(pInput);
      _validSize.Unserialize16(pInput);
      _validBarycenter.Unserialize16(pInput);
      _mergeLocation.Unserialize16(pInput);
      // 计算纹理
      SFloatSize2& mergeSize = _pAnimation->MergeFitSize();
      _coord.x1 = (TFloat)_mergeLocation.x / mergeSize.width;
      _coord.y1 = (TFloat)_mergeLocation.y / mergeSize.height;
      _coord.x2 = (TFloat)(_mergeLocation.x + _validSize.width) / mergeSize.width;
      _coord.y2 = (TFloat)(_mergeLocation.y + _validSize.height) / mergeSize.height;
      // 读取数据
      MO_DEBUG(TC("Unserialize resource picture info. (code=%d, size=%dx%d, valid_location=%d,%d, valid_size=%dx%d)"),
            _pAnimation->Code(), _size.width, _size.height, _validLocation.x, _validLocation.y, _validSize.width, _validSize.height);
   }
   return ESuccess;
}

MO_NAMESPACE_END
