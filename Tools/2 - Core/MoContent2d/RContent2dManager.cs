using MO.Common.Content;
using MO.Content2d.Constant;
using MO.Content2d.Content;
using MO.Content2d.Style;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;

namespace MO.Content2d
{
   //============================================================
   // <T>资源管理器。</T>
   //============================================================
   public class RContent2dManager
   {
      // 分割图片平均像素
      public static int CompressBlockSplit = 1024 * 64;

      // 常量控制台
      protected static FTplConstantConsole _constantConsole = new FTplConstantConsole();

      // 样式控制台
      protected static FTplStyleConsole _styleConsole = new FTplStyleConsole();

      // 资源控制台
      protected static FRsResourceConsole _resourceConsole = new FRsResourceConsole();

      // 主题控制台
      protected static FTplThemeConsole _themeConsole = new FTplThemeConsole();

      // 界面控制台
      protected static FRcFrameConsole _frameConsole = new FRcFrameConsole();

      //============================================================
      // <T>获取模版常量控制台。</T>
      //============================================================
      public static FTplConstantConsole ConstantConsole {
         get { return _constantConsole; }
      }

      //============================================================
      // <T>获取模版样式控制台。</T>
      //============================================================
      public static FTplStyleConsole StyleConsole {
         get { return _styleConsole; }
      }

      //============================================================
      // <T>获得资源控制台。</T>
      //============================================================
      public static FRsResourceConsole ResourceConsole {
         get { return _resourceConsole; }
      }

      //============================================================
      // <T>获得主题控制台。</T>
      //============================================================
      public static FTplThemeConsole ThemeConsole {
         get { return _themeConsole; }
      }

      //============================================================
      // <T>获得界面控制台。</T>
      //============================================================
      public static FRcFrameConsole FrameConsole {
         get { return _frameConsole; }
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      //============================================================
      public static void LoadConfig(FXmlNode xconfig) {
         // 读取节点配置
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Console")) {
               string name = xnode.Get("name");
               // 读取常量配置
               if (name == _constantConsole.Name) {
                  _constantConsole.LoadConfig(xnode);
               }
               // 读取样式配置
               if (name == _styleConsole.Name) {
                  _styleConsole.LoadConfig(xnode);
               }
               // 读取资源配置
               if (name == _resourceConsole.Name) {
                  _resourceConsole.LoadConfig(xnode);
               }
               // 读取主题配置
               if (name == _themeConsole.Name) {
                  _themeConsole.LoadConfig(xnode);
               }
               // 读取界面配置
               if (name == _frameConsole.Name) {
                  _frameConsole.LoadConfig(xnode);
               }
            }
         }
      }

      //============================================================
      // <T>配置处理。</T>
      //============================================================
      public static void Setup() {
         // 打开常量管理器
         _constantConsole.Open();
         // 打开样式管理器
         _styleConsole.Open();
         // 打开资源控制台
         _resourceConsole.Open();
         // 打开主题控制台
         _themeConsole.Open();
         // 打开界面控制台
         _frameConsole.Open();
      }

      //============================================================
      // <T>释放处理。</T>
      //============================================================
      public static void Release() {
      }
   }
}
