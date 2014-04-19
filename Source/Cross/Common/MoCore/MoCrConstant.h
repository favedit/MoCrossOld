//============================================================
// <T>系统核心枚举定义。</T>
//
// @version 1.0.1
// @auto 代码器管理的代码，如需修改请在手动修改标出的地方修改。
//============================================================
#ifndef __MO_CR_CONSTANT_H__
#define __MO_CR_CONSTANT_H__

#ifndef __MO_CR_COMMON_H__
#include "MoCrCommon.h"
#endif // __MO_CR_COMMON_H__

MO_NAMESPACE_BEGIN

// 模版标签最大长度定义
const TUint32 MO_CR_TPL_NAME_MAXLEN = 64;

// 模版标签最大长度定义
const TUint32 MO_CR_TPL_LABEL_MAXLEN = 64;

// 模版内容最大长度定义
const TUint32 MO_CR_TPL_NOTE_MAXLEN = 1024;

// 内存对象处理最大个数定义
const TUint32 MO_CR_MEMORY_OBJECT_PROCESS_MAXCNT = 16;

//============================================================
// <T>网络包协议枚举定义。</T>
//
// @enum
//============================================================
enum ENetProtocol{
   ENetProtocol_Unknown        = 0x00, // 位置
   ENetProtocol_Data           = 0x01, // 数据
   ENetProtocol_Message        = 0x03, // 消息
   ENetProtocol_Router         = 0x07, // 路由
   ENetProtocol_Transfer       = 0x0F, // 传输
   ENetProtocol_Package        = 0x1F, // 数据包
};

//============================================================
// <T>网络包协议枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetProtocol{
public:
   static ENetProtocol Parse(TCharC* pValue);
   static ENetProtocol ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetProtocol value);
   static TCharC* ToString(ENetProtocol value);
};

//============================================================
// <T>网络包标志枚举定义。</T>
//
// @enum
//============================================================
enum ENetSign{
   ENetSign_Unknown        = 0, // 未知
   ENetSign_Valid          = 1, // 有效
   ENetSign_Client         = 2, // 客户
   ENetSign_Debug          = 3, // 调试
   ENetSign_Analysis       = 4, // 分析
   ENetSign_Track          = 5, // 跟踪
};

//============================================================
// <T>网络包标志枚举类。</T>
//
// @class
//============================================================
struct MO_CR_DECLARE TNetSignSet : TBitSet<TUint32>{
public:
   //------------------------------------------------------------
   // <T>获得未知。</T>
   MO_INLINE TBool IsUnknown(){
      return GetBit(ENetSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>获得未知。</T>
   MO_INLINE TBool IsNotUnknown(){
      return !GetBit(ENetSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>设置未知。</T>
   MO_INLINE void UnknownSet(){
      return SetBit(ENetSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>反转未知。</T>
   MO_INLINE void UnknownInverse(){
      InverseBit(ENetSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>修改未知。</T>
   MO_INLINE void UnknownModify(TBool flag){
      ModifyBit(ENetSign_Unknown, flag);
   }
   //------------------------------------------------------------
   // <T>清除未知。</T>
   MO_INLINE void UnknownClear(){
      ClearBit(ENetSign_Unknown);
   }
public:
   //------------------------------------------------------------
   // <T>获得有效。</T>
   MO_INLINE TBool IsValid(){
      return GetBit(ENetSign_Valid);
   }
   //------------------------------------------------------------
   // <T>获得有效。</T>
   MO_INLINE TBool IsNotValid(){
      return !GetBit(ENetSign_Valid);
   }
   //------------------------------------------------------------
   // <T>设置有效。</T>
   MO_INLINE void ValidSet(){
      return SetBit(ENetSign_Valid);
   }
   //------------------------------------------------------------
   // <T>反转有效。</T>
   MO_INLINE void ValidInverse(){
      InverseBit(ENetSign_Valid);
   }
   //------------------------------------------------------------
   // <T>修改有效。</T>
   MO_INLINE void ValidModify(TBool flag){
      ModifyBit(ENetSign_Valid, flag);
   }
   //------------------------------------------------------------
   // <T>清除有效。</T>
   MO_INLINE void ValidClear(){
      ClearBit(ENetSign_Valid);
   }
public:
   //------------------------------------------------------------
   // <T>获得客户。</T>
   MO_INLINE TBool IsClient(){
      return GetBit(ENetSign_Client);
   }
   //------------------------------------------------------------
   // <T>获得客户。</T>
   MO_INLINE TBool IsNotClient(){
      return !GetBit(ENetSign_Client);
   }
   //------------------------------------------------------------
   // <T>设置客户。</T>
   MO_INLINE void ClientSet(){
      return SetBit(ENetSign_Client);
   }
   //------------------------------------------------------------
   // <T>反转客户。</T>
   MO_INLINE void ClientInverse(){
      InverseBit(ENetSign_Client);
   }
   //------------------------------------------------------------
   // <T>修改客户。</T>
   MO_INLINE void ClientModify(TBool flag){
      ModifyBit(ENetSign_Client, flag);
   }
   //------------------------------------------------------------
   // <T>清除客户。</T>
   MO_INLINE void ClientClear(){
      ClearBit(ENetSign_Client);
   }
public:
   //------------------------------------------------------------
   // <T>获得调试。</T>
   MO_INLINE TBool IsDebug(){
      return GetBit(ENetSign_Debug);
   }
   //------------------------------------------------------------
   // <T>获得调试。</T>
   MO_INLINE TBool IsNotDebug(){
      return !GetBit(ENetSign_Debug);
   }
   //------------------------------------------------------------
   // <T>设置调试。</T>
   MO_INLINE void DebugSet(){
      return SetBit(ENetSign_Debug);
   }
   //------------------------------------------------------------
   // <T>反转调试。</T>
   MO_INLINE void DebugInverse(){
      InverseBit(ENetSign_Debug);
   }
   //------------------------------------------------------------
   // <T>修改调试。</T>
   MO_INLINE void DebugModify(TBool flag){
      ModifyBit(ENetSign_Debug, flag);
   }
   //------------------------------------------------------------
   // <T>清除调试。</T>
   MO_INLINE void DebugClear(){
      ClearBit(ENetSign_Debug);
   }
public:
   //------------------------------------------------------------
   // <T>获得分析。</T>
   MO_INLINE TBool IsAnalysis(){
      return GetBit(ENetSign_Analysis);
   }
   //------------------------------------------------------------
   // <T>获得分析。</T>
   MO_INLINE TBool IsNotAnalysis(){
      return !GetBit(ENetSign_Analysis);
   }
   //------------------------------------------------------------
   // <T>设置分析。</T>
   MO_INLINE void AnalysisSet(){
      return SetBit(ENetSign_Analysis);
   }
   //------------------------------------------------------------
   // <T>反转分析。</T>
   MO_INLINE void AnalysisInverse(){
      InverseBit(ENetSign_Analysis);
   }
   //------------------------------------------------------------
   // <T>修改分析。</T>
   MO_INLINE void AnalysisModify(TBool flag){
      ModifyBit(ENetSign_Analysis, flag);
   }
   //------------------------------------------------------------
   // <T>清除分析。</T>
   MO_INLINE void AnalysisClear(){
      ClearBit(ENetSign_Analysis);
   }
public:
   //------------------------------------------------------------
   // <T>获得跟踪。</T>
   MO_INLINE TBool IsTrack(){
      return GetBit(ENetSign_Track);
   }
   //------------------------------------------------------------
   // <T>获得跟踪。</T>
   MO_INLINE TBool IsNotTrack(){
      return !GetBit(ENetSign_Track);
   }
   //------------------------------------------------------------
   // <T>设置跟踪。</T>
   MO_INLINE void TrackSet(){
      return SetBit(ENetSign_Track);
   }
   //------------------------------------------------------------
   // <T>反转跟踪。</T>
   MO_INLINE void TrackInverse(){
      InverseBit(ENetSign_Track);
   }
   //------------------------------------------------------------
   // <T>修改跟踪。</T>
   MO_INLINE void TrackModify(TBool flag){
      ModifyBit(ENetSign_Track, flag);
   }
   //------------------------------------------------------------
   // <T>清除跟踪。</T>
   MO_INLINE void TrackClear(){
      ClearBit(ENetSign_Track);
   }
public:
   TSize Capacity();
   void Assign(TNetSignSet& set);
   void Parse(TCharC* pValue);
   void ParseValue(TCharC* pValue);
   TChar* ToString(TChar* pValue, TSize capacity);
   TFsText ToString();
};

//============================================================
// <T>网络包标志枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetSign{
public:
   static ENetSign Parse(TCharC* pValue);
   static ENetSign ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetSign value);
   static TCharC* ToString(ENetSign value);
};

//============================================================
// <T>消息类型枚举定义。</T>
//
// @enum
//============================================================
enum ENetMessage{
   ENetMessage_Unknown        = 0, // 未知
   ENetMessage_Request        = 1, // 请求
   ENetMessage_Response       = 2, // 应答
   ENetMessage_Notify         = 3, // 通知
};

//============================================================
// <T>消息类型枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetMessage{
public:
   static ENetMessage Parse(TCharC* pValue);
   static ENetMessage ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetMessage value);
   static TCharC* ToString(ENetMessage value);
};

//============================================================
// <T>消息标志枚举定义。</T>
//
// @enum
//============================================================
enum ENetMessageSign{
   ENetMessageSign_Unknown        = 0, // 未知
   ENetMessageSign_Tcp            = 1, // TCP支持
   ENetMessageSign_Udp            = 2, // UDP支持
   ENetMessageSign_Thread         = 3, // 线程支持
};

//============================================================
// <T>消息标志枚举类。</T>
//
// @class
//============================================================
struct MO_CR_DECLARE TNetMessageSignSet : TBitSet<TUint32>{
public:
   //------------------------------------------------------------
   // <T>获得未知。</T>
   MO_INLINE TBool IsUnknown(){
      return GetBit(ENetMessageSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>获得未知。</T>
   MO_INLINE TBool IsNotUnknown(){
      return !GetBit(ENetMessageSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>设置未知。</T>
   MO_INLINE void UnknownSet(){
      return SetBit(ENetMessageSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>反转未知。</T>
   MO_INLINE void UnknownInverse(){
      InverseBit(ENetMessageSign_Unknown);
   }
   //------------------------------------------------------------
   // <T>修改未知。</T>
   MO_INLINE void UnknownModify(TBool flag){
      ModifyBit(ENetMessageSign_Unknown, flag);
   }
   //------------------------------------------------------------
   // <T>清除未知。</T>
   MO_INLINE void UnknownClear(){
      ClearBit(ENetMessageSign_Unknown);
   }
public:
   //------------------------------------------------------------
   // <T>获得TCP支持。</T>
   MO_INLINE TBool IsTcp(){
      return GetBit(ENetMessageSign_Tcp);
   }
   //------------------------------------------------------------
   // <T>获得TCP支持。</T>
   MO_INLINE TBool IsNotTcp(){
      return !GetBit(ENetMessageSign_Tcp);
   }
   //------------------------------------------------------------
   // <T>设置TCP支持。</T>
   MO_INLINE void TcpSet(){
      return SetBit(ENetMessageSign_Tcp);
   }
   //------------------------------------------------------------
   // <T>反转TCP支持。</T>
   MO_INLINE void TcpInverse(){
      InverseBit(ENetMessageSign_Tcp);
   }
   //------------------------------------------------------------
   // <T>修改TCP支持。</T>
   MO_INLINE void TcpModify(TBool flag){
      ModifyBit(ENetMessageSign_Tcp, flag);
   }
   //------------------------------------------------------------
   // <T>清除TCP支持。</T>
   MO_INLINE void TcpClear(){
      ClearBit(ENetMessageSign_Tcp);
   }
public:
   //------------------------------------------------------------
   // <T>获得UDP支持。</T>
   MO_INLINE TBool IsUdp(){
      return GetBit(ENetMessageSign_Udp);
   }
   //------------------------------------------------------------
   // <T>获得UDP支持。</T>
   MO_INLINE TBool IsNotUdp(){
      return !GetBit(ENetMessageSign_Udp);
   }
   //------------------------------------------------------------
   // <T>设置UDP支持。</T>
   MO_INLINE void UdpSet(){
      return SetBit(ENetMessageSign_Udp);
   }
   //------------------------------------------------------------
   // <T>反转UDP支持。</T>
   MO_INLINE void UdpInverse(){
      InverseBit(ENetMessageSign_Udp);
   }
   //------------------------------------------------------------
   // <T>修改UDP支持。</T>
   MO_INLINE void UdpModify(TBool flag){
      ModifyBit(ENetMessageSign_Udp, flag);
   }
   //------------------------------------------------------------
   // <T>清除UDP支持。</T>
   MO_INLINE void UdpClear(){
      ClearBit(ENetMessageSign_Udp);
   }
public:
   //------------------------------------------------------------
   // <T>获得线程支持。</T>
   MO_INLINE TBool IsThread(){
      return GetBit(ENetMessageSign_Thread);
   }
   //------------------------------------------------------------
   // <T>获得线程支持。</T>
   MO_INLINE TBool IsNotThread(){
      return !GetBit(ENetMessageSign_Thread);
   }
   //------------------------------------------------------------
   // <T>设置线程支持。</T>
   MO_INLINE void ThreadSet(){
      return SetBit(ENetMessageSign_Thread);
   }
   //------------------------------------------------------------
   // <T>反转线程支持。</T>
   MO_INLINE void ThreadInverse(){
      InverseBit(ENetMessageSign_Thread);
   }
   //------------------------------------------------------------
   // <T>修改线程支持。</T>
   MO_INLINE void ThreadModify(TBool flag){
      ModifyBit(ENetMessageSign_Thread, flag);
   }
   //------------------------------------------------------------
   // <T>清除线程支持。</T>
   MO_INLINE void ThreadClear(){
      ClearBit(ENetMessageSign_Thread);
   }
public:
   TSize Capacity();
   void Assign(TNetMessageSignSet& set);
   void Parse(TCharC* pValue);
   void ParseValue(TCharC* pValue);
   TChar* ToString(TChar* pValue, TSize capacity);
   TFsText ToString();
};

//============================================================
// <T>消息标志枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetMessageSign{
public:
   static ENetMessageSign Parse(TCharC* pValue);
   static ENetMessageSign ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetMessageSign value);
   static TCharC* ToString(ENetMessageSign value);
};

//============================================================
// <T>网络消息服务类型枚举定义。</T>
//
// @enum
//============================================================
enum ENetMessageService{
   ENetMessageService_Logic          = 0, // 逻辑
   ENetMessageService_Login          = 1, // 登录
   ENetMessageService_Refresh        = 2, // 刷新
};

//============================================================
// <T>网络消息服务类型枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetMessageService{
public:
   static ENetMessageService Parse(TCharC* pValue);
   static ENetMessageService ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetMessageService value);
   static TCharC* ToString(ENetMessageService value);
};

//============================================================
// <T>网络终端枚举定义。</T>
//
// @enum
//============================================================
enum ENetTerminal{
   ENetTerminal_Unknown        = 0, // 未知
   ENetTerminal_Client         = 1, // 客户端
   ENetTerminal_ServerTransfer = 2, // 服务器传输
   ENetTerminal_GlobalTransfer = 3, // 全局传输
   ENetTerminal_GlobalProxy    = 4, // 全局代理
   ENetTerminal_GlobalDomain   = 5, // 全局主域
   ENetTerminal_GlobalLogin    = 6, // 全局登陆
   ENetTerminal_GlobalGate     = 7, // 全局网关
   ENetTerminal_GlobalStorage  = 8, // 全局存储
   ENetTerminal_GameProxy      = 9, // 游戏代理
   ENetTerminal_GameDomain     = 10, // 游戏主域
   ENetTerminal_GameGate       = 11, // 游戏网关
   ENetTerminal_GameLogin      = 12, // 游戏登录
   ENetTerminal_GameScene      = 13, // 游戏场景
   ENetTerminal_GameBattle     = 14, // 游戏战斗
   ENetTerminal_GameStorage    = 15, // 游戏存储
   ENetTerminal_GameChat       = 16, // 游戏聊天
   ENetTerminal_GameLogger     = 17, // 游戏日志
   ENetTerminal_GameWebInterface = 18, // 外网对接服务器
   ENetTerminal_Count,
};

//============================================================
// <T>网络终端代码枚举定义。</T>
//
// @enum
//============================================================
enum ENetTerminalCode{
   // @member 未知
   ENetTerminal_UNK = ENetTerminal_Unknown,
   // @member 客户端
   ENetTerminal_CLT = ENetTerminal_Client,
   // @member 服务器传输
   ENetTerminal_STS = ENetTerminal_ServerTransfer,
   // @member 全局传输
   ENetTerminal_ATS = ENetTerminal_GlobalTransfer,
   // @member 全局代理
   ENetTerminal_APX = ENetTerminal_GlobalProxy,
   // @member 全局主域
   ENetTerminal_ADM = ENetTerminal_GlobalDomain,
   // @member 全局登陆
   ENetTerminal_ALG = ENetTerminal_GlobalLogin,
   // @member 全局网关
   ENetTerminal_AGT = ENetTerminal_GlobalGate,
   // @member 全局存储
   ENetTerminal_ASR = ENetTerminal_GlobalStorage,
   // @member 游戏代理
   ENetTerminal_GPX = ENetTerminal_GameProxy,
   // @member 游戏主域
   ENetTerminal_GDM = ENetTerminal_GameDomain,
   // @member 游戏网关
   ENetTerminal_GGT = ENetTerminal_GameGate,
   // @member 游戏登录
   ENetTerminal_GLG = ENetTerminal_GameLogin,
   // @member 游戏场景
   ENetTerminal_GSC = ENetTerminal_GameScene,
   // @member 游戏战斗
   ENetTerminal_GBT = ENetTerminal_GameBattle,
   // @member 游戏存储
   ENetTerminal_GSR = ENetTerminal_GameStorage,
   // @member 游戏聊天
   ENetTerminal_GCT = ENetTerminal_GameChat,
   // @member 游戏日志
   ENetTerminal_GLO = ENetTerminal_GameLogger,
   // @member 外网对接服务器
   ENetTerminal_GWI = ENetTerminal_GameWebInterface,
};

//============================================================
// <T>网络终端枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetTerminal{
public:
   static ENetTerminal Parse(TCharC* pValue);
   static ENetTerminal ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetTerminal value);
   static TCharC* ToString(ENetTerminal value);
};

//============================================================
// <T>路由标志枚举定义。</T>
//
// @enum
//============================================================
enum ENetRouterFlag{
   ENetRouterFlag_Valid          = 0, // 有效
   ENetRouterFlag_Disconnect     = 1, // 断开链接
};

//============================================================
// <T>路由标志枚举类。</T>
//
// @class
//============================================================
struct MO_CR_DECLARE TNetRouterFlagSet : TBitSet<TUint32>{
public:
   //------------------------------------------------------------
   // <T>获得有效。</T>
   MO_INLINE TBool IsValid(){
      return GetBit(ENetRouterFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>获得有效。</T>
   MO_INLINE TBool IsNotValid(){
      return !GetBit(ENetRouterFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>设置有效。</T>
   MO_INLINE void ValidSet(){
      return SetBit(ENetRouterFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>反转有效。</T>
   MO_INLINE void ValidInverse(){
      InverseBit(ENetRouterFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>修改有效。</T>
   MO_INLINE void ValidModify(TBool flag){
      ModifyBit(ENetRouterFlag_Valid, flag);
   }
   //------------------------------------------------------------
   // <T>清除有效。</T>
   MO_INLINE void ValidClear(){
      ClearBit(ENetRouterFlag_Valid);
   }
public:
   //------------------------------------------------------------
   // <T>获得断开链接。</T>
   MO_INLINE TBool IsDisconnect(){
      return GetBit(ENetRouterFlag_Disconnect);
   }
   //------------------------------------------------------------
   // <T>获得断开链接。</T>
   MO_INLINE TBool IsNotDisconnect(){
      return !GetBit(ENetRouterFlag_Disconnect);
   }
   //------------------------------------------------------------
   // <T>设置断开链接。</T>
   MO_INLINE void DisconnectSet(){
      return SetBit(ENetRouterFlag_Disconnect);
   }
   //------------------------------------------------------------
   // <T>反转断开链接。</T>
   MO_INLINE void DisconnectInverse(){
      InverseBit(ENetRouterFlag_Disconnect);
   }
   //------------------------------------------------------------
   // <T>修改断开链接。</T>
   MO_INLINE void DisconnectModify(TBool flag){
      ModifyBit(ENetRouterFlag_Disconnect, flag);
   }
   //------------------------------------------------------------
   // <T>清除断开链接。</T>
   MO_INLINE void DisconnectClear(){
      ClearBit(ENetRouterFlag_Disconnect);
   }
public:
   TSize Capacity();
   void Assign(TNetRouterFlagSet& set);
   void Parse(TCharC* pValue);
   void ParseValue(TCharC* pValue);
   TChar* ToString(TChar* pValue, TSize capacity);
   TFsText ToString();
};

//============================================================
// <T>路由标志枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumNetRouterFlag{
public:
   static ENetRouterFlag Parse(TCharC* pValue);
   static ENetRouterFlag ParseCode(TCharC* pValue);
   static TCharC* ToCode(ENetRouterFlag value);
   static TCharC* ToString(ENetRouterFlag value);
};

//============================================================
// <T>收集模式枚举定义。</T>
//
// @enum
//============================================================
enum ECrAllocMode{
   ECrAllocMode_Create         = 0, // 动态创建
   ECrAllocMode_Link           = 1, // 静态链接
};

//============================================================
// <T>收集模式枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumCrAllocMode{
public:
   static ECrAllocMode Parse(TCharC* pValue);
   static ECrAllocMode ParseCode(TCharC* pValue);
   static TCharC* ToCode(ECrAllocMode value);
   static TCharC* ToString(ECrAllocMode value);
};

//============================================================
// <T>对象状态枚举定义。</T>
//
// @enum
//============================================================
enum EGmObjectStatus{
   EGmObjectStatus_Unknown        = 0, // 未知
   EGmObjectStatus_Valid          = 1, // 有效
   EGmObjectStatus_Active         = 2, // 激活
   EGmObjectStatus_Frozen         = 3, // 冻结
};

//============================================================
// <T>对象状态枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumGmObjectStatus{
public:
   static EGmObjectStatus Parse(TCharC* pValue);
   static EGmObjectStatus ParseCode(TCharC* pValue);
   static TCharC* ToCode(EGmObjectStatus value);
   static TCharC* ToString(EGmObjectStatus value);
};

//============================================================
// <T>更改更新枚举定义。</T>
//
// @enum
//============================================================
enum EGmObjectChange{
   EGmObjectChange_Unknown        = 0, // 未知
   EGmObjectChange_Unchanged      = 1, // 未更改
   EGmObjectChange_Create         = 2, // 创建
   EGmObjectChange_Delete         = 3, // 删除
   EGmObjectChange_Change         = 4, // 更改
   EGmObjectChange_Receive        = 5, // 获得
};

//============================================================
// <T>更改更新枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumGmObjectChange{
public:
   static EGmObjectChange Parse(TCharC* pValue);
   static EGmObjectChange ParseCode(TCharC* pValue);
   static TCharC* ToCode(EGmObjectChange value);
   static TCharC* ToString(EGmObjectChange value);
};

//============================================================
// <T>对象标志枚举定义。</T>
//
// @enum
//============================================================
enum EGmObjectFlag{
   EGmObjectFlag_Create         = 0, // 创建
   EGmObjectFlag_Valid          = 1, // 有效
   EGmObjectFlag_Lock           = 2, // 锁定
};

//============================================================
// <T>对象标志枚举类。</T>
//
// @class
//============================================================
struct MO_CR_DECLARE TGmObjectFlagSet : TBitSet<TUint32>{
public:
   //------------------------------------------------------------
   // <T>获得创建。</T>
   MO_INLINE TBool IsCreate(){
      return GetBit(EGmObjectFlag_Create);
   }
   //------------------------------------------------------------
   // <T>获得创建。</T>
   MO_INLINE TBool IsNotCreate(){
      return !GetBit(EGmObjectFlag_Create);
   }
   //------------------------------------------------------------
   // <T>设置创建。</T>
   MO_INLINE void CreateSet(){
      return SetBit(EGmObjectFlag_Create);
   }
   //------------------------------------------------------------
   // <T>反转创建。</T>
   MO_INLINE void CreateInverse(){
      InverseBit(EGmObjectFlag_Create);
   }
   //------------------------------------------------------------
   // <T>修改创建。</T>
   MO_INLINE void CreateModify(TBool flag){
      ModifyBit(EGmObjectFlag_Create, flag);
   }
   //------------------------------------------------------------
   // <T>清除创建。</T>
   MO_INLINE void CreateClear(){
      ClearBit(EGmObjectFlag_Create);
   }
public:
   //------------------------------------------------------------
   // <T>获得有效。</T>
   MO_INLINE TBool IsValid(){
      return GetBit(EGmObjectFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>获得有效。</T>
   MO_INLINE TBool IsNotValid(){
      return !GetBit(EGmObjectFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>设置有效。</T>
   MO_INLINE void ValidSet(){
      return SetBit(EGmObjectFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>反转有效。</T>
   MO_INLINE void ValidInverse(){
      InverseBit(EGmObjectFlag_Valid);
   }
   //------------------------------------------------------------
   // <T>修改有效。</T>
   MO_INLINE void ValidModify(TBool flag){
      ModifyBit(EGmObjectFlag_Valid, flag);
   }
   //------------------------------------------------------------
   // <T>清除有效。</T>
   MO_INLINE void ValidClear(){
      ClearBit(EGmObjectFlag_Valid);
   }
public:
   //------------------------------------------------------------
   // <T>获得锁定。</T>
   MO_INLINE TBool IsLock(){
      return GetBit(EGmObjectFlag_Lock);
   }
   //------------------------------------------------------------
   // <T>获得锁定。</T>
   MO_INLINE TBool IsNotLock(){
      return !GetBit(EGmObjectFlag_Lock);
   }
   //------------------------------------------------------------
   // <T>设置锁定。</T>
   MO_INLINE void LockSet(){
      return SetBit(EGmObjectFlag_Lock);
   }
   //------------------------------------------------------------
   // <T>反转锁定。</T>
   MO_INLINE void LockInverse(){
      InverseBit(EGmObjectFlag_Lock);
   }
   //------------------------------------------------------------
   // <T>修改锁定。</T>
   MO_INLINE void LockModify(TBool flag){
      ModifyBit(EGmObjectFlag_Lock, flag);
   }
   //------------------------------------------------------------
   // <T>清除锁定。</T>
   MO_INLINE void LockClear(){
      ClearBit(EGmObjectFlag_Lock);
   }
public:
   TSize Capacity();
   void Assign(TGmObjectFlagSet& set);
   void Parse(TCharC* pValue);
   void ParseValue(TCharC* pValue);
   TChar* ToString(TChar* pValue, TSize capacity);
   TFsText ToString();
};

//============================================================
// <T>对象标志枚举类。</T>
//
// @reference
//============================================================
class MO_CR_DECLARE REnumGmObjectFlag{
public:
   static EGmObjectFlag Parse(TCharC* pValue);
   static EGmObjectFlag ParseCode(TCharC* pValue);
   static TCharC* ToCode(EGmObjectFlag value);
   static TCharC* ToString(EGmObjectFlag value);
};

MO_NAMESPACE_END

#endif // __MO_CR_CONSTANT_H__
