using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Core.Hook {

   [Flags]
   public enum EHookType {

      // 监视放入系统消息队列中的输入消息。对于记录宏很有用
      JournalRecord = 0,

      // 发送一个已经被WH_JOURNALRECORD类型的钩子记录的消息
      JournalPlayback = 1,

      // 监视按键消息
      Keyboard = 2,

      // 监视放入消息列表中的消息
      GetMessage = 3,

      // 在消息被发送到目的窗口处理程序之前，处理消息
      CallWndProc = 4,

      // 对于基于计算机的训练程序，取得对程序有用的通知
      CBT = 5,

      // 监视输入事件的结果消息，这些输入事件在对话框，消息框，菜单，滚动条上。
      // 钩子监视同一桌面上的所有程序的此类消息
      SysMsgFilter = 6,

      // 监视鼠标消息
      Mouse = 7,

      Hardware = 8,

      // 用于debug其他钩子程序
      Debug = 9,

      // 接受对于shell程序有用的通知
      Shell = 10,

      // 程序最先的线程将要转为空闲状态时，钩子程序被调用。在空闲时，执行低优先级的程序，此类钩子很有用
      ForegroundIdle = 11,

      // 在消息被目标窗口处理程序处理之后，处理消息
      CallWndProcRet = 12,

      // 监视低层的键盘输入事件
      KeyboardLL = 13,

      // 监视低层的鼠标输入事件
      MouseLL = 14

   }
}
