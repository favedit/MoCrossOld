#ifndef __MO_CM_XML_H__
#define __MO_CM_XML_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_ATTRIBUTES_H__
#include "MoCmAttributes.h"
#endif // __MO_CM_ATTRIBUTES_H__

#ifndef __MO_CM_DICTIONARY_H__
#include "MoCmDictionary.h"
#endif // __MO_CM_DICTIONARY_H__

#ifndef __MO_CM_CLASS_H__
#include "MoCmClass.h"
#endif // __MO_CM_CLASS_H__

#ifndef __MO_CM_STREAM_H__
#include "MoCmStream.h"
#endif // __MO_CM_STREAM_H__

#ifndef __MO_CM_CONFIG_H__
#include "MoCmConfig.h"
#endif // __MO_CM_CONFIG_H__

#define MO_EACH_NODE(I, X) TXmlNodeIteratorC I = X->NodeIteratorC();while(I.next())

MO_NAMESPACE_BEGIN

//============================================================
class FXmlNode;
class FXmlNodes;
class FXmlDocument;

//============================================================
// <T>XML节点的类型。</T>
//============================================================
enum EXmlNodeType{
   EXmlNodeType_Element,
   EXmlNodeType_Text,
   EXmlNodeType_Data,
   EXmlNodeType_Comment
};

//============================================================
// <T>配置构建器。</T>
//============================================================
class MO_CM_DECLARE FXmlBuilder : public FConfigBuilder
{
   MO_CLASS_DECLARE_INHERITS(FXmlBuilder, FConfigBuilder);
public:
   FXmlBuilder();
   MO_ABSTRACT ~FXmlBuilder();
public:
   MO_OVERRIDE TResult SetName(FInstance* pInstance, TCharC* pName);
   MO_OVERRIDE TResult SetText(FInstance* pInstance, TCharC* pText);
public:
   MO_OVERRIDE TResult Set(FInstance* pInstance, TCharC* pName, TCharC* pValue);
   MO_OVERRIDE TResult Push(FInstance* pInstance, FInstance* pChildInstance);
};

//============================================================
// <T>XML节点的迭代器。</T>
//============================================================
class MO_CM_DECLARE TXmlNodeIteratorC : public TListIteratorC<FXmlNode*>{
public:
   typedef SListEntry<FXmlNode*> SEntry;
public:
   TXmlNodeIteratorC();
   TXmlNodeIteratorC(const TXmlNodeIteratorC& iterator);
   TXmlNodeIteratorC(SEntry* pEntry);
public:
   TBool Next();
   TBool Next(TCharC* pName);
};

//============================================================
// <T>XML节点信息类。</T>
//============================================================
class MO_CM_DECLARE FXmlNode : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FXmlNode, FInstance);
private:
   FXmlDocument* _pDocument;
   EXmlNodeType _nodeType;
   TString _name;
   TString _text;
   FAttributes* _pAttributes;
   FXmlNodes* _pNodes;
public:
   FXmlNode();
   FXmlNode(FXmlDocument* pDocument);
   FXmlNode(FXmlDocument* pDocument, EXmlNodeType nodeType);
   MO_ABSTRACT ~FXmlNode();
protected:
   void InnerBuildXml(MString* pXml, FXmlNode* pNode, TInt level);
public:
   FXmlDocument* Document();
   //------------------------------------------------------------
   EXmlNodeType NodeType();
   TBool IsName(TCharC* pName);
   TCharC* Name();
   void SetName(TCharC* pName);
   TBool IsAttribute(TCharC* pAttrName, TCharC* pAttrValue);
   TBool HasText();
   TCharC* Text();
   TBool TextAsBool(TBool nvl=EFalse);
   TInt TextAsInt(TInt nvl=0);
   TInt32 TextAsInt32(TInt32 nvl=0);
   TUint32 TextAsUint32(TUint32 nvl=0);
   TInt64 TextAsInt64(TInt64 nvl=0);
   TUint64 TextAsUint64(TUint64 nvl=0);
   TFloat TextAsFloat(TFloat nvl=0);
   void GetText(TChar* pText, TInt length);
   void SetText(TCharC* pText);
   void SetTextBool(TBool text);
   void SetTextInt(TInt text);
   void SetTextFloat(TFloat text);
   void TextAppend(TCharC* pText);
   //------------------------------------------------------------
   TBool HasAttribute();
   FAttributes* Attributes();
   TBool Contains(TCharC* pName);
   TCharC* Get(TCharC* pName);
   TCharC* Get(TCharC* pName, TCharC* pDefault);
   TBool GetAsBool(TCharC* pName, TBool nvl = EFalse);
   TInt GetAsInt(TCharC* pName, TInt nvl=0);
   TInt32 GetAsInt32(TCharC* pName, TInt32 nvl=0);
   TUint32 GetAsUint32(TCharC* pName, TUint32 nvl=0);
   TInt64 GetAsInt64(TCharC* pName, TInt64 nvl=0);
   TUint64 GetAsUint64(TCharC* pName, TUint64 nvl=0);
   TFloat GetAsFloat(TCharC* pName, TFloat nvl = 0);
   TDateTime GetAsDateTime(TCharC* pName, TDateTime nvl = 0);
   MO_OVERRIDE TResult Set(TCharC* pName, TCharC* pValue);
   void SetBool(TCharC* pName, TBool value);
   void SetInt(TCharC* pName, TInt value);
   void SetFloat(TCharC* pName, TFloat value);
   void SetDateTime(TCharC* pName, TDateTime value);
   TResult SetNvl(TCharC* pName, TCharC* pValue);
   void SetBoolNvl(TCharC* pName, TBool value);
   void SetIntNvl(TCharC* pName, TInt value);
   void SetFloatNvl(TCharC* pName, TFloat value);
   void SetDateTimeNvl(TCharC* pName, TDateTime value);
   //------------------------------------------------------------
   TBool HasNode();
   TBool ContainsNode(TCharC* pName);
   TBool ContainsNode(TCharC* pAttrName, TCharC* pAttrValue);
   TBool ContainsNode(TCharC* pName, TCharC* pAttrName, TCharC* pAttrValue);
   FXmlNodes* Nodes();
   TXmlNodeIteratorC NodeIteratorC();
   FXmlNode* Node(TInt index);
   FXmlNode* FindNode(TCharC* pName);
   FXmlNode* FindNode(TCharC* pAttrName, TCharC* pAttrValue);
   FXmlNode* FindNode(TCharC* pName, TCharC* pAttrName, TCharC* pAttrValue);
   TCharC* FindText(TCharC* pName);
   TCharC* FindText(TCharC* pAttrName, TCharC* pAttrValue);
   TCharC* FindText(TCharC* pName, TCharC* pAttrName, TCharC* pAttrValue);
   //------------------------------------------------------------
   FXmlNode* CreateNode();
   FXmlNode* CreateNode(TCharC* pName);
   FXmlNode* CreateNode(TCharC* pName, TCharC* pAttributeName, TCharC* pAttributeValue);
   TResult Push(FXmlNode* pNode);
public:
   MO_ABSTRACT TBool Serialize(IDataOutput* pOutput);
   MO_ABSTRACT TBool Unserialize(IDataInput* pInput);
public:
   void BuildXml(MString* pXml);
   TString Xml();
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FArray<FXmlNode*> FXmlNodeArray;
typedef MO_CM_DECLARE FVector<FXmlNode*> FXmlNodeVector;
typedef MO_CM_DECLARE FObjects<FXmlNode*> FXmlNodeCollection;
typedef MO_CM_DECLARE FList<FXmlNode*> FXmlNodeList;
typedef MO_CM_DECLARE FSet<TInt, FXmlNode*> FXmlNodeSet;
typedef MO_CM_DECLARE FDictionary<FXmlNode*> FXmlNodeDictionary;

//============================================================
// 内存管理工具类
//============================================================
class MO_CM_DECLARE FXmlNodes : public FXmlNodeList{
public:
   TXmlNodeIteratorC IteratorC();
};

//============================================================
// 内存管理工具类
//============================================================
class MO_CM_DECLARE FXmlDocument : public FObject{
private:
   TFsFileName _fileName;
   TFsCode _encoding;
   FXmlNodes* _pNodes;
   FXmlNode* _pRoot;
   TFsCode _indent;
public:
   FXmlDocument();
   FXmlDocument(TCharC* pFileName);
   FXmlDocument(FXmlNode* pRootNode);
   MO_ABSTRACT ~FXmlDocument();
public:
   TCharC* FileName();
   TCharC* Encoding();
   void SetEncoding(TCharC* pEncoding);
   TBool HasNode();
   FXmlNode* Root();
public:
   FXmlNode* CreateNode(EXmlNodeType type = EXmlNodeType_Element);
public:
   MO_ABSTRACT TBool Serialize(IDataOutput* pOutput);
   MO_ABSTRACT TBool Unserialize(IDataInput* pInput);
public:
   MO_ABSTRACT TBool SerializeFile(TCharC* pFileName);
   MO_ABSTRACT TBool UnserializeFile(TCharC* pFileName);
   MO_ABSTRACT TBool LoadFile(TCharC* pFileName);
   MO_ABSTRACT TBool SaveFile8(TCharC* pFileName);
   MO_ABSTRACT TBool SaveFile(TCharC* pFileName);
public:
   void BuildXml(MString* pXml);
   TString Xml();
};
//------------------------------------------------------------
typedef MO_CM_DECLARE FList<FXmlDocument*> FXmlDocumentList;
typedef MO_CM_DECLARE FSet<TInt, FXmlDocument*> FXmlDocumentSet;
typedef MO_CM_DECLARE FDictionary<FXmlDocument*> FXmlDocumentDictionary;

//============================================================
// <T>配置解析器。</T>
//============================================================
class MO_CM_DECLARE FXmlParser : public FConfigParser
{
   MO_CLASS_DECLARE_INHERITS(FXmlParser, FConfigParser);
public:
   FXmlParser();
   MO_ABSTRACT ~FXmlParser();
public:
   TCharC* ScanHeader(TCharC* pSource);
public:
   FXmlNode* LoadNode(TCharC* pSource);
   FXmlNode* LoadNodeFile(TCharC* pFileName);
   FXmlDocument* LoadDocument(TCharC* pSource);
   FXmlDocument* LoadDocumentFile(TCharC* pFileName);
};

//============================================================
// <T>XML工具类。</T>
//============================================================
class MO_CM_DECLARE RXml{
public:
   static TBool MakeNodeAttribute(MString* pXml, TCharC* pValue);
   static TBool MakeNodeText(MString* pXml, TCharC* pValue);
};

MO_NAMESPACE_END

#endif // __MO_CM_XML_H__
