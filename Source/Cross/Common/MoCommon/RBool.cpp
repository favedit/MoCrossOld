#include "MoCmType.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>判断字符内容是否为真值。</T>
//
// @param value 字符
// @return 是否为真值
//============================================================
TBool RBool::IsTrue(TChar value){
   return Parse(value);
}

//============================================================
// <T>判断字符串内容是否为真值。</T>
//
// @param pValue 字符串
// @param defaultValue 默认布尔值
// @return 是否为真值
//============================================================
TBool RBool::IsTrue(TCharC* pValue, TBool defaultValue){
   return Parse(pValue, defaultValue);
}

//============================================================
// <T>解析字符为布尔值。</T>
//
// @param value 字符
// @return 布尔值
//============================================================
TBool RBool::Parse(TChar value){
   switch(value){
      case '1':
      case 'Y':
      case 'y':
      case 'T':
      case 't':
      case 'O':
      case 'o':{
         return ETrue;
      }
      default:{
         break;
      }
   }
   return EFalse;
}

//============================================================
// <T>解析字符串为布尔值。</T>
//
// @param pValue 字符串
// @param defaultValue 默认布尔值
// @return 布尔值
//============================================================
TBool RBool::Parse(TCharC* pValue, TBool defaultValue){
   if(NULL != pValue){
      TInt length = RChars::Length(pValue);
      if(length > 0){
         switch(length){
            case 1:{
               // 长度为1的情况
               TChar value = pValue[0];
               switch(value){
                  case '1':
                  case 'Y':
                  case 'y':
                  case 'T':
                  case 't':
                  case 'O':
                  case 'o':{
                     return ETrue;
                  }
                  default:{
                     break;
                  }
               }
               break;
            }
            case 2:{
               // 长度为2的情况
               if(RChars::Equals(pValue, TC("OK")) || RChars::Equals(pValue, TC("Ok")) || RChars::Equals(pValue, TC("ok"))){
                  return ETrue;
               }
               break;
            }
            case 3:{
               // 长度为3的情况
               if(RChars::Equals(pValue, TC("YES")) || RChars::Equals(pValue, TC("Yes")) || RChars::Equals(pValue, TC("yes"))){
                  return ETrue;
               }
               break;
            }
            case 4:{
               // 长度为4的情况
               if(RChars::Equals(pValue, TC("TRUE")) || RChars::Equals(pValue, TC("True")) || RChars::Equals(pValue, TC("true"))){
                  return ETrue;
               }
               break;
            }
            default:{
               break;
            }
         }
      }
   }
   return defaultValue;
}

//============================================================
// <T>解析字符串为布尔值。</T>
//
// @param pValue 字符串
// @param defaultValue 默认布尔值
// @return 布尔值
//============================================================
TBool RBool::ParseNvl(TCharC* pValue, TBool defaultValue){
   if(NULL != pValue){
      return Parse(pValue, defaultValue);
   }
   return defaultValue;
}

//============================================================
// <T>将布尔值变换为字符。</T>
//
// @param value 布尔值
// @return 字符
//============================================================
TChar RBool::ToChar(TBool value){
   return value ? MO_TYPE_BOOL_TRUE_CHAR : MO_TYPE_BOOL_FALSE_CHAR;
}

//============================================================
// <T>将布尔值变换为字符串。</T>
//
// @param value 布尔值
// @return 字符串
//============================================================
TCharC* RBool::ToString(TBool value){
   return value ? MO_TYPE_BOOL_TRUE_STRING : MO_TYPE_BOOL_FALSE_STRING;
}

//============================================================
// <T>将布尔值变换为显示字符串。</T>
//
// @param value 布尔值
// @return 显示字符串
//============================================================
TCharC* RBool::ToDisplay(TBool value){
   return value ? MO_TYPE_BOOL_TRUE_DISPLAY : MO_TYPE_BOOL_FALSE_DISPLAY;
}

MO_NAMESPACE_END
