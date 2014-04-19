namespace MO.Common.Lang
{
   //============================================================
   // <T>日志级别。</T>
   //============================================================
   public enum ELoggerLevel : int
   {
      Print = 0x01,

      Debug = 0x02,

      Info = 0x04,

      Warn = 0x08,

      Error = 0x10,

      Fatal = 0x20,

      NoDebug = 0xFC,

      All = 0xFF
   }
}
