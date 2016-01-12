#ifndef __MO_CM_STRING_H__
#define __MO_CM_STRING_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_STRING8_H__
#include "MoCmString8.h"
#endif // __MO_CM_STRING8_H__

#ifndef __MO_CM_STRING16_H__
#include "MoCmString16.h"
#endif // __MO_CM_STRING16_H__

#ifndef __MO_CM_STRING32_H__
#include "MoCmString32.h"
#endif // __MO_CM_STRING32_H__

#define MO_BOOL_TRUE_CHAR     'Y'
#define MO_BOOL_TRUE_STRING   TC("Y")
#define MO_BOOL_TRUE_DISPLAY  TC("True")
#define MO_BOOL_FALSE_CHAR    'N'
#define MO_BOOL_FALSE_STRING  TC("N")
#define MO_BOOL_FALSE_DISPLAY TC("False")

MO_NAMESPACE_BEGIN

//============================================================
// <T>字符串定义。</T>
//============================================================
#ifndef _UNICODE
#define TStringPtrC       TString8PtrC
#define TStringPtr        TString8Ptr
#define MString           MString8
#define TStringRefer      TString8Refer
#define TString           TString8
#define FString           FString8
#define TStringBuffer     TStringBuffer8
#define FStringBuffer     FStringBuffer8
#define TFixString        TFixString8
#define FStrings          FString8s
#define FProperties       FProperties8
#define TFixStringToken   TFixStringToken8
#define TFsStringToken    TFsStringToken8
#define MO_BCD_TFIXSTRING MO_BCD_TFIXSTRING8
#else
#ifdef _MO_WINDOWS
#define TStringPtrC       TString16PtrC
#define TStringPtr        TString16Ptr
#define MString           MString16
#define TStringRefer      TString16Refer
#define TString           TString16
#define FString           FString16
#define TStringBuffer     TStringBuffer16
#define FStringBuffer     FStringBuffer16
#define TFixString        TFixString16
#define FStrings          FString16s
#define FProperties       FProperties16
#define TFixStringToken   TFixStringToken16
#define TFsStringToken    TFsStringToken16
#define MO_BCD_TFIXSTRING MO_BCD_TFIXSTRING16
#else
#define TStringPtrC       TString32PtrC
#define TStringPtr        TString32Ptr
#define MString           MString32
#define TStringRefer      TString32Refer
#define TString           TString32
#define FString           FString32
#define TStringBuffer     TStringBuffer32
#define FStringBuffer     FStringBuffer32
#define TFixString        TFixString32
#define FStrings          FString32s
#define FProperties       FProperties32
#define TFixStringToken   TFixStringToken32
#define TFsStringToken    TFsStringToken32
#define MO_BCD_TFIXSTRING MO_BCD_TFIXSTRING32
#endif // _MO_WINDOWS
#endif // _UNICODE
//------------------------------------------------------------
typedef TChar8          TAnsiChar;
typedef TChar8C         TAnsiCharC;
typedef MString8        MAnsiString;
typedef TString8Refer   TAnsiStringRefer;
typedef TString8        TAnsiString;
typedef FString8        FAnsiString;
typedef TStringBuffer8  TAnsiStringBuffer;
typedef FStringBuffer8  FAnsiStringBuffer;
#define TAnsiFixString  TAnsiFixString8
#ifdef _MO_WINDOWS
typedef TChar16         TWideChar;
typedef TChar16C        TWideCharC;
typedef MString16       MWideString;
typedef TString16Refer  TWideStringRefer;
typedef TString16       TWideString;
typedef FString16       FWideString;
typedef TStringBuffer16 TWideStringBuffer;
typedef FStringBuffer16 FWideStringBuffer;
#define TWideFixString  TFixString16
#else
typedef TChar32         TWideChar;
typedef TChar32C        TWideCharC;
typedef MString32       MWideString;
typedef TString32Refer  TWideStringRefer;
typedef TString32       TWideString;
typedef FString32       FWideString;
typedef TStringBuffer32 TWideStringBuffer;
typedef FStringBuffer32 FWideStringBuffer;
#define TWideFixString  TFixString32
#endif // _MO_WINDOWS
//------------------------------------------------------------
typedef MO_CM_DECLARE FArray<FString*> FStringArray;
typedef MO_CM_DECLARE FVector<FString*> FStringVector;
typedef MO_CM_DECLARE FList<FString*> FStringList;

//============================================================
// <T>定长字符串。</T>
//============================================================
#ifndef _UNICODE
#define TFsNumber   TFsNumber8
#define TFsTimeTick TFsTimeTick8
#define TFsCode     TFsCode8
#define TFsName     TFsName8
#define TFsLabel    TFsLabel8
#define TFsFileName TFsFileName8
#define TFsText     TFsText8
#define TFsCommand  TFsCommand8
#define TFsPath     TFsPath8
#define TFsNote     TFsNote8
#define TFsDump     TFsDump8
#define TFsSql      TFsSql8
#define TFsLogger   TFsLogger8
#define TFsTrack    TFsTrack8
#else
#ifdef _MO_WINDOWS
#define TFsNumber   TFsNumber16
#define TFsTimeTick TFsTimeTick16
#define TFsCode     TFsCode16
#define TFsName     TFsName16
#define TFsLabel    TFsLabel16
#define TFsFileName TFsFileName16
#define TFsText     TFsText16
#define TFsCommand  TFsCommand16
#define TFsPath     TFsPath16
#define TFsNote     TFsNote16
#define TFsDump     TFsDump16
#define TFsSql      TFsSql16
#define TFsLogger   TFsLogger16
#define TFsTrack    TFsTrack16
#else
#define TFsNumber   TFsNumber32
#define TFsTimeTick TFsTimeTick32
#define TFsCode     TFsCode32
#define TFsName     TFsName32
#define TFsLabel    TFsLabel32
#define TFsFileName TFsFileName32
#define TFsText     TFsText32
#define TFsCommand  TFsCommand32
#define TFsPath     TFsPath32
#define TFsNote     TFsNote32
#define TFsDump     TFsDump32
#define TFsSql      TFsSql32
#define TFsLogger   TFsLogger32
#define TFsTrack    TFsTrack32
#endif // _MO_WINDOWS
#endif // _UNICODE

//------------------------------------------------------------
#if _UNICODE
typedef MO_CM_DECLARE RChar16 RChar;
typedef MO_CM_DECLARE RString16 RString;
#else
typedef MO_CM_DECLARE RChar8 RChar;
typedef MO_CM_DECLARE RString8 RString;
#endif // _UNICODE

typedef RChar8    RAnsiChar;
typedef RString8  RAnsiString;
#if _MO_WINDOWS
typedef RChar16   RWideChar;
typedef RString16 RWideString;
#else
typedef RChar32   RWideChar;
typedef RString32 RWideString;
#endif // _MO_WINDOWS

//============================================================
// <T>字符编码方式。</T>
//============================================================
enum TCharEncoding{
   ECharSet_Gb2312,
   ECharSet_Gbk,
   ECharSet_Gb18030,
};

//============================================================
// <T>字符编码管理。</T>
//============================================================
class MO_CM_DECLARE RCharEncoding{
public:
   static TCharC* Gb2312;
   static TCharC* Gbk;
   static TCharC* Gb18030;
public:
   static TCharC* Default;
};

//============================================================
// <T>字符集。</T>
//============================================================
enum TCharSet{
   ECharSet_Utf8,
   ECharSet_Utf16,
   ECharSet_Ut32,
};

//============================================================
// <T>字符集管理。</T>
//============================================================
class MO_CM_DECLARE RCharSet{
public:
   static TCharC* Utf8;
   static TCharC* Utf16;
   static TCharC* Utf32;
};

MO_NAMESPACE_END

#endif // __MO_CM_STRING_H__
