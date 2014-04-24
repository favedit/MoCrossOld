using System;

namespace MO.Common.Lang
{
    //============================================================
    // <T>时刻工具类。</T>
    //
    // @history MAOCY 140422
    //============================================================
    public class RTimeTick
    {
        // 启动时间
        protected static DateTime _startDateTime = DateTime.Now;

        //============================================================
        // <T>获得启动到现在的毫秒数。</T>
        //
        // @return 字符串
        //============================================================
        public static int CurrentMillisecond {
            get { return (int)DateTime.Now.Subtract(_startDateTime).TotalMilliseconds; }
        }

        //============================================================
        // <T>获得启动到现在的毫秒数。</T>
        //
        // @return 字符串
        //============================================================
        public static long CurrentLongMillisecond {
            get { return (long)DateTime.Now.Subtract(_startDateTime).TotalMilliseconds; }
        }
    }
}
