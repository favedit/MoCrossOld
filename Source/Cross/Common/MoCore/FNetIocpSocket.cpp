#include "MoCrNetIocp.h"

MO_NAMESPACE_BEGIN

#ifdef _MO_WINDOWS

//============================================================
// <T>构造客户链接。</T>
//============================================================
FNetIocpSocket::FNetIocpSocket(){
   RMemory::Clear(&_data, sizeof(SIocpData));
   _data.pSocket = this;
}

//============================================================
// <T>构造客户链接。</T>
//============================================================
SIocpData& FNetIocpSocket::Data(){
   return _data;
}

//============================================================
// <T>开始网络链接。</T>
//============================================================
TBool FNetIocpSocket::Startup(){
   // 检查是否启动
   if(_processing){
      return EFalse;
   }
   // 设置内容
   TDateTime tick = RDateTime::Current();
   _isConnected = ETrue;
   _info.createDateTime = tick;
   _info.updateDateTime = tick;
   _info.receiveSerial = 0;
   _info.receiveDateTime = tick;
   _info.receiveTick = 0;
   _info.sendSerial = 0;
   _info.sendDateTime = tick;
   // 设置缓冲大小
   SetReceiveBufferSize(_info.receiveBufferSize);
   SetSendBufferSize(_info.sendBufferSize);
   // 增加到池中
   MO_ASSERT(_pReceivePool);
   _pReceivePool->Add(this, 0);
   _processing = ETrue;
   return ETrue;
}

//============================================================
// <T>关闭网络链接。</T>
//============================================================
TBool FNetIocpSocket::Shutdown(){
   // 检查是否启动
   if(!_processing){
      return EFalse;
   }
   // 从池中删除
   _pReceivePool->Remove(this, 0);
   MO_CLEAR(_pReceivePool);
   // 重置信息
   _pInputPipe->Reset();
   _pOutputPipe->Reset();
   // 关闭对象
   Close();
   _processing = EFalse;
   return ETrue;
}

#endif // _MO_WINDOWS

MO_NAMESPACE_END
