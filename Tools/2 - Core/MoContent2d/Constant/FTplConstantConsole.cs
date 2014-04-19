using MO.Common;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;

namespace MO.Content2d.Constant
{
   //============================================================
   // <T>模版常量控制台。</T>
   //============================================================
   public class FTplConstantConsole : FConsole
   {
      // 打开标志
      protected bool _opened;

      // 配置文件名称
      protected string _configFileName;

      // 常量集合
      protected FObjects<FTplConstant> _constants = new FObjects<FTplConstant>();

      //============================================================
      // <T>构造模版常量控制台。</T>
      //============================================================
      public FTplConstantConsole() {
         _name = "Content.Constant.Console";
      }

      //============================================================
      // <T>获得常量集合。</T>
      //============================================================
      public FObjects<FTplConstant> Constants {
         get { return _constants; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Property")){
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
         foreach(FTplConstant constant in _constants) {
            FXmlNode xnode = xconfig.CreateNode("Constant");
            constant.SaveConfig(xnode);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteUint16((ushort)_constants.Count);
         foreach(FTplConstant constant in _constants) {
            constant.Serialize(output);
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
         if(!RFile.Exists(_configFileName)){
            return;
         }
         if(_opened) {
            return;
         }
         using(FXmlDocument xdocument = new FXmlDocument(_configFileName)){
            FXmlNode xconfig = xdocument.Root;
            foreach(FXmlNode xnode in xconfig.Nodes) {
               if(xnode.IsName("Constant")){
                  FTplConstant constant = new FTplConstant();
                  constant.LoadConfig(xnode);
                  _constants.Push(constant);
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
         string exportFileName = RMoCommon.GetEnvironment("export.root") + @"\db\constant.db";
         SerializeFile(exportFileName);
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public void Close() {
         using (FXmlDocument xdocument = new FXmlDocument()) {
            FXmlNode xconfig = xdocument.Root;
            foreach (FTplConstant constant in _constants) {
               FXmlNode xnode = xconfig.CreateNode("Constant");
               constant.SaveConfig(xnode);
            }
            xdocument.SaveFile(_configFileName);
         }
      }
   }
}
