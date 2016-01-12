#include "MoCrTemplate.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTemplateTagTrue, FTemplateTag);

//============================================================
// <T>构造模板真逻辑标签。</T>
//============================================================
FTemplateTagTrue::FTemplateTagTrue(){
   _nowrapRight = ETrue;
}

//============================================================
// <T>析构模板真逻辑标签。</T>
//============================================================
FTemplateTagTrue::~FTemplateTagTrue(){
}

//============================================================
// <T>开始标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagTrue::OnBegin(FTemplateContext* pContext){
   TBool result = ETrue;
   TInt length = _source.Length();
   // 计算结果
   TFsText value;
   TChar spliter = 0;
   TInt position = 0;
   while(position < length){
      TInt find = RChars::IndexsOf((TCharC*)_source + position, TC("|&"));
      if(find != ENotFound){
         spliter = _source.Get(position + find);
         value = _source.SubStrC(position, position + find);
         position += find + 1;
      }else{
         value = _source.SubStrC(position, length);
         position = length;
      }
      // 计算内容
      TBool flag = pContext->Config()->GetAsBool(value);
      if(spliter == '|'){
         if(flag){
            result = ETrue;
         }
      }else if(spliter == '&'){
         if(!result){
            break;
         }
         if(!flag){
            result = EFalse;
            break;
         }
      }else{
         result = flag;
      }
   }
   // 返回结果
   if(result){
      return ETemplateTagResult_Include;
   }
   return ETemplateTagResult_Continue;
}

//============================================================
// <T>结束标签处理。</T>
//
// @param pContext 标签环境
// @return 处理结果
//============================================================
ETemplateTagResult FTemplateTagTrue::OnEnd(FTemplateContext* pContext){
   return ETemplateTagResult_Stop;
}

//============================================================
// <T>设置一个属性。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
// @return 处理结果
//============================================================
TResult FTemplateTagTrue::SetAttribute(TCharC* pName, TCharC* pValue){
   if(RString::Equals(pName, TC("source"))){
      _source = pValue;
   }
   return ESuccess;
}

MO_NAMESPACE_END
