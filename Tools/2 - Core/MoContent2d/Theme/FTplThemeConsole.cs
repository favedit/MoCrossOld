using MO.Common;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.System;

namespace MO.Content2d.Theme
{
   //============================================================
   // <T>主题控制台。</T>
   //============================================================
   public class FTplThemeConsole : FConsole
   {
      // 打开标志
      protected bool _opened;

      // 配置路径
      protected string _configDirectory;

      // 激活的主题
      protected FTplTheme _activeTheme;

      // 常量集合
      protected FDictionary<FTplTheme> _themes = new FDictionary<FTplTheme>();

      //============================================================
      // <T>构造主题控制台。</T>
      //============================================================
      public FTplThemeConsole() {
         _name = "Content.Theme.Console";
      }

      //============================================================
      // <T>获得主题集合。</T>
      //============================================================
      public FDictionary<FTplTheme> Themes {
         get { return _themes; }
      }

      //============================================================
      // <T>根据名称获得主题。</T>
      //
      // @param name 名称
      // @return 主题
      //============================================================
      public FTplTheme FindTheme(string name) {
         return _themes.Find(name);
      }

      //============================================================
      // <T>根据样式名称获得样式。</T>
      //
      // @param styleName 样式名称
      // @return 样式
      //============================================================
      public FTplThemeStyle FindActiveStyle(string styleName) {
         if(_activeTheme == null){
            return null;
         }
         FTplThemeStyle style = _activeTheme.FindStyle(styleName);
         return style;
      }

      //============================================================
      // <T>根据样式名称获得样式。</T>
      //
      // @param styleName 样式名称
      // @param propertyName 样式名称
      // @return 样式
      //============================================================
      public FTplThemeStyleProperty FindActiveProperty(string styleName, string propertyName) {
         if (_activeTheme == null) {
            return null;
         }
         FTplThemeStyle style = _activeTheme.FindStyle(styleName);
         if (style == null) {
            return null;
         }
         FTplThemeStyleProperty property = style.FindProperty(propertyName);
         return property;
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
               if (xnode.IsAttribute("name", "config_directory")) {
                  _configDirectory = RMoCommon.ParseEnvironment(xnode.Text);
               }
            }
         }
      }

      //============================================================
      // <T>打开处理。</T>
      //============================================================
      public void Open() {
         if (!RDirectory.Exists(_configDirectory)) {
            return;
         }
         if(_opened) {
            return;
         }
         // 打开文件
         foreach(string fileName in RDirectory.ListFiles(_configDirectory)){
            // 检查类型
            if (!fileName.EndsWith(".xml")) {
               continue;
            }
            // 创建主题
            FTplTheme theme = new FTplTheme();
            theme.FileName = fileName;
            using (FXmlDocument xdocument = new FXmlDocument(fileName)) {
               theme.LoadConfig(xdocument.Root);
            }
            _themes.Set(theme.Name, theme);
            // 设置默认主题
            if (_activeTheme == null) {
               _activeTheme = theme;
            }
         }
         _opened = true;
      }

      //============================================================
      // <T>关闭处理。</T>
      //============================================================
      public void Close() {
         foreach (INamePair<FTplTheme> pair in _themes) {
            FTplTheme theme = pair.Value;
            using (FXmlDocument xdocument = new FXmlDocument()) {
               theme.SaveConfig(xdocument.Root);
               xdocument.SaveFile(theme.FileName);
            }
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         output.WriteUint16((ushort)_themes.Count);
         foreach (INamePair<FTplTheme> pair in _themes) {
            pair.Value.Serialize(output);
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
      // <T>存储处理。</T>
      //============================================================
      public void Export() {
         Open();
         string exportFileName = RMoCommon.GetEnvironment("export.root") + @"\db\theme.db";
         SerializeFile(exportFileName);
      }
   }
}
