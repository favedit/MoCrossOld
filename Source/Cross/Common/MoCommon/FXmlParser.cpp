#include "MoCmFormat.h"
#include "MoCmXml.h"
#include "MoCmLanguage.h"
#include "MoCmFile.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FXmlParser, FConfigParser);

//============================================================
// <T>创建配置解析器。</T>
//============================================================
FXmlParser::FXmlParser(){
   _builder = FXmlBuilder::InstanceCreate();
   _pFactory->Register(MO_CONFIG_ELEMENT_NODE, FXmlNode::Class());
   _pFactory->Register(MO_CONFIG_ELEMENT_TEXT, FXmlNode::Class());
}

//============================================================
// <T>释放配置解析器。</T>
//============================================================
FXmlParser::~FXmlParser(){
}

//============================================================
// <T>扫描头信息。</T>
//
// @param pSource 来源
// @return 数据开始
//============================================================
TCharC* FXmlParser::ScanHeader(TCharC* pSource){
   MO_ASSERT(pSource);
   // 查找头信息描述块
   TInt begin = -1;
   TInt end = -1;
   TInt length = RString::Length(pSource);
   for(TInt n = 0; n < length; n++){
      TChar value = pSource[n];
      // 结束查找
      if((value == 0) || (value == '>') || (value == '\r') || (value == '\n')){
         return pSource;
      }
      // 查找开始标志
      if((value == '<') && (pSource[n + 1] == '?') && (pSource[n + 2] == 'x') && (pSource[n + 3] == 'm') && (pSource[n + 4] == 'l') && (pSource[n + 5] == ' ')){
         begin = n;
         continue;
      }
      // 查找结束标志
      if((value == '?') && (pSource[n + 1] == '>')){
         end = n;
         break;
      }
   }
   // 解析内容
   TFsText headSource = TStringPtrC(pSource + begin + 6, end - begin - 6);
   FXmlNode* pHeadNode = MO_CREATE(FXmlNode);
   ParseAttribute(pHeadNode, headSource);
   TCharC* pVersion = pHeadNode->Get(TC("version"), NULL);
   TCharC* pEncoding = pHeadNode->Get(TC("encoding"), NULL);
   // 返回开始内容
   TCharC* pReader = pSource + end + 2;
   if(*pReader == '\r'){
      pReader++;
   }
   if(*pReader == '\n'){
      pReader++;
   }
   return pReader;
}

//============================================================
// <T>从来源中加载一个配置节点。</T>
//
// @param pSource 来源
// @return 配置节点
//============================================================
FXmlNode* FXmlParser::LoadNode(TCharC* pSource){
   // 扫描头信息
   TCharC* pBody = ScanHeader(pSource);
   FInstance* pRootInstance = Parse(pBody);
   // 解析内容
   return pRootInstance->Convert<FXmlNode>();
}

//============================================================
// <T>从文件中加载一个配置节点。</T>
//
// @param pFileName 文件名称
// @return 配置节点
//============================================================
FXmlNode* FXmlParser::LoadNodeFile(TCharC* pFileName){
   // 加载文件
   FFileString* pFile = MO_CREATE(FFileString);
   pFile->LoadFile(pFileName);
   // 解析内容
   TCharC* pSource = pFile->MemoryC();
   FXmlNode* pNode = LoadNode(pSource);
   // 释放资源
   MO_DELETE(pFile);
   return pNode;
}

//============================================================
FXmlDocument* FXmlParser::LoadDocument(TCharC* pSource){
   return NULL;
}

//============================================================
FXmlDocument* FXmlParser::LoadDocumentFile(TCharC* pFileName){
   return NULL;
}

MO_NAMESPACE_END
