using MO.Common.IO;
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
      // 程序名称
      protected string _name;

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
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
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
         if(count > 0) {
            FFrameInfo find = _frames.Get(count - 1);
            if(find.Tick == findTick) {
               return find;
            }
         }
         FFrameInfo info = new FFrameInfo();
         info.Index = _frames.Count;
         info.Tick = findTick;
         _frames.Push(info);
         return info;
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      // @return 处理结果
      //============================================================
      public EResult Serialize(IDataOutput output) {
         // 写入属性
         output.WriteString(_name);
         // 写入帧集合
         int count = _frames.Count;
         output.WriteInt32(count);
         for(int n = 0; n < count; n++) {
            FFrameInfo frameInfo = _frames.Get(n);
            frameInfo.Serialize(output);
         }
         return EResult.Success;
      }

      //============================================================
      // <T>写入文件中。</T>
      //============================================================
      public void SaveFile(string fileName) {
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(fileName);
      }

      //============================================================
      // <T>从输入流中反序列化数据。</T>
      //
      // @param input 输入流
      // @return 处理结果
      //============================================================
      public EResult Unserialize(IDataInput input) {
         // 读取属性
         _name = input.ReadString();
         // 读取帧集合
         int count = input.ReadInt32();
         for(int n = 0; n < count; n++) {
            FFrameInfo frameInfo = new FFrameInfo();
            frameInfo.Unserialize(input);
            frameInfo.Index = _frames.Count;
            _frames.Push(frameInfo);
         }
         return EResult.Success;
      }

      //============================================================
      // <T>从文件中加载数据。</T>
      //============================================================
      public void LoadFile(string fileName) {
         Clear();
         using(FByteFile file = new FByteFile(fileName)) {
            Unserialize(file);
         }
      }

      //============================================================
      // <T>序列化数据到输出流。</T>
      //
      // @param output 输出流
      // @return 处理结果
      //============================================================
      public void Clear() {
         _frames.Clear();
      }
   }
}
