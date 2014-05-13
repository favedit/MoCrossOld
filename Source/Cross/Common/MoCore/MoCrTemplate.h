#ifndef __MO_CR_TEMPLATE_H__
#define __MO_CR_TEMPLATE_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

#ifndef __MO_CR_STATISTICS_H__
#include "MoCrStatistics.h"
#endif // __MO_CR_STATISTICS_H__

#ifndef __MO_CR_SERVICE_H__
#include "MoCrService.h"
#endif // __MO_CR_SERVICE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FTemplateTag;
typedef MO_CR_DECLARE FObjects<FTemplateTag*> FTemplateTagCollection;
class FTemplate;
class FTemplateTagInclude;
typedef MO_CR_DECLARE FObjects<FTemplateTagInclude*> FTemplateTagIncludeCollection;
class FTemplateBuilder;
class FTemplateSource;
class FTemplateConsole;

//============================================================
// <T>模板标签结果。</T>
//============================================================
enum ETemplateTagResult{
   ETemplateTagResult_None,
   ETemplateTagResult_Include,
   ETemplateTagResult_Continue,
   ETemplateTagResult_Loop,
   ETemplateTagResult_Stop,
   ETemplateTagResult_Failure,
};

//============================================================
// <T>模板环境。</T>
//============================================================
class MO_CR_DECLARE FTemplateContext : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FTemplateContext, FInstance);
protected:
   TString _space;
   GPtr<FXmlNode> _config;
   GPtr<FXmlNode> _mergeConfig;
   FStringBuffer* _pSource;
   TBool _trimOnceFlag;
   TBool _trimBeginLine;
   TBool _trimEmptyLine;
   TBool _trimEndLine;
   TBool _trimCommentLine;
public:
   FTemplateContext();
   MO_ABSTRACT ~FTemplateContext();
public:
   //------------------------------------------------------------
   // <T>获得空间。</T>
   MO_INLINE TCharC* Space(){
      return _space;
   }
   //------------------------------------------------------------
   // <T>设置空间。</T>
   MO_INLINE void SetSpace(TCharC* pSpace){
      _space = pSpace;
   }
   //------------------------------------------------------------
   // <T>获得配置。</T>
   MO_INLINE FXmlNode* Config(){
      return _config;
   }
   //------------------------------------------------------------
   // <T>获得合并配置节点。</T>
   MO_INLINE FXmlNode* MergeConfig(){
      return _mergeConfig;
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE FStringBuffer* Source(){
      return _pSource;
   }
   //------------------------------------------------------------
   // <T>获得截取开始回行标志。</T>
   MO_INLINE TBool TrimBeginLine(){
      return _trimBeginLine;
   }
   //------------------------------------------------------------
   // <T>设置截取开始回行标志。</T>
   MO_INLINE void SetTrimBeginLine(TBool trimBeginLine){
      _trimBeginLine = trimBeginLine;
   }
   //------------------------------------------------------------
   // <T>获得截取空行标志。</T>
   MO_INLINE TBool TrimEmptyLine(){
      return _trimEmptyLine;
   }
   //------------------------------------------------------------
   // <T>设置截取空行标志。</T>
   MO_INLINE void SetTrimEmptyLine(TBool trimEmptyLine){
      _trimEmptyLine = trimEmptyLine;
   }
   //------------------------------------------------------------
   // <T>获得截取结束回行标志。</T>
   MO_INLINE TBool TrimEndLine(){
      return _trimEndLine;
   }
   //------------------------------------------------------------
   // <T>设置截取结束回行标志。</T>
   MO_INLINE void SetTrimEndLine(TBool trimEndLine){
      _trimEndLine = trimEndLine;
   }
   //------------------------------------------------------------
   // <T>获得截取注释标志。</T>
   MO_INLINE TBool TrimCommentLine(){
      return _trimCommentLine;
   }
   //------------------------------------------------------------
   // <T>设置截取注释标志。</T>
   MO_INLINE void SetTrimCommentLine(TBool trimCommentLine){
      _trimCommentLine = trimCommentLine;
   }
public:
   void SetTrimOnceFlag();
   void TrimLastLine();
public:
   TBool GetDefineAsBool(TCharC* pName, TBool defaultValue);
   TInt GetDefineAsInt(TCharC* pName, TInt defaultValue);
   TCharC* GetDefineAsString(TCharC* pName, TCharC* pDefaultValue);
   void DefineBool(TCharC* pName, TBool value);
   void DefineInt(TCharC* pName, TInt value);
   void DefineString(TCharC* pName, TCharC* pValue);
public:
   TCharC* SourceMemory();
   void SourceReset();
   void SourceWrite(TCharC* pSource);
   void SourceFormat();
};

//============================================================
// <T>模板标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTag : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTag, FInstance);
protected:
   TString _name;
   TBool _nowrapLeft;
   TBool _nowrapRight;
   FTemplateTagCollection* _pChildren;
public:
   FTemplateTag();
   MO_ABSTRACT ~FTemplateTag();
public:
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得左侧不卷行。</T>
   MO_INLINE TBool NowrapLeft(){
      return _nowrapLeft;
   }
   //------------------------------------------------------------
   // <T>获得右侧不卷行。</T>
   MO_INLINE TBool NowrapRight(){
      return _nowrapRight;
   }
   //------------------------------------------------------------
   // <T>获得子节点集合。</T>
   MO_INLINE FTemplateTagCollection* Children(){
      return _pChildren;
   }
public:
   MO_ABSTRACT ETemplateTagResult OnBegin(FTemplateContext* pContext);
   MO_ABSTRACT ETemplateTagResult OnLoop(FTemplateContext* pContext);
   MO_ABSTRACT ETemplateTagResult OnEnd(FTemplateContext* pContext);
public:
   MO_ABSTRACT TResult SetAttribute(TCharC* pName, TCharC* pValue);
   MO_ABSTRACT TResult Push(FTemplateTag* pTag);
   MO_ABSTRACT TResult Setup(FTemplateBuilder* pBuilder);
};

//============================================================
// <T>模板文本标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagText : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagText, FTemplateTag);
protected:
   TString _text;
public:
   FTemplateTagText();
   MO_ABSTRACT ~FTemplateTagText();
public:
   //------------------------------------------------------------
   // <T>获得文本。</T>
   MO_INLINE TCharC* Text(){
      return _text;
   }
   //------------------------------------------------------------
   // <T>设置文本。</T>
   MO_INLINE void SetText(TCharC* pText){
      _text = pText;
   }
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
};

//============================================================
// <T>模板输出逻辑标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagWrite : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagWrite, FTemplateTag);
protected:
   TString _source;
public:
   FTemplateTagWrite();
   MO_ABSTRACT ~FTemplateTagWrite();
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
public:
   MO_OVERRIDE TResult SetAttribute(TCharC* pName, TCharC* pValue);
};

//============================================================
// <T>模板包含逻辑标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagInclude : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagInclude, FTemplateTag);
protected:
   TString _source;
   TString _segment;
   FTemplate* _pTemplate;
   FTemplateSource* _pSource;
   FTemplateTagIncludeCollection* _pIncludeTags;
public:
   FTemplateTagInclude();
   MO_ABSTRACT ~FTemplateTagInclude();
public:
   //------------------------------------------------------------
   // <T>获得根节点。</T>
   MO_INLINE FTemplate* Template(){
      return _pTemplate;
   }
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
   MO_OVERRIDE ETemplateTagResult OnEnd(FTemplateContext* pContext);
public:
   MO_OVERRIDE TResult SetAttribute(TCharC* pName, TCharC* pValue);
   MO_OVERRIDE TResult Setup(FTemplateBuilder* pBuilder);
};

//============================================================
// <T>模板真逻辑标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagTrue : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagTrue, FTemplateTag);
protected:
   TString _source;
public:
   FTemplateTagTrue();
   MO_ABSTRACT ~FTemplateTagTrue();
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
   MO_OVERRIDE ETemplateTagResult OnEnd(FTemplateContext* pContext);
public:
   MO_OVERRIDE TResult SetAttribute(TCharC* pName, TCharC* pValue);
};

//============================================================
// <T>模板假逻辑标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagFalse : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagFalse, FTemplateTag);
protected:
   TString _source;
public:
   FTemplateTagFalse();
   MO_ABSTRACT ~FTemplateTagFalse();
public:
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
   MO_OVERRIDE ETemplateTagResult OnEnd(FTemplateContext* pContext);
public:
   MO_OVERRIDE TResult SetAttribute(TCharC* pName, TCharC* pValue);
};

//============================================================
// <T>模板相等逻辑标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagEquals : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagEquals, FTemplateTag);
protected:
   TString _source;
public:
   FTemplateTagEquals();
   MO_ABSTRACT ~FTemplateTagEquals();
public:
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
   MO_OVERRIDE ETemplateTagResult OnEnd(FTemplateContext* pContext);
public:
   MO_OVERRIDE TResult SetAttribute(TCharC* pName, TCharC* pValue);
};

//============================================================
// <T>模板计数器标签。</T>
//============================================================
class MO_CR_DECLARE FTemplateTagCounter : public FTemplateTag
{
   MO_CLASS_DECLARE_INHERITS(FTemplateTagCounter, FTemplateTag);
protected:
   TString _source;
   TString _operator;
   TString _value;
   TString _define;
public:
   FTemplateTagCounter();
   MO_ABSTRACT ~FTemplateTagCounter();
public:
public:
   MO_OVERRIDE ETemplateTagResult OnBegin(FTemplateContext* pContext);
   MO_OVERRIDE ETemplateTagResult OnEnd(FTemplateContext* pContext);
public:
   MO_OVERRIDE TResult SetAttribute(TCharC* pName, TCharC* pValue);
};

//============================================================
// <T>模板代码。</T>
//============================================================
class MO_CR_DECLARE FTemplateSource : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FTemplateSource, FInstance);
protected:
   GPtr<FTemplateTag> _rootTag;
public:
   FTemplateSource();
   MO_ABSTRACT ~FTemplateSource();
public:
   //------------------------------------------------------------
   // <T>获得根节点。</T>
   MO_INLINE FTemplateTag* RootTag(){
      return _rootTag;
   }
   //------------------------------------------------------------
   // <T>设置根节点。</T>
   MO_INLINE void SetRootTag(FTemplateTag* pTag){
      _rootTag = pTag;
   }
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FDictionary<FTemplateSource*> FTemplateSourceDictionary;

//============================================================
// <T>模板。</T>
//============================================================
class MO_CR_DECLARE FTemplate : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FTemplate, FInstance);
protected:
   GPtr<FXmlNode> _config;
   FTemplateSourceDictionary* _pSources;
   FTemplateTagIncludeCollection* _pIncludeTags;
public:
   FTemplate();
   MO_ABSTRACT ~FTemplate();
public:
   //------------------------------------------------------------
   // <T>获得配置信息。</T>
   MO_INLINE FXmlNode* Config(){
      return _config;
   }
   //------------------------------------------------------------
   // <T>设置配置信息。</T>
   MO_INLINE void SetConfig(FXmlNode* pConfig){
      _config = pConfig;
   }
   //------------------------------------------------------------
   // <T>获得来源集合。</T>
   MO_INLINE FTemplateSourceDictionary* Sources(){
      return _pSources;
   }
   //------------------------------------------------------------
   // <T>获得包含标签集合。</T>
   MO_INLINE FTemplateTagIncludeCollection* IncludeTags(){
      return _pIncludeTags;
   }
protected:
   TResult ParseTag(FTemplateContext* pContext, FTemplateTag* pParentTag, TInt index, FTemplateTag* pTag);
public:
   TResult Parse(FTemplateContext* pContext, TCharC* pPartName = NULL);
   TResult AdjustConfigNodes(FTemplateContext* pContext);
   TResult Track();
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FDictionary<FTemplate*> FTemplateDictionary;

//============================================================
// <T>模板构建器。</T>
//============================================================
class MO_CR_DECLARE FTemplateBuilder : public FConfigBuilder
{
   MO_CLASS_DECLARE_INHERITS(FTemplateBuilder, FConfigBuilder);
protected:
   FTemplateConsole* _pConsole;
   FTemplateContext* _pContext;
   FTemplate* _pTemplate;
public:
   FTemplateBuilder();
   MO_ABSTRACT ~FTemplateBuilder();
public:
   //------------------------------------------------------------
   // <T>获得控制台。</T>
   MO_INLINE FTemplateConsole* Console(){
      return _pConsole;
   }
   //------------------------------------------------------------
   // <T>设置控制台。</T>
   MO_INLINE void SetConsole(FTemplateConsole* pConsole){
      _pConsole = pConsole;
   }
   //------------------------------------------------------------
   // <T>获得环境。</T>
   MO_INLINE FTemplateContext* Context(){
      return _pContext;
   }
   //------------------------------------------------------------
   // <T>设置环境。</T>
   MO_INLINE void SetContext(FTemplateContext* pContext){
      _pContext = pContext;
   }
   //------------------------------------------------------------
   // <T>获得模板。</T>
   MO_INLINE FTemplate* Template(){
      return _pTemplate;
   }
   //------------------------------------------------------------
   // <T>设置模板。</T>
   MO_INLINE void SetTemplate(FTemplate* pTemplate){
      _pTemplate = pTemplate;
   }
public:
   MO_OVERRIDE TResult SetName(FInstance* pInstance, TCharC* pName);
   MO_OVERRIDE TResult SetText(FInstance* pInstance, TCharC* pText);
public:
   MO_OVERRIDE TResult Set(FInstance* pInstance, TCharC* pName, TCharC* pValue);
   MO_OVERRIDE TResult Push(FInstance* pInstance, FInstance* pChildInstance);
   MO_OVERRIDE TResult Setup(FInstance* pInstance);
};

//============================================================
// <T>模板解析器。</T>
//============================================================
class MO_CR_DECLARE FTemplateParser : public FConfigParser
{
   MO_CLASS_DECLARE_INHERITS(FTemplateParser, FConfigParser);
public:
   FTemplateParser();
   MO_ABSTRACT ~FTemplateParser();
public:
   FTemplateSource* Load(TCharC* pSource);
};
//------------------------------------------------------------
typedef MO_CR_DECLARE FDictionary<FTemplate*> FTemplateDictionary;

//============================================================
// <T>模板控制台。</T>
//============================================================
class MO_CR_DECLARE FTemplateConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FTemplateConsole, FConsole);
protected:
   FTemplateParser* _pParser;
   FTemplateDictionary* _pTemplates;
public:
   FTemplateConsole();
   MO_ABSTRACT ~FTemplateConsole();
public:
   FTemplate* Load(FTemplateContext* pContext, TCharC* pTemplateName);
};

//============================================================
// <T>模板管理器。</T>
//
// @history 100301 MAOCY 创建
//============================================================
class MO_CR_DECLARE RTemplateManager : public RSingleton<FTemplateConsole>{
};

MO_NAMESPACE_END

#endif // __MO_CR_TEMPLATE_H__
