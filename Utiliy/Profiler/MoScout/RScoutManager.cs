using MO.Scout.Service.Data;
using MoScout.Core;

namespace MO.Scout
{
   //============================================================
   // <T>效率管理器。</T>
   //============================================================
   public class RScoutManager
   {
      // 信息控制台
      protected static FInfoConsole _infoConsole = new FInfoConsole();

      // 消息服务
      protected static FNetDataService _messageService = new FNetDataService();

      //============================================================
      // <T>获得信息控制台。</T>
      //============================================================
      public static FInfoConsole InfoConsole {
         get { return _infoConsole; }
      }

      //============================================================
      // <T>获得消息服务。</T>
      //============================================================
      public static FNetDataService MessageService {
         get { return _messageService; }
      }

      //============================================================
      // <T>配置处理。</T>
      //============================================================
      public static void Setup() {
      }

      //============================================================
      // <T>开始处理。</T>
      //============================================================
      public static void Startup() {
         _messageService.Startup();
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public static void Shutdown() {
         _messageService.Shutdown();
      }
   }
}
