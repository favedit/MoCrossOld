#include "MoCmFormat.h"
#include "MoCmConfig.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FConfigParser, FInstance);

//============================================================
// <T>创建配置解析器。</T>
//============================================================
FConfigParser::FConfigParser(){
   _optionTextNode = EFalse;
   _pFactory = MO_CREATE(FClassFactory);
   MO_CLEAR(_pRootNode);
   _start.Assign(TC("<"));
   _tagFlags = _start.MemoryC();
   _startLength = _start.Length();
   _tagEnd.Assign(TC("</"));
   _dataStart = TC("<![CDATA[");
   _dataEnd = TC("]]>");
}

//============================================================
// <T>释放配置解析器。</T>
//============================================================
FConfigParser::~FConfigParser(){
   MO_DELETE(_pFactory);
}

//============================================================
// <T>根据标签名称创建标签。</T>
//
// @param pTagName 标签名称
// @return 节点对象
//============================================================
FInstance* FConfigParser::CreateNode(TCharC* pTagName){
   MO_ASSERT(pTagName);
   // 创建节点
   FInstance* pInstance = NULL;
   if(_pFactory->Contains(pTagName)){
      pInstance = _pFactory->Create(pTagName);
   }else{
      pInstance = _pFactory->Create(MO_CONFIG_ELEMENT_NODE);
   }
   _builder->SetName(pInstance, pTagName);
   // 设置根节点
   if(_pRootNode == NULL){
      _pRootNode = pInstance;
   }
   return pInstance;
}

//============================================================
// <T>构造模板标签。</T>
//============================================================
void FConfigParser::SetSpace(TCharC* pSpace){
   _space = pSpace;
   _start.AssignFormat(TC("<%s:"), (TCharC*)_space);
   _tagFlags = _start.MemoryC();
   _startLength = _start.Length();
   _tagEnd.AssignFormat(TC("</%s:"), (TCharC*)_space);
}

//============================================================
// <T>解析属性集合。</T>
//
// @param pNode 元素对象
// @param pSource 属性内容
//============================================================
TResult FConfigParser::ParseAttribute(FInstance* pNode, TCharC* pSource){
   MO_CHECK(pNode, return ENull);
   MO_CHECK(pSource, return ENull);
   // 检查长度
   TInt length = RChars::Length(pSource);
   if(length == 0){
      return ESuccess;
   }
   // 解析内容
   _attributeName.Clear();
   _attributeValue.Clear();
   _attributeNameBuffer.Clear();
   _attributeValueBuffer.Clear();
   TInt n = 0;
   while(ETrue){
      // 分解属性名称
      if(n >= length){
         break;
      }
      for(; n < length; n++){
         TChar loopChar = pSource[n];
         if(loopChar == '='){
            _attributeName = _attributeNameBuffer.MemoryC();
            _attributeNameBuffer.Clear();
            break;
         }else if(loopChar != ' '){
            _attributeNameBuffer.Append(loopChar);
         }
      }
      // 分解属性内容
      if(n + 1 >= length){
         break;
      }
      n++;
      TChar valueChar = pSource[n];
      if((valueChar == '\'') || (valueChar == '\"')){
         n++;
      }else{
         valueChar = ' ';
      }
      for(; n < length; n++){
         TChar loopChar = pSource[n];
         if(loopChar == valueChar){
            _attributeValue = _attributeValueBuffer.MemoryC();
            _attributeValueBuffer.Clear();
            if(valueChar != ' '){
               n++;
            }
            _builder->Set(pNode, _attributeName, _attributeValue);
            break;
         }else{
            _attributeValueBuffer.Append(loopChar);
         }
      }
   }
   return ESuccess;
}

//============================================================
// <T>解析代码。</T>
//
// @param pParentNode 父节点
// @param chars 解析字符流
// @param start 开始解析位置
// @param length 解析长度
// @param endTag 标签结束标志
// @return 解析位置
//============================================================
TInt FConfigParser::ParseSource(FInstance* pParentNode, TCharC* pSource, TInt start, TInt length, TCharC* pEndTag){
   // 创建缓冲
   TStringBuffer tagName;
   TStringBuffer tagContent;
   TStringBuffer temp;
   // 生成变量
   TBool findEndTag = (pEndTag != NULL);
   TCharC* endTagChars = NULL;
   TInt endTagLength = 0;
   if(findEndTag){
      endTagChars = pEndTag;
      endTagLength = RChars::Length(pEndTag);
   }
   // 解析内容
   TInt n = start;
   for(; n < length; n++){
      TChar loopChar = pSource[n];
      // 查找标签结束位置
      if(findEndTag){
         TInt endEqualsLength = 0;
         for(TInt i = 0; i < endTagLength; i++){
            if(pSource[n + i] == endTagChars[i]){
               endEqualsLength++;
               continue;
            }
            break;
         }
         if(endEqualsLength == endTagLength){
            // 计算结束位置
            n += endTagLength - 1;
            if(!temp.IsEmpty()){
               if(_optionTextNode && (pParentNode != NULL)){
                  FInstance* pTextNode = CreateNode(MO_CONFIG_ELEMENT_TEXT);
                  _builder->SetText(pTextNode, temp);
                  _builder->Push(pParentNode, pTextNode);
               }
               temp.Clear();
            }
            return n;
         }
      }
      //............................................................
      // 忽略注释部分 <!-- -->
      if(loopChar == '<' && pSource[n + 1] == '!' && pSource[n + 2] == '-' && pSource[n + 3] == '-'){
         for(n += 4; n < length; n++){
            if(pSource[n] == '-' && pSource[n + 1] == '-' && pSource[n + 2] == '>'){
               n += 3;
               loopChar = pSource[n];
               break;
            }
         }
      }
      //............................................................
      // 查找标签开始位置
      TInt equalsLength = 0;
      for(TInt i = 0; i < _startLength; i++){
         if(pSource[n + i] == _tagFlags[i]){
            equalsLength++;
            continue;
         }
         break;
      }
      if(equalsLength == _startLength){
         // 保存将前的内容
         if(!temp.IsEmpty()){
            if(_optionTextNode && (pParentNode != NULL)){
               FInstance* pTextNode = CreateNode(MO_CONFIG_ELEMENT_TEXT);
               _builder->SetText(pTextNode, temp);
               _builder->Push(pParentNode, pTextNode);
            }
            temp.Clear();
         }
         // 分解标签
         n += _startLength;
         //............................................................
         TBool bTagClose = ETrue; // 当前标签是否结束
         TBool bTagNameFind = EFalse; // 当前标签是否结束
         // 查找标签名称和标签内容
         tagName.Clear();
         tagContent.Clear();
         while(ETrue){
            loopChar = pSource[n];
            if(loopChar == ' '){
               bTagNameFind = ETrue;
            }
            if(loopChar == '>'){
               bTagNameFind = ETrue;
               bTagClose = EFalse;
               break;
            }
            if((loopChar == '/') && (pSource[n + 1] == '>')){
               bTagNameFind = ETrue;
               bTagClose = ETrue;
               n++;
               break;
            }
            if(!bTagNameFind){
               tagName.Append(loopChar);
            }else{
               tagContent.Append(loopChar);
            }
            n++;
         }
         //............................................................
         // 测试数据节点
         TBool dataSegment = EFalse;
         TCharC* pData = &pSource[n + 1];
         TInt dataBegin = -1;
         TInt dataEnd = -1;
         if(RTypes<TChar>::Equals(pData, (TCharC*)_dataStart, _dataStart.Length())){
            dataBegin = n + 1;
            dataEnd = RChars::Find(pData, length - n, (TCharC*)_dataEnd, _dataEnd.Length());
            if(dataEnd == ENotFound){
               MO_FATAL(TC("Data segment is not end. (tag_name=%s)"), (TCharC*)tagName);
            }
            dataSegment = ETrue;
         }
         //............................................................
         // 标签关闭处理
         FInstance* pChildNode = CreateNode(tagName);
         if(!tagContent.IsEmpty()){
            ParseAttribute(pChildNode, tagContent);
         }
         if(pParentNode != NULL){
            _builder->Push(pParentNode, pChildNode);
         }
         if(dataSegment){
            TString data = TStringPtrC(pData + _dataStart.Length() , dataEnd - _dataStart.Length() - _dataEnd.Length());
            _builder->SetText(pChildNode, data);
            bTagClose = ETrue;
            TFsName tagEnd;
            tagEnd.AppendFormat(TC("%s%s>"), (TCharC*)_tagEnd, (TCharC*)tagName);
            TInt findTagEnd = RChars::Find(pData, length - n, (TCharC*)tagEnd, tagEnd.Length());
            if(findTagEnd == ENotFound){
               MO_FATAL(TC("Tag end is not found. (tag_name=%s)"), (TCharC*)tagName);
            }
            n += findTagEnd + tagEnd.Length();
         }
         if(!bTagClose){
            TFsName tagEnd;
            tagEnd.AppendFormat(TC("%s%s>"), (TCharC*)_tagEnd, (TCharC*)tagName);
            n = ParseSource(pChildNode, pSource, n + 1, length, tagEnd);
         }
         // 标签设置处理
         _builder->Setup(pChildNode);
      }else{
         temp.Append(loopChar);
      }
   }
   // 追加尾部字符串
   if(!temp.IsEmpty()){
      if(pParentNode != NULL){
         FInstance* pTextNode = CreateNode(MO_CONFIG_ELEMENT_TEXT);
         _builder->SetText(pTextNode, temp);
         _builder->Push(pParentNode, pTextNode);
      }
      temp.Clear();
   }
   return n;
}

//============================================================
// <T>解析配置文本内容。</T>
//
// @param pSource 来源内容
// @return 实例
//============================================================
FInstance* FConfigParser::Parse(TCharC* pSource){
   TInt length = RChars::Length(pSource);
   _pRootNode = NULL;
   ParseSource(NULL, pSource, 0, length, NULL);
   return _pRootNode;
}

//============================================================
// <T>解析配置文本内容。</T>
//
// @param pInstance 实例
// @param pSource 来源内容
// @return 处理结果
//============================================================
TResult FConfigParser::Parse(FInstance* pInstance, TCharC* pSource){
   TInt length = RChars::Length(pSource);
   _pRootNode = pInstance;
   ParseSource(pInstance, pSource, 0, length, NULL);
   return ESuccess;
}

MO_NAMESPACE_END
