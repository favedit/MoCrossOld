using MO.Common.Lang;

namespace MoScout.Core
{
   //============================================================
   // <T>应用信息。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class FApplicationInfo : FObject
   {
      // 帧集合
      protected FObjects<FFrameInfo> _frames = new FObjects<FFrameInfo>();

      //============================================================
      // <T>构造应用信息。</T>
      //
      // @history MAOCY 140414
      //============================================================
      public FApplicationInfo() { 
      }

      //============================================================
      // <T>获得帧集合。</T>
      //============================================================
      public FObjects<FFrameInfo> Frames {
         get { return _frames; }
      }

      //============================================================
      // <T>获得指定时间的帧对象。</T>
      //============================================================
      public FFrameInfo SyncFrame(long tick) {
         long findTick = tick / 1000000;
         int count = _frames.Count;
         if(count > 0){
            FFrameInfo find = _frames.Get(count - 1);
            if(find.Tick == findTick) {
               return find;
            }
         }
         FFrameInfo info = new FFrameInfo();
         info.Tick = findTick;
         _frames.Push(info);
         return info;
      }
   }
}
