//============================================================
// <T>系统核心枚举定义。</T>
//
// @version 1.0.1
// @auto 代码器管理的代码，如需修改请在手动修改标出的地方修改。
//============================================================
#include "MoCrConstant.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>根据字符串获得网络包协议枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetProtocol REnumNetProtocol::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetProtocol)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否位置
   if(text.Equals("Unknown")){
      return ENetProtocol_Unknown;
   }
   // 判断是否数据
   if(text.Equals("Data")){
      return ENetProtocol_Data;
   }
   // 判断是否消息
   if(text.Equals("Message")){
      return ENetProtocol_Message;
   }
   // 判断是否路由
   if(text.Equals("Router")){
      return ENetProtocol_Router;
   }
   // 判断是否传输
   if(text.Equals("Transfer")){
      return ENetProtocol_Transfer;
   }
   // 判断是否数据包
   if(text.Equals("Package")){
      return ENetProtocol_Package;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetProtocol, value=%s)", pValue);
   return (ENetProtocol)0;
}

//============================================================
// <T>根据代码获得网络包协议枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetProtocol REnumNetProtocol::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetProtocol, code=%s)", pCode);
   return (ENetProtocol)0;
}

//============================================================
// <T>根据网络包协议枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetProtocol::ToCode(ENetProtocol value){
   return "Unknown";
}

//============================================================
// <T>根据网络包协议枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetProtocol::ToString(ENetProtocol value){
   // 获得位置
   if(ENetProtocol_Unknown == value){
      return "Unknown";
   }
   // 获得数据
   if(ENetProtocol_Data == value){
      return "Data";
   }
   // 获得消息
   if(ENetProtocol_Message == value){
      return "Message";
   }
   // 获得路由
   if(ENetProtocol_Router == value){
      return "Router";
   }
   // 获得传输
   if(ENetProtocol_Transfer == value){
      return "Transfer";
   }
   // 获得数据包
   if(ENetProtocol_Package == value){
      return "Package";
   }
   return "Unknown";
}

//============================================================
// <T>获得数据容量。</T>
//
// @return 格式化字符串
//============================================================
TSize TNetSignSet::Capacity(){
   return sizeof(TUint32);
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetSignSet::Assign(TNetSignSet& set){
   _value = set.Get();
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetSignSet::Parse(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("Unknown")){
         SetBit(ENetSign_Unknown);
         continue;
      }
      if(code.Equals("Valid")){
         SetBit(ENetSign_Valid);
         continue;
      }
      if(code.Equals("Client")){
         SetBit(ENetSign_Client);
         continue;
      }
      if(code.Equals("Debug")){
         SetBit(ENetSign_Debug);
         continue;
      }
      if(code.Equals("Analysis")){
         SetBit(ENetSign_Analysis);
         continue;
      }
      if(code.Equals("Track")){
         SetBit(ENetSign_Track);
         continue;
      }
   }
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetSignSet::ParseValue(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("0")){
         SetBit(ENetSign_Unknown);
         continue;
      }
      if(code.Equals("1")){
         SetBit(ENetSign_Valid);
         continue;
      }
      if(code.Equals("2")){
         SetBit(ENetSign_Client);
         continue;
      }
      if(code.Equals("3")){
         SetBit(ENetSign_Debug);
         continue;
      }
      if(code.Equals("4")){
         SetBit(ENetSign_Analysis);
         continue;
      }
      if(code.Equals("5")){
         SetBit(ENetSign_Track);
         continue;
      }
   }
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @param pValue 字符串
// @param capacity 数据长度
// @return 格式化字符串
//============================================================
TChar* TNetSignSet::ToString(TChar* pValue, TSize capacity){
   TStringRefer text(pValue, capacity);
   text.Append(GetBit(ENetSign_Unknown) ? "" : "_");
   text.Append(GetBit(ENetSign_Valid) ? "" : "_");
   text.Append(GetBit(ENetSign_Client) ? "" : "_");
   text.Append(GetBit(ENetSign_Debug) ? "" : "_");
   text.Append(GetBit(ENetSign_Analysis) ? "" : "_");
   text.Append(GetBit(ENetSign_Track) ? "" : "_");
   return pValue;
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @return 格式化字符串
//============================================================
TFsText TNetSignSet::ToString(){
   TFsText text;
   ToString(text.Memory(), text.Size());
   return text;
}

//============================================================
// <T>根据字符串获得网络包标志枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetSign REnumNetSign::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetSign)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否未知
   if(text.Equals("Unknown")){
      return ENetSign_Unknown;
   }
   // 判断是否有效
   if(text.Equals("Valid")){
      return ENetSign_Valid;
   }
   // 判断是否客户
   if(text.Equals("Client")){
      return ENetSign_Client;
   }
   // 判断是否调试
   if(text.Equals("Debug")){
      return ENetSign_Debug;
   }
   // 判断是否分析
   if(text.Equals("Analysis")){
      return ENetSign_Analysis;
   }
   // 判断是否跟踪
   if(text.Equals("Track")){
      return ENetSign_Track;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetSign, value=%s)", pValue);
   return (ENetSign)0;
}

//============================================================
// <T>根据代码获得网络包标志枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetSign REnumNetSign::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetSign, code=%s)", pCode);
   return (ENetSign)0;
}

//============================================================
// <T>根据网络包标志枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetSign::ToCode(ENetSign value){
   return "Unknown";
}

//============================================================
// <T>根据网络包标志枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetSign::ToString(ENetSign value){
   // 获得未知
   if(ENetSign_Unknown == value){
      return "Unknown";
   }
   // 获得有效
   if(ENetSign_Valid == value){
      return "Valid";
   }
   // 获得客户
   if(ENetSign_Client == value){
      return "Client";
   }
   // 获得调试
   if(ENetSign_Debug == value){
      return "Debug";
   }
   // 获得分析
   if(ENetSign_Analysis == value){
      return "Analysis";
   }
   // 获得跟踪
   if(ENetSign_Track == value){
      return "Track";
   }
   return "Unknown";
}

//============================================================
// <T>根据字符串获得消息类型枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetMessage REnumNetMessage::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetMessage)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否未知
   if(text.Equals("Unknown")){
      return ENetMessage_Unknown;
   }
   // 判断是否请求
   if(text.Equals("Request")){
      return ENetMessage_Request;
   }
   // 判断是否应答
   if(text.Equals("Response")){
      return ENetMessage_Response;
   }
   // 判断是否通知
   if(text.Equals("Notify")){
      return ENetMessage_Notify;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetMessage, value=%s)", pValue);
   return (ENetMessage)0;
}

//============================================================
// <T>根据代码获得消息类型枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetMessage REnumNetMessage::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetMessage, code=%s)", pCode);
   return (ENetMessage)0;
}

//============================================================
// <T>根据消息类型枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetMessage::ToCode(ENetMessage value){
   return "Unknown";
}

//============================================================
// <T>根据消息类型枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetMessage::ToString(ENetMessage value){
   // 获得未知
   if(ENetMessage_Unknown == value){
      return "Unknown";
   }
   // 获得请求
   if(ENetMessage_Request == value){
      return "Request";
   }
   // 获得应答
   if(ENetMessage_Response == value){
      return "Response";
   }
   // 获得通知
   if(ENetMessage_Notify == value){
      return "Notify";
   }
   return "Unknown";
}

//============================================================
// <T>获得数据容量。</T>
//
// @return 格式化字符串
//============================================================
TSize TNetMessageSignSet::Capacity(){
   return sizeof(TUint32);
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetMessageSignSet::Assign(TNetMessageSignSet& set){
   _value = set.Get();
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetMessageSignSet::Parse(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("Unknown")){
         SetBit(ENetMessageSign_Unknown);
         continue;
      }
      if(code.Equals("Tcp")){
         SetBit(ENetMessageSign_Tcp);
         continue;
      }
      if(code.Equals("Udp")){
         SetBit(ENetMessageSign_Udp);
         continue;
      }
      if(code.Equals("Thread")){
         SetBit(ENetMessageSign_Thread);
         continue;
      }
   }
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetMessageSignSet::ParseValue(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("0")){
         SetBit(ENetMessageSign_Unknown);
         continue;
      }
      if(code.Equals("1")){
         SetBit(ENetMessageSign_Tcp);
         continue;
      }
      if(code.Equals("2")){
         SetBit(ENetMessageSign_Udp);
         continue;
      }
      if(code.Equals("3")){
         SetBit(ENetMessageSign_Thread);
         continue;
      }
   }
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @param pValue 字符串
// @param capacity 数据长度
// @return 格式化字符串
//============================================================
TChar* TNetMessageSignSet::ToString(TChar* pValue, TSize capacity){
   TStringRefer text(pValue, capacity);
   text.Append(GetBit(ENetMessageSign_Unknown) ? "" : "_");
   text.Append(GetBit(ENetMessageSign_Tcp) ? "" : "_");
   text.Append(GetBit(ENetMessageSign_Udp) ? "" : "_");
   text.Append(GetBit(ENetMessageSign_Thread) ? "" : "_");
   return pValue;
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @return 格式化字符串
//============================================================
TFsText TNetMessageSignSet::ToString(){
   TFsText text;
   ToString(text.Memory(), text.Size());
   return text;
}

//============================================================
// <T>根据字符串获得消息标志枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetMessageSign REnumNetMessageSign::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetMessageSign)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否未知
   if(text.Equals("Unknown")){
      return ENetMessageSign_Unknown;
   }
   // 判断是否TCP支持
   if(text.Equals("Tcp")){
      return ENetMessageSign_Tcp;
   }
   // 判断是否UDP支持
   if(text.Equals("Udp")){
      return ENetMessageSign_Udp;
   }
   // 判断是否线程支持
   if(text.Equals("Thread")){
      return ENetMessageSign_Thread;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetMessageSign, value=%s)", pValue);
   return (ENetMessageSign)0;
}

//============================================================
// <T>根据代码获得消息标志枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetMessageSign REnumNetMessageSign::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetMessageSign, code=%s)", pCode);
   return (ENetMessageSign)0;
}

//============================================================
// <T>根据消息标志枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetMessageSign::ToCode(ENetMessageSign value){
   return "Unknown";
}

//============================================================
// <T>根据消息标志枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetMessageSign::ToString(ENetMessageSign value){
   // 获得未知
   if(ENetMessageSign_Unknown == value){
      return "Unknown";
   }
   // 获得TCP支持
   if(ENetMessageSign_Tcp == value){
      return "Tcp";
   }
   // 获得UDP支持
   if(ENetMessageSign_Udp == value){
      return "Udp";
   }
   // 获得线程支持
   if(ENetMessageSign_Thread == value){
      return "Thread";
   }
   return "Unknown";
}

//============================================================
// <T>根据字符串获得网络消息服务类型枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetMessageService REnumNetMessageService::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetMessageService)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否逻辑
   if(text.Equals("Logic")){
      return ENetMessageService_Logic;
   }
   // 判断是否登录
   if(text.Equals("Login")){
      return ENetMessageService_Login;
   }
   // 判断是否刷新
   if(text.Equals("Refresh")){
      return ENetMessageService_Refresh;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetMessageService, value=%s)", pValue);
   return (ENetMessageService)0;
}

//============================================================
// <T>根据代码获得网络消息服务类型枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetMessageService REnumNetMessageService::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetMessageService, code=%s)", pCode);
   return (ENetMessageService)0;
}

//============================================================
// <T>根据网络消息服务类型枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetMessageService::ToCode(ENetMessageService value){
   return "Unknown";
}

//============================================================
// <T>根据网络消息服务类型枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetMessageService::ToString(ENetMessageService value){
   // 获得逻辑
   if(ENetMessageService_Logic == value){
      return "Logic";
   }
   // 获得登录
   if(ENetMessageService_Login == value){
      return "Login";
   }
   // 获得刷新
   if(ENetMessageService_Refresh == value){
      return "Refresh";
   }
   return "Unknown";
}

//============================================================
// <T>根据字符串获得网络终端枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetTerminal REnumNetTerminal::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetTerminal)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否未知
   if(text.Equals("Unknown")){
      return ENetTerminal_Unknown;
   }
   // 判断是否客户端
   if(text.Equals("Client")){
      return ENetTerminal_Client;
   }
   // 判断是否服务器传输
   if(text.Equals("ServerTransfer")){
      return ENetTerminal_ServerTransfer;
   }
   // 判断是否全局传输
   if(text.Equals("GlobalTransfer")){
      return ENetTerminal_GlobalTransfer;
   }
   // 判断是否全局代理
   if(text.Equals("GlobalProxy")){
      return ENetTerminal_GlobalProxy;
   }
   // 判断是否全局主域
   if(text.Equals("GlobalDomain")){
      return ENetTerminal_GlobalDomain;
   }
   // 判断是否全局登陆
   if(text.Equals("GlobalLogin")){
      return ENetTerminal_GlobalLogin;
   }
   // 判断是否全局网关
   if(text.Equals("GlobalGate")){
      return ENetTerminal_GlobalGate;
   }
   // 判断是否全局存储
   if(text.Equals("GlobalStorage")){
      return ENetTerminal_GlobalStorage;
   }
   // 判断是否游戏代理
   if(text.Equals("GameProxy")){
      return ENetTerminal_GameProxy;
   }
   // 判断是否游戏主域
   if(text.Equals("GameDomain")){
      return ENetTerminal_GameDomain;
   }
   // 判断是否游戏网关
   if(text.Equals("GameGate")){
      return ENetTerminal_GameGate;
   }
   // 判断是否游戏登录
   if(text.Equals("GameLogin")){
      return ENetTerminal_GameLogin;
   }
   // 判断是否游戏场景
   if(text.Equals("GameScene")){
      return ENetTerminal_GameScene;
   }
   // 判断是否游戏战斗
   if(text.Equals("GameBattle")){
      return ENetTerminal_GameBattle;
   }
   // 判断是否游戏存储
   if(text.Equals("GameStorage")){
      return ENetTerminal_GameStorage;
   }
   // 判断是否游戏聊天
   if(text.Equals("GameChat")){
      return ENetTerminal_GameChat;
   }
   // 判断是否游戏日志
   if(text.Equals("GameLogger")){
      return ENetTerminal_GameLogger;
   }
   // 判断是否外网对接服务器
   if(text.Equals("GameWebInterface")){
      return ENetTerminal_GameWebInterface;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetTerminal, value=%s)", pValue);
   return (ENetTerminal)0;
}

//============================================================
// <T>根据代码获得网络终端枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetTerminal REnumNetTerminal::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   // 判断是否未知
   if(text.Equals("UNK")){
      return ENetTerminal_Unknown;
   }
   // 判断是否客户端
   if(text.Equals("CLT")){
      return ENetTerminal_Client;
   }
   // 判断是否服务器传输
   if(text.Equals("STS")){
      return ENetTerminal_ServerTransfer;
   }
   // 判断是否全局传输
   if(text.Equals("ATS")){
      return ENetTerminal_GlobalTransfer;
   }
   // 判断是否全局代理
   if(text.Equals("APX")){
      return ENetTerminal_GlobalProxy;
   }
   // 判断是否全局主域
   if(text.Equals("ADM")){
      return ENetTerminal_GlobalDomain;
   }
   // 判断是否全局登陆
   if(text.Equals("ALG")){
      return ENetTerminal_GlobalLogin;
   }
   // 判断是否全局网关
   if(text.Equals("AGT")){
      return ENetTerminal_GlobalGate;
   }
   // 判断是否全局存储
   if(text.Equals("ASR")){
      return ENetTerminal_GlobalStorage;
   }
   // 判断是否游戏代理
   if(text.Equals("GPX")){
      return ENetTerminal_GameProxy;
   }
   // 判断是否游戏主域
   if(text.Equals("GDM")){
      return ENetTerminal_GameDomain;
   }
   // 判断是否游戏网关
   if(text.Equals("GGT")){
      return ENetTerminal_GameGate;
   }
   // 判断是否游戏登录
   if(text.Equals("GLG")){
      return ENetTerminal_GameLogin;
   }
   // 判断是否游戏场景
   if(text.Equals("GSC")){
      return ENetTerminal_GameScene;
   }
   // 判断是否游戏战斗
   if(text.Equals("GBT")){
      return ENetTerminal_GameBattle;
   }
   // 判断是否游戏存储
   if(text.Equals("GSR")){
      return ENetTerminal_GameStorage;
   }
   // 判断是否游戏聊天
   if(text.Equals("GCT")){
      return ENetTerminal_GameChat;
   }
   // 判断是否游戏日志
   if(text.Equals("GLO")){
      return ENetTerminal_GameLogger;
   }
   // 判断是否外网对接服务器
   if(text.Equals("GWI")){
      return ENetTerminal_GameWebInterface;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetTerminal, code=%s)", pCode);
   return (ENetTerminal)0;
}

//============================================================
// <T>根据网络终端枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetTerminal::ToCode(ENetTerminal value){
   // 获得未知
   if(ENetTerminal_Unknown == value){
      return "UNK";
   }
   // 获得客户端
   if(ENetTerminal_Client == value){
      return "CLT";
   }
   // 获得服务器传输
   if(ENetTerminal_ServerTransfer == value){
      return "STS";
   }
   // 获得全局传输
   if(ENetTerminal_GlobalTransfer == value){
      return "ATS";
   }
   // 获得全局代理
   if(ENetTerminal_GlobalProxy == value){
      return "APX";
   }
   // 获得全局主域
   if(ENetTerminal_GlobalDomain == value){
      return "ADM";
   }
   // 获得全局登陆
   if(ENetTerminal_GlobalLogin == value){
      return "ALG";
   }
   // 获得全局网关
   if(ENetTerminal_GlobalGate == value){
      return "AGT";
   }
   // 获得全局存储
   if(ENetTerminal_GlobalStorage == value){
      return "ASR";
   }
   // 获得游戏代理
   if(ENetTerminal_GameProxy == value){
      return "GPX";
   }
   // 获得游戏主域
   if(ENetTerminal_GameDomain == value){
      return "GDM";
   }
   // 获得游戏网关
   if(ENetTerminal_GameGate == value){
      return "GGT";
   }
   // 获得游戏登录
   if(ENetTerminal_GameLogin == value){
      return "GLG";
   }
   // 获得游戏场景
   if(ENetTerminal_GameScene == value){
      return "GSC";
   }
   // 获得游戏战斗
   if(ENetTerminal_GameBattle == value){
      return "GBT";
   }
   // 获得游戏存储
   if(ENetTerminal_GameStorage == value){
      return "GSR";
   }
   // 获得游戏聊天
   if(ENetTerminal_GameChat == value){
      return "GCT";
   }
   // 获得游戏日志
   if(ENetTerminal_GameLogger == value){
      return "GLO";
   }
   // 获得外网对接服务器
   if(ENetTerminal_GameWebInterface == value){
      return "GWI";
   }
   return "Unknown";
}

//============================================================
// <T>根据网络终端枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetTerminal::ToString(ENetTerminal value){
   // 获得未知
   if(ENetTerminal_Unknown == value){
      return "Unknown";
   }
   // 获得客户端
   if(ENetTerminal_Client == value){
      return "Client";
   }
   // 获得服务器传输
   if(ENetTerminal_ServerTransfer == value){
      return "ServerTransfer";
   }
   // 获得全局传输
   if(ENetTerminal_GlobalTransfer == value){
      return "GlobalTransfer";
   }
   // 获得全局代理
   if(ENetTerminal_GlobalProxy == value){
      return "GlobalProxy";
   }
   // 获得全局主域
   if(ENetTerminal_GlobalDomain == value){
      return "GlobalDomain";
   }
   // 获得全局登陆
   if(ENetTerminal_GlobalLogin == value){
      return "GlobalLogin";
   }
   // 获得全局网关
   if(ENetTerminal_GlobalGate == value){
      return "GlobalGate";
   }
   // 获得全局存储
   if(ENetTerminal_GlobalStorage == value){
      return "GlobalStorage";
   }
   // 获得游戏代理
   if(ENetTerminal_GameProxy == value){
      return "GameProxy";
   }
   // 获得游戏主域
   if(ENetTerminal_GameDomain == value){
      return "GameDomain";
   }
   // 获得游戏网关
   if(ENetTerminal_GameGate == value){
      return "GameGate";
   }
   // 获得游戏登录
   if(ENetTerminal_GameLogin == value){
      return "GameLogin";
   }
   // 获得游戏场景
   if(ENetTerminal_GameScene == value){
      return "GameScene";
   }
   // 获得游戏战斗
   if(ENetTerminal_GameBattle == value){
      return "GameBattle";
   }
   // 获得游戏存储
   if(ENetTerminal_GameStorage == value){
      return "GameStorage";
   }
   // 获得游戏聊天
   if(ENetTerminal_GameChat == value){
      return "GameChat";
   }
   // 获得游戏日志
   if(ENetTerminal_GameLogger == value){
      return "GameLogger";
   }
   // 获得外网对接服务器
   if(ENetTerminal_GameWebInterface == value){
      return "GameWebInterface";
   }
   return "Unknown";
}

//============================================================
// <T>获得数据容量。</T>
//
// @return 格式化字符串
//============================================================
TSize TNetRouterFlagSet::Capacity(){
   return sizeof(TUint32);
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetRouterFlagSet::Assign(TNetRouterFlagSet& set){
   _value = set.Get();
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetRouterFlagSet::Parse(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("Valid")){
         SetBit(ENetRouterFlag_Valid);
         continue;
      }
      if(code.Equals("Disconnect")){
         SetBit(ENetRouterFlag_Disconnect);
         continue;
      }
   }
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TNetRouterFlagSet::ParseValue(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("0")){
         SetBit(ENetRouterFlag_Valid);
         continue;
      }
      if(code.Equals("1")){
         SetBit(ENetRouterFlag_Disconnect);
         continue;
      }
   }
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @param pValue 字符串
// @param capacity 数据长度
// @return 格式化字符串
//============================================================
TChar* TNetRouterFlagSet::ToString(TChar* pValue, TSize capacity){
   TStringRefer text(pValue, capacity);
   text.Append(GetBit(ENetRouterFlag_Valid) ? "" : "_");
   text.Append(GetBit(ENetRouterFlag_Disconnect) ? "" : "_");
   return pValue;
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @return 格式化字符串
//============================================================
TFsText TNetRouterFlagSet::ToString(){
   TFsText text;
   ToString(text.Memory(), text.Size());
   return text;
}

//============================================================
// <T>根据字符串获得路由标志枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ENetRouterFlag REnumNetRouterFlag::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ENetRouterFlag)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否有效
   if(text.Equals("Valid")){
      return ENetRouterFlag_Valid;
   }
   // 判断是否断开链接
   if(text.Equals("Disconnect")){
      return ENetRouterFlag_Disconnect;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetRouterFlag, value=%s)", pValue);
   return (ENetRouterFlag)0;
}

//============================================================
// <T>根据代码获得路由标志枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ENetRouterFlag REnumNetRouterFlag::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ENetRouterFlag, code=%s)", pCode);
   return (ENetRouterFlag)0;
}

//============================================================
// <T>根据路由标志枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumNetRouterFlag::ToCode(ENetRouterFlag value){
   return "Unknown";
}

//============================================================
// <T>根据路由标志枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumNetRouterFlag::ToString(ENetRouterFlag value){
   // 获得有效
   if(ENetRouterFlag_Valid == value){
      return "Valid";
   }
   // 获得断开链接
   if(ENetRouterFlag_Disconnect == value){
      return "Disconnect";
   }
   return "Unknown";
}

//============================================================
// <T>根据字符串获得收集模式枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
ECrAllocMode REnumCrAllocMode::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (ECrAllocMode)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否动态创建
   if(text.Equals("Create")){
      return ECrAllocMode_Create;
   }
   // 判断是否静态链接
   if(text.Equals("Link")){
      return ECrAllocMode_Link;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=ECrAllocMode, value=%s)", pValue);
   return (ECrAllocMode)0;
}

//============================================================
// <T>根据代码获得收集模式枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
ECrAllocMode REnumCrAllocMode::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=ECrAllocMode, code=%s)", pCode);
   return (ECrAllocMode)0;
}

//============================================================
// <T>根据收集模式枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumCrAllocMode::ToCode(ECrAllocMode value){
   return "Unknown";
}

//============================================================
// <T>根据收集模式枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumCrAllocMode::ToString(ECrAllocMode value){
   // 获得动态创建
   if(ECrAllocMode_Create == value){
      return "Create";
   }
   // 获得静态链接
   if(ECrAllocMode_Link == value){
      return "Link";
   }
   return "Unknown";
}

//============================================================
// <T>根据字符串获得对象状态枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
EGmObjectStatus REnumGmObjectStatus::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (EGmObjectStatus)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否未知
   if(text.Equals("Unknown")){
      return EGmObjectStatus_Unknown;
   }
   // 判断是否有效
   if(text.Equals("Valid")){
      return EGmObjectStatus_Valid;
   }
   // 判断是否激活
   if(text.Equals("Active")){
      return EGmObjectStatus_Active;
   }
   // 判断是否冻结
   if(text.Equals("Frozen")){
      return EGmObjectStatus_Frozen;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=EGmObjectStatus, value=%s)", pValue);
   return (EGmObjectStatus)0;
}

//============================================================
// <T>根据代码获得对象状态枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
EGmObjectStatus REnumGmObjectStatus::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=EGmObjectStatus, code=%s)", pCode);
   return (EGmObjectStatus)0;
}

//============================================================
// <T>根据对象状态枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumGmObjectStatus::ToCode(EGmObjectStatus value){
   return "Unknown";
}

//============================================================
// <T>根据对象状态枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumGmObjectStatus::ToString(EGmObjectStatus value){
   // 获得未知
   if(EGmObjectStatus_Unknown == value){
      return "Unknown";
   }
   // 获得有效
   if(EGmObjectStatus_Valid == value){
      return "Valid";
   }
   // 获得激活
   if(EGmObjectStatus_Active == value){
      return "Active";
   }
   // 获得冻结
   if(EGmObjectStatus_Frozen == value){
      return "Frozen";
   }
   return "Unknown";
}

//============================================================
// <T>根据字符串获得更改更新枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
EGmObjectChange REnumGmObjectChange::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (EGmObjectChange)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否未知
   if(text.Equals("Unknown")){
      return EGmObjectChange_Unknown;
   }
   // 判断是否未更改
   if(text.Equals("Unchanged")){
      return EGmObjectChange_Unchanged;
   }
   // 判断是否创建
   if(text.Equals("Create")){
      return EGmObjectChange_Create;
   }
   // 判断是否删除
   if(text.Equals("Delete")){
      return EGmObjectChange_Delete;
   }
   // 判断是否更改
   if(text.Equals("Change")){
      return EGmObjectChange_Change;
   }
   // 判断是否获得
   if(text.Equals("Receive")){
      return EGmObjectChange_Receive;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=EGmObjectChange, value=%s)", pValue);
   return (EGmObjectChange)0;
}

//============================================================
// <T>根据代码获得更改更新枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
EGmObjectChange REnumGmObjectChange::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=EGmObjectChange, code=%s)", pCode);
   return (EGmObjectChange)0;
}

//============================================================
// <T>根据更改更新枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumGmObjectChange::ToCode(EGmObjectChange value){
   return "Unknown";
}

//============================================================
// <T>根据更改更新枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumGmObjectChange::ToString(EGmObjectChange value){
   // 获得未知
   if(EGmObjectChange_Unknown == value){
      return "Unknown";
   }
   // 获得未更改
   if(EGmObjectChange_Unchanged == value){
      return "Unchanged";
   }
   // 获得创建
   if(EGmObjectChange_Create == value){
      return "Create";
   }
   // 获得删除
   if(EGmObjectChange_Delete == value){
      return "Delete";
   }
   // 获得更改
   if(EGmObjectChange_Change == value){
      return "Change";
   }
   // 获得获得
   if(EGmObjectChange_Receive == value){
      return "Receive";
   }
   return "Unknown";
}

//============================================================
// <T>获得数据容量。</T>
//
// @return 格式化字符串
//============================================================
TSize TGmObjectFlagSet::Capacity(){
   return sizeof(TUint32);
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TGmObjectFlagSet::Assign(TGmObjectFlagSet& set){
   _value = set.Get();
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TGmObjectFlagSet::Parse(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("Create")){
         SetBit(EGmObjectFlag_Create);
         continue;
      }
      if(code.Equals("Valid")){
         SetBit(EGmObjectFlag_Valid);
         continue;
      }
      if(code.Equals("Lock")){
         SetBit(EGmObjectFlag_Lock);
         continue;
      }
   }
}

//============================================================
// <T>接受对方数据内容。</T>
//
// @param set 集合对象
//============================================================
void TGmObjectFlagSet::ParseValue(TCharC* pValue){
   _value = 0;
   TFsStringToken token(pValue, ',');
   TInt count = token.Count();
   for(TInt n = 0; n < count; n ++){
      TFsCode code = token.Get(n);
      if(code.Equals("0")){
         SetBit(EGmObjectFlag_Create);
         continue;
      }
      if(code.Equals("1")){
         SetBit(EGmObjectFlag_Valid);
         continue;
      }
      if(code.Equals("2")){
         SetBit(EGmObjectFlag_Lock);
         continue;
      }
   }
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @param pValue 字符串
// @param capacity 数据长度
// @return 格式化字符串
//============================================================
TChar* TGmObjectFlagSet::ToString(TChar* pValue, TSize capacity){
   TStringRefer text(pValue, capacity);
   text.Append(GetBit(EGmObjectFlag_Create) ? "C" : "_");
   text.Append(GetBit(EGmObjectFlag_Valid) ? "V" : "_");
   text.Append(GetBit(EGmObjectFlag_Lock) ? "L" : "_");
   return pValue;
}

//============================================================
// <T>获得格式化字符串。</T>
//
// @return 格式化字符串
//============================================================
TFsText TGmObjectFlagSet::ToString(){
   TFsText text;
   ToString(text.Memory(), text.Size());
   return text;
}

//============================================================
// <T>根据字符串获得对象标志枚举类。</T>
//
// @param pValue 字符串
// @return 枚举类
//============================================================
EGmObjectFlag REnumGmObjectFlag::Parse(TCharC* pValue){
   // 判断是否需要解析
   if(RInt::IsInteger(pValue)){
      return (EGmObjectFlag)RInt::Parse(pValue);
   }
   // 获得枚举值
   TFsText text = pValue;
   // 判断是否创建
   if(text.Equals("Create")){
      return EGmObjectFlag_Create;
   }
   // 判断是否有效
   if(text.Equals("Valid")){
      return EGmObjectFlag_Valid;
   }
   // 判断是否锁定
   if(text.Equals("Lock")){
      return EGmObjectFlag_Lock;
   }
   MO_STATIC_FATAL("Unknown enum value. (enum=EGmObjectFlag, value=%s)", pValue);
   return (EGmObjectFlag)0;
}

//============================================================
// <T>根据代码获得对象标志枚举类。</T>
//
// @param pCode 代码
// @return 枚举类
//============================================================
EGmObjectFlag REnumGmObjectFlag::ParseCode(TCharC* pCode){
   TFsText text = pCode;
   MO_STATIC_FATAL("Unknown enum value. (enum=EGmObjectFlag, code=%s)", pCode);
   return (EGmObjectFlag)0;
}

//============================================================
// <T>根据对象标志枚举类获得代码。</T>
//
// @param value 枚举类
// @return 代码
//============================================================
TCharC* REnumGmObjectFlag::ToCode(EGmObjectFlag value){
   return "Unknown";
}

//============================================================
// <T>根据对象标志枚举类获得字符串。</T>
//
// @param value 枚举类
// @return 字符串
//============================================================
TCharC* REnumGmObjectFlag::ToString(EGmObjectFlag value){
   // 获得创建
   if(EGmObjectFlag_Create == value){
      return "Create";
   }
   // 获得有效
   if(EGmObjectFlag_Valid == value){
      return "Valid";
   }
   // 获得锁定
   if(EGmObjectFlag_Lock == value){
      return "Lock";
   }
   return "Unknown";
}

MO_NAMESPACE_END
