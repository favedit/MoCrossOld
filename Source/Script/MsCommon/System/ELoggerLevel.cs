namespace MS.Common.System
{
   //============================================================
   // <T>日志级别。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public enum ELoggerLevel : int
   {
      // 打印级别
      Print = 0x01,

      // 调试级别
      Debug = 0x02,

      // 信息级别
      Info = 0x04,

      // 警告级别
      Warn = 0x08,

      // 错误级别
      Error = 0x10,

      // 例外级别
      Fatal = 0x20,

      // 无调试
      NoDebug = 0xFC,

      // 全部
      All = 0xFF
   }
}
