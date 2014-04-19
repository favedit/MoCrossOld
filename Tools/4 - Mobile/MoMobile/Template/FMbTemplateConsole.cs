using MO.Common;
using MO.Common.Content;
using MO.Common.System;
using MoMobile.Template.Enemy;

namespace MoMobile.Template
{
   //============================================================
   // <T>模版控制台。</T>
   //============================================================
   public class FMbTemplateConsole : FConsole
   {
      // 实例化对象
      protected static FMbTemplateConsole _instance;

      // 目录
      protected string _directory;

      // 模版集合
      protected FMbEnemyConsole _enemyConsole = new FMbEnemyConsole();

      //============================================================
      // <T>构造模版集合控制台。</T>   
      //============================================================
      public FMbTemplateConsole() {
         _name = "Content.Template.Console";
      }

      //============================================================
      // <T>实例化模版集合。</T>   
      //============================================================
      public static FMbTemplateConsole Instance {
         get {
            if (null == _instance) {
               _instance = new FMbTemplateConsole();
            }
            return _instance;
         }
      }

      //============================================================
      // <T>获得模版集合。</T>   
      //============================================================
      public FMbEnemyConsole EnemyConsole {
         get { return _enemyConsole; }
      }

      //============================================================
      // <T>获得或设置路径</T>
      //============================================================
      public string Directory {
         get { return _directory; }
         set { _directory = value; }
      }

      //============================================================
      // <T>加载配置信息</T>
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         // 加载属性
         foreach (FXmlNode node in xconfig.Nodes) {
            string name = node.Get("name");
            if ("template_directory" == name) {
               string text = node.Text;
               _directory = RMoCommon.ParseEnvironment(text);
            }
         }
         // 加载配置
         LoadConsole();
      }

      //============================================================
      // <T>加载控制台</T>
      //============================================================
      public void LoadConsole() {
         // 加载敌机控制台
         _enemyConsole.LoadTemplateConfig(_directory);
      }
   }
}
