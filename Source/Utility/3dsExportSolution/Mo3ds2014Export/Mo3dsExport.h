#pragma once

#include <Max.h>
#include <MoCommon.h>

MO_NAMESPACE_INCLUDE;

//============================================================
// <T>导出类描述。</T>
//============================================================
class T3dsClassDesc : public ClassDesc {
public:
   TInt32 IsPublic();
   SClass_ID SuperClassID();
   Class_ID ClassID();
   TCharC* Category();
   TCharC* ClassName();
   TAny* Create(TInt32 loading = EFalse);
};
//------------------------------------------------------------
static T3dsClassDesc g_classDesc;

//============================================================
// <T>场景导出器。</T>
//============================================================
class F3dsSceneExport : public SceneExport{
public:
   PPtr Construct();
   void Dispose();
public:
   TInt32 ExtCount();
   TCharC* Ext(TInt32 index);
   TCharC* ShortDesc();
   TCharC* LongDesc();
   TCharC* AuthorName();
   TCharC* CopyrightMessage();
   TCharC* OtherMessage1();
   TCharC* OtherMessage2();
   TUint32 Version();
public:
   TInt32 DoExport(TCharC* pFileName, ExpInterface* piExpInterface, Interface* piInterface, TInt32 suppressPrompts = EFalse, TUlong options = 0);
   TBool SupportsOptions(TInt ext, TUint32 options);
   void ShowAbout(HWND hWnd);
};
