#ifndef __MO_CR_CONFIGURATION_H__
#define __MO_CR_CONFIGURATION_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>可设置对象控制台。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CR_DECLARE FConfigurationConsole : public FConsole{
protected:
   FConfiguraList* _pConfigurations;
   FXmlNodeDictionary* _pNodes;
   FXmlDocumentList* _pDocuments;
public:
   FConfigurationConsole();
   MO_ABSTRACT( ~FConfigurationConsole() );
public:
   void Register(IConfiguration* pConfig);
   void Unregister(IConfiguration* pConfig);
public:
   TBool LoadFile(TCharC* pFileName);
   TBool UnloadFiles();
public:
   TBool LoadConfiguration(TCharC* pFileName);
};
//------------------------------------------------------------
class MO_CR_DECLARE RConfigurationManager : public RSingleton<FConfigurationConsole>{
};

//============================================================
// <T>配置文件容器控制台。</T>
//
// @history 100927 MAOCY 创建
//============================================================
class MO_CR_DECLARE FXmlContainerConsole : public FConsole{
protected:
   FXmlDocumentDictionary* _pDocuments;
public:
   FXmlContainerConsole();
   MO_ABSTRACT( ~FXmlContainerConsole() );
public:
   FXmlDocument* Find(TCharC* pFileName);
   FXmlDocument* Get(TCharC* pFileName);
   FXmlDocument* Sync(TCharC* pFileName);
};
//------------------------------------------------------------
class MO_CR_DECLARE RXmlContainerManager : public RSingleton<FXmlContainerConsole>{
};

MO_NAMESPACE_END

#endif // __MO_CR_CONFIGURATION_H__
