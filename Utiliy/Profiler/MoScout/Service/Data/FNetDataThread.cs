using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.Net.Sockets;
using MO.Common.System;
using MoScout.Core;
using MoScout.Core.Logger;
using System.Text;

namespace MO.Scout.Service.Data
{
   //============================================================
   // <T>数据线程。</T>
   //============================================================
   public class FNetDataThread : FThread
   {
      // 数据服务
      internal FNetDataService _service;

      // 应用信息
      internal FApplicationInfo _applicationInfo;

      // 处理间隔
      protected int _interval = 10;

      // 网络
      protected FSocket _socket;

      // 数据流
      protected FBytes _socketData = new FBytes();

      // 数据流
      protected FByteStream _data = new FByteStream();

      //============================================================
      // <T>构造数据线程。</T>
      //============================================================
      public FNetDataThread() {
      }

      //============================================================
      // <T>获得或设置网络端口。</T>
      //============================================================
      public FSocket Socket {
         get { return _socket; }
         set { _socket = value; }
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public override void OnProcess() {
         FBytes data = new FBytes();
         // 异步处理
         while(!IsStop) {
            // 接收数据
            int length = _socket.Input.ReadAll(data);
            if(length == -1) {
               break;
            }
            if(length > 0) {
               lock(_socketData) {
                  _socketData.Append(data);
               }
            }
            data.Clear();
         }
         // 移除端口线程
         _service.SocketThreadRemove(this);
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public void ProcessMessage() {
         // 读取头信息
         int length = _data.ReadInt32();
         int type = _data.ReadUint8();
         int level = _data.ReadUint8();
         long tick = _data.ReadInt64();
         int dataLength = length - 14;
         // 读取数据
         byte[] data = new byte[dataLength];
         _data.Read(data, 0, dataLength);
         string message = Encoding.ASCII.GetString(data);
         // 创建日志
         FLoggerInfo loggerInfo = new FLoggerInfo();
         loggerInfo.Tick = tick;
         loggerInfo.Level = level;
         loggerInfo.Message = message;
         // 放入应用中
         FFrameInfo info = _applicationInfo.SyncFrame(tick);
         info.Loggers.Push(loggerInfo);
      }

      //============================================================
      // <T>执行处理。</T>
      //============================================================
      public void ProcessMessages() {
         // 读取所有网络数据
         lock(_socketData) {
            _data.Append(_socketData);
            _socketData.Clear();
         }
         // 处理消息
         if(_data.Length > 0) {
            while(true) {
               // 检查最小长度
               if(_data.Remain <= RInt.BYTE_SIZE) {
                  break;
               }
               // 测试数据是否完整
               int peekLength = _data.PeekInt32();
               if(_data.Remain >= peekLength) {
                  ProcessMessage();
               }
            }
            int position = _data.Position;
            if(position > 0) {
               _data.Erase(0, position);
               _data.Position = 0;
            }
         }
      }
   }
}
