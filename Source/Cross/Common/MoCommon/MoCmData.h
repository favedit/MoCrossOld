#ifndef __MO_CM_DATA_H__
#define __MO_CM_DATA_H__
//------------------------------------------------------------
#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_COLLECTION_H__
#include "MoCmCollection.h"
#endif // __MO_CM_COLLECTION_H__

#ifndef __MO_CM_STRING_H__
#include "MoCmString.h"
#endif // __MO_CM_STRING_H__

#ifndef __MO_CM_DICTIONARY_H__
#include "MoCmDictionary.h"
#endif // __MO_CM_DICTIONARY_H__

#ifndef __MO_CM_LOCK_H__
#include "MoCmLock.h"
#endif // __MO_CM_LOCK_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>类定义。</T>
class ISqlConnection;
class FDatasetMeta;
class FDataset;

//============================================================
// <T>记录编号。</T>
typedef TInt64 TRecordId;
typedef FVector<TRecordId> FRecordIdVector;
// <T>SQL回调函数。</T>
typedef TInt (*HSqlConnectionCommandInvoker)(ISqlConnection* pConnection, TCharC* pSql, TInt sqlResult, TAny* pParams);

//============================================================
// <T>数据模式。</T>
//
// @history 130420 MAOCY 创建
//============================================================
enum ESqlMode{
   ESqlMode_Ansy = 0x01,
   ESqlMode_Sync = 0x02,
};

//============================================================
// <T>数据标志。</T>
//
// @history 130420 MAOCY 创建
//============================================================
enum ESqlFlag{
   ESqlFlag_Transaction  = 0x01,
   ESqlFlag_Statement    = 0x02,
   ESqlFlag_MultiExecute = 0x04,
};

//============================================================
// <T>数据操作。</T>
//
// @history 130422 MAOCY 创建
//============================================================
enum ESqlProcess{
   ESqlProcess_Unknown   = 0,
   ESqlProcess_Select    = 1,
   ESqlProcess_Find      = 2,
   ESqlProcess_Fetch     = 3,
   ESqlProcess_Insert    = 4,
   ESqlProcess_Update    = 5,
   ESqlProcess_Delete    = 6,
   ESqlProcess_Function  = 7,
   ESqlProcess_Procedure = 8,
   ESqlProcess_Process   = 9,
   ESqlProcess_Command   = 10,
   ESqlProcess_Count     = 11,
};

//============================================================
// <T>数据级别。</T>
//
// @history 130425 MAOCY 创建
//============================================================
enum ESqlLevel{
   ESqlLevel_Unknown     = 0x00,
   ESqlLevel_Delay       = 0x01,
   ESqlLevel_Immediately = 0x02,
   ESqlLevel_Check       = 0x03,
   ESqlLevel_Count       = 0x04,
};

//============================================================
// <T>数据类型。</T>
//
// @history 100109 MAOCY 创建
//============================================================
enum ESqlType{
   ESqlType_Unknown = 0x00,
   ESqlType_Boolean = 0x01,
   ESqlType_Integer = 0x02,
   ESqlType_Float   = 0x03,
   ESqlType_Date    = 0x04,
   ESqlType_String  = 0x05,
   ESqlType_Memo    = 0x06
};

//============================================================
// <T>数据结果。</T>
//
// @history 100109 MAOCY 创建
//============================================================
enum ESqlResult{
   ESqlResult_Success    =  0,
   ESqlResult_Disconnect = -1,
   ESqlResult_Execute    = -2,
};

//============================================================
// <T>异步命令字符串。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class MO_CM_DECLARE SAnsySql{
public:
   FDatasetMeta* metaPtr;
   ESqlProcess processCd;
   ESqlLevel levelCd;
   TRecordId recordId;
   TCharC* sqlPtr;
   HSqlConnectionCommandInvoker invoker;
   TAny* paramsPtr;
public:
   //------------------------------------------------------------
   // <T>构造异步命令字符串。</T>
   SAnsySql(){
      MO_CLEAR(metaPtr);
      processCd = ESqlProcess_Unknown;
      levelCd = ESqlLevel_Unknown;
      recordId = 0;
      MO_CLEAR(sqlPtr);
      MO_CLEAR(invoker);
      MO_CLEAR(paramsPtr);
   }
};

//============================================================
// <T>命令字符串。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class MO_CM_DECLARE MSql : public MString{
public:
   void AppendBoolean(TBool value);
   void AppendInteger(TInt value);
   void AppendString(TCharC* pValue, TInt length = -1);
   void AppendStringFormat(TCharC* pFormat, ...);
};
//------------------------------------------------------------
typedef FList<MSql*> FSqlList;

//============================================================
// <T>命令字符串。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class MO_CM_DECLARE FSql :
      public FObject,
      public MSql{
public:
   FSql();
   MO_ABSTRACT ~FSql();
protected:
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends, TBool force);
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TCharC* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TPtrC<TChar>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const MSql& value){
      Assign(value.MemoryC(), value.Length());
   }
};

//============================================================
// <T>命令字符串。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class MO_CM_DECLARE TSql : public MSql{
protected:
   TChar _memory[MO_FS_SQL_MAX_LENGTH];
public:
   //------------------------------------------------------------
   // <T>构造命令字符串。</T>
   TSql(){
      InnerInitialize();
   }
   //------------------------------------------------------------
   // <T>构造命令字符串。</T>
   TSql(TCharC* pValue, TInt length = -1){
      InnerInitialize();
      Assign(pValue, length);
   }
   //------------------------------------------------------------
   // <T>构造命令字符串。</T>
   TSql(const TPtrC<TChar>& ptr){
      InnerInitialize();
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>构造命令字符串。</T>
   TSql(const TSql& value){
      InnerInitialize();
      Assign(value.MemoryC(), value.Length());
   }
protected:
   //------------------------------------------------------------
   // <T>内部初始化。</T>
   MO_INLINE void InnerInitialize(){
      _pMemory = _memory;
      _capacity = MO_FS_SQL_MAX_LENGTH;
      _memory[0] = 0;
   }
   //------------------------------------------------------------
   // <T>调整内存大小。</T>
   MO_OVERRIDE void InnerResize(TInt size, TBool copy, TBool extends){
      MO_ASSERT(size <= MO_FS_SQL_MAX_LENGTH);
   }
public:
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串的内容。</T>
   MO_INLINE void operator=(TCharC* pValue){
      Assign(pValue);
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const TPtrC<TChar>& ptr){
      Assign(ptr.MemoryC(), ptr.Length());
   }
   //------------------------------------------------------------
   // <T>将当前字符串的内容设置为指定字符串对象的内容。</T>
   MO_INLINE void operator=(const MSql& value){
      Assign(value.MemoryC(), value.Length());
   }
public:
   //------------------------------------------------------------
   // <T>获得容纳长度。</T>
   MO_INLINE static TInt Size(){
      return MO_FS_SQL_MAX_LENGTH;
   }
};

//============================================================
// <T>命令字符串缓冲。</T>
//
// @history 130422 MAOCY 创建
//============================================================
class FSqlPool : public FPool<FSql*>{
protected:
   TThreadLocker _locker;
public:
   FSqlPool();
   MO_ABSTRACT ~FSqlPool();
public:
   //------------------------------------------------------------
   // <T>获得命令字符串缓冲。</T>
   MO_INLINE IThreadLocker* Locker(){
      return &_locker;
   }
public:
   FSql* Alloc();
   void Free(FSql* pSql);
};

//============================================================
// <T>数据字段。</T>
//
// @history 100108 MAOCY 创建
//============================================================
class MO_CM_DECLARE FField{
protected:
   TFsName _name;
   ESqlType _type;
public:
   FField();
   ~FField();
public:
   TCharC* Name();
   ESqlType Type();
   void SetName(TCharC* pName);
   void SetType(ESqlType type);
};

//============================================================
// <T>数据字段集合。</T>
//
// @history 100108 MAOCY 创建
//============================================================
class MO_CM_DECLARE FFields : public FDictionary<FField*>{
public:
};

//============================================================
// <T>数据行对象。</T>
//
// @history 100108 MAOCY 创建
//============================================================
class MO_CM_DECLARE FRow : public FObject{
protected:
   FDataset* _pDataset;
   FStringArray* _pValues;
public:
   FRow(FDataset* pDataset);
   MO_ABSTRACT ~FRow();
public:
   TCharC* operator[](TInt index);
   TCharC* operator[](TCharC* pName);
public:
   TCharC* Get(TInt index);
   TCharC* Get(TCharC* pName);
   void Set(TInt index, TCharC* pValue);
   void Set(TCharC* pName, TCharC* pValue);
};

//============================================================
// <T>数据集合接口。</T>
//============================================================
class MO_CM_DECLARE IDataset{
public:
   //------------------------------------------------------------
   // <T>析构数据集合接口。</T>
   MO_ABSTRACT ~IDataset(){
   }
public:
   MO_VIRTUAL TInt Count() = 0;
   MO_VIRTUAL TAny** Ptrs() = 0;
public:
   MO_VIRTUAL void ForceCount(TInt count) = 0;
};

//============================================================
// <T>数据集合信息。</T>
//============================================================
class MO_CM_DECLARE FDatasetMeta : public FObject{
protected:
   TInt _logicCode;
   TCharC* _pLogicName;
   TCharC* _pDataName;
   TCharC* _pDataFields;
   TInt64 _counters[ESqlProcess_Count];
public:
   FDatasetMeta();
   MO_ABSTRACT ~FDatasetMeta();
public:
   //------------------------------------------------------------
   // <T>获得逻辑代码。</T>
   MO_INLINE TInt LogicCode(){
      return _logicCode;
   }
   //------------------------------------------------------------
   // <T>设置逻辑代码。</T>
   MO_INLINE void SetLogicCode(TInt logicCode){
      _logicCode = logicCode;
   }
   //------------------------------------------------------------
   // <T>获得逻辑名称。</T>
   MO_INLINE TCharC* LogicName(){
      return _pLogicName;
   }
   //------------------------------------------------------------
   // <T>设置逻辑名称。</T>
   MO_INLINE void SetLogicName(TCharC* pLogicName){
      _pLogicName = pLogicName;
   }
   //------------------------------------------------------------
   // <T>获得数据名称。</T>
   MO_INLINE TCharC* DataName(){
      return _pDataName;
   }
   //------------------------------------------------------------
   // <T>设置数据名称。</T>
   MO_INLINE void SetDataName(TCharC* pDataName){
      _pDataName = pDataName;
   }
   //------------------------------------------------------------
   // <T>获得数据字段集合。</T>
   MO_INLINE TCharC* DataFields(){
      return _pDataFields;
   }
   //------------------------------------------------------------
   // <T>设置数据字段集合。</T>
   MO_INLINE void SetDataFields(TCharC* pDataFields){
      _pDataFields = pDataFields;
   }
public:
   //------------------------------------------------------------
   // <T>获得处理类型的总数。</T>
   MO_INLINE TInt64 ProcessTotal(ESqlProcess processCd){
      return _counters[processCd];
   }
   //------------------------------------------------------------
   // <T>设置处理类型的总数。</T>
   MO_INLINE void SetProcessTotal(ESqlProcess processCd, TInt64 total){
      _counters[processCd] = total;
   }
public:
   MO_ABSTRACT TBool Setup();
public:
   MO_OVERRIDE void Process(ESqlProcess processCd);
   MO_OVERRIDE void Reset();
public:
   MO_OVERRIDE void Dump(MString* pDump);
};
//------------------------------------------------------------
typedef FVector<FDatasetMeta*> FDatasetMetaVecrtor;
typedef FList<FDatasetMeta*> FDatasetMetaList;

//============================================================
// <T>数据集合对象。</T>
//
// @history 091208 MAOCY 创建
//============================================================
class MO_CM_DECLARE FDataset :
      public FList<FRow*>,
      public IDataset{
protected:
   FFields* _pFields;
public:
   FDataset();
   MO_ABSTRACT ~FDataset();
public:
   FFields* Fields();
   FRow* CreateRow();
public:
   MO_OVERRIDE TInt Count();
   MO_OVERRIDE TAny** Ptrs();
public:
   MO_OVERRIDE void ForceCount(TInt count);
};

//============================================================
// <T>数据集合。</T>
//============================================================
template <typename T>
class MO_CM_DECLARE TDataset{
public:
   typedef FList<T*> FRowList;
protected:
   TInt _count;
   FRowList* _pRows;
   T** _ppPtrs;
public:
   //------------------------------------------------------------
   // <T>构造数据集合。</T>
   TDataset(){
      _count = 0;
      _pRows = MO_CREATE(FRowList);
      MO_CLEAR(_ppPtrs);
   }
   //------------------------------------------------------------
   // <T>析构数据集合。</T>
   ~TDataset(){
      TListIteratorC<T*> iterator = _pRows->IteratorC();
      while(iterator.Next()){
         T* pRow = *iterator;
         MO_DELETE(pRow);
      }
      MO_DELETE(_pRows);
      MO_FREE(_ppPtrs);
   }
public:
   //------------------------------------------------------------
   // <T>创建一个行对象。</T>
   MO_INLINE T* CreateRow(){
      T* pRow = MO_TYPE_ALLOC(T);
      RType<T>::Clear(pRow);
      _pRows->Push(pRow);
      return pRow;
   }
public:
   //------------------------------------------------------------
   // <T>获得是否为空。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得个数。</T>
   MO_OVERRIDE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得对象。</T>
   MO_INLINE T* Get(TInt index){
      if((index >= 0) && (index < _count) && (NULL != _ppPtrs)){
         return _ppPtrs[index];
      }
   }
   //------------------------------------------------------------
   // <T>获得行指针集合。</T>
   MO_OVERRIDE TAny** Ptrs(){
      if(_count != _pRows->Count()){
         // 释放内存
         MO_FREE(_ppPtrs);
         // 收集内存
         TInt n = 0;
         TInt count = _pRows->Count();
         _ppPtrs = MO_TYPES_ALLOC(T*, count);
         TListIteratorC<T*> iterator = _pRows->IteratorC();
         while(iterator.Next()){
            _ppPtrs[n++] = *iterator;
         }
         _count = count;
      }
      return _ppPtrs;
   }
public:
   //------------------------------------------------------------
   // <T>强制行总数。</T>
   MO_OVERRIDE void ForceCount(TInt count){
      TInt loop = count - _pRows->Count();
      for(TInt n = 0; n < loop; n++){
         CreateRow();
      }
      _count = count;
   }
};

//============================================================
// <T>行填充接口。</T>
//
// @history 091208 MAOCY 创建
//============================================================
class MO_CM_DECLARE ISqlRowFieldFill{
public:
   //------------------------------------------------------------
   // <T>析构行填充接口。</T>
   MO_ABSTRACT ~ISqlRowFieldFill(){
   }
public:
   MO_VIRTUAL void Fill(TCharC* pName, TCharC* pValue) = 0;
};

//============================================================
// <T>行填充接口。</T>
//
// @history 091208 MAOCY 创建
//============================================================
class MO_CM_DECLARE ISqlRowFill{
public:
   //------------------------------------------------------------
   // <T>析构行填充接口。</T>
   MO_ABSTRACT ~ISqlRowFill(){
   }
public:
   MO_VIRTUAL void Fill(TChar** ppValues, TInt count) = 0;
};

//============================================================
// <T>行填充器接口。</T>
//
// @history 091208 MAOCY 创建
//============================================================
class MO_CM_DECLARE ISqlRowFiller{
public:
   //------------------------------------------------------------
   // <T>析构行填充器接口。</T>
   MO_ABSTRACT ~ISqlRowFiller(){
   }
public:
   MO_VIRTUAL void Fill(TAny* pRow, TChar** ppValues, TInt count) = 0;
};

//============================================================
// <T>数据链接接口。</T>
//
// @history 091208 MAOCY 创建
//============================================================
class MO_CM_DECLARE ISqlConnection{
public:
   //------------------------------------------------------------
   // <T>构造数据链接接口。</T>
   MO_ABSTRACT ~ISqlConnection(){
   }
public:
   MO_VIRTUAL TInt Id() = 0;
   MO_VIRTUAL TInt Type() = 0;
   MO_VIRTUAL TCharC* Host() = 0;
   MO_VIRTUAL TUint16 Port() = 0;
   MO_VIRTUAL TCharC* UserName() = 0;
   MO_VIRTUAL TCharC* Password() = 0;
   MO_VIRTUAL TCharC* Database() = 0;
   MO_VIRTUAL TCharC* Charset() = 0;
   MO_VIRTUAL ESqlMode ModeCd() = 0;
   MO_VIRTUAL void SetModeCd(ESqlMode modeCd) = 0;
   MO_VIRTUAL TBool IsSupport(ESqlFlag flagCd) = 0;
   MO_VIRTUAL TInt Flag() = 0;
   MO_VIRTUAL TBool IsConnected() = 0;
public:
   MO_VIRTUAL TInt64 CounterTotal() = 0;
   MO_VIRTUAL TInt64 CounterGet(ESqlProcess processCd) = 0;
   MO_VIRTUAL void CounterSet(ESqlProcess processCd, TInt64 total) = 0;
   MO_VIRTUAL void CounterProcess(ESqlProcess processCd) = 0;
   MO_VIRTUAL void CounterReset() = 0;
public:
   MO_VIRTUAL TBool Connect(TCharC* pHost = NULL, TInt port = 0, TCharC* pUserName = NULL, TCharC* pPassword = NULL, TCharC* pDatabase = NULL) = 0;
   MO_VIRTUAL TBool Reconnect() = 0;
   MO_VIRTUAL TBool Begin(TBool flag = ETrue) = 0;
   MO_VIRTUAL TBool Commit() = 0;
   MO_VIRTUAL TBool Rollback() = 0;
   MO_VIRTUAL TBool Close() = 0;
public:
   MO_VIRTUAL TInt ExecuteInsertSql(TCharC* pSql, TRecordId& recordId) = 0;
   MO_VIRTUAL TInt ExecuteSql(TCharC* pSql, ESqlProcess processCd = ESqlProcess_Unknown) = 0;
   MO_VIRTUAL TBool ExecuteAnsySql(SAnsySql* pSql) = 0;
   MO_VIRTUAL TBool ExecuteExist(TCharC* pSql) = 0;
   MO_VIRTUAL TBool ExecuteInteger(TCharC* pSql, TInt64& value) = 0;
   MO_VIRTUAL TBool ExecuteString(TCharC* pSql, TChar* pValue, TInt capacity) = 0;
public:
   MO_VIRTUAL TBool Find(TCharC* pSql, ISqlRowFill* piFill) = 0;
   MO_VIRTUAL TBool Find(TCharC* pSql, ISqlRowFieldFill* piFieldFill) = 0;
   MO_VIRTUAL TBool Find(TCharC* pSql, ISqlRowFiller* piFiller, TAny* pRow) = 0;
   MO_VIRTUAL TInt Fetch(TCharC* pSql, ISqlRowFill** ppiFills, TInt count) = 0;
   MO_VIRTUAL TInt Fetch(TCharC* pSql, ISqlRowFiller* piFiller, TAny** ppRows, TInt count) = 0;
   MO_VIRTUAL TInt Fetch(TCharC* pSql, ISqlRowFiller* piFiller, IDataset* pDataset) = 0;
public:
   MO_VIRTUAL IThreadLocker* Locker() = 0;
   MO_VIRTUAL TBool TestTimeout(TDateTime current) = 0;
   MO_VIRTUAL TBool Refresh() = 0;
   MO_VIRTUAL TBool Free() = 0;
};
//------------------------------------------------------------
typedef FVector<ISqlConnection*> FSqlConnectionVector;
typedef FList<ISqlConnection*> FSqlConnectionList;

//============================================================
// <T>数据库链接。</T>
//============================================================
class MO_CM_DECLARE FSqlConnection :
      public FObject,
      public ISqlConnection{
protected:
   TInt _id;
   TInt _type;
   TFsName _host;
   TUint16 _port;
   TFsName _username;
   TFsName _password;
   TFsName _database;
   TFsName _charset;
   ESqlMode _modeCd;
   TInt _flag;
protected:
   TBool _initialized;
   TBool _connected;
   TBool _errored;
   TBool _supportTransaction;
   TBool _inTransaction;
   TBool _dirtyTransaction;
   TTimeSpan _freeTimeout;
   TDateTime _freeDate;
   TInt64 _counters[ESqlProcess_Count];
   TThreadLocker _locker;
public:
   FSqlConnection();
   MO_ABSTRACT ~FSqlConnection();
public:
   MO_OVERRIDE TInt Id();
   MO_OVERRIDE TInt Type();
   MO_OVERRIDE TCharC* Host();
   MO_OVERRIDE TUint16 Port();
   MO_OVERRIDE TCharC* UserName();
   MO_OVERRIDE TCharC* Password();
   MO_OVERRIDE TCharC* Database();
   MO_OVERRIDE TCharC* Charset();
   MO_OVERRIDE ESqlMode ModeCd();
   MO_OVERRIDE void SetModeCd(ESqlMode modeCd);
   MO_OVERRIDE TBool IsSupport(ESqlFlag flagCd);
   MO_OVERRIDE TInt Flag();
   MO_OVERRIDE TBool IsConnected();
public:
   //------------------------------------------------------------
   void SetId(TInt id);
   void SetType(TInt type);
   void SetHost(TCharC* pHost);
   void SetHostPort(TInt port);
   void SetUserName(TCharC* pUserName);
   void SetPassword(TCharC* pPassword);
   void SetDatabase(TCharC* pDatabase);
   void SetCharset(TCharC* pCharset);
   void SetFlag(TInt flag);
public:
   MO_OVERRIDE TInt64 CounterTotal();
   MO_OVERRIDE TInt64 CounterGet(ESqlProcess processCd);
   MO_OVERRIDE void CounterSet(ESqlProcess processCd, TInt64 total);
   MO_OVERRIDE void CounterProcess(ESqlProcess processCd);
   MO_OVERRIDE void CounterReset();
public:
   MO_OVERRIDE IThreadLocker* Locker();
   MO_OVERRIDE TBool TestTimeout(TDateTime current);
   MO_OVERRIDE TBool Refresh();
   MO_OVERRIDE TBool Free();
};

MO_NAMESPACE_END

#endif // __MO_CM_MEMORY_H__
