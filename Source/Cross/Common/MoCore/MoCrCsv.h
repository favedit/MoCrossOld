#ifndef __MO_CR_CSV_H__
#define __MO_CR_CSV_H__

#ifndef __MO_CM_STREAM_H__
#include <MoCmStream.h>
#endif // __MO_CM_STREAM_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>CSV文件段头部项。</T>
//
// @history 100805 MZYNG 创建
//============================================================
class MO_CR_DECLARE FCsvHead : public FObject{
protected:
   TInt _index;
   TFsName _name;
   TFsLabel _label;
   TFsNote _description;
public:
   FCsvHead();
   MO_ABSTRACT( ~FCsvHead() );
public:
   TInt Index();
   TCharC* Name();
   TCharC* Label();
   TCharC* Description();
   void SetIndex(TInt index);
   void SetName(TCharC* name);
   void SetLabel(TCharC* label);
   void SetDescription(TCharC* description);
};

//------------------------------------------------------------
typedef FDictionary<FCsvHead*> FCsvHeadDictionary;

//============================================================
// <T>CSV文件段头部。</T>
//
// @history 100805 MZYNG 创建
//============================================================
class MO_CR_DECLARE FCsvHeads : public FObject{
protected:
   FCsvHeadDictionary* _pHeads;
public:
   FCsvHeads();
   MO_ABSTRACT( ~FCsvHeads() );
public:
   TInt IndexOf(TCharC* pHeadName);
   FCsvHead* Insert(TCharC* pName);
public:
   void SaveToFile(TDataOutput& out);
   void Parse(TCharC* pValue);
};

//============================================================
// <T>CSV文件的一行数据。</T>
//
// @history 100805 MZYNG 创建
//============================================================
class MO_CR_DECLARE FCsvLine : public FObject{
public:
   friend class FCsvLines;
protected:
   FCsvHeads* _pHeads;
   FStrings* _pCells;
   TBool WrapedByRefer(TCharC* pValue);
   void SinglePairRefer(TCharC* pValue, TString& result);
public:
   FCsvLine();
   FCsvLine(FCsvHeads* pHeads);
   MO_ABSTRACT( ~FCsvLine() );
protected:
   void Store(TDataOutput& out);
   void Parse(TCharC* pLine);
public:
   TInt Count();
   void SetHeads(FCsvHeads* pHeads);
public:
   TCharC* At(TInt index);
   TCharC* At(TInt index, TCharC* pValue);
   TBool AtBoolean(TInt index);
   TBool AtBoolean(TInt index, TBool value);
   TInt AtInt(TInt index);
   TInt AtInt(TInt index, TInt value);
   TFloat AtFloat(TInt index);
   TFloat AtFloat(TInt index, TFloat value);
public:
   TCharC* Get(TCharC* pName);
   TCharC* Get(TCharC* pName, TCharC* pValue);
   TBool GetBoolean(TCharC* pName);
   TBool GetBoolean(TCharC* pName, TBool value);
   TInt GetInt(TCharC* pNameindex);
   TInt GetInt(TCharC* pName, TInt value);
   TFloat GetFloat(TCharC* pName);
   TFloat GetFloat(TCharC* pName, TFloat value);
public:
   void Set(TInt index, TCharC* pValue);
   void SetBoolean(TInt index, TBool value);
   void SetInt(TInt index, TInt value);
   void SetFloat(TInt index, TFloat value);
public:
   void Put(TCharC* pName, TCharC* pValue);
   void PutBoolean(TCharC* pName, TBool value);
   void PutInt(TCharC* pName, TInt value);
   void PutFloat(TCharC* pName, TFloat value);
public:
   void Push(TCharC* pValue);
   void PushBoolean(TBool value);
   void PushInt(TInt value);
   void PushFloat(TFloat value);
};
//------------------------------------------------------------
typedef FVector<FCsvLine*> FCsvLineVector;

//============================================================
// <T>CSV文件段的行数据。</T>
//
// @history 100805 MZYNG 创建
//============================================================
class MO_CR_DECLARE FCsvLines : public FObject{
public:
   friend class FCsvSegment;
   friend class FCsvFile;
protected:
   FCsvHeads* _pHeads;
   FCsvLineVector* _pLines;
protected:
   void Store(TDataOutput& out);
   void Parse(TCharC* pValue);
public:
   FCsvLines(FCsvHeads* pHeads);
   MO_ABSTRACT( ~FCsvLines() );
public:
   TInt Count();
   FCsvLine* Get(TInt index);
public:
   FCsvLine* Insert(TInt index = -1);

};

//============================================================
// <T>CSV文件段尾部项。</T>
//
// @history 100805 MZYNG 创建
//============================================================
class MO_CR_DECLARE FCsvFooter : public FObject{
protected:
   TInt    _index;
   TFsName _name;
   TFsLabel _label;
   TFsNote _description;
public:
   FCsvFooter();
   MO_ABSTRACT( ~FCsvFooter() );
public:
   TInt Index();
   TCharC* Name();
   TCharC* Label();
   TCharC* Description();
   void SetIndex(TInt index);
   void SetName(TCharC* name);
   void SetLabel(TCharC* label);
   void SetDescription(TCharC* description);
};
//------------------------------------------------------------
typedef FDictionary<FCsvFooter*> FCsvFooterDictionary;

//============================================================
// <T>CSV文件段尾部。</T>
//
// @history 100805 MZYNG 创建
//============================================================
class MO_CR_DECLARE FCsvFooters : public FObject{
public:
   friend class FCsvSegment;
   friend class FCsvFile;
protected:
   FCsvFooterDictionary* _pFooters;
public:
   FCsvFooters();
   MO_ABSTRACT( ~FCsvFooters() );
protected:
   void Store(TDataOutput& out);
   void Parse(TCharC* pValue);
public:
   TInt IndexOf(TCharC* pName);
   FCsvFooter* Insert(TCharC* pName, TInt index = -1);
};

//============================================================
class MO_CR_DECLARE FCsvSegment : public FObject{
public:
   friend class FCsvFile;
protected:
   TFsName _name;
   FCsvHeads* _pHeads;
   FCsvLines* _pLines;
   FCsvFooters* _pFooters;
public:
   FCsvSegment();
   MO_ABSTRACT( ~FCsvSegment() );
protected:
   void SetName(TCharC* pName);
   void Store(TDataOutput& out, TBool saveHead);
public:
   TCharC* Name();
public:
   FCsvHeads* Heads();
   FCsvLines* Lines();
   FCsvFooters* Footers();
public:
   FCsvHead* InsertHead(TCharC* pName);
   FCsvLine* InsertLine();
   FCsvFooter* InsertFooter(TCharC* pName);
};
//------------------------------------------------------------
typedef FDictionary<FCsvSegment*> FCsvSegmentDictionary;

//============================================================
class MO_CR_DECLARE FCsvFile : public FObject{
protected:
   TFsFileName _filename;
   FCsvSegment* _pDefaultSegment;
   FCsvSegment* _pCurrentSegment;
   FCsvSegmentDictionary* _pSegments;
public:
   FCsvFile();
   MO_ABSTRACT( ~FCsvFile() );
public:
   FCsvSegment* DefaultSegment();
   FCsvSegment* CurrentSegment();
   FCsvSegment* AddSegment(TCharC* pName);
   void SelectSegment(TCharC* pName);
public:
   void Clear();
   TBool LoadFile(TCharC* pFileName);
   TBool SaveFile(TCharC* pFileName);
   TBool StoreFile();
};

MO_NAMESPACE_END

#endif // __MO_CR_CSV_H__
