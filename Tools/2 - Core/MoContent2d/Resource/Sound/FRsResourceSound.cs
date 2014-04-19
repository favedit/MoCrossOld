using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Common;
using MO.Content2d.Resource.Common;
using System.IO;

namespace MO.Content2d.Resource.Sound
{
   //============================================================
   // <T>设计声音资源。</T>
   //============================================================
   public class FRsResourceSound : FRsResource
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FRsResourceSound));

      // 文件名称
      protected string _fileName;

      // 文件大小
      protected uint _length;

      //============================================================
      // <T>构造设计声音资源。</T>
      //============================================================
      public FRsResourceSound() {
         _typeCd = ERsResource.Sound;
         _typeName = ERsResource.SoundCode;
      }

      //============================================================
      // <T>获得或设置文件名称。</T>
      //============================================================
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得或设置文件大小。</T>
      //============================================================
      public uint Length {
         get { return _length; }
         set { _length = value; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
      }

      //============================================================
      // <T>保存设置信息。<T>      
      // 
      // @param xconfig 设置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         // 设置文件长度
         xconfig.Set("length", _length);
         // 设置文件名称
         xconfig.Set("file_name", RFile.GetFileName(_fileName));
      }

      //============================================================
      // <T>序列化信息。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
      }

      //============================================================
      // <T>解压当前信息。</T>
      //
      // @param input 输入流
      //============================================================
      public override void Unserialize(IInput input) {
         base.Unserialize(input);
      }

      //============================================================
      // <T>扫描资源。</T>
      //============================================================
      public override void Scan() {
         base.Scan();
         _optionValid = true;
      }
      
      //============================================================
      // <T>打开资源。</T>
      //============================================================
      public override void Open() {
         if (!_optionValid) {
            base.Open();
            // 打开图片资源
            _length = (uint)new FileInfo(_fileName).Length;
         }
      }

      //============================================================
      // <T>根据指定模式导出数据。</T>
      //
      // @param modeCd 导出模式
      //============================================================
      public override void Export(ERsExportMode modeCd){
         // 打开资源
         Open();
         //............................................................
         string exportDirectory = RContent2dManager.ResourceConsole.ExportDirectory + "\\sd";
         //............................................................
         FByteFile file = new FByteFile();
         file.LoadFile(_fileName);
         file.SaveFile(exportDirectory + "\\" + Code + ".mp3");
         //............................................................
         _logger.Debug(this, "Export", "Export sound resource. (type_name={0}, code={1})", _typeName, _code);
      }

      //============================================================
      // <T>获得字符串信息。<T>
      //
      // @return 字符串信息
      //============================================================      
      public override string ToString() {
         return "Sound (code=" + _code + ")";
      }
   }
}
