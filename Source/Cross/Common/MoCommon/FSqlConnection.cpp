#include "MoCmData.h"
#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造数据库链接。</T>
//============================================================
FSqlConnection::FSqlConnection(){
   _id = 0;
   _type = 0;
   _port = 0;
   _modeCd = ESqlMode_Sync;
   _flag = 0;
   _initialized = EFalse;
   _connected = EFalse;
   _errored = EFalse;
   _supportTransaction = EFalse;
   _inTransaction = EFalse;
   _dirtyTransaction = EFalse;
#ifdef _MO_DEBUG
   _freeTimeout = 300000000LL; // 5分钟
#else
   _freeTimeout = 3600000000LL; // 60分钟
#endif // _MO_DEBUG
   _freeDate = 0;
   CounterReset();
}

//============================================================
// <T>析构数据库链接。</T>
//============================================================
FSqlConnection::~FSqlConnection(){
}

//============================================================
// <T>获得链接数据库编号。</T>
//
// @return 编号
//============================================================
TInt FSqlConnection::Id(){
   return _id;
}

//============================================================
// <T>获得链接数据库类型。</T>
//
// @return 类型
//============================================================
TInt FSqlConnection::Type(){
   return _type;
}

//============================================================
// <T>获得主机数据指针。</T>
//
// @return 主机数据指针
//============================================================
TCharC* FSqlConnection::Host(){
   return _host;
}

//============================================================
// <T>获得数据库端口。</T>
//
// @return 数据库端口
//============================================================
TUint16 FSqlConnection::Port(){
   return _port;
}

//============================================================
// <T>获得用户名。</T>
//
// @return 用户名指针
//============================================================
TCharC* FSqlConnection::UserName(){
   return _username;
}

//============================================================
// <T>获得密码。</T>
//
// @return 密码
//============================================================
TCharC* FSqlConnection::Password(){
   return _password;
}

//============================================================
// <T>获得数据库。</T>
//
// @return 数据库
//============================================================
TCharC* FSqlConnection::Database(){
   return _database;
}

//============================================================
// <T>获得字符集。</T>
//
// @return 字符集
//============================================================
TCharC* FSqlConnection::Charset(){
   return _charset;
}

//============================================================
// <T>获得模式类型。</T>
//
// @return 模式类型
//============================================================
ESqlMode FSqlConnection::ModeCd(){
   return _modeCd;
}

//============================================================
// <T>设置模式类型。</T>
//
// @param modeCd 模式类型
//============================================================
void FSqlConnection::SetModeCd(ESqlMode modeCd){
   _modeCd = modeCd;
}

//============================================================
// <T>判断是否支持标志。</T>
//
// @return 是否支持
//============================================================
TBool FSqlConnection::IsSupport(ESqlFlag flagCd){
   return (_flag & flagCd);
}

//============================================================
// <T>获得标志。</T>
//
// @return 标志
//============================================================
TInt FSqlConnection::Flag(){
   return _flag;
}

//============================================================
// <T>获得是否链接。</T>
//
// @return 是否链接
//============================================================
TBool FSqlConnection::IsConnected(){
   return _connected;
}

//============================================================
// <T>设置编号。</T>
//
// @param id 编号
//============================================================
void FSqlConnection::SetId(TInt id){
   _id = id;
}

//============================================================
// <T>设置类型。</T>
//
// @param type 类型
//============================================================
void FSqlConnection::SetType(TInt type){
   _type = type;
}

//============================================================
// <T>设置主机。</T>
//
// @param pHost 主机
//============================================================
void FSqlConnection::SetHost(TCharC* pHost){
   _host = pHost;
}

//============================================================
// <T>设置主机端口。</T>
//
// @param port 端口号
//============================================================
void FSqlConnection::SetHostPort(TInt port){
   _port = port;
}

//============================================================
// <T>设置用户名。</T>
//
// @param pUserName 用户名
//============================================================
void FSqlConnection::SetUserName(TCharC* pUserName){
   _username = pUserName;
}

//============================================================
// <T>设置密码。</T>
//
// @param pPassword 密码
//============================================================
void FSqlConnection::SetPassword(TCharC* pPassword){
   _password = pPassword;
}

//============================================================
// <T>设置数据库。</T>
//
// @param pDatabase 数据库
//============================================================
void FSqlConnection::SetDatabase(TCharC* pDatabase){
   _database = pDatabase;
}

//============================================================
// <T>设置字符集。</T>
//
// @param pCharset 字符集
//============================================================
void FSqlConnection::SetCharset(TCharC* pCharset){
   _charset = pCharset;
}

//============================================================
// <T>设置标志。</T>
//
// @param flag 标志
//============================================================
void FSqlConnection::SetFlag(TInt flag){
   _flag = flag;
}

//============================================================
// <T>获得处理类型的计数器总数。</T>
//
// @return 计数器总数
//============================================================
TInt64 FSqlConnection::CounterTotal(){
   TInt64 total = 0;
   for(TInt n = 0; n < ESqlProcess_Count; n++){
      total += _counters[n];
   }
   return total;
}

//============================================================
// <T>获得处理类型的计数器。</T>
//
// @param processCd 处理类型
// @return 计数器
//============================================================
TInt64 FSqlConnection::CounterGet(ESqlProcess processCd){
   return _counters[processCd];
}

//============================================================
// <T>设置处理类型的计数器。</T>
//
// @param processCd 处理类型
// @param counter 计数器
//============================================================
void FSqlConnection::CounterSet(ESqlProcess processCd, TInt64 counter){
   _counters[processCd] = counter;
}

//============================================================
// <T>计数器逻辑处理。</T>
//
// @param processCd 处理类型
//============================================================
void FSqlConnection::CounterProcess(ESqlProcess processCd){
   _counters[processCd]++;
}

//============================================================
// <T>计数器重置处理。</T>
//============================================================
void FSqlConnection::CounterReset(){
   for(TInt n = 0; n < ESqlProcess_Count; n++){
      _counters[n] = 0;
   }
}

//============================================================
// <T>获得线程锁。</T>
//
// @return 线程锁
//============================================================
IThreadLocker* FSqlConnection::Locker(){
   return &_locker;
}

//============================================================
// <T>测试是否超时。</T>
//
// @param current 当前时间
// @return 是否超时
//============================================================
TBool FSqlConnection::TestTimeout(TDateTime current){
   // 检查释放时间
   if(0 == _freeDate){
      return EFalse;
   }
   // 检查释放间隔
   TTimeSpan span = current - _freeDate;
   if(span < _freeTimeout){
      return EFalse;
   }
   // 释放可
   return ETrue;
}

//============================================================
// <T>刷新处理。</T>
//
// @return 处理结果
//============================================================
TBool FSqlConnection::Refresh(){
   return ETrue;
}

//============================================================
// <T>释放处理。</T>
//
// @return 处理结果
//============================================================
TBool FSqlConnection::Free(){
   _freeDate = RDateTime::Current();
   return ETrue;
}

MO_NAMESPACE_END
