#ifndef __MO_FR_CONTENT_PIPELINE_H__
#define __MO_FR_CONTENT_PIPELINE_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT_H__
#include "MoFrContent.h"
#endif // __MO_FR_CONTENT_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>内容管道。</T>
//============================================================
class MO_FR_DECLARE FContentPipeline : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FContent, FInstance);
public:
   FContentPipeline();
   MO_ABSTRACT ~FContentPipeline();
public:
   MO_ABSTRACT TResult Process(FContentLoader* pLoader);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtr<FContentPipeline> GContentPipelinePtr;

//============================================================
// <T>内容资源管道。</T>
//============================================================
class MO_FR_DECLARE FContentAssetPipeline : public FContentPipeline
{
   MO_CLASS_DECLARE_INHERITS(FContentAssetPipeline, FContentPipeline);
public:
   FContentAssetPipeline();
   MO_ABSTRACT ~FContentAssetPipeline();
public:
   MO_ABSTRACT TResult Process(FContentLoader* pLoader);
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT_PIPELINE_H__
