#include <fcntl.h>
#ifdef _MO_WINDOWS
#import <msxml6.dll> named_guids
#endif // _MO_SYS_WINDOWS
#ifdef _MO_LINUX
#include <libxml/parser.h>
#include <libxml/xmlsave.h>
#endif // _MO_LINUX
#include "MoCmXml.h"
#include "MoCmSystem.h"

#define MO_DEF_ROOT_NAME TC("Configuration")
#define MO_DEF_NODE_NAME TC("Node")

#define MO_CRT_BSTR_TO_STRING(V) _com_util::ConvertBSTRToString(V)
#define MO_CRT_STRING_TO_BSTR(V) _com_util::ConvertStringToBSTR(V)

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建配置文件实例。</T>
//============================================================
FXmlDocument::FXmlDocument(){
   _encoding = TC("UTF-8");
   _indent = TC("   ");
   _pNodes = MO_CREATE(FXmlNodes);
   MO_CLEAR(_pRoot);
}

//============================================================
// <T>创建配置文件实例，加载配置文件。</T>
//
// @param fileName 文件名称
//============================================================
FXmlDocument::FXmlDocument(TCharC* pFileName){
   _encoding = TC("UTF-8");
   _indent = TC("   ");
   _pNodes = MO_CREATE(FXmlNodes);
   MO_CLEAR(_pRoot);
   LoadFile(pFileName);
}

//============================================================
// <T>创建配置文件实例，加载配置文件。</T>
//
// @param fileName 文件名称
//============================================================
FXmlDocument::FXmlDocument(FXmlNode* pRootNode){
   _encoding = TC("UTF-8");
   _indent = TC("   ");
   _pNodes = MO_CREATE(FXmlNodes);
   _pRoot = pRootNode;
}

//============================================================
// <T>释放当前实例。</T>
//
// @param fileName 文件名称
//============================================================
FXmlDocument::~FXmlDocument(){
   TListIteratorC<FXmlNode*> iterator = _pNodes->IteratorC();
   while(iterator.Next()){
      FXmlNode* pNode = *iterator;
      MO_DELETE(pNode);
   }
   MO_DELETE(_pNodes);
}

//============================================================
// <T>获得一个文件名称。</T>
//
// @return 文件名称
//============================================================
TCharC* FXmlDocument::FileName(){
   return _fileName.MemoryC();
}

//============================================================
// <T>获得文字编码。</T>
//
// @return 文字编码
//============================================================
TCharC* FXmlDocument::Encoding(){
   return _encoding.MemoryC();
}

//============================================================
// <T>设置文字编码。</T>
//
// @param pEncoding 文字编码
//============================================================
void FXmlDocument::SetEncoding(TCharC* pEncoding){
   _encoding.Assign(pEncoding);
}

//============================================================
// <T>判断是否存在节点。</T>
//
// @return 是否存在节点
//============================================================
TBool FXmlDocument::HasNode(){
   return NULL != _pRoot;
}

//============================================================
// <T>获得根节点。</T>
//
// @return 配置节点
//============================================================
FXmlNode* FXmlDocument::Root(){
   if(NULL == _pRoot){
      // 创建根节点
      _pRoot = CreateNode(EXmlNodeType_Element);
      _pRoot->SetName(MO_DEF_ROOT_NAME);
   }
   return _pRoot;
}

//============================================================
// <T>创建一个配置节点。</T>
//
// @return 配置节点
//============================================================
FXmlNode* FXmlDocument::CreateNode(EXmlNodeType type){
   FXmlNode* pNode = MO_CREATE(FXmlNode, this, type);
   pNode->SetName(MO_DEF_NODE_NAME);
   _pNodes->Push(pNode);
   return pNode;
}

//============================================================
// <T>同步元素节点到配置节点内。</T>
//
// @param piDocument 文档对象
// @param pNode 配置节点
// @param piNode 元素节点
//============================================================
#ifdef _MO_WINDOWS
void SyncNodeFromElement(FXmlDocument* pDocument, MSXML2::IXMLDOMDocument3Ptr piDocument, FXmlNode* pNode, MSXML2::IXMLDOMNodePtr piNode){
#ifdef _UNICODE
#else
   pNode->SetName(MO_CRT_BSTR_TO_STRING(piNode->nodeName));
#endif
   // 判断是否存在属性
   MSXML2::IXMLDOMNamedNodeMapPtr piAttributes = piNode->Getattributes();
   TInt attrLength = piAttributes->Getlength();
   if(attrLength > 0){
      // 获取所有属性
      FAttributes* pAttributes = pNode->Attributes();
      for(TInt n=0; n<attrLength; n++){
         MSXML2::IXMLDOMNodePtr attrNode = piAttributes->Getitem(n);
#ifdef _UNICODE
#else
         TCharC* pAttrName = MO_CRT_BSTR_TO_STRING(attrNode->nodeName);
         TCharC* pAttrValue = MO_CRT_BSTR_TO_STRING(attrNode->nodeValue.bstrVal);
         pAttributes->Set(pAttrName, pAttrValue);
#endif
      }
   }
   // 判断是否存在子节点
   MSXML2::IXMLDOMNodeListPtr piChildren = piNode->GetchildNodes();
   TInt nodeLength = piChildren->Getlength();
   if(nodeLength > 0){
      // 获取所有属性
      FXmlNodes* pChildren = pNode->Nodes();
      for(TInt n=0; n<nodeLength; n++){
         // 获得当前子节点
         MSXML2::IXMLDOMNodePtr piChild = piChildren->Getitem(n);
         switch(piChild->GetnodeType()){
            case NODE_ELEMENT:{
                  FXmlNode* pChild = pDocument->CreateNode(EXmlNodeType_Element);
                  SyncNodeFromElement(pDocument, piDocument, pChild, piChild);
                  pChildren->Push(pChild);
               }
               break;
            case NODE_TEXT:{
#ifdef _UNICODE
#else
               pNode->TextAppend(MO_CRT_BSTR_TO_STRING(piChild->nodeValue.bstrVal));
#endif
               }
               break;
            case NODE_CDATA_SECTION:{
#ifdef _UNICODE
#else
                  pNode->SetText(MO_CRT_BSTR_TO_STRING(piChild->nodeValue.bstrVal));
#endif
               }
               break;
            case NODE_COMMENT:{
                  FXmlNode* pChild = pDocument->CreateNode(EXmlNodeType_Comment);
#ifdef _UNICODE
#else
                  pChild->SetText(MO_CRT_BSTR_TO_STRING(piChild->nodeValue.bstrVal));
#endif
                  pChildren->Push(pChild);
               }
               break;
         }
      }
   }
}
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
void SyncNodeFromElement(FXmlDocument* pDocument, FXmlNode* pNode, xmlDocPtr xdocPtr, xmlNodePtr xnodePtr){
   pNode->SetName((TCharC*)xnodePtr->name);
   // 判断是否存在属性
   xmlAttrPtr xattrsPtr = xnodePtr->properties;
   if(NULL != xattrsPtr){
      // 获取所有属性
      FAttributes* pAttributes = pNode->Attributes();
      while(NULL != xattrsPtr){
         // 设置属性
         xmlChar* szAttrValue = xmlGetProp(xnodePtr, xattrsPtr->name);
         pAttributes->Set((TCharC*)xattrsPtr->name, (TCharC*)szAttrValue);
         xmlFree(szAttrValue);
         // 获得下一个节点
         xattrsPtr = xattrsPtr->next;
      }
   }
   // 判断是否存在子节点
   xmlNodePtr xchildPtr = xnodePtr->children;
   if(NULL != xchildPtr){
      // 获取所有属性
      FXmlNodes* pChildren = pNode->Nodes();
      while(NULL != xchildPtr){
         // 获得当前子节点
         switch(xchildPtr->type){
            case XML_ELEMENT_NODE:{
               // 创建节点
               FXmlNode* pChild = pDocument->CreateNode(EXmlNodeType_Element);
               SyncNodeFromElement(pDocument, pChild, xdocPtr, xchildPtr);
               pChildren->Push(pChild);
               break;
            }
            case XML_TEXT_NODE:{
               // 获得文本数据
               xmlChar* szText = xmlNodeGetContent(xchildPtr);
               pNode->TextAppend((TCharC*)szText);
               xmlFree(szText);
               break;
            }
            case XML_CDATA_SECTION_NODE:{
               // 获得文本数据
               xmlChar* szText = xmlNodeGetContent(xchildPtr);
               pNode->SetText((TCharC*)szText);
               xmlFree(szText);
               break;
            }
            case XML_COMMENT_NODE:{
               // 创建注释节点
               FXmlNode* pChild = pDocument->CreateNode(EXmlNodeType_Comment);
               xmlChar* szText = xmlNodeGetContent(xchildPtr);
               pChild->SetText((TCharC*)szText);
               xmlFree(szText);
               pChildren->Push(pChild);
               break;
            }
            case XML_ATTRIBUTE_NODE:
            case XML_ENTITY_REF_NODE:
            case XML_ENTITY_NODE:
            case XML_PI_NODE:
            case XML_DOCUMENT_NODE:
            case XML_DOCUMENT_TYPE_NODE:
            case XML_DOCUMENT_FRAG_NODE:
            case XML_NOTATION_NODE:
            case XML_HTML_DOCUMENT_NODE:
            case XML_DTD_NODE:
            case XML_ELEMENT_DECL:
            case XML_ATTRIBUTE_DECL:
            case XML_ENTITY_DECL:
            case XML_NAMESPACE_DECL:
            case XML_XINCLUDE_START:
            case XML_XINCLUDE_END:
            case XML_DOCB_DOCUMENT_NODE:{
               break;
            }
         }
         xchildPtr = xchildPtr->next;
      }
   }
}
#endif // _MO_LINUX

//============================================================
// <T>同步配置节点到元素节点内。</T>
//
// @param piDocument 文档对象
// @param piNode 元素节点
// @param pNode 配置节点
// @param level 节点层次
//============================================================
#ifdef _MO_SYS_WINDOWS
void SyncElementFromNode(FXmlDocument* pDocument, MSXML2::IXMLDOMDocument3Ptr piDocument, MSXML2::IXMLDOMNodePtr piNode, FXmlNode* pNode, TInt level){
   // 设置文本内容
   if(!pNode->HasNode()){
      if(!pNode->HasText()){
         piNode->put_text(_bstr_t(pNode->Text()));
      }
   }
   // 判断是否存在属性
   if(pNode->HasAttribute()){
      FAttributes* pAttributes = pNode->Attributes();
      TAttributesIteratorC iterator = pAttributes->IteratorC();
      while(iterator.Next()){
         MSXML2::IXMLDOMAttributePtr piAttribute = piDocument->createAttribute(_bstr_t(iterator.Name()));
         piAttribute->put_value(_variant_t(iterator.Value()));
         piNode->attributes->setNamedItem(piAttribute);
      }
   }
   // 判断是否存在子节点
   if(pNode->HasNode()){
      FXmlNodes* pNodes = pNode->Nodes();
      TInt count = pNodes->Count();
      // 获取所有子节点
      for(TInt n = 0; n < count; n++){
         FXmlNode* pChild = pNodes->GetByIndex(n);
         switch(pChild->NodeType()){
            case EXmlNodeType_Element:{
                  // 新建换行符
                  //TString space("\n");
                  //space.AppendRepeat(_indent, level);
                  //piNode->appendChild(piDocument->createTextNode(space.MemoryC()));
                  // 新建节点内容
                  MSXML2::IXMLDOMElementPtr piChild = piDocument->createElement(_bstr_t(pChild->Name()));
                  SyncElementFromNode(pDocument, piDocument, piChild, pChild, level+1);
                  piNode->appendChild(piChild);
               }
               break;
            case EXmlNodeType_Text:{
               }
               break;
            case EXmlNodeType_Data:{
               }
               break;
            case EXmlNodeType_Comment:{
                  //piChild = piDocument->createNode(NODE_COMMENT, _bstr_t(pChild->Name().MemoryC()), _bstr_t());
                  //SyncElementFromNode(piDocument, piChild, pChild);
                  //piNode->appendChild(piChild);
               }
               break;
         }
      }
      // 新建换行符
      //TString afterSpace("\n");
      //afterSpace.AppendRepeat(_indent, level-1);
      //piNode->appendChild(piDocument->createTextNode(afterSpace.MemoryC()));
   }
}
#endif // _MO_SYS_WINDOWS
#ifdef _MO_LINUX
void SyncElementFromNode(FXmlDocument* pDocument, FXmlNode* pNode, xmlDocPtr xdocPtr, xmlNodePtr xnodePtr){
   // 设置文本内容
   if(!pNode->HasNode()){
      if(pNode->HasText()){
         xmlNodeSetContent(xnodePtr, BAD_CAST(pNode->Text()));
      }
   }
   // 判断是否存在属性
   if(pNode->HasAttribute()){
      // 获取所有属性
      TAttributesIteratorC iterator = pNode->Attributes()->IteratorC();
      while(iterator.Next()){
         xmlNewProp(xnodePtr, BAD_CAST(iterator.Name()), BAD_CAST(iterator.Value()));
      }
   }
   // 判断是否存在子节点
   if(pNode->HasNode()){
      TXmlNodeIteratorC iterator = pNode->NodeIteratorC();
      // 获取所有子节点
      while(iterator.Next()){
         FXmlNode* pChild = *iterator;
         switch(pChild->NodeType()){
            case EXmlNodeType_Element:{
               // 新建节点内容
               xmlNodePtr xchildPtr = xmlNewChild(xnodePtr, NULL, BAD_CAST(pChild->Name()), BAD_CAST(pChild->Text()));
               // 递归处理
               SyncElementFromNode(pDocument, pChild, xdocPtr, xchildPtr);
               break;
               }
            case EXmlNodeType_Text:{
               break;
               }
            case EXmlNodeType_Data:{
               break;
               }
            case EXmlNodeType_Comment:{
               xmlNodePtr xchildPtr = xmlNewComment(BAD_CAST(pChild->Text()));
               xmlAddChild(xnodePtr, xchildPtr);
               break;
               }
         }
      }
   }
}
#endif // _MO_LINUX

//============================================================
// <T>序列化内容到输出流。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TBool FXmlDocument::Serialize(IDataOutput* pOutput){
   return Root()->Serialize(pOutput);
}

//============================================================
// <T>从输入流中反序列化内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FXmlDocument::Unserialize(IDataInput* pInput){
   return Root()->Unserialize(pInput);
}

//============================================================
// <T>序列化内容到文件。</T>
//
// @param pOutput 输出流
// @return 处理结果
//============================================================
TBool FXmlDocument::SerializeFile(TCharC* pFileName){
   MO_FATAL_UNSUPPORT();
   return ETrue;
}

//============================================================
// <T>从文件中反序列化内容。</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FXmlDocument::UnserializeFile(TCharC* pFileName){
   // 打开文件
#ifdef _MO_WINDOWS
   TFileHandle handle;
#ifdef _UNICODE
   _wfopen_s(&handle, pFileName, TC("rb"));
#else
   fopen_s(&handle, pFileName, "rb");
#endif
#else
   TFileHandle handle = fopen(pFileName, "rb");
#endif
   if(NULL == handle){
      MO_PERROR(fopen);
      return EFalse;
   }
   // 获得长度
   if(ESuccess != fseek(handle, 0, SEEK_END)){
      MO_PERROR(fseek);
      return EFalse;
   }
   TInt length = ftell(handle);
   // 从开始位置读取
   if(ESuccess != fseek(handle, 0, SEEK_SET)){
      MO_PERROR(fseek);
      return EFalse;
   }
   TByte* pMemory = MO_TYPES_ALLOC(TByte, length);
   TInt readed = fread(pMemory, length, 1, handle);
   if(1 != readed){
      MO_PFATAL(fread);
   }
   // 反序列化文件
   TLinkInput input(pMemory, length);
   Unserialize(&input);
   // 关闭文件
   if(ESuccess != fclose(handle)){
      MO_PFATAL(fclose);
   }
   MO_FREE(pMemory);
   return ETrue;
}

//============================================================
// <T>加载配置文件。</T>
//
// @param fileName 文件名称
//============================================================
TBool FXmlDocument::LoadFile(TCharC* pFileName){
   TFileInfo fileInfo = pFileName;
   _fileName.Assign(fileInfo.FullName());
#ifdef _MO_SYS_WINDOWS
   // 存储要加载文件的名称
   // 创建文档对象
   MSXML2::IXMLDOMDocument3Ptr piDocument;
   HRESULT hr = piDocument.CreateInstance(__uuidof(MSXML2::DOMDocument60));
   if(FAILED(hr)){
      MO_FATAL(TC("Create document failure. (file_name=%s)"), (TCharC*)_fileName);
      return EFalse;
   }
   piDocument->put_async(VARIANT_FALSE);
   piDocument->put_validateOnParse(VARIANT_FALSE);
   piDocument->put_resolveExternals(VARIANT_FALSE);
   piDocument->put_preserveWhiteSpace(VARIANT_TRUE);
   VARIANT_BOOL result = piDocument->load((TCharC*)_fileName);
   if(!result){
      piDocument.Release();
      MO_FATAL(TC("Load configuration file failure. (file_name=%s)"), (TCharC*)_fileName);
      return EFalse;
   }
   // 元素节点到配置节点
   MSXML2::IXMLDOMElementPtr piRoot = piDocument->documentElement;
   if(NULL == piRoot){
      piDocument.Release();
      MO_FATAL(TC("Load configuration roor failure. (file_name=%s)"), (TCharC*)_fileName);
      return EFalse;
   }
   SyncNodeFromElement(this, piDocument, Root(), piRoot);
   // 释放对象
   piDocument.Release();
#endif // _MO_SYS_WINDOWS
#ifdef _MO_LINUX
   // 打开文档
   xmlDocPtr xdocPtr;
   if(NULL == (xdocPtr = xmlReadFile(_fileName, NULL, XML_PARSE_RECOVER | XML_PARSE_NOBLANKS))){
      MO_PERROR(xmlParseFile);
      return EFalse;
   }
   // 获得跟节点
   xmlNodePtr nodePtr;
   if(NULL == (nodePtr = xmlDocGetRootElement(xdocPtr))){
      MO_PERROR(xmlDocGetRootElement);
      xmlFreeDoc(xdocPtr);
      xdocPtr = NULL;
      return EFalse;
   }
   // 元素节点到配置节点
   SyncNodeFromElement(this, Root(), xdocPtr, nodePtr);
   // 释放对象
   xmlFreeDoc(xdocPtr);
#ifdef _MO_DEBUG
   xmlMemoryDump();
#endif // _MO_DEBUG
#endif // _MO_LINUX
   return ETrue;
}

//============================================================
// <T>存储配置文件。</T>
//
// @param fileName 文件名称
//============================================================
TBool FXmlDocument::SaveFile8(TCharC* pFileName){
#ifdef _MO_SYS_WINDOWS
   // 建立文本内容
   FStringBuffer* pXml = MO_CREATE(FStringBuffer);
   BuildXml(pXml);
#ifdef _MO_UNICODE
   FStringBuffer8* pXml8 = MO_CREATE(FStringBuffer8);
   pXml8->Assign16(pXml->MemoryC());
#else
   FStringBuffer8* pXml8 = pXml;
#endif // _MO_UNICODE
   // 存储文件
   HANDLE handle = CreateFile(pFileName, GENERIC_WRITE, NULL, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL);
   MO_ASSERT(INVALID_HANDLE_VALUE != handle);
   TDword writted;
   WriteFile(handle, pXml8->MemoryC(), pXml8->ByteLength(), &writted, NULL);
   MO_ASSERT(writted == pXml8->ByteLength());
   CloseHandle(handle);
   // 释放资源
#ifdef _MO_UNICODE
   MO_DELETE(pXml8);
#endif // _MO_UNICODE
   MO_DELETE(pXml);
#endif // _MO_SYS_WINDOWS
   return ETrue;
}

//============================================================
// <T>存储配置文件。</T>
//
// @param fileName 文件名称
//============================================================
TBool FXmlDocument::SaveFile(TCharC* pFileName){
#ifdef _MO_WINDOWS
#if 1
   // 保存文件
   FStringBuffer* pXml = MO_CREATE(FStringBuffer);
   BuildXml(pXml);
   HANDLE handle = CreateFile(pFileName, GENERIC_WRITE, NULL, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL);
   MO_ASSERT(INVALID_HANDLE_VALUE != handle);
   TDword writted;
   WriteFile(handle, pXml->MemoryC(), pXml->ByteLength(), &writted, NULL);
   MO_ASSERT(writted == pXml->ByteLength());
   CloseHandle(handle);
   MO_DELETE(pXml);
#else
   // 创建文档对象
   MSXML2::IXMLDOMDocument3Ptr piDocument;
   HRESULT hr = piDocument.CreateInstance(__uuidof(MSXML2::DOMDocument60));
   if(FAILED(hr)){
      throw EError_Unknown;
   }
   piDocument->put_async(VARIANT_FALSE);
   piDocument->put_validateOnParse(VARIANT_FALSE);
   piDocument->put_resolveExternals(VARIANT_FALSE);
   piDocument->put_preserveWhiteSpace(VARIANT_TRUE);
   // 创建文件声明
   MSXML2::IXMLDOMProcessingInstructionPtr piInstruction = piDocument->createProcessingInstruction("xml", "version='1.0' encoding='UTF-8'");
   piDocument->appendChild(piInstruction);
   // 创建空行
   //MSXML2::IXMLDOMTextPtr piSpace = piDocument->createTextNode("\n");
   //piFragment->appendChild(piSpace);
   // 同步配置节点到元素节点
   FXmlNode* pRoot = Root();
   MSXML2::IXMLDOMElementPtr piRoot = piDocument->createNode(NODE_ELEMENT, _bstr_t(pRoot->Name()), _bstr_t());
   SyncElementFromNode(this, piDocument, piRoot, pRoot, 1);
   piDocument->documentElement = piRoot;
   piDocument->save(_bstr_t(pFileName));
   // 释放对象
   piDocument.Release();
#endif
#endif // _MO_WINDOWS
#ifdef _MO_LINUX
#if 0
   // TODO:无法控制格式化
   // 打开文件
   xmlDocPtr xdocPtr = xmlNewDoc(BAD_CAST("1.0"));
   if(NULL == xdocPtr){
      MO_PERROR(xmlNewDoc);
      return EFalse;
   }
   // 创建根节点
   FXmlNode* pRoot = Root();
   xmlNodePtr xnodePtr = xmlNewDocNode(xdocPtr, NULL, BAD_CAST(pRoot->Name().MemoryC()), BAD_CAST(pRoot->Text().MemoryC()));
   if(NULL == xnodePtr){
      MO_PERROR(xmlNewDocNode);
      xmlFreeDoc(xdocPtr);
      xdocPtr = NULL;
      return EFalse;
   }
   xmlDocSetRootElement(xdocPtr, xnodePtr);
   // 同步节点
   SyncElementFromNode(this, Root(), xdocPtr, xnodePtr);
   // 保存文件
   /*xmlKeepBlanksDefault(0);
   xmlSaveCtxtPtr xctxtPtr = xmlSaveToFilename(fileName.MemoryC(), "UTF-8", XML_SAVE_FORMAT);
   if(NULL == xctxtPtr){
      MO_PERROR(xmlSaveToFilename);
      return EFalse;
   }
   if(xmlSaveDoc(xctxtPtr, xdocPtr) < 0){
      MO_PERROR(xmlSaveDoc);
      return EFalse;
   }
   xmlSaveClose(xctxtPtr);*/
   // xmlSaveFormatFileEnc(fileName.MemoryC(), xdocPtr, "UTF-8", 1);
   // 释放对象
   xmlFreeDoc(xdocPtr);
#ifdef _MO_DEBUG
   xmlMemoryDump();
#endif
#else
   // 保存文件
   TString xml = Xml();
   TInt handle = open(pFileName, O_CREAT | O_TRUNC | O_WRONLY, 0777);
   TInt size = write(handle, xml.MemoryC(), xml.Length());
   MO_ASSERT(size == xml.Length());
   close(handle);
#endif
#endif // _MO_LINUX
   return ETrue;
}

//============================================================
// <T>获得XML字符串。</T>
//
// @return XML字符串
//============================================================
void FXmlDocument::BuildXml(MString* pXml){
   pXml->EnsureSize(1024*1024);
   pXml->Append(TC("<?xml version=\"1.0\" encoding=\""));
   pXml->Append(_encoding);
   pXml->Append(TC("\"?>\n"));
   Root()->BuildXml(pXml);
}

//============================================================
// <T>获得XML字符串。</T>
//
// @return XML字符串
//============================================================
TString FXmlDocument::Xml(){
   TStringBuffer xml;
   xml.EnsureSize(1024*1024);
   xml.Append(TC("<?xml version=\"1.0\" encoding=\""));
   xml.Append(_encoding);
   xml.Append(TC("\"?>\n"));
   Root()->BuildXml(&xml);
   return xml;
}

MO_NAMESPACE_END
