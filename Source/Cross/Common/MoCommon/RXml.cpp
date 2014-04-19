#include "MoCmXml.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>生成节点属性内容。</T>
//
// @param pXml 属性内容
// @param pValue 字符串
// @return 处理结果
//============================================================
TBool RXml::MakeNodeAttribute(MString* pXml, TCharC* pValue){
   // 检查参数
   if((pXml == NULL) || (pValue == NULL)){
      return EFalse;
   }
   // 检查长度
   TInt length = RString::Length(pValue);
   if(length == 0){
      return EFalse;
   }
   // 追加内容
   for(TInt n = 0; n < length; n++){
      TChar value = pValue[n];
      if('&' == value){
         pXml->Append(TC("&amp;"));
      }else if('"' == value){
         pXml->Append(TC("&quot;"));
      }else if('<' == value){
         pXml->Append(TC("&lt;"));
      }else if('>' == value){
         pXml->Append(TC("&gt;"));
      }else if('\r' == value){
         continue;
      }else if('\n' == value){
         pXml->Append(TC("\\n"));
      }else{
         pXml->Append(value);
      }
   }
   return ETrue;
}

//============================================================
// <T>生成节点内容。</T>
//
// @param pXml 节点内容
// @param pValue 字符串
// @return 处理结果
//============================================================
TBool RXml::MakeNodeText(MString* pXml, TCharC* pValue){
   // 检查参数
   if((pXml == NULL) || (pValue == NULL)){
      return EFalse;
   }
   // 检查长度
   TSize length = RString::Length(pValue);
   if(length == 0){
      return EFalse;
   }
   // 追加内容
   for(TSize n = 0; n < length; n++){
      TChar value = pValue[n];
      if('&' == value){
         pXml->Append(TC("&amp;"));
      }else if('"' == value){
         pXml->Append(TC("&quot;"));
      }else if('<' == value){
         pXml->Append(TC("&lt;"));
      }else if('>' == value){
         pXml->Append(TC("&gt;"));
      }else if('\r' == value){
         continue;
      }else if('\n' == value){
         pXml->Append(TC("\\n"));
      }else{
         pXml->Append(value);
      }
   }
   return ETrue;
}

MO_NAMESPACE_END
