using MO.Common;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>模版样式控制台。</T>
   //============================================================
   public class FTplStyleConsole : FConsole
   {
      // 打开标志
      protected bool _opened;

      // 配置文件名称
      protected string _configFileName;

      // 样式集合
      protected FObjects<FTplStyle> _styles = new FObjects<FTplStyle>();

      //============================================================
      // <T>构造模版样式控制台。</T>
      //============================================================
      public FTplStyleConsole() {
         _name = "Content.Style.Console";
      }

      //============================================================
      // <T>获得样式集合。</T>
      //============================================================
      public FObjects<FTplStyle> Styles {
         get { return _styles; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Property")) {
               if (xnode.IsAttribute("name", "config_file")) {
                  _configFileName = RMoCommon.ParseEnvironment(xnode.Text);
               }
            }
         }
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         foreach(FTplStyle style in _styles) {
            FXmlNode xnode = xconfig.CreateNode("Style");
            style.SaveConfig(xnode);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteUint16((ushort)_styles.Count);
         foreach(FTplStyle style in _styles) {
            style.Serialize(output);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void SerializeFile(string fileName) {
         FByteFile file = new FByteFile();
         Serialize(file);
         file.SaveFile(fileName);
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public void Open() {
         if (!RFile.Exists(_configFileName)) {
            return;
         }
         if (_opened) {
            return;
         }
         using (FXmlDocument xdocument = new FXmlDocument(_configFileName)) {
            FXmlNode xconfig = xdocument.Root;
            foreach (FXmlNode xnode in xconfig.Nodes) {
               if (xnode.IsName("Style")) {
                  FTplStyle style = new FTplStyle();
                  style.LoadConfig(xnode);
                  _styles.Push(style);
               }
            }
         }
         _opened = true;
      }

      //============================================================
      // <T>存储处理。</T>
      //============================================================
      public void Export() {
         Open();
         string exportFileName = RMoCommon.GetEnvironment("export.root") + @"\db\style.db";
         SerializeFile(exportFileName);
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public void Close() {
         using (FXmlDocument xdocument = new FXmlDocument()) {
            FXmlNode xconfig = xdocument.Root;
            foreach (FTplStyle style in _styles) {
               FXmlNode xnode = xconfig.CreateNode("Style");
               style.SaveConfig(xnode);
            }
            xdocument.SaveFile(_configFileName);
         }
      }
   }
}
