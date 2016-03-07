#include "MoCmXml.h"
#include "MoCmFormat.h"
#include "MoCmLanguage.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FXmlNode, FInstance);

//============================================================
// <T>创建配置节点实例。</T>
//============================================================
FXmlNode::FXmlNode(){
   MO_CLEAR(_pDocument);
   _nodeType = EXmlNodeType_Element;
   MO_CLEAR(_pAttributes);
   MO_CLEAR(_pNodes);
}

//============================================================
// <T>创建配置节点实例。</T>
//============================================================
FXmlNode::FXmlNode(FXmlDocument* pDocument){
   _pDocument = pDocument;
   _nodeType = EXmlNodeType_Element;
   MO_CLEAR(_pAttributes);
   MO_CLEAR(_pNodes);
}

//============================================================
// <T>创建配置节点实例。</T>
//
// @param nodeType 节点类型
//============================================================
FXmlNode::FXmlNode(FXmlDocument* pDocument, EXmlNodeType nodeType){
   _pDocument = pDocument;
   _nodeType = nodeType;
   MO_CLEAR(_pAttributes);
   MO_CLEAR(_pNodes);
}

//============================================================
// <T>释放配置节点实例。</T>
//============================================================
FXmlNode::~FXmlNode(){
   // 释放属性表
   if(NULL != _pAttributes){
      MO_DELETE(_pAttributes);
   }
   // 释放节点表
   if(NULL != _pNodes){
      MO_DELETE(_pNodes);
   }
}

//============================================================
// <T>建立XML文本内容。</T>
//
// @param xml 文本内容
// @param pNode 节点对象
// @param level 层次
//============================================================
void FXmlNode::InnerBuildXml(MString* pXml, FXmlNode* pNode, TInt level){
   pXml->AppendRepeat(TC("   "), level);
   if(EXmlNodeType_Comment == pNode->NodeType()){
      // 处理注释节点
      pXml->Append(TC("<--"));
      pXml->Append(pNode->Text());
      pXml->Append(TC("-->\n"));
   }else{
      // 处理数据节点
      pXml->Append('<');
      pXml->Append(pNode->Name());
      if(pNode->HasAttribute()){
         pXml->Append(' ');
         TAttributesIteratorC iterator = pNode->Attributes()->IteratorC();
         while(iterator.Next()){
            pXml->Append(iterator.Name());
            pXml->Append(TC("=\""));
            RXml::MakeNodeAttribute(pXml, iterator.Value());
            pXml->Append('"');
            if(iterator.HasNext()){
               pXml->Append(' ');
            }
         }
      }
      if(pNode->HasNode()){
         pXml->Append(TC(">\n"));
         // 处理所有子节点
         TListIteratorC<FXmlNode*> iterator = pNode->Nodes()->IteratorC();
         while(iterator.Next()){
            FXmlNode* pChildNode = *iterator;
            InnerBuildXml(pXml, pChildNode, level + 1);
         }
         // 关闭节点
         pXml->AppendRepeat(TC("   "), level);
         pXml->Append(TC("</"));
         pXml->Append(pNode->Name());
         pXml->Append(TC(">\n"));
      }else if(pNode->HasText()){
         pXml->Append(TC(">"));
         RXml::MakeNodeText(pXml, pNode->Text());
         pXml->Append(TC("</"));
         pXml->Append(pNode->Name());
         pXml->Append(TC(">\n"));
      }else{
         pXml->Append(TC("/>\n"));
      }
   }
}

//============================================================
// <T>获得文档对象。</T>
//============================================================
FXmlDocument* FXmlNode::Document(){
   return _pDocument;
}

//============================================================
// <T>获得节点类型。</T>
//
// @return 节点类型
//============================================================
EXmlNodeType FXmlNode::NodeType(){
   return _nodeType;
}

//============================================================
// <T>判断节点的名称是否为指定名称。</T>
//
// @param pName 名称
// @return
//    <L value='ETrue'>相等</L>
//    <L value='EFalse'>不等</L>
//============================================================
TBool FXmlNode::IsName(TCharC* pName){
   return _name.EqualsIgnoreCase(pName);
}

//============================================================
// <T>获得节点名称。</T>
//
// @return 节点名称
//============================================================
TCharC* FXmlNode::Name(){
   return _name.MemoryC();
}

//============================================================
// <T>设置节点名称。</T>
//
// @param name 节点名称
//============================================================
void FXmlNode::SetName(TCharC* pName){
   _name.Assign(pName);
}

//============================================================
// <T>判断节点的属性名称的内容是否为指定内容。</T>
//
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return
//    <L value='ETrue'>相等</L>
//    <L value='EFalse'>不等</L>
//============================================================
TBool FXmlNode::IsAttribute(TCharC* pAttrName, TCharC* pAttrValue){
   if(NULL == _pAttributes){
      return EFalse;
   }
   TCharC* pValue = _pAttributes->Find(pAttrName);
   TBool result = EFalse;
   if(NULL != pValue){
      if(RString::Equals(pAttrValue, pValue)){
         result = ETrue;
      }
   }
   return result;
}

//============================================================
// <T>获得节点内容。</T>
//
// @return 节点内容
//============================================================
TBool FXmlNode::HasText(){
   return !_text.IsEmpty();
}

//============================================================
// <T>获得节点内容。</T>
//
// @return 节点内容
//============================================================
TCharC* FXmlNode::Text(){
   return _text.MemoryC();
}

//============================================================
// <T>以布尔格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 布尔内容
//============================================================
TBool FXmlNode::TextAsBool(TBool nvl){
   return _text.IsEmpty() ? nvl : RBool::IsTrue((TCharC*)_text);
}

//============================================================
// <T>以整数格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 整数内容
//============================================================
TInt FXmlNode::TextAsInt(TInt nvl){
   return _text.IsEmpty() ? nvl : RInt::Parse((TCharC*)_text);
}

//============================================================
// <T>以整数格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 长整数内容
//============================================================
TInt32 FXmlNode::TextAsInt32(TInt32 nvl){
   return _text.IsEmpty() ? nvl : RInt32::Parse((TCharC*)_text);
}

//============================================================
// <T>以整数格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 长整数内容
//============================================================
TUint32 FXmlNode::TextAsUint32(TUint32 nvl){
   return _text.IsEmpty() ? nvl : RUint32::Parse((TCharC*)_text);
}

//============================================================
// <T>以长整数格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 长整数内容
//============================================================
TInt64 FXmlNode::TextAsInt64(TInt64 nvl){
   return _text.IsEmpty() ? nvl : RInt64::Parse((TCharC*)_text);
}

//============================================================
// <T>以长整数格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 长整数内容
//============================================================
TUint64 FXmlNode::TextAsUint64(TUint64 nvl){
   return _text.IsEmpty() ? nvl : RUint64::Parse((TCharC*)_text);
}

//============================================================
// <T>以浮点数格式，获得节点内容，如果节点内容为空，则返回默认值。</T>
//
// @param nvl 节点内容为空时的取值
// @return 浮点数内容
//============================================================
TFloat FXmlNode::TextAsFloat(TFloat nvl){
   return _text.IsEmpty() ? nvl : RFloat::Parse((TCharC*)_text);
}

//============================================================
// <T>获得节点内容。</T>
//
// @param pText 数据指针
// @param length 数据长度
//============================================================
void FXmlNode::GetText(TChar* pText, TInt length){
   MO_ASSERT(pText);
   TInt copyed = MO_LIB_MIN(_text.Length(), length - 1);
   memcpy(pText, (TCharC*)_text, copyed);
   pText[copyed] = 0;
}

//============================================================
// <T>设置节点内容。</T>
//
// @param text 节点内容
//============================================================
void FXmlNode::SetText(TCharC* pText){
   _text.Assign(pText);
}

//============================================================
// <T>用布尔值设置节点内容。</T>
//
// @param text 节点内容
//============================================================
void FXmlNode::SetTextBool(TBool text){
   _text.Assign(RBool::ToString(text));
}

//============================================================
// <T>用整数设置节点内容。</T>
//
// @param text 节点内容
//============================================================
void FXmlNode::SetTextInt(TInt text){
   TChar pBuffer[MO_INT_MAXLENGTH];
   _text.Assign(RInt::ToString(text, pBuffer, MO_INT_MAXLENGTH));
}

//============================================================
// <T>用浮点数设置节点内容。</T>
//
// @param text 节点内容
//============================================================
void FXmlNode::SetTextFloat(TFloat text){
   TChar buffer[MO_FLOAT_MAXLENGTH];
   _text.Assign(RFloat::ToString(text, buffer, MO_FLOAT_MAXLENGTH));
}

//============================================================
// <T>追加文本内容。</T>
//
// @param pText 文本内容
//============================================================
void FXmlNode::TextAppend(TCharC* pText){
   _text.Append(pText);
}

//============================================================
// <T>判断是否存在属性。</T>
//
// @return
//    <L value='ETrue'>存在</L>
//    <L value='EFalse'>不存在</L>
//============================================================
TBool FXmlNode::HasAttribute(){
   return (NULL != _pAttributes) ? !_pAttributes->IsEmpty() : EFalse;
}

//============================================================
// <T>获得属性表。</T>
//
// @return 属性表
//============================================================
FAttributes* FXmlNode::Attributes(){
   if(NULL == _pAttributes){
      _pAttributes = MO_CREATE(FAttributes);
   }
   return _pAttributes;
}

//============================================================
// <T>是否含有属性。</T>
//
// @param pName 属性名称
// @return
//    <L value='ETrue'>含有</L>
//    <L value='EFalse'>不含有</L>
//============================================================
TBool FXmlNode::Contains(TCharC* pName){
   if(NULL == _pAttributes){
      return EFalse;
   }
   return _pAttributes->Contains(pName);
}

//============================================================
// <T>根据属性名称，获得属性内容。</T>
//
// @param pName 属性名称
// @return 属性内容
//============================================================
TCharC* FXmlNode::Get(TCharC* pName){
   if(NULL == _pAttributes){
      return NULL;
   }
   return _pAttributes->Get(pName);
}

//============================================================
// <T>根据属性名称，获得属性内容，如果属性不存在，则返回默认值。</T>
//
// @param pName 属性名称
// @return 属性内容
//============================================================
TCharC* FXmlNode::Get(TCharC* pName, TCharC* pDefault){
   if(NULL == _pAttributes){
      return pDefault;
   }
   return _pAttributes->FindNvl(pName, pDefault);
}

//============================================================
// <T>以布尔格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 数字内容
//============================================================
TBool FXmlNode::GetAsBool(TCharC* pName, TBool nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RBool::IsTrue(pValue);
}

//============================================================
// <T>以数字格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 数字内容
//============================================================
TInt FXmlNode::GetAsInt(TCharC* pName, TInt nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RInt::Parse(pValue);
}

//============================================================
// <T>以数字格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 数字内容
//============================================================
TInt32 FXmlNode::GetAsInt32(TCharC* pName, TInt32 nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RInt32::Parse(pValue);
}

//============================================================
// <T>以数字格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 数字内容
//============================================================
TUint32 FXmlNode::GetAsUint32(TCharC* pName, TUint32 nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RUint32::Parse(pValue);
}

//============================================================
// <T>以数字格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 数字内容
//============================================================
TInt64 FXmlNode::GetAsInt64(TCharC* pName, TInt64 nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RInt64::Parse(pValue);
}

//============================================================
// <T>以数字格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 数字内容
//============================================================
TUint64 FXmlNode::GetAsUint64(TCharC* pName, TUint64 nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RUint64::Parse(pValue);
}

//============================================================
// <T>以浮点数格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 浮点数
//============================================================
TFloat FXmlNode::GetAsFloat(TCharC* pName, TFloat nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return (NULL == pValue) ? nvl : RFloat::Parse(pValue);
}

//============================================================
// <T>以时间格式，获得节点属性，如果节点属性为空，则返回默认值。</T>
//
// @param nvl 节点属性为空时的取值
// @return 浮点数
//============================================================
TDateTime FXmlNode::GetAsDateTime(TCharC* pName, TDateTime nvl){
   if(NULL == _pAttributes){
      return nvl;
   }
   TCharC* pValue = _pAttributes->Find(pName);
   return RDateTime::ParseNvl(pValue, TC("YYYYMMDDHH24MISS"));
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
//============================================================
TResult FXmlNode::Set(TCharC* pName, TCharC* pValue){
   MO_CHECK(pName, return ENull);
   Attributes()->Set(pName, pValue);
   return ESuccess;
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetBool(TCharC* pName, TBool value){
   Attributes()->Set(pName, RBool::ToString(value));
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetInt(TCharC* pName, TInt value){
   TChar pBuffer[MO_INT_MAXLENGTH];
   Attributes()->Set(pName, RInt::ToString(value, pBuffer, MO_INT_MAXLENGTH));
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetFloat(TCharC* pName, TFloat value){
   TChar buffer[MO_FLOAT_MAXLENGTH];
   Attributes()->Set(pName, RFloat::ToString(value, buffer, MO_FLOAT_MAXLENGTH));
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetDateTime(TCharC* pName, TDateTime value){
   TFsDateTime dateTime = value;
   Attributes()->Set(pName, dateTime.Format(TC("YYYYMMDDHH24MISS")));
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param pValue 属性内容
//============================================================
TResult FXmlNode::SetNvl(TCharC* pName, TCharC* pValue){
   if(pValue != NULL){
      TInt length = RString::Length(pValue);
      if(length > 0){
         return Set(pName, pValue);
      }
   }
   return EFailure;
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetBoolNvl(TCharC* pName, TBool value){
   if(value){
      SetInt(pName, value);
   }
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetIntNvl(TCharC* pName, TInt value){
   if(value != 0.0f){
      SetInt(pName, value);
   }
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetFloatNvl(TCharC* pName, TFloat value){
   if(value != 0.0f){
      SetFloat(pName, value);
   }
}

//============================================================
// <T>根据属性名称，设置属性内容。</T>
//
// @param pName 属性名称
// @param value 属性内容
//============================================================
void FXmlNode::SetDateTimeNvl(TCharC* pName, TDateTime value){
   if(value != 0){
      SetDateTime(pName, value);
   }
}

//============================================================
// <T>判断是否存在节点。</T>
//
// @return
//    <L value='ETrue'>存在</L>
//    <L value='EFalse'>不存在</L>
//============================================================
TBool FXmlNode::HasNode(){
   return (NULL != _pNodes) ? !_pNodes->IsEmpty() : EFalse;
}

//============================================================
// <T>是否含有指定名称的子节点。</T>
//
// @return
//    <L value='ETrue'>含有</L>
//    <L value='EFalse'>不含有</L>
//============================================================
TBool FXmlNode::ContainsNode(TCharC* pName){
   return (NULL != FindNode(pName));
}

//============================================================
// <T>是否含有指定属性名称和属性内容的子节点。</T>
//
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return
//    <L value='ETrue'>含有</L>
//    <L value='EFalse'>不含有</L>
//============================================================
TBool FXmlNode::ContainsNode(TCharC* pAttrName, TCharC* pAttrValue){
   return (NULL != FindNode(pAttrName, pAttrValue));
}

//============================================================
// <T>是否含有指定节点名称，并属性名称和属性内容符合的子节点。</T>
//
// @param pName 属性名称
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return
//    <L value='ETrue'>含有</L>
//    <L value='EFalse'>不含有</L>
//============================================================
TBool FXmlNode::ContainsNode(TCharC* pName, TCharC* pAttrName, TCharC* pAttrValue){
   return (NULL != FindNode(pName, pAttrName, pAttrValue));
}

//============================================================
// <T>获得子节点集合。</T>
//
// @return 子节点集合
//============================================================
FXmlNodes* FXmlNode::Nodes(){
   if(NULL == _pNodes){
      _pNodes = MO_CREATE(FXmlNodes);
   }
   return _pNodes;
}

//============================================================
// <T>获得子节点的迭代器。</T>
//
// @return 子节点迭代器
//============================================================
TXmlNodeIteratorC FXmlNode::NodeIteratorC(){
   if(NULL == _pNodes){
      return TXmlNodeIteratorC();
   }
   return _pNodes->IteratorC();
}

//============================================================
// <T>获得指定索引的子节点。</T>
//
// @param index 索引位置
// @return 子节点
//============================================================
FXmlNode* FXmlNode::Node(TInt index){
   return HasNode() ? _pNodes->GetByIndex(index) : NULL;
}

//============================================================
// <T>获得指定名称的子节点。</T>
//
// @param pName 子节点名称
// @return 子节点
//============================================================
FXmlNode* FXmlNode::FindNode(TCharC* pName){
   if(NULL != _pNodes){
      TXmlNodeIteratorC iterator = _pNodes->IteratorC();
      while(iterator.Next()){
         if(iterator->IsName(pName)){
            return iterator.Get();
         }
      }
   }
   return NULL;
}

//============================================================
// <T>获得指定属性名称和属性内容的子节点。</T>
//
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return 子节点
//============================================================
FXmlNode* FXmlNode::FindNode(TCharC* pAttrName, TCharC* pAttrValue){
   if(NULL != _pNodes){
      TXmlNodeIteratorC iterator = _pNodes->IteratorC();
      while(iterator.Next()){
         if(iterator->IsAttribute(pAttrName, pAttrValue)){
            return iterator.Get();
         }
      }
   }
   return NULL;
}

//============================================================
// <T>获得指定节点名称，并属性名称和属性内容符合的子节点。</T>
//
// @param pName 节点名称
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return 子节点
//============================================================
FXmlNode* FXmlNode::FindNode(TCharC* pName, TCharC* pAttrName, TCharC* pAttrValue){
   if(NULL != _pNodes){
      TXmlNodeIteratorC iterator = _pNodes->IteratorC();
      while(iterator.Next()){
         if(iterator->IsName(pName)){
            if(iterator->IsAttribute(pAttrName, pAttrValue)){
               return iterator.Get();
            }
         }
      }
   }
   return NULL;
}

//============================================================
// <T>查找指定名称的子节点，获得子节点内容。</T>
//
// @param pName 子节点名称
// @return 子节点内容
//============================================================
TCharC* FXmlNode::FindText(TCharC* pName){
   FXmlNode* pNode = FindNode(pName);
   return (NULL == pNode) ? NULL : pNode->Text();
}

//============================================================
// <T>获得指定属性名称和属性内容的子节点内容。</T>
//
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return 子节点内容
//============================================================
TCharC* FXmlNode::FindText(TCharC* pAttrName, TCharC* pAttrValue){
   FXmlNode* pNode = FindNode(pAttrName, pAttrValue);
   return (NULL == pNode) ? NULL : pNode->Text();
}

//============================================================
// <T>获得指定节点名称，并属性名称和属性内容符合的子节点内容。</T>
//
// @param pName 子节点名称
// @param pAttrName 属性名称
// @param pAttrValue 属性内容
// @return 子节点内容
//============================================================
TCharC* FXmlNode::FindText(TCharC* pName, TCharC* pAttrName, TCharC* pAttrValue){
   FXmlNode* pNode = FindNode(pName, pAttrName, pAttrValue);
   return (NULL == pNode) ? NULL : pNode->Text();
}

//============================================================
// <T>创建子节点，并将子节点加入当前节点内。</T>
//
// @return 子节点
//============================================================
FXmlNode* FXmlNode::CreateNode(){
   FXmlNode* pNode = _pDocument->CreateNode();
   Nodes()->Push(pNode);
   return pNode;
}

//============================================================
// <T>创建子节点，并将子节点加入当前节点内。</T>
//
// @param pName 节点名称
// @return 子节点
//============================================================
FXmlNode* FXmlNode::CreateNode(TCharC* pName){
   FXmlNode* pNode = _pDocument->CreateNode();
   pNode->SetName(pName);
   Nodes()->Push(pNode);
   return pNode;
}

//============================================================
// <T>创建子节点，并将子节点加入当前节点内。</T>
// <P>创建时，初始化一个属性。</P>
//
// @param pName 节点名称
// @param pAttributeName 属性名称
// @param pAttributeValue 属性内容
// @return 子节点
//============================================================
FXmlNode* FXmlNode::CreateNode(TCharC* pName, TCharC* pAttributeName, TCharC* pAttributeValue){
   FXmlNode* pNode = CreateNode(pName);
   pNode->Set(pAttributeName, pAttributeValue);
   return pNode;
}

//============================================================
// <T>将子节点加入当前节点内。</T>
//
// @param pNode 子节点
//============================================================
TResult FXmlNode::Push(FXmlNode* pNode){
   Nodes()->Push(pNode);
   return ESuccess;
}

//============================================================
// <T>序列化内容到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TBool FXmlNode::Serialize(IDataOutput* pOutput){
   return ETrue;
}

//============================================================
// <T>从输入流中反序列化内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FXmlNode::Unserialize(IDataInput* pInput){
   // 读取名称
   _name = pInput->ReadString();
   // 读取内容
   _text = pInput->ReadString();
   // 读取属性集合
   TInt attributeCount = pInput->ReadInt16();
   if(attributeCount > 0){
      FAttributes* pAttributes = Attributes();
      for(TInt n = 0; n < attributeCount; n++){
         // 读取属性内容
         TString attributeName = pInput->ReadString();
         TString attributeValue = pInput->ReadString();
         // 设置属性内容
         pAttributes->Set(attributeName, attributeValue);
      }
   }
   // 读取节点集合
   TInt nodeCount = pInput->ReadInt16();
   if(nodeCount > 0){
      for(TInt n = 0; n < nodeCount; n++){
         FXmlNode* pNode = CreateNode();
         pNode->Unserialize(pInput);
      }
   }
   return ETrue;
}

//============================================================
// <T>建立XML内容。</T>
//
// @param pXml XML内容
//============================================================
void FXmlNode::BuildXml(MString* pXml){
   InnerBuildXml(pXml, this, 0);
}

//============================================================
// <T>获得配置节点的XML内容。</T>
//
// @return XML内容
//============================================================
TString FXmlNode::Xml(){
   TStringBuffer xml;
   InnerBuildXml(&xml, this, 0);
   return xml;
}

MO_NAMESPACE_END
