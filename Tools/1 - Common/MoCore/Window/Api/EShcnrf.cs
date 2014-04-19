using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EShcnrf {

      /// <summary>
      /// 接收来自文件系统的中断级别通知消息。
      /// </summary>
      InterruptLevel = 0x0001,

      /// <summary>
      /// 接收来自Shell的Shell级别通知消息。 
      /// </summary>
      ShellLevel = 0x0002,

      /// <summary>
      /// 接收目录下所有子目录的中断事件。
      /// 此标志必须和SHCNRF_InterruptLevel 标志合在一起使用。
      /// 当使用该标志时，必须同时设置对应的SHChangeNotifyEntry结构体中的
      ///   fRecursive成员为TRUE（此结构体由函数的最后一个参数pfsne指向），
      ///   这样通知消息在目录树上是递归的。
      /// </summary>
      RecursiveInterrupt = 0x1000,

      /// <summary>
      /// 接收到的消息使用共享内存。
      /// 必须先调用SHChangeNotification_Lock，然后才能存取实际的数据，
      ///   完成后调用SHChangeNotification_Unlock函数释放内存。
      /// </summary>
      NewDelivery = 0x8000

   }
}
